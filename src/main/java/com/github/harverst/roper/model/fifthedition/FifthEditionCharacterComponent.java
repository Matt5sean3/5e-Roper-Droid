package com.github.harverst.roper.model.fifthedition;

import com.github.harverst.roper.model.Character;
import com.github.harverst.roper.model.ScoreComponent;
import com.github.harverst.roper.model.CharacterComponent;
import com.github.harverst.roper.model.common.BasicScoreComponent;
import com.github.harverst.roper.model.fifthedition.FifthEditionScoreType;
import com.github.harverst.roper.model.fifthedition.FifthEditionPhase;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.Map;
import java.util.EnumMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.lang.String;
import java.io.FileReader;



public abstract class FifthEditionCharacterComponent
  implements CharacterComponent<FifthEditionScoreType, FifthEditionPhase>
{
  public static CharacterComponent<FifthEditionScoreType, FifthEditionPhase>
    fromJson(String source)
    throws JSONException
  {
    JSONObject obj = new JSONObject(source);
    return new FifthEditionCharacterJsonComponent(obj);
  }
  public static CharacterComponent<FifthEditionScoreType, FifthEditionPhase> 
    fromJsonFile(String filename)
    throws JSONException
  {
    // Open the json file and read all the contents
    return fromJson(filename);
  }
}

class FifthEditionCharacterJsonComponent extends FifthEditionCharacterComponent
{
  Map<FifthEditionScoreType, 
    List<ScoreComponent<FifthEditionPhase> > > components;
  public FifthEditionCharacterJsonComponent(JSONObject json)
  {
    for(FifthEditionScoreType t : FifthEditionScoreType.values())
    {
      List<ScoreComponent<FifthEditionPhase> > subList = 
        new ArrayList();
      try
      {
        JSONObject subjson = json.getJSONObject(t.name());
        // given no 
        
        for(String name : t.getNames())
        {
          try
          {
            subList.add(BasicScoreComponent.fromJson(
              FifthEditionPhase.class,
              subjson.getJSONObject(name)));
          }
          catch(JSONException e)
          {
            // emplace null
            subList.add(null);
          }
        }
      }
      catch(JSONException e)
      {
        // Don't do anything
      }
      components.put(t, subList);
    }
  }
  public void composite(Character<FifthEditionScoreType, FifthEditionPhase> c)
  {
    
  }
  public void seperate(Character<FifthEditionScoreType, FifthEditionPhase> c)
  {
    
  }
}

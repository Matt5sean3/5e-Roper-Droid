package com.github.harverst.roper.model.common;

import com.github.harverst.roper.model.Character;
import com.github.harverst.roper.model.CharacterComponent;
import com.github.harverst.roper.model.ScoreComponent;
import com.github.harverst.roper.model.ScoreGroupType;

import java.lang.Enum;
import java.lang.String;
import java.lang.Class;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import static java.util.EnumSet.allOf;

import org.json.JSONObject;
import org.json.JSONException;

// TODO: relying on things behaving as Enums isn't ideal.
// TODO: before pulling into Harverst switch from "extends Enum" to "extends <SomeBasicInterfaceIMakeUp>"

public abstract class BasicCharacterComponent
{
  public static <P> CharacterComponent<P>
    fromJson(List<ScoreGroupType> scoreGroups, Map<String, P> phases, String source)
    throws JSONException
  {
    JSONObject obj = new JSONObject(source);
    return new BasicCharacterJsonComponent(scoreGroups, phases, obj);
  }
  public static <P> CharacterComponent<P>
    fromFile(List<ScoreGroupType> scoreGroups, List<P> phases, String source)
    throws JSONException
  {
    // TODO: implement
    return null;
  }
}

/**
 * 
 */
class BasicCharacterJsonComponent<P>
  implements CharacterComponent<P>
{
  // Not sure how to make this part work now
  Map<ScoreGroupType, List<ScoreComponent<P> > > scoreComponents;
  public BasicCharacterJsonComponent(List<ScoreGroupType> scoreGroups, 
    Map<String, P> phaseMap, JSONObject json)
  {
    scoreComponents = new HashMap();
    for(ScoreGroupType scoreGroup : scoreGroups)
    {
      try
      {
        List<ScoreComponent<P> > subList = new ArrayList();
        // Require that score groups have a name
        JSONObject subjson = json.getJSONObject(scoreGroup.getName());
        // Require that score groups have a listing of their scores' names
        for(String scoreName : scoreGroup.getScoreNames())
        {
          //
          try
          {
            subList.add(BasicScoreComponent.fromJson(phaseMap, 
              subjson.getJSONObject(scoreName)));
          }
          catch(JSONException e)
          {
            // Add a null to hold the place in the list
            subList.add(null);
          }
        }
        scoreComponents.put(scoreGroup, subList);
      }
      catch(JSONException e)
      {
        // Just not present, don't worry
      }
    }
  }
  
  public void composite(Character<P> c)
  {
    for(Map.Entry<ScoreGroupType, List<ScoreComponent<P> > > component : 
      scoreComponents.entrySet())
    {
      c.addGroupComponent(component.getKey(), component.getValue());
    }
  }
  public void seperate(Character<P> c)
  {
    for(Map.Entry<ScoreGroupType, List<ScoreComponent<P> > > component : 
      scoreComponents.entrySet())
    {
      c.removeGroupComponent(component.getKey(), component.getValue());
    }
  }
}


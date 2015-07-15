package com.github.harverst.roper.model.common;

import com.github.harverst.roper.model.ScoreGroupType;
import com.github.harverst.roper.model.ScoreComponent;
import com.github.harverst.roper.model.CharacterComponent;
import com.github.harverst.roper.model.Character;
import com.github.harverst.roper.model.PhaseOrder;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import java.io.InputStream;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONException;

import static java.util.Arrays.asList;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.MAX_VALUE;
import java.lang.NumberFormatException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BasicCharacterComponentTest
{
  @Test
  public void simpleConstructor()
  {
    Set<ScoreGroupType> scoreTypes = new HashSet();
    String slots[] = {
      "A",
      "B",
      "C",
      "D",
      "E"
    };
    ScoreGroupType groupType = new BasicScoreGroupType("Q", asList(slots));
    scoreTypes.add(groupType);
    
    List<ScoreComponent<Integer> > scoreComponents = new ArrayList();
    scoreComponents.add(new AdditiveScoreComponent(0, 1));
    scoreComponents.add(null);
    scoreComponents.add(null);
    scoreComponents.add(new AdditiveScoreComponent(0, 2));
    scoreComponents.add(null);
    
    Map<ScoreGroupType, List<ScoreComponent<Integer> > > components = 
      new HashMap();
    components.put(groupType, scoreComponents);
    
    PhaseOrder<Integer> order = new LowToHighPhaseOrder();
    Character<Integer> charac = new BasicCharacter(scoreTypes);
    CharacterComponent<Integer> comp = new BasicCharacterComponent(components);

    comp.composite(charac);
    assertEquals(1, charac.getScore(groupType, 0).getValue(order));
    assertEquals(0, charac.getScore(groupType, 1).getValue(order));
    assertEquals(0, charac.getScore(groupType, 2).getValue(order));
    assertEquals(2, charac.getScore(groupType, 3).getValue(order));
    assertEquals(0, charac.getScore(groupType, 4).getValue(order));
    
    comp.seperate(charac);
    assertEquals(0, charac.getScore(groupType, 0).getValue(order));
    assertEquals(0, charac.getScore(groupType, 1).getValue(order));
    assertEquals(0, charac.getScore(groupType, 2).getValue(order));
    assertEquals(0, charac.getScore(groupType, 3).getValue(order));
    assertEquals(0, charac.getScore(groupType, 4).getValue(order));
  }
  
  @Test
  public void fromJson()
  {
    String source = new String();
    byte data[] = new byte[1024];
    int nread = 0;
    try
    {
      InputStream is = getClass().getResourceAsStream(
        "/json/character_component.json");
      nread = is.read(data);
      assertEquals(178, nread);
    }
    catch(IOException e)
    {
      fail(e.getMessage());
    }
    Set<ScoreGroupType> scoreTypes = new HashSet();
    String slots[] = {
      "A",
      "B",
      "C",
      "D",
      "E"
    };
    ScoreGroupType groupType = new BasicScoreGroupType("Q", asList(slots));
    scoreTypes.add(groupType);

    Character<Integer> charac = new BasicCharacter(scoreTypes);
    try
    {
      CharacterComponent<Integer> comp = BasicCharacterComponent.fromJson(
        new JSONObject(new String(data, 0, 178)), 
        scoreTypes, new StringIntegerMap());
      comp.composite(charac);
      PhaseOrder<Integer> order = new LowToHighPhaseOrder();
      assertEquals(1, charac.getScore(groupType, 0).getValue(order));
      assertEquals(0, charac.getScore(groupType, 1).getValue(order));
      assertEquals(0, charac.getScore(groupType, 2).getValue(order));
      assertEquals(2, charac.getScore(groupType, 3).getValue(order));
      assertEquals(0, charac.getScore(groupType, 4).getValue(order));

      comp.seperate(charac);
      assertEquals(0, charac.getScore(groupType, 0).getValue(order));
      assertEquals(0, charac.getScore(groupType, 1).getValue(order));
      assertEquals(0, charac.getScore(groupType, 2).getValue(order));
      assertEquals(0, charac.getScore(groupType, 3).getValue(order));
      assertEquals(0, charac.getScore(groupType, 4).getValue(order));
    }
    catch(JSONException e)
    {
      fail(e.getMessage());
    }
  }
}

package com.github.harverst.roper.model.common;

import com.github.harverst.roper.model.Character;
import com.github.harverst.roper.model.PhaseOrder;
import com.github.harverst.roper.model.ScoreComponent;
import java.lang.Integer;
import java.util.List;
import java.util.ArrayList;
import static java.util.EnumSet.allOf;

import java.io.InputStream;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;

public class BasicCharacterTest
{
  @Test
  public void hasProvidedGroups()
  {
    // uses integer keys, uses integer phase ordering
    Character<Integer> noob = 
      new BasicCharacter(allOf(TestScoreGroupType.class));
    assertEquals("First enum size is 6", 6, 
      TestScoreGroupType.ABILITIES.getSize());
    assertEquals("Second enum size is 10", 10, 
      TestScoreGroupType.SKILLS.getSize());
  }

  @Test
  public void hasScores()
  {
    Character<Integer> noob = 
      new BasicCharacter(allOf(TestScoreGroupType.class));
    PhaseOrder<Integer> order = new LowToHighPhaseOrder();
    assertEquals("ABILITY SCORE 0", 0, 
      noob.getScore(TestScoreGroupType.ABILITIES, 0).getValue(order));
    assertEquals("ABILITY SCORE 1", 0, 
      noob.getScore(TestScoreGroupType.ABILITIES, 1).getValue(order));
    assertEquals("ABILITY SCORE 2", 0, 
      noob.getScore(TestScoreGroupType.ABILITIES, 2).getValue(order));
    assertEquals("ABILITY SCORE 3", 0, 
      noob.getScore(TestScoreGroupType.ABILITIES, 3).getValue(order));
    assertEquals("ABILITY SCORE 4", 0, 
      noob.getScore(TestScoreGroupType.ABILITIES, 4).getValue(order));
    assertEquals("ABILITY SCORE 5", 0, 
      noob.getScore(TestScoreGroupType.ABILITIES, 5).getValue(order));
    assertEquals("SKILLS SCORE 0", 0, 
      noob.getScore(TestScoreGroupType.SKILLS, 0).getValue(order));
    assertEquals("SKILLS SCORE 1", 0, 
      noob.getScore(TestScoreGroupType.SKILLS, 1).getValue(order));
    assertEquals("SKILLS SCORE 2", 0, 
      noob.getScore(TestScoreGroupType.SKILLS, 2).getValue(order));
    assertEquals("SKILLS SCORE 3", 0, 
      noob.getScore(TestScoreGroupType.SKILLS, 3).getValue(order));
    assertEquals("SKILLS SCORE 4", 0, 
      noob.getScore(TestScoreGroupType.SKILLS, 4).getValue(order));
    assertEquals("SKILLS SCORE 5", 0, 
      noob.getScore(TestScoreGroupType.SKILLS, 5).getValue(order));
    assertEquals("SKILLS SCORE 6", 0, 
      noob.getScore(TestScoreGroupType.SKILLS, 6).getValue(order));
    assertEquals("SKILLS SCORE 7", 0, 
      noob.getScore(TestScoreGroupType.SKILLS, 7).getValue(order));
    assertEquals("SKILLS SCORE 8", 0, 
      noob.getScore(TestScoreGroupType.SKILLS, 8).getValue(order));
    assertEquals("SKILLS SCORE 9", 0, 
      noob.getScore(TestScoreGroupType.SKILLS, 9).getValue(order));
  }

  @Test
  public void shouldRecordModification()
  {
    Character<Integer> noob = 
      new BasicCharacter(allOf(TestScoreGroupType.class));
    List<ScoreComponent<Integer> > base = new ArrayList();
    base.add(new AdditiveScoreComponent(0, 10));
    base.add(new AdditiveScoreComponent(0, 12));
    base.add(new AdditiveScoreComponent(0, 13));
    base.add(new AdditiveScoreComponent(0, 14));
    base.add(new AdditiveScoreComponent(0, 8));
    base.add(new AdditiveScoreComponent(0, 10));
    noob.addGroupComponent(TestScoreGroupType.ABILITIES, base);
    
    PhaseOrder<Integer> order = new LowToHighPhaseOrder();
    // Check that everything changed correctly
    assertEquals("ABILITY SCORE 0 should be 10", 10, 
      noob.getScore(TestScoreGroupType.ABILITIES, 0).getValue(order));
    assertEquals("ABILITY SCORE 1 should be 12", 12, 
      noob.getScore(TestScoreGroupType.ABILITIES, 1).getValue(order));
    assertEquals("ABILITY SCORE 2 should be 13", 13, 
      noob.getScore(TestScoreGroupType.ABILITIES, 2).getValue(order));
    assertEquals("ABILITY SCORE 3 should be 14", 14, 
      noob.getScore(TestScoreGroupType.ABILITIES, 3).getValue(order));
    assertEquals("ABILITY SCORE 4 should be 8", 8, 
      noob.getScore(TestScoreGroupType.ABILITIES, 4).getValue(order));
    assertEquals("ABILITY SCORE 5 should be 10", 10, 
      noob.getScore(TestScoreGroupType.ABILITIES, 5).getValue(order));
    // Check that the change was recorded correctly
    assertEquals("Added to history", 1, noob.getHistory().size());
  }

  @Test
  public void fromJsonWorks()
  {
    try
    {
      InputStream s = getClass().getResourceAsStream("/json/score_groups.json");
      assertNotNull("JSON file existence failure", s);
      byte buffer[] = new byte[1024];
      int nread;
      String source;
      // assume reading 94 bytes
      nread = s.read(buffer);
      source = new String(buffer, 0, 94);
      JSONObject obj = new JSONObject(source);
      Character<Integer> c = BasicCharacter.fromJson(obj);
      assertEquals("json file has 1 score group", 1, c.getScoreGroupTypes().size());
    }
    catch(JSONException e)
    {
      fail("JSON: " + e.getMessage());
    }
    catch(IOException e)
    {
      fail("IO: " + e.getMessage());
    }
  }
}


package com.github.harverst.roper.model.common;

import com.github.harverst.roper.model.ScoreComponent;
import java.lang.Integer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.json.JSONObject;
import org.json.JSONException;

import org.junit.Test;

public class BasicScoreComponentTest
{
  @Test
  public void PhaseConstructorPhaseShouldBeConfigured()
  {
    ScoreComponent<Integer> basic = new TestScoreComponent(2);
    assertEquals("Phase constructor, phase should be configured", 
      new Integer(2), basic.getPhase());
  }
  @Test
  public void fromJson()
  {
    JSONObject addObj, mulObj, divObj;
    try
    {
      addObj = new JSONObject(
        "{type: \"ADDITIVE\", phase: \"0\", value: 1}");
      mulObj = new JSONObject(
        "{type: \"MULTIPLICATIVE\", phase: \"0\", value: 1}");
      divObj = new JSONObject(
        "{type: \"DIVISIVE\", phase: \"0\", value: 1}");
      ScoreComponent<Integer> json;
      
      json = BasicScoreComponent.fromJson(new StringIntegerMap(), addObj);
      assertTrue(json instanceof AdditiveScoreComponent);
      
      json = BasicScoreComponent.fromJson(new StringIntegerMap(), mulObj);
      assertTrue(json instanceof MultiplicativeScoreComponent);
      
      json = BasicScoreComponent.fromJson(new StringIntegerMap(), divObj);
      assertTrue(json instanceof DivisiveScoreComponent);
    }
    catch(JSONException e)
    {
      fail("JSON parsing failed");
    }
  }
}


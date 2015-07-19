package com.github.harverst.roper.model.common;

import com.github.harverst.roper.model.ScoreGroupType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static java.util.Arrays.asList;


public class BasicScoreGroupTypeTest
{
  @Test
  public void simpleConstructor()
  {
    String scores[] = {
      "A",
      "B",
      "C",
      "D",
      "E"
    };
    ScoreGroupType group = new BasicScoreGroupType("Q", asList(scores));
    assertEquals("Score group name should be Q", "Q", group.getName());
    assertEquals("Should have 5 scores", 5, group.getSize());
    assertEquals("First score should be A", 0, group.getIndex("A"));
    assertEquals("Second score should be B", 1, group.getIndex("B"));
    assertEquals("Third score should be C", 2, group.getIndex("C"));
    assertEquals("Fourth score should be D", 3, group.getIndex("D"));
    assertEquals("Fifth score should be E", 4, group.getIndex("E"));
    assertEquals("Provided and retrieved list must match", 
      asList(scores), group.getScoreNames());
  }
  @Test
  public void fromJson()
  {
    // Load from json
    try
    {
      InputStream s = 
        getClass().getResourceAsStream("/json/score_groups.json");
      assertNotNull("JSON file existence failure", s);
      byte buffer[] = new byte[1024];
      int nread;
      String source;
      // reads 94 bytes to the end of the file
      nread = s.read(buffer);
      // assumes 94 bytes are read
      source = new String(buffer, 0, 94);
      JSONObject obj = new JSONObject(source);
      ScoreGroupType group = BasicScoreGroupType.fromJson("Q", obj.getJSONArray("Q"));
      assertEquals("Score group name should be Q", "Q", group.getName());
      assertEquals("Should have 5 scores", 5, group.getSize());
      assertEquals("First score should be A", 0, group.getIndex("A"));
      assertEquals("Second score should be B", 1, group.getIndex("B"));
      assertEquals("Third score should be C", 2, group.getIndex("C"));
      assertEquals("Fourth score should be D", 3, group.getIndex("D"));
      assertEquals("Fifth score should be E", 4, group.getIndex("E"));
    }
    catch(IOException e)
    {
      fail("Failed input file reading");
    }
    catch(JSONException e)
    {
      fail("Failed interpreting JSON");
    }
  }
}

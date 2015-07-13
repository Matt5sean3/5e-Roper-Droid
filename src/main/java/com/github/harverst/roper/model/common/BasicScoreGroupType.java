package com.github.harverst.roper.model.common;

import com.github.harverst.roper.model.ScoreGroupType;

import java.lang.String;
import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class BasicScoreGroupType implements ScoreGroupType
{
  String name;
  List<String> scores;
  BasicScoreGroupType(String aName, List<String> scoreNames)
  {
    name = aName;
    scores = scoreNames;
  }
  public String getName()
  {
    return name;
  }
  public int getSize()
  {
    return scores.size();
  }
  public int getIndex(String name)
  {
    for(int c = 0; c < scores.size(); c++)
    {
      String score = scores.get(c);
      if(score.equals(name))
      {
        return c;
      }
    }
    return -1;
  }
  public List<String> getScoreNames()
  {
    return scores;
  }

  public static ScoreGroupType fromJson(String name, JSONArray arr)
    throws JSONException
  {
    List<String> scoreNames = new ArrayList();
    for(int c = 0; c < arr.length(); c++)
    {
      scoreNames.add(arr.getString(c));
    }
    return new BasicScoreGroupType(name, scoreNames);
  }
}


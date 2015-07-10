package com.github.harverst.roper.model;

import java.util.List;
import java.lang.String;

public interface ScoreGroupType
{
  public String getName();
  public int getSize();
  public int getIndex(String name);
  public List<String> getScoreNames();
}


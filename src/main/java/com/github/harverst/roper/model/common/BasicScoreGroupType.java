package com.github.harverst.roper.model.common;

import java.util.List;
import java.lang.String;

public interface BasicScoreGroupType
{
  public String getName();
  public int getSize();
  public int getIndex(String name);
  public List<String> getScoreNames();
}


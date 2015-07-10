package com.github.harverst.roper.model.common;

import com.github.harverst.roper.model.ScoreGroupType;
import java.util.List;
import static java.util.Arrays.asList;
import static java.lang.Enum.valueOf;

public enum TestScoreGroupType implements ScoreGroupType
{
  ABILITIES(6),
  SKILLS(10);
  private int size;
  private String[] scoreNames;
  TestScoreGroupType(int aSize)
  {
    size = aSize;
  }
  public String getName()
  {
    return name();
  }
  public int getSize()
  {
    return size;
  }
  public int getIndex(String name)
  {
    return valueOf(TestScoreGroupType.class, name).ordinal();
  }
  public List<String> getScoreNames()
  {
    return asList(scoreNames);
  }
}


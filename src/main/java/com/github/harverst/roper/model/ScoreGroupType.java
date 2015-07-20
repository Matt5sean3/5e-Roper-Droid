package com.github.harverst.roper.model;

import java.util.List;
import java.lang.String;

public interface ScoreGroupType
{
  /**
   * Retrieves the name of the score group.
   *
   * @return name of the score group.
   */
  public String getName();
  /**
   * Gets the number of scores in the group.
   *
   * @return the number of scores in the group.
   */
  public int getSize();
  /**
   * Gets the index of a named score.
   *
   * @return the index of the named score.
   */
  public int getIndex(String name);
  /**
   * Returns a list of the scores' names
   *
   * @return list of the scores' names
   */
  public List<String> getScoreNames();
}


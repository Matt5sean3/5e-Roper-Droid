package com.github.harverst.roper.model;

import java.util.List;
import java.util.Map;

public interface ScoreGroup<P> extends Map<String, Score<P> >
{
  /**
   * Adds a list of components to the contained elements.
   *
   * In implementation, if the size doesn't match, no change should be enacted.
   */
  public void addComponent(Map<String, ScoreComponent<P> > mod);
  /**
   * Removes a list of components from the contained elements.
   */
  public void removeComponent(Map<String, ScoreComponent<P> > mod);
  /**
   * Score groups must have names.
   *
   * @return the name of the score group.
   */
  public String getName();
}


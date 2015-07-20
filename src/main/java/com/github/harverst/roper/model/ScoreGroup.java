package com.github.harverst.roper.model;

import java.util.List;

public interface ScoreGroup<P> extends List<Score<P> >
{
  /**
   * Adds a list of components to the contained elements.
   *
   * In implementation, if the size doesn't match, no change should be enacted.
   */
  public void addComponent(List<ScoreComponent<P> > mod) 
    throws ScoreGroupComponentSizeMismatchException;
  /**
   * Removes a list of components from the contained elements.
   */
  public void removeComponent(List<ScoreComponent<P> > mod)
    throws ScoreGroupComponentSizeMismatchException;
  /**
   * Gets the type of the score group.
   *
   * @return type of the group.
   */
  public ScoreGroupType getType();
}


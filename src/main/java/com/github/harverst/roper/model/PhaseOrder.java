package com.github.harverst.roper.model;

import java.util.Collection;
import java.util.List;
/**
 * The parameter, P, is the type used to determine order.
 */
public interface PhaseOrder<P>
{
  /**
   * The phase order provides ordering to components of a Score
   *
   * @param original The unsorted collection
   * @return The collection after sorting into a list
   */
  public List<ScoreComponent<P>> order(Collection<ScoreComponent<P>> original);
}

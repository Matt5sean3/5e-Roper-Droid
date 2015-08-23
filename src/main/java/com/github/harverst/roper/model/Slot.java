package com.github.harverst.roper.model;

/**
 * A slot is meant to model spaces on a character sheet that are used up.
 *
 * What type of object populates a slot is generalized and may even vary
 * within a single game system.
 *
 * A set of slots as seen in many game systems.
 */
public interface Slot<C>
{
  /**
   * Whether the slot is populated.
   */
  public boolean isEmpty();
  public void populate(C contents);
  public C getContents();
}


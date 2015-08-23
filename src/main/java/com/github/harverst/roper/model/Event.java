package com.github.harverst.roper.model;

public interface Event
{
  /**
   * Returns the action that created the event.
   *
   * @return the action that created this event.
   */
  public Action getAction();
  
  /**
   * Returns the character component associated with this event.
   *
   * @return the character component associated with this event.
   */
  public CharacterComponent getComponent();
  
  /**
   * Reverts the event.
   *
   * @return whether reversion is successful.
   */
  public boolean rollback();
}


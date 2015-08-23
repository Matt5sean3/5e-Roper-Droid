package com.github.harverst.roper.model;

import java.lang.Enum;

/**
 * An action
 */
public interface Action
{
  /**
   * Gets the name of the action.
   *
   * @return name of the action.
   */
  public String getName();
  
  /**
   * Actions should have descriptions.
   *
   * @return text description of the action.
   */
  public String getDescription();

  /**
   * Perform the action
   *
   * @param actors A list of characters the action is applied to.
   */
  public void perform(List<Character> actors);
}


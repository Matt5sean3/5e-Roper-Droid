package com.github.harverst.roper.model;

/**
 * Character components work analogously to score components.
 *
 * In order to avoid complication, the order in which character components are
 * applied needs to be irrelevant to the resulting character. By and large, the
 * means to avoid order dependency is by only adding components.
 */
public interface CharacterComponent<P>
{
  /**
   * Adds information that composes this piece of the character.
   *
   * @param character The character being composited.
   */
  public void composite(Character<P> character);

  /**
   * Removes information that composes this piece of the character.
   *
   * @param character The character this component is being seperated from.
   */
  public void seperate(Character<P> character);
}


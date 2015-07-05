package com.github.harverst.roper.model.common.dice;

import com.github.harverst.roper.model.common.AdditiveScoreComponent;

import java.util.Random;

/**
 * DiceComponent
 */
public class DiceComponent<P>  extends AdditiveScoreComponent<P> {

  //TODO: Allow for better random number generation
  private static Random r = new Random();

  private boolean allow_reroll = false;

  private int sides = 0;

  public DiceComponent(P phase, int sides, boolean reroll){
    super(phase);
    this.sides = sides;
    this.setAddend(roll());
    this.allow_reroll = reroll;
  }
  public DiceComponent(P phase, int sides) {
    this(phase, sides, true);
  }
  private int roll(){
    return (sides < 0) ? -DiceComponent.r.nextInt(-sides+1)
        : DiceComponent.r.nextInt(sides+1);
  }
  @Override
  protected int getAddend(){
    if(allow_reroll) {
      this.setAddend(roll());
    }
    return super.getAddend();
  }
}

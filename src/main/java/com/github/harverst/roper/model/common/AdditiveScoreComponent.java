package com.github.harverst.roper.model.common;

public class AdditiveScoreComponent<P> extends BasicScoreComponent<P>
{
  private int addend;
  protected AdditiveScoreComponent(P aPhase) {
    super(aPhase);
  }
  public AdditiveScoreComponent(P aPhase, int anAddend)
  {
    super(aPhase);
    addend = anAddend;
  }
  protected int getAddend(){
    return addend;
  }
  protected void setAddend(int addend){
    this.addend = addend;
  }
  @Override
  public int composite(int oldValue)
  {
    return oldValue + getAddend();
  }
}


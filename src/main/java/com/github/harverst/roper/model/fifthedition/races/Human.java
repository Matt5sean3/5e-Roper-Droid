package com.github.harverst.roper.model.fifthedition.races;

import com.github.harverst.roper.model.ScoreComponent;
import com.github.harverst.roper.model.Character;
import com.github.harverst.roper.model.common.AdditiveScoreComponent;
import com.github.harverst.roper.model.fifthedition.FifthEditionRace;
import com.github.harverst.roper.model.fifthedition.FifthEditionScoreType;
import com.github.harverst.roper.model.fifthedition.FifthEditionPhase;
import com.github.harverst.roper.model.fifthedition.FifthEditionAbility;
import com.github.harverst.roper.model.fifthedition.FifthEditionSize;
import com.github.harverst.roper.model.fifthedition.FifthEditionSpeed;
import com.github.harverst.roper.model.fifthedition.FifthEditionLanguage;

import java.util.List;
import java.util.ArrayList;

public class Human extends FifthEditionRace
{
  List<ScoreComponent<FifthEditionPhase> > abilityMod;
  List<ScoreComponent<FifthEditionPhase> > speedMod;
  List<ScoreComponent<FifthEditionPhase> > langMod;
  public Human(FifthEditionLanguage lang)
  {
    abilityMod = new ArrayList();
    ScoreComponent<FifthEditionPhase> mod = 
      new AdditiveScoreComponent(FifthEditionPhase.BASE, 1);
    // Allow to adjust to change in 
    for(int c = 0; c < FifthEditionScoreType.ABILITY.getSize(); c++)
    {
      abilityMod.add(mod);
    }
    speedMod = new ArrayList();
    for(int c = 0; c < FifthEditionScoreType.SPEED.getSize(); c++)
    {
      speedMod.add(null);
    }
    speedMod.set(FifthEditionSpeed.GROUND.ordinal(), 
      new AdditiveScoreComponent(FifthEditionPhase.BASE, 30));
    langMod = new ArrayList();
    for(int c = 0; c < FifthEditionScoreType.LANGUAGE.getSize(); c++)
    {
      langMod.add(null);
    }
    langMod.set(FifthEditionLanguage.COMMON.ordinal(),
      new AdditiveScoreComponent(FifthEditionPhase.BASE, 1));
    langMod.set(lang.ordinal(),
      new AdditiveScoreComponent(FifthEditionPhase.BASE, 1));
  }
  /**
   * Performs all the actions related to being a human
   *
   * Adds one to all ability scores
   * sets size to medium
   * sets base speed to 30
   * sets common and the chosen language as being known
   */
  public void composite(Character<FifthEditionScoreType, FifthEditionPhase> c)
  {
    // increases all base ability scores by one
    c.addGroupComponent(FifthEditionScoreType.ABILITY, abilityMod);
    // sets size to medium
    
    // sets base speed to 30
    c.addGroupComponent(FifthEditionScoreType.SPEED, speedMod);
    // speaks common and one language of player's choice
    c.addGroupComponent(FifthEditionScoreType.LANGUAGE, langMod);
  }
  /**
   * Undoes all the actions related to being a human
   *
   * removes ability score increase of one
   * unsets size from medium
   * unsets base speed from 30
   * unsets common and the chosen language from being known
   */
  public void seperate(Character<FifthEditionScoreType, FifthEditionPhase> c)
  {
    // remove increase of one
    c.removeGroupComponent(FifthEditionScoreType.ABILITY, abilityMod);
    // unset medium size
    // unsets base speed from 30
    c.removeGroupComponent(FifthEditionScoreType.SPEED, speedMod);
    // unsets common and player chosen language
    c.removeGroupComponent(FifthEditionScoreType.LANGUAGE, langMod);
  }
}


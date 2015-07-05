package com.github.harverst.roper.model.fifthEdition;

import com.github.harverst.roper.model.common.BasicScoreGroupType;

// Uses some reflection
import java.lang.Class;
import static java.util.EnumSet.allOf;
/**
 * The score types present in 5e.
 */
public enum FifthEditionScoreType implements BasicScoreGroupType
{
  ABILITY (FifthEditionAbility.class),
  SAVING_THROW (FifthEditionAbility.class),
  SKILLS (FifthEditionSkill.class),
  ARMOR_CLASS (FifthEditionArmorClass.class),
  INITIATIVE (FifthEditionInitiative.class),
  SPEED (FifthEditionSpeed.class),
  HIT_DICE (FifthEditionHitDice.class),
  MAX_HIT_DICE (FifthEditionHitDice.class),
  SPELL_SLOT(FifthEditionSpellSlot.class),
  MAX_SPELL_SLOT(FifthEditionSpellSlot.class),
  SIZE(FifthEditionSize.class),
  LANGUAGE(FifthEditionLanguage.class),
  CURRENCY(FifthEditionCurrency.class);
  public int size;
  // Populates using other enums
  <E extends Enum<E> > FifthEditionScoreType(Class<E> type)
  {
    size = allOf(type).size();
  }
  public int getSize()
  {
    return size;
  }
}


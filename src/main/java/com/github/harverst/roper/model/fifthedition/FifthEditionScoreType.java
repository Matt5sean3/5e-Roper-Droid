package com.github.harverst.roper.model.fifthedition;

import com.github.harverst.roper.model.common.BasicScoreGroupType;

// Uses some reflection
import java.lang.Class;
import java.lang.String;
import java.util.Set;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import static java.util.Arrays.asList;
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
  private int size;
  private Map<String, Integer> names;
  // Populates using other enums
  <E extends Enum<E> > FifthEditionScoreType(Class<E> type)
  {
    Set<E> members = allOf(type);
    
    names = new HashMap();
    size = members.size();
    for(E member : members)
    {
      names.put(member.name(), member.ordinal());
    }
  }
  public int getSize()
  {
    return size;
  }
  public Collection<String> getNames()
  {
    return names.keySet();
  }
  public int getIndex(String name)
  {
    // using Enum.valueOf might be better
    // but I can't think of how to set it up
    return names.get(name);
  }
}


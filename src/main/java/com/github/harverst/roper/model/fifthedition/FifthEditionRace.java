package com.github.harverst.roper.model.fifthedition;

import com.github.harverst.roper.model.CharacterComponent;
import com.github.harverst.roper.model.fifthedition.FifthEditionScoreType;
import com.github.harverst.roper.model.fifthedition.FifthEditionPhase;
import java.lang.String;

public abstract class FifthEditionRace
  implements CharacterComponent<FifthEditionScoreType, FifthEditionPhase>
{
  public static CharacterComponent<FifthEditionScoreType, FifthEditionPhase>
   loadFromFile(String filename)
  {
    // TODO: implement loading race data from
    return null;
  }
  
}


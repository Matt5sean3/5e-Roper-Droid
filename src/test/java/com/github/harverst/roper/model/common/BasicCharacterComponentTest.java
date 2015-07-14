package com.github.harverst.roper.model.common;

import com.github.harverst.roper.model.ScoreGroupType;
import com.github.harverst.roper.model.ScoreComponent;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BasicCharacterComponentTest
{
  @Test
  public void simpleConstructor()
  {
    Map<ScoreGroupType, List<ScoreComponent<Integer> > > components = 
      new HashMap();
  }
}

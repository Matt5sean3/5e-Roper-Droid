package com.github.harverst.roper.model.common;

import com.github.harverst.roper.model.Score;
import com.github.harverst.roper.model.ScoreGroup;
import com.github.harverst.roper.model.ScoreComponent;
import com.github.harverst.roper.model.PhaseOrder;
import com.github.harverst.roper.model.ScoreGroupComponentSizeMismatchException;
import java.lang.Integer;
import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.asList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class BasicScoreGroupTest
{
  @Test
  public void listConstructorInitializesData()
  {
    PhaseOrder<Integer> ordering = new LowToHighPhaseOrder<Integer>();
    List<Score<Integer> > scores = new ArrayList<Score<Integer> >();
    String names[] = {"A", "B", "C", "D", "E"};
    ScoreGroup<Integer> group = new BasicScoreGroup(
      new BasicScoreGroupType("Q", asList(names)));
    assertEquals("Size of group is 5", 5, group.size());
    assertEquals("Check idx 0", 0, group.get(0).getValue(ordering));
    assertEquals("Check idx 1", 0, group.get(1).getValue(ordering));
    assertEquals("Check idx 2", 0, group.get(2).getValue(ordering));
    assertEquals("Check idx 3", 0, group.get(3).getValue(ordering));
    assertEquals("Check idx 4", 0, group.get(4).getValue(ordering));
    List<ScoreComponent<Integer> > mods = new ArrayList();
    mods.add(new AdditiveScoreComponent(0, 4));
    mods.add(new MultiplicativeScoreComponent(1, 4));
    mods.add(new DivisiveScoreComponent(2, 3));
    mods.add(null);
    mods.add(new AdditiveScoreComponent(0, 6));
    List<ScoreComponent<Integer> > mod2 = new ArrayList();
    mod2.add(null);
    mod2.add(new AdditiveScoreComponent(0, 4));
    mod2.add(new AdditiveScoreComponent(0, 3));
    mod2.add(null);
    mod2.add(null);
    try
    {
      group.addComponent(mods);
      group.addComponent(mod2);
    }
    catch(ScoreGroupComponentSizeMismatchException e)
    {
      fail("List size mismatch occurred during add");
    }
    assertEquals("Size of group is still 5", 5, group.size());
    assertEquals("Modded check idx 0", 4, group.get(0).getValue(ordering));
    assertEquals("Modded check idx 1", 16, group.get(1).getValue(ordering));
    assertEquals("Modded check idx 2", 1, group.get(2).getValue(ordering));
    assertEquals("Modded check idx 3", 0, group.get(3).getValue(ordering));
    assertEquals("Modded check idx 4", 6, group.get(4).getValue(ordering));
    try
    {
      group.removeComponent(mods);
      group.removeComponent(mod2);
    }
    catch(ScoreGroupComponentSizeMismatchException e)
    {
      fail("List size mismatch occurred during remove");
    }
    assertEquals("Size of group still 5", 5, group.size());
    assertEquals("Removed check idx 0", 0, group.get(0).getValue(ordering));
    assertEquals("Removed check idx 1", 0, group.get(1).getValue(ordering));
    assertEquals("Removed check idx 2", 0, group.get(2).getValue(ordering));
    assertEquals("Removed check idx 3", 0, group.get(3).getValue(ordering));
    assertEquals("Removed check idx 4", 0, group.get(4).getValue(ordering));
  }
}


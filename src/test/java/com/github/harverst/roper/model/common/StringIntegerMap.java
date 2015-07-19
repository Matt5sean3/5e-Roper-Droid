package com.github.harverst.roper.model.common;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Collection;
import java.util.Set;
import java.util.AbstractSet;
import java.util.Map;
import java.util.AbstractMap;
import java.lang.String;
import java.lang.Integer;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import java.lang.UnsupportedOperationException;
import static java.util.Arrays.copyOf;

public class StringIntegerMap extends AbstractMap<String, Integer>
{
  public Set<Map.Entry<String, Integer> > entrySet()
  {
    // A set of all integers
    return new StringIntegerSet();
  }
}

// The set of all integers
class StringIntegerSet extends AbstractSet<Map.Entry<String, Integer> >
{
  public Iterator<Map.Entry<String, Integer> > iterator()
  {
    // Should make getting to small numbers fairly quick
    // Note: this is a hack to make it run reasonably quickly, setting the iterator base at -20
    return new IntegerEntryIterator(-20);
  }
  public int size()
  {
    // Formally, needs to be MAX_VALUE - MIN_VALUE
    // but that would overflow
    return MAX_VALUE;
  }
}

class IntegerEntry implements Map.Entry<String, Integer>
{
  private Integer value;
  public IntegerEntry(int v)
  {
    value = v;
  }
  public boolean equals(Object o)
  {
    if(!(o instanceof IntegerEntry))
    {
      return false;
    }
    IntegerEntry p = this.getClass().cast(o);
    return value == p.value;
  }
  public String getKey()
  {
    return value.toString();
  }
  public Integer getValue()
  {
    return value;
  }
  public int hashCode()
  {
    if(value == 0)
      return 1;
    if(value < 0)
      return 1 - 2 * value;
    if(value > 0)
      return 2 * value;
    return 0;
  }
  public Integer setValue(Integer v)
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  }
}

class IntegerEntryIterator implements Iterator<Map.Entry<String, Integer> >
{
  private Integer value;
  public IntegerEntryIterator()
  {
    value = MIN_VALUE;
  }
  public IntegerEntryIterator(int startingPoint)
  {
    value = startingPoint;
  }
  public boolean hasNext()
  {
    return MAX_VALUE > value;
  }
  public Map.Entry<String, Integer> next()
    throws NoSuchElementException
  {
    if(!hasNext())
    {
      throw new NoSuchElementException();
    }
    return new IntegerEntry(++value);
  }
  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  }
}


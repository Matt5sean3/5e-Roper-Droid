package com.github.harverst.roper.model.fifthedition;

import com.github.harverst.roper.model.ScoreComponent;
import com.github.harverst.roper.model.common.AdditiveScoreComponent;
import com.github.harverst.roper.model.common.MultiplicativeScoreComponent;
import com.github.harverst.roper.model.common.DivisiveScoreComponent;

import org.json.JSONObject;
import org.json.JSONException;
import static java.lang.Enum.valueOf;

// TODO: attempt this generalization
/**
 * I'm not entirely sure but conceptually this should work in the common package
 * Possibly using something like this

  public static ScoreComponent<P extends Enum> 
    fromJson(Class<P> phaseType, JSONObject obj)
  {
    FifthEditionPhase phase = 
      valueOf(type, obj.getString("phase"));
    int value = obj.getInt("value");
    switch(valueOf(BasicComponentType.class, obj.getString("type"))
    {
    case BasicComponentType.ADDITIVE:
      return new AdditiveScoreComponent(phase, value);
    case BasicComponentType.MULTIPLICATIVE:
      return new MultiplicativeScoreComponent(phase, value);
    case BasicComponentType.DIVISIVE:
      return new DivisiveScoreComponent(phase, value);
    }
  }

enum BasicComponentType
{
  ADDITIVE, 
  MULTIPLICATIVE,
  DIVISIVE
}
 */
/**
 *
 */
public abstract class FifthEditionScoreComponent 
  implements ScoreComponent<FifthEditionPhase>
{
  public static ScoreComponent<FifthEditionPhase> fromJson(JSONObject obj)
  {
    // 
    try
    {
      FifthEditionPhase phase = 
        valueOf(FifthEditionPhase.class, obj.getString("phase"));
      int value = obj.getInt("value");
      switch(valueOf(FifthEditionComponentType.class, obj.getString("type")))
      {
      case ADDITIVE:
        return new AdditiveScoreComponent(phase, value);
      case MULTIPLICATIVE:
        return new MultiplicativeScoreComponent(phase, value);
      case DIVISIVE:
        return new DivisiveScoreComponent(phase, value);
      default:
        return null;
      }
    }
    catch(JSONException e)
    {
      // Returns null in case of a failure
      return null;
    }
  }
}

enum FifthEditionComponentType
{
  ADDITIVE, 
  MULTIPLICATIVE,
  DIVISIVE
}


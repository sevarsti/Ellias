package com.nineoldandroids.animation;

public class FloatEvaluator
  implements TypeEvaluator<Number>
{
  public Float evaluate(float paramFloat, Number paramNumber1, Number paramNumber2)
  {
    float f = paramNumber1.floatValue();
    return Float.valueOf(f + paramFloat * (paramNumber2.floatValue() - f));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.nineoldandroids.animation.FloatEvaluator
 * JD-Core Version:    0.6.0
 */
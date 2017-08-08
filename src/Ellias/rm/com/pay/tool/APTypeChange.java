package com.pay.tool;

public class APTypeChange
{
  public static float StringToFloat(String paramString)
  {
    try
    {
      float f = Float.parseFloat(paramString);
      return f;
    }
    catch (Exception localException)
    {
    }
    return 0.0F;
  }

  public static int StringToInt(String paramString)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception localException)
    {
    }
    return 0;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APTypeChange
 * JD-Core Version:    0.6.0
 */
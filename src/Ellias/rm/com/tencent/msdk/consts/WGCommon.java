package com.tencent.msdk.consts;

public class WGCommon
{
  public static enum Environment
  {
    int value = 0;

    static
    {
      ONLINE = new Environment("ONLINE", 1, 1);
      Environment[] arrayOfEnvironment = new Environment[2];
      arrayOfEnvironment[0] = TEST;
      arrayOfEnvironment[1] = ONLINE;
      $VALUES = arrayOfEnvironment;
    }

    private Environment(int paramInt)
    {
      this.value = paramInt;
    }

    public int val()
    {
      return this.value;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.consts.WGCommon
 * JD-Core Version:    0.6.0
 */
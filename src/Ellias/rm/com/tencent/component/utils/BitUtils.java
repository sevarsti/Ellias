package com.tencent.component.utils;

public final class BitUtils
{
  public static final int a(int paramInt)
  {
    return (int)(Math.log(paramInt) / Math.log(2.0D));
  }

  public static final int a(int paramInt1, int paramInt2)
  {
    return paramInt1 | paramInt2;
  }

  public static final boolean b(int paramInt1, int paramInt2)
  {
    return paramInt2 == (paramInt1 & paramInt2);
  }

  public static final int c(int paramInt1, int paramInt2)
  {
    return paramInt1 ^ paramInt1 & paramInt2;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.BitUtils
 * JD-Core Version:    0.6.0
 */
package com.tencent.map.b;

public final class u
{
  public byte[] a;
  public String b = "GBK";

  public final String toString()
  {
    try
    {
      String str = new String(this.a, this.b);
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.map.b.u
 * JD-Core Version:    0.6.0
 */
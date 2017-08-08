package com.tenpay.a.a;

public class c
{
  public static a a(String paramString)
  {
    try
    {
      a locala = (a)Class.forName(paramString).newInstance();
      return locala;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.a.a.c
 * JD-Core Version:    0.6.0
 */
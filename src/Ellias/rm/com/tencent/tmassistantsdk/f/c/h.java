package com.tencent.tmassistantsdk.f.c;

public class h extends a
{
  protected static h a = null;

  public static h f()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new h();
      h localh = a;
      return localh;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected String[] b(int paramInt)
  {
    if (paramInt > 1)
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = e();
      return arrayOfString;
    }
    return null;
  }

  protected String d()
  {
    return "TipsInfoLogData";
  }

  protected String e()
  {
    return "CREATE TABLE if not exists TipsInfoLogData( _id INTEGER PRIMARY KEY AUTOINCREMENT, logData BLOB );";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.c.h
 * JD-Core Version:    0.6.0
 */
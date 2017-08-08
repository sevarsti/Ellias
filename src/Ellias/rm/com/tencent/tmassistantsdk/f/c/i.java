package com.tencent.tmassistantsdk.f.c;

public class i extends a
{
  private static i a = null;

  public static i f()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new i();
      i locali = a;
      return locali;
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
    return "SelfUpdateInfoLogData";
  }

  protected String e()
  {
    return "CREATE TABLE if not exists SelfUpdateInfoLogData( _id INTEGER PRIMARY KEY AUTOINCREMENT, logData BLOB );";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.c.i
 * JD-Core Version:    0.6.0
 */
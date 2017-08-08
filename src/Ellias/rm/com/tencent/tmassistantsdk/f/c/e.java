package com.tencent.tmassistantsdk.f.c;

public class e extends a
{
  protected static e a = null;

  public static e f()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new e();
      e locale = a;
      return locale;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected String[] b(int paramInt)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = e();
    return arrayOfString;
  }

  protected String d()
  {
    return "downloadLogData";
  }

  protected String e()
  {
    return "CREATE TABLE if not exists downloadLogData( _id INTEGER PRIMARY KEY AUTOINCREMENT, logData BLOB );";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.c.e
 * JD-Core Version:    0.6.0
 */
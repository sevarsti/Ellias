package com.tencent.tmassistantsdk.f;

import com.tencent.tmassistantsdk.f.c.c;
import java.util.ArrayList;

public class a
{
  protected static a a = null;

  public static a a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new a();
      a locala = a;
      return locala;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(com.tencent.tmassistantsdk.downloadservice.d paramd)
  {
    com.tencent.tmassistantsdk.f.c.d.a(paramd);
  }

  public void a(String paramString)
  {
    com.tencent.tmassistantsdk.f.c.d.a(paramString);
    c.a(paramString);
  }

  public void a(String paramString1, String paramString2)
  {
    c.a(paramString1, paramString2);
  }

  public void a(ArrayList paramArrayList)
  {
    com.tencent.tmassistantsdk.f.c.d.a(paramArrayList);
  }

  public com.tencent.tmassistantsdk.downloadservice.d b(String paramString)
  {
    return com.tencent.tmassistantsdk.f.c.d.b(paramString);
  }

  public ArrayList b()
  {
    return com.tencent.tmassistantsdk.f.c.d.c();
  }

  public ArrayList c()
  {
    return com.tencent.tmassistantsdk.f.c.d.d();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.a
 * JD-Core Version:    0.6.0
 */
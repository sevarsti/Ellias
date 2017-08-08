package com.tencent.a.b;

import android.os.Environment;

public class c
{
  public static boolean a()
  {
    String str = Environment.getExternalStorageState();
    return ("mounted".equals(str)) || ("mounted_ro".equals(str));
  }

  public static d b()
  {
    if (!a())
      return null;
    return d.b(Environment.getExternalStorageDirectory());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.a.b.c
 * JD-Core Version:    0.6.0
 */
package com.tencent.android.tpush.logging.b;

import android.os.Environment;

public class d
{
  public static boolean a()
  {
    String str = Environment.getExternalStorageState();
    return ("mounted".equals(str)) || ("mounted_ro".equals(str));
  }

  public static e b()
  {
    if (!a())
      return null;
    try
    {
      e locale = e.b(Environment.getExternalStorageDirectory());
      return locale;
    }
    catch (Exception localException)
    {
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.b.d
 * JD-Core Version:    0.6.0
 */
package com.tencent.qqgamemi.common;

import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

public class CommonData
{
  public static final String a = "storage/emulated/";
  public static final String b = "storage/sdcard";
  private static String c = "";
  private static String d = "";

  public static String a()
  {
    if (TextUtils.isEmpty(c))
      c = Environment.getExternalStorageDirectory().getPath();
    if (c.contains("storage/emulated/"))
      c = c.replace("storage/emulated/", "storage/sdcard");
    return c;
  }

  public static String a(String paramString)
  {
    if (paramString.contains("storage/emulated/"))
      paramString = paramString.replace("storage/emulated/", "storage/sdcard");
    return paramString;
  }

  public static String b()
  {
    if (TextUtils.isEmpty(d))
      d = Environment.getExternalStorageDirectory().getAbsolutePath();
    if (d.contains("storage/emulated/"))
      d = d.replace("storage/emulated/", "storage/sdcard");
    return d;
  }

  public static File c()
  {
    return new File(a());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.common.CommonData
 * JD-Core Version:    0.6.0
 */
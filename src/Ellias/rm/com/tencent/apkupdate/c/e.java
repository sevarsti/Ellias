package com.tencent.apkupdate.c;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

public final class e
{
  private static String b = "NA";
  private Context a = null;

  public e(Context paramContext)
  {
    this.a = paramContext;
  }

  private static StringBuffer a(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (TextUtils.isEmpty(paramString))
    {
      localStringBuffer.append("NA");
      return localStringBuffer;
    }
    char[] arrayOfChar = paramString.toCharArray();
    for (int i = 0; i < arrayOfChar.length; i++)
    {
      if ((arrayOfChar[i] <= ' ') || (arrayOfChar[i] == '/') || (arrayOfChar[i] == '_') || (arrayOfChar[i] == '&') || (arrayOfChar[i] == '|') || (arrayOfChar[i] == '-'))
        continue;
      localStringBuffer.append(arrayOfChar[i]);
    }
    return localStringBuffer;
  }

  public final String a()
  {
    d locald = new d();
    String str1;
    StringBuffer localStringBuffer1;
    String str2;
    if ("100".contains("BuildNo"))
    {
      str1 = "0000";
      locald.c(str1);
      locald.d("");
      locald.e(b);
      localStringBuffer1 = new StringBuffer();
      str2 = Build.VERSION.RELEASE;
      if (!TextUtils.isEmpty(str2))
        break label221;
      localStringBuffer1.append("NA");
    }
    while (true)
    {
      localStringBuffer1.append("_");
      localStringBuffer1.append(Build.VERSION.SDK_INT);
      locald.f(localStringBuffer1.toString());
      locald.b(this.a.getResources().getDisplayMetrics().heightPixels);
      locald.a(this.a.getResources().getDisplayMetrics().widthPixels);
      locald.c(0);
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append(a(Build.BRAND));
      localStringBuffer2.append("_");
      localStringBuffer2.append(a(Build.MODEL));
      locald.a(localStringBuffer2.toString());
      locald.b("100");
      Log.i("vivianliao", " qua :" + locald.a());
      return locald.a();
      str1 = "100";
      break;
      label221: localStringBuffer1.append(str2);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.c.e
 * JD-Core Version:    0.6.0
 */
package com.tencent.apkupdate.c;

import android.text.TextUtils;

public final class d
{
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private int g;
  private int h;
  private int i;

  public final String a()
  {
    StringBuffer localStringBuffer1 = new StringBuffer();
    localStringBuffer1.append("0");
    localStringBuffer1.append(this.b.subSequence(0, 1));
    localStringBuffer1.append(this.c);
    String str = localStringBuffer1.toString();
    StringBuffer localStringBuffer2 = new StringBuffer();
    localStringBuffer2.append("TMASDK_");
    localStringBuffer2.append(this.b);
    if (!TextUtils.isEmpty(this.d))
    {
      localStringBuffer2.append("_");
      localStringBuffer2.append(this.d);
    }
    localStringBuffer2.append("/");
    localStringBuffer2.append(str);
    localStringBuffer2.append("&NA/");
    localStringBuffer2.append(str);
    localStringBuffer2.append("&");
    localStringBuffer2.append(this.f);
    localStringBuffer2.append("_");
    localStringBuffer2.append(this.i);
    localStringBuffer2.append("&");
    localStringBuffer2.append(this.g / 16);
    localStringBuffer2.append("_");
    localStringBuffer2.append(this.h / 16);
    localStringBuffer2.append("_");
    localStringBuffer2.append("14&");
    localStringBuffer2.append(this.a);
    localStringBuffer2.append("&");
    localStringBuffer2.append(this.e);
    localStringBuffer2.append("&");
    localStringBuffer2.append("NA");
    localStringBuffer2.append("&");
    localStringBuffer2.append("V3");
    return localStringBuffer2.toString();
  }

  public final void a(int paramInt)
  {
    this.g = paramInt;
  }

  public final void a(String paramString)
  {
    this.a = paramString;
  }

  public final void b(int paramInt)
  {
    this.h = paramInt;
  }

  public final void b(String paramString)
  {
    this.b = paramString;
  }

  public final void c(int paramInt)
  {
    this.i = 0;
  }

  public final void c(String paramString)
  {
    this.c = paramString;
  }

  public final void d(String paramString)
  {
    this.d = paramString;
  }

  public final void e(String paramString)
  {
    this.e = paramString;
  }

  public final void f(String paramString)
  {
    this.f = paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.c.d
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.plugin.server;

import android.text.TextUtils;

final class b
{
  String a;
  String b;
  String c;
  int d;
  boolean e = false;

  private static boolean a(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return paramString2 == null;
    return paramString1.equals(paramString2);
  }

  public boolean a()
  {
    return (!TextUtils.isEmpty(this.a)) && (!TextUtils.isEmpty(this.b)) && (this.d >= 0);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof b)));
    b localb;
    do
    {
      return false;
      localb = (b)paramObject;
    }
    while ((!a(this.a, localb.a)) || (!a(this.b, localb.b)) || (this.d != localb.d));
    return true;
  }

  public int hashCode()
  {
    int i;
    int j;
    int k;
    if (this.a == null)
    {
      i = 0;
      j = 31 * (i + 527);
      String str = this.b;
      k = 0;
      if (str != null)
        break label54;
    }
    while (true)
    {
      return 31 * (j + k) + this.d;
      i = this.a.hashCode();
      break;
      label54: k = this.b.hashCode();
    }
  }

  public String toString()
  {
    return "PluginRecord{" + this.a + " " + this.d + " " + this.b + "}";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.b
 * JD-Core Version:    0.6.0
 */
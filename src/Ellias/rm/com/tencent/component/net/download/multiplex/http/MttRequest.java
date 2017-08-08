package com.tencent.component.net.download.multiplex.http;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MttRequest
{
  public static final String a = "Close";
  public static final String b = "GET";
  public static final String c = "POST";
  public static final byte d = 101;
  public static final byte e = 102;
  public static final byte f = 103;
  public static final byte g = 104;
  private static final String j = "application/vnd.wap.xhtml+xml,application/xml,text/vnd.wap.wml,text/html,application/xhtml+xml,image/jpeg;q=0.5,image/png;q=0.5,image/gif;q=0.5,image/*;q=0.6,video/*,audio/*,*/*;q=0.6";
  private static final String k = "gzip";
  private static String l = null;
  public byte h;
  public boolean i = false;
  private byte m = 101;
  private boolean n = false;
  private boolean o = true;
  private String p;
  private byte q;
  private HashMap r = new HashMap();
  private String s;
  private int t = 0;
  private boolean u = false;
  private String v;

  public MttRequest()
  {
    o();
    a("Accept", "application/vnd.wap.xhtml+xml,application/xml,text/vnd.wap.wml,text/html,application/xhtml+xml,image/jpeg;q=0.5,image/png;q=0.5,image/gif;q=0.5,image/*;q=0.6,video/*,audio/*,*/*;q=0.6");
    a("Accept-Encoding", "gzip");
    if (l != null)
    {
      a("User-Agent", l);
      return;
    }
    System.err.println("user_agent is null!");
  }

  public static String n()
  {
    return l;
  }

  private void o()
  {
    StringBuffer localStringBuffer;
    if (l == null)
    {
      Locale localLocale = Locale.getDefault();
      localStringBuffer = new StringBuffer();
      String str1 = Build.VERSION.RELEASE;
      if (str1.length() <= 0)
        break label172;
      localStringBuffer.append(str1);
      localStringBuffer.append("; ");
      String str2 = localLocale.getLanguage();
      if (str2 == null)
        break label182;
      localStringBuffer.append(str2.toLowerCase());
      String str5 = localLocale.getCountry();
      if (str5 != null)
      {
        localStringBuffer.append("-");
        localStringBuffer.append(str5.toLowerCase());
      }
    }
    while (true)
    {
      if (Integer.parseInt(Build.VERSION.SDK) > 3)
      {
        String str4 = Build.MODEL;
        if (str4.length() > 0)
        {
          localStringBuffer.append("; ");
          localStringBuffer.append(str4);
        }
      }
      String str3 = Build.ID;
      if (str3.length() > 0)
      {
        localStringBuffer.append(" Build/");
        localStringBuffer.append(str3);
      }
      l = String.format("Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Mobile Safari/533.1", new Object[] { localStringBuffer });
      return;
      label172: localStringBuffer.append("1.0");
      break;
      label182: localStringBuffer.append("en");
    }
  }

  public String a()
  {
    return this.p;
  }

  public void a(byte paramByte)
  {
    this.m = paramByte;
  }

  public void a(String paramString)
  {
    this.p = paramString;
  }

  public void a(String paramString1, String paramString2)
  {
    this.r.put(paramString1, paramString2);
  }

  public void a(boolean paramBoolean)
  {
    this.n = paramBoolean;
  }

  public void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    if (b() == 104)
      a("Accept-Encoding", "identity");
    if ((!TextUtils.isEmpty(j())) && (!paramBoolean1))
      a("Referer", j());
    do
      return;
    while (!this.i);
    a("Referer", this.p.toString());
  }

  public byte b()
  {
    return this.m;
  }

  public String b(String paramString)
  {
    return (String)this.r.get(paramString);
  }

  public void b(byte paramByte)
  {
    this.q = paramByte;
  }

  public void b(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)))
      return;
    this.r.remove(paramString1);
    this.r.put(paramString1, paramString2);
  }

  public void b(boolean paramBoolean)
  {
    this.u = paramBoolean;
  }

  public void c(String paramString)
  {
    this.r.remove(paramString);
  }

  public void c(boolean paramBoolean)
  {
    this.o = paramBoolean;
  }

  public boolean c()
  {
    return this.n;
  }

  public void d(String paramString)
  {
    this.s = paramString;
  }

  public boolean d()
  {
    return this.u;
  }

  public void e(String paramString)
  {
    this.v = paramString;
  }

  public boolean e()
  {
    return this.o;
  }

  public byte f()
  {
    return this.q;
  }

  public String g()
  {
    if (this.q == 1)
      return "POST";
    return "GET";
  }

  public Map h()
  {
    return this.r;
  }

  public boolean i()
  {
    return this.m == 103;
  }

  public String j()
  {
    return this.s;
  }

  public String k()
  {
    return this.p + hashCode();
  }

  public int l()
  {
    return this.t;
  }

  public String m()
  {
    if (this.v == null)
      return l;
    return this.v;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Method : " + g() + "\n");
    localStringBuilder.append("NUrl : " + a() + "\n");
    localStringBuilder.append("RequestType : " + b() + "\n");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.http.MttRequest
 * JD-Core Version:    0.6.0
 */
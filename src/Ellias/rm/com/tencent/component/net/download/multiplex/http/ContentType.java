package com.tencent.component.net.download.multiplex.http;

import android.text.TextUtils;

public class ContentType
{
  public static final String a = "text";
  public static final String b = "image";
  public static final String c = "application";
  public static final String d = "html";
  public static final String e = "css";
  public static final String f = "javascript";
  public static final String g = "plain";
  public static final String h = "png";
  public static final String i = "jpeg";
  public static final String j = "gif";
  public static final String k = "octet-stream";
  public static final String l = "utf-8";
  public static final String m = "binary";
  private String n;
  private String o;
  private String p;

  public ContentType()
  {
  }

  public ContentType(String paramString)
  {
    d(paramString);
  }

  public ContentType(String paramString1, String paramString2, String paramString3)
  {
    this.n = paramString1;
    this.o = paramString2;
    this.p = paramString3;
  }

  private void d(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    label130: label137: 
    while (true)
    {
      return;
      String str1 = paramString.trim();
      int i1 = str1.indexOf(';');
      String str2;
      String str3;
      if (i1 != -1)
      {
        str2 = str1.substring(0, i1);
        str3 = str1.substring(i1 + 1);
        label42: if (str2 != null)
        {
          int i3 = str2.indexOf('/');
          if (i3 == -1)
            break label130;
          this.n = str2.substring(0, i3);
          this.o = str2.substring(i3 + 1);
        }
      }
      while (true)
      {
        if (str3 == null)
          break label137;
        int i2 = str3.indexOf('=');
        if (i2 == -1)
          break;
        this.p = str3.substring(i2 + 1);
        return;
        str2 = str1;
        str3 = null;
        break label42;
        this.n = str2;
      }
    }
  }

  public String a()
  {
    return this.n;
  }

  public void a(String paramString)
  {
    this.n = paramString;
  }

  public String b()
  {
    return this.o;
  }

  public void b(String paramString)
  {
    this.o = paramString;
  }

  public String c()
  {
    return this.p;
  }

  public void c(String paramString)
  {
    this.p = paramString;
  }

  public String toString()
  {
    if (this.n != null)
      return this.n.concat("/").concat(this.o);
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.http.ContentType
 * JD-Core Version:    0.6.0
 */
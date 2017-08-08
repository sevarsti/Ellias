package com.tencent.component.net.download.multiplex.http;

import com.tencent.component.utils.log.LogUtil;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MttResponse
{
  private static final String a = "MttResponse";
  private String b;
  private Integer c = new Integer(-1);
  private String d;
  private String e;
  private ContentType f;
  private long g;
  private String h;
  private String i;
  private String j;
  private String k;
  private String l;
  private String m;
  private String n;
  private String o;
  private String p;
  private String q;
  private String r;
  private String s;
  private String t;
  private String u;
  private String v;
  private MttInputStream w;
  private int x = 0;

  public int a()
  {
    if (this.w != null)
      return this.w.b() + this.x;
    return this.x;
  }

  public void a(long paramLong)
  {
    this.g = paramLong;
  }

  public void a(ContentType paramContentType)
  {
    this.f = paramContentType;
  }

  public void a(MttInputStream paramMttInputStream)
  {
    this.w = paramMttInputStream;
  }

  public void a(Integer paramInteger)
  {
    this.c = paramInteger;
  }

  public void a(String paramString)
  {
    this.b = paramString;
  }

  public void a(Map paramMap)
  {
    if (paramMap == null)
    {
      LogUtil.d("MttResponse", "headerFields == null");
      return;
    }
    LogUtil.d("MttResponse", "start to add flow");
    Iterator localIterator1 = paramMap.entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((List)((Map.Entry)localIterator1.next()).getValue()).iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        try
        {
          this.x += str.getBytes("UTF-8").length;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          localUnsupportedEncodingException.printStackTrace();
        }
      }
    }
  }

  public MttInputStream b()
  {
    return this.w;
  }

  public void b(String paramString)
  {
    this.d = paramString;
  }

  public Integer c()
  {
    return this.c;
  }

  public void c(String paramString)
  {
    this.q = paramString;
  }

  public String d()
  {
    return this.b;
  }

  public void d(String paramString)
  {
    this.i = paramString;
  }

  public String e()
  {
    return this.d;
  }

  public void e(String paramString)
  {
    this.j = paramString;
  }

  public ContentType f()
  {
    return this.f;
  }

  public void f(String paramString)
  {
    this.k = paramString;
  }

  public long g()
  {
    return this.g;
  }

  public void g(String paramString)
  {
    this.m = paramString;
  }

  public String h()
  {
    return this.q;
  }

  public void h(String paramString)
  {
    this.e = paramString;
  }

  public String i()
  {
    return this.i;
  }

  public void i(String paramString)
  {
    this.h = paramString;
  }

  public String j()
  {
    return this.j;
  }

  public void j(String paramString)
  {
    this.l = paramString;
  }

  public String k()
  {
    return this.k;
  }

  public void k(String paramString)
  {
    this.n = paramString;
  }

  public String l()
  {
    return this.m;
  }

  public void l(String paramString)
  {
    this.o = paramString;
  }

  public String m()
  {
    return this.e;
  }

  public void m(String paramString)
  {
    this.p = paramString;
  }

  public String n()
  {
    return this.h;
  }

  public void n(String paramString)
  {
    this.r = paramString;
  }

  public String o()
  {
    return this.l;
  }

  public void o(String paramString)
  {
    this.s = paramString;
  }

  public String p()
  {
    return this.n;
  }

  public void p(String paramString)
  {
    this.t = paramString;
  }

  public String q()
  {
    return this.o;
  }

  public void q(String paramString)
  {
    this.u = paramString;
  }

  public String r()
  {
    return this.p;
  }

  public void r(String paramString)
  {
    this.v = paramString;
  }

  public String s()
  {
    return this.r;
  }

  public String t()
  {
    return this.s;
  }

  public String u()
  {
    return this.t;
  }

  public String v()
  {
    return this.u;
  }

  public String w()
  {
    return this.v;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.http.MttResponse
 * JD-Core Version:    0.6.0
 */
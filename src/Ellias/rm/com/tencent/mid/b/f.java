package com.tencent.mid.b;

import android.content.Context;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.util.Util;

public abstract class f
{
  protected Context a = null;

  protected f(Context paramContext)
  {
    this.a = paramContext;
  }

  private void d(String paramString)
  {
    if (a())
      a(b(paramString));
  }

  public static String e()
  {
    return Util.decode("6X8Y4XdM2Vhvn0I=");
  }

  public static String f()
  {
    return Util.decode("6X8Y4XdM2Vhvn0KfzcEatGnWaNU=");
  }

  public static String g()
  {
    return Util.decode("4kU71lN96TJUomD1vOU9lgj9U+kKmxDPLVM+zzjst5U=");
  }

  private String l()
  {
    if (a())
      return c(b());
    return null;
  }

  public void a(MidEntity paramMidEntity)
  {
    if (paramMidEntity == null)
      return;
    d(paramMidEntity.toString());
  }

  protected abstract void a(a parama);

  protected abstract void a(String paramString);

  protected abstract boolean a();

  protected abstract String b();

  protected String b(String paramString)
  {
    return Util.encode(paramString);
  }

  public void b(a parama)
  {
    if (parama == null);
    do
      return;
    while (!a());
    a(parama);
  }

  protected String c(String paramString)
  {
    return Util.decode(paramString);
  }

  protected abstract void c();

  protected abstract a d();

  public MidEntity h()
  {
    String str = l();
    if (str != null)
      return MidEntity.parse(str);
    return null;
  }

  void i()
  {
    if (a())
      c();
  }

  public a j()
  {
    if (a())
      return d();
    return null;
  }

  protected String k()
  {
    return Util.decode("4kU71lN96TJUomD1vOU9lgj9Tw==");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.b.f
 * JD-Core Version:    0.6.0
 */
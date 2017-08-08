package com.tencent.android.tpush.a;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.encrypt.Rijndael;

public class h
{
  private long a = -1L;
  private long b = -1L;
  private long c = -1L;
  private String d = "";
  private long e = -1L;
  private long f = -1L;
  private Context g = null;
  private Intent h = null;
  private a i = null;

  private h(Context paramContext, Intent paramIntent)
  {
    this.g = paramContext;
    this.h = paramIntent;
  }

  public static h a(Context paramContext, Intent paramIntent)
  {
    h localh = new h(paramContext, paramIntent);
    String str = Rijndael.decrypt(paramIntent.getStringExtra("content"));
    localh.d = str;
    localh.a = paramIntent.getLongExtra("msgId", -1L);
    localh.b = paramIntent.getLongExtra("accId", -1L);
    localh.c = paramIntent.getLongExtra("busiMsgId", -1L);
    localh.e = paramIntent.getLongExtra("timestamps", -1L);
    localh.f = paramIntent.getLongExtra("type", -1L);
    if (localh.f == 2L);
    for (Object localObject = new i(str); ; localObject = new c(str))
    {
      localh.i = ((a)localObject);
      localh.i.a();
      return localh;
      if (localh.f != 1L)
        break;
    }
    throw new IllegalArgumentException("error message type:" + localh.f);
  }

  public void a()
  {
    if (this.i.b() != 1)
      return;
    b.b(this.g, this);
  }

  public long b()
  {
    return this.a;
  }

  public long c()
  {
    return this.b;
  }

  public long d()
  {
    return this.c;
  }

  public long e()
  {
    return this.e;
  }

  public String f()
  {
    return this.d;
  }

  public Intent g()
  {
    return this.h;
  }

  public a h()
  {
    return this.i;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.a.h
 * JD-Core Version:    0.6.0
 */
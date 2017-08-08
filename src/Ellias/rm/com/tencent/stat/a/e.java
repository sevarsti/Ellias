package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.DeviceInfo;
import com.tencent.stat.StatConfig;
import com.tencent.stat.common.k;
import com.tencent.stat.n;
import org.json.JSONObject;

public abstract class e
{
  private static volatile boolean a = false;
  protected String b = null;
  protected long c;
  protected int d;
  protected DeviceInfo e = null;
  protected int f;
  protected String g = null;
  protected String h = null;
  protected String i = null;
  protected String j = null;
  protected Context k;

  e(Context paramContext, int paramInt)
  {
    this.k = paramContext;
    this.c = (System.currentTimeMillis() / 1000L);
    this.d = paramInt;
    this.b = StatConfig.getAppKey(paramContext);
    this.g = StatConfig.getCustomUserId(paramContext);
    this.e = n.a(paramContext).b(paramContext);
    this.f = k.w(paramContext).intValue();
    this.i = k.n(paramContext);
    this.h = StatConfig.getInstallChannel(paramContext);
  }

  public abstract f a();

  public abstract boolean a(JSONObject paramJSONObject);

  public long b()
  {
    return this.c;
  }

  public boolean b(JSONObject paramJSONObject)
  {
    try
    {
      k.a(paramJSONObject, "ky", this.b);
      paramJSONObject.put("et", a().a());
      if (this.e != null)
      {
        paramJSONObject.put("ui", this.e.getImei());
        k.a(paramJSONObject, "mc", this.e.getMac());
        paramJSONObject.put("ut", this.e.getUserType());
      }
      k.a(paramJSONObject, "cui", this.g);
      if (a() != f.b)
      {
        k.a(paramJSONObject, "av", this.i);
        k.a(paramJSONObject, "ch", this.h);
      }
      k.a(paramJSONObject, "mid", StatConfig.getMid(this.k));
      paramJSONObject.put("idx", this.f);
      paramJSONObject.put("si", this.d);
      paramJSONObject.put("ts", this.c);
      if ((this.e.getUserType() == 0) && (k.E(this.k) == 1))
        paramJSONObject.put("ia", 1);
      boolean bool = a(paramJSONObject);
      return bool;
    }
    catch (Throwable localThrowable)
    {
    }
    return false;
  }

  public Context c()
  {
    return this.k;
  }

  public String d()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      b(localJSONObject);
      String str = localJSONObject.toString();
      return str;
    }
    catch (Throwable localThrowable)
    {
    }
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.a.e
 * JD-Core Version:    0.6.0
 */
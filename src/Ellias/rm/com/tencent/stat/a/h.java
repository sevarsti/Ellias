package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.StatAppMonitor;
import com.tencent.stat.common.k;
import org.json.JSONObject;

public class h extends e
{
  private static String l = null;
  private static String m = null;
  private StatAppMonitor a = null;

  public h(Context paramContext, int paramInt, StatAppMonitor paramStatAppMonitor)
  {
    super(paramContext, paramInt);
    this.a = paramStatAppMonitor.clone();
  }

  public f a()
  {
    return f.f;
  }

  public boolean a(JSONObject paramJSONObject)
  {
    if (this.a == null)
      return false;
    paramJSONObject.put("na", this.a.getInterfaceName());
    paramJSONObject.put("rq", this.a.getReqSize());
    paramJSONObject.put("rp", this.a.getRespSize());
    paramJSONObject.put("rt", this.a.getResultType());
    paramJSONObject.put("tm", this.a.getMillisecondsConsume());
    paramJSONObject.put("rc", this.a.getReturnCode());
    paramJSONObject.put("sp", this.a.getSampling());
    if (m == null)
      m = k.r(this.k);
    k.a(paramJSONObject, "av", m);
    if (l == null)
      l = k.m(this.k);
    k.a(paramJSONObject, "op", l);
    paramJSONObject.put("cn", k.p(this.k));
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.a.h
 * JD-Core Version:    0.6.0
 */
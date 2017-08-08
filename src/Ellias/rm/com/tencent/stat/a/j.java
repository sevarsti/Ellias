package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.common.k;
import org.json.JSONObject;

public class j extends e
{
  Long a = null;
  String l;
  String m;

  public j(Context paramContext, String paramString1, String paramString2, int paramInt, Long paramLong)
  {
    super(paramContext, paramInt);
    this.m = paramString1;
    this.l = paramString2;
    this.a = paramLong;
  }

  public f a()
  {
    return f.a;
  }

  public boolean a(JSONObject paramJSONObject)
  {
    k.a(paramJSONObject, "pi", this.l);
    k.a(paramJSONObject, "rf", this.m);
    if (this.a != null)
      paramJSONObject.put("du", this.a);
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.a.j
 * JD-Core Version:    0.6.0
 */
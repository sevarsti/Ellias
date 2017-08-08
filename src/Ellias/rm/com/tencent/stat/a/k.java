package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.DeviceInfo;
import com.tencent.stat.common.a;
import org.json.JSONObject;

public class k extends e
{
  private a a;
  private JSONObject l = null;

  public k(Context paramContext, int paramInt, JSONObject paramJSONObject)
  {
    super(paramContext, paramInt);
    this.a = new a(paramContext);
    this.l = paramJSONObject;
  }

  public f a()
  {
    return f.b;
  }

  public boolean a(JSONObject paramJSONObject)
  {
    if (this.e != null)
      paramJSONObject.put("ut", this.e.getUserType());
    if (this.l != null)
      paramJSONObject.put("cfg", this.l);
    this.a.a(paramJSONObject);
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.a.k
 * JD-Core Version:    0.6.0
 */
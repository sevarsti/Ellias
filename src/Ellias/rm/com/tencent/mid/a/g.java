package com.tencent.mid.a;

import android.content.Context;
import android.util.Log;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.util.Util;
import com.tencent.mid.util.e;
import org.json.JSONObject;

public class g
{
  protected Context a = null;
  private int b = 0;

  public g(Context paramContext)
  {
    this.a = paramContext;
    this.b = (int)(System.currentTimeMillis() / 1000L);
  }

  public int a()
  {
    return 2;
  }

  public JSONObject a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      paramJSONObject = new JSONObject();
    paramJSONObject.put("et", a());
    b(paramJSONObject);
    return paramJSONObject;
  }

  protected void b(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("mid", "0");
      paramJSONObject.put("ts", this.b);
      paramJSONObject.put("si", this.b);
      Util.jsonPut(paramJSONObject, "ui", Util.getImei(this.a));
      Util.jsonPut(paramJSONObject, "mc", Util.getWifiMacAddress(this.a));
      MidEntity localMidEntity = com.tencent.mid.b.g.a(this.a).a();
      if ((localMidEntity != null) && (Util.isMidValid(localMidEntity.getMid())))
        paramJSONObject.put("mid", localMidEntity.getMid());
      new e(this.a).a(paramJSONObject);
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e("MID", "encode error.", localThrowable);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.a.g
 * JD-Core Version:    0.6.0
 */
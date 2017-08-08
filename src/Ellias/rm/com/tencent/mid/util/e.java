package com.tencent.mid.util;

import android.content.Context;
import org.json.JSONObject;

public class e
{
  static g a;
  private static JSONObject d = null;
  Integer b = null;
  String c = null;

  public e(Context paramContext)
  {
    try
    {
      a(paramContext);
      this.b = i.e(paramContext.getApplicationContext());
      this.c = i.d(paramContext);
      return;
    }
    catch (Throwable localThrowable)
    {
      Util.logWarn(localThrowable);
    }
  }

  static g a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new g(paramContext.getApplicationContext(), null);
      g localg = a;
      return localg;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (a != null)
        a.a(localJSONObject);
      Util.jsonPut(localJSONObject, "cn", this.c);
      if (this.b != null)
        localJSONObject.put("tn", this.b);
      paramJSONObject.put("ev", localJSONObject);
      if ((d != null) && (d.length() > 0))
        paramJSONObject.put("eva", d);
      return;
    }
    catch (Throwable localThrowable)
    {
      Util.logWarn(localThrowable);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.util.e
 * JD-Core Version:    0.6.0
 */
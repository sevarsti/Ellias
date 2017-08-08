package com.tencent.stat.common;

import android.content.Context;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONObject;

public class a
{
  static c a;
  private static StatLogger d = k.b();
  private static JSONObject e = null;
  Integer b = null;
  String c = null;

  public a(Context paramContext)
  {
    try
    {
      a(paramContext);
      this.b = k.q(paramContext.getApplicationContext());
      this.c = k.p(paramContext);
      return;
    }
    catch (Throwable localThrowable)
    {
      d.e(localThrowable);
    }
  }

  static c a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new c(paramContext.getApplicationContext(), null);
      c localc = a;
      return localc;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void a(Context paramContext, Map<String, String> paramMap)
  {
    if (paramMap == null);
    while (true)
    {
      return;
      HashMap localHashMap = new HashMap(paramMap);
      if (e == null)
        e = new JSONObject();
      Iterator localIterator = localHashMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        e.put((String)localEntry.getKey(), localEntry.getValue());
      }
    }
  }

  public void a(JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (a != null)
        a.a(localJSONObject);
      k.a(localJSONObject, "cn", this.c);
      if (this.b != null)
        localJSONObject.put("tn", this.b);
      paramJSONObject.put("ev", localJSONObject);
      if ((e != null) && (e.length() > 0))
        paramJSONObject.put("eva", e);
      return;
    }
    catch (Throwable localThrowable)
    {
      d.e(localThrowable);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.a
 * JD-Core Version:    0.6.0
 */
package com.tencent.tmassistantsdk.e;

import android.text.TextUtils;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

public abstract class b
{
  protected HttpGet b = null;

  protected abstract void a(JSONObject paramJSONObject, int paramInt);

  protected boolean a(String paramString)
  {
    monitorenter;
    try
    {
      boolean bool = TextUtils.isEmpty(paramString);
      if (bool);
      while (true)
      {
        return false;
        if (this.b != null)
          continue;
        new Thread(new c(this, paramString)).start();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.e.b
 * JD-Core Version:    0.6.0
 */
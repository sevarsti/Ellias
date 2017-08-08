package com.tenpay.a.a;

import android.content.Context;
import android.os.Bundle;
import org.json.JSONObject;

public abstract class a
{
  protected f a;
  protected b b;

  public void a(Context paramContext, int paramInt1, Bundle paramBundle, boolean paramBoolean, int paramInt2)
  {
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      str1 = g.a(paramContext, paramBundle, localJSONObject1);
      if (str1 != null)
      {
        if (paramBoolean)
        {
          str2 = g.a(paramInt2, str1);
          if ((str2 != null) && (!str2.startsWith("{")))
            str2 = g.a(0, str1);
          JSONObject localJSONObject2 = g.a(str2);
          if (this.b == null)
            return;
          this.b.onBLCallback(getClass().getName(), paramInt1, localJSONObject2);
          return;
        }
      }
      else
      {
        if ((!localJSONObject1.has("retcode")) || (this.b == null))
          return;
        this.b.onBLCallback(getClass().getName(), paramInt1, localJSONObject1);
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        String str1;
        localException.printStackTrace();
        if (this.b == null)
          break;
        this.b.onBLCallback(getClass().getName(), paramInt1, null);
        return;
        String str2 = str1;
      }
    }
  }

  public abstract void a(Context paramContext, int paramInt1, JSONObject paramJSONObject, long paramLong, String paramString, int paramInt2);

  public void a(b paramb)
  {
    this.b = paramb;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.a.a.a
 * JD-Core Version:    0.6.0
 */
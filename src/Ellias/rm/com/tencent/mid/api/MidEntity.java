package com.tencent.mid.api;

import android.util.Log;
import com.tencent.mid.util.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class MidEntity
{
  public static final String TAG_IMEI = "ui";
  public static final String TAG_MAC = "mc";
  public static final String TAG_MID = "mid";
  public static final String TAG_TIMESTAMPS = "ts";
  private String a = null;
  private String b = null;
  private String c = "0";
  private long d = 0L;

  public static MidEntity parse(String paramString)
  {
    MidEntity localMidEntity = new MidEntity();
    if (Util.isStringValid(paramString));
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      if (!localJSONObject.isNull("ui"))
        localMidEntity.setImei(localJSONObject.getString("ui"));
      if (!localJSONObject.isNull("mc"))
        localMidEntity.setMac(localJSONObject.getString("mc"));
      if (!localJSONObject.isNull("mid"))
        localMidEntity.setMid(localJSONObject.getString("mid"));
      if (!localJSONObject.isNull("ts"))
        localMidEntity.setTimestamps(localJSONObject.getLong("ts"));
      return localMidEntity;
    }
    catch (JSONException localJSONException)
    {
      Log.w("MID", "", localJSONException);
    }
    return localMidEntity;
  }

  JSONObject a()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      Util.jsonPut(localJSONObject, "ui", this.a);
      Util.jsonPut(localJSONObject, "mc", this.b);
      Util.jsonPut(localJSONObject, "mid", this.c);
      localJSONObject.put("ts", this.d);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      Util.logWarn(localJSONException);
    }
    return localJSONObject;
  }

  public int compairTo(MidEntity paramMidEntity)
  {
    if (paramMidEntity == null);
    do
      while (true)
      {
        return 1;
        if ((!isMidValid()) || (!paramMidEntity.isMidValid()))
          break;
        if (this.c.equals(paramMidEntity.c))
          return 0;
        if (this.d < paramMidEntity.d)
          return -1;
      }
    while (isMidValid());
    return -1;
  }

  public String getImei()
  {
    return this.a;
  }

  public String getMac()
  {
    return this.b;
  }

  public String getMid()
  {
    return this.c;
  }

  public long getTimestamps()
  {
    return this.d;
  }

  public boolean isMidValid()
  {
    return Util.isMidValid(this.c);
  }

  public void setImei(String paramString)
  {
    this.a = paramString;
  }

  public void setMac(String paramString)
  {
    this.b = paramString;
  }

  public void setMid(String paramString)
  {
    this.c = paramString;
  }

  public void setTimestamps(long paramLong)
  {
    this.d = paramLong;
  }

  public String toString()
  {
    return a().toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.api.MidEntity
 * JD-Core Version:    0.6.0
 */
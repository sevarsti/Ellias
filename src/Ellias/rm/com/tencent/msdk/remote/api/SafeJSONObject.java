package com.tencent.msdk.remote.api;

import com.tencent.msdk.tools.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SafeJSONObject extends JSONObject
{
  public SafeJSONObject()
  {
  }

  public SafeJSONObject(String paramString)
    throws JSONException
  {
    super(paramString);
  }

  public SafeJSONObject(JSONObject paramJSONObject)
    throws JSONException
  {
    super(paramJSONObject.toString());
  }

  public boolean getBoolean(String paramString)
    throws JSONException
  {
    if (!has(paramString))
    {
      Logger.w("json no key: " + paramString);
      return false;
    }
    return super.getBoolean(paramString);
  }

  public double getDouble(String paramString)
    throws JSONException
  {
    if (!has(paramString))
    {
      Logger.w("json no key: " + paramString);
      return -1.0D;
    }
    return super.getDouble(paramString);
  }

  public int getInt(String paramString)
    throws JSONException
  {
    if (!has(paramString))
    {
      Logger.w("json no key: " + paramString);
      return -1;
    }
    return super.getInt(paramString);
  }

  public JSONArray getJSONArray(String paramString)
    throws JSONException
  {
    if (!has(paramString))
    {
      Logger.w("json no key: " + paramString);
      return new JSONArray();
    }
    return super.getJSONArray(paramString);
  }

  public JSONObject getJSONObject(String paramString)
    throws JSONException
  {
    if (!has(paramString))
    {
      Logger.w("json no key: " + paramString);
      return new SafeJSONObject();
    }
    return super.getJSONObject(paramString);
  }

  public long getLong(String paramString)
    throws JSONException
  {
    if (!has(paramString))
    {
      Logger.w("json no key: " + paramString);
      return -1L;
    }
    return super.getLong(paramString);
  }

  public String getString(String paramString)
    throws JSONException
  {
    if (!has(paramString))
    {
      Logger.w("json no key: " + paramString);
      return "";
    }
    return super.getString(paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.SafeJSONObject
 * JD-Core Version:    0.6.0
 */
package com.tencent.mid.b;

import com.tencent.mid.util.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  public static String a = "ts";
  public static String b = "times";
  public static String c = "mfreq";
  public static String d = "mdays";
  private long e = 0L;
  private int f = 1;
  private int g = 1024;
  private int h = 3;

  public a()
  {
  }

  public a(String paramString)
  {
    if (!Util.isStringValid(paramString));
    while (true)
    {
      return;
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        if (!localJSONObject.isNull(a))
          this.e = localJSONObject.getLong(a);
        if (!localJSONObject.isNull(c))
          this.g = localJSONObject.getInt(c);
        if (!localJSONObject.isNull(b))
          this.f = localJSONObject.getInt(b);
        if (localJSONObject.isNull(d))
          continue;
        this.h = localJSONObject.getInt(d);
        return;
      }
      catch (JSONException localJSONException)
      {
        Util.logWarn(localJSONException);
      }
    }
  }

  public int a()
  {
    return this.h;
  }

  public void a(int paramInt)
  {
    this.h = paramInt;
  }

  public void a(long paramLong)
  {
    this.e = paramLong;
  }

  public long b()
  {
    return this.e;
  }

  public void b(int paramInt)
  {
    this.f = paramInt;
  }

  public int c()
  {
    return this.f;
  }

  public void c(int paramInt)
  {
    this.g = paramInt;
  }

  public int d()
  {
    return this.g;
  }

  public String toString()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put(a, this.e);
      localJSONObject.put(b, this.f);
      localJSONObject.put(c, this.g);
      localJSONObject.put(d, this.h);
      return localJSONObject.toString();
    }
    catch (JSONException localJSONException)
    {
      while (true)
        Util.logWarn(localJSONException);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.b.a
 * JD-Core Version:    0.6.0
 */
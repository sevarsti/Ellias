package com.tencent.tmassistantsdk.b;

import com.tencent.tmassistantsdk.g.a;
import com.tencent.tmassistantsdk.g.e;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class c
{
  public int a = -1;
  public String b = "";
  public int c = 0;
  public String d = "";
  public int e = 0;
  public String f = "";
  public long g = 0L;
  public long h = 0L;
  public int i = 0;
  public byte[] j = null;

  public c(String paramString1, int paramInt1, String paramString2, int paramInt2, String paramString3, long paramLong1, long paramLong2, int paramInt3, byte[] paramArrayOfByte)
  {
    this.b = paramString1;
    if (this.b == null)
      this.b = "";
    this.c = paramInt1;
    this.d = paramString2;
    if (this.d == null)
      this.d = "";
    this.e = paramInt2;
    this.f = paramString3;
    if (this.f == null)
      this.f = "";
    this.g = paramLong1;
    this.h = paramLong2;
    this.i = paramInt3;
    this.j = paramArrayOfByte;
  }

  public static c a(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return null;
    byte[] arrayOfByte1 = new e().a(paramArrayOfByte, "&-*)Wb5_U,[^!9'+".getBytes());
    if (arrayOfByte1 != null);
    try
    {
      String str1 = new String(arrayOfByte1, "UTF-8");
      if (str1 != null)
      {
        JSONObject localJSONObject = new JSONObject(str1);
        if (localJSONObject != null)
        {
          String str2 = localJSONObject.getString("mHostPackageName");
          int k = localJSONObject.getInt("mHostVersion");
          String str3 = localJSONObject.getString("mHostUserIdentity");
          int m = localJSONObject.getInt("mDataItemType");
          String str4 = localJSONObject.getString("mDataItemAction");
          long l1 = localJSONObject.getLong("mDataItemStartTime");
          long l2 = localJSONObject.getLong("mDataItemEndTime");
          int n = localJSONObject.getInt("mDataItemVersion");
          if (n < 1)
            break label200;
          String str5 = localJSONObject.getString("mIPCData");
          if (str5 == null)
            break label200;
          arrayOfByte2 = a.a(str5, 0);
          c localc = new c(str2, k, str3, m, str4, l1, l2, n, arrayOfByte2);
          return localc;
        }
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
      return null;
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        localJSONException.printStackTrace();
        continue;
        label200: byte[] arrayOfByte2 = null;
      }
    }
  }

  public byte[] a()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("mHostPackageName", this.b);
      localJSONObject.put("mHostVersion", this.c);
      localJSONObject.put("mHostUserIdentity", this.d);
      localJSONObject.put("mDataItemType", this.e);
      localJSONObject.put("mDataItemAction", this.f);
      localJSONObject.put("mDataItemStartTime", this.g);
      localJSONObject.put("mDataItemEndTime", this.h);
      localJSONObject.put("mDataItemVersion", this.i);
      if ((this.i >= 1) && (this.j != null))
      {
        String str2 = a.b(this.j, 0);
        if (str2 != null)
          localJSONObject.put("mIPCData", str2);
      }
      String str1 = localJSONObject.toString();
      if (str1 != null)
      {
        byte[] arrayOfByte1 = str1.getBytes("UTF-8");
        if (arrayOfByte1 != null)
        {
          byte[] arrayOfByte2 = new e().b(arrayOfByte1, "&-*)Wb5_U,[^!9'+".getBytes());
          return arrayOfByte2;
        }
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return null;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        localUnsupportedEncodingException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.b.c
 * JD-Core Version:    0.6.0
 */
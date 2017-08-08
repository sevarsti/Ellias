package com.tencent.smtt.sdk.tips;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.smtt.sdk.stat.DesUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;

public class RecommendParams
{
  public static final String BROWSER_VER = "qb_ver";
  public static final int FROM_TYPE_AIO = 2;
  public static final int FROM_TYPE_DYM = 4;
  public static final int FROM_TYPE_PUB_ACCOUNT = 1;
  public static final int FROM_TYPE_QZONE = 3;
  public static final String KEY_FROM = "from";
  public static final String KEY_IMEI = "imei";
  public static final String KEY_IMSI = "imsi";
  public static final String KEY_NETMODE = "mode";
  public static final String KEY_PACKAGE_NAME = "paK";
  public static final String KEY_URL = "url";
  private static char[] base64EncodeChars = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private Bundle mBundle = new Bundle();

  public static String base64Encode(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = paramArrayOfByte.length;
    int j = 0;
    int k;
    int m;
    if (j < i)
    {
      k = j + 1;
      m = 0xFF & paramArrayOfByte[j];
      if (k == i)
      {
        localStringBuffer.append(base64EncodeChars[(m >>> 2)]);
        localStringBuffer.append(base64EncodeChars[((m & 0x3) << 4)]);
        localStringBuffer.append("==");
      }
    }
    while (true)
    {
      return localStringBuffer.toString();
      int n = k + 1;
      int i1 = 0xFF & paramArrayOfByte[k];
      if (n == i)
      {
        localStringBuffer.append(base64EncodeChars[(m >>> 2)]);
        localStringBuffer.append(base64EncodeChars[((m & 0x3) << 4 | (i1 & 0xF0) >>> 4)]);
        localStringBuffer.append(base64EncodeChars[((i1 & 0xF) << 2)]);
        localStringBuffer.append("=");
        continue;
      }
      int i2 = n + 1;
      int i3 = 0xFF & paramArrayOfByte[n];
      localStringBuffer.append(base64EncodeChars[(m >>> 2)]);
      localStringBuffer.append(base64EncodeChars[((m & 0x3) << 4 | (i1 & 0xF0) >>> 4)]);
      localStringBuffer.append(base64EncodeChars[((i1 & 0xF) << 2 | (i3 & 0xC0) >>> 6)]);
      localStringBuffer.append(base64EncodeChars[(i3 & 0x3F)]);
      j = i2;
      break;
    }
  }

  private String encode(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("utf-8");
      String str = base64Encode(DesUtils.DesEncrypt("24Xdf8j6".getBytes("utf-8"), arrayOfByte, 1));
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return null;
  }

  public String buildUpon(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.mBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      String str3 = this.mBundle.getString(str2);
      if (TextUtils.isEmpty(str3))
        continue;
      if (localStringBuilder.length() != 0)
        localStringBuilder.append('&');
      localStringBuilder.append(str2).append('=').append(URLEncoder.encode(str3));
    }
    String str1 = encode(localStringBuilder.toString());
    Uri.Builder localBuilder = Uri.parse(paramString).buildUpon();
    localBuilder.appendQueryParameter("p", str1);
    return localBuilder.toString();
  }

  public RecommendParams put(String paramString1, String paramString2)
  {
    this.mBundle.putCharSequence(paramString1, paramString2);
    return this;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.tips.RecommendParams
 * JD-Core Version:    0.6.0
 */
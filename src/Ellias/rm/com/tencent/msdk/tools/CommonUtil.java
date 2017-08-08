package com.tencent.msdk.tools;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.json.JSONObject;

public class CommonUtil
{
  private static final char[] digits;
  static long timeEnd;
  static long timeStart = 0L;

  static
  {
    timeEnd = 0L;
    digits = new char[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  }

  public static final String arrayToString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfByte.length; i++)
      localStringBuffer.append(paramArrayOfByte[i] + " ");
    return localStringBuffer.toString();
  }

  public static byte[] bitmap2Bytes(Bitmap paramBitmap)
  {
    if (paramBitmap == null)
      return null;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static String bytes2BinString(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
    {
      Logger.d("on CommonUtil.bytes2BinString _bytes is null !");
      return null;
    }
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      String str = Integer.toBinaryString(0xFF & paramArrayOfByte[i]);
      for (int j = 8; j > str.length(); j--)
        localStringBuilder.append("0");
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }

  public static String bytes2HexStr(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return null;
    char[] arrayOfChar = new char[2 * paramArrayOfByte.length];
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      int j = paramArrayOfByte[i];
      arrayOfChar[(1 + i * 2)] = digits[(j & 0xF)];
      int k = (byte)(j >>> 4);
      arrayOfChar[(0 + i * 2)] = digits[(k & 0xF)];
    }
    return new String(arrayOfChar);
  }

  public static String bytes2HexString(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
    {
      Logger.d("on CommonUtil.bytes2HexString _bytes is null !");
      return null;
    }
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]).toUpperCase();
      if (str.length() < 2)
        localStringBuilder.append(0);
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }

  public static long calcUsedTime()
  {
    timeEnd = System.currentTimeMillis();
    long l = timeEnd - timeStart;
    Logger.d("useEdTime:" + l);
    return l;
  }

  public static byte char2Byte(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9'))
      return (byte)(paramChar - '0');
    if ((paramChar >= 'a') && (paramChar <= 'f'))
      return (byte)(10 + (paramChar - 'a'));
    if ((paramChar >= 'A') && (paramChar <= 'F'))
      return (byte)(10 + (paramChar - 'A'));
    return 0;
  }

  public static boolean ckIsEmpty(String paramString)
  {
    return (paramString == null) || (paramString.trim().equals("")) || (paramString.trim().equals("null"));
  }

  public static boolean ckNonEmpty(String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
      if (ckIsEmpty(paramArrayOfString[j]))
        return true;
    return false;
  }

  public static String encode(String paramString)
  {
    Object localObject = "";
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      localObject = str;
      label14: StringBuffer localStringBuffer = new StringBuffer(((String)localObject).length());
      int i = 0;
      if (i < ((String)localObject).length())
      {
        char c = ((String)localObject).charAt(i);
        if (c == '*')
          localStringBuffer.append("%2A");
        while (true)
        {
          i++;
          break;
          if (c == '+')
          {
            localStringBuffer.append("%20");
            continue;
          }
          if ((c == '%') && (i + 1 < ((String)localObject).length()) && (((String)localObject).charAt(i + 1) == '7') && (((String)localObject).charAt(i + 2) == 'E'))
          {
            localStringBuffer.append('~');
            i += 2;
            continue;
          }
          localStringBuffer.append(c);
        }
      }
      return localStringBuffer.toString();
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      break label14;
    }
  }

  public static String generateHttpBaseQueryString(String paramString1, String paramString2, String paramString3, HashMap<String, String> paramHashMap)
  {
    return null;
  }

  public static String generateQueryJson(Map<String, String> paramMap)
  {
    if ((paramMap == null) || (paramMap.isEmpty()))
      return "";
    return new JSONObject(paramMap).toString();
  }

  public static String generateQueryString(Map<String, String> paramMap)
  {
    String str1;
    if (paramMap == null)
      str1 = "";
    do
    {
      return str1;
      str1 = "";
    }
    while (paramMap.size() <= 0);
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      String str3 = encode((String)paramMap.get(str2));
      str1 = str1 + str2 + "=" + str3 + "&";
    }
    return str1.substring(0, -1 + str1.length());
  }

  public static String getNoQueryUrl(String paramString)
  {
    try
    {
      URL localURL = new URL(paramString);
      String str = new URL(localURL.getProtocol(), localURL.getHost(), localURL.getPort(), localURL.getPath()).toString();
      return str;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      localMalformedURLException.printStackTrace();
    }
    return null;
  }

  public static String getRandomString(int paramInt)
  {
    char[] arrayOfChar = new char[paramInt];
    Random localRandom = new Random();
    for (int i = 0; i < paramInt; i++)
      arrayOfChar[i] = (char)(97 + localRandom.nextInt(9));
    return new String(arrayOfChar);
  }

  public static byte[] hexStr2Bytes(String paramString)
  {
    byte[] arrayOfByte;
    if ((paramString == null) || (paramString.equals("")))
      arrayOfByte = null;
    while (true)
    {
      return arrayOfByte;
      arrayOfByte = new byte[paramString.length() / 2];
      for (int i = 0; i < arrayOfByte.length; i++)
      {
        char c1 = paramString.charAt(i * 2);
        char c2 = paramString.charAt(1 + i * 2);
        arrayOfByte[i] = (byte)(16 * char2Byte(c1) + char2Byte(c2));
      }
    }
  }

  public static boolean strIsInList(String paramString, List<String> paramList)
  {
    if (paramList == null);
    Iterator localIterator;
    do
      while (!localIterator.hasNext())
      {
        return true;
        localIterator = paramList.iterator();
      }
    while (!((String)localIterator.next()).equals(paramString));
    return false;
  }

  public static void testTimeBegain()
  {
    timeStart = System.currentTimeMillis();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.CommonUtil
 * JD-Core Version:    0.6.0
 */
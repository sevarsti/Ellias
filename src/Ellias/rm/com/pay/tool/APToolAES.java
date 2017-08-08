package com.pay.tool;

import com.pay.common.tool.APLog;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class APToolAES
{
  public static String decPress(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 5; ; i++)
    {
      if (i >= paramArrayOfByte.length)
        return localStringBuffer.toString();
      int j = (byte)(0xF & paramArrayOfByte[i] >> 4);
      int k = (byte)(0xF & paramArrayOfByte[i]);
      localStringBuffer.append(j);
      localStringBuffer.append(k);
    }
  }

  public static String decryptAES(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramString.getBytes(), "AES");
      Cipher localCipher = Cipher.getInstance("AES/ECB/NoPadding");
      localCipher.init(2, localSecretKeySpec);
      byte[] arrayOfByte2 = localCipher.doFinal(paramArrayOfByte);
      arrayOfByte1 = arrayOfByte2;
      if (arrayOfByte1 != null)
        return new String(arrayOfByte1);
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        byte[] arrayOfByte1 = null;
      }
    }
    return "";
  }

  public static String decryptDES(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramString.getBytes(), "AES");
      Cipher localCipher = Cipher.getInstance("AES/ECB/NoPadding");
      localCipher.init(2, localSecretKeySpec);
      byte[] arrayOfByte2 = localCipher.doFinal(paramArrayOfByte);
      arrayOfByte1 = arrayOfByte2;
      if (arrayOfByte1 != null)
        return new String(arrayOfByte1);
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        byte[] arrayOfByte1 = null;
      }
    }
    return "";
  }

  public static String doDecode(String paramString1, String paramString2)
  {
    return decryptAES(parseHexStr2Byte(paramString1), paramString2);
  }

  public static String doEncode(String paramString1, String paramString2)
  {
    for (String str = new String(paramString1); ; str = str + "a")
      if (str.length() % 16 == 0)
        return encryptAES(str, paramString2);
  }

  public static String encryptAES(String paramString1, String paramString2)
  {
    try
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramString2.getBytes(), "AES");
      Cipher localCipher = Cipher.getInstance("AES/ECB/NoPadding");
      localCipher.init(1, localSecretKeySpec);
      byte[] arrayOfByte2 = localCipher.doFinal(paramString1.getBytes());
      arrayOfByte1 = arrayOfByte2;
      if (arrayOfByte1 != null)
        paramString1 = new String(parseByte2HexStr(arrayOfByte1));
      return paramString1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        APLog.w("encryptAESTools", String.valueOf(localException));
        byte[] arrayOfByte1 = null;
      }
    }
  }

  public static String parseByte2HexStr(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfByte.length)
        return localStringBuffer.toString();
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() == 1)
        str = "0" + str;
      localStringBuffer.append(str.toUpperCase());
    }
  }

  public static byte[] parseHexStr2Byte(String paramString)
  {
    if (paramString.length() <= 0)
      return null;
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    for (int i = 0; ; i++)
    {
      if (i >= paramString.length() / 2)
        return arrayOfByte;
      int j = Integer.parseInt(paramString.substring(i << 1, 1 + (i << 1)), 16);
      arrayOfByte[i] = (byte)(Integer.parseInt(paramString.substring(1 + (i << 1), 2 + (i << 1)), 16) + (j << 4));
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APToolAES
 * JD-Core Version:    0.6.0
 */
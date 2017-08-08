package com.pay.common.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class APMD5
{
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

  public static String toMd5(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update(paramArrayOfByte);
      String str = parseByte2HexStr(localMessageDigest.digest());
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.common.tool.APMD5
 * JD-Core Version:    0.6.0
 */
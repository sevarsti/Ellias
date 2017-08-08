package com.tencent.msdk.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class MsdkSig
{
  public static String make(String paramString1, String paramString2)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      String str1 = paramString2 + paramString1;
      Logger.d("Original Sig: " + str1);
      localMessageDigest.update(str1.getBytes());
      String str2 = HexUtil.bytes2HexStr(localMessageDigest.digest()).toLowerCase(Locale.CHINA);
      return str2;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.MsdkSig
 * JD-Core Version:    0.6.0
 */
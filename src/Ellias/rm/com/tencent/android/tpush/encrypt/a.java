package com.tencent.android.tpush.encrypt;

import com.tencent.android.tpush.logging.TLog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class a
{
  public static String a(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      String str = a(localMessageDigest.digest());
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      TLog.e("TPush", "### md5 encrypt:" + paramString, localNoSuchAlgorithmException);
      return "";
    }
    catch (Exception localException)
    {
      while (true)
        TLog.e("TPush", "### md5 encrypt:" + paramString, localException);
    }
  }

  public static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfByte.length;
    for (int j = 0; j < i; j++)
      localStringBuilder.append(Integer.toHexString(0xFF & paramArrayOfByte[j]));
    return localStringBuilder.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.encrypt.a
 * JD-Core Version:    0.6.0
 */
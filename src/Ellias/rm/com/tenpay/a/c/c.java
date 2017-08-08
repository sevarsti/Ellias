package com.tenpay.a.c;

import java.security.MessageDigest;

public class c
{
  public static String a(String paramString)
  {
    int i = 0;
    if (paramString == null)
      return "";
    char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte = localMessageDigest.digest();
      char[] arrayOfChar2 = new char[32];
      int j = 0;
      while (true)
      {
        if (i >= 16)
          return new String(arrayOfChar2);
        int k = arrayOfByte[i];
        int m = j + 1;
        arrayOfChar2[j] = arrayOfChar1[(0xF & k >>> 4)];
        j = m + 1;
        arrayOfChar2[m] = arrayOfChar1[(k & 0xF)];
        i++;
      }
    }
    catch (Exception localException)
    {
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.a.c.c
 * JD-Core Version:    0.6.0
 */
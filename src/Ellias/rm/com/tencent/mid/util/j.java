package com.tencent.mid.util;

public class j
{
  public static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return null;
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() < 2)
        localStringBuilder.append(0);
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString().toUpperCase();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.util.j
 * JD-Core Version:    0.6.0
 */
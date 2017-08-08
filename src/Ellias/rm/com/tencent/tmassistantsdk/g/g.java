package com.tencent.tmassistantsdk.g;

public class g
{
  static final byte[] a = { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

  public static String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return "";
    String str = "";
    for (int i = 0; i < paramArrayOfByte.length; i++)
      str = str + Integer.toString(256 + (0xFF & paramArrayOfByte[i]), 16).substring(1);
    return str.toUpperCase();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.g.g
 * JD-Core Version:    0.6.0
 */
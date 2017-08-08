package com.tencent.msdk.tools;

public class Base64Util
{
  private static final char[] encodeTable;
  private static final char last2byte = (char)Integer.parseInt("00000011", 2);
  private static final char last4byte = (char)Integer.parseInt("00001111", 2);
  private static final char last6byte = (char)Integer.parseInt("00111111", 2);
  private static final char lead2byte;
  private static final char lead4byte;
  private static final char lead6byte = (char)Integer.parseInt("11111100", 2);

  static
  {
    lead4byte = (char)Integer.parseInt("11110000", 2);
    lead2byte = (char)Integer.parseInt("11000000", 2);
    encodeTable = new char[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  }

  public static String encode(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(3 + (int)(1.34D * paramArrayOfByte.length));
    int i = 0;
    int j = 0;
    for (int k = 0; k < paramArrayOfByte.length; k++)
    {
      i %= 8;
      if (i >= 8)
        continue;
      switch (i)
      {
      case 1:
      case 3:
      case 5:
      default:
      case 0:
      case 2:
      case 4:
      case 6:
      }
      while (true)
      {
        localStringBuffer.append(encodeTable[j]);
        i += 6;
        break;
        j = (char)((char)(paramArrayOfByte[k] & lead6byte) >>> '\002');
        continue;
        j = (char)(paramArrayOfByte[k] & last6byte);
        continue;
        j = (char)((char)(paramArrayOfByte[k] & last4byte) << '\002');
        if (k + 1 >= paramArrayOfByte.length)
          continue;
        j = (char)(j | (paramArrayOfByte[(k + 1)] & lead2byte) >>> 6);
        continue;
        j = (char)((char)(paramArrayOfByte[k] & last2byte) << '\004');
        if (k + 1 >= paramArrayOfByte.length)
          continue;
        j = (char)(j | (paramArrayOfByte[(k + 1)] & lead4byte) >>> 4);
      }
    }
    if (localStringBuffer.length() % 4 != 0)
      for (int m = 4 - localStringBuffer.length() % 4; m > 0; m--)
        localStringBuffer.append("=");
    return localStringBuffer.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.Base64Util
 * JD-Core Version:    0.6.0
 */
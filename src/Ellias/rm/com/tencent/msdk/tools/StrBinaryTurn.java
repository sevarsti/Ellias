package com.tencent.msdk.tools;

public class StrBinaryTurn
{
  private static char BinstrToChar(String paramString)
  {
    int[] arrayOfInt = BinstrToIntArray(paramString);
    int i = 0;
    for (int j = 0; j < arrayOfInt.length; j++)
      i += (arrayOfInt[(-1 + arrayOfInt.length - j)] << j);
    return (char)i;
  }

  private static int[] BinstrToIntArray(String paramString)
  {
    char[] arrayOfChar = paramString.toCharArray();
    int[] arrayOfInt = new int[arrayOfChar.length];
    for (int i = 0; i < arrayOfChar.length; i++)
      arrayOfInt[i] = ('￐' + arrayOfChar[i]);
    return arrayOfInt;
  }

  public static String BinstrToStr(String paramString)
  {
    String[] arrayOfString = StrToStrArray(paramString);
    char[] arrayOfChar = new char[arrayOfString.length];
    for (int i = 0; i < arrayOfString.length; i++)
      arrayOfChar[i] = BinstrToChar(arrayOfString[i]);
    return String.valueOf(arrayOfChar);
  }

  public static String StrToBinstr(String paramString)
  {
    char[] arrayOfChar = paramString.toCharArray();
    String str = "";
    for (int i = 0; i < arrayOfChar.length; i++)
      str = str + Integer.toBinaryString(arrayOfChar[i]) + " ";
    return str;
  }

  private static String[] StrToStrArray(String paramString)
  {
    return paramString.split(" ");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.StrBinaryTurn
 * JD-Core Version:    0.6.0
 */
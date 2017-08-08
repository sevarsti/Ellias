package com.tencent.smtt.sdk.stat;

import java.lang.reflect.Array;

public class DesUtils
{
  private static final int[] E;
  public static final int FLAG_DECRYPT = 0;
  public static final int FLAG_ENCRYPT = 1;
  private static final int[] IP = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
  private static final int[] IP_1 = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };
  public static final byte[] KEY;
  private static final int[] LeftMove;
  public static final byte[] MAC_KEY;
  public static final byte[] MTT_KEY;
  private static final int[] P;
  private static final int[] PC_1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };
  private static final int[] PC_2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
  public static final byte[] QQMARKET_KEY;
  public static final byte[] REPORT_KEY_TEA;
  private static final int[][][] S_Box;

  static
  {
    E = new int[] { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
    P = new int[] { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };
    S_Box = new int[][][] { { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 }, { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 }, { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 }, { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } }, { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 }, { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 }, { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 }, { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } }, { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 }, { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 }, { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 }, { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } }, { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 }, { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 }, { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 }, { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } }, { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 }, { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 }, { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 }, { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } }, { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 }, { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 }, { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 }, { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } }, { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 }, { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 }, { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 }, { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } }, { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 }, { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 }, { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 }, { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } } };
    LeftMove = new int[] { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
    REPORT_KEY_TEA = new byte[] { 98, -24, 57, -84, -115, 117, 55, 121 };
    KEY = new byte[] { -25, -101, -115, 1, 47, 7, -27, -59, 18, -128, 123, 79, -44, 37, 46, 115 };
    MAC_KEY = new byte[] { 37, -110, 60, 127, 42, -27, -17, -110 };
    MTT_KEY = new byte[] { -122, -8, -23, -84, -125, 113, 84, 99 };
    QQMARKET_KEY = "AL!#$AC9Ahg@KLJ1".getBytes();
  }

  private static byte[] ByteDataFormat(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    int j = 8 - i % 8;
    int k = i + j;
    byte[] arrayOfByte = new byte[k];
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, i);
    for (int m = i; m < k; m++)
      arrayOfByte[m] = (byte)j;
    return arrayOfByte;
  }

  public static byte[] DesEncrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    if ((paramArrayOfByte2 == null) || (paramArrayOfByte1 == null))
      return paramArrayOfByte2;
    try
    {
      byte[] arrayOfByte1 = KeyDataFormat(paramArrayOfByte1);
      byte[] arrayOfByte2 = ByteDataFormat(paramArrayOfByte2);
      int i = arrayOfByte2.length;
      int j = i / 8;
      byte[] arrayOfByte3 = new byte[i];
      for (int k = 0; k < j; k++)
      {
        byte[] arrayOfByte4 = new byte[8];
        byte[] arrayOfByte5 = new byte[8];
        System.arraycopy(arrayOfByte1, 0, arrayOfByte4, 0, 8);
        System.arraycopy(arrayOfByte2, k * 8, arrayOfByte5, 0, 8);
        System.arraycopy(UnitDes(arrayOfByte4, arrayOfByte5, paramInt), 0, arrayOfByte3, k * 8, 8);
      }
      byte[] arrayOfByte6;
      int m;
      int n;
      if (paramInt == 0)
      {
        arrayOfByte6 = new byte[paramArrayOfByte2.length];
        System.arraycopy(arrayOfByte3, 0, arrayOfByte6, 0, arrayOfByte6.length);
        m = arrayOfByte6[(-1 + arrayOfByte6.length)];
        if ((m > 0) && (m <= 8))
          n = 1;
      }
      for (int i1 = 0; ; i1++)
      {
        if (i1 < m)
        {
          if (m == arrayOfByte6[(-1 + arrayOfByte6.length - i1)])
            continue;
          n = 0;
        }
        if (n != 0)
        {
          arrayOfByte3 = new byte[arrayOfByte6.length - m];
          System.arraycopy(arrayOfByte6, 0, arrayOfByte3, 0, arrayOfByte3.length);
        }
        return arrayOfByte3;
      }
    }
    catch (Exception localException)
    {
    }
    return paramArrayOfByte2;
  }

  private static byte[] Encrypt(int[] paramArrayOfInt, int paramInt, int[][] paramArrayOfInt1)
  {
    byte[] arrayOfByte = new byte[8];
    int[] arrayOfInt1 = new int[64];
    int[] arrayOfInt2 = new int[64];
    for (int i = 0; i < 64; i++)
      arrayOfInt1[i] = paramArrayOfInt[(-1 + IP[i])];
    if (paramInt == 1)
      for (int m = 0; m < 16; m++)
        LoopF(arrayOfInt1, m, paramInt, paramArrayOfInt1);
    if (paramInt == 0)
      for (int k = 15; k > -1; k--)
        LoopF(arrayOfInt1, k, paramInt, paramArrayOfInt1);
    for (int j = 0; j < 64; j++)
      arrayOfInt2[j] = arrayOfInt1[(-1 + IP_1[j])];
    GetEncryptResultOfByteArray(arrayOfInt2, arrayOfByte);
    return arrayOfByte;
  }

  private static void GetEncryptResultOfByteArray(int[] paramArrayOfInt, byte[] paramArrayOfByte)
  {
    for (int i = 0; i < 8; i++)
      for (int j = 0; j < 8; j++)
        paramArrayOfByte[i] = (byte)(paramArrayOfByte[i] + (paramArrayOfInt[(j + (i << 3))] << 7 - j));
  }

  private static byte[] KeyDataFormat(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[8];
    for (int i = 0; i < arrayOfByte.length; i++)
      arrayOfByte[i] = 0;
    if (paramArrayOfByte.length > 8)
    {
      System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, arrayOfByte.length);
      return arrayOfByte;
    }
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramArrayOfByte.length);
    return arrayOfByte;
  }

  private static void KeyInitialize(int[] paramArrayOfInt, int[][] paramArrayOfInt1)
  {
    int[] arrayOfInt = new int[56];
    for (int i = 0; i < 56; i++)
      arrayOfInt[i] = paramArrayOfInt[(-1 + PC_1[i])];
    for (int j = 0; j < 16; j++)
    {
      LeftBitMove(arrayOfInt, LeftMove[j]);
      for (int k = 0; k < 48; k++)
        paramArrayOfInt1[j][k] = arrayOfInt[(-1 + PC_2[k])];
    }
  }

  private static void LeftBitMove(int[] paramArrayOfInt, int paramInt)
  {
    int[] arrayOfInt1 = new int[28];
    int[] arrayOfInt2 = new int[28];
    int[] arrayOfInt3 = new int[28];
    int[] arrayOfInt4 = new int[28];
    for (int i = 0; i < 28; i++)
    {
      arrayOfInt1[i] = paramArrayOfInt[i];
      arrayOfInt2[i] = paramArrayOfInt[(i + 28)];
    }
    if (paramInt == 1)
    {
      for (int m = 0; m < 27; m++)
      {
        arrayOfInt3[m] = arrayOfInt1[(m + 1)];
        arrayOfInt4[m] = arrayOfInt2[(m + 1)];
      }
      arrayOfInt3[27] = arrayOfInt1[0];
      arrayOfInt4[27] = arrayOfInt2[0];
    }
    while (true)
    {
      for (int k = 0; k < 28; k++)
      {
        paramArrayOfInt[k] = arrayOfInt3[k];
        paramArrayOfInt[(k + 28)] = arrayOfInt4[k];
      }
      if (paramInt != 2)
        continue;
      for (int j = 0; j < 26; j++)
      {
        arrayOfInt3[j] = arrayOfInt1[(j + 2)];
        arrayOfInt4[j] = arrayOfInt2[(j + 2)];
      }
      arrayOfInt3[26] = arrayOfInt1[0];
      arrayOfInt4[26] = arrayOfInt2[0];
      arrayOfInt3[27] = arrayOfInt1[1];
      arrayOfInt4[27] = arrayOfInt2[1];
    }
  }

  private static void LoopF(int[] paramArrayOfInt, int paramInt1, int paramInt2, int[][] paramArrayOfInt1)
  {
    int[] arrayOfInt1 = new int[32];
    int[] arrayOfInt2 = new int[32];
    int[] arrayOfInt3 = new int[32];
    int[] arrayOfInt4 = new int[32];
    int[] arrayOfInt5 = new int[48];
    int[] arrayOfInt6 = { 8, 6 };
    int[][] arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, arrayOfInt6);
    int[] arrayOfInt7 = new int[8];
    int[] arrayOfInt8 = new int[32];
    int[] arrayOfInt9 = new int[32];
    for (int i = 0; i < 32; i++)
    {
      arrayOfInt1[i] = paramArrayOfInt[i];
      arrayOfInt2[i] = paramArrayOfInt[(i + 32)];
    }
    for (int j = 0; j < 48; j++)
    {
      arrayOfInt5[j] = arrayOfInt2[(-1 + E[j])];
      arrayOfInt5[j] += paramArrayOfInt1[paramInt1][j];
      if (arrayOfInt5[j] != 2)
        continue;
      arrayOfInt5[j] = 0;
    }
    for (int k = 0; k < 8; k++)
    {
      for (int n = 0; n < 6; n++)
        arrayOfInt[k][n] = arrayOfInt5[(n + k * 6)];
      arrayOfInt7[k] = S_Box[k][((arrayOfInt[k][0] << 1) + arrayOfInt[k][5])][((arrayOfInt[k][1] << 3) + (arrayOfInt[k][2] << 2) + (arrayOfInt[k][3] << 1) + arrayOfInt[k][4])];
      for (int i1 = 0; i1 < 4; i1++)
      {
        arrayOfInt8[(3 + k * 4 - i1)] = (arrayOfInt7[k] % 2);
        arrayOfInt7[k] /= 2;
      }
    }
    int m = 0;
    if (m < 32)
    {
      arrayOfInt9[m] = arrayOfInt8[(-1 + P[m])];
      arrayOfInt3[m] = arrayOfInt2[m];
      arrayOfInt1[m] += arrayOfInt9[m];
      if (arrayOfInt4[m] == 2)
        arrayOfInt4[m] = 0;
      if (((paramInt2 == 0) && (paramInt1 == 0)) || ((paramInt2 == 1) && (paramInt1 == 15)))
      {
        paramArrayOfInt[m] = arrayOfInt4[m];
        paramArrayOfInt[(m + 32)] = arrayOfInt3[m];
      }
      while (true)
      {
        m++;
        break;
        paramArrayOfInt[m] = arrayOfInt3[m];
        paramArrayOfInt[(m + 32)] = arrayOfInt4[m];
      }
    }
  }

  private static int[] ReadDataToBirnaryIntArray(byte[] paramArrayOfByte)
  {
    int[] arrayOfInt1 = new int[8];
    for (int i = 0; i < 8; i++)
    {
      arrayOfInt1[i] = paramArrayOfByte[i];
      if (arrayOfInt1[i] >= 0)
        continue;
      arrayOfInt1[i] = (256 + arrayOfInt1[i]);
      arrayOfInt1[i] %= 256;
    }
    int[] arrayOfInt2 = new int[64];
    for (int j = 0; j < 8; j++)
      for (int k = 0; k < 8; k++)
      {
        arrayOfInt2[(7 + j * 8 - k)] = (arrayOfInt1[j] % 2);
        arrayOfInt1[j] /= 2;
      }
    return arrayOfInt2;
  }

  private static byte[] UnitDes(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    if ((paramArrayOfByte1.length != 8) || (paramArrayOfByte2.length != 8) || ((paramInt != 1) && (paramInt != 0)))
      throw new RuntimeException("Data Format Error !");
    new int[64];
    new int[64];
    new byte[8];
    int[] arrayOfInt1 = { 16, 48 };
    int[][] arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, arrayOfInt1);
    int[] arrayOfInt2 = ReadDataToBirnaryIntArray(paramArrayOfByte1);
    int[] arrayOfInt3 = ReadDataToBirnaryIntArray(paramArrayOfByte2);
    KeyInitialize(arrayOfInt2, arrayOfInt);
    return Encrypt(arrayOfInt3, paramInt, arrayOfInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.stat.DesUtils
 * JD-Core Version:    0.6.0
 */
package com.tencent.android.tpush.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class f
{
  public static Object a(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return null;
    try
    {
      Object localObject = new ObjectInputStream(new ByteArrayInputStream(b(paramString))).readObject();
      return localObject;
    }
    catch (Exception localException)
    {
    }
    throw localException;
  }

  public static String a(Serializable paramSerializable)
  {
    if (paramSerializable == null)
      return "";
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
      localObjectOutputStream.writeObject(paramSerializable);
      localObjectOutputStream.close();
      String str = a(localByteArrayOutputStream.toByteArray());
      return str;
    }
    catch (Exception localException)
    {
    }
    throw localException;
  }

  public static String a(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      localStringBuffer.append((char)(97 + (0xF & paramArrayOfByte[i] >> 4)));
      localStringBuffer.append((char)(97 + (0xF & paramArrayOfByte[i])));
    }
    return localStringBuffer.toString();
  }

  public static byte[] b(String paramString)
  {
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    for (int i = 0; i < paramString.length(); i += 2)
    {
      int j = paramString.charAt(i);
      int k = i / 2;
      if (k >= arrayOfByte.length)
        continue;
      arrayOfByte[k] = (byte)(j - 97 << 4);
      int m = paramString.charAt(i + 1);
      arrayOfByte[k] = (byte)(arrayOfByte[k] + (m - 97));
    }
    return arrayOfByte;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.common.f
 * JD-Core Version:    0.6.0
 */
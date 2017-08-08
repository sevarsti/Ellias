package com.tencent.component.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.FileDescriptor;

public class DecodeUtils
{
  public static Bitmap a(FileDescriptor paramFileDescriptor, BitmapFactory.Options paramOptions)
  {
    if (paramOptions == null)
      paramOptions = new BitmapFactory.Options();
    return BitmapFactory.decodeFileDescriptor(paramFileDescriptor, null, paramOptions);
  }

  public static Bitmap a(String paramString, BitmapFactory.Options paramOptions)
  {
    if (paramOptions == null)
      paramOptions = new BitmapFactory.Options();
    return BitmapFactory.decodeFile(paramString, paramOptions);
  }

  public static Bitmap a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BitmapFactory.Options paramOptions)
  {
    if (paramOptions == null)
      paramOptions = new BitmapFactory.Options();
    return BitmapFactory.decodeByteArray(paramArrayOfByte, paramInt1, paramInt2, paramOptions);
  }

  public static Bitmap a(byte[] paramArrayOfByte, BitmapFactory.Options paramOptions)
  {
    return a(paramArrayOfByte, 0, paramArrayOfByte.length, paramOptions);
  }

  public static void b(FileDescriptor paramFileDescriptor, BitmapFactory.Options paramOptions)
  {
    if (paramOptions != null);
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      paramOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFileDescriptor(paramFileDescriptor, null, paramOptions);
      paramOptions.inJustDecodeBounds = false;
      return;
    }
  }

  public static void b(String paramString, BitmapFactory.Options paramOptions)
  {
    if (paramOptions != null);
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      paramOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, paramOptions);
      paramOptions.inJustDecodeBounds = false;
      return;
    }
  }

  public static void b(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BitmapFactory.Options paramOptions)
  {
    if (paramOptions != null);
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      paramOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeByteArray(paramArrayOfByte, paramInt1, paramInt2, paramOptions);
      paramOptions.inJustDecodeBounds = false;
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.DecodeUtils
 * JD-Core Version:    0.6.0
 */
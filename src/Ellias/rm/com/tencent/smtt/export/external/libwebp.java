package com.tencent.smtt.export.external;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class libwebp
{
  private static final int BITMAP_ALPHA_8 = 1;
  private static final int BITMAP_ARGB_4444 = 3;
  private static final int BITMAP_ARGB_8888 = 4;
  private static final int BITMAP_RGB_565 = 2;
  private static final String LOGTAG = "[image]";
  private static boolean isMultiCore;
  private static libwebp mInstance = null;
  private static boolean mIsLoadLibSuccess = false;
  private int mBitmapType = 4;

  static
  {
    isMultiCore = false;
  }

  private String getCPUinfo()
  {
    String str = "";
    try
    {
      InputStream localInputStream = new ProcessBuilder(new String[] { "/system/bin/cat", "/proc/cpuinfo" }).start().getInputStream();
      byte[] arrayOfByte = new byte[1024];
      while (localInputStream.read(arrayOfByte) != -1)
        str = str + new String(arrayOfByte);
      localInputStream.close();
      return str;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return str;
  }

  public static libwebp getInstance(Context paramContext)
  {
    if (mInstance == null)
    {
      loadWepLibraryIfNeed(paramContext);
      mInstance = new libwebp();
    }
    return mInstance;
  }

  private boolean isMultiCore()
  {
    return getCPUinfo().contains("processor");
  }

  public static void loadWepLibraryIfNeed(Context paramContext)
  {
    if (!mIsLoadLibSuccess);
    try
    {
      LibraryLoader.loadLibrary(paramContext, "webp_base");
      mIsLoadLibSuccess = true;
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      Log.e("[image]", "Load WebP Library Error...: libwebp.java - loadWepLibraryIfNeed()");
    }
  }

  public static void loadWepLibraryIfNeed(Context paramContext, String paramString)
  {
    if (!mIsLoadLibSuccess);
    try
    {
      System.load(paramString + File.separator + "libwebp_base.so");
      mIsLoadLibSuccess = true;
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      Log.e("[image]", "Load WebP Library Error...: libwebp.java - loadWepLibraryIfNeed()");
    }
  }

  public int[] decodeBase(byte[] paramArrayOfByte, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if (!mIsLoadLibSuccess)
    {
      Log.e("[image]", "Load WebP Library Error...");
      return null;
    }
    return nativeDecode(paramArrayOfByte, isMultiCore, paramArrayOfInt1, paramArrayOfInt2);
  }

  public int[] decodeBase_16bit(byte[] paramArrayOfByte, Bitmap.Config paramConfig)
  {
    if (!mIsLoadLibSuccess)
    {
      Log.e("[image]", "Load WebP Library Error...");
      return null;
    }
    switch (1.$SwitchMap$android$graphics$Bitmap$Config[paramConfig.ordinal()])
    {
    default:
      this.mBitmapType = 2;
    case 1:
    case 2:
    }
    while (true)
    {
      return nativeDecode_16bit(paramArrayOfByte, isMultiCore, this.mBitmapType);
      this.mBitmapType = 3;
      continue;
      this.mBitmapType = 2;
    }
  }

  public int[] decodeInto(byte[] paramArrayOfByte, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if (!mIsLoadLibSuccess)
    {
      Log.e("[image]", "Load WebP Library Error...");
      return null;
    }
    return nativeDecodeInto(paramArrayOfByte, isMultiCore, paramArrayOfInt1, paramArrayOfInt2);
  }

  public int getInfo(byte[] paramArrayOfByte, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if (!mIsLoadLibSuccess)
      return 0;
    return nativeGetInfo(paramArrayOfByte, paramArrayOfInt1, paramArrayOfInt2);
  }

  public int[] incDecode(byte[] paramArrayOfByte, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if (!mIsLoadLibSuccess)
    {
      Log.e("[image]", "Load WebP Library Error...");
      return null;
    }
    return nativeIDecode(paramArrayOfByte, isMultiCore, paramArrayOfInt1, paramArrayOfInt2);
  }

  public native int[] nativeDecode(byte[] paramArrayOfByte, boolean paramBoolean, int[] paramArrayOfInt1, int[] paramArrayOfInt2);

  public native int[] nativeDecodeInto(byte[] paramArrayOfByte, boolean paramBoolean, int[] paramArrayOfInt1, int[] paramArrayOfInt2);

  public native int[] nativeDecode_16bit(byte[] paramArrayOfByte, boolean paramBoolean, int paramInt);

  public native int nativeGetInfo(byte[] paramArrayOfByte, int[] paramArrayOfInt1, int[] paramArrayOfInt2);

  public native int[] nativeIDecode(byte[] paramArrayOfByte, boolean paramBoolean, int[] paramArrayOfInt1, int[] paramArrayOfInt2);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.export.external.libwebp
 * JD-Core Version:    0.6.0
 */
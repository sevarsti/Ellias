package com.tencent.component.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils
{
  private static final int a = 90;

  public static Bitmap a(Bitmap paramBitmap, int paramInt)
  {
    int i = paramInt % 360;
    if (i == 0);
    while (true)
    {
      return paramBitmap;
      int j;
      label41: int k;
      label51: int m;
      if (((i > 45) && (i < 135)) || ((i > 225) && (i < 315)))
      {
        j = 1;
        if (j != 0)
          break label185;
        k = paramBitmap.getWidth();
        if (j != 0)
          break label194;
        m = paramBitmap.getHeight();
      }
      try
      {
        while (true)
        {
          Bitmap localBitmap2 = Bitmap.createBitmap(k, m, paramBitmap.getConfig());
          localBitmap1 = localBitmap2;
          if ((localBitmap1 == null) || (localBitmap1 == paramBitmap))
            break;
          Canvas localCanvas = new Canvas(localBitmap1);
          int n = (k - paramBitmap.getWidth()) / 2;
          int i1 = (m - paramBitmap.getHeight()) / 2;
          if ((n != 0) || (i1 != 0))
            localCanvas.translate(n, i1);
          localCanvas.rotate(i, paramBitmap.getWidth() / 2, paramBitmap.getHeight() / 2);
          localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, null);
          paramBitmap.recycle();
          return localBitmap1;
          j = 0;
          break label41;
          label185: k = paramBitmap.getHeight();
          break label51;
          label194: m = paramBitmap.getWidth();
        }
      }
      catch (Throwable localThrowable)
      {
        while (true)
          Bitmap localBitmap1 = null;
      }
    }
  }

  public static Bitmap a(Bitmap paramBitmap, View paramView, int paramInt1, int paramInt2, int paramInt3, Drawable paramDrawable)
  {
    Log.d("drawViewToBitmap", "view" + paramInt1 + " " + paramInt2);
    float f = 1.0F / paramInt3;
    int i = paramView.getHeight();
    paramView.layout(0, 0, paramInt1, paramInt2);
    int j = (int)(f * paramInt1);
    int k = (int)(f * paramInt2);
    Log.d("drawViewToBitmap", "bmpview" + j + " " + k);
    Log.d("drawViewToBitmap", "heightCopy" + i);
    if ((paramBitmap == null) || (paramBitmap.getWidth() != j) || (paramBitmap.getHeight() != k))
      paramBitmap = Bitmap.createBitmap(j, k, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(paramBitmap);
    paramDrawable.setBounds(new Rect(0, 0, paramInt1, paramInt2));
    paramDrawable.draw(localCanvas);
    if (paramInt3 > 1)
      localCanvas.scale(f, f);
    paramView.draw(localCanvas);
    paramView.layout(0, 0, paramInt1, i);
    return paramBitmap;
  }

  @SuppressLint({"InlinedApi"})
  public static Bitmap a(Bitmap paramBitmap, String paramString)
  {
    if ((paramBitmap == null) || (paramString == null));
    while (true)
    {
      return paramBitmap;
      if (PlatformUtil.version() < 5)
        continue;
      try
      {
        localExifInterface = new ExifInterface(paramString);
        if (localExifInterface == null)
          continue;
        int i = localExifInterface.getAttributeInt("Orientation", 0);
        j = 0;
        switch (i)
        {
        case 4:
        case 5:
        case 7:
        default:
          return a(paramBitmap, j);
        case 6:
        case 3:
        case 8:
        }
      }
      catch (Throwable localThrowable)
      {
        while (true)
        {
          localThrowable.printStackTrace();
          ExifInterface localExifInterface = null;
          continue;
          int j = 90;
          continue;
          j = 180;
          continue;
          j = 270;
        }
      }
    }
  }

  public static Bitmap a(View paramView)
  {
    boolean bool = paramView.willNotCacheDrawing();
    paramView.setWillNotCacheDrawing(false);
    int i = paramView.getDrawingCacheBackgroundColor();
    paramView.setDrawingCacheBackgroundColor(0);
    if (i != 0)
      paramView.destroyDrawingCache();
    paramView.buildDrawingCache();
    Bitmap localBitmap1 = paramView.getDrawingCache();
    if (localBitmap1 == null)
      return null;
    Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1);
    paramView.destroyDrawingCache();
    paramView.setWillNotCacheDrawing(bool);
    paramView.setDrawingCacheBackgroundColor(i);
    return localBitmap2;
  }

  public static Bitmap a(View paramView, int paramInt1, int paramInt2)
  {
    boolean bool = paramView.willNotCacheDrawing();
    paramView.setWillNotCacheDrawing(false);
    int i = paramView.getDrawingCacheBackgroundColor();
    paramView.setDrawingCacheBackgroundColor(0);
    if (i != 0)
      paramView.destroyDrawingCache();
    paramView.buildDrawingCache();
    Bitmap localBitmap1 = paramView.getDrawingCache();
    if (localBitmap1 == null)
      return null;
    Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, paramInt1, paramInt2);
    paramView.destroyDrawingCache();
    paramView.setWillNotCacheDrawing(bool);
    paramView.setDrawingCacheBackgroundColor(i);
    return localBitmap2;
  }

  public static byte[] a(Bitmap paramBitmap)
  {
    return a(paramBitmap, 90, Bitmap.CompressFormat.JPEG);
  }

  public static byte[] a(Bitmap paramBitmap, int paramInt, Bitmap.CompressFormat paramCompressFormat)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(65536);
    paramBitmap.compress(paramCompressFormat, paramInt, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] a(Bitmap paramBitmap, Bitmap.CompressFormat paramCompressFormat)
  {
    return a(paramBitmap, 90, paramCompressFormat);
  }

  public static void b(Bitmap paramBitmap, String paramString)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 40, localByteArrayOutputStream);
      File localFile = new File(Environment.getExternalStorageDirectory() + "/tmp" + File.separator + paramString);
      localFile.createNewFile();
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      localFileOutputStream.write(localByteArrayOutputStream.toByteArray());
      localFileOutputStream.close();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.BitmapUtils
 * JD-Core Version:    0.6.0
 */
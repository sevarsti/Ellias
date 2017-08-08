package com.tencent.game.helper;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.opengl.GLSurfaceView.Renderer;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class m3eRenderer
  implements GLSurfaceView.Renderer, MediaPlayer.OnCompletionListener
{
  private static final long NANOSECONDSPERMINISECOND = 1000000L;
  private static final long NANOSECONDSPERSECOND = 1000000000L;
  private static long animationInterval;
  static int frames;
  public static boolean isShotScreen = false;
  private static int m_nFPS;
  static long startTime = System.nanoTime();
  private long RenderCostTime;
  private long last;
  private m3eActivity mActivity;
  private long mFrameCount = 0L;
  private boolean mInitialed = false;
  public boolean mbTouched = true;

  static
  {
    frames = 0;
    animationInterval = 16666666L;
    m_nFPS = 60;
  }

  public m3eRenderer(Activity paramActivity)
  {
    this.mActivity = ((m3eActivity)paramActivity);
  }

  private byte[] Bitmap2Bytes(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static Bitmap SavePixels(int paramInt1, int paramInt2, int paramInt3, int paramInt4, GL10 paramGL10)
  {
    Log.e("SavePixels", "SavePixels render start1");
    int[] arrayOfInt1 = new int[paramInt3 * (paramInt2 + paramInt4)];
    Log.e("SavePixels", "SavePixels render start2");
    int[] arrayOfInt2 = new int[paramInt3 * paramInt4];
    Log.e("SavePixels", "SavePixels render start3");
    IntBuffer localIntBuffer = IntBuffer.wrap(arrayOfInt1);
    Log.e("SavePixels", "SavePixels render start4");
    localIntBuffer.position(0);
    Log.e("SavePixels", "SavePixels render start5");
    paramGL10.glReadPixels(paramInt1, 0, paramInt3, paramInt2 + paramInt4, 6408, 5121, localIntBuffer);
    Log.e("SavePixels", "SavePixels render start6");
    int i = 0;
    int j = 0;
    while (j < paramInt4)
    {
      for (int k = 0; k < paramInt3; k++)
      {
        int m = arrayOfInt1[(k + j * paramInt3)];
        int n = 0xFF & m >> 16 | (0xFF0000 & m << 16 | m & 0xFF00FF00);
        arrayOfInt2[(k + paramInt3 * (-1 + (paramInt4 - i)))] = n;
      }
      j++;
      i++;
    }
    Log.e("SavePixels", "SavePixels render start7");
    Bitmap localBitmap = Bitmap.createBitmap(arrayOfInt2, paramInt3, paramInt4, Bitmap.Config.ARGB_8888);
    Log.e("SavePixels", "SavePixels render start7");
    return localBitmap;
  }

  public static void SetAnimationInterval(int paramInt)
  {
    m_nFPS = paramInt;
    animationInterval = ()(1000000000.0D * (1.0D / paramInt));
  }

  private static native void nativeInit(int paramInt1, int paramInt2);

  private static native void nativeRender();

  private static native void nativeResize(int paramInt1, int paramInt2);

  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    Log.e("m3e", "onCompletion");
  }

  public void onDrawFrame(GL10 paramGL10)
  {
    if (this.mbTouched)
    {
      if (!this.mInitialed)
      {
        this.mInitialed = true;
        DisplayMetrics localDisplayMetrics = this.mActivity.getResources().getDisplayMetrics();
        Log.d(m3eActivity.S_LOGTAG, "Screen size : " + localDisplayMetrics.widthPixels + " " + localDisplayMetrics.heightPixels);
        nativeInit(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
        this.last = System.nanoTime();
      }
    }
    else
      return;
    frames = 1 + frames;
    if (System.nanoTime() - startTime >= 1000000000L)
    {
      Log.d("FPSCounter", "jackson999991111 FPSNOW: " + frames);
      frames = 0;
      startTime = System.nanoTime();
    }
    if ((m_nFPS < 60) && (this.RenderCostTime < animationInterval));
    try
    {
      Thread.sleep((animationInterval - this.RenderCostTime) / 1000000L);
      label193: long l = System.nanoTime();
      nativeRender();
      this.mFrameCount = (1L + this.mFrameCount);
      if (isShotScreen)
      {
        Log.e("isShotScreen", "isShotScreen render start1");
        isShotScreen = false;
        Log.e("isShotScreen", "isShotScreen render start2");
        Bitmap localBitmap1 = SavePixels(0, 0, m3eActivity.s_screenW, m3eActivity.s_screenH, paramGL10);
        Log.e("isShotScreen", "isShotScreen render start3");
        Matrix localMatrix = new Matrix();
        Log.e("isShotScreen", "isShotScreen render start4");
        localMatrix.postScale(960.0F / m3eActivity.s_screenW, 640.0F / m3eActivity.s_screenH);
        Log.e("isShotScreen", "isShotScreen render start5");
        Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, m3eActivity.s_screenW, m3eActivity.s_screenH, localMatrix, true);
        Log.e("isShotScreen", "isShotScreen render start6");
        m3eActivity.SaveShotSceen(localBitmap2);
        Log.e("isShotScreen", "isShotScreen render start7");
      }
      this.RenderCostTime = (System.nanoTime() - l);
      this.last = l;
      return;
    }
    catch (Exception localException)
    {
      break label193;
    }
  }

  public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2)
  {
    Log.d(m3eActivity.S_LOGTAG, "GLSurfaceView.Renderer.onSurfaceChanged");
    nativeResize(paramInt1, paramInt2);
  }

  public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
  {
    Log.d(m3eActivity.S_LOGTAG, "GLSurfaceView.Renderer.onSurfaceCreated");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.m3eRenderer
 * JD-Core Version:    0.6.0
 */
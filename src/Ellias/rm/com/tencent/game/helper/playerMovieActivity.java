package com.tencent.game.helper;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class playerMovieActivity extends Activity
  implements SurfaceHolder.Callback, MediaPlayer.OnCompletionListener
{
  public static String S_LOGTAG = "m3e";
  private static SurfaceView mGLView;
  private static boolean m_playVideo = false;
  private static MediaPlayer m_player;
  private static SurfaceHolder m_surfaceHolder;
  public Handler mHandler;
  private RelativeLayout mRootLayout;

  private void PlayMovie()
  {
    if (!m_playVideo)
    {
      Log.e("m3e", "surfaceCreated");
      m_playVideo = true;
      m_player = new MediaPlayer();
      m_player.setAudioStreamType(3);
      m_player.setDisplay(m_surfaceHolder);
    }
    try
    {
      AssetFileDescriptor localAssetFileDescriptor = getAssets().openFd("ui/tg_logo.mp4");
      m_player.setDataSource(localAssetFileDescriptor.getFileDescriptor(), localAssetFileDescriptor.getStartOffset(), localAssetFileDescriptor.getLength());
      localAssetFileDescriptor.close();
      m_player.prepare();
      m_player.start();
      m_player.setOnCompletionListener(this);
      Log.d(S_LOGTAG, "playerMovieActivity::surfaceCreated()1");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      m_player.setDisplay(null);
      m_player.stop();
      m_player.reset();
      m_player.release();
      setResult(1, new Intent(this, m3eActivity.class));
      finish();
    }
  }

  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    Log.d(S_LOGTAG, "playerMovieActivity::onCompletion()");
    m_player.setDisplay(null);
    m_player.stop();
    m_player.reset();
    m_player.release();
    setResult(1, new Intent(this, m3eActivity.class));
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Log.d(S_LOGTAG, "playerMovieActivity::onCreate()");
    setContentView(2130903054);
    this.mRootLayout = ((RelativeLayout)findViewById(2131165264));
    mGLView = (SurfaceView)findViewById(2131165265);
    m_surfaceHolder = mGLView.getHolder();
    m_surfaceHolder.addCallback(this);
    m_surfaceHolder.setType(3);
    (int)(0.05D * m3eActivity.s_screenH);
    int i = (int)(0.1D * m3eActivity.s_screenH);
    int j = (int)(0.01D * m3eActivity.s_screenH);
    ViewGroup.MarginLayoutParams localMarginLayoutParams = new ViewGroup.MarginLayoutParams(new RelativeLayout.LayoutParams(m3eActivity.s_screenW, j + (i + m3eActivity.s_screenH)));
    localMarginLayoutParams.setMargins(0, -i, 0, -j);
    mGLView.setLayoutParams(new RelativeLayout.LayoutParams(localMarginLayoutParams));
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
      return true;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onLowMemory()
  {
    Log.d(S_LOGTAG, "***~~~!!!onLowMemory()!!!~~~***");
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    PlayMovie();
    Log.d(S_LOGTAG, "playerMovieActivity::surfaceCreated()");
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    Log.d(S_LOGTAG, "playerMovieActivity::surfaceDestroyed()");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.playerMovieActivity
 * JD-Core Version:    0.6.0
 */
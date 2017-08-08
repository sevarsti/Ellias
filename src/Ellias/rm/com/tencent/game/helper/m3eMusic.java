package com.tencent.game.helper;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.util.Log;

public class m3eMusic
{
  private static m3eMusic instance = new m3eMusic();
  public MediaPlayer mBackgroundMediaPlayer;
  private Context mContext;
  private String mCurrentPath;
  private float mLeftVolume;
  private boolean mPaused;
  private float mRightVolume;

  private m3eMusic()
  {
    initData();
  }

  public static m3eMusic getInstance()
  {
    return instance;
  }

  private void initData()
  {
    this.mLeftVolume = 0.5F;
    this.mRightVolume = 0.5F;
    this.mBackgroundMediaPlayer = null;
    this.mPaused = false;
    this.mCurrentPath = null;
  }

  public MediaPlayer createMediaplayerFromAssets(String paramString)
  {
    MediaPlayer localMediaPlayer = new MediaPlayer();
    try
    {
      Log.e("m3eMusic", "createMediaplayerFromAssets: %s" + paramString);
      if (paramString.startsWith("/"))
        localMediaPlayer.setDataSource(paramString);
      while (true)
      {
        localMediaPlayer.prepare();
        localMediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
        return localMediaPlayer;
        AssetFileDescriptor localAssetFileDescriptor = this.mContext.getAssets().openFd(paramString);
        localMediaPlayer.setDataSource(localAssetFileDescriptor.getFileDescriptor(), localAssetFileDescriptor.getStartOffset(), localAssetFileDescriptor.getLength());
      }
    }
    catch (Exception localException)
    {
      Log.e("m3eMusic", "error: " + localException.getMessage(), localException);
    }
    return null;
  }

  public void end()
  {
    if (this.mBackgroundMediaPlayer != null)
      this.mBackgroundMediaPlayer.release();
    initData();
  }

  public float getBackgroundVolume()
  {
    if (this.mBackgroundMediaPlayer != null)
      return (this.mLeftVolume + this.mRightVolume) / 2.0F;
    return 0.0F;
  }

  public boolean isBackgroundMusicPlaying()
  {
    if (this.mBackgroundMediaPlayer == null)
      return false;
    try
    {
      boolean bool = this.mBackgroundMediaPlayer.isPlaying();
      return bool;
    }
    catch (Exception localException)
    {
      Log.e("m3eMusic", "isBackgroundMusicPlaying.crash" + localException.getMessage());
    }
    return false;
  }

  public void pauseBackgroundMusic()
  {
    Log.e("pauseBackgroundMusic ", "");
    if (isBackgroundMusicPlaying())
    {
      this.mBackgroundMediaPlayer.pause();
      this.mPaused = true;
    }
  }

  public void playBackgroundMusic(String paramString, boolean paramBoolean)
  {
    Log.e("playBackgroundMusic ", paramString);
    if (this.mCurrentPath == null)
    {
      this.mBackgroundMediaPlayer = createMediaplayerFromAssets(paramString);
      this.mCurrentPath = paramString;
      Log.e("playBackgroundMusic ", this.mCurrentPath);
    }
    while (this.mBackgroundMediaPlayer == null)
    {
      Log.e("m3eMusic", "playBackgroundMusic: background media player is null");
      return;
      if (!this.mCurrentPath.equals(paramString))
      {
        Log.e("playBackgroundMusic ", this.mCurrentPath);
        if (this.mBackgroundMediaPlayer != null)
        {
          this.mBackgroundMediaPlayer.release();
          this.mBackgroundMediaPlayer = null;
        }
        this.mBackgroundMediaPlayer = createMediaplayerFromAssets(paramString);
        this.mCurrentPath = paramString;
        continue;
      }
      if (isBackgroundMusicPlaying())
        return;
    }
    this.mBackgroundMediaPlayer.stop();
    this.mBackgroundMediaPlayer.setLooping(paramBoolean);
    try
    {
      this.mBackgroundMediaPlayer.prepare();
      this.mBackgroundMediaPlayer.seekTo(0);
      this.mBackgroundMediaPlayer.start();
      this.mPaused = false;
      return;
    }
    catch (Exception localException)
    {
      Log.e("m3eMusic", "playBackgroundMusic: error state");
    }
  }

  public void preloadBackgroundMusic(String paramString)
  {
    Log.e("preloadBackgroundMusic ", paramString);
    if ((this.mCurrentPath == null) || (!this.mCurrentPath.equals(paramString)))
    {
      if (this.mBackgroundMediaPlayer != null)
      {
        this.mBackgroundMediaPlayer.release();
        this.mBackgroundMediaPlayer = null;
      }
      this.mBackgroundMediaPlayer = createMediaplayerFromAssets(paramString);
      this.mCurrentPath = paramString;
    }
  }

  public void resumeBackgroundMusic()
  {
    Log.e("pauseBackgroundMusic ", "");
    if ((this.mBackgroundMediaPlayer != null) && (this.mPaused))
    {
      this.mBackgroundMediaPlayer.start();
      this.mPaused = false;
    }
  }

  public void rewindBackgroundMusic()
  {
    if (this.mBackgroundMediaPlayer != null)
      this.mBackgroundMediaPlayer.stop();
    try
    {
      this.mBackgroundMediaPlayer.prepare();
      this.mBackgroundMediaPlayer.seekTo(0);
      this.mBackgroundMediaPlayer.start();
      this.mPaused = false;
      return;
    }
    catch (Exception localException)
    {
      Log.e("m3eMusic", "rewindBackgroundMusic: error state");
    }
  }

  public void setBackgroundVolume(float paramFloat)
  {
    float f1 = 1.0F;
    boolean bool = paramFloat < 0.0F;
    float f2 = 0.0F;
    if (bool);
    while (true)
    {
      if (f2 > f1);
      while (true)
      {
        this.mRightVolume = f1;
        this.mLeftVolume = f1;
        if (this.mBackgroundMediaPlayer != null)
          this.mBackgroundMediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
        return;
        f1 = f2;
      }
      f2 = paramFloat;
    }
  }

  public void setContext(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public void stopBackgroundMusic()
  {
    Log.e("stopBackgroundMusic ", "");
    if (this.mBackgroundMediaPlayer != null)
    {
      this.mBackgroundMediaPlayer.stop();
      this.mPaused = false;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.m3eMusic
 * JD-Core Version:    0.6.0
 */
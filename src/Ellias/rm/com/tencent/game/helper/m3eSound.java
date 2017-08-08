package com.tencent.game.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.util.Log;
import java.util.HashMap;

public class m3eSound
{
  private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 5;
  private static final int SOUND_PRIORITY = 1;
  private static final int SOUND_QUALITY = 5;
  private static final float SOUND_RATE = 1.0F;
  private static final String TAG = "Cocos2dxSound";
  private static m3eSound instance = new m3eSound();
  private final int INVALID_SOUND_ID = -1;
  private final int INVALID_STREAM_ID = -1;
  private Context mContext;
  private float mLeftVolume;
  private HashMap<String, Integer> mPathSoundIDMap;
  private float mRightVolume;
  private HashMap<Integer, Integer> mSoundIdStreamIdMap;
  private SoundPool mSoundPool;

  private m3eSound()
  {
    initData();
  }

  public static m3eSound getInstance()
  {
    return instance;
  }

  private void initData()
  {
    this.mSoundIdStreamIdMap = new HashMap();
    this.mSoundPool = new SoundPool(5, 3, 5);
    this.mPathSoundIDMap = new HashMap();
    this.mLeftVolume = 0.5F;
    this.mRightVolume = 0.5F;
  }

  public int createSoundIdFromAsset(String paramString)
  {
    try
    {
      int i = this.mSoundPool.load(this.mContext.getAssets().openFd(paramString), 0);
      return i;
    }
    catch (Exception localException)
    {
      Log.e("Cocos2dxSound", "error: " + localException.getMessage(), localException);
    }
    return -1;
  }

  public void end()
  {
    this.mSoundPool.release();
    this.mPathSoundIDMap.clear();
    this.mSoundIdStreamIdMap.clear();
    initData();
  }

  public float getEffectsVolume()
  {
    return (this.mLeftVolume + this.mRightVolume) / 2.0F;
  }

  public int playEffect(String paramString, boolean paramBoolean)
  {
    int i = -1;
    Integer localInteger = (Integer)this.mPathSoundIDMap.get(paramString);
    Log.d("m3eSound", "playEffect");
    int j;
    if (localInteger != null)
    {
      this.mSoundPool.stop(localInteger.intValue());
      SoundPool localSoundPool = this.mSoundPool;
      int k = localInteger.intValue();
      float f1 = this.mLeftVolume;
      float f2 = this.mRightVolume;
      if (paramBoolean)
      {
        j = localSoundPool.play(k, f1, f2, 1, i, 1.0F);
        this.mSoundIdStreamIdMap.put(localInteger, Integer.valueOf(j));
      }
    }
    while (true)
    {
      i = j;
      do
      {
        return i;
        i = 0;
        break;
      }
      while (Integer.valueOf(preloadEffect(paramString)).intValue() == i);
      j = playEffect(paramString, paramBoolean);
    }
  }

  public int preloadEffect(String paramString)
  {
    Log.d("m3eSound", "preloadEffect");
    if (this.mPathSoundIDMap.get(paramString) != null)
      return ((Integer)this.mPathSoundIDMap.get(paramString)).intValue();
    int i = createSoundIdFromAsset(paramString);
    if (i != -1)
    {
      this.mSoundIdStreamIdMap.put(Integer.valueOf(i), Integer.valueOf(-1));
      this.mPathSoundIDMap.put(paramString, Integer.valueOf(i));
      return i;
    }
    Log.d("m3eSound", "preloadEffect, soundid = INVALID_SOUND_ID");
    return i;
  }

  public void setContext(Context paramContext)
  {
    Log.d("m3eSound", "setContext");
    this.mContext = paramContext;
  }

  public void setEffectsVolume(float paramFloat)
  {
    this.mRightVolume = paramFloat;
    this.mLeftVolume = paramFloat;
  }

  public void stopEffect(int paramInt)
  {
    this.mSoundPool.stop(paramInt);
  }

  public void unloadEffect(String paramString)
  {
    Integer localInteger = (Integer)this.mPathSoundIDMap.remove(paramString);
    if (localInteger != null)
    {
      this.mSoundPool.unload(localInteger.intValue());
      this.mSoundIdStreamIdMap.remove(localInteger);
      Log.e("jackson", "jackson remove effect   " + localInteger);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.m3eSound
 * JD-Core Version:    0.6.0
 */
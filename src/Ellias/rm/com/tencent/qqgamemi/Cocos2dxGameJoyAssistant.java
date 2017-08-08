package com.tencent.qqgamemi;

import android.content.Context;

public class Cocos2dxGameJoyAssistant
{
  public static native String GetCocos2dxVersion();

  public static native void StartRecord(int paramInt1, int paramInt2, float paramFloat);

  public static native void StopRecord();

  private static String getGameEngineVersion()
  {
    try
    {
      String str = "cocos2dx_" + GetCocos2dxVersion();
      return str;
    }
    catch (Exception localException)
    {
    }
    return "GetGameEngineFailed";
  }

  public static void hideAssistant(Context paramContext)
  {
    QmiSdkApi.hideQMi(paramContext);
  }

  public static void queryGameEngineType()
  {
    QmiSdkApi.setGameEngineType(getGameEngineVersion());
  }

  public static void scrollAssitantToSide(Context paramContext)
  {
    QmiSdkApi.scollToSide(paramContext);
  }

  public static void showAssistant(Context paramContext)
  {
    QmiSdkApi.showQMi(paramContext, getGameEngineVersion());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.Cocos2dxGameJoyAssistant
 * JD-Core Version:    0.6.0
 */
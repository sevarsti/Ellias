package com.tencent.msdk.notice;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import com.tencent.msdk.tools.Logger;

public enum eMSDK_SCREENDIR
{
  int value = 0;

  static
  {
    eMSDK_SCREENDIR_PORTRAIT = new eMSDK_SCREENDIR("eMSDK_SCREENDIR_PORTRAIT", 1, 1);
    eMSDK_SCREENDIR_LANDSCAPE = new eMSDK_SCREENDIR("eMSDK_SCREENDIR_LANDSCAPE", 2, 2);
    eMSDK_SCREENDIR[] arrayOfeMSDK_SCREENDIR = new eMSDK_SCREENDIR[3];
    arrayOfeMSDK_SCREENDIR[0] = eMSDK_SCREENDIR_SENSOR;
    arrayOfeMSDK_SCREENDIR[1] = eMSDK_SCREENDIR_PORTRAIT;
    arrayOfeMSDK_SCREENDIR[2] = eMSDK_SCREENDIR_LANDSCAPE;
    $VALUES = arrayOfeMSDK_SCREENDIR;
  }

  private eMSDK_SCREENDIR(int paramInt)
  {
    this.value = paramInt;
  }

  public static eMSDK_SCREENDIR getEnum(int paramInt)
  {
    switch (paramInt)
    {
    default:
      Logger.w("bad screen dir :" + paramInt);
      return eMSDK_SCREENDIR_SENSOR;
    case 0:
      return eMSDK_SCREENDIR_SENSOR;
    case 1:
      return eMSDK_SCREENDIR_PORTRAIT;
    case 2:
    }
    return eMSDK_SCREENDIR_LANDSCAPE;
  }

  public static eMSDK_SCREENDIR getEnum(ActivityInfo paramActivityInfo)
  {
    if (paramActivityInfo.screenOrientation == 0)
      return eMSDK_SCREENDIR_LANDSCAPE;
    if (paramActivityInfo.screenOrientation == 1)
      return eMSDK_SCREENDIR_PORTRAIT;
    return eMSDK_SCREENDIR_SENSOR;
  }

  public static eMSDK_SCREENDIR getEnum(Configuration paramConfiguration)
  {
    if (paramConfiguration.orientation == 2)
      return eMSDK_SCREENDIR_LANDSCAPE;
    if (paramConfiguration.orientation == 1)
      return eMSDK_SCREENDIR_PORTRAIT;
    return eMSDK_SCREENDIR_SENSOR;
  }

  public int val()
  {
    return this.value;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.eMSDK_SCREENDIR
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.utils;

import android.os.Build.VERSION;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public class PlatformUtil
{
  @PluginApi(a=6)
  public static int version()
  {
    return Build.VERSION.SDK_INT;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.PlatformUtil
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.plugin;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class al
  implements Parcelable.Creator
{
  public PluginPlatformConfig a(Parcel paramParcel)
  {
    PluginPlatformConfig localPluginPlatformConfig = new PluginPlatformConfig();
    PluginPlatformConfig.a(localPluginPlatformConfig, paramParcel.readString());
    PluginPlatformConfig.a(localPluginPlatformConfig, paramParcel.createIntArray());
    return localPluginPlatformConfig;
  }

  public PluginPlatformConfig[] a(int paramInt)
  {
    return new PluginPlatformConfig[paramInt];
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.al
 * JD-Core Version:    0.6.0
 */
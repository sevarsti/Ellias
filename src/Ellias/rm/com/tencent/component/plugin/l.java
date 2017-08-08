package com.tencent.component.plugin;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class l
  implements Parcelable.Creator
{
  public PluginInfo.ExtraInfo a(Parcel paramParcel)
  {
    return new PluginInfo.ExtraInfo(paramParcel, null);
  }

  public PluginInfo.ExtraInfo[] a(int paramInt)
  {
    return new PluginInfo.ExtraInfo[paramInt];
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.l
 * JD-Core Version:    0.6.0
 */
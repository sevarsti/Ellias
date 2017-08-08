package com.tencent.component.plugin;

import android.content.pm.Signature;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

final class k
  implements Parcelable.Creator
{
  public PluginInfo a(Parcel paramParcel)
  {
    int i = 1;
    PluginInfo localPluginInfo = new PluginInfo();
    localPluginInfo.pluginId = paramParcel.readString();
    localPluginInfo.installPath = paramParcel.readString();
    localPluginInfo.a = paramParcel.readString();
    localPluginInfo.nativeLibraryDir = paramParcel.readString();
    localPluginInfo.dexOptimizeDir = paramParcel.readString();
    localPluginInfo.d = paramParcel.createStringArray();
    localPluginInfo.minPlatformVersion = paramParcel.readInt();
    localPluginInfo.maxPlatformVersion = paramParcel.readInt();
    localPluginInfo.j = ((Uri)paramParcel.readParcelable(Uri.class.getClassLoader()));
    localPluginInfo.version = paramParcel.readInt();
    localPluginInfo.versionName = paramParcel.readString();
    localPluginInfo.e = paramParcel.readString();
    localPluginInfo.f = paramParcel.readInt();
    localPluginInfo.g = paramParcel.readInt();
    localPluginInfo.i = paramParcel.readString();
    int j;
    int k;
    if (paramParcel.readInt() == i)
    {
      j = i;
      localPluginInfo.l = j;
      localPluginInfo.h = ((Signature[])paramParcel.createTypedArray(Signature.CREATOR));
      localPluginInfo.k.a((PluginInfo.ExtraInfo)paramParcel.readParcelable(PluginInfo.ExtraInfo.class.getClassLoader()));
      localPluginInfo.minAndroidVersion = paramParcel.readInt();
      localPluginInfo.maxAndroidVersion = paramParcel.readInt();
      localPluginInfo.b = paramParcel.readString();
      localPluginInfo.c = paramParcel.readString();
      if (paramParcel.readInt() != i)
        break label258;
      k = i;
      label231: localPluginInfo.m = k;
      if (paramParcel.readInt() != i)
        break label264;
    }
    while (true)
    {
      localPluginInfo.n = i;
      return localPluginInfo;
      j = 0;
      break;
      label258: k = 0;
      break label231;
      label264: i = 0;
    }
  }

  public PluginInfo[] a(int paramInt)
  {
    return new PluginInfo[paramInt];
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.k
 * JD-Core Version:    0.6.0
 */
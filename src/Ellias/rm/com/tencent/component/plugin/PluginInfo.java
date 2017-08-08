package com.tencent.component.plugin;

import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.log.LogUtil;

@PluginApi(a=6)
public class PluginInfo
  implements Parcelable
{
  public static final Parcelable.Creator CREATOR = new k();
  public String a;
  public String b;
  public String c;
  public String[] d;

  @PluginApi(a=6)
  public String dexOptimizeDir;
  public String e;
  public int f;
  public int g;
  public Signature[] h;
  public String i;

  @PluginApi(a=6)
  public String installPath;
  public Uri j;
  public PluginInfo.ExtraInfo k = new PluginInfo.ExtraInfo();
  public boolean l;
  public boolean m = true;

  @PluginApi(a=6)
  public int maxAndroidVersion;

  @PluginApi(a=6)
  public int maxPlatformVersion;

  @PluginApi(a=6)
  public int minAndroidVersion;

  @PluginApi(a=6)
  public int minPlatformVersion;
  public boolean n = false;

  @PluginApi(a=6)
  public String nativeLibraryDir;

  @PluginApi(a=6)
  public String pluginId;

  @PluginApi(a=6)
  public int version;

  @PluginApi(a=6)
  public String versionName;

  public PluginInfo()
  {
  }

  public PluginInfo(PluginInfo paramPluginInfo)
  {
    this.installPath = paramPluginInfo.installPath;
    this.a = paramPluginInfo.a;
    this.b = paramPluginInfo.b;
    this.nativeLibraryDir = paramPluginInfo.nativeLibraryDir;
    this.dexOptimizeDir = paramPluginInfo.dexOptimizeDir;
    this.d = paramPluginInfo.d;
    this.minPlatformVersion = paramPluginInfo.minPlatformVersion;
    this.maxPlatformVersion = paramPluginInfo.maxPlatformVersion;
    this.minAndroidVersion = paramPluginInfo.minAndroidVersion;
    this.maxAndroidVersion = paramPluginInfo.maxAndroidVersion;
    this.pluginId = paramPluginInfo.pluginId;
    this.j = paramPluginInfo.j;
    this.version = paramPluginInfo.version;
    this.versionName = paramPluginInfo.versionName;
    this.e = paramPluginInfo.e;
    this.f = paramPluginInfo.f;
    this.g = paramPluginInfo.g;
    this.h = paramPluginInfo.h;
    this.i = paramPluginInfo.i;
    this.k = paramPluginInfo.k;
    this.l = paramPluginInfo.l;
    this.c = paramPluginInfo.c;
    this.m = paramPluginInfo.m;
    this.n = paramPluginInfo.n;
  }

  public Drawable a(PluginManager paramPluginManager)
  {
    Resources localResources = paramPluginManager.b(this);
    if (localResources != null)
      try
      {
        Drawable localDrawable = localResources.getDrawable(this.f);
        return localDrawable;
      }
      catch (Exception localException)
      {
        LogUtil.e("PluginInfo", localException.getMessage(), localException);
      }
    return null;
  }

  public boolean a()
  {
    return TextUtils.isEmpty(this.installPath);
  }

  public int describeContents()
  {
    return 0;
  }

  public String toString()
  {
    return "PluginInfo{" + this.pluginId + " " + this.version + " " + this.m + "}";
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i1 = 1;
    paramParcel.writeString(this.pluginId);
    paramParcel.writeString(this.installPath);
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.nativeLibraryDir);
    paramParcel.writeString(this.dexOptimizeDir);
    paramParcel.writeStringArray(this.d);
    paramParcel.writeInt(this.minPlatformVersion);
    paramParcel.writeInt(this.maxPlatformVersion);
    paramParcel.writeParcelable(this.j, paramInt);
    paramParcel.writeInt(this.version);
    paramParcel.writeString(this.versionName);
    paramParcel.writeString(this.e);
    paramParcel.writeInt(this.f);
    paramParcel.writeInt(this.g);
    paramParcel.writeString(this.i);
    int i2;
    int i3;
    if (this.l)
    {
      i2 = i1;
      paramParcel.writeInt(i2);
      paramParcel.writeTypedArray(this.h, paramInt);
      paramParcel.writeParcelable(this.k, paramInt);
      paramParcel.writeInt(this.minAndroidVersion);
      paramParcel.writeInt(this.maxAndroidVersion);
      paramParcel.writeString(this.b);
      paramParcel.writeString(this.c);
      if (!this.m)
        break label224;
      i3 = i1;
      label199: paramParcel.writeInt(i3);
      if (!this.n)
        break label230;
    }
    while (true)
    {
      paramParcel.writeInt(i1);
      return;
      i2 = 0;
      break;
      label224: i3 = 0;
      break label199;
      label230: i1 = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginInfo
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.plugin;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class PluginInfo$ExtraInfo
  implements Parcelable
{
  public static final Parcelable.Creator CREATOR = new l();
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  public int d;
  public boolean e;
  public Boolean f;
  public boolean g;

  public PluginInfo$ExtraInfo()
  {
  }

  private PluginInfo$ExtraInfo(Parcel paramParcel)
  {
    this.d = paramParcel.readInt();
    boolean bool2;
    int i;
    Boolean localBoolean;
    if (paramParcel.readInt() != 0)
    {
      bool2 = bool1;
      this.e = bool2;
      i = paramParcel.readInt();
      if (i != -1)
        break label67;
      localBoolean = null;
      this.f = localBoolean;
      if (paramParcel.readInt() == 0)
        break label91;
    }
    while (true)
    {
      this.g = bool1;
      return;
      bool2 = false;
      break;
      label67: if (i != 0);
      for (boolean bool3 = bool1; ; bool3 = false)
      {
        localBoolean = Boolean.valueOf(bool3);
        break;
      }
      label91: bool1 = false;
    }
  }

  public void a(ExtraInfo paramExtraInfo)
  {
    if (paramExtraInfo == null)
      return;
    this.d = paramExtraInfo.d;
    this.e = paramExtraInfo.e;
    this.f = paramExtraInfo.f;
    this.g = paramExtraInfo.g;
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    paramParcel.writeInt(this.d);
    int j;
    int k;
    if (this.e)
    {
      j = i;
      paramParcel.writeInt(j);
      if (this.f != null)
        break label61;
      k = -1;
      label36: paramParcel.writeInt(k);
      if (!this.g)
        break label83;
    }
    while (true)
    {
      paramParcel.writeInt(i);
      return;
      j = 0;
      break;
      label61: if (this.f.booleanValue())
      {
        k = i;
        break label36;
      }
      k = 0;
      break label36;
      label83: i = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginInfo.ExtraInfo
 * JD-Core Version:    0.6.0
 */
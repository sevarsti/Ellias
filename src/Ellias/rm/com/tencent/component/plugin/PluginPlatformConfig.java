package com.tencent.component.plugin;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PluginPlatformConfig
  implements Parcelable
{
  public static final Parcelable.Creator CREATOR;
  private static final int[] a = { 2071990634 };
  private String b;
  private int c = 150;
  private int[] d = a;

  static
  {
    CREATOR = new al();
  }

  public String a()
  {
    return this.b;
  }

  public void a(int paramInt)
  {
    this.c = paramInt;
  }

  public void a(String paramString)
  {
    this.b = paramString;
  }

  public void a(int[] paramArrayOfInt)
  {
    this.d = paramArrayOfInt;
  }

  public int[] b()
  {
    return this.d;
  }

  public int c()
  {
    return this.c;
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.b);
    paramParcel.writeIntArray(this.d);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginPlatformConfig
 * JD-Core Version:    0.6.0
 */
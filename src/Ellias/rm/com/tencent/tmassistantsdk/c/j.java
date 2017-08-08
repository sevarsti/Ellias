package com.tencent.tmassistantsdk.c;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class j
  implements Parcelable.Creator
{
  public i a(Parcel paramParcel)
  {
    return new i(paramParcel.readString(), paramParcel.readString(), paramParcel.readInt(), paramParcel.readLong(), paramParcel.readLong(), paramParcel.readString());
  }

  public i[] a(int paramInt)
  {
    return new i[paramInt];
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.c.j
 * JD-Core Version:    0.6.0
 */
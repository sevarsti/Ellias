package com.tencent.android.tpush.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class c
  implements Parcelable.Creator
{
  public StorageEntity a(Parcel paramParcel)
  {
    return new StorageEntity(paramParcel);
  }

  public StorageEntity[] a(int paramInt)
  {
    return new StorageEntity[paramInt];
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.data.c
 * JD-Core Version:    0.6.0
 */
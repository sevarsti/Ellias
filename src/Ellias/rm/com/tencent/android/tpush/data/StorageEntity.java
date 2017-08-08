package com.tencent.android.tpush.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StorageEntity
  implements Parcelable
{
  public static final Parcelable.Creator CREATOR = new c();
  public String a = "";
  public int b = -1;
  public boolean c;
  public String d;
  public int e;
  public float f;
  public long g;

  public StorageEntity()
  {
  }

  public StorageEntity(Parcel paramParcel)
  {
    a(paramParcel);
  }

  public StorageEntity(String paramString, boolean paramBoolean)
  {
    this.a = paramString;
    this.b = 1;
    this.c = paramBoolean;
  }

  private void a(Parcel paramParcel)
  {
    int i = 1;
    this.a = paramParcel.readString();
    this.b = paramParcel.readInt();
    if (paramParcel.readByte() == i);
    while (true)
    {
      this.c = i;
      this.d = paramParcel.readString();
      this.e = paramParcel.readInt();
      this.f = paramParcel.readFloat();
      this.g = paramParcel.readLong();
      return;
      i = 0;
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public String toString()
  {
    return "StorageEntity[key:" + this.a + ",type:" + this.b + ",strValue:" + this.d + ",boolValue:" + this.c + ",intValue" + this.e + ",floatValue:" + this.f + ",longValue:" + this.g + "]";
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeInt(this.b);
    if (this.c);
    for (byte b1 = 1; ; b1 = 0)
    {
      paramParcel.writeByte(b1);
      paramParcel.writeString(this.d);
      paramParcel.writeInt(this.e);
      paramParcel.writeFloat(this.f);
      paramParcel.writeLong(this.g);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.data.StorageEntity
 * JD-Core Version:    0.6.0
 */
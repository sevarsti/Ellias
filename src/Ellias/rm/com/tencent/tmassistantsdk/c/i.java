package com.tencent.tmassistantsdk.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class i
  implements Parcelable
{
  public static final Parcelable.Creator g = new j();
  public String a;
  public String b;
  public int c;
  public long d;
  public long e;
  public String f;

  public i(String paramString1, String paramString2, int paramInt, long paramLong1, long paramLong2, String paramString3)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramInt;
    this.d = paramLong1;
    this.e = paramLong2;
    this.f = paramString3;
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (this.a != null)
    {
      paramParcel.writeString(this.a);
      if (this.b == null)
        break label72;
      paramParcel.writeString(this.b);
    }
    while (true)
    {
      paramParcel.writeInt(this.c);
      paramParcel.writeLong(this.d);
      paramParcel.writeLong(this.e);
      paramParcel.writeString(this.f);
      return;
      paramParcel.writeString("");
      break;
      label72: paramParcel.writeString("");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.c.i
 * JD-Core Version:    0.6.0
 */
package com.tencent.open.yyb;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ShareModel
  implements Parcelable
{
  public static final Parcelable.Creator<ShareModel> CREATOR = new a();
  public String a;
  public String b;
  public String c;
  public String d;

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeString(this.d);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.yyb.ShareModel
 * JD-Core Version:    0.6.0
 */
package com.tencent.open.yyb;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a
  implements Parcelable.Creator<ShareModel>
{
  public ShareModel a(Parcel paramParcel)
  {
    ShareModel localShareModel = new ShareModel();
    localShareModel.a = paramParcel.readString();
    localShareModel.b = paramParcel.readString();
    localShareModel.c = paramParcel.readString();
    localShareModel.d = paramParcel.readString();
    return localShareModel;
  }

  public ShareModel[] a(int paramInt)
  {
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.yyb.a
 * JD-Core Version:    0.6.0
 */
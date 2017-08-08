package com.tencent.qqgamemi.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b
  implements Parcelable.Creator
{
  public Point a(Parcel paramParcel)
  {
    Point localPoint = new Point();
    localPoint.a(paramParcel);
    return localPoint;
  }

  public Point[] a(int paramInt)
  {
    return new Point[paramInt];
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.data.b
 * JD-Core Version:    0.6.0
 */
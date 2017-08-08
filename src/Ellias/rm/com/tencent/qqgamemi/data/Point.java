package com.tencent.qqgamemi.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Point
  implements Parcelable
{
  public static final Parcelable.Creator CREATOR = new b();
  public int a;
  public int b;

  public Point()
  {
  }

  public Point(int paramInt1, int paramInt2)
  {
    this.a = paramInt1;
    this.b = paramInt2;
  }

  public Point(Point paramPoint)
  {
    this.a = paramPoint.a;
    this.b = paramPoint.b;
  }

  public final void a()
  {
    this.a = (-this.a);
    this.b = (-this.b);
  }

  public void a(int paramInt1, int paramInt2)
  {
    this.a = paramInt1;
    this.b = paramInt2;
  }

  public void a(Parcel paramParcel)
  {
    this.a = paramParcel.readInt();
    this.b = paramParcel.readInt();
  }

  public final void b(int paramInt1, int paramInt2)
  {
    this.a = (paramInt1 + this.a);
    this.b = (paramInt2 + this.b);
  }

  public final boolean c(int paramInt1, int paramInt2)
  {
    return (this.a == paramInt1) && (this.b == paramInt2);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = paramObject instanceof Point;
    int i = 0;
    if (bool)
    {
      Point localPoint = (Point)paramObject;
      int j = this.a;
      int k = localPoint.a;
      i = 0;
      if (j == k)
      {
        int m = this.b;
        int n = localPoint.b;
        i = 0;
        if (m == n)
          i = 1;
      }
    }
    return i;
  }

  public int hashCode()
  {
    return 32713 * this.a + this.b;
  }

  public String toString()
  {
    return "Point(" + this.a + ", " + this.b + ")";
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.a);
    paramParcel.writeInt(this.b);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.data.Point
 * JD-Core Version:    0.6.0
 */
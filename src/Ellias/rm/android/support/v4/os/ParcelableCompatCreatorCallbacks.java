package android.support.v4.os;

import android.os.Parcel;

public abstract interface ParcelableCompatCreatorCallbacks<T>
{
  public abstract T createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader);

  public abstract T[] newArray(int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     android.support.v4.os.ParcelableCompatCreatorCallbacks
 * JD-Core Version:    0.6.0
 */
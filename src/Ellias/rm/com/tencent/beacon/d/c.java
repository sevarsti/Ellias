package com.tencent.beacon.d;

import android.util.SparseArray;

public final class c<E>
{
  public static SparseArray<E> a(SparseArray<E> paramSparseArray)
  {
    if (paramSparseArray != null)
    {
      SparseArray localSparseArray = new SparseArray(paramSparseArray.size());
      for (int i = 0; i < paramSparseArray.size(); i++)
      {
        int j = paramSparseArray.keyAt(i);
        localSparseArray.append(j, paramSparseArray.get(j));
      }
      return localSparseArray;
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.d.c
 * JD-Core Version:    0.6.0
 */
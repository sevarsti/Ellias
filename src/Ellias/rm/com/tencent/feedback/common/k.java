package com.tencent.feedback.common;

import android.util.SparseArray;

public final class k<E>
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
 * Qualified Name:     com.tencent.feedback.common.k
 * JD-Core Version:    0.6.0
 */
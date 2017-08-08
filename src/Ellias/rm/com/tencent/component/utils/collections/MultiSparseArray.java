package com.tencent.component.utils.collections;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

public class MultiSparseArray
{
  private SparseArray a;

  public MultiSparseArray()
  {
    this.a = new SparseArray();
  }

  public MultiSparseArray(int paramInt)
  {
    this.a = new SparseArray(paramInt);
  }

  public int a()
  {
    return this.a.size();
  }

  public List a(int paramInt)
  {
    return (List)this.a.get(paramInt);
  }

  public void a(int paramInt, Object paramObject)
  {
    if (paramObject == null);
    Object localObject;
    do
    {
      return;
      localObject = a(paramInt);
      if (localObject != null)
        continue;
      localObject = new ArrayList();
      this.a.put(paramInt, (ArrayList)localObject);
    }
    while (((List)localObject).contains(paramObject));
    ((List)localObject).add(paramObject);
  }

  public int b(int paramInt)
  {
    return this.a.keyAt(paramInt);
  }

  public void b()
  {
    this.a.clear();
  }

  public void b(int paramInt, Object paramObject)
  {
    List localList = a(paramInt);
    if (localList != null)
      localList.remove(paramObject);
  }

  public List c(int paramInt)
  {
    return (List)this.a.valueAt(paramInt);
  }

  public void d(int paramInt)
  {
    this.a.remove(paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.MultiSparseArray
 * JD-Core Version:    0.6.0
 */
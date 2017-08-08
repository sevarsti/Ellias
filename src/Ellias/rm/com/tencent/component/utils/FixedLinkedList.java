package com.tencent.component.utils;

import java.util.LinkedList;

public class FixedLinkedList extends LinkedList
{
  private final int mCapacity;
  private final boolean mTrimHead;

  public FixedLinkedList(int paramInt)
  {
    this(paramInt, true);
  }

  public FixedLinkedList(int paramInt, boolean paramBoolean)
  {
    this.mCapacity = paramInt;
    this.mTrimHead = paramBoolean;
  }

  private void a()
  {
    while ((this.mCapacity > 0) && (size() > this.mCapacity))
    {
      if (this.mTrimHead)
      {
        removeFirst();
        continue;
      }
      removeLast();
    }
  }

  public void add(int paramInt, Object paramObject)
  {
    if (paramObject == null)
      return;
    super.add(paramInt, paramObject);
    a();
  }

  public boolean add(Object paramObject)
  {
    if (paramObject == null)
      return false;
    boolean bool = super.add(paramObject);
    a();
    return bool;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.FixedLinkedList
 * JD-Core Version:    0.6.0
 */
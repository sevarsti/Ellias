package com.tencent.component.utils.collections;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WeakArrayList extends AbstractList
{
  private static final Object a = new Object();
  private final transient ReferenceQueue b = new ReferenceQueue();
  private Object[] c;
  private int d;
  private boolean e = false;
  private List f = null;

  public WeakArrayList()
  {
    this(10);
  }

  public WeakArrayList(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Illegal Capacity: " + paramInt);
    this.c = new Object[paramInt];
    this.d = 0;
  }

  public WeakArrayList(Collection paramCollection)
  {
    this.c = new Object[paramCollection.size()];
    this.d = this.c.length;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      this.c[i] = c(localObject);
      i++;
    }
  }

  private static Object a(Object paramObject)
  {
    if (paramObject == null)
      paramObject = a;
    return paramObject;
  }

  private static Object b(Object paramObject)
  {
    if (paramObject == a)
      paramObject = null;
    return paramObject;
  }

  private Reference c(Object paramObject)
  {
    return new WeakReference(a(paramObject), this.b);
  }

  public void a()
  {
    this.modCount = (1 + this.modCount);
    int i = this.c.length;
    if (this.d < i)
      this.c = Arrays.copyOf(this.c, this.d);
  }

  public void a(int paramInt)
  {
    this.modCount = (1 + this.modCount);
    int i = this.c.length;
    Object[] arrayOfObject;
    int j;
    if (paramInt > i)
    {
      arrayOfObject = this.c;
      j = 1 + i * 3 / 2;
      if (j >= paramInt)
        break label51;
    }
    while (true)
    {
      this.c = Arrays.copyOf(arrayOfObject, paramInt);
      return;
      label51: paramInt = j;
    }
  }

  protected void a(int paramInt, boolean paramBoolean)
  {
    int i = b();
    if (paramInt < 0)
      throw new IndexOutOfBoundsException("invalid negative value: " + Integer.toString(paramInt));
    if ((paramBoolean) && (paramInt > i))
      throw new IndexOutOfBoundsException("index>" + i + ": " + Integer.toString(paramInt));
    if ((!paramBoolean) && (paramInt >= i))
      throw new IndexOutOfBoundsException("index>=" + i + ": " + Integer.toString(paramInt));
  }

  public void a(ReferenceListener paramReferenceListener)
  {
    if (this.f == null)
      this.f = new LinkedList();
    synchronized (this.f)
    {
      ???.add(paramReferenceListener);
      return;
    }
  }

  public void add(int paramInt, Object paramObject)
  {
    a(paramInt, true);
    a(1 + this.d);
    System.arraycopy(this.c, paramInt, this.c, paramInt + 1, this.d - paramInt);
    this.c[paramInt] = c(paramObject);
    this.d = (1 + this.d);
    this.modCount = (1 + this.modCount);
  }

  public int b()
  {
    while (this.b.poll() != null)
      this.e = true;
    int i;
    if (this.e)
    {
      int k = 0;
      i = 0;
      if (k < this.d)
      {
        Reference localReference = (Reference)this.c[k];
        if ((localReference == null) || (localReference.isEnqueued()) || (localReference.get() == null))
        {
          if (localReference != null)
            localReference.clear();
          this.c[k] = null;
        }
        while (true)
        {
          k++;
          break;
          if (k != i)
          {
            this.c[i] = this.c[k];
            this.c[k] = null;
          }
          i++;
        }
      }
      this.e = false;
    }
    while (this.b.poll() != null)
    {
      this.e = true;
      continue;
      i = this.d;
    }
    int j = this.d;
    this.d = i;
    if (i < j)
      b(j - i);
    return this.d;
  }

  protected void b(int paramInt)
  {
    List localList = this.f;
    if ((localList != null) && (!localList.isEmpty()))
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
        ((ReferenceListener)localIterator.next()).a(paramInt);
    }
  }

  public void b(ReferenceListener paramReferenceListener)
  {
    List localList = this.f;
    if (localList != null)
    {
      monitorenter;
      try
      {
        localList.remove(paramReferenceListener);
        if (localList.isEmpty())
          this.f = null;
        return;
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public Object get(int paramInt)
  {
    Object localObject;
    do
    {
      a(paramInt, false);
      localObject = ((Reference)this.c[paramInt]).get();
    }
    while (localObject == null);
    return b(localObject);
  }

  public Object remove(int paramInt)
  {
    Reference localReference;
    Object localObject;
    do
    {
      a(paramInt, false);
      localReference = (Reference)this.c[paramInt];
      localObject = localReference.get();
    }
    while (localObject == null);
    localReference.clear();
    System.arraycopy(this.c, paramInt + 1, this.c, paramInt, -1 + (this.d - paramInt));
    this.c[(-1 + this.d)] = null;
    this.d = (-1 + this.d);
    this.modCount = (1 + this.modCount);
    return b(localObject);
  }

  public Object set(int paramInt, Object paramObject)
  {
    Reference localReference;
    Object localObject;
    do
    {
      a(paramInt, false);
      localReference = (Reference)this.c[paramInt];
      localObject = localReference.get();
    }
    while (localObject == null);
    localReference.clear();
    this.c[paramInt] = c(paramObject);
    this.modCount = (1 + this.modCount);
    return b(localObject);
  }

  public int size()
  {
    return b();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < this.d)
    {
      Reference localReference = (Reference)this.c[i];
      Object localObject;
      if (this.c[i] == null)
      {
        localObject = null;
        label40: localStringBuilder.append('{');
        if (localObject != null)
          break label84;
      }
      label84: for (String str = null; ; str = localObject.toString())
      {
        localStringBuilder.append(str);
        localStringBuilder.append('}');
        i++;
        break;
        localObject = localReference.get();
        break label40;
      }
    }
    return localStringBuilder.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.WeakArrayList
 * JD-Core Version:    0.6.0
 */
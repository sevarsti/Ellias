package com.tencent.component.event;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.collections.MultiSparseArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@PluginApi(a=6)
public class EventCenter
{
  private static final boolean a;
  private static final EventCenter b = new EventCenter();
  private ReadWriteLock c = new ReentrantReadWriteLock();
  private HashMap d = new HashMap();
  private List e = new CopyOnWriteArrayList();
  private Handler f = new Handler(Looper.getMainLooper());

  private ArrayList a()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.e.iterator();
    while (localIterator.hasNext())
    {
      EventInterceptor localEventInterceptor = (EventInterceptor)localIterator.next();
      if (localEventInterceptor == null)
        continue;
      localArrayList.add(localEventInterceptor);
    }
    return localArrayList;
  }

  private Collection a(Event paramEvent)
  {
    EventSource localEventSource = paramEvent.source;
    MultiSparseArray localMultiSparseArray = (MultiSparseArray)this.d.get(localEventSource);
    if (localMultiSparseArray != null)
    {
      a(localMultiSparseArray, paramEvent.what);
      List localList = localMultiSparseArray.a(paramEvent.what);
      if (localList != null)
        return new ArrayList(localList);
    }
    return null;
  }

  private void a(b paramb, Event paramEvent)
  {
    if (paramb != null)
    {
      Object localObject = paramb.b();
      if ((localObject == null) || (localObject == paramEvent.source.sender))
      {
        if (!paramb.d)
          break label65;
        if (Looper.myLooper() != Looper.getMainLooper())
          break label46;
        paramb.a(paramEvent);
      }
    }
    return;
    label46: this.f.post(new a(this, paramb, paramEvent));
    return;
    label65: paramb.a(paramEvent);
  }

  private void a(MultiSparseArray paramMultiSparseArray, int paramInt)
  {
    if ((paramMultiSparseArray != null) && (paramMultiSparseArray.a() > 0))
    {
      List localList = paramMultiSparseArray.a(paramInt);
      if ((localList != null) && (localList.size() > 0))
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          b localb = (b)localIterator.next();
          if ((localb == null) || (localb.a() != null))
            continue;
          localIterator.remove();
        }
      }
    }
  }

  private void a(Object paramObject, EventSource paramEventSource)
  {
    MultiSparseArray localMultiSparseArray = (MultiSparseArray)this.d.get(paramEventSource);
    if (localMultiSparseArray != null)
    {
      int i = localMultiSparseArray.a();
      for (int j = 0; j < i; j++)
        a(localMultiSparseArray.a(localMultiSparseArray.b(j)), paramObject);
    }
  }

  private void a(Object paramObject, EventSource paramEventSource, int paramInt)
  {
    MultiSparseArray localMultiSparseArray = (MultiSparseArray)this.d.get(paramEventSource);
    if (localMultiSparseArray != null)
      a(localMultiSparseArray.a(paramInt), paramObject);
  }

  private void a(Collection paramCollection, Object paramObject)
  {
    if (paramCollection != null)
    {
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        b localb = (b)localIterator.next();
        if (localb == null)
          continue;
        Object localObject = localb.a();
        if ((localObject == null) || (!localObject.equals(paramObject)))
          continue;
        localIterator.remove();
      }
    }
  }

  @PluginApi(a=6)
  public static EventCenter getInstance()
  {
    return b;
  }

  public void a(Observer paramObserver, EventSource paramEventSource, int[] paramArrayOfInt)
  {
    addObserver(paramObserver, null, paramEventSource, false, paramArrayOfInt);
  }

  @PluginApi(a=6)
  public void addEventInterceptor(EventInterceptor paramEventInterceptor)
  {
    if (paramEventInterceptor == null)
      throw new NullPointerException("interceptor cannot be null");
    Lock localLock = this.c.writeLock();
    try
    {
      localLock.lock();
      this.e.add(paramEventInterceptor);
      return;
    }
    finally
    {
      localLock.unlock();
    }
    throw localObject;
  }

  @PluginApi(a=6)
  public void addObserver(Observer paramObserver, String paramString, EventSource paramEventSource, boolean paramBoolean, int[] paramArrayOfInt)
  {
    if (paramObserver == null)
      throw new NullPointerException("observingObject can't be null!");
    if ((paramEventSource == null) || (TextUtils.isEmpty(paramEventSource.name)))
      throw new NullPointerException("you must specified eventSource!");
    if (paramArrayOfInt == null)
      return;
    if (paramString == null)
      paramString = "onNotify";
    Lock localLock = this.c.writeLock();
    while (true)
    {
      MultiSparseArray localMultiSparseArray1;
      try
      {
        localLock.lock();
        b localb = new b(paramObserver, paramEventSource.sender, paramString, paramBoolean);
        localMultiSparseArray1 = (MultiSparseArray)this.d.get(paramEventSource);
        if (localMultiSparseArray1 == null)
        {
          MultiSparseArray localMultiSparseArray2 = new MultiSparseArray();
          this.d.put(paramEventSource, localMultiSparseArray2);
          localMultiSparseArray3 = localMultiSparseArray2;
          int i = paramArrayOfInt.length;
          int j = 0;
          if (j >= i)
            continue;
          int k = paramArrayOfInt[j];
          a(localMultiSparseArray3, k);
          localMultiSparseArray3.a(k, localb);
          j++;
          continue;
          return;
        }
      }
      finally
      {
        localLock.unlock();
      }
      MultiSparseArray localMultiSparseArray3 = localMultiSparseArray1;
    }
  }

  @PluginApi(a=6)
  public void addObserver(Observer paramObserver, String paramString, int[] paramArrayOfInt)
  {
    addObserver(paramObserver, null, new EventSource(paramString), false, paramArrayOfInt);
  }

  @PluginApi(a=6)
  public void addUIObserver(Observer paramObserver, String paramString, int[] paramArrayOfInt)
  {
    addObserver(paramObserver, null, new EventSource(paramString), true, paramArrayOfInt);
  }

  public void b(Observer paramObserver, EventSource paramEventSource, int[] paramArrayOfInt)
  {
    addObserver(paramObserver, null, paramEventSource, true, paramArrayOfInt);
  }

  @PluginApi(a=6)
  public void notify(Event paramEvent)
  {
    if (paramEvent == null)
      throw new NullPointerException("Event cannot be null");
    EventSource localEventSource = paramEvent.source;
    if ((localEventSource == null) || (TextUtils.isEmpty(localEventSource.name)))
      throw new NullPointerException("EventSource cannot be null");
    Lock localLock = this.c.readLock();
    while (true)
    {
      Collection localCollection;
      try
      {
        localLock.lock();
        ArrayList localArrayList = a();
        localCollection = a(paramEvent);
        localLock.unlock();
        if (localArrayList != null)
        {
          Iterator localIterator2 = localArrayList.iterator();
          if (localIterator2.hasNext())
          {
            EventInterceptor localEventInterceptor = (EventInterceptor)localIterator2.next();
            if ((localEventInterceptor != null) && (localEventInterceptor.intercept(paramEvent)))
              return;
          }
        }
      }
      finally
      {
        localLock.unlock();
      }
      if (localCollection == null)
        continue;
      Iterator localIterator1 = localCollection.iterator();
      while (localIterator1.hasNext())
        a((b)localIterator1.next(), paramEvent);
    }
  }

  @PluginApi(a=6)
  public void notify(EventSource paramEventSource, int paramInt, Event.EventRank paramEventRank, Object[] paramArrayOfObject)
  {
    if (paramEventRank == null)
      paramEventRank = Event.EventRank.NORMAL;
    notify(Event.a(paramInt, paramEventSource, paramArrayOfObject, paramEventRank));
  }

  @PluginApi(a=6)
  public void removeEventInterceptor(EventInterceptor paramEventInterceptor)
  {
    Lock localLock = this.c.writeLock();
    try
    {
      localLock.lock();
      this.e.remove(paramEventInterceptor);
      return;
    }
    finally
    {
      localLock.unlock();
    }
    throw localObject;
  }

  @PluginApi(a=6)
  public void removeObserver(Object paramObject)
  {
    removeObserver(paramObject, null);
  }

  @PluginApi(a=6)
  public void removeObserver(Object paramObject, EventSource paramEventSource)
  {
    if (paramObject == null)
      throw new NullPointerException("observingObject cannot be null");
    Lock localLock = this.c.writeLock();
    try
    {
      localLock.lock();
      if (paramEventSource != null)
        a(paramObject, paramEventSource);
      while (true)
      {
        return;
        Set localSet = this.d.keySet();
        if (localSet == null)
          continue;
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext())
          a(paramObject, (EventSource)localIterator.next());
      }
    }
    finally
    {
      localLock.unlock();
    }
    throw localObject;
  }

  @PluginApi(a=6)
  public void removeObserver(Object paramObject, EventSource paramEventSource, int[] paramArrayOfInt)
  {
    int i = 0;
    if (paramObject == null)
      throw new NullPointerException("observingObject must not be null");
    if (paramArrayOfInt == null)
      return;
    Lock localLock = this.c.writeLock();
    try
    {
      localLock.lock();
      if (paramEventSource != null)
      {
        int j = paramArrayOfInt.length;
        while (i < j)
        {
          a(paramObject, paramEventSource, paramArrayOfInt[i]);
          i++;
        }
      }
      Set localSet = this.d.keySet();
      if (localSet != null)
      {
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext())
        {
          EventSource localEventSource = (EventSource)localIterator.next();
          int k = paramArrayOfInt.length;
          for (int m = 0; m < k; m++)
            a(paramObject, localEventSource, paramArrayOfInt[m]);
        }
      }
      return;
    }
    finally
    {
      localLock.unlock();
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.event.EventCenter
 * JD-Core Version:    0.6.0
 */
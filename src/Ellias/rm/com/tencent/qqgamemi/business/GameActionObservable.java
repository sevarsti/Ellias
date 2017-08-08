package com.tencent.qqgamemi.business;

import android.database.Observable;
import java.util.ArrayList;
import java.util.Iterator;

public class GameActionObservable extends Observable
{
  public void a(String paramString)
  {
    synchronized (this.mObservers)
    {
      Iterator localIterator = this.mObservers.iterator();
      if (localIterator.hasNext())
        ((GameActionObserver)localIterator.next()).a(paramString);
    }
    monitorexit;
  }

  public void b(String paramString)
  {
    synchronized (this.mObservers)
    {
      Iterator localIterator = this.mObservers.iterator();
      if (localIterator.hasNext())
        ((GameActionObserver)localIterator.next()).b(paramString);
    }
    monitorexit;
  }

  public void c(String paramString)
  {
    synchronized (this.mObservers)
    {
      Iterator localIterator = this.mObservers.iterator();
      if (localIterator.hasNext())
        ((GameActionObserver)localIterator.next()).c(paramString);
    }
    monitorexit;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.GameActionObservable
 * JD-Core Version:    0.6.0
 */
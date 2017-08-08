package com.tencent.component.net.download.multiplex.task;

import com.tencent.component.net.download.multiplex.http.MttRequest;
import com.tencent.component.net.download.multiplex.http.MttResponse;
import com.tencent.component.net.download.multiplex.http.Requester;
import com.tencent.component.utils.log.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class Task
  implements Runnable
{
  public static final byte T = -1;
  public static final byte U = 0;
  public static final byte V = 1;
  public static final byte W = 2;
  public static final byte X = 3;
  public static final byte Y = 4;
  public static final int Z = 1;
  private static final String a = "Task";
  public static final int aa = 2;
  public static final int ab = 4;
  public static final int ac = 8;
  public static final int ad = 16;
  public static final int ae = 32;
  public static final byte af = 0;
  public static final byte ag = 1;
  public static final byte ah = -1;
  public static final byte ai = 1;
  public static final byte aj = 2;
  public static final byte ak = 3;
  public static final byte al = 4;
  public static final int am = 300;
  public static final byte an = -1;
  public static final byte ao = 0;
  public static final byte ap = 1;
  public static final byte aq = 2;
  public static final byte ar = 3;
  public static final byte as = 4;
  public static final byte at = 5;
  public static final byte au = 6;
  public static final byte av = 7;
  public static final byte aw = 8;
  public static final byte ax = 1;
  private static final String b = "";
  protected Requester aA;
  public byte aB;
  public int aC = 0;
  public byte aD = 0;
  protected boolean aE = false;
  protected int aF;
  protected List aG;
  protected int aH = -1;
  protected MttRequest ay;
  protected MttResponse az;
  private int c;
  private boolean d = false;
  private int e;
  private boolean f = false;
  private ReadWriteLock g = new ReentrantReadWriteLock();

  private ArrayList a()
  {
    try
    {
      this.g.readLock().lock();
      localArrayList = new ArrayList(this.aG);
      this.g.readLock().unlock();
      if (localArrayList == null)
        localArrayList = new ArrayList();
      return localArrayList;
    }
    catch (Exception localException)
    {
      while (true)
      {
        LogUtil.e("Task", localException.getMessage(), localException);
        this.g.readLock().unlock();
        ArrayList localArrayList = null;
      }
    }
    finally
    {
      this.g.readLock().unlock();
    }
    throw localObject;
  }

  public abstract void R();

  public void a(TaskObserver paramTaskObserver)
  {
    if (paramTaskObserver == null)
      return;
    try
    {
      this.g.writeLock().lock();
      if (this.aG == null)
        this.aG = new ArrayList(3);
      Iterator localIterator = this.aG.iterator();
      while (localIterator.hasNext())
      {
        TaskObserver localTaskObserver = (TaskObserver)localIterator.next();
        if (localTaskObserver == paramTaskObserver)
          return;
      }
      this.aG.add(paramTaskObserver);
      return;
    }
    catch (Exception localException)
    {
      LogUtil.e("Task", localException.getMessage(), localException);
      return;
    }
    finally
    {
      this.g.writeLock().unlock();
    }
    throw localObject;
  }

  public int aA()
  {
    return this.aF;
  }

  public void aB()
  {
    this.ay.a("Connection", "Close");
  }

  public byte an()
  {
    return this.aD;
  }

  public int ao()
  {
    return this.e;
  }

  public MttRequest ap()
  {
    return this.ay;
  }

  public MttResponse aq()
  {
    return this.az;
  }

  public int ar()
  {
    if (this.ay != null);
    for (int i = this.ay.l(); ; i = 0)
    {
      MttResponse localMttResponse = this.az;
      int j = 0;
      if (localMttResponse != null)
        j = this.az.a();
      return i + j;
    }
  }

  public int as()
  {
    return this.c;
  }

  public Requester at()
  {
    return this.aA;
  }

  public int au()
  {
    return this.aH;
  }

  public boolean av()
  {
    return this.aE;
  }

  protected void aw()
  {
    if ((this.aA != null) && (this.aA.j))
      LogUtil.d("Task", "[Task] BackWrite Cookie");
    if (this.aA != null)
    {
      this.aA.c();
      this.aA = null;
    }
  }

  protected void ax()
  {
    if (this.f);
    i(this.aD);
  }

  public void ay()
  {
    if (this.aG == null);
    while (true)
    {
      return;
      Iterator localIterator = a().iterator();
      while (localIterator.hasNext())
        ((TaskObserver)localIterator.next()).g(this);
    }
  }

  public void az()
  {
    if (this.aG == null);
    while (true)
    {
      return;
      Iterator localIterator = a().iterator();
      while (localIterator.hasNext())
        ((TaskObserver)localIterator.next()).g(this);
    }
  }

  public void b(MttResponse paramMttResponse)
  {
    this.az = paramMttResponse;
    if (this.aA != null)
    {
      this.c = this.aA.h();
      return;
    }
    this.c = 0;
  }

  public void b(TaskObserver paramTaskObserver)
  {
    if (this.aG != null);
    try
    {
      this.g.writeLock().lock();
      this.aG.remove(paramTaskObserver);
      return;
    }
    catch (Exception localException)
    {
      LogUtil.e("Task", localException.getMessage(), localException);
      return;
    }
    finally
    {
      this.g.writeLock().unlock();
    }
    throw localObject;
  }

  public void f(int paramInt)
  {
    this.aC = (paramInt | this.aC);
  }

  public void g(int paramInt)
  {
    this.aC &= (paramInt ^ 0xFFFFFFFF);
  }

  public boolean h(int paramInt)
  {
    return (paramInt & this.aC) != 0;
  }

  protected void i(int paramInt)
  {
    if (this.aG == null);
    while (true)
    {
      return;
      ArrayList localArrayList;
      try
      {
        LogUtil.i("Benson", "[Task] ObserverEvent status : " + paramInt);
        localArrayList = a();
        switch (paramInt)
        {
        case 0:
          Iterator localIterator6 = localArrayList.iterator();
          while (localIterator6.hasNext())
            ((TaskObserver)localIterator6.next()).a(this);
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        }
      }
      catch (Exception localException)
      {
        LogUtil.e("Task", localException.getMessage(), localException);
        return;
      }
      Iterator localIterator5 = localArrayList.iterator();
      while (localIterator5.hasNext())
        ((TaskObserver)localIterator5.next()).b(this);
      continue;
      Iterator localIterator4 = localArrayList.iterator();
      while (localIterator4.hasNext())
        ((TaskObserver)localIterator4.next()).c(this);
      continue;
      Iterator localIterator3 = localArrayList.iterator();
      while (localIterator3.hasNext())
        ((TaskObserver)localIterator3.next()).d(this);
      continue;
      Iterator localIterator2 = localArrayList.iterator();
      while (localIterator2.hasNext())
        ((TaskObserver)localIterator2.next()).e(this);
      continue;
      Iterator localIterator1 = localArrayList.iterator();
      while (localIterator1.hasNext())
        ((TaskObserver)localIterator1.next()).e(this);
    }
  }

  public void p(boolean paramBoolean)
  {
    this.f = paramBoolean;
  }

  public void q(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }

  public String v()
  {
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.task.Task
 * JD-Core Version:    0.6.0
 */
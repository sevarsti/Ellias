package com.tencent.component.net.http.upload;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class UploadManager
{
  private static final String a = "UploadManager";
  private static final int b = 1;
  private static final int c = 2;
  private final ReentrantReadWriteLock d = new ReentrantReadWriteLock();
  private final ReentrantReadWriteLock.WriteLock e;
  private final ReentrantReadWriteLock f = new ReentrantReadWriteLock();
  private final List g = new ArrayList();
  private final List h = new ArrayList();
  private HandlerThread i;
  private Handler j;
  private HashSet k = new HashSet();
  private UploadManager.PersistenceAgent l;
  private volatile boolean m;

  protected UploadManager(UploadManager.PersistenceAgent paramPersistenceAgent)
  {
    this.l = paramPersistenceAgent;
    this.e = this.d.writeLock();
    f();
  }

  private void a(List paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      try
      {
        this.f.writeLock().lock();
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          UploadTask localUploadTask = (UploadTask)localIterator.next();
          if ((localUploadTask == null) || (this.g.contains(localUploadTask)))
            continue;
          localUploadTask.setTaskStatus(2);
          this.g.add(localUploadTask);
        }
      }
      finally
      {
        this.f.writeLock().unlock();
      }
      this.f.writeLock().unlock();
    }
  }

  private void c()
  {
    try
    {
      this.e.lock();
      if (this.i != null)
      {
        boolean bool = this.i.isAlive();
        if (bool)
          return;
      }
      this.i = new HandlerThread("uploadHandle");
      this.i.start();
      this.j = new a(this, this.i.getLooper());
      return;
    }
    finally
    {
      this.e.unlock();
    }
    throw localObject;
  }

  private void d()
  {
    if (this.l != null)
      ThreadPool.getInstance().submit(new b(this));
  }

  private void e()
  {
    if (this.l != null);
    try
    {
      this.f.readLock().lock();
      this.l.a(this.g);
      return;
    }
    finally
    {
      this.f.readLock().unlock();
    }
    throw localObject;
  }

  private void f()
  {
    if (this.l != null)
    {
      ThreadPool.getInstance().submit(new c(this));
      return;
    }
    this.m = true;
  }

  private void f(UploadTask paramUploadTask)
  {
    try
    {
      this.f.writeLock().lock();
      this.g.add(paramUploadTask);
      this.f.writeLock().unlock();
      paramUploadTask.setTaskStatus(1);
      k();
      return;
    }
    finally
    {
      this.f.writeLock().unlock();
    }
    throw localObject;
  }

  private void g()
  {
    ArrayList localArrayList = new ArrayList(this.h);
    LogUtil.i("UploadManager", "datas loaded , try to upload pending task. task size:" + this.g.size() + " | pending size:" + this.h.size());
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
      e((UploadTask)localIterator.next());
  }

  private void g(UploadTask paramUploadTask)
  {
    Iterator localIterator = new HashSet(this.k).iterator();
    while (localIterator.hasNext())
    {
      UploadManager.TaskListListener localTaskListListener = (UploadManager.TaskListListener)localIterator.next();
      if (paramUploadTask == null)
        continue;
      localTaskListListener.a(paramUploadTask);
    }
  }

  private int h()
  {
    while (true)
    {
      int n;
      try
      {
        this.f.readLock().lock();
        n = 0;
        Iterator localIterator = this.g.iterator();
        if (!localIterator.hasNext())
          continue;
        int i1 = ((UploadTask)localIterator.next()).getTaskStatus();
        if (i1 == 3)
        {
          i2 = n + 1;
          n = i2;
          continue;
          return n;
        }
      }
      finally
      {
        this.f.readLock().unlock();
      }
      int i2 = n;
    }
  }

  private void h(UploadTask paramUploadTask)
  {
    Iterator localIterator = new HashSet(this.k).iterator();
    while (localIterator.hasNext())
    {
      UploadManager.TaskListListener localTaskListListener = (UploadManager.TaskListListener)localIterator.next();
      if (paramUploadTask == null)
        continue;
      localTaskListListener.b(paramUploadTask);
    }
  }

  private UploadTask i()
  {
    try
    {
      this.f.readLock().lock();
      Iterator localIterator = this.g.iterator();
      while (localIterator.hasNext())
      {
        UploadTask localUploadTask = (UploadTask)localIterator.next();
        int n = localUploadTask.getTaskStatus();
        if (n == 1)
          return localUploadTask;
      }
      return null;
    }
    finally
    {
      this.f.readLock().unlock();
    }
    throw localObject;
  }

  private void j()
  {
    if (h() < 2)
    {
      UploadTask localUploadTask = i();
      if (localUploadTask != null)
      {
        localUploadTask.setTaskStatus(3);
        if (this.j == null)
          c();
        if (this.j != null)
          this.j.obtainMessage(1, localUploadTask).sendToTarget();
      }
    }
  }

  private void k()
  {
    HashSet localHashSet = new HashSet(this.k);
    ArrayList localArrayList = new ArrayList(this.g);
    Iterator localIterator = localHashSet.iterator();
    while (localIterator.hasNext())
      ((UploadManager.TaskListListener)localIterator.next()).a(localArrayList);
    d();
  }

  public List a()
  {
    if (this.g != null)
      return new ArrayList(this.g);
    return null;
  }

  public void a(UploadManager.TaskListListener paramTaskListListener)
  {
    if (paramTaskListListener != null)
      this.k.add(paramTaskListListener);
  }

  public void a(UploadTask paramUploadTask)
  {
    if (paramUploadTask != null)
    {
      paramUploadTask.setTaskStatus(2);
      j();
    }
  }

  public void a(boolean paramBoolean, UploadTask paramUploadTask)
  {
    if ((paramUploadTask == null) || (paramBoolean));
    while (true)
    {
      try
      {
        this.f.writeLock().lock();
        this.g.remove(paramUploadTask);
        this.f.writeLock().unlock();
        paramUploadTask.setTaskStatus(4);
        g(paramUploadTask);
        k();
        j();
        return;
      }
      finally
      {
        this.f.writeLock().unlock();
      }
      paramUploadTask.setTaskStatus(5);
      h(paramUploadTask);
    }
  }

  public void b()
  {
    try
    {
      this.f.writeLock().lock();
      Iterator localIterator = this.g.iterator();
      while (localIterator.hasNext())
      {
        UploadTask localUploadTask = (UploadTask)localIterator.next();
        if (localUploadTask == null)
          continue;
        localUploadTask.setTaskStatus(2);
      }
    }
    finally
    {
      this.f.writeLock().unlock();
    }
    this.f.writeLock().unlock();
  }

  public void b(UploadManager.TaskListListener paramTaskListListener)
  {
    if (paramTaskListListener != null)
      this.k.remove(paramTaskListListener);
  }

  public void b(UploadTask paramUploadTask)
  {
    if (paramUploadTask != null)
    {
      paramUploadTask.setTaskStatus(1);
      j();
    }
  }

  public void c(UploadTask paramUploadTask)
  {
    if (paramUploadTask != null);
    try
    {
      this.f.writeLock().lock();
      this.g.remove(paramUploadTask);
      this.f.writeLock().unlock();
      paramUploadTask.setTaskStatus(6);
      k();
      j();
      return;
    }
    finally
    {
      this.f.writeLock().unlock();
    }
    throw localObject;
  }

  public void d(UploadTask paramUploadTask)
  {
    d();
  }

  public boolean e(UploadTask paramUploadTask)
  {
    if (paramUploadTask == null)
    {
      LogUtil.e("UploadManager", "task is empty,ingnore it!");
      return false;
    }
    if (!paramUploadTask.haslegalFile())
    {
      LogUtil.e("UploadManager", "task file is illegal.");
      return false;
    }
    if (!this.m)
    {
      LogUtil.i("UploadManager", "add a upload to pending list (data not init) task size:" + this.g.size());
      this.h.add(paramUploadTask);
    }
    while (true)
    {
      return true;
      LogUtil.i("UploadManager", "receive a upload request . task size:" + this.g.size());
      c();
      paramUploadTask.caculateFileSize();
      f(paramUploadTask);
      j();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.upload.UploadManager
 * JD-Core Version:    0.6.0
 */
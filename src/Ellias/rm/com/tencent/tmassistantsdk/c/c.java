package com.tencent.tmassistantsdk.c;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.a.a;
import com.tencent.tmassistantsdk.g.l;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class c extends e
{
  protected ReferenceQueue a = new ReferenceQueue();
  protected ArrayList b = new ArrayList();

  public c(Context paramContext, String paramString)
  {
    super(paramContext, paramString, "com.tencent.tmassistantsdk.downloadservice.TMAssistantDownloadSDKService");
    this.h = new d(this);
  }

  public int a(String paramString1, int paramInt, String paramString2, String paramString3, Map paramMap)
  {
    monitorenter;
    try
    {
      l.b("TMAssistantDownloadSDKClient", "startDownloadTask,clientKey:" + this.d + ",url:" + paramString1);
      if (paramString1 == null)
        throw new IllegalArgumentException("TMAssistantDownloadSDKClient.startDownloadTask url is null");
    }
    finally
    {
      monitorexit;
    }
    if ((paramString2.equals("resource/tm.android.unknown")) && (TextUtils.isEmpty(paramString3)))
      throw new IllegalArgumentException("if contentType is others, filename shouldn't be null!");
    com.tencent.tmassistantsdk.a.d locald = (com.tencent.tmassistantsdk.a.d)super.g();
    int i;
    if (locald != null)
      i = locald.a(this.d, paramString1, 0, paramString2, paramString3, paramMap);
    for (int j = i; ; j = 0)
    {
      monitorexit;
      return j;
      super.e();
    }
  }

  public int a(String paramString1, String paramString2)
  {
    monitorenter;
    try
    {
      int i = a(paramString1, 0, paramString2, null, null);
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public i a(String paramString)
  {
    monitorenter;
    try
    {
      l.b("TMAssistantDownloadSDKClient", "getDownloadTaskState,clientKey:" + this.d + ",url:" + paramString);
      if (paramString == null)
        throw new IllegalArgumentException("TMAssistantDownloadSDKClient.getDownloadTaskState url is null");
    }
    finally
    {
      monitorexit;
    }
    com.tencent.tmassistantsdk.a.d locald = (com.tencent.tmassistantsdk.a.d)super.g();
    i locali1;
    if (locald != null)
      locali1 = locald.a(this.d, paramString);
    for (i locali2 = locali1; ; locali2 = null)
    {
      monitorexit;
      return locali2;
      super.e();
    }
  }

  protected void a()
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      b localb = (b)((WeakReference)localIterator.next()).get();
      g.a().a(this, localb);
    }
  }

  protected void a(IBinder paramIBinder)
  {
    this.g = com.tencent.tmassistantsdk.a.e.a(paramIBinder);
  }

  public boolean a(b paramb)
  {
    monitorenter;
    int i;
    if (paramb == null)
      i = 0;
    while (true)
    {
      monitorexit;
      return i;
      try
      {
        while (true)
        {
          Reference localReference = this.a.poll();
          if (localReference == null)
            break;
          l.b("TMAssistantDownloadSDKClient", "registerDownloadTaskListener removed listener!!!!");
          this.b.remove(localReference);
        }
      }
      finally
      {
        monitorexit;
      }
      Iterator localIterator = this.b.iterator();
      while (true)
        if (localIterator.hasNext())
        {
          if ((b)((WeakReference)localIterator.next()).get() != paramb)
            continue;
          i = 1;
          break;
        }
      WeakReference localWeakReference = new WeakReference(paramb, this.a);
      this.b.add(localWeakReference);
      i = 1;
    }
  }

  protected void b()
  {
    ((com.tencent.tmassistantsdk.a.d)this.g).a(this.d, (a)this.h);
  }

  public void b(String paramString)
  {
    monitorenter;
    try
    {
      l.b("TMAssistantDownloadSDKClient", "pauseDownloadTask,clientKey:" + this.d + ",url:" + paramString);
      if (paramString == null)
        throw new IllegalArgumentException("TMAssistantDownloadSDKClient.startDownloadTask url is null");
    }
    finally
    {
      monitorexit;
    }
    com.tencent.tmassistantsdk.a.d locald = (com.tencent.tmassistantsdk.a.d)super.g();
    if (locald != null)
      locald.b(this.d, paramString);
    while (true)
    {
      monitorexit;
      return;
      super.e();
    }
  }

  protected Intent c()
  {
    return new Intent(this.c, Class.forName(this.e));
  }

  protected void d()
  {
    ((com.tencent.tmassistantsdk.a.d)this.g).b(this.d, (a)this.h);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.c.c
 * JD-Core Version:    0.6.0
 */
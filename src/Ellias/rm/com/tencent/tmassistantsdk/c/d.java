package com.tencent.tmassistantsdk.c;

import com.tencent.tmassistantsdk.g.l;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

class d extends com.tencent.tmassistantsdk.a.b
{
  d(c paramc)
  {
  }

  public void a(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3)
  {
    l.b("TMAssistantDownloadSDKClient", "OnDownloadStateChanged,clientKey:" + paramString1 + ",state:" + paramInt1 + ", errorcode" + paramInt2 + ",url:" + paramString2);
    Iterator localIterator = this.a.b.iterator();
    while (localIterator.hasNext())
    {
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      b localb = (b)localWeakReference.get();
      if (localb == null)
        l.b("TMAssistantDownloadSDKClient", " listener = " + localb + "   linstenerWeakReference :" + localWeakReference);
      g.a().a(this.a, localb, paramString2, paramInt1, paramInt2, paramString3);
    }
  }

  public void a(String paramString1, String paramString2, long paramLong1, long paramLong2)
  {
    l.b("TMAssistantDownloadSDKClient", "OnDownloadProgressChanged,clientKey:" + paramString1 + ",receivedLen:" + paramLong1 + ",totalLen:" + paramLong2 + ",url:" + paramString2);
    Iterator localIterator = this.a.b.iterator();
    while (localIterator.hasNext())
    {
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      b localb = (b)localWeakReference.get();
      if (localb == null)
        l.b("TMAssistantDownloadSDKClient", " listener = " + localb + "   linstenerWeakReference :" + localWeakReference);
      g.a().a(this.a, localb, paramString2, paramLong1, paramLong2);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.c.d
 * JD-Core Version:    0.6.0
 */
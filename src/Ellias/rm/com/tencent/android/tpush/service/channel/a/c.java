package com.tencent.android.tpush.service.channel.a;

import com.tencent.android.tpush.service.channel.b.d;
import com.tencent.android.tpush.service.channel.b.e;
import com.tencent.android.tpush.service.channel.b.g;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

public class c extends a
{
  private static final String n = c.class.getSimpleName();
  protected String l = null;
  protected String m = null;
  private boolean o = false;

  public c(SocketChannel paramSocketChannel, b paramb)
  {
    super(paramSocketChannel, paramb);
    StringBuilder localStringBuilder = new StringBuilder().append(this.g);
    if (this.h == 80);
    for (String str = ""; ; str = ":" + this.h)
    {
      this.m = str;
      this.l = "/";
      this.i = 1;
      return;
    }
  }

  protected c(SocketChannel paramSocketChannel, b paramb, String paramString1, int paramInt, String paramString2)
  {
    super(paramSocketChannel, paramb);
    this.g = paramString1;
    this.h = paramInt;
    StringBuilder localStringBuilder = new StringBuilder().append(paramString1);
    if (paramInt == 80);
    for (String str = ""; ; str = ":" + paramInt)
    {
      this.m = str;
      this.l = paramString2;
      return;
    }
  }

  public void a(a parama, d paramd)
  {
    if ((paramd instanceof com.tencent.android.tpush.service.channel.b.a))
    {
      Iterator localIterator = ((com.tencent.android.tpush.service.channel.b.a)paramd).i.iterator();
      while (localIterator.hasNext())
      {
        g localg = (g)localIterator.next();
        this.a.b(parama, localg);
      }
      c();
      return;
    }
    throw new InnerException("packet is not instance of Http****Packet!");
  }

  public void a(a parama, e parame)
  {
    this.o = true;
    if ((parame instanceof com.tencent.android.tpush.service.channel.b.b))
    {
      Iterator localIterator = ((com.tencent.android.tpush.service.channel.b.b)parame).d.iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        this.a.a(parama, (i)locale);
      }
    }
    throw new InnerException("packet is not instance of Http****Packet!");
  }

  protected boolean a()
  {
    if (!this.o);
    do
    {
      return false;
      if (this.e != null)
        continue;
      this.e = new com.tencent.android.tpush.service.channel.b.a();
      this.e.a(this.d);
    }
    while (this.e == null);
    return true;
  }

  protected boolean b()
  {
    if ((this.f == null) && (!this.o))
    {
      ArrayList localArrayList = this.a.a(this, 16);
      if (localArrayList.size() > 0)
      {
        com.tencent.android.tpush.service.channel.b.b localb = new com.tencent.android.tpush.service.channel.b.b(this.m, this.l);
        localb.a(this.d);
        localb.a("Host", this.m);
        localb.a("User-Agent", "TPNS_CLIENT/0.1");
        localb.a("Content-Type", "application/binary");
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
          localb.a((h)localIterator.next());
        this.f = localb;
      }
    }
    return this.f != null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.a.c
 * JD-Core Version:    0.6.0
 */
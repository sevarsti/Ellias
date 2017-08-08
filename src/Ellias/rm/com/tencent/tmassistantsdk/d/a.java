package com.tencent.tmassistantsdk.d;

import com.qq.taf.jce.JceStruct;
import com.tencent.tmassistantsdk.f.c.b;
import com.tencent.tmassistantsdk.g.f;
import com.tencent.tmassistantsdk.g.l;
import java.util.ArrayList;
import java.util.List;

public abstract class a
  implements d
{
  protected e a = null;
  protected int b = 0;
  protected final List c = new ArrayList();

  public void a()
  {
    this.a = null;
    this.c.clear();
  }

  public void a(JceStruct paramJceStruct)
  {
    monitorenter;
    try
    {
      l.b("BaseReportManager", "addLogData,loginfo");
      if (paramJceStruct != null)
      {
        byte[] arrayOfByte = com.tencent.tmassistantsdk.protocol.a.c(paramJceStruct);
        e().a(arrayOfByte);
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(e parame, boolean paramBoolean)
  {
    l.b("BaseReportManager", "onLogReprotHttpRequestFinish,result:" + paramBoolean);
    if ((paramBoolean) && (this.c != null) && (this.c.size() > 0))
      e().a(this.c);
    this.a = null;
    this.c.clear();
    if ((paramBoolean) && (this.b < 5))
    {
      l.b("BaseReportManager", "onLogReprotHttpRequestFinish wifi is true reportlog go on,result:" + paramBoolean + " count:" + this.b);
      c();
      this.b = (1 + this.b);
    }
  }

  public void b()
  {
    if (this.a != null)
    {
      this.a.b();
      this.a = null;
    }
  }

  public void c()
  {
    monitorenter;
    while (true)
    {
      b localb;
      try
      {
        boolean bool1 = f.a().l();
        if (!bool1)
          return;
        l.b("BaseReportManager", "reportLogData,request:" + this.a);
        if (this.a != null)
          continue;
        this.a = new e();
        this.a.a(this);
        localb = e().a(1000);
        l.b("BaseReportManager", "readLogDataAndSendToServer,wrappterCount:" + localb.b.size());
        i = 0;
        if (localb == null)
          continue;
        int j = localb.b.size();
        i = 0;
        if (j <= 0)
        {
          if (i != 0)
            continue;
          this.b = 0;
          this.a = null;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      this.c.addAll(localb.a);
      boolean bool2 = this.a.a(f(), localb);
      int i = bool2;
    }
  }

  public void d()
  {
    this.b = 0;
  }

  protected abstract com.tencent.tmassistantsdk.f.c.a e();

  protected abstract byte f();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.d.a
 * JD-Core Version:    0.6.0
 */
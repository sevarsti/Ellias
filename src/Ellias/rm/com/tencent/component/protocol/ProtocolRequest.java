package com.tencent.component.protocol;

import com.qq.taf.jce.JceStruct;
import com.tencent.component.ComponentContext;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.net.http.AsyncHttpResult.FailDescription;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.WupTools;
import com.tencent.component.utils.log.LogUtil;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ProtocolRequest
{
  protected static final String a = "ProtocolRequest";
  public static String m;
  public static String n;
  private static volatile AtomicInteger q = new AtomicInteger();
  protected int b = -1;
  protected long c = -1L;
  protected Class d;
  protected long e = System.currentTimeMillis();
  protected long f = -1L;
  protected long g = -1L;
  protected long h;
  protected long i;
  protected long j;
  protected long k;
  protected String l = null;
  private String o;
  private int p = 30000;
  private int r = q.getAndIncrement();
  private HashMap s;

  static
  {
    m = "Statistic.Request.Counter";
    n = "[SeqNo:%d] [Cmd:%s] ALL = %dms |INIT = %dms |QUEUE = %dms |NETWORK = %dms |REQ_SIZE = %db |RSP SIZE = %db |DECOMPRESSED RSP SIZE = %db";
  }

  public abstract JceStruct a();

  public Object a(Object paramObject)
  {
    if ((this.s == null) || (paramObject == null))
      return null;
    return this.s.get(paramObject);
  }

  public void a(int paramInt)
  {
    this.p = paramInt;
  }

  public void a(long paramLong)
  {
    this.c = paramLong;
  }

  public abstract void a(long paramLong, byte[] paramArrayOfByte);

  public abstract void a(AsyncHttpResult.FailDescription paramFailDescription);

  public void a(Object paramObject1, Object paramObject2)
  {
    if (this.s == null)
      monitorenter;
    try
    {
      if (this.s == null)
        this.s = new HashMap();
      monitorexit;
      this.s.put(paramObject1, paramObject2);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(String paramString)
  {
    this.l = paramString;
  }

  public abstract byte[] a(byte[] paramArrayOfByte);

  public void b(int paramInt)
  {
    this.b = paramInt;
    this.o = c(paramInt);
  }

  public byte[] b()
  {
    if (this.b < 0)
      throw new IllegalStateException("Must specified protocol cmd before doRequest!");
    JceStruct localJceStruct = a();
    if (localJceStruct == null);
    for (byte[] arrayOfByte1 = new byte[0]; ; arrayOfByte1 = WupTools.encodeWup(localJceStruct))
    {
      byte[] arrayOfByte2 = a(arrayOfByte1);
      this.i = arrayOfByte2.length;
      return arrayOfByte2;
    }
  }

  public int c()
  {
    return this.p;
  }

  protected abstract String c(int paramInt);

  public String d()
  {
    if (this.o == null)
      this.o = c(this.b);
    return this.o;
  }

  public int e()
  {
    return this.b;
  }

  public int f()
  {
    return this.r;
  }

  public void g()
  {
    if (this.g <= 0L)
      this.g = System.currentTimeMillis();
  }

  @PluginApi(a=6)
  public abstract Class getResponseClass();

  public void h()
  {
    if (this.f <= 0L)
      this.f = System.currentTimeMillis();
  }

  public abstract void i();

  public abstract void j();

  public String k()
  {
    long l1 = this.f - this.e;
    if (l1 < 0L)
      l1 = 0L;
    long l2 = this.g - this.f;
    if (l2 < 0L)
      l2 = 0L;
    long l3 = this.h - this.g;
    if (l3 < 0L)
      l3 = 0L;
    long l4 = this.h - this.e;
    long l5;
    if (l4 < 0L)
      l5 = System.currentTimeMillis() - this.e;
    while (true)
    {
      String str1 = n;
      Object[] arrayOfObject = new Object[9];
      arrayOfObject[0] = Integer.valueOf(f());
      arrayOfObject[1] = d();
      arrayOfObject[2] = Long.valueOf(l5);
      arrayOfObject[3] = Long.valueOf(l1);
      arrayOfObject[4] = Long.valueOf(l2);
      arrayOfObject[5] = Long.valueOf(l3);
      arrayOfObject[6] = Long.valueOf(this.i);
      arrayOfObject[7] = Long.valueOf(this.j);
      arrayOfObject[8] = Long.valueOf(this.k);
      String str2 = String.format(str1, arrayOfObject);
      LogUtil.i(m, str2);
      return str2;
      l5 = l4;
    }
  }

  public String l()
  {
    if (DebugUtil.a(ComponentContext.a()));
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.protocol.ProtocolRequest
 * JD-Core Version:    0.6.0
 */
package com.tencent.android.tpush.logging.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class a extends k
  implements Handler.Callback
{
  private b a;
  private FileWriter b;
  private File c;
  private char[] d;
  private volatile g e;
  private volatile g f;
  private volatile g g;
  private volatile g h;
  private volatile boolean i = false;
  private HandlerThread j;
  private Handler k;

  public a(int paramInt, boolean paramBoolean, j paramj, b paramb)
  {
    super(paramInt, paramBoolean, paramj);
    a(paramb);
    this.e = new g();
    this.f = new g();
    this.g = this.e;
    this.h = this.f;
    this.d = new char[paramb.f()];
    paramb.b();
    h();
    this.j = new HandlerThread(paramb.c(), paramb.i());
    if (this.j != null)
      this.j.start();
    if (this.j.isAlive())
      this.k = new Handler(this.j.getLooper(), this);
    f();
  }

  public a(b paramb)
  {
    this(63, true, j.a, paramb);
  }

  private void f()
  {
    if (this.k != null)
      this.k.sendEmptyMessageDelayed(1024, c().g());
  }

  // ERROR //
  private void g()
  {
    // Byte code:
    //   0: invokestatic 117	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   3: aload_0
    //   4: getfield 72	com/tencent/android/tpush/logging/a/a:j	Landroid/os/HandlerThread;
    //   7: if_acmpeq +4 -> 11
    //   10: return
    //   11: aload_0
    //   12: getfield 32	com/tencent/android/tpush/logging/a/a:i	Z
    //   15: ifne -5 -> 10
    //   18: aload_0
    //   19: iconst_1
    //   20: putfield 32	com/tencent/android/tpush/logging/a/a:i	Z
    //   23: aload_0
    //   24: invokespecial 119	com/tencent/android/tpush/logging/a/a:j	()V
    //   27: aload_0
    //   28: getfield 48	com/tencent/android/tpush/logging/a/a:h	Lcom/tencent/android/tpush/logging/a/g;
    //   31: aload_0
    //   32: invokespecial 60	com/tencent/android/tpush/logging/a/a:h	()Ljava/io/Writer;
    //   35: aload_0
    //   36: getfield 55	com/tencent/android/tpush/logging/a/a:d	[C
    //   39: invokevirtual 122	com/tencent/android/tpush/logging/a/g:a	(Ljava/io/Writer;[C)V
    //   42: aload_0
    //   43: getfield 48	com/tencent/android/tpush/logging/a/a:h	Lcom/tencent/android/tpush/logging/a/g;
    //   46: invokevirtual 123	com/tencent/android/tpush/logging/a/g:b	()V
    //   49: aload_0
    //   50: iconst_0
    //   51: putfield 32	com/tencent/android/tpush/logging/a/a:i	Z
    //   54: return
    //   55: astore_2
    //   56: aload_0
    //   57: getfield 48	com/tencent/android/tpush/logging/a/a:h	Lcom/tencent/android/tpush/logging/a/g;
    //   60: invokevirtual 123	com/tencent/android/tpush/logging/a/g:b	()V
    //   63: goto -14 -> 49
    //   66: astore_1
    //   67: aload_0
    //   68: getfield 48	com/tencent/android/tpush/logging/a/a:h	Lcom/tencent/android/tpush/logging/a/g;
    //   71: invokevirtual 123	com/tencent/android/tpush/logging/a/g:b	()V
    //   74: aload_1
    //   75: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   27	42	55	java/io/IOException
    //   27	42	66	finally
  }

  private Writer h()
  {
    File localFile1 = c().a();
    File localFile2 = this.c;
    int m = 0;
    if (localFile2 != null)
      if (this.c.exists())
      {
        boolean bool = this.c.canWrite();
        m = 0;
        if (bool);
      }
      else
      {
        m = 1;
      }
    if ((m != 0) || ((localFile1 != null) && (!localFile1.equals(this.c))))
    {
      this.c = localFile1;
      i();
    }
    try
    {
      this.b = new FileWriter(this.c, true);
      return this.b;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  private void i()
  {
    try
    {
      if (this.b != null)
      {
        this.b.flush();
        this.b.close();
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void j()
  {
    monitorenter;
    try
    {
      if (this.g == this.e)
        this.g = this.f;
      for (this.h = this.e; ; this.h = this.f)
      {
        return;
        this.g = this.e;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a()
  {
    if (this.k.hasMessages(1024))
      this.k.removeMessages(1024);
    this.k.sendEmptyMessage(1024);
  }

  protected void a(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable)
  {
    a(e().a(paramInt, paramThread, paramLong, paramString1, paramString2, paramThrowable));
  }

  public void a(b paramb)
  {
    this.a = paramb;
  }

  protected void a(String paramString)
  {
    this.g.a(paramString);
    if (this.g.a() >= c().f())
      a();
  }

  public void b()
  {
    i();
    this.j.quit();
  }

  public b c()
  {
    return this.a;
  }

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1024:
    }
    while (true)
    {
      return true;
      g();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.a.a
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.debug;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.tencent.component.app.ExceptionManager;
import com.tencent.component.data.SafeStringQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTracer extends Tracer
  implements Handler.Callback
{
  private static final int a = 1024;
  private FileTracerConfig b;
  private FileOutputStream c;
  private File d;
  private char[] e;
  private volatile SafeStringQueue f;
  private volatile SafeStringQueue g;
  private volatile SafeStringQueue h;
  private volatile SafeStringQueue i;
  private volatile boolean j = false;
  private HandlerThread k;
  private Handler l;

  public FileTracer(int paramInt, boolean paramBoolean, TraceFormat paramTraceFormat, FileTracerConfig paramFileTracerConfig)
  {
    super(paramInt, paramBoolean, paramTraceFormat);
    a(paramFileTracerConfig);
    this.f = new SafeStringQueue();
    this.g = new SafeStringQueue();
    this.h = this.f;
    this.i = this.g;
    this.e = new char[paramFileTracerConfig.f()];
    i();
    this.k = new HandlerThread(paramFileTracerConfig.c(), paramFileTracerConfig.i());
    if (this.k != null)
      this.k.start();
    if (this.k.isAlive())
      this.l = new Handler(this.k.getLooper(), this);
    g();
    this.l.postDelayed(new a(this), 15000L);
  }

  public FileTracer(FileTracerConfig paramFileTracerConfig)
  {
    this(63, true, TraceFormat.i, paramFileTracerConfig);
  }

  private void g()
  {
    this.l.sendEmptyMessageDelayed(1024, c().g());
  }

  // ERROR //
  private void h()
  {
    // Byte code:
    //   0: invokestatic 129	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   3: aload_0
    //   4: getfield 73	com/tencent/component/debug/FileTracer:k	Landroid/os/HandlerThread;
    //   7: if_acmpeq +4 -> 11
    //   10: return
    //   11: aload_0
    //   12: getfield 35	com/tencent/component/debug/FileTracer:j	Z
    //   15: ifne -5 -> 10
    //   18: aload_0
    //   19: iconst_1
    //   20: putfield 35	com/tencent/component/debug/FileTracer:j	Z
    //   23: aconst_null
    //   24: astore_1
    //   25: aload_0
    //   26: invokespecial 131	com/tencent/component/debug/FileTracer:k	()V
    //   29: aload_0
    //   30: invokespecial 61	com/tencent/component/debug/FileTracer:i	()Ljava/io/FileOutputStream;
    //   33: astore 8
    //   35: aconst_null
    //   36: astore_1
    //   37: aload 8
    //   39: ifnull +45 -> 84
    //   42: aload 8
    //   44: invokevirtual 137	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   47: invokevirtual 143	java/nio/channels/FileChannel:lock	()Ljava/nio/channels/FileLock;
    //   50: astore 9
    //   52: aload 9
    //   54: astore_1
    //   55: new 145	java/io/OutputStreamWriter
    //   58: dup
    //   59: aload 8
    //   61: invokespecial 148	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   64: astore 10
    //   66: aload 10
    //   68: ifnull +16 -> 84
    //   71: aload_0
    //   72: getfield 51	com/tencent/component/debug/FileTracer:i	Lcom/tencent/component/data/SafeStringQueue;
    //   75: aload 10
    //   77: aload_0
    //   78: getfield 58	com/tencent/component/debug/FileTracer:e	[C
    //   81: invokevirtual 151	com/tencent/component/data/SafeStringQueue:a	(Ljava/io/Writer;[C)V
    //   84: aload_1
    //   85: ifnull +7 -> 92
    //   88: aload_1
    //   89: invokevirtual 156	java/nio/channels/FileLock:release	()V
    //   92: aload_0
    //   93: getfield 51	com/tencent/component/debug/FileTracer:i	Lcom/tencent/component/data/SafeStringQueue;
    //   96: invokevirtual 158	com/tencent/component/data/SafeStringQueue:b	()V
    //   99: aload_0
    //   100: iconst_0
    //   101: putfield 35	com/tencent/component/debug/FileTracer:j	Z
    //   104: return
    //   105: astore 6
    //   107: aload_1
    //   108: ifnull +7 -> 115
    //   111: aload_1
    //   112: invokevirtual 156	java/nio/channels/FileLock:release	()V
    //   115: aload_0
    //   116: getfield 51	com/tencent/component/debug/FileTracer:i	Lcom/tencent/component/data/SafeStringQueue;
    //   119: invokevirtual 158	com/tencent/component/data/SafeStringQueue:b	()V
    //   122: goto -23 -> 99
    //   125: astore_2
    //   126: aconst_null
    //   127: astore_3
    //   128: aload_2
    //   129: astore 4
    //   131: aload_3
    //   132: ifnull +7 -> 139
    //   135: aload_3
    //   136: invokevirtual 156	java/nio/channels/FileLock:release	()V
    //   139: aload_0
    //   140: getfield 51	com/tencent/component/debug/FileTracer:i	Lcom/tencent/component/data/SafeStringQueue;
    //   143: invokevirtual 158	com/tencent/component/data/SafeStringQueue:b	()V
    //   146: aload 4
    //   148: athrow
    //   149: astore 12
    //   151: goto -59 -> 92
    //   154: astore 7
    //   156: goto -41 -> 115
    //   159: astore 5
    //   161: goto -22 -> 139
    //   164: astore 11
    //   166: aload_1
    //   167: astore_3
    //   168: aload 11
    //   170: astore 4
    //   172: goto -41 -> 131
    //
    // Exception table:
    //   from	to	target	type
    //   29	35	105	java/lang/Exception
    //   42	52	105	java/lang/Exception
    //   55	66	105	java/lang/Exception
    //   71	84	105	java/lang/Exception
    //   29	35	125	finally
    //   42	52	125	finally
    //   88	92	149	java/lang/Exception
    //   111	115	154	java/lang/Exception
    //   135	139	159	java/lang/Exception
    //   55	66	164	finally
    //   71	84	164	finally
  }

  private FileOutputStream i()
  {
    File localFile1 = c().a();
    File localFile2 = this.d;
    int m = 0;
    if (localFile2 != null)
      if (this.d.exists())
      {
        boolean bool = this.d.canWrite();
        m = 0;
        if (bool);
      }
      else
      {
        m = 1;
      }
    if ((m != 0) || ((localFile1 != null) && (!localFile1.equals(this.d))))
    {
      this.d = localFile1;
      j();
    }
    try
    {
      this.c = new FileOutputStream(this.d, true);
      return this.c;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  private void j()
  {
    try
    {
      if (this.c != null)
      {
        this.c.flush();
        this.c.close();
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void k()
  {
    monitorenter;
    try
    {
      if (this.h == this.f)
        this.h = this.g;
      for (this.i = this.f; ; this.i = this.g)
      {
        return;
        this.h = this.f;
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
    if (this.l.hasMessages(1024))
      this.l.removeMessages(1024);
    this.l.sendEmptyMessage(1024);
  }

  protected void a(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable)
  {
    a(f().a(paramInt, paramThread, paramLong, paramString1, paramString2, paramThrowable));
  }

  public void a(FileTracerConfig paramFileTracerConfig)
  {
    this.b = paramFileTracerConfig;
  }

  public void a(File paramFile)
  {
    this.d = paramFile;
  }

  protected void a(String paramString)
  {
    this.h.a(paramString);
    if (this.h.a() >= c().f())
      a();
  }

  public void b()
  {
    j();
    this.k.quit();
  }

  public FileTracerConfig c()
  {
    return this.b;
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
      try
      {
        h();
        g();
      }
      catch (Throwable localThrowable)
      {
        while (true)
          ExceptionManager.a().a(localThrowable);
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.debug.FileTracer
 * JD-Core Version:    0.6.0
 */
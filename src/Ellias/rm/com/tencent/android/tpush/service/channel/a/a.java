package com.tencent.android.tpush.service.channel.a;

import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.b.d;
import com.tencent.android.tpush.service.channel.b.e;
import com.tencent.android.tpush.service.channel.b.g;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class a extends Thread
{
  protected b a;
  protected SocketChannel b = null;
  protected Selector c = null;
  protected TpnsSecurity d = new TpnsSecurity();
  protected d e = null;
  protected e f = null;
  protected String g = "";
  protected int h = 0;
  protected int i = 0;
  protected long j = 9223372036854775807L;
  protected com.tencent.android.tpush.service.channel.a k = null;
  private volatile boolean l = false;

  public a(SocketChannel paramSocketChannel, b paramb)
  {
    super("TpnsClient");
    TLog.v("XGService", "@@ construct(" + paramb + ")");
    if (paramSocketChannel.socket().isConnected())
      if (paramSocketChannel.socket().getInetAddress() != null)
        break label154;
    label154: for (String str = ""; ; str = paramSocketChannel.socket().getInetAddress().getHostAddress())
    {
      this.g = str;
      this.h = paramSocketChannel.socket().getPort();
      this.i = 0;
      this.b = paramSocketChannel;
      this.a = paramb;
      return;
    }
  }

  protected int a(InputStream paramInputStream)
  {
    TLog.v("XGTcpRecvPacks", "@@ recvHandle(" + paramInputStream + ")");
    int m = 0;
    while (paramInputStream.available() > 0)
    {
      TLog.i("XGTcpRecvPacks", ">> is.avaiable : " + paramInputStream.available());
      a();
      if (this.e == null)
        continue;
      m += this.e.a(paramInputStream);
      TLog.i("XGTcpRecvPacks", ">> recv " + m + " bytes");
      if (this.e.b())
      {
        TLog.i("XGTcpRecvPacks", ">> success");
        a(this, this.e);
        this.e = null;
        continue;
      }
      TLog.i("XGTcpRecvPacks", ">> not success");
    }
    TLog.i("XGTcpRecvPacks", ">> total " + m + " bytes");
    return m;
  }

  protected int a(OutputStream paramOutputStream)
  {
    TLog.v("XGTcpSendPacks", "@@ sendHandle(" + paramOutputStream + ")");
    if (!f())
    {
      TLog.i("XGTcpSendPacks", ">> isRetired : " + f());
      b();
    }
    e locale = this.f;
    int m = 0;
    if (locale != null)
    {
      TLog.i("XGTcpSendPacks", ">> currentSendPacket != null");
      m = this.f.a(paramOutputStream);
      if (this.f.b())
      {
        TLog.i("XGTcpSendPacks", ">> success");
        a(this, this.f);
        this.f = null;
      }
      if (b())
      {
        TLog.i("XGTcpSendPacks", ">> next write");
        g();
      }
    }
    return m;
  }

  public void a(a parama, d paramd)
  {
    this.a.b(parama, (i)paramd);
  }

  public void a(a parama, e parame)
  {
    this.a.a(parama, (i)parame);
  }

  protected boolean a()
  {
    TLog.v("XGTcpRecvPacks", "@@ buildRecvPacket()");
    if (this.e == null)
    {
      this.e = new g();
      ((g)this.e).a(this.d);
    }
    return true;
  }

  protected boolean b()
  {
    TLog.v("XGService", "@@ buildSendPacket()");
    if (this.f == null)
    {
      ArrayList localArrayList = this.a.a(this, 1);
      if (!localArrayList.isEmpty())
        this.f = ((e)localArrayList.get(0));
      if (this.f != null)
        ((h)this.f).a(this.d);
    }
    return this.f != null;
  }

  public void c()
  {
    monitorenter;
    try
    {
      TLog.v("XGService", "@@ cancel()");
      this.l = true;
      g();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean d()
  {
    return this.i == 1;
  }

  public com.tencent.android.tpush.service.channel.a e()
  {
    int m = 1;
    Object[] arrayOfObject;
    if (this.k == null)
    {
      arrayOfObject = new Object[6];
      arrayOfObject[0] = Integer.valueOf(0);
      arrayOfObject[m] = this.g;
      arrayOfObject[2] = Integer.valueOf(m);
      arrayOfObject[3] = Integer.valueOf(this.h);
      arrayOfObject[4] = Integer.valueOf(2);
      if (this.i != m)
        break label85;
    }
    while (true)
    {
      arrayOfObject[5] = Boolean.valueOf(m);
      this.k = new com.tencent.android.tpush.service.channel.a(arrayOfObject);
      return this.k;
      label85: int n = 0;
    }
  }

  protected boolean f()
  {
    return System.currentTimeMillis() > this.j;
  }

  public void g()
  {
    TLog.v("XGService", "@@ wakeup()");
    try
    {
      if ((this.c != null) && (this.c.isOpen()))
        this.c.wakeup();
      return;
    }
    catch (Exception localException)
    {
      TLog.e("TpnsClient", ">>selector wakeup err", localException);
    }
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: ldc 67
    //   2: ldc_w 273
    //   5: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   8: aload_0
    //   9: invokestatic 278	java/nio/channels/Selector:open	()Ljava/nio/channels/Selector;
    //   12: putfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   15: aload_0
    //   16: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   19: iconst_0
    //   20: invokevirtual 282	java/nio/channels/SocketChannel:configureBlocking	(Z)Ljava/nio/channels/SelectableChannel;
    //   23: pop
    //   24: sipush 24576
    //   27: invokestatic 288	java/nio/ByteBuffer:allocateDirect	(I)Ljava/nio/ByteBuffer;
    //   30: astore 25
    //   32: new 290	com/tencent/android/tpush/service/channel/c/a
    //   35: dup
    //   36: sipush 24576
    //   39: iconst_0
    //   40: invokespecial 293	com/tencent/android/tpush/service/channel/c/a:<init>	(IZ)V
    //   43: astore 26
    //   45: sipush 24576
    //   48: newarray byte
    //   50: astore 27
    //   52: sipush 4096
    //   55: invokestatic 288	java/nio/ByteBuffer:allocateDirect	(I)Ljava/nio/ByteBuffer;
    //   58: astore 28
    //   60: new 290	com/tencent/android/tpush/service/channel/c/a
    //   63: dup
    //   64: iconst_m1
    //   65: iconst_0
    //   66: invokespecial 293	com/tencent/android/tpush/service/channel/c/a:<init>	(IZ)V
    //   69: astore 29
    //   71: sipush 4096
    //   74: newarray byte
    //   76: astore 30
    //   78: aload 28
    //   80: invokevirtual 297	java/nio/ByteBuffer:flip	()Ljava/nio/Buffer;
    //   83: pop
    //   84: lconst_0
    //   85: lstore 32
    //   87: aload_0
    //   88: getfield 59	com/tencent/android/tpush/service/channel/a/a:l	Z
    //   91: ifne +107 -> 198
    //   94: ldc 67
    //   96: new 69	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   103: ldc_w 299
    //   106: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: lload 32
    //   111: invokevirtual 302	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   114: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: invokestatic 135	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   120: aload_0
    //   121: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   124: aload_0
    //   125: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   128: iconst_1
    //   129: invokevirtual 306	java/nio/channels/SocketChannel:register	(Ljava/nio/channels/Selector;I)Ljava/nio/channels/SelectionKey;
    //   132: pop
    //   133: aload_0
    //   134: invokevirtual 169	com/tencent/android/tpush/service/channel/a/a:b	()Z
    //   137: ifne +19 -> 156
    //   140: aload 28
    //   142: invokevirtual 309	java/nio/ByteBuffer:remaining	()I
    //   145: ifgt +11 -> 156
    //   148: aload 29
    //   150: invokevirtual 311	com/tencent/android/tpush/service/channel/c/a:c	()I
    //   153: ifle +16 -> 169
    //   156: aload_0
    //   157: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   160: aload_0
    //   161: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   164: iconst_4
    //   165: invokevirtual 306	java/nio/channels/SocketChannel:register	(Ljava/nio/channels/Selector;I)Ljava/nio/channels/SelectionKey;
    //   168: pop
    //   169: aload_0
    //   170: invokevirtual 163	com/tencent/android/tpush/service/channel/a/a:f	()Z
    //   173: ifeq +92 -> 265
    //   176: aload_0
    //   177: getfield 47	com/tencent/android/tpush/service/channel/a/a:e	Lcom/tencent/android/tpush/service/channel/b/d;
    //   180: ifnonnull +85 -> 265
    //   183: aload_0
    //   184: getfield 49	com/tencent/android/tpush/service/channel/a/a:f	Lcom/tencent/android/tpush/service/channel/b/e;
    //   187: ifnonnull +78 -> 265
    //   190: ldc 67
    //   192: ldc_w 313
    //   195: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   198: aload_0
    //   199: monitorenter
    //   200: aload_0
    //   201: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   204: invokevirtual 316	java/nio/channels/Selector:close	()V
    //   207: aload_0
    //   208: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   211: invokevirtual 317	java/nio/channels/SocketChannel:close	()V
    //   214: aload_0
    //   215: monitorexit
    //   216: iconst_0
    //   217: ifeq +950 -> 1167
    //   220: ldc 67
    //   222: new 69	java/lang/StringBuilder
    //   225: dup
    //   226: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   229: ldc_w 319
    //   232: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: aconst_null
    //   236: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   239: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   245: aload_0
    //   246: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   249: aload_0
    //   250: aconst_null
    //   251: invokeinterface 322 3 0
    //   256: ldc 67
    //   258: ldc_w 324
    //   261: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   264: return
    //   265: aload_0
    //   266: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   269: lload 32
    //   271: invokevirtual 328	java/nio/channels/Selector:select	(J)I
    //   274: istore 38
    //   276: lconst_0
    //   277: lstore 39
    //   279: aload_0
    //   280: getfield 49	com/tencent/android/tpush/service/channel/a/a:f	Lcom/tencent/android/tpush/service/channel/b/e;
    //   283: ifnull +127 -> 410
    //   286: aload_0
    //   287: getfield 49	com/tencent/android/tpush/service/channel/a/a:f	Lcom/tencent/android/tpush/service/channel/b/e;
    //   290: invokeinterface 330 1 0
    //   295: lstore 57
    //   297: lload 57
    //   299: lconst_0
    //   300: lcmp
    //   301: ifgt +97 -> 398
    //   304: new 271	java/util/concurrent/TimeoutException
    //   307: dup
    //   308: ldc_w 332
    //   311: invokespecial 333	java/util/concurrent/TimeoutException:<init>	(Ljava/lang/String;)V
    //   314: athrow
    //   315: astore 20
    //   317: new 335	com/tencent/android/tpush/service/channel/exception/ChannelException
    //   320: dup
    //   321: sipush 10103
    //   324: ldc_w 337
    //   327: aload 20
    //   329: invokespecial 340	com/tencent/android/tpush/service/channel/exception/ChannelException:<init>	(ILjava/lang/String;Ljava/lang/Throwable;)V
    //   332: astore 21
    //   334: aload_0
    //   335: monitorenter
    //   336: aload_0
    //   337: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   340: invokevirtual 316	java/nio/channels/Selector:close	()V
    //   343: aload_0
    //   344: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   347: invokevirtual 317	java/nio/channels/SocketChannel:close	()V
    //   350: aload_0
    //   351: monitorexit
    //   352: aload 21
    //   354: ifnull +900 -> 1254
    //   357: ldc 67
    //   359: new 69	java/lang/StringBuilder
    //   362: dup
    //   363: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   366: ldc_w 319
    //   369: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: aload 21
    //   374: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   377: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   380: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   383: aload_0
    //   384: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   387: aload_0
    //   388: aload 21
    //   390: invokeinterface 322 3 0
    //   395: goto -139 -> 256
    //   398: lload 57
    //   400: lload 39
    //   402: lcmp
    //   403: ifge +126 -> 529
    //   406: lload 57
    //   408: lstore 39
    //   410: aload_0
    //   411: getfield 47	com/tencent/android/tpush/service/channel/a/a:e	Lcom/tencent/android/tpush/service/channel/b/d;
    //   414: ifnull +1471 -> 1885
    //   417: aload_0
    //   418: getfield 47	com/tencent/android/tpush/service/channel/a/a:e	Lcom/tencent/android/tpush/service/channel/b/d;
    //   421: invokeinterface 341 1 0
    //   426: lstore 55
    //   428: lload 55
    //   430: lconst_0
    //   431: lcmp
    //   432: ifgt +104 -> 536
    //   435: new 271	java/util/concurrent/TimeoutException
    //   438: dup
    //   439: ldc_w 343
    //   442: invokespecial 333	java/util/concurrent/TimeoutException:<init>	(Ljava/lang/String;)V
    //   445: athrow
    //   446: astore 16
    //   448: new 335	com/tencent/android/tpush/service/channel/exception/ChannelException
    //   451: dup
    //   452: sipush 10104
    //   455: ldc_w 345
    //   458: aload 16
    //   460: invokespecial 340	com/tencent/android/tpush/service/channel/exception/ChannelException:<init>	(ILjava/lang/String;Ljava/lang/Throwable;)V
    //   463: astore 17
    //   465: aload_0
    //   466: monitorenter
    //   467: aload_0
    //   468: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   471: invokevirtual 316	java/nio/channels/Selector:close	()V
    //   474: aload_0
    //   475: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   478: invokevirtual 317	java/nio/channels/SocketChannel:close	()V
    //   481: aload_0
    //   482: monitorexit
    //   483: aload 17
    //   485: ifnull +856 -> 1341
    //   488: ldc 67
    //   490: new 69	java/lang/StringBuilder
    //   493: dup
    //   494: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   497: ldc_w 319
    //   500: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   503: aload 17
    //   505: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   508: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   511: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   514: aload_0
    //   515: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   518: aload_0
    //   519: aload 17
    //   521: invokeinterface 322 3 0
    //   526: goto -270 -> 256
    //   529: lload 39
    //   531: lstore 57
    //   533: goto -127 -> 406
    //   536: lload 55
    //   538: lload 39
    //   540: lcmp
    //   541: ifge +232 -> 773
    //   544: lload 55
    //   546: lstore 32
    //   548: iconst_0
    //   549: istore 41
    //   551: iconst_0
    //   552: istore 42
    //   554: aload_0
    //   555: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   558: invokevirtual 349	java/nio/channels/Selector:selectedKeys	()Ljava/util/Set;
    //   561: invokeinterface 355 1 0
    //   566: astore 43
    //   568: ldc 67
    //   570: new 69	java/lang/StringBuilder
    //   573: dup
    //   574: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   577: ldc_w 357
    //   580: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   583: aload_0
    //   584: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   587: invokevirtual 349	java/nio/channels/Selector:selectedKeys	()Ljava/util/Set;
    //   590: invokeinterface 360 1 0
    //   595: invokevirtual 133	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   598: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   601: invokestatic 135	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   604: aload 43
    //   606: invokeinterface 365 1 0
    //   611: ifeq +384 -> 995
    //   614: aload 43
    //   616: invokeinterface 369 1 0
    //   621: checkcast 371	java/nio/channels/SelectionKey
    //   624: astore 44
    //   626: aload 44
    //   628: invokevirtual 374	java/nio/channels/SelectionKey:isReadable	()Z
    //   631: ifeq +212 -> 843
    //   634: ldc 67
    //   636: ldc_w 376
    //   639: invokestatic 91	com/tencent/android/tpush/logging/TLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   642: aload 25
    //   644: invokevirtual 379	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   647: pop
    //   648: aload 25
    //   650: aload 26
    //   652: invokevirtual 381	com/tencent/android/tpush/service/channel/c/a:d	()I
    //   655: invokevirtual 385	java/nio/ByteBuffer:limit	(I)Ljava/nio/Buffer;
    //   658: pop
    //   659: aload_0
    //   660: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   663: aload 25
    //   665: invokevirtual 389	java/nio/ByteBuffer:slice	()Ljava/nio/ByteBuffer;
    //   668: invokevirtual 393	java/nio/channels/SocketChannel:read	(Ljava/nio/ByteBuffer;)I
    //   671: istore 41
    //   673: iload 41
    //   675: iconst_m1
    //   676: if_icmpne +104 -> 780
    //   679: new 265	java/io/IOException
    //   682: dup
    //   683: ldc_w 395
    //   686: invokespecial 396	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   689: athrow
    //   690: astore 12
    //   692: new 335	com/tencent/android/tpush/service/channel/exception/ChannelException
    //   695: dup
    //   696: sipush 10108
    //   699: ldc_w 398
    //   702: aload 12
    //   704: invokespecial 340	com/tencent/android/tpush/service/channel/exception/ChannelException:<init>	(ILjava/lang/String;Ljava/lang/Throwable;)V
    //   707: astore 13
    //   709: aload_0
    //   710: monitorenter
    //   711: aload_0
    //   712: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   715: invokevirtual 316	java/nio/channels/Selector:close	()V
    //   718: aload_0
    //   719: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   722: invokevirtual 317	java/nio/channels/SocketChannel:close	()V
    //   725: aload_0
    //   726: monitorexit
    //   727: aload 13
    //   729: ifnull +699 -> 1428
    //   732: ldc 67
    //   734: new 69	java/lang/StringBuilder
    //   737: dup
    //   738: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   741: ldc_w 319
    //   744: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   747: aload 13
    //   749: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   752: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   755: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   758: aload_0
    //   759: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   762: aload_0
    //   763: aload 13
    //   765: invokeinterface 322 3 0
    //   770: goto -514 -> 256
    //   773: lload 39
    //   775: lstore 55
    //   777: goto -233 -> 544
    //   780: ldc 121
    //   782: new 69	java/lang/StringBuilder
    //   785: dup
    //   786: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   789: ldc_w 400
    //   792: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   795: aload 26
    //   797: invokevirtual 311	com/tencent/android/tpush/service/channel/c/a:c	()I
    //   800: invokevirtual 133	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   803: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   806: invokestatic 91	com/tencent/android/tpush/logging/TLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   809: aload 25
    //   811: aload 27
    //   813: iconst_0
    //   814: iload 41
    //   816: invokevirtual 403	java/nio/ByteBuffer:get	([BII)Ljava/nio/ByteBuffer;
    //   819: pop
    //   820: aload 26
    //   822: invokevirtual 406	com/tencent/android/tpush/service/channel/c/a:a	()Ljava/io/OutputStream;
    //   825: aload 27
    //   827: iconst_0
    //   828: iload 41
    //   830: invokevirtual 412	java/io/OutputStream:write	([BII)V
    //   833: aload_0
    //   834: aload 26
    //   836: invokevirtual 415	com/tencent/android/tpush/service/channel/c/a:b	()Ljava/io/InputStream;
    //   839: invokevirtual 416	com/tencent/android/tpush/service/channel/a/a:a	(Ljava/io/InputStream;)I
    //   842: pop
    //   843: aload 44
    //   845: invokevirtual 419	java/nio/channels/SelectionKey:isWritable	()Z
    //   848: ifeq +1030 -> 1878
    //   851: ldc 67
    //   853: ldc_w 421
    //   856: invokestatic 91	com/tencent/android/tpush/logging/TLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   859: aload_0
    //   860: aload 29
    //   862: invokevirtual 406	com/tencent/android/tpush/service/channel/c/a:a	()Ljava/io/OutputStream;
    //   865: invokevirtual 422	com/tencent/android/tpush/service/channel/a/a:a	(Ljava/io/OutputStream;)I
    //   868: pop
    //   869: aload 29
    //   871: invokevirtual 311	com/tencent/android/tpush/service/channel/c/a:c	()I
    //   874: ifle +1004 -> 1878
    //   877: ldc 159
    //   879: new 69	java/lang/StringBuilder
    //   882: dup
    //   883: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   886: ldc_w 424
    //   889: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   892: aload 29
    //   894: invokevirtual 311	com/tencent/android/tpush/service/channel/c/a:c	()I
    //   897: invokevirtual 133	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   900: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   903: invokestatic 135	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   906: aload 28
    //   908: invokevirtual 427	java/nio/ByteBuffer:compact	()Ljava/nio/ByteBuffer;
    //   911: pop
    //   912: aload 28
    //   914: invokevirtual 309	java/nio/ByteBuffer:remaining	()I
    //   917: aload 29
    //   919: invokevirtual 311	com/tencent/android/tpush/service/channel/c/a:c	()I
    //   922: if_icmpge +63 -> 985
    //   925: aload 28
    //   927: invokevirtual 309	java/nio/ByteBuffer:remaining	()I
    //   930: istore 48
    //   932: aload 28
    //   934: aload 30
    //   936: iconst_0
    //   937: aload 29
    //   939: invokevirtual 415	com/tencent/android/tpush/service/channel/c/a:b	()Ljava/io/InputStream;
    //   942: aload 30
    //   944: iconst_0
    //   945: iload 48
    //   947: invokevirtual 430	java/io/InputStream:read	([BII)I
    //   950: invokevirtual 433	java/nio/ByteBuffer:put	([BII)Ljava/nio/ByteBuffer;
    //   953: pop
    //   954: aload 28
    //   956: invokevirtual 297	java/nio/ByteBuffer:flip	()Ljava/nio/Buffer;
    //   959: pop
    //   960: aload_0
    //   961: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   964: aload 28
    //   966: invokevirtual 435	java/nio/channels/SocketChannel:write	(Ljava/nio/ByteBuffer;)I
    //   969: istore 45
    //   971: aload 43
    //   973: invokeinterface 438 1 0
    //   978: iload 45
    //   980: istore 42
    //   982: goto -378 -> 604
    //   985: aload 29
    //   987: invokevirtual 311	com/tencent/android/tpush/service/channel/c/a:c	()I
    //   990: istore 48
    //   992: goto -60 -> 932
    //   995: ldc 67
    //   997: new 69	java/lang/StringBuilder
    //   1000: dup
    //   1001: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   1004: ldc_w 440
    //   1007: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1010: iload 42
    //   1012: invokevirtual 133	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1015: ldc_w 442
    //   1018: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1021: iload 41
    //   1023: invokevirtual 133	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1026: ldc_w 444
    //   1029: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1032: iload 38
    //   1034: invokevirtual 133	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1037: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1040: invokestatic 135	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1043: goto -956 -> 87
    //   1046: astore 8
    //   1048: new 335	com/tencent/android/tpush/service/channel/exception/ChannelException
    //   1051: dup
    //   1052: sipush 10105
    //   1055: ldc_w 446
    //   1058: aload 8
    //   1060: invokespecial 340	com/tencent/android/tpush/service/channel/exception/ChannelException:<init>	(ILjava/lang/String;Ljava/lang/Throwable;)V
    //   1063: astore 9
    //   1065: aload_0
    //   1066: monitorenter
    //   1067: aload_0
    //   1068: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   1071: invokevirtual 316	java/nio/channels/Selector:close	()V
    //   1074: aload_0
    //   1075: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   1078: invokevirtual 317	java/nio/channels/SocketChannel:close	()V
    //   1081: aload_0
    //   1082: monitorexit
    //   1083: aload 9
    //   1085: ifnull +430 -> 1515
    //   1088: ldc 67
    //   1090: new 69	java/lang/StringBuilder
    //   1093: dup
    //   1094: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   1097: ldc_w 319
    //   1100: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1103: aload 9
    //   1105: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1108: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1111: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1114: aload_0
    //   1115: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1118: aload_0
    //   1119: aload 9
    //   1121: invokeinterface 322 3 0
    //   1126: goto -870 -> 256
    //   1129: astore 35
    //   1131: aload_0
    //   1132: monitorexit
    //   1133: aload 35
    //   1135: athrow
    //   1136: astore 34
    //   1138: ldc 67
    //   1140: new 69	java/lang/StringBuilder
    //   1143: dup
    //   1144: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   1147: ldc_w 448
    //   1150: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1153: aload 34
    //   1155: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1158: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1161: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1164: goto -948 -> 216
    //   1167: aload_0
    //   1168: getfield 59	com/tencent/android/tpush/service/channel/a/a:l	Z
    //   1171: ifeq +24 -> 1195
    //   1174: ldc 67
    //   1176: ldc_w 450
    //   1179: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1182: aload_0
    //   1183: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1186: aload_0
    //   1187: invokeinterface 453 2 0
    //   1192: goto -936 -> 256
    //   1195: ldc 67
    //   1197: ldc_w 455
    //   1200: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1203: aload_0
    //   1204: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1207: aload_0
    //   1208: invokeinterface 457 2 0
    //   1213: goto -957 -> 256
    //   1216: astore 23
    //   1218: aload_0
    //   1219: monitorexit
    //   1220: aload 23
    //   1222: athrow
    //   1223: astore 22
    //   1225: ldc 67
    //   1227: new 69	java/lang/StringBuilder
    //   1230: dup
    //   1231: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   1234: ldc_w 448
    //   1237: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1240: aload 22
    //   1242: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1245: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1248: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1251: goto -899 -> 352
    //   1254: aload_0
    //   1255: getfield 59	com/tencent/android/tpush/service/channel/a/a:l	Z
    //   1258: ifeq +24 -> 1282
    //   1261: ldc 67
    //   1263: ldc_w 450
    //   1266: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1269: aload_0
    //   1270: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1273: aload_0
    //   1274: invokeinterface 453 2 0
    //   1279: goto -1023 -> 256
    //   1282: ldc 67
    //   1284: ldc_w 455
    //   1287: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1290: aload_0
    //   1291: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1294: aload_0
    //   1295: invokeinterface 457 2 0
    //   1300: goto -1044 -> 256
    //   1303: astore 19
    //   1305: aload_0
    //   1306: monitorexit
    //   1307: aload 19
    //   1309: athrow
    //   1310: astore 18
    //   1312: ldc 67
    //   1314: new 69	java/lang/StringBuilder
    //   1317: dup
    //   1318: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   1321: ldc_w 448
    //   1324: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1327: aload 18
    //   1329: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1332: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1335: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1338: goto -855 -> 483
    //   1341: aload_0
    //   1342: getfield 59	com/tencent/android/tpush/service/channel/a/a:l	Z
    //   1345: ifeq +24 -> 1369
    //   1348: ldc 67
    //   1350: ldc_w 450
    //   1353: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1356: aload_0
    //   1357: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1360: aload_0
    //   1361: invokeinterface 453 2 0
    //   1366: goto -1110 -> 256
    //   1369: ldc 67
    //   1371: ldc_w 455
    //   1374: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1377: aload_0
    //   1378: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1381: aload_0
    //   1382: invokeinterface 457 2 0
    //   1387: goto -1131 -> 256
    //   1390: astore 15
    //   1392: aload_0
    //   1393: monitorexit
    //   1394: aload 15
    //   1396: athrow
    //   1397: astore 14
    //   1399: ldc 67
    //   1401: new 69	java/lang/StringBuilder
    //   1404: dup
    //   1405: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   1408: ldc_w 448
    //   1411: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1414: aload 14
    //   1416: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1419: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1422: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1425: goto -698 -> 727
    //   1428: aload_0
    //   1429: getfield 59	com/tencent/android/tpush/service/channel/a/a:l	Z
    //   1432: ifeq +24 -> 1456
    //   1435: ldc 67
    //   1437: ldc_w 450
    //   1440: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1443: aload_0
    //   1444: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1447: aload_0
    //   1448: invokeinterface 453 2 0
    //   1453: goto -1197 -> 256
    //   1456: ldc 67
    //   1458: ldc_w 455
    //   1461: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1464: aload_0
    //   1465: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1468: aload_0
    //   1469: invokeinterface 457 2 0
    //   1474: goto -1218 -> 256
    //   1477: astore 11
    //   1479: aload_0
    //   1480: monitorexit
    //   1481: aload 11
    //   1483: athrow
    //   1484: astore 10
    //   1486: ldc 67
    //   1488: new 69	java/lang/StringBuilder
    //   1491: dup
    //   1492: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   1495: ldc_w 448
    //   1498: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1501: aload 10
    //   1503: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1506: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1509: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1512: goto -429 -> 1083
    //   1515: aload_0
    //   1516: getfield 59	com/tencent/android/tpush/service/channel/a/a:l	Z
    //   1519: ifeq +24 -> 1543
    //   1522: ldc 67
    //   1524: ldc_w 450
    //   1527: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1530: aload_0
    //   1531: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1534: aload_0
    //   1535: invokeinterface 453 2 0
    //   1540: goto -1284 -> 256
    //   1543: ldc 67
    //   1545: ldc_w 455
    //   1548: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1551: aload_0
    //   1552: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1555: aload_0
    //   1556: invokeinterface 457 2 0
    //   1561: goto -1305 -> 256
    //   1564: astore 4
    //   1566: new 335	com/tencent/android/tpush/service/channel/exception/ChannelException
    //   1569: dup
    //   1570: sipush 10109
    //   1573: ldc_w 459
    //   1576: aload 4
    //   1578: invokespecial 340	com/tencent/android/tpush/service/channel/exception/ChannelException:<init>	(ILjava/lang/String;Ljava/lang/Throwable;)V
    //   1581: astore 5
    //   1583: aload_0
    //   1584: monitorenter
    //   1585: aload_0
    //   1586: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   1589: invokevirtual 316	java/nio/channels/Selector:close	()V
    //   1592: aload_0
    //   1593: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   1596: invokevirtual 317	java/nio/channels/SocketChannel:close	()V
    //   1599: aload_0
    //   1600: monitorexit
    //   1601: aload 5
    //   1603: ifnull +82 -> 1685
    //   1606: ldc 67
    //   1608: new 69	java/lang/StringBuilder
    //   1611: dup
    //   1612: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   1615: ldc_w 319
    //   1618: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1621: aload 5
    //   1623: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1626: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1629: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1632: aload_0
    //   1633: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1636: aload_0
    //   1637: aload 5
    //   1639: invokeinterface 322 3 0
    //   1644: goto -1388 -> 256
    //   1647: astore 7
    //   1649: aload_0
    //   1650: monitorexit
    //   1651: aload 7
    //   1653: athrow
    //   1654: astore 6
    //   1656: ldc 67
    //   1658: new 69	java/lang/StringBuilder
    //   1661: dup
    //   1662: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   1665: ldc_w 448
    //   1668: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1671: aload 6
    //   1673: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1676: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1679: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1682: goto -81 -> 1601
    //   1685: aload_0
    //   1686: getfield 59	com/tencent/android/tpush/service/channel/a/a:l	Z
    //   1689: ifeq +24 -> 1713
    //   1692: ldc 67
    //   1694: ldc_w 450
    //   1697: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1700: aload_0
    //   1701: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1704: aload_0
    //   1705: invokeinterface 453 2 0
    //   1710: goto -1454 -> 256
    //   1713: ldc 67
    //   1715: ldc_w 455
    //   1718: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1721: aload_0
    //   1722: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1725: aload_0
    //   1726: invokeinterface 457 2 0
    //   1731: goto -1475 -> 256
    //   1734: astore_1
    //   1735: aload_0
    //   1736: monitorenter
    //   1737: aload_0
    //   1738: getfield 38	com/tencent/android/tpush/service/channel/a/a:c	Ljava/nio/channels/Selector;
    //   1741: invokevirtual 316	java/nio/channels/Selector:close	()V
    //   1744: aload_0
    //   1745: getfield 36	com/tencent/android/tpush/service/channel/a/a:b	Ljava/nio/channels/SocketChannel;
    //   1748: invokevirtual 317	java/nio/channels/SocketChannel:close	()V
    //   1751: aload_0
    //   1752: monitorexit
    //   1753: iconst_0
    //   1754: ifeq +75 -> 1829
    //   1757: ldc 67
    //   1759: new 69	java/lang/StringBuilder
    //   1762: dup
    //   1763: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   1766: ldc_w 319
    //   1769: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1772: aconst_null
    //   1773: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1776: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1779: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1782: aload_0
    //   1783: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1786: aload_0
    //   1787: aconst_null
    //   1788: invokeinterface 322 3 0
    //   1793: aload_1
    //   1794: athrow
    //   1795: astore_3
    //   1796: aload_0
    //   1797: monitorexit
    //   1798: aload_3
    //   1799: athrow
    //   1800: astore_2
    //   1801: ldc 67
    //   1803: new 69	java/lang/StringBuilder
    //   1806: dup
    //   1807: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   1810: ldc_w 448
    //   1813: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1816: aload_2
    //   1817: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1820: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1823: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1826: goto -73 -> 1753
    //   1829: aload_0
    //   1830: getfield 59	com/tencent/android/tpush/service/channel/a/a:l	Z
    //   1833: ifeq +24 -> 1857
    //   1836: ldc 67
    //   1838: ldc_w 450
    //   1841: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1844: aload_0
    //   1845: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1848: aload_0
    //   1849: invokeinterface 453 2 0
    //   1854: goto -61 -> 1793
    //   1857: ldc 67
    //   1859: ldc_w 455
    //   1862: invokestatic 275	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1865: aload_0
    //   1866: getfield 113	com/tencent/android/tpush/service/channel/a/a:a	Lcom/tencent/android/tpush/service/channel/a/b;
    //   1869: aload_0
    //   1870: invokeinterface 457 2 0
    //   1875: goto -82 -> 1793
    //   1878: iload 42
    //   1880: istore 45
    //   1882: goto -911 -> 971
    //   1885: lload 39
    //   1887: lstore 32
    //   1889: goto -1341 -> 548
    //
    // Exception table:
    //   from	to	target	type
    //   8	84	315	java/io/IOException
    //   87	156	315	java/io/IOException
    //   156	169	315	java/io/IOException
    //   169	198	315	java/io/IOException
    //   265	276	315	java/io/IOException
    //   279	297	315	java/io/IOException
    //   304	315	315	java/io/IOException
    //   410	428	315	java/io/IOException
    //   435	446	315	java/io/IOException
    //   554	604	315	java/io/IOException
    //   604	673	315	java/io/IOException
    //   679	690	315	java/io/IOException
    //   780	843	315	java/io/IOException
    //   843	932	315	java/io/IOException
    //   932	971	315	java/io/IOException
    //   971	978	315	java/io/IOException
    //   985	992	315	java/io/IOException
    //   995	1043	315	java/io/IOException
    //   8	84	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   87	156	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   156	169	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   169	198	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   265	276	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   279	297	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   304	315	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   410	428	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   435	446	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   554	604	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   604	673	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   679	690	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   780	843	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   843	932	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   932	971	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   971	978	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   985	992	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   995	1043	446	com/tencent/android/tpush/service/channel/exception/InnerException
    //   8	84	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   87	156	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   156	169	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   169	198	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   265	276	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   279	297	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   304	315	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   410	428	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   435	446	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   554	604	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   604	673	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   679	690	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   780	843	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   843	932	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   932	971	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   971	978	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   985	992	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   995	1043	690	com/tencent/android/tpush/service/channel/exception/UnexpectedDataException
    //   8	84	1046	java/util/concurrent/TimeoutException
    //   87	156	1046	java/util/concurrent/TimeoutException
    //   156	169	1046	java/util/concurrent/TimeoutException
    //   169	198	1046	java/util/concurrent/TimeoutException
    //   265	276	1046	java/util/concurrent/TimeoutException
    //   279	297	1046	java/util/concurrent/TimeoutException
    //   304	315	1046	java/util/concurrent/TimeoutException
    //   410	428	1046	java/util/concurrent/TimeoutException
    //   435	446	1046	java/util/concurrent/TimeoutException
    //   554	604	1046	java/util/concurrent/TimeoutException
    //   604	673	1046	java/util/concurrent/TimeoutException
    //   679	690	1046	java/util/concurrent/TimeoutException
    //   780	843	1046	java/util/concurrent/TimeoutException
    //   843	932	1046	java/util/concurrent/TimeoutException
    //   932	971	1046	java/util/concurrent/TimeoutException
    //   971	978	1046	java/util/concurrent/TimeoutException
    //   985	992	1046	java/util/concurrent/TimeoutException
    //   995	1043	1046	java/util/concurrent/TimeoutException
    //   200	216	1129	finally
    //   1131	1133	1129	finally
    //   198	200	1136	java/lang/Exception
    //   1133	1136	1136	java/lang/Exception
    //   336	352	1216	finally
    //   1218	1220	1216	finally
    //   334	336	1223	java/lang/Exception
    //   1220	1223	1223	java/lang/Exception
    //   467	483	1303	finally
    //   1305	1307	1303	finally
    //   465	467	1310	java/lang/Exception
    //   1307	1310	1310	java/lang/Exception
    //   711	727	1390	finally
    //   1392	1394	1390	finally
    //   709	711	1397	java/lang/Exception
    //   1394	1397	1397	java/lang/Exception
    //   1067	1083	1477	finally
    //   1479	1481	1477	finally
    //   1065	1067	1484	java/lang/Exception
    //   1481	1484	1484	java/lang/Exception
    //   8	84	1564	java/lang/Exception
    //   87	156	1564	java/lang/Exception
    //   156	169	1564	java/lang/Exception
    //   169	198	1564	java/lang/Exception
    //   265	276	1564	java/lang/Exception
    //   279	297	1564	java/lang/Exception
    //   304	315	1564	java/lang/Exception
    //   410	428	1564	java/lang/Exception
    //   435	446	1564	java/lang/Exception
    //   554	604	1564	java/lang/Exception
    //   604	673	1564	java/lang/Exception
    //   679	690	1564	java/lang/Exception
    //   780	843	1564	java/lang/Exception
    //   843	932	1564	java/lang/Exception
    //   932	971	1564	java/lang/Exception
    //   971	978	1564	java/lang/Exception
    //   985	992	1564	java/lang/Exception
    //   995	1043	1564	java/lang/Exception
    //   1585	1601	1647	finally
    //   1649	1651	1647	finally
    //   1583	1585	1654	java/lang/Exception
    //   1651	1654	1654	java/lang/Exception
    //   8	84	1734	finally
    //   87	156	1734	finally
    //   156	169	1734	finally
    //   169	198	1734	finally
    //   265	276	1734	finally
    //   279	297	1734	finally
    //   304	315	1734	finally
    //   317	334	1734	finally
    //   410	428	1734	finally
    //   435	446	1734	finally
    //   448	465	1734	finally
    //   554	604	1734	finally
    //   604	673	1734	finally
    //   679	690	1734	finally
    //   692	709	1734	finally
    //   780	843	1734	finally
    //   843	932	1734	finally
    //   932	971	1734	finally
    //   971	978	1734	finally
    //   985	992	1734	finally
    //   995	1043	1734	finally
    //   1048	1065	1734	finally
    //   1566	1583	1734	finally
    //   1737	1753	1795	finally
    //   1796	1798	1795	finally
    //   1735	1737	1800	java/lang/Exception
    //   1798	1800	1800	java/lang/Exception
  }

  public void start()
  {
    monitorenter;
    try
    {
      TLog.v("XGService", "@@ start()");
      super.start();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(getClass().getSimpleName()).append("(ip:").append(this.g).append(",port:").append(this.h).append(",protocol:");
    if (this.i == 1);
    for (String str = "http"; ; str = "tcp")
      return str + ")";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.a.a
 * JD-Core Version:    0.6.0
 */
package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.logging.TLog;

class k
  implements b
{
  k(i parami)
  {
  }

  public void a(StrategyItem paramStrategyItem)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("@@ onFail(item:");
    if (paramStrategyItem == null);
    for (String str = "null"; ; str = paramStrategyItem.toString())
    {
      TLog.v("XGHorse", str + ",status:" + i.e(this.a) + ")");
      if ((!t.j().c()) && (!h.j().c()) && (i.e(this.a) == 0))
      {
        i.a(this.a, 2);
        if (i.b(this.a) != null)
          i.b(this.a).a(10101, "create http channel fail!");
      }
      return;
    }
  }

  // ERROR //
  public void a(java.nio.channels.SocketChannel paramSocketChannel, StrategyItem paramStrategyItem)
  {
    // Byte code:
    //   0: ldc 29
    //   2: new 18	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 19	java/lang/StringBuilder:<init>	()V
    //   9: ldc 90
    //   11: invokevirtual 25	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: aload_1
    //   15: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   18: ldc 95
    //   20: invokevirtual 25	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: aload_2
    //   24: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   27: ldc 42
    //   29: invokevirtual 25	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokestatic 52	com/tencent/android/tpush/logging/TLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   38: iconst_0
    //   39: invokestatic 98	com/tencent/android/tpush/horse/i:a	(I)I
    //   42: pop
    //   43: invokestatic 58	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   46: invokevirtual 62	com/tencent/android/tpush/horse/t:c	()Z
    //   49: ifeq +12 -> 61
    //   52: aload_0
    //   53: getfield 12	com/tencent/android/tpush/horse/k:a	Lcom/tencent/android/tpush/horse/i;
    //   56: iconst_1
    //   57: invokestatic 71	com/tencent/android/tpush/horse/i:a	(Lcom/tencent/android/tpush/horse/i;I)I
    //   60: pop
    //   61: aload_0
    //   62: getfield 12	com/tencent/android/tpush/horse/k:a	Lcom/tencent/android/tpush/horse/i;
    //   65: invokestatic 102	com/tencent/android/tpush/horse/i:d	(Lcom/tencent/android/tpush/horse/i;)Ljava/lang/Object;
    //   68: astore 4
    //   70: aload 4
    //   72: monitorenter
    //   73: aload_0
    //   74: getfield 12	com/tencent/android/tpush/horse/k:a	Lcom/tencent/android/tpush/horse/i;
    //   77: invokestatic 37	com/tencent/android/tpush/horse/i:e	(Lcom/tencent/android/tpush/horse/i;)I
    //   80: iconst_1
    //   81: if_icmpne +39 -> 120
    //   84: ldc 29
    //   86: new 18	java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial 19	java/lang/StringBuilder:<init>	()V
    //   93: ldc 104
    //   95: invokevirtual 25	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: invokestatic 110	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   101: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   104: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   107: invokestatic 113	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   110: aload_0
    //   111: getfield 12	com/tencent/android/tpush/horse/k:a	Lcom/tencent/android/tpush/horse/i;
    //   114: invokestatic 102	com/tencent/android/tpush/horse/i:d	(Lcom/tencent/android/tpush/horse/i;)Ljava/lang/Object;
    //   117: invokevirtual 116	java/lang/Object:wait	()V
    //   120: aload 4
    //   122: monitorexit
    //   123: aload_1
    //   124: invokevirtual 121	java/nio/channels/SocketChannel:isConnected	()Z
    //   127: ifeq +127 -> 254
    //   130: invokestatic 58	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   133: invokevirtual 123	com/tencent/android/tpush/horse/t:d	()Z
    //   136: ifne +118 -> 254
    //   139: ldc 29
    //   141: new 18	java/lang/StringBuilder
    //   144: dup
    //   145: invokespecial 19	java/lang/StringBuilder:<init>	()V
    //   148: ldc 125
    //   150: invokevirtual 25	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: aload_2
    //   154: invokevirtual 85	com/tencent/android/tpush/horse/data/StrategyItem:toString	()Ljava/lang/String;
    //   157: invokevirtual 25	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokestatic 113	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: aload_0
    //   167: getfield 12	com/tencent/android/tpush/horse/k:a	Lcom/tencent/android/tpush/horse/i;
    //   170: invokestatic 75	com/tencent/android/tpush/horse/i:b	(Lcom/tencent/android/tpush/horse/i;)Lcom/tencent/android/tpush/horse/n;
    //   173: ifnull +73 -> 246
    //   176: aload_2
    //   177: invokevirtual 127	com/tencent/android/tpush/horse/data/StrategyItem:j	()Z
    //   180: ifeq +44 -> 224
    //   183: aload_1
    //   184: invokevirtual 130	java/nio/channels/SocketChannel:close	()V
    //   187: return
    //   188: astore 7
    //   190: ldc 29
    //   192: aload 7
    //   194: invokevirtual 131	java/lang/Exception:toString	()Ljava/lang/String;
    //   197: invokestatic 133	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   200: goto -80 -> 120
    //   203: astore 5
    //   205: aload 4
    //   207: monitorexit
    //   208: aload 5
    //   210: athrow
    //   211: astore 6
    //   213: ldc 29
    //   215: aload 6
    //   217: invokevirtual 131	java/lang/Exception:toString	()Ljava/lang/String;
    //   220: invokestatic 133	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   223: return
    //   224: aload_0
    //   225: getfield 12	com/tencent/android/tpush/horse/k:a	Lcom/tencent/android/tpush/horse/i;
    //   228: invokestatic 75	com/tencent/android/tpush/horse/i:b	(Lcom/tencent/android/tpush/horse/i;)Lcom/tencent/android/tpush/horse/n;
    //   231: aload_1
    //   232: aload_2
    //   233: invokeinterface 135 3 0
    //   238: invokestatic 140	com/tencent/android/tpush/service/i:e	()Landroid/content/Context;
    //   241: aload_2
    //   242: invokestatic 146	com/tencent/android/tpush/horse/Tools:sendCurStrategyItem	(Landroid/content/Context;Lcom/tencent/android/tpush/horse/data/StrategyItem;)V
    //   245: return
    //   246: ldc 29
    //   248: ldc 148
    //   250: invokestatic 133	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   253: return
    //   254: aload_1
    //   255: invokevirtual 121	java/nio/channels/SocketChannel:isConnected	()Z
    //   258: ifne -71 -> 187
    //   261: invokestatic 58	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   264: invokevirtual 123	com/tencent/android/tpush/horse/t:d	()Z
    //   267: ifne -80 -> 187
    //   270: ldc 29
    //   272: ldc 150
    //   274: invokestatic 113	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   277: aload_0
    //   278: getfield 12	com/tencent/android/tpush/horse/k:a	Lcom/tencent/android/tpush/horse/i;
    //   281: sipush 10101
    //   284: ldc 152
    //   286: invokestatic 155	com/tencent/android/tpush/horse/i:a	(Lcom/tencent/android/tpush/horse/i;ILjava/lang/String;)V
    //   289: return
    //
    // Exception table:
    //   from	to	target	type
    //   110	120	188	java/lang/Exception
    //   73	110	203	finally
    //   110	120	203	finally
    //   120	123	203	finally
    //   190	200	203	finally
    //   205	208	203	finally
    //   183	187	211	java/lang/Exception
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.k
 * JD-Core Version:    0.6.0
 */
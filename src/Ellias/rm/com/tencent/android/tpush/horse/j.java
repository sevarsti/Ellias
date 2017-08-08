package com.tencent.android.tpush.horse;

class j
  implements Runnable
{
  j(i parami)
  {
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: ldc 24
    //   4: ldc 26
    //   6: invokestatic 32	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   9: aload_0
    //   10: monitorenter
    //   11: ldc 24
    //   13: new 34	java/lang/StringBuilder
    //   16: dup
    //   17: invokespecial 35	java/lang/StringBuilder:<init>	()V
    //   20: ldc 37
    //   22: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: invokestatic 47	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   28: invokevirtual 50	com/tencent/android/tpush/horse/t:a	()I
    //   31: invokevirtual 53	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   34: ldc 55
    //   36: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: invokestatic 60	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   42: invokevirtual 61	com/tencent/android/tpush/horse/h:a	()I
    //   45: invokevirtual 53	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   48: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: invokestatic 68	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   54: invokestatic 47	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   57: invokevirtual 72	com/tencent/android/tpush/horse/t:c	()Z
    //   60: ifne +321 -> 381
    //   63: invokestatic 60	com/tencent/android/tpush/horse/h:j	()Lcom/tencent/android/tpush/horse/h;
    //   66: invokevirtual 73	com/tencent/android/tpush/horse/h:c	()Z
    //   69: ifne +312 -> 381
    //   72: invokestatic 78	com/tencent/android/tpush/service/i:e	()Landroid/content/Context;
    //   75: invokestatic 84	com/tencent/android/tpush/service/c/c:f	(Landroid/content/Context;)Ljava/lang/String;
    //   78: astore_3
    //   79: invokestatic 78	com/tencent/android/tpush/service/i:e	()Landroid/content/Context;
    //   82: aload_3
    //   83: invokestatic 90	com/tencent/android/tpush/service/cache/CacheManager:getOptStrategyList	(Landroid/content/Context;Ljava/lang/String;)Lcom/tencent/android/tpush/horse/data/OptStrategyList;
    //   86: astore 7
    //   88: aload 7
    //   90: invokevirtual 95	com/tencent/android/tpush/horse/data/OptStrategyList:e	()Lcom/tencent/android/tpush/horse/data/StrategyItem;
    //   93: astore_1
    //   94: aload_1
    //   95: ifnull +14 -> 109
    //   98: aload 7
    //   100: invokevirtual 99	com/tencent/android/tpush/horse/data/OptStrategyList:g	()J
    //   103: invokestatic 104	com/tencent/android/tpush/horse/f:a	(J)Z
    //   106: ifeq +14 -> 120
    //   109: aload_0
    //   110: getfield 12	com/tencent/android/tpush/horse/j:a	Lcom/tencent/android/tpush/horse/i;
    //   113: aload_3
    //   114: invokestatic 109	com/tencent/android/tpush/horse/i:a	(Lcom/tencent/android/tpush/horse/i;Ljava/lang/String;)V
    //   117: aload_0
    //   118: monitorexit
    //   119: return
    //   120: aload_0
    //   121: getfield 12	com/tencent/android/tpush/horse/j:a	Lcom/tencent/android/tpush/horse/i;
    //   124: invokestatic 114	java/lang/System:currentTimeMillis	()J
    //   127: invokestatic 117	com/tencent/android/tpush/horse/i:a	(Lcom/tencent/android/tpush/horse/i;J)J
    //   130: pop2
    //   131: aload_1
    //   132: invokevirtual 122	com/tencent/android/tpush/horse/data/StrategyItem:d	()I
    //   135: ifne +75 -> 210
    //   138: ldc 24
    //   140: ldc 124
    //   142: invokestatic 68	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   145: aload_0
    //   146: getfield 12	com/tencent/android/tpush/horse/j:a	Lcom/tencent/android/tpush/horse/i;
    //   149: iconst_1
    //   150: invokestatic 127	com/tencent/android/tpush/horse/i:a	(Lcom/tencent/android/tpush/horse/i;Z)Z
    //   153: pop
    //   154: new 129	java/util/ArrayList
    //   157: dup
    //   158: invokespecial 130	java/util/ArrayList:<init>	()V
    //   161: astore 12
    //   163: aload_1
    //   164: iconst_0
    //   165: invokevirtual 133	com/tencent/android/tpush/horse/data/StrategyItem:a	(I)V
    //   168: aload 12
    //   170: aload_1
    //   171: invokevirtual 137	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   174: pop
    //   175: invokestatic 47	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   178: aload_0
    //   179: getfield 12	com/tencent/android/tpush/horse/j:a	Lcom/tencent/android/tpush/horse/i;
    //   182: invokestatic 140	com/tencent/android/tpush/horse/i:a	(Lcom/tencent/android/tpush/horse/i;)Lcom/tencent/android/tpush/horse/b;
    //   185: invokevirtual 143	com/tencent/android/tpush/horse/t:a	(Lcom/tencent/android/tpush/horse/b;)V
    //   188: invokestatic 47	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   191: aload 12
    //   193: invokevirtual 146	com/tencent/android/tpush/horse/t:a	(Ljava/util/List;)V
    //   196: invokestatic 47	com/tencent/android/tpush/horse/t:j	()Lcom/tencent/android/tpush/horse/t;
    //   199: invokevirtual 149	com/tencent/android/tpush/horse/t:h	()V
    //   202: aload_0
    //   203: monitorexit
    //   204: return
    //   205: astore_2
    //   206: aload_0
    //   207: monitorexit
    //   208: aload_2
    //   209: athrow
    //   210: ldc 24
    //   212: ldc 151
    //   214: invokestatic 68	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   217: new 153	com/tencent/android/tpush/horse/q
    //   220: dup
    //   221: invokespecial 154	com/tencent/android/tpush/horse/q:<init>	()V
    //   224: astore 10
    //   226: aload 10
    //   228: aload_1
    //   229: invokevirtual 157	com/tencent/android/tpush/horse/q:a	(Lcom/tencent/android/tpush/horse/data/StrategyItem;)V
    //   232: aload 10
    //   234: invokevirtual 160	com/tencent/android/tpush/horse/q:a	()Ljava/nio/channels/SocketChannel;
    //   237: invokevirtual 165	java/nio/channels/SocketChannel:isConnected	()Z
    //   240: ifeq -38 -> 202
    //   243: aload_0
    //   244: getfield 12	com/tencent/android/tpush/horse/j:a	Lcom/tencent/android/tpush/horse/i;
    //   247: invokestatic 169	com/tencent/android/tpush/horse/i:b	(Lcom/tencent/android/tpush/horse/i;)Lcom/tencent/android/tpush/horse/n;
    //   250: ifnull -48 -> 202
    //   253: iconst_0
    //   254: bipush 103
    //   256: iconst_5
    //   257: invokestatic 114	java/lang/System:currentTimeMillis	()J
    //   260: aload_0
    //   261: getfield 12	com/tencent/android/tpush/horse/j:a	Lcom/tencent/android/tpush/horse/i;
    //   264: invokestatic 172	com/tencent/android/tpush/horse/i:c	(Lcom/tencent/android/tpush/horse/i;)J
    //   267: lsub
    //   268: aload_1
    //   269: aconst_null
    //   270: invokestatic 177	com/tencent/android/tpush/horse/g:a	(IIIJLcom/tencent/android/tpush/horse/data/StrategyItem;Lcom/tencent/android/tpush/horse/data/StrategyItem;)V
    //   273: aload_0
    //   274: getfield 12	com/tencent/android/tpush/horse/j:a	Lcom/tencent/android/tpush/horse/i;
    //   277: invokestatic 169	com/tencent/android/tpush/horse/i:b	(Lcom/tencent/android/tpush/horse/i;)Lcom/tencent/android/tpush/horse/n;
    //   280: aload 10
    //   282: invokevirtual 160	com/tencent/android/tpush/horse/q:a	()Ljava/nio/channels/SocketChannel;
    //   285: aload_1
    //   286: invokeinterface 182 3 0
    //   291: invokestatic 78	com/tencent/android/tpush/service/i:e	()Landroid/content/Context;
    //   294: aload_1
    //   295: invokestatic 188	com/tencent/android/tpush/horse/Tools:sendCurStrategyItem	(Landroid/content/Context;Lcom/tencent/android/tpush/horse/data/StrategyItem;)V
    //   298: aload_0
    //   299: monitorexit
    //   300: return
    //   301: astore 6
    //   303: ldc 24
    //   305: aload 6
    //   307: invokevirtual 189	com/tencent/android/tpush/service/channel/exception/NullReturnException:toString	()Ljava/lang/String;
    //   310: invokestatic 32	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   313: aload_0
    //   314: getfield 12	com/tencent/android/tpush/horse/j:a	Lcom/tencent/android/tpush/horse/i;
    //   317: aload_3
    //   318: invokestatic 109	com/tencent/android/tpush/horse/i:a	(Lcom/tencent/android/tpush/horse/i;Ljava/lang/String;)V
    //   321: goto -119 -> 202
    //   324: astore 5
    //   326: ldc 24
    //   328: aload 5
    //   330: invokevirtual 190	com/tencent/android/tpush/service/channel/exception/HorseIgnoreException:toString	()Ljava/lang/String;
    //   333: invokestatic 32	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   336: iconst_1
    //   337: bipush 104
    //   339: iconst_5
    //   340: invokestatic 114	java/lang/System:currentTimeMillis	()J
    //   343: aload_0
    //   344: getfield 12	com/tencent/android/tpush/horse/j:a	Lcom/tencent/android/tpush/horse/i;
    //   347: invokestatic 172	com/tencent/android/tpush/horse/i:c	(Lcom/tencent/android/tpush/horse/i;)J
    //   350: lsub
    //   351: aload_1
    //   352: aconst_null
    //   353: invokestatic 177	com/tencent/android/tpush/horse/g:a	(IIIJLcom/tencent/android/tpush/horse/data/StrategyItem;Lcom/tencent/android/tpush/horse/data/StrategyItem;)V
    //   356: aload_0
    //   357: getfield 12	com/tencent/android/tpush/horse/j:a	Lcom/tencent/android/tpush/horse/i;
    //   360: invokevirtual 192	com/tencent/android/tpush/horse/i:c	()V
    //   363: goto -161 -> 202
    //   366: astore 4
    //   368: ldc 24
    //   370: aload 4
    //   372: invokevirtual 193	java/lang/Exception:toString	()Ljava/lang/String;
    //   375: invokestatic 32	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   378: goto -176 -> 202
    //   381: ldc 24
    //   383: ldc 195
    //   385: invokestatic 68	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   388: goto -186 -> 202
    //
    // Exception table:
    //   from	to	target	type
    //   11	79	205	finally
    //   79	94	205	finally
    //   98	109	205	finally
    //   109	117	205	finally
    //   117	119	205	finally
    //   120	202	205	finally
    //   202	204	205	finally
    //   206	208	205	finally
    //   210	298	205	finally
    //   298	300	205	finally
    //   303	321	205	finally
    //   326	363	205	finally
    //   368	378	205	finally
    //   381	388	205	finally
    //   79	94	301	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   98	109	301	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   109	117	301	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   120	202	301	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   210	298	301	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   79	94	324	com/tencent/android/tpush/service/channel/exception/HorseIgnoreException
    //   98	109	324	com/tencent/android/tpush/service/channel/exception/HorseIgnoreException
    //   109	117	324	com/tencent/android/tpush/service/channel/exception/HorseIgnoreException
    //   120	202	324	com/tencent/android/tpush/service/channel/exception/HorseIgnoreException
    //   210	298	324	com/tencent/android/tpush/service/channel/exception/HorseIgnoreException
    //   79	94	366	java/lang/Exception
    //   98	109	366	java/lang/Exception
    //   109	117	366	java/lang/Exception
    //   120	202	366	java/lang/Exception
    //   210	298	366	java/lang/Exception
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.j
 * JD-Core Version:    0.6.0
 */
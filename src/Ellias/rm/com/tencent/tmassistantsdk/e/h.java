package com.tencent.tmassistantsdk.e;

import com.qq.taf.jce.JceStruct;

class h
  implements Runnable
{
  h(g paramg, JceStruct paramJceStruct)
  {
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 16	com/tencent/tmassistantsdk/e/h:a	Lcom/qq/taf/jce/JceStruct;
    //   4: invokestatic 33	com/tencent/tmassistantsdk/protocol/a:b	(Lcom/qq/taf/jce/JceStruct;)Lcom/tencent/tmassistantsdk/protocol/jce/Request;
    //   7: astore_1
    //   8: aload_1
    //   9: invokestatic 36	com/tencent/tmassistantsdk/protocol/a:a	(Lcom/tencent/tmassistantsdk/protocol/jce/Request;)[B
    //   12: astore_2
    //   13: aload_0
    //   14: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   17: new 38	org/apache/http/client/methods/HttpPost
    //   20: dup
    //   21: ldc 40
    //   23: invokespecial 43	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   26: putfield 48	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   29: aload_0
    //   30: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   33: getfield 48	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   36: ldc 50
    //   38: ldc 52
    //   40: invokevirtual 56	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   43: aload_0
    //   44: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   47: getfield 48	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   50: new 58	org/apache/http/entity/ByteArrayEntity
    //   53: dup
    //   54: aload_2
    //   55: invokespecial 61	org/apache/http/entity/ByteArrayEntity:<init>	([B)V
    //   58: invokevirtual 65	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   61: invokestatic 70	com/tencent/tmassistantsdk/downloadservice/k:a	()Lorg/apache/http/client/HttpClient;
    //   64: astore 9
    //   66: aload 9
    //   68: astore 4
    //   70: aload 4
    //   72: invokestatic 73	com/tencent/tmassistantsdk/downloadservice/k:a	(Lorg/apache/http/client/HttpClient;)V
    //   75: aload 4
    //   77: aload_0
    //   78: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   81: getfield 48	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   84: invokeinterface 79 2 0
    //   89: astore 10
    //   91: aload 10
    //   93: ifnull +244 -> 337
    //   96: aload 10
    //   98: invokeinterface 85 1 0
    //   103: invokeinterface 91 1 0
    //   108: sipush 200
    //   111: if_icmpne +226 -> 337
    //   114: aload 10
    //   116: invokeinterface 95 1 0
    //   121: astore 11
    //   123: aload 11
    //   125: ifnull +212 -> 337
    //   128: new 97	org/apache/http/util/ByteArrayBuffer
    //   131: dup
    //   132: aload 11
    //   134: invokeinterface 103 1 0
    //   139: l2i
    //   140: invokespecial 106	org/apache/http/util/ByteArrayBuffer:<init>	(I)V
    //   143: astore 12
    //   145: aload 11
    //   147: invokeinterface 110 1 0
    //   152: astore 13
    //   154: sipush 2048
    //   157: newarray byte
    //   159: astore 14
    //   161: aload 13
    //   163: aload 14
    //   165: invokevirtual 116	java/io/InputStream:read	([B)I
    //   168: istore 15
    //   170: iload 15
    //   172: iconst_m1
    //   173: if_icmpeq +59 -> 232
    //   176: aload 12
    //   178: aload 14
    //   180: iconst_0
    //   181: iload 15
    //   183: invokevirtual 120	org/apache/http/util/ByteArrayBuffer:append	([BII)V
    //   186: goto -25 -> 161
    //   189: astore_3
    //   190: aload_3
    //   191: invokevirtual 123	org/apache/http/conn/ConnectTimeoutException:printStackTrace	()V
    //   194: aload_0
    //   195: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   198: aload_1
    //   199: aconst_null
    //   200: sipush 601
    //   203: invokevirtual 126	com/tencent/tmassistantsdk/e/g:a	(Lcom/qq/taf/jce/JceStruct;Lcom/qq/taf/jce/JceStruct;I)V
    //   206: aload_0
    //   207: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   210: aconst_null
    //   211: putfield 48	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   214: aload 4
    //   216: ifnull +15 -> 231
    //   219: aload 4
    //   221: invokeinterface 130 1 0
    //   226: invokeinterface 135 1 0
    //   231: return
    //   232: aload 12
    //   234: invokevirtual 139	org/apache/http/util/ByteArrayBuffer:buffer	()[B
    //   237: astore 16
    //   239: aload 16
    //   241: ifnull +96 -> 337
    //   244: aload 16
    //   246: arraylength
    //   247: iconst_4
    //   248: if_icmple +89 -> 337
    //   251: aload 16
    //   253: invokestatic 142	com/tencent/tmassistantsdk/protocol/a:a	([B)Lcom/tencent/tmassistantsdk/protocol/jce/Response;
    //   256: astore 17
    //   258: aload 17
    //   260: ifnull +77 -> 337
    //   263: aload 17
    //   265: getfield 148	com/tencent/tmassistantsdk/protocol/jce/Response:body	[B
    //   268: ifnull +69 -> 337
    //   271: aload_0
    //   272: getfield 16	com/tencent/tmassistantsdk/e/h:a	Lcom/qq/taf/jce/JceStruct;
    //   275: aload 17
    //   277: getfield 148	com/tencent/tmassistantsdk/protocol/jce/Response:body	[B
    //   280: invokestatic 151	com/tencent/tmassistantsdk/protocol/a:a	(Lcom/qq/taf/jce/JceStruct;[B)Lcom/qq/taf/jce/JceStruct;
    //   283: astore 18
    //   285: aload 18
    //   287: ifnull +50 -> 337
    //   290: aload_0
    //   291: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   294: aload_0
    //   295: getfield 16	com/tencent/tmassistantsdk/e/h:a	Lcom/qq/taf/jce/JceStruct;
    //   298: aload 18
    //   300: aload 17
    //   302: getfield 155	com/tencent/tmassistantsdk/protocol/jce/Response:head	Lcom/tencent/tmassistantsdk/protocol/jce/RspHead;
    //   305: getfield 161	com/tencent/tmassistantsdk/protocol/jce/RspHead:ret	I
    //   308: invokevirtual 126	com/tencent/tmassistantsdk/e/g:a	(Lcom/qq/taf/jce/JceStruct;Lcom/qq/taf/jce/JceStruct;I)V
    //   311: aload_0
    //   312: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   315: aconst_null
    //   316: putfield 48	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   319: aload 4
    //   321: ifnull -90 -> 231
    //   324: aload 4
    //   326: invokeinterface 130 1 0
    //   331: invokeinterface 135 1 0
    //   336: return
    //   337: aload_0
    //   338: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   341: aconst_null
    //   342: putfield 48	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   345: aload 4
    //   347: ifnull -116 -> 231
    //   350: aload 4
    //   352: invokeinterface 130 1 0
    //   357: invokeinterface 135 1 0
    //   362: return
    //   363: astore 8
    //   365: aconst_null
    //   366: astore 4
    //   368: aload 8
    //   370: invokevirtual 162	java/net/ConnectException:printStackTrace	()V
    //   373: aload_0
    //   374: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   377: aload_1
    //   378: aconst_null
    //   379: iconst_1
    //   380: invokevirtual 126	com/tencent/tmassistantsdk/e/g:a	(Lcom/qq/taf/jce/JceStruct;Lcom/qq/taf/jce/JceStruct;I)V
    //   383: aload_0
    //   384: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   387: aconst_null
    //   388: putfield 48	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   391: aload 4
    //   393: ifnull -162 -> 231
    //   396: aload 4
    //   398: invokeinterface 130 1 0
    //   403: invokeinterface 135 1 0
    //   408: return
    //   409: astore 7
    //   411: aconst_null
    //   412: astore 4
    //   414: aload 7
    //   416: invokevirtual 163	java/net/SocketTimeoutException:printStackTrace	()V
    //   419: aload_0
    //   420: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   423: aload_1
    //   424: aconst_null
    //   425: sipush 602
    //   428: invokevirtual 126	com/tencent/tmassistantsdk/e/g:a	(Lcom/qq/taf/jce/JceStruct;Lcom/qq/taf/jce/JceStruct;I)V
    //   431: aload_0
    //   432: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   435: aconst_null
    //   436: putfield 48	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   439: aload 4
    //   441: ifnull -210 -> 231
    //   444: aload 4
    //   446: invokeinterface 130 1 0
    //   451: invokeinterface 135 1 0
    //   456: return
    //   457: astore 6
    //   459: aconst_null
    //   460: astore 4
    //   462: aload 6
    //   464: invokevirtual 164	java/lang/Exception:printStackTrace	()V
    //   467: aload_0
    //   468: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   471: aload_0
    //   472: getfield 16	com/tencent/tmassistantsdk/e/h:a	Lcom/qq/taf/jce/JceStruct;
    //   475: aconst_null
    //   476: sipush 604
    //   479: invokevirtual 126	com/tencent/tmassistantsdk/e/g:a	(Lcom/qq/taf/jce/JceStruct;Lcom/qq/taf/jce/JceStruct;I)V
    //   482: aload_0
    //   483: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   486: aconst_null
    //   487: putfield 48	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   490: aload 4
    //   492: ifnull -261 -> 231
    //   495: aload 4
    //   497: invokeinterface 130 1 0
    //   502: invokeinterface 135 1 0
    //   507: return
    //   508: astore 5
    //   510: aconst_null
    //   511: astore 4
    //   513: aload_0
    //   514: getfield 14	com/tencent/tmassistantsdk/e/h:b	Lcom/tencent/tmassistantsdk/e/g;
    //   517: aconst_null
    //   518: putfield 48	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   521: aload 4
    //   523: ifnull +15 -> 538
    //   526: aload 4
    //   528: invokeinterface 130 1 0
    //   533: invokeinterface 135 1 0
    //   538: aload 5
    //   540: athrow
    //   541: astore 5
    //   543: goto -30 -> 513
    //   546: astore 6
    //   548: goto -86 -> 462
    //   551: astore 7
    //   553: goto -139 -> 414
    //   556: astore 8
    //   558: goto -190 -> 368
    //   561: astore_3
    //   562: aconst_null
    //   563: astore 4
    //   565: goto -375 -> 190
    //
    // Exception table:
    //   from	to	target	type
    //   70	91	189	org/apache/http/conn/ConnectTimeoutException
    //   96	123	189	org/apache/http/conn/ConnectTimeoutException
    //   128	161	189	org/apache/http/conn/ConnectTimeoutException
    //   161	170	189	org/apache/http/conn/ConnectTimeoutException
    //   176	186	189	org/apache/http/conn/ConnectTimeoutException
    //   232	239	189	org/apache/http/conn/ConnectTimeoutException
    //   244	258	189	org/apache/http/conn/ConnectTimeoutException
    //   263	285	189	org/apache/http/conn/ConnectTimeoutException
    //   290	311	189	org/apache/http/conn/ConnectTimeoutException
    //   61	66	363	java/net/ConnectException
    //   61	66	409	java/net/SocketTimeoutException
    //   61	66	457	java/lang/Exception
    //   61	66	508	finally
    //   70	91	541	finally
    //   96	123	541	finally
    //   128	161	541	finally
    //   161	170	541	finally
    //   176	186	541	finally
    //   190	206	541	finally
    //   232	239	541	finally
    //   244	258	541	finally
    //   263	285	541	finally
    //   290	311	541	finally
    //   368	383	541	finally
    //   414	431	541	finally
    //   462	482	541	finally
    //   70	91	546	java/lang/Exception
    //   96	123	546	java/lang/Exception
    //   128	161	546	java/lang/Exception
    //   161	170	546	java/lang/Exception
    //   176	186	546	java/lang/Exception
    //   232	239	546	java/lang/Exception
    //   244	258	546	java/lang/Exception
    //   263	285	546	java/lang/Exception
    //   290	311	546	java/lang/Exception
    //   70	91	551	java/net/SocketTimeoutException
    //   96	123	551	java/net/SocketTimeoutException
    //   128	161	551	java/net/SocketTimeoutException
    //   161	170	551	java/net/SocketTimeoutException
    //   176	186	551	java/net/SocketTimeoutException
    //   232	239	551	java/net/SocketTimeoutException
    //   244	258	551	java/net/SocketTimeoutException
    //   263	285	551	java/net/SocketTimeoutException
    //   290	311	551	java/net/SocketTimeoutException
    //   70	91	556	java/net/ConnectException
    //   96	123	556	java/net/ConnectException
    //   128	161	556	java/net/ConnectException
    //   161	170	556	java/net/ConnectException
    //   176	186	556	java/net/ConnectException
    //   232	239	556	java/net/ConnectException
    //   244	258	556	java/net/ConnectException
    //   263	285	556	java/net/ConnectException
    //   290	311	556	java/net/ConnectException
    //   61	66	561	org/apache/http/conn/ConnectTimeoutException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.e.h
 * JD-Core Version:    0.6.0
 */
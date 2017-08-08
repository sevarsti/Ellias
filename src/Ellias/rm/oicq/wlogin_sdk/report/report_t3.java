package oicq.wlogin_sdk.report;

public class report_t3 extends report_t
{
  private static final long serialVersionUID = 1L;
  public int _cmd = 0;
  public int _conn = 0;
  public String _host = new String("");
  public String _ip = new String("");
  public int _net = 0;
  public int _port = 0;
  public int _rlen = 0;
  public int _rst2 = 0;
  public int _slen = 0;
  public String _str = new String("");
  public int _sub = 0;
  public int _try = 0;
  public int _used = 0;
  public int _wap = 0;

  // ERROR //
  public org.json.JSONObject toJasonObj()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 69	org/json/JSONObject
    //   5: dup
    //   6: invokespecial 70	org/json/JSONObject:<init>	()V
    //   9: astore_2
    //   10: iconst_1
    //   11: anewarray 72	java/lang/Object
    //   14: astore 6
    //   16: aload 6
    //   18: iconst_0
    //   19: aload_0
    //   20: getfield 30	oicq/wlogin_sdk/report/report_t3:_cmd	I
    //   23: invokestatic 78	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   26: aastore
    //   27: aload_2
    //   28: ldc 80
    //   30: ldc 82
    //   32: aload 6
    //   34: invokestatic 86	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   37: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   40: pop
    //   41: iconst_1
    //   42: anewarray 72	java/lang/Object
    //   45: astore 8
    //   47: aload 8
    //   49: iconst_0
    //   50: aload_0
    //   51: getfield 32	oicq/wlogin_sdk/report/report_t3:_sub	I
    //   54: invokestatic 78	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   57: aastore
    //   58: aload_2
    //   59: ldc 92
    //   61: ldc 82
    //   63: aload 8
    //   65: invokestatic 86	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   68: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   71: pop
    //   72: iconst_1
    //   73: anewarray 72	java/lang/Object
    //   76: astore 10
    //   78: aload 10
    //   80: iconst_0
    //   81: aload_0
    //   82: getfield 34	oicq/wlogin_sdk/report/report_t3:_rst2	I
    //   85: invokestatic 78	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   88: aastore
    //   89: aload_2
    //   90: ldc 94
    //   92: ldc 96
    //   94: aload 10
    //   96: invokestatic 86	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   99: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   102: pop
    //   103: iconst_1
    //   104: anewarray 72	java/lang/Object
    //   107: astore 12
    //   109: aload 12
    //   111: iconst_0
    //   112: aload_0
    //   113: getfield 36	oicq/wlogin_sdk/report/report_t3:_used	I
    //   116: invokestatic 78	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   119: aastore
    //   120: aload_2
    //   121: ldc 98
    //   123: ldc 96
    //   125: aload 12
    //   127: invokestatic 86	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   130: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   133: pop
    //   134: iconst_1
    //   135: anewarray 72	java/lang/Object
    //   138: astore 14
    //   140: aload 14
    //   142: iconst_0
    //   143: aload_0
    //   144: getfield 38	oicq/wlogin_sdk/report/report_t3:_try	I
    //   147: invokestatic 78	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   150: aastore
    //   151: aload_2
    //   152: ldc 100
    //   154: ldc 96
    //   156: aload 14
    //   158: invokestatic 86	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   161: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   164: pop
    //   165: aload_2
    //   166: ldc 102
    //   168: aload_0
    //   169: getfield 47	oicq/wlogin_sdk/report/report_t3:_host	Ljava/lang/String;
    //   172: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   175: pop
    //   176: aload_2
    //   177: ldc 104
    //   179: aload_0
    //   180: getfield 49	oicq/wlogin_sdk/report/report_t3:_ip	Ljava/lang/String;
    //   183: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   186: pop
    //   187: iconst_1
    //   188: anewarray 72	java/lang/Object
    //   191: astore 18
    //   193: aload 18
    //   195: iconst_0
    //   196: aload_0
    //   197: getfield 51	oicq/wlogin_sdk/report/report_t3:_port	I
    //   200: invokestatic 78	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   203: aastore
    //   204: aload_2
    //   205: ldc 106
    //   207: ldc 96
    //   209: aload 18
    //   211: invokestatic 86	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   214: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   217: pop
    //   218: iconst_1
    //   219: anewarray 72	java/lang/Object
    //   222: astore 20
    //   224: aload 20
    //   226: iconst_0
    //   227: aload_0
    //   228: getfield 53	oicq/wlogin_sdk/report/report_t3:_conn	I
    //   231: invokestatic 78	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   234: aastore
    //   235: aload_2
    //   236: ldc 108
    //   238: ldc 96
    //   240: aload 20
    //   242: invokestatic 86	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   245: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   248: pop
    //   249: iconst_1
    //   250: anewarray 72	java/lang/Object
    //   253: astore 22
    //   255: aload 22
    //   257: iconst_0
    //   258: aload_0
    //   259: getfield 55	oicq/wlogin_sdk/report/report_t3:_net	I
    //   262: invokestatic 78	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   265: aastore
    //   266: aload_2
    //   267: ldc 110
    //   269: ldc 96
    //   271: aload 22
    //   273: invokestatic 86	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   276: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   279: pop
    //   280: aload_2
    //   281: ldc 112
    //   283: aload_0
    //   284: getfield 57	oicq/wlogin_sdk/report/report_t3:_str	Ljava/lang/String;
    //   287: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   290: pop
    //   291: iconst_1
    //   292: anewarray 72	java/lang/Object
    //   295: astore 25
    //   297: aload 25
    //   299: iconst_0
    //   300: aload_0
    //   301: getfield 59	oicq/wlogin_sdk/report/report_t3:_rlen	I
    //   304: invokestatic 78	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   307: aastore
    //   308: aload_2
    //   309: ldc 114
    //   311: ldc 96
    //   313: aload 25
    //   315: invokestatic 86	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   318: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   321: pop
    //   322: iconst_1
    //   323: anewarray 72	java/lang/Object
    //   326: astore 27
    //   328: aload 27
    //   330: iconst_0
    //   331: aload_0
    //   332: getfield 61	oicq/wlogin_sdk/report/report_t3:_slen	I
    //   335: invokestatic 78	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   338: aastore
    //   339: aload_2
    //   340: ldc 116
    //   342: ldc 96
    //   344: aload 27
    //   346: invokestatic 86	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   349: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   352: pop
    //   353: iconst_1
    //   354: anewarray 72	java/lang/Object
    //   357: astore 29
    //   359: aload 29
    //   361: iconst_0
    //   362: aload_0
    //   363: getfield 63	oicq/wlogin_sdk/report/report_t3:_wap	I
    //   366: invokestatic 78	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   369: aastore
    //   370: aload_2
    //   371: ldc 118
    //   373: ldc 96
    //   375: aload 29
    //   377: invokestatic 86	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   380: invokevirtual 90	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   383: pop
    //   384: aload_2
    //   385: areturn
    //   386: astore_3
    //   387: new 120	java/io/StringWriter
    //   390: dup
    //   391: invokespecial 121	java/io/StringWriter:<init>	()V
    //   394: astore 4
    //   396: new 123	java/io/PrintWriter
    //   399: dup
    //   400: aload 4
    //   402: iconst_1
    //   403: invokespecial 126	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   406: astore 5
    //   408: aload_3
    //   409: aload 5
    //   411: invokevirtual 130	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   414: aload 5
    //   416: invokevirtual 133	java/io/PrintWriter:flush	()V
    //   419: aload 4
    //   421: invokevirtual 134	java/io/StringWriter:flush	()V
    //   424: ldc 136
    //   426: aload 4
    //   428: invokevirtual 140	java/io/StringWriter:toString	()Ljava/lang/String;
    //   431: invokestatic 146	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;Ljava/lang/String;)V
    //   434: aload_1
    //   435: areturn
    //   436: astore_3
    //   437: aload_2
    //   438: astore_1
    //   439: goto -52 -> 387
    //
    // Exception table:
    //   from	to	target	type
    //   2	10	386	java/lang/Exception
    //   10	384	436	java/lang/Exception
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.report.report_t3
 * JD-Core Version:    0.6.0
 */
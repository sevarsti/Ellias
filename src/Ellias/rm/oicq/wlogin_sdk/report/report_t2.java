package oicq.wlogin_sdk.report;

import java.util.TreeMap;

public class report_t2 extends report_t
{
  private static final long serialVersionUID = 1L;
  public long _app = 0L;
  public long[] _app_list = null;
  public TreeMap<Integer, report_t3> _log = new TreeMap();
  public String _name = new String("");
  public String _oper = new String("");
  public int _rlen = 0;
  public int _rst1 = 0;
  public int _rst2 = 0;
  public int _slen = 0;
  public long _start = 0L;
  public long _sub_app = 0L;
  public String _type = new String("");
  public long _uin = 0L;
  public int _used = 0;

  public report_t2(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, long[] paramArrayOfLong)
  {
    this._type = paramString1;
    this._oper = paramString2;
    this._start = paramLong1;
    this._app = paramLong2;
    this._sub_app = paramLong3;
    this._app_list = paramArrayOfLong;
  }

  public void add_t3(report_t3 paramreport_t3)
  {
    this._rlen += paramreport_t3._rlen;
    this._slen += paramreport_t3._slen;
    this._log.put(Integer.valueOf(this._log.size()), paramreport_t3);
  }

  public void clear_t3()
  {
    this._log.clear();
  }

  public void commit(long paramLong, String paramString, int paramInt1, int paramInt2)
  {
    this._uin = paramLong;
    this._name = paramString;
    this._rst1 = paramInt1;
    this._rst2 = paramInt2;
    this._used = (int)(System.currentTimeMillis() - this._start);
  }

  // ERROR //
  public org.json.JSONObject toJasonObj()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 108	org/json/JSONObject
    //   5: dup
    //   6: invokespecial 109	org/json/JSONObject:<init>	()V
    //   9: astore_2
    //   10: aload_2
    //   11: ldc 111
    //   13: aload_0
    //   14: getfield 41	oicq/wlogin_sdk/report/report_t2:_type	Ljava/lang/String;
    //   17: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   20: pop
    //   21: aload_2
    //   22: ldc 116
    //   24: aload_0
    //   25: getfield 43	oicq/wlogin_sdk/report/report_t2:_oper	Ljava/lang/String;
    //   28: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   31: pop
    //   32: iconst_1
    //   33: anewarray 118	java/lang/Object
    //   36: astore 8
    //   38: aload 8
    //   40: iconst_0
    //   41: ldc2_w 119
    //   44: aload_0
    //   45: getfield 45	oicq/wlogin_sdk/report/report_t2:_start	J
    //   48: ldc2_w 121
    //   51: ldiv
    //   52: land
    //   53: invokestatic 127	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   56: aastore
    //   57: aload_2
    //   58: ldc 129
    //   60: ldc 131
    //   62: aload 8
    //   64: invokestatic 135	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   67: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   70: pop
    //   71: iconst_1
    //   72: anewarray 118	java/lang/Object
    //   75: astore 10
    //   77: aload 10
    //   79: iconst_0
    //   80: ldc2_w 119
    //   83: aload_0
    //   84: getfield 47	oicq/wlogin_sdk/report/report_t2:_used	I
    //   87: i2l
    //   88: land
    //   89: invokestatic 127	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   92: aastore
    //   93: aload_2
    //   94: ldc 137
    //   96: ldc 131
    //   98: aload 10
    //   100: invokestatic 135	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   103: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   106: pop
    //   107: iconst_1
    //   108: anewarray 118	java/lang/Object
    //   111: astore 12
    //   113: aload 12
    //   115: iconst_0
    //   116: ldc2_w 119
    //   119: aload_0
    //   120: getfield 49	oicq/wlogin_sdk/report/report_t2:_uin	J
    //   123: land
    //   124: invokestatic 127	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   127: aastore
    //   128: aload_2
    //   129: ldc 139
    //   131: ldc 131
    //   133: aload 12
    //   135: invokestatic 135	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   138: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   141: pop
    //   142: iconst_1
    //   143: anewarray 118	java/lang/Object
    //   146: astore 14
    //   148: aload 14
    //   150: iconst_0
    //   151: ldc2_w 119
    //   154: aload_0
    //   155: getfield 53	oicq/wlogin_sdk/report/report_t2:_app	J
    //   158: land
    //   159: invokestatic 127	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   162: aastore
    //   163: aload_2
    //   164: ldc 141
    //   166: ldc 131
    //   168: aload 14
    //   170: invokestatic 135	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   173: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   176: pop
    //   177: iconst_1
    //   178: anewarray 118	java/lang/Object
    //   181: astore 16
    //   183: aload 16
    //   185: iconst_0
    //   186: ldc2_w 119
    //   189: aload_0
    //   190: getfield 59	oicq/wlogin_sdk/report/report_t2:_sub_app	J
    //   193: land
    //   194: invokestatic 127	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   197: aastore
    //   198: aload_2
    //   199: ldc 143
    //   201: ldc 131
    //   203: aload 16
    //   205: invokestatic 135	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   208: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   211: pop
    //   212: aload_2
    //   213: ldc 145
    //   215: aload_0
    //   216: getfield 51	oicq/wlogin_sdk/report/report_t2:_name	Ljava/lang/String;
    //   219: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   222: pop
    //   223: ldc 36
    //   225: astore 19
    //   227: aload_0
    //   228: getfield 61	oicq/wlogin_sdk/report/report_t2:_app_list	[J
    //   231: ifnull +16 -> 247
    //   234: iconst_0
    //   235: istore 20
    //   237: iload 20
    //   239: aload_0
    //   240: getfield 61	oicq/wlogin_sdk/report/report_t2:_app_list	[J
    //   243: arraylength
    //   244: if_icmplt +131 -> 375
    //   247: aload_2
    //   248: ldc 147
    //   250: aload 19
    //   252: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   255: pop
    //   256: iconst_1
    //   257: anewarray 118	java/lang/Object
    //   260: astore 22
    //   262: aload 22
    //   264: iconst_0
    //   265: ldc2_w 119
    //   268: aload_0
    //   269: getfield 55	oicq/wlogin_sdk/report/report_t2:_rst1	I
    //   272: i2l
    //   273: land
    //   274: invokestatic 127	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   277: aastore
    //   278: aload_2
    //   279: ldc 149
    //   281: ldc 131
    //   283: aload 22
    //   285: invokestatic 135	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   288: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   291: pop
    //   292: iconst_1
    //   293: anewarray 118	java/lang/Object
    //   296: astore 24
    //   298: aload 24
    //   300: iconst_0
    //   301: ldc2_w 119
    //   304: aload_0
    //   305: getfield 57	oicq/wlogin_sdk/report/report_t2:_rst2	I
    //   308: i2l
    //   309: land
    //   310: invokestatic 127	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   313: aastore
    //   314: aload_2
    //   315: ldc 151
    //   317: ldc 131
    //   319: aload 24
    //   321: invokestatic 135	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   324: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   327: pop
    //   328: new 153	org/json/JSONArray
    //   331: dup
    //   332: invokespecial 154	org/json/JSONArray:<init>	()V
    //   335: astore 26
    //   337: iconst_0
    //   338: istore 27
    //   340: aload_0
    //   341: getfield 70	oicq/wlogin_sdk/report/report_t2:_log	Ljava/util/TreeMap;
    //   344: invokevirtual 158	java/util/TreeMap:keySet	()Ljava/util/Set;
    //   347: invokeinterface 164 1 0
    //   352: astore 28
    //   354: aload 28
    //   356: invokeinterface 170 1 0
    //   361: ifne +142 -> 503
    //   364: aload_2
    //   365: ldc 172
    //   367: aload 26
    //   369: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   372: pop
    //   373: aload_2
    //   374: areturn
    //   375: iload 20
    //   377: iconst_m1
    //   378: aload_0
    //   379: getfield 61	oicq/wlogin_sdk/report/report_t2:_app_list	[J
    //   382: arraylength
    //   383: iadd
    //   384: if_icmpne +61 -> 445
    //   387: new 174	java/lang/StringBuilder
    //   390: dup
    //   391: aload 19
    //   393: invokestatic 177	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   396: invokespecial 178	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   399: astore 31
    //   401: iconst_1
    //   402: anewarray 118	java/lang/Object
    //   405: astore 32
    //   407: aload 32
    //   409: iconst_0
    //   410: ldc2_w 119
    //   413: aload_0
    //   414: getfield 61	oicq/wlogin_sdk/report/report_t2:_app_list	[J
    //   417: iload 20
    //   419: laload
    //   420: land
    //   421: invokestatic 127	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   424: aastore
    //   425: aload 31
    //   427: ldc 131
    //   429: aload 32
    //   431: invokestatic 135	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   434: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   437: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   440: astore 19
    //   442: goto +151 -> 593
    //   445: new 174	java/lang/StringBuilder
    //   448: dup
    //   449: aload 19
    //   451: invokestatic 177	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   454: invokespecial 178	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   457: astore 33
    //   459: iconst_1
    //   460: anewarray 118	java/lang/Object
    //   463: astore 34
    //   465: aload 34
    //   467: iconst_0
    //   468: ldc2_w 119
    //   471: aload_0
    //   472: getfield 61	oicq/wlogin_sdk/report/report_t2:_app_list	[J
    //   475: iload 20
    //   477: laload
    //   478: land
    //   479: invokestatic 127	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   482: aastore
    //   483: aload 33
    //   485: ldc 188
    //   487: aload 34
    //   489: invokestatic 135	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   492: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   498: astore 19
    //   500: goto +93 -> 593
    //   503: aload 26
    //   505: iload 27
    //   507: aload_0
    //   508: getfield 70	oicq/wlogin_sdk/report/report_t2:_log	Ljava/util/TreeMap;
    //   511: aload 28
    //   513: invokeinterface 192 1 0
    //   518: invokevirtual 196	java/util/TreeMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   521: checkcast 74	oicq/wlogin_sdk/report/report_t3
    //   524: invokevirtual 198	oicq/wlogin_sdk/report/report_t3:toJasonObj	()Lorg/json/JSONObject;
    //   527: invokevirtual 201	org/json/JSONArray:put	(ILjava/lang/Object;)Lorg/json/JSONArray;
    //   530: pop
    //   531: iinc 27 1
    //   534: goto -180 -> 354
    //   537: astore_3
    //   538: new 203	java/io/StringWriter
    //   541: dup
    //   542: invokespecial 204	java/io/StringWriter:<init>	()V
    //   545: astore 4
    //   547: new 206	java/io/PrintWriter
    //   550: dup
    //   551: aload 4
    //   553: iconst_1
    //   554: invokespecial 209	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   557: astore 5
    //   559: aload_3
    //   560: aload 5
    //   562: invokevirtual 213	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   565: aload 5
    //   567: invokevirtual 216	java/io/PrintWriter:flush	()V
    //   570: aload 4
    //   572: invokevirtual 217	java/io/StringWriter:flush	()V
    //   575: ldc 219
    //   577: aload 4
    //   579: invokevirtual 220	java/io/StringWriter:toString	()Ljava/lang/String;
    //   582: invokestatic 226	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;Ljava/lang/String;)V
    //   585: aload_1
    //   586: areturn
    //   587: astore_3
    //   588: aload_2
    //   589: astore_1
    //   590: goto -52 -> 538
    //   593: iinc 20 1
    //   596: goto -359 -> 237
    //
    // Exception table:
    //   from	to	target	type
    //   2	10	537	java/lang/Exception
    //   10	223	587	java/lang/Exception
    //   227	234	587	java/lang/Exception
    //   237	247	587	java/lang/Exception
    //   247	337	587	java/lang/Exception
    //   340	354	587	java/lang/Exception
    //   354	373	587	java/lang/Exception
    //   375	442	587	java/lang/Exception
    //   445	500	587	java/lang/Exception
    //   503	531	587	java/lang/Exception
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.report.report_t2
 * JD-Core Version:    0.6.0
 */
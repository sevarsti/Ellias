package oicq.wlogin_sdk.report;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import oicq.wlogin_sdk.tools.LogCallBack;
import oicq.wlogin_sdk.tools.util;

public class report_t1 extends report_t
{
  private static final long serialVersionUID = 1L;
  public String _app_n = new String("");
  public String _app_sig = new String("");
  public String _app_v = new String("");
  public String _device = new String("");
  public String _ksid = new String("");
  public TreeMap<Integer, report_t2> _lst = new TreeMap();
  public int _os = 2;
  public String _os_v = new String("");
  public String _sdk_v = new Integer(util.SSO_VERSION).toString();

  public void add_t2(report_t2 paramreport_t2)
  {
    monitorenter;
    try
    {
      if (this._lst.size() >= 10)
        this._lst.remove(Integer.valueOf(-1 + this._lst.size()));
      this._lst.put(Integer.valueOf(this._lst.size()), paramreport_t2);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void add_t3(report_t3 paramreport_t3)
  {
    monitorenter;
    try
    {
      int i = this._lst.size();
      if (i <= 0);
      while (true)
      {
        return;
        ((report_t2)this._lst.get(Integer.valueOf(-1 + this._lst.size()))).add_t3(paramreport_t3);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void clear_t2()
  {
    monitorenter;
    try
    {
      Iterator localIterator = this._lst.keySet().iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          this._lst.clear();
          return;
        }
        ((report_t2)this._lst.get(localIterator.next())).clear_t3();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void commit(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    monitorenter;
    try
    {
      this._os_v = paramString1;
      this._app_v = paramString2;
      this._ksid = paramString4;
      this._app_n = paramString5;
      this._device = paramString6;
      this._app_sig = paramString7;
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

  public void commit_t2(long paramLong, String paramString, int paramInt1, int paramInt2)
  {
    monitorenter;
    try
    {
      int i = this._lst.size();
      if (i <= 0);
      while (true)
      {
        return;
        ((report_t2)this._lst.get(Integer.valueOf(-1 + this._lst.size()))).commit(paramLong, paramString, paramInt1, paramInt2);
        if (util.LCB == null)
          continue;
        util.LCB.OnLog(((report_t2)this._lst.get(Integer.valueOf(-1 + this._lst.size()))).toJasonObj());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public org.json.JSONObject toJasonObj()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore_1
    //   4: new 145	org/json/JSONObject
    //   7: dup
    //   8: invokespecial 146	org/json/JSONObject:<init>	()V
    //   11: astore_2
    //   12: iconst_1
    //   13: anewarray 148	java/lang/Object
    //   16: astore 7
    //   18: aload 7
    //   20: iconst_0
    //   21: aload_0
    //   22: getfield 27	oicq/wlogin_sdk/report/report_t1:_os	I
    //   25: invokestatic 77	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   28: aastore
    //   29: aload_2
    //   30: ldc 150
    //   32: ldc 152
    //   34: aload 7
    //   36: invokestatic 156	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   39: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   42: pop
    //   43: aload_2
    //   44: ldc 161
    //   46: aload_0
    //   47: getfield 36	oicq/wlogin_sdk/report/report_t1:_os_v	Ljava/lang/String;
    //   50: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   53: pop
    //   54: aload_2
    //   55: ldc 163
    //   57: aload_0
    //   58: getfield 38	oicq/wlogin_sdk/report/report_t1:_app_v	Ljava/lang/String;
    //   61: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   64: pop
    //   65: aload_2
    //   66: ldc 165
    //   68: aload_0
    //   69: getfield 54	oicq/wlogin_sdk/report/report_t1:_sdk_v	Ljava/lang/String;
    //   72: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   75: pop
    //   76: aload_2
    //   77: ldc 167
    //   79: aload_0
    //   80: getfield 56	oicq/wlogin_sdk/report/report_t1:_ksid	Ljava/lang/String;
    //   83: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   86: pop
    //   87: aload_2
    //   88: ldc 169
    //   90: aload_0
    //   91: getfield 58	oicq/wlogin_sdk/report/report_t1:_app_n	Ljava/lang/String;
    //   94: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   97: pop
    //   98: aload_2
    //   99: ldc 171
    //   101: aload_0
    //   102: getfield 60	oicq/wlogin_sdk/report/report_t1:_device	Ljava/lang/String;
    //   105: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   108: pop
    //   109: aload_2
    //   110: ldc 173
    //   112: aload_0
    //   113: getfield 62	oicq/wlogin_sdk/report/report_t1:_app_sig	Ljava/lang/String;
    //   116: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   119: pop
    //   120: new 175	org/json/JSONArray
    //   123: dup
    //   124: invokespecial 176	org/json/JSONArray:<init>	()V
    //   127: astore 16
    //   129: iconst_0
    //   130: istore 17
    //   132: aload_0
    //   133: getfield 67	oicq/wlogin_sdk/report/report_t1:_lst	Ljava/util/TreeMap;
    //   136: invokevirtual 99	java/util/TreeMap:keySet	()Ljava/util/Set;
    //   139: invokeinterface 105 1 0
    //   144: astore 18
    //   146: aload 18
    //   148: invokeinterface 111 1 0
    //   153: ifne +18 -> 171
    //   156: aload_2
    //   157: ldc 178
    //   159: aload 16
    //   161: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   164: pop
    //   165: aload_2
    //   166: astore_1
    //   167: aload_0
    //   168: monitorexit
    //   169: aload_1
    //   170: areturn
    //   171: aload 16
    //   173: iload 17
    //   175: aload_0
    //   176: getfield 67	oicq/wlogin_sdk/report/report_t1:_lst	Ljava/util/TreeMap;
    //   179: aload 18
    //   181: invokeinterface 118 1 0
    //   186: invokevirtual 90	java/util/TreeMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   189: checkcast 92	oicq/wlogin_sdk/report/report_t2
    //   192: invokevirtual 135	oicq/wlogin_sdk/report/report_t2:toJasonObj	()Lorg/json/JSONObject;
    //   195: invokevirtual 181	org/json/JSONArray:put	(ILjava/lang/Object;)Lorg/json/JSONArray;
    //   198: pop
    //   199: iinc 17 1
    //   202: goto -56 -> 146
    //   205: astore 4
    //   207: new 183	java/io/StringWriter
    //   210: dup
    //   211: invokespecial 184	java/io/StringWriter:<init>	()V
    //   214: astore 5
    //   216: new 186	java/io/PrintWriter
    //   219: dup
    //   220: aload 5
    //   222: iconst_1
    //   223: invokespecial 189	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   226: astore 6
    //   228: aload 4
    //   230: aload 6
    //   232: invokevirtual 193	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   235: aload 6
    //   237: invokevirtual 196	java/io/PrintWriter:flush	()V
    //   240: aload 5
    //   242: invokevirtual 197	java/io/StringWriter:flush	()V
    //   245: ldc 199
    //   247: aload 5
    //   249: invokevirtual 200	java/io/StringWriter:toString	()Ljava/lang/String;
    //   252: invokestatic 204	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;)V
    //   255: goto -88 -> 167
    //   258: astore_3
    //   259: aload_0
    //   260: monitorexit
    //   261: aload_3
    //   262: athrow
    //   263: astore 4
    //   265: aload_2
    //   266: astore_1
    //   267: goto -60 -> 207
    //   270: astore_3
    //   271: goto -12 -> 259
    //
    // Exception table:
    //   from	to	target	type
    //   4	12	205	java/lang/Exception
    //   4	12	258	finally
    //   207	255	258	finally
    //   12	129	263	java/lang/Exception
    //   132	146	263	java/lang/Exception
    //   146	165	263	java/lang/Exception
    //   171	199	263	java/lang/Exception
    //   12	129	270	finally
    //   132	146	270	finally
    //   146	165	270	finally
    //   171	199	270	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.report.report_t1
 * JD-Core Version:    0.6.0
 */
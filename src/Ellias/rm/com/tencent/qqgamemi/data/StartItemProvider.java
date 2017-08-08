package com.tencent.qqgamemi.data;

import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.TLog;
import java.io.File;

public class StartItemProvider
{
  private static final String a = "StartItemProvider";
  private String b = QMiCommon.a() + File.separator + "startItem";
  private StartItem c = null;

  // ERROR //
  private void b(StartItem paramStartItem)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 48	java/io/ObjectOutputStream
    //   5: dup
    //   6: new 50	java/io/FileOutputStream
    //   9: dup
    //   10: aload_0
    //   11: getfield 39	com/tencent/qqgamemi/data/StartItemProvider:b	Ljava/lang/String;
    //   14: invokespecial 53	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   17: invokespecial 56	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   20: astore_3
    //   21: aload_3
    //   22: aload_1
    //   23: invokevirtual 60	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   26: aload_3
    //   27: ifnull +7 -> 34
    //   30: aload_3
    //   31: invokevirtual 63	java/io/ObjectOutputStream:close	()V
    //   34: return
    //   35: astore 10
    //   37: ldc 8
    //   39: ldc 65
    //   41: aload 10
    //   43: invokestatic 71	com/tencent/qqgamemi/common/TLog:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   46: aload 10
    //   48: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   51: return
    //   52: astore 4
    //   54: aconst_null
    //   55: astore_3
    //   56: ldc 8
    //   58: ldc 65
    //   60: aload 4
    //   62: invokestatic 71	com/tencent/qqgamemi/common/TLog:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   65: aload 4
    //   67: invokevirtual 75	java/io/FileNotFoundException:printStackTrace	()V
    //   70: aload_3
    //   71: ifnull -37 -> 34
    //   74: aload_3
    //   75: invokevirtual 63	java/io/ObjectOutputStream:close	()V
    //   78: return
    //   79: astore 7
    //   81: ldc 8
    //   83: ldc 65
    //   85: aload 7
    //   87: invokestatic 71	com/tencent/qqgamemi/common/TLog:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   90: aload 7
    //   92: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   95: return
    //   96: astore 8
    //   98: ldc 8
    //   100: ldc 65
    //   102: aload 8
    //   104: invokestatic 71	com/tencent/qqgamemi/common/TLog:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   107: aload 8
    //   109: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   112: aload_2
    //   113: ifnull -79 -> 34
    //   116: aload_2
    //   117: invokevirtual 63	java/io/ObjectOutputStream:close	()V
    //   120: return
    //   121: astore 9
    //   123: ldc 8
    //   125: ldc 65
    //   127: aload 9
    //   129: invokestatic 71	com/tencent/qqgamemi/common/TLog:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   132: aload 9
    //   134: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   137: return
    //   138: astore 5
    //   140: aload_2
    //   141: ifnull +7 -> 148
    //   144: aload_2
    //   145: invokevirtual 63	java/io/ObjectOutputStream:close	()V
    //   148: aload 5
    //   150: athrow
    //   151: astore 6
    //   153: ldc 8
    //   155: ldc 65
    //   157: aload 6
    //   159: invokestatic 71	com/tencent/qqgamemi/common/TLog:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   162: aload 6
    //   164: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   167: goto -19 -> 148
    //   170: astore 5
    //   172: aload_3
    //   173: astore_2
    //   174: goto -34 -> 140
    //   177: astore 8
    //   179: aload_3
    //   180: astore_2
    //   181: goto -83 -> 98
    //   184: astore 4
    //   186: goto -130 -> 56
    //
    // Exception table:
    //   from	to	target	type
    //   30	34	35	java/io/IOException
    //   2	21	52	java/io/FileNotFoundException
    //   74	78	79	java/io/IOException
    //   2	21	96	java/io/IOException
    //   116	120	121	java/io/IOException
    //   2	21	138	finally
    //   98	112	138	finally
    //   144	148	151	java/io/IOException
    //   21	26	170	finally
    //   56	70	170	finally
    //   21	26	177	java/io/IOException
    //   21	26	184	java/io/FileNotFoundException
  }

  public StartItem a()
  {
    if (this.c == null)
      this.c = b();
    TLog.c("StartItemProvider", "getStartItem=" + this.c);
    return this.c;
  }

  public void a(StartItem paramStartItem)
  {
    TLog.c("StartItemProvider", "setStartInfo:" + paramStartItem);
    this.c = paramStartItem;
    b(paramStartItem);
  }

  // ERROR //
  public StartItem b()
  {
    // Byte code:
    //   0: new 98	java/io/ObjectInputStream
    //   3: dup
    //   4: new 100	java/io/FileInputStream
    //   7: dup
    //   8: aload_0
    //   9: getfield 39	com/tencent/qqgamemi/data/StartItemProvider:b	Ljava/lang/String;
    //   12: invokespecial 101	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   15: invokespecial 104	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   18: astore_1
    //   19: aload_1
    //   20: invokevirtual 108	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   23: checkcast 110	com/tencent/qqgamemi/data/StartItem
    //   26: astore 5
    //   28: aload_1
    //   29: ifnull +7 -> 36
    //   32: aload_1
    //   33: invokevirtual 111	java/io/ObjectInputStream:close	()V
    //   36: ldc 8
    //   38: new 17	java/lang/StringBuilder
    //   41: dup
    //   42: invokespecial 18	java/lang/StringBuilder:<init>	()V
    //   45: ldc 113
    //   47: invokevirtual 27	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: aload 5
    //   52: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   55: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: invokestatic 86	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: aload 5
    //   63: areturn
    //   64: astore 15
    //   66: ldc 8
    //   68: ldc 65
    //   70: aload 15
    //   72: invokestatic 115	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   75: aload 15
    //   77: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   80: goto -44 -> 36
    //   83: astore_2
    //   84: aconst_null
    //   85: astore_1
    //   86: ldc 8
    //   88: ldc 65
    //   90: aload_2
    //   91: invokestatic 71	com/tencent/qqgamemi/common/TLog:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   94: aload_1
    //   95: ifnull +311 -> 406
    //   98: aload_1
    //   99: invokevirtual 111	java/io/ObjectInputStream:close	()V
    //   102: aconst_null
    //   103: astore 5
    //   105: goto -69 -> 36
    //   108: astore 6
    //   110: ldc 8
    //   112: ldc 65
    //   114: aload 6
    //   116: invokestatic 115	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   119: aload 6
    //   121: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   124: aconst_null
    //   125: astore 5
    //   127: goto -91 -> 36
    //   130: astore 7
    //   132: aconst_null
    //   133: astore_1
    //   134: ldc 8
    //   136: ldc 65
    //   138: aload 7
    //   140: invokestatic 71	com/tencent/qqgamemi/common/TLog:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   143: aload 7
    //   145: invokevirtual 75	java/io/FileNotFoundException:printStackTrace	()V
    //   148: aload_1
    //   149: ifnull +257 -> 406
    //   152: aload_1
    //   153: invokevirtual 111	java/io/ObjectInputStream:close	()V
    //   156: aconst_null
    //   157: astore 5
    //   159: goto -123 -> 36
    //   162: astore 8
    //   164: ldc 8
    //   166: ldc 65
    //   168: aload 8
    //   170: invokestatic 115	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   173: aload 8
    //   175: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   178: aconst_null
    //   179: astore 5
    //   181: goto -145 -> 36
    //   184: astore 9
    //   186: aconst_null
    //   187: astore_1
    //   188: ldc 8
    //   190: ldc 65
    //   192: aload 9
    //   194: invokestatic 71	com/tencent/qqgamemi/common/TLog:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   197: aload 9
    //   199: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   202: aload_1
    //   203: ifnull +203 -> 406
    //   206: aload_1
    //   207: invokevirtual 111	java/io/ObjectInputStream:close	()V
    //   210: aconst_null
    //   211: astore 5
    //   213: goto -177 -> 36
    //   216: astore 10
    //   218: ldc 8
    //   220: ldc 65
    //   222: aload 10
    //   224: invokestatic 115	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   227: aload 10
    //   229: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   232: aconst_null
    //   233: astore 5
    //   235: goto -199 -> 36
    //   238: astore 11
    //   240: aconst_null
    //   241: astore_1
    //   242: ldc 8
    //   244: ldc 65
    //   246: aload 11
    //   248: invokestatic 71	com/tencent/qqgamemi/common/TLog:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   251: aload 11
    //   253: invokevirtual 116	java/lang/ClassNotFoundException:printStackTrace	()V
    //   256: aload_1
    //   257: ifnull +149 -> 406
    //   260: aload_1
    //   261: invokevirtual 111	java/io/ObjectInputStream:close	()V
    //   264: aconst_null
    //   265: astore 5
    //   267: goto -231 -> 36
    //   270: astore 12
    //   272: ldc 8
    //   274: ldc 65
    //   276: aload 12
    //   278: invokestatic 115	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   281: aload 12
    //   283: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   286: aconst_null
    //   287: astore 5
    //   289: goto -253 -> 36
    //   292: astore 13
    //   294: aconst_null
    //   295: astore_1
    //   296: ldc 8
    //   298: ldc 65
    //   300: aload 13
    //   302: invokestatic 71	com/tencent/qqgamemi/common/TLog:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   305: aload 13
    //   307: invokevirtual 117	java/lang/ClassCastException:printStackTrace	()V
    //   310: aload_1
    //   311: ifnull +95 -> 406
    //   314: aload_1
    //   315: invokevirtual 111	java/io/ObjectInputStream:close	()V
    //   318: aconst_null
    //   319: astore 5
    //   321: goto -285 -> 36
    //   324: astore 14
    //   326: ldc 8
    //   328: ldc 65
    //   330: aload 14
    //   332: invokestatic 115	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   335: aload 14
    //   337: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   340: aconst_null
    //   341: astore 5
    //   343: goto -307 -> 36
    //   346: astore_3
    //   347: aconst_null
    //   348: astore_1
    //   349: aload_1
    //   350: ifnull +7 -> 357
    //   353: aload_1
    //   354: invokevirtual 111	java/io/ObjectInputStream:close	()V
    //   357: aload_3
    //   358: athrow
    //   359: astore 4
    //   361: ldc 8
    //   363: ldc 65
    //   365: aload 4
    //   367: invokestatic 115	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   370: aload 4
    //   372: invokevirtual 74	java/io/IOException:printStackTrace	()V
    //   375: goto -18 -> 357
    //   378: astore_3
    //   379: goto -30 -> 349
    //   382: astore 13
    //   384: goto -88 -> 296
    //   387: astore 11
    //   389: goto -147 -> 242
    //   392: astore 9
    //   394: goto -206 -> 188
    //   397: astore 7
    //   399: goto -265 -> 134
    //   402: astore_2
    //   403: goto -317 -> 86
    //   406: aconst_null
    //   407: astore 5
    //   409: goto -373 -> 36
    //
    // Exception table:
    //   from	to	target	type
    //   32	36	64	java/io/IOException
    //   0	19	83	java/io/StreamCorruptedException
    //   98	102	108	java/io/IOException
    //   0	19	130	java/io/FileNotFoundException
    //   152	156	162	java/io/IOException
    //   0	19	184	java/io/IOException
    //   206	210	216	java/io/IOException
    //   0	19	238	java/lang/ClassNotFoundException
    //   260	264	270	java/io/IOException
    //   0	19	292	java/lang/ClassCastException
    //   314	318	324	java/io/IOException
    //   0	19	346	finally
    //   353	357	359	java/io/IOException
    //   19	28	378	finally
    //   86	94	378	finally
    //   134	148	378	finally
    //   188	202	378	finally
    //   242	256	378	finally
    //   296	310	378	finally
    //   19	28	382	java/lang/ClassCastException
    //   19	28	387	java/lang/ClassNotFoundException
    //   19	28	392	java/io/IOException
    //   19	28	397	java/io/FileNotFoundException
    //   19	28	402	java/io/StreamCorruptedException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.data.StartItemProvider
 * JD-Core Version:    0.6.0
 */
package com.tencent.stat;

class t
  implements Runnable
{
  t(n paramn)
  {
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 12	com/tencent/stat/t:a	Lcom/tencent/stat/n;
    //   4: invokestatic 24	com/tencent/stat/n:b	(Lcom/tencent/stat/n;)Lcom/tencent/stat/w;
    //   7: invokevirtual 30	com/tencent/stat/w:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   10: ldc 32
    //   12: aconst_null
    //   13: aconst_null
    //   14: aconst_null
    //   15: aconst_null
    //   16: aconst_null
    //   17: aconst_null
    //   18: invokevirtual 38	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   21: astore 4
    //   23: aload 4
    //   25: astore_2
    //   26: aload_2
    //   27: invokeinterface 44 1 0
    //   32: ifeq +112 -> 144
    //   35: aload_2
    //   36: iconst_0
    //   37: invokeinterface 48 2 0
    //   42: istore 5
    //   44: aload_2
    //   45: iconst_1
    //   46: invokeinterface 52 2 0
    //   51: astore 6
    //   53: aload_2
    //   54: iconst_2
    //   55: invokeinterface 52 2 0
    //   60: astore 7
    //   62: aload_2
    //   63: iconst_3
    //   64: invokeinterface 48 2 0
    //   69: istore 8
    //   71: new 54	com/tencent/stat/b
    //   74: dup
    //   75: iload 5
    //   77: invokespecial 57	com/tencent/stat/b:<init>	(I)V
    //   80: astore 9
    //   82: aload 9
    //   84: iload 5
    //   86: putfield 60	com/tencent/stat/b:a	I
    //   89: aload 9
    //   91: new 62	org/json/JSONObject
    //   94: dup
    //   95: aload 6
    //   97: invokespecial 65	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   100: putfield 68	com/tencent/stat/b:b	Lorg/json/JSONObject;
    //   103: aload 9
    //   105: aload 7
    //   107: putfield 72	com/tencent/stat/b:c	Ljava/lang/String;
    //   110: aload 9
    //   112: iload 8
    //   114: putfield 75	com/tencent/stat/b:d	I
    //   117: aload 9
    //   119: invokestatic 80	com/tencent/stat/StatConfig:a	(Lcom/tencent/stat/b;)V
    //   122: goto -96 -> 26
    //   125: astore_1
    //   126: invokestatic 83	com/tencent/stat/n:d	()Lcom/tencent/stat/common/StatLogger;
    //   129: aload_1
    //   130: invokevirtual 89	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   133: aload_2
    //   134: ifnull +9 -> 143
    //   137: aload_2
    //   138: invokeinterface 92 1 0
    //   143: return
    //   144: aload_2
    //   145: ifnull -2 -> 143
    //   148: aload_2
    //   149: invokeinterface 92 1 0
    //   154: return
    //   155: astore_3
    //   156: aconst_null
    //   157: astore_2
    //   158: aload_2
    //   159: ifnull +9 -> 168
    //   162: aload_2
    //   163: invokeinterface 92 1 0
    //   168: aload_3
    //   169: athrow
    //   170: astore_3
    //   171: goto -13 -> 158
    //   174: astore_1
    //   175: aconst_null
    //   176: astore_2
    //   177: goto -51 -> 126
    //
    // Exception table:
    //   from	to	target	type
    //   26	122	125	java/lang/Throwable
    //   0	23	155	finally
    //   26	122	170	finally
    //   126	133	170	finally
    //   0	23	174	java/lang/Throwable
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.t
 * JD-Core Version:    0.6.0
 */
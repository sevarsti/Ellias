package com.tencent.qqgamemi.plugin;

import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.login.QMiLoginManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PluginOrderManager
{
  private static final String a = "PluginOrderManager";
  private static boolean c = true;
  private static long d = 0L;
  private static List e = new ArrayList();
  private static PluginOrderManager f = null;
  private String b = QMiCommon.a() + File.separator;

  public static PluginOrderManager a()
  {
    if (f == null)
      f = new PluginOrderManager();
    return f;
  }

  // ERROR //
  private void a(File paramFile, List paramList)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: new 64	java/io/ObjectOutputStream
    //   8: dup
    //   9: new 66	java/io/FileOutputStream
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 69	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   17: invokespecial 72	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   20: astore_3
    //   21: aload_3
    //   22: aload_2
    //   23: invokeinterface 78 1 0
    //   28: invokevirtual 82	java/io/ObjectOutputStream:writeInt	(I)V
    //   31: aload_2
    //   32: invokeinterface 86 1 0
    //   37: astore 10
    //   39: aload 10
    //   41: invokeinterface 92 1 0
    //   46: ifeq +80 -> 126
    //   49: aload 10
    //   51: invokeinterface 96 1 0
    //   56: checkcast 98	com/tencent/qqgamemi/plugin/PluginOrder
    //   59: astore 12
    //   61: ldc 8
    //   63: new 35	java/lang/StringBuilder
    //   66: dup
    //   67: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   70: ldc 100
    //   72: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: aload 12
    //   77: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   80: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: invokestatic 108	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   86: aload_3
    //   87: aload 12
    //   89: invokevirtual 112	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   92: goto -53 -> 39
    //   95: astore 8
    //   97: ldc 8
    //   99: ldc 114
    //   101: invokestatic 116	com/tencent/qqgamemi/common/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   104: aload 8
    //   106: invokevirtual 119	java/io/FileNotFoundException:printStackTrace	()V
    //   109: aload_3
    //   110: ifnull -106 -> 4
    //   113: aload_3
    //   114: invokevirtual 122	java/io/ObjectOutputStream:close	()V
    //   117: return
    //   118: astore 9
    //   120: aload 9
    //   122: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   125: return
    //   126: aload_3
    //   127: ifnull -123 -> 4
    //   130: aload_3
    //   131: invokevirtual 122	java/io/ObjectOutputStream:close	()V
    //   134: return
    //   135: astore 11
    //   137: aload 11
    //   139: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   142: return
    //   143: astore 4
    //   145: aconst_null
    //   146: astore_3
    //   147: ldc 8
    //   149: ldc 125
    //   151: invokestatic 116	com/tencent/qqgamemi/common/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   154: aload 4
    //   156: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   159: aload_3
    //   160: ifnull -156 -> 4
    //   163: aload_3
    //   164: invokevirtual 122	java/io/ObjectOutputStream:close	()V
    //   167: return
    //   168: astore 7
    //   170: aload 7
    //   172: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   175: return
    //   176: astore 5
    //   178: aconst_null
    //   179: astore_3
    //   180: aload_3
    //   181: ifnull +7 -> 188
    //   184: aload_3
    //   185: invokevirtual 122	java/io/ObjectOutputStream:close	()V
    //   188: aload 5
    //   190: athrow
    //   191: astore 6
    //   193: aload 6
    //   195: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   198: goto -10 -> 188
    //   201: astore 5
    //   203: goto -23 -> 180
    //   206: astore 4
    //   208: goto -61 -> 147
    //   211: astore 8
    //   213: aconst_null
    //   214: astore_3
    //   215: goto -118 -> 97
    //
    // Exception table:
    //   from	to	target	type
    //   21	39	95	java/io/FileNotFoundException
    //   39	92	95	java/io/FileNotFoundException
    //   113	117	118	java/io/IOException
    //   130	134	135	java/io/IOException
    //   5	21	143	java/io/IOException
    //   163	167	168	java/io/IOException
    //   5	21	176	finally
    //   184	188	191	java/io/IOException
    //   21	39	201	finally
    //   39	92	201	finally
    //   97	109	201	finally
    //   147	159	201	finally
    //   21	39	206	java/io/IOException
    //   39	92	206	java/io/IOException
    //   5	21	211	java/io/FileNotFoundException
  }

  private String c()
  {
    return this.b + d + ".pluginOrder";
  }

  private void d()
  {
    if (d != QMiLoginManager.a().i())
    {
      c = true;
      d = QMiLoginManager.a().i();
      TLog.c("PluginOrderManager", "reset uin:" + d);
    }
  }

  private boolean e()
  {
    e.clear();
    File localFile = new File(c());
    if (localFile.exists())
    {
      e.addAll(a(localFile));
      return true;
    }
    TLog.c("PluginOrderManager", "file is not exist");
    return false;
  }

  // ERROR //
  public List a(File paramFile)
  {
    // Byte code:
    //   0: new 25	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 28	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: new 167	java/io/ObjectInputStream
    //   11: dup
    //   12: new 169	java/io/FileInputStream
    //   15: dup
    //   16: aload_1
    //   17: invokespecial 170	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   20: invokespecial 173	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   23: astore_3
    //   24: aload_3
    //   25: invokevirtual 176	java/io/ObjectInputStream:readInt	()I
    //   28: istore 14
    //   30: iconst_0
    //   31: istore 15
    //   33: iload 15
    //   35: iload 14
    //   37: if_icmpge +52 -> 89
    //   40: aload_3
    //   41: invokevirtual 179	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   44: checkcast 98	com/tencent/qqgamemi/plugin/PluginOrder
    //   47: astore 16
    //   49: ldc 8
    //   51: new 35	java/lang/StringBuilder
    //   54: dup
    //   55: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   58: ldc 181
    //   60: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload 16
    //   65: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   68: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: invokestatic 108	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   74: aload_2
    //   75: aload 16
    //   77: invokeinterface 185 2 0
    //   82: pop
    //   83: iinc 15 1
    //   86: goto -53 -> 33
    //   89: aload_3
    //   90: ifnull +7 -> 97
    //   93: aload_3
    //   94: invokevirtual 186	java/io/ObjectInputStream:close	()V
    //   97: aload_2
    //   98: areturn
    //   99: astore 18
    //   101: aload 18
    //   103: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   106: aload_2
    //   107: areturn
    //   108: astore 4
    //   110: aconst_null
    //   111: astore_3
    //   112: ldc 8
    //   114: ldc 188
    //   116: invokestatic 116	com/tencent/qqgamemi/common/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   119: aload 4
    //   121: invokevirtual 189	java/io/StreamCorruptedException:printStackTrace	()V
    //   124: aload_3
    //   125: ifnull -28 -> 97
    //   128: aload_3
    //   129: invokevirtual 186	java/io/ObjectInputStream:close	()V
    //   132: aload_2
    //   133: areturn
    //   134: astore 7
    //   136: aload 7
    //   138: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   141: aload_2
    //   142: areturn
    //   143: astore 8
    //   145: aconst_null
    //   146: astore_3
    //   147: ldc 8
    //   149: ldc 191
    //   151: invokestatic 116	com/tencent/qqgamemi/common/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   154: aload 8
    //   156: invokevirtual 119	java/io/FileNotFoundException:printStackTrace	()V
    //   159: aload_3
    //   160: ifnull -63 -> 97
    //   163: aload_3
    //   164: invokevirtual 186	java/io/ObjectInputStream:close	()V
    //   167: aload_2
    //   168: areturn
    //   169: astore 9
    //   171: aload 9
    //   173: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   176: aload_2
    //   177: areturn
    //   178: astore 10
    //   180: aconst_null
    //   181: astore_3
    //   182: ldc 8
    //   184: ldc 193
    //   186: invokestatic 116	com/tencent/qqgamemi/common/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   189: aload 10
    //   191: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   194: aload_3
    //   195: ifnull -98 -> 97
    //   198: aload_3
    //   199: invokevirtual 186	java/io/ObjectInputStream:close	()V
    //   202: aload_2
    //   203: areturn
    //   204: astore 11
    //   206: aload 11
    //   208: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   211: aload_2
    //   212: areturn
    //   213: astore 12
    //   215: aconst_null
    //   216: astore_3
    //   217: ldc 8
    //   219: ldc 195
    //   221: invokestatic 116	com/tencent/qqgamemi/common/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   224: aload 12
    //   226: invokevirtual 196	java/lang/ClassNotFoundException:printStackTrace	()V
    //   229: aload_3
    //   230: ifnull -133 -> 97
    //   233: aload_3
    //   234: invokevirtual 186	java/io/ObjectInputStream:close	()V
    //   237: aload_2
    //   238: areturn
    //   239: astore 13
    //   241: aload 13
    //   243: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   246: aload_2
    //   247: areturn
    //   248: astore 5
    //   250: aconst_null
    //   251: astore_3
    //   252: aload_3
    //   253: ifnull +7 -> 260
    //   256: aload_3
    //   257: invokevirtual 186	java/io/ObjectInputStream:close	()V
    //   260: aload 5
    //   262: athrow
    //   263: astore 6
    //   265: aload 6
    //   267: invokevirtual 123	java/io/IOException:printStackTrace	()V
    //   270: goto -10 -> 260
    //   273: astore 5
    //   275: goto -23 -> 252
    //   278: astore 12
    //   280: goto -63 -> 217
    //   283: astore 10
    //   285: goto -103 -> 182
    //   288: astore 8
    //   290: goto -143 -> 147
    //   293: astore 4
    //   295: goto -183 -> 112
    //
    // Exception table:
    //   from	to	target	type
    //   93	97	99	java/io/IOException
    //   8	24	108	java/io/StreamCorruptedException
    //   128	132	134	java/io/IOException
    //   8	24	143	java/io/FileNotFoundException
    //   163	167	169	java/io/IOException
    //   8	24	178	java/io/IOException
    //   198	202	204	java/io/IOException
    //   8	24	213	java/lang/ClassNotFoundException
    //   233	237	239	java/io/IOException
    //   8	24	248	finally
    //   256	260	263	java/io/IOException
    //   24	30	273	finally
    //   40	83	273	finally
    //   112	124	273	finally
    //   147	159	273	finally
    //   182	194	273	finally
    //   217	229	273	finally
    //   24	30	278	java/lang/ClassNotFoundException
    //   40	83	278	java/lang/ClassNotFoundException
    //   24	30	283	java/io/IOException
    //   40	83	283	java/io/IOException
    //   24	30	288	java/io/FileNotFoundException
    //   40	83	288	java/io/FileNotFoundException
    //   24	30	293	java/io/StreamCorruptedException
    //   40	83	293	java/io/StreamCorruptedException
  }

  public void a(List paramList)
  {
    d();
    File localFile;
    if (paramList != null)
    {
      c = true;
      localFile = new File(c());
      if (localFile.exists());
    }
    try
    {
      localFile.createNewFile();
      a(localFile, paramList);
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public List b()
  {
    d();
    if (c)
    {
      TLog.c("PluginOrderManager", "getPluginOrder");
      if (e());
    }
    return e;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.PluginOrderManager
 * JD-Core Version:    0.6.0
 */
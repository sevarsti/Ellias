package com.tencent.component.net.download.multiplex.download;

import com.tencent.component.utils.log.LogUtil;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DownloadSections
{
  private static final String c = "DownloadSections";
  protected File a;
  protected long b = 0L;
  private Map d = new ConcurrentHashMap();

  public DownloadSections.DownloadSection a(int paramInt)
  {
    return (DownloadSections.DownloadSection)this.d.get(Integer.valueOf(paramInt));
  }

  protected File a(String paramString1, String paramString2)
  {
    return new File(paramString1, "." + paramString2 + ".dltmp");
  }

  protected void a()
  {
    a(this.a);
  }

  // ERROR //
  public void a(long paramLong, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 63	com/tencent/component/net/download/multiplex/download/DownloadSections:a	Ljava/io/File;
    //   4: ifnonnull +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: getfield 63	com/tencent/component/net/download/multiplex/download/DownloadSections:a	Ljava/io/File;
    //   12: invokevirtual 75	java/io/File:exists	()Z
    //   15: ifne +11 -> 26
    //   18: aload_0
    //   19: getfield 63	com/tencent/component/net/download/multiplex/download/DownloadSections:a	Ljava/io/File;
    //   22: invokevirtual 78	java/io/File:createNewFile	()Z
    //   25: pop
    //   26: aload_0
    //   27: getfield 63	com/tencent/component/net/download/multiplex/download/DownloadSections:a	Ljava/io/File;
    //   30: ifnull +99 -> 129
    //   33: aload_0
    //   34: getfield 63	com/tencent/component/net/download/multiplex/download/DownloadSections:a	Ljava/io/File;
    //   37: invokevirtual 75	java/io/File:exists	()Z
    //   40: ifeq +89 -> 129
    //   43: new 80	java/io/RandomAccessFile
    //   46: dup
    //   47: aload_0
    //   48: getfield 63	com/tencent/component/net/download/multiplex/download/DownloadSections:a	Ljava/io/File;
    //   51: ldc 82
    //   53: invokespecial 85	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   56: astore 5
    //   58: aload 5
    //   60: ifnull +72 -> 132
    //   63: aload 5
    //   65: lconst_0
    //   66: invokevirtual 89	java/io/RandomAccessFile:seek	(J)V
    //   69: aload 5
    //   71: lload_1
    //   72: invokevirtual 92	java/io/RandomAccessFile:writeLong	(J)V
    //   75: iconst_0
    //   76: istore 10
    //   78: iload 10
    //   80: iload_3
    //   81: if_icmpge +51 -> 132
    //   84: aload_0
    //   85: getfield 23	com/tencent/component/net/download/multiplex/download/DownloadSections:d	Ljava/util/Map;
    //   88: iload 10
    //   90: invokestatic 32	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   93: invokeinterface 38 2 0
    //   98: checkcast 40	com/tencent/component/net/download/multiplex/download/DownloadSections$DownloadSection
    //   101: astore 11
    //   103: aload 11
    //   105: ifnull +10 -> 115
    //   108: aload 11
    //   110: aload 5
    //   112: invokevirtual 95	com/tencent/component/net/download/multiplex/download/DownloadSections$DownloadSection:a	(Ljava/io/DataOutput;)V
    //   115: iinc 10 1
    //   118: goto -40 -> 78
    //   121: astore 12
    //   123: aload 12
    //   125: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   128: return
    //   129: aconst_null
    //   130: astore 5
    //   132: aload 5
    //   134: ifnull -127 -> 7
    //   137: aload 5
    //   139: invokevirtual 101	java/io/RandomAccessFile:close	()V
    //   142: return
    //   143: astore 9
    //   145: aload 9
    //   147: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   150: return
    //   151: astore 7
    //   153: aconst_null
    //   154: astore 5
    //   156: aload 7
    //   158: invokevirtual 102	java/lang/Exception:printStackTrace	()V
    //   161: aload 5
    //   163: ifnull -156 -> 7
    //   166: aload 5
    //   168: invokevirtual 101	java/io/RandomAccessFile:close	()V
    //   171: return
    //   172: astore 8
    //   174: aload 8
    //   176: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   179: return
    //   180: astore 4
    //   182: aconst_null
    //   183: astore 5
    //   185: aload 5
    //   187: ifnull +8 -> 195
    //   190: aload 5
    //   192: invokevirtual 101	java/io/RandomAccessFile:close	()V
    //   195: aload 4
    //   197: athrow
    //   198: astore 6
    //   200: aload 6
    //   202: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   205: goto -10 -> 195
    //   208: astore 4
    //   210: goto -25 -> 185
    //   213: astore 7
    //   215: goto -59 -> 156
    //
    // Exception table:
    //   from	to	target	type
    //   18	26	121	java/io/IOException
    //   137	142	143	java/io/IOException
    //   26	58	151	java/lang/Exception
    //   166	171	172	java/io/IOException
    //   26	58	180	finally
    //   190	195	198	java/io/IOException
    //   63	75	208	finally
    //   84	103	208	finally
    //   108	115	208	finally
    //   156	161	208	finally
    //   63	75	213	java/lang/Exception
    //   84	103	213	java/lang/Exception
    //   108	115	213	java/lang/Exception
  }

  public void a(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists()))
      paramFile.delete();
  }

  public void a(String paramString1, String paramString2, int paramInt)
  {
    this.b = -1L;
    this.a = a(paramString1, paramString2);
    if (!this.a.exists())
      this.a.createNewFile();
    File localFile = this.a;
    int i = 0;
    RandomAccessFile localRandomAccessFile;
    if (localFile != null)
      localRandomAccessFile = new RandomAccessFile(this.a, "r");
    try
    {
      if (this.a != null)
      {
        this.b = localRandomAccessFile.readLong();
        for (int j = 0; j < paramInt; j++)
        {
          DownloadSections.DownloadSection localDownloadSection = new DownloadSections.DownloadSection(j);
          localDownloadSection.a(localRandomAccessFile);
          this.d.put(Integer.valueOf(j), localDownloadSection);
        }
      }
      if (localRandomAccessFile != null)
        try
        {
          localRandomAccessFile.close();
          i = 1;
          if (i == 0)
            a(true);
          return;
        }
        catch (IOException localIOException3)
        {
          while (true)
          {
            localIOException3.printStackTrace();
            i = 1;
          }
        }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        i = 0;
        if (localRandomAccessFile == null)
          continue;
        try
        {
          localRandomAccessFile.close();
          i = 0;
        }
        catch (IOException localIOException2)
        {
          localIOException2.printStackTrace();
          i = 0;
        }
      }
    }
    finally
    {
      while (true)
      {
        if (localRandomAccessFile != null);
        try
        {
          localRandomAccessFile.close();
          throw localObject;
        }
        catch (IOException localIOException1)
        {
          while (true)
            localIOException1.printStackTrace();
        }
        i = 1;
      }
    }
  }

  public void a(boolean paramBoolean)
  {
    LogUtil.d("DownloadSections", "clear, clearFiles=true");
    if (paramBoolean)
      a();
    this.d.clear();
  }

  // ERROR //
  public boolean a(String paramString1, String paramString2, long paramLong, int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 6
    //   3: new 20	java/util/concurrent/ConcurrentHashMap
    //   6: dup
    //   7: invokespecial 21	java/util/concurrent/ConcurrentHashMap:<init>	()V
    //   10: astore 7
    //   12: lconst_0
    //   13: lstore 8
    //   15: aload_0
    //   16: aload_1
    //   17: aload_2
    //   18: invokevirtual 110	com/tencent/component/net/download/multiplex/download/DownloadSections:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;
    //   21: astore 10
    //   23: aload 10
    //   25: ifnull +300 -> 325
    //   28: aload 10
    //   30: invokevirtual 75	java/io/File:exists	()Z
    //   33: ifeq +292 -> 325
    //   36: new 80	java/io/RandomAccessFile
    //   39: dup
    //   40: aload 10
    //   42: ldc 112
    //   44: invokespecial 85	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   47: astore 15
    //   49: aload 15
    //   51: astore 16
    //   53: aload 16
    //   55: ifnull +80 -> 135
    //   58: aload 16
    //   60: invokevirtual 116	java/io/RandomAccessFile:readLong	()J
    //   63: lstore 8
    //   65: iload 6
    //   67: ifeq +68 -> 135
    //   70: iconst_0
    //   71: istore 24
    //   73: iload 24
    //   75: iload 5
    //   77: if_icmpge +58 -> 135
    //   80: new 40	com/tencent/component/net/download/multiplex/download/DownloadSections$DownloadSection
    //   83: dup
    //   84: iload 24
    //   86: invokespecial 119	com/tencent/component/net/download/multiplex/download/DownloadSections$DownloadSection:<init>	(I)V
    //   89: astore 25
    //   91: aload 25
    //   93: aload 16
    //   95: invokevirtual 122	com/tencent/component/net/download/multiplex/download/DownloadSections$DownloadSection:a	(Ljava/io/DataInput;)V
    //   98: aload 7
    //   100: iload 24
    //   102: invokestatic 32	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   105: aload 25
    //   107: invokeinterface 126 3 0
    //   112: pop
    //   113: iinc 24 1
    //   116: goto -43 -> 73
    //   119: astore 27
    //   121: aload 27
    //   123: invokevirtual 144	java/io/FileNotFoundException:printStackTrace	()V
    //   126: iconst_0
    //   127: istore 6
    //   129: aconst_null
    //   130: astore 16
    //   132: goto -79 -> 53
    //   135: lload 8
    //   137: lstore 17
    //   139: aload 16
    //   141: ifnull +8 -> 149
    //   144: aload 16
    //   146: invokevirtual 101	java/io/RandomAccessFile:close	()V
    //   149: lload 17
    //   151: lstore 8
    //   153: iload 6
    //   155: istore 11
    //   157: aload 7
    //   159: ifnull +178 -> 337
    //   162: aload 7
    //   164: invokeinterface 148 1 0
    //   169: iload 5
    //   171: if_icmpne +166 -> 337
    //   174: iconst_0
    //   175: istore 13
    //   177: iload 13
    //   179: iload 5
    //   181: if_icmpge +162 -> 343
    //   184: aload 7
    //   186: iload 13
    //   188: invokestatic 32	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   191: invokeinterface 38 2 0
    //   196: checkcast 40	com/tencent/component/net/download/multiplex/download/DownloadSections$DownloadSection
    //   199: astore 14
    //   201: aload 14
    //   203: ifnull +128 -> 331
    //   206: aload 14
    //   208: invokestatic 151	com/tencent/component/net/download/multiplex/download/DownloadSections$DownloadSection:a	(Lcom/tencent/component/net/download/multiplex/download/DownloadSections$DownloadSection;)J
    //   211: lload_3
    //   212: lconst_1
    //   213: lsub
    //   214: lcmp
    //   215: ifle +116 -> 331
    //   218: iconst_0
    //   219: istore 12
    //   221: iload 12
    //   223: ifeq +21 -> 244
    //   226: aload_0
    //   227: aload 7
    //   229: putfield 23	com/tencent/component/net/download/multiplex/download/DownloadSections:d	Ljava/util/Map;
    //   232: aload_0
    //   233: lload 8
    //   235: putfield 25	com/tencent/component/net/download/multiplex/download/DownloadSections:b	J
    //   238: aload_0
    //   239: aload 10
    //   241: putfield 63	com/tencent/component/net/download/multiplex/download/DownloadSections:a	Ljava/io/File;
    //   244: iload 12
    //   246: ireturn
    //   247: astore 19
    //   249: aload 19
    //   251: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   254: goto -105 -> 149
    //   257: astore 22
    //   259: lload 8
    //   261: lstore 17
    //   263: aload 22
    //   265: invokevirtual 102	java/lang/Exception:printStackTrace	()V
    //   268: iconst_0
    //   269: istore 6
    //   271: aload 16
    //   273: ifnull -124 -> 149
    //   276: aload 16
    //   278: invokevirtual 101	java/io/RandomAccessFile:close	()V
    //   281: iconst_0
    //   282: istore 6
    //   284: goto -135 -> 149
    //   287: astore 23
    //   289: aload 23
    //   291: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   294: iconst_0
    //   295: istore 6
    //   297: goto -148 -> 149
    //   300: astore 20
    //   302: aload 16
    //   304: ifnull +8 -> 312
    //   307: aload 16
    //   309: invokevirtual 101	java/io/RandomAccessFile:close	()V
    //   312: aload 20
    //   314: athrow
    //   315: astore 21
    //   317: aload 21
    //   319: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   322: goto -10 -> 312
    //   325: iconst_0
    //   326: istore 11
    //   328: goto -171 -> 157
    //   331: iinc 13 1
    //   334: goto -157 -> 177
    //   337: iconst_0
    //   338: istore 12
    //   340: goto -119 -> 221
    //   343: iload 11
    //   345: istore 12
    //   347: goto -126 -> 221
    //
    // Exception table:
    //   from	to	target	type
    //   36	49	119	java/io/FileNotFoundException
    //   144	149	247	java/io/IOException
    //   58	65	257	java/lang/Exception
    //   80	113	257	java/lang/Exception
    //   276	281	287	java/io/IOException
    //   58	65	300	finally
    //   80	113	300	finally
    //   263	268	300	finally
    //   307	312	315	java/io/IOException
  }

  public int b()
  {
    return this.d.size();
  }

  public DownloadSections.DownloadSection b(int paramInt)
  {
    DownloadSections.DownloadSection localDownloadSection = new DownloadSections.DownloadSection(paramInt);
    localDownloadSection.a = 0L;
    DownloadSections.DownloadSection.a(localDownloadSection, -1L);
    localDownloadSection.b = 0L;
    this.d.put(Integer.valueOf(paramInt), localDownloadSection);
    return localDownloadSection;
  }

  public void b(String paramString1, String paramString2)
  {
    this.a = a(paramString1, paramString2);
  }

  protected boolean b(long paramLong, int paramInt)
  {
    int i;
    if ((this.d != null) && (this.d.size() == paramInt))
      i = 0;
    while (i < paramInt)
    {
      DownloadSections.DownloadSection localDownloadSection = (DownloadSections.DownloadSection)this.d.get(Integer.valueOf(i));
      if ((localDownloadSection != null) && (DownloadSections.DownloadSection.a(localDownloadSection) > paramLong - 1L))
        return false;
      i++;
      continue;
      return false;
    }
    return true;
  }

  public long c()
  {
    return this.b;
  }

  public boolean d()
  {
    if (this.d == null)
      return false;
    Iterator localIterator = this.d.values().iterator();
    while (localIterator.hasNext())
      if (!((DownloadSections.DownloadSection)localIterator.next()).b())
        return false;
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.DownloadSections
 * JD-Core Version:    0.6.0
 */
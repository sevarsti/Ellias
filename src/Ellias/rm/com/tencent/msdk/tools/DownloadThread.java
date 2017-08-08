package com.tencent.msdk.tools;

import android.os.Handler;
import android.os.Message;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadThread extends Thread
{
  public static final int THREAD_BEGIN = 1;
  public static final int THREAD_FINISHED_FAIL = 3;
  public static final int THREAD_FINISHED_SUCC = 2;
  private static Handler myHandler;
  private static Lock sLock = new ReentrantLock();
  private static Queue<DownloadThread> threads = new LinkedList();
  private long mFileLength;
  private URL mFileUrl;
  private String mHashValue;
  private boolean mIsStarted = false;
  private String mLocalFilePath;
  private float mPercent = 0.0F;

  static
  {
    myHandler = new Handler()
    {
      public void handleMessage(Message paramMessage)
      {
        switch (paramMessage.what)
        {
        default:
          return;
        case 1:
          DownloadThread.sLock.lock();
          if (!DownloadThread.threads.isEmpty())
          {
            DownloadThread localDownloadThread = (DownloadThread)DownloadThread.threads.poll();
            if (!localDownloadThread.isStarted())
              localDownloadThread.start();
          }
          DownloadThread.sLock.unlock();
          return;
        case 2:
          DownloadThread.sLock.lock();
          if (!DownloadThread.threads.isEmpty())
          {
            Message localMessage2 = new Message();
            localMessage2.what = 1;
            sendMessage(localMessage2);
          }
          DownloadThread.sLock.unlock();
          return;
        case 3:
        }
        DownloadThread.sLock.lock();
        if (!DownloadThread.threads.isEmpty())
        {
          Message localMessage1 = new Message();
          localMessage1.what = 1;
          sendMessage(localMessage1);
        }
        DownloadThread.sLock.unlock();
      }
    };
  }

  public DownloadThread(URL paramURL, String paramString1, String paramString2)
  {
    this.mFileUrl = paramURL;
    this.mLocalFilePath = paramString1;
    this.mHashValue = paramString2;
  }

  public static void addToDownloadQueue(URL paramURL, String paramString1, String paramString2)
  {
    if ((paramURL == null) || (T.ckIsEmpty(paramString1)) || (T.ckIsEmpty(paramString2)))
    {
      Logger.w("url or filePath or hashValue is null");
      return;
    }
    sLock.lock();
    try
    {
      DownloadThread localDownloadThread = new DownloadThread(paramURL, paramString1, paramString2);
      if (!threads.contains(localDownloadThread))
        threads.add(localDownloadThread);
      sLock.unlock();
      Message localMessage = new Message();
      localMessage.what = 1;
      myHandler.sendMessage(localMessage);
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        sLock.unlock();
      }
    }
    finally
    {
      sLock.unlock();
    }
    throw localObject;
  }

  public static void delFileByPath(String paramString)
  {
    if (T.ckIsEmpty(paramString));
    File localFile;
    do
    {
      return;
      localFile = new File(paramString);
    }
    while (localFile == null);
    localFile.delete();
  }

  public float getPercent()
  {
    return this.mPercent;
  }

  public URL getUrl()
  {
    return this.mFileUrl;
  }

  public boolean isStarted()
  {
    return this.mIsStarted;
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: putfield 54	com/tencent/msdk/tools/DownloadThread:mIsStarted	Z
    //   5: aconst_null
    //   6: astore_1
    //   7: aconst_null
    //   8: astore_2
    //   9: aload_0
    //   10: getfield 56	com/tencent/msdk/tools/DownloadThread:mFileUrl	Ljava/net/URL;
    //   13: invokevirtual 138	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   16: checkcast 140	java/net/HttpURLConnection
    //   19: astore 7
    //   21: aload 7
    //   23: sipush 5000
    //   26: invokevirtual 144	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   29: aload 7
    //   31: ldc 146
    //   33: invokevirtual 149	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   36: aload 7
    //   38: ldc 151
    //   40: ldc 153
    //   42: invokevirtual 157	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   45: aload 7
    //   47: ldc 159
    //   49: ldc 161
    //   51: invokevirtual 157	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   54: aload 7
    //   56: ldc 163
    //   58: ldc 165
    //   60: invokevirtual 157	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   63: aload 7
    //   65: ldc 167
    //   67: ldc 169
    //   69: invokevirtual 157	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   72: aload 7
    //   74: invokevirtual 172	java/net/HttpURLConnection:connect	()V
    //   77: aload_0
    //   78: aload 7
    //   80: invokevirtual 176	java/net/HttpURLConnection:getContentLength	()I
    //   83: i2l
    //   84: putfield 178	com/tencent/msdk/tools/DownloadThread:mFileLength	J
    //   87: sipush 4096
    //   90: newarray byte
    //   92: astore 8
    //   94: aload_0
    //   95: getfield 178	com/tencent/msdk/tools/DownloadThread:mFileLength	J
    //   98: invokestatic 184	com/tencent/msdk/stat/ReportEvent:ReportPicLength	(J)V
    //   101: new 186	java/io/BufferedInputStream
    //   104: dup
    //   105: aload 7
    //   107: invokevirtual 190	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   110: invokespecial 193	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   113: astore 9
    //   115: new 118	java/io/File
    //   118: dup
    //   119: new 195	java/lang/StringBuilder
    //   122: dup
    //   123: invokespecial 196	java/lang/StringBuilder:<init>	()V
    //   126: aload_0
    //   127: getfield 58	com/tencent/msdk/tools/DownloadThread:mLocalFilePath	Ljava/lang/String;
    //   130: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: ldc 202
    //   135: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: invokevirtual 206	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   141: invokespecial 120	java/io/File:<init>	(Ljava/lang/String;)V
    //   144: astore 10
    //   146: new 208	java/io/FileOutputStream
    //   149: dup
    //   150: aload 10
    //   152: invokespecial 211	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   155: astore 11
    //   157: new 213	java/io/BufferedOutputStream
    //   160: dup
    //   161: aload 11
    //   163: invokespecial 216	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   166: astore 12
    //   168: lconst_0
    //   169: lstore 13
    //   171: ldc 218
    //   173: invokestatic 224	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   176: astore 15
    //   178: aload 9
    //   180: aload 8
    //   182: invokevirtual 228	java/io/BufferedInputStream:read	([B)I
    //   185: istore 16
    //   187: iload 16
    //   189: iconst_m1
    //   190: if_icmpeq +79 -> 269
    //   193: aload 12
    //   195: aload 8
    //   197: iconst_0
    //   198: iload 16
    //   200: invokevirtual 232	java/io/BufferedOutputStream:write	([BII)V
    //   203: aload 15
    //   205: aload 8
    //   207: iconst_0
    //   208: iload 16
    //   210: invokevirtual 235	java/security/MessageDigest:update	([BII)V
    //   213: aload 12
    //   215: invokevirtual 238	java/io/BufferedOutputStream:flush	()V
    //   218: lload 13
    //   220: iload 16
    //   222: i2l
    //   223: ladd
    //   224: lstore 13
    //   226: aload_0
    //   227: lload 13
    //   229: aload_0
    //   230: getfield 178	com/tencent/msdk/tools/DownloadThread:mFileLength	J
    //   233: ldiv
    //   234: l2f
    //   235: putfield 52	com/tencent/msdk/tools/DownloadThread:mPercent	F
    //   238: goto -60 -> 178
    //   241: astore_3
    //   242: aload 12
    //   244: astore_2
    //   245: aload 9
    //   247: astore_1
    //   248: aload_3
    //   249: invokevirtual 115	java/lang/Exception:printStackTrace	()V
    //   252: aload_1
    //   253: ifnull +7 -> 260
    //   256: aload_1
    //   257: invokevirtual 241	java/io/BufferedInputStream:close	()V
    //   260: aload_2
    //   261: ifnull +7 -> 268
    //   264: aload_2
    //   265: invokevirtual 242	java/io/BufferedOutputStream:close	()V
    //   268: return
    //   269: aload 15
    //   271: invokevirtual 246	java/security/MessageDigest:digest	()[B
    //   274: invokestatic 252	com/tencent/msdk/tools/HexUtil:bytes2HexStr	([B)Ljava/lang/String;
    //   277: getstatic 258	java/util/Locale:CHINA	Ljava/util/Locale;
    //   280: invokevirtual 264	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   283: astore 17
    //   285: new 102	android/os/Message
    //   288: dup
    //   289: invokespecial 103	android/os/Message:<init>	()V
    //   292: astore 18
    //   294: aload 17
    //   296: aload_0
    //   297: getfield 60	com/tencent/msdk/tools/DownloadThread:mHashValue	Ljava/lang/String;
    //   300: invokevirtual 267	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   303: ifeq +146 -> 449
    //   306: aload 10
    //   308: new 118	java/io/File
    //   311: dup
    //   312: aload_0
    //   313: getfield 58	com/tencent/msdk/tools/DownloadThread:mLocalFilePath	Ljava/lang/String;
    //   316: invokespecial 120	java/io/File:<init>	(Ljava/lang/String;)V
    //   319: invokevirtual 271	java/io/File:renameTo	(Ljava/io/File;)Z
    //   322: ifeq +65 -> 387
    //   325: aload 18
    //   327: iconst_2
    //   328: putfield 106	android/os/Message:what	I
    //   331: new 195	java/lang/StringBuilder
    //   334: dup
    //   335: invokespecial 196	java/lang/StringBuilder:<init>	()V
    //   338: ldc_w 273
    //   341: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: aload_0
    //   345: getfield 58	com/tencent/msdk/tools/DownloadThread:mLocalFilePath	Ljava/lang/String;
    //   348: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: invokevirtual 206	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   354: invokestatic 276	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   357: getstatic 48	com/tencent/msdk/tools/DownloadThread:myHandler	Landroid/os/Handler;
    //   360: aload 18
    //   362: invokevirtual 112	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   365: pop
    //   366: aload 9
    //   368: ifnull +8 -> 376
    //   371: aload 9
    //   373: invokevirtual 241	java/io/BufferedInputStream:close	()V
    //   376: aload 12
    //   378: ifnull +8 -> 386
    //   381: aload 12
    //   383: invokevirtual 242	java/io/BufferedOutputStream:close	()V
    //   386: return
    //   387: aload 18
    //   389: iconst_3
    //   390: putfield 106	android/os/Message:what	I
    //   393: new 195	java/lang/StringBuilder
    //   396: dup
    //   397: invokespecial 196	java/lang/StringBuilder:<init>	()V
    //   400: ldc_w 278
    //   403: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   406: aload_0
    //   407: getfield 58	com/tencent/msdk/tools/DownloadThread:mLocalFilePath	Ljava/lang/String;
    //   410: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   413: invokevirtual 206	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   416: invokestatic 276	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   419: goto -62 -> 357
    //   422: astore 4
    //   424: aload 12
    //   426: astore_2
    //   427: aload 9
    //   429: astore_1
    //   430: aload_1
    //   431: ifnull +7 -> 438
    //   434: aload_1
    //   435: invokevirtual 241	java/io/BufferedInputStream:close	()V
    //   438: aload_2
    //   439: ifnull +7 -> 446
    //   442: aload_2
    //   443: invokevirtual 242	java/io/BufferedOutputStream:close	()V
    //   446: aload 4
    //   448: athrow
    //   449: aload 18
    //   451: iconst_3
    //   452: putfield 106	android/os/Message:what	I
    //   455: new 195	java/lang/StringBuilder
    //   458: dup
    //   459: invokespecial 196	java/lang/StringBuilder:<init>	()V
    //   462: ldc_w 280
    //   465: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   468: aload 17
    //   470: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: ldc_w 282
    //   476: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   479: aload_0
    //   480: getfield 60	com/tencent/msdk/tools/DownloadThread:mHashValue	Ljava/lang/String;
    //   483: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: invokevirtual 206	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   489: invokestatic 81	com/tencent/msdk/tools/Logger:w	(Ljava/lang/String;)V
    //   492: getstatic 288	com/tencent/msdk/stat/eEVENT_TYPE:eEVENT_BASIC_MD5BAD	Lcom/tencent/msdk/stat/eEVENT_TYPE;
    //   495: invokestatic 292	com/tencent/msdk/stat/ReportEvent:ReportBasicClickEvent	(Lcom/tencent/msdk/stat/eEVENT_TYPE;)V
    //   498: aload_0
    //   499: getfield 58	com/tencent/msdk/tools/DownloadThread:mLocalFilePath	Ljava/lang/String;
    //   502: invokestatic 294	com/tencent/msdk/tools/DownloadThread:delFileByPath	(Ljava/lang/String;)V
    //   505: goto -148 -> 357
    //   508: astore 20
    //   510: aload 20
    //   512: invokevirtual 295	java/io/IOException:printStackTrace	()V
    //   515: return
    //   516: astore 6
    //   518: aload 6
    //   520: invokevirtual 295	java/io/IOException:printStackTrace	()V
    //   523: return
    //   524: astore 5
    //   526: aload 5
    //   528: invokevirtual 295	java/io/IOException:printStackTrace	()V
    //   531: goto -85 -> 446
    //   534: astore 4
    //   536: goto -106 -> 430
    //   539: astore 4
    //   541: aload 9
    //   543: astore_1
    //   544: aconst_null
    //   545: astore_2
    //   546: goto -116 -> 430
    //   549: astore_3
    //   550: aconst_null
    //   551: astore_1
    //   552: aconst_null
    //   553: astore_2
    //   554: goto -306 -> 248
    //   557: astore_3
    //   558: aload 9
    //   560: astore_1
    //   561: aconst_null
    //   562: astore_2
    //   563: goto -315 -> 248
    //
    // Exception table:
    //   from	to	target	type
    //   171	178	241	java/lang/Exception
    //   178	187	241	java/lang/Exception
    //   193	218	241	java/lang/Exception
    //   226	238	241	java/lang/Exception
    //   269	357	241	java/lang/Exception
    //   357	366	241	java/lang/Exception
    //   387	419	241	java/lang/Exception
    //   449	505	241	java/lang/Exception
    //   171	178	422	finally
    //   178	187	422	finally
    //   193	218	422	finally
    //   226	238	422	finally
    //   269	357	422	finally
    //   357	366	422	finally
    //   387	419	422	finally
    //   449	505	422	finally
    //   371	376	508	java/io/IOException
    //   381	386	508	java/io/IOException
    //   256	260	516	java/io/IOException
    //   264	268	516	java/io/IOException
    //   434	438	524	java/io/IOException
    //   442	446	524	java/io/IOException
    //   9	115	534	finally
    //   248	252	534	finally
    //   115	168	539	finally
    //   9	115	549	java/lang/Exception
    //   115	168	557	java/lang/Exception
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.DownloadThread
 * JD-Core Version:    0.6.0
 */
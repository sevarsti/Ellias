package com.tencent.msdk.notice;

import com.tencent.msdk.tools.DownloadThread;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;

public class NoticePic
{
  public String mNoticeId = "";
  public String mPicHash = "";
  public String mPicUrl = "";
  public eMSDK_SCREENDIR mScreenDir = eMSDK_SCREENDIR.eMSDK_SCREENDIR_SENSOR;

  public NoticePic()
  {
  }

  public NoticePic(String paramString1, String paramString2, eMSDK_SCREENDIR parameMSDK_SCREENDIR, String paramString3)
  {
    this.mNoticeId = paramString1;
    this.mPicUrl = paramString2;
    this.mScreenDir = parameMSDK_SCREENDIR;
    this.mPicHash = paramString3;
  }

  public static Boolean checkNoticePicExist(String paramString1, String paramString2, String paramString3)
  {
    if (T.ckIsEmpty(paramString2))
      return Boolean.valueOf(false);
    if (new File(getFilePathByNoticeIdAndHashValue(paramString1, paramString2, paramString3)).exists())
      return Boolean.valueOf(true);
    return Boolean.valueOf(false);
  }

  public static Boolean checkNoticePicIsRight(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if ((T.ckIsEmpty(paramString2)) || (T.ckIsEmpty(paramString1)))
      return Boolean.valueOf(false);
    File localFile = new File(getFilePathByNoticeIdAndHashValue(paramString1, paramString3, paramString4));
    if (localFile.exists())
    {
      if (checkPicMd5(localFile, paramString2).booleanValue())
        return Boolean.valueOf(true);
      localFile.delete();
      return Boolean.valueOf(false);
    }
    return Boolean.valueOf(false);
  }

  // ERROR //
  public static Boolean checkPicMd5(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 77	java/io/FileInputStream
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 80	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   10: astore_3
    //   11: aload_3
    //   12: invokevirtual 84	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   15: getstatic 90	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   18: lconst_0
    //   19: aload_0
    //   20: invokevirtual 94	java/io/File:length	()J
    //   23: invokevirtual 100	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   26: astore 13
    //   28: ldc 102
    //   30: invokestatic 108	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   33: astore 14
    //   35: aload 14
    //   37: aload 13
    //   39: invokevirtual 112	java/security/MessageDigest:update	(Ljava/nio/ByteBuffer;)V
    //   42: aload 14
    //   44: invokevirtual 116	java/security/MessageDigest:digest	()[B
    //   47: invokestatic 122	com/tencent/msdk/tools/HexUtil:bytes2HexStr	([B)Ljava/lang/String;
    //   50: getstatic 128	java/util/Locale:CHINA	Ljava/util/Locale;
    //   53: invokevirtual 134	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   56: astore 15
    //   58: new 136	java/lang/StringBuilder
    //   61: dup
    //   62: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   65: ldc 139
    //   67: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload 15
    //   72: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: ldc 145
    //   77: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: aload_1
    //   81: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokestatic 154	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   90: aload 15
    //   92: aload_1
    //   93: invokevirtual 157	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   96: ifeq +34 -> 130
    //   99: iconst_1
    //   100: invokestatic 44	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   103: astore 18
    //   105: aload 18
    //   107: astore 8
    //   109: aload_3
    //   110: ifnull +7 -> 117
    //   113: aload_3
    //   114: invokevirtual 160	java/io/FileInputStream:close	()V
    //   117: aload 8
    //   119: areturn
    //   120: astore 19
    //   122: aload 19
    //   124: invokevirtual 163	java/io/IOException:printStackTrace	()V
    //   127: goto -10 -> 117
    //   130: iconst_0
    //   131: invokestatic 44	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   134: astore 16
    //   136: aload_3
    //   137: ifnull +7 -> 144
    //   140: aload_3
    //   141: invokevirtual 160	java/io/FileInputStream:close	()V
    //   144: aload 16
    //   146: areturn
    //   147: astore 17
    //   149: aload 17
    //   151: invokevirtual 163	java/io/IOException:printStackTrace	()V
    //   154: goto -10 -> 144
    //   157: astore 20
    //   159: ldc 165
    //   161: invokestatic 168	com/tencent/msdk/tools/Logger:e	(Ljava/lang/String;)V
    //   164: iconst_0
    //   165: invokestatic 44	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   168: astore 7
    //   170: aload 7
    //   172: astore 8
    //   174: aload_2
    //   175: ifnull -58 -> 117
    //   178: aload_2
    //   179: invokevirtual 160	java/io/FileInputStream:close	()V
    //   182: aload 8
    //   184: areturn
    //   185: astore 9
    //   187: aload 9
    //   189: invokevirtual 163	java/io/IOException:printStackTrace	()V
    //   192: aload 8
    //   194: areturn
    //   195: astore 10
    //   197: aload 10
    //   199: invokevirtual 169	java/lang/Exception:printStackTrace	()V
    //   202: iconst_0
    //   203: invokestatic 44	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   206: astore 11
    //   208: aload 11
    //   210: astore 8
    //   212: aload_2
    //   213: ifnull -96 -> 117
    //   216: aload_2
    //   217: invokevirtual 160	java/io/FileInputStream:close	()V
    //   220: aload 8
    //   222: areturn
    //   223: astore 12
    //   225: aload 12
    //   227: invokevirtual 163	java/io/IOException:printStackTrace	()V
    //   230: aload 8
    //   232: areturn
    //   233: astore 5
    //   235: aload_2
    //   236: ifnull +7 -> 243
    //   239: aload_2
    //   240: invokevirtual 160	java/io/FileInputStream:close	()V
    //   243: aload 5
    //   245: athrow
    //   246: astore 6
    //   248: aload 6
    //   250: invokevirtual 163	java/io/IOException:printStackTrace	()V
    //   253: goto -10 -> 243
    //   256: astore 5
    //   258: aload_3
    //   259: astore_2
    //   260: goto -25 -> 235
    //   263: astore 10
    //   265: aload_3
    //   266: astore_2
    //   267: goto -70 -> 197
    //   270: astore 4
    //   272: aload_3
    //   273: astore_2
    //   274: goto -115 -> 159
    //
    // Exception table:
    //   from	to	target	type
    //   113	117	120	java/io/IOException
    //   140	144	147	java/io/IOException
    //   2	11	157	java/io/FileNotFoundException
    //   178	182	185	java/io/IOException
    //   2	11	195	java/lang/Exception
    //   216	220	223	java/io/IOException
    //   2	11	233	finally
    //   159	170	233	finally
    //   197	208	233	finally
    //   239	243	246	java/io/IOException
    //   11	105	256	finally
    //   130	136	256	finally
    //   11	105	263	java/lang/Exception
    //   130	136	263	java/lang/Exception
    //   11	105	270	java/io/FileNotFoundException
    //   130	136	270	java/io/FileNotFoundException
  }

  public static void deleteNoticePicByNoticeId(int paramInt)
  {
    String[] arrayOfString = getExternalMSDKDir().list(new FilenameFilter("Notice_" + paramInt)
    {
      public boolean accept(File paramFile, String paramString)
      {
        return paramString.contains(this.val$fileNameString);
      }
    });
    if (arrayOfString != null)
    {
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        String str = arrayOfString[j];
        File localFile = new File(getExternalMSDKDir(), str);
        Logger.d("delete file:", localFile.toString());
        localFile.delete();
      }
    }
  }

  public static void downloadNoticePic(NoticePic paramNoticePic)
  {
    if ((paramNoticePic == null) || (T.ckIsEmpty(paramNoticePic.getmPicHash())) || (T.ckIsEmpty(paramNoticePic.getmPicUrl())))
      return;
    try
    {
      if (checkNoticePicExist(paramNoticePic.getmNoticeId(), paramNoticePic.getmPicUrl(), paramNoticePic.getmPicHash()).booleanValue())
        break label101;
      String str = getFilePathByNoticeIdAndHashValue(paramNoticePic.getmNoticeId(), paramNoticePic.getmPicUrl(), paramNoticePic.getmPicHash());
      if (!T.ckIsEmpty(str))
      {
        DownloadThread.addToDownloadQueue(new URL(paramNoticePic.getmPicUrl()), str, paramNoticePic.getmPicHash());
        return;
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      localMalformedURLException.printStackTrace();
      return;
    }
    Logger.w("filePathString is empty");
    return;
    label101: Logger.w("file has exist");
  }

  public static File getExternalMSDKDir()
  {
    File localFile = new File(NoticeManager.sNoticePicPath, "MSDK");
    if (!localFile.exists())
      localFile.mkdirs();
    return localFile;
  }

  public static String getFilePathByNoticeId(int paramInt)
  {
    return new File(getExternalMSDKDir(), "Notice_" + paramInt).toString();
  }

  public static String getFilePathByNoticeIdAndHashValue(String paramString1, String paramString2, String paramString3)
  {
    if ((T.ckIsEmpty(paramString2)) || (T.ckIsEmpty(paramString1)) || (T.ckIsEmpty(paramString3)))
      return "";
    String[] arrayOfString = paramString2.split("\\.");
    return new File(getExternalMSDKDir(), "Notice_" + paramString1 + "_" + paramString3 + "." + arrayOfString[(-1 + arrayOfString.length)]).toString();
  }

  public static void saveNoticePics(NoticeInfo paramNoticeInfo)
  {
    if (paramNoticeInfo == null);
    do
    {
      return;
      if ((T.ckIsEmpty(paramNoticeInfo.mNoticeHImgUrl)) || (T.ckIsEmpty(paramNoticeInfo.mNoticeHImgHash)))
        continue;
      Logger.d("add to queue :" + paramNoticeInfo.mNoticeId + "," + paramNoticeInfo.mNoticeHImgUrl + eMSDK_SCREENDIR.eMSDK_SCREENDIR_LANDSCAPE + paramNoticeInfo.mNoticeHImgHash);
      downloadNoticePic(new NoticePic(paramNoticeInfo.mNoticeId, paramNoticeInfo.mNoticeHImgUrl, eMSDK_SCREENDIR.eMSDK_SCREENDIR_LANDSCAPE, paramNoticeInfo.mNoticeHImgHash));
    }
    while ((T.ckIsEmpty(paramNoticeInfo.mNoticeVImgUrl)) || (T.ckIsEmpty(paramNoticeInfo.mNoticeVImgHash)));
    Logger.d("add to queue :" + paramNoticeInfo.mNoticeId + "," + paramNoticeInfo.mNoticeVImgUrl + eMSDK_SCREENDIR.eMSDK_SCREENDIR_PORTRAIT + paramNoticeInfo.mNoticeVImgHash);
    downloadNoticePic(new NoticePic(paramNoticeInfo.mNoticeId, paramNoticeInfo.mNoticeVImgUrl, eMSDK_SCREENDIR.eMSDK_SCREENDIR_PORTRAIT, paramNoticeInfo.mNoticeVImgHash));
  }

  public String getmNoticeId()
  {
    return this.mNoticeId;
  }

  public String getmPicHash()
  {
    return this.mPicHash;
  }

  public String getmPicUrl()
  {
    return this.mPicUrl;
  }

  public eMSDK_SCREENDIR getmScreenDir()
  {
    return this.mScreenDir;
  }

  public void setmNoticeId(String paramString)
  {
    this.mNoticeId = paramString;
  }

  public void setmPicHash(String paramString)
  {
    if (!T.ckIsEmpty(paramString))
      this.mPicHash = paramString;
  }

  public void setmPicUrl(String paramString)
  {
    if (!T.ckIsEmpty(paramString))
      this.mPicUrl = paramString;
  }

  public void setmScreenDir(eMSDK_SCREENDIR parameMSDK_SCREENDIR)
  {
    this.mScreenDir = parameMSDK_SCREENDIR;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.NoticePic
 * JD-Core Version:    0.6.0
 */
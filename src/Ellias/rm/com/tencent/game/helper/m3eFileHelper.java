package com.tencent.game.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class m3eFileHelper
{
  private static m3eFileHelper instance = new m3eFileHelper();
  public Context context = null;

  public static m3eFileHelper getInstance()
  {
    return instance;
  }

  public String GetCurDate()
  {
    return new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
  }

  public int GetImageWidthByFile(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    return localOptions.outWidth;
  }

  public boolean IsPng(String paramString)
  {
    Log.d("m3e", "openFileAndroid(" + paramString + ")");
    BitmapFactory.Options localOptions = null;
    if (0 == 0)
      localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    return (localOptions.mCancel) || (localOptions.outWidth == -1) || (localOptions.outHeight == -1);
  }

  public void closeFileAndroid(APKFile paramAPKFile)
  {
    try
    {
      paramAPKFile.is.close();
      paramAPKFile.data = new byte[0];
      paramAPKFile.is = null;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.d("m3e", "closeFileAndroid:" + localIOException.getMessage());
    }
  }

  public void createDir(String paramString)
  {
    File localFile = new File(paramString);
    if (!localFile.exists())
      localFile.mkdir();
  }

  public void deleteFile(String paramString)
  {
    File localFile = new File(paramString);
    if (localFile.exists() == true)
      localFile.delete();
  }

  public String getNomediaDirectory()
  {
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    File localFile = new File(str + "/RM/", ".nomedia");
    if (!localFile.exists());
    try
    {
      localFile.createNewFile();
      label48: return str + "/RM/.nomedia";
    }
    catch (IOException localIOException)
    {
      break label48;
    }
  }

  public String getResDirectory()
  {
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    return str + "/RM/res";
  }

  public String getUserDirectory()
  {
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    return str + "/RM/";
  }

  public boolean isExistFile(String paramString)
  {
    return new File(paramString).exists() == true;
  }

  public APKFile openFileAndroid(String paramString)
  {
    Log.d("m3e", "openFileAndroid(" + paramString + ")");
    APKFile localAPKFile = new APKFile();
    localAPKFile.is = null;
    localAPKFile.length = 0;
    localAPKFile.position = 0;
    localAPKFile.bufferSize = 0;
    try
    {
      localAPKFile.is = this.context.getAssets().open(paramString);
      localAPKFile.length = localAPKFile.is.available();
      localAPKFile.is.mark(268435456);
      localAPKFile.bufferSize = 1024;
      localAPKFile.data = new byte[localAPKFile.bufferSize];
      return localAPKFile;
    }
    catch (Exception localException)
    {
      Log.d("m3e", "openFileAndroid:" + localException.getMessage());
    }
    return null;
  }

  public void readFileAndroid(APKFile paramAPKFile, int paramInt)
  {
    if (paramInt > paramAPKFile.bufferSize)
    {
      paramAPKFile.data = new byte[paramInt];
      paramAPKFile.bufferSize = paramInt;
    }
    try
    {
      paramAPKFile.is.read(paramAPKFile.data, 0, paramInt);
      paramAPKFile.position = (paramInt + paramAPKFile.position);
      return;
    }
    catch (IOException localIOException)
    {
      Log.d("m3e", "readFileAndroid:" + localIOException.getMessage());
    }
  }

  public long seekFileAndroid(APKFile paramAPKFile, int paramInt)
  {
    long l1 = 0L;
    long l2;
    try
    {
      paramAPKFile.is.reset();
      int i = 128;
      long l3 = l1;
      while (true)
        if ((paramInt > 0) && (i > 0))
          try
          {
            long l5 = paramAPKFile.is.skip(paramInt);
            l3 = l5;
            long l4 = l1 + l3;
            paramInt = (int)(paramInt - l3);
            i--;
            l1 = l4;
          }
          catch (IOException localIOException2)
          {
            while (true)
              Log.d("m3e", "seekFileAndroid while :" + localIOException2.getMessage());
          }
    }
    catch (IOException localIOException1)
    {
      l2 = l1;
      Log.d("m3e", "seekFileAndroid:" + localIOException1.getMessage());
    }
    while (true)
    {
      paramAPKFile.position = (int)l2;
      return l2;
      l2 = l1;
    }
  }

  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }

  // ERROR //
  public void unZipFile(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 225	com/tencent/game/helper/m3eFileHelper:isExistFile	(Ljava/lang/String;)Z
    //   5: ifeq +227 -> 232
    //   8: new 227	java/util/zip/ZipInputStream
    //   11: dup
    //   12: new 229	java/io/BufferedInputStream
    //   15: dup
    //   16: new 231	java/io/FileInputStream
    //   19: dup
    //   20: aload_1
    //   21: invokespecial 232	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   24: invokespecial 235	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   27: invokespecial 236	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   30: astore_3
    //   31: ldc 67
    //   33: new 69	java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   40: ldc 238
    //   42: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: aload_1
    //   46: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: invokevirtual 81	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: invokestatic 87	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   55: pop
    //   56: aload_3
    //   57: invokevirtual 242	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   60: astore 6
    //   62: aload 6
    //   64: ifnull +164 -> 228
    //   67: ldc 244
    //   69: new 69	java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   76: ldc 246
    //   78: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: aload 6
    //   83: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   86: invokevirtual 81	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokestatic 252	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   92: pop
    //   93: sipush 4096
    //   96: newarray byte
    //   98: astore 9
    //   100: aload 6
    //   102: invokevirtual 257	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   105: astore 10
    //   107: new 120	java/io/File
    //   110: dup
    //   111: new 69	java/lang/StringBuilder
    //   114: dup
    //   115: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   118: aload_2
    //   119: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: aload 10
    //   124: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: invokevirtual 81	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   130: invokespecial 121	java/io/File:<init>	(Ljava/lang/String;)V
    //   133: astore 11
    //   135: new 120	java/io/File
    //   138: dup
    //   139: aload 11
    //   141: invokevirtual 260	java/io/File:getParent	()Ljava/lang/String;
    //   144: invokespecial 121	java/io/File:<init>	(Ljava/lang/String;)V
    //   147: astore 12
    //   149: aload 12
    //   151: invokevirtual 125	java/io/File:exists	()Z
    //   154: ifne +9 -> 163
    //   157: aload 12
    //   159: invokevirtual 263	java/io/File:mkdirs	()Z
    //   162: pop
    //   163: new 265	java/io/BufferedOutputStream
    //   166: dup
    //   167: new 267	java/io/FileOutputStream
    //   170: dup
    //   171: aload 11
    //   173: invokespecial 270	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   176: sipush 4096
    //   179: invokespecial 273	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   182: astore 13
    //   184: aload_3
    //   185: aload 9
    //   187: iconst_0
    //   188: sipush 4096
    //   191: invokevirtual 274	java/util/zip/ZipInputStream:read	([BII)I
    //   194: istore 14
    //   196: iload 14
    //   198: iconst_m1
    //   199: if_icmpeq +16 -> 215
    //   202: aload 13
    //   204: aload 9
    //   206: iconst_0
    //   207: iload 14
    //   209: invokevirtual 278	java/io/BufferedOutputStream:write	([BII)V
    //   212: goto -28 -> 184
    //   215: aload 13
    //   217: invokevirtual 281	java/io/BufferedOutputStream:flush	()V
    //   220: aload 13
    //   222: invokevirtual 282	java/io/BufferedOutputStream:close	()V
    //   225: goto -169 -> 56
    //   228: aload_3
    //   229: invokevirtual 283	java/util/zip/ZipInputStream:close	()V
    //   232: return
    //   233: astore 4
    //   235: return
    //   236: astore 7
    //   238: goto -182 -> 56
    //
    // Exception table:
    //   from	to	target	type
    //   8	56	233	java/lang/Exception
    //   56	62	233	java/lang/Exception
    //   228	232	233	java/lang/Exception
    //   67	163	236	java/lang/Exception
    //   163	184	236	java/lang/Exception
    //   184	196	236	java/lang/Exception
    //   202	212	236	java/lang/Exception
    //   215	225	236	java/lang/Exception
  }

  public class APKFile
  {
    public int bufferSize;
    public byte[] data;
    public InputStream is;
    public int length;
    public int position;

    public APKFile()
    {
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.m3eFileHelper
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.net.download.multiplex.download.extension;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.tencent.component.ComponentContext;
import com.tencent.component.utils.UITools;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils
{
  private static final String A = "recent";
  private static final String B = "cookies";
  private static final String C = "accounts";
  private static final String D = "plugin";
  private static final String E = "webviewCache";
  private static final String F = "skin_config_cache";
  private static final String G = "market";
  private static final String H = "read_css";
  private static final String I = ".incrupdate";
  private static final String J = "particularurls.dat";
  private static final String K = "wup.dat";
  private static final String L = "statservice.dat";
  private static final String M = "statthirdplugin.dat";
  private static final String N = "qqmaket.dat";
  private static final String O = "user.inf";
  private static final String P = "splash.inf";
  private static final String Q = "startpage.dat";
  private static final String R = "stat.dat";
  private static final String S = "log.dat";
  private static final String T = "search.dat";
  private static final String U = "safedomain";
  private static final String V = "safedomain_2";
  private static final String W = "flash.inf";
  private static final String X = "flashversion";
  private static final String Y = "lbsdomain";
  private static final String Z = "mttsyncmerger";
  public static final int a = 0;
  private static final String aa = "mttuserlevel";
  private static final String ab = "actab";
  private static final String ac = "acadv.dat";
  private static final String ad = "acpromptword.dat";
  private static final String ae = "pushbanner";
  private static final String af = "shareicon";
  private static final String ag = "qab";
  private static final String ah = "skins";
  private static final String ai = "home.dat";
  private static final String aj = ".core";
  private static final String ak = "push";
  private static final String al = "push_app_list";
  private static final String am = "plugin";
  private static final String an = "frequent_favicon";
  private static HashMap ao = new HashMap();
  private static Lock ap = new ReentrantLock();
  private static Pattern aq = Pattern.compile("^(.*)\\((\\d+)\\)$", 2);
  private static Pattern ar = Pattern.compile("[\\\\\\/\\:\\*\\?\\\"\\|\\<\\>]", 2);
  public static final int b = 1;
  public static final int c = 2;
  public static final String d = ".Application";
  public static final String e = "channel_conf";
  public static final String f = "startpage";
  public static final String g = "exclude";
  public static final String h = "channel.ini";
  public static final String i = "snapshot";
  public static final String j = "mttappstart.dat";
  public static final String k = "entrystat.dat";
  public static final String l = "search_engine_icon";
  public static final String m = "boot_stat.dat";
  public static final String n = "cmd_results.data";
  public static final String o = "qqmarketupdate_4_1.dat";
  public static final String p = "qqmarketadv.dat";
  public static final String q = "qqmkt";
  public static final String r = ".spreaddevice";
  public static final String s = "dynamic_jar_output";
  private static final String t = "FileUtils";
  private static final String u = "http://disk.imtt.qq.com/u?action=upload";
  private static final String v = "DownloadDemo";
  private static final String w = ".cache";
  private static final String x = "data";
  private static final String y = "images";
  private static final String z = "pages";

  public static Bitmap a(String paramString1, String paramString2)
  {
    boolean bool1 = TextUtils.isEmpty(paramString1);
    Bitmap localBitmap = null;
    if (!bool1)
    {
      boolean bool2 = TextUtils.isEmpty(paramString2);
      localBitmap = null;
      if (!bool2)
      {
        File localFile1 = new File(paramString2, "." + paramString1 + ".png.tmp");
        localBitmap = null;
        if (localFile1 != null)
          localBitmap = b(localFile1);
        if (localBitmap == null)
        {
          File localFile2 = new File(paramString2, "." + paramString1 + ".png");
          if (localFile2 != null)
            localBitmap = b(localFile2);
        }
      }
    }
    return localBitmap;
  }

  public static File a()
  {
    return ComponentContext.a().getFilesDir();
  }

  public static File a(File paramFile, String paramString)
  {
    File localFile;
    if ((paramFile == null) || (paramString == null) || (paramString.length() == 0))
      localFile = null;
    do
    {
      return localFile;
      localFile = new File(paramFile, paramString);
    }
    while (localFile.exists());
    localFile.mkdirs();
    return localFile;
  }

  public static String a(String paramString)
  {
    File localFile = j();
    String str = null;
    if (localFile != null)
      str = localFile.getAbsolutePath();
    return str;
  }

  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    a(paramContext, paramString1, paramString2, null);
  }

  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    LogUtil.d("FileUtils", "openLocalFile");
    if (!o())
      UITools.a("SD卡没找到!");
    File localFile;
    String str;
    do
    {
      do
        return;
      while ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)));
      localFile = new File(paramString1, paramString2);
      if (!localFile.exists())
      {
        UITools.a("文件不存在");
        return;
      }
      int i1 = 1 + localFile.getName().lastIndexOf('.');
      if (i1 == -1)
        i1 = paramString2.length();
      str = paramString2.substring(i1);
    }
    while ((paramString2.toLowerCase().endsWith("pdf")) && (d(localFile)));
    a(localFile.getAbsolutePath(), paramString3, str);
  }

  public static void a(File paramFile1, File paramFile2, boolean paramBoolean)
  {
  }

  public static void a(String paramString1, String paramString2, Bitmap paramBitmap)
  {
    if ((!TextUtils.isEmpty(paramString1)) && (paramBitmap != null))
      a(new File(paramString2, "." + paramString1 + ".png.tmp"), paramBitmap);
  }

  private static void a(String paramString1, String paramString2, String paramString3)
  {
    if (TextUtils.isEmpty(paramString1))
      return;
    File localFile = new File(paramString1);
    if (!localFile.exists())
    {
      UITools.a("文件没找到");
      return;
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435456);
    localIntent.addFlags(1073741824);
    if (!TextUtils.isEmpty(paramString2))
      localIntent.putExtra("android.intent.extra.INSTALLER_PACKAGE_NAME", paramString2);
    int i1;
    if (TextUtils.isEmpty(paramString3))
    {
      i1 = paramString1.lastIndexOf('.');
      if (i1 <= -1)
        break label168;
    }
    label168: for (paramString3 = paramString1.substring(i1 + 1); ; paramString3 = "")
    {
      String str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString3);
      localIntent.setDataAndType(Uri.fromFile(localFile), str);
      Context localContext = ComponentContext.a();
      try
      {
        localContext.startActivity(localIntent);
        return;
      }
      catch (Exception localException)
      {
        new Handler(Looper.getMainLooper()).post(new a(localContext));
        return;
      }
    }
  }

  public static void a(String paramString, boolean paramBoolean)
  {
  }

  public static boolean a(File paramFile)
  {
    return (paramFile != null) && (paramFile.getAbsolutePath().contains(n().getAbsolutePath())) && (!o());
  }

  public static boolean a(File paramFile, Bitmap paramBitmap)
  {
    return true;
  }

  public static int b(String paramString)
  {
    if (paramString != null)
    {
      int i1 = paramString.lastIndexOf('.');
      if (i1 > 0)
      {
        String str = paramString.substring(i1 + 1).toLowerCase();
        Integer localInteger = (Integer)ao.get(str);
        if (localInteger != null)
          return localInteger.intValue();
      }
    }
    return -1;
  }

  // ERROR //
  public static Bitmap b(File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +175 -> 176
    //   4: aload_0
    //   5: invokevirtual 291	java/io/File:exists	()Z
    //   8: ifeq +168 -> 176
    //   11: aload_0
    //   12: invokestatic 449	com/tencent/component/net/download/multiplex/download/extension/FileUtils:c	(Ljava/io/File;)Ljava/io/FileInputStream;
    //   15: astore 12
    //   17: aload 12
    //   19: astore_2
    //   20: aload_2
    //   21: invokestatic 455	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   24: astore 15
    //   26: aload 15
    //   28: astore_1
    //   29: aload_2
    //   30: ifnull +7 -> 37
    //   33: aload_2
    //   34: invokevirtual 460	java/io/InputStream:close	()V
    //   37: aload_1
    //   38: areturn
    //   39: astore_3
    //   40: aload_3
    //   41: invokevirtual 463	java/io/IOException:printStackTrace	()V
    //   44: aload_1
    //   45: areturn
    //   46: astore 10
    //   48: aconst_null
    //   49: astore_2
    //   50: ldc 187
    //   52: ldc_w 465
    //   55: invokestatic 313	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: aconst_null
    //   59: astore_1
    //   60: aload_2
    //   61: ifnull -24 -> 37
    //   64: aload_2
    //   65: invokevirtual 460	java/io/InputStream:close	()V
    //   68: aconst_null
    //   69: areturn
    //   70: astore 11
    //   72: aload 11
    //   74: invokevirtual 463	java/io/IOException:printStackTrace	()V
    //   77: aconst_null
    //   78: areturn
    //   79: astore 7
    //   81: aconst_null
    //   82: astore 8
    //   84: ldc 187
    //   86: ldc_w 467
    //   89: aload 7
    //   91: invokestatic 470	com/tencent/component/utils/log/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   94: aconst_null
    //   95: astore_1
    //   96: aload 8
    //   98: ifnull -61 -> 37
    //   101: aload 8
    //   103: invokevirtual 460	java/io/InputStream:close	()V
    //   106: aconst_null
    //   107: areturn
    //   108: astore 9
    //   110: aload 9
    //   112: invokevirtual 463	java/io/IOException:printStackTrace	()V
    //   115: aconst_null
    //   116: areturn
    //   117: astore 4
    //   119: aconst_null
    //   120: astore_2
    //   121: aload 4
    //   123: astore 5
    //   125: aload_2
    //   126: ifnull +7 -> 133
    //   129: aload_2
    //   130: invokevirtual 460	java/io/InputStream:close	()V
    //   133: aload 5
    //   135: athrow
    //   136: astore 6
    //   138: aload 6
    //   140: invokevirtual 463	java/io/IOException:printStackTrace	()V
    //   143: goto -10 -> 133
    //   146: astore 5
    //   148: goto -23 -> 125
    //   151: astore 5
    //   153: aload 8
    //   155: astore_2
    //   156: goto -31 -> 125
    //   159: astore 14
    //   161: aload_2
    //   162: astore 8
    //   164: aload 14
    //   166: astore 7
    //   168: goto -84 -> 84
    //   171: astore 13
    //   173: goto -123 -> 50
    //   176: aconst_null
    //   177: astore_1
    //   178: aconst_null
    //   179: astore_2
    //   180: goto -151 -> 29
    //
    // Exception table:
    //   from	to	target	type
    //   33	37	39	java/io/IOException
    //   4	17	46	java/lang/OutOfMemoryError
    //   64	68	70	java/io/IOException
    //   4	17	79	java/lang/Exception
    //   101	106	108	java/io/IOException
    //   4	17	117	finally
    //   129	133	136	java/io/IOException
    //   20	26	146	finally
    //   50	58	146	finally
    //   84	94	151	finally
    //   20	26	159	java/lang/Exception
    //   20	26	171	java/lang/OutOfMemoryError
  }

  public static File b()
  {
    try
    {
      File localFile = ComponentContext.a().getCacheDir();
      return localFile;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
    }
    return null;
  }

  private static void b(Context paramContext)
  {
  }

  public static void b(String paramString1, String paramString2)
  {
    if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)))
    {
      File localFile1 = new File(paramString2, "." + paramString1 + ".png");
      if ((localFile1 != null) && (localFile1.exists()))
        localFile1.delete();
      File localFile2 = new File(paramString2, "." + paramString1 + ".png.tmp");
      if ((localFile2 != null) && (localFile2.exists()))
        localFile2.delete();
    }
  }

  public static File c()
  {
    return a(a(), "data");
  }

  public static FileInputStream c(File paramFile)
  {
    if (paramFile.exists())
    {
      if (paramFile.isDirectory())
        throw new IOException("File '" + paramFile + "' exists but is a directory");
      if (!paramFile.canRead())
        throw new IOException("File '" + paramFile + "' cannot be read");
    }
    else
    {
      throw new FileNotFoundException("File '" + paramFile + "' does not exist");
    }
    return new FileInputStream(paramFile);
  }

  public static FileInputStream c(String paramString)
  {
    return c(new File(paramString));
  }

  public static String c(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)));
    do
    {
      return paramString2;
      if (e(paramString2))
        continue;
      String[] arrayOfString = ar.split(paramString2);
      StringBuilder localStringBuilder = new StringBuilder();
      int i3 = arrayOfString.length;
      for (int i4 = 0; i4 < i3; i4++)
        localStringBuilder.append(arrayOfString[i4]);
      paramString2 = localStringBuilder.toString();
    }
    while ((!new File(paramString1, paramString2).exists()) && (!new File(paramString1, paramString2 + ".qbdltmp").exists()));
    int i1 = paramString2.lastIndexOf('.');
    String str1;
    Matcher localMatcher;
    if (i1 > -1)
    {
      String str3 = paramString2.substring(0, i1);
      str1 = paramString2.substring(i1);
      paramString2 = str3;
      localMatcher = aq.matcher(paramString2);
      if (!localMatcher.find())
        break label301;
      paramString2 = localMatcher.group(1);
    }
    label301: for (int i2 = Integer.parseInt(localMatcher.group(2)); ; i2 = 0)
    {
      String str2;
      File localFile1;
      File localFile2;
      do
      {
        i2++;
        str2 = paramString2 + "(" + i2 + ")" + str1;
        localFile1 = new File(paramString1, str2);
        localFile2 = new File(paramString1, str2 + ".qbdltmp");
      }
      while ((localFile1.exists()) || (localFile2.exists()));
      return str2;
      str1 = "";
      break;
    }
  }

  public static File d()
  {
    return a(a(), "plugin");
  }

  private static boolean d(File paramFile)
  {
    return false;
  }

  public static boolean d(String paramString)
  {
    Integer localInteger = (Integer)ao.get(paramString);
    return (localInteger != null) && (localInteger.intValue() != 0);
  }

  public static String e()
  {
    return "exclude/channel_conf/";
  }

  public static boolean e(String paramString)
  {
    return !ar.matcher(paramString).find();
  }

  public static String f()
  {
    return e() + "channel.ini";
  }

  public static File g()
  {
    return a(b(), "images");
  }

  public static File h()
  {
    File localFile = a(c(), "skins");
    if (!localFile.exists())
      localFile.mkdirs();
    return localFile;
  }

  public static boolean i()
  {
    return q() > 1048576L;
  }

  public static File j()
  {
    File localFile = new File(Environment.getExternalStorageDirectory(), "DownloadDemo");
    if (!localFile.exists())
      localFile.mkdirs();
    return localFile;
  }

  public static File k()
  {
    File localFile = new File(j(), ".Download");
    if (!localFile.exists())
      localFile.mkdirs();
    return localFile;
  }

  public static File l()
  {
    File localFile = new File(j(), "apk");
    if (!localFile.exists())
      localFile.mkdirs();
    return localFile;
  }

  public static String m()
  {
    return j().getAbsolutePath() + "/" + "Media";
  }

  public static File n()
  {
    return Environment.getExternalStorageDirectory();
  }

  public static boolean o()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }

  public static long p()
  {
    StatFs localStatFs = new StatFs(n().getAbsolutePath());
    return localStatFs.getBlockSize() * localStatFs.getAvailableBlocks();
  }

  public static long q()
  {
    String str = c().getAbsolutePath();
    try
    {
      StatFs localStatFs = new StatFs(str);
      localStatFs.restat(str);
      return localStatFs.getAvailableBlocks() * localStatFs.getBlockSize();
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
    return 0L;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.extension.FileUtils
 * JD-Core Version:    0.6.0
 */
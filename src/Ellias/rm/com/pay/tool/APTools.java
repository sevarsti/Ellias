package com.pay.tool;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Proxy;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.SyncFailedException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.WriteAbortedException;
import java.lang.reflect.Field;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class APTools
{
  private static HashMap a;
  private static ArrayList b;
  private static DisplayMetrics c;
  private static String d;
  private static ProgressDialog e;
  public static long s_screenHeight = 0L;
  public static long s_screenWidth = 0L;

  static
  {
    c = null;
  }

  public static String GetActiveNetWorkName()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)AndroidPay.singleton().GetContext().getSystemService("connectivity");
    String str2;
    if (localConnectivityManager == null)
      str2 = "";
    while (true)
    {
      return str2;
      NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      if (localNetworkInfo == null)
        break;
      String str1 = localNetworkInfo.getTypeName();
      str2 = localNetworkInfo.getSubtypeName();
      if ((str2 == null) || (str2.length() == 0))
        return str1;
    }
    return "";
  }

  public static float GetDensity(Activity paramActivity)
  {
    if (c == null)
    {
      c = new DisplayMetrics();
      if (paramActivity != null)
        paramActivity.getWindowManager().getDefaultDisplay().getMetrics(c);
    }
    return c.density;
  }

  public static int GetDrawableByName(String paramString1, String paramString2)
  {
    return reflectResouce(paramString1, "drawable", paramString2);
  }

  public static Node GetFirstElementByTagName(Element paramElement, String paramString)
  {
    NodeList localNodeList = paramElement.getChildNodes();
    for (int i = 0; ; i++)
    {
      if (i >= localNodeList.getLength())
        return null;
      Node localNode = localNodeList.item(i);
      if (localNode.getNodeName().equalsIgnoreCase(paramString))
        return localNode;
    }
  }

  public static int GetIDByName(String paramString1, String paramString2)
  {
    return reflectResouce(paramString1, "id", paramString2);
  }

  public static int GetLayoutByName(String paramString1, String paramString2)
  {
    return reflectResouce(paramString1, "layout", paramString2);
  }

  public static Bitmap GetRoundedCornerBitmap(Bitmap paramBitmap, float paramFloat)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    RectF localRectF = new RectF(localRect);
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-12434878);
    localCanvas.drawRoundRect(localRectF, paramFloat, paramFloat, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    return localBitmap;
  }

  public static long GetScreenHeight(Activity paramActivity)
  {
    if (s_screenHeight == 0L)
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      if (paramActivity == null)
        break label45;
      paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      s_screenHeight = localDisplayMetrics.heightPixels;
    }
    while (true)
    {
      return s_screenHeight;
      label45: s_screenHeight = 0L;
    }
  }

  public static long GetScreenWidth(Activity paramActivity)
  {
    if (s_screenWidth == 0L)
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      if (paramActivity == null)
        break label45;
      paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      s_screenWidth = localDisplayMetrics.widthPixels;
    }
    while (true)
    {
      return s_screenWidth;
      label45: s_screenHeight = 0L;
    }
  }

  public static boolean IsNetworkAvailable()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)AndroidPay.singleton().GetContext().getSystemService("connectivity");
    if (localConnectivityManager == null);
    while (true)
    {
      return false;
      NetworkInfo[] arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
      if (arrayOfNetworkInfo == null)
        continue;
      for (int i = 0; i < arrayOfNetworkInfo.length; i++)
        if (arrayOfNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED)
          return true;
    }
  }

  public static String array2String(String[] paramArrayOfString, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramArrayOfString.length > 0);
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfString.length)
        return localStringBuffer.toString();
      localStringBuffer.append(paramArrayOfString[i]);
      if (i >= -1 + paramArrayOfString.length)
        continue;
      localStringBuffer.append(paramString);
    }
  }

  public static boolean checkApkIsExist(Context paramContext, String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return false;
      try
      {
        boolean bool = new File(paramString).exists();
        if (bool)
          return true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return false;
  }

  public static void closeProgressDialog()
  {
    if (e != null)
      e.dismiss();
  }

  public static String collectDeviceInfo(Context paramContext)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    String str1 = getDeviceId(paramContext);
    localStringBuffer.append("imei=" + str1);
    String str2;
    if (paramContext == null)
      str2 = null;
    while (true)
    {
      if (str2 == null)
        str2 = "";
      localStringBuffer.append("&mac=" + str2);
      String str3 = Build.DEVICE;
      if (str3 == null)
        str3 = "";
      localStringBuffer.append("&device=" + str3);
      String str4 = getAvailMemory2(paramContext);
      if (str4 == null)
        str4 = "";
      localStringBuffer.append("&availableMemory=" + str4);
      String str5 = getTotalMemory(paramContext);
      if (str5 == null)
        str5 = "";
      localStringBuffer.append("&totalMemory=" + str5);
      String[] arrayOfString = getCpuInfo();
      if (arrayOfString.length == 2)
      {
        localStringBuffer.append("&cup=" + arrayOfString[0]);
        localStringBuffer.append("&cupFrequency=" + arrayOfString[1]);
      }
      return localStringBuffer.toString();
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if (localWifiManager == null)
      {
        str2 = null;
        continue;
      }
      WifiInfo localWifiInfo = localWifiManager.getConnectionInfo();
      if (localWifiInfo == null)
      {
        str2 = null;
        continue;
      }
      str2 = localWifiInfo.getMacAddress();
    }
  }

  public static Bitmap createRepeater(int paramInt, Bitmap paramBitmap)
  {
    int i = (-1 + (paramInt + paramBitmap.getWidth())) / paramBitmap.getWidth();
    Bitmap localBitmap = Bitmap.createBitmap(paramInt, paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localBitmap;
      localCanvas.drawBitmap(paramBitmap, j * paramBitmap.getWidth(), 0.0F, null);
    }
  }

  public static String formatFloat(double paramDouble, int paramInt)
  {
    String str1 = "";
    while (true)
    {
      try
      {
        String str2 = String.valueOf(paramDouble);
        if (!str2.contains("."))
          break label261;
        String[] arrayOfString = str2.split("\\.");
        str2 = arrayOfString[0];
        if (arrayOfString.length <= 1)
          continue;
        str1 = arrayOfString[1];
        if (str1.length() != 0)
          continue;
        if (paramInt == 0)
        {
          String str4 = str2;
          Object localObject = "";
          if (!((String)localObject).equals(""))
          {
            return str4 + "." + (String)localObject;
            if (str1.length() != 1)
              continue;
            if (paramInt == 2)
            {
              String str5 = str1 + "0";
              str4 = str2;
              localObject = str5;
              continue;
              if (str1.length() != 2)
              {
                String str6 = str1.substring(0, 1);
                str4 = str2;
                localObject = str6;
                continue;
                if (paramInt == 2)
                {
                  str4 = str2;
                  localObject = "00";
                  continue;
                }
              }
            }
          }
        }
      }
      catch (Exception localException)
      {
        APLog.i("formatFloat error", localException.toString());
        return "";
      }
      do
      {
        String str3 = str1;
        str4 = str2;
        localObject = str3;
        break;
        return str4;
        if (paramInt != 1)
          continue;
        str4 = str2;
        localObject = "0";
        break;
      }
      while (paramInt != 2);
      str4 = str2;
      localObject = "00";
      continue;
      label261: if (paramInt == 0)
      {
        str4 = str2;
        localObject = "";
        continue;
      }
      if (paramInt != 1)
        continue;
      str4 = str2;
      localObject = "0";
    }
  }

  public static String formatFloat(float paramFloat)
  {
    try
    {
      String str1 = String.valueOf(paramFloat);
      String str3;
      String str2;
      if (str1.contains("."))
      {
        str3 = str1.split("\\.")[0];
        str2 = str1.split("\\.")[1];
        if (str2.length() == 0)
          str2 = "00";
      }
      while (true)
      {
        return str3 + "." + str2;
        if (str2.length() == 1)
        {
          str2 = str2 + "0";
          continue;
        }
        if (str2.length() == 2)
          continue;
        str2 = str2.substring(0, 1);
        continue;
        str2 = "00";
        str3 = str1;
      }
    }
    catch (Exception localException)
    {
      APLog.i("formatFloat error", localException.toString());
    }
    return "";
  }

  public static String getAppPackageName()
  {
    return d;
  }

  // ERROR //
  public static String getAppVersionName()
  {
    // Byte code:
    //   0: invokestatic 36	com/pay/AndroidPay:singleton	()Lcom/pay/AndroidPay;
    //   3: invokevirtual 40	com/pay/AndroidPay:GetContext	()Landroid/content/Context;
    //   6: astore_3
    //   7: aload_3
    //   8: invokevirtual 383	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   11: aload_3
    //   12: invokevirtual 386	android/content/Context:getPackageName	()Ljava/lang/String;
    //   15: iconst_0
    //   16: invokevirtual 392	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: getfield 397	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   22: astore_1
    //   23: aload_1
    //   24: ifnull +14 -> 38
    //   27: aload_1
    //   28: invokevirtual 70	java/lang/String:length	()I
    //   31: istore 4
    //   33: iload 4
    //   35: ifgt +6 -> 41
    //   38: ldc 52
    //   40: astore_1
    //   41: aload_1
    //   42: areturn
    //   43: astore_0
    //   44: ldc 52
    //   46: astore_1
    //   47: aload_0
    //   48: astore_2
    //   49: aload_2
    //   50: invokevirtual 263	java/lang/Exception:printStackTrace	()V
    //   53: aload_1
    //   54: areturn
    //   55: astore_2
    //   56: goto -7 -> 49
    //
    // Exception table:
    //   from	to	target	type
    //   0	23	43	java/lang/Exception
    //   27	33	55	java/lang/Exception
  }

  public static long getAvailMemory(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem / 1048576L;
  }

  public static String getAvailMemory2(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    if (localActivityManager != null)
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      localActivityManager.getMemoryInfo(localMemoryInfo);
      return Formatter.formatFileSize(paramContext, localMemoryInfo.availMem);
    }
    return null;
  }

  // ERROR //
  public static String[] getCpuInfo()
  {
    // Byte code:
    //   0: iconst_2
    //   1: anewarray 66	java/lang/String
    //   4: dup
    //   5: iconst_0
    //   6: ldc 52
    //   8: aastore
    //   9: dup
    //   10: iconst_1
    //   11: ldc 52
    //   13: aastore
    //   14: astore_0
    //   15: new 425	java/io/FileReader
    //   18: dup
    //   19: ldc_w 427
    //   22: invokespecial 428	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   25: astore_1
    //   26: new 430	java/io/BufferedReader
    //   29: dup
    //   30: aload_1
    //   31: sipush 8192
    //   34: invokespecial 433	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   37: astore_2
    //   38: aload_2
    //   39: invokevirtual 436	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   42: astore 10
    //   44: aload 10
    //   46: ifnull +169 -> 215
    //   49: aload 10
    //   51: ldc_w 438
    //   54: invokevirtual 348	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   57: astore 11
    //   59: iconst_2
    //   60: istore 12
    //   62: iload 12
    //   64: aload 11
    //   66: arraylength
    //   67: if_icmplt +109 -> 176
    //   70: aload_2
    //   71: invokevirtual 436	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   74: astore 13
    //   76: aload 13
    //   78: ifnull +240 -> 318
    //   81: aload 13
    //   83: ldc_w 438
    //   86: invokevirtual 348	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   89: astore 14
    //   91: aload 14
    //   93: ifnull +175 -> 268
    //   96: aload 14
    //   98: arraylength
    //   99: iconst_3
    //   100: if_icmplt +168 -> 268
    //   103: aload_0
    //   104: iconst_1
    //   105: new 278	java/lang/StringBuilder
    //   108: dup
    //   109: aload_0
    //   110: iconst_1
    //   111: aaload
    //   112: invokestatic 355	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   115: invokespecial 281	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   118: aload 14
    //   120: iconst_2
    //   121: aaload
    //   122: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: invokevirtual 285	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: aastore
    //   129: aload_2
    //   130: invokevirtual 441	java/io/BufferedReader:close	()V
    //   133: aload_1
    //   134: invokevirtual 442	java/io/FileReader:close	()V
    //   137: getstatic 448	java/lang/System:out	Ljava/io/PrintStream;
    //   140: new 278	java/lang/StringBuilder
    //   143: dup
    //   144: ldc_w 450
    //   147: invokespecial 281	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   150: aload_0
    //   151: iconst_0
    //   152: aaload
    //   153: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: ldc_w 452
    //   159: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: aload_0
    //   163: iconst_1
    //   164: aaload
    //   165: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: invokevirtual 285	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   171: invokevirtual 457	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   174: aload_0
    //   175: areturn
    //   176: aload_0
    //   177: iconst_0
    //   178: new 278	java/lang/StringBuilder
    //   181: dup
    //   182: aload_0
    //   183: iconst_0
    //   184: aaload
    //   185: invokestatic 355	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   188: invokespecial 281	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   191: aload 11
    //   193: iload 12
    //   195: aaload
    //   196: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: ldc_w 459
    //   202: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: invokevirtual 285	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   208: aastore
    //   209: iinc 12 1
    //   212: goto -150 -> 62
    //   215: aload_0
    //   216: iconst_0
    //   217: new 278	java/lang/StringBuilder
    //   220: dup
    //   221: aload_0
    //   222: iconst_0
    //   223: aaload
    //   224: invokestatic 355	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   227: invokespecial 281	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   230: invokevirtual 285	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: aastore
    //   234: goto -164 -> 70
    //   237: astore 7
    //   239: aload_2
    //   240: astore 8
    //   242: aload 8
    //   244: ifnull +8 -> 252
    //   247: aload 8
    //   249: invokevirtual 441	java/io/BufferedReader:close	()V
    //   252: aload_1
    //   253: ifnull -116 -> 137
    //   256: aload_1
    //   257: invokevirtual 442	java/io/FileReader:close	()V
    //   260: goto -123 -> 137
    //   263: astore 9
    //   265: goto -128 -> 137
    //   268: aload_0
    //   269: iconst_1
    //   270: new 278	java/lang/StringBuilder
    //   273: dup
    //   274: aload_0
    //   275: iconst_1
    //   276: aaload
    //   277: invokestatic 355	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   280: invokespecial 281	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   283: invokevirtual 285	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   286: aastore
    //   287: goto -158 -> 129
    //   290: astore_3
    //   291: aload_1
    //   292: astore 4
    //   294: aload_3
    //   295: astore 5
    //   297: aload_2
    //   298: ifnull +7 -> 305
    //   301: aload_2
    //   302: invokevirtual 441	java/io/BufferedReader:close	()V
    //   305: aload 4
    //   307: ifnull +8 -> 315
    //   310: aload 4
    //   312: invokevirtual 442	java/io/FileReader:close	()V
    //   315: aload 5
    //   317: athrow
    //   318: aload_0
    //   319: iconst_1
    //   320: new 278	java/lang/StringBuilder
    //   323: dup
    //   324: aload_0
    //   325: iconst_1
    //   326: aaload
    //   327: invokestatic 355	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   330: invokespecial 281	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   333: invokevirtual 285	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   336: aastore
    //   337: goto -208 -> 129
    //   340: astore 15
    //   342: goto -205 -> 137
    //   345: astore 6
    //   347: goto -32 -> 315
    //   350: astore 5
    //   352: aconst_null
    //   353: astore 4
    //   355: aconst_null
    //   356: astore_2
    //   357: goto -60 -> 297
    //   360: astore 17
    //   362: aload_1
    //   363: astore 4
    //   365: aload 17
    //   367: astore 5
    //   369: aconst_null
    //   370: astore_2
    //   371: goto -74 -> 297
    //   374: astore 18
    //   376: aconst_null
    //   377: astore_1
    //   378: aconst_null
    //   379: astore 8
    //   381: goto -139 -> 242
    //   384: astore 16
    //   386: aconst_null
    //   387: astore 8
    //   389: goto -147 -> 242
    //
    // Exception table:
    //   from	to	target	type
    //   38	44	237	java/io/IOException
    //   49	59	237	java/io/IOException
    //   62	70	237	java/io/IOException
    //   70	76	237	java/io/IOException
    //   81	91	237	java/io/IOException
    //   96	129	237	java/io/IOException
    //   176	209	237	java/io/IOException
    //   215	234	237	java/io/IOException
    //   268	287	237	java/io/IOException
    //   318	337	237	java/io/IOException
    //   247	252	263	java/lang/Exception
    //   256	260	263	java/lang/Exception
    //   38	44	290	finally
    //   49	59	290	finally
    //   62	70	290	finally
    //   70	76	290	finally
    //   81	91	290	finally
    //   96	129	290	finally
    //   176	209	290	finally
    //   215	234	290	finally
    //   268	287	290	finally
    //   318	337	290	finally
    //   129	137	340	java/lang/Exception
    //   301	305	345	java/lang/Exception
    //   310	315	345	java/lang/Exception
    //   15	26	350	finally
    //   26	38	360	finally
    //   15	26	374	java/io/IOException
    //   26	38	384	java/io/IOException
  }

  public static String getDeviceId(Context paramContext)
  {
    if (paramContext != null)
      try
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        if ((localTelephonyManager != null) && (!TextUtils.isEmpty(localTelephonyManager.getDeviceId())))
        {
          String str = localTelephonyManager.getDeviceId();
          return str;
        }
      }
      catch (Exception localException)
      {
      }
    return "";
  }

  public static int getErrorCodeFromException(IOException paramIOException)
  {
    if ((paramIOException instanceof CharConversionException))
      return -20;
    if ((paramIOException instanceof MalformedInputException))
      return -21;
    if ((paramIOException instanceof UnmappableCharacterException))
      return -22;
    if ((paramIOException instanceof HttpResponseException))
      return -23;
    if ((paramIOException instanceof ClosedChannelException))
      return -24;
    if ((paramIOException instanceof ConnectionClosedException))
      return -25;
    if ((paramIOException instanceof EOFException))
      return -26;
    if ((paramIOException instanceof FileLockInterruptionException))
      return -27;
    if ((paramIOException instanceof FileNotFoundException))
      return -28;
    if ((paramIOException instanceof HttpRetryException))
      return -29;
    if ((paramIOException instanceof ConnectTimeoutException))
      return -7;
    if ((paramIOException instanceof SocketTimeoutException))
      return -8;
    if ((paramIOException instanceof InvalidPropertiesFormatException))
      return -30;
    if ((paramIOException instanceof MalformedChunkCodingException))
      return -31;
    if ((paramIOException instanceof MalformedURLException))
      return -3;
    if ((paramIOException instanceof NoHttpResponseException))
      return -32;
    if ((paramIOException instanceof InvalidClassException))
      return -33;
    if ((paramIOException instanceof InvalidObjectException))
      return -34;
    if ((paramIOException instanceof NotActiveException))
      return -35;
    if ((paramIOException instanceof NotSerializableException))
      return -36;
    if ((paramIOException instanceof OptionalDataException))
      return -37;
    if ((paramIOException instanceof StreamCorruptedException))
      return -38;
    if ((paramIOException instanceof WriteAbortedException))
      return -39;
    if ((paramIOException instanceof ProtocolException))
      return -40;
    if ((paramIOException instanceof SSLHandshakeException))
      return -41;
    if ((paramIOException instanceof SSLKeyException))
      return -42;
    if ((paramIOException instanceof SSLPeerUnverifiedException))
      return -43;
    if ((paramIOException instanceof SSLProtocolException))
      return -44;
    if ((paramIOException instanceof BindException))
      return -45;
    if ((paramIOException instanceof ConnectException))
      return -46;
    if ((paramIOException instanceof NoRouteToHostException))
      return -47;
    if ((paramIOException instanceof PortUnreachableException))
      return -48;
    if ((paramIOException instanceof SyncFailedException))
      return -49;
    if ((paramIOException instanceof UTFDataFormatException))
      return -50;
    if ((paramIOException instanceof UnknownHostException))
      return -51;
    if ((paramIOException instanceof UnknownServiceException))
      return -52;
    if ((paramIOException instanceof UnsupportedEncodingException))
      return -53;
    if ((paramIOException instanceof ZipException))
      return -54;
    return -2;
  }

  public static String getLocalIp()
  {
    try
    {
      while (true)
      {
        Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
        while (true)
          if (localEnumeration1.hasMoreElements())
          {
            Enumeration localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
            if (!localEnumeration2.hasMoreElements())
              continue;
            InetAddress localInetAddress = (InetAddress)localEnumeration2.nextElement();
            if ((localInetAddress.isLoopbackAddress()) || (localInetAddress.isLinkLocalAddress()))
              break;
            String str = localInetAddress.getHostAddress().toString();
            return str;
          }
      }
    }
    catch (SocketException localSocketException)
    {
      Log.e("WifiPreference IpAddress", localSocketException.toString());
    }
    return null;
  }

  public static String getLocalMacAddress()
  {
    return ((WifiManager)AndroidPay.singleton().GetContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
  }

  public static int getNetWorkType(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    String str;
    int i;
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
    {
      str = localNetworkInfo.getTypeName();
      if (str.equalsIgnoreCase("WIFI"))
        i = 4;
    }
    while (true)
    {
      APLog.i("networktype", String.valueOf(i));
      return i;
      if (str.equalsIgnoreCase("MOBILE"))
      {
        if (!TextUtils.isEmpty(Proxy.getDefaultHost()))
        {
          i = 1;
          continue;
        }
        int j = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType();
        int k = 0;
        switch (j)
        {
        case 0:
        case 1:
        case 2:
        case 4:
        case 7:
        default:
        case 5:
        case 6:
        case 8:
        case 10:
        case 9:
        case 3:
        }
        while (true)
        {
          if (k == 0)
            break label202;
          i = 3;
          break;
          k = 1;
          continue;
          k = 1;
          continue;
          k = 1;
          continue;
          k = 1;
          continue;
          k = 1;
          continue;
          k = 1;
        }
        label202: i = 2;
        continue;
        i = 0;
        continue;
      }
      i = 0;
    }
  }

  public static double getScreenInch(Context paramContext)
  {
    new DisplayMetrics();
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    int i = localDisplayMetrics.widthPixels;
    int j = localDisplayMetrics.heightPixels;
    float f1 = localDisplayMetrics.xdpi;
    float f2 = localDisplayMetrics.ydpi;
    if (!isXYDpiAvailable(f1, f2))
    {
      f1 = localDisplayMetrics.densityDpi;
      f2 = localDisplayMetrics.densityDpi;
    }
    float f3 = i / f1;
    float f4 = j / f2;
    return Math.sqrt(f3 * f3 + f4 * f4);
  }

  // ERROR //
  public static String getTotalMemory(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: lconst_0
    //   3: lstore_2
    //   4: new 425	java/io/FileReader
    //   7: dup
    //   8: ldc_w 644
    //   11: invokespecial 428	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   14: astore 4
    //   16: new 430	java/io/BufferedReader
    //   19: dup
    //   20: aload 4
    //   22: sipush 8192
    //   25: invokespecial 433	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   28: astore 5
    //   30: aload 5
    //   32: invokevirtual 436	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   35: astore 10
    //   37: aload 10
    //   39: ifnull +59 -> 98
    //   42: aload 10
    //   44: ldc_w 438
    //   47: invokevirtual 348	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   50: astore 11
    //   52: aload 11
    //   54: arraylength
    //   55: istore 12
    //   57: iconst_0
    //   58: istore 13
    //   60: iload 13
    //   62: iload 12
    //   64: if_icmplt +50 -> 114
    //   67: aload 11
    //   69: ifnull +29 -> 98
    //   72: aload 11
    //   74: arraylength
    //   75: iconst_2
    //   76: if_icmplt +22 -> 98
    //   79: aload 11
    //   81: iconst_1
    //   82: aaload
    //   83: invokestatic 649	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   86: invokevirtual 652	java/lang/Integer:intValue	()I
    //   89: istore 16
    //   91: iload 16
    //   93: bipush 10
    //   95: ishl
    //   96: i2l
    //   97: lstore_2
    //   98: aload 5
    //   100: invokevirtual 441	java/io/BufferedReader:close	()V
    //   103: aload 4
    //   105: invokevirtual 442	java/io/FileReader:close	()V
    //   108: aload_0
    //   109: lload_2
    //   110: invokestatic 421	android/text/format/Formatter:formatFileSize	(Landroid/content/Context;J)Ljava/lang/String;
    //   113: areturn
    //   114: aload 10
    //   116: new 278	java/lang/StringBuilder
    //   119: dup
    //   120: aload 11
    //   122: iload 13
    //   124: aaload
    //   125: invokestatic 355	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   128: invokespecial 281	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   131: ldc_w 654
    //   134: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: invokevirtual 285	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   140: invokestatic 656	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   143: pop
    //   144: iinc 13 1
    //   147: goto -87 -> 60
    //   150: astore 18
    //   152: aconst_null
    //   153: astore 4
    //   155: aload_1
    //   156: ifnull +7 -> 163
    //   159: aload_1
    //   160: invokevirtual 441	java/io/BufferedReader:close	()V
    //   163: aload 4
    //   165: ifnull -57 -> 108
    //   168: aload 4
    //   170: invokevirtual 442	java/io/FileReader:close	()V
    //   173: goto -65 -> 108
    //   176: astore 7
    //   178: goto -70 -> 108
    //   181: astore 8
    //   183: aconst_null
    //   184: astore 4
    //   186: aconst_null
    //   187: astore 5
    //   189: aload 5
    //   191: ifnull +8 -> 199
    //   194: aload 5
    //   196: invokevirtual 441	java/io/BufferedReader:close	()V
    //   199: aload 4
    //   201: ifnull +8 -> 209
    //   204: aload 4
    //   206: invokevirtual 442	java/io/FileReader:close	()V
    //   209: aload 8
    //   211: athrow
    //   212: astore 15
    //   214: goto -106 -> 108
    //   217: astore 9
    //   219: goto -10 -> 209
    //   222: astore 8
    //   224: aconst_null
    //   225: astore 5
    //   227: goto -38 -> 189
    //   230: astore 8
    //   232: goto -43 -> 189
    //   235: astore 17
    //   237: aconst_null
    //   238: astore_1
    //   239: goto -84 -> 155
    //   242: astore 6
    //   244: aload 5
    //   246: astore_1
    //   247: goto -92 -> 155
    //
    // Exception table:
    //   from	to	target	type
    //   4	16	150	java/io/IOException
    //   159	163	176	java/lang/Exception
    //   168	173	176	java/lang/Exception
    //   4	16	181	finally
    //   98	108	212	java/lang/Exception
    //   194	199	217	java/lang/Exception
    //   204	209	217	java/lang/Exception
    //   16	30	222	finally
    //   30	37	230	finally
    //   42	57	230	finally
    //   72	91	230	finally
    //   114	144	230	finally
    //   16	30	235	java/io/IOException
    //   30	37	242	java/io/IOException
    //   42	57	242	java/io/IOException
    //   72	91	242	java/io/IOException
    //   114	144	242	java/io/IOException
  }

  public static String getUUID()
  {
    try
    {
      String str = UUID.randomUUID().toString();
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  public static String getUrlParamsValue(String paramString1, String paramString2)
  {
    String[] arrayOfString1 = paramString1.split("[?]");
    String[] arrayOfString2;
    int j;
    if ((arrayOfString1.length > 1) && (arrayOfString1[1] != null))
    {
      arrayOfString2 = arrayOfString1[1].split("[&]");
      int i = arrayOfString2.length;
      j = 0;
      if (j < i);
    }
    else
    {
      return null;
    }
    String[] arrayOfString3 = arrayOfString2[j].split("[=]");
    if ((arrayOfString3.length > 1) && (arrayOfString3[0] != null));
    for (String str1 = arrayOfString3[0]; ; str1 = null)
    {
      if ((arrayOfString3.length > 1) && (arrayOfString3[1] != null));
      for (String str2 = arrayOfString3[1]; ; str2 = null)
      {
        if ((str1 != null) && (str1.compareToIgnoreCase(paramString2) == 0))
          return str2;
        j++;
        break;
      }
    }
  }

  public static boolean hasSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  public static void installApp(Context paramContext, String paramString)
  {
    if (paramString == null)
      return;
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  // ERROR //
  public static void installPaySDK(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 688	android/content/Intent
    //   5: dup
    //   6: invokespecial 711	android/content/Intent:<init>	()V
    //   9: astore_3
    //   10: aload_3
    //   11: ldc_w 712
    //   14: invokevirtual 716	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   17: pop
    //   18: aload_3
    //   19: ldc_w 690
    //   22: invokevirtual 720	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   25: pop
    //   26: aload_0
    //   27: invokevirtual 724	java/lang/Object:getClass	()Ljava/lang/Class;
    //   30: new 278	java/lang/StringBuilder
    //   33: dup
    //   34: ldc_w 726
    //   37: invokespecial 281	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   40: aload_1
    //   41: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: invokevirtual 285	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   47: invokevirtual 732	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   50: astore 12
    //   52: aload 12
    //   54: astore 7
    //   56: aload_0
    //   57: aload_1
    //   58: iconst_0
    //   59: invokevirtual 736	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   62: astore_2
    //   63: sipush 1024
    //   66: newarray byte
    //   68: astore 13
    //   70: aload 7
    //   72: aload 13
    //   74: invokevirtual 742	java/io/InputStream:read	([B)I
    //   77: istore 14
    //   79: iload 14
    //   81: iconst_m1
    //   82: if_icmpne +79 -> 161
    //   85: aload_2
    //   86: invokevirtual 747	java/io/FileOutputStream:flush	()V
    //   89: aload 7
    //   91: ifnull +8 -> 99
    //   94: aload 7
    //   96: invokevirtual 748	java/io/InputStream:close	()V
    //   99: aload_2
    //   100: ifnull +7 -> 107
    //   103: aload_2
    //   104: invokevirtual 749	java/io/FileOutputStream:close	()V
    //   107: aload_3
    //   108: new 254	java/io/File
    //   111: dup
    //   112: new 278	java/lang/StringBuilder
    //   115: dup
    //   116: aload_0
    //   117: invokevirtual 753	android/content/Context:getFilesDir	()Ljava/io/File;
    //   120: invokevirtual 756	java/io/File:getPath	()Ljava/lang/String;
    //   123: invokestatic 355	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   126: invokespecial 281	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   129: ldc_w 758
    //   132: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload_1
    //   136: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: invokevirtual 285	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   142: invokespecial 257	java/io/File:<init>	(Ljava/lang/String;)V
    //   145: invokestatic 762	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
    //   148: ldc_w 701
    //   151: invokevirtual 705	android/content/Intent:setDataAndType	(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;
    //   154: pop
    //   155: aload_0
    //   156: aload_3
    //   157: invokevirtual 709	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   160: return
    //   161: aload_2
    //   162: aload 13
    //   164: iconst_0
    //   165: iload 14
    //   167: invokevirtual 766	java/io/FileOutputStream:write	([BII)V
    //   170: goto -100 -> 70
    //   173: astore 6
    //   175: aload 6
    //   177: invokevirtual 263	java/lang/Exception:printStackTrace	()V
    //   180: aload 7
    //   182: ifnull +8 -> 190
    //   185: aload 7
    //   187: invokevirtual 748	java/io/InputStream:close	()V
    //   190: aload_2
    //   191: ifnull -84 -> 107
    //   194: aload_2
    //   195: invokevirtual 749	java/io/FileOutputStream:close	()V
    //   198: goto -91 -> 107
    //   201: astore 10
    //   203: aload 10
    //   205: invokevirtual 767	java/io/IOException:printStackTrace	()V
    //   208: goto -101 -> 107
    //   211: astore 8
    //   213: aconst_null
    //   214: astore 7
    //   216: aload 7
    //   218: ifnull +8 -> 226
    //   221: aload 7
    //   223: invokevirtual 748	java/io/InputStream:close	()V
    //   226: aload_2
    //   227: ifnull +7 -> 234
    //   230: aload_2
    //   231: invokevirtual 749	java/io/FileOutputStream:close	()V
    //   234: aload 8
    //   236: athrow
    //   237: astore 9
    //   239: aload 9
    //   241: invokevirtual 767	java/io/IOException:printStackTrace	()V
    //   244: goto -10 -> 234
    //   247: astore 15
    //   249: aload 15
    //   251: invokevirtual 767	java/io/IOException:printStackTrace	()V
    //   254: goto -147 -> 107
    //   257: astore 8
    //   259: goto -43 -> 216
    //   262: astore 6
    //   264: aconst_null
    //   265: astore_2
    //   266: aconst_null
    //   267: astore 7
    //   269: goto -94 -> 175
    //
    // Exception table:
    //   from	to	target	type
    //   56	70	173	java/lang/Exception
    //   70	79	173	java/lang/Exception
    //   85	89	173	java/lang/Exception
    //   161	170	173	java/lang/Exception
    //   185	190	201	java/io/IOException
    //   194	198	201	java/io/IOException
    //   26	52	211	finally
    //   221	226	237	java/io/IOException
    //   230	234	237	java/io/IOException
    //   94	99	247	java/io/IOException
    //   103	107	247	java/io/IOException
    //   56	70	257	finally
    //   70	79	257	finally
    //   85	89	257	finally
    //   161	170	257	finally
    //   175	180	257	finally
    //   26	52	262	java/lang/Exception
  }

  // ERROR //
  public static boolean isGameNumMoreMin(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 771	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   4: istore 5
    //   6: iload 5
    //   8: istore_2
    //   9: invokestatic 776	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   12: invokevirtual 780	com/pay/tool/APDataInterface:getOrderInfo	()Lcom/pay/data/orderInfo/APOrderInfo;
    //   15: getfield 786	com/pay/data/orderInfo/APOrderInfo:buyInfo	Lcom/pay/data/buyInfo/APBaseBuyInfo;
    //   18: getfield 791	com/pay/data/buyInfo/APBaseBuyInfo:minNum	I
    //   21: istore_3
    //   22: iconst_0
    //   23: istore 4
    //   25: iload_2
    //   26: iload_3
    //   27: if_icmplt +6 -> 33
    //   30: iconst_1
    //   31: istore 4
    //   33: iload 4
    //   35: ireturn
    //   36: astore_1
    //   37: iconst_0
    //   38: istore_2
    //   39: ldc_w 793
    //   42: aload_1
    //   43: invokevirtual 366	java/lang/Exception:toString	()Ljava/lang/String;
    //   46: invokestatic 372	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   49: iconst_0
    //   50: istore_3
    //   51: goto -29 -> 22
    //   54: astore_1
    //   55: goto -16 -> 39
    //
    // Exception table:
    //   from	to	target	type
    //   0	6	36	java/lang/Exception
    //   9	22	54	java/lang/Exception
  }

  // ERROR //
  public static boolean isHavedPermission(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: invokestatic 36	com/pay/AndroidPay:singleton	()Lcom/pay/AndroidPay;
    //   5: getfield 800	com/pay/AndroidPay:fromActivity	Landroid/app/Activity;
    //   8: invokevirtual 801	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   11: invokestatic 36	com/pay/AndroidPay:singleton	()Lcom/pay/AndroidPay;
    //   14: getfield 800	com/pay/AndroidPay:fromActivity	Landroid/app/Activity;
    //   17: invokevirtual 802	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   20: sipush 4096
    //   23: invokevirtual 392	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   26: getfield 806	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   29: astore 5
    //   31: iconst_0
    //   32: istore_3
    //   33: iload_1
    //   34: aload 5
    //   36: arraylength
    //   37: if_icmplt +5 -> 42
    //   40: iload_3
    //   41: ireturn
    //   42: aload 5
    //   44: iload_1
    //   45: aaload
    //   46: aload_0
    //   47: invokevirtual 352	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   50: istore 6
    //   52: iload 6
    //   54: ifeq +5 -> 59
    //   57: iconst_1
    //   58: istore_3
    //   59: iinc 1 1
    //   62: goto -29 -> 33
    //   65: astore_2
    //   66: iconst_0
    //   67: istore_3
    //   68: aload_2
    //   69: astore 4
    //   71: ldc_w 808
    //   74: aload 4
    //   76: invokevirtual 809	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   79: invokestatic 372	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   82: iload_3
    //   83: ireturn
    //   84: astore 4
    //   86: goto -15 -> 71
    //
    // Exception table:
    //   from	to	target	type
    //   2	31	65	android/content/pm/PackageManager$NameNotFoundException
    //   33	40	84	android/content/pm/PackageManager$NameNotFoundException
    //   42	52	84	android/content/pm/PackageManager$NameNotFoundException
  }

  public static String isInstalledApp(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramString == null)
      return "unStalled";
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramBoolean)
    {
      if (b != null)
        b.clear();
      b = null;
    }
    if (b == null)
      b = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= localList.size())
      {
        if (!b.contains(paramString))
          break;
        return "installed";
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if ((0x1 & localPackageInfo.applicationInfo.flags) != 0)
        continue;
      b.add(localPackageInfo.applicationInfo.packageName);
    }
    return "unStalled";
  }

  public static boolean isServiceRunning(Context paramContext, String paramString)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(2147483647);
    if (localList.size() <= 0);
    while (true)
    {
      return false;
      for (int i = 0; i < localList.size(); i++)
        if (((ActivityManager.RunningServiceInfo)localList.get(i)).service.getClassName().equals(paramString))
          return true;
    }
  }

  public static boolean isXYDpiAvailable(float paramFloat1, float paramFloat2)
  {
    return (paramFloat1 <= 450.0F) && (paramFloat1 >= 100.0F) && (paramFloat2 <= 450.0F) && (paramFloat2 >= 100.0F);
  }

  public static Object loadDataCach(String paramString)
  {
    if (a == null)
      a = new HashMap();
    return a.get(paramString);
  }

  public static String map2UrlParams(HashMap paramHashMap)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      Iterator localIterator = paramHashMap.entrySet().iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          if (!TextUtils.isEmpty(localStringBuffer))
            localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
          label45: return localStringBuffer.toString();
        }
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localStringBuffer.append((String)localEntry.getKey());
        localStringBuffer.append("=");
        localStringBuffer.append((String)localEntry.getValue());
        localStringBuffer.append("&");
      }
    }
    catch (Exception localException)
    {
      break label45;
    }
  }

  public static String readInfo(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(paramString1, 0);
    String str = null;
    if (localSharedPreferences != null)
      str = localSharedPreferences.getString(paramString2, null);
    return str;
  }

  public static int reflectResouce(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null))
      return 0;
    try
    {
      Class localClass = Class.forName(paramString1 + "$" + paramString2);
      int i = Integer.parseInt(localClass.getField(paramString3).get(localClass.newInstance()).toString());
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1;
  }

  public static Drawable resizeImage(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f1 = paramInt1 / i;
    float f2 = paramInt2 / j;
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(f1, f2);
    localMatrix.postRotate(paramInt3);
    return new BitmapDrawable(Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true));
  }

  public static void saveDataCach(String paramString, Object paramObject)
  {
    if (a == null)
      a = new HashMap();
    a.put(paramString, paramObject);
  }

  public static void saveInfo(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(paramString1, 0);
    if (localSharedPreferences != null)
      localSharedPreferences.edit().putString(paramString2, paramString3).commit();
  }

  public static String screenOrient(Activity paramActivity)
  {
    int i = paramActivity.getWindowManager().getDefaultDisplay().getWidth();
    int j = paramActivity.getWindowManager().getDefaultDisplay().getHeight();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    if (i > j)
      return "landscape";
    return "portrait";
  }

  public static void setPackageName(String paramString)
  {
    d = paramString;
  }

  public static void showAlertDialog(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null) || (paramString3 == null))
      return;
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramString1).setMessage(paramString2);
    localBuilder.setPositiveButton(paramString3, new e());
    localBuilder.show();
  }

  public static void showProgressDialog(Context paramContext)
  {
    if (paramContext == null)
      return;
    e = ProgressDialog.show(paramContext, "连接中..", "正在读取数据,请稍候....", true, true);
  }

  public static void unstallApp(Context paramContext, String paramString)
  {
    if (paramString == null)
      return;
    paramContext.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString)));
  }

  // ERROR //
  public static HashMap url2Map(String paramString)
  {
    // Byte code:
    //   0: new 876	java/util/HashMap
    //   3: dup
    //   4: invokespecial 877	java/util/HashMap:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: ldc_w 1037
    //   12: invokevirtual 348	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   15: iconst_1
    //   16: aaload
    //   17: astore_2
    //   18: aload_2
    //   19: invokestatic 470	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   22: ifne +91 -> 113
    //   25: aload_2
    //   26: ldc_w 1039
    //   29: invokevirtual 348	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   32: astore 4
    //   34: ldc 52
    //   36: astore 5
    //   38: ldc 52
    //   40: astore 6
    //   42: iconst_0
    //   43: istore 7
    //   45: aload 4
    //   47: arraylength
    //   48: istore 8
    //   50: iload 7
    //   52: iload 8
    //   54: if_icmplt +5 -> 59
    //   57: aload_1
    //   58: areturn
    //   59: aload 4
    //   61: iload 7
    //   63: aaload
    //   64: ldc_w 1041
    //   67: invokevirtual 348	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   70: iconst_0
    //   71: aaload
    //   72: astore 5
    //   74: aload 4
    //   76: iload 7
    //   78: aaload
    //   79: ldc_w 1041
    //   82: invokevirtual 348	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   85: iconst_1
    //   86: aaload
    //   87: astore 6
    //   89: aload 5
    //   91: astore 10
    //   93: aload 10
    //   95: invokestatic 470	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   98: ifne +39 -> 137
    //   101: aload_1
    //   102: aload 10
    //   104: aload 6
    //   106: invokevirtual 969	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   109: pop
    //   110: goto +27 -> 137
    //   113: ldc_w 1042
    //   116: ldc_w 1044
    //   119: invokestatic 372	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   122: aload_1
    //   123: areturn
    //   124: astore_3
    //   125: ldc_w 1042
    //   128: aload_3
    //   129: invokevirtual 366	java/lang/Exception:toString	()Ljava/lang/String;
    //   132: invokestatic 1047	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   135: aload_1
    //   136: areturn
    //   137: iinc 7 1
    //   140: aload 10
    //   142: astore 5
    //   144: goto -99 -> 45
    //   147: astore 9
    //   149: aload 5
    //   151: astore 10
    //   153: goto -60 -> 93
    //
    // Exception table:
    //   from	to	target	type
    //   18	34	124	java/lang/Exception
    //   45	50	124	java/lang/Exception
    //   93	110	124	java/lang/Exception
    //   113	122	124	java/lang/Exception
    //   59	89	147	java/lang/Exception
  }

  public static String urlEncode(String paramString, int paramInt)
  {
    Object localObject = "";
    int i;
    if (!TextUtils.isEmpty(paramString))
    {
      if (paramInt <= 0)
        return paramString;
      i = 0;
      if (i < paramInt);
    }
    while (true)
    {
      while (true)
      {
        return localObject;
        try
        {
          String str = URLEncoder.encode(paramString, "utf-8");
          localObject = str;
          i++;
          paramString = (String)localObject;
        }
        catch (Exception localException)
        {
          while (true)
            APLog.i("urlEncode", localException.toString());
        }
      }
      APLog.w("", "编码内容为空");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APTools
 * JD-Core Version:    0.6.0
 */
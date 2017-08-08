package com.tenpay.a.a;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.tenpay.a.c.c;
import com.tenpay.tenpayplugin.TenpayResourceUtil;
import java.io.File;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import tencent.com.cftutils.DesDecUtil;
import tencent.com.cftutils.DesEncUtil;
import tencent.com.cftutils.Md5EncUtil;

public class g
{
  public static String a(int paramInt, String paramString)
  {
    String str = "";
    DesDecUtil localDesDecUtil = new DesDecUtil();
    if (localDesDecUtil != null)
    {
      localDesDecUtil.decryptDes(paramInt, paramString);
      str = localDesDecUtil.getDecRes();
    }
    return str;
  }

  public static String a(Context paramContext, int paramInt, boolean paramBoolean, String paramString)
  {
    String str1;
    if (paramString == null)
      str1 = null;
    StringBuilder localStringBuilder;
    DesEncUtil localDesEncUtil;
    do
    {
      return str1;
      str1 = "";
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      if (paramBoolean)
      {
        HashMap localHashMap = b(paramString);
        Md5EncUtil localMd5EncUtil = new Md5EncUtil();
        if (localMd5EncUtil != null)
        {
          localMd5EncUtil.encryptMd5(paramInt, localMd5EncUtil.sortConstructVars(localHashMap));
          String str2 = localMd5EncUtil.getMd5Sign();
          localStringBuilder.append("&sign=");
          localStringBuilder.append(str2);
        }
      }
      localStringBuilder.append("&");
      localStringBuilder.append(b(paramContext));
      localDesEncUtil = new DesEncUtil();
    }
    while (localDesEncUtil == null);
    localDesEncUtil.encryptDes(paramInt, localStringBuilder.toString());
    return localDesEncUtil.getDesEncResult();
  }

  public static String a(Context paramContext, Bundle paramBundle, JSONObject paramJSONObject)
  {
    Object localObject1 = null;
    if (paramBundle != null);
    String str;
    while (true)
    {
      try
      {
        if (paramBundle.getInt("op_code") == 4)
          return null;
        if ((paramBundle == null) || (paramBundle.getInt("op_code") != 1))
        {
          paramJSONObject.put("retcode", -1);
          paramJSONObject.put("retmsg", paramContext.getResources().getString(TenpayResourceUtil.getStringId(paramContext, "unipay_tenpay_network_error")));
          return null;
        }
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        return localObject1;
      }
      byte[] arrayOfByte = paramBundle.getByteArray("data");
      localObject1 = null;
      if (arrayOfByte == null)
        break label177;
      str = new String(arrayOfByte, "UTF-8");
      if (str == null)
        break;
      try
      {
        if ((str.length() <= 5) || (str.substring(0, 6).toLowerCase().indexOf("<html") < 0))
          break;
        paramJSONObject.put("retcode", -1);
        paramJSONObject.put("retmsg", paramContext.getResources().getString(TenpayResourceUtil.getStringId(paramContext, "unipay_tenpay_http_check_network_wifi")));
        return null;
      }
      catch (Exception localException2)
      {
        localObject1 = str;
        Object localObject2 = localException2;
      }
    }
    localObject1 = str;
    label177: return localObject1;
  }

  public static JSONObject a(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }

  private static void a(Context paramContext, String paramString)
  {
    View localView = LayoutInflater.from(paramContext).inflate(TenpayResourceUtil.getLayoutId(paramContext, "unipay_tenpay_toast_custom"), null);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(paramContext, "tenpay_toast_txt"))).setText(paramString);
    Toast localToast = new Toast(paramContext);
    localToast.setDuration(0);
    localToast.setGravity(16, 0, 0);
    localToast.setView(localView);
    localToast.show();
  }

  // ERROR //
  public static boolean a(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 207
    //   3: invokevirtual 211	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   6: checkcast 213	android/net/wifi/WifiManager
    //   9: astore_2
    //   10: aload_2
    //   11: ifnull +37 -> 48
    //   14: aload_2
    //   15: invokevirtual 217	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   18: istore_3
    //   19: iload_3
    //   20: ifeq +26 -> 46
    //   23: aload_2
    //   24: invokevirtual 221	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   27: invokevirtual 226	android/net/wifi/WifiInfo:getIpAddress	()I
    //   30: istore 5
    //   32: iload 5
    //   34: ifne +12 -> 46
    //   37: iconst_0
    //   38: ireturn
    //   39: astore_1
    //   40: iconst_0
    //   41: ireturn
    //   42: astore 4
    //   44: iload_3
    //   45: ireturn
    //   46: iload_3
    //   47: ireturn
    //   48: iconst_0
    //   49: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	39	java/lang/Exception
    //   14	19	39	java/lang/Exception
    //   23	32	42	java/lang/Exception
  }

  public static boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      String str1 = paramJSONObject.optString("retcode");
      String str2 = paramJSONObject.optString("retmsg");
      if ("0".equals(str1))
        return true;
      if (("66100002".equals(str1)) || ("66210007".equals(str1)))
      {
        a(paramContext, str2);
        return false;
      }
      if ("66221152".equals(str1))
      {
        a(paramContext, str2);
        return false;
      }
      if ("66222180".equals(str1))
      {
        a(paramContext, str2);
        return false;
      }
      if ((str2 == null) || ("".equals(str2.trim())))
      {
        a(paramContext, paramContext.getResources().getString(TenpayResourceUtil.getStringId(paramContext, "unipay_tenpay_utils_unknown_error")));
        return false;
      }
      a(paramContext, str2);
      return false;
    }
    a(paramContext, paramContext.getResources().getString(TenpayResourceUtil.getStringId(paramContext, "unipay_tenpay_utils_unknown_error")));
    return false;
  }

  public static String b(Context paramContext)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("h_model=android_tda");
    localStringBuffer.append("&h_edition=");
    localStringBuffer.append(41);
    localStringBuffer.append("&h_location=");
    StringBuilder localStringBuilder = new StringBuilder(160);
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    String str1 = localTelephonyManager.getDeviceId();
    if ((str1 != null) && (str1.length() > 0))
      localStringBuilder.append(c.a(str1));
    localStringBuilder.append("||");
    localStringBuilder.append(Build.MODEL);
    localStringBuilder.append("|");
    localStringBuilder.append(Build.VERSION.RELEASE);
    localStringBuilder.append(",sdk");
    localStringBuilder.append(Build.VERSION.SDK_INT);
    localStringBuilder.append("|");
    String str2 = d(paramContext);
    if ((str2 != null) && (str2.length() > 0))
      localStringBuilder.append(c.a(str1 + str2));
    localStringBuilder.append("|");
    try
    {
      String str4 = localTelephonyManager.getSubscriberId();
      if (str4 != null)
        localStringBuilder.append(c.a(str4));
      localStringBuilder.append("|");
      if (c(paramContext))
      {
        str3 = "1";
        localStringBuilder.append(str3);
        localStringBuilder.append("|");
        localStringBuffer.append(Uri.encode(localStringBuilder.toString()));
        localStringBuffer.append("&h_exten=");
        return localStringBuffer.toString();
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        continue;
        String str3 = "0";
      }
    }
  }

  private static HashMap b(String paramString)
  {
    if (paramString != null)
    {
      HashMap localHashMap = new HashMap();
      String[] arrayOfString = paramString.split("&");
      if (arrayOfString != null);
      for (int i = 0; ; i++)
      {
        if (i >= arrayOfString.length)
          return localHashMap;
        int j = arrayOfString[i].indexOf("=");
        if ((j <= 0) || (j >= -1 + arrayOfString[i].length()))
          continue;
        localHashMap.put(arrayOfString[i].substring(0, j), Uri.decode(arrayOfString[i].substring(j + 1)));
      }
    }
    return null;
  }

  public static boolean c(Context paramContext)
  {
    int i = 1;
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "/system/bin/";
    arrayOfString[i] = "/system/xbin/";
    arrayOfString[2] = "/system/sbin/";
    arrayOfString[3] = "/sbin/";
    arrayOfString[4] = "/vendor/bin/";
    while (true)
    {
      int j;
      int k;
      try
      {
        j = arrayOfString.length;
        k = 0;
        break label105;
        File localFile = new File(arrayOfString[k] + "su");
        if (localFile == null)
          continue;
        boolean bool = localFile.exists();
        if (bool)
          break;
        k++;
      }
      catch (Exception localException)
      {
        return false;
      }
      label105: if (k < j)
        continue;
      i = 0;
    }
    return i;
  }

  // ERROR //
  public static String d(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 207
    //   3: invokevirtual 211	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   6: checkcast 213	android/net/wifi/WifiManager
    //   9: astore_2
    //   10: aload_2
    //   11: ifnull +37 -> 48
    //   14: aload_2
    //   15: invokevirtual 221	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   18: invokevirtual 368	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   21: ifnonnull +27 -> 48
    //   24: aload_2
    //   25: invokevirtual 217	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   28: istore_3
    //   29: iconst_0
    //   30: istore 4
    //   32: iload 4
    //   34: iconst_3
    //   35: if_icmplt +15 -> 50
    //   38: iload_3
    //   39: ifne +9 -> 48
    //   42: aload_2
    //   43: iconst_0
    //   44: invokevirtual 372	android/net/wifi/WifiManager:setWifiEnabled	(Z)Z
    //   47: pop
    //   48: aconst_null
    //   49: areturn
    //   50: aload_2
    //   51: iconst_1
    //   52: invokevirtual 372	android/net/wifi/WifiManager:setWifiEnabled	(Z)Z
    //   55: pop
    //   56: aload_2
    //   57: invokevirtual 221	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   60: invokevirtual 368	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   63: astore 11
    //   65: aload 11
    //   67: ifnull +18 -> 85
    //   70: iload_3
    //   71: ifne +9 -> 80
    //   74: aload_2
    //   75: iconst_0
    //   76: invokevirtual 372	android/net/wifi/WifiManager:setWifiEnabled	(Z)Z
    //   79: pop
    //   80: aload 11
    //   82: areturn
    //   83: astore 8
    //   85: ldc2_w 373
    //   88: invokestatic 380	java/lang/Thread:sleep	(J)V
    //   91: iinc 4 1
    //   94: goto -62 -> 32
    //   97: astore 5
    //   99: iload_3
    //   100: ifne +9 -> 109
    //   103: aload_2
    //   104: iconst_0
    //   105: invokevirtual 372	android/net/wifi/WifiManager:setWifiEnabled	(Z)Z
    //   108: pop
    //   109: aload 5
    //   111: athrow
    //   112: astore_1
    //   113: goto -65 -> 48
    //   116: astore 12
    //   118: goto -38 -> 80
    //   121: astore 9
    //   123: goto -32 -> 91
    //   126: astore 6
    //   128: goto -19 -> 109
    //   131: astore 14
    //   133: goto -85 -> 48
    //
    // Exception table:
    //   from	to	target	type
    //   50	65	83	java/lang/Exception
    //   50	65	97	finally
    //   85	91	97	finally
    //   0	10	112	java/lang/Exception
    //   14	29	112	java/lang/Exception
    //   109	112	112	java/lang/Exception
    //   74	80	116	java/lang/Exception
    //   85	91	121	java/lang/InterruptedException
    //   103	109	126	java/lang/Exception
    //   42	48	131	java/lang/Exception
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.a.a.g
 * JD-Core Version:    0.6.0
 */
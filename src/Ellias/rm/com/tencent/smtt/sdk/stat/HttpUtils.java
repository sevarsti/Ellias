package com.tencent.smtt.sdk.stat;

import MTT.ThirdAppInfoNew;
import android.content.Context;
import android.telephony.TelephonyManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class HttpUtils
{
  public static final String DEFAULT_ENCODE_NAME = "utf-8";
  public static final String DEFAULT_POST_ADDR = "http://p.mb.qq.com/thirdapp";
  private static final int DEFAULT_TIME_OUT = 20000;
  public static byte[] POST_DATA_KEY = null;
  private static final String TAG = "HttpUtils";
  public static final String WUP_PROXY_DOMAIN = "http://wup.imtt.qq.com:8080";

  static
  {
    try
    {
      POST_DATA_KEY = "65dRa93L".getBytes("utf-8");
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
  }

  public static void doReport(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt, boolean paramBoolean)
  {
    while (true)
    {
      try
      {
        ThirdAppInfoNew localThirdAppInfoNew = new ThirdAppInfoNew();
        localThirdAppInfoNew.sAppName = paramContext.getPackageName();
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        localThirdAppInfoNew.sTime = localSimpleDateFormat.format(Calendar.getInstance().getTime());
        localThirdAppInfoNew.sGuid = paramString1;
        localThirdAppInfoNew.sQua = paramString2;
        localThirdAppInfoNew.sLc = paramString3;
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        if (localTelephonyManager == null)
          continue;
        try
        {
          String str1 = localTelephonyManager.getDeviceId();
          if ((str1 == null) || ("".equals(str1)))
            continue;
          localThirdAppInfoNew.sImei = str1;
          String str2 = localTelephonyManager.getSubscriberId();
          if ((str2 == null) || ("".equals(str2)))
            continue;
          localThirdAppInfoNew.sImsi = str2;
          localThirdAppInfoNew.sMac = "";
          localThirdAppInfoNew.iPv = paramInt;
          if (paramBoolean)
          {
            i = 1;
            localThirdAppInfoNew.iCoreType = i;
            post(localThirdAppInfoNew);
            return;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return;
      }
      int i = 0;
    }
  }

  private static StringBuffer getPostData(ThirdAppInfoNew paramThirdAppInfoNew)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      localStringBuffer.append("sAppName").append("=").append(URLEncoder.encode(paramThirdAppInfoNew.sAppName, "utf-8")).append("|");
      localStringBuffer.append("sTime").append("=").append(URLEncoder.encode(paramThirdAppInfoNew.sTime, "utf-8")).append("|");
      localStringBuffer.append("sQua").append("=").append(URLEncoder.encode(paramThirdAppInfoNew.sQua, "utf-8")).append("|");
      localStringBuffer.append("sLc").append("=").append(URLEncoder.encode(paramThirdAppInfoNew.sLc, "utf-8")).append("|");
      localStringBuffer.append("sGuid").append("=").append(URLEncoder.encode(paramThirdAppInfoNew.sGuid, "utf-8")).append("|");
      localStringBuffer.append("sImei").append("=").append(URLEncoder.encode(paramThirdAppInfoNew.sImei, "utf-8")).append("|");
      localStringBuffer.append("sImsi").append("=").append(URLEncoder.encode(paramThirdAppInfoNew.sImsi, "utf-8")).append("|");
      localStringBuffer.append("sMac").append("=").append(URLEncoder.encode(paramThirdAppInfoNew.sMac, "utf-8")).append("|");
      localStringBuffer.append("iPv").append("=").append(URLEncoder.encode(String.valueOf(paramThirdAppInfoNew.iPv), "utf-8")).append("|");
      localStringBuffer.append("iCoreType").append("=").append(URLEncoder.encode(String.valueOf(paramThirdAppInfoNew.iCoreType), "utf-8"));
      return localStringBuffer;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static void post(ThirdAppInfoNew paramThirdAppInfoNew)
  {
    new Thread("HttpUtils", paramThirdAppInfoNew)
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: getstatic 32	com/tencent/smtt/sdk/stat/HttpUtils:POST_DATA_KEY	[B
        //   3: ifnonnull +13 -> 16
        //   6: ldc 34
        //   8: ldc 36
        //   10: invokevirtual 42	java/lang/String:getBytes	(Ljava/lang/String;)[B
        //   13: putstatic 32	com/tencent/smtt/sdk/stat/HttpUtils:POST_DATA_KEY	[B
        //   16: getstatic 32	com/tencent/smtt/sdk/stat/HttpUtils:POST_DATA_KEY	[B
        //   19: ifnonnull +4 -> 23
        //   22: return
        //   23: new 44	java/net/URL
        //   26: dup
        //   27: ldc 46
        //   29: invokespecial 47	java/net/URL:<init>	(Ljava/lang/String;)V
        //   32: invokevirtual 51	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   35: checkcast 53	java/net/HttpURLConnection
        //   38: astore_2
        //   39: aload_2
        //   40: ldc 55
        //   42: invokevirtual 58	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
        //   45: aload_2
        //   46: iconst_1
        //   47: invokevirtual 62	java/net/HttpURLConnection:setDoOutput	(Z)V
        //   50: aload_2
        //   51: iconst_1
        //   52: invokevirtual 65	java/net/HttpURLConnection:setDoInput	(Z)V
        //   55: aload_2
        //   56: iconst_0
        //   57: invokevirtual 68	java/net/HttpURLConnection:setUseCaches	(Z)V
        //   60: aload_2
        //   61: sipush 20000
        //   64: invokevirtual 72	java/net/HttpURLConnection:setConnectTimeout	(I)V
        //   67: getstatic 78	android/os/Build$VERSION:SDK_INT	I
        //   70: bipush 13
        //   72: if_icmple +11 -> 83
        //   75: aload_2
        //   76: ldc 80
        //   78: ldc 82
        //   80: invokevirtual 86	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   83: aload_0
        //   84: getfield 15	com/tencent/smtt/sdk/stat/HttpUtils$1:val$appInfo	LMTT/ThirdAppInfoNew;
        //   87: invokestatic 90	com/tencent/smtt/sdk/stat/HttpUtils:access$000	(LMTT/ThirdAppInfoNew;)Ljava/lang/StringBuffer;
        //   90: invokevirtual 96	java/lang/StringBuffer:toString	()Ljava/lang/String;
        //   93: ldc 36
        //   95: invokevirtual 42	java/lang/String:getBytes	(Ljava/lang/String;)[B
        //   98: astore 10
        //   100: aload 10
        //   102: astore 4
        //   104: aload 4
        //   106: ifnull -84 -> 22
        //   109: getstatic 32	com/tencent/smtt/sdk/stat/HttpUtils:POST_DATA_KEY	[B
        //   112: aload 4
        //   114: iconst_1
        //   115: invokestatic 102	com/tencent/smtt/sdk/stat/DesUtils:DesEncrypt	([B[BI)[B
        //   118: astore 5
        //   120: aload_2
        //   121: ldc 104
        //   123: ldc 106
        //   125: invokevirtual 86	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   128: aload_2
        //   129: ldc 108
        //   131: aload 5
        //   133: arraylength
        //   134: invokestatic 112	java/lang/String:valueOf	(I)Ljava/lang/String;
        //   137: invokevirtual 86	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   140: aload_2
        //   141: invokevirtual 116	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
        //   144: astore 7
        //   146: aload 7
        //   148: aload 5
        //   150: invokevirtual 122	java/io/OutputStream:write	([B)V
        //   153: aload 7
        //   155: invokevirtual 125	java/io/OutputStream:flush	()V
        //   158: aload_2
        //   159: invokevirtual 129	java/net/HttpURLConnection:getResponseCode	()I
        //   162: sipush 200
        //   165: if_icmpne +12 -> 177
        //   168: ldc 131
        //   170: ldc 133
        //   172: invokestatic 139	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   175: pop
        //   176: return
        //   177: ldc 131
        //   179: ldc 141
        //   181: invokestatic 139	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   184: pop
        //   185: return
        //   186: astore_3
        //   187: aconst_null
        //   188: astore 4
        //   190: goto -86 -> 104
        //   193: astore 11
        //   195: goto -179 -> 16
        //   198: astore 6
        //   200: return
        //   201: astore_1
        //   202: return
        //
        // Exception table:
        //   from	to	target	type
        //   83	100	186	java/lang/Exception
        //   6	16	193	java/io/UnsupportedEncodingException
        //   140	176	198	java/lang/Throwable
        //   177	185	198	java/lang/Throwable
        //   23	45	201	java/io/IOException
      }
    }
    .start();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.stat.HttpUtils
 * JD-Core Version:    0.6.0
 */
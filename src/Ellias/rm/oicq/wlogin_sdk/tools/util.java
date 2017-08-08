package oicq.wlogin_sdk.tools;

import B;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import oicq.wlogin_sdk.request.request_global;
import oicq.wlogin_sdk.tlv_type.tlv_t;

public class util
{
  public static int ASYN_CHECK_IMAGE = 0;
  public static int ASYN_CHECK_SMS = 0;
  public static int ASYN_EXCEPTION = 0;
  public static int ASYN_GET_REMOTE_ST_WITHOUT_PWD = 0;
  public static int ASYN_GET_ST_WITHOUT_PWD = 0;
  public static int ASYN_GET_ST_WITH_PWD = 0;
  public static int ASYN_NEW_REFLUSH_IMAGE = 0;
  public static int ASYN_NEW_TRANSPORT = 0;
  public static int ASYN_PING = 0;
  public static int ASYN_PUSH_CONNECTED = 0;
  public static int ASYN_PUSH_DISCONNECTED = 0;
  public static int ASYN_REFLUSH_IMAGE = 0;
  public static int ASYN_REFLUSH_SMS = 0;
  public static int ASYN_REPORT = 0;
  public static int ASYN_REPORT_ERROR = 0;
  public static int ASYN_TRANSPORT = 0;
  public static int ASYN_TRANSPORT_MSF = 0;
  public static int ASYN_TRANSPORT_PUSH = 0;
  public static final int D = 2;
  public static final int E_A1_DECRYPT = -1014;
  public static final int E_A1_FORMAT = -1016;
  public static final int E_APK_CHK_ERR = -1021;
  public static final int E_DECRYPT = -1002;
  public static final int E_ENCODING = -1013;
  public static final int E_INPUT = -1017;
  public static final int E_NAME_INVALID = -1008;
  public static final int E_NO_KEY = -1004;
  public static final int E_NO_REG_CMD = -1010;
  public static final int E_NO_RET = -1000;
  public static final int E_NO_TGTKEY = -1006;
  public static final int E_NO_UIN = -1003;
  public static final int E_PENDING = -1001;
  public static final int E_PK_LEN = -1009;
  public static final int E_PUSH_REG = -1011;
  public static final int E_RESOLVE_ADDR = -1007;
  public static final int E_SHARE_SERVICE_EXCEPTION = -1020;
  public static final int E_SHARE_SERVICE_PARAM = -1019;
  public static final int E_SHARE_SERVICE_UNCHECK = -1018;
  public static final int E_SYSTEM = -1012;
  public static final int E_TLV_DECRYPT = -1015;
  public static final int E_TLV_VERIFY = -1005;
  public static final String FILE_DIR = "tencent/wtlogin";
  private static int HONEYCOMB = 0;
  public static final int I = 1;
  public static LogCallBack LCB;
  public static boolean LOGCAT_OUT = false;
  public static int LOG_LEVEL = 0;
  public static int MAX_APPID = 0;
  public static final int MAX_CONTENT_SIZE = 2048;
  public static final int MAX_FILE_SIZE = 102400;
  public static int MAX_NAME_LEN = 0;
  public static int SSO_VERSION = 0;
  public static final int S_DELAY = 3;
  public static final int S_GET_IMAGE = 2;
  public static final int S_GET_SMS_CHECK = 160;
  public static final int S_PUSH_RECONNECT = -1100;
  public static final int S_PWD_WRONG = 1;
  public static final int S_SUCCESS = 0;
  public static final String TAG = "wlogin_sdk";
  public static final int W = 0;
  static final char[] base64_encode_chars;
  static final char base64_pad_url = '_';
  static final short[] base64_reverse_table_url;
  public static String logContent;

  static
  {
    ASYN_GET_ST_WITH_PWD = 0;
    ASYN_REFLUSH_IMAGE = 1;
    ASYN_CHECK_IMAGE = 2;
    ASYN_GET_ST_WITHOUT_PWD = 3;
    ASYN_PING = 4;
    ASYN_REPORT = 5;
    ASYN_TRANSPORT = 6;
    ASYN_TRANSPORT_MSF = 7;
    ASYN_TRANSPORT_PUSH = 8;
    ASYN_PUSH_CONNECTED = 9;
    ASYN_PUSH_DISCONNECTED = 10;
    ASYN_REFLUSH_SMS = 11;
    ASYN_CHECK_SMS = 12;
    ASYN_EXCEPTION = 13;
    ASYN_REPORT_ERROR = 14;
    ASYN_GET_REMOTE_ST_WITHOUT_PWD = 15;
    ASYN_NEW_REFLUSH_IMAGE = 16;
    ASYN_NEW_TRANSPORT = 17;
    LOG_LEVEL = 1;
    LCB = null;
    LOGCAT_OUT = true;
    SSO_VERSION = 4;
    logContent = "";
    HONEYCOMB = 11;
    base64_encode_chars = new char[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
    short[] arrayOfShort = new short[256];
    arrayOfShort[0] = -1;
    arrayOfShort[1] = -1;
    arrayOfShort[2] = -1;
    arrayOfShort[3] = -1;
    arrayOfShort[4] = -1;
    arrayOfShort[5] = -1;
    arrayOfShort[6] = -1;
    arrayOfShort[7] = -1;
    arrayOfShort[8] = -1;
    arrayOfShort[9] = -1;
    arrayOfShort[10] = -1;
    arrayOfShort[11] = -1;
    arrayOfShort[12] = -1;
    arrayOfShort[13] = -1;
    arrayOfShort[14] = -1;
    arrayOfShort[15] = -1;
    arrayOfShort[16] = -1;
    arrayOfShort[17] = -1;
    arrayOfShort[18] = -1;
    arrayOfShort[19] = -1;
    arrayOfShort[20] = -1;
    arrayOfShort[21] = -1;
    arrayOfShort[22] = -1;
    arrayOfShort[23] = -1;
    arrayOfShort[24] = -1;
    arrayOfShort[25] = -1;
    arrayOfShort[26] = -1;
    arrayOfShort[27] = -1;
    arrayOfShort[28] = -1;
    arrayOfShort[29] = -1;
    arrayOfShort[30] = -1;
    arrayOfShort[31] = -1;
    arrayOfShort[32] = -1;
    arrayOfShort[33] = -1;
    arrayOfShort[34] = -1;
    arrayOfShort[35] = -1;
    arrayOfShort[36] = -1;
    arrayOfShort[37] = -1;
    arrayOfShort[38] = -1;
    arrayOfShort[39] = -1;
    arrayOfShort[40] = -1;
    arrayOfShort[41] = -1;
    arrayOfShort[42] = 62;
    arrayOfShort[43] = -1;
    arrayOfShort[44] = -1;
    arrayOfShort[45] = 63;
    arrayOfShort[46] = -1;
    arrayOfShort[47] = -1;
    arrayOfShort[48] = 52;
    arrayOfShort[49] = 53;
    arrayOfShort[50] = 54;
    arrayOfShort[51] = 55;
    arrayOfShort[52] = 56;
    arrayOfShort[53] = 57;
    arrayOfShort[54] = 58;
    arrayOfShort[55] = 59;
    arrayOfShort[56] = 60;
    arrayOfShort[57] = 61;
    arrayOfShort[58] = -1;
    arrayOfShort[59] = -1;
    arrayOfShort[60] = -1;
    arrayOfShort[61] = -1;
    arrayOfShort[62] = -1;
    arrayOfShort[63] = -1;
    arrayOfShort[64] = -1;
    arrayOfShort[66] = 1;
    arrayOfShort[67] = 2;
    arrayOfShort[68] = 3;
    arrayOfShort[69] = 4;
    arrayOfShort[70] = 5;
    arrayOfShort[71] = 6;
    arrayOfShort[72] = 7;
    arrayOfShort[73] = 8;
    arrayOfShort[74] = 9;
    arrayOfShort[75] = 10;
    arrayOfShort[76] = 11;
    arrayOfShort[77] = 12;
    arrayOfShort[78] = 13;
    arrayOfShort[79] = 14;
    arrayOfShort[80] = 15;
    arrayOfShort[81] = 16;
    arrayOfShort[82] = 17;
    arrayOfShort[83] = 18;
    arrayOfShort[84] = 19;
    arrayOfShort[85] = 20;
    arrayOfShort[86] = 21;
    arrayOfShort[87] = 22;
    arrayOfShort[88] = 23;
    arrayOfShort[89] = 24;
    arrayOfShort[90] = 25;
    arrayOfShort[91] = -1;
    arrayOfShort[92] = -1;
    arrayOfShort[93] = -1;
    arrayOfShort[94] = -1;
    arrayOfShort[95] = -1;
    arrayOfShort[96] = -1;
    arrayOfShort[97] = 26;
    arrayOfShort[98] = 27;
    arrayOfShort[99] = 28;
    arrayOfShort[100] = 29;
    arrayOfShort[101] = 30;
    arrayOfShort[102] = 31;
    arrayOfShort[103] = 32;
    arrayOfShort[104] = 33;
    arrayOfShort[105] = 34;
    arrayOfShort[106] = 35;
    arrayOfShort[107] = 36;
    arrayOfShort[108] = 37;
    arrayOfShort[109] = 38;
    arrayOfShort[110] = 39;
    arrayOfShort[111] = 40;
    arrayOfShort[112] = 41;
    arrayOfShort[113] = 42;
    arrayOfShort[114] = 43;
    arrayOfShort[115] = 44;
    arrayOfShort[116] = 45;
    arrayOfShort[117] = 46;
    arrayOfShort[118] = 47;
    arrayOfShort[119] = 48;
    arrayOfShort[120] = 49;
    arrayOfShort[121] = 50;
    arrayOfShort[122] = 51;
    arrayOfShort[123] = -1;
    arrayOfShort[124] = -1;
    arrayOfShort[125] = -1;
    arrayOfShort[126] = -1;
    arrayOfShort[127] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[''] = -1;
    arrayOfShort[' '] = -1;
    arrayOfShort['¡'] = -1;
    arrayOfShort['¢'] = -1;
    arrayOfShort['£'] = -1;
    arrayOfShort['¤'] = -1;
    arrayOfShort['¥'] = -1;
    arrayOfShort['¦'] = -1;
    arrayOfShort['§'] = -1;
    arrayOfShort['¨'] = -1;
    arrayOfShort['©'] = -1;
    arrayOfShort['ª'] = -1;
    arrayOfShort['«'] = -1;
    arrayOfShort['¬'] = -1;
    arrayOfShort['­'] = -1;
    arrayOfShort['®'] = -1;
    arrayOfShort['¯'] = -1;
    arrayOfShort['°'] = -1;
    arrayOfShort['±'] = -1;
    arrayOfShort['²'] = -1;
    arrayOfShort['³'] = -1;
    arrayOfShort['´'] = -1;
    arrayOfShort['µ'] = -1;
    arrayOfShort['¶'] = -1;
    arrayOfShort['·'] = -1;
    arrayOfShort['¸'] = -1;
    arrayOfShort['¹'] = -1;
    arrayOfShort['º'] = -1;
    arrayOfShort['»'] = -1;
    arrayOfShort['¼'] = -1;
    arrayOfShort['½'] = -1;
    arrayOfShort['¾'] = -1;
    arrayOfShort['¿'] = -1;
    arrayOfShort['À'] = -1;
    arrayOfShort['Á'] = -1;
    arrayOfShort['Â'] = -1;
    arrayOfShort['Ã'] = -1;
    arrayOfShort['Ä'] = -1;
    arrayOfShort['Å'] = -1;
    arrayOfShort['Æ'] = -1;
    arrayOfShort['Ç'] = -1;
    arrayOfShort['È'] = -1;
    arrayOfShort['É'] = -1;
    arrayOfShort['Ê'] = -1;
    arrayOfShort['Ë'] = -1;
    arrayOfShort['Ì'] = -1;
    arrayOfShort['Í'] = -1;
    arrayOfShort['Î'] = -1;
    arrayOfShort['Ï'] = -1;
    arrayOfShort['Ð'] = -1;
    arrayOfShort['Ñ'] = -1;
    arrayOfShort['Ò'] = -1;
    arrayOfShort['Ó'] = -1;
    arrayOfShort['Ô'] = -1;
    arrayOfShort['Õ'] = -1;
    arrayOfShort['Ö'] = -1;
    arrayOfShort['×'] = -1;
    arrayOfShort['Ø'] = -1;
    arrayOfShort['Ù'] = -1;
    arrayOfShort['Ú'] = -1;
    arrayOfShort['Û'] = -1;
    arrayOfShort['Ü'] = -1;
    arrayOfShort['Ý'] = -1;
    arrayOfShort['Þ'] = -1;
    arrayOfShort['ß'] = -1;
    arrayOfShort['à'] = -1;
    arrayOfShort['á'] = -1;
    arrayOfShort['â'] = -1;
    arrayOfShort['ã'] = -1;
    arrayOfShort['ä'] = -1;
    arrayOfShort['å'] = -1;
    arrayOfShort['æ'] = -1;
    arrayOfShort['ç'] = -1;
    arrayOfShort['è'] = -1;
    arrayOfShort['é'] = -1;
    arrayOfShort['ê'] = -1;
    arrayOfShort['ë'] = -1;
    arrayOfShort['ì'] = -1;
    arrayOfShort['í'] = -1;
    arrayOfShort['î'] = -1;
    arrayOfShort['ï'] = -1;
    arrayOfShort['ð'] = -1;
    arrayOfShort['ñ'] = -1;
    arrayOfShort['ò'] = -1;
    arrayOfShort['ó'] = -1;
    arrayOfShort['ô'] = -1;
    arrayOfShort['õ'] = -1;
    arrayOfShort['ö'] = -1;
    arrayOfShort['÷'] = -1;
    arrayOfShort['ø'] = -1;
    arrayOfShort['ù'] = -1;
    arrayOfShort['ú'] = -1;
    arrayOfShort['û'] = -1;
    arrayOfShort['ü'] = -1;
    arrayOfShort['ý'] = -1;
    arrayOfShort['þ'] = -1;
    arrayOfShort['ÿ'] = -1;
    base64_reverse_table_url = arrayOfShort;
  }

  public static boolean ExistSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  public static void LOGD(String paramString)
  {
    try
    {
      if (LOG_LEVEL >= 2)
      {
        if (LCB != null)
        {
          LCB.OnLog(paramString);
          return;
        }
        if (LOGCAT_OUT)
        {
          Log.d("wlogin_sdk" + getLineInfo(2), paramString);
          return;
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  public static void LOGD(String paramString1, String paramString2)
  {
    try
    {
      if (LOG_LEVEL >= 2)
      {
        if (LCB != null)
        {
          LCB.OnLog(paramString1, paramString2);
          return;
        }
        if (LOGCAT_OUT)
        {
          Log.d("wlogin_sdk" + getLineInfo(2), paramString1 + ":" + paramString2);
          return;
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  public static void LOGI(String paramString)
  {
    try
    {
      if (LOG_LEVEL >= 1)
      {
        if (LCB != null)
        {
          LCB.OnLog(paramString);
          return;
        }
        if (LOGCAT_OUT)
        {
          Log.i("wlogin_sdk" + getLineInfo(2), paramString);
          return;
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  public static void LOGI(String paramString1, Context paramContext, String paramString2, int paramInt)
  {
    try
    {
      if (LOG_LEVEL >= 1)
      {
        if (LCB != null)
          LCB.OnLog(paramString1);
        while (true)
        {
          FileTracer.writeLog(paramContext, paramString2, paramString1);
          return;
          if (!LOGCAT_OUT)
            continue;
          Log.i("wlogin_sdk" + getLineInfo(2), paramString1);
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  public static void LOGW(String paramString1, String paramString2)
  {
    try
    {
      if (LOG_LEVEL >= 0)
      {
        if (LCB != null)
        {
          LCB.OnLog(paramString1, paramString2);
          return;
        }
        if (LOGCAT_OUT)
        {
          Log.w("wlogin_sdk" + getLineInfo(2), paramString1 + ":" + paramString2);
          return;
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  public static void LOGW(String paramString1, String paramString2, Context paramContext, String paramString3)
  {
    try
    {
      if (LOG_LEVEL >= 0)
      {
        if (LCB != null)
          LCB.OnLog(paramString1, paramString2);
        while (true)
        {
          FileTracer.writeLog(paramContext, paramString3, paramString1 + ":" + paramString2);
          return;
          if (!LOGCAT_OUT)
            continue;
          Log.w("wlogin_sdk" + getLineInfo(2), paramString1 + ":" + paramString2);
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  public static byte[] base64_decode_url(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 0;
    int j = 0;
    byte[] arrayOfByte = new byte[24];
    int k = 0;
    int m = 0;
    int n = paramInt;
    int i1 = n - 1;
    int i3;
    if (n > 0)
    {
      i3 = m + 1;
      i = paramArrayOfByte[m];
      if (i != 0);
    }
    while (true)
    {
      int i2 = k;
      if (i == 95);
      switch (j % 4)
      {
      default:
        return arrayOfByte;
        if (i == 95)
          continue;
        if (i == 32)
          i = 42;
        i = base64_reverse_table_url[i];
        if (i < 0)
        {
          m = i3;
          n = i1;
          break;
        }
        int i4;
        switch (j % 4)
        {
        default:
          i4 = k;
        case 0:
        case 1:
        case 2:
        case 3:
        }
        while (true)
        {
          j++;
          k = i4;
          m = i3;
          n = i1;
          break;
          arrayOfByte[k] = (byte)(i << 2);
          i4 = k;
          continue;
          i4 = k + 1;
          arrayOfByte[k] = (byte)(arrayOfByte[k] | i >> 4);
          arrayOfByte[i4] = (byte)((i & 0xF) << 4);
          continue;
          i4 = k + 1;
          arrayOfByte[k] = (byte)(arrayOfByte[k] | i >> 2);
          arrayOfByte[i4] = (byte)((i & 0x3) << 6);
          continue;
          i4 = k + 1;
          arrayOfByte[k] = (byte)(i | arrayOfByte[k]);
        }
      case 0:
      case 1:
        return null;
      case 2:
        i2++;
      case 3:
        (i2 + 1);
        arrayOfByte[i2] = 0;
        return arrayOfByte;
      }
    }
  }

  public static String base64_encode(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = paramArrayOfByte.length;
    int i2;
    for (int j = 0; ; j = i2)
    {
      if (j >= i);
      int m;
      int n;
      int i1;
      while (true)
      {
        return localStringBuffer.toString();
        int k = j + 1;
        m = 0xFF & paramArrayOfByte[j];
        if (k == i)
        {
          localStringBuffer.append(base64_encode_chars[(m >>> 2)]);
          localStringBuffer.append(base64_encode_chars[((m & 0x3) << 4)]);
          continue;
        }
        n = k + 1;
        i1 = 0xFF & paramArrayOfByte[k];
        if (n != i)
          break;
        localStringBuffer.append(base64_encode_chars[(m >>> 2)]);
        localStringBuffer.append(base64_encode_chars[((m & 0x3) << 4 | (i1 & 0xF0) >>> 4)]);
        localStringBuffer.append(base64_encode_chars[((i1 & 0xF) << 2)]);
      }
      i2 = n + 1;
      int i3 = 0xFF & paramArrayOfByte[n];
      localStringBuffer.append(base64_encode_chars[(m >>> 2)]);
      localStringBuffer.append(base64_encode_chars[((m & 0x3) << 4 | (i1 & 0xF0) >>> 4)]);
      localStringBuffer.append(base64_encode_chars[((i1 & 0xF) << 2 | (i3 & 0xC0) >>> 6)]);
      localStringBuffer.append(base64_encode_chars[(i3 & 0x3F)]);
    }
  }

  public static long buf_len(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return 0L;
    return paramArrayOfByte.length;
  }

  public static int buf_to_int16(byte[] paramArrayOfByte, int paramInt)
  {
    return (0xFF00 & paramArrayOfByte[paramInt] << 8) + (0xFF & paramArrayOfByte[(paramInt + 1)] << 0);
  }

  public static int buf_to_int32(byte[] paramArrayOfByte, int paramInt)
  {
    return (0xFF000000 & paramArrayOfByte[paramInt] << 24) + (0xFF0000 & paramArrayOfByte[(paramInt + 1)] << 16) + (0xFF00 & paramArrayOfByte[(paramInt + 2)] << 8) + (0xFF & paramArrayOfByte[(paramInt + 3)] << 0);
  }

  public static long buf_to_int64(byte[] paramArrayOfByte, int paramInt)
  {
    return 0L + (0x0 & paramArrayOfByte[paramInt] << 56) + (0x0 & paramArrayOfByte[(paramInt + 1)] << 48) + (0x0 & paramArrayOfByte[(paramInt + 2)] << 40) + (0x0 & paramArrayOfByte[(paramInt + 3)] << 32) + (0xFF000000 & paramArrayOfByte[(paramInt + 4)] << 24) + (0xFF0000 & paramArrayOfByte[(paramInt + 5)] << 16) + (0xFF00 & paramArrayOfByte[(paramInt + 6)] << 8) + (0xFF & paramArrayOfByte[(paramInt + 7)] << 0);
  }

  public static int buf_to_int8(byte[] paramArrayOfByte, int paramInt)
  {
    return 0xFF & paramArrayOfByte[paramInt];
  }

  public static String buf_to_string(byte[] paramArrayOfByte)
  {
    String str;
    if (paramArrayOfByte == null)
      str = "";
    while (true)
    {
      return str;
      str = "";
      for (int i = 0; i < paramArrayOfByte.length; i++)
        str = new StringBuilder(String.valueOf(str)).append(Integer.toHexString(0xF & paramArrayOfByte[i] >> 4)).toString() + Integer.toHexString(0xF & paramArrayOfByte[i]);
    }
  }

  public static String buf_to_string(byte[] paramArrayOfByte, int paramInt)
  {
    String str;
    if (paramArrayOfByte == null)
      str = "";
    while (true)
    {
      return str;
      if (paramInt > paramArrayOfByte.length)
        paramInt = paramArrayOfByte.length;
      str = "";
      for (int i = 0; i < paramInt; i++)
        str = new StringBuilder(String.valueOf(str)).append(Integer.toHexString(0xF & paramArrayOfByte[i] >> 4)).toString() + Integer.toHexString(0xF & paramArrayOfByte[i]);
    }
  }

  public static char[] byte2char(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[paramArrayOfByte.length];
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfChar.length)
        return arrayOfChar;
      arrayOfChar[i] = (char)paramArrayOfByte[i];
    }
  }

  public static tlv_t bytes_to_tlv(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length < 4);
    do
      return null;
    while (4 + buf_to_int16(paramArrayOfByte, 2) != paramArrayOfByte.length);
    tlv_t localtlv_t = new tlv_t();
    localtlv_t.set_buf(paramArrayOfByte, paramArrayOfByte.length);
    return localtlv_t;
  }

  public static byte[] char2byte(char[] paramArrayOfChar, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    for (int i = 0; ; i++)
    {
      if (i >= paramInt)
        return arrayOfByte;
      arrayOfByte[i] = (byte)paramArrayOfChar[i];
    }
  }

  public static Boolean check_uin_account(String paramString)
  {
    try
    {
      if (Long.parseLong(paramString) > 3000000000L)
        return Boolean.valueOf(false);
      Boolean localBoolean = Boolean.valueOf(true);
      return localBoolean;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return Boolean.valueOf(false);
  }

  public static void chg_retry_type(Context paramContext)
  {
    if (get_net_retry_type(paramContext) == 0)
    {
      set_net_retry_type(paramContext, 1);
      return;
    }
    set_net_retry_type(paramContext, 0);
  }

  public static byte[] compress(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return paramArrayOfByte;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      DeflaterOutputStream localDeflaterOutputStream = new DeflaterOutputStream(localByteArrayOutputStream);
      localDeflaterOutputStream.write(paramArrayOfByte);
      localDeflaterOutputStream.close();
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localIOException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      LOGW("exception", localStringWriter.toString());
    }
    return null;
  }

  public static void decompress(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0));
    int j;
    label35: ByteArrayOutputStream localByteArrayOutputStream;
    ByteArrayInputStream localByteArrayInputStream;
    while (true)
    {
      return;
      LOGI("data len:" + paramArrayOfByte.length);
      int i = 0;
      j = 0;
      if (paramArrayOfByte.length <= i + 3)
        break;
      int k = buf_to_int32(paramArrayOfByte, i);
      if (paramArrayOfByte.length <= 3 + (i + k))
        continue;
      byte[] arrayOfByte1 = new byte[k];
      System.arraycopy(paramArrayOfByte, i + 4, arrayOfByte1, 0, k);
      i = k + (i + 4);
      j++;
      LOGI("len:" + k);
      localByteArrayOutputStream = new ByteArrayOutputStream();
      localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte1);
    }
    while (true)
    {
      byte[] arrayOfByte2;
      int m;
      try
      {
        InflaterInputStream localInflaterInputStream = new InflaterInputStream(localByteArrayInputStream);
        arrayOfByte2 = new byte[1024];
        m = localInflaterInputStream.read(arrayOfByte2);
        if (m != -1)
          break label239;
        LOGI(j + localByteArrayOutputStream.toString());
      }
      catch (IOException localIOException)
      {
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localIOException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        LOGW("exception", localStringWriter.toString());
      }
      break label35;
      break;
      label239: localByteArrayOutputStream.write(arrayOfByte2, 0, m);
    }
  }

  public static void deleteExpireFile(String paramString, int paramInt)
  {
    if ((paramString == null) || (paramString.length() == 0));
    while (true)
    {
      return;
      LOGI("file path:" + paramString);
      try
      {
        File localFile = new File(paramString);
        if (!localFile.isDirectory())
          continue;
        File[] arrayOfFile = localFile.listFiles();
        if (arrayOfFile == null)
          continue;
        int i = arrayOfFile.length;
        for (int j = 0; j < i; j++)
        {
          if ((arrayOfFile[j].isDirectory()) || ((System.currentTimeMillis() - arrayOfFile[j].lastModified()) / 1000L <= paramInt))
            continue;
          arrayOfFile[j].delete();
        }
      }
      catch (Exception localException)
      {
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        LOGW("exception", localStringWriter.toString());
      }
    }
  }

  public static void deleteExpireLog(Context paramContext)
  {
    if (paramContext == null)
      return;
    try
    {
      if (ExistSDCard())
      {
        deleteExpireFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "tencent/wtlogin" + "/" + paramContext.getPackageName(), 691200);
        return;
      }
    }
    catch (Exception localException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      LOGW("exception", localStringWriter.toString());
      return;
    }
    deleteExpireFile(paramContext.getFilesDir().getPath() + "/" + "tencent/wtlogin", 259200);
  }

  public static int format_ret_code(int paramInt, String paramString)
  {
    switch (paramInt)
    {
    default:
    case 0:
    case -1000:
    case 2:
    case -1015:
    case -1014:
    case -1002:
    case 6:
    case 16:
    }
    do
    {
      return 17;
      return 0;
      return 1;
      return 2;
      return 5;
    }
    while (paramString == null);
    return 16;
  }

  public static String getCurrentDay()
  {
    return new SimpleDateFormat("yyyyMMdd").format(new Date());
  }

  public static String getDate()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return "[" + localSimpleDateFormat.format(new Date()) + "]";
  }

  public static String getFileMD5(File paramFile)
  {
    if (!paramFile.isFile())
      return null;
    byte[] arrayOfByte = new byte[1024];
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      FileInputStream localFileInputStream = new FileInputStream(paramFile);
      try
      {
        while (true)
        {
          int i = localFileInputStream.read(arrayOfByte, 0, 1024);
          if (i == -1)
          {
            localFileInputStream.close();
            return new BigInteger(1, localMessageDigest.digest()).toString(16);
          }
          localMessageDigest.update(arrayOfByte, 0, i);
        }
      }
      catch (Exception localException1)
      {
      }
      label85: printException(localException1);
      return null;
    }
    catch (Exception localException2)
    {
      break label85;
    }
  }

  public static long getFileModifyTime(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    while (true)
    {
      return 0L;
      try
      {
        File localFile = new File(paramString);
        if ((!localFile.exists()) || (!localFile.isFile()))
          continue;
        long l = localFile.lastModified();
        return l;
      }
      catch (Exception localException)
      {
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        LOGW("exception", localStringWriter.toString());
      }
    }
    return 0L;
  }

  public static int getFileSize(String paramString)
  {
    try
    {
      File localFile = new File(paramString);
      boolean bool1 = localFile.exists();
      int i = 0;
      if (bool1)
      {
        boolean bool2 = localFile.isFile();
        i = 0;
        if (bool2)
        {
          long l = localFile.length();
          i = (int)l;
        }
      }
      return i;
    }
    catch (Exception localException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      LOGW("exception", localStringWriter.toString());
    }
    return 0;
  }

  public static String getLineInfo(int paramInt)
  {
    if (paramInt < 0)
      return "";
    try
    {
      StackTraceElement localStackTraceElement = new java.lang.Throwable().getStackTrace()[paramInt];
      String str = "[" + localStackTraceElement.getFileName() + ":" + localStackTraceElement.getLineNumber() + "]";
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  public static String getLogFileName(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null) || (paramString.length() == 0))
      return null;
    try
    {
      if (ExistSDCard())
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "tencent/wtlogin" + "/" + paramContext.getPackageName() + "/" + base64_encode(paramString.getBytes());
      String str = paramContext.getFilesDir().getPath() + "/" + "tencent/wtlogin" + "/" + base64_encode(paramString.getBytes());
      return str;
    }
    catch (Exception localException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      LOGW("exception", localStringWriter.toString());
    }
    return null;
  }

  public static long getLogModifyTime(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null) || (paramString.length() == 0))
      return 0L;
    return getFileModifyTime(getLogFileName(paramContext, paramString));
  }

  public static String getPkgMD5FromPid(Context paramContext, long paramLong)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    int i = localList.size();
    for (int j = 0; ; j++)
    {
      if (j >= i);
      while (true)
      {
        return null;
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localList.get(j);
        if (localRunningAppProcessInfo.pid == paramLong)
          try
          {
            if ((localRunningAppProcessInfo.pkgList == null) || (localRunningAppProcessInfo.pkgList.length <= 0) || (localRunningAppProcessInfo.pkgList[0].length() <= 0))
              continue;
            String str = getFileMD5(new File(paramContext.getPackageManager().getPackageInfo(localRunningAppProcessInfo.pkgList[0], 0).applicationInfo.publicSourceDir));
            return str;
          }
          catch (Exception localException)
          {
            printException(localException);
          }
      }
    }
  }

  public static String getPkgNameFromPid(Context paramContext, long paramLong)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    int i = localList.size();
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return "";
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localList.get(j);
      if (localRunningAppProcessInfo.pid != paramLong)
        continue;
      try
      {
        if ((localRunningAppProcessInfo.pkgList != null) && (localRunningAppProcessInfo.pkgList.length > 0) && (localRunningAppProcessInfo.pkgList[0].length() > 0))
        {
          PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(localRunningAppProcessInfo.pkgList[0], 64);
          if (localPackageInfo != null)
            continue;
          String str = localPackageInfo.packageName;
          return str;
        }
        return "";
      }
      catch (Exception localException)
      {
        printException(localException);
      }
    }
  }

  public static byte[] getPkgPublicKeyFromApkName(Context paramContext, String paramString)
  {
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramString, 64);
      if ((localPackageInfo.signatures != null) && (localPackageInfo.signatures.length > 0))
      {
        if (localPackageInfo.signatures[0] == null)
          return null;
        byte[] arrayOfByte = MD5.toMD5Byte(((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(localPackageInfo.signatures[0].toByteArray()))).getPublicKey().getEncoded());
        return arrayOfByte;
      }
    }
    catch (Exception localException)
    {
      printException(localException);
    }
    return null;
  }

  public static byte[] getPkgPublicKeyFromPid(Context paramContext, long paramLong)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    int i = localList.size();
    for (int j = 0; ; j++)
    {
      if (j >= i);
      while (true)
      {
        return null;
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localList.get(j);
        if (localRunningAppProcessInfo.pid == paramLong)
          try
          {
            if ((localRunningAppProcessInfo.pkgList == null) || (localRunningAppProcessInfo.pkgList.length <= 0) || (localRunningAppProcessInfo.pkgList[0].length() <= 0))
              continue;
            PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(localRunningAppProcessInfo.pkgList[0], 64);
            if ((localPackageInfo.signatures == null) || (localPackageInfo.signatures.length <= 0) || (localPackageInfo.signatures[0] == null))
              continue;
            byte[] arrayOfByte = MD5.toMD5Byte(((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(localPackageInfo.signatures[0].toByteArray()))).getPublicKey().getEncoded());
            return arrayOfByte;
          }
          catch (Exception localException)
          {
            printException(localException);
          }
      }
    }
  }

  public static long getSDAllSize()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    return localStatFs.getBlockSize() * localStatFs.getBlockCount();
  }

  public static long getSDFreeSize()
  {
    File localFile = Environment.getExternalStorageDirectory();
    Log.i("wlogin_sdk", "sdcard path:" + localFile.getAbsolutePath());
    StatFs localStatFs = new StatFs(localFile.getPath());
    return localStatFs.getBlockSize() * localStatFs.getAvailableBlocks();
  }

  public static String getSdkVersion()
  {
    return "[" + SSO_VERSION + "]";
  }

  public static String getThreadId()
  {
    return "[" + Thread.currentThread().getId() + "]";
  }

  public static String getUser(String paramString)
  {
    if (paramString != null)
      return "[" + paramString + "]";
    return "[]";
  }

  public static byte[] get_IMEI(Context paramContext)
  {
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      String str1 = null;
      if (localTelephonyManager != null)
        str1 = localTelephonyManager.getDeviceId();
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      String str2 = null;
      if (localWifiManager != null)
      {
        WifiInfo localWifiInfo = localWifiManager.getConnectionInfo();
        str2 = null;
        if (localWifiInfo != null)
          str2 = localWifiInfo.getMacAddress();
      }
      String str3 = "";
      if (str1 != null)
        str3 = str3 + str1;
      if (str2 != null)
        str3 = str3 + str2;
      if (str3.length() <= 0)
        return new byte[0];
      byte[] arrayOfByte = MD5.toMD5Byte(str3.getBytes());
      return arrayOfByte;
    }
    catch (Exception localException)
    {
    }
    return new byte[0];
  }

  public static byte[] get_apk_id(Context paramContext)
  {
    try
    {
      byte[] arrayOfByte = paramContext.getPackageName().getBytes();
      return arrayOfByte;
    }
    catch (Exception localException)
    {
    }
    return new byte[0];
  }

  public static byte[] get_apk_sig(Context paramContext, String paramString)
  {
    try
    {
      byte[] arrayOfByte = getPkgPublicKeyFromApkName(paramContext, paramString);
      return arrayOfByte;
    }
    catch (Exception localException)
    {
    }
    return new byte[0];
  }

  public static byte[] get_apk_v(Context paramContext, String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionName.getBytes();
      return arrayOfByte;
    }
    catch (Exception localException)
    {
    }
    return new byte[0];
  }

  public static String get_apn_string(Context paramContext)
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo.getType() == 0)
      {
        String str = localNetworkInfo.getExtraInfo();
        if (str != null)
          return str;
      }
      else
      {
        return "wifi";
      }
    }
    catch (Exception localException)
    {
    }
    return "wifi";
  }

  public static byte get_char(byte paramByte)
  {
    if ((paramByte >= 48) && (paramByte <= 57))
      return (byte)(paramByte - 48);
    if ((paramByte >= 97) && (paramByte <= 102))
      return (byte)(10 + (paramByte - 97));
    if ((paramByte >= 65) && (paramByte <= 70))
      return (byte)(10 + (paramByte - 65));
    return 0;
  }

  public static long get_cur_time()
  {
    return request_global.get_cur_time();
  }

  public static byte[] get_imei_id(Context paramContext)
  {
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      if (localTelephonyManager != null)
      {
        String str = localTelephonyManager.getDeviceId();
        if (str != null)
        {
          byte[] arrayOfByte = str.getBytes();
          return arrayOfByte;
        }
      }
    }
    catch (Exception localException)
    {
    }
    return new byte[0];
  }

  public static byte[] get_ksid(Context paramContext)
  {
    Object localObject = new String("").getBytes();
    try
    {
      byte[] arrayOfByte = string_to_buf(paramContext.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString("ksid", new String("")));
      localObject = arrayOfByte;
      if ((localObject == null) || (localObject.length <= 0))
      {
        LOGD("get_ksid:null");
        return localObject;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        LOGW("exception", localStringWriter.toString());
      }
      LOGD("get_ksid:" + buf_to_string(localObject));
    }
    return (B)localObject;
  }

  public static byte[] get_mac_addr(Context paramContext)
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if (localWifiManager != null)
      {
        WifiInfo localWifiInfo = localWifiManager.getConnectionInfo();
        if (localWifiInfo != null)
        {
          String str = localWifiInfo.getMacAddress();
          if (str != null)
          {
            byte[] arrayOfByte = str.getBytes();
            return arrayOfByte;
          }
        }
      }
    }
    catch (Exception localException)
    {
    }
    return new byte[0];
  }

  public static int get_net_retry_type(Context paramContext)
  {
    return paramContext.getSharedPreferences("WLOGIN_NET_RETRY_TYPE", 0).getInt("type", 0);
  }

  public static int get_network_type(Context paramContext)
  {
    try
    {
      int j = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo().getType();
      i = j;
      if (i == 0)
        return 1;
    }
    catch (Exception localException)
    {
      int i;
      while (true)
        i = 0;
      if (i == 1)
        return 2;
    }
    return 0;
  }

  public static int get_os_type(int paramInt)
  {
    return 1;
  }

  public static byte[] get_os_type()
  {
    return new String("android").getBytes();
  }

  public static byte[] get_os_version()
  {
    return Build.VERSION.RELEASE.getBytes();
  }

  @SuppressLint({"NewApi"})
  @TargetApi(4)
  public static String get_proxy_ip()
  {
    if (Build.VERSION.SDK_INT < HONEYCOMB)
      return Proxy.getDefaultHost();
    return System.getProperty("http.proxyHost");
  }

  @SuppressLint({"NewApi", "NewApi"})
  @TargetApi(4)
  public static int get_proxy_port()
  {
    if (Build.VERSION.SDK_INT < HONEYCOMB)
      return Proxy.getDefaultPort();
    try
    {
      int i = Integer.parseInt(System.getProperty("http.proxyPort"));
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
    }
    return -1;
  }

  public static int get_rand_16()
  {
    return (int)(2147483647.0D * Math.random());
  }

  public static byte[] get_rand_16byte(SecureRandom paramSecureRandom)
  {
    return SecureRandom.getSeed(16);
  }

  public static byte[] get_rand_16byte(SecureRandom paramSecureRandom, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte1 = SecureRandom.getSeed(16);
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length + paramArrayOfByte.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte2, arrayOfByte1.length, paramArrayOfByte.length);
    return MD5.toMD5Byte(arrayOfByte2);
  }

  public static int get_rand_32()
  {
    return (int)(2147483647.0D * Math.random());
  }

  public static byte[] get_rand_8byte()
  {
    int i = (int)(2147483647.0D * Math.random());
    byte[] arrayOfByte = new byte[8];
    int32_to_buf(arrayOfByte, 0, i);
    int32_to_buf(arrayOfByte, 4, (int)(2147483647.0D * Math.random()));
    return arrayOfByte;
  }

  public static byte[] get_rand_IMEI(Context paramContext)
  {
    byte[] arrayOfByte = new byte[16];
    int32_to_buf(arrayOfByte, 0, get_rand_32());
    int32_to_buf(arrayOfByte, 4, get_rand_32());
    int32_to_buf(arrayOfByte, 8, get_rand_32());
    int32_to_buf(arrayOfByte, 12, get_rand_32());
    return MD5.toMD5Byte(arrayOfByte);
  }

  public static String get_release_time()
  {
    return new String("2013/07/16 19:40");
  }

  public static byte[] get_saved_imei(Context paramContext)
  {
    Object localObject = new byte[0];
    try
    {
      byte[] arrayOfByte = string_to_buf(paramContext.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString("imei", new String("")));
      localObject = arrayOfByte;
      if ((localObject == null) || (localObject.length <= 0))
        return new byte[0];
    }
    catch (Exception localException)
    {
      while (true)
      {
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        LOGW("exception", localStringWriter.toString());
      }
      LOGD("get_imei:" + buf_to_string(localObject));
    }
    return (B)localObject;
  }

  public static int get_saved_network_type(Context paramContext)
  {
    return paramContext.getSharedPreferences("WLOGIN_SERVER_INFO", 0).getInt("network_type", 0);
  }

  public static long get_server_cur_time()
  {
    return request_global.get_server_cur_time();
  }

  public static byte[] get_server_host1(Context paramContext)
  {
    return paramContext.getSharedPreferences("WLOGIN_SERVER_INFO", 0).getString("host1", "").getBytes();
  }

  public static byte[] get_server_host2(Context paramContext)
  {
    return paramContext.getSharedPreferences("WLOGIN_SERVER_INFO", 0).getString("host2", "").getBytes();
  }

  public static byte[] get_signature(Context paramContext)
  {
    try
    {
      byte[] arrayOfByte = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures[0].toByteArray();
      return arrayOfByte;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static byte[] get_sim_operator_name(Context paramContext)
  {
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      if (localTelephonyManager.getSimState() == 5)
      {
        byte[] arrayOfByte = localTelephonyManager.getSimOperatorName().getBytes();
        return arrayOfByte;
      }
    }
    catch (Exception localException)
    {
    }
    return new byte[0];
  }

  public static byte[] get_wap_server_host1(Context paramContext)
  {
    return paramContext.getSharedPreferences("WLOGIN_SERVER_INFO", 0).getString("wap-host1", "").getBytes();
  }

  public static byte[] get_wap_server_host2(Context paramContext)
  {
    return paramContext.getSharedPreferences("WLOGIN_SERVER_INFO", 0).getString("wap-host2", "").getBytes();
  }

  public static void int16_to_buf(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[(paramInt1 + 1)] = (byte)(paramInt2 >> 0);
    paramArrayOfByte[(paramInt1 + 0)] = (byte)(paramInt2 >> 8);
  }

  public static void int32_to_buf(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[(paramInt1 + 3)] = (byte)(paramInt2 >> 0);
    paramArrayOfByte[(paramInt1 + 2)] = (byte)(paramInt2 >> 8);
    paramArrayOfByte[(paramInt1 + 1)] = (byte)(paramInt2 >> 16);
    paramArrayOfByte[(paramInt1 + 0)] = (byte)(paramInt2 >> 24);
  }

  public static void int64_to_buf(byte[] paramArrayOfByte, int paramInt, long paramLong)
  {
    paramArrayOfByte[(paramInt + 7)] = (byte)(int)(paramLong >> 0);
    paramArrayOfByte[(paramInt + 6)] = (byte)(int)(paramLong >> 8);
    paramArrayOfByte[(paramInt + 5)] = (byte)(int)(paramLong >> 16);
    paramArrayOfByte[(paramInt + 4)] = (byte)(int)(paramLong >> 24);
    paramArrayOfByte[(paramInt + 3)] = (byte)(int)(paramLong >> 32);
    paramArrayOfByte[(paramInt + 2)] = (byte)(int)(paramLong >> 40);
    paramArrayOfByte[(paramInt + 1)] = (byte)(int)(paramLong >> 48);
    paramArrayOfByte[(paramInt + 0)] = (byte)(int)(paramLong >> 56);
  }

  public static void int64_to_buf32(byte[] paramArrayOfByte, int paramInt, long paramLong)
  {
    paramArrayOfByte[(paramInt + 3)] = (byte)(int)(paramLong >> 0);
    paramArrayOfByte[(paramInt + 2)] = (byte)(int)(paramLong >> 8);
    paramArrayOfByte[(paramInt + 1)] = (byte)(int)(paramLong >> 16);
    paramArrayOfByte[(paramInt + 0)] = (byte)(int)(paramLong >> 24);
  }

  public static void int8_to_buf(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[(paramInt1 + 0)] = (byte)(paramInt2 >> 0);
  }

  public static boolean isFileExist(String paramString)
  {
    try
    {
      boolean bool = new File(paramString).exists();
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static boolean is_wap_proxy_retry(Context paramContext)
  {
    try
    {
      String str = get_apn_string(paramContext);
      if (str != null)
      {
        if (str.equalsIgnoreCase("cmwap"))
          return true;
        if ((!str.equalsIgnoreCase("uniwap")) && (!str.equalsIgnoreCase("ctwap")))
        {
          boolean bool = str.equalsIgnoreCase("3gwap");
          if (bool);
        }
      }
      else
      {
        label53: return false;
      }
    }
    catch (Exception localException)
    {
      break label53;
    }
    return true;
  }

  public static boolean is_wap_retry(Context paramContext)
  {
    return get_net_retry_type(paramContext) != 0;
  }

  public static void printException(Exception paramException)
  {
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
    paramException.printStackTrace(localPrintWriter);
    localPrintWriter.flush();
    localStringWriter.flush();
    LOGW("exception:", localStringWriter.toString());
  }

  public static byte[] readFile(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return null;
    try
    {
      File localFile = new File(paramString);
      if ((localFile.exists()) && (localFile.isFile()))
      {
        FileInputStream localFileInputStream = new FileInputStream(paramString);
        int i = localFileInputStream.available();
        if (i > 106496)
        {
          localFileInputStream.close();
          return null;
        }
        byte[] arrayOfByte = new byte[i];
        localFileInputStream.read(arrayOfByte);
        localFileInputStream.close();
        return arrayOfByte;
      }
    }
    catch (Exception localException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      LOGW("exception", localStringWriter.toString());
      return null;
    }
    return null;
  }

  public static byte[] readLog(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null) || (paramString.length() == 0))
      return null;
    return readFile(getLogFileName(paramContext, paramString));
  }

  public static void save_imei(Context paramContext, byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0))
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
      localEditor.putString("imei", buf_to_string(paramArrayOfByte));
      localEditor.commit();
    }
  }

  public static void save_network_type(Context paramContext, int paramInt)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("WLOGIN_SERVER_INFO", 0).edit();
    localEditor.putInt("network_type", paramInt);
    localEditor.commit();
  }

  public static void set_ksid(Context paramContext, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = get_ksid(paramContext);
    if ((arrayOfByte == null) || (arrayOfByte.length <= 0))
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
      localEditor.putString("ksid", buf_to_string(paramArrayOfByte));
      localEditor.commit();
    }
  }

  public static void set_net_retry_type(Context paramContext, int paramInt)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("WLOGIN_NET_RETRY_TYPE", 0).edit();
    localEditor.putInt("type", paramInt);
    localEditor.commit();
  }

  public static void set_server_host1(Context paramContext, byte[] paramArrayOfByte)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("WLOGIN_SERVER_INFO", 0).edit();
    localEditor.putString("host1", new String(paramArrayOfByte));
    localEditor.commit();
  }

  public static void set_server_host2(Context paramContext, byte[] paramArrayOfByte)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("WLOGIN_SERVER_INFO", 0).edit();
    localEditor.putString("host2", new String(paramArrayOfByte));
    localEditor.commit();
  }

  public static void set_wap_server_host1(Context paramContext, byte[] paramArrayOfByte)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("WLOGIN_SERVER_INFO", 0).edit();
    localEditor.putString("wap-host1", new String(paramArrayOfByte));
    localEditor.commit();
  }

  public static void set_wap_server_host2(Context paramContext, byte[] paramArrayOfByte)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("WLOGIN_SERVER_INFO", 0).edit();
    localEditor.putString("wap-host2", new String(paramArrayOfByte));
    localEditor.commit();
  }

  public static byte[] string_to_buf(String paramString)
  {
    byte[] arrayOfByte;
    if (paramString == null)
      arrayOfByte = new byte[0];
    while (true)
    {
      return arrayOfByte;
      arrayOfByte = new byte[paramString.length() / 2];
      for (int i = 0; i < paramString.length() / 2; i++)
        arrayOfByte[i] = (byte)((get_char((byte)paramString.charAt(i * 2)) << 4) + get_char((byte)paramString.charAt(1 + i * 2)));
    }
  }

  public static byte[] tlv_to_bytes(int paramInt, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[4 + paramArrayOfByte.length];
    int16_to_buf(arrayOfByte, 0, paramInt);
    int16_to_buf(arrayOfByte, 2, paramArrayOfByte.length);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 4, paramArrayOfByte.length);
    return arrayOfByte;
  }

  public static boolean unsigned_bigthan(long paramLong1, long paramLong2)
  {
    if ((paramLong1 < 0L) && (paramLong2 >= 0L));
    do
    {
      return true;
      if ((paramLong1 >= 0L) && (paramLong2 < 0L))
        return false;
    }
    while (paramLong1 > paramLong2);
    return false;
  }

  public static void writeFile(String paramString, byte[] paramArrayOfByte)
  {
    monitorenter;
    if (paramString != null);
    try
    {
      int i = paramString.length();
      if (i == 0);
      while (true)
      {
        return;
        try
        {
          File localFile1 = new File(paramString);
          if (!localFile1.exists())
          {
            File localFile2 = localFile1.getParentFile();
            if ((localFile2 == null) || ((!localFile2.mkdirs()) && (!localFile2.isDirectory())))
              continue;
          }
          if (getFileSize(paramString) >= 102400)
            continue;
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile1, true);
          localFileOutputStream.write(paramArrayOfByte);
          localFileOutputStream.close();
        }
        catch (Exception localException)
        {
          StringWriter localStringWriter = new StringWriter();
          PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
          localException.printStackTrace(localPrintWriter);
          localPrintWriter.flush();
          localStringWriter.flush();
          LOGW("exception", localStringWriter.toString());
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static final class APNName
  {
    public static final String NAME_3GNET = "3gnet";
    public static final String NAME_3GWAP = "3gwap";
    public static final String NAME_CMCC = "cmcc";
    public static final String NAME_CMCT = "cmct";
    public static final String NAME_CMNET = "cmnet";
    public static final String NAME_CMWAP = "cmwap";
    public static final String NAME_CTNET = "ctnet";
    public static final String NAME_CTWAP = "ctwap";
    public static final String NAME_UNICOM = "unicom";
    public static final String NAME_UNINET = "uninet";
    public static final String NAME_UNIWAP = "uniwap";
    public static final String NAME_WIFI = "wifi";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tools.util
 * JD-Core Version:    0.6.0
 */
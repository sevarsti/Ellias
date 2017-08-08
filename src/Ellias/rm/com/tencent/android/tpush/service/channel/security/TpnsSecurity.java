package com.tencent.android.tpush.service.channel.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import java.io.File;

public class TpnsSecurity
{
  private static final String DEVICE_ID_KEY_NAME = "deviceId_v1";
  private static final String DEVICE_ID_PREFIX = "deviceId_";
  private static final String DEVICE_ID_VERSION = "v1";
  private static final String SETTINGS_DEVICE_ID_KEY_NAME = ".com.tencent.tpush.cache.deviceId_v1";
  private static final String SETTINGS_DEVICE_ID_PREFIX = ".com.tencent.tpush.cache";
  private static final String SHAREPREFERENCE_FILE_NAME = "device_id";
  private static boolean checkNativeMethod = false;
  private static boolean loadedTpnsSecuritySo = false;
  public static final String tpnsSecurityLibFullName = "libtpnsSecurity.so";
  private static final String tpnsSecurityLibName = "tpnsSecurity";
  protected byte[] encKey;
  protected long inc = 0L;
  protected long incRemote;
  protected byte[] iv;
  protected byte[] key;
  protected long random;

  static
  {
    try
    {
      System.loadLibrary("tpnsSecurity");
      TLog.i("XGService", "libtpnsSecurity.so has loaded");
      loadedTpnsSecuritySo = true;
      checkNativeMethod = false;
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        TLog.e("XGService", "can not load library,error:" + localThrowable.toString());
        loadedTpnsSecuritySo = false;
      }
    }
  }

  private static boolean checkNativeMethod(Context paramContext)
  {
    try
    {
      byte[] arrayOfByte = "OnlyForTest".getBytes();
      generateAESKey();
      generateIV(System.currentTimeMillis());
      oiSymmetryEncrypt2Byte("OnlyForTest");
      oiSymmetryDecrypt2Byte(arrayOfByte);
      generateLocalSocketServieNameNative(paramContext);
      getBusinessDeviceIdNative(paramContext);
      getEncryptAPKSignatureNative(paramContext);
      checkNativeMethod = true;
      Log.i("XGService", "checkNativeMethod passed.");
      return checkNativeMethod;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        Log.e("XGService", "check native method failed , error:" + localThrowable.toString());
        Log.e("XGService", "please update libtpnsSecurity.so to your android project and try again.");
        checkNativeMethod = false;
      }
    }
  }

  public static boolean checkTpnsSecurityLibSo(Context paramContext)
  {
    if ((loadedTpnsSecuritySo) && (checkNativeMethod));
    while (true)
    {
      return true;
      String str;
      if (paramContext != null)
      {
        TLog.i("XGService", "@@ checkTpnsSecuritySo(" + paramContext.getPackageName() + ")");
        if (!loadedTpnsSecuritySo)
          str = paramContext.getFilesDir().getParentFile().getAbsolutePath() + File.separator + "lib" + File.separator + "libtpnsSecurity.so";
      }
      try
      {
        Log.i("XGService", ">> checkTpnsSecuritySo loadpath=" + str);
        System.load(str);
        Log.i("XGService", "libtpnsSecurity.so has loaded");
        loadedTpnsSecuritySo = true;
        if (loadedTpnsSecuritySo)
          checkNativeMethod(paramContext);
        if ((loadedTpnsSecuritySo) && (checkNativeMethod))
          continue;
        return false;
      }
      catch (Throwable localThrowable)
      {
        while (true)
        {
          Log.e("XGService", "can not load library from " + str + ",error:" + localThrowable.toString());
          Log.e("XGService", "please add libtpnsSecurity.so to your android project and try again.");
          loadedTpnsSecuritySo = false;
        }
      }
    }
  }

  protected static native byte[] generateAESKey();

  protected static native byte[] generateIV(long paramLong);

  public static String generateLocalSocketServieName(Context paramContext)
  {
    if (paramContext != null)
      try
      {
        String str = generateLocalSocketServieNameNative(paramContext);
        return str;
      }
      catch (Throwable localThrowable)
      {
        TLog.e("XGService", "generateLocalSocketServieName 未知错误", localThrowable);
      }
    throw new SecurityException("generate local socket server name error");
  }

  private static native String generateLocalSocketServieNameNative(Object paramObject);

  public static String getBusinessDeviceId(Context paramContext)
  {
    if (paramContext == null)
      throw new SecurityException("get device id error cause context is null");
    String str1 = getSettingsLocalDeviceId(paramContext);
    if (str1 != null)
      return str1;
    String str2 = getPreferenceLocalDeviceId(paramContext);
    if (str2 != null)
    {
      setSettingsLocalDeviceId(paramContext, str2);
      return str2;
    }
    String str3 = getBusinessDeviceIdNative(paramContext);
    setPreferenceLocalDeviceId(paramContext, str3);
    setSettingsLocalDeviceId(paramContext, str3);
    return str3;
  }

  private static native String getBusinessDeviceIdNative(Object paramObject);

  public static String getEncryptAPKSignature(Context paramContext)
  {
    if (paramContext != null)
      return getEncryptAPKSignatureNative(paramContext);
    throw new SecurityException("get encrypt apk signature error");
  }

  private static native String getEncryptAPKSignatureNative(Object paramObject);

  private static String getPreferenceLocalDeviceId(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("device_id", 0);
    if (!localSharedPreferences.contains(com.tencent.android.tpush.encrypt.a.a("deviceId_v1")));
    String str2;
    do
    {
      String str1;
      do
      {
        return null;
        str1 = localSharedPreferences.getString(com.tencent.android.tpush.encrypt.a.a("deviceId_v1"), null);
      }
      while ((str1 == null) || (str1.trim().equals("")));
      str2 = Rijndael.decrypt(str1);
    }
    while (c.a(str2));
    return str2;
  }

  private static String getSettingsLocalDeviceId(Context paramContext)
  {
    String str1 = c.c(paramContext, ".com.tencent.tpush.cache.deviceId_v1");
    if (str1 == null);
    String str2;
    do
    {
      return null;
      str2 = Rijndael.decrypt(str1);
    }
    while (c.a(str2));
    return str2;
  }

  public static String oiSymmetryDecrypt2(String paramString)
  {
    "".getBytes();
    if (paramString != null);
    byte[] arrayOfByte2;
    try
    {
      if (paramString.length() <= 0)
      {
        TLog.e("XGService", ">> oiSymmetryDecrypt2 解密内容输入为空");
        return "";
      }
      byte[] arrayOfByte1 = a.a(paramString);
      if ((arrayOfByte1 == null) || (arrayOfByte1.length <= 0))
      {
        TLog.e("XGService", ">> oiSymmetryDecrypt2 解码失败，返回空字符串");
        return "failed";
      }
      arrayOfByte2 = oiSymmetryDecrypt2Byte(arrayOfByte1);
      if ((arrayOfByte2 == null) || (arrayOfByte2.length <= 0))
      {
        TLog.e("XGService", ">> oiSymmetryDecrypt2 解密失败，返回空字符串");
        return "failed";
      }
    }
    catch (Throwable localThrowable)
    {
      TLog.e("XGService", ">> oiSymmetryEncrypt2 未知错误", localThrowable);
      return "failed";
    }
    return new String(arrayOfByte2);
  }

  private static native byte[] oiSymmetryDecrypt2Byte(byte[] paramArrayOfByte);

  public static String oiSymmetryEncrypt2(String paramString)
  {
    if (paramString != null);
    String str;
    try
    {
      if (paramString.length() <= 0)
      {
        TLog.e("XGService", ">> oiSymmetryEncrypt2 加密内容输入为空");
        return "";
      }
      byte[] arrayOfByte = oiSymmetryEncrypt2Byte(paramString);
      if (arrayOfByte == null)
      {
        TLog.e("XGService", ">> oiSymmetryEncrypt2 加密失败，返回空字符串 inBuff:" + paramString);
        return "failed";
      }
      str = b.a(arrayOfByte);
      if (str == null)
      {
        TLog.e("XGService", ">> oiSymmetryEncrypt2 Base64编码失败，返回空字符串");
        return "failed";
      }
    }
    catch (Throwable localThrowable)
    {
      TLog.e("XGService", ">> oiSymmetryEncrypt2 未知错误", localThrowable);
      str = "failed";
    }
    return str;
  }

  private static native byte[] oiSymmetryEncrypt2Byte(String paramString);

  private static void setPreferenceLocalDeviceId(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("device_id", 0).edit();
    localEditor.putString(com.tencent.android.tpush.encrypt.a.a("deviceId_v1"), Rijndael.encrypt(paramString));
    localEditor.commit();
  }

  private static void setSettingsLocalDeviceId(Context paramContext, String paramString)
  {
    c.a(paramContext, ".com.tencent.tpush.cache.deviceId_v1", Rijndael.encrypt(paramString));
  }

  private static String toCharsString(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    char[] arrayOfChar = new char[i * 2];
    int j = 0;
    if (j < i)
    {
      int k = paramArrayOfByte[j];
      int m = 0xF & k >> 4;
      int n = j * 2;
      int i1;
      label52: int i2;
      int i3;
      if (m >= 10)
      {
        i1 = -10 + (m + 97);
        arrayOfChar[n] = (char)i1;
        i2 = k & 0xF;
        i3 = 1 + j * 2;
        if (i2 < 10)
          break label113;
      }
      label113: for (int i4 = -10 + (i2 + 97); ; i4 = i2 + 48)
      {
        arrayOfChar[i3] = (char)i4;
        j++;
        break;
        i1 = m + 48;
        break label52;
      }
    }
    return new String(arrayOfChar);
  }

  public void checkRemoteInc(long paramLong)
  {
    if (paramLong <= this.incRemote)
      throw new SecurityException("检查的inc小于等于当前记录的远端inc");
    this.incRemote = paramLong;
  }

  protected native byte[] decryptByAES(byte[] paramArrayOfByte, long paramLong);

  public byte[] decryptData(byte[] paramArrayOfByte)
  {
    try
    {
      byte[] arrayOfByte = decryptByAES(paramArrayOfByte, this.random);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return paramArrayOfByte;
  }

  protected native byte[] encryptByAES(byte[] paramArrayOfByte, long paramLong);

  protected native byte[] encryptByRSA(byte[] paramArrayOfByte);

  public byte[] encryptData(byte[] paramArrayOfByte)
  {
    try
    {
      byte[] arrayOfByte = encryptByAES(paramArrayOfByte, this.random);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return paramArrayOfByte;
  }

  public byte[] getEncKey()
  {
    return this.encKey;
  }

  public long getInc()
  {
    long l = 1L + this.inc;
    this.inc = l;
    return l;
  }

  public long getRandom()
  {
    return this.random;
  }

  public boolean needsUpdate()
  {
    return this.random == 0L;
  }

  public void reset()
  {
    this.random = 0L;
  }

  public void update()
  {
    for (this.random = 0L; this.random == 0L; this.random = ()(2147483647.0D * Math.random()));
    this.iv = generateIV(this.random);
    try
    {
      this.key = generateAESKey();
      this.encKey = encryptByRSA(this.key);
      return;
    }
    catch (Throwable localThrowable)
    {
      TLog.e("XGService", "update error:" + localThrowable.toString());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.security.TpnsSecurity
 * JD-Core Version:    0.6.0
 */
package com.tencent.qqgamemi.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.QMiApplication;
import com.tencent.qqgamemi.QMiService;
import com.tencent.qqgamemi.business.QMiEnvironmentHelper;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.DataModel;
import com.tencent.qqgamemi.plugin.QMiPluginManager;
import com.tencent.qqgamemi.protocol.MsgHandle;
import com.tencent.qqgamemi.protocol.QMiJceCommonData;
import com.tencent.qqgamemi.view.QMiToast;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressLint({"InlinedApi"})
public class QMiLoginManager
{
  private static byte[] A;
  private static byte[] B;
  private static long C = 0L;
  private static String D;
  private static String E;
  private static int F = 0;
  private static byte[] G;
  private static String H;
  private static String I;
  private static String J;
  public static final String a = "UIN";
  public static final String b = "SID";
  public static final String c = "MQQ_GAME_ST";
  public static final String d = "EncryptA8AuthData";
  public static final String e = "callback";
  public static final String f = "QIMEI";
  public static final String g = "sybId";
  public static final String h = "sybAccountName";
  public static final String i = "sybStType";
  public static final String j = "sybStData";
  public static final String k = "openId";
  public static final String l = "openToken";
  public static final String m = "com.tencent.gamejoy.ACTION_LOGIN_REQUEST";
  public static final String n = "com.tencent.qqgame.ACTION_LOGIN_REQUEST";
  public static final String o = "com.tencent.gamejoy.ACTION_LOGIN_QUERY";
  public static final String p = "com.tencent.qqgame.ACTION_LOGIN_QUERY";
  public static final String q = "com.tencent.gamejoy.ACTION_LOGIN_OUT";
  public static final String r = "com.tencent.qqgame.ACTION_LOGIN_OUT";
  private static final String s = "QMiLoginManager";
  private static QMiLoginManager t = null;
  private static final int u = 1;
  private static LoginCallBack y = null;
  private static String z;
  private boolean v = false;
  private QMiLoginManager.MsdkLoginBean w = null;
  private Handler x = new a(this, Looper.getMainLooper());

  static
  {
    C = 0L;
  }

  private QMiLoginManager()
  {
    s();
  }

  public static QMiLoginManager a()
  {
    if (t == null)
      t = new QMiLoginManager();
    return t;
  }

  public static void a(int paramInt)
  {
    if (y != null)
    {
      if (!a().m())
        break label28;
      y.onLoginSuccess();
    }
    while (true)
    {
      y = null;
      return;
      label28: y.onLoginCancel();
    }
  }

  public static void a(Context paramContext)
  {
    Intent localIntent;
    if (!QMiConfig.b())
    {
      TLog.c("SYBACCOUNT", "queryLoginInfo");
      localIntent = new Intent();
      if (QMiConfig.c() != 2)
        break label41;
      localIntent.setAction("com.tencent.qqgame.ACTION_LOGIN_QUERY");
    }
    while (true)
    {
      paramContext.sendBroadcast(localIntent);
      return;
      label41: localIntent.setAction("com.tencent.gamejoy.ACTION_LOGIN_QUERY");
    }
  }

  private static void a(Context paramContext, int paramInt)
  {
    QMiToast.a(paramContext, paramInt, 1000).show();
  }

  public static void a(Context paramContext, LoginCallBack paramLoginCallBack)
  {
    TLog.c("SYBACCOUNT", "login");
    if (QMiConfig.b())
    {
      y = paramLoginCallBack;
      QMiLoginManager localQMiLoginManager = a();
      if (!localQMiLoginManager.m())
      {
        if (a().v)
          a(paramContext, ResourceUtil.b("qmi_login_text_logining"));
      }
      else
        return;
      if (localQMiLoginManager.p().a())
      {
        a(paramContext, ResourceUtil.b("qmi_login_text_logining"));
        localQMiLoginManager.q();
        return;
      }
      a(paramContext, ResourceUtil.b("qmi_login_text_syb"));
      return;
    }
    y = paramLoginCallBack;
    Intent localIntent = new Intent();
    localIntent.setAction("com.tencent.gamejoy.ACTION_LOGIN_REQUEST");
    if (QMiConfig.c() == 2)
      localIntent.setAction("com.tencent.qqgame.ACTION_LOGIN_REQUEST");
    while (true)
    {
      localIntent.putExtra("COME_FROM_QMI", true);
      localIntent.putExtra("CALLBACK", 1);
      if (!(paramContext instanceof Activity))
        localIntent.setFlags(268435456);
      try
      {
        paramContext.startActivity(localIntent);
        return;
      }
      catch (Exception localException)
      {
        LogUtil.d("QMiLoginManager", "", localException);
        return;
      }
      localIntent.setAction("com.tencent.gamejoy.ACTION_LOGIN_REQUEST");
    }
  }

  private void a(String paramString1, String paramString2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString3, int paramInt, byte[] paramArrayOfByte3, String paramString4)
  {
    a(paramString1);
    a(Long.valueOf(Long.parseLong(paramString2)).longValue());
    LogUtil.d("QMiLoginManager", "setUin:" + paramString2);
    a(paramArrayOfByte1);
    b(paramArrayOfByte2);
    b(paramString3);
    b(paramInt);
    c(paramArrayOfByte3);
    d(paramString4);
    QMiJceCommonData.a(i());
  }

  public static void b()
  {
    if (QMiConfig.b());
  }

  public static void b(Context paramContext)
  {
    Intent localIntent;
    if (!QMiConfig.b())
    {
      TLog.c("SYBACCOUNT", "loginOut3");
      a().t();
      localIntent = new Intent();
      if (QMiConfig.c() != 2)
        break label48;
      localIntent.setAction("com.tencent.qqgame.ACTION_LOGIN_OUT");
    }
    while (true)
    {
      paramContext.sendBroadcast(localIntent);
      return;
      label48: localIntent.setAction("com.tencent.gamejoy.ACTION_LOGIN_OUT");
    }
  }

  private void s()
  {
    SharedPreferences localSharedPreferences = u();
    b(localSharedPreferences.getString("sybAccountName", null));
    b((short)localSharedPreferences.getInt("sybStType", 0));
    String str = localSharedPreferences.getString("sybStData", null);
    if (str != null)
      c(f(str));
    d(localSharedPreferences.getString("openToken", null));
    c(localSharedPreferences.getString("openToken", null));
  }

  private void t()
  {
    SharedPreferences.Editor localEditor = u().edit();
    localEditor.clear();
    localEditor.commit();
  }

  private SharedPreferences u()
  {
    return QMiApplication.a().getSharedPreferences("SYBAccountInfo2", 0);
  }

  public void a(long paramLong)
  {
    C = paramLong;
  }

  public void a(Context paramContext, boolean paramBoolean)
  {
    QMiPluginManager.a().d();
    QMiPluginManager.a().g();
    this.v = false;
    z = null;
    C = 0L;
    A = null;
    B = null;
    E = null;
    D = null;
    F = -1;
    G = null;
    I = null;
    J = null;
    if (paramBoolean)
      b(paramContext);
  }

  public void a(Intent paramIntent)
  {
    if (paramIntent == null)
      return;
    String str1 = paramIntent.getExtras().getString("SID");
    String str2 = paramIntent.getExtras().getString("UIN");
    a(str1, str2, paramIntent.getExtras().getByteArray("MQQ_GAME_ST"), paramIntent.getExtras().getByteArray("EncryptA8AuthData"), paramIntent.getExtras().getString("sybAccountName"), paramIntent.getExtras().getShort("sybStType"), paramIntent.getExtras().getByteArray("sybStData"), paramIntent.getExtras().getString("openId"));
    a(paramIntent.getExtras().getInt("callback"));
    if (m())
    {
      LogUtil.d("SYBACCOUNT", "is logined then requestUserInfo:" + str2);
      Service localService = QMiService.a();
      if (localService != null)
        DataModel.a(localService).a(Long.parseLong(str2), null);
      a().o();
    }
    QMiPluginManager.a().g();
  }

  public void a(String paramString)
  {
    z = paramString;
  }

  public void a(byte[] paramArrayOfByte)
  {
    TLog.c("QMiLoginManager", "setMQQGameSt");
    A = paramArrayOfByte;
  }

  public boolean a(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    if ((paramString3 == null) || (TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString1)))
      return false;
    int i1;
    if (paramInt == 1)
      i1 = 1;
    while (true)
    {
      MsgHandle.a(this.x, 1, paramString1, paramString2, paramString3.getBytes(), i1);
      this.v = true;
      return true;
      i1 = 0;
      if (paramInt != 2)
        continue;
      i1 = 0;
    }
  }

  public byte[] a(int paramInt1, int paramInt2)
  {
    return B;
  }

  public void b(int paramInt)
  {
    F = paramInt;
  }

  public void b(String paramString)
  {
    D = paramString;
  }

  public void b(byte[] paramArrayOfByte)
  {
    TLog.c("QMiLoginManager", "setA8AuthData");
    B = paramArrayOfByte;
  }

  public String c()
  {
    return D;
  }

  public void c(String paramString)
  {
    H = paramString;
  }

  public void c(byte[] paramArrayOfByte)
  {
    G = paramArrayOfByte;
  }

  public int d()
  {
    return F;
  }

  protected String d(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    int i1 = paramArrayOfByte.length;
    for (int i2 = 0; i2 < i1; i2++)
    {
      int i3 = 0xFF & paramArrayOfByte[i2];
      if (i3 < 16)
        localStringBuilder.append('0');
      localStringBuilder.append(Integer.toHexString(i3));
    }
    String str = localStringBuilder.toString().toUpperCase();
    TLog.c("SYBACCOUNT", "byteArrayToHexString str=" + str + ", len=" + str.length());
    return str;
  }

  public void d(String paramString)
  {
    I = paramString;
  }

  public void e(String paramString)
  {
    J = paramString;
  }

  public byte[] e()
  {
    return G;
  }

  public String f()
  {
    return H;
  }

  protected byte[] f(String paramString)
  {
    if (paramString == null)
      return null;
    int i1 = paramString.length();
    byte[] arrayOfByte = new byte[i1 / 2];
    int i2 = 0;
    while (true)
      if (i2 < i1)
        try
        {
          arrayOfByte[(i2 / 2)] = (byte)((Character.digit(paramString.charAt(i2), 16) << 4) + Character.digit(paramString.charAt(i2 + 1), 16));
          i2 += 2;
        }
        catch (Exception localException)
        {
        }
    TLog.c("SYBACCOUNT", "hexStringToByteArray byte[] len=" + arrayOfByte.length);
    return arrayOfByte;
  }

  public String g()
  {
    return J;
  }

  public String h()
  {
    return I;
  }

  public long i()
  {
    return C;
  }

  public String j()
  {
    return z;
  }

  public byte[] k()
  {
    return A;
  }

  public String l()
  {
    return String.valueOf(QMiEnvironmentHelper.a);
  }

  public boolean m()
  {
    return (G != null) && (G.length != 0) && (C != 0L);
  }

  public String n()
  {
    return E;
  }

  public void o()
  {
    if (m())
    {
      SharedPreferences.Editor localEditor = u().edit();
      localEditor.putString("sybAccountName", c());
      localEditor.putInt("sybStType", d());
      localEditor.putString("sybStData", d(e()));
      localEditor.putString("openId", h());
      localEditor.putString("openToken", f());
      localEditor.commit();
    }
  }

  public QMiLoginManager.MsdkLoginBean p()
  {
    this.w = new QMiLoginManager.MsdkLoginBean(this);
    try
    {
      Object localObject = Class.forName("com.tencent.msdk.qmi.MsdkApiForQmi").getMethod("getLoginInfo", new Class[0]).invoke(null, new Object[0]);
      Class localClass = Class.forName("com.tencent.msdk.qmi.LoginInfo");
      String str1 = (String)localClass.getField("appId").get(localObject);
      this.w.b = str1;
      int i1 = ((Integer)localClass.getField("platform").get(localObject)).intValue();
      this.w.a = i1;
      String str2 = (String)localClass.getField("openId").get(localObject);
      this.w.c = str2;
      String str3 = (String)localClass.getField("accessToken").get(localObject);
      this.w.d = str3;
      TLog.c("SYBACCOUNT", "reqMsdkLoginInfo:" + this.w);
      return this.w;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void q()
  {
    a().a(QMiService.a(), false);
    if ((this.w != null) && (this.w.a != 0))
    {
      J = this.w.b;
      a().a(this.w.b, this.w.c, this.w.d, this.w.a);
    }
  }

  public QMiLoginManager.MsdkLoginBean r()
  {
    return this.w;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.login.QMiLoginManager
 * JD-Core Version:    0.6.0
 */
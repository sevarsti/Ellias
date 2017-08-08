package com.tencent.android.tpush;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import java.lang.reflect.Method;
import java.util.Properties;

public class XGPro
{
  public static final String TPUSH_ENABLE_PRO = "XG_V2_ENABLE_PRO";
  private static XGPro a = null;
  private Context b = null;
  private Class c = null;
  private Class d = null;
  private Class e = null;
  private Class f = null;
  private Object g = null;
  private Method h = null;
  private Method i = null;
  private Method j = null;
  private int k = -1;
  private boolean l = false;

  private XGPro(Context paramContext)
  {
    this.b = paramContext.getApplicationContext();
  }

  static XGPro a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new XGPro(paramContext);
      XGPro localXGPro = a;
      return localXGPro;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void b()
  {
    int m = 1;
    monitorenter;
    try
    {
      if (!this.l)
      {
        ClassLoader localClassLoader = this.b.getClassLoader();
        this.c = localClassLoader.loadClass("com.tencent.stat.StatConfig");
        this.d = localClassLoader.loadClass("com.tencent.stat.StatServiceImpl");
        this.e = localClassLoader.loadClass("com.tencent.stat.common.StatConstants");
        this.e.getDeclaredField("XG_PRO_VERSION");
        this.f = localClassLoader.loadClass("com.tencent.stat.StatSpecifyReportedInfo");
        this.g = this.f.newInstance();
        Method localMethod1 = this.f.getMethod("setAppKey", new Class[] { String.class });
        Object localObject2 = this.g;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = ("Axg" + XGPushConfig.getAccessId(this.b));
        localMethod1.invoke(localObject2, arrayOfObject1);
        Class localClass1 = this.f;
        Class[] arrayOfClass1 = new Class[1];
        arrayOfClass1[0] = Boolean.TYPE;
        Method localMethod2 = localClass1.getMethod("setSendImmediately", arrayOfClass1);
        Object localObject3 = this.g;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Boolean.valueOf(true);
        localMethod2.invoke(localObject3, arrayOfObject2);
        Class localClass2 = this.c;
        Class[] arrayOfClass2 = new Class[1];
        arrayOfClass2[0] = Boolean.TYPE;
        Method localMethod3 = localClass2.getMethod("setXGProMode", arrayOfClass2);
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = Boolean.valueOf(true);
        localMethod3.invoke(null, arrayOfObject3);
        Class localClass3 = this.d;
        Class[] arrayOfClass3 = new Class[4];
        arrayOfClass3[0] = Context.class;
        arrayOfClass3[1] = String.class;
        arrayOfClass3[2] = Properties.class;
        arrayOfClass3[3] = this.f;
        this.h = localClass3.getMethod("trackCustomKVEvent", arrayOfClass3);
        Class localClass4 = this.d;
        Class[] arrayOfClass4 = new Class[4];
        arrayOfClass4[0] = Context.class;
        arrayOfClass4[1] = String.class;
        arrayOfClass4[2] = Properties.class;
        arrayOfClass4[3] = this.f;
        this.i = localClass4.getMethod("trackCustomBeginKVEvent", arrayOfClass4);
        Class localClass5 = this.d;
        Class[] arrayOfClass5 = new Class[4];
        arrayOfClass5[0] = Context.class;
        arrayOfClass5[1] = String.class;
        arrayOfClass5[2] = Properties.class;
        arrayOfClass5[3] = this.f;
        this.j = localClass5.getMethod("trackCustomEndKVEvent", arrayOfClass5);
        if (c.b(this.b, "com.tencent.android.tpush.debug," + this.b.getPackageName(), 0) != m)
          break label522;
      }
      while (true)
      {
        Class localClass6 = this.c;
        Class[] arrayOfClass6 = new Class[1];
        arrayOfClass6[0] = Boolean.TYPE;
        Method localMethod4 = localClass6.getMethod("setXGProMode", arrayOfClass6);
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = Boolean.valueOf(m);
        localMethod4.invoke(null, arrayOfObject4);
        this.l = true;
        TLog.i("XGPro", "init mta success.");
        return;
        label522: int n = 0;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public static void enableXGPro(Context paramContext, boolean paramBoolean)
  {
    if (paramContext == null)
      throw new Exception("context == null for enableXGPro().");
    a(paramContext).a(paramBoolean);
  }

  public static boolean isEnableXGPro(Context paramContext)
  {
    return a(paramContext).a();
  }

  void a(String paramString, Properties paramProperties)
  {
    if (a());
    try
    {
      b();
      if (this.h != null)
      {
        TLog.i("XGPro", "call MTA trackCustomKVEvent,event_id:" + paramString + ",property:" + paramProperties);
        Method localMethod = this.h;
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = this.b;
        arrayOfObject[1] = paramString;
        arrayOfObject[2] = paramProperties;
        arrayOfObject[3] = this.g;
        localMethod.invoke(null, arrayOfObject);
      }
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGPro", "call trackCustomKVMethod failed", localException);
    }
  }

  void a(boolean paramBoolean)
  {
    int m;
    if (paramBoolean)
      m = 1;
    while (true)
    {
      this.k = m;
      Throwable localThrowable = null;
      if (paramBoolean);
      try
      {
        b();
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.b);
        if (localSharedPreferences != null)
        {
          SharedPreferences.Editor localEditor = localSharedPreferences.edit();
          localEditor.putInt("XG_V2_ENABLE_PRO", this.k);
          localEditor.commit();
        }
        if (localThrowable != null)
        {
          throw new Exception("start XGPro failed, please check MTA or MID SDK version.", localThrowable);
          m = 0;
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          TLog.e("TPush", "start XGPro failed, please check MTA or MID SDK version.", localException);
          this.k = 0;
        }
      }
    }
  }

  boolean a()
  {
    if (this.k == -1)
      this.k = PreferenceManager.getDefaultSharedPreferences(this.b).getInt("XG_V2_ENABLE_PRO", -1);
    return this.k == 1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGPro
 * JD-Core Version:    0.6.0
 */
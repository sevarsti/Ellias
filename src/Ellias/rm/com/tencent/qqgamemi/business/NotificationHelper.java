package com.tencent.qqgamemi.business;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat.Builder;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.QMiService;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NotificationHelper
{
  private static final String a = "NotificationHelper";
  private static final Class[] i;
  private static final Class[] j;
  private static final Class[] k;
  private Service b;
  private boolean c = false;
  private NotificationCompat.Builder d;
  private Method e;
  private Method f;
  private Method g;
  private NotificationManager h;
  private Object[] l = new Object[1];
  private Object[] m = new Object[2];
  private Object[] n = new Object[1];

  static
  {
    Class[] arrayOfClass1 = new Class[2];
    arrayOfClass1[0] = Integer.TYPE;
    arrayOfClass1[1] = Notification.class;
    i = arrayOfClass1;
    Class[] arrayOfClass2 = new Class[1];
    arrayOfClass2[0] = Boolean.TYPE;
    j = arrayOfClass2;
    Class[] arrayOfClass3 = new Class[1];
    arrayOfClass3[0] = Boolean.TYPE;
    k = arrayOfClass3;
  }

  public NotificationHelper(Service paramService)
  {
    this.b = paramService;
    this.h = ((NotificationManager)paramService.getSystemService("notification"));
    this.d = new NotificationCompat.Builder(paramService);
    this.d.setSmallIcon(ResourceUtil.c("qmi_stand1"));
    String str1 = paramService.getResources().getString(ResourceUtil.b("qmi_foreground_notify_title"));
    String str2 = paramService.getResources().getString(ResourceUtil.b("qmi_foreground_notify_content"));
    this.d.setContentTitle(str1);
    this.d.setContentText(str2);
    this.d.setContentIntent(PendingIntent.getService(paramService, 0, new Intent(paramService, QMiService.class), 0));
    this.d.setTicker(str1);
    this.d.setAutoCancel(true);
    try
    {
      this.e = paramService.getClass().getMethod("startForeground", i);
      this.f = paramService.getClass().getMethod("stopForeground", j);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException1)
    {
      TLog.d("NotificationHelper", "no suchmethod exception", localNoSuchMethodException1.fillInStackTrace());
      this.f = null;
      this.e = null;
      try
      {
        this.g = paramService.getClass().getMethod("setForeground", k);
        return;
      }
      catch (NoSuchMethodException localNoSuchMethodException2)
      {
        TLog.d("NotificationHelper", "no suchmethod exception2", localNoSuchMethodException2.fillInStackTrace());
        this.g = null;
      }
    }
  }

  private void a(int paramInt)
  {
    if (this.f != null)
    {
      this.n[0] = Boolean.TRUE;
      a(this.f, this.n);
    }
    do
      return;
    while (this.g == null);
    this.h.cancel(paramInt);
    this.l[0] = Boolean.FALSE;
    a(this.g, this.l);
  }

  private void a(int paramInt, Notification paramNotification)
  {
    if (this.e != null)
    {
      this.m[0] = Integer.valueOf(paramInt);
      this.m[1] = paramNotification;
      a(this.e, this.m);
    }
    do
      return;
    while (this.g == null);
    this.l[0] = Boolean.TRUE;
    a(this.g, this.l);
    this.h.notify(paramInt, paramNotification);
  }

  private void a(Method paramMethod, Object[] paramArrayOfObject)
  {
    try
    {
      paramMethod.invoke(this.b, paramArrayOfObject);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      TLog.d("NotificationHelper", "Unable to invoke method", localInvocationTargetException);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      TLog.d("NotificationHelper", "Unable to invoke method", localIllegalAccessException);
    }
  }

  public void a()
  {
    if ((!QMiConfig.b()) && (!this.c))
    {
      TLog.c("NotificationHelper", "setForeground");
      this.c = true;
      a(ResourceUtil.b("qmi_foreground_notify_content"), this.d.build());
    }
  }

  public void b()
  {
    if ((!QMiConfig.b()) && (this.c))
    {
      TLog.c("NotificationHelper", "setBackground");
      this.c = false;
      a(ResourceUtil.b("qmi_foreground_notify_content"));
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.NotificationHelper
 * JD-Core Version:    0.6.0
 */
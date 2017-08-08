package com.tencent.android.tpush;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.RemoteViews;
import com.tencent.android.tpush.common.a;
import org.json.JSONObject;

public abstract class XGPushNotificationBuilder
{
  public static final String BASIC_NOTIFICATION_BUILDER_TYPE = "basic";
  public static final String CUSTOM_NOTIFICATION_BUILDER_TYPE = "custom";
  protected Integer a = null;
  protected PendingIntent b = null;
  protected RemoteViews c = null;
  protected Integer d = null;
  protected PendingIntent e = null;
  protected Integer f = null;
  protected Integer g = null;
  protected Integer h = null;
  protected Integer i = null;
  protected Integer j = null;
  protected Integer k = null;
  protected Integer l = null;
  protected Uri m = null;
  protected CharSequence n = null;
  protected long[] o = null;
  protected Long p = null;
  protected String q;
  protected Integer r = null;

  protected Notification a(Context paramContext)
  {
    Notification localNotification = new Notification();
    if (this.r == null)
      this.r = Integer.valueOf(0);
    if (this.q == null)
      this.q = getTitle(paramContext);
    if (this.a != null)
      localNotification.audioStreamType = this.a.intValue();
    if (this.b != null)
      localNotification.contentIntent = this.b;
    if (this.c != null)
      localNotification.contentView = this.c;
    if (this.d != null)
      localNotification.defaults = this.d.intValue();
    if (this.e != null)
      localNotification.deleteIntent = this.e;
    if (this.f != null);
    for (localNotification.flags = this.f.intValue(); ; localNotification.flags = 16)
    {
      if (this.g != null)
        localNotification.icon = this.g.intValue();
      if (this.h != null)
        localNotification.iconLevel = this.h.intValue();
      if (this.i != null)
        localNotification.ledARGB = this.i.intValue();
      if (this.j != null)
        localNotification.ledOffMS = this.j.intValue();
      if (this.k != null)
        localNotification.ledOnMS = this.k.intValue();
      if (this.l != null)
        localNotification.number = this.l.intValue();
      if (this.m != null)
        localNotification.sound = this.m;
      if (this.n != null)
        localNotification.tickerText = this.n;
      if (this.o != null)
        localNotification.vibrate = this.o;
      if (this.p == null)
        break;
      localNotification.when = this.p.longValue();
      return localNotification;
    }
    localNotification.when = System.currentTimeMillis();
    return localNotification;
  }

  protected abstract void a(JSONObject paramJSONObject);

  protected abstract void b(JSONObject paramJSONObject);

  public abstract Notification buildNotification(Context paramContext);

  public void decode(String paramString)
  {
    JSONObject localJSONObject = new JSONObject(paramString);
    b(localJSONObject);
    this.a = ((Integer)a.b(localJSONObject, "audioStringType", null));
    this.d = ((Integer)a.b(localJSONObject, "defaults", null));
    this.f = ((Integer)a.b(localJSONObject, "flags", null));
    this.g = ((Integer)a.b(localJSONObject, "icon", null));
    this.h = ((Integer)a.b(localJSONObject, "iconLevel", null));
    this.i = ((Integer)a.b(localJSONObject, "ledARGB", null));
    this.j = ((Integer)a.b(localJSONObject, "ledOffMS", null));
    this.k = ((Integer)a.b(localJSONObject, "ledOnMS", null));
    this.l = ((Integer)a.b(localJSONObject, "number", null));
    String str1 = (String)a.b(localJSONObject, "sound", null);
    if (str1 != null)
      this.m = Uri.parse(str1);
    String str2 = (String)a.b(localJSONObject, "vibrate", null);
    if (str2 != null)
    {
      String[] arrayOfString = str2.split(",");
      int i1 = arrayOfString.length;
      this.o = new long[i1];
      for (int i2 = 0; i2 < i1; i2++)
        this.o[i2] = Long.valueOf(arrayOfString[i2]).longValue();
    }
    this.r = ((Integer)a.b(localJSONObject, "notificationId", null));
  }

  public void encode(JSONObject paramJSONObject)
  {
    a(paramJSONObject);
    a.a(paramJSONObject, "audioStringType", this.a);
    a.a(paramJSONObject, "defaults", this.d);
    a.a(paramJSONObject, "flags", this.f);
    a.a(paramJSONObject, "icon", this.g);
    a.a(paramJSONObject, "iconLevel", this.h);
    a.a(paramJSONObject, "ledARGB", this.i);
    a.a(paramJSONObject, "ledOffMS", this.j);
    a.a(paramJSONObject, "ledOnMS", this.k);
    a.a(paramJSONObject, "number", this.l);
    a.a(paramJSONObject, "sound", this.m);
    if (this.o != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (int i1 = 0; i1 < this.o.length; i1++)
      {
        localStringBuilder.append(String.valueOf(this.o[i1]));
        if (i1 == -1 + this.o.length)
          continue;
        localStringBuilder.append(",");
      }
      a.a(paramJSONObject, "vibrate", localStringBuilder.toString());
    }
    a.a(paramJSONObject, "notificationId", this.r);
  }

  public int getApplicationIcon(Context paramContext)
  {
    return paramContext.getApplicationInfo().icon;
  }

  public PendingIntent getContentIntent()
  {
    return this.b;
  }

  public int getDefaults()
  {
    return this.d.intValue();
  }

  public int getFlags()
  {
    return this.f.intValue();
  }

  public int getIcon()
  {
    return this.g.intValue();
  }

  public Uri getSound()
  {
    return this.m;
  }

  public CharSequence getTickerText()
  {
    return this.n;
  }

  public String getTitle(Context paramContext)
  {
    if (this.q == null)
    {
      ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
      this.q = ((String)paramContext.getApplicationContext().getPackageManager().getApplicationLabel(localApplicationInfo));
    }
    return this.q;
  }

  public abstract String getType();

  public long[] getVibrate()
  {
    return this.o;
  }

  public long getWhen()
  {
    return this.p.longValue();
  }

  public XGPushNotificationBuilder setContentIntent(PendingIntent paramPendingIntent)
  {
    this.b = paramPendingIntent;
    return this;
  }

  public XGPushNotificationBuilder setDefaults(int paramInt)
  {
    this.d = Integer.valueOf(paramInt);
    return this;
  }

  public XGPushNotificationBuilder setFlags(int paramInt)
  {
    this.f = Integer.valueOf(paramInt);
    return this;
  }

  public XGPushNotificationBuilder setIcon(int paramInt)
  {
    this.g = Integer.valueOf(paramInt);
    return this;
  }

  public XGPushNotificationBuilder setSound(Uri paramUri)
  {
    this.m = paramUri;
    return this;
  }

  public XGPushNotificationBuilder setTickerText(CharSequence paramCharSequence)
  {
    this.n = paramCharSequence;
    return this;
  }

  public void setTitle(String paramString)
  {
    this.q = paramString;
  }

  public XGPushNotificationBuilder setVibrate(long[] paramArrayOfLong)
  {
    this.o = paramArrayOfLong;
    return this;
  }

  public XGPushNotificationBuilder setWhen(long paramLong)
  {
    this.p = Long.valueOf(paramLong);
    return this;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGPushNotificationBuilder
 * JD-Core Version:    0.6.0
 */
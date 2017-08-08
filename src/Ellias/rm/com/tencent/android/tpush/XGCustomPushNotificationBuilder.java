package com.tencent.android.tpush;

import android.app.Notification;
import android.content.Context;
import android.widget.RemoteViews;
import com.tencent.android.tpush.common.a;
import org.json.JSONObject;

public class XGCustomPushNotificationBuilder extends XGPushNotificationBuilder
{
  private Integer s = null;
  private Integer t = null;
  private Integer u = null;
  private Integer v = null;
  private Integer w = null;
  private Integer x = null;

  protected void a(JSONObject paramJSONObject)
  {
    a.a(paramJSONObject, "layoutId", this.s);
    a.a(paramJSONObject, "layoutIconId", this.t);
    a.a(paramJSONObject, "layoutTitleId", this.u);
    a.a(paramJSONObject, "layoutTextId", this.v);
    a.a(paramJSONObject, "layoutIconDrawableId", this.w);
    a.a(paramJSONObject, "statusBarIconDrawableId", this.x);
  }

  protected void b(JSONObject paramJSONObject)
  {
    this.s = ((Integer)a.b(paramJSONObject, "layoutId", null));
    this.t = ((Integer)a.b(paramJSONObject, "layoutIconId", null));
    this.u = ((Integer)a.b(paramJSONObject, "layoutTitleId", null));
    this.v = ((Integer)a.b(paramJSONObject, "layoutTextId", null));
    this.w = ((Integer)a.b(paramJSONObject, "layoutIconDrawableId", null));
    this.x = ((Integer)a.b(paramJSONObject, "statusBarIconDrawableId", null));
  }

  public Notification buildNotification(Context paramContext)
  {
    if (this.s != null)
    {
      RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), this.s.intValue());
      if (this.u != null)
        localRemoteViews.setTextViewText(this.u.intValue(), getTitle(paramContext));
      if (this.v != null)
        localRemoteViews.setTextViewText(this.v.intValue(), this.n);
      if (this.t != null)
        localRemoteViews.setImageViewResource(this.t.intValue(), this.w.intValue());
      if (this.x != null)
        localRemoteViews.setTextViewText(this.x.intValue(), getTitle(paramContext));
      this.c = localRemoteViews;
      return a(paramContext);
    }
    Notification localNotification = a(paramContext);
    localNotification.setLatestEventInfo(paramContext, getTitle(paramContext), getTickerText(), getContentIntent());
    return localNotification;
  }

  public int getLayoutIconDrawableId()
  {
    return this.w.intValue();
  }

  public int getLayoutIconId()
  {
    return this.t.intValue();
  }

  public int getLayoutId()
  {
    return this.s.intValue();
  }

  public int getLayoutTextId()
  {
    return this.v.intValue();
  }

  public int getLayoutTitleId()
  {
    return this.u.intValue();
  }

  public String getType()
  {
    return "custom";
  }

  public XGCustomPushNotificationBuilder setLayoutIconDrawableId(int paramInt)
  {
    this.w = Integer.valueOf(paramInt);
    return this;
  }

  public XGCustomPushNotificationBuilder setLayoutIconId(int paramInt)
  {
    this.t = Integer.valueOf(paramInt);
    return this;
  }

  public XGCustomPushNotificationBuilder setLayoutId(int paramInt)
  {
    this.s = Integer.valueOf(paramInt);
    return this;
  }

  public XGCustomPushNotificationBuilder setLayoutTextId(int paramInt)
  {
    this.v = Integer.valueOf(paramInt);
    return this;
  }

  public XGCustomPushNotificationBuilder setLayoutTitleId(int paramInt)
  {
    this.u = Integer.valueOf(paramInt);
    return this;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGCustomPushNotificationBuilder
 * JD-Core Version:    0.6.0
 */
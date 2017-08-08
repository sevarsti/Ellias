package com.tencent.android.tpush;

import android.app.Notification;
import android.content.Context;
import org.json.JSONObject;

public class XGBasicPushNotificationBuilder extends XGPushNotificationBuilder
{
  protected void a(JSONObject paramJSONObject)
  {
  }

  protected void b(JSONObject paramJSONObject)
  {
  }

  public Notification buildNotification(Context paramContext)
  {
    Notification localNotification = a(paramContext);
    localNotification.setLatestEventInfo(paramContext, getTitle(paramContext), getTickerText(), getContentIntent());
    return localNotification;
  }

  public String getType()
  {
    return "basic";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGBasicPushNotificationBuilder
 * JD-Core Version:    0.6.0
 */
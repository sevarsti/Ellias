package com.tencent.msdk.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat.Builder;
import com.tencent.msdk.push.req.UnRegisterAppReq;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.Res;
import com.tencent.msdk.tools.T;

public class PushMsgEmitter
{
  private static final int sActLaunchApp = 1;

  public void emit(Context paramContext, MsgEntry paramMsgEntry)
  {
    switch (paramMsgEntry.getClickAction())
    {
    default:
      return;
    case 1:
    }
    showLaunchAppNtf(paramContext, paramMsgEntry);
  }

  public void showLaunchAppNtf(Context paramContext, MsgEntry paramMsgEntry)
  {
    if (paramContext == null)
      Logger.d("Ctx is null");
    NotificationManager localNotificationManager;
    String str;
    do
    {
      return;
      localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
      str = paramMsgEntry.getPkgName();
    }
    while (T.ckIsEmpty(str));
    try
    {
      Drawable localDrawable = paramContext.getPackageManager().getApplicationIcon(str);
      Intent localIntent = new Intent();
      localIntent.setClassName(str, str + ".push." + "ForwardActivity");
      localIntent.setFlags(335544320);
      localIntent.putExtra("PUSH_MSG", paramMsgEntry);
      PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, (int)(System.currentTimeMillis() + (int)(100.0D * Math.random())), localIntent, 134217728);
      Bitmap localBitmap = ((BitmapDrawable)localDrawable).getBitmap();
      Notification localNotification = new NotificationCompat.Builder(paramContext).setAutoCancel(true).setSmallIcon(new Res(paramContext).drawable("com_tencent_msdk_push_icon")).setLargeIcon(localBitmap).setContentIntent(localPendingIntent).setContentTitle(paramMsgEntry.getTitle()).setContentText(paramMsgEntry.getContent()).setTicker(paramMsgEntry.getTitle()).build();
      localNotificationManager.notify(paramMsgEntry.getId(), localNotification);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
      new UnRegisterAppReq().send();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.PushMsgEmitter
 * JD-Core Version:    0.6.0
 */
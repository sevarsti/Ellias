package com.tencent.msdk.push;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.push.db.PushClientDbModel;
import com.tencent.msdk.push.req.PullMsgReq;
import com.tencent.msdk.push.req.PullMsgReq.Callback;
import com.tencent.msdk.push.req.PullProxyReq;
import com.tencent.msdk.push.req.PullProxyReq.Callback;
import com.tencent.msdk.push.req.PushStateReq;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import org.json.JSONArray;
import org.json.JSONException;

public class AlarmReveiver extends BroadcastReceiver
{
  public static Context sCtx;

  private void resetPollingAlarm(Context paramContext, String paramString)
  {
    if ("false".equals(ConfigManager.getIsServerIntervalAccepted()));
    while (true)
    {
      return;
      AlarmManager localAlarmManager = (AlarmManager)sCtx.getSystemService("alarm");
      Intent localIntent = new Intent(paramContext, AlarmReveiver.class);
      long l = SystemClock.elapsedRealtime();
      int i = 600000;
      if ((!T.ckIsEmpty(paramString)) && (paramString.split(",").length > 1));
      try
      {
        int j = Integer.parseInt(paramString.split(",")[1].trim());
        i = 1000 * (j * 60);
        if ((HttpPushService.sPendingIntent == null) || (i == HttpPushService.sLastPollingInterval))
          continue;
        Logger.d("cancel default pendingIntent");
        Logger.d("reset AlarmManager: " + i);
        localAlarmManager.cancel(HttpPushService.sPendingIntent);
        HttpPushService.sPendingIntent = PendingIntent.getBroadcast(HttpPushService.sHttpPushServiceContext, 8888, localIntent, 134217728);
        HttpPushService.sLastPollingInterval = i;
        localAlarmManager.setRepeating(3, l + i, i, HttpPushService.sPendingIntent);
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        while (true)
          Logger.d("NumberFormatException ");
      }
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    sCtx = paramContext;
    Logger.d("onReceive");
    PullProxyReq localPullProxyReq = new PullProxyReq();
    localPullProxyReq.setmCallback(new PullProxyReq.Callback(paramContext)
    {
      public void onFail(String paramString)
      {
        Logger.d("poll push msg failed!");
      }

      public void onMsgReady(String paramString)
      {
        Logger.d("PullProxyCallback onSuccess");
        new PullMsgReq(new AlarmReveiver.PullMsgReqCallback()).send();
      }

      public void onNoMsg(String paramString)
      {
        Logger.d("no push msg@now");
        AlarmReveiver.this.resetPollingAlarm(this.val$ctx, paramString);
      }
    });
    localPullProxyReq.send();
  }

  public static class PullMsgReqCallback
    implements PullMsgReq.Callback
  {
    public void onFail()
    {
      Logger.d("onFail");
    }

    public void onSuccess(JSONArray paramJSONArray)
    {
      if ((paramJSONArray == null) || (paramJSONArray.length() == 0))
      {
        Logger.d("pull msg success, but msgList empty!");
        return;
      }
      while (true)
      {
        int j;
        try
        {
          PushMsgEmitter localPushMsgEmitter = new PushMsgEmitter();
          int i = 0;
          j = 0;
          if (j >= paramJSONArray.length())
            continue;
          MsgEntry localMsgEntry = new MsgEntry(paramJSONArray.getJSONObject(j));
          localPushMsgEmitter.emit(AlarmReveiver.sCtx, localMsgEntry);
          int k = localMsgEntry.getId();
          if (i < k)
          {
            i = k;
            break label155;
            PushClientDbModel localPushClientDbModel = new PushClientDbModel();
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("last_msg_id", "" + i);
            localPushClientDbModel.update(localContentValues, null, null);
            new PushStateReq().send();
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return;
        }
        label155: j++;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.AlarmReveiver
 * JD-Core Version:    0.6.0
 */
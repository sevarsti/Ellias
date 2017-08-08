package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.tencent.android.tpush.a.f;
import com.tencent.android.tpush.logging.TLog;

public class XGPushReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramContext == null) || (paramIntent == null));
    String str;
    do
    {
      return;
      str = paramIntent.getAction();
    }
    while (str == null);
    com.tencent.android.tpush.service.i.a(paramContext.getApplicationContext());
    TLog.init(paramContext.getApplicationContext());
    TLog.w("XGService", ">>> PushReceiver received " + str + " @@" + paramContext.getPackageName());
    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(str))
    {
      com.tencent.android.tpush.horse.i.a().b();
      return;
    }
    if ("com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE".equals(str))
    {
      TLog.e("XGService", "@@ handle an N/M : " + paramContext.getPackageName());
      f.a(paramContext).a(paramIntent);
      return;
    }
    if ("com.tencent.android.tpush.action.SDK".equals(str))
    {
      Log.e("XGService", "@@ has Receiver : " + paramContext.getPackageName());
      paramIntent.setAction("com.tencent.android.tpush.action.BROADCAST_ACK");
      paramIntent.putExtra("packName", paramContext.getPackageName());
      paramContext.sendBroadcast(paramIntent);
    }
    com.tencent.android.tpush.service.i.a(paramContext, paramIntent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGPushReceiver
 * JD-Core Version:    0.6.0
 */
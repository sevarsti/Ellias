package com.tencent.android.tpush.service;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg;
import java.util.ArrayList;

class g
  implements Runnable
{
  private Context b = null;
  private Intent c = null;

  public g(a parama, Context paramContext, Intent paramIntent)
  {
    this.b = paramContext;
    this.c = paramIntent;
  }

  public void run()
  {
    TLog.v("XGService", "@@ run()");
    String str = this.c.getAction();
    if (str == null);
    do
    {
      return;
      if (("android.intent.action.PACKAGE_ADDED".equals(str)) || ("android.intent.action.PACKAGE_REPLACED".equals(str)))
        a.a(this.a, this.b, this.c);
      while ("android.intent.action.PACKAGE_REMOVED".equals(str))
      {
        a.b(this.a, this.b, this.c);
        return;
        if (!"android.intent.action.PACKAGE_REMOVED".equals(str))
          continue;
        a.b(this.a, this.b, this.c);
      }
      if ("com.tencent.android.tpush.action.REGISTER".equals(str))
      {
        a.c(this.a, this.b, this.c);
        return;
      }
      if ("com.tencent.android.tpush.action.UNREGISTER".equals(str))
      {
        a.d(this.a, this.b, this.c);
        return;
      }
      if ("com.tencent.android.tpush.action.ENABLE_DEBUG".equals(str))
      {
        a.e(this.a, this.b, this.c);
        return;
      }
      if ("com.tencent.android.tpush.action.MSG_ACK".equals(str))
      {
        com.tencent.android.tpush.service.b.a.a().a(this.b, this.c);
        return;
      }
      if ("com.tencent.android.tpush.action.TAG".equals(str))
      {
        a.f(this.a, this.b, this.c);
        return;
      }
      if ("com.tencent.android.tpush.action.PUSH_CLICK.RESULT".equals(str))
      {
        com.tencent.android.tpush.service.b.a.a().b(this.b, this.c);
        return;
      }
      if (!"com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT".equals(str))
        continue;
      com.tencent.android.tpush.service.b.a.a().b(this.b, this.c);
      Intent localIntent = new Intent("com.tencent.android.tpush.action.FEEDBACK");
      localIntent.putExtras(this.c);
      localIntent.putExtra("TPUSH.FEEDBACK", 4);
      this.b.sendBroadcast(localIntent);
      return;
    }
    while (!"com.tencent.android.tpush.action.CUSTOM_NOTIFICATION".equals(str));
    TpnsPushMsg localTpnsPushMsg = new TpnsPushMsg();
    localTpnsPushMsg.msgId = this.c.getLongExtra("msgId", -System.currentTimeMillis());
    localTpnsPushMsg.accessId = this.c.getLongExtra("accessId", -1L);
    localTpnsPushMsg.appPkgName = this.c.getStringExtra("appPkgName");
    localTpnsPushMsg.busiMsgId = 0L;
    localTpnsPushMsg.timestamp = System.currentTimeMillis();
    localTpnsPushMsg.type = this.c.getIntExtra("type", 1);
    localTpnsPushMsg.multiPkg = 0L;
    localTpnsPushMsg.date = this.c.getStringExtra("date");
    localTpnsPushMsg.content = ("{\"title\":\"" + this.c.getStringExtra("title") + "\",\"content\":\"" + this.c.getStringExtra("content") + "\",\"builder_id\":" + this.c.getLongExtra("builderId", 0L) + ",\"ring\":1,\"vibrate\":1,\"clearable\":1,\"accept_time\":[{\"start\":{\"hour\":\"" + this.c.getStringExtra("hour") + "\",\"min\":\"" + this.c.getStringExtra("min") + "\"},\"end\":{\"hour\":\"23\",\"min\":\"59\"}}],\"action\":{\"action_type\":1}}");
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(localTpnsPushMsg);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(0);
    arrayOfObject[1] = "127.0.0.1";
    com.tencent.android.tpush.service.channel.a locala = new com.tencent.android.tpush.service.channel.a(arrayOfObject);
    com.tencent.android.tpush.service.b.a.a().a(localArrayList, this.c.getLongExtra("timeUs", System.currentTimeMillis()), locala);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.g
 * JD-Core Version:    0.6.0
 */
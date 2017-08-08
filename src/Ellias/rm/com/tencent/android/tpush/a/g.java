package com.tencent.android.tpush.a;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import org.json.JSONException;

class g
  implements Runnable
{
  private Context b;
  private Intent c;
  private XGIOperateCallback d;

  public g(f paramf, Context paramContext, Intent paramIntent, XGIOperateCallback paramXGIOperateCallback)
  {
    TLog.v("XGService", "create PushMessageRunnable, intent=" + paramIntent);
    this.b = paramContext;
    this.c = paramIntent;
    this.d = paramXGIOperateCallback;
  }

  private void a()
  {
    Intent localIntent = new Intent("com.tencent.android.tpush.action.PUSH_MESSAGE");
    localIntent.setPackage(this.b.getPackageName());
    localIntent.putExtras(this.c);
    TLog.v("XGService", "@@ sendCopyToApp(" + localIntent + "," + localIntent.getExtras() + ")");
    this.b.sendBroadcast(localIntent);
  }

  public void run()
  {
    TLog.v("XGService", "PushMessageRunnable @ run()");
    try
    {
      long l1 = this.c.getLongExtra("msgId", -1L);
      String str1 = this.c.getPackage();
      String str2 = "@" + l1 + str1 + "@";
      long l2 = this.c.getLongExtra("accId", -1L);
      String str3 = "";
      if (l1 > 0L)
      {
        str3 = str3 + c.c(this.b, new StringBuilder().append("tpush_msgId_").append(l2).toString());
        if (str3.length() > 20480)
          str3 = str3.substring(0, str3.indexOf("@@", 5120));
      }
      TLog.i("XGService", ">> msgIds:" + str3);
      TLog.i("XGService", ">> msgId:" + l1 + ",is contains:" + str3.contains(str2));
      Object localObject;
      if ((l1 < 0L) || (!str3.contains(str2)))
      {
        TLog.i("XGService", ">> PushMessageRunnable@!msgIds.contains(msgIdstr)@msgId:" + l1 + "@pkgName:" + str1);
        c.a(this.b, "tpush_msgId_" + l2, str2 + str3);
        TLog.i("XGService", ">> PushMessageRunnable@!msgIds.contains(msgIdstr)@msgIds:" + str3);
        a();
        h localh = h.a(this.b, this.c);
        a locala = localh.h();
        localObject = null;
        if (locala != null)
        {
          int i = localh.h().b();
          localObject = null;
          if (i == 1)
          {
            boolean bool = c.a(localh.f());
            localObject = null;
            if (!bool)
            {
              localh.a();
              XGPushManager.msgAck(this.b, localh);
            }
          }
        }
      }
      while (true)
      {
        if (this.d != null)
        {
          if (localObject == null)
            break;
          this.d.onFail("", -1, localObject.toString());
        }
        return;
        this.d = null;
        localObject = null;
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
        TLog.e("XGService", "push解包失败", localJSONException);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        TLog.e("XGService", "push消息类型错误", localIllegalArgumentException);
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
        TLog.e("XGService", "", localSecurityException);
    }
    catch (Throwable localThrowable)
    {
      while (true)
        TLog.e("XGService", "出现未知异常", localThrowable);
      this.d.onSuccess("", 0);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.a.g
 * JD-Core Version:    0.6.0
 */
package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.a.h;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import org.json.JSONException;

public abstract class XGPushBaseReceiver extends BroadcastReceiver
{
  public static final int SUCCESS;

  private void a(Context paramContext, Intent paramIntent)
  {
    try
    {
      h localh = h.a(paramContext, paramIntent);
      if ((localh != null) && (localh.h() != null) && (localh.h().b() == 2))
      {
        XGPushManager.msgAck(paramContext, localh);
        XGPushTextMessage localXGPushTextMessage = new XGPushTextMessage();
        localXGPushTextMessage.a = localh.h().d();
        localXGPushTextMessage.b = localh.h().e();
        localXGPushTextMessage.c = localh.h().f();
        onTextMessage(paramContext, localXGPushTextMessage);
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      TLog.e("XGPushMessage", "解包失败", localJSONException);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      TLog.e("XGPushMessage", "参数不对", localIllegalArgumentException);
    }
  }

  private void b(Context paramContext, Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("TPUSH.FEEDBACK", -1);
    int j = paramIntent.getIntExtra("TPUSH.ERRORCODE", -1);
    Log.i("XGPushMessage", ">>XGPushBaseReceiver receiver feedbackHandler>>@feedbackType:" + i + " ,@errorCode:" + j);
    switch (i)
    {
    default:
      TLog.e("XGPushMessage", "未知的feedbackType:" + i);
    case 1:
    case 2:
    case 3:
      String str;
      do
      {
        return;
        XGPushRegisterResult localXGPushRegisterResult = new XGPushRegisterResult();
        localXGPushRegisterResult.parseIntent(paramIntent);
        onRegisterResult(paramContext, j, localXGPushRegisterResult);
        return;
        onUnregisterResult(paramContext, j);
        return;
        str = Rijndael.decrypt(paramIntent.getStringExtra("tagName"));
      }
      while (c.a(str));
      int m = paramIntent.getIntExtra("tagFlag", -1);
      if (m == 1)
      {
        onSetTagResult(paramContext, j, str);
        return;
      }
      if (m == 2)
      {
        onDeleteTagResult(paramContext, j, str);
        return;
      }
      TLog.e("XGPushMessage", "错误的标签处理类型：" + m + " ,标签名：" + str);
      return;
    case 4:
      int k = paramIntent.getIntExtra("action", 1);
      if (k == 1)
      {
        long l = paramIntent.getLongExtra("busiMsgId", -1L);
        TLog.v("XGService", "ACTION_PUSH_CANCELLED_RESULT onReceive(" + paramContext.getPackageName() + "," + paramIntent + "busiId" + l + ", actionType = " + k + ")");
        XGPushManager.reportNotifactionClear2Mta(paramContext, l);
      }
      XGPushClickedResult localXGPushClickedResult = new XGPushClickedResult();
      localXGPushClickedResult.parseIntent(paramIntent);
      onNotifactionClickedResult(paramContext, localXGPushClickedResult);
      return;
    case 5:
    }
    XGPushShowedResult localXGPushShowedResult = new XGPushShowedResult();
    localXGPushShowedResult.parseIntent(paramIntent);
    onNotifactionShowedResult(paramContext, localXGPushShowedResult);
  }

  public abstract void onDeleteTagResult(Context paramContext, int paramInt, String paramString);

  public abstract void onNotifactionClickedResult(Context paramContext, XGPushClickedResult paramXGPushClickedResult);

  public abstract void onNotifactionShowedResult(Context paramContext, XGPushShowedResult paramXGPushShowedResult);

  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramContext != null) && (paramIntent != null))
    {
      Log.i("XGPushMessage", ">>TPushBaseReceiver receiver msg>>");
      if (TpnsSecurity.checkTpnsSecurityLibSo(paramContext));
    }
    else
    {
      return;
    }
    Log.i("TPush", ">>TPushBaseReceiver receiver msg>>" + paramIntent + "," + paramIntent.getExtras());
    String str = paramIntent.getAction();
    if ("com.tencent.android.tpush.action.PUSH_MESSAGE".equals(str))
    {
      a(paramContext, paramIntent);
      return;
    }
    if ("com.tencent.android.tpush.action.FEEDBACK".equals(str))
    {
      b(paramContext, paramIntent);
      return;
    }
    TLog.e("XGPushMessage", "未知的action:" + str);
  }

  public abstract void onRegisterResult(Context paramContext, int paramInt, XGPushRegisterResult paramXGPushRegisterResult);

  public abstract void onSetTagResult(Context paramContext, int paramInt, String paramString);

  public abstract void onTextMessage(Context paramContext, XGPushTextMessage paramXGPushTextMessage);

  public abstract void onUnregisterResult(Context paramContext, int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGPushBaseReceiver
 * JD-Core Version:    0.6.0
 */
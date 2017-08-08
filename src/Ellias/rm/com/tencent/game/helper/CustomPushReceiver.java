package com.tencent.game.helper;

import android.content.Context;
import android.util.Log;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

public class CustomPushReceiver extends XGPushBaseReceiver
{
  public static final String LogTag = "TPushRMReceiver";

  public void onDeleteTagResult(Context paramContext, int paramInt, String paramString)
  {
    if (paramInt == 0);
    for (String str = "\"" + paramString + "\"删除成功"; ; str = "\"" + paramString + "\"删除失败,错误码：" + paramInt)
    {
      Log.d("TPushRMReceiver", str);
      return;
    }
  }

  public void onNotifactionClickedResult(Context paramContext, XGPushClickedResult paramXGPushClickedResult)
  {
    Log.d("TPushRMReceiver", "通知被打开 :" + paramXGPushClickedResult);
  }

  public void onNotifactionShowedResult(Context paramContext, XGPushShowedResult paramXGPushShowedResult)
  {
    Log.d("TPushRMReceiver", "已展示通知 :" + paramXGPushShowedResult);
  }

  public void onRegisterResult(Context paramContext, int paramInt, XGPushRegisterResult paramXGPushRegisterResult)
  {
    String str;
    if (paramInt == 0)
    {
      str = paramXGPushRegisterResult + "注册成功";
      paramXGPushRegisterResult.getToken();
    }
    while (true)
    {
      Log.d("TPushRMReceiver", str);
      return;
      str = paramXGPushRegisterResult + "注册失败，错误码：" + paramInt;
    }
  }

  public void onSetTagResult(Context paramContext, int paramInt, String paramString)
  {
    if (paramInt == 0);
    for (String str = "\"" + paramString + "\"设置成功"; ; str = "\"" + paramString + "\"设置失败,错误码：" + paramInt)
    {
      Log.d("TPushRMReceiver", str);
      return;
    }
  }

  public void onTextMessage(Context paramContext, XGPushTextMessage paramXGPushTextMessage)
  {
    Log.d("TPushRMReceiver", "收到消息:" + paramXGPushTextMessage.toString());
  }

  public void onUnregisterResult(Context paramContext, int paramInt)
  {
    if (paramInt == 0);
    for (String str = "反注册成功"; ; str = "反注册失败" + paramInt)
    {
      Log.d("TPushRMReceiver", str);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.CustomPushReceiver
 * JD-Core Version:    0.6.0
 */
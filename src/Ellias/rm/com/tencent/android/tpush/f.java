package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.i;
import com.tencent.android.tpush.logging.TLog;

final class f extends BroadcastReceiver
{
  f(Intent paramIntent, XGIOperateCallback paramXGIOperateCallback)
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramContext == null) || (paramIntent == null))
      return;
    TLog.d("TPush", ">>> service start from app :" + paramIntent.getStringExtra("pkg") + " , sdk version:" + paramIntent.getFloatExtra("ver", 0.0F));
    i.a(paramContext, this);
    XGPushManager.a(paramContext, this.a, this.b);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.f
 * JD-Core Version:    0.6.0
 */
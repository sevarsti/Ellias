package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.c;
import com.tencent.android.tpush.common.i;
import com.tencent.android.tpush.logging.TLog;

final class h extends BroadcastReceiver
{
  h(XGIOperateCallback paramXGIOperateCallback)
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    TLog.d("TPush", ">>> register call back to " + paramContext.getPackageName());
    i.a(paramContext, this);
    c.a().a(new j(this.a, paramContext, paramIntent, 1));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.h
 * JD-Core Version:    0.6.0
 */
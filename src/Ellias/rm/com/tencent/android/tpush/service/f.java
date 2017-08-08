package com.tencent.android.tpush.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.c;
import com.tencent.android.tpush.logging.TLog;

class f extends BroadcastReceiver
{
  private f(a parama)
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramIntent != null) && (paramContext != null))
    {
      TLog.v("XGService", "@@ onReceive(" + paramContext.getPackageName() + "," + paramIntent + ")");
      c.a().a(new g(this.a, paramContext, paramIntent));
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.f
 * JD-Core Version:    0.6.0
 */
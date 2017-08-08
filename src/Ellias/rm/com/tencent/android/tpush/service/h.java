package com.tencent.android.tpush.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.c;
import com.tencent.android.tpush.logging.TLog;

class h extends BroadcastReceiver
{
  private h(a parama)
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    TLog.v("XGService", "@@ onReceive()");
    if ((paramIntent != null) && (paramContext != null))
      c.a().a(new g(this.a, paramContext, paramIntent));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.h
 * JD-Core Version:    0.6.0
 */
package com.tencent.android.tpush.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.logging.TLog;
import java.util.Timer;

class k extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramContext == null) || (paramIntent == null))
      return;
    TLog.v("XGService", "@@ onReceive(" + paramContext + "," + paramIntent + ")");
    i.a(5000L);
    String str = paramIntent.getStringExtra("packName");
    if ((i.a(str)) && (!str.equals(paramContext.getPackageName())))
    {
      i.b().purge();
      i.a(paramContext, this);
      return;
    }
    i.a(5000L);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.common.k
 * JD-Core Version:    0.6.0
 */
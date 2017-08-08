package com.tencent.map.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class p extends BroadcastReceiver
{
  p(n paramn)
  {
  }

  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((!paramIntent.getBooleanExtra("noConnectivity", false)) && (n.d(this.a) != null))
      n.d(this.a).sendEmptyMessage(256);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.map.b.p
 * JD-Core Version:    0.6.0
 */
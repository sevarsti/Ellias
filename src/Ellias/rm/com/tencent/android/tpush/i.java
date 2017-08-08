package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.c;

final class i extends BroadcastReceiver
{
  i(XGIOperateCallback paramXGIOperateCallback)
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    com.tencent.android.tpush.common.i.a(paramContext, this);
    c.a().a(new j(this.a, paramContext, paramIntent, 1));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.i
 * JD-Core Version:    0.6.0
 */
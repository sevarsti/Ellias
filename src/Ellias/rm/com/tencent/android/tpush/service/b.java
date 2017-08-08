package com.tencent.android.tpush.service;

import android.content.Context;
import com.tencent.android.tpush.common.i;
import com.tencent.android.tpush.logging.TLog;

class b
  implements Runnable
{
  b(a parama, Context paramContext)
  {
  }

  public void run()
  {
    TLog.i("XGService", ">> Fire service restart broadcast.");
    i.d(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.b
 * JD-Core Version:    0.6.0
 */
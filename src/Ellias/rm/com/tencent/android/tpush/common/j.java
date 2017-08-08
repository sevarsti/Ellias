package com.tencent.android.tpush.common;

import android.content.Intent;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.XGPushService;
import java.util.TimerTask;

final class j extends TimerTask
{
  public void run()
  {
    TLog.i("XGService", ">> run()");
    Intent localIntent = new Intent(com.tencent.android.tpush.service.i.e(), XGPushService.class);
    com.tencent.android.tpush.service.i.a(com.tencent.android.tpush.service.i.e(), localIntent);
    i.a(com.tencent.android.tpush.service.i.e(), i.a());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.common.j
 * JD-Core Version:    0.6.0
 */
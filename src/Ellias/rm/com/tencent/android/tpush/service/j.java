package com.tencent.android.tpush.service;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.channel.b;

class j extends Handler
{
  j(i parami, Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    if (paramMessage != null);
    switch (paramMessage.what)
    {
    default:
      return;
    case 1:
    }
    if (!i.a(this.a))
    {
      TLog.i("XGService", ">> stopService " + paramMessage.obj);
      Intent localIntent = (Intent)paramMessage.obj;
      i.f().stopService(localIntent);
      return;
    }
    if (!i.g())
    {
      TLog.i("XGService", ">> Service's first running @@" + i.f().getPackageName());
      i.a(true);
      c.b(i.f());
      g.a(i.f());
      a.a().a(i.f());
    }
    b.b().c();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.j
 * JD-Core Version:    0.6.0
 */
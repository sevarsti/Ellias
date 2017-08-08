package com.tencent.qqgamemi.business;

import android.os.Handler;
import android.os.Message;

class c extends Handler
{
  c(GameActionReportHelper paramGameActionReportHelper)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    case 322:
    default:
      return;
    case 320:
      long l2 = 1000L * ((Integer)paramMessage.obj).intValue();
      if (l2 > 1000L)
        GameActionReportHelper.a(l2);
      GameActionReportHelper.a(this.a);
      return;
    case 321:
      GameActionReportHelper.b(this.a);
      return;
    case 323:
    }
    long l1 = 1000L * ((Integer)paramMessage.obj).intValue();
    if (l1 > 1000L)
      GameActionReportHelper.a(l1);
    GameActionReportHelper.a(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.c
 * JD-Core Version:    0.6.0
 */
package com.tencent.qqgamemi.business;

import android.os.Handler;
import android.os.Message;
import com.tencent.component.utils.log.LogUtil;

class a extends Handler
{
  a(AutoDownLoadGameJoy paramAutoDownLoadGameJoy)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 1:
      if (paramMessage.arg1 == 0)
      {
        AutoDownLoadGameJoy.a(this.a, (AutoDownLoadInfo)paramMessage.obj);
        return;
      }
      LogUtil.i(AutoDownLoadGameJoy.b(), "sendAutoDownLoadInfo failed:" + paramMessage.obj);
      return;
    case 2:
    }
    String str = (String)paramMessage.obj;
    AutoDownLoadGameJoy.a(this.a, str);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.a
 * JD-Core Version:    0.6.0
 */
package com.tencent.qqgamemi.data;

import CobraHallQmiProto.TBodyGetGameExtendInfoRsp;
import CobraHallQmiProto.TBodyGetUserInfoV2Resp;
import CobraHallQmiProto.TBodyQmiStartRsp;
import android.os.Handler;
import android.os.Message;
import com.tencent.qqgamemi.common.TLog;

class a extends Handler
{
  a(DataModel paramDataModel)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 11:
      this.a.b();
      return;
    case 1000:
      if (paramMessage.arg1 == 0)
      {
        DataModel.a(this.a, (TBodyQmiStartRsp)paramMessage.obj);
        return;
      }
      TLog.e(DataModel.f(), "request start info failed:" + paramMessage.obj);
      DataModel.a(this.a, null);
      return;
    case 1001:
      if (paramMessage.arg1 == 0)
      {
        TBodyGetGameExtendInfoRsp localTBodyGetGameExtendInfoRsp = (TBodyGetGameExtendInfoRsp)paramMessage.obj;
        DataModel.a(this.a, localTBodyGetGameExtendInfoRsp.noGameFreshTimeInterval);
        this.a.a(localTBodyGetGameExtendInfoRsp.vGameExtendInfo);
        return;
      }
      TLog.e(DataModel.f(), "request game url failed:" + paramMessage.obj);
      this.a.d();
      return;
    case 1003:
      if (paramMessage.arg1 == 0)
      {
        DataModel.a(this.a, (TBodyGetUserInfoV2Resp)paramMessage.obj);
        return;
      }
      TLog.e(DataModel.f(), "request user info failed:" + paramMessage.obj);
      DataModel.a(this.a, null);
      return;
    case 2000:
    }
    this.a.d();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.data.a
 * JD-Core Version:    0.6.0
 */
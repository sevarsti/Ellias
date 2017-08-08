package com.tencent.qqgamemi.login;

import CobraHallQmiProto.TBodyGetSdkSybAccessTokenResp;
import android.app.Service;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.qqgamemi.QMiService;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.DataModel;

class a extends Handler
{
  a(QMiLoginManager paramQMiLoginManager, Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 1:
    }
    QMiLoginManager.a(this.a, false);
    int i = paramMessage.arg1;
    if (i == 0)
    {
      TLog.c("SYBACCOUNT", "login success");
      TBodyGetSdkSybAccessTokenResp localTBodyGetSdkSybAccessTokenResp = (TBodyGetSdkSybAccessTokenResp)paramMessage.obj;
      String str = Long.toString(localTBodyGetSdkSybAccessTokenResp.sybId);
      int j = localTBodyGetSdkSybAccessTokenResp.sybAccessTokenType;
      byte[] arrayOfByte = localTBodyGetSdkSybAccessTokenResp.accessToken;
      QMiLoginManager.a(this.a, "", str, new byte[0], new byte[0], "", j, arrayOfByte, "");
      Service localService = QMiService.a();
      if (localService != null)
        DataModel.a(localService).a(Long.parseLong(str), null);
    }
    while (true)
    {
      QMiLoginManager.a(0);
      return;
      TLog.c("SYBACCOUNT", "login failed:" + i);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.login.a
 * JD-Core Version:    0.6.0
 */
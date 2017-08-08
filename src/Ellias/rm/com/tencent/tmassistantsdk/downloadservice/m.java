package com.tencent.tmassistantsdk.downloadservice;

import android.os.Handler;
import android.os.Message;

class m extends Handler
{
  m(NetworkMonitorReceiver paramNetworkMonitorReceiver)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    switch (paramMessage.what)
    {
    default:
      return;
    case 67:
    }
    this.a.d();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.m
 * JD-Core Version:    0.6.0
 */
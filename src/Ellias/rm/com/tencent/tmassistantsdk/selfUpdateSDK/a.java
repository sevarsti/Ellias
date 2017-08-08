package com.tencent.tmassistantsdk.selfUpdateSDK;

import android.os.Handler;
import android.os.Message;

class a extends Handler
{
  a(PackageInstallReceiver paramPackageInstallReceiver)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    PackageInstallReceiver.a(this.a, (String)paramMessage.obj, paramMessage.what);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.a
 * JD-Core Version:    0.6.0
 */
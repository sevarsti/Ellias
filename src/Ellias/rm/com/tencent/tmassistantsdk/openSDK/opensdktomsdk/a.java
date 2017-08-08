package com.tencent.tmassistantsdk.openSDK.opensdktomsdk;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.b;

class a
  implements Handler.Callback
{
  a(TMOpenSDKToMsdkManager paramTMOpenSDKToMsdkManager)
  {
  }

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    case 2:
    default:
    case 4:
    case 5:
    case 0:
    case 1:
    case 6:
    case 3:
    }
    while (true)
    {
      return false;
      b localb = (b)paramMessage.obj;
      if (localb == null)
        continue;
      this.a.onNetworkFinishedSuccess(localb);
      continue;
      int j = ((Integer)paramMessage.obj).intValue();
      this.a.onNetworkFinishedFailed(j);
      continue;
      int i = paramMessage.arg1;
      String str = paramMessage.obj.toString();
      this.a.handleInstall(str, i);
      continue;
      Bundle localBundle2 = paramMessage.getData();
      if (localBundle2 == null)
        continue;
      this.a.handleDownloading(localBundle2.getLong("receiveDataLen"), localBundle2.getLong("totalDataLen"));
      continue;
      Bundle localBundle1 = paramMessage.getData();
      if (localBundle1 == null)
        continue;
      this.a.handleDownloadContinue(localBundle1.getLong("receiveDataLen"), localBundle1.getLong("totalDataLen"));
      continue;
      this.a.handleDownloadFailed();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.a
 * JD-Core Version:    0.6.0
 */
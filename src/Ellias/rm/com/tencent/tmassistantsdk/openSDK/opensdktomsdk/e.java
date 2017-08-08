package com.tencent.tmassistantsdk.openSDK.opensdktomsdk;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.tmassistantsdk.d.h;
import com.tencent.tmassistantsdk.openSDK.opensdktomsdk.a.a;
import com.tencent.tmassistantsdk.protocol.jce.TipsInfoLog;

class e
  implements View.OnClickListener
{
  e(TMOpenSDKToMsdkManager paramTMOpenSDKToMsdkManager)
  {
  }

  public void onClick(View paramView)
  {
    if (this.a.dialog.isShowing())
    {
      this.a.dialog.dismiss();
      this.a.mHttpRequest = null;
    }
    TipsInfoLog localTipsInfoLog = h.g().a(this.a.mAuthorizedInfo);
    localTipsInfoLog.cancelBtnClickCount = (1 + localTipsInfoLog.cancelBtnClickCount);
    h.g().a(localTipsInfoLog);
    try
    {
      if (this.a.mClient != null)
        this.a.pauseDownloadTask(this.a.mDownloadUrl);
      if (this.a.authorizedState == 2)
      {
        this.a.notifyAuthorizedFinished(true, this.a.mAuthorizedInfo);
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
      this.a.notifyAuthorizedFinished(false, this.a.mAuthorizedInfo);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.e
 * JD-Core Version:    0.6.0
 */
package com.tencent.tmassistantsdk.openSDK.opensdktomsdk;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a;

class f
  implements View.OnClickListener
{
  f(TMOpenSDKToMsdkManager paramTMOpenSDKToMsdkManager)
  {
  }

  public void onClick(View paramView)
  {
    if (this.a.mContext == null)
      return;
    a locala = (a)paramView.getTag();
    try
    {
      int i = this.a.mOpenSDK.a(this.a.mSupportVersionCode);
      switch (i)
      {
      default:
        return;
      case 0:
        try
        {
          this.a.startToQQDownloaderAuthorized(locala.d);
          return;
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          return;
        }
      case 2:
      case 1:
      }
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      return;
    }
    this.a.startDownloadTask(this.a.mDownloadUrl, locala.d);
    return;
    this.a.startDownloadTask(this.a.mDownloadUrl, locala.d);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.f
 * JD-Core Version:    0.6.0
 */
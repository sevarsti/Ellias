package com.tencent.tmassistantsdk.openSDK.opensdktomsdk;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.tmassistantsdk.openSDK.opensdktomsdk.a.a;

class d
  implements View.OnClickListener
{
  d(TMOpenSDKToMsdkManager paramTMOpenSDKToMsdkManager)
  {
  }

  public void onClick(View paramView)
  {
    if (this.a.mAuthorizedInfo == null)
      return;
    if (this.a.mContext != null)
      this.a.getUserAuthorizedInfo(this.a.mAuthorizedInfo, this.a.mContext);
    this.a.dialog.dismiss();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.d
 * JD-Core Version:    0.6.0
 */
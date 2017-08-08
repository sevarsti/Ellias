package com.pay.ui.common;

import android.os.Message;

public class APHFPaySuccessActivity$tHander extends APHFPaySuccessActivity.mHandler
{
  public APHFPaySuccessActivity$tHander(APHFPaySuccessActivity paramAPHFPaySuccessActivity)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    switch (paramMessage.what)
    {
    default:
      return;
    case 0:
    }
    this.a.initStateUI(APHFPaySuccessActivity.b(this.a));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APHFPaySuccessActivity.tHander
 * JD-Core Version:    0.6.0
 */
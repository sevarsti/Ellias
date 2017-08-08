package com.pay.ui.qdsafe;

import android.content.Intent;
import android.os.Bundle;
import com.pay.tool.APDataInterface;

public class APSafeCenterWebActivity$SigManager
{
  public APSafeCenterWebActivity$SigManager(APSafeCenterWebActivity paramAPSafeCenterWebActivity)
  {
  }

  public void SetMbSig(String paramString)
  {
    APDataInterface.singleton().setMbSig(paramString);
    Intent localIntent = new Intent();
    Bundle localBundle = new Bundle();
    localBundle.putInt("ret", 0);
    localIntent.putExtras(localBundle);
    this.a.setResult(0, localIntent);
    this.a.finish();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.APSafeCenterWebActivity.SigManager
 * JD-Core Version:    0.6.0
 */
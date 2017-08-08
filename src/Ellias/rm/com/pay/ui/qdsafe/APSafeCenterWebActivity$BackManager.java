package com.pay.ui.qdsafe;

public class APSafeCenterWebActivity$BackManager
{
  private String a = "";

  public APSafeCenterWebActivity$BackManager(APSafeCenterWebActivity paramAPSafeCenterWebActivity)
  {
  }

  public void BackToMain()
  {
    this.b.finish();
  }

  public String GetBackText()
  {
    return this.a;
  }

  public void SetBackText(String paramString)
  {
    this.a = paramString;
    this.b.SetBackBtn(paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.APSafeCenterWebActivity.BackManager
 * JD-Core Version:    0.6.0
 */
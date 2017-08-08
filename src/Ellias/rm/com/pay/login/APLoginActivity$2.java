package com.pay.login;

import com.pay.ui.common.APUICommonMethod;

class APLoginActivity$2 extends APWtLoginListener
{
  public void LoginFailCallBack(String paramString)
  {
    APUICommonMethod.showToast(this.a, paramString);
    APLoginManager.loginFinish("", "");
  }

  public void LoginNeedVerify(byte[] paramArrayOfByte)
  {
    APLoginActivity.a(this.a, paramArrayOfByte);
  }

  public void LoginSuccCallBack(String paramString1, String paramString2)
  {
    this.a.finish();
    APLoginManager.loginFinish(paramString1, paramString2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginActivity.2
 * JD-Core Version:    0.6.0
 */
package com.pay.buyManager;

import com.pay.login.IAPLoginCallBack;

final class e
  implements IAPLoginCallBack
{
  e(APPayBase paramAPPayBase)
  {
  }

  public final void onLoginFail()
  {
  }

  public final void onLoginSucc(String paramString1, String paramString2)
  {
    switch (this.a.relogin_from)
    {
    default:
      return;
    case 1:
      this.a.buyInfo();
      return;
    case 2:
      new APGetKeyManager(this.a.context).getSecretyKey(this.a.getKeyCallBack);
      return;
    case 3:
      new APGetKeyManager(this.a.context).getCryptoKey(this.a.getKeyCallBack);
      return;
    case 4:
      this.a.getTokenAndSave(this.a.saveType);
      return;
    case 5:
    }
    this.a.doSave(this.a.saveType);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.e
 * JD-Core Version:    0.6.0
 */
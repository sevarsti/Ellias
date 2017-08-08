package com.pay.buyManager;

import android.content.Context;
import com.pay.http.APNetworkManager;
import com.pay.tool.APAppDataInterface;

public class APGetKeyManager extends APPayBase
{
  private int a = 0;

  public APGetKeyManager(Context paramContext)
  {
    super(paramContext);
  }

  private void a(int paramInt)
  {
    APNetworkManager.getInstance().getKey(paramInt, this.a, new d(this));
  }

  public void changeKey(IAPGetKeyCallBack paramIAPGetKeyCallBack)
  {
    this.getKeyCallBack = paramIAPGetKeyCallBack;
    if (APAppDataInterface.singleton().getSecretKey().length() <= 0)
    {
      a(0);
      return;
    }
    if (APAppDataInterface.singleton().getCryptoKey().equals(""))
    {
      a(1);
      return;
    }
    paramIAPGetKeyCallBack.onGetKeySucc("");
  }

  public void getCryptoKey(IAPGetKeyCallBack paramIAPGetKeyCallBack)
  {
    this.getKeyCallBack = paramIAPGetKeyCallBack;
    a(1);
  }

  public void getSecretyKey(IAPGetKeyCallBack paramIAPGetKeyCallBack)
  {
    this.getKeyCallBack = paramIAPGetKeyCallBack;
    a(0);
  }

  public void getSecretyKey(IAPGetKeyCallBack paramIAPGetKeyCallBack, int paramInt)
  {
    this.a = paramInt;
    getSecretyKey(paramIAPGetKeyCallBack);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.APGetKeyManager
 * JD-Core Version:    0.6.0
 */
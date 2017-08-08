package com.pay.buyManager;

import android.content.Context;
import com.pay.login.APLoginManager;
import com.pay.login.IAPLoginCallBack;

public class APPayBase
{
  public static final int RELOGIN_FROM_BUYPAY = 1;
  public static final int RELOGIN_FROM_CRYPTROKEY = 3;
  public static final int RELOGIN_FROM_SAVE = 5;
  public static final int RELOGIN_FROM_SECRETKEY = 2;
  public static final int RELOGIN_FROM_TOKEN = 4;
  public static final int SAVETYPE_GAME = 0;
  public static final int SAVETYPE_GOODS = 1;
  public static final int SAVETYPE_MONTH = 4;
  public static final int SAVETYPE_QB = 3;
  public static final int SAVETYPE_QD = 2;
  public static final int SAVETYPE_SUBSCRIBE = 5;
  private IAPLoginCallBack a = new e(this);
  protected Context context;
  protected int curChannel;
  protected IAPGetKeyCallBack getKeyCallBack;
  protected String goodsTonkenUrl;
  protected int relogin_from;
  protected int saveType;

  public APPayBase(Context paramContext)
  {
    this.context = paramContext;
  }

  protected void buyInfo()
  {
  }

  public void doSave(int paramInt)
  {
  }

  public String getCurrentChannel()
  {
    switch (this.curChannel)
    {
    case 6:
    case 10:
    default:
      return "";
    case 0:
      return "qdqb";
    case 11:
      return "qbqd";
    case 5:
      return "mcard";
    case 4:
      return "qqcard";
    case 1:
      return "cft";
    case 2:
      return "bank";
    case 8:
      return "wechat";
    case 7:
      return "yb";
    case 9:
      return "hf";
    case 3:
    }
    return "kj";
  }

  public void getTokenAndSave(int paramInt)
  {
  }

  protected void loadLogin(boolean paramBoolean, String paramString, int paramInt)
  {
    this.relogin_from = paramInt;
    APLoginManager localAPLoginManager = new APLoginManager(this.context);
    String str = "";
    if ((this.curChannel == 0) || (this.curChannel == 11))
      str = "立即支付";
    localAPLoginManager.loadLogin(this.curChannel, paramBoolean, paramString, str, this.a);
  }

  protected void loginErrorProgress(int paramInt)
  {
    this.relogin_from = paramInt;
    new APLoginManager(this.context).loginProgress(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.APPayBase
 * JD-Core Version:    0.6.0
 */
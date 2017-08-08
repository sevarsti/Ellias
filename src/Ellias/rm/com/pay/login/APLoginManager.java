package com.pay.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.pay.data.userInfo.APUserInfo;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataInterface;
import com.pay.ui.common.APAlertDialog;
import com.pay.ui.common.APAlertDialog.Builder;

public class APLoginManager
{
  private static IAPLoginCallBack b;
  private Context a;
  private int c;

  public APLoginManager(Context paramContext)
  {
    this.a = paramContext;
  }

  private void a(boolean paramBoolean, String paramString1, String paramString2)
  {
    if (!hashLogin())
      return;
    Intent localIntent = new Intent(this.a, APLoginActivity.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("enSureBtn", paramString2);
    localBundle.putString("tittle", paramString1);
    localBundle.putBoolean("editUin", paramBoolean);
    localBundle.putInt("curChannel", this.c);
    localIntent.putExtras(localBundle);
    this.a.startActivity(localIntent);
  }

  public static void loginFinish(String paramString1, String paramString2)
  {
    APUserInfo localAPUserInfo = APDataInterface.singleton().getUserInfo();
    if ((APAppDataInterface.singleton().getReloginInSDK()) && (APAppDataInterface.singleton().getIsOwnResearch()))
    {
      localAPUserInfo.payId = paramString1;
      localAPUserInfo.authKey = paramString2;
      localAPUserInfo.openId = paramString1;
      localAPUserInfo.openKey = paramString2;
      localAPUserInfo.sessionId = "uin";
      localAPUserInfo.sessionType = "skey";
      if (b != null)
      {
        if ((!paramString1.equals("")) && (!paramString2.equals("")))
          break label90;
        b.onLoginFail();
      }
    }
    label90: 
    do
    {
      while (true)
      {
        return;
        b.onLoginSucc(paramString1, paramString2);
        return;
        if ((paramString1.equals("")) || (paramString2.equals("")))
          break;
        localAPUserInfo.payId = paramString1;
        localAPUserInfo.authKey = paramString2;
        APDataInterface.singleton().getUserInfo().isUinLogin = true;
        if (b == null)
          continue;
        b.onLoginSucc(paramString1, paramString2);
        return;
      }
      APDataInterface.singleton().getUserInfo().isUinLogin = false;
    }
    while (b == null);
    b.onLoginFail();
  }

  public static void release()
  {
    b = null;
  }

  public boolean hashLogin()
  {
    try
    {
      Class.forName("com.paylogin.sdk.payLoginSDK");
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
    }
    return false;
  }

  public void loadLogin(int paramInt, boolean paramBoolean, String paramString1, String paramString2, IAPLoginCallBack paramIAPLoginCallBack)
  {
    b = paramIAPLoginCallBack;
    this.c = paramInt;
    a(paramBoolean, paramString1, paramString2);
  }

  public void loginProgress(IAPLoginCallBack paramIAPLoginCallBack)
  {
    b = paramIAPLoginCallBack;
    if ((APAppDataInterface.singleton().getReloginInSDK()) && (APAppDataInterface.singleton().getIsOwnResearch()))
    {
      a(false, "", "");
      return;
    }
    showLoginErrorAlert("登录过期，请重新登录");
  }

  protected void showLoginErrorAlert(String paramString)
  {
    APAlertDialog.Builder localBuilder = new APAlertDialog.Builder(this.a);
    localBuilder.setTitle("温馨提示");
    localBuilder.setMessage(paramString);
    localBuilder.setPositiveButton("确定", new APLoginManager.1(this));
    APAlertDialog localAPAlertDialog = localBuilder.create();
    if (localAPAlertDialog == null)
      return;
    localAPAlertDialog.setOnKeyListener(new APLoginManager.2(this));
    try
    {
      localAPAlertDialog.show();
      return;
    }
    catch (Exception localException)
    {
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginManager
 * JD-Core Version:    0.6.0
 */
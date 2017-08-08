package com.paylogin.sdk;

import android.content.Context;
import android.util.Log;
import oicq.wlogin_sdk.request.TransReqContext;
import oicq.wlogin_sdk.request.WUserSigInfo;
import oicq.wlogin_sdk.request.WloginLastLoginInfo;
import oicq.wlogin_sdk.request.WtloginHelper;
import oicq.wlogin_sdk.request.WtloginListener;
import oicq.wlogin_sdk.sharemem.WloginSimpleInfo;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.util;

public class payLoginSDK
{
  private static final long LOGIN_APPID = 518005007L;
  private static final int LOGIN_TIMEOUT = 1800;
  private Context context;
  public WloginLastLoginInfo info;
  private String lastUin = "";
  IPayLoginCallBack loginCallBack;
  WtloginListener mListener = new WtloginListener()
  {
    public void OnCheckPictureAndGetSt(String paramString, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo, int paramInt)
    {
      util.LOGD("OnCheckPictureAndGetSt:" + paramInt);
      if (paramInt == 0)
        payLoginSDK.this.LoginSucess(paramString, paramWUserSigInfo);
      while (true)
      {
        return;
        if (paramInt != 2)
          break;
        byte[] arrayOfByte = payLoginSDK.this.mLoginHelper.GetPictureData(paramString);
        if (payLoginSDK.this.loginCallBack == null)
          continue;
        payLoginSDK.this.loginCallBack.LoginNeedVerify(arrayOfByte);
        return;
      }
      payLoginSDK.this.LoginFail(paramString, paramInt);
    }

    public void OnGetStWithPasswd(String paramString1, long paramLong1, int paramInt1, long paramLong2, String paramString2, WUserSigInfo paramWUserSigInfo, int paramInt2)
    {
      util.LOGD("OnGetStWithPasswd:" + paramInt2);
      if (paramInt2 == 0)
        payLoginSDK.this.LoginSucess(paramString1, paramWUserSigInfo);
      while (true)
      {
        return;
        if (paramInt2 != 2)
          break;
        byte[] arrayOfByte = payLoginSDK.this.mLoginHelper.GetPictureData(paramString1);
        if (payLoginSDK.this.loginCallBack == null)
          continue;
        payLoginSDK.this.loginCallBack.LoginNeedVerify(arrayOfByte);
        return;
      }
      payLoginSDK.this.LoginFail(paramString1, paramInt2);
    }

    public void OnGetStWithoutPasswd(String paramString, long paramLong1, long paramLong2, int paramInt1, long paramLong3, WUserSigInfo paramWUserSigInfo, int paramInt2)
    {
      if (paramInt2 == 0)
      {
        payLoginSDK.this.LoginSucess(paramString, paramWUserSigInfo);
        return;
      }
      payLoginSDK.this.LoginFail(paramString, paramInt2);
    }

    public void OnInit(int paramInt)
    {
    }

    public void OnRefreshPictureData(String paramString, byte[] paramArrayOfByte, int paramInt)
    {
      util.LOGD("OnRefreshPictureData:" + paramInt);
    }

    public void OnRequestTransport(String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, int paramInt)
    {
      util.LOGD(util.buf_to_string(paramTransReqContext._body));
      Log.e("util.LOGD", util.buf_to_string(paramTransReqContext._body));
    }
  };
  public WtloginHelper mLoginHelper = null;
  private int resultCode = 0;
  private String resultMsg = "";
  private String uin = "";

  public payLoginSDK(Context paramContext)
  {
    this.context = paramContext;
    InitLogin();
  }

  public payLoginSDK(Context paramContext, IPayLoginCallBack paramIPayLoginCallBack)
  {
    this.context = paramContext;
    this.loginCallBack = paramIPayLoginCallBack;
    InitLogin();
  }

  private void InitLogin()
  {
    this.mLoginHelper = new WtloginHelper(this.context);
    this.mLoginHelper.SetTkTimeOut(1800L);
    this.mLoginHelper.SetListener(this.mListener);
    WloginLastLoginInfo localWloginLastLoginInfo = this.mLoginHelper.GetLastLoginInfo();
    if ((localWloginLastLoginInfo != null) && (localWloginLastLoginInfo.mAccount.length() > 0))
    {
      this.mLoginHelper.GetA1ByAccount(localWloginLastLoginInfo.mAccount, 518005007L);
      this.lastUin = localWloginLastLoginInfo.mAccount;
    }
  }

  public void LoginFail(String paramString, int paramInt)
  {
    this.resultCode = paramInt;
    String str;
    if (paramInt == -1000)
      str = "网络未连接";
    while (true)
    {
      if (this.loginCallBack != null)
        this.loginCallBack.LoginFailCallBack(paramInt, str);
      return;
      if (paramInt == 15)
      {
        str = "密码保存超过有效期，请重新输入密码";
        continue;
      }
      str = this.mLoginHelper.GetLastErrMsg().getMessage();
    }
  }

  public void LoginSucess(String paramString, WUserSigInfo paramWUserSigInfo)
  {
    WloginSimpleInfo localWloginSimpleInfo = new WloginSimpleInfo();
    this.mLoginHelper.GetBasicUserInfo(paramString, localWloginSimpleInfo);
    String str = new String(paramWUserSigInfo._sKey);
    if (this.loginCallBack != null)
      this.loginCallBack.LoginSuccCallBack(paramString, str);
  }

  public void checkVerifyCode(String paramString)
  {
    if (paramString.length() > 0)
    {
      WUserSigInfo localWUserSigInfo = new WUserSigInfo();
      this.mLoginHelper.CheckPictureAndGetSt(this.uin, paramString.getBytes(), localWUserSigInfo, 0);
      return;
    }
    this.mLoginHelper.RefreshPictureData(this.uin, 0);
  }

  public void clearLoginData()
  {
    WloginLastLoginInfo localWloginLastLoginInfo = this.mLoginHelper.GetLastLoginInfo();
    this.mLoginHelper.ClearUserLoginData(localWloginLastLoginInfo.mAccount, 518005007L);
  }

  public String getLastUin()
  {
    this.lastUin = this.mLoginHelper.GetLastLoginInfo().mAccount;
    return this.lastUin;
  }

  public int getResultCode()
  {
    return this.resultCode;
  }

  public String getResultMsg()
  {
    return this.resultMsg;
  }

  public byte[] getVerifyCodeImg(String paramString)
  {
    this.mLoginHelper.RefreshPictureData(paramString, 1);
    return this.mLoginHelper.GetPictureData(paramString);
  }

  public boolean isSigValid()
  {
    WloginLastLoginInfo localWloginLastLoginInfo = this.mLoginHelper.GetLastLoginInfo();
    return this.mLoginHelper.GetA1ByAccount(localWloginLastLoginInfo.mAccount, 518005007L) != null;
  }

  public void loginAction(String paramString1, String paramString2)
  {
    if (((paramString1 == null) || (paramString1.length() == 0) || (paramString2 == null) || (paramString2.length() == 0)) && (this.loginCallBack != null))
      this.loginCallBack.LoginFailCallBack(-1, "帐号和密码不能为空");
    this.uin = paramString1;
    WUserSigInfo localWUserSigInfo = new WUserSigInfo();
    WloginLastLoginInfo localWloginLastLoginInfo = this.mLoginHelper.GetLastLoginInfo();
    if (this.mLoginHelper.IsNeedLoginWithPasswd(paramString1, 518005007L).booleanValue())
    {
      localWUserSigInfo._userPasswdSig = this.mLoginHelper.GetA1ByAccount(paramString1, 518005007L);
      if ((localWloginLastLoginInfo != null) && (localWUserSigInfo._userPasswdSig != null))
      {
        this.mLoginHelper.GetStWithPasswd(paramString1, 518005007L, "", localWUserSigInfo, 0);
        return;
      }
      this.mLoginHelper.GetStWithPasswd(paramString1, 518005007L, paramString2, localWUserSigInfo, 0);
      return;
    }
    this.mLoginHelper.GetStWithoutPasswd(paramString1, 518005007L, 518005007L, localWUserSigInfo, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.paylogin.sdk.payLoginSDK
 * JD-Core Version:    0.6.0
 */
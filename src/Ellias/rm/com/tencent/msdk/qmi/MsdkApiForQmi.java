package com.tencent.msdk.qmi;

import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.tools.Logger;

public class MsdkApiForQmi
{
  public static LoginInfo getLoginInfo()
  {
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    LoginInfo localLoginInfo = new LoginInfo(localLoginRet);
    Logger.d(localLoginInfo.toString());
    return localLoginInfo;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.qmi.MsdkApiForQmi
 * JD-Core Version:    0.6.0
 */
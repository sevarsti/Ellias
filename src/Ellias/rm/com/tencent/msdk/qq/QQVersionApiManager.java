package com.tencent.msdk.qq;

import android.content.Context;
import com.tencent.msdk.tools.VersionHelper;

public class QQVersionApiManager
{
  public static final String QQ_PKG_NAME = "com.tencent.mobileqq";

  public static boolean isSupport(Context paramContext, ApiName paramApiName)
  {
    VersionHelper localVersionHelper = new VersionHelper(paramContext, "com.tencent.mobileqq");
    switch (1.$SwitchMap$com$tencent$msdk$qq$ApiName[paramApiName.ordinal()])
    {
    default:
    case 1:
    }
    do
      return true;
    while (localVersionHelper.compareVersion("4.5") > 0);
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.qq.QQVersionApiManager
 * JD-Core Version:    0.6.0
 */
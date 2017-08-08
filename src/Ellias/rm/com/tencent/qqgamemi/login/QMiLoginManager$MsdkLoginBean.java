package com.tencent.qqgamemi.login;

import android.text.TextUtils;

public class QMiLoginManager$MsdkLoginBean
{
  public int a = -1;
  public String b = "";
  public String c = "";
  public String d = "";

  public QMiLoginManager$MsdkLoginBean(QMiLoginManager paramQMiLoginManager)
  {
  }

  public boolean a()
  {
    return (this.b != null) && (!TextUtils.isEmpty(this.c)) && (!TextUtils.isEmpty(this.d));
  }

  public String toString()
  {
    return "msdkLoginType:" + this.a + ",msdkAppID:" + this.b + ",msdkOpenID:" + this.c + ",msdkAcessToken:" + this.d;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.login.QMiLoginManager.MsdkLoginBean
 * JD-Core Version:    0.6.0
 */
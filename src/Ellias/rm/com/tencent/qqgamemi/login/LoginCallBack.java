package com.tencent.qqgamemi.login;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=4)
public abstract interface LoginCallBack
{
  @PluginApi(a=4)
  public abstract void onLoginCancel();

  @PluginApi(a=4)
  public abstract void onLoginClose();

  @PluginApi(a=4)
  public abstract void onLoginSuccess();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.login.LoginCallBack
 * JD-Core Version:    0.6.0
 */
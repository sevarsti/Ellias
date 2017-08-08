package com.pay.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class APDataStorage
{
  public void clearData(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = (SharedPreferences.Editor)paramContext.getSharedPreferences(paramString, 0);
    localEditor.clear();
    localEditor.commit();
  }

  public boolean getAutoLogin(Context paramContext)
  {
    return paramContext.getSharedPreferences("account", 0).getBoolean("AUTOLOGIN", false);
  }

  public String getData(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences(paramString1, 0).getString(paramString2, "");
  }

  public boolean getRememberPwd(Context paramContext)
  {
    return paramContext.getSharedPreferences("account", 0).getBoolean("REMEMBERPWD", true);
  }

  public void setAutoLogin(Context paramContext, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("account", 0).edit();
    localEditor.putBoolean("AUTOLOGIN", paramBoolean);
    localEditor.commit();
  }

  public void setRememberPwd(Context paramContext, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("account", 0).edit();
    localEditor.putBoolean("REMEMBERPWD", paramBoolean);
    localEditor.commit();
  }

  public void storeData(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(paramString1, 0).edit();
    localEditor.putString(paramString2, paramString3);
    localEditor.commit();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APDataStorage
 * JD-Core Version:    0.6.0
 */
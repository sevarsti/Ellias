package com.tencent.smtt.sdk.tips;

import android.content.Context;
import android.net.ConnectivityManager;

public class ContextHolder
{
  private static ConnectivityManager mConnectivityManager;
  private static Context mContext;

  public static ConnectivityManager getConnectivityManager()
  {
    if ((mConnectivityManager == null) && (mContext != null))
      mConnectivityManager = (ConnectivityManager)mContext.getSystemService("connectivity");
    return mConnectivityManager;
  }

  public static Context getContext()
  {
    return mContext;
  }

  public static void setContext(Context paramContext)
  {
    mContext = paramContext;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.tips.ContextHolder
 * JD-Core Version:    0.6.0
 */
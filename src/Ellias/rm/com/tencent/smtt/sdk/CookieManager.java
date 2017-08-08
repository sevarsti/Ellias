package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.WebViewWizardBase;

public class CookieManager
{
  private static CookieManager mInstance;
  private android.webkit.CookieManager sysCookieManager = android.webkit.CookieManager.getInstance();

  public static CookieManager getInstance()
  {
    monitorenter;
    try
    {
      if (mInstance == null)
        mInstance = new CookieManager();
      CookieManager localCookieManager = mInstance;
      return localCookieManager;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean acceptCookie()
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
      return localSDKEngine.wizard().cookieManager_acceptCookie();
    return this.sysCookieManager.acceptCookie();
  }

  public String getCookie(String paramString)
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
      return localSDKEngine.wizard().getCookie(paramString);
    return this.sysCookieManager.getCookie(paramString);
  }

  public boolean hasCookies()
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
      return localSDKEngine.wizard().cookieManager_hasCookies();
    return this.sysCookieManager.hasCookies();
  }

  public void removeAllCookie()
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
    {
      localSDKEngine.wizard().cookieManager_removeAllCookie();
      return;
    }
    this.sysCookieManager.removeAllCookie();
  }

  public void removeExpiredCookie()
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
    {
      localSDKEngine.wizard().cookieManager_removeExpiredCookie();
      return;
    }
    this.sysCookieManager.removeExpiredCookie();
  }

  public void removeSessionCookie()
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
    {
      localSDKEngine.wizard().cookieManager_removeSessionCookie();
      return;
    }
    this.sysCookieManager.removeSessionCookie();
  }

  public void setAcceptCookie(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      SDKEngine localSDKEngine = SDKEngine.getInstance(false);
      if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
        localSDKEngine.wizard().cookieManager_setAcceptCookie(paramBoolean);
      while (true)
      {
        return;
        this.sysCookieManager.setAcceptCookie(paramBoolean);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setCookie(String paramString1, String paramString2)
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
    {
      localSDKEngine.wizard().cookieManager_setCookie(paramString1, paramString2);
      return;
    }
    this.sysCookieManager.setCookie(paramString1, paramString2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.CookieManager
 * JD-Core Version:    0.6.0
 */
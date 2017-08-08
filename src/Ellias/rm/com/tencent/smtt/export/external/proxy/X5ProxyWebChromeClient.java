package com.tencent.smtt.export.external.proxy;

import com.tencent.smtt.export.external.WebViewWizardBase;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;

public abstract class X5ProxyWebChromeClient extends ProxyWebChromeClient
{
  public X5ProxyWebChromeClient(WebViewWizardBase paramWebViewWizardBase)
  {
    this.mWebChromeClient = ((IX5WebChromeClient)paramWebViewWizardBase.newInstance(paramWebViewWizardBase.isDynamicMode(), "com.tencent.smtt.webkit.WebChromeClient"));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.export.external.proxy.X5ProxyWebChromeClient
 * JD-Core Version:    0.6.0
 */
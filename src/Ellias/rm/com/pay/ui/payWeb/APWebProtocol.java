package com.pay.ui.payWeb;

public class APWebProtocol
{
  public static String URLPRE = "https://";
  public static String URLSUFFIX_LANDSCAPE;
  public static String URLSUFFIX_PORTRAIT;
  public static String URL_DOMAIN_RELEASE;
  public static String URL_DOMAIN_SANDBOX = "sandbox.api.unipay.qq.com";
  public static String WEBACTION_PAYGAME_CANCEL;
  public static String WEBACTION_PAYGAME_CLOSE;
  public static String WEBACTION_PAYGAME_PAY;
  public static String WEBACTION_PAYGAME_REPAY;
  public static String WEBPAGE_PAYGAME_INPUT;
  public static String WEBPAGE_PAYGAME_LIST;
  public static String WEBPAGE_PAYGAME_RESULT;
  public static String WEB_ACTION;
  public static String WEB_PAGE;
  public static String WEB_REQFROM;

  static
  {
    URL_DOMAIN_RELEASE = "api.unipay.qq.com";
    URLSUFFIX_PORTRAIT = "mp.html";
    URLSUFFIX_LANDSCAPE = "mp_hor.html";
    WEB_REQFROM = "sdk";
    WEB_PAGE = "page";
    WEBPAGE_PAYGAME_LIST = "list";
    WEBPAGE_PAYGAME_INPUT = "input";
    WEBPAGE_PAYGAME_RESULT = "result";
    WEB_ACTION = "action";
    WEBACTION_PAYGAME_PAY = "pay";
    WEBACTION_PAYGAME_REPAY = "repay";
    WEBACTION_PAYGAME_CANCEL = "cancel";
    WEBACTION_PAYGAME_CLOSE = "close";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payWeb.APWebProtocol
 * JD-Core Version:    0.6.0
 */
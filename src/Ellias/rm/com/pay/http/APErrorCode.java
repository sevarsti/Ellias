package com.pay.http;

public class APErrorCode
{
  public static final int ERROR_APP_REGETKEYERROR = 5001;
  public static final int ERROR_APP_SYSTEM = 2000;
  public static final int ERROR_APP_SYSTEM_KEYNULL = 2001;
  public static final int ERROR_APP_TENPAY = 3000;
  public static final int ERROR_APP_TENPAY_RET3 = 3003;
  public static final int ERROR_APP_TENPAY_RET4 = 3004;
  public static final int ERROR_APP_TENPAY_RET5 = 3005;
  public static final int ERROR_APP_WECHAT = 4000;
  public static final int ERROR_APP_WECHAT_RET = 4001;
  public static final int ERROR_NETWORK_CONTENTNULL = 1004;
  public static final int ERROR_NETWORK_CONTIMEOUT = 1001;
  public static final int ERROR_NETWORK_HTTPSTIMES = 1100;
  public static final int ERROR_NETWORK_JOSNFORMT = 1003;
  public static final int ERROR_NETWORK_READTIMEOUT = 1002;
  public static final int ERROR_NETWORK_SYSTEM = 1000;

  public static String getErrorCode(int paramInt)
  {
    return "(100-100-" + paramInt + ")";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APErrorCode
 * JD-Core Version:    0.6.0
 */
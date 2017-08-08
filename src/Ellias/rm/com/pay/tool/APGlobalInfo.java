package com.pay.tool;

import com.pay.AndroidPay;

public class APGlobalInfo
{
  public static final int APCHANEL_ACCT_QBQD = 11;
  public static final int APCHANEL_ACCT_QDQB = 0;
  public static final int APCHANEL_GOLDCOUPONS = 10;
  public static final int APCHANEL_HF = 9;
  public static final int APCHANEL_MCARD = 5;
  public static final int APCHANEL_NOTQQFAST = 13;
  public static final int APCHANEL_NOTTENPAYBIND = 11;
  public static final int APCHANEL_QQCARD = 4;
  public static final int APCHANEL_QQCARD_AI = 15;
  public static final int APCHANEL_QQCARD_NOTAI = 16;
  public static final int APCHANEL_TENPAYBIND = 12;
  public static final int APCHANEL_TENPAY_BANK = 2;
  public static final int APCHANEL_TENPAY_CFT = 1;
  public static final int APCHANEL_TENPAY_KJ = 3;
  public static final int APCHANEL_TENPAY_NOTSIG = 9;
  public static final int APCHANEL_TENPAY_SIG = 10;
  public static final int APCHANEL_WECHAT = 8;
  public static final int APCHANEL_YB = 7;
  public static final String AP_DESKEY = "zyhr1s5k95fgw53j";
  public static final String CustomEnv = "custom";
  public static final String DevEnv = "dev";
  public static final String ERROR_INFO_SYSTEMERROR = "系统繁忙,请稍后再试";
  public static final String FROM_ANDROIDPAY = "AndroidPay";
  public static final String FROM_BUYINPUTNUM = "APPayGameInputNumActivity";
  public static final String FROM_BUYLISTNUM = "APPayGameListNumActivity";
  public static final String FROM_SAVEACCOUNTINPUTNUM = "APSaveAccountInputNumActivity";
  public static final String FROM_SAVEACCOUNTLISTNUM = "APSaveAccountListNumActivity";
  public static final String FROM_WEBLIST = "APWebBuyActivity";
  public static int QBIMG = 0;
  public static final int RET_ACCOUNTLIMT = 1035;
  public static final int RET_BINDPHONE = 10005;
  public static final int RET_CRYKEYVALID = 1009;
  public static final int RET_HFPAY_UNSUPPORT = 10008;
  public static final int RET_LOGINVALID = 1018;
  public static final int RET_NEEDVC = 10002;
  public static final int RET_NOBALANCE = 1004;
  public static final int RET_OK = 0;
  public static final int RET_PAYSESSIONVALID = 10009;
  public static final int RET_PHONEMB = 10001;
  public static final int RET_PHONEMB_CHECKERROR = 1125;
  public static final int RET_PHONEPWD = 10003;
  public static final int RET_PORTALGETWAY = 1058;
  public static final int RET_QQCARDNOBALANCE = 1014;
  public static final int RET_REQ_CANCELED = -2007;
  public static final int RET_SECKEYERROR = 1094;
  public static final int RET_SECKEYVALID = 1099;
  public static final int RET_SETPHONEPWD = 10004;
  public static final int RET_TOKENINVALID = 1023;
  public static final int RET_VCERROR = 10006;
  public static final String ReleaseEnv = "release";
  public static int SCREENHEIGHT = 0;
  public static final String SDK_VERSION = "1.3.7b";
  public static final String TestEnv = "test";
  public static final String WX_PARTNERID = "1000018901";

  static
  {
    QBIMG = APCommMethod.getDrawableId(AndroidPay.singleton().applicationContext, "unipay_pic_channel_icon3");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APGlobalInfo
 * JD-Core Version:    0.6.0
 */
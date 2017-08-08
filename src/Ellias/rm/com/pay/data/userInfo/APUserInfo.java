package com.pay.data.userInfo;

import com.pay.common.tool.APBigNum;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class APUserInfo
{
  public int accoutBalance = 0;
  public String acctType = "common";
  public String authKey = "";
  public boolean isBindQQ = false;
  public boolean isCFTUser = false;
  public boolean isFirstCharge = false;
  public boolean isKJUser = false;
  public boolean isUinLogin = false;
  public String openId = "";
  public String openKey = "";
  public String payId = "";
  public String pf = "";
  public String pfKey = "";
  public String sessionId = "";
  public String sessionType = "";
  public String uinFromSvr = "";
  public String uinNum = "";
  public String uinType = "";
  public String uinTypeFromSvr = "";
  public String vipType;
  public String zoneId = "";

  public String getUserQBBalance()
  {
    BigDecimal localBigDecimal = APBigNum.divide(this.accoutBalance, 100.0D);
    DecimalFormat localDecimalFormat = new DecimalFormat();
    localDecimalFormat.applyPattern("0.##");
    return localDecimalFormat.format(localBigDecimal);
  }

  public String getUserQDBalance()
  {
    BigDecimal localBigDecimal = APBigNum.divide(this.accoutBalance, 10.0D);
    DecimalFormat localDecimalFormat = new DecimalFormat();
    localDecimalFormat.applyPattern("0.#");
    return localDecimalFormat.format(localBigDecimal);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.userInfo.APUserInfo
 * JD-Core Version:    0.6.0
 */
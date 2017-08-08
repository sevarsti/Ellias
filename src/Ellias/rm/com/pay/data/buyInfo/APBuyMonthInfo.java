package com.pay.data.buyInfo;

import android.text.TextUtils;
import com.pay.common.tool.APTypeChange;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class APBuyMonthInfo extends APBaseBuyInfo
{
  public String autoPay;
  public String payRemark;
  public String productId;
  public String serviceCode;
  public String serviceName;

  public String getCost(String paramString)
  {
    if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
      return this.price;
    if ((TextUtils.isEmpty(this.price)) || (TextUtils.isEmpty(paramString)))
      return null;
    int i = APTypeChange.StringToInt(this.price);
    int j = APTypeChange.StringToInt(paramString);
    BigDecimal localBigDecimal1 = new BigDecimal(i);
    BigDecimal localBigDecimal2 = new BigDecimal(j).multiply(localBigDecimal1).divide(new BigDecimal(100.0D));
    DecimalFormat localDecimalFormat = new DecimalFormat();
    localDecimalFormat.applyPattern("0.##");
    return localDecimalFormat.format(localBigDecimal2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.buyInfo.APBuyMonthInfo
 * JD-Core Version:    0.6.0
 */
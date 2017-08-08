package com.pay.data.buyInfo;

import android.text.TextUtils;
import com.pay.common.tool.APTypeChange;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class APBuyGameInfo extends APBaseBuyInfo
{
  public String mpTips;

  public String getCost(String paramString)
  {
    if ((TextUtils.isEmpty(this.price)) || (TextUtils.isEmpty(paramString)))
      return null;
    int i = APTypeChange.StringToInt(this.price);
    int j = APTypeChange.StringToInt(paramString);
    BigDecimal localBigDecimal1 = new BigDecimal(i);
    BigDecimal localBigDecimal2 = new BigDecimal(j).divide(localBigDecimal1);
    DecimalFormat localDecimalFormat = new DecimalFormat();
    localDecimalFormat.applyPattern("0.##");
    return localDecimalFormat.format(localBigDecimal2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.buyInfo.APBuyGameInfo
 * JD-Core Version:    0.6.0
 */
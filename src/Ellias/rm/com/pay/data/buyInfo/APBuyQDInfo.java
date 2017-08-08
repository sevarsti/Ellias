package com.pay.data.buyInfo;

import android.text.TextUtils;
import com.pay.common.tool.APTypeChange;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class APBuyQDInfo extends APBaseBuyInfo
{
  public String getCost(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    int i = APTypeChange.StringToInt(paramString);
    BigDecimal localBigDecimal1 = new BigDecimal(10.0D);
    BigDecimal localBigDecimal2 = new BigDecimal(i).divide(localBigDecimal1);
    DecimalFormat localDecimalFormat = new DecimalFormat();
    localDecimalFormat.applyPattern("0.##");
    return localDecimalFormat.format(localBigDecimal2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.buyInfo.APBuyQDInfo
 * JD-Core Version:    0.6.0
 */
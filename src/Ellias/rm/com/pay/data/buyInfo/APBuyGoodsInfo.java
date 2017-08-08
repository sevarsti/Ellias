package com.pay.data.buyInfo;

import android.text.TextUtils;
import com.pay.common.tool.APTypeChange;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class APBuyGoodsInfo extends APBaseBuyInfo
{
  public String buyGoodsUrl = "";
  public String goodsSaveUrl = "";
  public String tokenUrl = "";

  public String getCost(String paramString)
  {
    if (!TextUtils.isEmpty(this.disPrice));
    for (String str = this.disPrice; (TextUtils.isEmpty(str)) || (TextUtils.isEmpty(paramString)); str = this.price)
      return null;
    int i = APTypeChange.StringToInt(str);
    int j = APTypeChange.StringToInt(paramString);
    BigDecimal localBigDecimal1 = new BigDecimal(i);
    BigDecimal localBigDecimal2 = new BigDecimal(j).multiply(localBigDecimal1).divide(new BigDecimal(100.0D));
    DecimalFormat localDecimalFormat = new DecimalFormat();
    localDecimalFormat.applyPattern("0.##");
    return localDecimalFormat.format(localBigDecimal2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.buyInfo.APBuyGoodsInfo
 * JD-Core Version:    0.6.0
 */
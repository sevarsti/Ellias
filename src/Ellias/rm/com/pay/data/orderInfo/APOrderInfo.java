package com.pay.data.orderInfo;

import android.text.TextUtils;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.buyInfo.APBuyGameInfo;
import com.pay.data.buyInfo.APBuyGoodsInfo;
import com.pay.data.buyInfo.APBuyMonthInfo;
import com.pay.data.buyInfo.APBuyQBInfo;
import com.pay.data.buyInfo.APBuyQDInfo;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class APOrderInfo
  implements Cloneable
{
  public String assignChannel;
  public APBaseBuyInfo buyInfo;
  public String exSaveAcctNum;
  public String expressChannel;
  public boolean isNumCanChange;
  public String orderId;
  public int originalSaveType;
  public int payChannel;
  public String saveNum;
  public int saveType;
  public String sessionToken;
  public String succSaveNum;
  public String tokenId;
  public int transStats = -1;

  public APOrderInfo(int paramInt)
  {
    this.originalSaveType = paramInt;
    this.saveType = paramInt;
    this.saveNum = "";
    this.expressChannel = "";
    this.assignChannel = "";
    this.tokenId = "";
    this.sessionToken = "";
    this.isNumCanChange = true;
    switch (paramInt)
    {
    default:
      return;
    case 0:
      this.buyInfo = new APBuyGameInfo();
      return;
    case 1:
      this.buyInfo = new APBuyGoodsInfo();
      return;
    case 4:
    case 5:
      this.buyInfo = new APBuyMonthInfo();
      return;
    case 3:
      this.buyInfo = new APBuyQBInfo();
      return;
    case 2:
    }
    this.buyInfo = new APBuyQDInfo();
  }

  public Object clone()
  {
    ((APOrderInfo)super.clone()).buyInfo.clone();
    return super.clone();
  }

  public String getCost()
  {
    if (this.buyInfo != null)
      return this.buyInfo.getCost(this.saveNum);
    return null;
  }

  public String getCost(int paramInt)
  {
    if (this.buyInfo != null)
      return this.buyInfo.getCost(String.valueOf(paramInt));
    return null;
  }

  public String getCost(String paramString)
  {
    if (this.buyInfo != null)
      return this.buyInfo.getCost(paramString);
    return null;
  }

  public String getCostWithPoint(int paramInt)
  {
    String str;
    if (this.buyInfo != null)
    {
      str = this.buyInfo.getCost(this.saveNum);
      if (!TextUtils.isEmpty(str));
    }
    else
    {
      return null;
    }
    BigDecimal localBigDecimal = new BigDecimal(str);
    DecimalFormat localDecimalFormat = new DecimalFormat();
    if (paramInt == 2)
      localDecimalFormat.applyPattern("0.00");
    while (true)
    {
      return localDecimalFormat.format(localBigDecimal);
      if (paramInt == 1)
      {
        localDecimalFormat.applyPattern("0.0");
        continue;
      }
      localDecimalFormat.applyPattern("0.##");
    }
  }

  public String getQDCost()
  {
    return getQDCost(this.saveNum);
  }

  public String getQDCost(String paramString)
  {
    String str;
    if (this.buyInfo != null)
    {
      str = this.buyInfo.getCost(paramString);
      if (!TextUtils.isEmpty(str));
    }
    else
    {
      return null;
    }
    BigDecimal localBigDecimal = new BigDecimal(str).multiply(new BigDecimal(10.0D));
    DecimalFormat localDecimalFormat = new DecimalFormat();
    localDecimalFormat.applyPattern("0.##");
    return localDecimalFormat.format(localBigDecimal);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.orderInfo.APOrderInfo
 * JD-Core Version:    0.6.0
 */
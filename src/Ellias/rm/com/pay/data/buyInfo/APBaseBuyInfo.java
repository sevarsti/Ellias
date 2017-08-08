package com.pay.data.buyInfo;

public abstract class APBaseBuyInfo
  implements Cloneable
{
  public String disPrice = "";
  public byte[] logo;
  public int maxNum;
  public int minNum = 0;
  public String name = "";
  public String offerName = "";
  public String price = "";
  public String unit = "";

  public Object clone()
  {
    return super.clone();
  }

  public abstract String getCost(String paramString);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.buyInfo.APBaseBuyInfo
 * JD-Core Version:    0.6.0
 */
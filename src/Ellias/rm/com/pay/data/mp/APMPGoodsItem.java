package com.pay.data.mp;

import android.text.TextUtils;

public class APMPGoodsItem
{
  public String name;
  public String num;
  public String url;

  public boolean getIsHasSend()
  {
    return !TextUtils.isEmpty(this.name);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.mp.APMPGoodsItem
 * JD-Core Version:    0.6.0
 */
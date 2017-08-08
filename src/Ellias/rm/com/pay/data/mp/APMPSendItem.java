package com.pay.data.mp;

import android.text.TextUtils;
import java.util.ArrayList;

public class APMPSendItem
{
  private boolean a = true;
  public APMPGamesItem sendGames;
  public ArrayList sendGoodsList = null;

  public void clear()
  {
    this.sendGames = null;
    this.sendGoodsList = null;
  }

  public boolean getIsHasGoodsPic()
  {
    int i;
    if (this.sendGoodsList != null)
    {
      i = 0;
      if (i < this.sendGoodsList.size());
    }
    while (true)
    {
      return this.a;
      if (!TextUtils.isEmpty(((APMPGoodsItem)this.sendGoodsList.get(i)).url))
      {
        i++;
        break;
      }
      this.a = false;
    }
  }

  public boolean getIsHasSend()
  {
    if (this.sendGames != null);
    for (boolean bool = this.sendGames.getIsHasSend(); ; bool = false)
    {
      if (this.sendGoodsList != null);
      for (int j = 0; ; j++)
      {
        if (j >= this.sendGoodsList.size());
        for (int i = 0; ; i = 1)
        {
          if ((bool) || (i != 0))
            break label75;
          return false;
          if (!((APMPGoodsItem)this.sendGoodsList.get(j)).getIsHasSend())
            break;
        }
      }
      label75: return true;
    }
  }

  public boolean getIsHasSendGoods()
  {
    return (this.sendGoodsList != null) && (this.sendGoodsList.size() > 0);
  }

  public int getRealSendGamesNum(int paramInt)
  {
    if (this.sendGames != null)
      return this.sendGames.getRealSendGamesNum(paramInt);
    return 0;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.mp.APMPSendItem
 * JD-Core Version:    0.6.0
 */
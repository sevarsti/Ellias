package com.pay.data.mp;

public class APMPGamesItem
{
  public int limitNum = 0;
  public int realSendNum = 0;
  public String sendExt;
  public int sendNum = 0;
  public int sendRate = 0;
  public String sendType;

  public boolean getIsHasSend()
  {
    if (("1".equals(this.sendType)) && (this.sendRate > 0));
    do
      return true;
    while (("2".equals(this.sendType)) && (this.sendNum > 0));
    return false;
  }

  public int getRealSendGamesNum(int paramInt)
  {
    if ("1".equals(this.sendType))
      if (paramInt * this.sendRate % 100 != 0)
        this.realSendNum = (1 + paramInt * this.sendRate / 100);
    while (true)
    {
      return this.realSendNum;
      this.realSendNum = (paramInt * this.sendRate / 100);
      continue;
      if ("2".equals(this.sendType))
      {
        if (paramInt >= this.limitNum)
        {
          this.realSendNum = this.sendNum;
          continue;
        }
        this.realSendNum = 0;
        continue;
      }
      this.realSendNum = 0;
    }
  }

  public int getSendGamesNum()
  {
    if ("1".equals(this.sendType))
      if (this.sendRate * this.limitNum % 100 != 0)
        this.realSendNum = (1 + this.sendRate * this.limitNum / 100);
    while (true)
    {
      return this.realSendNum;
      this.realSendNum = (this.sendRate * this.limitNum / 100);
      continue;
      if ("2".equals(this.sendType))
      {
        this.realSendNum = this.sendNum;
        continue;
      }
      this.realSendNum = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.mp.APMPGamesItem
 * JD-Core Version:    0.6.0
 */
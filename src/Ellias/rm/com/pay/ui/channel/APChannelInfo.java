package com.pay.ui.channel;

public class APChannelInfo
{
  public String channelDiscount = "100";
  public String channelId = "";
  public String channelInfo = "";
  public String channelLogo = "";
  public String channelName = "";
  public String channelRemark = "";
  public int channelState = 0;
  public boolean isRecommand = false;

  public boolean equals(Object paramObject)
  {
    APChannelInfo localAPChannelInfo = (APChannelInfo)paramObject;
    return this.channelId.equals(localAPChannelInfo.channelId);
  }

  public int hashCode()
  {
    if (this.channelId == null)
      return 0;
    return this.channelId.hashCode();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.APChannelInfo
 * JD-Core Version:    0.6.0
 */
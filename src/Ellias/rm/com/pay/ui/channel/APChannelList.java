package com.pay.ui.channel;

import java.util.ArrayList;
import java.util.List;

public class APChannelList
{
  private static APChannelList h;
  private ArrayList a = new ArrayList();
  private ArrayList b = new ArrayList();
  private ArrayList c = new ArrayList();
  private ArrayList d = new ArrayList();
  private ArrayList e = new ArrayList();
  private String[] f = { "cft,财付通余额,unipay_pic_channel_icon2,财付通支付", "qbqd,Q币,unipay_pic_channel_icon3,Q币支付", "qdqb,Q点,unipay_pic_channel_icon3,Q点支付", "bank,银行卡快捷支付,unipay_pic_channel_icon1,银行卡支付", "mcard,手机充值卡,unipay_pic_channel_icon5,立即支付", "hfpay,手机话费,unipay_pic_channel_icon6,立即支付", "qqcard,QQ卡,unipay_pic_channel_icon7,立即支付", "yb,元宝,unipay_pic_channel_icon8,元宝支付", "wechat,微信支付,unipay_pic_channel_icon9,微信支付", "gold_coupons,金券,unipay_pic_channel_icon10,金券支付" };
  private String g = "";

  private APChannelList()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.f.length)
        return;
      String str1 = this.f[i].split(",")[0];
      String str2 = this.f[i].split(",")[1];
      String str3 = this.f[i].split(",")[2];
      String str4 = this.f[i].split(",")[3];
      APChannelInfo localAPChannelInfo = new APChannelInfo();
      localAPChannelInfo.channelId = str1;
      localAPChannelInfo.channelName = str2;
      localAPChannelInfo.channelLogo = str3;
      localAPChannelInfo.channelRemark = str4;
      this.e.add(localAPChannelInfo);
    }
  }

  private static String a(List paramList, String paramString)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramList.size());
      APChannelInfo localAPChannelInfo;
      for (String str = "100"; ; str = localAPChannelInfo.channelDiscount)
      {
        if (str.length() >= 3)
          break label65;
        return str;
        localAPChannelInfo = (APChannelInfo)paramList.get(i);
        if (!localAPChannelInfo.channelId.equals(paramString))
          break;
      }
    }
    label65: return "";
  }

  private void a(ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayList2.size())
        return;
      APChannelInfo localAPChannelInfo = (APChannelInfo)paramArrayList2.get(i);
      if (!this.e.contains(localAPChannelInfo))
        continue;
      String str = localAPChannelInfo.channelName;
      if ((str == null) || (str.equals("")))
      {
        str = b(this.e, localAPChannelInfo.channelId);
        localAPChannelInfo.channelName = str;
      }
      localAPChannelInfo.channelLogo = c(this.e, localAPChannelInfo.channelId);
      if ((paramArrayList1.contains(localAPChannelInfo)) || (str.length() <= 0))
        continue;
      paramArrayList1.add(localAPChannelInfo);
    }
  }

  private static String b(List paramList, String paramString)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramList.size())
        return "";
      APChannelInfo localAPChannelInfo = (APChannelInfo)paramList.get(i);
      if (paramString.equals(localAPChannelInfo.channelId))
        return localAPChannelInfo.channelName;
    }
  }

  private static String c(List paramList, String paramString)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramList.size())
        return "";
      APChannelInfo localAPChannelInfo = (APChannelInfo)paramList.get(i);
      if (paramString.equals(localAPChannelInfo.channelId))
        return localAPChannelInfo.channelLogo;
    }
  }

  public static void release()
  {
    h = null;
  }

  public static APChannelList singleton()
  {
    if (h == null)
      h = new APChannelList();
    return h;
  }

  public void clearAllChannelList()
  {
    this.a.clear();
  }

  public void clearMoreAccoutChannelList()
  {
    this.d.clear();
  }

  public void clearMoreCommChannelList()
  {
    this.c.clear();
  }

  public void clearShowCommChannelList()
  {
    this.b.clear();
  }

  public List getAcctChannelList()
  {
    this.a.clear();
    a(this.a, this.b);
    if (this.d.size() > 0)
      a(this.a, this.d);
    while (true)
    {
      return this.a;
      for (int i = 0; i < this.c.size(); i++)
      {
        APChannelInfo localAPChannelInfo = (APChannelInfo)this.c.get(i);
        if ((!this.e.contains(localAPChannelInfo)) || (localAPChannelInfo.channelId.equals("qdqb")) || (localAPChannelInfo.channelId.equals("qbqd")) || (localAPChannelInfo.channelId.equals("yb")) || (localAPChannelInfo.channelId.equals("hfpay")) || (localAPChannelInfo.channelId.equals("gold_coupons")))
          continue;
        String str = localAPChannelInfo.channelName;
        if ((str == null) || (str.equals("")))
        {
          str = b(this.e, localAPChannelInfo.channelId);
          localAPChannelInfo.channelName = str;
        }
        localAPChannelInfo.channelLogo = c(this.e, localAPChannelInfo.channelId);
        if ((this.a.contains(localAPChannelInfo)) || (str.length() <= 0))
          continue;
        this.a.add(localAPChannelInfo);
      }
    }
  }

  public String getAcctChannelName(String paramString)
  {
    getAcctChannelList();
    return b(this.a, paramString);
  }

  public String getAcctDiscount(String paramString)
  {
    getAcctChannelList();
    return a(this.a, paramString);
  }

  public List getCommChannelList()
  {
    this.a.clear();
    a(this.a, this.b);
    a(this.a, this.c);
    return this.a;
  }

  public String getCommChannelName(String paramString)
  {
    getCommChannelList();
    return b(this.a, paramString);
  }

  public String getCommDiscount(String paramString)
  {
    getCommChannelList();
    return a(this.a, paramString);
  }

  public int getCurrentAcctChannelId()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.a.size());
      APChannelInfo localAPChannelInfo;
      do
      {
        return 0;
        localAPChannelInfo = (APChannelInfo)this.a.get(i);
      }
      while (localAPChannelInfo.channelId.equals("qdqb"));
      if (localAPChannelInfo.channelId.equals("qbqd"))
        return 11;
    }
  }

  public String getCurrentAcctName()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.a.size())
        return "";
      APChannelInfo localAPChannelInfo = (APChannelInfo)this.a.get(i);
      if ((localAPChannelInfo.channelId.equals("qdqb")) || (localAPChannelInfo.channelId.equals("qbqd")))
        return localAPChannelInfo.channelName;
    }
  }

  public String getDefaultChannel()
  {
    return h.g;
  }

  public void setDefaultChannel(String paramString)
  {
    h.g = paramString;
  }

  public void setMoreAccoutChannelList(ArrayList paramArrayList)
  {
    this.d.clear();
    a(this.d, paramArrayList);
  }

  public void setMoreCommChannelList(ArrayList paramArrayList)
  {
    this.c.clear();
    a(this.c, paramArrayList);
  }

  public void setShowCommChannelList(ArrayList paramArrayList)
  {
    this.b.clear();
    a(this.b, paramArrayList);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.APChannelList
 * JD-Core Version:    0.6.0
 */
package com.tencent.android.tpush.horse.data;

import android.text.format.Time;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OptStrategyList
  implements Serializable
{
  private static final long serialVersionUID = 4532907276158395575L;
  private StrategyItem httpItem;
  private StrategyItem httpRedirectItem;
  private StrategyItem tcpItem;
  private StrategyItem tcpRedirectItem;
  private long timestamp;

  public StrategyItem a()
  {
    return this.tcpRedirectItem;
  }

  public List a(short paramShort)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramShort == 1)
    {
      if (this.httpRedirectItem != null)
        localArrayList.add(this.httpRedirectItem);
      if (this.httpItem != null)
        localArrayList.add(this.httpItem);
    }
    do
    {
      return localArrayList;
      if (this.tcpRedirectItem == null)
        continue;
      localArrayList.add(this.tcpRedirectItem);
    }
    while (this.tcpItem == null);
    localArrayList.add(this.tcpItem);
    return localArrayList;
  }

  public void a(long paramLong)
  {
    this.timestamp = paramLong;
  }

  public void a(StrategyItem paramStrategyItem)
  {
    this.tcpRedirectItem = paramStrategyItem;
  }

  public StrategyItem b()
  {
    return this.tcpItem;
  }

  public void b(StrategyItem paramStrategyItem)
  {
    this.tcpItem = paramStrategyItem;
  }

  public StrategyItem c()
  {
    return this.httpRedirectItem;
  }

  public void c(StrategyItem paramStrategyItem)
  {
    this.httpRedirectItem = paramStrategyItem;
  }

  public StrategyItem d()
  {
    return this.httpItem;
  }

  public void d(StrategyItem paramStrategyItem)
  {
    this.httpItem = paramStrategyItem;
  }

  public StrategyItem e()
  {
    if (this.tcpRedirectItem != null)
      return this.tcpRedirectItem;
    if (this.tcpItem != null)
      return this.tcpItem;
    if (this.httpRedirectItem != null)
      return this.httpRedirectItem;
    if (this.httpItem != null)
      return this.httpItem;
    throw new NullReturnException("getOptStrategyItem return null,because the optstragegylist is empty");
  }

  public List f()
  {
    ArrayList localArrayList = new ArrayList();
    if (this.tcpRedirectItem != null)
      localArrayList.add(this.tcpRedirectItem);
    if (this.tcpItem != null)
      localArrayList.add(this.tcpItem);
    if (this.httpRedirectItem != null)
      localArrayList.add(this.httpRedirectItem);
    if (this.httpItem != null)
      localArrayList.add(this.httpItem);
    return localArrayList;
  }

  public long g()
  {
    return this.timestamp;
  }

  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("\n------list start-----\n");
    StringBuilder localStringBuilder2 = new StringBuilder().append("[TcpRedirectStrategyItem]:");
    String str1;
    String str2;
    label83: String str3;
    label128: StringBuilder localStringBuilder5;
    if (this.tcpRedirectItem == null)
    {
      str1 = " tcpRedirect item is null";
      localStringBuilder1.append(str1 + "\n");
      StringBuilder localStringBuilder3 = new StringBuilder().append("[TCPStrategyItem]:");
      if (this.tcpItem != null)
        break label268;
      str2 = " tcp item is null";
      localStringBuilder1.append(str2 + "\n");
      StringBuilder localStringBuilder4 = new StringBuilder().append("[HttpRedirectStrategyItem]:");
      if (this.httpRedirectItem != null)
        break label280;
      str3 = " httpRedirect item is null";
      localStringBuilder1.append(str3 + "\n");
      localStringBuilder5 = new StringBuilder().append("[HttpStrategyItem]:");
      if (this.httpItem != null)
        break label292;
    }
    label268: label280: label292: for (String str4 = " http item is null"; ; str4 = this.httpItem.toString())
    {
      localStringBuilder1.append(str4 + "\n");
      Time localTime = new Time();
      localTime.set(this.timestamp);
      localStringBuilder1.append(">>>saveTime=" + localTime.format2445() + ">>>\n");
      localStringBuilder1.append("------list end-----\n");
      return localStringBuilder1.toString();
      str1 = this.tcpRedirectItem.toString();
      break;
      str2 = this.tcpItem.toString();
      break label83;
      str3 = this.httpRedirectItem.toString();
      break label128;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.data.OptStrategyList
 * JD-Core Version:    0.6.0
 */
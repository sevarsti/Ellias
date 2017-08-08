package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class ApList extends JceStruct
  implements Cloneable
{
  static ArrayList cache_portList;
  static Map cache_primary;
  static Map cache_secondary;
  static ArrayList cache_speedTestIpList;
  public long backup = 0L;
  public String domain = "";
  public ArrayList portList = null;
  public Map primary = null;
  public Map secondary = null;
  public ArrayList speedTestIpList = null;

  static
  {
    if (!ApList.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public ApList()
  {
  }

  public ApList(Map paramMap1, Map paramMap2, long paramLong, String paramString, ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    this.primary = paramMap1;
    this.secondary = paramMap2;
    this.backup = paramLong;
    this.domain = paramString;
    this.portList = paramArrayList1;
    this.speedTestIpList = paramArrayList2;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.ApList";
  }

  public Object clone()
  {
    try
    {
      Object localObject2 = super.clone();
      localObject1 = localObject2;
      return localObject1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      boolean bool;
      do
      {
        bool = $assertionsDisabled;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.primary, "primary");
    localJceDisplayer.display(this.secondary, "secondary");
    localJceDisplayer.display(this.backup, "backup");
    localJceDisplayer.display(this.domain, "domain");
    localJceDisplayer.display(this.portList, "portList");
    localJceDisplayer.display(this.speedTestIpList, "speedTestIpList");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.primary, true);
    localJceDisplayer.displaySimple(this.secondary, true);
    localJceDisplayer.displaySimple(this.backup, true);
    localJceDisplayer.displaySimple(this.domain, true);
    localJceDisplayer.displaySimple(this.portList, true);
    localJceDisplayer.displaySimple(this.speedTestIpList, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    ApList localApList;
    do
    {
      return false;
      localApList = (ApList)paramObject;
    }
    while ((!JceUtil.equals(this.primary, localApList.primary)) || (!JceUtil.equals(this.secondary, localApList.secondary)) || (!JceUtil.equals(this.backup, localApList.backup)) || (!JceUtil.equals(this.domain, localApList.domain)) || (!JceUtil.equals(this.portList, localApList.portList)) || (!JceUtil.equals(this.speedTestIpList, localApList.speedTestIpList)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.ApList";
  }

  public long getBackup()
  {
    return this.backup;
  }

  public String getDomain()
  {
    return this.domain;
  }

  public ArrayList getPortList()
  {
    return this.portList;
  }

  public Map getPrimary()
  {
    return this.primary;
  }

  public Map getSecondary()
  {
    return this.secondary;
  }

  public ArrayList getSpeedTestIpList()
  {
    return this.speedTestIpList;
  }

  public int hashCode()
  {
    try
    {
      throw new Exception("Need define key first!");
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public void readFrom(JceInputStream paramJceInputStream)
  {
    if (cache_primary == null)
    {
      cache_primary = new HashMap();
      Byte localByte2 = Byte.valueOf(0);
      Long localLong3 = Long.valueOf(0L);
      cache_primary.put(localByte2, localLong3);
    }
    this.primary = ((Map)paramJceInputStream.read(cache_primary, 0, true));
    if (cache_secondary == null)
    {
      cache_secondary = new HashMap();
      Byte localByte1 = Byte.valueOf(0);
      Long localLong2 = Long.valueOf(0L);
      cache_secondary.put(localByte1, localLong2);
    }
    this.secondary = ((Map)paramJceInputStream.read(cache_secondary, 1, true));
    this.backup = paramJceInputStream.read(this.backup, 2, true);
    this.domain = paramJceInputStream.readString(3, true);
    if (cache_portList == null)
    {
      cache_portList = new ArrayList();
      Integer localInteger = Integer.valueOf(0);
      cache_portList.add(localInteger);
    }
    this.portList = ((ArrayList)paramJceInputStream.read(cache_portList, 4, true));
    if (cache_speedTestIpList == null)
    {
      cache_speedTestIpList = new ArrayList();
      Long localLong1 = Long.valueOf(0L);
      cache_speedTestIpList.add(localLong1);
    }
    this.speedTestIpList = ((ArrayList)paramJceInputStream.read(cache_speedTestIpList, 5, true));
  }

  public void setBackup(long paramLong)
  {
    this.backup = paramLong;
  }

  public void setDomain(String paramString)
  {
    this.domain = paramString;
  }

  public void setPortList(ArrayList paramArrayList)
  {
    this.portList = paramArrayList;
  }

  public void setPrimary(Map paramMap)
  {
    this.primary = paramMap;
  }

  public void setSecondary(Map paramMap)
  {
    this.secondary = paramMap;
  }

  public void setSpeedTestIpList(ArrayList paramArrayList)
  {
    this.speedTestIpList = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.primary, 0);
    paramJceOutputStream.write(this.secondary, 1);
    paramJceOutputStream.write(this.backup, 2);
    paramJceOutputStream.write(this.domain, 3);
    paramJceOutputStream.write(this.portList, 4);
    paramJceOutputStream.write(this.speedTestIpList, 5);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.ApList
 * JD-Core Version:    0.6.0
 */
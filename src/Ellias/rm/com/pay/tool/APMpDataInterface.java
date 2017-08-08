package com.pay.tool;

import java.util.TreeMap;

public class APMpDataInterface
{
  private static APMpDataInterface d = null;
  private int a;
  private TreeMap b = new TreeMap(new c(this, 0));
  private String c = "";

  public static APMpDataInterface getIntanceMpDataInterface()
  {
    if (d == null)
      d = new APMpDataInterface();
    return d;
  }

  public static void release()
  {
    d = null;
  }

  public void clear()
  {
    this.b.clear();
    this.c = "";
    this.a = 0;
  }

  public int getFirstMpInfo()
  {
    return this.a;
  }

  public String getGiveMp()
  {
    return this.c;
  }

  public TreeMap getMpInfoMap()
  {
    return this.b;
  }

  public void setFirstMpInfo(int paramInt)
  {
    this.a = paramInt;
  }

  public void setGiveMp(String paramString)
  {
    this.c = paramString;
  }

  public void setMpInfoMap(TreeMap paramTreeMap)
  {
    this.b = paramTreeMap;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APMpDataInterface
 * JD-Core Version:    0.6.0
 */
package com.pay.tool;

import com.pay.data.orderInfo.APOrderInfo;

public class APMonthDataInterface
{
  private static APMonthDataInterface m = null;
  private String a = "";
  private String b = "";
  private String c = "";
  private String d = "";
  private String e = "";
  private String f = "";
  private boolean g = false;
  private String h = "";
  private String i = "1";
  private String j = "";
  private String k = "";
  private APMonthDataInterface.MonthOpenType l = APMonthDataInterface.MonthOpenType.OpenType_Rate;

  public static void release()
  {
    m = null;
  }

  public static APMonthDataInterface singleton()
  {
    if (m == null)
      m = new APMonthDataInterface();
    return m;
  }

  public String getAutoPay()
  {
    return m.i;
  }

  public boolean getIsOpened()
  {
    return m.g;
  }

  public APMonthDataInterface.MonthOpenType getOpenType()
  {
    return this.l;
  }

  public String getPayRemark()
  {
    return m.j;
  }

  public String getServiceCode()
  {
    return m.h;
  }

  public String getToUpGrade()
  {
    return m.f;
  }

  public String getUnit()
  {
    if ((APDataInterface.singleton().getOrderInfo().saveType == 4) && (singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_Rate))
      return "个月";
    return this.k;
  }

  public String getUpGradeAvailable()
  {
    return m.e;
  }

  public String getUpGradeMaxNum()
  {
    return m.a;
  }

  public String getUpGradePrice()
  {
    return m.b;
  }

  public String getUpGradeServiceCode()
  {
    return m.c;
  }

  public String getUpGradeServiceName()
  {
    return m.d;
  }

  public void setAutoPay(String paramString)
  {
    m.i = paramString;
  }

  public void setIsOpened(boolean paramBoolean)
  {
    m.g = paramBoolean;
  }

  public void setOpenType(APMonthDataInterface.MonthOpenType paramMonthOpenType)
  {
    this.l = paramMonthOpenType;
  }

  public void setPayRemark(String paramString)
  {
    m.j = paramString;
  }

  public void setServiceCode(String paramString)
  {
    m.h = paramString;
  }

  public void setToUpGrade(String paramString)
  {
    m.f = paramString;
  }

  public void setUnit(String paramString)
  {
    this.k = paramString;
  }

  public void setUpGradeAvailable(String paramString)
  {
    m.e = paramString;
  }

  public void setUpGradeMaxNum(String paramString)
  {
    m.a = paramString;
  }

  public void setUpGradePrice(String paramString)
  {
    m.b = paramString;
  }

  public void setUpGradeServiceCode(String paramString)
  {
    m.c = paramString;
  }

  public void setUpGradeServiceName(String paramString)
  {
    m.d = paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APMonthDataInterface
 * JD-Core Version:    0.6.0
 */
package com.tencent.msdk.consts;

public enum EPlatform
{
  int value = 0;

  static
  {
    ePlatform_QQ = new EPlatform("ePlatform_QQ", 2, 2);
    ePlatform_QQHall = new EPlatform("ePlatform_QQHall", 3, 4);
    EPlatform[] arrayOfEPlatform = new EPlatform[4];
    arrayOfEPlatform[0] = ePlatform_None;
    arrayOfEPlatform[1] = ePlatform_Weixin;
    arrayOfEPlatform[2] = ePlatform_QQ;
    arrayOfEPlatform[3] = ePlatform_QQHall;
    $VALUES = arrayOfEPlatform;
  }

  private EPlatform(int paramInt)
  {
    this.value = paramInt;
  }

  public static EPlatform getEnum(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return ePlatform_QQ;
    case 0:
      return ePlatform_None;
    case 1:
    }
    return ePlatform_Weixin;
  }

  public static EPlatform str2EPlat(String paramString)
  {
    if ("qq_m".equals(paramString))
      return ePlatform_QQ;
    if ("mobile".equals(paramString))
      return ePlatform_QQHall;
    if ("wechat".equals(paramString))
      return ePlatform_Weixin;
    return ePlatform_None;
  }

  public int val()
  {
    return this.value;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.consts.EPlatform
 * JD-Core Version:    0.6.0
 */
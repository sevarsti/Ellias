package com.tencent.msdk.qq;

public enum ApiName
{
  public static int MAX_QQ_API;
  public static int MAX_WX_API;
  private int val;

  static
  {
    ApiName[] arrayOfApiName = new ApiName[1];
    arrayOfApiName[0] = WGSendToQQWithPhoto;
    $VALUES = arrayOfApiName;
    MAX_QQ_API = 10000;
    MAX_WX_API = 20000;
  }

  private ApiName(int paramInt)
  {
    this.val = paramInt;
  }

  public static ApiName getEnum(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 0:
    }
    return WGSendToQQWithPhoto;
  }

  public int val()
  {
    return this.val;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.qq.ApiName
 * JD-Core Version:    0.6.0
 */
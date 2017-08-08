package com.tencent.msdk.api;

public enum eWechatScene
{
  int value = 0;

  static
  {
    eWechatScene[] arrayOfeWechatScene = new eWechatScene[2];
    arrayOfeWechatScene[0] = WechatScene_Session;
    arrayOfeWechatScene[1] = WechatScene_Timeline;
    $VALUES = arrayOfeWechatScene;
  }

  private eWechatScene(int paramInt)
  {
    this.value = paramInt;
  }

  public static eWechatScene getEnum(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 0:
      return WechatScene_Session;
    case 1:
    }
    return WechatScene_Timeline;
  }

  public int val()
  {
    return this.value;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.api.eWechatScene
 * JD-Core Version:    0.6.0
 */
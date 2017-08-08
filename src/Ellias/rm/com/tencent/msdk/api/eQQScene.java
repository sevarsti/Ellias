package com.tencent.msdk.api;

public enum eQQScene
{
  int value = 0;

  static
  {
    eQQScene[] arrayOfeQQScene = new eQQScene[3];
    arrayOfeQQScene[0] = QQScene_None;
    arrayOfeQQScene[1] = QQScene_QZone;
    arrayOfeQQScene[2] = QQScene_Session;
    $VALUES = arrayOfeQQScene;
  }

  private eQQScene(int paramInt)
  {
    this.value = paramInt;
  }

  public static eQQScene getEnum(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 0:
      return QQScene_None;
    case 1:
      return QQScene_QZone;
    case 2:
    }
    return QQScene_Session;
  }

  public int val()
  {
    return this.value;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.api.eQQScene
 * JD-Core Version:    0.6.0
 */
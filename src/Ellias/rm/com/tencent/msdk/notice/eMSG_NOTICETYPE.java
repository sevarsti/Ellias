package com.tencent.msdk.notice;

import com.tencent.msdk.tools.Logger;

public enum eMSG_NOTICETYPE
{
  int value = 0;

  static
  {
    eMSG_NOTICETYPE_ALL = new eMSG_NOTICETYPE("eMSG_NOTICETYPE_ALL", 2, 2);
    eMSG_NOTICETYPE[] arrayOfeMSG_NOTICETYPE = new eMSG_NOTICETYPE[3];
    arrayOfeMSG_NOTICETYPE[0] = eMSG_NOTICETYPE_ALERT;
    arrayOfeMSG_NOTICETYPE[1] = eMSG_NOTICETYPE_SCROLL;
    arrayOfeMSG_NOTICETYPE[2] = eMSG_NOTICETYPE_ALL;
    $VALUES = arrayOfeMSG_NOTICETYPE;
  }

  private eMSG_NOTICETYPE(int paramInt)
  {
    this.value = paramInt;
  }

  public static boolean checkIsValidType(eMSG_NOTICETYPE parameMSG_NOTICETYPE)
  {
    if (parameMSG_NOTICETYPE == null);
    do
      return false;
    while ((eMSG_NOTICETYPE_ALERT != parameMSG_NOTICETYPE) && (eMSG_NOTICETYPE_SCROLL != parameMSG_NOTICETYPE) && (eMSG_NOTICETYPE_ALL != parameMSG_NOTICETYPE));
    return true;
  }

  public static eMSG_NOTICETYPE getEnum(int paramInt)
  {
    switch (paramInt)
    {
    default:
      Logger.e("bad notice type:" + paramInt);
      return eMSG_NOTICETYPE_ALERT;
    case 0:
      return eMSG_NOTICETYPE_ALERT;
    case 1:
      return eMSG_NOTICETYPE_SCROLL;
    case 2:
    }
    return eMSG_NOTICETYPE_ALL;
  }

  public int val()
  {
    return this.value;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.eMSG_NOTICETYPE
 * JD-Core Version:    0.6.0
 */
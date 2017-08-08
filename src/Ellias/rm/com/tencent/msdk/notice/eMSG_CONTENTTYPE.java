package com.tencent.msdk.notice;

import com.tencent.msdk.tools.Logger;

public enum eMSG_CONTENTTYPE
{
  int value = 0;

  static
  {
    eMSG_CONTENTTYPE_IMAGE = new eMSG_CONTENTTYPE("eMSG_CONTENTTYPE_IMAGE", 1, 1);
    eMSG_CONTENTTYPE_WEB = new eMSG_CONTENTTYPE("eMSG_CONTENTTYPE_WEB", 2, 2);
    eMSG_CONTENTTYPE[] arrayOfeMSG_CONTENTTYPE = new eMSG_CONTENTTYPE[3];
    arrayOfeMSG_CONTENTTYPE[0] = eMSG_CONTENTTYPE_TEXT;
    arrayOfeMSG_CONTENTTYPE[1] = eMSG_CONTENTTYPE_IMAGE;
    arrayOfeMSG_CONTENTTYPE[2] = eMSG_CONTENTTYPE_WEB;
    $VALUES = arrayOfeMSG_CONTENTTYPE;
  }

  private eMSG_CONTENTTYPE(int paramInt)
  {
    this.value = paramInt;
  }

  public static boolean checkIsValidType(eMSG_CONTENTTYPE parameMSG_CONTENTTYPE)
  {
    if (parameMSG_CONTENTTYPE == null);
    do
      return false;
    while ((eMSG_CONTENTTYPE_IMAGE != parameMSG_CONTENTTYPE) && (eMSG_CONTENTTYPE_TEXT != parameMSG_CONTENTTYPE) && (eMSG_CONTENTTYPE_WEB != parameMSG_CONTENTTYPE));
    return true;
  }

  public static eMSG_CONTENTTYPE getEnum(int paramInt)
  {
    switch (paramInt)
    {
    default:
      Logger.e("bad notice content type:" + paramInt);
      return eMSG_CONTENTTYPE_TEXT;
    case 0:
      return eMSG_CONTENTTYPE_TEXT;
    case 1:
      return eMSG_CONTENTTYPE_IMAGE;
    case 2:
    }
    return eMSG_CONTENTTYPE_WEB;
  }

  public int val()
  {
    return this.value;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.eMSG_CONTENTTYPE
 * JD-Core Version:    0.6.0
 */
package com.tencent.msdk.stat;

import com.tencent.msdk.tools.Logger;

public enum eEVENT_TYPE
{
  int value = 0;

  static
  {
    eEVENT_NOTICE_MORE = new eEVENT_TYPE("eEVENT_NOTICE_MORE", 1, 2);
    eEVENT_BASIC_MD5BAD = new eEVENT_TYPE("eEVENT_BASIC_MD5BAD", 2, 10);
    eEVENT_BASIC_MATIDFAIL = new eEVENT_TYPE("eEVENT_BASIC_MATIDFAIL", 3, 11);
    eEVENT_BASIC_PICLENGTH = new eEVENT_TYPE("eEVENT_BASIC_PICLENGTH", 4, 12);
    eEVENT_BASIC_GAMETIME = new eEVENT_TYPE("eEVENT_BASIC_GAMETIME", 5, 13);
    eEVENT_TYPE[] arrayOfeEVENT_TYPE = new eEVENT_TYPE[6];
    arrayOfeEVENT_TYPE[0] = eEVENT_NOTICE_SHOW;
    arrayOfeEVENT_TYPE[1] = eEVENT_NOTICE_MORE;
    arrayOfeEVENT_TYPE[2] = eEVENT_BASIC_MD5BAD;
    arrayOfeEVENT_TYPE[3] = eEVENT_BASIC_MATIDFAIL;
    arrayOfeEVENT_TYPE[4] = eEVENT_BASIC_PICLENGTH;
    arrayOfeEVENT_TYPE[5] = eEVENT_BASIC_GAMETIME;
    $VALUES = arrayOfeEVENT_TYPE;
  }

  private eEVENT_TYPE(int paramInt)
  {
    this.value = paramInt;
  }

  public static eEVENT_TYPE getEnum(int paramInt)
  {
    switch (paramInt)
    {
    default:
      Logger.e("bad event type:" + paramInt);
      return eEVENT_NOTICE_SHOW;
    case 1:
      return eEVENT_NOTICE_SHOW;
    case 2:
      return eEVENT_NOTICE_MORE;
    case 10:
    }
    return eEVENT_BASIC_MD5BAD;
  }

  public int val()
  {
    return this.value;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.stat.eEVENT_TYPE
 * JD-Core Version:    0.6.0
 */
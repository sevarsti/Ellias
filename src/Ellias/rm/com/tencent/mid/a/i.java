package com.tencent.mid.a;

import com.tencent.mid.api.MidCallback;
import com.tencent.mid.util.Util;

class i
  implements MidCallback
{
  i(h paramh)
  {
  }

  public void onFail(int paramInt, String paramString)
  {
    Util.logInfo("checkServer failed, errCode:" + paramInt + ",msg:" + paramString);
  }

  public void onSuccess(Object paramObject)
  {
    Util.logInfo("checkServer success:" + paramObject);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.a.i
 * JD-Core Version:    0.6.0
 */
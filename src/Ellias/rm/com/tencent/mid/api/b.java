package com.tencent.mid.api;

import android.util.Log;
import com.tencent.mid.util.Util;

final class b
  implements MidCallback
{
  public void onFail(int paramInt, String paramString)
  {
    Log.e("MID", "failed to get mid, errorcode:" + paramInt + " ,msg:" + paramString);
  }

  public void onSuccess(Object paramObject)
  {
    if (paramObject != null)
    {
      MidEntity localMidEntity = MidEntity.parse(paramObject.toString());
      Util.logInfo("success to get mid:" + localMidEntity.getMid());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.api.b
 * JD-Core Version:    0.6.0
 */
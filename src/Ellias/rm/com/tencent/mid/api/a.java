package com.tencent.mid.api;

import android.util.Log;
import com.tencent.mid.util.Util;

final class a
  implements MidCallback
{
  a(MidCallback paramMidCallback)
  {
  }

  public void onFail(int paramInt, String paramString)
  {
    Log.e("MID", "failed to get mid, errorcode:" + paramInt + " ,msg:" + paramString);
    this.a.onFail(paramInt, paramString);
  }

  public void onSuccess(Object paramObject)
  {
    if (paramObject != null)
    {
      MidEntity localMidEntity = MidEntity.parse(paramObject.toString());
      Util.logInfo("success to get mid:" + localMidEntity.getMid());
      this.a.onSuccess(localMidEntity.getMid());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.api.a
 * JD-Core Version:    0.6.0
 */
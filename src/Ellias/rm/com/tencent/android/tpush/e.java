package com.tencent.android.tpush;

import com.tencent.android.tpush.logging.TLog;

final class e
  implements XGIOperateCallback
{
  public void onFail(Object paramObject, int paramInt, String paramString)
  {
    TLog.w("TPush", "+++ unregisterPush push fail. token:" + paramObject + ", errCode:" + paramInt + ",msg:" + paramString);
  }

  public void onSuccess(Object paramObject, int paramInt)
  {
    TLog.w("TPush", "+++ unregisterPush push sucess. flag:" + paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.e
 * JD-Core Version:    0.6.0
 */
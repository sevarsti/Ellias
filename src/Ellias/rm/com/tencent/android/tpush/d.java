package com.tencent.android.tpush;

import com.tencent.android.tpush.logging.TLog;

final class d
  implements XGIOperateCallback
{
  public void onFail(Object paramObject, int paramInt, String paramString)
  {
    TLog.d("TPush", "+++ register push fail. token:" + paramObject + ", errCode:" + paramInt + ",msg:" + paramString);
  }

  public void onSuccess(Object paramObject, int paramInt)
  {
    TLog.d("TPush", "+++ register push sucess. token:" + paramObject);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.d
 * JD-Core Version:    0.6.0
 */
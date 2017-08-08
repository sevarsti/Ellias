package com.tencent.qqgamemi;

import android.widget.Toast;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.StartItem;
import com.tencent.qqgamemi.data.StartItemCallBack;
import com.tencent.qqgamemi.protocol.QMiJceCommonData;

class b
  implements StartItemCallBack
{
  b(QMiServiceLogic paramQMiServiceLogic)
  {
  }

  public void a(StartItem paramStartItem)
  {
    if (paramStartItem != null)
    {
      TLog.c("QMiService", "set uuid:" + paramStartItem.uuid);
      QMiJceCommonData.h(paramStartItem.uuid);
      return;
    }
    if (DebugUtil.a())
      Toast.makeText(QMiServiceLogic.a(this.a), "error for no uuid", 1).show();
    LogUtil.e("QMiService", "can not get uuid!!!");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.b
 * JD-Core Version:    0.6.0
 */
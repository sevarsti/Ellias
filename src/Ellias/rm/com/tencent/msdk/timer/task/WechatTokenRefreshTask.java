package com.tencent.msdk.timer.task;

import com.tencent.msdk.Singleton;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.stat.MsdkStat;
import com.tencent.msdk.tools.Logger;

public class WechatTokenRefreshTask extends BaseTask
{
  private static final int MY_INTERVAL = 5;
  private static final int TASK_DELAY_TIME_IN_SECOND = 60;

  public int getMyInterval()
  {
    return 5;
  }

  public void run()
  {
    long l = System.currentTimeMillis() / 1000L;
    if ((((MsdkStat)MsdkStat.gDefault.get()).getResumedTime() != 0L) && (l - ((MsdkStat)MsdkStat.gDefault.get()).getResumedTime() < 60L))
    {
      Logger.d("Skip WechatTokenRefreshTask");
      return;
    }
    Logger.d("WechatTokenRefreshTask run");
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    switch (localLoginRet.flag)
    {
    default:
      return;
    case 0:
      Logger.d("token is still valid!");
      return;
    case 2007:
    }
    Logger.w("token need refresh!");
    WeGame.getInstance().refreshWxToken();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.timer.task.WechatTokenRefreshTask
 * JD-Core Version:    0.6.0
 */
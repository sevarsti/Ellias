package com.tencent.msdk.timer.task;

import com.tencent.msdk.notice.NoticeManager;
import com.tencent.msdk.tools.Logger;

public class NoticeTask extends BaseTask
{
  public int getMyInterval()
  {
    return NoticeManager.sRealNoticeTime;
  }

  public void run()
  {
    NoticeManager.getInstance().getNoticeInfo();
    Logger.d("NoticeTask run");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.timer.task.NoticeTask
 * JD-Core Version:    0.6.0
 */
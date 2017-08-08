package com.tencent.qqgamemi.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.common.QMiCommon;

class e extends Handler
{
  e(FeedbackDialog paramFeedbackDialog)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 1:
    }
    if (paramMessage.arg1 == 0)
    {
      QMiCommon.c(FeedbackDialog.a(this.a), FeedbackDialog.a(this.a).getResources().getString(ResourceUtil.b("qmi_feedback_thanks")));
      return;
    }
    String str = (String)paramMessage.obj;
    QMiCommon.c(FeedbackDialog.a(this.a), str);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.e
 * JD-Core Version:    0.6.0
 */
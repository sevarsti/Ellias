package com.tencent.qqgamemi.ui;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.protocol.MsgHandle;

class d
  implements View.OnClickListener
{
  d(FeedbackDialog paramFeedbackDialog, TextView paramTextView1, TextView paramTextView2)
  {
  }

  public void onClick(View paramView)
  {
    String str1;
    String str2;
    String str3;
    if (QMiConfig.b())
    {
      str1 = "(V150)";
      str2 = str1 + this.a.getText().toString();
      str3 = this.b.getText().toString();
      if ((this.a.getText().toString().length() < 4) || (this.a.getText().toString().length() > 30))
        str2 = "意见反馈：  ";
      if ((str3.length() >= 5) && (str3.length() <= 1200))
        break label170;
      QMiCommon.c(FeedbackDialog.a(this.c), FeedbackDialog.a(this.c).getResources().getString(ResourceUtil.b("qmi_feedback_content_length_invalid")));
    }
    label170: 
    do
    {
      return;
      str1 = "(V150-g" + FeedbackDialog.b(this.c) + ")";
      break;
    }
    while (!FeedbackDialog.c(this.c));
    FeedbackDialog.a(this.c, false);
    MsgHandle.a(FeedbackDialog.d(this.c), 1, str2, str3, FeedbackDialog.e(this.c));
    this.c.dismiss();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.d
 * JD-Core Version:    0.6.0
 */
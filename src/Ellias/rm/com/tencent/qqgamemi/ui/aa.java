package com.tencent.qqgamemi.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.common.QMiCommon;

class aa
  implements View.OnClickListener
{
  aa(LoginInfoDialog paramLoginInfoDialog)
  {
  }

  public void onClick(View paramView)
  {
    String str = (String)paramView.getTag(ResourceUtil.f("qmi_tag_temp"));
    if (!TextUtils.isEmpty(str))
      QMiCommon.b(LoginInfoDialog.a(this.a), str);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.aa
 * JD-Core Version:    0.6.0
 */
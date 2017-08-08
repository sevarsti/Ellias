package com.tencent.qqgamemi.ui;

import android.text.TextUtils;
import android.widget.TextView;
import com.tencent.component.ui.widget.image.AsyncImageView;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.data.UserInfo;
import com.tencent.qqgamemi.data.UserInfoCallBack;

class ak
  implements UserInfoCallBack
{
  ak(MeDialog.Builder paramBuilder)
  {
  }

  public void a(UserInfo paramUserInfo)
  {
    if (paramUserInfo != null)
    {
      MeDialog.Builder.j(this.a).setAsyncImageUrl(paramUserInfo.getFaceUrl());
      MeDialog.Builder.k(this.a).setText(paramUserInfo.getNickName());
      if ((paramUserInfo.getSign() == null) || (TextUtils.isEmpty(paramUserInfo.getSign().trim())))
        MeDialog.Builder.l(this.a).setText(ResourceUtil.b("qmi_login_info_no_sign"));
    }
    else
    {
      return;
    }
    MeDialog.Builder.l(this.a).setText(paramUserInfo.getSign().trim());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.ak
 * JD-Core Version:    0.6.0
 */
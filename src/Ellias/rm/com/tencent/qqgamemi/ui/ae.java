package com.tencent.qqgamemi.ui;

import CobraHallQmiProto.TRecentPlayGameInfo;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tencent.component.ui.widget.image.AsyncImageView;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.data.UserInfo;
import com.tencent.qqgamemi.data.UserInfoCallBack;
import java.util.List;

class ae
  implements UserInfoCallBack
{
  ae(LoginInfoDialog paramLoginInfoDialog)
  {
  }

  public void a(UserInfo paramUserInfo)
  {
    if (paramUserInfo != null)
    {
      LoginInfoDialog.e(this.a).setText(paramUserInfo.getNickName());
      LoginInfoDialog.f(this.a).setAsyncImageUrl(paramUserInfo.getFaceUrl());
      int i = ResourceUtil.c("qmi_boy");
      int j = ResourceUtil.c("qmi_boy_bg");
      if (paramUserInfo.getSex() == 1)
      {
        i = ResourceUtil.c("qmi_girl");
        j = ResourceUtil.c("qmi_girl_bg");
      }
      LoginInfoDialog.g(this.a).setText(paramUserInfo.getAge() + "岁");
      Drawable localDrawable = this.a.getContext().getResources().getDrawable(i);
      localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
      LoginInfoDialog.g(this.a).setCompoundDrawables(localDrawable, null, null, null);
      LoginInfoDialog.g(this.a).setBackgroundResource(j);
      LoginInfoDialog.h(this.a).setText(paramUserInfo.getStar());
      if ((paramUserInfo.getSign() == null) || (TextUtils.isEmpty(paramUserInfo.getSign().trim())))
      {
        LoginInfoDialog.i(this.a).setText(ResourceUtil.b("qmi_login_info_no_sign"));
        if (!TextUtils.isEmpty(paramUserInfo.getPlace()))
          break label364;
        LoginInfoDialog.j(this.a).setText(ResourceUtil.b("qmi_login_info_no_place"));
      }
      while (true)
      {
        List localList = paramUserInfo.getRecentPlayGames();
        if ((localList == null) || (LoginInfoDialog.k(this.a) == null))
          return;
        LoginInfoDialog.k(this.a).setVisibility(0);
        for (int k = 0; k < Math.min(5, localList.size()); k++)
        {
          TRecentPlayGameInfo localTRecentPlayGameInfo = (TRecentPlayGameInfo)localList.get(k);
          AsyncImageView localAsyncImageView = (AsyncImageView)LoginInfoDialog.k(this.a).findViewById(LoginInfoDialog.b()[k]);
          localAsyncImageView.setAsyncImageUrl(localTRecentPlayGameInfo.gameIcon);
          localAsyncImageView.setAsyncDefaultImage(ResourceUtil.c("qmi_game_icon_default"));
          localAsyncImageView.setVisibility(0);
        }
        LoginInfoDialog.i(this.a).setText(paramUserInfo.getSign());
        break;
        label364: LoginInfoDialog.j(this.a).setText(paramUserInfo.getPlace());
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.ae
 * JD-Core Version:    0.6.0
 */
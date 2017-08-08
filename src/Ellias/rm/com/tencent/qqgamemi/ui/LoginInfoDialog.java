package com.tencent.qqgamemi.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.component.ui.widget.image.AsyncImageView;
import com.tencent.component.ui.widget.image.processor.OvalProcessor;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.business.UrlDownLoadGameJoy;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.data.DataModel;
import com.tencent.qqgamemi.login.QMiLoginManager;
import java.io.File;

public class LoginInfoDialog extends QMiDialog
{
  private static final String a = LoginInfoDialog.class.getSimpleName();
  private static final int b = 5;
  private static final int[] c;
  private Context d = null;
  private QMiLoginManager e = null;
  private View.OnClickListener f = new aa(this);
  private View.OnClickListener g = new ab(this);
  private View h = null;
  private View i = null;
  private View j = null;
  private ViewGroup k = null;
  private TextView l;
  private AsyncImageView m;
  private TextView n;
  private TextView o;
  private TextView p;
  private TextView q;

  static
  {
    int[] arrayOfInt = new int[5];
    arrayOfInt[0] = ResourceUtil.f("recent_game_icon1");
    arrayOfInt[1] = ResourceUtil.f("recent_game_icon2");
    arrayOfInt[2] = ResourceUtil.f("recent_game_icon3");
    arrayOfInt[3] = ResourceUtil.f("recent_game_icon4");
    arrayOfInt[4] = ResourceUtil.f("recent_game_icon5");
    c = arrayOfInt;
  }

  public LoginInfoDialog(Context paramContext)
  {
    super(paramContext, ResourceUtil.d("Qmi_Common_Dialog"));
    a(paramContext);
  }

  public LoginInfoDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    a(paramContext);
  }

  private void a(Context paramContext)
  {
    this.d = paramContext;
    this.e = QMiLoginManager.a();
    View localView = getLayoutInflater().inflate(ResourceUtil.a("qmi_login_show_dialog"), null);
    a(localView);
    addContentView(localView, new ViewGroup.LayoutParams(-1, -1));
    setCanceledOnTouchOutside(true);
    getWindow().setType(2003);
  }

  private void a(View paramView)
  {
    ((ImageView)paramView.findViewById(ResourceUtil.f("title_icon"))).setImageResource(ResourceUtil.c("qmi_ic_me_dialog_title"));
    ((TextView)paramView.findViewById(ResourceUtil.f("title_text"))).setText(ResourceUtil.b("qmi_login_info_title"));
    paramView.findViewById(ResourceUtil.f("title_close")).setOnClickListener(new ac(this));
    paramView.findViewById(ResourceUtil.f("title_left_layout")).setOnClickListener(new ad(this));
    this.l = ((TextView)paramView.findViewById(ResourceUtil.f("login_show_name")));
    this.m = ((AsyncImageView)paramView.findViewById(ResourceUtil.f("login_show_icon")));
    OvalProcessor localOvalProcessor = new OvalProcessor();
    this.m.setImageProcessor(localOvalProcessor);
    this.n = ((TextView)paramView.findViewById(ResourceUtil.f("me_perinfo_age")));
    this.o = ((TextView)paramView.findViewById(ResourceUtil.f("login_show_star")));
    this.p = ((TextView)paramView.findViewById(ResourceUtil.f("login_info_sign")));
    this.q = ((TextView)paramView.findViewById(ResourceUtil.f("login_info_place")));
    this.k = ((ViewGroup)paramView.findViewById(ResourceUtil.f("recent_game_layout")));
    if ((QMiConfig.b()) && (!QMiCommon.c(getContext())))
    {
      this.i = paramView.findViewById(ResourceUtil.f("login_show_download"));
      if (this.i != null)
      {
        this.i.setVisibility(0);
        this.i.setOnClickListener(this.g);
        String str = UrlDownLoadGameJoy.a(this.d);
        if (!TextUtils.isEmpty(str))
        {
          File localFile = new File(str);
          if (localFile.exists())
          {
            ((TextView)this.i.findViewById(ResourceUtil.f("login_download_text"))).setText(ResourceUtil.b("qmi_login_info_install"));
            this.i.setTag(ResourceUtil.f("qmi_tag_temp"), localFile.getAbsolutePath());
            this.i.setOnClickListener(this.f);
          }
        }
      }
      this.h = paramView.findViewById(ResourceUtil.f("login_show_download_info"));
      if (this.h != null)
        this.h.setVisibility(0);
    }
    if (this.e.m())
      DataModel.a(this.d).a(QMiLoginManager.a().i(), new ae(this));
    this.j = paramView.findViewById(ResourceUtil.f("login_show_loginout"));
    if (this.j != null)
      this.j.setOnClickListener(this.g);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.LoginInfoDialog
 * JD-Core Version:    0.6.0
 */
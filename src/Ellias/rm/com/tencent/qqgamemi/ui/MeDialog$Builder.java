package com.tencent.qqgamemi.ui;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.component.ui.widget.image.AsyncImageView;
import com.tencent.component.ui.widget.image.ExtendImageView;
import com.tencent.component.ui.widget.image.processor.OvalProcessor;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.QmiSdkApi;
import com.tencent.qqgamemi.business.UrlDownLoadGameJoy;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.DataModel;
import com.tencent.qqgamemi.login.QMiLoginManager;
import com.tencent.qqgamemi.plugin.QMiPluginManager;
import com.tencent.qqgamemi.protocol.ServerType;
import java.io.File;

public class MeDialog$Builder
{
  private Context a = null;
  private DisplayMetrics b = new DisplayMetrics();
  private Dialog c = null;
  private QMiLoginManager d = null;
  private View e = null;
  private View f = null;
  private View g = null;
  private View h = null;
  private View i = null;
  private View j = null;
  private AsyncImageView k;
  private TextView l;
  private TextView m;
  private TextView n;
  private TextView o;
  private View.OnClickListener p = new ag(this);

  public MeDialog$Builder(Context paramContext)
  {
    this.a = paramContext;
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(this.b);
    this.d = QMiLoginManager.a();
  }

  private void a(View paramView)
  {
    ((ImageView)paramView.findViewById(ResourceUtil.f("title_icon"))).setImageResource(ResourceUtil.c("qmi_ic_me_dialog_title"));
    ((TextView)paramView.findViewById(ResourceUtil.f("title_text"))).setText(ResourceUtil.b("qmi_me_dialog_title"));
    paramView.findViewById(ResourceUtil.f("title_close")).setOnClickListener(new ai(this));
    TLog.c("MeDialog", "login status=" + this.d.m());
    if (this.d.m())
    {
      this.f = paramView.findViewById(ResourceUtil.f("show_login_layout"));
      b(this.f);
      b();
      this.f.setVisibility(0);
      this.f.setOnClickListener(this.p);
      this.h = paramView.findViewById(ResourceUtil.f("plugin_layout"));
      this.h.setOnClickListener(this.p);
      if (!QMiPluginManager.a().j())
        break label592;
      ImageView localImageView2 = (ImageView)paramView.findViewById(ResourceUtil.f("plugin_layout_new"));
      if (localImageView2 != null)
        localImageView2.setVisibility(0);
    }
    while (true)
    {
      View localView = paramView.findViewById(ResourceUtil.f("feedback_divider"));
      if (localView != null)
        localView.setVisibility(0);
      this.i = paramView.findViewById(ResourceUtil.f("feedback_layout"));
      this.i.setVisibility(0);
      this.i.setOnClickListener(this.p);
      this.j = paramView.findViewById(ResourceUtil.f("introduce_layout"));
      if (this.j != null)
        this.j.setOnClickListener(this.p);
      if ((QMiConfig.b()) && (!QMiCommon.c(this.a)))
      {
        this.g = paramView.findViewById(ResourceUtil.f("login_show_download"));
        if (this.g != null)
        {
          this.g.setVisibility(0);
          this.g.setOnClickListener(this.p);
          String str2 = UrlDownLoadGameJoy.a(this.a);
          if (!TextUtils.isEmpty(str2))
          {
            File localFile = new File(str2);
            if (localFile.exists())
            {
              ((TextView)this.g.findViewById(ResourceUtil.f("login_download_text"))).setText(ResourceUtil.b("qmi_login_info_install"));
              this.g.setTag(ResourceUtil.f("qmi_tag_temp"), localFile.getAbsolutePath());
              this.g.setOnClickListener(new aj(this));
            }
          }
        }
        paramView.findViewById(ResourceUtil.f("download_divider")).setVisibility(0);
      }
      this.o = ((TextView)paramView.findViewById(ResourceUtil.f("server_type")));
      if ((this.o != null) && (DebugUtil.a()))
      {
        this.o.setVisibility(0);
        this.o.setText(ServerType.a(this.a));
      }
      this.n = ((TextView)paramView.findViewById(ResourceUtil.f("extra_setting_desc")));
      String str1 = QmiSdkApi.getExtraSettingDescription();
      if ((this.n != null) && (!TextUtils.isEmpty(str1)))
      {
        this.n.setVisibility(0);
        this.n.setText(str1);
      }
      return;
      this.e = paramView.findViewById(ResourceUtil.f("login_layout"));
      c(this.e);
      this.e.setVisibility(0);
      this.e.setOnClickListener(this.p);
      break;
      label592: ImageView localImageView1 = (ImageView)paramView.findViewById(ResourceUtil.f("plugin_layout_new"));
      if (localImageView1 == null)
        continue;
      localImageView1.setVisibility(4);
    }
  }

  private void a(Window paramWindow)
  {
    paramWindow.getAttributes().width = this.b.widthPixels;
  }

  private void b()
  {
    DataModel.a(this.a).a(QMiLoginManager.a().i(), new ak(this));
  }

  private void b(View paramView)
  {
    this.m = ((TextView)paramView.findViewById(ResourceUtil.f("show_login_name")));
    this.k = ((AsyncImageView)paramView.findViewById(ResourceUtil.f("show_login_icon")));
    OvalProcessor localOvalProcessor = new OvalProcessor();
    this.k.setImageProcessor(localOvalProcessor);
    this.l = ((TextView)paramView.findViewById(ResourceUtil.f("show_login_sign")));
  }

  private void c(View paramView)
  {
    ((ExtendImageView)paramView.findViewById(ResourceUtil.f("login_icon"))).setImageProcessor(new OvalProcessor());
  }

  public Dialog a()
  {
    this.c = new MeDialog(this.a, ResourceUtil.d("Qmi_Common_Dialog"));
    View localView = ((LayoutInflater)this.a.getSystemService("layout_inflater")).inflate(ResourceUtil.a("qmi_me_dialog"), null);
    a(localView);
    this.c.addContentView(localView, new ViewGroup.LayoutParams(-1, -1));
    this.c.setContentView(localView);
    this.c.setCanceledOnTouchOutside(true);
    Window localWindow = this.c.getWindow();
    localWindow.setType(2003);
    localWindow.setSoftInputMode(18);
    a(localWindow);
    return this.c;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.MeDialog.Builder
 * JD-Core Version:    0.6.0
 */
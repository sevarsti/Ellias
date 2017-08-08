package com.tencent.qqgamemi.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.CheckBox;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.common.TLog;

public class MiuiNotifyDialog$Builder
{
  private static final String a = "package";
  private static final String b = "com.android.settings.ApplicationPkgName";
  private static final String c = "pkg";
  private static final String d = "com.android.settings";
  private static final String e = "com.android.settings.InstalledAppDetails";
  private Context f;
  private MiuiNotifyDialog g = null;
  private View h = null;
  private View i = null;
  private CheckBox j = null;
  private boolean k = true;
  private boolean l = true;
  private View.OnClickListener m = new am(this);

  public MiuiNotifyDialog$Builder(Context paramContext)
  {
    this.f = paramContext;
  }

  @SuppressLint({"InlinedApi"})
  public static void a(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    int n = Build.VERSION.SDK_INT;
    if (n >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", paramString, null));
      localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    if (n == 8);
    for (String str = "pkg"; ; str = "com.android.settings.ApplicationPkgName")
    {
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      localIntent.putExtra(str, paramString);
      break;
    }
  }

  private void b()
  {
    TLog.c("MiuiNotifyDialog", "goto Settings module");
    a(this.f, this.f.getPackageName());
  }

  private void b(View paramView)
  {
    this.h = paramView.findViewById(ResourceUtil.f("notify_dialog_btn_ignore"));
    this.h.setOnClickListener(this.m);
    this.i = paramView.findViewById(ResourceUtil.f("notify_dialog_btn_go"));
    this.i.setOnClickListener(this.m);
    this.j = ((CheckBox)paramView.findViewById(ResourceUtil.f("notify_dialog_checkBox")));
    if (this.k)
    {
      this.j.setOnCheckedChangeListener(new al(this));
      return;
    }
    this.j.setVisibility(8);
  }

  @SuppressLint({"WorldWriteableFiles"})
  private void c(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = this.f.getSharedPreferences("QMiCheckRom", 2).edit();
    TLog.c("MiuiNotifyDialog", "setNeedCheckRom:" + paramBoolean);
    localEditor.putBoolean("needCheckRom", paramBoolean);
    localEditor.commit();
  }

  public Builder a(View paramView)
  {
    return this;
  }

  public MiuiNotifyDialog a()
  {
    View localView = ((LayoutInflater)this.f.getSystemService("layout_inflater")).inflate(ResourceUtil.a("qmi_miui_notify_dialog"), null);
    b(localView);
    this.g = new MiuiNotifyDialog(this.f, ResourceUtil.d("Qmi_Close_Dialog"));
    this.g.addContentView(localView, new ViewGroup.LayoutParams(-1, -2));
    this.g.setContentView(localView);
    this.g.setCanceledOnTouchOutside(this.l);
    this.g.getWindow().setType(2003);
    return this.g;
  }

  public void a(boolean paramBoolean)
  {
    this.k = paramBoolean;
  }

  public void b(boolean paramBoolean)
  {
    this.l = paramBoolean;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.MiuiNotifyDialog.Builder
 * JD-Core Version:    0.6.0
 */
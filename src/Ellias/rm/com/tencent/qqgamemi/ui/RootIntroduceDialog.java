package com.tencent.qqgamemi.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.component.utils.PlatformUtil;
import com.tencent.component.utils.ResourceUtil;

public class RootIntroduceDialog extends QMiDialog
{
  private static final String a = RootIntroduceDialog.class.getSimpleName();
  private static final String b = "http://adev.qq.com/act/a20140509root/index.html";

  public RootIntroduceDialog(Context paramContext)
  {
    super(paramContext, ResourceUtil.d("Qmi_Common_Dialog"));
    a(paramContext);
  }

  public RootIntroduceDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    a(paramContext);
  }

  private void a(Context paramContext)
  {
    View localView = getLayoutInflater().inflate(ResourceUtil.a("qmi_root_introduce"), null);
    a(localView);
    addContentView(localView, new ViewGroup.LayoutParams(-1, -1));
    setCanceledOnTouchOutside(true);
    getWindow().setType(2003);
  }

  @SuppressLint({"NewApi"})
  private void a(View paramView)
  {
    ((ImageView)paramView.findViewById(ResourceUtil.f("title_icon"))).setImageResource(ResourceUtil.c("qmi_ic_me_dialog_title"));
    ((TextView)paramView.findViewById(ResourceUtil.f("title_text"))).setText(ResourceUtil.b("qmi_rootintro_dialog_title"));
    paramView.findViewById(ResourceUtil.f("title_close")).setOnClickListener(new ar(this));
    WebView localWebView = (WebView)paramView.findViewById(ResourceUtil.f("introduce_layout_webview"));
    if ((localWebView == null) || (PlatformUtil.version() >= 11));
    try
    {
      localWebView.removeJavascriptInterface("searchBoxJavaBridge_");
      label91: localWebView.setWebViewClient(new as(this, null));
      localWebView.loadUrl("http://adev.qq.com/act/a20140509root/index.html");
      return;
    }
    catch (Exception localException)
    {
      break label91;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.RootIntroduceDialog
 * JD-Core Version:    0.6.0
 */
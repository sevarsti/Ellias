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

public class IntroduceDialog extends QMiDialog
{
  private static final String a = IntroduceDialog.class.getSimpleName();
  private static final String b = "http://g.qq.com/act/a20140404help/";
  private Context c;

  public IntroduceDialog(Context paramContext)
  {
    super(paramContext, ResourceUtil.d("Qmi_Common_Dialog"));
    a(paramContext);
  }

  public IntroduceDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    a(paramContext);
  }

  private void a(Context paramContext)
  {
    this.c = paramContext;
    View localView = getLayoutInflater().inflate(ResourceUtil.a("qmi_introduce"), null);
    a(localView);
    addContentView(localView, new ViewGroup.LayoutParams(-1, -1));
    setCanceledOnTouchOutside(true);
    getWindow().setType(2003);
  }

  @SuppressLint({"NewApi"})
  private void a(View paramView)
  {
    ((ImageView)paramView.findViewById(ResourceUtil.f("title_icon"))).setImageResource(ResourceUtil.c("qmi_ic_me_dialog_title"));
    ((TextView)paramView.findViewById(ResourceUtil.f("title_text"))).setText(ResourceUtil.b("qmi_me_introduce_plugin"));
    paramView.findViewById(ResourceUtil.f("title_close")).setOnClickListener(new f(this));
    paramView.findViewById(ResourceUtil.f("title_left_layout")).setOnClickListener(new g(this));
    WebView localWebView = (WebView)paramView.findViewById(ResourceUtil.f("introduce_layout_webview"));
    if ((localWebView == null) || (PlatformUtil.version() >= 11));
    try
    {
      localWebView.removeJavascriptInterface("searchBoxJavaBridge_");
      label111: localWebView.setWebViewClient(new h(this, null));
      localWebView.loadUrl("http://g.qq.com/act/a20140404help/");
      return;
    }
    catch (Exception localException)
    {
      break label111;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.IntroduceDialog
 * JD-Core Version:    0.6.0
 */
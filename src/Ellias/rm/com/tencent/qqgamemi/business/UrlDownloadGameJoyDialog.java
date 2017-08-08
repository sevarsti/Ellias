package com.tencent.qqgamemi.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.TextView;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.ui.QMiDialog;

public class UrlDownloadGameJoyDialog extends QMiDialog
{
  private static final String a = UrlDownloadGameJoyDialog.class.getSimpleName();
  private Context b = null;
  private String c = "";
  private String d = "";
  private View.OnClickListener e = new g(this);
  private View f = null;
  private View g = null;
  private TextView h = null;

  public UrlDownloadGameJoyDialog(Context paramContext)
  {
    super(paramContext, ResourceUtil.d("Qmi_Close_Dialog"));
    a(paramContext);
  }

  public UrlDownloadGameJoyDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    a(paramContext);
  }

  private void a(Context paramContext)
  {
    this.b = paramContext;
    View localView = getLayoutInflater().inflate(ResourceUtil.a("qmi_download_dialog"), null);
    a(localView);
    addContentView(localView, new ViewGroup.LayoutParams(-1, -1));
    setCanceledOnTouchOutside(true);
    getWindow().setType(2003);
  }

  private void a(View paramView)
  {
    this.g = paramView.findViewById(ResourceUtil.f("download_dialog_btn_left"));
    if (this.g != null)
      this.g.setOnClickListener(this.e);
    this.f = paramView.findViewById(ResourceUtil.f("download_dialog_btn_right"));
    if (this.f != null)
      this.f.setOnClickListener(this.e);
    this.h = ((TextView)paramView.findViewById(ResourceUtil.f("download_dialog_context")));
  }

  public void a(String paramString)
  {
    this.c = paramString;
  }

  public void b(String paramString)
  {
    this.d = paramString;
  }

  public void c(String paramString)
  {
    if (this.h != null)
      this.h.setText(paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.UrlDownloadGameJoyDialog
 * JD-Core Version:    0.6.0
 */
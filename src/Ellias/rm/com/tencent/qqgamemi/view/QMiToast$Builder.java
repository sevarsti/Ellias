package com.tencent.qqgamemi.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.TextView;
import com.tencent.component.utils.ResourceUtil;

public class QMiToast$Builder
{
  private Context a;
  private QMiToast b = null;
  private String c = "";

  public QMiToast$Builder(Context paramContext)
  {
    this.a = paramContext;
  }

  private void a(View paramView)
  {
    ((TextView)paramView.findViewById(ResourceUtil.f("toast_text"))).setText(this.c);
  }

  public Builder a(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public QMiToast a()
  {
    View localView = ((LayoutInflater)this.a.getSystemService("layout_inflater")).inflate(ResourceUtil.a("qmi_toast"), null);
    a(localView);
    this.b = new QMiToast(this.a, ResourceUtil.d("Qmi_Toast"));
    this.b.addContentView(localView, new ViewGroup.LayoutParams(-2, -2));
    this.b.setContentView(localView);
    this.b.getWindow().setType(2003);
    this.b.getWindow().setFlags(56, 56);
    return this.b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.QMiToast.Builder
 * JD-Core Version:    0.6.0
 */
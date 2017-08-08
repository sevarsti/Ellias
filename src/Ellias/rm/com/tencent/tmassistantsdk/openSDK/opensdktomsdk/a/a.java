package com.tencent.tmassistantsdk.openSDK.opensdktomsdk.a;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.tmassistantsdk.g.k;
import com.tencent.tmassistantsdk.g.l;

public class a extends Dialog
{
  protected Context a = null;
  protected int b = 0;
  protected TextView c = null;
  protected RelativeLayout d = null;
  protected TextView e = null;
  protected Button f = null;
  protected FrameLayout g = null;
  public ProgressBar h = null;
  public TextView i = null;
  public k j = null;
  private Button k = null;

  public a(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext, paramInt1);
    this.a = paramContext;
    this.b = paramInt2;
  }

  private int b(int paramInt)
  {
    int m = b();
    int n = a();
    l.b("PopDialog", " width = " + n + "  height = " + m);
    if (m > n);
    while (true)
    {
      int i1 = (int)(paramInt * (0.0F + m) / 1280.0F);
      l.b("PopDialog", "rtn" + paramInt + ":" + i1);
      return i1;
      m = n;
    }
  }

  private void c()
  {
    d();
    switch (this.b)
    {
    default:
      return;
    case 1:
      e();
      return;
    case 2:
    }
    f();
  }

  private void d()
  {
    this.c.setPadding(b(30), 0, b(30), 0);
    this.d.setPadding(b(30), 0, b(30), b(30));
    this.e.setPadding(0, b(30), 0, b(30));
  }

  private void e()
  {
    this.g.setVisibility(8);
    this.k.setHeight(b(78));
    this.k.setText(this.a.getString(this.j.a("white_list_submit")));
  }

  private void f()
  {
    this.k.setHeight(b(78));
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)this.k.getLayoutParams();
    localMarginLayoutParams.setMargins(0, 0, b(30), 0);
    this.k.setLayoutParams(localMarginLayoutParams);
    this.f.setHeight(b(78));
    this.f.setTextSize(b(30));
    this.h.setMinimumHeight(b(78));
    this.i.setHeight(b(78));
  }

  public int a()
  {
    if (this.a != null)
      return this.a.getResources().getDisplayMetrics().widthPixels;
    return 0;
  }

  public void a(int paramInt)
  {
    if (paramInt != 0)
      this.f.setBackgroundResource(paramInt);
  }

  public void a(View.OnClickListener paramOnClickListener)
  {
    if (paramOnClickListener != null)
      this.f.setOnClickListener(paramOnClickListener);
  }

  public void a(com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a parama)
  {
    if (parama != null)
      this.f.setTag(parama);
  }

  public void a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      this.c.setText(paramString);
  }

  public void a(boolean paramBoolean)
  {
    if (this.f != null)
      this.f.setEnabled(paramBoolean);
  }

  public int b()
  {
    if (this.a != null)
      return this.a.getResources().getDisplayMetrics().heightPixels;
    return 0;
  }

  public void b(View.OnClickListener paramOnClickListener)
  {
    if (paramOnClickListener != null)
      this.k.setOnClickListener(paramOnClickListener);
  }

  public void b(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      this.e.setText(paramString);
  }

  public void c(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      this.i.setText(paramString);
  }

  public void d(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      this.k.setText(paramString);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.j = new k(this.a);
    super.setContentView(this.j.c("com_tencent_tmassistant_sdk_white_list_dlg"));
    super.getWindow().setLayout(b(662), b(662));
    setCancelable(false);
    this.c = ((TextView)findViewById(this.j.d("dlg_title_tv")));
    this.d = ((RelativeLayout)findViewById(this.j.d("content")));
    this.e = ((TextView)findViewById(this.j.d("dlg_body_tv")));
    this.f = ((Button)findViewById(this.j.d("positive_btn")));
    this.g = ((FrameLayout)findViewById(this.j.d("positive_btn_frame_layout")));
    this.h = ((ProgressBar)findViewById(this.j.d("download_pb")));
    this.i = ((TextView)findViewById(this.j.d("progress_txt_tv")));
    this.k = ((Button)findViewById(this.j.d("negtive_btn")));
    c();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.a.a
 * JD-Core Version:    0.6.0
 */
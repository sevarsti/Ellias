package com.tencent.qqgamemi.ui;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.tencent.component.utils.ResourceUtil;

public class QmiSpecialDownloadDialog$Builder
{
  private Context a;
  private QmiSpecialDownloadDialog b = null;
  private View.OnClickListener c = new aq(this);

  public QmiSpecialDownloadDialog$Builder(Context paramContext)
  {
    this.a = paramContext;
  }

  private void a(View paramView)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(ResourceUtil.f("dialog_content"));
    TextView localTextView2 = (TextView)paramView.findViewById(ResourceUtil.f("AlertButtonCancelSubmit"));
    TextView localTextView3 = (TextView)paramView.findViewById(ResourceUtil.f("AlertButtonOKSubmit"));
    localTextView2.setOnClickListener(this.c);
    localTextView3.setOnClickListener(this.c);
    SpannableString localSpannableString = new SpannableString(this.a.getString(ResourceUtil.b("qmi_friendly_imply_content")));
    localSpannableString.setSpan(new ForegroundColorSpan(-46776), 29, 32, 18);
    localTextView1.setText(localSpannableString);
  }

  public QmiSpecialDownloadDialog a()
  {
    View localView = ((LayoutInflater)this.a.getSystemService("layout_inflater")).inflate(ResourceUtil.a("qmi_dialog_special_download"), null);
    a(localView);
    this.b = new QmiSpecialDownloadDialog(this.a, ResourceUtil.d("Qmi_WhiteDialog"));
    this.b.addContentView(localView, new ViewGroup.LayoutParams(-1, -2));
    this.b.setContentView(localView);
    this.b.setCanceledOnTouchOutside(true);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)this.a.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.b.getWindow().setType(2003);
    return this.b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.QmiSpecialDownloadDialog.Builder
 * JD-Core Version:    0.6.0
 */
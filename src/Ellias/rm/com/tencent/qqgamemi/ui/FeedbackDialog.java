package com.tencent.qqgamemi.ui;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.component.net.NetworkManager;
import com.tencent.component.os.info.DeviceDash;
import com.tencent.component.utils.PerformanceUtil;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.login.QMiLoginManager;

public class FeedbackDialog extends QMiDialog
{
  private static final String a = "FeedbackDialog";
  private static final int d = 1;
  private Context b;
  private boolean c = true;
  private Handler e = new e(this);

  public FeedbackDialog(Context paramContext)
  {
    super(paramContext, ResourceUtil.d("Qmi_Common_Dialog"));
    a(paramContext);
  }

  public FeedbackDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    a(paramContext);
  }

  private String a()
  {
    try
    {
      String str = this.b.getPackageManager().getPackageInfo(this.b.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "unknown";
  }

  private void a(Context paramContext)
  {
    this.b = paramContext;
    View localView = getLayoutInflater().inflate(ResourceUtil.a("qmi_feedback"), null);
    a(localView);
    addContentView(localView, new ViewGroup.LayoutParams(-1, -1));
    setCanceledOnTouchOutside(true);
    getWindow().setType(2003);
  }

  private void a(View paramView)
  {
    ((ImageView)paramView.findViewById(ResourceUtil.f("title_icon"))).setImageResource(ResourceUtil.c("qmi_ic_me_dialog_title"));
    ((TextView)paramView.findViewById(ResourceUtil.f("title_text"))).setText(ResourceUtil.b("qmi_me_feedback_plugin"));
    paramView.findViewById(ResourceUtil.f("title_close")).setOnClickListener(new b(this));
    paramView.findViewById(ResourceUtil.f("title_left_layout")).setOnClickListener(new c(this));
    TextView localTextView1 = (TextView)paramView.findViewById(ResourceUtil.f("title"));
    TextView localTextView2 = (TextView)paramView.findViewById(ResourceUtil.f("context"));
    ((Button)paramView.findViewById(ResourceUtil.f("feedback_btn"))).setOnClickListener(new d(this, localTextView1, localTextView2));
  }

  private String b()
  {
    String str1 = d();
    if (Environment.getExternalStorageState().equals("mounted"));
    for (String str2 = "yes"; ; str2 = "no")
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)this.b.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      String str3 = Integer.toString(localDisplayMetrics.widthPixels) + "*" + Integer.toString(localDisplayMetrics.heightPixels);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("uin:");
      localStringBuilder.append(QMiLoginManager.a().i());
      localStringBuilder.append("\n");
      localStringBuilder.append("\n");
      localStringBuilder.append("deviceinfo:");
      localStringBuilder.append(DeviceDash.getInstance().getDeviceInfo());
      localStringBuilder.append("\n");
      localStringBuilder.append("network:");
      localStringBuilder.append(str1);
      localStringBuilder.append("\n");
      localStringBuilder.append("has sdcard：");
      localStringBuilder.append(str2);
      localStringBuilder.append("\n");
      localStringBuilder.append("分辨率：");
      localStringBuilder.append(str3);
      localStringBuilder.append("\n");
      localStringBuilder.append("adtag:");
      localStringBuilder.append("QMi.150");
      localStringBuilder.append("\n");
      localStringBuilder.append("cpu频率:");
      localStringBuilder.append(Formatter.formatFileSize(this.b, 1024L * PerformanceUtil.b()) + "(" + PerformanceUtil.b() + ")");
      localStringBuilder.append("\n");
      localStringBuilder.append("cpu核数:");
      localStringBuilder.append(PerformanceUtil.a());
      localStringBuilder.append("\n");
      localStringBuilder.append("设备RAM内存:");
      localStringBuilder.append(Formatter.formatFileSize(this.b, PerformanceUtil.d()) + "(" + PerformanceUtil.d() + ")");
      return localStringBuilder.toString();
    }
  }

  private String d()
  {
    return NetworkManager.a();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.FeedbackDialog
 * JD-Core Version:    0.6.0
 */
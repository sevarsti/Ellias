package com.tencent.qqgamemi.root;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.tencent.component.utils.ResourceUtil;

public class RootAlertDialog$Builder
{
  private Context a;
  private CharSequence b;
  private String c;
  private String d;
  private String e;
  private View f;
  private DialogInterface.OnCancelListener g;
  private boolean h = true;
  private DialogInterface.OnClickListener i;
  private DialogInterface.OnClickListener j;

  public RootAlertDialog$Builder(Context paramContext)
  {
    this.a = paramContext;
  }

  public Builder a(int paramInt)
  {
    this.c = ((String)this.a.getText(paramInt));
    return this;
  }

  public Builder a(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.d = ((String)this.a.getText(paramInt));
    this.i = paramOnClickListener;
    return this;
  }

  public Builder a(DialogInterface.OnCancelListener paramOnCancelListener)
  {
    this.g = paramOnCancelListener;
    return this;
  }

  public Builder a(CharSequence paramCharSequence)
  {
    this.b = paramCharSequence;
    return this;
  }

  public Builder a(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public Builder a(String paramString, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.d = paramString;
    this.i = paramOnClickListener;
    return this;
  }

  public RootAlertDialog a()
  {
    LayoutInflater localLayoutInflater = LayoutInflater.from(this.a);
    RootAlertDialog localRootAlertDialog = new RootAlertDialog(this.a);
    View localView = localLayoutInflater.inflate(ResourceUtil.a("qmi_dialog_alert"), null);
    localRootAlertDialog.setContentView(localView);
    if (!TextUtils.isEmpty(this.b))
    {
      ((TextView)localView.findViewById(ResourceUtil.f("alert_dialog_title"))).getPaint().setFakeBoldText(true);
      ((TextView)localView.findViewById(ResourceUtil.f("alert_dialog_title"))).setText(this.b);
      if (this.d == null)
        break label257;
      ((TextView)localView.findViewById(ResourceUtil.f("alert_dialog_positive_button"))).setText(this.d);
      ((TextView)localView.findViewById(ResourceUtil.f("alert_dialog_positive_button"))).setOnClickListener(new a(this, localRootAlertDialog));
      label134: if (this.e == null)
        break label274;
      ((TextView)localView.findViewById(ResourceUtil.f("alert_dialog_negative_button"))).setText(this.e);
      ((TextView)localView.findViewById(ResourceUtil.f("alert_dialog_negative_button"))).setOnClickListener(new b(this, localRootAlertDialog));
      label184: if (this.c == null)
        break label291;
      ((TextView)localView.findViewById(ResourceUtil.f("alert_dialog_content"))).setText(this.c);
    }
    while (true)
    {
      if (this.g != null)
        localRootAlertDialog.setOnCancelListener(this.g);
      localRootAlertDialog.setCancelable(this.h);
      localRootAlertDialog.setContentView(localView);
      return localRootAlertDialog;
      localView.findViewById(ResourceUtil.f("alert_dialog_title")).setVisibility(8);
      break;
      label257: localView.findViewById(ResourceUtil.f("alert_dialog_positive_button")).setVisibility(8);
      break label134;
      label274: localView.findViewById(ResourceUtil.f("alert_dialog_positive_button")).setVisibility(8);
      break label184;
      label291: if (this.f == null)
        continue;
    }
  }

  public void a(boolean paramBoolean)
  {
    this.h = paramBoolean;
  }

  public Builder b(int paramInt)
  {
    this.b = ((String)this.a.getText(paramInt));
    return this;
  }

  public Builder b(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.e = ((String)this.a.getText(paramInt));
    this.j = paramOnClickListener;
    return this;
  }

  public Builder b(String paramString, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.e = paramString;
    this.j = paramOnClickListener;
    return this;
  }

  public RootAlertDialog b()
  {
    RootAlertDialog localRootAlertDialog = a();
    localRootAlertDialog.show();
    return localRootAlertDialog;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.root.RootAlertDialog.Builder
 * JD-Core Version:    0.6.0
 */
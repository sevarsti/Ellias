package com.pay.ui.common;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.pay.tool.APCommMethod;

public class APAlertDialog$Builder
{
  private Context a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private DialogInterface.OnClickListener g;
  private DialogInterface.OnClickListener h;
  private DialogInterface.OnClickListener i;

  public APAlertDialog$Builder(Context paramContext)
  {
    this.a = paramContext;
  }

  public APAlertDialog create()
  {
    LayoutInflater localLayoutInflater = (LayoutInflater)this.a.getSystemService("layout_inflater");
    APAlertDialog localAPAlertDialog = new APAlertDialog(this.a, APCommMethod.getStyleId(this.a, "unipay_customDialog"));
    View localView = localLayoutInflater.inflate(APCommMethod.getLayoutId(this.a, "unipay_layout_custom_dialog"), null);
    localAPAlertDialog.addContentView(localView, new ViewGroup.LayoutParams(-1, -2));
    int j = ((WindowManager)this.a.getSystemService("window")).getDefaultDisplay().getWidth();
    float f1;
    if (this.a.getResources().getConfiguration().orientation == 2)
    {
      f1 = 0.56F;
      localAPAlertDialog.getWindow().getAttributes().width = (int)(f1 * j);
      ((TextView)localView.findViewById(APCommMethod.getId(this.a, "unipay_id_DialogTittle"))).setText(this.b);
      if (this.d == null)
        break label386;
      ((Button)localView.findViewById(APCommMethod.getId(this.a, "unipay_id_DialogEnsure"))).setText(this.d);
      if (this.g != null)
        ((Button)localView.findViewById(APCommMethod.getId(this.a, "unipay_id_DialogEnsure"))).setOnClickListener(new d(this, localAPAlertDialog));
      label212: if (this.e == null)
        break label407;
      ((Button)localView.findViewById(APCommMethod.getId(this.a, "unipay_id_DialogCancel"))).setText(this.e);
      if (this.h != null)
        ((Button)localView.findViewById(APCommMethod.getId(this.a, "unipay_id_DialogCancel"))).setOnClickListener(new e(this, localAPAlertDialog));
      label277: if (this.f == null)
        break label428;
      ((Button)localView.findViewById(APCommMethod.getId(this.a, "unipay_id_DialogNeutral"))).setText(this.f);
      if (this.i != null)
        ((Button)localView.findViewById(APCommMethod.getId(this.a, "unipay_id_DialogNeutral"))).setOnClickListener(new f(this, localAPAlertDialog));
    }
    while (true)
    {
      if (this.c != null)
        ((TextView)localView.findViewById(APCommMethod.getId(this.a, "unipay_id_DialogMessage"))).setText(this.c);
      localAPAlertDialog.setContentView(localView);
      return localAPAlertDialog;
      f1 = 0.8F;
      break;
      label386: localView.findViewById(APCommMethod.getId(this.a, "unipay_id_DialogEnsure")).setVisibility(8);
      break label212;
      label407: localView.findViewById(APCommMethod.getId(this.a, "unipay_id_DialogCancel")).setVisibility(8);
      break label277;
      label428: localView.findViewById(APCommMethod.getId(this.a, "unipay_id_DialogNeutral")).setVisibility(8);
    }
  }

  public Builder setContentView(View paramView)
  {
    return this;
  }

  public Builder setMessage(int paramInt)
  {
    this.c = ((String)this.a.getText(paramInt));
    return this;
  }

  public Builder setMessage(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public Builder setNegativeButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.e = ((String)this.a.getText(paramInt));
    this.h = paramOnClickListener;
    return this;
  }

  public Builder setNegativeButton(String paramString, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.e = paramString;
    this.h = paramOnClickListener;
    return this;
  }

  public Builder setNeutralButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.f = ((String)this.a.getText(paramInt));
    this.i = paramOnClickListener;
    return this;
  }

  public Builder setNeutralButton(String paramString, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.f = paramString;
    this.i = paramOnClickListener;
    return this;
  }

  public Builder setPositiveButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.d = ((String)this.a.getText(paramInt));
    this.g = paramOnClickListener;
    return this;
  }

  public Builder setPositiveButton(String paramString, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.d = paramString;
    this.g = paramOnClickListener;
    return this;
  }

  public Builder setTitle(int paramInt)
  {
    this.b = ((String)this.a.getText(paramInt));
    return this;
  }

  public Builder setTitle(String paramString)
  {
    this.b = paramString;
    return this;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APAlertDialog.Builder
 * JD-Core Version:    0.6.0
 */
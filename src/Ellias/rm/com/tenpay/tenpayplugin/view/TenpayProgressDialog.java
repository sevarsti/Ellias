package com.tenpay.tenpayplugin.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.tenpay.tenpayplugin.TenpayResourceUtil;

public class TenpayProgressDialog extends ProgressDialog
{
  private Context a;
  private String b = "请稍侯...";
  private TextView c = null;

  public TenpayProgressDialog(Context paramContext)
  {
    super(paramContext);
    this.a = paramContext.getApplicationContext();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(TenpayResourceUtil.getLayoutId(this.a, "unipay_tenpay_loadding"));
    Animation localAnimation = AnimationUtils.loadAnimation(this.a, TenpayResourceUtil.getAnimId(this.a, "unipay_tenpay_loading_anim"));
    ((ProgressBar)findViewById(TenpayResourceUtil.getId(this.a, "tenpay_loading_pro"))).setAnimation(localAnimation);
    localAnimation.start();
    this.c = ((TextView)findViewById(TenpayResourceUtil.getId(this.a, "tenpay_loading_txt")));
    this.c.setText(this.b);
    setCancelable(false);
  }

  public void setMessage(CharSequence paramCharSequence)
  {
    super.setMessage(paramCharSequence);
    this.b = String.valueOf(paramCharSequence);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.TenpayProgressDialog
 * JD-Core Version:    0.6.0
 */
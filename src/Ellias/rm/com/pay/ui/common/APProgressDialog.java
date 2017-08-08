package com.pay.ui.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.pay.AndroidPay;
import com.pay.tool.APCommMethod;

public class APProgressDialog extends ProgressDialog
{
  private String a = "请稍候...";
  private TextView b = null;

  public APProgressDialog(Context paramContext)
  {
    super(paramContext);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(APCommMethod.getLayoutId(AndroidPay.singleton().applicationContext, "unipay_layout_loadding"));
    ProgressBar localProgressBar = (ProgressBar)findViewById(APCommMethod.getId(AndroidPay.singleton().applicationContext, "unipay_progress"));
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.2F);
    localAlphaAnimation.setDuration(600L);
    localAlphaAnimation.setRepeatCount(-1);
    localAlphaAnimation.setRepeatMode(2);
    localProgressBar.setAnimation(localAlphaAnimation);
    localAlphaAnimation.start();
    this.b = ((TextView)findViewById(APCommMethod.getId(AndroidPay.singleton().applicationContext, "unipay_id_LoadingTxt")));
    this.b.setText(this.a);
    setCancelable(false);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      cancel();
      return false;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void setMessage(CharSequence paramCharSequence)
  {
    super.setMessage(paramCharSequence);
    this.a = String.valueOf(paramCharSequence);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APProgressDialog
 * JD-Core Version:    0.6.0
 */
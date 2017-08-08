package com.pay.ui.channel;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.pay.AndroidPay;
import com.pay.buyManager.APWechatPaySDK;
import com.pay.tool.APCommMethod;
import com.pay.ui.common.APActivity;

public class APWechatActivity extends APActivity
{
  private APWechatPaySDK a;
  private String b = "请稍候...";
  private TextView c = null;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(APCommMethod.getLayoutId(AndroidPay.singleton().applicationContext, "unipay_layout_loadding"));
    ProgressBar localProgressBar = (ProgressBar)findViewById(APCommMethod.getId(AndroidPay.singleton().applicationContext, "unipay_progress"));
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.4F);
    localAlphaAnimation.setDuration(600L);
    localAlphaAnimation.setRepeatCount(-1);
    localAlphaAnimation.setRepeatMode(2);
    localProgressBar.setAnimation(localAlphaAnimation);
    localAlphaAnimation.start();
    this.c = ((TextView)findViewById(APCommMethod.getId(AndroidPay.singleton().applicationContext, "unipay_id_LoadingTxt")));
    this.c.setText(this.b);
    this.a = new APWechatPaySDK(this);
    this.a.handleIntent(getIntent());
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    this.a.handleIntent(paramIntent);
    finish();
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onRestart()
  {
    super.onRestart();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.APWechatActivity
 * JD-Core Version:    0.6.0
 */
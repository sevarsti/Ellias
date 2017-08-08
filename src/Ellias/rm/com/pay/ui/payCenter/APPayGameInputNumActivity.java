package com.pay.ui.payCenter;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.mp.APMPSendInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.channel.APRecoChannelActivity;
import com.pay.ui.common.APUICommonMethod;

public class APPayGameInputNumActivity extends APRecoChannelActivity
{
  private EditText a;
  private ImageButton b;
  private int c = 1;
  private int d = 1;
  private int e = 0;
  private Handler f;
  private int g = 60;
  private TextWatcher h = new a(this);

  protected void doPay()
  {
    dismissInput();
    payAutoSelect();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
    {
      finish();
      return;
    }
    this.saveType = APDataInterface.singleton().getOrderInfo().saveType;
    this.e = 0;
    this.f = new Handler();
    this.f.postDelayed(new h(this, 0), 100L);
    AndroidPay.singleton().isUILaunched = true;
    int i = getResources().getDisplayMetrics().densityDpi;
    int j = getResources().getDisplayMetrics().widthPixels;
    APLog.i("APPayGameInputNumActivity", "densityDpi:" + i + " width:" + j);
    if ((i > 240) || (j >= 960))
    {
      this.g = 60;
      return;
    }
    this.g = 20;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (APDataInterface.singleton().getSourceActivity().equals("APPayGameListNumActivity"))
      {
        Intent localIntent = new Intent();
        localIntent.setClass(this, APPayGameListNumActivity.class);
        startActivity(localIntent);
        finish();
      }
      do
      {
        return true;
        APDataReportManager.getInstance().insertData("sdk.goods.keyback", this.saveType);
        APUICommonMethod.popActivity();
      }
      while (!APDataInterface.singleton().getSourceActivity().equals("APPayGameInputNumActivity"));
      APCommMethod.payErrorCallBack(2, "");
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    APDataReportManager.getInstance().insertData("sdk.accountinput.show", this.saveType);
    if (getResources().getConfiguration().orientation == 2)
      getWindow().setSoftInputMode(3);
    while (true)
    {
      super.onResume();
      return;
      if ((getResources().getConfiguration().orientation != 1) || (this.a == null))
        continue;
      this.a.setSelectAllOnFocus(true);
      showInputDelay(this.a, 0);
    }
  }

  public void onStop()
  {
    dismissInput();
    APUICommonMethod.dismissWaitDialog();
    super.onStop();
  }

  public void setUptoNumMpMpSendInfo(int paramInt)
  {
    TextView localTextView;
    String str1;
    GridView localGridView;
    if ((this.orderInfo.saveType == 0) && (APMPSendInfo.getInstance().getIsHasUptoNumMpMPInfo()))
    {
      localTextView = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_PerPrice"));
      str1 = "1元=" + this.orderInfo.buyInfo.price + "，满赠活动进行中，多买多送";
      localGridView = (GridView)findViewById(APCommMethod.getId(this, "unipay_id_mpGoodsPic"));
      if (APMPSendInfo.getInstance().getSendGameLevelNum(paramInt) <= 0)
        break label119;
    }
    for (String str2 = APMPSendInfo.getInstance().getUptoNumSendInfo(paramInt, this.orderInfo.buyInfo.name); ; str2 = str1)
    {
      localTextView.setText(str2);
      return;
      label119: localGridView.setVisibility(8);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.APPayGameInputNumActivity
 * JD-Core Version:    0.6.0
 */
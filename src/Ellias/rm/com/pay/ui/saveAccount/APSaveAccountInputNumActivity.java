package com.pay.ui.saveAccount;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.channel.APRecoChannelActivity;
import com.pay.ui.common.APUICommonMethod;

public class APSaveAccountInputNumActivity extends APRecoChannelActivity
{
  private EditText a;
  private ImageButton b;
  private Handler c;
  private int d = 60;
  private TextWatcher e = new a(this);

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
    this.c = new Handler();
    this.c.postDelayed(new f(this, 0), 100L);
    AndroidPay.singleton().isUILaunched = true;
    int i = getResources().getDisplayMetrics().densityDpi;
    int j = getResources().getDisplayMetrics().widthPixels;
    APLog.i("APSaveAccountListNumActivity", "densityDpi:" + i + " width:" + j);
    if ((i > 240) || (j >= 960))
    {
      this.d = 60;
      return;
    }
    this.d = 20;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      APDataReportManager.getInstance().insertData("sdk.accountinput.keyback", this.saveType);
      finish();
      APCommMethod.payErrorCallBack(2, "");
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.saveAccount.APSaveAccountInputNumActivity
 * JD-Core Version:    0.6.0
 */
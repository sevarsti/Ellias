package com.pay.ui.payExpress;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import com.pay.AndroidPay;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.channel.APRecoChannelActivity;
import com.pay.ui.common.APUICommonMethod;
import com.pay.ui.payCenter.APPayGameInputNumActivity;
import com.pay.ui.payCenter.APPayGameListNumActivity;
import com.pay.ui.payWeb.APWebBuyActivity;
import com.pay.ui.payWeb.APWebProtocol;

public class APPayExpressActivity extends APRecoChannelActivity
{
  private Handler a;
  private int b = 0;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
    {
      finish();
      return;
    }
    this.saveType = this.orderInfo.saveType;
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null)
      this.b = localBundle.getInt("subChannel");
    this.a = new Handler();
    this.a.postDelayed(new d(this, 0), 100L);
    AndroidPay.singleton().isUILaunched = true;
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      APDataReportManager.getInstance().insertData("sdk.payexpress.keyback", this.saveType);
      String str = APDataInterface.singleton().getSourceActivity();
      if (str.equals("AndroidPay"))
      {
        APUICommonMethod.popActivity();
        APCommMethod.payErrorCallBack(2, "");
      }
      while (true)
      {
        finish();
        return true;
        if (str.equals("APPayGameInputNumActivity"))
        {
          Intent localIntent1 = new Intent();
          localIntent1.setClass(this, APPayGameInputNumActivity.class);
          startActivity(localIntent1);
          continue;
        }
        if (str.equals("APPayGameListNumActivity"))
        {
          Intent localIntent2 = new Intent();
          localIntent2.setClass(this, APPayGameListNumActivity.class);
          startActivity(localIntent2);
          continue;
        }
        if (!str.equals("APWebBuyActivity"))
          continue;
        Intent localIntent3 = new Intent();
        APWebBuyActivity.loadWebPage = APWebProtocol.WEBPAGE_PAYGAME_LIST;
        localIntent3.setClass(this, APWebBuyActivity.class);
        startActivity(localIntent3);
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    super.onResume();
    APDataReportManager.getInstance().insertData("sdk.payexpress.show", this.saveType);
  }

  protected void onStart()
  {
    super.onStart();
  }

  public void onStop()
  {
    super.onStop();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payExpress.APPayExpressActivity
 * JD-Core Version:    0.6.0
 */
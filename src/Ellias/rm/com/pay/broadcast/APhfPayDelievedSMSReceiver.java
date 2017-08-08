package com.pay.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.pay.common.tool.APBase64;
import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APNetworkManager;
import com.pay.tool.APDataInterface;
import com.pay.tool.APTools;

public class APhfPayDelievedSMSReceiver extends BroadcastReceiver
{
  private Context a;

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.a = paramContext;
    new Bundle();
    APNetworkManager.getInstance().queryHFStatus(APDataInterface.singleton().getOrderInfo().orderId, new a(this));
  }

  public void savePhoneNum(String paramString)
  {
    APLog.e("savePhoneNum", "phoneNum = " + paramString);
    if ((paramString == null) || (paramString.equals("")))
      return;
    String str = APBase64.encode(paramString.getBytes());
    APTools.saveInfo(this.a, "TencentUnipay", "succHFNum", str);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.broadcast.APhfPayDelievedSMSReceiver
 * JD-Core Version:    0.6.0
 */
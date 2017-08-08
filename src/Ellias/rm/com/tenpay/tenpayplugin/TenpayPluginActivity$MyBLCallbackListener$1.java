package com.tenpay.tenpayplugin;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tenpay.a.a.g;
import org.json.JSONObject;

class TenpayPluginActivity$MyBLCallbackListener$1
  implements Runnable
{
  public void run()
  {
    if (this.b != 0)
      TenpayPluginActivity.MyBLCallbackListener.a(this.a).dismissProgressDialog();
    if (this.c != null)
    {
      if ((("28010402".equals(this.c.optString("retcode"))) || ("28010406".equals(this.c.optString("retcode")))) && (TenpayPluginActivity.MyBLCallbackListener.a(this.a).b == 4))
      {
        TenpayPluginActivity.a(TenpayPluginActivity.MyBLCallbackListener.a(this.a), this.c.optString("retmsg"));
        TenpayPluginActivity.MyBLCallbackListener.a(this.a).b = 5;
        TenpayPluginActivity.MyBLCallbackListener.a(this.a).a();
      }
      do
      {
        return;
        if ("66210007".equals(this.c.optString("retcode")))
        {
          if (this.b == 0)
            TenpayPluginActivity.MyBLCallbackListener.a(this.a).dismissProgressDialog();
          TenpayPluginActivity.MyBLCallbackListener.a(this.a).showMyDialog(TenpayResourceUtil.getStringId(TenpayPluginActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_alert"), TenpayResourceUtil.getStringId(TenpayPluginActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_login_error"), new TenpayPluginActivity.MyBLCallbackListener.1.1(this));
          return;
        }
        if ("28010400".equals(this.c.optString("retcode")))
        {
          if (this.b == 0)
            TenpayPluginActivity.MyBLCallbackListener.a(this.a).dismissProgressDialog();
          TenpayPluginActivity.MyBLCallbackListener.a(this.a).showMyDialog(TenpayResourceUtil.getStringId(TenpayPluginActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_alert"), TenpayResourceUtil.getStringId(TenpayPluginActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_pass_time_error"), new TenpayPluginActivity.MyBLCallbackListener.1.2(this));
          return;
        }
        if (this.b == 1)
        {
          if (!"28026210".equals(this.c.optString("retcode")))
            continue;
          if (TenpayPluginActivity.MyBLCallbackListener.a(this.a).b == 5)
          {
            TenpayPluginActivity.a(TenpayPluginActivity.MyBLCallbackListener.a(this.a), 60);
            TenpayPluginActivity.b(TenpayPluginActivity.MyBLCallbackListener.a(this.a));
            TenpayPluginActivity.MyBLCallbackListener.a(this.a).mHandler.postDelayed(TenpayPluginActivity.MyBLCallbackListener.a(this.a).mResendTimer, 1000L);
          }
          if (TenpayPluginActivity.MyBLCallbackListener.a(this.a).b != 5)
          {
            TenpayPluginActivity.MyBLCallbackListener.a(this.a).c = TenpayPluginActivity.MyBLCallbackListener.a(this.a).b;
            TenpayPluginActivity.MyBLCallbackListener.a(this.a).b = 5;
            TenpayPluginActivity.MyBLCallbackListener.a(this.a).a();
            return;
          }
          TenpayPluginActivity.b(TenpayPluginActivity.MyBLCallbackListener.a(this.a), TenpayResourceUtil.getStringId(TenpayPluginActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_alert_kuai_resend_ok"));
          return;
        }
        if ((this.b != 0) || ("0".equals(this.c.optString("retcode"))))
          continue;
        TenpayPluginActivity.MyBLCallbackListener.a(this.a).dismissProgressDialog();
        Bundle localBundle2 = new Bundle();
        localBundle2.putString("trace", TenpayPluginActivity.MyBLCallbackListener.a(this.a).e.toString());
        localBundle2.putInt("pay_type", TenpayPluginActivity.MyBLCallbackListener.a(this.a).getPayType());
        localBundle2.putInt("backfrom", TenpayPluginActivity.c(TenpayPluginActivity.MyBLCallbackListener.a(this.a)));
        if ("-1".equals(this.c.optString("retcode")))
          localBundle2.putString("msg", "网络请求失败");
        while (true)
        {
          TenpayUtil.onCallback(TenpayPluginActivity.MyBLCallbackListener.a(this.a), 4, localBundle2);
          TenpayPluginActivity.MyBLCallbackListener.a(this.a).finish();
          return;
          String str5 = this.c.optString("retmsg");
          if ("".equals(str5))
          {
            localBundle2.putString("msg", "支付网关返回错误");
            continue;
          }
          localBundle2.putString("msg", str5);
        }
      }
      while (!g.a(TenpayPluginActivity.MyBLCallbackListener.a(this.a), this.c));
      switch (this.b)
      {
      case 2:
      case 4:
      case 5:
      default:
        return;
      case 0:
        TenpayPluginActivity.MyBLCallbackListener.a(this.a).mPayGate = this.c;
        TenpayPluginActivity.c(TenpayPluginActivity.MyBLCallbackListener.a(this.a), this.c.optInt("trans_seq"));
        TenpayPluginActivity.b(TenpayPluginActivity.MyBLCallbackListener.a(this.a), this.c.optString("skey"));
        long l = this.c.optLong("time_stamp");
        TenpayPluginActivity.a(TenpayPluginActivity.MyBLCallbackListener.a(this.a), l - System.currentTimeMillis() / 1000L);
        if ((TenpayPluginActivity.MyBLCallbackListener.a(this.a).a != 0) && (TenpayPluginActivity.MyBLCallbackListener.a(this.a).a != 2))
        {
          TenpayPluginActivity.f(TenpayPluginActivity.MyBLCallbackListener.a(this.a));
          return;
        }
        TenpayPluginActivity.g(TenpayPluginActivity.MyBLCallbackListener.a(this.a));
        TenpayPluginActivity.MyBLCallbackListener.a(this.a).dismissProgressDialog();
        return;
      case 1:
        TenpayPluginActivity.MyBLCallbackListener.a(this.a).doSuccess(this.c);
        return;
      case 3:
        TenpayPluginActivity.MyBLCallbackListener.a(this.a).doSuccess(this.c);
        return;
      case 6:
        if (this.c.optInt("send_flag", 1) == 1)
        {
          TenpayPluginActivity.MyBLCallbackListener.a(this.a).mKuaijiePre = this.c;
          if ((TenpayPluginActivity.h(TenpayPluginActivity.MyBLCallbackListener.a(this.a)) != null) && (TenpayPluginActivity.h(TenpayPluginActivity.MyBLCallbackListener.a(this.a)).getVisibility() == 0))
          {
            TenpayPluginActivity.h(TenpayPluginActivity.MyBLCallbackListener.a(this.a)).setVisibility(8);
            String str3 = TenpayPluginActivity.i(TenpayPluginActivity.MyBLCallbackListener.a(this.a)).getText().toString();
            if ((TenpayPluginActivity.j(TenpayPluginActivity.MyBLCallbackListener.a(this.a)) != null) && (str3.length() > 10))
            {
              StringBuilder localStringBuilder2 = new StringBuilder();
              localStringBuilder2.append(str3.substring(0, 3));
              localStringBuilder2.append("******");
              localStringBuilder2.append(str3.substring(-2 + str3.length()));
              String str4 = TenpayPluginActivity.MyBLCallbackListener.a(this.a).getResources().getString(TenpayResourceUtil.getStringId(TenpayPluginActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_yz_phone_text"));
              TextView localTextView2 = TenpayPluginActivity.j(TenpayPluginActivity.MyBLCallbackListener.a(this.a));
              Object[] arrayOfObject2 = new Object[1];
              arrayOfObject2[0] = localStringBuilder2.toString();
              localTextView2.setText(String.format(str4, arrayOfObject2));
            }
            TenpayPluginActivity.MyBLCallbackListener.a(this.a).a();
          }
          if ((TenpayPluginActivity.k(TenpayPluginActivity.MyBLCallbackListener.a(this.a)) != null) && (TenpayPluginActivity.k(TenpayPluginActivity.MyBLCallbackListener.a(this.a)).getVisibility() == 0))
            TenpayPluginActivity.k(TenpayPluginActivity.MyBLCallbackListener.a(this.a)).setVisibility(8);
          if (TenpayPluginActivity.MyBLCallbackListener.a(this.a).b == 5)
          {
            TenpayPluginActivity.a(TenpayPluginActivity.MyBLCallbackListener.a(this.a), 60);
            TenpayPluginActivity.b(TenpayPluginActivity.MyBLCallbackListener.a(this.a));
            TenpayPluginActivity.MyBLCallbackListener.a(this.a).mHandler.postDelayed(TenpayPluginActivity.MyBLCallbackListener.a(this.a).mResendTimer, 1000L);
          }
          if (TenpayPluginActivity.MyBLCallbackListener.a(this.a).b != 5)
          {
            TenpayPluginActivity.MyBLCallbackListener.a(this.a).c = TenpayPluginActivity.MyBLCallbackListener.a(this.a).b;
            TenpayPluginActivity.MyBLCallbackListener.a(this.a).b = 5;
            String str1 = TenpayPluginActivity.MyBLCallbackListener.a(this.a).mKuaijiePre.optString("mobile");
            if ((TenpayPluginActivity.j(TenpayPluginActivity.MyBLCallbackListener.a(this.a)) != null) && (str1.length() > 10))
            {
              StringBuilder localStringBuilder1 = new StringBuilder();
              localStringBuilder1.append(str1.substring(0, 3));
              localStringBuilder1.append("******");
              localStringBuilder1.append(str1.substring(-2 + str1.length()));
              String str2 = TenpayPluginActivity.MyBLCallbackListener.a(this.a).getResources().getString(TenpayResourceUtil.getStringId(TenpayPluginActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_yz_phone_text"));
              TextView localTextView1 = TenpayPluginActivity.j(TenpayPluginActivity.MyBLCallbackListener.a(this.a));
              Object[] arrayOfObject1 = new Object[1];
              arrayOfObject1[0] = localStringBuilder1.toString();
              localTextView1.setText(String.format(str2, arrayOfObject1));
            }
            TenpayPluginActivity.MyBLCallbackListener.a(this.a).a();
            return;
          }
          TenpayPluginActivity.b(TenpayPluginActivity.MyBLCallbackListener.a(this.a), TenpayResourceUtil.getStringId(TenpayPluginActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_alert_kuai_resend_ok"));
          return;
        }
        TenpayPluginActivity.MyBLCallbackListener.a(this.a).doSuccess(this.c);
        return;
      case 7:
      }
      if (TenpayPluginActivity.l(TenpayPluginActivity.MyBLCallbackListener.a(this.a)))
      {
        TenpayPluginActivity.a(TenpayPluginActivity.MyBLCallbackListener.a(this.a), false);
        TenpayPluginActivity.a(TenpayPluginActivity.MyBLCallbackListener.a(this.a), this.c);
        TenpayPluginActivity.MyBLCallbackListener.a(this.a).mLastPayType = TenpayPluginActivity.MyBLCallbackListener.a(this.a).a;
        TenpayPluginActivity.MyBLCallbackListener.a(this.a).a = 12;
        TenpayPluginActivity.MyBLCallbackListener.a(this.a).gotoBind(true);
        return;
      }
      TenpayPluginActivity.MyBLCallbackListener.a(this.a).initBanks(this.c);
      return;
    }
    TenpayPluginActivity.MyBLCallbackListener.a(this.a).dismissProgressDialog();
    if (this.b == 0)
    {
      Bundle localBundle1 = new Bundle();
      localBundle1.putInt("pay_type", TenpayPluginActivity.MyBLCallbackListener.a(this.a).getPayType());
      localBundle1.putInt("backfrom", TenpayPluginActivity.c(TenpayPluginActivity.MyBLCallbackListener.a(this.a)));
      localBundle1.putString("msg", "网络请求失败");
      TenpayUtil.onCallback(TenpayPluginActivity.MyBLCallbackListener.a(this.a), 4, localBundle1);
      if ((TenpayPluginActivity.d(TenpayPluginActivity.MyBLCallbackListener.a(this.a)) != null) && (TenpayPluginActivity.d(TenpayPluginActivity.MyBLCallbackListener.a(this.a)).isShowing()))
      {
        TenpayPluginActivity.d(TenpayPluginActivity.MyBLCallbackListener.a(this.a)).dismiss();
        TenpayPluginActivity.a(TenpayPluginActivity.MyBLCallbackListener.a(this.a), null);
      }
      TenpayPluginActivity.MyBLCallbackListener.a(this.a).finish();
      return;
    }
    TenpayPluginActivity.b(TenpayPluginActivity.MyBLCallbackListener.a(this.a), TenpayResourceUtil.getStringId(TenpayPluginActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_network_error"));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.MyBLCallbackListener.1
 * JD-Core Version:    0.6.0
 */
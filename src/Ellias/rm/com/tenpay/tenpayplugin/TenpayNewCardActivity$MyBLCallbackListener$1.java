package com.tenpay.tenpayplugin;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.tenpay.a.a.g;
import com.tenpay.tenpayplugin.view.ClearableEditText;
import com.tenpay.tenpayplugin.view.TenpaySecureEditText;
import java.io.FileOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;

class TenpayNewCardActivity$MyBLCallbackListener$1
  implements Runnable
{
  public void run()
  {
    if (this.b != 0)
      TenpayNewCardActivity.MyBLCallbackListener.a(this.a).dismissProgressDialog();
    if (this.c != null)
    {
      if (("28010402".equals(this.c.optString("retcode"))) && (TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b == 4))
      {
        TenpayNewCardActivity.a(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), this.c.optString("retmsg"));
        TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b = 5;
        TenpayNewCardActivity.MyBLCallbackListener.a(this.a).a();
        TenpayNewCardActivity.c(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)).performClick();
      }
      do
      {
        do
        {
          return;
          if ("66210007".equals(this.c.optString("retcode")))
          {
            if (this.b == 0)
              TenpayNewCardActivity.MyBLCallbackListener.a(this.a).dismissProgressDialog();
            TenpayNewCardActivity.MyBLCallbackListener.a(this.a).showMyDialog(TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_alert"), TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_login_error"), new TenpayNewCardActivity.MyBLCallbackListener.1.1(this));
            return;
          }
          if ("28010400".equals(this.c.optString("retcode")))
          {
            if (this.b == 0)
              TenpayNewCardActivity.MyBLCallbackListener.a(this.a).dismissProgressDialog();
            TenpayNewCardActivity.MyBLCallbackListener.a(this.a).showMyDialog(TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_alert"), TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_pass_time_error"), new TenpayNewCardActivity.MyBLCallbackListener.1.2(this));
            return;
          }
          if ((this.b != 1) || (!"28026210".equals(this.c.optString("retcode"))))
            continue;
          if (TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b == 5)
          {
            TenpayNewCardActivity.a(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), 60);
            TenpayNewCardActivity.b(TenpayNewCardActivity.MyBLCallbackListener.a(this.a));
            TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mHandler.postDelayed(TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mResendTimer, 1000L);
          }
          if (TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b != 5)
          {
            TenpayNewCardActivity.MyBLCallbackListener.a(this.a).c = TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b;
            TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b = 5;
            TenpayNewCardActivity.MyBLCallbackListener.a(this.a).a();
            return;
          }
          TenpayNewCardActivity.b(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_alert_kuai_resend_ok"));
          return;
        }
        while (!g.a(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), this.c));
        switch (this.b)
        {
        case 7:
        case 8:
        case 10:
        default:
          return;
        case 2:
          TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mKuaijiePre = this.c;
          if (TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b == 5)
          {
            TenpayNewCardActivity.a(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), 60);
            TenpayNewCardActivity.b(TenpayNewCardActivity.MyBLCallbackListener.a(this.a));
            TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mHandler.postDelayed(TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mResendTimer, 1000L);
          }
          if (TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b != 5)
          {
            TenpayNewCardActivity.MyBLCallbackListener.a(this.a).c = TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b;
            TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b = 5;
            String str5 = TenpayNewCardActivity.f(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)).getText().toString();
            if ((TenpayNewCardActivity.g(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)) != null) && (str5.length() > 10))
            {
              StringBuilder localStringBuilder3 = new StringBuilder();
              localStringBuilder3.append(str5.substring(0, 3));
              localStringBuilder3.append("******");
              localStringBuilder3.append(str5.substring(-2 + str5.length()));
              String str6 = TenpayNewCardActivity.MyBLCallbackListener.a(this.a).getResources().getString(TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_yz_phone_text"));
              TextView localTextView3 = TenpayNewCardActivity.g(TenpayNewCardActivity.MyBLCallbackListener.a(this.a));
              Object[] arrayOfObject3 = new Object[1];
              arrayOfObject3[0] = localStringBuilder3.toString();
              localTextView3.setText(String.format(str6, arrayOfObject3));
            }
            TenpayNewCardActivity.MyBLCallbackListener.a(this.a).a();
            return;
          }
          TenpayNewCardActivity.b(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_alert_kuai_resend_ok"));
          return;
        case 3:
          TenpayNewCardActivity.MyBLCallbackListener.a(this.a).doSuccess(this.c);
          return;
        case 11:
          TenpayNewCardActivity.a(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), this.c.optJSONArray("detail"));
        case 4:
        case 5:
        case 6:
        case 9:
        }
      }
      while (TenpayNewCardActivity.h(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)) == null);
      try
      {
        SharedPreferences.Editor localEditor = TenpayNewCardActivity.MyBLCallbackListener.a(this.a).getSharedPreferences("unipay_tenpay_prefer", 0).edit();
        localEditor.putInt("bin_ver", TenpayNewCardActivity.i(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)));
        localEditor.commit();
        FileOutputStream localFileOutputStream = TenpayNewCardActivity.MyBLCallbackListener.a(this.a).openFileOutput("unipay_tenpay_card_bin", 0);
        localFileOutputStream.write(TenpayNewCardActivity.h(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)).toString().getBytes());
        localFileOutputStream.close();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mNewUserPre = this.c;
      if (TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b == 5)
      {
        TenpayNewCardActivity.a(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), 60);
        TenpayNewCardActivity.b(TenpayNewCardActivity.MyBLCallbackListener.a(this.a));
        TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mHandler.postDelayed(TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mResendTimer, 1000L);
      }
      if (TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b != 5)
      {
        TenpayNewCardActivity.MyBLCallbackListener.a(this.a).c = TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b;
        TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b = 5;
        if (TenpayNewCardActivity.f(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)) != null)
        {
          String str3 = TenpayNewCardActivity.f(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)).getText().toString();
          if ((TenpayNewCardActivity.g(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)) != null) && (str3.length() > 10))
          {
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append(str3.substring(0, 3));
            localStringBuilder2.append("******");
            localStringBuilder2.append(str3.substring(-2 + str3.length()));
            String str4 = TenpayNewCardActivity.MyBLCallbackListener.a(this.a).getResources().getString(TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_yz_phone_text"));
            TextView localTextView2 = TenpayNewCardActivity.g(TenpayNewCardActivity.MyBLCallbackListener.a(this.a));
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = localStringBuilder2.toString();
            localTextView2.setText(String.format(str4, arrayOfObject2));
          }
        }
        TenpayNewCardActivity.MyBLCallbackListener.a(this.a).a();
        return;
      }
      TenpayNewCardActivity.b(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_alert_kuai_resend_ok"));
      return;
      TenpayNewCardActivity.MyBLCallbackListener.a(this.a).doSuccess(this.c);
      return;
      if (this.c.optInt("send_flag", 1) == 1)
      {
        TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mKuaijiePre = this.c;
        if (TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b == 5)
        {
          TenpayNewCardActivity.a(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), 60);
          TenpayNewCardActivity.b(TenpayNewCardActivity.MyBLCallbackListener.a(this.a));
          TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mHandler.postDelayed(TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mResendTimer, 1000L);
        }
        if (TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b != 5)
        {
          TenpayNewCardActivity.MyBLCallbackListener.a(this.a).c = TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b;
          TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b = 5;
          String str1 = TenpayNewCardActivity.MyBLCallbackListener.a(this.a).mKuaijiePre.optString("mobile");
          if ((TenpayNewCardActivity.g(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)) != null) && (str1.length() > 10))
          {
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append(str1.substring(0, 3));
            localStringBuilder1.append("******");
            localStringBuilder1.append(str1.substring(-2 + str1.length()));
            String str2 = TenpayNewCardActivity.MyBLCallbackListener.a(this.a).getResources().getString(TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_yz_phone_text"));
            TextView localTextView1 = TenpayNewCardActivity.g(TenpayNewCardActivity.MyBLCallbackListener.a(this.a));
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = localStringBuilder1.toString();
            localTextView1.setText(String.format(str2, arrayOfObject1));
          }
          TenpayNewCardActivity.MyBLCallbackListener.a(this.a).a();
          return;
        }
        TenpayNewCardActivity.b(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_alert_kuai_resend_ok"));
        return;
      }
      TenpayNewCardActivity.MyBLCallbackListener.a(this.a).doSuccess(this.c);
      return;
      TenpayNewCardActivity.MyBLCallbackListener.a(this.a).b = 4;
      TenpayNewCardActivity.j(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)).setVisibility(0);
      TenpayNewCardActivity.k(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)).setVisibility(8);
      TenpayNewCardActivity.j(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)).setHint(TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_set_pass_hint"));
      TenpayNewCardActivity.l(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)).setText(TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_set_pass"));
      TenpayNewCardActivity.m(TenpayNewCardActivity.MyBLCallbackListener.a(this.a)).setText(TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_set_pass_next_hint"));
      TenpayNewCardActivity.MyBLCallbackListener.a(this.a).a();
      return;
    }
    TenpayNewCardActivity.MyBLCallbackListener.a(this.a).dismissProgressDialog();
    TenpayNewCardActivity.b(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), TenpayResourceUtil.getStringId(TenpayNewCardActivity.MyBLCallbackListener.a(this.a), "unipay_tenpay_network_error"));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.MyBLCallbackListener.1
 * JD-Core Version:    0.6.0
 */
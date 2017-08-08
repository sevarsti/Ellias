package com.pay.ui.channel;

import android.os.Handler;
import android.os.Message;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.pay.tool.APCommMethod;
import com.pay.tool.APTools;
import java.lang.ref.WeakReference;

final class l extends Handler
{
  private WeakReference a;
  private APHFPayActivity b = null;

  public l(APHFPayActivity paramAPHFPayActivity)
  {
    this.a = new WeakReference(paramAPHFPayActivity);
  }

  public final void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    this.b = ((APHFPayActivity)this.a.get());
    APHFPayActivity.d(this.b, false);
    switch (paramMessage.what)
    {
    default:
    case 1:
    }
    float f;
    while (true)
    {
      return;
      if ((APHFPayActivity.r(this.b).equals("")) || (APHFPayActivity.s(this.b).equals("")))
        APHFPayActivity.t(this.b).setVisibility(8);
      while (true)
      {
        APHFPayActivity.u(this.b).setVisibility(8);
        APHFPayActivity.a(this.b).setCursorVisible(false);
        APHFPayActivity.i(this.b).hideSoftInputFromWindow(APHFPayActivity.a(this.b).getWindowToken(), 0);
        switch (APHFPayActivity.v(this.b))
        {
        default:
          APHFPayActivity.j(this.b).setText("");
          APHFPayActivity.k(this.b).setText("");
          APHFPayActivity.a(this.b).setText("");
          APHFPayActivity.g(this.b).setEnabled(false);
          return;
          APHFPayActivity.t(this.b).setVisibility(0);
          APHFPayActivity.j(this.b).setText(APHFPayActivity.r(this.b));
          APHFPayActivity.k(this.b).setText(APHFPayActivity.s(this.b));
        case 0:
        case 10008:
        }
      }
      if ((!APHFPayActivity.w(this.b).equals("2")) || ((APHFPayActivity.f(this.b) != 4) && (APHFPayActivity.f(this.b) != 5)))
        break;
      APHFPayActivity.l(this.b).setText("第一步:发送" + APHFPayActivity.e(this.b) + "\n到" + APHFPayActivity.d(this.b) + ",您将收到系统回复短信");
      this.b.a.setVisibility(0);
      APHFPayActivity.g(this.b).setEnabled(true);
      APHFPayActivity.g(this.b).setText("发送短信");
      APHFPayActivity.g(this.b).setBackgroundResource(APCommMethod.getDrawableId(this.b, "unipay_drawable_embtn"));
      APHFPayActivity.b(this.b, true);
      if ((APHFPayActivity.f(this.b) != 4) && (APHFPayActivity.f(this.b) != 5))
        continue;
      if (this.b != null)
      {
        f = Integer.parseInt(APHFPayActivity.m(this.b)) / 100.0F;
        if (f >= 9.989999999999999E-006D)
          break label569;
        APHFPayActivity.n(this.b).setVisibility(8);
      }
      label479: if (!APHFPayActivity.a().equals("1"))
        break label683;
      this.b.b.setText("注:本服务为一次性支付，到期后不会自动续费");
    }
    while (true)
    {
      this.b.b.setVisibility(0);
      return;
      APHFPayActivity.l(this.b).setText("发送" + APHFPayActivity.e(this.b) + "\n到" + APHFPayActivity.d(this.b) + "即可完成本次支付");
      break;
      label569: APHFPayActivity.n(this.b).setVisibility(0);
      String str1 = APTools.formatFloat(f);
      String[] arrayOfString = str1.split("\\.");
      String str2 = "";
      if (arrayOfString.length > 1)
        str1 = arrayOfString[0];
      if (arrayOfString.length == 2)
      {
        String str3 = arrayOfString[1];
        str2 = "." + str3;
      }
      APHFPayActivity.o(this.b).setText(str1);
      APHFPayActivity.p(this.b).setText(str2);
      APHFPayActivity.q(this.b).setText("￥");
      break label479;
      label683: this.b.b.setText("注:本服务为按月订阅，您可以通过短信退订");
    }
    APHFPayActivity.l(this.b).setText("对不起暂时不支持该地区");
    APHFPayActivity.g(this.b).setEnabled(false);
    APHFPayActivity.g(this.b).setBackgroundResource(APCommMethod.getDrawableId(this.b, "unipay_pic_disbtnbg"));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.l
 * JD-Core Version:    0.6.0
 */
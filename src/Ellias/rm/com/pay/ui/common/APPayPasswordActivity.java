package com.pay.ui.common;

import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.pay.AndroidPay;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;

public class APPayPasswordActivity extends APActivity
{
  private TextWatcher a = new u(this);
  protected int saveType;
  protected EditText unipay_id_apPayPWDEdit;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
    {
      finish();
      return;
    }
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_phonepwd"));
    APDataInterface.singleton().setMbSig("");
    this.saveType = APDataInterface.singleton().getOrderInfo().saveType;
    if (this.saveType == 0)
      initGameTitle();
    while (true)
    {
      this.unipay_id_apPayPWDEdit = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_apPayPWDEdit")));
      this.unipay_id_apPayPWDEdit.setHint("手机支付密码");
      this.unipay_id_apPayPWDEdit.addTextChangedListener(this.a);
      ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_ContentLayout"))).setOnClickListener(new v(this));
      Button localButton = (Button)findViewById(APCommMethod.getId(this, "unipay_id_apSubmitBottom"));
      localButton.setText("确定");
      localButton.setOnClickListener(new w(this));
      ((ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_apDelBtn"))).setOnClickListener(new x(this));
      titleAnimation();
      this.unipay_id_apPayPWDEdit.setHintTextColor(-6710887);
      editLight(APCommMethod.getId(this, "unipay_id_apEdit"));
      return;
      if (this.saveType == 1)
      {
        initGoodsTitle();
        continue;
      }
      if ((this.saveType == 3) || (this.saveType == 2))
      {
        initAccountTitle(this.saveType);
        continue;
      }
      if ((this.saveType != 4) && (this.saveType != 5))
        continue;
      initMonthTitle();
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APPayPasswordActivity
 * JD-Core Version:    0.6.0
 */
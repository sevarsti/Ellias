package com.pay.ui.channel;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.pay.AndroidPay;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.common.APScrollView;
import com.pay.ui.common.APTransformationMethod;
import com.pay.ui.common.APUICommonMethod;

public class APQCardPayActivity extends APRecoChannelActivity
  implements TextView.OnEditorActionListener
{
  int a = 3;
  int b = 4;
  private EditText c;
  private EditText d;
  private String e;
  private String f;
  private View.OnTouchListener g = new A(this);
  private TextWatcher h;
  private TextWatcher i;
  protected int saveNumber = 0;
  public float saveRate = 1.0F;

  public APQCardPayActivity()
  {
    new C(this);
    this.h = new D(this);
    this.i = new E(this);
  }

  private void a()
  {
    this.d.clearFocus();
    this.c.requestFocus();
    c();
  }

  private void b()
  {
    this.c.clearFocus();
    this.d.requestFocus();
    c();
  }

  private void c()
  {
    ((InputMethodManager)getSystemService("input_method")).showSoftInput(this.c, 2);
  }

  protected boolean checkInput()
  {
    this.c = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_apQCardNumEdit")));
    this.d = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_apQCardPWDEdit")));
    this.e = this.c.getText().toString().replace("\n", "").trim();
    this.f = this.d.getText().toString().replace("\n", "").trim();
    if (this.e.length() == 0)
    {
      APUICommonMethod.showToast(this, "请输入QQ卡卡号");
      a();
      return false;
    }
    if (this.e.length() < 8)
    {
      APUICommonMethod.showToast(this, "请输入正确的QQ卡卡号");
      a();
      return false;
    }
    if (this.f.length() == 0)
    {
      APUICommonMethod.showToast(this, "请输入QQ卡密码");
      b();
      return false;
    }
    if (this.f.length() < 9)
    {
      APUICommonMethod.showToast(this, "请输入正确的QQ卡密码");
      b();
      return false;
    }
    if (this.saveNumber == 0)
    {
      APUICommonMethod.showToast(this, "请输入充值数额");
      return false;
    }
    return isValidSaveNumber();
  }

  protected void doPay()
  {
    if (checkInput())
      qCardPay(this.e, this.f, this.orderInfo.saveNum);
  }

  protected void initUI()
  {
    titleAnimation();
    ((Button)findViewById(APCommMethod.getId(this, "unipay_id_BuyBottomBtn"))).setOnClickListener(new F(this));
    APScrollView localAPScrollView = (APScrollView)findViewById(APCommMethod.getId(this, "unipay_id_ScrollView"));
    APTransformationMethod localAPTransformationMethod = new APTransformationMethod();
    try
    {
      this.saveNumber = Integer.parseInt(this.orderInfo.saveNum);
      label64: this.c = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_apQCardNumEdit")));
      this.c.addTextChangedListener(this.h);
      this.c.setTransformationMethod(localAPTransformationMethod);
      this.c.setOnTouchListener(new G(this));
      this.c.setOnFocusChangeListener(new H(this));
      this.d = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_apQCardPWDEdit")));
      this.d.addTextChangedListener(this.i);
      this.d.setOnEditorActionListener(this);
      this.d.setTransformationMethod(localAPTransformationMethod);
      this.d.setOnTouchListener(this.g);
      this.d.setOnFocusChangeListener(new I(this, localAPScrollView));
      if (getResources().getConfiguration().orientation == 2)
      {
        EditText localEditText3 = this.c;
        InputFilter[] arrayOfInputFilter3 = new InputFilter[1];
        arrayOfInputFilter3[0] = new InputFilter.LengthFilter(10);
        localEditText3.setFilters(arrayOfInputFilter3);
        EditText localEditText4 = this.d;
        InputFilter[] arrayOfInputFilter4 = new InputFilter[1];
        arrayOfInputFilter4[0] = new InputFilter.LengthFilter(12);
        localEditText4.setFilters(arrayOfInputFilter4);
      }
      while (true)
      {
        ((ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_CardNumDel"))).setOnClickListener(new J(this));
        ((ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_CardPWDDel"))).setOnClickListener(new B(this));
        return;
        EditText localEditText1 = this.c;
        InputFilter[] arrayOfInputFilter1 = new InputFilter[1];
        arrayOfInputFilter1[0] = new InputFilter.LengthFilter(13);
        localEditText1.setFilters(arrayOfInputFilter1);
        EditText localEditText2 = this.d;
        InputFilter[] arrayOfInputFilter2 = new InputFilter[1];
        arrayOfInputFilter2[0] = new InputFilter.LengthFilter(14);
        localEditText2.setFilters(arrayOfInputFilter2);
      }
    }
    catch (Exception localException)
    {
      break label64;
    }
  }

  protected boolean isValidSaveNumber()
  {
    return true;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
    {
      finish();
      return;
    }
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_qqcard_pay"));
    this.saveType = APDataInterface.singleton().getOrderInfo().saveType;
    if (this.saveType == 0)
      initGameTitle();
    while (true)
    {
      initUI();
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

  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    switch (paramInt)
    {
    default:
    case 0:
    case 4:
    case 6:
    }
    while (true)
    {
      return true;
      localInputMethodManager.hideSoftInputFromWindow(this.d.getWindowToken(), 0);
      continue;
      localInputMethodManager.hideSoftInputFromWindow(this.d.getWindowToken(), 0);
      continue;
      localInputMethodManager.hideSoftInputFromWindow(this.d.getWindowToken(), 0);
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      APDataReportManager.getInstance().insertData("sdk.qcard.keyback", this.saveType);
      finish();
      overridePendingTransition(APCommMethod.getAnimId(this, "unipay_anim_in_from_left"), APCommMethod.getAnimId(this, "unipay_anim_out_to_right"));
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onStart()
  {
    APDataReportManager.getInstance().insertData("sdk.qcard.show", this.saveType, null, "4", null);
    super.onStart();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.APQCardPayActivity
 * JD-Core Version:    0.6.0
 */
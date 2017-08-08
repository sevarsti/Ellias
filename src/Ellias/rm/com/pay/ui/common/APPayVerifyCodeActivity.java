package com.pay.ui.common;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.pay.AndroidPay;
import com.pay.common.tool.APBase64;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

public class APPayVerifyCodeActivity extends APActivity
  implements TextView.OnEditorActionListener
{
  private ImageView a;
  private EditText b;
  private PopupWindow c;
  private View d;
  private String e = "";
  private String f = "";
  private String g = "";
  private int h = -1;
  private View.OnTouchListener i = new y(this);
  private TextWatcher j = new z(this);

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getKeyCode() == 4) && (this.c != null))
      this.c.dismiss();
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
    {
      finish();
      return;
    }
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_yzm"));
    this.d = ((LayoutInflater)getSystemService("layout_inflater")).inflate(APCommMethod.getLayoutId(this, "unipay_layout_popwindow"), null);
    this.c = new PopupWindow(this.d, -2, -2);
    this.b = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_apVeryCodeEdit")));
    EditText localEditText = this.b;
    InputFilter[] arrayOfInputFilter = new InputFilter[1];
    arrayOfInputFilter[0] = new InputFilter.LengthFilter(4);
    localEditText.setFilters(arrayOfInputFilter);
    this.b.addTextChangedListener(this.j);
    this.b.setOnEditorActionListener(this);
    this.b.setOnTouchListener(this.i);
    ((ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_apCodeDel"))).setOnClickListener(new A(this));
    this.a = ((ImageView)findViewById(APCommMethod.getId(this, "unipay_id_apVeryCodeImg")));
    this.a.setOnClickListener(new B(this));
    TextView localTextView = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_change"));
    localTextView.setText("看不清，换一张");
    localTextView.setOnClickListener(new C(this));
    Bundle localBundle = getIntent().getExtras();
    this.e = localBundle.getString("vs");
    this.f = localBundle.getString("vc");
    this.g = localBundle.getString("pay_method");
    if (this.g.equals("qqcard"))
      this.h = 4;
    while (true)
    {
      if (Boolean.valueOf(localBundle.getBoolean("vcerror")).booleanValue())
        APUICommonMethod.showToast(this, "验证码输入有误");
      if ((!this.f.equals("")) && (this.f.length() > 20))
      {
        if (this.f.length() % 2 != 0)
          this.f += "F";
        byte[] arrayOfByte = APBase64.decode(this.f);
        Bitmap localBitmap = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length, null);
        this.a.setImageBitmap(localBitmap);
        ((ImageView)this.d.findViewById(APCommMethod.getId(this, "unipay_id_apImgPop"))).setImageBitmap(localBitmap);
      }
      ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_apContent"))).setOnClickListener(new D(this));
      Button localButton = (Button)findViewById(APCommMethod.getId(this, "unipay_id_apVeryCodeSureBtn"));
      localButton.setText("确定");
      localButton.setOnClickListener(new E(this));
      editLight(APCommMethod.getId(this, "unipay_id_apVerifyLayout"));
      return;
      if (!this.g.equals("mcard"))
        continue;
      this.h = 5;
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
      localInputMethodManager.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
      if (this.c == null)
        continue;
      this.c.dismiss();
      continue;
      localInputMethodManager.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
      continue;
      localInputMethodManager.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
      if (this.c == null)
        continue;
      this.c.dismiss();
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      APDataReportManager.getInstance().insertData("sdk.vc.keyback", APDataInterface.singleton().getOrderInfo().saveType, null, String.valueOf(this.h), null);
      if (this.c != null)
        this.c.dismiss();
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    super.onResume();
    APDataReportManager.getInstance().insertData("sdk.vc.show", APDataInterface.singleton().getOrderInfo().saveType, null, String.valueOf(this.h), null);
    if (getResources().getConfiguration().orientation == 2)
    {
      dismissInput();
      return;
    }
    showInputDelay(this.b, 0);
  }

  public void onStart()
  {
    super.onStart();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APPayVerifyCodeActivity
 * JD-Core Version:    0.6.0
 */
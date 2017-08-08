package com.pay.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.common.APActivity;

public class APLoginVerifyCodeActivity extends APActivity
{
  private ImageView a;
  private EditText b;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_yzm"));
    this.b = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_apVeryCodeEdit")));
    this.b.setHint("输入以下字符，不分区大小写");
    EditText localEditText = this.b;
    InputFilter[] arrayOfInputFilter = new InputFilter[1];
    arrayOfInputFilter[0] = new InputFilter.LengthFilter(4);
    localEditText.setFilters(arrayOfInputFilter);
    this.a = ((ImageView)findViewById(APCommMethod.getId(this, "unipay_id_apVeryCodeImg")));
    this.a.setOnClickListener(new APLoginVerifyCodeActivity.1(this));
    TextView localTextView = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_change"));
    localTextView.setText("看不清，换一张");
    localTextView.setOnClickListener(new APLoginVerifyCodeActivity.2(this));
    byte[] arrayOfByte = getIntent().getExtras().getByteArray("vc");
    if (arrayOfByte != null)
    {
      Bitmap localBitmap = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length);
      this.a.setImageBitmap(localBitmap);
    }
    Button localButton = (Button)findViewById(APCommMethod.getId(this, "unipay_id_apVeryCodeSureBtn"));
    localButton.setText("确定");
    localButton.setOnClickListener(new APLoginVerifyCodeActivity.3(this));
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    APDataReportManager.getInstance().insertData("sdk.loginvc.keyback", APDataInterface.singleton().getOrderInfo().saveType);
    if (paramInt == 4)
      finish();
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    APDataReportManager.getInstance().insertData("sdk.loginvc.show", APDataInterface.singleton().getOrderInfo().saveType);
    super.onResume();
  }

  public void onStart()
  {
    super.onStart();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginVerifyCodeActivity
 * JD-Core Version:    0.6.0
 */
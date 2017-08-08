package com.pay.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APTools;
import com.pay.ui.common.APActivity;

public class APLoginActivity extends APActivity
{
  private EditText a;
  private EditText b;
  private CheckBox c;
  private APWtLogin d;
  private boolean e = true;
  private int f = -1;
  private TextWatcher g = new APLoginActivity.1(this);
  private APWtLoginListener h = new APLoginActivity.2(this);

  private void a(byte[] paramArrayOfByte)
  {
    Intent localIntent = new Intent(this, APLoginVerifyCodeActivity.class);
    Bundle localBundle = new Bundle();
    localBundle.putByteArray("vc", paramArrayOfByte);
    localIntent.putExtras(localBundle);
    startActivityForResult(localIntent, 110);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramIntent == null);
    byte[] arrayOfByte;
    do
    {
      do
      {
        return;
        if ((paramInt1 != 110) || (paramInt2 != 10002))
          continue;
        String str = paramIntent.getExtras().getString("vc");
        if (str.length() <= 0)
          continue;
        this.d.checkVerifyCode(str);
      }
      while ((paramInt1 != 110) || (paramInt2 != 10003));
      arrayOfByte = this.d.getVerifyCodeImg(this.a.getText().toString().trim());
    }
    while (arrayOfByte == null);
    a(arrayOfByte);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_login"));
    this.d = new APWtLogin(this, this.h);
    this.a = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_LoginUin")));
    this.b = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_LoginPWD")));
    this.c = ((CheckBox)findViewById(APCommMethod.getId(this, "unipay_id_PWDLock")));
    Button localButton = (Button)findViewById(APCommMethod.getId(this, "unipay_id_LoginBtn"));
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null)
    {
      boolean bool3 = localBundle.getBoolean("editUin");
      this.f = localBundle.getInt("curChannel");
      if (!bool3)
        this.a.setEnabled(false);
      String str3 = localBundle.getString("enSureBtn");
      if (str3.length() != 0)
      {
        this.e = false;
        localButton.setText(str3);
      }
    }
    else
    {
      boolean bool1 = getSharedPreferences("TencentUnipay", 0).getBoolean("REMEMBERPWD", true);
      this.c.setChecked(bool1);
      this.c.setOnClickListener(new APLoginActivity.3(this));
      String str1 = this.d.getLastUin();
      if ((str1 != null) && (str1.length() != 0))
        this.a.setText(str1);
      boolean bool2 = this.d.isSigValid();
      String str2 = APTools.readInfo(this, "TencentUnipay", "OPENID");
      if (!APDataInterface.singleton().getUserInfo().openId.equals(str2))
      {
        this.d.clearLoginData();
        bool2 = false;
      }
      if (this.a.getText().toString().length() == 0)
        this.a.setEnabled(true);
      if ((!bool2) || (!this.c.isChecked()))
        break label454;
      this.b.setText("00000");
      label334: localButton.setOnClickListener(new APLoginActivity.4(this));
      ((Button)findViewById(APCommMethod.getId(this, "unipay_id_LoginCancel"))).setOnClickListener(new APLoginActivity.5(this));
      this.b.addTextChangedListener(this.g);
      if (this.b.getText().toString().length() > 0)
        break label467;
      this.c.setVisibility(4);
    }
    while (true)
    {
      this.b.setOnFocusChangeListener(new APLoginActivity.6(this));
      this.a.setOnFocusChangeListener(new APLoginActivity.7(this));
      return;
      this.e = true;
      localButton.setText("登录");
      break;
      label454: this.b.setText("");
      break label334;
      label467: this.c.setVisibility(0);
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
      APDataReportManager.getInstance().insertData("sdk.login.keyback", APDataInterface.singleton().getOrderInfo().saveType, null, String.valueOf(this.f), null);
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    super.onResume();
    APDataReportManager.getInstance().insertData("sdk.login.show", APDataInterface.singleton().getOrderInfo().saveType, null, String.valueOf(this.f), null);
    if (getResources().getConfiguration().orientation == 2)
      getWindow().setSoftInputMode(3);
    do
      return;
    while ((getResources().getConfiguration().orientation != 1) || (this.a == null));
    showInputDelay(this.a, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginActivity
 * JD-Core Version:    0.6.0
 */
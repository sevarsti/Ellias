package com.pay.ui.channel;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.pay.AndroidPay;
import com.pay.buyManager.APPayManager;
import com.pay.common.tool.APBase64;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APBaseHttpAns;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.network.modle.APSaveAns;
import com.pay.network.modle.APhfPayAns;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APTools;
import com.pay.ui.common.APAlertDialog;
import com.pay.ui.common.APAlertDialog.Builder;
import com.pay.ui.common.APHFPaySuccessActivity;
import com.pay.ui.common.APTransformationMethod;
import com.pay.ui.common.APUICommonMethod;

public class APHFPayActivity extends APRecoChannelActivity
  implements IAPHttpAnsObserver
{
  public static final int GET_XIAODAN_RETURN = 1;
  private static String l = "";
  private TextView A = null;
  private TextView B = null;
  private l C;
  private boolean D = false;
  private boolean E = false;
  private boolean F = true;
  private String G;
  private APPayManager H;
  private View.OnClickListener I = new e(this);
  private View.OnClickListener J = new f(this);
  TextView a = null;
  TextView b = null;
  private String c = "1";
  private String d = "2";
  private String e;
  private String f;
  private boolean g = false;
  private String h = "";
  private String i = "";
  private String j = "";
  private String k = "";
  private int m = 100;
  private String n = "";
  private String o = "";
  private EditText p;
  private TextView q;
  private TextView r;
  private TextView s;
  private Button t;
  private Button u;
  private ImageButton v;
  private TableRow w;
  private InputMethodManager x;
  private LinearLayout y = null;
  private TextView z = null;

  public String getPhoneNum()
  {
    return ((TelephonyManager)getSystemService("phone")).getLine1Number();
  }

  protected void initUI()
  {
    this.q = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_operatorTv")));
    this.r = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_localTv")));
    this.s = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_somethingTv")));
    this.v = ((ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_MobileNumDel")));
    this.v.setOnClickListener(this.I);
    this.t = ((Button)findViewById(APCommMethod.getId(this, "unipay_id_NextBottomBtn")));
    this.t.setEnabled(false);
    this.t.setBackgroundResource(APCommMethod.getDrawableId(this, "unipay_pic_disbtnbg"));
    this.t.setOnClickListener(this.J);
    this.u = ((Button)findViewById(APCommMethod.getId(this, "unipay_id_ReturnBottomBtn")));
    this.u.setOnClickListener(new n(this, 0));
    this.u.setVisibility(8);
    this.w = ((TableRow)findViewById(APCommMethod.getId(this, "unipay_tableRow")));
    this.x = ((InputMethodManager)getSystemService("input_method"));
    APTransformationMethod localAPTransformationMethod = new APTransformationMethod();
    this.p = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_apMobileNumEdit")));
    EditText localEditText = this.p;
    InputFilter[] arrayOfInputFilter = new InputFilter[1];
    arrayOfInputFilter[0] = new InputFilter.LengthFilter(11);
    localEditText.setFilters(arrayOfInputFilter);
    this.p.addTextChangedListener(new m(this, 0));
    this.p.setTransformationMethod(localAPTransformationMethod);
    this.p.setOnTouchListener(new g(this));
    this.p.setOnFocusChangeListener(new h(this));
    String str1 = "";
    String str2 = APTools.readInfo(this, "TencentUnipay", "succHFNum");
    if ((str2 != null) && (str2.length() != 0))
      str1 = new String(APBase64.decode(str2));
    if (str1.length() != 0)
    {
      this.p.setText(str1);
      this.p.setSelection(str1.length());
      getWindow().setSoftInputMode(3);
    }
    do
    {
      return;
      this.e = getPhoneNum();
    }
    while ((this.e == null) || (this.e.equals("")));
    try
    {
      this.e = reverseStr(this.e);
      this.e = this.e.substring(0, 11);
      this.e = reverseStr(this.e);
      this.p.setText(this.e);
      this.p.setSelection(this.e.length());
      this.F = true;
      APDataReportManager.getInstance().insertData("sdk.hf.fill", this.saveType);
      label498: getWindow().setSoftInputMode(3);
      return;
    }
    catch (Exception localException)
    {
      break label498;
    }
  }

  public boolean isHavePermission(Context paramContext, String paramString)
  {
    int i1 = 0;
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      String[] arrayOfString = localPackageManager.getPackageInfo(localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).packageName, 4096).requestedPermissions;
      for (int i2 = 0; ; i2++)
      {
        if (i2 >= arrayOfString.length)
          return i1;
        boolean bool = arrayOfString[i2].equals(paramString);
        if (!bool)
          continue;
        i1 = 1;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return i1;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
    {
      finish();
      return;
    }
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_hf_pay"));
    APUICommonMethod.pushActivity(this);
    if (paramBundle != null)
    {
      this.D = paramBundle.getBoolean("isFromSysSMS");
      this.E = paramBundle.getBoolean("isNextBtnCanSendSMS");
    }
    this.C = new l(this);
    this.saveType = APDataInterface.singleton().getOrderInfo().saveType;
    if ((this.saveType != 4) || (this.saveType != 5))
      titleAnimation();
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
      if (this.saveType == 3)
      {
        initAccountTitle(this.saveType);
        continue;
      }
      if (this.saveType == 2)
      {
        initAccountTitle(this.saveType);
        continue;
      }
      if ((this.saveType != 4) && (this.saveType != 5))
        continue;
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleOfferName"))).setText(this.orderInfo.buyInfo.name);
      TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_bd"));
      TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleMount"));
      ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_VIPLayout"))).setVisibility(8);
      ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_PriceLayout"))).setVisibility(8);
      TextView localTextView3 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_TittleNum"));
      if (localTextView3 != null)
        localTextView3.setText("时长");
      localTextView1.setText(": ");
      localTextView2.setText("1" + APMonthDataInterface.singleton().getUnit());
      this.y = ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_PriceLayout")));
      this.z = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleAmt")));
      this.A = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittlePoint")));
      this.B = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleUnit")));
      this.a = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_stMtv")));
      this.b = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_stMtip")));
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_mpInfo"))).setText("使用话费方式，一次开通一个月" + this.orderInfo.buyInfo.name);
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.showToast(this, "网络错误，请稍后重试");
  }

  public void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    if (paramAPBaseHttpAns.getHttpReqKey().equals("hfstatus"))
    {
      int i1 = this.o.length();
      float f1 = 0.0F;
      if (i1 > 0)
        f1 = Integer.parseInt(this.o) / 100.0F;
      String str = ((APhfPayAns)paramAPBaseHttpAns).getHfStatus();
      Intent localIntent = new Intent(this, APHFPaySuccessActivity.class);
      localIntent.setFlags(268435456);
      Bundle localBundle = new Bundle();
      localBundle.putInt("channel", 9);
      localBundle.putString("state", str);
      localBundle.putFloat("hfprice", f1);
      localIntent.putExtras(localBundle);
      startActivity(localIntent);
      finish();
    }
    APSaveAns localAPSaveAns;
    do
    {
      do
        return;
      while (!paramAPBaseHttpAns.getHttpReqKey().equals("save"));
      APUICommonMethod.dismissWaitDialog();
      this.H.progressPayManagerAns(paramAPBaseHttpAns);
      localAPSaveAns = (APSaveAns)paramAPBaseHttpAns;
    }
    while (localAPSaveAns == null);
    this.m = localAPSaveAns.getResultCode();
    switch (this.m)
    {
    default:
      return;
    case 0:
      this.h = localAPSaveAns.getHf_operator();
      this.i = localAPSaveAns.getHf_area();
      this.j = localAPSaveAns.getHf_accessnum();
      this.k = localAPSaveAns.getHf_accessmsg();
      l = localAPSaveAns.getHf_FeeType();
      if ((this.saveType == 4) || (this.saveType == 5))
      {
        this.o = localAPSaveAns.getHf_price();
        this.n = localAPSaveAns.getHf_upTimes();
      }
      APDataReportManager.getInstance().insertData("sdk.hfsendsms.show", this.saveType);
      APDataInterface.singleton().getOrderInfo().orderId = localAPSaveAns.getHf_billno();
      this.C.sendEmptyMessage(1);
      return;
    case 10008:
    }
    this.C.sendEmptyMessage(1);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.g)
        APUICommonMethod.popActivity();
    }
    else
      return super.onKeyDown(paramInt, paramKeyEvent);
    finish();
    overridePendingTransition(APCommMethod.getAnimId(this, "unipay_anim_in_from_left"), APCommMethod.getAnimId(this, "unipay_anim_out_to_right"));
    return true;
  }

  public void onResume()
  {
    super.onResume();
    if (this.E)
      APDataReportManager.getInstance().insertData("sdk.hfsendsms.show", this.saveType);
    while (true)
    {
      if (this.D)
      {
        APDataReportManager.getInstance().insertData("sdk.ask.show", this.saveType);
        APAlertDialog.Builder localBuilder = new APAlertDialog.Builder(this);
        localBuilder.setTitle("温馨提示");
        localBuilder.setMessage("您是否已发送短信？");
        localBuilder.setPositiveButton("已发送", new i(this));
        localBuilder.setNegativeButton("还没有", new j(this));
        APAlertDialog localAPAlertDialog = localBuilder.create();
        if (localAPAlertDialog != null)
        {
          localAPAlertDialog.setOnKeyListener(new k(this));
          localAPAlertDialog.show();
        }
      }
      return;
      APDataReportManager.getInstance().insertData("sdk.hf.show", this.saveType);
      APDataReportManager.getInstance().reportOneRecord("sdk.hfpay.show.now");
    }
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putBoolean("isFromSysSMS", this.D);
    paramBundle.putBoolean("isNextBtnCanSendSMS", this.E);
    super.onSaveInstanceState(paramBundle);
  }

  protected void onStart()
  {
    super.onStart();
  }

  public void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
    super.onStop(paramAPBaseHttpAns);
  }

  public String reverseStr(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramString);
    return localStringBuffer.reverse().toString();
  }

  public void searchPhoneNumInfo(String paramString)
  {
    if (paramString.length() == 11)
      this.e = getPhoneNum();
    try
    {
      if ((this.e != null) && (!this.e.equals("")))
      {
        this.e = reverseStr(this.e);
        this.e = this.e.substring(0, 11);
        this.e = reverseStr(this.e);
      }
      label74: this.f = this.p.getText().toString().trim();
      if ((this.e != null) && (this.f.endsWith(this.e)))
      {
        this.G = this.c;
        APUICommonMethod.showWaitDialog(this, "正在查询");
        this.H = new APPayManager(this, this);
        if ((this.saveType != 4) && (this.saveType != 5))
          break label208;
        this.H.toHFPay(this.saveType, this.e, this.f, "1", this.G);
      }
      while (true)
      {
        APDataReportManager.getInstance().insertData("sdk.hf.check", this.saveType);
        return;
        this.G = this.d;
        break;
        label208: this.H.toHFPay(this.saveType, this.e, this.f, this.orderInfo.saveNum, this.G);
      }
      APUICommonMethod.showToast(this, "请输入手机号码");
      return;
    }
    catch (Exception localException)
    {
      break label74;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.APHFPayActivity
 * JD-Core Version:    0.6.0
 */
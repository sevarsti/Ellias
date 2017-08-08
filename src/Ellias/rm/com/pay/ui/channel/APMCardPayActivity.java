package com.pay.ui.channel;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import com.pay.ui.common.APAlertDialog;
import com.pay.ui.common.APAlertDialog.Builder;
import com.pay.ui.common.APScrollView;
import com.pay.ui.common.APUICommonMethod;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class APMCardPayActivity extends APRecoChannelActivity
  implements TextView.OnEditorActionListener
{
  EditText a;
  EditText b;
  private int c = 0;
  private RadioGroup d;
  private String e;
  private String f;
  private int g = 0;
  private boolean h = false;
  private String i = "";
  private int j = 0;
  private Runnable k = new o(this);
  private TextWatcher l = new s(this);
  private TextWatcher m = new t(this);
  protected float saveRate = 1.0F;

  private static String a(int paramInt)
  {
    BigDecimal localBigDecimal = new BigDecimal(paramInt);
    DecimalFormat localDecimalFormat = new DecimalFormat();
    localDecimalFormat.applyPattern("0.00");
    return localDecimalFormat.format(localBigDecimal);
  }

  private void a()
  {
    new Handler().postDelayed(this.k, 500L);
  }

  protected boolean checkInput()
  {
    this.e = this.a.getText().toString().trim();
    this.f = this.b.getText().toString().trim();
    if ((this.g <= 0) && (this.c > 0))
    {
      APUICommonMethod.showToast(this, "该业务暂时不支持这个面值充值卡");
      return false;
    }
    if ((this.g <= 0) || (this.c == 0))
    {
      APUICommonMethod.showToast(this, "请选择充值卡面值");
      return false;
    }
    if (this.e.length() == 0)
    {
      APUICommonMethod.showToast(this, "请输入手机充值卡序列号");
      return false;
    }
    if (this.e.length() < 15)
    {
      APUICommonMethod.showToast(this, "请输入正确的手机充值卡序列号和密码");
      return false;
    }
    if (this.f.length() == 0)
    {
      APUICommonMethod.showToast(this, "请输入手机充值卡密码");
      return false;
    }
    if (this.f.length() < 15)
    {
      APUICommonMethod.showToast(this, "请输入正确的手机充值卡序列号和密码");
      return false;
    }
    return true;
  }

  protected void doPay()
  {
    if (checkInput())
    {
      this.orderInfo.saveNum = String.valueOf(this.g);
      mCardPay(this.e, this.f, this.c, this.g);
    }
  }

  protected void initUI()
  {
    ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_PriceLayout"))).setVisibility(8);
    LinearLayout localLinearLayout = (LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_VIPLayout"));
    localLinearLayout.setVisibility(8);
    gamePerPriceInfo();
    int[] arrayOfInt;
    if (this.saveType == 0)
    {
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleOfferName"))).setText(this.orderInfo.buyInfo.name);
      titleAnimation();
      ((Button)findViewById(APCommMethod.getId(this, "unipay_id_BuyBottomBtn"))).setOnClickListener(new u(this));
      arrayOfInt = new int[] { 10, 20, 30, 50, 100 };
      this.d = ((RadioGroup)findViewById(APCommMethod.getId(this, "unipay_id_MardNumGrp")));
    }
    for (int n = 0; ; n++)
    {
      if (n >= this.d.getChildCount())
      {
        this.d.setOnCheckedChangeListener(new v(this, arrayOfInt));
        APScrollView localAPScrollView = (APScrollView)findViewById(APCommMethod.getId(this, "unipay_id_ScrollView"));
        this.a = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_TelCardNumEdit")));
        this.a.addTextChangedListener(this.l);
        EditText localEditText1 = this.a;
        InputFilter[] arrayOfInputFilter1 = new InputFilter[1];
        arrayOfInputFilter1[0] = new InputFilter.LengthFilter(30);
        localEditText1.setFilters(arrayOfInputFilter1);
        this.a.setOnTouchListener(new w(this));
        this.a.setOnFocusChangeListener(new x(this));
        this.b = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_TelCardPWDEdit")));
        this.b.addTextChangedListener(this.m);
        EditText localEditText2 = this.b;
        InputFilter[] arrayOfInputFilter2 = new InputFilter[1];
        arrayOfInputFilter2[0] = new InputFilter.LengthFilter(30);
        localEditText2.setFilters(arrayOfInputFilter2);
        this.b.setOnEditorActionListener(this);
        this.b.setOnTouchListener(new y(this));
        this.b.setOnFocusChangeListener(new z(this, localAPScrollView));
        ImageButton localImageButton1 = (ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_CardNumDel"));
        localImageButton1.setOnClickListener(new p(this, localImageButton1));
        ImageButton localImageButton2 = (ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_CardPWDDel"));
        localImageButton2.setOnClickListener(new q(this, localImageButton2));
        return;
        if ((this.saveType == 3) || (this.saveType == 2))
        {
          accountPerPriceInfo(this.saveType);
          break;
        }
        if ((this.saveType == 4) || (this.saveType == 5))
        {
          TextView localTextView = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_PerPrice"));
          if (this.saveType == 5)
          {
            localTextView.setVisibility(8);
            break;
          }
          localTextView.setText(APCommMethod.fenToYuan(this.orderInfo.buyInfo.price, 2) + "元/月");
          break;
        }
        if (this.saveType != 1)
          break;
        localLinearLayout.setVisibility(0);
        goodsPerPriceInfo();
        break;
      }
      ((RadioButton)this.d.getChildAt(n)).setText(String.valueOf(arrayOfInt[n]) + "元");
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
    {
      finish();
      return;
    }
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_mcard_pay"));
    this.i = APDataInterface.singleton().getOrderInfo().saveNum;
    while (true)
    {
      try
      {
        this.j = Integer.parseInt(this.i);
        this.saveType = this.orderInfo.saveType;
        if (TextUtils.isEmpty(this.orderInfo.buyInfo.price))
          continue;
      }
      catch (Exception localException1)
      {
        try
        {
          this.saveRate = Integer.parseInt(this.orderInfo.buyInfo.price);
          if (this.saveType != 0)
            continue;
          initGameTitle();
          ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_bd"))).setVisibility(4);
          initUI();
          ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleMount"))).setVisibility(4);
          LinearLayout localLinearLayout = (LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_PayInputLayout"));
          if (localLinearLayout == null)
            break;
          localLinearLayout.setVisibility(8);
          return;
          localException1 = localException1;
          APLog.i("intPreSaveNum", localException1.toString());
        }
        catch (Exception localException3)
        {
          APLog.i("saveRate", localException3.toString());
          continue;
          if (this.saveType != 1)
            continue;
          initGoodsTitle();
          continue;
          if (this.saveType != 3)
            continue;
          initAccountTitle(this.saveType);
          this.saveRate = 1.0F;
          continue;
          if (this.saveType != 2)
            continue;
          initAccountTitle(this.saveType);
          this.saveRate = 10.0F;
          continue;
          if (this.saveType != 4)
            if (this.saveType != 5)
              continue;
        }
      }
      try
      {
        this.saveRate = Integer.parseInt(this.orderInfo.buyInfo.price);
        label316: this.saveRate /= 100.0F;
        initMonthTitle();
      }
      catch (Exception localException2)
      {
        break label316;
      }
    }
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
      continue;
      localInputMethodManager.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
      continue;
      localInputMethodManager.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (!this.i.equals(""))
        this.orderInfo.saveNum = this.i;
      APDataReportManager.getInstance().insertData("sdk.pcard.keyback", this.saveType);
      finish();
      overridePendingTransition(APCommMethod.getAnimId(this, "unipay_anim_in_from_left"), APCommMethod.getAnimId(this, "unipay_anim_out_to_right"));
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    super.onResume();
    APDataReportManager.getInstance().insertData("sdk.pcard.show", this.saveType, null, "5", null);
  }

  public void onStart()
  {
    super.onStart();
  }

  protected void refreshNumber(int paramInt)
  {
    LinearLayout localLinearLayout = (LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_PriceLayout"));
    localLinearLayout.setVisibility(0);
    TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleMount"));
    TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_bd"));
    TextView localTextView3 = (TextView)findViewById(APCommMethod.getId(this, "unipay_mcard_valuePrompt"));
    switch (this.saveType)
    {
    default:
    case 0:
    case 2:
    case 3:
    case 1:
    case 4:
    case 5:
    }
    while (true)
    {
      if (!APAppDataInterface.singleton().getIsShowSaveNum())
      {
        localTextView2.setVisibility(4);
        localTextView1.setVisibility(4);
      }
      return;
      this.g = (int)(paramInt * this.saveRate);
      if (this.orderInfo.isNumCanChange)
      {
        if (APAppDataInterface.singleton().getIsShowSaveNum())
        {
          localTextView2.setVisibility(0);
          localTextView1.setVisibility(0);
          localTextView1.setText(String.valueOf(this.g));
        }
        while (true)
        {
          setCost(a(paramInt));
          localTextView3.setText(paramInt + "元面值,充值" + this.g + this.orderInfo.buyInfo.unit + this.orderInfo.buyInfo.name);
          break;
          localTextView2.setVisibility(4);
          localTextView1.setVisibility(4);
        }
      }
      if (this.g == this.j)
      {
        if (APAppDataInterface.singleton().getIsShowSaveNum())
        {
          localTextView2.setVisibility(0);
          localTextView1.setVisibility(0);
          localTextView1.setText(String.valueOf(this.g));
        }
        while (true)
        {
          setCost(a(paramInt));
          localTextView3.setText(paramInt + "元面值,充值" + this.g + this.orderInfo.buyInfo.unit + this.orderInfo.buyInfo.name);
          break;
          localTextView2.setVisibility(4);
          localTextView1.setVisibility(4);
        }
      }
      this.g = 0;
      localTextView2.setVisibility(4);
      localTextView1.setVisibility(4);
      localLinearLayout.setVisibility(4);
      localTextView3.setText("该业务暂时不支持使用" + paramInt + "元充值卡");
      continue;
      this.g = (int)(paramInt * this.saveRate);
      setCost(a(paramInt));
      localTextView2.setVisibility(0);
      localTextView1.setVisibility(0);
      localTextView1.setText(String.valueOf(this.g));
      localTextView3.setText(paramInt + "元面值,充值" + this.g + "Q点");
      continue;
      if (APAppDataInterface.singleton().getIsShowSaveNum())
        localTextView2.setVisibility(0);
      String str2 = this.orderInfo.buyInfo.price;
      if (!TextUtils.isEmpty(this.orderInfo.buyInfo.disPrice))
        str2 = this.orderInfo.buyInfo.disPrice;
      int i2 = Integer.valueOf(str2).intValue();
      if (paramInt * 100 % i2 == 0)
      {
        this.g = (paramInt * 100 / i2);
        if (!this.orderInfo.isNumCanChange);
      }
      try
      {
        String str3 = String.valueOf(this.orderInfo.buyInfo.maxNum);
        if (!TextUtils.isEmpty(str3))
        {
          int i3 = Integer.parseInt(str3);
          if (this.g <= i3)
          {
            setCost(a(paramInt));
            localTextView1.setVisibility(0);
            localTextView1.setText(String.valueOf(this.g));
            localTextView3.setText(paramInt + "元面值,购买" + this.g + this.orderInfo.buyInfo.name);
            continue;
          }
          this.g = 0;
          localTextView2.setVisibility(4);
          localLinearLayout.setVisibility(4);
          localTextView1.setVisibility(4);
          localTextView3.setText("该业务暂时不支持使用" + paramInt + "元充值卡");
          continue;
        }
        setCost(a(paramInt));
        localTextView1.setVisibility(0);
        localTextView1.setText(String.valueOf(this.g));
        localTextView3.setText(paramInt + "元面值,购买" + this.g + this.orderInfo.buyInfo.name);
        continue;
        if (this.g == this.j)
        {
          setCost(a(paramInt));
          localTextView1.setVisibility(0);
          localTextView1.setText(String.valueOf(this.g));
          localTextView3.setText(paramInt + "元面值,购买" + this.g + this.orderInfo.buyInfo.name);
          continue;
        }
        this.g = 0;
        localTextView2.setVisibility(4);
        localLinearLayout.setVisibility(8);
        localTextView1.setVisibility(4);
        localTextView3.setText("该业务暂时不支持使用" + paramInt + "元充值卡");
        continue;
        localTextView2.setVisibility(4);
        localLinearLayout.setVisibility(8);
        localTextView1.setVisibility(4);
        localTextView3.setText("该业务暂时不支持使用" + paramInt + "元充值卡");
        continue;
        localTextView2.setVisibility(0);
        localTextView2.setText(": ");
        APMonthDataInterface localAPMonthDataInterface = APMonthDataInterface.singleton();
        if (localAPMonthDataInterface.getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
          continue;
        if (this.orderInfo.isNumCanChange)
        {
          if (paramInt < this.saveRate)
          {
            this.g = 0;
            localTextView2.setVisibility(4);
            localLinearLayout.setVisibility(4);
            localTextView1.setVisibility(4);
            localTextView3.setText("该业务暂时不支持使用" + paramInt + "元充值卡");
            continue;
          }
          int n;
          if (paramInt >= this.saveRate)
          {
            n = 1;
            label1155: if (paramInt % this.saveRate != 0.0F)
              break label1297;
          }
          label1297: for (int i1 = 1; ; i1 = 0)
          {
            if ((n & i1) == 0)
              break label1303;
            this.g = (int)(paramInt / this.saveRate);
            setCost(a(paramInt));
            localTextView1.setVisibility(0);
            localTextView1.setText(String.valueOf(this.g) + localAPMonthDataInterface.getUnit());
            localTextView3.setText(paramInt + "元面值,开通" + this.g + localAPMonthDataInterface.getUnit() + this.orderInfo.buyInfo.name);
            break;
            n = 0;
            break label1155;
          }
          label1303: localLinearLayout.setVisibility(4);
          localTextView1.setVisibility(4);
          this.g = (int)(paramInt / this.saveRate);
          localTextView3.setText("您选择的是" + paramInt + "元面值的充值卡,\n将开通" + this.g + localAPMonthDataInterface.getUnit() + this.orderInfo.buyInfo.name + ",剩余金额充入" + APChannelList.singleton().getCurrentAcctName() + "账户。");
          setCost(a(paramInt));
          localTextView1.setVisibility(0);
          localTextView1.setText(String.valueOf(this.g) + localAPMonthDataInterface.getUnit());
          continue;
        }
        String str1 = APUICommonMethod.reCostByRate();
        try
        {
          float f2 = Float.parseFloat(str1);
          f1 = f2;
          if (paramInt < f1)
          {
            this.g = 0;
            localTextView2.setVisibility(4);
            localLinearLayout.setVisibility(4);
            localTextView1.setVisibility(4);
            localTextView3.setText("该业务暂时不支持使用" + paramInt + "元充值卡");
          }
        }
        catch (Exception localException1)
        {
          float f1;
          while (true)
          {
            APLog.i("mcard intMonthCost", localException1.toString());
            f1 = 0.0F;
          }
          if ((1.E-005D + paramInt > f1) && (paramInt - 1.E-005D < f1))
          {
            this.g = this.j;
            setCost(a(paramInt));
            localTextView1.setVisibility(0);
            localTextView1.setText(String.valueOf(this.i) + localAPMonthDataInterface.getUnit());
            localTextView3.setText(paramInt + "元面值,开通" + this.i + localAPMonthDataInterface.getUnit() + this.orderInfo.buyInfo.name);
            continue;
          }
          this.g = this.j;
          localLinearLayout.setVisibility(4);
          localTextView1.setVisibility(4);
          localTextView3.setText("您选择的是" + paramInt + "元面值的充值卡,\n将开通" + this.i + localAPMonthDataInterface.getUnit() + this.orderInfo.buyInfo.name + ",剩余金额充入" + APChannelList.singleton().getCurrentAcctName() + "账户。");
          setCost(a(paramInt));
          localTextView1.setVisibility(0);
          localTextView1.setText(String.valueOf(this.i) + localAPMonthDataInterface.getUnit());
        }
      }
      catch (Exception localException2)
      {
      }
    }
  }

  protected void showMCardDialog(String paramString)
  {
    APAlertDialog.Builder localBuilder = new APAlertDialog.Builder(this);
    localBuilder.setTitle("温馨提示");
    localBuilder.setMessage(paramString);
    localBuilder.setNeutralButton("确定", new r(this));
    APAlertDialog localAPAlertDialog = localBuilder.create();
    if (localAPAlertDialog == null)
      return;
    localAPAlertDialog.show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.APMCardPayActivity
 * JD-Core Version:    0.6.0
 */
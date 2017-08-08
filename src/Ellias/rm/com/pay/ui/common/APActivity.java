package com.pay.ui.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.buyInfo.APBuyGoodsInfo;
import com.pay.data.buyInfo.APBuyMonthInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import java.lang.reflect.Field;
import java.util.Timer;

public class APActivity extends Activity
{
  protected int NaviBarHeight = 0;
  protected int TITLEANIMATIONTIME = 200;
  private String a = "Q点";
  private String b = "Q币";
  private String c = "1元=1Q币";
  protected int callMethodID = -1;
  protected RelativeLayout contentView = null;
  private String d = "1元=10Q点";
  private boolean e = false;
  protected boolean hasBottomShadow = true;
  protected boolean hasTopShadow = true;
  protected APOrderInfo orderInfo;
  protected int saveType;
  protected boolean singlePage = false;
  protected String title = null;
  protected ProgressDialog waitDialog;

  private void a()
  {
    APBuyMonthInfo localAPBuyMonthInfo = (APBuyMonthInfo)this.orderInfo.buyInfo;
    String str = APCommMethod.fenToYuan(localAPBuyMonthInfo.price, 2);
    TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_PerPrice"));
    if (this.orderInfo.saveType == 5)
      localTextView1.setText(localAPBuyMonthInfo.offerName);
    while (true)
    {
      ImageView localImageView = (ImageView)findViewById(APCommMethod.getId(this, "unipay_id_VIPLogo"));
      TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_VIPPrice"));
      if (localImageView != null)
        localImageView.setVisibility(8);
      if (localTextView2 != null)
        localTextView2.setVisibility(8);
      return;
      if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
      {
        localTextView1.setText(localAPBuyMonthInfo.offerName);
        continue;
      }
      localTextView1.setText(str + "元/月");
    }
  }

  protected void AddRequest(int paramInt)
  {
  }

  public void AddResponseArrived()
  {
  }

  public void AllResponseArrived()
  {
  }

  protected void ClearAllRequests()
  {
  }

  public void OnTitlePressed()
  {
  }

  public void PushActivity(Class paramClass)
  {
    PushActivity(paramClass, null);
  }

  public void PushActivity(Class paramClass, Bundle paramBundle)
  {
  }

  public void ResponseArrived()
  {
    ResponseArrived(true);
  }

  public void ResponseArrived(boolean paramBoolean)
  {
  }

  public void SetLeftTitle(String paramString)
  {
    Button localButton = new Button(this);
    localButton.setTextColor(-1);
    localButton.setTextSize(15.0F);
    localButton.setText(paramString);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins(5, 0, 0, 0);
    localLayoutParams.addRule(15);
    localButton.setLayoutParams(localLayoutParams);
    localButton.setPadding(30, 0, 20, 0);
    localButton.setOnClickListener(new b(this));
  }

  public void ShowSendBlogLoadingScreen()
  {
  }

  public void accountPerPriceInfo(int paramInt)
  {
    TextView localTextView = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_PerPrice"));
    if (paramInt == 3)
      localTextView.setText(this.c);
    do
      return;
    while (paramInt != 2);
    localTextView.setText(this.d);
  }

  protected ProgressDialog createDialog()
  {
    APProgressDialog localAPProgressDialog = new APProgressDialog(this);
    localAPProgressDialog.setMessage("请稍候...");
    return localAPProgressDialog;
  }

  public void dismissInput()
  {
    try
    {
      if (getWindow().getAttributes().softInputMode == 5)
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 1);
      return;
    }
    catch (Exception localException)
    {
      APLog.w("APActivity", "dismissInput" + localException.getMessage().toString());
    }
  }

  public void editLight(int paramInt)
  {
    findViewById(paramInt).setBackgroundDrawable(getResources().getDrawable(APCommMethod.getDrawableId(this, "unipay_inputbg_normal_high")));
  }

  public void editNotLight(int paramInt)
  {
    findViewById(paramInt).setBackgroundDrawable(getResources().getDrawable(APCommMethod.getDrawableId(this, "unipay_inputbg_normal")));
  }

  protected void gamePerPriceInfo()
  {
    ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_PerPrice"))).setText("1元=" + this.orderInfo.buyInfo.price + this.orderInfo.buyInfo.name);
  }

  public String getActivityTitle()
  {
    return null;
  }

  protected void goodsPerPriceInfo()
  {
    String str = APCommMethod.fenToYuan(((APBuyGoodsInfo)this.orderInfo.buyInfo).price, 2);
    ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_PerPrice"))).setText(str + "元/个");
  }

  public void initAccountTitle(int paramInt)
  {
    refreshAccountCost(this.orderInfo.saveNum, paramInt);
    TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_PerPrice"));
    TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleOfferName"));
    if (paramInt == 3)
    {
      localTextView1.setText(this.c);
      localTextView2.setText(this.b);
    }
    while (true)
    {
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleMount"))).setText(this.orderInfo.saveNum);
      ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_VIPLayout"))).setVisibility(8);
      return;
      if (paramInt != 2)
        continue;
      localTextView1.setText(this.d);
      localTextView2.setText(this.a);
    }
  }

  public void initAccountTitle(String paramString, int paramInt)
  {
    refreshAccountCost(paramString, paramInt);
    TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_PerPrice"));
    TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleBuyInfo"));
    if (paramInt == 3)
    {
      localTextView1.setText(this.c);
      localTextView2.setText(this.b);
      return;
    }
    if (paramInt == 2)
    {
      localTextView1.setText(this.d);
      localTextView2.setText(this.a);
      return;
    }
    APUICommonMethod.showToast(this, "充值类型未定义");
  }

  public void initGameTitle()
  {
    refreshCost(this.orderInfo.saveNum);
    gamePerPriceInfo();
    ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleOfferName"))).setText(this.orderInfo.buyInfo.name);
    TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_bd"));
    TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleMount"));
    TextView localTextView3 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_TittleNum"));
    if (APAppDataInterface.singleton().getIsShowSaveNum())
      if (!APAppDataInterface.singleton().getIsShowSaveNum())
      {
        localTextView1.setVisibility(8);
        localTextView2.setVisibility(8);
        if (localTextView3 != null)
          localTextView3.setVisibility(8);
      }
    while (true)
    {
      ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_VIPLayout"))).setVisibility(8);
      return;
      localTextView2.setText(String.valueOf(this.orderInfo.saveNum));
      continue;
      localTextView1.setVisibility(4);
      localTextView2.setVisibility(4);
      if (localTextView3 == null)
        continue;
      localTextView3.setVisibility(4);
    }
  }

  public void initGameTitle(String paramString)
  {
    refreshCost(paramString);
    gamePerPriceInfo();
    ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleBuyInfo"))).setText(this.orderInfo.buyInfo.name);
  }

  public void initGoodsTitle()
  {
    refreshCost(this.orderInfo.saveNum);
    goodsPerPriceInfo();
    ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleOfferName"))).setText(this.orderInfo.buyInfo.name);
    TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_bd"));
    TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleMount"));
    TextView localTextView3 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_TittleNum"));
    if (APAppDataInterface.singleton().getIsShowSaveNum())
      if (!APAppDataInterface.singleton().getIsShowSaveNum())
      {
        localTextView1.setVisibility(8);
        localTextView2.setVisibility(8);
        if (localTextView3 != null)
          localTextView3.setVisibility(8);
      }
    do
    {
      return;
      localTextView2.setText(this.orderInfo.saveNum);
      return;
      localTextView1.setVisibility(4);
      localTextView2.setVisibility(4);
    }
    while (localTextView3 == null);
    localTextView3.setVisibility(4);
  }

  public void initGoodsTitle(String paramString)
  {
    refreshCost(paramString);
    goodsPerPriceInfo();
    ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleBuyInfo"))).setText(this.orderInfo.buyInfo.name);
  }

  public void initMonthTitle()
  {
    refreshCost(this.orderInfo.saveNum);
    a();
    TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleOfferName"));
    localTextView1.setText(this.orderInfo.buyInfo.name);
    TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_bd"));
    TextView localTextView3 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleMount"));
    TextView localTextView4 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_TittleNum"));
    ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_VIPLayout"))).setVisibility(8);
    if (localTextView4 != null)
    {
      if ((this.orderInfo.saveType == 4) && (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_Rate))
        localTextView4.setText("时长");
    }
    else
    {
      if (APAppDataInterface.singleton().getIsShowSaveNum())
        break label190;
      localTextView2.setVisibility(8);
      localTextView3.setVisibility(8);
      if (localTextView4 != null)
        localTextView4.setVisibility(8);
    }
    label190: 
    do
    {
      return;
      localTextView4.setText("数量");
      break;
      localTextView2.setText(": ");
      if (this.orderInfo.saveType != 5)
        break label360;
      localTextView2.setVisibility(8);
      localTextView3.setVisibility(8);
      if (APMonthDataInterface.singleton().getOpenType() != APMonthDataInterface.MonthOpenType.OpenType_NoRate)
        break label269;
      localTextView1.setText(APMonthDataInterface.singleton().getUnit());
    }
    while (localTextView4 == null);
    localTextView3.setText(this.orderInfo.saveNum);
    localTextView2.setVisibility(0);
    localTextView3.setVisibility(0);
    return;
    label269: if (localTextView4 != null)
    {
      localTextView1.setText(((APBuyMonthInfo)this.orderInfo.buyInfo).serviceName);
      localTextView3.setText(this.orderInfo.saveNum);
      localTextView2.setVisibility(0);
      localTextView3.setVisibility(0);
      return;
    }
    localTextView1.setText(((APBuyMonthInfo)this.orderInfo.buyInfo).serviceName + " × " + this.orderInfo.saveNum);
    return;
    label360: if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
    {
      localTextView1.setText(APMonthDataInterface.singleton().getUnit());
      if (localTextView4 != null)
      {
        localTextView3.setText(this.orderInfo.saveNum);
        return;
      }
      localTextView2.setVisibility(8);
      localTextView3.setVisibility(8);
      return;
    }
    localTextView3.setText(this.orderInfo.saveNum + APMonthDataInterface.singleton().getUnit());
  }

  public void initMonthTitle(String paramString)
  {
    refreshCost(paramString);
    a();
    ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleBuyInfo"))).setText(this.orderInfo.buyInfo.name);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
    {
      APLog.e("APActivity", "data is null");
      return;
    }
    APUICommonMethod.pushActivity(this);
    AndroidPay.singleton().isUILaunched = true;
    this.orderInfo = APDataInterface.singleton().getOrderInfo();
    this.saveType = this.orderInfo.saveType;
    int i = APDataInterface.singleton().getScreenType();
    int j;
    if ((i == 0) || (i == 1))
      j = i;
    while (true)
    {
      setRequestedOrientation(j);
      this.waitDialog = createDialog();
      return;
      if (getResources().getConfiguration().orientation == 2);
      try
      {
        int n = ActivityInfo.class.getField("SCREEN_ORIENTATION_SENSOR_LANDSCAPE").getInt(null);
        j = n;
        continue;
        int k = getResources().getConfiguration().orientation;
        j = 0;
        if (k != 1)
          continue;
        try
        {
          int m = ActivityInfo.class.getField("SCREEN_ORIENTATION_SENSOR_PORTRAIT").getInt(null);
          j = m;
        }
        catch (Exception localException1)
        {
          j = 1;
        }
      }
      catch (Exception localException2)
      {
        j = 0;
      }
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
    if (this.waitDialog != null)
      this.waitDialog.dismiss();
    ClearAllRequests();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
      finish();
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onStop()
  {
    super.onStop();
  }

  public void refreshAccountCost(String paramString, int paramInt)
  {
    this.orderInfo.saveNum = paramString;
    setCost(this.orderInfo.getCostWithPoint(2));
  }

  public void refreshCost(String paramString)
  {
    this.orderInfo.saveNum = paramString;
    setCost(this.orderInfo.getCostWithPoint(2));
  }

  public void setContentView(int paramInt)
  {
    setContentView(View.inflate(this, paramInt, null));
  }

  public void setContentView(View paramView)
  {
    super.setContentView(paramView);
  }

  protected void setCost(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_PriceLayout"))).setVisibility(8);
      return;
    }
    ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_PriceLayout"))).setVisibility(0);
    TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleAmt"));
    TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittlePoint"));
    TextView localTextView3 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleUnit"));
    String[] arrayOfString = paramString.split("\\.");
    String str1 = "";
    if (arrayOfString.length > 1)
      paramString = arrayOfString[0];
    if (arrayOfString.length == 2)
    {
      String str2 = arrayOfString[1];
      str1 = "." + str2;
    }
    localTextView1.setText(paramString);
    localTextView2.setText(str1);
    localTextView3.setText("￥");
  }

  public void showInputDelay(View paramView, int paramInt)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    new Timer().schedule(new c(this, localInputMethodManager, paramView), 1L);
  }

  public void titleAnimation()
  {
    try
    {
      LinearLayout localLinearLayout1 = (LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_HeadLayout"));
      LinearLayout localLinearLayout2 = (LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_dropInfo"));
      if (localLinearLayout1 != null)
        localLinearLayout1.setOnClickListener(new a(this, localLinearLayout2));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APActivity
 * JD-Core Version:    0.6.0
 */
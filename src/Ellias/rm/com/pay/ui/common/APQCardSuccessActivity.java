package com.pay.ui.common;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.pay.AndroidPay;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.mp.APMPGoodsItem;
import com.pay.data.mp.APMPSendInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import com.pay.ui.marketing.APWebMarketActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class APQCardSuccessActivity extends APActivity
{
  private int a;
  private int b = -1;
  private String c = "";

  protected void initTitleLandscape()
  {
    if (this.a == 0)
      initGameTitle();
    do
    {
      return;
      if ((this.a == 2) || (this.a == 3))
      {
        initAccountTitle(this.a);
        return;
      }
      if (this.a != 1)
        continue;
      initGoodsTitle();
      return;
    }
    while ((this.a != 4) && (this.a != 5));
    initMonthTitle();
  }

  protected void initUI()
  {
    TextView localTextView = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_unipay_id_apTips1"));
    Bundle localBundle = getIntent().getExtras();
    Button localButton1;
    Button localButton2;
    if ((localBundle != null) && ((this.b == 1) || (this.b == 2)))
    {
      int i = localBundle.getInt("pay_type");
      this.c = ("pt" + String.valueOf(i));
      if ((this.c.equals("pt2")) || (this.c.equals("pt3")) || (this.c.equals("pt4")))
      {
        ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_tenpay_succdash"))).setVisibility(0);
        ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tenpay_succTips"))).setText(APCommMethod.getStringId(this, "unipay_bank_opened"));
      }
    }
    else
    {
      localButton1 = (Button)findViewById(APCommMethod.getId(this, "unipay_id_apBackGame"));
      localButton1.setText(APCommMethod.getStringId(this, "unipay_back"));
      localButton2 = (Button)findViewById(APCommMethod.getId(this, "unipay_id_apBackBuy"));
      localButton2.setText(APCommMethod.getStringId(this, "unipay_continuesave"));
      switch (this.a)
      {
      default:
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
    }
    while (true)
    {
      localButton1.setOnClickListener(new H(this));
      localButton2.setOnClickListener(new I(this));
      titleAnimation();
      return;
      if (!this.c.equals("pt6"))
        break;
      ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_tenpay_succdash"))).setVisibility(0);
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tenpay_succTips"))).setText(APCommMethod.getStringId(this, "unipay_bank_findpwd"));
      break;
      showMPInfo(true);
      localButton1.setVisibility(0);
      localButton2.setVisibility(0);
      if (TextUtils.isEmpty(APDataInterface.singleton().getPayAssignChannel()))
        continue;
      localButton2.setVisibility(4);
      continue;
      showMPInfo(false);
      localButton2.setVisibility(8);
      localButton1.setVisibility(0);
      continue;
      showMPInfo(false);
      localTextView.setText(APCommMethod.getStringId(this, "unipay_qd_paysucc"));
      localButton1.setVisibility(8);
      localButton2.setBackgroundDrawable(APCommMethod.getDrawable(this, "unipay_drawable_embtn"));
      localButton2.setTextColor(-1);
      localButton2.setText(APCommMethod.getStringId(this, "unipay_continue"));
      localButton2.setVisibility(0);
      continue;
      showMPInfo(false);
      localTextView.setText(APCommMethod.getStringId(this, "unipay_qb_paysucc"));
      localButton1.setVisibility(8);
      localButton2.setBackgroundDrawable(APCommMethod.getDrawable(this, "unipay_drawable_embtn"));
      localButton2.setTextColor(-1);
      localButton2.setText(APCommMethod.getStringId(this, "unipay_continue"));
      localButton2.setVisibility(0);
      continue;
      showMPInfo(false);
      localButton1.setVisibility(0);
      localButton2.setText(APCommMethod.getStringId(this, "unipay_continueopen"));
      localButton2.setVisibility(0);
      continue;
      showMPInfo(false);
      localButton1.setVisibility(0);
      localButton2.setText(APCommMethod.getStringId(this, "unipay_pay_continue"));
      localButton2.setVisibility(0);
    }
  }

  protected void initUILandscape()
  {
    initUI();
    if ((this.b == 1) || (this.b == 2))
      setCost(String.valueOf(Float.valueOf(getIntent().getExtras().getFloat("total_fee"))));
  }

  protected void initUIPortralt()
  {
    initUI();
    TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succtext"));
    TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succsavenum"));
    TextView localTextView3 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succsaveunit"));
    switch (this.a)
    {
    default:
    case 0:
    case 1:
      do
      {
        do
        {
          return;
          localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
          localTextView3.setText(this.orderInfo.buyInfo.name);
        }
        while (!APAppDataInterface.singleton().getIsShowSaveNum());
        localTextView2.setText("×" + this.orderInfo.saveNum + this.orderInfo.buyInfo.unit);
        return;
        localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_buy"));
        localTextView3.setText(this.orderInfo.buyInfo.name);
      }
      while (!APAppDataInterface.singleton().getIsShowSaveNum());
      localTextView2.setText("×" + this.orderInfo.saveNum + this.orderInfo.buyInfo.unit);
      return;
    case 2:
      localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
      localTextView3.setText(APCommMethod.getStringId(this, "unipay_qd"));
      localTextView2.setText("×" + this.orderInfo.saveNum);
      return;
    case 3:
      localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
      localTextView3.setText(APCommMethod.getStringId(this, "unipay_qb"));
      localTextView2.setText("×" + this.orderInfo.saveNum);
      return;
    case 4:
      if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
      {
        localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_open"));
        localTextView3.setText("");
        localTextView2.setText(APMonthDataInterface.singleton().getUnit());
        return;
      }
      localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_open"));
      localTextView3.setText(this.orderInfo.buyInfo.name);
      localTextView2.setText("×" + this.orderInfo.saveNum + APMonthDataInterface.singleton().getUnit());
      return;
    case 5:
    }
    localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_buy"));
    localTextView3.setText(this.orderInfo.buyInfo.name);
    if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
    {
      localTextView3.setText(APMonthDataInterface.singleton().getUnit());
      localTextView2.setText("");
      return;
    }
    localTextView2.setText("×" + this.orderInfo.saveNum);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_qcardsuccess"));
    this.a = APDataInterface.singleton().getOrderInfo().saveType;
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null)
      this.b = localBundle.getInt("channel");
    if (getResources().getConfiguration().orientation == 2)
    {
      initTitleLandscape();
      initUILandscape();
    }
    do
      return;
    while (getResources().getConfiguration().orientation != 1);
    initUIPortralt();
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if ((this.b == 1) || (this.b == 2))
      {
        APDataReportManager.getInstance().insertData("sdk.succ.keyback", this.a, null, this.c, null);
        if ((this.b != 0) && (this.b != 11) && (this.b != 4))
          break label125;
        APUICommonMethod.popActivity();
        APCommMethod.paySuccCallBack(this.b, 0, 0);
        finish();
        overridePendingTransition(APCommMethod.getAnimId(this, "unipay_anim_in_from_left"), APCommMethod.getAnimId(this, "unipay_anim_out_to_right"));
      }
      while (true)
      {
        return true;
        APDataReportManager.getInstance().insertData("sdk.succ.keyback", this.a, null, String.valueOf(this.b), null);
        break;
        label125: if ((AndroidPay.singleton().isValidPayChannelAndMarket()) && (APDataInterface.singleton().getPayAssignChannel().equals("bank")))
        {
          APUICommonMethod.popActivity();
          Intent localIntent = new Intent();
          Bundle localBundle = new Bundle();
          localBundle.putString("loadUI", "showBankResultActivity");
          localIntent.putExtras(localBundle);
          localIntent.setClass(this, APWebMarketActivity.class);
          startActivity(localIntent);
          continue;
        }
        APUICommonMethod.popActivity();
        APCommMethod.paySuccCallBack(this.b, 0, -1);
        finish();
        overridePendingTransition(APCommMethod.getAnimId(this, "unipay_anim_in_from_left"), APCommMethod.getAnimId(this, "unipay_anim_out_to_right"));
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    super.onResume();
    if ((this.b == 1) || (this.b == 2))
    {
      APDataReportManager.getInstance().insertData("sdk.succ.show", this.a, null, this.c, null);
      return;
    }
    APDataReportManager.getInstance().insertData("sdk.succ.show", this.a, null, String.valueOf(this.b), null);
  }

  public void showMPInfo(boolean paramBoolean)
  {
    ArrayList localArrayList1;
    GridView localGridView;
    if (this.a == 0)
    {
      localArrayList1 = APMPSendInfo.getInstance().getTotalSendInfo();
      if ((localArrayList1 != null) && (localArrayList1.size() > 0))
      {
        ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_totalSendInfo"))).setVisibility(0);
        localGridView = (GridView)findViewById(APCommMethod.getId(this, "unipay_gridview"));
        localGridView.setSelector(new ColorDrawable(0));
        if (localArrayList1.size() != 1)
          break label179;
        localGridView.setNumColumns(1);
      }
    }
    ArrayList localArrayList2;
    int i;
    while (true)
    {
      localArrayList2 = new ArrayList();
      i = 0;
      if (i < localArrayList1.size())
        break;
      int j = APCommMethod.getLayoutId(this, "unipay_layout_suc_result");
      String[] arrayOfString = { "itemText", "itemNumber" };
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = APCommMethod.getId(this, "unipay_textview");
      arrayOfInt[1] = APCommMethod.getId(this, "unipay_textnum");
      localGridView.setAdapter(new SimpleAdapter(this, localArrayList2, j, arrayOfString, arrayOfInt));
      return;
      label179: if (localArrayList1.size() != 2)
        continue;
      localGridView.setNumColumns(2);
    }
    HashMap localHashMap = new HashMap();
    String str = ((APMPGoodsItem)localArrayList1.get(i)).name;
    byte[] arrayOfByte = str.getBytes();
    if (arrayOfByte.length != str.length())
      if (str.length() > 5)
        str = str.substring(0, 4) + "...";
    while (true)
    {
      localHashMap.put("itemText", str);
      localHashMap.put("itemNumber", "×" + ((APMPGoodsItem)localArrayList1.get(i)).num);
      localArrayList2.add(localHashMap);
      i++;
      break;
      if (arrayOfByte.length <= 10)
        continue;
      str = new String(arrayOfByte, 0, 8) + "...";
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APQCardSuccessActivity
 * JD-Core Version:    0.6.0
 */
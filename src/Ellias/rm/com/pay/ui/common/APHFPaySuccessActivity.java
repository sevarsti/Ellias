package com.pay.ui.common;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.mp.APMPGoodsItem;
import com.pay.data.mp.APMPSendInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APMonthDataInterface;
import java.util.ArrayList;
import java.util.HashMap;

public class APHFPaySuccessActivity extends APActivity
{
  IAPHttpAnsObserver a = new i(this);
  private LinearLayout b = null;
  private LinearLayout c = null;
  private LinearLayout d = null;
  private LinearLayout e = null;
  private TextView f = null;
  private TextView g = null;
  private Button h = null;
  private Button i = null;
  private Button j = null;
  private ImageView k = null;
  private long l = 0L;
  private String m = "";
  private APHFPaySuccessActivity.tHander n = null;
  private int o = 0;

  public void initDelayFinalUI()
  {
    this.k.setImageResource(APCommMethod.getDrawableId(this, "unipay_pic_wrong"));
    this.c.setVisibility(0);
    this.f.setText("支付结果延时");
    this.g.setText("支付成功后，系统会向您的手机发送短信通知");
    this.h.setVisibility(0);
    this.i.setVisibility(8);
    this.j.setVisibility(8);
  }

  public void initStateUI(String paramString)
  {
    String str;
    if (paramString.equals("succeed"))
      if (getResources().getConfiguration().orientation == 2)
      {
        showSuccWithLandscape();
        str = paramString;
      }
    while (true)
    {
      APDataReportManager.getInstance().insertData("sdk.hfsucc.show", this.o, null, str, null);
      return;
      if (getResources().getConfiguration().orientation == 1)
      {
        showSuccWithPortralt();
        str = paramString;
        continue;
        if (paramString.equals("failed"))
        {
          this.k.setImageResource(APCommMethod.getDrawableId(this, "unipay_pic_wrong"));
          if (getResources().getConfiguration().orientation == 2)
          {
            showFaildWithLandscape();
            str = paramString;
            continue;
          }
          if (getResources().getConfiguration().orientation == 1)
          {
            showFaildWithPortralt();
            str = paramString;
            continue;
          }
        }
        else
        {
          if (System.currentTimeMillis() - this.l >= 60000L)
          {
            initDelayFinalUI();
            str = "delayfinal";
            continue;
          }
          this.k.setImageResource(APCommMethod.getDrawableId(this, "unipay_pic_wait"));
          if (getResources().getConfiguration().orientation == 2)
          {
            showProgressWithLandscape();
            str = paramString;
            continue;
          }
          if (getResources().getConfiguration().orientation == 1)
            showProgressWithPortralt();
        }
      }
      str = paramString;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_hfsuccess"));
    if (getResources().getConfiguration().orientation == 2)
    {
      this.o = APDataInterface.singleton().getOrderInfo().saveType;
      if (this.o != 0)
        break label320;
      initGameTitle();
    }
    while (true)
    {
      this.b = ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_SuccessLayout")));
      this.d = ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_FailedLayout")));
      this.c = ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_ProcessLayout")));
      this.e = ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_savelayout")));
      this.f = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine1")));
      this.g = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine2")));
      this.h = ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apBackGame")));
      this.h.setOnClickListener(new APHFPaySuccessActivity.BackGameClick(this));
      this.i = ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apBuyContinue")));
      this.i.setOnClickListener(new APHFPaySuccessActivity.BuyContinue(this));
      this.j = ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apSearchOrder")));
      this.j.setOnClickListener(new APHFPaySuccessActivity.SearchOrderClick(this));
      this.k = ((ImageView)findViewById(APCommMethod.getId(this, "unipay_id_TipsImage")));
      this.l = System.currentTimeMillis();
      this.n = new APHFPaySuccessActivity.tHander(this);
      Bundle localBundle = getIntent().getExtras();
      this.o = APDataInterface.singleton().getOrderInfo().saveType;
      this.m = localBundle.getString("state");
      return;
      label320: if (this.o == 1)
      {
        initGoodsTitle();
        continue;
      }
      if (this.o == 3)
      {
        initAccountTitle(this.o);
        continue;
      }
      if (this.o == 2)
      {
        initAccountTitle(this.o);
        continue;
      }
      if ((this.o != 4) && (this.o != 5))
        continue;
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleOfferName"))).setText(this.orderInfo.buyInfo.name);
      ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_VIPLayout"))).setVisibility(8);
      float f1 = getIntent().getExtras().getFloat("hfprice");
      if (f1 > 0.0F)
        setCost(String.valueOf(f1));
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_TittleNum"))).setText("时长");
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleMount"))).setText("1" + APMonthDataInterface.singleton().getUnit());
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (System.currentTimeMillis() - this.l >= 60000L)
      this.m = "delayfinal";
    APDataReportManager.getInstance().insertData("sdk.hfsucc.keyback", this.o, null, this.m, null);
    APUICommonMethod.popActivity();
    if (this.m.equals("failed"))
      APCommMethod.payErrorCallBack(-1, "fail");
    while (true)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      if (this.m.equals("succeed"))
      {
        APCommMethod.paySuccCallBack(9, 0, -1);
        continue;
      }
      APCommMethod.paySuccCallBack(9, -1, -1);
    }
  }

  public void onResume()
  {
    super.onResume();
    initStateUI(this.m);
  }

  protected void onStart()
  {
    super.onStart();
  }

  public void showFaildWithLandscape()
  {
    this.d.setVisibility(0);
    this.c.setVisibility(8);
    this.j.setVisibility(8);
  }

  public void showFaildWithPortralt()
  {
    this.d.setVisibility(0);
    this.c.setVisibility(8);
    this.j.setVisibility(8);
    this.e.setVisibility(8);
  }

  public void showMPInfo(boolean paramBoolean)
  {
    ArrayList localArrayList1;
    GridView localGridView;
    if (this.o == 0)
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
    int i1;
    while (true)
    {
      localArrayList2 = new ArrayList();
      i1 = 0;
      if (i1 < localArrayList1.size())
        break;
      int i2 = APCommMethod.getLayoutId(this, "unipay_layout_suc_result");
      String[] arrayOfString = { "itemText", "itemNumber" };
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = APCommMethod.getId(this, "unipay_textview");
      arrayOfInt[1] = APCommMethod.getId(this, "unipay_textnum");
      localGridView.setAdapter(new SimpleAdapter(this, localArrayList2, i2, arrayOfString, arrayOfInt));
      return;
      label179: if (localArrayList1.size() != 2)
        continue;
      localGridView.setNumColumns(2);
    }
    HashMap localHashMap = new HashMap();
    String str = ((APMPGoodsItem)localArrayList1.get(i1)).name;
    byte[] arrayOfByte = str.getBytes();
    if (arrayOfByte.length != str.length())
      if (str.length() > 5)
        str = str.substring(0, 4) + "...";
    while (true)
    {
      localHashMap.put("itemText", str);
      localHashMap.put("itemNumber", "×" + ((APMPGoodsItem)localArrayList1.get(i1)).num);
      localArrayList2.add(localHashMap);
      i1++;
      break;
      if (arrayOfByte.length <= 10)
        continue;
      str = new String(arrayOfByte, 0, 8) + "...";
    }
  }

  public void showProgressWithLandscape()
  {
    this.c.setVisibility(0);
    this.f.setText("短信发送成功!");
    this.g.setText("话费支付需要收到运营商成功确认短信后才会扣费发货,支付结果可能有一分钟左右的延时，点击查询结果");
    this.i.setVisibility(8);
  }

  public void showProgressWithPortralt()
  {
    this.c.setVisibility(0);
    this.e.setVisibility(8);
    this.f.setText("短信发送成功!");
    this.g.setText("话费支付需要收到运营商成功确认短信后才会扣费发货,支付结果可能有一分钟左右的延时，点击查询结果");
    this.i.setVisibility(8);
  }

  public void showSuccWithLandscape()
  {
    this.b.setVisibility(0);
    this.j.setVisibility(8);
    switch (this.o)
    {
    default:
      return;
    case 0:
      showMPInfo(true);
      return;
    case 1:
      showMPInfo(false);
      return;
    case 2:
    case 3:
      this.i.setText(APCommMethod.getStringId(this, "unipay_continue"));
      showMPInfo(false);
      return;
    case 4:
    case 5:
    }
    this.i.setText(APCommMethod.getStringId(this, "unipay_continueopen"));
    showMPInfo(false);
  }

  public void showSuccWithPortralt()
  {
    this.b.setVisibility(0);
    this.j.setVisibility(8);
    if (this.o == 1)
      this.i.setVisibility(8);
    TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succtext"));
    TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succsavenum"));
    TextView localTextView3 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succsaveunit"));
    switch (this.o)
    {
    default:
    case 0:
    case 1:
      do
      {
        do
        {
          return;
          showMPInfo(true);
          localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
          localTextView3.setText(this.orderInfo.buyInfo.name);
        }
        while (!APAppDataInterface.singleton().getIsShowSaveNum());
        localTextView2.setText("×" + this.orderInfo.saveNum + this.orderInfo.buyInfo.unit);
        return;
        showMPInfo(false);
        localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_buy"));
        localTextView3.setText(this.orderInfo.buyInfo.name);
      }
      while (!APAppDataInterface.singleton().getIsShowSaveNum());
      localTextView2.setText("×" + this.orderInfo.saveNum + this.orderInfo.buyInfo.unit);
      return;
    case 2:
      showMPInfo(false);
      localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
      localTextView3.setText(APCommMethod.getStringId(this, "unipay_qd"));
      localTextView2.setText("×" + this.orderInfo.saveNum);
      this.i.setText(APCommMethod.getStringId(this, "unipay_continue"));
      return;
    case 3:
      showMPInfo(false);
      localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
      localTextView3.setText(APCommMethod.getStringId(this, "unipay_qb"));
      localTextView2.setText("×" + this.orderInfo.saveNum);
      this.i.setText(APCommMethod.getStringId(this, "unipay_continue"));
      return;
    case 4:
    case 5:
    }
    showMPInfo(false);
    localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_open"));
    localTextView3.setText(this.orderInfo.buyInfo.name);
    localTextView2.setText("×" + this.orderInfo.saveNum + APMonthDataInterface.singleton().getUnit());
    this.i.setText(APCommMethod.getStringId(this, "unipay_continueopen"));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APHFPaySuccessActivity
 * JD-Core Version:    0.6.0
 */
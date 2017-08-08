package com.pay.ui.saveAccount;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.channel.APRecoChannelActivity;
import com.pay.ui.common.APUICommonMethod;

public class APSaveAccountListNumActivity extends APRecoChannelActivity
{
  private final int[] a = { 10, 30, 50, 100 };
  private final int[] b = new int[4];
  private RelativeLayout c;
  private LinearLayout d;
  private int e = 60;

  public void dopay()
  {
    payAutoSelect();
  }

  protected void initSaveListwithPortrait()
  {
    ListView localListView = (ListView)findViewById(APCommMethod.getId(this, "unipay_id_apSaveValueList"));
    localListView.setAdapter(new APAccountListValueAdapter(this, this.a, this.b));
    if (this.a.length > 4)
      setListViewHeightBasedOnChildren(localListView, 4);
    localListView.setOnItemClickListener(new i(this));
  }

  protected void initUI()
  {
    int i1;
    label154: View localView;
    String str1;
    String[] arrayOfString;
    String str2;
    if (getResources().getConfiguration().orientation == 2)
    {
      this.d = ((LinearLayout)findViewById(APCommMethod.getId(this, "sorrl")));
      int i = this.a.length;
      LayoutInflater localLayoutInflater = LayoutInflater.from(this);
      WindowManager localWindowManager = (WindowManager)getSystemService("window");
      LinearLayout localLinearLayout1 = (LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_bugListLayout"));
      int j = APUICommonMethod.dip2px(this, this.e);
      LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)localLinearLayout1.getLayoutParams();
      localLayoutParams.leftMargin = j;
      localLayoutParams.rightMargin = j;
      int k = 50 + (this.e << 1);
      int m = localWindowManager.getDefaultDisplay().getWidth() - APUICommonMethod.dip2px(this, k);
      if (i <= 4);
      for (int n = m / i; ; n = m / 4)
      {
        i1 = 0;
        if (i1 < i)
          break;
        findViewById(APCommMethod.getId(this, "unipay_id_mpinfoIdText"));
        this.c = ((RelativeLayout)findViewById(APCommMethod.getId(this, "unipay_id_mpinfoIdView")));
        this.c.setVisibility(8);
        ((ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_CloseBtn"))).setOnClickListener(new g(this));
        ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_OtherChannel"))).setOnClickListener(new h(this));
        return;
      }
      localView = localLayoutInflater.inflate(APCommMethod.getLayoutId(this, "unipay_layout_tips_num_item"), null);
      LinearLayout localLinearLayout2 = (LinearLayout)localView.findViewById(APCommMethod.getId(this, "payLay"));
      localLinearLayout2.setLayoutParams(new LinearLayout.LayoutParams(n, -1));
      localLinearLayout2.setTag(Integer.valueOf(i1));
      localLinearLayout2.setMinimumHeight(APUICommonMethod.dip2px(this, 180.0F));
      localLinearLayout2.setOnClickListener(new j(this));
      str1 = String.valueOf(this.a[i1]);
      arrayOfString = APCommMethod.computerRate(str1, this.orderInfo.saveType);
      str2 = arrayOfString[0];
      if (arrayOfString.length <= 1)
        break label525;
    }
    label525: for (String str3 = arrayOfString[1]; ; str3 = "")
    {
      ImageView localImageView = (ImageView)localView.findViewById(APCommMethod.getId(this, "unipay_id_apPayIcon"));
      TextView localTextView1 = (TextView)localView.findViewById(APCommMethod.getId(this, "unipay_id_apPayNum"));
      TextView localTextView2 = (TextView)localView.findViewById(APCommMethod.getId(this, "unipay_id_apPayMoney"));
      TextView localTextView3 = (TextView)localView.findViewById(APCommMethod.getId(this, "unipay_id_moneyDecima"));
      localImageView.setBackgroundResource(APCommMethod.getDrawableId(this, "unipay_pic_channel_icon3"));
      localTextView1.setText(str1);
      localTextView2.setText(str2);
      localTextView3.setVisibility(0);
      localTextView3.setText("." + str3);
      this.d.addView(localView);
      i1++;
      break;
      if (getResources().getConfiguration().orientation != 1)
        break label154;
      initSaveListwithPortrait();
      break label154;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    int i = 0;
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
      finish();
    int m;
    while (true)
    {
      return;
      setContentView(APCommMethod.getLayoutId(this, "unipay_layout_tips_num"));
      this.saveType = APDataInterface.singleton().getOrderInfo().saveType;
      if (this.saveType != 3)
        break label212;
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleBuyInfo"))).setText("充值Q币");
      m = 0;
      if (m < this.a.length)
        break;
      label95: if ((this.a == null) || (this.b == null))
        continue;
      int j = getResources().getDisplayMetrics().densityDpi;
      int k = getResources().getDisplayMetrics().widthPixels;
      APLog.i("APSaveAccountListNumActivity", "densityDpi:" + j + " width:" + k);
      if ((j <= 240) && (k < 960))
        break label351;
    }
    label212: label351: for (this.e = 60; ; this.e = 20)
    {
      initUI();
      return;
      this.b[m] = this.a[m];
      m++;
      break;
      if (this.saveType == 2)
      {
        ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleBuyInfo"))).setText("充值Q点");
        while (i < this.a.length)
        {
          this.b[i] = this.a[i];
          this.a[i] = (10 * this.a[i]);
          i++;
        }
        break label95;
      }
      APUICommonMethod.showToast(this, "充值类型未定义");
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleBuyInfo"))).setText("充值Q点");
      while (i < this.a.length)
      {
        this.b[i] = this.a[i];
        this.a[i] = (10 * this.a[i]);
        i++;
      }
      break label95;
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      APDataReportManager.getInstance().insertData("sdk.accountlist.keyback", this.saveType);
      APUICommonMethod.popActivity();
      APCommMethod.payErrorCallBack(2, "");
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    super.onResume();
    APDataReportManager.getInstance().insertData("sdk.accountlist.show", this.saveType);
  }

  public void onStop()
  {
    super.onStop();
  }

  public void setListViewHeightBasedOnChildren(ListView paramListView, int paramInt)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null)
      return;
    int i = 0;
    int j = 0;
    while (true)
    {
      if (i >= localListAdapter.getCount());
      while (true)
      {
        ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
        localLayoutParams.height = (j + paramListView.getDividerHeight() * (paramInt - 1));
        paramListView.setLayoutParams(localLayoutParams);
        return;
        View localView = localListAdapter.getView(i, null, paramListView);
        localView.measure(0, 0);
        j += localView.getMeasuredHeight();
        if (paramInt >= localListAdapter.getCount())
          break;
        j = paramInt * localView.getMeasuredHeight();
      }
      i++;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.saveAccount.APSaveAccountListNumActivity
 * JD-Core Version:    0.6.0
 */
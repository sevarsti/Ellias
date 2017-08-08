package com.pay.ui.payCenter;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.GridView;
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
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.buyInfo.APBuyMonthInfo;
import com.pay.data.mp.APMPGamesItem;
import com.pay.data.mp.APMPGoodsItem;
import com.pay.data.mp.APMPSendInfo;
import com.pay.data.mp.APMPSendItem;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import com.pay.ui.channel.APRecoChannelActivity;
import com.pay.ui.common.APRotateTextView;
import com.pay.ui.common.APUICommonMethod;
import java.util.ArrayList;
import java.util.List;

public class APPayGameListNumActivity extends APRecoChannelActivity
{
  private TextView a;
  private RelativeLayout b;
  private LinearLayout c;
  private String[] d = null;
  private String[] e = null;
  private String[] f = null;
  private Handler g;
  private int h = 60;
  protected List listItem;

  public void dopay()
  {
    payAutoSelect();
  }

  protected void initSaveListwithPortrait()
  {
    this.d = APSaveValueList.singleton().getSaveNumber();
    this.e = APSaveValueList.singleton().getSaveMoney();
    this.f = APSaveValueList.singleton().getSaveName();
    if ((this.d == null) || (this.e == null))
      return;
    ListView localListView = (ListView)findViewById(APCommMethod.getId(this, "unipay_id_apSaveValueList"));
    localListView.setAdapter(new APGameListValueAdapter(this, this.listItem, this.d, this.e, this.f));
    if ((APMPSendInfo.getInstance().getIsHasUptoNumMpMPInfo()) && (this.saveType == 0))
      if (APMPSendInfo.getInstance().getUptoNumMpMpInfo().size() > 4)
        setListViewHeightBasedOnChildren(localListView, 4);
    while (true)
    {
      localListView.setOnItemClickListener(new l(this));
      if (this.saveType != 4)
        break;
      ImageButton localImageButton = (ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_ap_otherNumArrow"));
      if (localImageButton == null)
        break;
      localImageButton.setVisibility(8);
      return;
      if (this.d.length <= 5)
        continue;
      setListViewHeightBasedOnChildren(localListView, 5);
    }
  }

  protected void initUI()
  {
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_tips_num"));
    Object localObject1;
    label179: boolean bool;
    label311: int j;
    int k;
    int i12;
    int i13;
    int i2;
    int i3;
    label416: int i4;
    label419: View localView;
    String str4;
    int i6;
    String str7;
    int i7;
    TextView localTextView5;
    label636: String str8;
    APRotateTextView localAPRotateTextView;
    label697: LinearLayout localLinearLayout4;
    String str5;
    if (getResources().getConfiguration().orientation == 2)
    {
      this.c = ((LinearLayout)findViewById(APCommMethod.getId(this, "sorrl")));
      LinearLayout localLinearLayout1 = (LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_bugListLayout"));
      int i = APUICommonMethod.dip2px(this, this.h);
      LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)localLinearLayout1.getLayoutParams();
      localLayoutParams.leftMargin = i;
      localLayoutParams.rightMargin = i;
      this.d = APSaveValueList.singleton().getSaveNumber();
      this.e = APSaveValueList.singleton().getSaveMoney();
      this.f = APSaveValueList.singleton().getSaveName();
      if ((this.d == null) || (this.e == null));
      while (true)
        switch (this.saveType)
        {
        case 1:
        case 2:
        case 3:
        default:
          localObject1 = "";
          if (!APAppDataInterface.singleton().isElseNumberVisible())
            ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_OtherChannel"))).setVisibility(8);
          ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleBuyInfo"))).setText((CharSequence)localObject1);
          ((ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_CloseBtn"))).setOnClickListener(new i(this));
          ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_OtherChannel"))).setOnClickListener(new j(this));
          return;
          LayoutInflater localLayoutInflater = LayoutInflater.from(this);
          WindowManager localWindowManager = (WindowManager)getSystemService("window");
          if ((APMPSendInfo.getInstance().getIsHasUptoNumMpMPInfo()) && (this.orderInfo.saveType == 0))
          {
            bool = true;
            j = localWindowManager.getDefaultDisplay().getWidth();
            k = 50;
            if ((!APAppDataInterface.singleton().isElseNumberVisible()) || ((APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate) && (this.saveType == 5)))
              k = 10;
            if (!bool)
              break label1177;
            i12 = j - APUICommonMethod.dip2px(this, k + (this.h << 1));
            i13 = APMPSendInfo.getInstance().getUptoNumMpMpInfo().size();
            if (i13 > 4)
              break label1156;
            int i15 = i12 / i13;
            i2 = i13;
            i3 = i15;
            i4 = 0;
            if (i4 >= i2)
              continue;
            localView = localLayoutInflater.inflate(APCommMethod.getLayoutId(this, "unipay_layout_tips_num_item"), null);
            LinearLayout localLinearLayout2 = (LinearLayout)localView.findViewById(APCommMethod.getId(this, "payLay"));
            localLinearLayout2.setLayoutParams(new LinearLayout.LayoutParams(i3, -1));
            localLinearLayout2.setMinimumHeight(APUICommonMethod.dip2px(this, 180.0F));
            localLinearLayout2.setTag(Integer.valueOf(i4));
            localLinearLayout2.setOnClickListener(new m(this, bool));
            str4 = "";
            if (!bool)
              break label1636;
            i6 = ((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(i4)).sendGames.limitNum;
            str7 = String.valueOf(i6);
            i7 = ((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(i4)).sendGames.getSendGamesNum();
            localTextView5 = (TextView)localView.findViewById(APCommMethod.getId(this, "unipay_id_mpNum"));
            if (i7 <= 0)
              break label1274;
            localTextView5.setVisibility(0);
            if (APMPSendInfo.getInstance().isHasUptoSendGoods())
              break label1248;
            ((ImageView)localView.findViewById(APCommMethod.getId(this, "unipay_id_mpSendIcon"))).setVisibility(0);
            localTextView5.setText(String.valueOf(i7));
            str8 = ((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(i4)).sendGames.sendExt;
            localAPRotateTextView = (APRotateTextView)localView.findViewById(APCommMethod.getId(this, "unipay_id_mpExt"));
            if ((str8 != null) && (!str8.equals("")))
              break label1284;
            localAPRotateTextView.setVisibility(8);
            APMPSendItem localAPMPSendItem = (APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(i4);
            if ((localAPMPSendItem.sendGoodsList == null) || (localAPMPSendItem.sendGoodsList.size() <= 0))
              break label1583;
            ImageView localImageView2 = (ImageView)localView.findViewById(APCommMethod.getId(this, "unipay_id_apPaySendIcon"));
            localLinearLayout4 = (LinearLayout)localView.findViewById(APCommMethod.getId(this, "unipay_id_mpliear"));
            ((LinearLayout)localView.findViewById(APCommMethod.getId(this, "unipay_id_sendGoods"))).setVisibility(0);
            localImageView2.setVisibility(0);
            if (!localAPMPSendItem.getIsHasGoodsPic())
              break label1308;
            localLinearLayout4.setVisibility(8);
            APGameListGoodsPicAdapter localAPGameListGoodsPicAdapter = new APGameListGoodsPicAdapter(this, ((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(i4)).sendGoodsList);
            GridView localGridView = (GridView)localView.findViewById(APCommMethod.getId(this, "unipay_id_mpGoodsPic"));
            localGridView.setNumColumns(1);
            localGridView.setVisibility(0);
            localGridView.setAdapter(localAPGameListGoodsPicAdapter);
            str5 = str7;
          }
        case 0:
        case 4:
        case 5:
        }
    }
    while (true)
    {
      label873: int i5 = APDataInterface.singleton().getOrderInfo().saveType;
      String[] arrayOfString;
      label926: TextView localTextView1;
      TextView localTextView4;
      label1097: label1248: label1274: ArrayList localArrayList;
      if (((i5 == 4) || (i5 == 5)) && (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate))
      {
        arrayOfString = APCommMethod.moneyFormat(Float.valueOf(this.e[i4]).floatValue());
        String str6 = arrayOfString[0];
        if (arrayOfString.length > 1)
          str4 = arrayOfString[1];
        ImageView localImageView1 = (ImageView)localView.findViewById(APCommMethod.getId(this, "unipay_id_apPayIcon"));
        localTextView1 = (TextView)localView.findViewById(APCommMethod.getId(this, "unipay_id_apPayNum"));
        TextView localTextView2 = (TextView)localView.findViewById(APCommMethod.getId(this, "unipay_id_apPayMoney"));
        TextView localTextView3 = (TextView)localView.findViewById(APCommMethod.getId(this, "unipay_id_moneyDecima"));
        localTextView4 = (TextView)localView.findViewById(APCommMethod.getId(this, "unipay_id_mpNum"));
        localImageView1.setBackgroundDrawable(APDataInterface.singleton().getAppResDrawable());
        if (this.orderInfo.saveType != 4)
          break label1692;
        if (APMonthDataInterface.singleton().getOpenType() != APMonthDataInterface.MonthOpenType.OpenType_NoRate)
          break label1665;
        localTextView4.setText(this.f[i4]);
        localTextView4.setTextSize(17.0F);
        localTextView4.setVisibility(0);
        localTextView1.setVisibility(8);
        localTextView2.setText(str6);
        if (!TextUtils.isEmpty(str4))
          localTextView3.setText("." + str4);
        this.c.addView(localView);
        i4++;
        break label419;
        bool = false;
        break label311;
        label1156: int i14 = (int)(i12 / 4.5F);
        i2 = i13;
        i3 = i14;
        break label416;
        label1177: int m = j - APUICommonMethod.dip2px(this, k + (this.h << 1));
        int n = this.d.length;
        if (n <= 5)
        {
          int i11 = m / n;
          i2 = n;
          i3 = i11;
          break label416;
        }
        int i1 = (int)(m / 5.5F);
        i2 = n;
        i3 = i1;
        break label416;
        localTextView5.setText("+" + i7);
        break label636;
        localTextView5.setVisibility(8);
        break label636;
        label1284: localAPRotateTextView.setVisibility(0);
        localAPRotateTextView.setText(str8);
        localTextView5.setTextColor(-1864636);
        break label697;
        label1308: localArrayList = APMPSendInfo.getInstance().getUptoNumMpSendGoodsList(i6);
      }
      int i9;
      int i10;
      label1583: label1636: 
      do
      {
        int i8;
        while (true)
        {
          String str9;
          try
          {
            i8 = localArrayList.size();
            if (i8 <= 5)
              break label2249;
            i9 = 5;
            break label2253;
            TextView localTextView6 = new TextView(this);
            localTextView6.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            localTextView6.setTextSize(10.0F);
            localTextView6.setTextColor(APCommMethod.getColorId(this, "unipay_thin5_color"));
            localTextView6.setSingleLine(true);
            localTextView6.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            localLinearLayout4.addView(localTextView6);
            str9 = ((APMPGoodsItem)localArrayList.get(i10)).name;
            String str10 = ((APMPGoodsItem)localArrayList.get(i10)).num;
            byte[] arrayOfByte = str9.getBytes();
            if (arrayOfByte.length == str9.length())
              continue;
            if (str9.length() <= 5)
              break label2242;
            localObject2 = str9.substring(0, 4) + "...";
            localTextView6.setText(localObject2 + "×" + str10);
            i10++;
            break label2256;
            if (arrayOfByte.length <= 10)
              break label2242;
            String str11 = new String(arrayOfByte, 0, 8) + "...";
            localObject2 = str11;
            continue;
          }
          catch (Exception localException)
          {
            str5 = str7;
          }
          break label873;
          LinearLayout localLinearLayout3 = (LinearLayout)localView.findViewById(APCommMethod.getId(this, "unipay_id_sendGoods"));
          if (!APMPSendInfo.getInstance().isHasUptoSendGoods())
          {
            localLinearLayout3.setVisibility(8);
            str5 = str7;
            break label873;
          }
          localLinearLayout3.setVisibility(0);
          str5 = str7;
          break label873;
          str5 = this.d[i4];
          break label873;
          arrayOfString = APCommMethod.computerRate(str5, this.orderInfo.saveType);
          break label926;
          localTextView1.setText(str5);
          localTextView4.setText(APMonthDataInterface.singleton().getUnit());
          localTextView4.setVisibility(0);
          break label1097;
          if (this.orderInfo.saveType == 5)
          {
            if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
            {
              localTextView4.setText(this.f[i4]);
              localTextView4.setTextSize(17.0F);
            }
            while (true)
            {
              localTextView4.setVisibility(0);
              localTextView1.setVisibility(8);
              break;
              StringBuilder localStringBuilder = new StringBuilder(String.valueOf(((APBuyMonthInfo)this.orderInfo.buyInfo).serviceName));
              localTextView4.setText(" × " + str5);
              localTextView4.setTextSize(17.0F);
            }
          }
          localTextView1.setText(str5);
          break label1097;
          if (getResources().getConfiguration().orientation != 1)
            break;
          initSaveListwithPortrait();
          break;
          localObject1 = "充值" + this.orderInfo.buyInfo.name;
          this.a = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_mpinfoIdText")));
          this.b = ((RelativeLayout)findViewById(APCommMethod.getId(this, "unipay_id_mpinfoIdView")));
          String str2 = APMPSendInfo.getInstance().getMpTitle();
          if (!TextUtils.isEmpty(str2))
          {
            this.a.setText(str2);
            this.b.setVisibility(0);
            break label179;
          }
          if ((APMPSendInfo.getInstance().getIsHasFirstMPInfo()) && (APDataInterface.singleton().getUserInfo().isFirstCharge))
          {
            String str3 = APMPSendInfo.getInstance().getFirstMpInfo(this.orderInfo.buyInfo.name);
            this.a.setText(str3);
            this.b.setVisibility(0);
            break label179;
          }
          this.b.setVisibility(8);
          break label179;
          String str1 = "开通" + this.orderInfo.buyInfo.name;
          if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_Rate)
          {
            CheckBox localCheckBox = (CheckBox)findViewById(APCommMethod.getId(this, "unipay_id_ap_apAutoPayCheckBox"));
            localCheckBox.setVisibility(0);
            APBuyMonthInfo localAPBuyMonthInfo = (APBuyMonthInfo)APDataInterface.singleton().getOrderInfo().buyInfo;
            localAPBuyMonthInfo.autoPay = APMonthDataInterface.singleton().getAutoPay();
            if (localAPBuyMonthInfo.autoPay.equals("1"))
            {
              localAPBuyMonthInfo.autoPay = "1";
              localCheckBox.setChecked(true);
            }
            while (true)
            {
              localCheckBox.setOnCheckedChangeListener(new k(this));
              localObject1 = str1;
              break;
              if (!localAPBuyMonthInfo.autoPay.equals("0"))
                continue;
              localCheckBox.setChecked(false);
            }
          }
          ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_OtherChannel"))).setVisibility(8);
          localObject1 = str1;
          break label179;
          localObject1 = "购买" + this.orderInfo.buyInfo.name;
          if (APMonthDataInterface.singleton().getOpenType() != APMonthDataInterface.MonthOpenType.OpenType_NoRate)
            break label179;
          ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_OtherChannel"))).setVisibility(8);
          break label179;
          Object localObject2 = str9;
        }
        i9 = i8;
        i10 = 0;
      }
      while (i10 < i9);
      label1665: label1692: label2242: label2249: label2253: label2256: str5 = str7;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
    {
      finish();
      return;
    }
    this.g = new Handler();
    this.g.postDelayed(new n(this, 0), 100L);
    AndroidPay.singleton().isUILaunched = true;
    int i = getResources().getDisplayMetrics().densityDpi;
    int j = getResources().getDisplayMetrics().widthPixels;
    APLog.i("APPayGameListNumActivity", "densityDpi:" + i + " width:" + j);
    if ((i > 240) || (j >= 960))
    {
      this.h = 60;
      return;
    }
    this.h = 20;
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      APDataReportManager.getInstance().insertData("sdk.gamelist.keyback", this.saveType);
      APUICommonMethod.popActivity();
      APCommMethod.payErrorCallBack(2, "");
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    APDataReportManager.getInstance().insertData("sdk.gamelist.show", this.saveType);
    super.onResume();
  }

  public void onStart()
  {
    super.onStart();
  }

  public void setListViewHeightBasedOnChildren(ListView paramListView, int paramInt)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null)
      return;
    int m;
    int i;
    int n;
    if (paramInt <= localListAdapter.getCount())
    {
      m = 0;
      i = 0;
      n = 0;
      if (m < paramInt);
    }
    for (int j = n; ; j = 0)
    {
      int k = (int)(j + 0.5D * i);
      APLog.i("APPayGameListNum", "totalHeight=" + k);
      ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
      localLayoutParams.height = (k + (int)(paramListView.getDividerHeight() * (paramInt - 1.0F)));
      paramListView.setLayoutParams(localLayoutParams);
      return;
      View localView = localListAdapter.getView(m, null, paramListView);
      localView.measure(0, 0);
      int i1 = n + localView.getMeasuredHeight();
      int i2 = localView.getMeasuredHeight();
      APLog.i("APPayGameListNum", "i=" + m + " Height:" + localView.getMeasuredHeight());
      m++;
      i = i2;
      n = i1;
      break;
      i = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.APPayGameListNumActivity
 * JD-Core Version:    0.6.0
 */
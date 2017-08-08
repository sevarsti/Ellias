package com.pay.ui.common;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
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

public class APMcardSuccessActivity extends APActivity
{
  private String A = "";
  private int B;
  private String C = "";
  private View.OnClickListener D = new j(this);
  private View.OnClickListener E = new l(this);
  IAPHttpAnsObserver a = new m(this);
  private boolean b = true;
  private String c = "1001";
  private String d = "1002";
  private String e = "1004";
  private String f = "1010";
  private String g = "1013";
  private String h = "1015";
  private String i = "1";
  private String j = "-1";
  private String k = "999";
  private String l = "1003";
  private String m = "1005";
  private String n = "1008";
  private String o = "1009";
  private String p = "1012";
  private String q = "4";
  private String r = "9";
  private String s = "1006";
  private String t = "2";
  private String u = "1007";
  private String v = "1011";
  private String w = "3";
  private String x = "5";
  private String y = "6";
  private String z = "7";

  private void a()
  {
    if ((this.C.equals(this.s)) || (this.C.equals(this.t)))
    {
      APCommMethod.paySuccCallBack(5, 0, 0);
      return;
    }
    APCommMethod.paySuccCallBack(5, -1, -1);
  }

  private void a(String paramString)
  {
    Button localButton1 = (Button)findViewById(APCommMethod.getId(this, "unipay_id_apBuyContinue"));
    switch (this.B)
    {
    default:
      Button localButton2 = (Button)findViewById(APCommMethod.getId(this, "unipay_id_apBackGame"));
      localButton2.setVisibility(0);
      localButton2.setOnClickListener(this.E);
      if ((!paramString.equals(this.l)) && (!paramString.equals(this.m)) && (!paramString.equals(this.n)) && (!paramString.equals(this.o)) && (!paramString.equals(this.p)) && (!paramString.equals(this.q)) && (!paramString.equals(this.r)))
        break;
      ((ImageView)findViewById(APCommMethod.getId(this, "unipay_id_TipsImage"))).setImageResource(APCommMethod.getDrawableId(this, "unipay_pic_wrong"));
      ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_ProcessType"))).setVisibility(0);
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine1"))).setText("对不起，支付出错");
      TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine2"));
      localTextView1.setText("您可以联系客服中心(" + this.A + ")");
      localTextView1.setVisibility(0);
      localTextView1.setOnClickListener(new q(this));
      LinearLayout localLinearLayout1 = (LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine3"));
      localLinearLayout1.setVisibility(0);
      localLinearLayout1.setOnClickListener(new r(this));
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessOrder"))).setText("订单号：" + APDataInterface.singleton().getOrderInfo().orderId);
      ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apSearchOrder"))).setVisibility(8);
    case 0:
    case 2:
    case 3:
    case 1:
    case 4:
    case 5:
    }
    do
    {
      return;
      localButton1.setVisibility(0);
      localButton1.setOnClickListener(this.D);
      break;
      localButton1.setVisibility(8);
      break;
      if ((paramString.equals(this.s)) || (paramString.equals(this.t)))
      {
        ((ImageView)findViewById(APCommMethod.getId(this, "unipay_id_TipsImage"))).setImageResource(APCommMethod.getDrawableId(this, "unipay_pic_suc"));
        ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_SuccessLayout"))).setVisibility(0);
        ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_SuccessText"))).setText("支付成功");
        TextView localTextView2;
        TextView localTextView3;
        TextView localTextView4;
        if (getResources().getConfiguration().orientation == 1)
        {
          this.B = APDataInterface.singleton().getOrderInfo().saveType;
          ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_saveNum"))).setVisibility(0);
          localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succtext"));
          localTextView3 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succsavenum"));
          localTextView4 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succsaveunit"));
          switch (this.B)
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
          ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apSearchOrder"))).setVisibility(8);
          ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine1"))).setVisibility(8);
          ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine3"))).setVisibility(8);
          ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_mcardTips"))).setVisibility(0);
          showMPInfo(true);
          return;
          localTextView2.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
          localTextView3.setText(this.orderInfo.saveNum);
          localTextView4.setText(this.orderInfo.buyInfo.unit + this.orderInfo.buyInfo.name);
          continue;
          localTextView2.setText(APCommMethod.getStringId(this, "unipay_succ_buy"));
          if (APAppDataInterface.singleton().getIsShowSaveNum())
            localTextView3.setText(this.orderInfo.saveNum);
          localTextView4.setText(this.orderInfo.buyInfo.unit + this.orderInfo.buyInfo.name);
          continue;
          localTextView2.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
          localTextView3.setText(this.orderInfo.saveNum);
          localTextView4.setText(APCommMethod.getStringId(this, "unipay_qd"));
          continue;
          localTextView2.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
          localTextView3.setText(this.orderInfo.saveNum);
          localTextView4.setText(APCommMethod.getStringId(this, "unipay_qb"));
          continue;
          localTextView2.setText(APCommMethod.getStringId(this, "unipay_succ_open"));
          localTextView3.setText(this.orderInfo.saveNum + APMonthDataInterface.singleton().getUnit());
          localTextView4.setText(this.orderInfo.buyInfo.name);
          continue;
          if (getResources().getConfiguration().orientation != 2)
            continue;
          ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine2"))).setVisibility(8);
        }
      }
      if ((paramString.equals(this.u)) || (paramString.equals(this.v)) || (paramString.equals(this.w)))
      {
        ((ImageView)findViewById(APCommMethod.getId(this, "unipay_id_TipsImage"))).setImageResource(APCommMethod.getDrawableId(this, "unipay_pic_wrong"));
        ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_ProcessType"))).setVisibility(0);
        ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine1"))).setText("您选择的面值与充值卡实际面值可能不符，\n充值卡内金额将在第二天到账,");
        TextView localTextView5 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine2"));
        localTextView5.setText("如未到账，请联系客服中心(" + this.A + ")");
        localTextView5.setVisibility(0);
        localTextView5.setOnClickListener(new s(this));
        LinearLayout localLinearLayout2 = (LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine3"));
        localLinearLayout2.setVisibility(0);
        localLinearLayout2.setOnClickListener(new k(this));
        ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessOrder"))).setText("订单号：" + APDataInterface.singleton().getOrderInfo().orderId);
        ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apSearchOrder"))).setVisibility(8);
        return;
      }
      if (paramString.equals(this.x))
      {
        b("支付失败，卡号或密码出错，请核对后重试");
        return;
      }
      if (paramString.equals(this.y))
      {
        b("支付失败，无效充值卡或不支持该卡");
        return;
      }
      if (!paramString.equals(this.z))
        continue;
      b("支付失败，该卡已被使用");
      return;
    }
    while ((!paramString.equals(this.c)) && (!paramString.equals(this.d)) && (!paramString.equals(this.e)) && (!paramString.equals(this.f)) && (!paramString.equals(this.g)) && (!paramString.equals(this.h)) && (!paramString.equals(this.i)) && (!paramString.equals(this.j)) && (!paramString.equals(this.k)));
    ((ImageView)findViewById(APCommMethod.getId(this, "unipay_id_TipsImage"))).setImageResource(APCommMethod.getDrawableId(this, "unipay_pic_wait"));
    showMPInfo(false);
    ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_ProcessType"))).setVisibility(0);
    if (this.b)
    {
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine1"))).setText("支付结果可能有1分钟左右的延时，请稍候");
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine2"))).setVisibility(8);
      ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine3"))).setVisibility(8);
      ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apBackGame"))).setVisibility(8);
      ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apBuyContinue"))).setVisibility(8);
      Button localButton3 = (Button)findViewById(APCommMethod.getId(this, "unipay_id_apSearchOrder"));
      localButton3.setVisibility(0);
      localButton3.setOnClickListener(new n(this));
    }
    while (true)
    {
      new t(this, 60000L, 1000L).start();
      return;
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine1"))).setText("支付结果延时,");
      TextView localTextView6 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine2"));
      localTextView6.setText("您可以联系客服中心(" + this.A + ")");
      localTextView6.setVisibility(0);
      localTextView6.setOnClickListener(new o(this));
      LinearLayout localLinearLayout3 = (LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine3"));
      localLinearLayout3.setVisibility(0);
      localLinearLayout3.setOnClickListener(new p(this));
      ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessOrder"))).setText("订单号：" + APDataInterface.singleton().getOrderInfo().orderId);
      ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apSearchOrder"))).setVisibility(8);
    }
  }

  private void b(String paramString)
  {
    ((ImageView)findViewById(APCommMethod.getId(this, "unipay_id_TipsImage"))).setImageResource(APCommMethod.getDrawableId(this, "unipay_pic_wrong"));
    ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_ProcessType"))).setVisibility(0);
    ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine1"))).setText(paramString);
    ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_apProcessLine3"))).setVisibility(8);
    ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apSearchOrder"))).setVisibility(8);
  }

  public void callPhone()
  {
    try
    {
      startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + this.A)));
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void initUI()
  {
    Bundle localBundle = getIntent().getExtras();
    this.B = APDataInterface.singleton().getOrderInfo().saveType;
    this.C = localBundle.getString("state");
    if (getResources().getConfiguration().orientation == 2)
      if (this.B == 0)
      {
        this.A = APCommMethod.getStringId(this, "unipay_game_mpay_tel");
        initGameTitle();
        showMPInfo(true);
      }
    while (true)
    {
      a(this.C);
      return;
      if ((this.B == 3) || (this.B == 2))
      {
        initAccountTitle(this.B);
        continue;
      }
      if (this.B == 1)
      {
        initGoodsTitle();
        continue;
      }
      if ((this.B != 4) && (this.B != 5))
        continue;
      initMonthTitle();
      continue;
      if (getResources().getConfiguration().orientation != 1)
        continue;
      if (this.B == 0)
        this.A = APCommMethod.getStringId(this, "unipay_game_mpay_tel");
      TextView localTextView1 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succtext"));
      TextView localTextView2 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succsavenum"));
      TextView localTextView3 = (TextView)findViewById(APCommMethod.getId(this, "unipay_id_succsaveunit"));
      Button localButton = (Button)findViewById(APCommMethod.getId(this, "unipay_id_apBuyContinue"));
      switch (this.B)
      {
      default:
        break;
      case 0:
        localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
        localTextView3.setText(this.orderInfo.buyInfo.name);
        if (!APAppDataInterface.singleton().getIsShowSaveNum())
          continue;
        localTextView2.setText("×" + this.orderInfo.saveNum + this.orderInfo.buyInfo.unit);
        break;
      case 1:
        localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_buy"));
        localTextView3.setText(this.orderInfo.buyInfo.name);
        if (!APAppDataInterface.singleton().getIsShowSaveNum())
          continue;
        localTextView2.setText("×" + this.orderInfo.saveNum + this.orderInfo.buyInfo.unit);
        break;
      case 2:
        this.A = APCommMethod.getStringId(this, "unipay_notgame_mpay_tel");
        localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
        localTextView3.setText(APCommMethod.getStringId(this, "unipay_qd"));
        localTextView2.setText("×" + this.orderInfo.saveNum);
        localButton.setVisibility(0);
        localButton.setText(APCommMethod.getStringId(this, "unipay_continue"));
        break;
      case 3:
        this.A = APCommMethod.getStringId(this, "unipay_notgame_mpay_tel");
        localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_save"));
        localTextView3.setText(APCommMethod.getStringId(this, "unipay_qb"));
        localTextView2.setText("×" + this.orderInfo.saveNum);
        localButton.setVisibility(0);
        localButton.setText(APCommMethod.getStringId(this, "unipay_continue"));
        break;
      case 4:
      case 5:
        this.A = APCommMethod.getStringId(this, "unipay_notgame_mpay_tel");
        localTextView1.setText(APCommMethod.getStringId(this, "unipay_succ_open"));
        localTextView3.setText(this.orderInfo.buyInfo.name);
        localTextView2.setText("×" + this.orderInfo.saveNum + APMonthDataInterface.singleton().getUnit());
        localButton.setVisibility(0);
        localButton.setText(APCommMethod.getStringId(this, "unipay_continueopen"));
      }
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_pcardsuccess"));
    this.A = APCommMethod.getStringId(this, "unipay_notgame_mpay_tel");
    initUI();
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      APDataReportManager.getInstance().insertData("sdk.telsucc.keyback", APDataInterface.singleton().getOrderInfo().saveType);
      finish();
      overridePendingTransition(APCommMethod.getAnimId(this, "unipay_anim_in_from_left"), APCommMethod.getAnimId(this, "unipay_anim_out_to_right"));
      APUICommonMethod.popActivity();
      a();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onStart()
  {
    super.onStart();
    APDataReportManager.getInstance().insertData("sdk.telsucc.show", APDataInterface.singleton().getOrderInfo().saveType);
  }

  public void showMPInfo(boolean paramBoolean)
  {
    ArrayList localArrayList1;
    GridView localGridView;
    if (this.B == 0)
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APMcardSuccessActivity
 * JD-Core Version:    0.6.0
 */
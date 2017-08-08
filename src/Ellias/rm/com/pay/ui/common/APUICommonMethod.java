package com.pay.ui.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.buyInfo.APBuyGoodsInfo;
import com.pay.data.buyInfo.APBuyMonthInfo;
import com.pay.data.mp.APMPGoodsItem;
import com.pay.data.mp.APMPSendInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class APUICommonMethod
{
  private static Stack a = null;
  private static HashMap b = null;

  public static void delActivity(Activity paramActivity)
  {
    if (a == null)
      return;
    a.remove(paramActivity);
  }

  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }

  public static void dismissWaitDialog()
  {
    if (b == null);
    while (true)
    {
      return;
      try
      {
        Iterator localIterator = b.entrySet().iterator();
        while (localIterator.hasNext())
        {
          ProgressDialog localProgressDialog = (ProgressDialog)((Map.Entry)localIterator.next()).getKey();
          if (!localProgressDialog.isShowing())
            continue;
          localProgressDialog.dismiss();
          b.remove(localProgressDialog);
        }
      }
      catch (Exception localException)
      {
      }
    }
  }

  public static APDiamondInfo getResCurrDiamondInfo()
  {
    String str = APDataInterface.singleton().getUserInfo().vipType;
    APLog.i("getResCurrDiamondInfo", str);
    boolean bool = TextUtils.isEmpty(str);
    APDiamondInfo localAPDiamondInfo = null;
    if (!bool)
    {
      localAPDiamondInfo = new APDiamondInfo();
      if (!str.equals("huangzuan"))
        break label67;
      localAPDiamondInfo.diamondName = "黄钻";
      localAPDiamondInfo.imageResId = APCommMethod.getDrawableId(AndroidPay.singleton().applicationContext, "unipay_pic_yellodiamond");
    }
    label67: 
    do
    {
      return localAPDiamondInfo;
      if (str.equals("lanzuan"))
      {
        localAPDiamondInfo.diamondName = "蓝钻";
        localAPDiamondInfo.imageResId = APCommMethod.getDrawableId(AndroidPay.singleton().applicationContext, "unipay_pic_bulediamond");
        return localAPDiamondInfo;
      }
      if (str.equals("huiyuan"))
      {
        localAPDiamondInfo.diamondName = "会员";
        localAPDiamondInfo.imageResId = APCommMethod.getDrawableId(AndroidPay.singleton().applicationContext, "unipay_pic_huiyuan");
        return localAPDiamondInfo;
      }
      if (str.equals("mozuan"))
      {
        localAPDiamondInfo.diamondName = "魔钻";
        localAPDiamondInfo.imageResId = APCommMethod.getDrawableId(AndroidPay.singleton().applicationContext, "unipay_pic_modiamond");
        return localAPDiamondInfo;
      }
      if (!str.equals("fenzuan"))
        continue;
      localAPDiamondInfo.diamondName = "粉钻";
      localAPDiamondInfo.imageResId = APCommMethod.getDrawableId(AndroidPay.singleton().applicationContext, "unipay_pic_fendiamond");
      return localAPDiamondInfo;
    }
    while (!str.equals("lvzuan"));
    localAPDiamondInfo.diamondName = "绿钻";
    localAPDiamondInfo.imageResId = APCommMethod.getDrawableId(AndroidPay.singleton().applicationContext, "unipay_pic_greendiamond");
    return localAPDiamondInfo;
  }

  public static void popActivity()
  {
    try
    {
      if (a == null)
      {
        return;
        while (true)
        {
          int j = a.size();
          if (i >= j)
          {
            releaseActivityStack();
            return;
          }
          if (a.get(i) != null)
            ((Activity)a.get(i)).finish();
          i++;
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        continue;
        int i = 0;
      }
    }
  }

  public static void pushActivity(Activity paramActivity)
  {
    if (a == null)
      a = new Stack();
    a.push(paramActivity);
  }

  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat / paramContext.getResources().getDisplayMetrics().density);
  }

  public static String reCostByRate()
  {
    String str1 = APDataInterface.singleton().getOrderInfo().saveNum;
    int i;
    try
    {
      int j = Integer.parseInt(str1);
      i = j;
      if (APDataInterface.singleton().getOrderInfo().saveType != 0);
    }
    catch (Exception localException1)
    {
      try
      {
        float f2 = Float.parseFloat(reGameRate());
        String str6 = String.valueOf(i / f2);
        return str6;
        localException1 = localException1;
        APLog.i("APUICommonMethod reCostByRate", localException1.toString());
        i = 0;
      }
      catch (Exception localException6)
      {
        APLog.i("APUICommonMethod reCostByRate game", localException6.toString());
        return "";
      }
    }
    if (APDataInterface.singleton().getOrderInfo().saveType == 1)
      try
      {
        String str5 = String.valueOf(i * Integer.parseInt(reGoodsRate()) / 100.0F);
        return str5;
      }
      catch (Exception localException5)
      {
        APLog.i("APUICommonMethod reCostByRate goods", localException5.toString());
        return "";
      }
    if ((APDataInterface.singleton().getOrderInfo().saveType == 4) || (APDataInterface.singleton().getOrderInfo().saveType == 5))
      try
      {
        String str2 = String.valueOf(i * Integer.parseInt(reMonthsRate()) / 100.0F);
        return str2;
      }
      catch (Exception localException2)
      {
        APLog.i("APUICommonMethod reCostByRate months", localException2.toString());
        return "";
      }
    if (APDataInterface.singleton().getOrderInfo().saveType == 3)
      try
      {
        String str4 = String.valueOf(i);
        return str4;
      }
      catch (Exception localException4)
      {
        APLog.i("APUICommonMethod reCostByRate qb", localException4.toString());
        return "";
      }
    if (APDataInterface.singleton().getOrderInfo().saveType == 2)
    {
      float f1 = i / 10.0F;
      try
      {
        String str3 = String.valueOf(f1);
        return str3;
      }
      catch (Exception localException3)
      {
        APLog.i("APUICommonMethod reCostByRate qd", localException3.toString());
      }
    }
    return "";
  }

  public static String reGameRate()
  {
    return String.valueOf(APDataInterface.singleton().getOrderInfo().buyInfo.price);
  }

  public static String reGoodsRate()
  {
    APBuyGoodsInfo localAPBuyGoodsInfo = (APBuyGoodsInfo)APDataInterface.singleton().getOrderInfo().buyInfo;
    if (APDataInterface.singleton().getUserInfo().vipType.length() != 0)
      return localAPBuyGoodsInfo.disPrice;
    return localAPBuyGoodsInfo.price;
  }

  public static String reMonthsRate()
  {
    return String.valueOf(((APBuyMonthInfo)APDataInterface.singleton().getOrderInfo().buyInfo).price);
  }

  public static void releaseActivityStack()
  {
    if (a != null)
      a.clear();
    a = null;
  }

  public static void releaseLoadingMap()
  {
    if (b != null)
      b.clear();
    b = null;
  }

  public static void showToast(Context paramContext, String paramString)
  {
    if ((paramString == null) || (paramString.equals("")))
      return;
    View localView = LayoutInflater.from(paramContext).inflate(APCommMethod.getLayoutId(paramContext, "unipay_layout_toast_custom"), null);
    ((TextView)localView.findViewById(APCommMethod.getId(paramContext, "unipay_id_apToastText"))).setText(paramString);
    Toast localToast = Toast.makeText(paramContext, paramString, 1);
    localToast.setGravity(17, 0, 0);
    localToast.setDuration(500);
    localToast.setView(localView);
    localToast.show();
  }

  public static void showToast(Context paramContext, String paramString, int paramInt)
  {
    if ((paramString == null) || (paramString.equals("")))
      return;
    View localView = LayoutInflater.from(paramContext).inflate(APCommMethod.getLayoutId(paramContext, "unipay_layout_toast_custom"), null);
    ((TextView)localView.findViewById(APCommMethod.getId(paramContext, "unipay_id_apToastText"))).setText(paramString);
    ImageView localImageView = (ImageView)localView.findViewById(APCommMethod.getId(paramContext, "unipay_id_apToastImg"));
    localImageView.setBackgroundDrawable(null);
    localImageView.setImageResource(paramInt);
    Toast localToast = Toast.makeText(paramContext, paramString, 1);
    localToast.setGravity(17, 0, 0);
    localToast.setDuration(500);
    localToast.setView(localView);
    localToast.show();
  }

  public static void showToast(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    if ((paramString1 == null) || (paramString1.equals("")))
      return;
    View localView1 = LayoutInflater.from(paramContext).inflate(APCommMethod.getLayoutId(paramContext, "unipay_layout_toast_custom"), null);
    ((TextView)localView1.findViewById(APCommMethod.getId(paramContext, "unipay_id_apToastText"))).setText(paramString1);
    View localView2 = localView1.findViewById(APCommMethod.getId(paramContext, "unipay_id_apToastImg"));
    Toast localToast;
    if (!TextUtils.isEmpty(paramString2))
    {
      localView2.setBackgroundDrawable(APCommMethod.getDrawable(paramContext, paramString2));
      localToast = Toast.makeText(paramContext, paramString1, 1);
      if (!paramBoolean)
        break label133;
      localToast.setGravity(49, 0, 0);
    }
    while (true)
    {
      localToast.setDuration(500);
      localToast.setView(localView1);
      localToast.show();
      return;
      localView2.setVisibility(8);
      break;
      label133: localToast.setGravity(17, 0, 0);
    }
  }

  public static void showToastWithTimesAndImage(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    View localView = LayoutInflater.from(paramContext).inflate(APCommMethod.getLayoutId(paramContext, "unipay_layout_toast_custom"), null);
    TextView localTextView = (TextView)localView.findViewById(APCommMethod.getId(paramContext, "unipay_id_apToastText"));
    ImageView localImageView = (ImageView)localView.findViewById(APCommMethod.getId(paramContext, "unipay_id_apToastImg"));
    localTextView.setText(paramString);
    localImageView.setBackgroundResource(paramInt2);
    Toast localToast = Toast.makeText(paramContext, paramString, 1);
    localToast.setGravity(17, 0, 0);
    localToast.setDuration(paramInt1);
    localToast.setView(localView);
    localToast.show();
  }

  public static void showWaitDialog(Context paramContext, String paramString)
  {
    try
    {
      if (b == null)
        b = new HashMap();
      Iterator localIterator = b.entrySet().iterator();
      if (!localIterator.hasNext());
      for (int i = 0; ; i = 1)
      {
        if (i != 0)
          return;
        APProgressDialog localAPProgressDialog = new APProgressDialog(paramContext);
        if ((paramString != null) && (paramString.length() != 0))
          localAPProgressDialog.setMessage(paramString);
        b.put(localAPProgressDialog, paramContext);
        localAPProgressDialog.setOnCancelListener(new K());
        localAPProgressDialog.show();
        return;
        boolean bool = ((ProgressDialog)((Map.Entry)localIterator.next()).getKey()).isShowing();
        if (!bool)
          break;
      }
    }
    catch (Exception localException)
    {
    }
  }

  public static void successToast(Context paramContext, int paramInt, String paramString)
  {
    if ((paramString == null) || (paramString.equals("")))
      return;
    View localView = LayoutInflater.from(paramContext).inflate(APCommMethod.getLayoutId(paramContext, "unipay_layout_suc"), null);
    TextView localTextView1 = (TextView)localView.findViewById(APCommMethod.getId(paramContext, "unipay_id_succTxt1"));
    TextView localTextView2 = (TextView)localView.findViewById(APCommMethod.getId(paramContext, "unipay_id_succNum"));
    TextView localTextView3 = (TextView)localView.findViewById(APCommMethod.getId(paramContext, "unipay_id_succTxt2"));
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    case 3:
    case 2:
    case 4:
    case 5:
    }
    while (true)
    {
      Toast localToast = Toast.makeText(paramContext, "", 1);
      localToast.setGravity(23, 0, 0);
      localToast.setDuration(1);
      localToast.setView(localView);
      localToast.show();
      return;
      localTextView1.setText("成功充值");
      localTextView3.setText(APDataInterface.singleton().getOrderInfo().buyInfo.name);
      if (APAppDataInterface.singleton().getIsShowSaveNum())
        localTextView2.setText("×" + paramString + APDataInterface.singleton().getOrderInfo().buyInfo.unit);
      ArrayList localArrayList1 = APMPSendInfo.getInstance().getTotalSendInfo();
      if ((localArrayList1 == null) || (localArrayList1.size() <= 0))
        continue;
      ((LinearLayout)localView.findViewById(APCommMethod.getId(paramContext, "unipay_totalSendInfo"))).setVisibility(0);
      GridView localGridView = (GridView)localView.findViewById(APCommMethod.getId(paramContext, "unipay_gridview"));
      localGridView.setSelector(new ColorDrawable(0));
      if (localArrayList1.size() == 1)
        localGridView.setNumColumns(1);
      ArrayList localArrayList2;
      int i;
      while (true)
      {
        localArrayList2 = new ArrayList();
        i = 0;
        if (i < localArrayList1.size())
          break label424;
        int j = APCommMethod.getLayoutId(paramContext, "unipay_layout_suc_result");
        String[] arrayOfString = { "itemText", "itemNumber" };
        int[] arrayOfInt = new int[2];
        arrayOfInt[0] = APCommMethod.getId(paramContext, "unipay_textview");
        arrayOfInt[1] = APCommMethod.getId(paramContext, "unipay_textnum");
        localGridView.setAdapter(new SimpleAdapter(paramContext, localArrayList2, j, arrayOfString, arrayOfInt));
        break;
        if (localArrayList1.size() != 2)
          continue;
        localGridView.setNumColumns(2);
      }
      label424: HashMap localHashMap = new HashMap();
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
      localTextView1.setText("成功购买");
      localTextView3.setText(APDataInterface.singleton().getOrderInfo().buyInfo.name);
      if (!APAppDataInterface.singleton().getIsShowSaveNum())
        continue;
      localTextView2.setText("×" + paramString + APDataInterface.singleton().getOrderInfo().buyInfo.unit);
      continue;
      localTextView1.setText("成功充值");
      localTextView3.setText("Q币");
      localTextView2.setText("×" + paramString);
      continue;
      localTextView1.setText("成功充值");
      localTextView3.setText("Q点");
      localTextView2.setText("×" + paramString);
      continue;
      if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
      {
        localTextView1.setText("成功开通");
        localTextView3.setText("");
        localTextView2.setText(APMonthDataInterface.singleton().getUnit());
        continue;
      }
      localTextView1.setText("成功开通");
      localTextView3.setText(APDataInterface.singleton().getOrderInfo().buyInfo.name);
      localTextView2.setText("×" + paramString + APMonthDataInterface.singleton().getUnit());
      continue;
      localTextView1.setText("成功购买");
      localTextView3.setText(APDataInterface.singleton().getOrderInfo().buyInfo.name);
      if (APMonthDataInterface.singleton().getOpenType() == APMonthDataInterface.MonthOpenType.OpenType_NoRate)
      {
        localTextView3.setText(APMonthDataInterface.singleton().getUnit());
        localTextView2.setText("");
        continue;
      }
      localTextView2.setText("×" + paramString + APMonthDataInterface.singleton().getUnit());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APUICommonMethod
 * JD-Core Version:    0.6.0
 */
package com.pay.tool;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.pay.AndroidPay;
import com.pay.api.APPayGameService;
import com.pay.api.APPayOpenService;
import com.pay.api.APPayResponseInfo;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.orderInfo.APOrderInfo;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class APCommMethod
{
  public static byte[] BitmapResIdToByteArrary(int paramInt)
  {
    if ((paramInt <= 0) && (AndroidPay.singleton().applicationContext != null))
      return null;
    Bitmap localBitmap = BitmapFactory.decodeResource(AndroidPay.singleton().applicationContext.getResources(), paramInt);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    localBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
    localBitmap.recycle();
    return arrayOfByte;
  }

  public static String MaptoString(HashMap paramHashMap)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = paramHashMap.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (localStringBuffer.length() > 0)
          localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
        return localStringBuffer.toString();
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuffer.append((String)localEntry.getKey());
      localStringBuffer.append("=");
      localStringBuffer.append((String)localEntry.getValue());
      localStringBuffer.append("&");
    }
  }

  public static String MaptoString(HashMap paramHashMap, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = paramHashMap.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (localStringBuffer.length() > 0)
          localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
        return localStringBuffer.toString();
      }
      localStringBuffer.append((String)((Map.Entry)localIterator.next()).getValue());
      localStringBuffer.append(paramString);
    }
  }

  public static String computerMp(String paramString)
  {
    TreeMap localTreeMap = APMpDataInterface.getIntanceMpDataInterface().getMpInfoMap();
    if (!TextUtils.isEmpty(paramString))
    {
      int i = Integer.valueOf(paramString).intValue();
      Iterator localIterator = localTreeMap.keySet().iterator();
      String str;
      for (Object localObject = "0"; ; localObject = str)
      {
        if (!localIterator.hasNext());
        int j;
        do
        {
          return localObject;
          str = (String)localIterator.next();
          j = Integer.valueOf(str).intValue();
        }
        while (i < j);
        if (i == j)
          return str;
      }
    }
    return (String)"0";
  }

  public static String[] computerRate(String paramString, int paramInt)
  {
    float f = 1.0F;
    if ((paramInt == 4) || (paramInt == 5))
      f = Float.valueOf(APDataInterface.singleton().getOrderInfo().buyInfo.price).floatValue() / 100.0F;
    while (true)
    {
      return moneyFormat(f * Float.valueOf(paramString).floatValue());
      if (paramInt == 2)
      {
        f = 0.1F;
        continue;
      }
      if (paramInt == 3)
        continue;
      f /= Float.valueOf(APDataInterface.singleton().getOrderInfo().buyInfo.price).floatValue();
    }
  }

  public static String dealString(String paramString)
  {
    String str = rawString(paramString);
    if (str.length() <= 3);
    do
    {
      return paramString;
      if ((str.length() > 3) && (str.length() <= 6))
        return paramString.substring(0, 3) + " " + paramString.substring(4, paramString.length());
      if ((str.length() > 6) && (str.length() <= 9))
        return paramString.substring(3, 6) + " " + paramString.substring(7, paramString.length());
    }
    while ((str.length() <= 9) || (str.length() > 12));
    return paramString.substring(6, 9) + " " + paramString.substring(10, paramString.length());
  }

  public static String fenToYuan(String paramString, int paramInt)
  {
    DecimalFormat localDecimalFormat = (DecimalFormat)DecimalFormat.getInstance();
    if (paramInt == 0)
      localDecimalFormat.applyPattern("0");
    try
    {
      while (true)
      {
        String str = localDecimalFormat.format(Float.valueOf(paramString).floatValue() / 100.0F);
        return str;
        if (paramInt == 1)
        {
          localDecimalFormat.applyPattern("0.0");
          continue;
        }
        if (paramInt != 2)
          continue;
        localDecimalFormat.applyPattern("0.00");
      }
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  public static int getAnimId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "anim", paramContext.getPackageName());
  }

  public static String getApplicationPackageName()
  {
    try
    {
      String str = AndroidPay.singleton().applicationContext.getPackageManager().getPackageInfo(AndroidPay.singleton().applicationContext.getPackageName(), 0).packageName;
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

  public static String getApplicationVersion()
  {
    try
    {
      String str = AndroidPay.singleton().applicationContext.getPackageManager().getPackageInfo(AndroidPay.singleton().applicationContext.getPackageName(), 0).versionName;
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

  public static int getColorId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "color", paramContext.getPackageName());
  }

  public static Drawable getDrawable(Context paramContext, String paramString)
  {
    int i = paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
    return paramContext.getResources().getDrawable(i);
  }

  public static int getDrawableId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
  }

  public static int getId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "id", paramContext.getPackageName());
  }

  public static int getLayoutId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "layout", paramContext.getPackageName());
  }

  public static boolean getNumberHashPoint(String[] paramArrayOfString, int paramInt)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfString.length)
        return false;
      if (!computerRate(paramArrayOfString[i], paramInt)[1].equals("00"))
        return true;
    }
  }

  public static String getStringId(Context paramContext, String paramString)
  {
    int i = paramContext.getResources().getIdentifier(paramString, "string", paramContext.getPackageName());
    return paramContext.getResources().getString(i);
  }

  public static int getStyleId(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "style", paramContext.getPackageName());
  }

  public static String getVersion()
  {
    if (getApplicationPackageName().equals("com.tencent.unipay"))
      return getApplicationVersion();
    return "1.3.7b";
  }

  public static boolean isApplicationShowing(Context paramContext)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    int i = 0;
    Iterator localIterator;
    if (localList != null)
      localIterator = localList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return i;
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (!localRunningAppProcessInfo.processName.equals(paramContext.getPackageName()))
        continue;
      int j = localRunningAppProcessInfo.importance;
      if ((j != 200) && (j != 100))
        continue;
      i = 1;
    }
  }

  public static boolean isPayGameListByWeb()
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    APLog.i("isPayGameListByWeb  getMallUrl", localAPDataInterface.getMallUrl());
    return (localAPDataInterface.getOrderInfo().saveType == 0) && (!TextUtils.isEmpty(localAPDataInterface.getMallUrl()));
  }

  public static boolean isPayGameResultByWeb()
  {
    APDataInterface localAPDataInterface = APDataInterface.singleton();
    return (localAPDataInterface.getOrderInfo().saveType == 0) && (!TextUtils.isEmpty(localAPDataInterface.getResultUrl()));
  }

  public static String[] moneyFormat(float paramFloat)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat();
    localDecimalFormat.applyPattern("0.00");
    return localDecimalFormat.format(paramFloat).split("\\.");
  }

  public static void payCallBack()
  {
    APPayResponseInfo localAPPayResponseInfo = new APPayResponseInfo();
    localAPPayResponseInfo.resultCode = AndroidPay.singleton().payResponseInfo.resultCode;
    localAPPayResponseInfo.realSaveNum = AndroidPay.singleton().payResponseInfo.realSaveNum;
    localAPPayResponseInfo.resultInerCode = AndroidPay.singleton().payResponseInfo.resultInerCode;
    localAPPayResponseInfo.payChannel = AndroidPay.singleton().payResponseInfo.payChannel;
    localAPPayResponseInfo.payState = AndroidPay.singleton().payResponseInfo.payState;
    localAPPayResponseInfo.provideState = AndroidPay.singleton().payResponseInfo.provideState;
    localAPPayResponseInfo.extendInfo = AndroidPay.singleton().payResponseInfo.extendInfo;
    localAPPayResponseInfo.resultMsg = AndroidPay.singleton().payResponseInfo.resultMsg;
    localAPPayResponseInfo.payReserve1 = AndroidPay.singleton().payResponseInfo.payReserve1;
    localAPPayResponseInfo.payReserve2 = AndroidPay.singleton().payResponseInfo.payReserve2;
    localAPPayResponseInfo.payReserve3 = AndroidPay.singleton().payResponseInfo.payReserve3;
    AndroidPay.singleton().payResponseInfo.reset();
    int i = APDataInterface.singleton().getOrderInfo().originalSaveType;
    if ((i == 0) || (i == 1))
      APPayGameService.PayGameServiceCallBack(localAPPayResponseInfo);
    do
    {
      return;
      if ((i != 4) && (i != 5))
        continue;
      APPayOpenService.PayOpenServiceCallBack(localAPPayResponseInfo);
      return;
    }
    while ((APDataInterface.singleton().getOrderInfo().saveType != 2) && (APDataInterface.singleton().getOrderInfo().saveType != 3));
    localAPPayResponseInfo.resultCode = 2;
    localAPPayResponseInfo.realSaveNum = 0;
    localAPPayResponseInfo.payState = 1;
    localAPPayResponseInfo.provideState = -1;
    if ((APDataInterface.singleton().getOrderInfo().originalSaveType == 4) || (APDataInterface.singleton().getOrderInfo().originalSaveType == 5))
    {
      APPayOpenService.PayOpenServiceCallBack(localAPPayResponseInfo);
      return;
    }
    APPayGameService.PayGameServiceCallBack(localAPPayResponseInfo);
  }

  public static void payErrorCallBack(int paramInt, String paramString)
  {
    updatePayResponseInfo(paramInt, -1, -1, -1, paramString, "");
    payCallBack();
  }

  public static void paySuccCallBack(int paramInt1, int paramInt2, int paramInt3)
  {
    updatePayResponseInfo(0, paramInt1, paramInt2, paramInt3, "", "");
    payCallBack();
  }

  public static String rawString(String paramString)
  {
    return paramString.replace(" ", "");
  }

  public static void retLoginCallBack()
  {
    int i = APDataInterface.singleton().getOrderInfo().saveType;
    if ((i == 4) || (i == 5))
    {
      APPayOpenService.retLogin();
      return;
    }
    APPayGameService.retLogin();
  }

  public static void transformStrToList(String paramString, List paramList)
  {
    int i = paramString.indexOf("[");
    int j = paramString.indexOf("]");
    paramList.clear();
    String[] arrayOfString;
    int k;
    if ((i != -1) && (j != -1) && (j > i))
    {
      String str = paramString.substring(i + 1, j);
      if (str.length() != 0)
      {
        arrayOfString = str.split(",");
        k = arrayOfString.length;
      }
    }
    for (int m = 0; ; m++)
    {
      if (m >= k)
        return;
      paramList.add(arrayOfString[m]);
    }
  }

  public static void transformStrToMap(String paramString, TreeMap paramTreeMap)
  {
    int i = paramString.indexOf("[");
    int j = paramString.indexOf("]");
    String str;
    if ((i != -1) && (j != -1) && (j > i))
    {
      str = paramString.substring(i + 1, j);
      if (str.length() != 0)
        break label54;
      paramTreeMap.clear();
    }
    while (true)
    {
      return;
      label54: String[] arrayOfString = str.split(",");
      int k = arrayOfString.length;
      if ((k <= 0) || (k % 2 != 0))
        continue;
      paramTreeMap.clear();
      for (int m = 0; m < k / 2; m++)
        paramTreeMap.put(arrayOfString[(m << 1)], arrayOfString[(1 + (m << 1))]);
    }
  }

  public static void transformStrToMpInfoList(String paramString, List paramList1, List paramList2)
  {
    int i = paramString.indexOf("[");
    int j = paramString.indexOf("]");
    String str1;
    if ((i != -1) && (j != -1) && (j > i))
    {
      str1 = paramString.substring(i + 1, j);
      if (str1.length() != 0)
        break label66;
      paramList1.clear();
      paramList2.clear();
    }
    while (true)
    {
      return;
      label66: String[] arrayOfString = str1.split(",");
      int k = arrayOfString.length;
      if ((k <= 0) || (k % 2 != 0))
        continue;
      paramList1.clear();
      paramList2.clear();
      for (int m = 0; m < k / 2; m++)
      {
        String str2 = arrayOfString[(m << 1)];
        String str3 = arrayOfString[(1 + (m << 1))];
        paramList1.add(str2);
        paramList2.add(str3);
      }
    }
  }

  public static void updatePayResponseInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString1, String paramString2)
  {
    if ((AndroidPay.singleton().payResponseInfo.resultCode == 0) && (paramInt1 != 0))
      return;
    AndroidPay.singleton().payResponseInfo.resultCode = paramInt1;
    try
    {
      AndroidPay.singleton().payResponseInfo.realSaveNum = Integer.valueOf(APDataInterface.singleton().getOrderInfo().succSaveNum).intValue();
      label51: AndroidPay.singleton().payResponseInfo.payChannel = paramInt2;
      AndroidPay.singleton().payResponseInfo.payState = paramInt3;
      AndroidPay.singleton().payResponseInfo.provideState = paramInt4;
      AndroidPay.singleton().payResponseInfo.extendInfo = paramString2;
      AndroidPay.singleton().payResponseInfo.resultMsg = paramString1;
      return;
    }
    catch (Exception localException)
    {
      break label51;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APCommMethod
 * JD-Core Version:    0.6.0
 */
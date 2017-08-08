package com.pay.ui.channel;

import android.text.TextUtils;
import com.pay.common.tool.APLog;
import com.pay.common.tool.APTypeChange;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;
import com.pay.ui.common.APUICommonMethod;
import java.util.ArrayList;
import java.util.List;

public class APMCardValueSupportList
{
  public static List reMcardValueList()
  {
    int[] arrayOfInt = { 10, 20, 30, 50, 100 };
    ArrayList localArrayList = new ArrayList();
    APOrderInfo localAPOrderInfo = APDataInterface.singleton().getOrderInfo();
    int i = localAPOrderInfo.saveType;
    APLog.i("reMcardValueList  saveType ===== ", String.valueOf(i));
    float f1 = Float.parseFloat(APUICommonMethod.reCostByRate());
    int j = (int)Math.ceil(f1);
    float f2 = Float.parseFloat(APUICommonMethod.reMonthsRate());
    APLog.i("reMcardValueList  fMonthRate ===== ", String.valueOf(f2));
    String str1 = APUICommonMethod.reGoodsRate();
    String str2 = String.valueOf(localAPOrderInfo.buyInfo.maxNum);
    APLog.i("reMcardValueList  goosdMaxNum ===== ", String.valueOf(str2));
    int k = 0;
    if (i != 3)
    {
      k = 0;
      if (i != 2);
    }
    else if (k < arrayOfInt.length);
    while (true)
    {
      APLog.i("mcardValueList == ", localArrayList.toString());
      return localArrayList;
      localArrayList.add(String.valueOf(arrayOfInt[k]));
      k++;
      break;
      if (i == 0)
      {
        boolean bool3 = localAPOrderInfo.isNumCanChange;
        int i1 = 0;
        if (bool3)
        {
          while (i1 < arrayOfInt.length)
          {
            localArrayList.add(String.valueOf(arrayOfInt[i1]));
            i1++;
          }
          continue;
        }
        do
        {
          if ((1.E-005D + arrayOfInt[i1] > f1) && (arrayOfInt[i1] - 1.E-005D < f1))
            localArrayList.add(String.valueOf(arrayOfInt[i1]));
          i1++;
        }
        while (i1 < arrayOfInt.length);
        continue;
      }
      if ((i == 4) || (i == 5))
      {
        APLog.i("fMonthRate ====== ", String.valueOf(f2));
        boolean bool1 = localAPOrderInfo.isNumCanChange;
        int m = 0;
        if (bool1)
        {
          while (m < arrayOfInt.length)
          {
            if (1.E-005D + arrayOfInt[m] > f2 / 100.0F)
              localArrayList.add(String.valueOf(arrayOfInt[m]));
            m++;
          }
          continue;
        }
        do
        {
          if (1.E-005D + arrayOfInt[m] > j)
            localArrayList.add(String.valueOf(arrayOfInt[m]));
          m++;
        }
        while (m < arrayOfInt.length);
        continue;
      }
      if (i != 1)
        continue;
      float f3 = Float.parseFloat(str1);
      boolean bool2 = localAPOrderInfo.isNumCanChange;
      int n = 0;
      if (bool2)
      {
        label427: if (n < arrayOfInt.length)
          if (100 * arrayOfInt[n] % f3 == 0.0F)
          {
            if (!TextUtils.isEmpty(str2))
              break label478;
            localArrayList.add(String.valueOf(arrayOfInt[n]));
          }
        while (true)
        {
          n++;
          break label427;
          break;
          try
          {
            label478: if ((int)(100 * arrayOfInt[n] / f3) > APTypeChange.StringToInt(str2))
              continue;
            localArrayList.add(String.valueOf(arrayOfInt[n]));
          }
          catch (Exception localException)
          {
            localArrayList.add(String.valueOf(arrayOfInt[n]));
          }
        }
      }
      do
      {
        if ((1.E-005D + arrayOfInt[n] > f1) && (arrayOfInt[n] - 1.E-005D < f1))
          localArrayList.add(String.valueOf(arrayOfInt[n]));
        n++;
      }
      while (n < arrayOfInt.length);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.APMCardValueSupportList
 * JD-Core Version:    0.6.0
 */
package com.pay.ui.payCenter;

import com.pay.common.tool.APTypeChange;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;

public class APSaveValueList
{
  private static APSaveValueList a = null;
  private String[] b;
  private String[] c;
  private String[] d;
  private String[] e;

  public static APSaveValueList singleton()
  {
    if (a == null)
      a = new APSaveValueList();
    return a;
  }

  public String[] getSaveMoney()
  {
    if (a.c == null)
      a.c = new String[0];
    return a.c;
  }

  public String[] getSaveName()
  {
    if (a.e == null)
      a.e = new String[0];
    return a.e;
  }

  public String[] getSaveNumber()
  {
    if (a.b == null)
      a.b = new String[0];
    return a.b;
  }

  public String[] getSaveProduct()
  {
    if (a.d == null)
      a.d = new String[0];
    return a.d;
  }

  public void setSaveValue(String[] paramArrayOfString)
  {
    this.b = new String[paramArrayOfString.length];
    this.b = paramArrayOfString;
    this.c = new String[paramArrayOfString.length];
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfString.length)
        return;
      try
      {
        int j = APTypeChange.StringToInt(paramArrayOfString[i]);
        int k = APTypeChange.StringToInt(APDataInterface.singleton().getOrderInfo().getCost(j));
        this.c[i] = String.valueOf(k);
        i++;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  }

  public void setSaveValue(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4)
  {
    this.b = new String[paramArrayOfString1.length];
    this.b = paramArrayOfString1;
    this.c = new String[paramArrayOfString2.length];
    this.c = paramArrayOfString2;
    this.d = new String[paramArrayOfString3.length];
    this.d = paramArrayOfString3;
    this.e = new String[paramArrayOfString4.length];
    this.e = paramArrayOfString4;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.APSaveValueList
 * JD-Core Version:    0.6.0
 */
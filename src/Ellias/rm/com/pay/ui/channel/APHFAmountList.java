package com.pay.ui.channel;

import java.util.ArrayList;

public class APHFAmountList
{
  private static APHFAmountList b;
  private ArrayList a = new ArrayList();

  public static void release()
  {
    b = null;
  }

  public static APHFAmountList singleton()
  {
    if (b == null)
      b = new APHFAmountList();
    return b;
  }

  public void addHFAmountList(String paramString)
  {
    b.a.add(paramString);
  }

  public void clear()
  {
    b.a.clear();
  }

  public ArrayList getHFAmountList()
  {
    return b.a;
  }

  public void setHFAmountList(ArrayList paramArrayList)
  {
    b.a = paramArrayList;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.APHFAmountList
 * JD-Core Version:    0.6.0
 */
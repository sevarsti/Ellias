package com.tenpay.tenpayplugin;

import android.os.Build.VERSION;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

class TenpayPluginSelectBankActivity$2
  implements AdapterView.OnItemClickListener
{
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    Object[] arrayOfObject = this.a.a.keySet().toArray();
    TenpayPluginSelectBankActivity.BankData localBankData = (TenpayPluginSelectBankActivity.BankData)this.a.a.get(arrayOfObject[paramInt]);
    String str;
    if (localBankData.credit != null)
      str = localBankData.credit.optString("sname");
    while (true)
    {
      TenpayPluginSelectBankActivity.a(this.a, "bank." + str);
      if ((this.a.b != null) && (this.a.b.equals(str)))
      {
        this.a.b = null;
        TenpayPluginSelectBankActivity.b(this.a).notifyDataSetChanged();
        return;
        if (localBankData.debit != null)
        {
          str = localBankData.debit.optString("sname");
          continue;
        }
      }
      else
      {
        int n;
        if (this.a.b != null)
          n = arrayOfObject.length;
        label351: for (int i1 = 0; ; i1++)
        {
          int i;
          if (i1 >= n)
            i = 0;
          while (true)
          {
            this.a.b = str;
            if ((i != 0) || (Build.VERSION.SDK_INT <= 7))
              break;
            int j = paramView.getHeight();
            int[] arrayOfInt = new int[2];
            paramView.getLocationInWindow(arrayOfInt);
            int k = j + arrayOfInt[1];
            TenpayPluginSelectBankActivity.a(this.a).getLocationInWindow(arrayOfInt);
            int m = k - (arrayOfInt[1] + TenpayPluginSelectBankActivity.a(this.a).getHeight()) + j * 2;
            if (m <= 0)
              break;
            ((RelativeLayout)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_drop"))).setVisibility(0);
            TenpayPluginSelectBankActivity.a(this.a).smoothScrollBy(m, 300);
            break;
            Object localObject = arrayOfObject[i1];
            if (localObject.equals(this.a.b))
            {
              i = 1;
              continue;
            }
            if (!localObject.equals(str))
              break label351;
            i = 0;
          }
        }
      }
      str = "";
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginSelectBankActivity.2
 * JD-Core Version:    0.6.0
 */
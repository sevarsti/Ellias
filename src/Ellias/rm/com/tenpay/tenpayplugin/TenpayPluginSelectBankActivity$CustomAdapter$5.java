package com.tenpay.tenpayplugin;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.Map;
import org.json.JSONObject;

class TenpayPluginSelectBankActivity$CustomAdapter$5
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    TenpayPluginSelectBankActivity.BankData localBankData = (TenpayPluginSelectBankActivity.BankData)TenpayPluginSelectBankActivity.CustomAdapter.a(this.a).a.get(TenpayPluginSelectBankActivity.CustomAdapter.a(this.a).a.keySet().toArray()[paramView.getId()]);
    Intent localIntent = new Intent();
    localIntent.putExtra("bank", localBankData.credit.toString());
    TenpayPluginSelectBankActivity.a(TenpayPluginSelectBankActivity.CustomAdapter.a(this.a), "bank." + localBankData.credit.optString("code"));
    localIntent.putExtra("trace", TenpayPluginSelectBankActivity.CustomAdapter.a(this.a).d.toString());
    TenpayPluginSelectBankActivity.CustomAdapter.a(this.a).setResult(1, localIntent);
    TenpayPluginSelectBankActivity.CustomAdapter.a(this.a).finish();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginSelectBankActivity.CustomAdapter.5
 * JD-Core Version:    0.6.0
 */
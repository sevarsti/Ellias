package com.tenpay.tenpayplugin;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.tenpay.tenpayplugin.view.BankSelectDialog.onItemSelectedListener;
import java.util.ArrayList;
import org.json.JSONObject;

class TenpayPluginActivity$25
  implements BankSelectDialog.onItemSelectedListener
{
  public void onItemSelect(int paramInt)
  {
    if ((TenpayPluginActivity.A(this.a) != null) && (paramInt < TenpayPluginActivity.A(this.a).size()))
    {
      TenpayPluginActivity.d(this.a, paramInt);
      JSONObject localJSONObject = (JSONObject)TenpayPluginActivity.A(this.a).get(paramInt);
      String str1 = localJSONObject.optString("type");
      if (TenpayPluginActivity.C(this.a) != null)
      {
        if (("DEBIT".equals(str1)) || ("CREDIT".equals(str1)))
          TenpayPluginActivity.C(this.a).setVisibility(8);
      }
      else
      {
        String str2 = localJSONObject.optString("card_tail");
        if ("".equals(str2))
          break label363;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localJSONObject.optString("name"));
        localStringBuilder.append(" **");
        localStringBuilder.append(str2);
        TenpayPluginActivity.D(this.a).setText(localStringBuilder.toString());
        label167: if (TenpayPluginActivity.E(this.a) != null)
          TenpayPluginActivity.E(this.a).setText(TenpayPluginActivity.D(this.a).getText());
        if (TenpayPluginActivity.F(this.a) != null)
          TenpayPluginActivity.F(this.a).setText(TenpayPluginActivity.D(this.a).getText());
        if ((!"FASTPAY_DEBIT".equals(str1)) && (!"FASTPAY_CREDIT".equals(str1)))
          break label451;
        if (this.a.mPayGate.optInt("nopwdnosms_flag", 0) != 1)
          break label382;
        TenpayPluginActivity.G(this.a).setVisibility(8);
        if (TenpayPluginActivity.H(this.a) != null)
          TenpayPluginActivity.H(this.a).setVisibility(8);
        if (TenpayPluginActivity.I(this.a) != null)
          TenpayPluginActivity.I(this.a).setVisibility(0);
        TenpayPluginActivity.r(this.a).setClickable(true);
        TenpayPluginActivity.r(this.a).setEnabled(true);
      }
      while (true)
      {
        this.a.initYZ();
        return;
        TenpayPluginActivity.C(this.a).setVisibility(0);
        break;
        label363: TenpayPluginActivity.D(this.a).setText(localJSONObject.optString("name"));
        break label167;
        label382: TenpayPluginActivity.G(this.a).setVisibility(0);
        TenpayPluginActivity.G(this.a).setText("");
        if (TenpayPluginActivity.H(this.a) != null)
          TenpayPluginActivity.H(this.a).setVisibility(0);
        if (TenpayPluginActivity.I(this.a) == null)
          continue;
        TenpayPluginActivity.I(this.a).setVisibility(8);
        continue;
        label451: TenpayPluginActivity.G(this.a).setVisibility(0);
        TenpayPluginActivity.G(this.a).setText("");
        if (TenpayPluginActivity.H(this.a) != null)
          TenpayPluginActivity.H(this.a).setVisibility(0);
        if (TenpayPluginActivity.I(this.a) == null)
          continue;
        TenpayPluginActivity.I(this.a).setVisibility(8);
      }
    }
    TenpayPluginActivity.c(this.a, "addbank");
    ((InputMethodManager)TenpayPluginActivity.G(this.a).getContext().getSystemService("input_method")).hideSoftInputFromWindow(TenpayPluginActivity.G(this.a).getWindowToken(), 0);
    this.a.mLastPayType = this.a.a;
    this.a.a = 5;
    this.a.gotoBind(false);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.25
 * JD-Core Version:    0.6.0
 */
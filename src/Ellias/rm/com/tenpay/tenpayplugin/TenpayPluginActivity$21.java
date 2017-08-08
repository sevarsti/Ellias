package com.tenpay.tenpayplugin;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.tenpay.tenpayplugin.view.ValidDateEdit;
import java.util.ArrayList;
import org.json.JSONObject;

class TenpayPluginActivity$21
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (TenpayPluginActivity.v(this.a) != null)
    {
      if (((JSONObject)TenpayPluginActivity.A(this.a).get(TenpayPluginActivity.B(this.a))).optInt("needcvv") == 1)
        TenpayPluginActivity.v(this.a).setVisibility(0);
    }
    else
    {
      TenpayPluginActivity.t(this.a).requestFocus();
      if (TenpayPluginActivity.y(this.a) != null)
        TenpayPluginActivity.y(this.a).setVisibility(8);
      if (TenpayPluginActivity.y(this.a) != null)
        TenpayPluginActivity.z(this.a).setVisibility(8);
      TenpayPluginActivity.c(this.a, "sure");
      TenpayPluginActivity.d(this.a, true);
      if (this.a.a >= 2)
        break label196;
    }
    label196: for (this.a.g = "tenpay.changeval."; ; this.a.g = "tenpay.changevalbig.")
    {
      TenpayPluginActivity.c(this.a, "show");
      this.a.f = null;
      TenpayPluginActivity.k(this.a).setVisibility(0);
      TenpayPluginActivity.e(this.a).dismiss();
      return;
      TenpayPluginActivity.v(this.a).setVisibility(8);
      break;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.21
 * JD-Core Version:    0.6.0
 */
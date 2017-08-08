package com.tenpay.tenpayplugin.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.tenpay.tenpayplugin.TenpayResourceUtil;
import java.util.List;
import org.json.JSONObject;

class BankSelectDialog$BankItemView extends LinearLayout
{
  private Context b = null;
  private List c;
  private float d = 1.0F;

  public BankSelectDialog$BankItemView(BankSelectDialog paramBankSelectDialog, Context paramContext)
  {
    super(paramContext);
    this.b = paramContext;
    this.c = paramBankSelectDialog.dataList;
    setOrientation(1);
    setFocusable(true);
    this.d = getResources().getDisplayMetrics().density;
  }

  private void a()
  {
    int i = this.c.size();
    if (BankSelectDialog.a(this.a));
    for (int j = i + 1; ; j = i)
    {
      int k = 0;
      if (k >= j)
        return;
      LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(this.b).inflate(TenpayResourceUtil.getLayoutId(this.b, "unipay_tenpay_bankview"), null);
      localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, (int)(40.0F * this.d)));
      localLinearLayout.setFocusable(true);
      localLinearLayout.setNextFocusDownId(k + 1);
      localLinearLayout.setId(k);
      localLinearLayout.setOnClickListener(new BankSelectDialog.BankItemView.1(this));
      localLinearLayout.setOnTouchListener(new BankSelectDialog.BankItemView.2(this));
      TextView localTextView = (TextView)localLinearLayout.findViewById(TenpayResourceUtil.getId(this.b, "bank_name"));
      if (k < this.c.size())
      {
        if (BankSelectDialog.b(this.a) == k)
          localTextView.setTextColor(-35072);
        String str = ((JSONObject)this.c.get(k)).optString("card_tail");
        if (!"".equals(str))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(((JSONObject)this.c.get(k)).optString("name"));
          localStringBuilder.append(" **");
          localStringBuilder.append(str);
          localTextView.setText(localStringBuilder.toString());
        }
      }
      while (true)
      {
        addView(localLinearLayout);
        if (k < j - 1)
        {
          ImageView localImageView = new ImageView(this.b);
          localImageView.setBackgroundResource(TenpayResourceUtil.getDrawableId(this.b, "unipay_tenpay_line"));
          addView(localImageView, -1, 1);
        }
        k++;
        break;
        localTextView.setText(((JSONObject)this.c.get(k)).optString("name"));
        continue;
        localTextView.setText(TenpayResourceUtil.getStringId(this.b, "unipay_tenpay_more_bank"));
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.BankSelectDialog.BankItemView
 * JD-Core Version:    0.6.0
 */
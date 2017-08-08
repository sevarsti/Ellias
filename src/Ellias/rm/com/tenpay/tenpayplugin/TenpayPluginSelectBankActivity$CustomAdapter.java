package com.tenpay.tenpayplugin;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Map;
import org.json.JSONObject;

class TenpayPluginSelectBankActivity$CustomAdapter extends BaseAdapter
{
  private LayoutInflater b;

  public TenpayPluginSelectBankActivity$CustomAdapter(TenpayPluginSelectBankActivity paramTenpayPluginSelectBankActivity, Activity paramActivity)
  {
    this.b = LayoutInflater.from(paramActivity);
  }

  public int getCount()
  {
    if (this.a.a != null)
      return this.a.a.size();
    return 0;
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    TenpayPluginSelectBankActivity.CustomAdapter.ViewHolder localViewHolder;
    TenpayPluginSelectBankActivity.BankData localBankData;
    if (paramView == null)
    {
      localViewHolder = new TenpayPluginSelectBankActivity.CustomAdapter.ViewHolder(this);
      paramView = this.b.inflate(TenpayResourceUtil.getLayoutId(this.a, "unipay_tenpay_bankitem"), null);
      localViewHolder.a = ((TextView)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_name")));
      localViewHolder.b = ((RelativeLayout)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_drop")));
      localViewHolder.c = ((LinearLayout)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_debit")));
      localViewHolder.d = ((TextView)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_debit_txt")));
      localViewHolder.e = ((TextView)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_debit_no")));
      localViewHolder.f = ((TextView)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_debit_bind")));
      localViewHolder.g = ((ImageView)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_debit_radio")));
      localViewHolder.h = ((TextView)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_debit_unsuport")));
      localViewHolder.i = ((LinearLayout)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_credit")));
      localViewHolder.j = ((TextView)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_credit_txt")));
      localViewHolder.k = ((TextView)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_credit_no")));
      localViewHolder.l = ((TextView)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_credit_bind")));
      localViewHolder.m = ((ImageView)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_credit_radio")));
      localViewHolder.n = ((TextView)paramView.findViewById(TenpayResourceUtil.getId(this.a, "bank_credit_unsuport")));
      paramView.setTag(localViewHolder);
      if (this.a.a != null)
      {
        localBankData = (TenpayPluginSelectBankActivity.BankData)this.a.a.get(this.a.a.keySet().toArray()[paramInt]);
        localViewHolder.a.setText(localBankData.name);
        localViewHolder.b.setVisibility(8);
        localViewHolder.c.setId(paramInt);
        localViewHolder.i.setId(paramInt);
        if (localBankData.debit != null)
          break label611;
        localViewHolder.c.setOnClickListener(new TenpayPluginSelectBankActivity.CustomAdapter.1(this));
        localViewHolder.d.setVisibility(8);
        localViewHolder.e.setVisibility(0);
        localViewHolder.h.setVisibility(0);
        localViewHolder.f.setVisibility(8);
        localViewHolder.g.setVisibility(8);
        localViewHolder.g.setBackgroundResource(TenpayResourceUtil.getDrawableId(this.a, "unipay_tenpay_radiobg"));
      }
    }
    while (true)
    {
      if (localBankData.credit != null)
        break label976;
      localViewHolder.i.setOnClickListener(new TenpayPluginSelectBankActivity.CustomAdapter.4(this));
      localViewHolder.j.setVisibility(8);
      localViewHolder.k.setVisibility(0);
      localViewHolder.n.setVisibility(0);
      localViewHolder.l.setVisibility(8);
      localViewHolder.m.setVisibility(8);
      localViewHolder.m.setBackgroundResource(TenpayResourceUtil.getDrawableId(this.a, "unipay_tenpay_radiobg"));
      return paramView;
      localViewHolder = (TenpayPluginSelectBankActivity.CustomAdapter.ViewHolder)paramView.getTag();
      break;
      label611: if ((this.a.b != null) && (this.a.b.equals(localBankData.debit.optString("sname"))))
        localViewHolder.b.setVisibility(0);
      if ("FASTPAY_DEBIT_UNBIND".equals(localBankData.debit.optString("type")))
      {
        localViewHolder.c.setOnClickListener(new TenpayPluginSelectBankActivity.CustomAdapter.2(this));
        localViewHolder.d.setVisibility(0);
        localViewHolder.e.setVisibility(8);
        localViewHolder.h.setVisibility(8);
        localViewHolder.f.setVisibility(8);
        localViewHolder.g.setVisibility(0);
        if ((this.a.c != null) && (this.a.c.equals(localBankData.debit.optString("sname"))) && (this.a.f == 0))
        {
          localViewHolder.g.setBackgroundResource(TenpayResourceUtil.getDrawableId(this.a, "unipay_tenpay_radiobg_high"));
          continue;
        }
        localViewHolder.g.setBackgroundResource(TenpayResourceUtil.getDrawableId(this.a, "unipay_tenpay_radiobg"));
        continue;
      }
      localViewHolder.c.setOnClickListener(new TenpayPluginSelectBankActivity.CustomAdapter.3(this));
      localViewHolder.d.setVisibility(0);
      localViewHolder.e.setVisibility(8);
      localViewHolder.h.setVisibility(8);
      localViewHolder.f.setVisibility(0);
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(this.a.getResources().getString(TenpayResourceUtil.getStringId(this.a, "unipay_tenpay_binded_card")));
      localStringBuilder1.append(localBankData.debit.optString("name"));
      localStringBuilder1.append("***");
      localStringBuilder1.append(localBankData.debit.optString("card_tail"));
      localViewHolder.f.setText(localStringBuilder1.toString());
      localViewHolder.g.setVisibility(8);
    }
    label976: if ((this.a.b != null) && (this.a.b.equals(localBankData.credit.optString("sname"))))
      localViewHolder.b.setVisibility(0);
    if ("FASTPAY_CREDIT_UNBIND".equals(localBankData.credit.optString("type")))
    {
      localViewHolder.i.setOnClickListener(new TenpayPluginSelectBankActivity.CustomAdapter.5(this));
      localViewHolder.j.setVisibility(0);
      localViewHolder.k.setVisibility(8);
      localViewHolder.n.setVisibility(8);
      localViewHolder.l.setVisibility(8);
      localViewHolder.m.setVisibility(0);
      if ((this.a.c != null) && (this.a.c.equals(localBankData.credit.optString("sname"))) && (this.a.f == 1))
      {
        localViewHolder.m.setBackgroundResource(TenpayResourceUtil.getDrawableId(this.a, "unipay_tenpay_radiobg_high"));
        return paramView;
      }
      localViewHolder.m.setBackgroundResource(TenpayResourceUtil.getDrawableId(this.a, "unipay_tenpay_radiobg"));
      return paramView;
    }
    localViewHolder.i.setOnClickListener(new TenpayPluginSelectBankActivity.CustomAdapter.6(this));
    localViewHolder.j.setVisibility(0);
    localViewHolder.k.setVisibility(8);
    localViewHolder.n.setVisibility(8);
    localViewHolder.l.setVisibility(0);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(this.a.getResources().getString(TenpayResourceUtil.getStringId(this.a, "unipay_tenpay_binded_card")));
    localStringBuilder2.append(localBankData.credit.optString("name"));
    localStringBuilder2.append("***");
    localStringBuilder2.append(localBankData.credit.optString("card_tail"));
    localViewHolder.l.setText(localStringBuilder2.toString());
    localViewHolder.m.setVisibility(8);
    return paramView;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginSelectBankActivity.CustomAdapter
 * JD-Core Version:    0.6.0
 */
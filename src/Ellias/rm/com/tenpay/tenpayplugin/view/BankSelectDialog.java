package com.tenpay.tenpayplugin.view;

import android.content.Context;
import android.content.res.Resources;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import com.tenpay.tenpayplugin.TenpayResourceUtil;
import java.util.List;

public class BankSelectDialog extends PopupWindow
{
  BankSelectDialog.onItemSelectedListener a;
  private Context b = null;
  private BankSelectDialog.BankItemView c = null;
  private boolean d;
  protected List dataList = null;
  private int e;
  private int f;

  public BankSelectDialog(Context paramContext, List paramList, int paramInt1, boolean paramBoolean, int paramInt2)
  {
    super(paramContext);
    this.dataList = paramList;
    this.b = paramContext;
    this.d = paramBoolean;
    this.e = paramInt2;
    this.f = paramInt1;
    init();
  }

  protected void init()
  {
    ScrollView localScrollView = new ScrollView(this.b);
    this.c = new BankSelectDialog.BankItemView(this, this.b);
    localScrollView.addView(this.c);
    localScrollView.setScrollBarStyle(33554432);
    setContentView(localScrollView);
    setWindowLayoutMode(0, -2);
    setWidth(this.f);
    setBackgroundDrawable(this.b.getResources().getDrawable(TenpayResourceUtil.getDrawableId(this.b, "unipay_tenpay_inputbg")));
    setOutsideTouchable(true);
    this.c.removeAllViews();
    BankSelectDialog.BankItemView.b(this.c);
  }

  public void setOnItemSelectedListener(BankSelectDialog.onItemSelectedListener paramonItemSelectedListener)
  {
    this.a = paramonItemSelectedListener;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.BankSelectDialog
 * JD-Core Version:    0.6.0
 */
package com.tenpay.tenpayplugin.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.util.AttributeSet;
import com.tenpay.tenpayplugin.TenpayResourceUtil;

public class ClearableEditText extends TenpayEditText
{
  Drawable a;
  ClearableEditText.OnTextChangedListener b;
  public String defaultValue = "";

  public ClearableEditText(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
  }

  public ClearableEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext);
  }

  public ClearableEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext);
  }

  private void a(Context paramContext)
  {
    setOnEditorActionListener(new ClearableEditText.1(this));
    this.a = getResources().getDrawable(TenpayResourceUtil.getDrawableId(paramContext, "unipay_tenpay_del"));
    this.a.setBounds(0, 0, this.a.getIntrinsicWidth(), this.a.getIntrinsicHeight());
    manageClearButton();
    setOnFocusChangeListener(new ClearableEditText.2(this));
    setOnTouchListener(new ClearableEditText.3(this));
    addTextChangedListener(new ClearableEditText.4(this));
  }

  public void addClearButton()
  {
    setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], this.a, getCompoundDrawables()[3]);
    setCompoundDrawablePadding(5);
  }

  public void manageClearButton()
  {
    if (getText().toString().equals(""))
    {
      removeClearButton();
      return;
    }
    addClearButton();
  }

  public void removeClearButton()
  {
    setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
  }

  public void setOnTextChangedListener(ClearableEditText.OnTextChangedListener paramOnTextChangedListener)
  {
    this.b = paramOnTextChangedListener;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.ClearableEditText
 * JD-Core Version:    0.6.0
 */
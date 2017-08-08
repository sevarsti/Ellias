package com.tenpay.tenpayplugin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class TenpayEditText extends EditText
{
  public TenpayEditText(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
  }

  public TenpayEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext);
  }

  public TenpayEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext);
  }

  private void a(Context paramContext)
  {
    setOnEditorActionListener(new TenpayEditText.1(this));
    addTextChangedListener(new TenpayEditText.2(this));
  }

  public void onError()
  {
    setTextColor(-2293760);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.TenpayEditText
 * JD-Core Version:    0.6.0
 */
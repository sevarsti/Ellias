package com.tencent.component.ui.widget;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ExtendEditText extends EditText
{
  public static final ExtendEditText.LengthConverter a = new b();
  public static final ExtendEditText.LengthConverter b = new c();
  private boolean c = false;
  private ExtendEditText.LimitListener d;
  private ExtendEditText.LengthConverter e = null;

  public ExtendEditText(Context paramContext)
  {
    super(paramContext);
    a(paramContext, null);
  }

  public ExtendEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext, paramAttributeSet);
  }

  public ExtendEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext, paramAttributeSet);
  }

  private void a()
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getContext().getSystemService("input_method");
    if (localInputMethodManager != null)
      localInputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
  }

  private void a(Context paramContext, AttributeSet paramAttributeSet)
  {
    if ((paramContext != null) && (paramAttributeSet == null));
  }

  public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((this.c) && (paramInt == 4) && (paramKeyEvent.getAction() == 1))
    {
      a();
      clearFocus();
      return true;
    }
    return super.onKeyPreIme(paramInt, paramKeyEvent);
  }

  public void setClearFocusOnBack(boolean paramBoolean)
  {
    this.c = paramBoolean;
  }

  public void setLimitListener(ExtendEditText.LimitListener paramLimitListener)
  {
    this.d = paramLimitListener;
  }

  public void setMaxLength(int paramInt)
  {
    if (paramInt >= 0)
      setFilters(new InputFilter[] { new d(this, paramInt) });
  }

  public void setMaxLengthConverter(ExtendEditText.LengthConverter paramLengthConverter)
  {
    this.e = paramLengthConverter;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.ExtendEditText
 * JD-Core Version:    0.6.0
 */
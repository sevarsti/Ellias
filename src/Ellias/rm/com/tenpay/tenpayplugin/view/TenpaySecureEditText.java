package com.tenpay.tenpayplugin.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ContextMenu;

public class TenpaySecureEditText extends TenpayEditText
{
  private static int c = 7;
  private static int d = -500;
  private static int e = 6;
  private Paint a;
  private float b;

  public TenpaySecureEditText(Context paramContext)
  {
    this(paramContext, null);
  }

  public TenpaySecureEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext, paramAttributeSet);
  }

  public TenpaySecureEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext, paramAttributeSet);
  }

  private void a(Context paramContext, AttributeSet paramAttributeSet)
  {
    this.b = getResources().getDisplayMetrics().density;
    setPadding(d, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    this.a = new Paint(1);
    this.a.setStyle(Paint.Style.FILL);
    this.a.setColor(-16777216);
  }

  private void a(Canvas paramCanvas)
  {
    int j;
    int k;
    int m;
    if (this.a != null)
    {
      int i = getWidth();
      j = getHeight();
      k = i / e;
      m = getText().length();
    }
    for (int n = 0; ; n++)
    {
      if (n >= m)
        return;
      paramCanvas.drawCircle(k / 2 + n * k, j / 2, c * this.b, this.a);
    }
  }

  public void ClearInput()
  {
    setText("");
  }

  protected void onCreateContextMenu(ContextMenu paramContextMenu)
  {
    if ((0x80 & getInputType()) <= 0)
      super.onCreateContextMenu(paramContextMenu);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    a(paramCanvas);
  }

  protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
    getText().toString().length();
  }

  public boolean onTextContextMenuItem(int paramInt)
  {
    if ((0x80 & getInputType()) > 0)
      return true;
    return super.onTextContextMenuItem(paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.TenpaySecureEditText
 * JD-Core Version:    0.6.0
 */
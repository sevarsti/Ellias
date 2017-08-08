package com.tencent.msdk.webview;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;

public class MarginButton extends Button
{
  private int landMargin = getResources().getDimensionPixelOffset(WebViewResID.dimen_more_cancel_land_height);
  private int portMargin = getResources().getDimensionPixelOffset(WebViewResID.dimen_more_cancel_height);

  public MarginButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void updateMargin(boolean paramBoolean)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)getLayoutParams();
    if (paramBoolean)
    {
      localMarginLayoutParams.leftMargin = this.portMargin;
      localMarginLayoutParams.rightMargin = this.portMargin;
      return;
    }
    localMarginLayoutParams.leftMargin = this.landMargin;
    localMarginLayoutParams.rightMargin = this.landMargin;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.webview.MarginButton
 * JD-Core Version:    0.6.0
 */
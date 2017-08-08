package com.pay.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import com.pay.common.tool.APLog;

public class APScrollView extends ScrollView
{
  public APScrollView(Context paramContext)
  {
    super(paramContext);
  }

  public APScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public APScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public void fling(int paramInt)
  {
    super.fling(paramInt / 40);
    APLog.i("APScrollView", String.valueOf(paramInt / 40));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APScrollView
 * JD-Core Version:    0.6.0
 */
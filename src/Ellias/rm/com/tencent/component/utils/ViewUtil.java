package com.tencent.component.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import java.security.InvalidParameterException;

public class ViewUtil
{
  private static final boolean a = true;
  private static final Object b = new Object();

  private int a(float paramFloat)
  {
    Paint localPaint = new Paint();
    localPaint.setTextSize(paramFloat);
    Paint.FontMetrics localFontMetrics = localPaint.getFontMetrics();
    return (int)Math.ceil(localFontMetrics.descent - localFontMetrics.ascent);
  }

  private static int a(ViewGroup paramViewGroup, View paramView)
  {
    if ((paramViewGroup == null) || (paramView == null))
    {
      i = -1;
      return i;
    }
    int i = 0;
    int j = paramViewGroup.getChildCount();
    while (true)
    {
      if ((i >= j) || (paramViewGroup.getChildAt(i) == paramView))
      {
        if ((i >= 0) && (i < j))
          break;
        return -1;
      }
      i++;
    }
  }

  private static FrameLayout.LayoutParams a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    localLayoutParams.gravity = paramInt1;
    localLayoutParams.leftMargin = paramInt2;
    localLayoutParams.topMargin = paramInt3;
    localLayoutParams.rightMargin = paramInt4;
    localLayoutParams.bottomMargin = paramInt5;
    return localLayoutParams;
  }

  private static FrameLayout.LayoutParams a(ViewGroup.LayoutParams paramLayoutParams)
  {
    if (paramLayoutParams == null)
      return null;
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams))
      return new FrameLayout.LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    return new FrameLayout.LayoutParams(paramLayoutParams);
  }

  public static Object a(View paramView)
  {
    return b(paramView, 0);
  }

  public static Object a(View paramView, int paramInt)
  {
    return b(paramView, paramInt);
  }

  public static void a(View paramView, int paramInt, Object paramObject)
  {
    a(paramView, paramInt, paramObject, false);
  }

  private static void a(View paramView, int paramInt, Object paramObject, boolean paramBoolean)
  {
    if (paramView == null)
      return;
    if ((!paramBoolean) && (paramInt >>> 24 < 2))
      throw new IllegalArgumentException("The key must be an application-specific resource id.");
    Object localObject = paramView.getTag();
    if ((localObject == null) || (!(localObject instanceof SparseArray)))
      localObject = new SparseArray();
    SparseArray localSparseArray = (SparseArray)localObject;
    localSparseArray.put(paramInt, paramObject);
    paramView.setTag(localSparseArray);
  }

  public static void a(View paramView1, View paramView2, int paramInt)
  {
    a(paramView1, paramView2, paramInt, 0, 0, 0, 0);
  }

  public static void a(View paramView1, View paramView2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if ((paramView1 == null) || (paramView2 == null))
      return;
    ViewParent localViewParent = paramView1.getParent();
    if (localViewParent == null)
      throw new IllegalStateException("host " + paramView1 + " not attached to parent");
    if (!(localViewParent instanceof ViewGroup))
      throw new InvalidParameterException("host " + paramView1 + " has invalid parent " + localViewParent);
    if (paramView2.getParent() != null)
      throw new IllegalStateException("decorate " + paramView2 + " has already be added to a ViewParent " + paramView2.getParent());
    FrameLayout.LayoutParams localLayoutParams1 = b(paramView1);
    FrameLayout.LayoutParams localLayoutParams2 = a(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    ViewGroup localViewGroup = (ViewGroup)localViewParent;
    if (((localViewGroup instanceof FrameLayout)) && (a(paramView1) == b));
    j localj;
    for (Object localObject = (FrameLayout)localViewGroup; ; localObject = localj)
    {
      ((FrameLayout)localObject).removeAllViews();
      if (localLayoutParams1 != null)
        ((FrameLayout)localObject).addView(paramView1, localLayoutParams1);
      if (localLayoutParams2 == null)
        break;
      ((FrameLayout)localObject).addView(paramView2, localLayoutParams2);
      return;
      localj = new j(localViewGroup.getContext(), paramView1);
      int i = a(localViewGroup, paramView1);
      ViewGroup.LayoutParams localLayoutParams = paramView1.getLayoutParams();
      localLayoutParams.width = -2;
      localLayoutParams.height = -2;
      localViewGroup.removeView(paramView1);
      localViewGroup.addView(localj, i, localLayoutParams);
      a(localj, b);
    }
  }

  public static void a(View paramView, Object paramObject)
  {
    a(paramView, 0, paramObject, true);
  }

  private static FrameLayout.LayoutParams b(View paramView)
  {
    if (paramView == null)
      return null;
    FrameLayout.LayoutParams localLayoutParams = a(paramView.getLayoutParams());
    localLayoutParams.bottomMargin = 0;
    localLayoutParams.topMargin = 0;
    localLayoutParams.rightMargin = 0;
    localLayoutParams.leftMargin = 0;
    return localLayoutParams;
  }

  private static Object b(View paramView, int paramInt)
  {
    if (paramView == null)
      return null;
    Object localObject = paramView.getTag();
    if ((localObject == null) || (!(localObject instanceof SparseArray)))
      return null;
    return ((SparseArray)localObject).get(paramInt);
  }

  public void a(Context paramContext, TextView paramTextView, float paramFloat)
  {
    float f1 = paramContext.getResources().getDisplayMetrics().density;
    float f2 = paramTextView.getTextSize() / f1;
    float f3 = f2 + paramFloat;
    int i = a(f2);
    float f4;
    float f5;
    if (i > f3)
    {
      f4 = f3 / i;
      f5 = -1.0F;
    }
    while (true)
    {
      paramTextView.setLineSpacing(f5, f4);
      return;
      f4 = 1.0F;
      f5 = f3 - i;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.ViewUtil
 * JD-Core Version:    0.6.0
 */
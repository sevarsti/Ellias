package com.tencent.component.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.component.ComponentContext;

public class UITools
{
  private static Handler a = new Handler(Looper.getMainLooper());

  public static void a(int paramInt)
  {
    a(paramInt, 0);
  }

  public static void a(int paramInt1, int paramInt2)
  {
    a(ComponentContext.a().getText(paramInt1), paramInt2);
  }

  public static void a(CharSequence paramCharSequence)
  {
    a(paramCharSequence, 0);
  }

  public static void a(CharSequence paramCharSequence, int paramInt)
  {
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      if (Looper.myLooper() == Looper.getMainLooper())
        d(paramCharSequence, paramInt);
    }
    else
      return;
    a.post(new i(paramCharSequence, paramInt));
  }

  public static void b(int paramInt)
  {
    b(paramInt, 0);
  }

  public static void b(int paramInt1, int paramInt2)
  {
    b(ComponentContext.a().getText(paramInt1), paramInt2);
  }

  public static void b(CharSequence paramCharSequence)
  {
    b(paramCharSequence, 0);
  }

  public static void b(CharSequence paramCharSequence, int paramInt)
  {
    if (DebugUtil.a())
      a(paramCharSequence, paramInt);
  }

  private static void d(CharSequence paramCharSequence, int paramInt)
  {
    Toast localToast = Toast.makeText(ComponentContext.a(), paramCharSequence, paramInt);
    localToast.setText(paramCharSequence);
    localToast.setDuration(paramInt);
    localToast.show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.UITools
 * JD-Core Version:    0.6.0
 */
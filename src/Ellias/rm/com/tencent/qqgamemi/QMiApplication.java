package com.tencent.qqgamemi;

import android.content.Context;
import com.tencent.component.ComponentContext;
import com.tencent.component.ComponentInitializer;

public class QMiApplication
{
  private static final String a = QMiApplication.class.getSimpleName();
  private static Context b = null;

  public static Context a()
  {
    if (b == null)
      b = ComponentContext.a();
    return b;
  }

  public static void a(Context paramContext)
  {
    b(paramContext.getApplicationContext());
    ComponentInitializer.a(paramContext);
  }

  private static void b(Context paramContext)
  {
    b = paramContext;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.QMiApplication
 * JD-Core Version:    0.6.0
 */
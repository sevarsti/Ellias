package com.tencent.qqgamemi.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;

public class QMiToast extends Dialog
{
  public static final int a = 1000;
  private static final String c = "QMiToast";
  private static int d = 1000;
  private static final int e;
  public Handler b = new e(this);

  public QMiToast(Context paramContext)
  {
    super(paramContext);
  }

  public QMiToast(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }

  public static QMiToast a(Context paramContext, int paramInt1, int paramInt2)
  {
    return a(paramContext, paramContext.getString(paramInt1), paramInt2);
  }

  public static QMiToast a(Context paramContext, String paramString, int paramInt)
  {
    QMiToast localQMiToast = new QMiToast.Builder(paramContext).a(paramString).a();
    a(paramInt);
    return localQMiToast;
  }

  private static void a(int paramInt)
  {
    d = paramInt;
  }

  public void show()
  {
    super.show();
    this.b.sendEmptyMessageDelayed(0, d);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.QMiToast
 * JD-Core Version:    0.6.0
 */
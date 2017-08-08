package com.tencent.qqgamemi.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.IntentFilter;
import com.tencent.qqgamemi.receiver.HideReceiver;

public abstract class QMiDialog extends Dialog
{
  private Context a;
  private HideReceiver b;
  private boolean c = false;
  private boolean d = false;

  public QMiDialog(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
  }

  public QMiDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    a(paramContext);
  }

  private void a(Context paramContext)
  {
    this.a = paramContext;
    this.b = new HideReceiver(this);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.tencent.qqgamemi.hide.action");
    paramContext.registerReceiver(this.b, localIntentFilter);
    this.c = true;
  }

  public boolean c()
  {
    return this.d;
  }

  public void dismiss()
  {
    super.dismiss();
    this.d = true;
    if (this.c)
    {
      this.c = false;
      this.a.unregisterReceiver(this.b);
    }
  }

  public void show()
  {
    super.show();
    this.d = false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.QMiDialog
 * JD-Core Version:    0.6.0
 */
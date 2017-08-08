package com.tencent.qqgamemi.ui;

import android.content.Context;
import com.tencent.qqgamemi.QMiService;
import com.tencent.qqgamemi.protocol.ServerType;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentSelectDialog extends QMiDialog
{
  public static String a;
  private static final String b = "EnvironmentSelectDialog";
  private static List c = new ArrayList(4);
  private Context d;

  static
  {
    a = "public";
  }

  public EnvironmentSelectDialog(Context paramContext)
  {
    super(paramContext);
    this.d = paramContext;
  }

  public EnvironmentSelectDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.d = paramContext;
  }

  public void dismiss()
  {
    super.dismiss();
    ServerType.a(this.d, a);
    QMiService.b = false;
    QMiService.a(this.d, 99);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.EnvironmentSelectDialog
 * JD-Core Version:    0.6.0
 */
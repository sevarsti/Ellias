package com.tencent.qqgamemi.root;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;
import com.tencent.component.utils.ResourceUtil;

public class RootAlertDialog extends Dialog
{
  public RootAlertDialog(Context paramContext)
  {
    super(paramContext, ResourceUtil.d("QMiAlertDialog"));
    Window localWindow = getWindow();
    localWindow.setType(2003);
    localWindow.setSoftInputMode(18);
  }

  public void a(int paramInt)
  {
    ((TextView)findViewById(ResourceUtil.f("alert_dialog_content"))).setText(paramInt);
  }

  public void a(String paramString)
  {
    ((TextView)findViewById(ResourceUtil.f("alert_dialog_content"))).setText(paramString);
  }

  public void b(String paramString)
  {
    ((TextView)findViewById(ResourceUtil.f("alert_dialog_title"))).setText(paramString);
  }

  public void setTitle(int paramInt)
  {
    ((TextView)findViewById(ResourceUtil.f("alert_dialog_title"))).setText(paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.root.RootAlertDialog
 * JD-Core Version:    0.6.0
 */
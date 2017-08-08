package com.tencent.qqgamemi;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.plugin.PluginItem;
import java.util.Iterator;
import java.util.List;

class ab extends Handler
{
  public ab(QMiViewManager paramQMiViewManager, Context paramContext)
  {
    super(paramContext.getMainLooper());
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    switch (paramMessage.what)
    {
    default:
      return;
    case 3:
      QMiViewManager.a(this.a, true);
      return;
    case 2:
    }
    TLog.c(QMiViewManager.a, "HANDLER_SHOW_INFLATE_WINDOW");
    QMiViewManager.c(this.a).clear();
    Object localObject;
    if (((paramMessage.obj instanceof List)) && (paramMessage.obj != null))
    {
      Iterator localIterator = ((List)paramMessage.obj).iterator();
      if (localIterator.hasNext())
      {
        localObject = localIterator.next();
        if (QMiViewManager.c(this.a).size() < 7)
          break label256;
      }
    }
    View localView1 = QMiViewManager.d(this.a);
    QMiViewManager.c(this.a).add(localView1);
    QMiViewManager.e(this.a).removeAllViews();
    QMiViewManager.f(this.a).removeAllViews();
    QMiViewManager.f(this.a).setVisibility(8);
    int i = 0;
    label187: if (i < QMiViewManager.c(this.a).size())
    {
      View localView2 = (View)QMiViewManager.c(this.a).get(i);
      if (i < 4)
        QMiViewManager.e(this.a).addView(localView2);
      while (true)
      {
        QMiViewManager.a(this.a, localView2);
        i++;
        break label187;
        if ((!(localObject instanceof PluginItem)) || (((PluginItem)localObject).getStatus() != 7))
          break;
        View localView3 = QMiViewManager.a(this.a, (PluginItem)localObject);
        QMiViewManager.c(this.a).add(localView3);
        break;
        QMiViewManager.f(this.a).addView(localView2);
        QMiViewManager.f(this.a).setVisibility(0);
      }
    }
    label256: QMiWindowManager.i();
    this.a.g.j();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ab
 * JD-Core Version:    0.6.0
 */
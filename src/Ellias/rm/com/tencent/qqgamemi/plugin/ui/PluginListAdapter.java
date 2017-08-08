package com.tencent.qqgamemi.plugin.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.component.ComponentContext;
import com.tencent.component.net.download.multiplex.FileDownload;
import com.tencent.component.net.download.multiplex.download.DownloadTask;
import com.tencent.component.net.download.multiplex.task.Task;
import com.tencent.component.net.download.multiplex.task.TaskObserver;
import com.tencent.component.ui.widget.image.AsyncImageView;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.plugin.PluginItem;
import com.tencent.qqgamemi.plugin.PluginOrder;
import com.tencent.qqgamemi.plugin.QMiPluginManager;
import com.tencent.qqgamemi.report.UserAccessStatics;
import com.tencent.qqgamemi.view.CircleProgressBar;
import com.tencent.qqgamemi.view.QMiToast;
import com.tencent.qqgamemi.view.dragsortlist.DragSortListView;
import com.tencent.qqgamemi.view.dragsortlist.DragSortListView.DropListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PluginListAdapter extends BaseAdapter
  implements TaskObserver, DragSortListView.DropListener
{
  private static final String b = PluginListAdapter.class.getSimpleName();
  private static final int j = 1;
  private static final int k = 2;
  private static final int l = 3;
  private static final int m = 4;
  private static final int n = 5;
  private static final int o = 6;
  Handler a = new c(this, Looper.getMainLooper());
  private Context c = null;
  private LayoutInflater d = null;
  private DragSortListView e = null;
  private PinnedItemList f = null;
  private List g = new ArrayList();
  private View.OnClickListener h = new a(this);
  private View.OnClickListener i = new b(this);

  public PluginListAdapter(Context paramContext, DragSortListView paramDragSortListView)
  {
    this.c = paramContext;
    this.d = LayoutInflater.from(paramContext);
    this.e = paramDragSortListView;
    this.f = new PinnedItemList(paramContext);
    ComponentContext.a(paramContext);
  }

  private View a(int paramInt)
  {
    if ((this.e != null) && (paramInt >= this.e.getFirstVisiblePosition()) && (paramInt <= this.e.getLastVisiblePosition()))
      return this.e.getChildAt(paramInt - this.e.getFirstVisiblePosition());
    return null;
  }

  private void a(PluginItem paramPluginItem)
  {
    DownloadTask localDownloadTask = FileDownload.a(paramPluginItem.pkgUrl);
    if (localDownloadTask != null)
    {
      FileDownload.a(localDownloadTask);
      return;
    }
    FileDownload.a(paramPluginItem.pkgUrl, QMiCommon.a(), QMiCommon.a(paramPluginItem.pkgUrl), this);
  }

  private void a(String paramString)
  {
    QMiToast.a(this.c, paramString, 1000).show();
  }

  private void a(List paramList)
  {
    this.f.a(paramList);
    notifyDataSetChanged();
  }

  private void b(PluginItem paramPluginItem)
  {
    FileDownload.b(paramPluginItem.pkgUrl);
  }

  private void f(Task paramTask)
  {
  }

  private void h(Task paramTask)
  {
    PinnedItem localPinnedItem = k(paramTask);
    if (localPinnedItem != null)
    {
      localPinnedItem.f.setStatus(2);
      View localView1 = a(this.f.a(localPinnedItem));
      if (localView1 != null)
      {
        View localView2 = ((ViewGroup)localView1).getChildAt(0);
        if (localView2 != null)
        {
          e locale = (e)localView2.getTag();
          if ((locale != null) && (locale.f != null) && (locale.f.c()) && (locale.f == localPinnedItem))
            locale.c.setProgress(((DownloadTask)paramTask).u());
        }
      }
    }
  }

  private void i(Task paramTask)
  {
    PinnedItem localPinnedItem = k(paramTask);
    if (localPinnedItem != null)
    {
      localPinnedItem.f.setStatus(5);
      notifyDataSetChanged();
    }
  }

  private void j(Task paramTask)
  {
    PinnedItem localPinnedItem = k(paramTask);
    if (localPinnedItem != null)
    {
      if (!localPinnedItem.c())
        break label53;
      localPinnedItem.f.setStatus(7);
      localPinnedItem.f.version = localPinnedItem.f.lastVersion;
      localPinnedItem.f.needUpdate = false;
    }
    while (true)
    {
      notifyDataSetChanged();
      return;
      label53: localPinnedItem.f.setStatus(8);
    }
  }

  private PinnedItem k(Task paramTask)
  {
    Iterator localIterator = this.f.c().iterator();
    while (localIterator.hasNext())
    {
      PinnedItem localPinnedItem = (PinnedItem)localIterator.next();
      if ((localPinnedItem.f != null) && (localPinnedItem.f.pkgUrl != null) && (localPinnedItem.f.pkgUrl.equals(paramTask.v())))
        return localPinnedItem;
    }
    return null;
  }

  public void a()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = this.f.c().iterator();
    while (localIterator1.hasNext())
    {
      PinnedItem localPinnedItem = (PinnedItem)localIterator1.next();
      if (localPinnedItem.c())
      {
        localArrayList.add(new PluginOrder(localPinnedItem.f.id, true));
        continue;
      }
      if ((!localPinnedItem.d()) || (!localPinnedItem.f.isLocal()))
        continue;
      localArrayList.add(new PluginOrder(localPinnedItem.f.id, false));
    }
    Iterator localIterator2 = this.g.iterator();
    while (localIterator2.hasNext())
    {
      PluginItem localPluginItem = (PluginItem)localIterator2.next();
      if (!localPluginItem.isHide)
        continue;
      TLog.c(b, "hide plugin:" + localPluginItem + " at:" + this.g.indexOf(localPluginItem));
      if (localPluginItem.getStatus() == 7)
      {
        int i2 = Math.min(localArrayList.size(), this.g.indexOf(localPluginItem));
        TLog.c(b, "hide enable plugin:" + localPluginItem + " at:" + i2);
        localArrayList.add(i2, new PluginOrder(localPluginItem.id, true));
        continue;
      }
      if (localPluginItem.getStatus() != 8)
        continue;
      int i1 = Math.min(localArrayList.size(), this.g.indexOf(localPluginItem));
      TLog.c(b, "hide disable plugin:" + localPluginItem + " at:" + i1);
      localArrayList.add(i1, new PluginOrder(localPluginItem.id, false));
    }
    QMiPluginManager.a().a(localArrayList);
  }

  public void a(int paramInt1, int paramInt2)
  {
    PinnedItem localPinnedItem1;
    PinnedItem localPinnedItem2;
    if (paramInt1 != paramInt2)
    {
      localPinnedItem1 = this.f.a(paramInt1);
      localPinnedItem2 = this.f.a(paramInt2);
      if (!localPinnedItem1.d())
        break label161;
      if (localPinnedItem1.e != localPinnedItem2.e)
      {
        if (!localPinnedItem1.f.isLocal())
          break label77;
        localPinnedItem1.f.setStatus(7);
        this.f.a(paramInt1, paramInt2);
        notifyDataSetChanged();
      }
    }
    label77: 
    do
    {
      return;
      if (localPinnedItem1.f.pkgSize > QMiCommon.d())
      {
        a("SD卡空间不足");
        notifyDataSetChanged();
        return;
      }
      if (this.f.a(paramInt1, paramInt2))
      {
        localPinnedItem1.f.setStatus(2);
        a(localPinnedItem1.f);
        UserAccessStatics.getInstance(this.c).addQMiAction(208, System.currentTimeMillis(), null, localPinnedItem1.f.id);
      }
      notifyDataSetChanged();
      return;
    }
    while (!localPinnedItem1.c());
    label161: if (localPinnedItem1.e != localPinnedItem2.e)
    {
      if (!localPinnedItem1.f.isLocal())
        break label214;
      localPinnedItem1.f.setStatus(8);
    }
    while (true)
    {
      this.f.a(paramInt1, paramInt2);
      notifyDataSetChanged();
      return;
      label214: b(localPinnedItem1.f);
      localPinnedItem1.f.setStatus(1);
    }
  }

  public void a(Task paramTask)
  {
    TLog.c(b, "onTaskCreated");
  }

  public void b()
  {
    TLog.c(b, "loadPluginInfos");
    List localList = QMiPluginManager.a().i();
    this.g.addAll(localList);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      if (!((PluginItem)localIterator.next()).isHide)
        continue;
      localIterator.remove();
    }
    Message localMessage = new Message();
    localMessage.what = 4;
    localMessage.obj = localList;
    this.a.sendMessage(localMessage);
  }

  public void b(Task paramTask)
  {
    TLog.c(b, "onTaskStarted");
  }

  public void c(Task paramTask)
  {
    TLog.c(b, "onTaskProgress:" + ((DownloadTask)paramTask).u());
    Message localMessage = new Message();
    localMessage.what = 1;
    localMessage.obj = paramTask;
    this.a.sendMessage(localMessage);
  }

  public void d(Task paramTask)
  {
    TLog.c(b, "onTaskCompleted");
    Message localMessage = new Message();
    localMessage.what = 2;
    localMessage.obj = paramTask;
    this.a.sendMessage(localMessage);
  }

  public void e(Task paramTask)
  {
    TLog.c(b, "onTaskFailed");
    Message localMessage = new Message();
    localMessage.what = 3;
    localMessage.obj = paramTask;
    this.a.sendMessage(localMessage);
  }

  public void g(Task paramTask)
  {
    TLog.c(b, "onTaskExtEvent");
  }

  public int getCount()
  {
    return this.f.a();
  }

  public Object getItem(int paramInt)
  {
    return this.f.a(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    return ((PinnedItem)getItem(paramInt)).e;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i1 = getItemViewType(paramInt);
    e locale;
    label48: PinnedItem localPinnedItem;
    if (paramView == null)
    {
      locale = new e(null);
      if (i1 == 0)
      {
        paramView = this.d.inflate(ResourceUtil.a("qmi_plugin_manager_list_install_tab"), paramViewGroup, false);
        paramView.setTag(locale);
        localPinnedItem = (PinnedItem)getItem(paramInt);
        locale.f = localPinnedItem;
        if (i1 != 1)
          break label497;
        locale.b.setText(localPinnedItem.f.name);
        Drawable localDrawable2 = localPinnedItem.f.getIcon(this.c);
        if (localDrawable2 == null)
          break label429;
        locale.a.setImageDrawable(localDrawable2);
        label116: locale.d.setOnClickListener(this.h);
        locale.d.setTag(localPinnedItem);
        if ((!localPinnedItem.f.needUpdate) || (localPinnedItem.f.getStatus() != 7))
          break label448;
        locale.d.setVisibility(0);
        label171: locale.c.setOnClickListener(this.i);
        locale.c.setTag(localPinnedItem);
        if (localPinnedItem.f.getStatus() != 2)
          break label461;
        locale.c.setVisibility(0);
      }
    }
    label429: label448: label461: label497: 
    do
    {
      return paramView;
      if (i1 == 1)
      {
        paramView = this.d.inflate(ResourceUtil.a("qmi_plugin_manager_list_install_item"), paramViewGroup, false);
        locale.a = ((AsyncImageView)paramView.findViewById(ResourceUtil.f("plugin_icon")));
        locale.b = ((TextView)paramView.findViewById(ResourceUtil.f("plugin_text")));
        locale.c = ((CircleProgressBar)paramView.findViewById(ResourceUtil.f("plugin_progress")));
        locale.d = ((Button)paramView.findViewById(ResourceUtil.f("plugin_update")));
        break;
      }
      if (i1 == 2)
      {
        paramView = this.d.inflate(ResourceUtil.a("qmi_plugin_manager_list_uninstall_tab"), paramViewGroup, false);
        break;
      }
      if (i1 != 3)
        break;
      paramView = this.d.inflate(ResourceUtil.a("qmi_plugin_manager_list_uninstall_item"), paramViewGroup, false);
      locale.a = ((AsyncImageView)paramView.findViewById(ResourceUtil.f("plugin_icon")));
      locale.b = ((TextView)paramView.findViewById(ResourceUtil.f("plugin_text")));
      locale.e = ((ImageView)paramView.findViewById(ResourceUtil.f("plugin_new")));
      break;
      locale = (e)paramView.getTag();
      break label48;
      locale.a.setAsyncImageUrl(localPinnedItem.f.iconUrl);
      break label116;
      locale.d.setVisibility(8);
      break label171;
      if (localPinnedItem.f.getStatus() == 5)
      {
        locale.c.setVisibility(8);
        return paramView;
      }
      locale.c.setVisibility(8);
      return paramView;
    }
    while (i1 != 3);
    locale.b.setText(localPinnedItem.f.name);
    if (localPinnedItem.f.getStatus() == 8)
    {
      Drawable localDrawable1 = localPinnedItem.f.getIcon(this.c);
      if (localDrawable1 != null)
        locale.a.setImageDrawable(localDrawable1);
    }
    while (localPinnedItem.f.isNew)
    {
      locale.e.setVisibility(0);
      return paramView;
      locale.a.setAsyncImageUrl(localPinnedItem.f.iconUrl);
      continue;
      locale.a.setAsyncImageUrl(localPinnedItem.f.iconUrl);
    }
    locale.e.setVisibility(8);
    return paramView;
  }

  public int getViewTypeCount()
  {
    return 4;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.ui.PluginListAdapter
 * JD-Core Version:    0.6.0
 */
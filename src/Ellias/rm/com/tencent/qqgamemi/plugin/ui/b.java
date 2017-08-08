package com.tencent.qqgamemi.plugin.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.qqgamemi.plugin.PluginItem;
import com.tencent.qqgamemi.report.UserAccessStatics;

class b
  implements View.OnClickListener
{
  b(PluginListAdapter paramPluginListAdapter)
  {
  }

  public void onClick(View paramView)
  {
    PinnedItem localPinnedItem = (PinnedItem)paramView.getTag();
    if (localPinnedItem != null)
    {
      PluginListAdapter.b(this.a, localPinnedItem.f);
      if (!localPinnedItem.f.isLocal())
        break label81;
      localPinnedItem.a();
      localPinnedItem.f.setStatus(7);
    }
    while (true)
    {
      this.a.notifyDataSetChanged();
      UserAccessStatics.getInstance(PluginListAdapter.a(this.a)).addQMiAction(210, System.currentTimeMillis(), null, localPinnedItem.f.id);
      return;
      label81: localPinnedItem.b();
      localPinnedItem.f.setStatus(1);
      PluginListAdapter.b(this.a).b(localPinnedItem);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.ui.b
 * JD-Core Version:    0.6.0
 */
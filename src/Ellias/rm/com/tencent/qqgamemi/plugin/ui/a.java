package com.tencent.qqgamemi.plugin.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.qqgamemi.plugin.PluginItem;
import com.tencent.qqgamemi.report.UserAccessStatics;

class a
  implements View.OnClickListener
{
  a(PluginListAdapter paramPluginListAdapter)
  {
  }

  public void onClick(View paramView)
  {
    PinnedItem localPinnedItem = (PinnedItem)paramView.getTag();
    if (localPinnedItem != null)
    {
      localPinnedItem.f.setStatus(2);
      PluginListAdapter.a(this.a, localPinnedItem.f);
      this.a.notifyDataSetChanged();
      UserAccessStatics.getInstance(PluginListAdapter.a(this.a)).addQMiAction(209, System.currentTimeMillis(), null, localPinnedItem.f.id);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.ui.a
 * JD-Core Version:    0.6.0
 */
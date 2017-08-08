package com.tencent.qqgamemi.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.List;

class a
  implements AdapterView.OnItemClickListener
{
  a(EnvironmentSelectDialog.Builder paramBuilder)
  {
  }

  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    EnvironmentSelectDialog.a = (String)EnvironmentSelectDialog.a().get(paramInt);
    EnvironmentSelectDialog.Builder.a(this.a).dismiss();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.a
 * JD-Core Version:    0.6.0
 */
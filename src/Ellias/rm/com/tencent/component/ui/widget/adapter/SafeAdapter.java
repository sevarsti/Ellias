package com.tencent.component.ui.widget.adapter;

import android.os.Handler;
import android.os.Looper;
import android.widget.BaseAdapter;
import com.tencent.component.annotation.PluginApi;
import java.util.ArrayList;
import java.util.List;

@PluginApi(a=4)
public abstract class SafeAdapter extends BaseAdapter
{
  private List a;
  private Handler b = new Handler(Looper.getMainLooper());

  private void a()
  {
    super.notifyDataSetChanged();
  }

  @PluginApi(a=4)
  public int getCount()
  {
    if (this.a == null)
      return 0;
    return this.a.size();
  }

  @PluginApi(a=4)
  public List getDatas()
  {
    return this.a;
  }

  @PluginApi(a=4)
  public Object getItem(int paramInt)
  {
    if (this.a == null);
    do
      return null;
    while (this.a.size() <= paramInt);
    return this.a.get(paramInt);
  }

  @PluginApi(a=4)
  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  @PluginApi(a=4)
  public boolean isEnabled(int paramInt)
  {
    if (paramInt > this.a.size())
      return false;
    return super.isEnabled(paramInt);
  }

  @PluginApi(a=4)
  public void notifyDataSetChanged()
  {
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      a();
      return;
    }
    this.b.post(new g(this));
  }

  @PluginApi(a=4)
  public void setDatas(List paramList)
  {
    if (paramList != null);
    for (ArrayList localArrayList = new ArrayList(paramList); Looper.myLooper() == Looper.getMainLooper(); localArrayList = null)
    {
      this.a = localArrayList;
      a();
      return;
    }
    this.b.post(new f(this, localArrayList));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.adapter.SafeAdapter
 * JD-Core Version:    0.6.0
 */
package com.pay.ui.channel;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.List;

final class b
  implements AdapterView.OnItemClickListener
{
  b(APChannelActivity paramAPChannelActivity)
  {
  }

  public final void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    String str = ((APChannelInfo)this.a.recoList.get(paramInt)).channelId;
    APChannelActivity.a(this.a, str);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.b
 * JD-Core Version:    0.6.0
 */
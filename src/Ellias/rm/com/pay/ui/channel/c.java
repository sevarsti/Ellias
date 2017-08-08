package com.pay.ui.channel;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.List;

final class c
  implements AdapterView.OnItemClickListener
{
  c(APChannelActivity paramAPChannelActivity)
  {
  }

  public final void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    String str = ((APChannelInfo)this.a.moreList.get(paramInt)).channelId;
    APChannelActivity.a(this.a, str);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.c
 * JD-Core Version:    0.6.0
 */
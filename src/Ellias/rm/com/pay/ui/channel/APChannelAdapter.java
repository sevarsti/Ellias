package com.pay.ui.channel;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.pay.tool.APCommMethod;
import java.util.ArrayList;
import java.util.List;

public class APChannelAdapter extends BaseAdapter
{
  private Context a;
  private List b = new ArrayList();

  public APChannelAdapter(Context paramContext, List paramList)
  {
    this.a = paramContext;
    this.b = paramList;
  }

  public int getCount()
  {
    return this.b.size();
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(Integer.parseInt(((APChannelInfo)this.b.get(paramInt)).channelLogo));
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null);
    while (true)
    {
      try
      {
        View localView3 = LayoutInflater.from(this.a).inflate(APCommMethod.getLayoutId(this.a, "unipay_layout_channel_item"), null);
        localView1 = localView3;
        TextView localTextView2;
        try
        {
          ImageView localImageView1 = (ImageView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_ChannelIcon"));
          ImageView localImageView2 = (ImageView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_ChannelReco"));
          TextView localTextView1 = (TextView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_ChannelText"));
          localTextView2 = (TextView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_ChannelInfo"));
          String str1 = ((APChannelInfo)this.b.get(paramInt)).channelLogo;
          String str2 = ((APChannelInfo)this.b.get(paramInt)).channelName;
          String str3 = ((APChannelInfo)this.b.get(paramInt)).channelDiscount;
          boolean bool = ((APChannelInfo)this.b.get(paramInt)).isRecommand;
          String str4 = ((APChannelInfo)this.b.get(paramInt)).channelInfo;
          if ((str3 == null) || (str3.length() <= 0) || (str3.equals("100")))
            continue;
          str2 = str2 + str3 + "折";
          int i = this.a.getResources().getIdentifier(str1, "drawable", this.a.getPackageName());
          if ((!bool) || (((APChannelInfo)this.b.get(paramInt)).channelState != 1))
            continue;
          localImageView2.setVisibility(0);
          if ((str4 == null) || (str4.length() <= 0))
            continue;
          localTextView2.setText(str4);
          localTextView2.setVisibility(0);
          localImageView1.setImageResource(i);
          localTextView1.setText(str2);
          return localView1;
          localImageView2.setVisibility(8);
          continue;
        }
        catch (Exception localException1)
        {
          localObject = localException1;
          localView2 = localView1;
        }
        ((Exception)localObject).printStackTrace();
        return localView2;
        localTextView2.setVisibility(8);
        continue;
      }
      catch (Exception localException2)
      {
        Object localObject = localException2;
        View localView2 = paramView;
        continue;
      }
      View localView1 = paramView;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.APChannelAdapter
 * JD-Core Version:    0.6.0
 */
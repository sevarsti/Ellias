package com.tencent.qqgamemi.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.component.ui.widget.image.AsyncImageView;
import com.tencent.component.utils.ResourceUtil;
import java.util.List;

class w extends BaseAdapter
{
  List a = null;
  LayoutInflater b = null;

  public w(LoginDialog paramLoginDialog, Context paramContext, List paramList)
  {
    this.a = paramList;
    this.b = LayoutInflater.from(paramContext);
  }

  public int getCount()
  {
    return this.a.size();
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    z localz;
    if (paramView == null)
    {
      paramView = this.b.inflate(ResourceUtil.a("qmi_login_spinner_item"), paramViewGroup, false);
      localz = new z(null);
      localz.a = ((AsyncImageView)paramView.findViewById(ResourceUtil.f("login_spinner_faceicon")));
      localz.b = ((TextView)paramView.findViewById(ResourceUtil.f("login_spinner_account")));
      localz.c = ((ImageView)paramView.findViewById(ResourceUtil.f("login_spinner_delete")));
      paramView.setTag(localz);
      localz.a.setAsyncImageUrl(((y)this.a.get(paramInt)).c);
      if (!TextUtils.isEmpty(((y)this.a.get(paramInt)).a))
        break label201;
      localz.b.setText(((y)this.a.get(paramInt)).b.toString());
    }
    while (true)
    {
      localz.c.setTag(Integer.valueOf(paramInt));
      localz.c.setOnClickListener(new x(this));
      return paramView;
      localz = (z)paramView.getTag();
      break;
      label201: localz.b.setText(((y)this.a.get(paramInt)).a);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.w
 * JD-Core Version:    0.6.0
 */
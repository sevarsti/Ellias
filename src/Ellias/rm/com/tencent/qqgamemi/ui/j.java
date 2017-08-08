package com.tencent.qqgamemi.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;
import java.util.List;

class j
  implements AdapterView.OnItemClickListener
{
  j(LoginDialog paramLoginDialog)
  {
  }

  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    String str = ((y)LoginDialog.n(this.a).get(paramInt)).a;
    if (TextUtils.isEmpty(str))
      LoginDialog.a(this.a, ((y)LoginDialog.n(this.a).get(paramInt)).b.toString());
    while (true)
    {
      LoginDialog.l(this.a).dismiss();
      return;
      LoginDialog.a(this.a, str);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.j
 * JD-Core Version:    0.6.0
 */
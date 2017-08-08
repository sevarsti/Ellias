package com.tencent.stat;

import android.app.ListActivity;

public class EasyListActivity extends ListActivity
{
  protected void onPause()
  {
    super.onPause();
    StatService.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    StatService.onResume(this);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.EasyListActivity
 * JD-Core Version:    0.6.0
 */
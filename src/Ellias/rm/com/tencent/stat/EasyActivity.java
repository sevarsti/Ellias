package com.tencent.stat;

import android.app.Activity;

public class EasyActivity extends Activity
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
 * Qualified Name:     com.tencent.stat.EasyActivity
 * JD-Core Version:    0.6.0
 */
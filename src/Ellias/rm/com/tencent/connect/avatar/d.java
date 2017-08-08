package com.tencent.connect.avatar;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

class d
  implements View.OnClickListener
{
  d(ImageActivity paramImageActivity)
  {
  }

  public void onClick(View paramView)
  {
    ImageActivity.d(this.a).setVisibility(0);
    ImageActivity.e(this.a).setEnabled(false);
    ImageActivity.e(this.a).setTextColor(Color.rgb(21, 21, 21));
    ImageActivity.f(this.a).setEnabled(false);
    ImageActivity.f(this.a).setTextColor(Color.rgb(36, 94, 134));
    new Thread(new Runnable()
    {
      public void run()
      {
        ImageActivity.g(d.this.a);
      }
    }).start();
    if (ImageActivity.h(this.a))
      this.a.a("10657", 0L);
    do
    {
      return;
      long l = System.currentTimeMillis() - ImageActivity.i(this.a);
      this.a.a("10655", l);
    }
    while (!ImageActivity.c(this.a).b);
    this.a.a("10654", 0L);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.connect.avatar.d
 * JD-Core Version:    0.6.0
 */
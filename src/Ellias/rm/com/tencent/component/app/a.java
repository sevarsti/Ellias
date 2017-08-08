package com.tencent.component.app;

import android.widget.Toast;

class a
  implements Runnable
{
  a(BaseFragment paramBaseFragment, String paramString, int paramInt)
  {
  }

  public void run()
  {
    Toast localToast = BaseFragment.a(this.c);
    localToast.setText(this.a);
    localToast.setGravity(this.b, localToast.getXOffset(), localToast.getYOffset());
    localToast.show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.app.a
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.ui.widget;

class i
  implements Runnable
{
  i(SwitchButton paramSwitchButton, boolean paramBoolean)
  {
  }

  public void run()
  {
    SwitchButton.a(this.b, true);
    this.b.invalidate();
    this.b.setChecked(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.i
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.ui.widget;

final class k
  implements Runnable
{
  private k(SwitchButton paramSwitchButton)
  {
  }

  public void run()
  {
    if (!SwitchButton.a(this.a))
      return;
    SwitchButton.b(this.a);
    l.a(this);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.k
 * JD-Core Version:    0.6.0
 */
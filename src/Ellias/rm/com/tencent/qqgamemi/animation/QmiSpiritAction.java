package com.tencent.qqgamemi.animation;

import android.widget.ImageView;
import java.lang.ref.WeakReference;

public abstract class QmiSpiritAction
  implements SpiritAction
{
  private QmiSpiritAction a;
  private WeakReference b;
  private boolean c = false;

  protected abstract void a();

  public abstract void a(ImageView paramImageView);

  public void a(ActionListener paramActionListener)
  {
    if (paramActionListener != null)
    {
      this.b = new WeakReference(paramActionListener);
      return;
    }
    this.b = null;
  }

  public void a(QmiSpiritAction paramQmiSpiritAction)
  {
    this.a = paramQmiSpiritAction;
  }

  public void a(boolean paramBoolean)
  {
    this.c = paramBoolean;
    if (paramBoolean)
      a();
  }

  public abstract void b();

  protected final void b(AnimationParam paramAnimationParam)
  {
    e();
    if (this.a != null)
      this.a.a(paramAnimationParam);
  }

  protected void c(AnimationParam paramAnimationParam)
  {
    if (this.b != null)
    {
      ActionListener localActionListener = (ActionListener)this.b.get();
      if (localActionListener != null)
        localActionListener.a(paramAnimationParam);
    }
  }

  protected boolean c()
  {
    return this.c;
  }

  protected void d()
  {
    if (this.b != null)
    {
      ActionListener localActionListener = (ActionListener)this.b.get();
      if (localActionListener != null)
        localActionListener.a();
    }
  }

  protected void e()
  {
    if (this.b != null)
    {
      ActionListener localActionListener = (ActionListener)this.b.get();
      if (localActionListener != null)
        localActionListener.b();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.animation.QmiSpiritAction
 * JD-Core Version:    0.6.0
 */
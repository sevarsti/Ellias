package com.tencent.component.ui.widget.image;

import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import com.tencent.component.image.ImageLoader;
import com.tencent.component.image.ImageLoader.ImageLoadListener;
import com.tencent.component.image.ImageLoader.Options;

public class AsyncImageable$AsyncImageableImpl
  implements AsyncImageable
{
  private static final String[] f = new String[0];
  int b = 0;
  Drawable c = null;
  int d = 0;
  Drawable e = null;
  private String g = null;
  private int h;
  private AsyncImageable.AsyncExtendOptions i = new AsyncImageable.AsyncExtendOptions();
  private ImageLoader.Options j = new ImageLoader.Options();
  private final ImageLoader k;
  private final AsyncImageable l;
  private AsyncImageable.AsyncImageListener m;
  private AsyncImageable.AsyncImageListener n;
  private final ExtendImageView o;
  private final c p;
  private final Thread q = Looper.getMainLooper().getThread();

  public AsyncImageable$AsyncImageableImpl(ExtendImageView paramExtendImageView, AsyncImageable paramAsyncImageable)
  {
    this.o = paramExtendImageView;
    this.p = new c(this);
    this.l = paramAsyncImageable;
    this.k = ImageLoader.a(paramExtendImageView.getContext());
    this.j.r = true;
  }

  private void a()
  {
    int i1 = this.h;
    if (i1 != 0)
    {
      Drawable localDrawable = this.o.getDrawable();
      if ((localDrawable == null) || (localDrawable.hashCode() != i1))
        b();
    }
  }

  private void a(float paramFloat)
  {
    AsyncImageable.AsyncImageListener localAsyncImageListener1 = this.m;
    Object localObject1 = this.l;
    Object localObject2;
    AsyncImageable.AsyncImageListener localAsyncImageListener2;
    if (localAsyncImageListener1 != null)
    {
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        localAsyncImageListener1.onImageProgress((AsyncImageable)localObject2, paramFloat);
      }
    }
    else
    {
      localAsyncImageListener2 = this.n;
      if (localAsyncImageListener2 != null)
        if (localObject1 == null)
          break label61;
    }
    while (true)
    {
      localAsyncImageListener2.onImageProgress((AsyncImageable)localObject1, paramFloat);
      return;
      localObject2 = this;
      break;
      label61: localObject1 = this;
    }
  }

  private void a(Drawable paramDrawable, boolean paramBoolean)
  {
    if (paramDrawable != null)
    {
      if (!paramBoolean)
        this.o.setImageDrawable(paramDrawable);
      while (true)
      {
        this.h = paramDrawable.hashCode();
        return;
        Animation localAnimation1 = this.i.g;
        Animation localAnimation2 = this.i.h;
        if (localAnimation2 != null)
        {
          b(this.o, localAnimation2, new a(this, paramDrawable, localAnimation1));
          continue;
        }
        if (localAnimation1 != null)
        {
          this.o.setImageDrawable(paramDrawable);
          b(this.o, localAnimation1, null);
          continue;
        }
        this.o.setImageDrawable(paramDrawable);
      }
    }
    this.h = 0;
  }

  private void a(String paramString)
  {
    if (Thread.currentThread() != this.q)
      throw new RuntimeException(paramString + " can ONLY be called within main thread!");
  }

  private void a(String paramString, String[] paramArrayOfString)
  {
    a();
    if (!b(paramString, paramArrayOfString))
      if (TextUtils.isEmpty(paramString))
        c();
    label143: label152: label162: 
    while (true)
    {
      return;
      this.g = paramString;
      this.i.j = paramArrayOfString;
      this.j = ((ImageLoader.Options)this.j.a());
      this.i.a(this.j);
      boolean bool1 = d(paramString, paramArrayOfString);
      Drawable localDrawable = null;
      boolean bool2;
      Object localObject;
      if (bool1)
      {
        e();
        bool2 = this.i.i;
        ImageLoader localImageLoader = this.k;
        if (!bool2)
          break label143;
        localObject = null;
        localDrawable = localImageLoader.a(paramString, (ImageLoader.ImageLoadListener)localObject, this.j);
        if (localDrawable == null)
          break label152;
        a(localDrawable, false);
        f();
      }
      while (true)
      {
        if (localDrawable != null)
          break label162;
        c();
        return;
        localObject = this.p;
        break;
        if (!bool2)
          continue;
        g();
      }
    }
  }

  private static boolean a(String paramString1, String paramString2)
  {
    return (paramString1 == paramString2) || ((paramString1 != null) && (paramString1.equals(paramString2)));
  }

  private void b()
  {
    this.g = null;
  }

  private static void b(View paramView, Animation paramAnimation, Runnable paramRunnable)
  {
    if ((paramView == null) || (paramAnimation == null))
    {
      if (paramRunnable != null)
        paramRunnable.run();
      return;
    }
    paramView.clearAnimation();
    paramAnimation.setAnimationListener(new b(paramRunnable));
    paramView.startAnimation(paramAnimation);
  }

  private boolean b(String paramString, String[] paramArrayOfString)
  {
    return c(paramString, paramArrayOfString);
  }

  private void c()
  {
    Drawable localDrawable = this.c;
    int i1 = this.b;
    if (localDrawable != null)
      this.o.setImageDrawable(localDrawable);
    do
      return;
    while (i1 == 0);
    this.o.setImageResource(i1);
  }

  private boolean c(String paramString, String[] paramArrayOfString)
  {
    return !a(this.g, paramString);
  }

  private void d()
  {
    Drawable localDrawable = this.e;
    int i1 = this.d;
    if (localDrawable != null)
      this.o.setImageDrawable(localDrawable);
    do
      return;
    while (i1 == 0);
    this.o.setImageResource(i1);
  }

  private static boolean d(String paramString, String[] paramArrayOfString)
  {
    if (!TextUtils.isEmpty(paramString))
      return true;
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      int i1 = paramArrayOfString.length;
      for (int i2 = 0; ; i2++)
      {
        if (i2 >= i1)
          break label43;
        if (!TextUtils.isEmpty(paramArrayOfString[i2]))
          break;
      }
    }
    label43: return false;
  }

  private void e()
  {
    AsyncImageable.AsyncImageListener localAsyncImageListener1 = this.m;
    Object localObject1 = this.l;
    Object localObject2;
    AsyncImageable.AsyncImageListener localAsyncImageListener2;
    if (localAsyncImageListener1 != null)
    {
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        localAsyncImageListener1.onImageStarted((AsyncImageable)localObject2);
      }
    }
    else
    {
      localAsyncImageListener2 = this.n;
      if (localAsyncImageListener2 != null)
        if (localObject1 == null)
          break label56;
    }
    while (true)
    {
      localAsyncImageListener2.onImageStarted((AsyncImageable)localObject1);
      return;
      localObject2 = this;
      break;
      label56: localObject1 = this;
    }
  }

  private void f()
  {
    AsyncImageable.AsyncImageListener localAsyncImageListener1 = this.m;
    Object localObject1 = this.l;
    Object localObject2;
    AsyncImageable.AsyncImageListener localAsyncImageListener2;
    if (localAsyncImageListener1 != null)
    {
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        localAsyncImageListener1.onImageLoaded((AsyncImageable)localObject2);
      }
    }
    else
    {
      localAsyncImageListener2 = this.n;
      if (localAsyncImageListener2 != null)
        if (localObject1 == null)
          break label56;
    }
    while (true)
    {
      localAsyncImageListener2.onImageLoaded((AsyncImageable)localObject1);
      return;
      localObject2 = this;
      break;
      label56: localObject1 = this;
    }
  }

  private void g()
  {
    AsyncImageable.AsyncImageListener localAsyncImageListener1 = this.m;
    Object localObject1 = this.l;
    Object localObject2;
    AsyncImageable.AsyncImageListener localAsyncImageListener2;
    if (localAsyncImageListener1 != null)
    {
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        localAsyncImageListener1.onImageFailed((AsyncImageable)localObject2);
      }
    }
    else
    {
      localAsyncImageListener2 = this.n;
      if (localAsyncImageListener2 != null)
        if (localObject1 == null)
          break label56;
    }
    while (true)
    {
      localAsyncImageListener2.onImageFailed((AsyncImageable)localObject1);
      return;
      localObject2 = this;
      break;
      label56: localObject1 = this;
    }
  }

  public String getAsyncImageUrl()
  {
    return this.g;
  }

  public AsyncImageable.AsyncExtendOptions getAsyncOptions()
  {
    return this.i;
  }

  public void setAsyncDefaultImage(int paramInt)
  {
    this.b = paramInt;
    this.c = null;
    if (TextUtils.isEmpty(this.g))
      c();
  }

  public void setAsyncDefaultImage(Drawable paramDrawable)
  {
    this.b = 0;
    this.c = paramDrawable;
    if (TextUtils.isEmpty(this.g))
      c();
  }

  public void setAsyncFailImage(int paramInt)
  {
    this.d = paramInt;
    this.e = null;
  }

  public void setAsyncFailImage(Drawable paramDrawable)
  {
    this.d = 0;
    this.e = paramDrawable;
  }

  public void setAsyncImageListener(AsyncImageable.AsyncImageListener paramAsyncImageListener)
  {
    this.m = paramAsyncImageListener;
  }

  public void setAsyncImageUrl(String paramString)
  {
    a(paramString, f);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.AsyncImageable.AsyncImageableImpl
 * JD-Core Version:    0.6.0
 */
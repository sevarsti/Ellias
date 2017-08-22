package com.tencent.component.ui.widget.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.tencent.component.annotation.PluginApi;

public class AsyncImageView extends ExtendImageView
  implements AsyncImageable
{
  private final AsyncImageable.AsyncImageableImpl b = new AsyncImageable.AsyncImageableImpl(this, this);

  @PluginApi(a=4)
  public AsyncImageView(Context paramContext)
  {
    this(paramContext, null);
  }

  @PluginApi(a=4)
  public AsyncImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  @PluginApi(a=4)
  public AsyncImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  @PluginApi(a=4)
  public String getAsyncImageUrl()
  {
    return this.b.getAsyncImageUrl();
  }

  @PluginApi(a=4)
  public AsyncImageable.AsyncExtendOptions getAsyncOptions()
  {
    return this.b.getAsyncOptions();
  }

  @PluginApi(a=4)
  public void setAsyncDefaultImage(int paramInt)
  {
    this.b.setAsyncDefaultImage(paramInt);
  }

  @PluginApi(a=4)
  public void setAsyncDefaultImage(Drawable paramDrawable)
  {
    this.b.setAsyncDefaultImage(paramDrawable);
  }

  @PluginApi(a=4)
  public void setAsyncFailImage(int paramInt)
  {
    this.b.setAsyncFailImage(paramInt);
  }

  @PluginApi(a=4)
  public void setAsyncFailImage(Drawable paramDrawable)
  {
    this.b.setAsyncFailImage(paramDrawable);
  }

  @PluginApi(a=4)
  public void setAsyncImageListener(AsyncImageable.AsyncImageListener paramAsyncImageListener)
  {
    this.b.setAsyncImageListener(paramAsyncImageListener);
  }

  @PluginApi(a=4)
  public void setAsyncImageUrl(String paramString)
  {
    this.b.setAsyncImageUrl(paramString);
  }

  @PluginApi(a=4)
  public void setAsyncPriority(boolean paramBoolean)
  {
    getAsyncOptions().setPriority(paramBoolean);
  }

  @PluginApi(a=4)
  public void setJustMemoryCache(boolean paramBoolean)
  {
    getAsyncOptions().setJustMemoryCache(paramBoolean);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.AsyncImageView
 * JD-Core Version:    0.6.0
 */
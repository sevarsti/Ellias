package com.tencent.component.ui.widget.image;

import android.graphics.drawable.Drawable;
import com.tencent.component.annotation.PluginApi;

public abstract interface AsyncImageable
{
  public static final String a = "AsyncImageable";

  @PluginApi(a=4)
  public abstract String getAsyncImageUrl();

  public abstract AsyncImageable.AsyncExtendOptions getAsyncOptions();

  @PluginApi(a=4)
  public abstract void setAsyncDefaultImage(int paramInt);

  @PluginApi(a=4)
  public abstract void setAsyncDefaultImage(Drawable paramDrawable);

  @PluginApi(a=4)
  public abstract void setAsyncFailImage(int paramInt);

  @PluginApi(a=4)
  public abstract void setAsyncFailImage(Drawable paramDrawable);

  @PluginApi(a=4)
  public abstract void setAsyncImageListener(AsyncImageable.AsyncImageListener paramAsyncImageListener);

  @PluginApi(a=4)
  public abstract void setAsyncImageUrl(String paramString);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.AsyncImageable
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.ui.widget.image;

import com.tencent.component.annotation.PluginApi;

public abstract interface AsyncImageable$AsyncImageListener
{
  @PluginApi(a=4)
  public abstract void onImageFailed(AsyncImageable paramAsyncImageable);

  @PluginApi(a=4)
  public abstract void onImageLoaded(AsyncImageable paramAsyncImageable);

  @PluginApi(a=4)
  public abstract void onImageProgress(AsyncImageable paramAsyncImageable, float paramFloat);

  @PluginApi(a=4)
  public abstract void onImageStarted(AsyncImageable paramAsyncImageable);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.AsyncImageable.AsyncImageListener
 * JD-Core Version:    0.6.0
 */
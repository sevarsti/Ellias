package com.tencent.component.image.image;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class b extends Handler
{
  b(GifStreamImage.AsyncGifDecoder paramAsyncGifDecoder, Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    GifStreamImage.AsyncGifDecoder.a(this.a, this, paramMessage);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.image.b
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.image.request;

import com.tencent.component.cache.common.BytesBufferPool.BytesBuffer;
import com.tencent.component.cache.image.ImageEntry;
import com.tencent.component.image.image.Image;

public abstract interface ImageRequest$Callback
{
  public abstract BytesBufferPool.BytesBuffer a();

  public abstract Image a(ImageEntry paramImageEntry);

  public abstract void a(BytesBufferPool.BytesBuffer paramBytesBuffer);

  public abstract void a(ImageEntry paramImageEntry, byte[] paramArrayOfByte);

  public abstract boolean a(ImageEntry paramImageEntry, BytesBufferPool.BytesBuffer paramBytesBuffer);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.request.ImageRequest.Callback
 * JD-Core Version:    0.6.0
 */
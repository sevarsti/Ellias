package com.tencent.component.cache.image;

import com.tencent.component.cache.common.BytesBufferPool;
import com.tencent.component.cache.common.BytesBufferPool.BytesBuffer;
import com.tencent.component.image.image.Image;
import com.tencent.component.image.request.ImageRequest.Callback;

class e
  implements ImageRequest.Callback
{
  e(ImageCacheService paramImageCacheService)
  {
  }

  public BytesBufferPool.BytesBuffer a()
  {
    return ImageCacheService.d().a();
  }

  public Image a(ImageEntry paramImageEntry)
  {
    return ImageCacheService.a(this.a).a(paramImageEntry);
  }

  public void a(BytesBufferPool.BytesBuffer paramBytesBuffer)
  {
    ImageCacheService.d().a(paramBytesBuffer);
  }

  public void a(ImageEntry paramImageEntry, byte[] paramArrayOfByte)
  {
    ImageCacheService.a(this.a, paramImageEntry, paramArrayOfByte);
  }

  public boolean a(ImageEntry paramImageEntry, BytesBufferPool.BytesBuffer paramBytesBuffer)
  {
    return ImageCacheService.a(this.a, paramImageEntry, paramBytesBuffer);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.e
 * JD-Core Version:    0.6.0
 */
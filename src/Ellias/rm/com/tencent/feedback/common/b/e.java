package com.tencent.feedback.common.b;

import android.content.Context;
import com.tencent.feedback.upload.AbstractUploadDatas;
import common.MixPackage;
import common.RequestPackage;

public final class e extends AbstractUploadDatas
{
  private MixPackage d = null;

  public e(Context paramContext, int paramInt1, int paramInt2, MixPackage paramMixPackage)
  {
    super(paramContext, 1111, 200);
    this.d = paramMixPackage;
  }

  public final RequestPackage a()
  {
    try
    {
      Context localContext = this.c;
      int i = this.a;
      if (this.d == null);
      byte[] arrayOfByte;
      for (Object localObject = null; ; localObject = arrayOfByte)
      {
        return a(localContext, i, localObject);
        arrayOfByte = this.d.toByteArray();
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return (RequestPackage)null;
  }

  public final void done(boolean paramBoolean)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.b.e
 * JD-Core Version:    0.6.0
 */
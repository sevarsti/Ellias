package com.tencent.feedback.upload;

import android.content.Context;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import common.RequestPackage;

public final class a extends AbstractUploadDatas
{
  public a(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext, paramInt1, paramInt2);
  }

  public final RequestPackage a()
  {
    c localc = c.p();
    byte[] arrayOfByte = "".getBytes();
    try
    {
      RequestPackage localRequestPackage = com.tencent.feedback.anr.a.a(b(), localc, arrayOfByte, -1, -1);
      if (localRequestPackage != null)
        return localRequestPackage;
      return null;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      e.d("rqdp{  encode2RequestPackage failed}", new Object[0]);
    }
    return null;
  }

  public final void done(boolean paramBoolean)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.upload.a
 * JD-Core Version:    0.6.0
 */
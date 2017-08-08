package com.tencent.beacon.upload;

import android.content.Context;
import com.tencent.beacon.a.d;
import com.tencent.beacon.a.e;

public final class b extends a
{
  public b(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext, 0, 100);
  }

  public final com.tencent.beacon.c.a.b a()
  {
    d locald = d.m();
    byte[] arrayOfByte = "".getBytes();
    try
    {
      com.tencent.beacon.c.a.b localb = e.a(c(), locald, arrayOfByte, -1, -1);
      if (localb != null)
        return localb;
      return null;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      com.tencent.beacon.d.a.d("encode2RequestPackage failed", new Object[0]);
    }
    return null;
  }

  public final void a(boolean paramBoolean)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.upload.b
 * JD-Core Version:    0.6.0
 */
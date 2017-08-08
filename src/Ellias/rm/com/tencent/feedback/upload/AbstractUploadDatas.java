package com.tencent.feedback.upload;

import android.content.Context;
import com.tencent.feedback.anr.a;
import com.tencent.feedback.common.b.f;
import com.tencent.feedback.common.b.f.a;
import com.tencent.feedback.common.e;
import common.RequestPackage;

public abstract class AbstractUploadDatas
{
  protected final int a;
  protected final int b;
  protected Context c;

  public AbstractUploadDatas(Context paramContext, int paramInt1, int paramInt2)
  {
    this.c = paramContext;
    this.a = paramInt2;
    this.b = paramInt1;
  }

  public static RequestPackage a(Context paramContext, int paramInt, byte[] paramArrayOfByte)
  {
    int i = -1;
    if (paramArrayOfByte != null);
    while (true)
    {
      try
      {
        f localf = com.tencent.feedback.common.b.c.a(paramContext).b();
        j = localf.f();
        i = localf.g();
        paramArrayOfByte = a.a(paramArrayOfByte, i, j, localf.d());
        if (paramArrayOfByte != null)
          continue;
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Integer.valueOf(i);
        arrayOfObject2[1] = Integer.valueOf(j);
        e.c("rqdp{  enzp error! }%d %d ", arrayOfObject2);
        return null;
        RequestPackage localRequestPackage = a.a(paramInt, com.tencent.feedback.common.c.p(), paramArrayOfByte, i, j);
        return localRequestPackage;
      }
      catch (Throwable localThrowable)
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = localThrowable.toString();
        e.c("rqdp{  imposiable comStrategy error} %s", arrayOfObject1);
        localThrowable.printStackTrace();
        return null;
      }
      int j = i;
    }
  }

  public static void d()
  {
    e.c("rqdp{  encode failed, clear db data}", new Object[0]);
  }

  public abstract RequestPackage a();

  public final int b()
  {
    return this.a;
  }

  public final String c()
  {
    try
    {
      if (this.b == 1111)
        return com.tencent.feedback.common.b.c.a(this.c).b().a();
      String str = com.tencent.feedback.common.b.c.a(this.c).b().e(this.b).a();
      return str;
    }
    catch (Throwable localThrowable)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localThrowable.toString();
      e.c("rqdp{  imposiable comStrategy error }%s", arrayOfObject);
      localThrowable.printStackTrace();
    }
    return null;
  }

  public abstract void done(boolean paramBoolean);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.upload.AbstractUploadDatas
 * JD-Core Version:    0.6.0
 */
package com.tencent.beacon.upload;

import android.content.Context;
import com.tencent.beacon.a.b.c;
import com.tencent.beacon.a.b.e.a;
import com.tencent.beacon.a.d;
import com.tencent.beacon.c.a.b;

public abstract class a
{
  protected final int a;
  protected final int b;
  protected Context c;

  public a(Context paramContext, int paramInt1, int paramInt2)
  {
    this.c = paramContext;
    this.a = paramInt2;
    this.b = paramInt1;
  }

  public static b a(Context paramContext, int paramInt, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null);
    try
    {
      c.a(paramContext).b();
      paramArrayOfByte = com.tencent.beacon.a.e.a(paramArrayOfByte, -1, -1, null);
      if (paramArrayOfByte == null)
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Integer.valueOf(-1);
        arrayOfObject2[1] = Integer.valueOf(-1);
        com.tencent.beacon.d.a.c("enzp error! :%d %d ", arrayOfObject2);
        return null;
      }
      b localb = com.tencent.beacon.a.e.a(paramInt, d.m(), paramArrayOfByte, -1, -1);
      return localb;
    }
    catch (Throwable localThrowable)
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = localThrowable.toString();
      com.tencent.beacon.d.a.c("imposiable comStrategy error:%s", arrayOfObject1);
      localThrowable.printStackTrace();
    }
    return null;
  }

  public abstract b a();

  public abstract void a(boolean paramBoolean);

  public void b()
  {
    com.tencent.beacon.d.a.c("encode failed, clear db data", new Object[0]);
  }

  public final int c()
  {
    return this.a;
  }

  public final String d()
  {
    try
    {
      if (this.b == 0)
        return c.a(this.c).b().b();
      String str = c.a(this.c).b().b(this.b).b();
      return str;
    }
    catch (Throwable localThrowable)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localThrowable.toString();
      com.tencent.beacon.d.a.c("imposiable comStrategy error:%s", arrayOfObject);
      localThrowable.printStackTrace();
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.upload.a
 * JD-Core Version:    0.6.0
 */
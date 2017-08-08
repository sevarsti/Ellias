package com.tencent.tmassistantsdk.c;

import android.content.Intent;
import android.os.IBinder;
import com.tencent.tmassistantsdk.a.a;
import com.tencent.tmassistantsdk.a.d;

public class h extends e
{
  protected void a()
  {
  }

  protected void a(IBinder paramIBinder)
  {
    this.g = com.tencent.tmassistantsdk.a.e.a(paramIBinder);
  }

  protected void b()
  {
    ((d)this.g).a(this.d, (a)this.h);
  }

  protected Intent c()
  {
    return new Intent(this.c, Class.forName(this.e));
  }

  protected void d()
  {
    ((d)this.g).b(this.d, (a)this.h);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.c.h
 * JD-Core Version:    0.6.0
 */
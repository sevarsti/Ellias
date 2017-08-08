package com.tencent.android.tpush.service.channel;

import android.util.SparseArray;

public class a
{
  private static a b = new a();
  private SparseArray a = new SparseArray();

  public a()
  {
  }

  public a(Object[] paramArrayOfObject)
  {
    for (int i = 0; i < paramArrayOfObject.length; i += 2)
      this.a.put(((Integer)paramArrayOfObject[i]).intValue(), paramArrayOfObject[(i + 1)]);
  }

  public static a a()
  {
    return b;
  }

  public void a(int paramInt, Object paramObject)
  {
    this.a.put(paramInt, paramObject);
  }

  public boolean b()
  {
    return ((Boolean)this.a.get(2, Boolean.valueOf(false))).booleanValue();
  }

  public long c()
  {
    return ((Long)this.a.get(3, Long.valueOf(0L))).longValue();
  }

  public String d()
  {
    return (String)this.a.get(0, "");
  }

  public int e()
  {
    return ((Integer)this.a.get(1, Integer.valueOf(0))).intValue();
  }

  public String f()
  {
    return d() + ":" + e();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.a
 * JD-Core Version:    0.6.0
 */
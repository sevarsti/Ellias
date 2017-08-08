package com.tencent.component.cache.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache
{
  private final LinkedHashMap a;
  private int b;
  private int c;
  private int d;
  private int e;
  private int f;
  private int g;
  private int h;

  public LruCache(int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("maxSize <= 0");
    this.c = paramInt;
    this.a = new LinkedHashMap(0, 0.75F, true);
  }

  private int c(Object paramObject1, Object paramObject2)
  {
    int i = a(paramObject1, paramObject2);
    if (i < 0)
      throw new IllegalStateException("Negative size: " + paramObject1 + "=" + paramObject2);
    return i;
  }

  protected int a(Object paramObject1, Object paramObject2)
  {
    return 1;
  }

  protected Object a(Object paramObject)
  {
    return null;
  }

  public final void a()
  {
    a(-1);
  }

  // ERROR //
  public void a(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 65	com/tencent/component/cache/common/LruCache:b	I
    //   6: iflt +20 -> 26
    //   9: aload_0
    //   10: getfield 36	com/tencent/component/cache/common/LruCache:a	Ljava/util/LinkedHashMap;
    //   13: invokevirtual 69	java/util/LinkedHashMap:isEmpty	()Z
    //   16: ifeq +48 -> 64
    //   19: aload_0
    //   20: getfield 65	com/tencent/component/cache/common/LruCache:b	I
    //   23: ifeq +41 -> 64
    //   26: new 41	java/lang/IllegalStateException
    //   29: dup
    //   30: new 43	java/lang/StringBuilder
    //   33: dup
    //   34: invokespecial 44	java/lang/StringBuilder:<init>	()V
    //   37: aload_0
    //   38: invokevirtual 73	java/lang/Object:getClass	()Ljava/lang/Class;
    //   41: invokevirtual 78	java/lang/Class:getName	()Ljava/lang/String;
    //   44: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc 80
    //   49: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: invokespecial 60	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   58: athrow
    //   59: astore_2
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_2
    //   63: athrow
    //   64: aload_0
    //   65: getfield 65	com/tencent/component/cache/common/LruCache:b	I
    //   68: iload_1
    //   69: if_icmple +13 -> 82
    //   72: aload_0
    //   73: getfield 36	com/tencent/component/cache/common/LruCache:a	Ljava/util/LinkedHashMap;
    //   76: invokevirtual 69	java/util/LinkedHashMap:isEmpty	()Z
    //   79: ifeq +6 -> 85
    //   82: aload_0
    //   83: monitorexit
    //   84: return
    //   85: aload_0
    //   86: getfield 36	com/tencent/component/cache/common/LruCache:a	Ljava/util/LinkedHashMap;
    //   89: invokevirtual 84	java/util/LinkedHashMap:entrySet	()Ljava/util/Set;
    //   92: invokeinterface 90 1 0
    //   97: invokeinterface 96 1 0
    //   102: checkcast 98	java/util/Map$Entry
    //   105: astore_3
    //   106: aload_3
    //   107: invokeinterface 101 1 0
    //   112: astore 4
    //   114: aload_3
    //   115: invokeinterface 104 1 0
    //   120: astore 5
    //   122: aload_0
    //   123: getfield 36	com/tencent/component/cache/common/LruCache:a	Ljava/util/LinkedHashMap;
    //   126: aload 4
    //   128: invokevirtual 107	java/util/LinkedHashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   131: pop
    //   132: aload_0
    //   133: aload_0
    //   134: getfield 65	com/tencent/component/cache/common/LruCache:b	I
    //   137: aload_0
    //   138: aload 4
    //   140: aload 5
    //   142: invokespecial 109	com/tencent/component/cache/common/LruCache:c	(Ljava/lang/Object;Ljava/lang/Object;)I
    //   145: isub
    //   146: putfield 65	com/tencent/component/cache/common/LruCache:b	I
    //   149: aload_0
    //   150: iconst_1
    //   151: aload_0
    //   152: getfield 111	com/tencent/component/cache/common/LruCache:f	I
    //   155: iadd
    //   156: putfield 111	com/tencent/component/cache/common/LruCache:f	I
    //   159: aload_0
    //   160: monitorexit
    //   161: aload_0
    //   162: iconst_1
    //   163: aload 4
    //   165: aload 5
    //   167: aconst_null
    //   168: invokevirtual 114	com/tencent/component/cache/common/LruCache:a	(ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   171: goto -171 -> 0
    //
    // Exception table:
    //   from	to	target	type
    //   2	26	59	finally
    //   26	59	59	finally
    //   60	62	59	finally
    //   64	82	59	finally
    //   82	84	59	finally
    //   85	161	59	finally
  }

  protected void a(boolean paramBoolean, Object paramObject1, Object paramObject2, Object paramObject3)
  {
  }

  public final int b()
  {
    monitorenter;
    try
    {
      int i = this.b;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final Object b(Object paramObject)
  {
    if (paramObject == null)
      throw new NullPointerException("key == null");
    monitorenter;
    Object localObject3;
    try
    {
      Object localObject2 = this.a.get(paramObject);
      if (localObject2 != null)
      {
        this.g = (1 + this.g);
        return localObject2;
      }
      this.h = (1 + this.h);
      monitorexit;
      localObject3 = a(paramObject);
      if (localObject3 == null)
        return null;
    }
    finally
    {
      monitorexit;
    }
    monitorenter;
    try
    {
      this.e = (1 + this.e);
      Object localObject5 = this.a.put(paramObject, localObject3);
      if (localObject5 != null)
        this.a.put(paramObject, localObject5);
      while (true)
      {
        monitorexit;
        if (localObject5 == null)
          break;
        a(false, paramObject, localObject3, localObject5);
        return localObject5;
        this.b += c(paramObject, localObject3);
      }
    }
    finally
    {
      monitorexit;
    }
    a(this.c);
    return localObject3;
  }

  public final Object b(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) || (paramObject2 == null))
      throw new NullPointerException("key == null || value == null");
    monitorenter;
    try
    {
      this.d = (1 + this.d);
      this.b += c(paramObject1, paramObject2);
      Object localObject2 = this.a.put(paramObject1, paramObject2);
      if (localObject2 != null)
        this.b -= c(paramObject1, localObject2);
      monitorexit;
      if (localObject2 != null)
        a(false, paramObject1, localObject2, paramObject2);
      a(this.c);
      return localObject2;
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public final int c()
  {
    monitorenter;
    try
    {
      int i = this.c;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final Object c(Object paramObject)
  {
    if (paramObject == null)
      throw new NullPointerException("key == null");
    monitorenter;
    try
    {
      Object localObject2 = this.a.remove(paramObject);
      if (localObject2 != null)
        this.b -= c(paramObject, localObject2);
      monitorexit;
      if (localObject2 != null)
        a(false, paramObject, localObject2, null);
      return localObject2;
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public final int d()
  {
    monitorenter;
    try
    {
      int i = this.g;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final int e()
  {
    monitorenter;
    try
    {
      int i = this.h;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final int f()
  {
    monitorenter;
    try
    {
      int i = this.e;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final int g()
  {
    monitorenter;
    try
    {
      int i = this.d;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final int h()
  {
    monitorenter;
    try
    {
      int i = this.f;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final Map i()
  {
    monitorenter;
    try
    {
      LinkedHashMap localLinkedHashMap = new LinkedHashMap(this.a);
      monitorexit;
      return localLinkedHashMap;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String toString()
  {
    monitorenter;
    try
    {
      int i = this.g + this.h;
      int j = 0;
      if (i != 0)
        j = 100 * this.g / i;
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = Integer.valueOf(this.c);
      arrayOfObject[1] = Integer.valueOf(this.g);
      arrayOfObject[2] = Integer.valueOf(this.h);
      arrayOfObject[3] = Integer.valueOf(j);
      String str = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", arrayOfObject);
      return str;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.common.LruCache
 * JD-Core Version:    0.6.0
 */
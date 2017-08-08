package com.tencent.component.event;

import android.support.v4.util.LruCache;
import android.text.TextUtils;
import com.tencent.component.utils.log.LogUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.HashMap;

class b
{
  public static final String a = "onNotify";
  public static final boolean b = true;
  private static LruCache g = new LruCache(20);
  public final String c;
  public final boolean d;
  private final WeakReference e;
  private final WeakReference f;
  private final int h;
  private final int i;

  public b(Object paramObject1, Object paramObject2, String paramString, boolean paramBoolean)
  {
    if (paramObject1 == null)
      throw new NullPointerException("ObserverBean cannot be null");
    if (paramString == null)
      throw new NullPointerException("mInvokationMethod cannot be null");
    this.e = new WeakReference(paramObject1);
    this.i = paramObject1.hashCode();
    if (paramObject2 != null)
      this.f = new WeakReference(paramObject2);
    for (this.h = paramObject2.hashCode(); ; this.h = 0)
    {
      this.c = paramString;
      this.d = paramBoolean;
      return;
      this.f = null;
    }
  }

  private static Method a(Class paramClass, String paramString)
  {
    if ((paramClass != null) && (!TextUtils.isEmpty(paramString)))
    {
      HashMap localHashMap = (HashMap)g.get(paramClass);
      if (localHashMap != null)
        return (Method)localHashMap.get(paramString);
    }
    return null;
  }

  private static void a(Class paramClass, String paramString, Method paramMethod)
  {
    if ((paramClass != null) && (!TextUtils.isEmpty(paramString)) && (paramMethod != null))
    {
      HashMap localHashMap = (HashMap)g.get(paramClass);
      if (localHashMap == null)
      {
        localHashMap = new HashMap();
        g.put(paramClass, localHashMap);
      }
      localHashMap.put(paramString, paramMethod);
    }
  }

  private void a(String paramString)
  {
    LogUtil.w("EventCenter", paramString);
  }

  private void a(String paramString, Exception paramException)
  {
    LogUtil.e("EventCenter", paramString);
  }

  private Method d()
  {
    Object localObject1 = a();
    String str = this.c;
    Object localObject2 = null;
    if (localObject1 != null)
    {
      boolean bool = TextUtils.isEmpty(str);
      localObject2 = null;
      if (!bool)
      {
        Class localClass = localObject1.getClass();
        localObject2 = a(localClass, str);
        if (localObject2 == null)
        {
          Method[] arrayOfMethod = localClass.getDeclaredMethods();
          if (arrayOfMethod != null)
          {
            int j = arrayOfMethod.length;
            int k = 0;
            if (k < j)
            {
              Object localObject3 = arrayOfMethod[k];
              Class[] arrayOfClass;
              if ((localObject3 != null) && (str.equals(((Method)localObject3).getName())))
              {
                arrayOfClass = ((Method)localObject3).getParameterTypes();
                if (arrayOfClass.length == 1)
                  if (arrayOfClass[0] == Event.class)
                    a(localClass, str, (Method)localObject3);
              }
              while (true)
              {
                k++;
                localObject2 = localObject3;
                break;
                a("Looking to invoke '" + this.c + "', found in " + localClass + " but parameterClass does not match. Expected " + Event.class + ", potential invokation method has " + arrayOfClass[0]);
                localObject3 = localObject2;
                continue;
                if (arrayOfClass.length == 0)
                {
                  a("Looking to invoke '" + this.c + "', found in " + localClass + " but has no parameter");
                  localObject3 = localObject2;
                  continue;
                }
                if (arrayOfClass.length > 1)
                  a("Looking to invoke '" + this.c + "', found in " + localClass + " but there are too many parameters");
                localObject3 = localObject2;
              }
            }
          }
        }
      }
    }
    return (Method)(Method)localObject2;
  }

  public Object a()
  {
    return this.e.get();
  }

  void a(Object paramObject)
  {
    Object localObject = a();
    if (localObject != null)
    {
      if (((localObject instanceof Observer)) && ("onNotify".equals(this.c)))
        ((Observer)localObject).onNotify((Event)paramObject);
    }
    else
      return;
    Method localMethod = d();
    if (localMethod != null)
      try
      {
        localMethod.setAccessible(true);
        localMethod.invoke(localObject, new Object[] { paramObject });
        return;
      }
      catch (Exception localException)
      {
        a(localException.getMessage(), localException);
        return;
      }
    a("method->" + this.c + " not exists in " + localObject);
  }

  public Object b()
  {
    if (this.f != null)
      return this.f.get();
    return null;
  }

  public boolean c()
  {
    return d() != null;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    b localb;
    do
    {
      return true;
      if (paramObject == null)
        return false;
      if (getClass() != paramObject.getClass())
        return false;
      localb = (b)paramObject;
      if (this.c == null)
      {
        if (localb.c != null)
          return false;
      }
      else if (!this.c.equals(localb.c))
        return false;
      if (this.d != localb.d)
        return false;
      if (this.i != localb.i)
        return false;
    }
    while (this.h == localb.h);
    return false;
  }

  public int hashCode()
  {
    int j;
    int k;
    if (this.c == null)
    {
      j = 0;
      k = 31 * (j + 31);
      if (!this.d)
        break label59;
    }
    label59: for (int m = 1231; ; m = 1237)
    {
      return 31 * (31 * (m + k) + this.i) + this.h;
      j = this.c.hashCode();
      break;
    }
  }

  public String toString()
  {
    return "ObserverBean [observer=" + a() + " invk=" + this.c + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.event.b
 * JD-Core Version:    0.6.0
 */
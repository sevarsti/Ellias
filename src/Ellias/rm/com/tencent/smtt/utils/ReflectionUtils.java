package com.tencent.smtt.utils;

import java.lang.reflect.Method;

public class ReflectionUtils
{
  public static Object invokeInstance(Object paramObject, String paramString)
  {
    return invokeInstance(paramObject, paramString, null, new Object[0]);
  }

  public static Object invokeInstance(Object paramObject, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    if (paramObject == null)
      return null;
    try
    {
      Method localMethod = paramObject.getClass().getMethod(paramString, paramArrayOfClass);
      localMethod.setAccessible(true);
      if (paramArrayOfObject.length == 0)
        paramArrayOfObject = null;
      Object localObject = localMethod.invoke(paramObject, paramArrayOfObject);
      return localObject;
    }
    catch (Throwable localThrowable)
    {
    }
    return null;
  }

  public static Object invokeStatic(Class<?> paramClass, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      Method localMethod = paramClass.getMethod(paramString, paramArrayOfClass);
      localMethod.setAccessible(true);
      Object localObject = localMethod.invoke(null, paramArrayOfObject);
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  public static Object invokeStatic(String paramString1, String paramString2)
  {
    try
    {
      Object localObject = Class.forName(paramString1).getMethod(paramString2, new Class[0]).invoke(null, new Object[0]);
      return localObject;
    }
    catch (Throwable localThrowable)
    {
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.utils.ReflectionUtils
 * JD-Core Version:    0.6.0
 */
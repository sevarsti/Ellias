package com.tencent.smtt.export.external;

import android.content.Context;
import android.util.Log;
import dalvik.system.DexClassLoader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DexLoader
{
  private DexClassLoader mClassLoader;

  public DexLoader(Context paramContext, String paramString1, String paramString2)
  {
    this(paramContext, new String[] { paramString1 }, paramString2);
  }

  public DexLoader(Context paramContext, String[] paramArrayOfString, String paramString)
  {
    Object localObject = paramContext.getClassLoader();
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      DexClassLoader localDexClassLoader = new DexClassLoader(paramArrayOfString[i], paramString, null, (ClassLoader)localObject);
      this.mClassLoader = localDexClassLoader;
      i++;
      localObject = localDexClassLoader;
    }
  }

  public Object getStaticField(String paramString1, String paramString2)
  {
    try
    {
      Field localField = this.mClassLoader.loadClass(paramString1).getField(paramString2);
      localField.setAccessible(true);
      Object localObject = localField.get(null);
      return localObject;
    }
    catch (Exception localException)
    {
      Log.e(getClass().getSimpleName(), "'" + paramString1 + "' get field '" + paramString2 + "' failed", localException);
    }
    return null;
  }

  public Object invokeMethod(Object paramObject, String paramString1, String paramString2, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      Method localMethod = this.mClassLoader.loadClass(paramString1).getMethod(paramString2, paramArrayOfClass);
      localMethod.setAccessible(true);
      Object localObject = localMethod.invoke(paramObject, paramArrayOfObject);
      return localObject;
    }
    catch (Exception localException)
    {
      Log.e(getClass().getSimpleName(), "'" + paramString1 + "' invoke method '" + paramString2 + "' failed", localException);
    }
    return null;
  }

  public Object invokeStaticMethod(String paramString1, String paramString2, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      Method localMethod = this.mClassLoader.loadClass(paramString1).getMethod(paramString2, paramArrayOfClass);
      localMethod.setAccessible(true);
      Object localObject = localMethod.invoke(null, paramArrayOfObject);
      return localObject;
    }
    catch (Exception localException)
    {
      Log.e(getClass().getSimpleName(), "'" + paramString1 + "' invoke static method '" + paramString2 + "' failed", localException);
    }
    return null;
  }

  public Class<?> loadClass(String paramString)
  {
    try
    {
      Class localClass = this.mClassLoader.loadClass(paramString);
      return localClass;
    }
    catch (Exception localException)
    {
      Log.e(getClass().getSimpleName(), "loadClass '" + paramString + "' failed", localException);
    }
    return null;
  }

  public Object newInstance(String paramString)
  {
    try
    {
      Object localObject = this.mClassLoader.loadClass(paramString).newInstance();
      return localObject;
    }
    catch (Exception localException)
    {
      Log.e(getClass().getSimpleName(), "create " + paramString + " instance failed", localException);
    }
    return null;
  }

  public Object newInstance(String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      Object localObject = this.mClassLoader.loadClass(paramString).getConstructor(paramArrayOfClass).newInstance(paramArrayOfObject);
      return localObject;
    }
    catch (Exception localException)
    {
      Log.e(getClass().getSimpleName(), "create '" + paramString + "' instance failed", localException);
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.export.external.DexLoader
 * JD-Core Version:    0.6.0
 */
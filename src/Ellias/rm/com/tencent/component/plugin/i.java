package com.tencent.component.plugin;

import dalvik.system.DexClassLoader;

class i extends DexClassLoader
{
  public i(String paramString1, String paramString2, String paramString3, ClassLoader paramClassLoader)
  {
    super(paramString1, paramString2, paramString3, paramClassLoader);
  }

  protected Class loadClass(String paramString, boolean paramBoolean)
  {
    Object localObject = findLoadedClass(paramString);
    if (localObject == null);
    try
    {
      Class localClass = findClass(paramString);
      localObject = localClass;
      label20: if (localObject == null)
        localObject = getParent().loadClass(paramString);
      return localObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      break label20;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.i
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.cache.file;

import com.tencent.component.cache.common.LruCache;
import com.tencent.component.utils.FileUtil;
import java.io.File;

public class FileCache extends LruCache
{
  public FileCache(int paramInt)
  {
    super(paramInt);
  }

  private static int b(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return 0;
    return 1;
  }

  protected int a(Object paramObject, String paramString)
  {
    return b(paramString);
  }

  public void a(String paramString)
  {
    if (paramString != null)
      FileUtil.a(new File(paramString));
  }

  protected void a(boolean paramBoolean, Object paramObject, String paramString1, String paramString2)
  {
    if (paramString1 == paramString2);
    do
      return;
    while ((paramString1 != null) && (paramString1.equals(paramString2)));
    monitorenter;
    try
    {
      a(paramString1);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.file.FileCache
 * JD-Core Version:    0.6.0
 */
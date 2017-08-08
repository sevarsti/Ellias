package com.tencent.component.os.info;

import android.content.Context;
import android.os.Environment;
import com.tencent.component.ComponentContext;

public class StorageDash
{
  public static boolean a()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }

  public static boolean b()
  {
    String str = Environment.getExternalStorageState();
    return ("mounted".equals(str)) || ("mounted_ro".equals(str));
  }

  public static StorageInfo c()
  {
    if (!b())
      return null;
    return StorageInfo.b(Environment.getExternalStorageDirectory());
  }

  public static StorageInfo d()
  {
    return StorageInfo.b(ComponentContext.a().getFilesDir());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.os.info.StorageDash
 * JD-Core Version:    0.6.0
 */
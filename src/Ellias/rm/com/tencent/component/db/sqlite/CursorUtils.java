package com.tencent.component.db.sqlite;

import android.database.Cursor;
import android.text.TextUtils;
import com.tencent.component.db.EntityContext;
import com.tencent.component.db.entity.ColumnEntity;
import com.tencent.component.db.entity.IdEntity;
import com.tencent.component.db.entity.TableEntity;
import com.tencent.component.utils.log.LogUtil;
import java.util.concurrent.ConcurrentHashMap;

public class CursorUtils
{
  private static final String a = "CursorUtils";

  public static Object a(Cursor paramCursor, Class paramClass, EntityContext paramEntityContext)
  {
    if (paramCursor == null)
      return null;
    while (true)
    {
      try
      {
        TableEntity localTableEntity = TableEntity.a(paramClass, paramEntityContext);
        IdEntity localIdEntity = localTableEntity.b();
        String str1 = localIdEntity.b();
        if (localTableEntity.d())
        {
          String str3 = paramCursor.getString(paramCursor.getColumnIndex("_reserved_dynamic_class"));
          if (!TextUtils.isEmpty(str3))
          {
            ClassLoader localClassLoader = paramEntityContext.c();
            if (localClassLoader != null)
              continue;
            localClassLoader = CursorUtils.class.getClassLoader();
            if (localClassLoader != null)
            {
              localObject = localClassLoader.loadClass(str3).newInstance();
              if (localObject != null)
                continue;
              localObject = paramClass.newInstance();
              localIdEntity.a(localObject, paramCursor, paramCursor.getColumnIndex(str1), paramEntityContext.c());
              int i = paramCursor.getColumnCount();
              int j = 0;
              if (j >= i)
                continue;
              String str2 = paramCursor.getColumnName(j);
              ColumnEntity localColumnEntity = (ColumnEntity)localTableEntity.a.get(str2);
              if (localColumnEntity == null)
                continue;
              localColumnEntity.a(localObject, paramCursor, j, paramEntityContext.c());
              j++;
              continue;
              return localObject;
            }
          }
        }
      }
      catch (Throwable localThrowable)
      {
        LogUtil.e("CursorUtils", localThrowable.getMessage(), localThrowable);
        return null;
      }
      Object localObject = null;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.sqlite.CursorUtils
 * JD-Core Version:    0.6.0
 */
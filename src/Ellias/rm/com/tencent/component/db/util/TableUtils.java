package com.tencent.component.db.util;

import android.text.TextUtils;
import com.tencent.component.db.EntityContext;
import com.tencent.component.db.annotation.Id;
import com.tencent.component.db.annotation.Table;
import com.tencent.component.db.converter.ColumnConverterFactory;
import com.tencent.component.db.entity.ColumnEntity;
import com.tencent.component.db.entity.IdEntity;
import com.tencent.component.utils.log.LogUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;

public class TableUtils
{
  private static final String a = "TableUtils";
  private static ConcurrentHashMap b = new ConcurrentHashMap();
  private static ConcurrentHashMap c = new ConcurrentHashMap();
  private static ConcurrentHashMap d = new ConcurrentHashMap();

  public static String a(Class paramClass)
  {
    return a(paramClass, null);
  }

  public static String a(Class paramClass, String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      String str = (String)b.get(paramClass);
      Table localTable;
      if (TextUtils.isEmpty(str))
      {
        localTable = (Table)paramClass.getAnnotation(Table.class);
        if ((localTable != null) && (!TextUtils.isEmpty(localTable.name())))
          break label77;
      }
      label77: for (str = paramClass.getName().toLowerCase().replace('.', '_'); ; str = localTable.name())
      {
        b.put(paramClass, str);
        return str;
      }
    }
    return paramString;
  }

  public static ConcurrentHashMap a(Class paramClass, EntityContext paramEntityContext)
  {
    monitorenter;
    try
    {
      ConcurrentHashMap localConcurrentHashMap;
      if (c.containsKey(paramClass.getCanonicalName()))
        localConcurrentHashMap = (ConcurrentHashMap)c.get(paramClass.getCanonicalName());
      while (true)
      {
        return localConcurrentHashMap;
        localConcurrentHashMap = new ConcurrentHashMap();
        a(paramClass, e(paramClass), localConcurrentHashMap, paramEntityContext);
        c.put(paramClass.getCanonicalName(), localConcurrentHashMap);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static void a(Class paramClass, String paramString, ConcurrentHashMap paramConcurrentHashMap, EntityContext paramEntityContext)
  {
    if ((paramClass != null) && (!Object.class.equals(paramClass)));
    while (true)
    {
      int j;
      try
      {
        Field[] arrayOfField = paramClass.getDeclaredFields();
        int i = arrayOfField.length;
        j = 0;
        if (j >= i)
          break label152;
        Field localField = arrayOfField[j];
        if ((ColumnUtils.c(localField)) || (Modifier.isStatic(localField.getModifiers())) || (!ColumnUtils.d(localField)) || (!ColumnConverterFactory.c(localField.getType())) || (localField.getName().equals(paramString)))
          break label163;
        ColumnEntity localColumnEntity = new ColumnEntity(paramClass, localField, paramEntityContext.d());
        if (paramConcurrentHashMap.containsKey(localColumnEntity.b()))
          break label163;
        paramConcurrentHashMap.put(localColumnEntity.b(), localColumnEntity);
      }
      catch (Throwable localThrowable)
      {
        LogUtil.e("TableUtils", localThrowable.getMessage(), localThrowable);
      }
      return;
      label152: a(paramClass.getSuperclass(), paramString, paramConcurrentHashMap, paramEntityContext);
      return;
      label163: j++;
    }
  }

  public static int b(Class paramClass)
  {
    Table localTable = (Table)paramClass.getAnnotation(Table.class);
    if (localTable == null)
      return 1;
    return localTable.version();
  }

  public static boolean c(Class paramClass)
  {
    Table localTable = (Table)paramClass.getAnnotation(Table.class);
    if (localTable == null)
      return false;
    return localTable.dynamicClass();
  }

  public static IdEntity d(Class paramClass)
  {
    int i = 0;
    monitorenter;
    try
    {
      if (Object.class.equals(paramClass))
        throw new RuntimeException("field 'id' not found");
    }
    finally
    {
      monitorexit;
    }
    IdEntity localIdEntity;
    if (d.containsKey(paramClass.getCanonicalName()))
    {
      localIdEntity = (IdEntity)d.get(paramClass.getCanonicalName());
      monitorexit;
      return localIdEntity;
    }
    Field[] arrayOfField = paramClass.getDeclaredFields();
    int k;
    label80: Object localObject2;
    label103: int m;
    if (arrayOfField != null)
    {
      int j = arrayOfField.length;
      k = 0;
      if (k < j)
      {
        localObject2 = arrayOfField[k];
        if (((Field)localObject2).getAnnotation(Id.class) == null)
          break label216;
        if (localObject2 == null)
          m = arrayOfField.length;
      }
    }
    while (true)
    {
      Field localField;
      if (i < m)
      {
        localField = arrayOfField[i];
        if ("id".equals(localField.getName()))
          break label209;
        if (!"_id".equals(localField.getName()))
          break label222;
      }
      while (true)
      {
        if (localObject2 == null)
        {
          localIdEntity = d(paramClass.getSuperclass());
          break;
        }
        localIdEntity = new IdEntity(paramClass, (Field)localObject2);
        d.put(paramClass.getCanonicalName(), localIdEntity);
        break;
        localObject2 = null;
        break label103;
        localObject2 = null;
        continue;
        label209: localObject2 = localField;
      }
      label216: k++;
      break label80;
      label222: i++;
    }
  }

  private static String e(Class paramClass)
  {
    IdEntity localIdEntity = d(paramClass);
    if (localIdEntity == null)
      return null;
    return localIdEntity.d().getName();
  }

  private static String f(Class paramClass)
  {
    IdEntity localIdEntity = d(paramClass);
    if (localIdEntity == null)
      return null;
    return localIdEntity.b();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.util.TableUtils
 * JD-Core Version:    0.6.0
 */
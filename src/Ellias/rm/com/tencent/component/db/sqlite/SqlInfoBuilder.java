package com.tencent.component.db.sqlite;

import com.tencent.component.db.EntityContext;
import com.tencent.component.db.entity.ColumnEntity;
import com.tencent.component.db.entity.IdEntity;
import com.tencent.component.db.entity.TableEntity;
import com.tencent.component.db.exception.DBException;
import com.tencent.component.db.util.ColumnUtils;
import com.tencent.component.utils.KeyValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SqlInfoBuilder
{
  public static SqlInfo a(EntityContext paramEntityContext, Class paramClass, Object paramObject, WhereBuilder paramWhereBuilder, String[] paramArrayOfString)
  {
    List localList = g(paramClass, paramObject, paramEntityContext);
    if (localList.size() == 0)
      return null;
    HashSet localHashSet2;
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      localHashSet2 = new HashSet(paramArrayOfString.length);
      Collections.addAll(localHashSet2, paramArrayOfString);
    }
    for (HashSet localHashSet1 = localHashSet2; ; localHashSet1 = null)
    {
      TableEntity localTableEntity = TableEntity.a(paramClass, paramEntityContext);
      SqlInfo localSqlInfo = new SqlInfo();
      StringBuffer localStringBuffer = new StringBuffer("UPDATE ");
      localStringBuffer.append(localTableEntity.a());
      localStringBuffer.append(" SET ");
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        KeyValue localKeyValue = (KeyValue)localIterator.next();
        if ((localHashSet1 != null) && (!localHashSet1.contains(localKeyValue.a())))
          continue;
        localStringBuffer.append(localKeyValue.a()).append("=?,");
        localSqlInfo.b(localKeyValue.b());
      }
      localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
      if ((paramWhereBuilder != null) && (paramWhereBuilder.a() > 0))
        localStringBuffer.append(" WHERE ").append(paramWhereBuilder.toString());
      localSqlInfo.a(localStringBuffer.toString());
      return localSqlInfo;
    }
  }

  public static SqlInfo a(EntityContext paramEntityContext, Class paramClass, Object paramObject, String[] paramArrayOfString)
  {
    List localList = g(paramClass, paramObject, paramEntityContext);
    if (localList.size() == 0)
      return null;
    HashSet localHashSet2;
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      localHashSet2 = new HashSet(paramArrayOfString.length);
      Collections.addAll(localHashSet2, paramArrayOfString);
    }
    for (HashSet localHashSet1 = localHashSet2; ; localHashSet1 = null)
    {
      TableEntity localTableEntity = TableEntity.a(paramClass, paramEntityContext);
      IdEntity localIdEntity = localTableEntity.b();
      Object localObject = localIdEntity.a(paramObject);
      if (localObject == null)
        throw new DBException("this entity[" + paramObject.getClass() + "]'s id value is null");
      SqlInfo localSqlInfo = new SqlInfo();
      StringBuffer localStringBuffer = new StringBuffer("UPDATE ");
      localStringBuffer.append(localTableEntity.a());
      localStringBuffer.append(" SET ");
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        KeyValue localKeyValue = (KeyValue)localIterator.next();
        if ((localHashSet1 != null) && (!localHashSet1.contains(localKeyValue.a())))
          continue;
        localStringBuffer.append(localKeyValue.a()).append("=?,");
        localSqlInfo.b(localKeyValue.b());
      }
      localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
      localStringBuffer.append(" WHERE ").append(WhereBuilder.create(localIdEntity.b(), "=", localObject));
      localSqlInfo.a(localStringBuffer.toString());
      return localSqlInfo;
    }
  }

  public static SqlInfo a(Class paramClass, EntityContext paramEntityContext)
  {
    TableEntity localTableEntity = TableEntity.a(paramClass, paramEntityContext);
    IdEntity localIdEntity = localTableEntity.b();
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("CREATE TABLE IF NOT EXISTS ");
    localStringBuffer.append(paramEntityContext.a());
    localStringBuffer.append(" ( ");
    if (localIdEntity.f())
      localStringBuffer.append("\"").append(localIdEntity.b()).append("\"  ").append("INTEGER PRIMARY KEY AUTOINCREMENT,");
    while (true)
    {
      Iterator localIterator = localTableEntity.a.values().iterator();
      while (localIterator.hasNext())
      {
        ColumnEntity localColumnEntity = (ColumnEntity)localIterator.next();
        localStringBuffer.append("\"").append(localColumnEntity.b()).append("\"  ");
        localStringBuffer.append(localColumnEntity.e());
        if (ColumnUtils.e(localColumnEntity.d()))
          localStringBuffer.append(" UNIQUE");
        if (!ColumnUtils.f(localColumnEntity.d()))
          localStringBuffer.append(" NOT NULL");
        localStringBuffer.append(",");
      }
      localStringBuffer.append("\"").append(localIdEntity.b()).append("\"  ").append(localIdEntity.e()).append(" PRIMARY KEY,");
    }
    if (localTableEntity.d())
    {
      localStringBuffer.append("\"").append("_reserved_dynamic_class").append("\"  ");
      localStringBuffer.append(" TEXT,");
    }
    localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
    localStringBuffer.append(" )");
    return new SqlInfo(localStringBuffer.toString());
  }

  public static SqlInfo a(Class paramClass, WhereBuilder paramWhereBuilder, EntityContext paramEntityContext)
  {
    StringBuilder localStringBuilder = new StringBuilder(a(TableEntity.a(paramClass, paramEntityContext).a()));
    if ((paramWhereBuilder != null) && (paramWhereBuilder.a() > 0))
      localStringBuilder.append(" WHERE ").append(paramWhereBuilder.toString());
    return new SqlInfo(localStringBuilder.toString());
  }

  public static SqlInfo a(Class paramClass, Object paramObject, EntityContext paramEntityContext)
  {
    List localList = f(paramClass, paramObject, paramEntityContext);
    if (localList.size() == 0)
      return null;
    SqlInfo localSqlInfo = new SqlInfo();
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("INSERT INTO ");
    localStringBuffer.append(TableEntity.a(paramClass, paramEntityContext).a());
    localStringBuffer.append(" (");
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      KeyValue localKeyValue = (KeyValue)localIterator.next();
      localStringBuffer.append(localKeyValue.a()).append(",");
      localSqlInfo.b(localKeyValue.b());
    }
    localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
    localStringBuffer.append(") VALUES (");
    int i = localList.size();
    for (int j = 0; j < i; j++)
      localStringBuffer.append("?,");
    localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
    localStringBuffer.append(")");
    localSqlInfo.a(localStringBuffer.toString());
    return localSqlInfo;
  }

  private static KeyValue a(Object paramObject, ColumnEntity paramColumnEntity)
  {
    String str = paramColumnEntity.b();
    Object localObject = paramColumnEntity.a(paramObject);
    if (localObject == null)
      localObject = paramColumnEntity.c();
    KeyValue localKeyValue = null;
    if (str != null)
      localKeyValue = new KeyValue(str, localObject);
    return localKeyValue;
  }

  private static String a(String paramString)
  {
    return "DELETE FROM " + paramString;
  }

  public static SqlInfo b(Class paramClass, Object paramObject, EntityContext paramEntityContext)
  {
    List localList = e(paramClass, paramObject, paramEntityContext);
    if (localList.size() == 0)
      return null;
    SqlInfo localSqlInfo = new SqlInfo();
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("REPLACE INTO ");
    localStringBuffer.append(TableEntity.a(paramClass, paramEntityContext).a());
    localStringBuffer.append(" (");
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      KeyValue localKeyValue = (KeyValue)localIterator.next();
      localStringBuffer.append(localKeyValue.a()).append(",");
      localSqlInfo.b(localKeyValue.b());
    }
    localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
    localStringBuffer.append(") VALUES (");
    int i = localList.size();
    for (int j = 0; j < i; j++)
      localStringBuffer.append("?,");
    localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
    localStringBuffer.append(")");
    localSqlInfo.a(localStringBuffer.toString());
    return localSqlInfo;
  }

  public static SqlInfo c(Class paramClass, Object paramObject, EntityContext paramEntityContext)
  {
    SqlInfo localSqlInfo = new SqlInfo();
    TableEntity localTableEntity = TableEntity.a(paramClass, paramEntityContext);
    IdEntity localIdEntity = localTableEntity.b();
    Object localObject = localIdEntity.a(paramObject);
    if (localObject == null)
      throw new DBException("this entity[" + paramObject.getClass() + "]'s id value is null");
    StringBuilder localStringBuilder = new StringBuilder(a(localTableEntity.a()));
    localStringBuilder.append(" WHERE ").append(WhereBuilder.create(localIdEntity.b(), "=", localObject));
    localSqlInfo.a(localStringBuilder.toString());
    return localSqlInfo;
  }

  public static SqlInfo d(Class paramClass, Object paramObject, EntityContext paramEntityContext)
  {
    SqlInfo localSqlInfo = new SqlInfo();
    TableEntity localTableEntity = TableEntity.a(paramClass, paramEntityContext);
    IdEntity localIdEntity = localTableEntity.b();
    StringBuilder localStringBuilder = new StringBuilder(a(localTableEntity.a()));
    if (paramObject != null)
      localStringBuilder.append(" WHERE ").append(WhereBuilder.create(localIdEntity.b(), "=", paramObject));
    localSqlInfo.a(localStringBuilder.toString());
    return localSqlInfo;
  }

  public static List e(Class paramClass, Object paramObject, EntityContext paramEntityContext)
  {
    ArrayList localArrayList = new ArrayList();
    TableEntity localTableEntity = TableEntity.a(paramClass, paramEntityContext);
    IdEntity localIdEntity = localTableEntity.b();
    Object localObject;
    if (!localIdEntity.f())
      if (localIdEntity.g())
      {
        localObject = UUID.randomUUID().toString();
        localArrayList.add(new KeyValue(localIdEntity.b(), localObject));
      }
    while (true)
    {
      label67: if (localTableEntity.d())
        localArrayList.add(new KeyValue("_reserved_dynamic_class", paramObject.getClass().getName()));
      Iterator localIterator = localTableEntity.a.values().iterator();
      while (true)
        if (localIterator.hasNext())
        {
          KeyValue localKeyValue = a(paramObject, (ColumnEntity)localIterator.next());
          if (localKeyValue == null)
            continue;
          localArrayList.add(localKeyValue);
          continue;
          localObject = localIdEntity.a(paramObject);
          break;
          try
          {
            long l2 = ((Number)localIdEntity.a(paramObject)).longValue();
            l1 = l2;
            if (l1 <= 0L)
              break label67;
            localArrayList.add(new KeyValue(localIdEntity.b(), Long.valueOf(l1)));
          }
          catch (Exception localException)
          {
            while (true)
              long l1 = 0L;
          }
        }
    }
    return (List)localArrayList;
  }

  public static List f(Class paramClass, Object paramObject, EntityContext paramEntityContext)
  {
    ArrayList localArrayList = new ArrayList();
    TableEntity localTableEntity = TableEntity.a(paramClass, paramEntityContext);
    IdEntity localIdEntity = localTableEntity.b();
    if (!localIdEntity.f())
      if (!localIdEntity.g())
        break label156;
    label156: for (Object localObject = UUID.randomUUID().toString(); ; localObject = localIdEntity.a(paramObject))
    {
      localArrayList.add(new KeyValue(localIdEntity.b(), localObject));
      if (localTableEntity.d())
        localArrayList.add(new KeyValue("_reserved_dynamic_class", paramObject.getClass().getName()));
      Iterator localIterator = localTableEntity.a.values().iterator();
      while (localIterator.hasNext())
      {
        KeyValue localKeyValue = a(paramObject, (ColumnEntity)localIterator.next());
        if (localKeyValue == null)
          continue;
        localArrayList.add(localKeyValue);
      }
    }
    return (List)localArrayList;
  }

  public static List g(Class paramClass, Object paramObject, EntityContext paramEntityContext)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = TableEntity.a(paramClass, paramEntityContext).a.values().iterator();
    while (localIterator.hasNext())
    {
      KeyValue localKeyValue = a(paramObject, (ColumnEntity)localIterator.next());
      if (localKeyValue == null)
        continue;
      localArrayList.add(localKeyValue);
    }
    return localArrayList;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.sqlite.SqlInfoBuilder
 * JD-Core Version:    0.6.0
 */
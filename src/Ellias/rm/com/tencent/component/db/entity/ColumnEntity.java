package com.tencent.component.db.entity;

import android.database.Cursor;
import com.tencent.component.db.ColumnValueProcessor;
import com.tencent.component.db.converter.ColumnConverter;
import com.tencent.component.db.converter.ColumnConverterFactory;
import com.tencent.component.db.util.ColumnUtils;
import com.tencent.component.utils.log.LogUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ColumnEntity
{
  public static final String a = "_reserved_dynamic_class";
  private static final String g = "ColumnEntity";
  protected final String b;
  protected final Method c;
  protected final Method d;
  protected final Field e;
  protected final ColumnConverter f;
  private final Object h;
  private final boolean i;
  private final ColumnValueProcessor j;
  private final Class k;

  public ColumnEntity(Class paramClass, Field paramField, ColumnValueProcessor paramColumnValueProcessor)
  {
    this.e = paramField;
    this.f = ColumnConverterFactory.a(paramField.getType());
    this.b = ColumnUtils.a(paramField);
    this.j = paramColumnValueProcessor;
    this.k = paramClass;
    if (this.f != null);
    for (this.h = this.f.b(ColumnUtils.b(paramField)); ; this.h = null)
    {
      this.i = ColumnUtils.c(paramClass, paramField);
      this.c = ColumnUtils.a(paramClass, paramField);
      this.d = ColumnUtils.b(paramClass, paramField);
      return;
    }
  }

  public Object a(Object paramObject)
  {
    Object localObject1 = b(paramObject);
    Object localObject2 = this.f.a(localObject1);
    if (this.j != null)
      localObject2 = this.j.b(localObject2, this.k, this.b);
    return localObject2;
  }

  public void a(Object paramObject, Cursor paramCursor, int paramInt, ClassLoader paramClassLoader)
  {
    Object localObject1 = this.f.b(paramCursor, paramInt);
    if (this.j != null)
      localObject1 = this.j.a(localObject1, this.k, this.b);
    Object localObject2 = this.f.a(localObject1, paramClassLoader);
    if (this.d != null)
      try
      {
        Method localMethod = this.d;
        Object[] arrayOfObject = new Object[1];
        if (localObject2 == null)
          localObject2 = this.h;
        arrayOfObject[0] = localObject2;
        localMethod.invoke(paramObject, arrayOfObject);
        return;
      }
      catch (Throwable localThrowable2)
      {
        LogUtil.e("ColumnEntity", localThrowable2.getMessage(), localThrowable2);
        return;
      }
    try
    {
      this.e.setAccessible(true);
      Field localField = this.e;
      if (localObject2 == null)
        localObject2 = this.h;
      localField.set(paramObject, localObject2);
      return;
    }
    catch (Throwable localThrowable1)
    {
      LogUtil.e("ColumnEntity", localThrowable1.getMessage(), localThrowable1);
    }
  }

  public boolean a()
  {
    return this.i;
  }

  public Object b(Object paramObject)
  {
    Object localObject1 = null;
    if ((paramObject == null) || (this.c != null))
      try
      {
        Object localObject3 = this.c.invoke(paramObject, new Object[0]);
        localObject1 = localObject3;
        return localObject1;
      }
      catch (Throwable localThrowable2)
      {
        LogUtil.e("ColumnEntity", localThrowable2.getMessage(), localThrowable2);
        return null;
      }
    try
    {
      this.e.setAccessible(true);
      Object localObject2 = this.e.get(paramObject);
      return localObject2;
    }
    catch (Throwable localThrowable1)
    {
      LogUtil.e("ColumnEntity", localThrowable1.getMessage(), localThrowable1);
    }
    return null;
  }

  public String b()
  {
    return this.b;
  }

  public Object c()
  {
    return this.h;
  }

  public Field d()
  {
    return this.e;
  }

  public String e()
  {
    if (this.f != null)
      return this.f.a();
    return "TEXT";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.entity.ColumnEntity
 * JD-Core Version:    0.6.0
 */
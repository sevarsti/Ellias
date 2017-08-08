package com.tencent.component.db.entity;

import com.tencent.component.db.annotation.Id;
import com.tencent.component.utils.log.LogUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class IdEntity extends ColumnEntity
{
  private static final String g = "IdEntity";

  public IdEntity(Class paramClass, Field paramField)
  {
    super(paramClass, paramField, null);
  }

  public Object a(Object paramObject)
  {
    Object localObject = super.a(paramObject);
    if ((localObject == null) || ((f()) && ((localObject.equals(Integer.valueOf(0))) || (localObject.equals(Long.valueOf(0L))))));
    do
      return null;
    while ((g()) && (localObject.equals("")));
    return localObject;
  }

  public void a(Object paramObject, long paramLong)
  {
    Object localObject = Long.valueOf(paramLong);
    Class localClass = this.e.getType();
    if ((localClass.equals(Integer.TYPE)) || (localClass.equals(Integer.class)))
      localObject = Integer.valueOf((int)paramLong);
    a(paramObject, localObject);
  }

  public void a(Object paramObject1, Object paramObject2)
  {
    if (this.d != null)
      try
      {
        this.d.invoke(paramObject1, new Object[] { paramObject2 });
        return;
      }
      catch (Throwable localThrowable2)
      {
        LogUtil.e("IdEntity", localThrowable2.getMessage(), localThrowable2);
        return;
      }
    try
    {
      this.e.setAccessible(true);
      this.e.set(paramObject1, paramObject2);
      return;
    }
    catch (Throwable localThrowable1)
    {
      LogUtil.e("IdEntity", localThrowable1.getMessage(), localThrowable1);
    }
  }

  public boolean f()
  {
    Id localId = (Id)d().getAnnotation(Id.class);
    if ((localId == null) || (localId.strategy() != 3))
      return false;
    Class localClass = d().getType();
    return (localClass.equals(Integer.TYPE)) || (localClass.equals(Integer.class)) || (localClass.equals(Long.TYPE)) || (localClass.equals(Long.class));
  }

  public boolean g()
  {
    Id localId = (Id)d().getAnnotation(Id.class);
    if ((localId == null) || (localId.strategy() != 2))
      return false;
    return d().getType().equals(String.class);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.entity.IdEntity
 * JD-Core Version:    0.6.0
 */
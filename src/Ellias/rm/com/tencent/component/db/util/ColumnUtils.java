package com.tencent.component.db.util;

import android.text.TextUtils;
import com.tencent.component.db.annotation.Column;
import com.tencent.component.db.annotation.Encrypt;
import com.tencent.component.db.annotation.Id;
import com.tencent.component.db.annotation.Transient;
import com.tencent.component.db.converter.ColumnConverter;
import com.tencent.component.db.converter.ColumnConverterFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ColumnUtils
{
  private static final String a = "ColumnUtils";
  private static final String b = "id";
  private static final String c = "_id";

  public static Object a(Object paramObject)
  {
    if (paramObject != null)
    {
      ColumnConverter localColumnConverter = ColumnConverterFactory.a(paramObject.getClass());
      if (localColumnConverter != null)
        paramObject = localColumnConverter.a(paramObject);
    }
    return paramObject;
  }

  private static String a(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && ("id".equalsIgnoreCase(paramString)))
      paramString = "_id";
    return paramString;
  }

  public static String a(Field paramField)
  {
    Column localColumn = (Column)paramField.getAnnotation(Column.class);
    if ((localColumn != null) && (!TextUtils.isEmpty(localColumn.name())))
      return a(localColumn.name());
    Id localId = (Id)paramField.getAnnotation(Id.class);
    if ((localId != null) && (!TextUtils.isEmpty(localId.name())))
      return a(localId.name());
    return a(paramField.getName());
  }

  private static Method a(Class paramClass, String paramString)
  {
    String str = "is" + paramString.substring(0, 1).toUpperCase() + paramString.substring(1);
    if (b(paramString));
    while (true)
    {
      try
      {
        Method localMethod = paramClass.getDeclaredMethod(paramString, new Class[0]);
        return localMethod;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        return null;
      }
      paramString = str;
    }
  }

  public static Method a(Class paramClass, Field paramField)
  {
    String str1 = paramField.getName();
    Class localClass1 = paramField.getType();
    Class localClass2 = Boolean.TYPE;
    Object localObject = null;
    if (localClass1 == localClass2)
      localObject = a(paramClass, str1);
    String str2;
    if (localObject == null)
      str2 = "get" + str1.substring(0, 1).toUpperCase() + str1.substring(1);
    try
    {
      Method localMethod = paramClass.getDeclaredMethod(str2, new Class[0]);
      localObject = localMethod;
      label89: if ((localObject == null) && (!Object.class.equals(paramClass.getSuperclass())))
        localObject = a(paramClass.getSuperclass(), paramField);
      return localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      break label89;
    }
  }

  public static String b(Field paramField)
  {
    Column localColumn = (Column)paramField.getAnnotation(Column.class);
    if ((localColumn != null) && (!TextUtils.isEmpty(localColumn.defaultValue())))
      return localColumn.defaultValue();
    return null;
  }

  public static Method b(Class paramClass, Field paramField)
  {
    String str1 = paramField.getName();
    Class localClass1 = paramField.getType();
    Class localClass2 = Boolean.TYPE;
    Object localObject = null;
    if (localClass1 == localClass2)
      localObject = d(paramClass, paramField);
    String str2;
    if (localObject == null)
      str2 = "set" + str1.substring(0, 1).toUpperCase() + str1.substring(1);
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = paramField.getType();
      Method localMethod = paramClass.getDeclaredMethod(str2, arrayOfClass);
      localObject = localMethod;
      label101: if ((localObject == null) && (!Object.class.equals(paramClass.getSuperclass())))
        localObject = b(paramClass.getSuperclass(), paramField);
      return localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      break label101;
    }
  }

  private static boolean b(String paramString)
  {
    return (paramString != null) && (paramString.startsWith("is"));
  }

  public static boolean c(Class paramClass, Field paramField)
  {
    Encrypt localEncrypt = (Encrypt)paramField.getAnnotation(Encrypt.class);
    if (localEncrypt == null)
      localEncrypt = (Encrypt)paramField.getAnnotation(Encrypt.class);
    return localEncrypt != null;
  }

  public static boolean c(Field paramField)
  {
    return paramField.getAnnotation(Transient.class) != null;
  }

  private static Method d(Class paramClass, Field paramField)
  {
    String str1 = paramField.getName();
    String str2;
    if (b(paramField.getName()))
      str2 = "set" + str1.substring(2, 3).toUpperCase() + str1.substring(3);
    try
    {
      while (true)
      {
        Class[] arrayOfClass = new Class[1];
        arrayOfClass[0] = paramField.getType();
        Method localMethod = paramClass.getDeclaredMethod(str2, arrayOfClass);
        return localMethod;
        str2 = "set" + str1.substring(0, 1).toUpperCase() + str1.substring(1);
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
    }
    return null;
  }

  public static boolean d(Field paramField)
  {
    return paramField.getAnnotation(Column.class) != null;
  }

  public static boolean e(Field paramField)
  {
    Column localColumn = (Column)paramField.getAnnotation(Column.class);
    if (localColumn != null)
      return localColumn.unique();
    return false;
  }

  public static boolean f(Field paramField)
  {
    Column localColumn = (Column)paramField.getAnnotation(Column.class);
    if (localColumn != null)
      return localColumn.c();
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.util.ColumnUtils
 * JD-Core Version:    0.6.0
 */
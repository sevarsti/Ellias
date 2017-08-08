package com.qq.jce.wup;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BasicClassTypeUtil
{
  private static ClassLoader mClassLoader = null;
  private static boolean mClassLoaderInitialize = true;

  private static void addType(ArrayList<String> paramArrayList, String paramString)
  {
    int i = paramString.length();
    do
    {
      if (paramString.charAt(i - 1) != '>')
        break;
      i--;
    }
    while (i != 0);
    paramArrayList.add(0, uni2JavaType(paramString.substring(0, i)));
  }

  public static Object createClassByName(String paramString, boolean paramBoolean, ClassLoader paramClassLoader)
    throws ObjectCreateException
  {
    if (paramString.equals("java.lang.Integer"))
      paramString = Integer.valueOf(0);
    do
    {
      return paramString;
      if (paramString.equals("java.lang.Boolean"))
        return Boolean.valueOf(false);
      if (paramString.equals("java.lang.Byte"))
        return Byte.valueOf(0);
      if (paramString.equals("java.lang.Double"))
        return Double.valueOf(0.0D);
      if (paramString.equals("java.lang.Float"))
        return Float.valueOf(0.0F);
      if (paramString.equals("java.lang.Long"))
        return Long.valueOf(0L);
      if (paramString.equals("java.lang.Short"))
        return Short.valueOf(0);
      if (paramString.equals("java.lang.Character"))
        throw new IllegalArgumentException("can not support java.lang.Character");
      if (paramString.equals("java.lang.String"))
        return "";
      if (paramString.equals("java.util.List"))
        return new ArrayList();
      if (paramString.equals("java.util.Map"))
        return new HashMap();
      if (paramString.equals("Array"))
        return "Array";
    }
    while (paramString.equals("?"));
    if (paramClassLoader != null);
    try
    {
      Object localObject = Class.forName(paramString, paramBoolean, paramClassLoader);
      while (true)
      {
        return ((Class)localObject).getConstructor(new Class[0]).newInstance(new Object[0]);
        if (mClassLoader != null)
        {
          localObject = Class.forName(paramString, mClassLoaderInitialize, mClassLoader);
          continue;
        }
        Class localClass = Class.forName(paramString);
        localObject = localClass;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    throw new ObjectCreateException(localException);
  }

  public static Object createClassByUni(String paramString, boolean paramBoolean, ClassLoader paramClassLoader)
    throws ObjectCreateException
  {
    ArrayList localArrayList = getTypeList(paramString);
    Object localObject1 = null;
    Iterator localIterator = localArrayList.iterator();
    Object localObject2 = null;
    Object localObject3 = null;
    Object localObject4;
    if (localIterator.hasNext())
    {
      localObject1 = createClassByName((String)localIterator.next(), paramBoolean, paramClassLoader);
      if ((localObject1 instanceof String))
        if ("Array".equals((String)(String)localObject1))
        {
          if (localObject3 != null)
            break label323;
          localObject1 = Array.newInstance(Byte.class, 0);
          localObject4 = localObject3;
          localObject3 = localObject2;
        }
    }
    while (true)
    {
      localObject2 = localObject3;
      localObject3 = localObject4;
      break;
      if ("?".equals((String)(String)localObject1))
      {
        localObject4 = localObject3;
        localObject3 = localObject2;
        continue;
      }
      if (localObject3 == null)
      {
        localObject4 = localObject1;
        localObject3 = localObject2;
        continue;
      }
      localObject4 = localObject1;
      continue;
      if ((localObject1 instanceof List))
      {
        if ((localObject3 != null) && ((localObject3 instanceof Byte)))
        {
          localObject1 = Array.newInstance(Byte.class, 1);
          Array.set(localObject1, 0, localObject3);
          localObject4 = localObject3;
          localObject3 = localObject2;
          continue;
        }
        if (localObject3 != null)
          ((List)localObject1).add(localObject3);
        localObject3 = localObject2;
        localObject4 = null;
        continue;
      }
      if ((localObject1 instanceof Map))
      {
        int i;
        if (localObject3 != null)
        {
          i = 1;
          label245: if (localObject2 == null)
            break label291;
        }
        label291: for (int j = 1; ; j = 0)
        {
          if ((j & i) != 0)
            ((Map)localObject1).put(localObject3, localObject2);
          localObject4 = null;
          localObject3 = null;
          break;
          i = 0;
          break label245;
        }
      }
      if (localObject3 == null)
      {
        localObject4 = localObject1;
        localObject3 = localObject2;
        continue;
      }
      localObject4 = localObject1;
      continue;
      return localObject1;
      label323: localObject4 = localObject3;
      localObject3 = localObject2;
    }
  }

  public static String getClassTransName(String paramString)
  {
    if (paramString.equals("int"))
      paramString = "Integer";
    do
    {
      return paramString;
      if (paramString.equals("boolean"))
        return "Boolean";
      if (paramString.equals("byte"))
        return "Byte";
      if (paramString.equals("double"))
        return "Double";
      if (paramString.equals("float"))
        return "Float";
      if (paramString.equals("long"))
        return "Long";
      if (paramString.equals("short"))
        return "Short";
    }
    while (!paramString.equals("char"));
    return "Character";
  }

  public static ArrayList<String> getTypeList(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    label17: int k;
    for (int j = paramString.indexOf("<"); i < j; j = k)
    {
      addType(localArrayList, paramString.substring(i, j));
      i = j + 1;
      j = paramString.indexOf("<", i);
      k = paramString.indexOf(",", i);
      if (j == -1)
        j = k;
      if ((k == -1) || (k >= j))
        break label17;
    }
    addType(localArrayList, paramString.substring(i, paramString.length()));
    return localArrayList;
  }

  public static String getVariableInit(String paramString1, String paramString2)
  {
    if (paramString2.equals("int"))
      return paramString2 + " " + paramString1 + "=0 ;\n";
    if (paramString2.equals("boolean"))
      return paramString2 + " " + paramString1 + "=false ;\n";
    if (paramString2.equals("byte"))
      return paramString2 + " " + paramString1 + " ;\n";
    if (paramString2.equals("double"))
      return paramString2 + " " + paramString1 + "=0 ;\n";
    if (paramString2.equals("float"))
      return paramString2 + " " + paramString1 + "=0 ;\n";
    if (paramString2.equals("long"))
      return paramString2 + " " + paramString1 + "=0 ;\n";
    if (paramString2.equals("short"))
      return paramString2 + " " + paramString1 + "=0 ;\n";
    if (paramString2.equals("char"))
      return paramString2 + " " + paramString1 + " ;\n";
    return paramString2 + " " + paramString1 + " = null ;\n";
  }

  public static boolean isBasicType(String paramString)
  {
    if (paramString.equals("int"));
    do
      return true;
    while ((paramString.equals("boolean")) || (paramString.equals("byte")) || (paramString.equals("double")) || (paramString.equals("float")) || (paramString.equals("long")) || (paramString.equals("short")) || (paramString.equals("char")) || (paramString.equals("Integer")) || (paramString.equals("Boolean")) || (paramString.equals("Byte")) || (paramString.equals("Double")) || (paramString.equals("Float")) || (paramString.equals("Long")) || (paramString.equals("Short")) || (paramString.equals("Char")));
    return false;
  }

  public static String java2UniType(String paramString)
  {
    if ((paramString.equals("java.lang.Integer")) || (paramString.equals("int")))
      paramString = "int32";
    do
    {
      return paramString;
      if ((paramString.equals("java.lang.Boolean")) || (paramString.equals("boolean")))
        return "bool";
      if ((paramString.equals("java.lang.Byte")) || (paramString.equals("byte")))
        return "char";
      if ((paramString.equals("java.lang.Double")) || (paramString.equals("double")))
        return "double";
      if ((paramString.equals("java.lang.Float")) || (paramString.equals("float")))
        return "float";
      if ((paramString.equals("java.lang.Long")) || (paramString.equals("long")))
        return "int64";
      if ((paramString.equals("java.lang.Short")) || (paramString.equals("short")))
        return "short";
      if (paramString.equals("java.lang.Character"))
        throw new IllegalArgumentException("can not support java.lang.Character");
      if (paramString.equals("java.lang.String"))
        return "string";
      if (paramString.equals("java.util.List"))
        return "list";
    }
    while (!paramString.equals("java.util.Map"));
    return "map";
  }

  public static void setClassLoader(boolean paramBoolean, ClassLoader paramClassLoader)
  {
    mClassLoaderInitialize = paramBoolean;
    mClassLoader = paramClassLoader;
  }

  public static String transTypeList(ArrayList<String> paramArrayList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayList.size(); i++)
      paramArrayList.set(i, java2UniType((String)paramArrayList.get(i)));
    Collections.reverse(paramArrayList);
    int j = 0;
    if (j < paramArrayList.size())
    {
      String str = (String)paramArrayList.get(j);
      if (str.equals("list"))
      {
        paramArrayList.set(j - 1, "<" + (String)paramArrayList.get(j - 1));
        paramArrayList.set(0, (String)paramArrayList.get(0) + ">");
      }
      while (true)
      {
        j++;
        break;
        if (str.equals("map"))
        {
          paramArrayList.set(j - 1, "<" + (String)paramArrayList.get(j - 1) + ",");
          paramArrayList.set(0, (String)paramArrayList.get(0) + ">");
          continue;
        }
        if (!str.equals("Array"))
          continue;
        paramArrayList.set(j - 1, "<" + (String)paramArrayList.get(j - 1));
        paramArrayList.set(0, (String)paramArrayList.get(0) + ">");
      }
    }
    Collections.reverse(paramArrayList);
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
      localStringBuffer.append((String)localIterator.next());
    return localStringBuffer.toString();
  }

  public static String uni2JavaType(String paramString)
  {
    if (paramString.equals("int32"))
      paramString = "java.lang.Integer";
    do
    {
      return paramString;
      if (paramString.equals("bool"))
        return "java.lang.Boolean";
      if (paramString.equals("char"))
        return "java.lang.Byte";
      if (paramString.equals("double"))
        return "java.lang.Double";
      if (paramString.equals("float"))
        return "java.lang.Float";
      if (paramString.equals("int64"))
        return "java.lang.Long";
      if (paramString.equals("short"))
        return "java.lang.Short";
      if (paramString.equals("string"))
        return "java.lang.String";
      if (paramString.equals("list"))
        return "java.util.List";
    }
    while (!paramString.equals("map"));
    return "java.util.Map";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.qq.jce.wup.BasicClassTypeUtil
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.db.sqlite;

import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.db.converter.ColumnConverterFactory;
import com.tencent.component.db.util.ColumnUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@PluginApi(a=4)
public class WhereBuilder
{
  private final List a = new ArrayList();

  private void a(String paramString1, String paramString2, String paramString3, Object paramObject)
  {
    int i = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.a.size() > 0)
      localStringBuilder.append(" ");
    if (!TextUtils.isEmpty(paramString1))
      localStringBuilder.append(paramString1 + " ");
    localStringBuilder.append(paramString2);
    if ("!=".equals(paramString3))
    {
      paramString3 = "<>";
      if (paramObject != null)
        break label189;
      if (!"=".equals(paramString3))
        break label136;
      localStringBuilder.append(" IS NULL");
    }
    while (true)
    {
      this.a.add(localStringBuilder.toString());
      return;
      if (!"==".equals(paramString3))
        break;
      paramString3 = "=";
      break;
      label136: if ("<>".equals(paramString3))
      {
        localStringBuilder.append(" IS NOT NULL");
        continue;
      }
      localStringBuilder.append(" " + paramString3 + " NULL");
    }
    label189: localStringBuilder.append(" " + paramString3 + " ");
    Object localObject7;
    if ("IN".equalsIgnoreCase(paramString3))
      if ((paramObject instanceof Iterable))
        localObject7 = (Iterable)paramObject;
    while (true)
    {
      if (localObject7 != null)
      {
        StringBuffer localStringBuffer = new StringBuffer("(");
        Iterator localIterator2 = ((Iterable)localObject7).iterator();
        label268: if (localIterator2.hasNext())
        {
          Object localObject8 = ColumnUtils.a(localIterator2.next());
          if ("TEXT".equals(ColumnConverterFactory.b(localObject8.getClass())))
          {
            String str4 = localObject8.toString();
            if (str4.indexOf('\'') != -1)
              str4 = str4.replace("'", "''");
            localStringBuffer.append("'" + str4 + "'");
          }
          while (true)
          {
            localStringBuffer.append(",");
            break label268;
            if (!paramObject.getClass().isArray())
              break label950;
            ArrayList localArrayList2 = new ArrayList();
            int k = Array.getLength(paramObject);
            while (i < k)
            {
              localArrayList2.add(Array.get(paramObject, i));
              i++;
            }
            localObject7 = localArrayList2;
            break;
            localStringBuffer.append(localObject8);
          }
        }
        localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
        localStringBuffer.append(")");
        localStringBuilder.append(localStringBuffer.toString());
        break;
      }
      throw new IllegalArgumentException("value must be an Array or an Iterable.");
      Object localObject2;
      if ("BETWEEN".equalsIgnoreCase(paramString3))
        if ((paramObject instanceof Iterable))
          localObject2 = (Iterable)paramObject;
      while (true)
      {
        if (localObject2 != null)
        {
          Iterator localIterator1 = ((Iterable)localObject2).iterator();
          if (!localIterator1.hasNext())
          {
            throw new IllegalArgumentException("value must have tow items.");
            if (paramObject.getClass().isArray())
            {
              ArrayList localArrayList1 = new ArrayList();
              int j = Array.getLength(paramObject);
              while (i < j)
              {
                localArrayList1.add(Array.get(paramObject, i));
                i++;
              }
              localObject2 = localArrayList1;
              continue;
            }
          }
          else
          {
            Object localObject3 = localIterator1.next();
            if (!localIterator1.hasNext())
              throw new IllegalArgumentException("value must have tow items.");
            Object localObject4 = localIterator1.next();
            Object localObject5 = ColumnUtils.a(localObject3);
            Object localObject6 = ColumnUtils.a(localObject4);
            if ("TEXT".equals(ColumnConverterFactory.b(localObject5.getClass())))
            {
              String str2 = localObject5.toString();
              if (str2.indexOf('\'') != -1)
                str2 = str2.replace("'", "''");
              String str3 = localObject6.toString();
              if (str3.indexOf('\'') != -1)
                str3 = str3.replace("'", "''");
              localStringBuilder.append("'" + str2 + "'");
              localStringBuilder.append(" AND ");
              localStringBuilder.append("'" + str3 + "'");
              break;
            }
            localStringBuilder.append(localObject5);
            localStringBuilder.append(" AND ");
            localStringBuilder.append(localObject6);
            break;
          }
        }
        else
        {
          throw new IllegalArgumentException("value must be an Array or an Iterable.");
          Object localObject1 = ColumnUtils.a(paramObject);
          if ("TEXT".equals(ColumnConverterFactory.b(localObject1.getClass())))
          {
            String str1 = localObject1.toString();
            if (str1.indexOf('\'') != -1)
              str1 = str1.replace("'", "''");
            localStringBuilder.append("'" + str1 + "'");
            break;
          }
          localStringBuilder.append(localObject1);
          break;
        }
        localObject2 = null;
      }
      label950: localObject7 = null;
    }
  }

  @PluginApi(a=4)
  public static WhereBuilder create()
  {
    return new WhereBuilder();
  }

  @PluginApi(a=4)
  public static WhereBuilder create(String paramString1, String paramString2, Object paramObject)
  {
    WhereBuilder localWhereBuilder = new WhereBuilder();
    localWhereBuilder.a(null, paramString1, paramString2, paramObject);
    return localWhereBuilder;
  }

  public int a()
  {
    return this.a.size();
  }

  @PluginApi(a=4)
  public WhereBuilder and(String paramString1, String paramString2, Object paramObject)
  {
    if (this.a.size() == 0);
    for (String str = null; ; str = "AND")
    {
      a(str, paramString1, paramString2, paramObject);
      return this;
    }
  }

  @PluginApi(a=4)
  public WhereBuilder expr(String paramString)
  {
    this.a.add(" " + paramString);
    return this;
  }

  @PluginApi(a=4)
  public WhereBuilder expr(String paramString1, String paramString2, Object paramObject)
  {
    a(null, paramString1, paramString2, paramObject);
    return this;
  }

  @PluginApi(a=4)
  public WhereBuilder or(String paramString1, String paramString2, Object paramObject)
  {
    if (this.a.size() == 0);
    for (String str = null; ; str = "OR")
    {
      a(str, paramString1, paramString2, paramObject);
      return this;
    }
  }

  public String toString()
  {
    if (this.a.size() < 1)
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext())
      localStringBuilder.append((String)localIterator.next());
    return localStringBuilder.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.sqlite.WhereBuilder
 * JD-Core Version:    0.6.0
 */
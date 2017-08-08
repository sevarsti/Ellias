package com.tencent.component.net.http;

import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.MiscUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@PluginApi(a=8)
public class FluentCaseInsensitiveStringsMap
  implements Iterable, Map
{
  private final Map a = new LinkedHashMap();
  private final Map b = new LinkedHashMap();

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap()
  {
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap(FluentCaseInsensitiveStringsMap paramFluentCaseInsensitiveStringsMap)
  {
    if (paramFluentCaseInsensitiveStringsMap != null)
    {
      Iterator localIterator = paramFluentCaseInsensitiveStringsMap.iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        add((String)localEntry.getKey(), (Collection)localEntry.getValue());
      }
    }
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap(Map paramMap)
  {
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        add((String)localEntry.getKey(), (Collection)localEntry.getValue());
      }
    }
  }

  private List a(Collection paramCollection)
  {
    Object localObject1 = null;
    String str1;
    if (paramCollection != null)
    {
      Iterator localIterator = paramCollection.iterator();
      if (localIterator.hasNext())
      {
        str1 = (String)localIterator.next();
        if (str1 != null)
          break label79;
      }
    }
    label79: for (String str2 = ""; ; str2 = str1)
    {
      if (localObject1 == null);
      for (Object localObject2 = new ArrayList(); ; localObject2 = localObject1)
      {
        ((List)localObject2).add(str2);
        localObject1 = localObject2;
        break;
        return localObject1;
      }
    }
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap add(String paramString, Collection paramCollection)
  {
    List localList1;
    String str2;
    if (paramString != null)
    {
      localList1 = a(paramCollection);
      if (localList1 != null)
      {
        String str1 = paramString.toLowerCase(Locale.ENGLISH);
        str2 = (String)this.b.get(str1);
        if (str2 != null)
          break label98;
        this.b.put(str1, paramString);
      }
    }
    label98: List localList2;
    for (Object localObject = null; ; localObject = localList2)
    {
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.a.put(paramString, localObject);
      }
      ((List)localObject).addAll(localList1);
      return this;
      localList2 = (List)this.a.get(str2);
      paramString = str2;
    }
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap add(String paramString, String[] paramArrayOfString)
  {
    if (MiscUtil.a(paramArrayOfString))
      add(paramString, Arrays.asList(paramArrayOfString));
    return this;
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap addAll(FluentCaseInsensitiveStringsMap paramFluentCaseInsensitiveStringsMap)
  {
    if (paramFluentCaseInsensitiveStringsMap != null)
    {
      Iterator localIterator = paramFluentCaseInsensitiveStringsMap.iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        add((String)localEntry.getKey(), (Collection)localEntry.getValue());
      }
    }
    return this;
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap addAll(Map paramMap)
  {
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        add((String)localEntry.getKey(), (Collection)localEntry.getValue());
      }
    }
    return this;
  }

  @PluginApi(a=8)
  public void clear()
  {
    this.b.clear();
    this.a.clear();
  }

  @PluginApi(a=8)
  public boolean containsKey(Object paramObject)
  {
    if (paramObject == null)
      return false;
    return this.b.containsKey(paramObject.toString().toLowerCase(Locale.ENGLISH));
  }

  @PluginApi(a=8)
  public boolean containsValue(Object paramObject)
  {
    return this.a.containsValue(paramObject);
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap delete(String paramString)
  {
    if (paramString != null)
    {
      String str1 = paramString.toLowerCase(Locale.ENGLISH);
      String str2 = (String)this.b.remove(str1);
      if (str2 != null)
        this.a.remove(str2);
    }
    return this;
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap deleteAll(Collection paramCollection)
  {
    if (paramCollection != null)
    {
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
        remove((String)localIterator.next());
    }
    return this;
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap deleteAll(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null)
    {
      int i = paramArrayOfString.length;
      for (int j = 0; j < i; j++)
        remove(paramArrayOfString[j]);
    }
    return this;
  }

  @PluginApi(a=8)
  public Set entrySet()
  {
    return this.a.entrySet();
  }

  @PluginApi(a=8)
  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    FluentCaseInsensitiveStringsMap localFluentCaseInsensitiveStringsMap;
    do
      while (true)
      {
        return true;
        if (paramObject == null)
          return false;
        if (getClass() != paramObject.getClass())
          return false;
        localFluentCaseInsensitiveStringsMap = (FluentCaseInsensitiveStringsMap)paramObject;
        if (this.a != null)
          break;
        if (localFluentCaseInsensitiveStringsMap.a != null)
          return false;
      }
    while (this.a.equals(localFluentCaseInsensitiveStringsMap.a));
    return false;
  }

  @PluginApi(a=8)
  public List get(Object paramObject)
  {
    if (paramObject == null)
      return null;
    String str1 = paramObject.toString().toLowerCase(Locale.ENGLISH);
    String str2 = (String)this.b.get(str1);
    if (str2 == null)
      return null;
    return (List)this.a.get(str2);
  }

  @PluginApi(a=8)
  public String getFirstValue(String paramString)
  {
    List localList = get(paramString);
    if (localList == null)
      return null;
    if (localList.isEmpty())
      return "";
    return (String)localList.get(0);
  }

  @PluginApi(a=8)
  public String getJoinedValue(String paramString1, String paramString2)
  {
    List localList = get(paramString1);
    if (localList == null)
      return null;
    if (localList.size() == 1)
      return (String)localList.get(0);
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (localStringBuilder.length() > 0)
        localStringBuilder.append(paramString2);
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }

  public int hashCode()
  {
    if (this.a == null)
      return 0;
    return this.a.hashCode();
  }

  @PluginApi(a=8)
  public boolean isEmpty()
  {
    return this.a.isEmpty();
  }

  @PluginApi(a=8)
  public Iterator iterator()
  {
    return Collections.unmodifiableSet(this.a.entrySet()).iterator();
  }

  @PluginApi(a=8)
  public Set keySet()
  {
    return new LinkedHashSet(this.b.values());
  }

  @PluginApi(a=8)
  public List put(String paramString, List paramList)
  {
    if (paramString == null)
      throw new NullPointerException("Null keys are not allowed");
    List localList = get(paramString);
    replace(paramString, paramList);
    return localList;
  }

  @PluginApi(a=8)
  public void putAll(Map paramMap)
  {
    replaceAll(paramMap);
  }

  @PluginApi(a=8)
  public List remove(Object paramObject)
  {
    if (paramObject == null)
      return null;
    List localList = get(paramObject.toString());
    delete(paramObject.toString());
    return localList;
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap replace(String paramString, Collection paramCollection)
  {
    List localList;
    String str1;
    String str2;
    if (paramString != null)
    {
      localList = a(paramCollection);
      str1 = paramString.toLowerCase(Locale.ENGLISH);
      str2 = (String)this.b.get(str1);
      if (localList != null)
        break label70;
      this.b.remove(str1);
      if (str2 != null)
        this.a.remove(str2);
    }
    return this;
    label70: if (!paramString.equals(str2))
    {
      this.b.put(str1, paramString);
      this.a.remove(str2);
    }
    this.a.put(paramString, localList);
    return this;
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap replace(String paramString, String[] paramArrayOfString)
  {
    return replace(paramString, Arrays.asList(paramArrayOfString));
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap replaceAll(FluentCaseInsensitiveStringsMap paramFluentCaseInsensitiveStringsMap)
  {
    if (paramFluentCaseInsensitiveStringsMap != null)
    {
      Iterator localIterator = paramFluentCaseInsensitiveStringsMap.iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        replace((String)localEntry.getKey(), (Collection)localEntry.getValue());
      }
    }
    return this;
  }

  @PluginApi(a=8)
  public FluentCaseInsensitiveStringsMap replaceAll(Map paramMap)
  {
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        replace((String)localEntry.getKey(), (Collection)localEntry.getValue());
      }
    }
    return this;
  }

  @PluginApi(a=8)
  public int size()
  {
    return this.a.size();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator1 = this.a.entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator1.next();
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("; ");
      localStringBuilder.append("\"");
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append("=");
      int i = 0;
      Iterator localIterator2 = ((List)localEntry.getValue()).iterator();
      if (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        if (i != 0)
          localStringBuilder.append(", ");
        while (true)
        {
          localStringBuilder.append(str);
          break;
          i = 1;
        }
      }
      localStringBuilder.append("\"");
    }
    return localStringBuilder.toString();
  }

  @PluginApi(a=8)
  public Collection values()
  {
    return this.a.values();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.FluentCaseInsensitiveStringsMap
 * JD-Core Version:    0.6.0
 */
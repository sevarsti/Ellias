package org.apache.http.entity.mime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Header
  implements Iterable<MinimalField>
{
  private final Map<String, List<MinimalField>> fieldMap = new HashMap();
  private final List<MinimalField> fields = new LinkedList();

  public void addField(MinimalField paramMinimalField)
  {
    if (paramMinimalField == null)
      return;
    String str = paramMinimalField.getName().toLowerCase(Locale.US);
    Object localObject = (List)this.fieldMap.get(str);
    if (localObject == null)
    {
      localObject = new LinkedList();
      this.fieldMap.put(str, localObject);
    }
    ((List)localObject).add(paramMinimalField);
    this.fields.add(paramMinimalField);
  }

  public MinimalField getField(String paramString)
  {
    if (paramString == null);
    List localList;
    do
    {
      return null;
      String str = paramString.toLowerCase(Locale.US);
      localList = (List)this.fieldMap.get(str);
    }
    while ((localList == null) || (localList.isEmpty()));
    return (MinimalField)localList.get(0);
  }

  public List<MinimalField> getFields()
  {
    return new ArrayList(this.fields);
  }

  public List<MinimalField> getFields(String paramString)
  {
    if (paramString == null)
      return null;
    String str = paramString.toLowerCase(Locale.US);
    List localList = (List)this.fieldMap.get(str);
    if ((localList == null) || (localList.isEmpty()))
      return Collections.emptyList();
    return new ArrayList(localList);
  }

  public Iterator<MinimalField> iterator()
  {
    return Collections.unmodifiableList(this.fields).iterator();
  }

  public int removeFields(String paramString)
  {
    if (paramString == null);
    List localList;
    do
    {
      return 0;
      String str = paramString.toLowerCase(Locale.US);
      localList = (List)this.fieldMap.remove(str);
    }
    while ((localList == null) || (localList.isEmpty()));
    this.fields.removeAll(localList);
    return localList.size();
  }

  public void setField(MinimalField paramMinimalField)
  {
    if (paramMinimalField == null)
      return;
    String str = paramMinimalField.getName().toLowerCase(Locale.US);
    List localList = (List)this.fieldMap.get(str);
    if ((localList == null) || (localList.isEmpty()))
    {
      addField(paramMinimalField);
      return;
    }
    localList.clear();
    localList.add(paramMinimalField);
    int i = -1;
    int j = 0;
    Iterator localIterator = this.fields.iterator();
    while (localIterator.hasNext())
    {
      if (((MinimalField)localIterator.next()).getName().equalsIgnoreCase(paramMinimalField.getName()))
      {
        localIterator.remove();
        if (i == -1)
          i = j;
      }
      j++;
    }
    this.fields.add(i, paramMinimalField);
  }

  public String toString()
  {
    return this.fields.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     org.apache.http.entity.mime.Header
 * JD-Core Version:    0.6.0
 */
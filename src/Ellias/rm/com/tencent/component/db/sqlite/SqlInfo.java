package com.tencent.component.db.sqlite;

import com.tencent.component.db.util.ColumnUtils;
import java.util.LinkedList;

public class SqlInfo
{
  private String a;
  private LinkedList b;

  public SqlInfo()
  {
  }

  public SqlInfo(String paramString)
  {
    this.a = paramString;
  }

  public SqlInfo(String paramString, Object[] paramArrayOfObject)
  {
    this.a = paramString;
    a(paramArrayOfObject);
  }

  public String a()
  {
    return this.a;
  }

  public void a(Object paramObject)
  {
    if (this.b == null)
      this.b = new LinkedList();
    this.b.add(ColumnUtils.a(paramObject));
  }

  public void a(String paramString)
  {
    this.a = paramString;
  }

  public void a(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null)
    {
      int i = paramArrayOfObject.length;
      for (int j = 0; j < i; j++)
        a(paramArrayOfObject[j]);
    }
  }

  public LinkedList b()
  {
    return this.b;
  }

  void b(Object paramObject)
  {
    if (this.b == null)
      this.b = new LinkedList();
    this.b.add(paramObject);
  }

  public Object[] c()
  {
    if (this.b != null)
      return this.b.toArray();
    return null;
  }

  public String[] d()
  {
    LinkedList localLinkedList = this.b;
    Object localObject1 = null;
    if (localLinkedList != null)
    {
      String[] arrayOfString = new String[this.b.size()];
      int i = 0;
      if (i < this.b.size())
      {
        Object localObject2 = this.b.get(i);
        if (localObject2 == null);
        for (String str = null; ; str = localObject2.toString())
        {
          arrayOfString[i] = str;
          i++;
          break;
        }
      }
      localObject1 = arrayOfString;
    }
    return localObject1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.sqlite.SqlInfo
 * JD-Core Version:    0.6.0
 */
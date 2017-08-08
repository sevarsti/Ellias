package com.tencent.component.db;

public class EntityContext
{
  private EntityManager a;
  private String b;
  private ClassLoader c;

  public EntityContext(EntityManager paramEntityManager, String paramString, ClassLoader paramClassLoader)
  {
    this.a = paramEntityManager;
    this.b = paramString;
    this.c = paramClassLoader;
  }

  public String a()
  {
    return this.b;
  }

  public EntityManager b()
  {
    return this.a;
  }

  public ClassLoader c()
  {
    return this.c;
  }

  public ColumnValueProcessor d()
  {
    monitorenter;
    monitorexit;
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.EntityContext
 * JD-Core Version:    0.6.0
 */
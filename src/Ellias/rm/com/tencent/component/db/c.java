package com.tencent.component.db;

import java.util.HashMap;

class c
  implements a
{
  c(EntityManagerFactory paramEntityManagerFactory)
  {
  }

  public void a(EntityManager paramEntityManager)
  {
    synchronized (EntityManagerFactory.b(this.a))
    {
      String str = (String)EntityManagerFactory.c(this.a).remove(paramEntityManager);
      EntityManagerFactory.b(this.a).remove(str);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.c
 * JD-Core Version:    0.6.0
 */
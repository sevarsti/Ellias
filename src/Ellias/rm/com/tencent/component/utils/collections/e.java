package com.tencent.component.utils.collections;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

class e
  implements Iterator
{
  Iterator a = WeakValueHashMap.c(this.c.a).iterator();
  c b = null;

  e(d paramd)
  {
  }

  public boolean hasNext()
  {
    if (this.a.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)this.a.next();
      f localf = (f)localEntry.getValue();
      if (localf == null);
      for (Object localObject = null; ; localObject = localf.get())
      {
        this.b = new c(this.c.a, localEntry, localObject);
        return true;
      }
    }
    return false;
  }

  public Object next()
  {
    if ((this.b == null) && (!hasNext()))
      throw new NoSuchElementException();
    c localc = this.b;
    this.b = null;
    return localc;
  }

  public void remove()
  {
    this.a.remove();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.e
 * JD-Core Version:    0.6.0
 */
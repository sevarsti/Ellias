package com.tencent.component.utils.collections;

import java.util.Iterator;
import java.util.Set;

class b
  implements Iterator
{
  private Iterator b = this.a.a.entrySet().iterator();

  b(a parama)
  {
  }

  public boolean hasNext()
  {
    return this.b.hasNext();
  }

  public Object next()
  {
    return ((c)this.b.next()).getValue();
  }

  public void remove()
  {
    this.b.remove();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.b
 * JD-Core Version:    0.6.0
 */
package com.tencent.android.tpush.logging.a;

import java.io.Writer;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class g
  implements Iterable
{
  private ConcurrentLinkedQueue a = null;
  private AtomicInteger b = null;

  public int a()
  {
    return this.b.get();
  }

  public int a(String paramString)
  {
    int i = paramString.length();
    this.a.add(paramString);
    return this.b.addAndGet(i);
  }

  public void a(Writer paramWriter, char[] paramArrayOfChar)
  {
    if ((paramWriter == null) || (paramArrayOfChar == null) || (paramArrayOfChar.length == 0) || (this.b.get() == 0))
      return;
    int i = paramArrayOfChar.length;
    while (true)
    {
      int m;
      int i2;
      int i3;
      int i4;
      int i5;
      try
      {
        Iterator localIterator = iterator();
        j = 0;
        k = i;
        if (!localIterator.hasNext())
          continue;
        String str = (String)localIterator.next();
        m = str.length();
        n = j;
        i1 = 0;
        break label184;
        str.getChars(i1, i1 + i2, paramArrayOfChar, n);
        i3 = k - i2;
        i4 = n + i2;
        m -= i2;
        i5 = i2 + i1;
        if (i3 == 0)
        {
          paramWriter.write(paramArrayOfChar, 0, i);
          k = i;
          i1 = i5;
          n = 0;
          break label184;
          if (j <= 0)
            continue;
          paramWriter.write(paramArrayOfChar, 0, j);
          paramWriter.flush();
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      int n = i4;
      int i1 = i5;
      int k = i3;
      label184: if (m > 0)
      {
        if (k > m)
        {
          i2 = m;
          continue;
        }
        i2 = k;
        continue;
      }
      int j = n;
    }
  }

  public void b()
  {
    this.a.clear();
    this.b.set(0);
  }

  public Iterator iterator()
  {
    return this.a.iterator();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.a.g
 * JD-Core Version:    0.6.0
 */
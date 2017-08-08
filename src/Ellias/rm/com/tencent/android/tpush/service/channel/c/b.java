package com.tencent.android.tpush.service.channel.c;

import java.io.IOException;
import java.io.InputStream;

public class b extends InputStream
{
  protected b(a parama)
  {
  }

  public int available()
  {
    synchronized (this.a)
    {
      if (this.a.i)
        throw new IOException("InputStream has been closed, it is not ready.");
    }
    int i = a.a(this.a);
    monitorexit;
    return i;
  }

  public void close()
  {
    synchronized (this.a)
    {
      this.a.i = true;
      return;
    }
  }

  public void mark(int paramInt)
  {
    synchronized (this.a)
    {
      if (-1 + this.a.a.length > paramInt)
      {
        this.a.e = paramInt;
        this.a.d = this.a.b;
      }
      return;
    }
  }

  public boolean markSupported()
  {
    return true;
  }

  public int read()
  {
    while (true)
    {
      synchronized (this.a)
      {
        if (this.a.i)
          throw new IOException("InputStream has been closed; cannot read from a closed InputStream.");
      }
      if (a.a(this.a) > 0)
      {
        int i = 0xFF & this.a.a[this.a.b];
        a locala2 = this.a;
        locala2.b = (1 + locala2.b);
        if (this.a.b == this.a.a.length)
          this.a.b = 0;
        a.b(this.a);
        monitorexit;
        return i;
      }
      if (this.a.k)
      {
        monitorexit;
        return -1;
      }
      monitorexit;
      try
      {
        Thread.sleep(100L);
      }
      catch (Exception localException)
      {
      }
    }
    throw new IOException("Blocking read operation interrupted.");
  }

  public int read(byte[] paramArrayOfByte)
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    while (true)
    {
      synchronized (this.a)
      {
        if (this.a.i)
          throw new IOException("InputStream has been closed; cannot read from a closed InputStream.");
      }
      int i = a.a(this.a);
      if (i > 0)
      {
        int j = Math.min(paramInt2, i);
        int k = Math.min(j, this.a.a.length - this.a.b);
        int m = j - k;
        System.arraycopy(this.a.a, this.a.b, paramArrayOfByte, paramInt1, k);
        if (m > 0)
          System.arraycopy(this.a.a, 0, paramArrayOfByte, k + paramInt1, m);
        a locala2;
        for (this.a.b = m; ; locala2.b = (j + locala2.b))
        {
          if (this.a.b == this.a.a.length)
            this.a.b = 0;
          a.b(this.a);
          monitorexit;
          return j;
          locala2 = this.a;
        }
      }
      if (this.a.k)
      {
        monitorexit;
        return -1;
      }
      monitorexit;
      try
      {
        Thread.sleep(100L);
      }
      catch (Exception localException)
      {
      }
    }
    throw new IOException("Blocking read operation interrupted.");
  }

  public void reset()
  {
    synchronized (this.a)
    {
      if (this.a.i)
        throw new IOException("InputStream has been closed; cannot reset a closed InputStream.");
    }
    this.a.b = this.a.d;
    monitorexit;
  }

  public long skip(long paramLong)
  {
    while (true)
    {
      synchronized (this.a)
      {
        if (this.a.i)
          throw new IOException("InputStream has been closed; cannot skip bytes on a closed InputStream.");
      }
      int i = a.a(this.a);
      if (i > 0)
      {
        int j = Math.min((int)paramLong, i);
        int k = j - Math.min(j, this.a.a.length - this.a.b);
        if (k > 0);
        a locala2;
        for (this.a.b = k; ; locala2.b = (j + locala2.b))
        {
          if (this.a.b == this.a.a.length)
            this.a.b = 0;
          a.b(this.a);
          long l = j;
          monitorexit;
          return l;
          locala2 = this.a;
        }
      }
      if (this.a.k)
      {
        monitorexit;
        return 0L;
      }
      monitorexit;
      try
      {
        Thread.sleep(100L);
      }
      catch (Exception localException)
      {
      }
    }
    throw new IOException("Blocking read operation interrupted.");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.c.b
 * JD-Core Version:    0.6.0
 */
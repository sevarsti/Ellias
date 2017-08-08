package com.tencent.android.tpush.service.channel.c;

import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import java.io.IOException;
import java.io.OutputStream;

public class c extends OutputStream
{
  protected c(a parama)
  {
  }

  public void close()
  {
    synchronized (this.a)
    {
      if (!this.a.k)
        flush();
      this.a.k = true;
      return;
    }
  }

  public void flush()
  {
    if (this.a.k)
      throw new IOException("OutputStream has been closed; cannot flush a closed OutputStream.");
    if (this.a.i)
      throw new IOException("Buffer closed by inputStream; cannot flush.");
  }

  public void write(int paramInt)
  {
    int i = 0;
    if (i == 0)
    {
      synchronized (this.a)
      {
        if (this.a.k)
          throw new IOException("OutputStream has been closed; cannot write to a closed OutputStream.");
      }
      if (this.a.i)
        throw new IOException("Buffer closed by InputStream; cannot write to a closed buffer.");
      for (int j = a.c(this.a); (this.a.f) && (j < 1); j = a.c(this.a))
        a.d(this.a);
      if ((!this.a.g) && (j < 1))
        throw new IORefusedException("CircularByteBuffer is full; cannot write 1 byte");
      if (j > 0)
      {
        this.a.a[this.a.c] = (byte)(paramInt & 0xFF);
        a locala2 = this.a;
        locala2.c = (1 + locala2.c);
        if (this.a.c != this.a.a.length)
          break label231;
        this.a.c = 0;
        break label231;
      }
    }
    while (true)
    {
      while (true)
      {
        monitorexit;
        if (i != 0)
          break;
        try
        {
          Thread.sleep(100L);
        }
        catch (Exception localException)
        {
          throw new IOException("Waiting for available space in buffer interrupted.");
        }
      }
      return;
      label231: i = 1;
    }
  }

  public void write(byte[] paramArrayOfByte)
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramInt2 > 0)
    {
      synchronized (this.a)
      {
        if (this.a.k)
          throw new IOException("OutputStream has been closed; cannot write to a closed OutputStream.");
      }
      if (this.a.i)
        throw new IOException("Buffer closed by InputStream; cannot write to a closed buffer.");
      for (int i = a.c(this.a); (this.a.f) && (i < paramInt2); i = a.c(this.a))
        a.d(this.a);
      if ((!this.a.g) && (i < paramInt2))
        throw new IORefusedException("CircularByteBuffer is full; cannot write " + paramInt2 + " bytes");
      int j = Math.min(paramInt2, i);
      int k = Math.min(j, this.a.a.length - this.a.c);
      int m = Math.min(j - k, -1 + (this.a.a.length - this.a.d));
      int n = k + m;
      if (k > 0)
        System.arraycopy(paramArrayOfByte, paramInt1, this.a.a, this.a.c, k);
      if (m > 0)
        System.arraycopy(paramArrayOfByte, k + paramInt1, this.a.a, 0, m);
      a locala2;
      for (this.a.c = m; ; locala2.c = (n + locala2.c))
      {
        while (true)
        {
          if (this.a.c == this.a.a.length)
            this.a.c = 0;
          paramInt1 += n;
          paramInt2 -= n;
          monitorexit;
          if (paramInt2 <= 0)
            break;
          try
          {
            Thread.sleep(100L);
          }
          catch (Exception localException)
          {
            throw new IOException("Waiting for available space in buffer interrupted.");
          }
        }
        locala2 = this.a;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.c.c
 * JD-Core Version:    0.6.0
 */
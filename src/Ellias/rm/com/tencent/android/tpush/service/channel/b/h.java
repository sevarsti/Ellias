package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class h extends i
  implements e
{
  protected HashMap a = new HashMap(4);
  protected int b = 0;
  protected int c = -1;

  public h(int paramInt)
  {
    this.d = 80;
    this.e = paramInt;
  }

  private void g(OutputStream paramOutputStream)
  {
    this.i = 0;
    if (this.j.needsUpdate())
    {
      this.i = 1;
      this.j.update();
    }
    com.tencent.android.tpush.service.channel.c.e.a(paramOutputStream, this.i);
    this.g = this.j.getRandom();
    com.tencent.android.tpush.service.channel.c.e.a(paramOutputStream, this.g);
    if (this.i != 0)
      com.tencent.android.tpush.service.channel.c.e.a(paramOutputStream, this.j.getEncKey());
    h(paramOutputStream);
  }

  private void h(OutputStream paramOutputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    long l;
    if (this.k != 1)
      l = 0L;
    while (true)
    {
      com.tencent.android.tpush.service.channel.c.e.a(localByteArrayOutputStream, l);
      com.tencent.android.tpush.service.channel.c.e.a(localByteArrayOutputStream, this.l);
      com.tencent.android.tpush.service.channel.c.e.a(localByteArrayOutputStream, this.h);
      com.tencent.android.tpush.service.channel.c.e.a(localByteArrayOutputStream, this.m);
      localByteArrayOutputStream.write(this.n);
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      if (this.k == 1)
        arrayOfByte = this.j.encryptData(arrayOfByte);
      com.tencent.android.tpush.service.channel.c.e.a(paramOutputStream, arrayOfByte);
      return;
      l = this.j.getInc();
    }
  }

  public int a(OutputStream paramOutputStream)
  {
    TLog.d("Channel.SendPacket", ">>> write(" + paramOutputStream + ")");
    c();
    try
    {
      this.b = 0;
      i = 0;
      while (true)
      {
        try
        {
          if (b())
            break label87;
          int j = this.b;
          this.b = (j + 1);
          if (j <= 2)
            break label90;
          throw new InnerException("the duration of the current step is too long!");
        }
        catch (IORefusedException localIORefusedException2)
        {
        }
        TLog.v("Channel.SendPacket", "write >>> IORefusedException thrown", localIORefusedException2);
        label87: return i;
        label90: switch (this.c)
        {
        default:
          throw new InnerException("illegal step value!");
        case -1:
          i += b(paramOutputStream);
          break;
        case -2:
          i += c(paramOutputStream);
          break;
        case -3:
          i += d(paramOutputStream);
          break;
        case -4:
          i += e(paramOutputStream);
          break;
        case -5:
          i += f(paramOutputStream);
          break;
        case 0:
          d();
        }
      }
    }
    catch (IORefusedException localIORefusedException1)
    {
      while (true)
      {
        Object localObject = localIORefusedException1;
        int i = 0;
      }
    }
  }

  void a(int paramInt)
  {
    if (this.c != paramInt)
      this.b = 0;
    this.c = paramInt;
  }

  protected int b(OutputStream paramOutputStream)
  {
    com.tencent.android.tpush.service.channel.c.e.a(paramOutputStream, this.d);
    a(-2);
    return 1;
  }

  protected int c(OutputStream paramOutputStream)
  {
    com.tencent.android.tpush.service.channel.c.e.a(paramOutputStream, this.k);
    switch (this.k)
    {
    default:
      throw new UnexpectedDataException("protocol: " + this.k);
    case 1:
    case 10:
      a(-3);
    case 20:
    }
    while (true)
    {
      return 1;
      a(0);
    }
  }

  protected int d(OutputStream paramOutputStream)
  {
    com.tencent.android.tpush.service.channel.c.e.b(paramOutputStream, this.e);
    a(-5);
    return 4;
  }

  public void d()
  {
    monitorenter;
    try
    {
      super.d();
      this.a.clear();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  protected int e(OutputStream paramOutputStream)
  {
    com.tencent.android.tpush.service.channel.c.e.a(paramOutputStream, this.f);
    a(-5);
    return 4;
  }

  protected int f(OutputStream paramOutputStream)
  {
    byte[] arrayOfByte1 = (byte[])(byte[])this.a.get("packetData");
    if (arrayOfByte1 == null)
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      try
      {
        if (this.k == 10)
          h(localByteArrayOutputStream);
        while (true)
        {
          byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
          this.f = (10 + arrayOfByte2.length);
          this.a.put("packetData", arrayOfByte2);
          this.a.put("packetDataLeftLength", Integer.valueOf(arrayOfByte2.length));
          a(-4);
          return 0;
          g(localByteArrayOutputStream);
        }
      }
      catch (IOException localIOException)
      {
        throw new UnexpectedDataException("packetData can not be write correctly!", localIOException);
      }
    }
    int i = ((Integer)this.a.get("packetDataLeftLength")).intValue();
    if (i == 0)
    {
      a(0);
      return 0;
    }
    int j = com.tencent.android.tpush.service.channel.c.e.a(paramOutputStream, arrayOfByte1);
    int k = i - j;
    this.a.put("packetDataLeftLength", Integer.valueOf(k));
    return j;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.b.h
 * JD-Core Version:    0.6.0
 */
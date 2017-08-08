package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.c.e;
import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class g extends i
  implements d
{
  protected HashMap a = new HashMap(4);
  protected int b = 0;
  protected int c = -1;

  public int a(InputStream paramInputStream)
  {
    TLog.v("Channel.RecvPacket", ">>> read(" + paramInputStream + ")");
    c();
    int i = paramInputStream.available();
    int j = 0;
    if (i == 0);
    while (true)
    {
      return j;
      try
      {
        this.b = 0;
        if (b())
          continue;
        int k = this.b;
        this.b = (k + 1);
        if (k <= 2)
          break;
        throw new InnerException("the duration of the current step is too long!");
      }
      catch (IORefusedException localIORefusedException)
      {
        TLog.w("Channel.RecvPacket", "read >>> IORefusedException thrown", localIORefusedException);
        return j;
      }
    }
    switch (this.c)
    {
    default:
      throw new InnerException("illegal step value!");
    case -1:
      j += b(paramInputStream);
    case -2:
    case -3:
    case -4:
    case -5:
    case -6:
    case -7:
    case 0:
    }
    while ((this.c != 0) && (paramInputStream.available() == 0))
    {
      TLog.i("Channel.RecvPacket", "read data len:" + j + ", but the package is n't full" + ",step:" + this.c);
      return j;
      j += c(paramInputStream);
      continue;
      j += d(paramInputStream);
      continue;
      j += e(paramInputStream);
      continue;
      j += f(paramInputStream);
      continue;
      j += g(paramInputStream);
      continue;
      j += h(paramInputStream);
      continue;
      d();
    }
  }

  void a(int paramInt)
  {
    if (this.c != paramInt)
      this.b = 0;
    this.c = paramInt;
  }

  protected int b(InputStream paramInputStream)
  {
    this.d = e.a(paramInputStream);
    if (this.d != 80)
      throw new UnexpectedDataException("soh: " + this.d + " != TPNS_SOH");
    a(-2);
    return 1;
  }

  protected int c(InputStream paramInputStream)
  {
    this.k = e.a(paramInputStream);
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

  protected int d(InputStream paramInputStream)
  {
    this.e = e.c(paramInputStream);
    a(-4);
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

  protected int e(InputStream paramInputStream)
  {
    this.f = e.b(paramInputStream);
    this.f -= 10L;
    TLog.v("Channel.RecvPacket", ">>> package content length:" + this.f);
    if ((this.f > 10485760L) || (this.f < 0L))
      throw new UnexpectedDataException("packetLength: " + this.f);
    if (this.k == 1)
      a(-5);
    while (true)
    {
      return 4;
      a(-7);
    }
  }

  protected int f(InputStream paramInputStream)
  {
    this.f -= 1L;
    this.i = e.a(paramInputStream);
    if (this.i != 0)
      throw new UnexpectedDataException("negotiateSecurity: " + this.i + " != 0");
    a(-6);
    return 1;
  }

  protected int g(InputStream paramInputStream)
  {
    this.f -= 4L;
    this.g = e.b(paramInputStream);
    if (this.g != this.j.getRandom())
      throw new UnexpectedDataException("unexpected random: " + this.g);
    a(-7);
    return 4;
  }

  protected int h(InputStream paramInputStream)
  {
    byte[] arrayOfByte1 = (byte[])(byte[])this.a.get("contentData");
    StringBuilder localStringBuilder = new StringBuilder().append("contentData.length=");
    int i;
    if (arrayOfByte1 == null)
      i = 0;
    while (true)
    {
      TLog.i("RecvPacket", i);
      if (arrayOfByte1 != null)
        break;
      if (this.f < 0L)
      {
        throw new UnexpectedDataException("unexpected packetLength: " + this.f + " < 0");
        i = arrayOfByte1.length;
        continue;
      }
      arrayOfByte1 = new byte[(int)this.f];
      this.a.put("contentData", arrayOfByte1);
      this.a.put("contentDataLeftLength", Integer.valueOf(arrayOfByte1.length));
    }
    byte[] arrayOfByte2 = arrayOfByte1;
    int j = ((Integer)this.a.get("contentDataLeftLength")).intValue();
    int k = e.a(paramInputStream, arrayOfByte2, arrayOfByte2.length - j);
    int m = j - k;
    this.a.put("contentDataLeftLength", Integer.valueOf(m));
    ByteArrayInputStream localByteArrayInputStream;
    if (m == 0)
    {
      if (this.k == 1)
        arrayOfByte2 = this.j.decryptData(arrayOfByte2);
      localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte2);
    }
    try
    {
      long l = e.b(localByteArrayInputStream);
      if (this.k == 1)
        this.j.checkRemoteInc(l);
      this.l = e.a(localByteArrayInputStream);
      this.h = e.a(localByteArrayInputStream);
      this.m = e.a(localByteArrayInputStream);
      if (localByteArrayInputStream.available() > 0)
      {
        this.n = new byte[localByteArrayInputStream.available()];
        e.a(localByteArrayInputStream, this.n, 0);
      }
      a(0);
      return k;
    }
    catch (IOException localIOException)
    {
    }
    throw new UnexpectedDataException("contentData can not be read correctly!", localIOException);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.b.g
 * JD-Core Version:    0.6.0
 */
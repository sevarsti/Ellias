package com.tencent.android.tpush.horse;

import android.text.TextUtils;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.channel.b.a;
import com.tencent.android.tpush.service.channel.b.b;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.exception.HorseIgnoreException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import com.tencent.android.tpush.service.channel.protocol.TpnsRedirectRsp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class q
{
  private SocketChannel a;
  private ArrayBlockingQueue b = new ArrayBlockingQueue(1);
  private StrategyItem c;
  private long d;
  private long e;

  private InetSocketAddress b(StrategyItem paramStrategyItem)
  {
    TLog.v("XGService", "@@ getSocketAddress()");
    if ((paramStrategyItem.d() == 1) && (paramStrategyItem.h()))
      return new InetSocketAddress(paramStrategyItem.c(), paramStrategyItem.e());
    return new InetSocketAddress(paramStrategyItem.a(), paramStrategyItem.b());
  }

  private void d()
  {
    TLog.v("XGService", "@@ notifyFail()");
    try
    {
      r localr = (r)this.b.remove();
      if (localr != null)
        localr.b(this.c);
      this.e = System.currentTimeMillis();
      g.a(1, g.a(this.c, null, false), 7, this.e - this.d, this.c, null);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        TLog.e("XGService", localException.toString());
    }
  }

  public SocketChannel a()
  {
    return this.a;
  }

  public void a(JceStruct paramJceStruct)
  {
    TLog.v("XGService", "@@ send(" + paramJceStruct + ")");
    JceOutputStream localJceOutputStream = new JceOutputStream();
    localJceOutputStream.setServerEncoding("UTF-8");
    paramJceStruct.writeTo(localJceOutputStream);
    h localh = new h(1);
    localh.b(10);
    localh.a(10);
    localh.a(localJceOutputStream.getByteBuffer().array());
    try
    {
      localByteArrayOutputStream = new ByteArrayOutputStream();
      if (this.c.d() == 0)
        while (!localh.b())
          localh.a(localByteArrayOutputStream);
    }
    catch (InnerException localInnerException)
    {
      TLog.e("XGService", localInnerException.toString());
      d();
      throw new HorseIgnoreException(localInnerException);
      b localb = new b(this.c.a(), "http://" + this.c.a() + ":" + this.c.b() + "/");
      if (this.c.h())
        localb.a("X-Online-Host", this.c.a() + ":" + this.c.b());
      localb.a(localh);
      while (!localb.b())
        localb.a(localByteArrayOutputStream);
    }
    catch (IOException localIOException)
    {
      ByteArrayOutputStream localByteArrayOutputStream;
      TLog.e("XGService", localIOException.toString());
      d();
      throw new HorseIgnoreException(localIOException);
      localByteArrayOutputStream.writeTo(this.a.socket().getOutputStream());
      localByteArrayOutputStream.flush();
      return;
    }
    catch (UnexpectedDataException localUnexpectedDataException)
    {
      TLog.e("XGService", localUnexpectedDataException.toString());
      d();
      throw new HorseIgnoreException(localUnexpectedDataException);
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
      d();
    }
  }

  public void a(StrategyItem paramStrategyItem)
  {
    TLog.v("XGService", "@@ connect()");
    this.d = System.currentTimeMillis();
    this.c = paramStrategyItem;
    try
    {
      this.a = SocketChannel.open();
      this.a.configureBlocking(true);
      InetSocketAddress localInetSocketAddress = b(this.c);
      this.a.socket().connect(localInetSocketAddress, f.b());
      this.a.socket().setSoTimeout(f.c());
      return;
    }
    catch (AssertionError localAssertionError)
    {
      TLog.e("XGService", localAssertionError.toString());
      d();
      if (paramStrategyItem == null);
      for (String str2 = "null"; ; str2 = paramStrategyItem.toString())
        throw new HorseIgnoreException(str2, localAssertionError);
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
      d();
      if (paramStrategyItem != null);
    }
    for (String str1 = "null"; ; str1 = paramStrategyItem.toString())
      throw new HorseIgnoreException(str1, localException);
  }

  public void a(r paramr)
  {
    TLog.v("XGService", "@@ register(" + paramr + ")");
    try
    {
      this.b.add(paramr);
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
  }

  public void b()
  {
    TLog.v("XGService", "@@ recv()");
    if (this.c == null)
    {
      d();
      throw new HorseIgnoreException("Recv() fail,because mStrategyItem is null");
    }
    byte[] arrayOfByte1;
    if ((this.c != null) && (this.c.d() == 0))
    {
      com.tencent.android.tpush.service.channel.b.g localg = new com.tencent.android.tpush.service.channel.b.g();
      try
      {
        InputStream localInputStream2 = this.a.socket().getInputStream();
        byte[] arrayOfByte3 = new byte[1024];
        ByteArrayInputStream localByteArrayInputStream2 = new ByteArrayInputStream(arrayOfByte3);
        int k = 0;
        while (!localg.b())
        {
          k = localInputStream2.read(arrayOfByte3, k, arrayOfByte3.length - k);
          localg.a(localByteArrayInputStream2);
        }
      }
      catch (UnexpectedDataException localUnexpectedDataException2)
      {
        TLog.e("XGService", localUnexpectedDataException2.toString());
        d();
        throw new HorseIgnoreException(localUnexpectedDataException2);
        byte[] arrayOfByte4 = localg.i();
        arrayOfByte1 = arrayOfByte4;
        if (arrayOfByte1 != null)
          break label528;
        TLog.e("XGService", ">> dataBuffer is null");
        d();
        return;
      }
      catch (IOException localIOException2)
      {
        TLog.e("XGService", localIOException2.toString());
        d();
        throw new HorseIgnoreException(localIOException2);
      }
      catch (InnerException localInnerException2)
      {
        TLog.e("XGService", localInnerException2.toString());
        d();
        throw new HorseIgnoreException(localInnerException2);
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException2)
      {
        TLog.e("XGService", localIndexOutOfBoundsException2.toString());
        d();
        throw new HorseIgnoreException(localIndexOutOfBoundsException2);
      }
      catch (Exception localException3)
      {
        TLog.e("XGService", localException3.toString());
        d();
      }
    }
    else
    {
      while (true)
        while (true)
        {
          arrayOfByte1 = null;
          break;
          a locala = new a();
          try
          {
            InputStream localInputStream1 = this.a.socket().getInputStream();
            byte[] arrayOfByte2 = new byte[1024];
            ByteArrayInputStream localByteArrayInputStream1 = new ByteArrayInputStream(arrayOfByte2);
            int j = 0;
            while (!locala.b())
            {
              j += localInputStream1.read(arrayOfByte2, j, arrayOfByte2.length - j);
              locala.a(localByteArrayInputStream1);
            }
          }
          catch (UnexpectedDataException localUnexpectedDataException1)
          {
            TLog.e("XGService", localUnexpectedDataException1.toString());
            d();
            throw new HorseIgnoreException(localUnexpectedDataException1);
            if ((locala != null) && (locala.i != null) && (locala.i.size() > 0))
            {
              arrayOfByte1 = ((com.tencent.android.tpush.service.channel.b.g)locala.i.get(0)).i();
              break;
            }
            TLog.e("XGService", ">> packet is null or packet.recvPackets is null");
            d();
            return;
          }
          catch (IOException localIOException1)
          {
            TLog.e("XGService", localIOException1.toString());
            d();
            throw new HorseIgnoreException(localIOException1);
          }
          catch (InnerException localInnerException1)
          {
            TLog.e("XGService", localInnerException1.toString());
            d();
            throw new HorseIgnoreException(localInnerException1);
          }
          catch (IndexOutOfBoundsException localIndexOutOfBoundsException1)
          {
            TLog.e("XGService", localIndexOutOfBoundsException1.toString());
            d();
            throw new HorseIgnoreException(localIndexOutOfBoundsException1);
          }
          catch (Exception localException1)
          {
            TLog.e("XGService", localException1.toString());
            d();
          }
        }
    }
    label528: JceInputStream localJceInputStream = new JceInputStream(arrayOfByte1);
    localJceInputStream.setServerEncoding("UTF-8");
    TpnsRedirectRsp localTpnsRedirectRsp = new TpnsRedirectRsp();
    localTpnsRedirectRsp.readFrom(localJceInputStream);
    TLog.i("XGService", "redirect ip=" + localTpnsRedirectRsp.ip + " port=" + localTpnsRedirectRsp.port);
    try
    {
      r localr2 = (r)this.b.remove();
      localr1 = localr2;
      if (localr1 != null)
      {
        String str = c.a(localTpnsRedirectRsp.ip);
        int i = localTpnsRedirectRsp.port;
        localStrategyItem2 = new StrategyItem(str, i, this.c.c(), this.c.e(), this.c.d(), this.c.f());
        if ((TextUtils.isEmpty(str)) || (i == 0))
        {
          if (localr1 == null)
            break label798;
          localr1.a(this.c);
          localStrategyItem1 = localStrategyItem2;
          this.e = System.currentTimeMillis();
          g.a(0, g.a(this.c, localStrategyItem1, true), 7, this.e - this.d, this.c, localStrategyItem1);
          return;
        }
      }
    }
    catch (Exception localException2)
    {
      while (true)
      {
        StrategyItem localStrategyItem2;
        TLog.e("XGService", localException2.toString());
        r localr1 = null;
        continue;
        localStrategyItem2.a(1);
        if (localr1 != null)
          localr1.a(this.c, localStrategyItem2);
        label798: StrategyItem localStrategyItem1 = localStrategyItem2;
        continue;
        localStrategyItem1 = null;
      }
    }
  }

  public void c()
  {
    TLog.v("XGService", "@@ close()");
    try
    {
      this.a.close();
      this.b.clear();
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.q
 * JD-Core Version:    0.6.0
 */
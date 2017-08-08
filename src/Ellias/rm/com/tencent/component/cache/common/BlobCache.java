package com.tencent.component.cache.common;

import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.zip.Adler32;

public class BlobCache
  implements Closeable
{
  private static final String a = "BlobCache";
  private static final int b = -1289277377;
  private static final int c = -1121680097;
  private static final int d = 0;
  private static final int e = 4;
  private static final int f = 8;
  private static final int g = 12;
  private static final int h = 16;
  private static final int i = 20;
  private static final int j = 24;
  private static final int k = 28;
  private static final int l = 32;
  private static final int m = 4;
  private static final int n = 0;
  private static final int o = 8;
  private static final int p = 12;
  private static final int q = 16;
  private static final int r = 20;
  private int A;
  private int B;
  private int C;
  private RandomAccessFile D;
  private RandomAccessFile E;
  private int F;
  private int G;
  private byte[] H = new byte[32];
  private byte[] I = new byte[20];
  private Adler32 J = new Adler32();
  private BlobCache.LookupRequest K = new BlobCache.LookupRequest();
  private int L;
  private int M;
  private RandomAccessFile s;
  private RandomAccessFile t;
  private RandomAccessFile u;
  private FileChannel v;
  private MappedByteBuffer w;
  private int x;
  private int y;
  private int z;

  public BlobCache(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this(paramString, paramInt1, paramInt2, paramBoolean, 0);
  }

  public BlobCache(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    this.s = new RandomAccessFile(paramString + ".idx", "rw");
    this.t = new RandomAccessFile(paramString + ".0", "rw");
    this.u = new RandomAccessFile(paramString + ".1", "rw");
    this.C = paramInt3;
    if ((!paramBoolean) && (e()));
    do
    {
      return;
      a(paramInt1, paramInt2);
    }
    while (e());
    d();
    throw new IOException("unable to load index");
  }

  static int a(byte[] paramArrayOfByte, int paramInt)
  {
    return 0xFF & paramArrayOfByte[paramInt] | (0xFF & paramArrayOfByte[(paramInt + 1)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 2)]) << 16 | (0xFF & paramArrayOfByte[(paramInt + 3)]) << 24;
  }

  private void a(int paramInt)
  {
    byte[] arrayOfByte = new byte[1024];
    this.w.position(paramInt);
    int i1 = 12 * this.x;
    while (i1 > 0)
    {
      int i2 = Math.min(i1, 1024);
      this.w.put(arrayOfByte, 0, i2);
      i1 -= i2;
    }
  }

  private void a(int paramInt1, int paramInt2)
  {
    this.s.setLength(0L);
    this.s.setLength(32 + 2 * (paramInt1 * 12));
    this.s.seek(0L);
    byte[] arrayOfByte = this.H;
    b(arrayOfByte, 0, -1289277377);
    b(arrayOfByte, 4, paramInt1);
    b(arrayOfByte, 8, paramInt2);
    b(arrayOfByte, 12, 0);
    b(arrayOfByte, 16, 0);
    b(arrayOfByte, 20, 4);
    b(arrayOfByte, 24, this.C);
    b(arrayOfByte, 28, a(arrayOfByte, 0, 28));
    this.s.write(arrayOfByte);
    this.t.setLength(0L);
    this.u.setLength(0L);
    this.t.seek(0L);
    this.u.seek(0L);
    b(arrayOfByte, 0, -1121680097);
    this.t.write(arrayOfByte, 0, 4);
    this.u.write(arrayOfByte, 0, 4);
  }

  private void a(long paramLong, byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = this.I;
    int i1 = a(paramArrayOfByte);
    a(arrayOfByte, 0, paramLong);
    b(arrayOfByte, 8, i1);
    b(arrayOfByte, 12, this.B);
    b(arrayOfByte, 16, paramInt);
    this.D.write(arrayOfByte);
    this.D.write(paramArrayOfByte, 0, paramInt);
    this.w.putLong(this.L, paramLong);
    this.w.putInt(8 + this.L, this.B);
    this.B += paramInt + 20;
    b(this.H, 20, this.B);
  }

  static void a(Closeable paramCloseable)
  {
    if (paramCloseable == null)
      return;
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public static void a(String paramString)
  {
    b(paramString + ".idx");
    b(paramString + ".0");
    b(paramString + ".1");
  }

  static void a(byte[] paramArrayOfByte, int paramInt, long paramLong)
  {
    for (int i1 = 0; i1 < 8; i1++)
    {
      paramArrayOfByte[(paramInt + i1)] = (byte)(int)(0xFF & paramLong);
      paramLong >>= 8;
    }
  }

  private boolean a(long paramLong, int paramInt)
  {
    int i1 = (int)(paramLong % this.x);
    if (i1 < 0)
      i1 += this.x;
    int i2 = i1;
    while (true)
    {
      int i3 = paramInt + i2 * 12;
      long l1 = this.w.getLong(i3);
      int i4 = this.w.getInt(i3 + 8);
      if (i4 == 0)
      {
        this.L = i3;
        return false;
      }
      if (l1 == paramLong)
      {
        this.L = i3;
        this.M = i4;
        return true;
      }
      i2++;
      if (i2 >= this.x)
        i2 = 0;
      if (i2 != i1)
        continue;
      Log.w("BlobCache", "corrupted index: clear the slot.");
      this.w.putInt(8 + (paramInt + i2 * 12), 0);
    }
  }

  private boolean a(RandomAccessFile paramRandomAccessFile, int paramInt, BlobCache.LookupRequest paramLookupRequest)
  {
    byte[] arrayOfByte1 = this.I;
    long l1 = paramRandomAccessFile.getFilePointer();
    long l2 = paramInt;
    try
    {
      paramRandomAccessFile.seek(l2);
      if (paramRandomAccessFile.read(arrayOfByte1) != 20)
      {
        Log.w("BlobCache", "cannot read blob header");
        return false;
      }
      long l3 = b(arrayOfByte1, 0);
      if (l3 != paramLookupRequest.a)
      {
        Log.w("BlobCache", "blob key does not match: " + l3);
        return false;
      }
      int i1 = a(arrayOfByte1, 8);
      int i2 = a(arrayOfByte1, 12);
      if (i2 != paramInt)
      {
        Log.w("BlobCache", "blob offset does not match: " + i2);
        return false;
      }
      int i3 = a(arrayOfByte1, 16);
      if ((i3 < 0) || (i3 > -20 + (this.y - paramInt)))
      {
        Log.w("BlobCache", "invalid blob length: " + i3);
        return false;
      }
      if ((paramLookupRequest.b == null) || (paramLookupRequest.b.length < i3))
        paramLookupRequest.b = new byte[i3];
      byte[] arrayOfByte2 = paramLookupRequest.b;
      paramLookupRequest.c = i3;
      if (paramRandomAccessFile.read(arrayOfByte2, 0, i3) != i3)
      {
        Log.w("BlobCache", "cannot read blob data");
        return false;
      }
      if (a(arrayOfByte2, 0, i3) != i1)
      {
        Log.w("BlobCache", "blob checksum does not match: " + i1);
        return false;
      }
      return true;
    }
    catch (Throwable localThrowable)
    {
      Log.e("BlobCache", "getBlob failed.", localThrowable);
      return false;
    }
    finally
    {
      paramRandomAccessFile.seek(l1);
    }
    throw localObject;
  }

  static long b(byte[] paramArrayOfByte, int paramInt)
  {
    long l1 = 0xFF & paramArrayOfByte[(paramInt + 7)];
    for (int i1 = 6; i1 >= 0; i1--)
      l1 = l1 << 8 | 0xFF & paramArrayOfByte[(paramInt + i1)];
    return l1;
  }

  private static void b(String paramString)
  {
    try
    {
      new File(paramString).delete();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  static void b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    for (int i1 = 0; i1 < 4; i1++)
    {
      paramArrayOfByte[(paramInt1 + i1)] = (byte)(paramInt2 & 0xFF);
      paramInt2 >>= 8;
    }
  }

  private void d()
  {
    a(this.v);
    a(this.s);
    a(this.t);
    a(this.u);
  }

  private boolean e()
  {
    try
    {
      this.s.seek(0L);
      this.t.seek(0L);
      this.u.seek(0L);
      byte[] arrayOfByte1 = this.H;
      if (this.s.read(arrayOfByte1) != 32)
      {
        Log.w("BlobCache", "cannot read header");
        return false;
      }
      if (a(arrayOfByte1, 0) != -1289277377)
      {
        Log.w("BlobCache", "cannot read header magic");
        return false;
      }
      if (a(arrayOfByte1, 24) != this.C)
      {
        Log.w("BlobCache", "version mismatch");
        return false;
      }
      this.x = a(arrayOfByte1, 4);
      this.y = a(arrayOfByte1, 8);
      this.z = a(arrayOfByte1, 12);
      this.A = a(arrayOfByte1, 16);
      this.B = a(arrayOfByte1, 20);
      int i1 = a(arrayOfByte1, 28);
      if (a(arrayOfByte1, 0, 28) != i1)
      {
        Log.w("BlobCache", "header checksum does not match");
        return false;
      }
      if (this.x <= 0)
      {
        Log.w("BlobCache", "invalid max entries");
        return false;
      }
      if (this.y <= 0)
      {
        Log.w("BlobCache", "invalid max bytes");
        return false;
      }
      if ((this.z != 0) && (this.z != 1))
      {
        Log.w("BlobCache", "invalid active region");
        return false;
      }
      if ((this.A < 0) || (this.A > this.x))
      {
        Log.w("BlobCache", "invalid active entries");
        return false;
      }
      if ((this.B < 4) || (this.B > this.y))
      {
        Log.w("BlobCache", "invalid active bytes");
        return false;
      }
      if (this.s.length() != 32 + 2 * (12 * this.x))
      {
        Log.w("BlobCache", "invalid index file length");
        return false;
      }
      byte[] arrayOfByte2 = new byte[4];
      if (this.t.read(arrayOfByte2) != 4)
      {
        Log.w("BlobCache", "cannot read data file magic");
        return false;
      }
      if (a(arrayOfByte2, 0) != -1121680097)
      {
        Log.w("BlobCache", "invalid data file magic");
        return false;
      }
      if (this.u.read(arrayOfByte2) != 4)
      {
        Log.w("BlobCache", "cannot read data file magic");
        return false;
      }
      if (a(arrayOfByte2, 0) != -1121680097)
      {
        Log.w("BlobCache", "invalid data file magic");
        return false;
      }
      this.v = this.s.getChannel();
      this.w = this.v.map(FileChannel.MapMode.READ_WRITE, 0L, this.s.length());
      this.w.order(ByteOrder.LITTLE_ENDIAN);
      f();
      return true;
    }
    catch (IOException localIOException)
    {
      Log.e("BlobCache", "loadIndex failed.", localIOException);
    }
    return false;
  }

  private void f()
  {
    RandomAccessFile localRandomAccessFile1;
    if (this.z == 0)
    {
      localRandomAccessFile1 = this.t;
      this.D = localRandomAccessFile1;
      if (this.z != 1)
        break label103;
    }
    label103: for (RandomAccessFile localRandomAccessFile2 = this.t; ; localRandomAccessFile2 = this.u)
    {
      this.E = localRandomAccessFile2;
      this.D.setLength(this.B);
      this.D.seek(this.B);
      this.F = 32;
      this.G = 32;
      if (this.z != 0)
        break label111;
      this.G += 12 * this.x;
      return;
      localRandomAccessFile1 = this.u;
      break;
    }
    label111: this.F += 12 * this.x;
  }

  private void g()
  {
    this.z = (1 - this.z);
    this.A = 0;
    this.B = 4;
    b(this.H, 12, this.z);
    b(this.H, 16, this.A);
    b(this.H, 20, this.B);
    h();
    f();
    a(this.F);
    a();
  }

  private void h()
  {
    b(this.H, 28, a(this.H, 0, 28));
    this.w.position(0);
    this.w.put(this.H);
  }

  int a(byte[] paramArrayOfByte)
  {
    this.J.reset();
    this.J.update(paramArrayOfByte);
    return (int)this.J.getValue();
  }

  int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.J.reset();
    this.J.update(paramArrayOfByte, paramInt1, paramInt2);
    return (int)this.J.getValue();
  }

  public void a()
  {
    try
    {
      this.w.force();
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.w("BlobCache", "sync index failed", localThrowable);
    }
  }

  public void a(long paramLong, byte[] paramArrayOfByte)
  {
    if (24 + paramArrayOfByte.length > this.y)
      throw new RuntimeException("blob is too large!");
    if ((20 + this.B + paramArrayOfByte.length > this.y) || (2 * this.A >= this.x))
      g();
    if (!a(paramLong, this.F))
    {
      this.A = (1 + this.A);
      b(this.H, 16, this.A);
    }
    a(paramLong, paramArrayOfByte, paramArrayOfByte.length);
    h();
  }

  public boolean a(BlobCache.LookupRequest paramLookupRequest)
  {
    if ((a(paramLookupRequest.a, this.F)) && (a(this.D, this.M, paramLookupRequest)));
    while (true)
    {
      return true;
      int i1 = this.L;
      if ((!a(paramLookupRequest.a, this.G)) || (!a(this.E, this.M, paramLookupRequest)))
        break;
      if ((20 + this.B + paramLookupRequest.c > this.y) || (2 * this.A >= this.x))
        continue;
      this.L = i1;
      try
      {
        a(paramLookupRequest.a, paramLookupRequest.b, paramLookupRequest.c);
        this.A = (1 + this.A);
        b(this.H, 16, this.A);
        h();
        return true;
      }
      catch (Throwable localThrowable)
      {
        Log.e("BlobCache", "cannot copy over");
        return true;
      }
    }
    return false;
  }

  public byte[] a(long paramLong)
  {
    this.K.a = paramLong;
    this.K.b = null;
    boolean bool = a(this.K);
    byte[] arrayOfByte = null;
    if (bool)
      arrayOfByte = this.K.b;
    return arrayOfByte;
  }

  public void b()
  {
    a();
    try
    {
      this.t.getFD().sync();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        while (true)
        {
          this.u.getFD().sync();
          return;
          localThrowable1 = localThrowable1;
          Log.w("BlobCache", "sync data file 0 failed", localThrowable1);
        }
      }
      catch (Throwable localThrowable2)
      {
        Log.w("BlobCache", "sync data file 1 failed", localThrowable2);
      }
    }
  }

  int c()
  {
    int i1 = 0;
    int i2 = 0;
    while (i1 < this.x)
    {
      int i3 = this.F + i1 * 12;
      this.w.getLong(i3);
      if (this.w.getInt(i3 + 8) != 0)
        i2++;
      i1++;
    }
    if (i2 == this.A)
      return i2;
    Log.e("BlobCache", "wrong active count: " + this.A + " vs " + i2);
    return -1;
  }

  public void close()
  {
    b();
    d();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.common.BlobCache
 * JD-Core Version:    0.6.0
 */
package com.tencent.component.utils.gif;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class GifStreamDecoder
{
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  protected static final int d = 4096;
  protected InputStream e;
  protected String f = null;
  protected int g;
  protected GifStreamDecoder.State h;
  protected GifStreamDecoder.GifFrame i;

  public GifStreamDecoder(String paramString)
  {
    this.f = paramString;
  }

  private boolean t()
  {
    h();
    try
    {
      this.e = new FileInputStream(this.f);
      if (this.e != null)
      {
        m();
        if (g())
          this.g = 2;
        if (this.g != 0)
          break label67;
        return true;
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
      {
        localFileNotFoundException.printStackTrace();
        continue;
        this.g = 2;
      }
    }
    label67: return false;
  }

  public int a()
  {
    if (this.h != null)
      return this.h.e;
    return 0;
  }

  protected int[] a(int paramInt)
  {
    int j = 0;
    int k = paramInt * 3;
    int[] arrayOfInt = null;
    byte[] arrayOfByte = new byte[k];
    while (true)
    {
      int n;
      try
      {
        int i7 = this.e.read(arrayOfByte);
        m = i7;
        if (m >= k)
          continue;
        this.g = 1;
        return arrayOfInt;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        int m = 0;
        continue;
        arrayOfInt = new int[256];
        n = 0;
      }
      while (j < paramInt)
      {
        int i1 = n + 1;
        int i2 = 0xFF & arrayOfByte[n];
        int i3 = i1 + 1;
        int i4 = 0xFF & arrayOfByte[i1];
        n = i3 + 1;
        int i5 = 0xFF & arrayOfByte[i3];
        int i6 = j + 1;
        arrayOfInt[j] = (i5 | (0xFF000000 | i2 << 16 | i4 << 8));
        j = i6;
      }
    }
  }

  protected void b()
  {
    int j = 0;
    int[] arrayOfInt = new int[this.h.a * this.h.b];
    if (this.h.C > 0)
      if (this.h.C == 3)
      {
        if (-2 + this.h.K > 0)
          this.h.y = null;
      }
      else
      {
        if (this.h.y == null)
          break label228;
        this.h.y.getPixels(arrayOfInt, 0, this.h.a, 0, 0, this.h.a, this.h.b);
        if (this.h.C != 2)
          break label228;
        if (this.h.D)
          break label549;
      }
    label543: label549: for (int i13 = this.h.k; ; i13 = 0)
    {
      for (int i14 = 0; ; i14++)
      {
        if (i14 >= this.h.w)
          break label228;
        int i15 = (i14 + this.h.u) * this.h.a + this.h.t;
        int i16 = i15 + this.h.v;
        while (true)
          if (i15 < i16)
          {
            arrayOfInt[i15] = i13;
            i15++;
            continue;
            this.h.y = null;
            break;
          }
      }
      label228: int k = 8;
      int m = 1;
      int n = 0;
      int i12;
      if (j < this.h.s)
      {
        if (!this.h.n)
          break label543;
        if (n >= this.h.s)
          m++;
        switch (m)
        {
        default:
          int i11 = n + k;
          i12 = n;
          n = i11;
        case 2:
        case 3:
        case 4:
        }
      }
      for (int i1 = i12; ; i1 = j)
      {
        int i2 = i1 + this.h.q;
        if (i2 < this.h.b)
        {
          int i3 = i2 * this.h.a;
          int i4 = i3 + this.h.p;
          int i5 = i4 + this.h.r;
          if (i3 + this.h.a < i5)
            i5 = i3 + this.h.a;
          int i6 = j * this.h.r;
          int i7 = i4;
          while (true)
            if (i7 < i5)
            {
              byte[] arrayOfByte = this.h.J;
              int i8 = i6 + 1;
              int i9 = 0xFF & arrayOfByte[i6];
              int i10 = this.h.h[i9];
              if (i10 != 0)
                arrayOfInt[i7] = i10;
              i7++;
              i6 = i8;
              continue;
              n = 4;
              break;
              n = 2;
              k = 4;
              break;
              n = 1;
              k = 2;
              break;
            }
        }
        j++;
        break;
        this.h.x = Bitmap.createBitmap(arrayOfInt, this.h.a, this.h.b, Bitmap.Config.ARGB_4444);
        return;
      }
    }
  }

  public GifStreamDecoder.GifFrame c()
  {
    if (this.e == null)
      t();
    if (this.e == null)
      return null;
    int j = 0;
    int k = 0;
    while ((k == 0) && (j == 0) && (!g()))
      switch (i())
      {
      default:
        this.g = 1;
        break;
      case 44:
        n();
        k = 1;
        break;
      case 33:
        switch (i())
        {
        default:
          s();
          break;
        case 249:
          l();
          break;
        case 255:
          j();
          StringBuilder localStringBuilder = new StringBuilder();
          for (int m = 0; m < 11; m++)
            localStringBuilder.append((char)this.h.z[m]);
          if (localStringBuilder.toString().equals("NETSCAPE2.0"))
          {
            p();
            continue;
          }
          s();
          break;
        case 254:
          s();
          break;
        case 1:
          s();
        }
        break;
      case 59:
        j = 1;
      }
    GifStreamDecoder.GifFrame localGifFrame = this.i;
    if (j != 0)
      d();
    return localGifFrame;
  }

  public int d()
  {
    if (this.e != null);
    try
    {
      this.e.close();
      this.e = null;
      label19: this.h = null;
      this.i = null;
      return 0;
    }
    catch (Exception localException)
    {
      break label19;
    }
  }

  public int e()
  {
    if (this.h != null)
      return this.h.K;
    return 0;
  }

  protected void f()
  {
    int j = this.h.r * this.h.s;
    if ((this.h.J == null) || (this.h.J.length < j))
      this.h.J = new byte[j];
    if (this.h.G == null)
      this.h.G = new short[4096];
    if (this.h.H == null)
      this.h.H = new byte[4096];
    if (this.h.I == null)
      this.h.I = new byte[4097];
    int k = i();
    int m = 1 << k;
    int n = m + 1;
    int i1 = m + 2;
    int i2 = k + 1;
    int i3 = -1 + (1 << i2);
    for (int i4 = 0; i4 < m; i4++)
    {
      this.h.G[i4] = 0;
      this.h.H[i4] = (byte)i4;
    }
    int i5 = 0;
    int i6 = 0;
    int i7 = 0;
    int i8 = 0;
    int i9 = 0;
    int i10 = 0;
    int i11 = 0;
    int i12 = -1;
    int i13 = 0;
    if (i9 < j)
    {
      if (i6 != 0)
        break label742;
      if (i11 >= i2)
        break label310;
      if (i10 != 0)
        break label276;
      i10 = j();
      if (i10 > 0)
        break label273;
    }
    label273: label276: label310: int i26;
    int i27;
    int i28;
    label460: int i29;
    do
    {
      do
      {
        for (int i14 = i5; i14 < j; i14++)
          this.h.J[i14] = 0;
        i13 = 0;
        i8 += ((0xFF & this.h.z[i13]) << i11);
        i11 += 8;
        i13++;
        i10--;
        break;
        i26 = i8 & i3;
        i8 >>= i2;
        i11 -= i2;
      }
      while ((i26 > i1) || (i26 == n));
      if (i26 == m)
      {
        i2 = k + 1;
        i3 = -1 + (1 << i2);
        i1 = m + 2;
        i12 = -1;
        break;
      }
      if (i12 == -1)
      {
        byte[] arrayOfByte5 = this.h.I;
        int i32 = i6 + 1;
        arrayOfByte5[i6] = this.h.H[i26];
        i6 = i32;
        i12 = i26;
        i7 = i26;
        break;
      }
      if (i26 != i1)
        break label731;
      byte[] arrayOfByte4 = this.h.I;
      i27 = i6 + 1;
      arrayOfByte4[i6] = (byte)i7;
      i28 = i12;
      while (i28 > m)
      {
        byte[] arrayOfByte3 = this.h.I;
        int i31 = i27 + 1;
        arrayOfByte3[i27] = this.h.H[i28];
        i28 = this.h.G[i28];
        i27 = i31;
      }
      i29 = 0xFF & this.h.H[i28];
    }
    while (i1 >= 4096);
    byte[] arrayOfByte2 = this.h.I;
    int i30 = i27 + 1;
    arrayOfByte2[i27] = (byte)i29;
    this.h.G[i1] = (short)i12;
    this.h.H[i1] = (byte)i29;
    int i22 = i1 + 1;
    if (((i22 & i3) == 0) && (i22 < 4096))
    {
      i2++;
      i3 += i22;
    }
    int i20 = i8;
    int i21 = i26;
    int i15 = i3;
    int i16 = i29;
    int i17 = i11;
    int i18 = i2;
    int i19 = i30;
    while (true)
    {
      int i23 = i19 - 1;
      byte[] arrayOfByte1 = this.h.J;
      int i24 = i5 + 1;
      arrayOfByte1[i5] = this.h.I[i23];
      i9++;
      i5 = i24;
      i2 = i18;
      i11 = i17;
      i7 = i16;
      i3 = i15;
      int i25 = i21;
      i8 = i20;
      i6 = i23;
      i1 = i22;
      i12 = i25;
      break;
      return;
      label731: i27 = i6;
      i28 = i26;
      break label460;
      label742: i15 = i3;
      i16 = i7;
      i17 = i11;
      i18 = i2;
      i19 = i6;
      i20 = i8;
      i21 = i12;
      i22 = i1;
    }
  }

  protected boolean g()
  {
    return this.g != 0;
  }

  protected void h()
  {
    this.g = 0;
    this.h = new GifStreamDecoder.State();
    this.h.K = 0;
    this.h.f = null;
    this.h.g = null;
  }

  protected int i()
  {
    try
    {
      int j = this.e.read();
      return j;
    }
    catch (Exception localException)
    {
      this.g = 1;
    }
    return 0;
  }

  protected int j()
  {
    this.h.A = i();
    int j = this.h.A;
    int k = 0;
    if (j > 0);
    try
    {
      while (true)
      {
        int m;
        if (k < this.h.A)
        {
          m = this.e.read(this.h.z, k, this.h.A - k);
          if (m != -1);
        }
        else
        {
          if (k < this.h.A)
            this.g = 1;
          return k;
        }
        k += m;
      }
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  protected void k()
  {
    int j = 0;
    while ((j == 0) && (!g()))
      switch (i())
      {
      default:
        this.g = 1;
        break;
      case 44:
        n();
        break;
      case 33:
        switch (i())
        {
        default:
          s();
          break;
        case 249:
          l();
          break;
        case 255:
          j();
          StringBuilder localStringBuilder = new StringBuilder();
          for (int k = 0; k < 11; k++)
            localStringBuilder.append((char)this.h.z[k]);
          if (localStringBuilder.toString().equals("NETSCAPE2.0"))
          {
            p();
            continue;
          }
          s();
          break;
        case 254:
          s();
          break;
        case 1:
          s();
        }
        break;
      case 59:
        j = 1;
      }
  }

  protected void l()
  {
    int j = 1;
    i();
    int k = i();
    this.h.B = ((k & 0x1C) >> 2);
    if (this.h.B == 0)
      this.h.B = j;
    GifStreamDecoder.State localState = this.h;
    if ((k & 0x1) != 0);
    while (true)
    {
      localState.D = j;
      this.h.E = (10 * q());
      this.h.F = i();
      i();
      return;
      j = 0;
    }
  }

  protected void m()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int j = 0; j < 6; j++)
      localStringBuilder.append((char)i());
    if (!localStringBuilder.toString().startsWith("GIF"))
      this.g = 1;
    do
    {
      return;
      o();
    }
    while ((!this.h.c) || (g()));
    this.h.f = a(this.h.d);
    this.h.j = this.h.f[this.h.i];
  }

  protected void n()
  {
    this.h.p = q();
    this.h.q = q();
    this.h.r = q();
    this.h.s = q();
    int j = i();
    GifStreamDecoder.State localState1 = this.h;
    boolean bool1;
    boolean bool2;
    label106: int k;
    if ((j & 0x80) != 0)
    {
      bool1 = true;
      localState1.m = bool1;
      this.h.o = (int)Math.pow(2.0D, 1 + (j & 0x7));
      GifStreamDecoder.State localState2 = this.h;
      if ((j & 0x40) == 0)
        break label237;
      bool2 = true;
      localState2.n = bool2;
      if (!this.h.m)
        break label243;
      this.h.g = a(this.h.o);
      this.h.h = this.h.g;
      label155: boolean bool3 = this.h.D;
      k = 0;
      if (bool3)
      {
        int m = this.h.h[this.h.F];
        this.h.h[this.h.F] = 0;
        k = m;
      }
      if (this.h.h == null)
        this.g = 1;
      if (!g())
        break label285;
    }
    label237: label243: 
    do
    {
      return;
      bool1 = false;
      break;
      bool2 = false;
      break label106;
      this.h.h = this.h.f;
      if (this.h.i != this.h.F)
        break label155;
      this.h.j = 0;
      break label155;
      f();
      s();
    }
    while (g());
    label285: GifStreamDecoder.State localState3 = this.h;
    localState3.K = (1 + localState3.K);
    this.h.x = Bitmap.createBitmap(this.h.a, this.h.b, Bitmap.Config.ARGB_4444);
    b();
    this.i = new GifStreamDecoder.GifFrame(this.h.x, this.h.E);
    if (this.h.D)
      this.h.h[this.h.F] = k;
    r();
  }

  protected void o()
  {
    this.h.a = q();
    this.h.b = q();
    int j = i();
    GifStreamDecoder.State localState = this.h;
    if ((j & 0x80) != 0);
    for (boolean bool = true; ; bool = false)
    {
      localState.c = bool;
      this.h.d = (2 << (j & 0x7));
      this.h.i = i();
      this.h.l = i();
      return;
    }
  }

  protected void p()
  {
    do
    {
      j();
      if (this.h.z[0] != 1)
        continue;
      int j = 0xFF & this.h.z[1];
      int k = 0xFF & this.h.z[2];
      this.h.e = (j | k << 8);
    }
    while ((this.h.A > 0) && (!g()));
  }

  protected int q()
  {
    return i() | i() << 8;
  }

  protected void r()
  {
    this.h.C = this.h.B;
    this.h.t = this.h.p;
    this.h.u = this.h.q;
    this.h.v = this.h.r;
    this.h.w = this.h.s;
    this.h.y = this.h.x;
    this.h.k = this.h.j;
    this.h.B = 0;
    this.h.D = false;
    this.h.E = 0;
    this.h.g = null;
  }

  protected void s()
  {
    do
      j();
    while ((this.h.A > 0) && (!g()));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.gif.GifStreamDecoder
 * JD-Core Version:    0.6.0
 */
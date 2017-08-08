package com.tencent.apkupdate.a;

import java.io.DataInputStream;

public final class c
{
  short a;
  short b;
  short c;
  short d;
  int e;
  int f;
  short g;
  byte[] h;

  public final void a(DataInputStream paramDataInputStream)
  {
    int i = paramDataInputStream.readShort();
    int j = i & 0xFF;
    this.a = (short)(0xFF & i >> 8 | j << 8);
    int k = paramDataInputStream.readShort();
    int m = k & 0xFF;
    this.b = (short)(0xFF & k >> 8 | m << 8);
    int n = paramDataInputStream.readShort();
    int i1 = n & 0xFF;
    this.c = (short)(0xFF & n >> 8 | i1 << 8);
    int i2 = paramDataInputStream.readShort();
    int i3 = i2 & 0xFF;
    this.d = (short)(0xFF & i2 >> 8 | i3 << 8);
    this.e = a.a(paramDataInputStream.readInt());
    this.f = a.a(paramDataInputStream.readInt());
    int i4 = paramDataInputStream.readShort();
    int i5 = i4 & 0xFF;
    this.g = (short)(0xFF & i4 >> 8 | i5 << 8);
    this.h = new byte[this.g];
    paramDataInputStream.read(this.h, 0, this.g);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.a.c
 * JD-Core Version:    0.6.0
 */
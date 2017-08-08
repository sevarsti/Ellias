package com.tencent.apkupdate.a;

import java.io.DataOutputStream;

public final class f
{
  int a;
  short b;
  short c;
  byte[] d;
  byte[] e;

  public static void a(b paramb, DataOutputStream paramDataOutputStream)
  {
    paramb.q = paramDataOutputStream.size();
    paramDataOutputStream.writeInt(1347093252);
    int i = paramb.b;
    int j = i & 0xFF;
    paramDataOutputStream.writeShort((short)(0xFF & i >> 8 | j << 8));
    int k = paramb.c;
    int m = k & 0xFF;
    paramDataOutputStream.writeShort((short)(0xFF & k >> 8 | m << 8));
    int n = paramb.d;
    int i1 = n & 0xFF;
    paramDataOutputStream.writeShort((short)(0xFF & n >> 8 | i1 << 8));
    int i2 = paramb.e;
    int i3 = i2 & 0xFF;
    paramDataOutputStream.writeShort((short)(0xFF & i2 >> 8 | i3 << 8));
    int i4 = paramb.f;
    int i5 = i4 & 0xFF;
    paramDataOutputStream.writeShort((short)(0xFF & i4 >> 8 | i5 << 8));
    if ((0x8 & paramb.c) == 8)
    {
      paramDataOutputStream.writeInt(a.a(0));
      paramDataOutputStream.writeInt(a.a(0));
      paramDataOutputStream.writeInt(a.a(0));
    }
    while (true)
    {
      int i6 = paramb.j;
      int i7 = i6 & 0xFF;
      paramDataOutputStream.writeShort((short)(0xFF & i6 >> 8 | i7 << 8));
      int i8 = paramb.k;
      int i9 = i8 & 0xFF;
      paramDataOutputStream.writeShort((short)(0xFF & i8 >> 8 | i9 << 8));
      if (paramb.j > 0)
        paramDataOutputStream.write(paramb.s);
      if (paramb.k > 0)
        paramDataOutputStream.write(paramb.t);
      return;
      paramDataOutputStream.writeInt(a.a(paramb.g));
      paramDataOutputStream.writeInt(a.a(paramb.h));
      paramDataOutputStream.writeInt(a.a(paramb.i));
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.a.f
 * JD-Core Version:    0.6.0
 */
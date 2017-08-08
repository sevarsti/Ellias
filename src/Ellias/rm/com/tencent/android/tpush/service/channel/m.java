package com.tencent.android.tpush.service.channel;

import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.c.d;
import java.util.Random;

public class m
{
  private static int e = new Random().nextInt();
  public long a = 9223372036854775807L;
  public long b = 9223372036854775807L;
  public JceStruct c = null;
  public n d;
  private int f = 0;
  private short g;

  public m(JceStruct paramJceStruct, n paramn)
  {
    this.g = d.a(paramJceStruct.getClass());
    this.c = paramJceStruct;
    this.d = paramn;
  }

  public m(short paramShort, JceStruct paramJceStruct, n paramn)
  {
    this.g = paramShort;
    this.c = paramJceStruct;
    this.d = paramn;
  }

  public void a(h paramh)
  {
    paramh.a(this.g);
    switch (0x7F & this.g)
    {
    default:
      paramh.b(1);
      JceOutputStream localJceOutputStream = new JceOutputStream();
      localJceOutputStream.setServerEncoding("UTF-8");
      this.c.writeTo(localJceOutputStream);
      paramh.a(localJceOutputStream.toByteArray());
      return;
    case 7:
    }
    paramh.b(20);
  }

  public boolean a()
  {
    return (0x7F & this.g) == 7;
  }

  public int b()
  {
    int i = 1 + e;
    e = i;
    this.f = i;
    return this.f;
  }

  public int c()
  {
    return this.f;
  }

  public String toString()
  {
    if (this.c == null)
      return "null";
    return this.c.getClass().getSimpleName() + ":" + this.c + ", " + this.d;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.m
 * JD-Core Version:    0.6.0
 */
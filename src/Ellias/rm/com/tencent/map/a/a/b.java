package com.tencent.map.a.a;

import com.tencent.map.b.q;

public class b
{
  private int a = 1;
  private int b = 0;
  private int c = 12;
  private int d = 1;

  public b(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    q.a("argument: " + this.a + " " + this.d + " " + this.b);
    if ((paramInt1 >= 0) && (paramInt1 <= 1))
      this.a = paramInt1;
    if ((paramInt2 >= 0) && (paramInt2 <= 1))
      this.d = paramInt2;
    if ((paramInt3 == 0) || (paramInt3 == 3) || (paramInt3 == 4) || (paramInt3 == 7))
      this.b = paramInt3;
    if (this.d == 0)
      this.b = 0;
    this.c = paramInt4;
  }

  public int a()
  {
    return this.a;
  }

  public void a(int paramInt)
  {
  }

  public void a(d paramd)
  {
  }

  public void a(byte[] paramArrayOfByte, int paramInt)
  {
  }

  public int b()
  {
    return this.b;
  }

  public int c()
  {
    return this.d;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.map.a.a.b
 * JD-Core Version:    0.6.0
 */
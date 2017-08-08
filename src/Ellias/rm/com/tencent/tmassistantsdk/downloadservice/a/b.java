package com.tencent.tmassistantsdk.downloadservice.a;

public class b
{
  public String a = null;
  public String b = null;
  public int c = 0;
  public long d = 0L;
  public long e = 0L;
  public long f = 0L;

  public b(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.b = paramString2;
  }

  public boolean a(long paramLong1, long paramLong2, long paramLong3)
  {
    if (paramLong1 == paramLong2)
    {
      this.d = paramLong1;
      this.e = paramLong2;
      this.f = paramLong3;
      return true;
    }
    if ((paramLong3 - this.f >= 1000L) && (paramLong1 != this.d))
    {
      this.d = paramLong1;
      this.e = paramLong2;
      this.f = paramLong3;
      return true;
    }
    if ((paramLong2 > 0L) && (1.0F * ((1.0F * (float)paramLong1 - 1.0F * (float)this.d) / (float)paramLong2) > 0.009999999776482582D))
    {
      this.d = paramLong1;
      this.e = paramLong2;
      this.f = paramLong3;
      return true;
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.a.b
 * JD-Core Version:    0.6.0
 */
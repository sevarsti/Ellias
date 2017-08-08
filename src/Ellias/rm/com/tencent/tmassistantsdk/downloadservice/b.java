package com.tencent.tmassistantsdk.downloadservice;

import android.content.res.Resources.NotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class b
{
  protected static final Pattern c = Pattern.compile("^\\s*([^=\\s]+)\\s*=\\s*(\\d*)\\s*-\\s*(\\d*)((?:\\s*,\\s*(?:\\d*)-(?:\\d*))*)\\s*$");
  protected static final Pattern d = Pattern.compile("^\\s*bytes\\s+(\\d+)-(\\d+)/(\\d+)\\s*$");
  protected final long a;
  protected final Long b;

  public b(long paramLong1, long paramLong2)
  {
    this(paramLong1, Long.valueOf(paramLong2));
    if (paramLong1 < 0L)
      throw new IllegalArgumentException("If end is provided, start must be positive.");
    if (paramLong2 < paramLong1)
      throw new IllegalArgumentException("end must be >= start.");
  }

  protected b(long paramLong, Long paramLong1)
  {
    this.a = paramLong;
    this.b = paramLong1;
  }

  public static b a(String paramString)
  {
    Matcher localMatcher = d.matcher(paramString);
    if (!localMatcher.matches())
      throw new Throwable("Invalid content-range format: " + paramString);
    return new b(Long.parseLong(localMatcher.group(1)), Long.parseLong(localMatcher.group(2)));
  }

  public static long b(String paramString)
  {
    int i = paramString.indexOf("/");
    if (i == -1)
      throw new Resources.NotFoundException();
    return Long.parseLong(paramString.substring(i + 1));
  }

  public boolean a()
  {
    return this.b != null;
  }

  public long b()
  {
    return this.a;
  }

  public long c()
  {
    if (!a())
      throw new IllegalStateException("Byte-range does not have end.  Check hasEnd() before use");
    return this.b.longValue();
  }

  public boolean equals(Object paramObject)
  {
    b localb;
    if ((paramObject instanceof b))
    {
      localb = (b)paramObject;
      if (this.a == localb.b())
        break label26;
    }
    label26: 
    do
      return false;
    while (a() != localb.a());
    if (a())
      return this.b.equals(Long.valueOf(localb.c()));
    return true;
  }

  public int hashCode()
  {
    int i = 629 + Long.valueOf(this.a).hashCode();
    if (this.b != null)
      i = i * 37 + this.b.hashCode();
    return i;
  }

  public String toString()
  {
    if (this.b != null)
      return "bytes=" + this.a + "-" + this.b;
    if (this.a < 0L)
      return "bytes=" + this.a;
    return "bytes=" + this.a + "-";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.b
 * JD-Core Version:    0.6.0
 */
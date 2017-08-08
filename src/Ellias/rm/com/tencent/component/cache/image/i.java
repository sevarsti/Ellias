package com.tencent.component.cache.image;

import com.tencent.component.utils.AssertUtil;
import java.io.File;

final class i
{
  private final String a;
  private final long b;
  private final long c;

  public i(String paramString)
  {
    if (paramString != null);
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      this.a = paramString;
      File localFile = new File(paramString);
      this.b = localFile.length();
      this.c = localFile.lastModified();
      return;
    }
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    i locali;
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof i)))
        return false;
      locali = (i)paramObject;
    }
    while ((this.a.equals(locali.a)) && (this.b == locali.b) && (this.c == locali.c));
    return false;
  }

  public int hashCode()
  {
    return 31 * (31 * (527 + this.a.hashCode()) + (int)(this.b ^ this.b >>> 32)) + (int)(this.c ^ this.c >>> 32);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.i
 * JD-Core Version:    0.6.0
 */
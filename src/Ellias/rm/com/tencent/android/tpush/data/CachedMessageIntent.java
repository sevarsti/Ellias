package com.tencent.android.tpush.data;

import java.io.Serializable;

public class CachedMessageIntent
  implements Serializable
{
  private static final long serialVersionUID = 1724218633838690967L;
  public String intent = "";
  public long msgId;
  public String pkgName = "";

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof CachedMessageIntent))
      return (((CachedMessageIntent)paramObject).pkgName.equals(this.pkgName)) && (((CachedMessageIntent)paramObject).intent.equals(this.intent));
    return super.equals(paramObject);
  }

  public int hashCode()
  {
    return super.hashCode();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.data.CachedMessageIntent
 * JD-Core Version:    0.6.0
 */
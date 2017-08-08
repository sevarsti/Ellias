package com.tencent.component.net.http.download;

import com.tencent.component.net.NetworkManager;

class e
{
  public boolean a = false;
  public boolean b = false;

  public e()
  {
    if (NetworkManager.b())
    {
      this.a = true;
      this.b = true;
      return;
    }
    this.a = false;
    this.b = false;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    e locale;
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof e)))
        return false;
      locale = (e)paramObject;
    }
    while ((this.a == locale.a) && (this.b) && (locale.b));
    return false;
  }

  public int hashCode()
  {
    int i = 1;
    int j;
    int k;
    if (this.a)
    {
      j = i;
      k = 31 * (j + 527);
      if (!this.b)
        break label36;
    }
    while (true)
    {
      return k + i;
      j = 0;
      break;
      label36: i = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.download.e
 * JD-Core Version:    0.6.0
 */
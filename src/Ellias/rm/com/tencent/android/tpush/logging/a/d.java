package com.tencent.android.tpush.logging.a;

import android.text.format.Time;
import java.io.File;
import java.io.FileFilter;

class d
  implements FileFilter
{
  d(b paramb)
  {
  }

  public boolean accept(File paramFile)
  {
    String str = paramFile.getName();
    if (!str.endsWith(this.a.j()));
    Time localTime;
    do
    {
      return false;
      localTime = new Time();
      localTime.set(System.currentTimeMillis());
    }
    while ((b.d(paramFile) == -1) || (!str.startsWith(String.valueOf(localTime.hour))));
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.a.d
 * JD-Core Version:    0.6.0
 */
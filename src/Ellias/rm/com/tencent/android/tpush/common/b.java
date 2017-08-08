package com.tencent.android.tpush.common;

import android.net.wifi.ScanResult;
import java.util.Comparator;

final class b
  implements Comparator
{
  public int a(ScanResult paramScanResult1, ScanResult paramScanResult2)
  {
    int i = Math.abs(paramScanResult1.level);
    int j = Math.abs(paramScanResult2.level);
    if (i > j)
      return 1;
    if (i == j)
      return 0;
    return -1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.common.b
 * JD-Core Version:    0.6.0
 */
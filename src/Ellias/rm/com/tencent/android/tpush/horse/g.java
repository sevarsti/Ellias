package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.report.ReportItem;
import com.tencent.android.tpush.service.report.e;

public class g
{
  public static int a(StrategyItem paramStrategyItem1, StrategyItem paramStrategyItem2, boolean paramBoolean)
  {
    int i = 21;
    if (paramStrategyItem1 == null)
      i = -1;
    while (true)
    {
      return i;
      if (paramStrategyItem1.d() != 1)
        break label145;
      if (paramStrategyItem1.f() == 1)
      {
        if (paramStrategyItem1.h())
        {
          if (paramBoolean)
          {
            if (paramStrategyItem2 != null)
              return 16;
            return 17;
          }
          return 15;
        }
        if (paramBoolean)
        {
          if (paramStrategyItem2 != null)
            return 9;
          return 10;
        }
        return 8;
      }
      if (!paramStrategyItem1.h())
        break label107;
      if (!paramBoolean)
        break label104;
      if (paramStrategyItem2 == null)
        continue;
      if (!paramStrategyItem2.g())
        break;
      if (paramStrategyItem2.equals(paramStrategyItem1))
        return 19;
    }
    return 20;
    label104: return 18;
    label107: if (paramBoolean)
    {
      if (paramStrategyItem2 == null)
        return 14;
      if (paramStrategyItem2.g())
      {
        if (paramStrategyItem2.equals(paramStrategyItem1))
          return 12;
        return 14;
      }
      return 13;
    }
    return 11;
    label145: if (paramStrategyItem1.f() == 1)
    {
      if (paramBoolean)
      {
        if (paramStrategyItem2 != null)
          return 2;
        return 3;
      }
      return 1;
    }
    if (paramBoolean)
    {
      if (paramStrategyItem2 == null)
        return 7;
      if (paramStrategyItem2.g())
      {
        if (paramStrategyItem2.equals(paramStrategyItem1))
          return 5;
        return 7;
      }
      return 6;
    }
    return 4;
  }

  public static void a(int paramInt1, int paramInt2, int paramInt3, long paramLong, StrategyItem paramStrategyItem1, StrategyItem paramStrategyItem2)
  {
    StringBuilder localStringBuilder1 = new StringBuilder().append("strategyItem1:");
    String str1;
    String str2;
    label44: String str3;
    int i;
    if (paramStrategyItem1 == null)
    {
      str1 = null;
      StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append(",strategyItem2=");
      str2 = null;
      if (paramStrategyItem2 != null)
        break label111;
      str3 = str2;
      if (paramStrategyItem1 != null)
        break label121;
      i = 0;
      label64: if (paramStrategyItem1 != null)
        break label131;
    }
    label131: for (String str4 = "0"; ; str4 = paramStrategyItem1.a())
    {
      ReportItem localReportItem = new ReportItem(i, paramInt1, paramInt2, paramInt3, str4, paramLong, str3);
      e.a().a(localReportItem);
      return;
      str1 = paramStrategyItem1.toString();
      break;
      label111: str2 = paramStrategyItem2.toString();
      break label44;
      label121: i = paramStrategyItem1.d();
      break label64;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.g
 * JD-Core Version:    0.6.0
 */
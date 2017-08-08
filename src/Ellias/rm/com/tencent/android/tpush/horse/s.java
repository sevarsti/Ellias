package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.a;
import com.tencent.android.tpush.service.i;
import java.util.List;

public class s
{
  private static StrategyItem a(String paramString, int paramInt1, int paramInt2)
  {
    StrategyItem localStrategyItem1;
    if ((paramString == null) || (paramInt1 == 0))
      localStrategyItem1 = null;
    do
    {
      return localStrategyItem1;
      localStrategyItem1 = new StrategyItem(paramString, paramInt1, "", 80, paramInt2, 0);
    }
    while ((!a.e(i.e())) || (paramInt2 != 1));
    String str1 = a.b(i.e());
    String str2 = a.d(i.e());
    int i = 80;
    try
    {
      int j = Integer.parseInt(str2);
      i = j;
      StrategyItem localStrategyItem2 = new StrategyItem(paramString, paramInt1, str1, i, paramInt2, 0);
      TLog.i("XGService", ">> wapStrategyItem=" + localStrategyItem2.toString());
      return localStrategyItem1;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
        TLog.e("XGService", localNumberFormatException.toString());
    }
  }

  public static List a(List paramList, String paramString)
  {
    return a(paramList, 0, paramString);
  }

  // ERROR //
  private static List a(List paramList, short paramShort, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: ldc 42
    //   4: ldc 77
    //   6: invokestatic 80	com/tencent/android/tpush/logging/TLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   9: aload_0
    //   10: ifnonnull +13 -> 23
    //   13: new 73	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   16: dup
    //   17: ldc 82
    //   19: invokespecial 85	com/tencent/android/tpush/service/channel/exception/NullReturnException:<init>	(Ljava/lang/String;)V
    //   22: athrow
    //   23: new 87	java/util/ArrayList
    //   26: dup
    //   27: invokespecial 88	java/util/ArrayList:<init>	()V
    //   30: astore 4
    //   32: invokestatic 22	com/tencent/android/tpush/service/i:e	()Landroid/content/Context;
    //   35: aload_2
    //   36: invokestatic 94	com/tencent/android/tpush/service/cache/CacheManager:getOptStrategyList	(Landroid/content/Context;Ljava/lang/String;)Lcom/tencent/android/tpush/horse/data/OptStrategyList;
    //   39: invokevirtual 99	com/tencent/android/tpush/horse/data/OptStrategyList:e	()Lcom/tencent/android/tpush/horse/data/StrategyItem;
    //   42: astore 13
    //   44: aload 13
    //   46: astore 6
    //   48: aload 6
    //   50: iconst_0
    //   51: invokevirtual 102	com/tencent/android/tpush/horse/data/StrategyItem:a	(I)V
    //   54: aload 6
    //   56: invokevirtual 105	com/tencent/android/tpush/horse/data/StrategyItem:d	()I
    //   59: iload_1
    //   60: if_icmpne +13 -> 73
    //   63: aload 4
    //   65: aload 6
    //   67: invokeinterface 111 2 0
    //   72: pop
    //   73: aload 6
    //   75: astore 8
    //   77: iload_3
    //   78: aload_0
    //   79: invokeinterface 114 1 0
    //   84: if_icmpge +122 -> 206
    //   87: aload_0
    //   88: iload_3
    //   89: invokeinterface 118 2 0
    //   94: checkcast 120	com/tencent/android/tpush/horse/data/ServerItem
    //   97: invokevirtual 122	com/tencent/android/tpush/horse/data/ServerItem:a	()Ljava/lang/String;
    //   100: aload_0
    //   101: iload_3
    //   102: invokeinterface 118 2 0
    //   107: checkcast 120	com/tencent/android/tpush/horse/data/ServerItem
    //   110: invokevirtual 124	com/tencent/android/tpush/horse/data/ServerItem:b	()I
    //   113: iload_1
    //   114: invokestatic 126	com/tencent/android/tpush/horse/s:a	(Ljava/lang/String;II)Lcom/tencent/android/tpush/horse/data/StrategyItem;
    //   117: astore 9
    //   119: aload 9
    //   121: ifnull +23 -> 144
    //   124: aload 9
    //   126: aload 8
    //   128: invokevirtual 129	com/tencent/android/tpush/horse/data/StrategyItem:equals	(Ljava/lang/Object;)Z
    //   131: ifne +13 -> 144
    //   134: aload 4
    //   136: aload 9
    //   138: invokeinterface 111 2 0
    //   143: pop
    //   144: iinc 3 1
    //   147: goto -70 -> 77
    //   150: astore 11
    //   152: aconst_null
    //   153: astore 6
    //   155: aload 11
    //   157: astore 12
    //   159: ldc 42
    //   161: aload 12
    //   163: invokevirtual 130	com/tencent/android/tpush/service/channel/exception/NullReturnException:toString	()Ljava/lang/String;
    //   166: invokestatic 67	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   169: aload 6
    //   171: astore 8
    //   173: iconst_0
    //   174: istore_3
    //   175: goto -98 -> 77
    //   178: astore 5
    //   180: aconst_null
    //   181: astore 6
    //   183: aload 5
    //   185: astore 7
    //   187: ldc 42
    //   189: aload 7
    //   191: invokevirtual 131	java/lang/Exception:toString	()Ljava/lang/String;
    //   194: invokestatic 67	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   197: aload 6
    //   199: astore 8
    //   201: iconst_0
    //   202: istore_3
    //   203: goto -126 -> 77
    //   206: aload 4
    //   208: areturn
    //   209: astore 7
    //   211: goto -24 -> 187
    //   214: astore 12
    //   216: goto -57 -> 159
    //
    // Exception table:
    //   from	to	target	type
    //   32	44	150	com/tencent/android/tpush/service/channel/exception/NullReturnException
    //   32	44	178	java/lang/Exception
    //   48	73	209	java/lang/Exception
    //   48	73	214	com/tencent/android/tpush/service/channel/exception/NullReturnException
  }

  public static List b(List paramList, String paramString)
  {
    return a(paramList, 1, paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.s
 * JD-Core Version:    0.6.0
 */
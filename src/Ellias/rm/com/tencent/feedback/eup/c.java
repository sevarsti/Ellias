package com.tencent.feedback.eup;

import android.content.Context;
import com.qq.taf.jce.JceInputStream;
import com.tencent.feedback.common.b.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import strategy.CrashStrategy;

public class c
  implements com.tencent.feedback.upload.e
{
  private Context a;

  public c(Context paramContext)
  {
    this.a = paramContext;
  }

  public static int a(Context paramContext)
  {
    com.tencent.feedback.common.e.b("rqdp{  EUPDAO.querySum() start}", new Object[0]);
    if (paramContext == null)
    {
      com.tencent.feedback.common.e.c("rqdp{  querySum() context is null arg}", new Object[0]);
      return -1;
    }
    return com.tencent.feedback.common.a.a.a(paramContext, new int[] { 2, 1 }, -1L, 9223372036854775807L, null);
  }

  public static int a(Context paramContext, List<d> paramList)
  {
    com.tencent.feedback.common.e.b("rqdp{  EUPDAO.deleteEupList() start}", new Object[0]);
    int j;
    if (paramContext == null)
    {
      com.tencent.feedback.common.e.c("rqdp{  deleteEupList() have null args!}", new Object[0]);
      j = -1;
    }
    int i;
    do
    {
      return j;
      i = paramList.size();
      j = 0;
    }
    while (i <= 0);
    Long[] arrayOfLong = new Long[paramList.size()];
    for (int k = 0; k < paramList.size(); k++)
      arrayOfLong[k] = Long.valueOf(((d)paramList.get(k)).a());
    return com.tencent.feedback.common.a.a.a(paramContext, arrayOfLong);
  }

  protected static com.tencent.feedback.common.a.a a(d paramd)
  {
    if (paramd == null)
      return null;
    while (true)
    {
      try
      {
        if (paramd.b())
        {
          i = 1;
          com.tencent.feedback.common.a.a locala = new com.tencent.feedback.common.a.a(i, 0, paramd.i(), com.tencent.feedback.anr.a.a(paramd));
          locala.b(paramd.o());
          locala.a(paramd.r());
          locala.a(paramd.t());
          locala.a(paramd.a());
          if (!paramd.B())
            break label100;
          j = 1;
          locala.c(j);
          return locala;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return null;
      }
      int i = 2;
      continue;
      label100: int j = 0;
    }
  }

  public static List<d> a(Context paramContext, int paramInt1, String paramString1, int paramInt2, String paramString2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2, Boolean paramBoolean)
  {
    com.tencent.feedback.common.e.b("rqdp{  EUPDAO.queryEupRecent() start}", new Object[0]);
    if ((paramContext == null) || (paramInt1 == 0) || ((paramLong2 > 0L) && (paramLong1 > paramLong2)) || ((paramInt4 > 0) && (paramInt3 > paramInt4)))
    {
      com.tencent.feedback.common.e.c("rqdp{  context == null || limitNum == 0 || (timeEnd > 0 && timeStart > timeEnd) || (maxCount > 0 && miniCount > maxCount ,pls check}", new Object[0]);
      return null;
    }
    int i;
    int[] arrayOfInt;
    label81: int j;
    if ("asc".equals(paramString1))
    {
      i = 1;
      if (paramInt2 != 2)
        break label143;
      arrayOfInt = new int[] { 2 };
      if (paramBoolean != null)
        break label195;
      j = -1;
    }
    List localList;
    while (true)
    {
      localList = com.tencent.feedback.common.a.a.a(paramContext, arrayOfInt, -1, i, -1L, paramInt1, paramString2, paramInt3, paramInt4, paramInt5, paramInt6, paramLong1, paramLong2, j);
      if ((localList != null) && (localList.size() > 0))
        break label215;
      return null;
      i = 2;
      break;
      label143: if (paramInt2 == 1)
      {
        arrayOfInt = new int[] { 1 };
        break label81;
      }
      if (paramInt2 < 0)
      {
        arrayOfInt = new int[] { 1, 2 };
        break label81;
      }
      com.tencent.feedback.common.e.c("rqdp{  queryEupRecent() seletedRecordType unaccepted}", new Object[0]);
      arrayOfInt = null;
      break label81;
      label195: if (paramBoolean.booleanValue())
      {
        j = 1;
        continue;
      }
      j = 0;
    }
    label215: ArrayList localArrayList = new ArrayList();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      com.tencent.feedback.common.a.a locala = (com.tencent.feedback.common.a.a)localIterator.next();
      try
      {
        Object localObject = com.tencent.feedback.anr.a.a(locala.b());
        if ((localObject == null) || (!d.class.isInstance(localObject)))
          continue;
        d locald = (d)d.class.cast(localObject);
        locald.a(locala.a());
        localArrayList.add(locald);
        localIterator.remove();
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        com.tencent.feedback.common.e.d("rqdp{  query have error!}", new Object[0]);
      }
    }
    if (localList.size() > 0)
    {
      com.tencent.feedback.common.e.b("rqdp{  there are error datas ,should be remove }" + localList.size(), new Object[0]);
      Long[] arrayOfLong = new Long[localList.size()];
      for (int k = 0; k < localList.size(); k++)
        arrayOfLong[k] = Long.valueOf(((com.tencent.feedback.common.a.a)localList.get(k)).a());
      com.tencent.feedback.common.a.a.a(paramContext, arrayOfLong);
    }
    com.tencent.feedback.common.e.b("rqdp{  EUPDAO.queryEupRecent() end}", new Object[0]);
    return localArrayList;
  }

  public static boolean a(Context paramContext, d paramd)
  {
    com.tencent.feedback.common.e.b("rqdp{  EUPDAO.insertEUP() start}", new Object[0]);
    if ((paramContext == null) || (paramd == null))
    {
      com.tencent.feedback.common.e.c("rqdp{  EUPDAO.insertEUP() have null args}", new Object[0]);
      return false;
    }
    try
    {
      com.tencent.feedback.common.a.a locala = a(paramd);
      if ((paramContext == null) || (locala == null))
        com.tencent.feedback.common.e.a("rqdp{  AnalyticsDAO.insert() have null args}", new Object[0]);
      boolean bool;
      for (int i = 0; i != 0; i = bool)
      {
        paramd.a(locala.a());
        return true;
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(locala);
        bool = com.tencent.feedback.common.a.a.a(paramContext, localArrayList);
      }
      return false;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      com.tencent.feedback.common.e.d("rqdp{  insert fail!}", new Object[0]);
      return false;
    }
    finally
    {
      com.tencent.feedback.common.e.b("rqdp{  EUPDAO.insertEUP() end}", new Object[0]);
    }
    throw localObject;
  }

  public static boolean b(Context paramContext, List<d> paramList)
  {
    com.tencent.feedback.common.e.b("rqdp{  EUPDAO.insertOrUpdateEupList() start}", new Object[0]);
    if ((paramContext == null) || (paramList == null) || (paramList.size() <= 0))
    {
      com.tencent.feedback.common.e.c("rqdp{  context == null ||| list == null || list.size() <= 0,pls check}", new Object[0]);
      return false;
    }
    try
    {
      localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        com.tencent.feedback.common.a.a locala = a((d)localIterator.next());
        if (locala == null)
          continue;
        localArrayList.add(locala);
      }
    }
    catch (Throwable localThrowable)
    {
      ArrayList localArrayList;
      localThrowable.printStackTrace();
      com.tencent.feedback.common.e.d("rqdp{  insert fail!}", new Object[0]);
      return false;
      boolean bool = com.tencent.feedback.common.a.a.b(paramContext, localArrayList);
      return bool;
    }
    finally
    {
      com.tencent.feedback.common.e.b("rqdp{  EUPDAO.insertOrUpdateEupList() end}", new Object[0]);
    }
    throw localObject;
  }

  public final void a(int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if ((paramInt == 302) && (paramArrayOfByte != null));
    while (true)
    {
      CrashStrategy localCrashStrategy;
      CrashStrategyBean localCrashStrategyBean1;
      CrashStrategyBean localCrashStrategyBean2;
      CrashStrategyBean localCrashStrategyBean4;
      try
      {
        e locale = e.k();
        if (locale != null)
          continue;
        com.tencent.feedback.common.e.c("rqdp{  imposiable handle response ,but no eup instance!}", new Object[0]);
        return;
        localCrashStrategy = new CrashStrategy();
        localCrashStrategy.readFrom(new JceInputStream(paramArrayOfByte));
        localCrashStrategyBean1 = locale.o();
        if (localCrashStrategyBean1 != null)
          break label451;
        localCrashStrategyBean2 = locale.n();
        if (localCrashStrategyBean2 == null)
        {
          com.tencent.feedback.common.e.f("rqdp{  init eup sStrategy by default}", new Object[0]);
          localCrashStrategyBean3 = new CrashStrategyBean();
          locale.a(localCrashStrategyBean3);
          localCrashStrategyBean4 = localCrashStrategyBean3;
          break label459;
          if ((i == 0) || (!paramBoolean))
            continue;
          com.tencent.feedback.common.e.f("rqdp{  save eup strategy}", new Object[0]);
          Context localContext = this.a;
          if (paramArrayOfByte == null)
            continue;
          i locali = new i();
          locali.a(paramInt);
          locali.a(paramArrayOfByte);
          com.tencent.feedback.anr.a.a(localContext, locali);
          com.tencent.feedback.common.e.h("rqdp{  crashStrategy}[%s]", new Object[] { localCrashStrategy });
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = localThrowable.toString();
        com.tencent.feedback.common.e.d("rqdp{  process crash strategy error} %s", arrayOfObject1);
        return;
      }
      com.tencent.feedback.common.e.f("rqdp{  init eup sStrategy by uStrategy}", new Object[0]);
      CrashStrategyBean localCrashStrategyBean3 = localCrashStrategyBean2.clone();
      continue;
      label451: label459: 
      do
      {
        if (localCrashStrategyBean4.isMerged() != localCrashStrategy.isMerge)
        {
          Object[] arrayOfObject5 = new Object[1];
          arrayOfObject5[0] = Boolean.valueOf(localCrashStrategy.isMerge);
          com.tencent.feedback.common.e.h("rqdp{  is merged changed} %b", arrayOfObject5);
          localCrashStrategyBean4.setMerged(localCrashStrategy.isMerge);
        }
        for (i = 1; ; i = 0)
        {
          if (localCrashStrategyBean4.isAssertOn() != localCrashStrategy.useAssert)
          {
            localCrashStrategyBean4.setAssertEnable(localCrashStrategy.useAssert);
            Object[] arrayOfObject4 = new Object[1];
            arrayOfObject4[0] = Boolean.valueOf(localCrashStrategy.useAssert);
            com.tencent.feedback.common.e.h("rqdp{ Assert enable changed: } %s", arrayOfObject4);
            i = 1;
          }
          if (localCrashStrategyBean4.getAssertTaskInterval() != localCrashStrategy.assertUploadTime)
          {
            localCrashStrategyBean4.setAssertTaskInterval(localCrashStrategy.assertUploadTime);
            Object[] arrayOfObject3 = new Object[1];
            arrayOfObject3[0] = Integer.valueOf(localCrashStrategy.assertUploadTime);
            com.tencent.feedback.common.e.h("rqdp{ Assert task interval changed: } %s", arrayOfObject3);
            i = 1;
          }
          if (localCrashStrategyBean4.getAssertLimitCount() == localCrashStrategy.assertUploadCount)
            break;
          localCrashStrategyBean4.setAssertLimitCount(localCrashStrategy.assertUploadCount);
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Integer.valueOf(localCrashStrategy.assertUploadCount);
          com.tencent.feedback.common.e.h("rqdp{ Assert limit count changed: } %s", arrayOfObject2);
          i = 1;
          break;
        }
        localCrashStrategyBean4 = localCrashStrategyBean1;
        continue;
        return;
      }
      while ((localCrashStrategy != null) && (localCrashStrategyBean4 != null));
      int i = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.eup.c
 * JD-Core Version:    0.6.0
 */
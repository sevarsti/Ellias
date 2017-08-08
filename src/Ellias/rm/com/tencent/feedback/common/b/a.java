package com.tencent.feedback.common.b;

import android.content.Context;
import android.util.SparseArray;
import com.qq.taf.jce.JceInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import strategy.CommonStrategy;
import strategy.ModuleStrategy;
import strategy.SecurityStrategy;

public final class a
  implements com.tencent.feedback.upload.e
{
  private Context a = null;

  public a(Context paramContext)
  {
    this.a = paramContext;
  }

  private static boolean a(ArrayList<ModuleStrategy> paramArrayList, f paramf)
  {
    if (paramf == null)
      return false;
    int n;
    int i;
    f.a locala2;
    int i1;
    boolean bool1;
    label60: boolean bool2;
    int i2;
    if (paramArrayList != null)
    {
      SparseArray localSparseArray2 = paramf.j();
      if (localSparseArray2 != null)
      {
        n = 0;
        i = 0;
        if (n >= localSparseArray2.size())
          break label300;
        locala2 = (f.a)localSparseArray2.valueAt(n);
        Iterator localIterator2 = paramArrayList.iterator();
        i1 = i;
        bool1 = false;
        if (localIterator2.hasNext())
        {
          ModuleStrategy localModuleStrategy2 = (ModuleStrategy)localIterator2.next();
          if (localModuleStrategy2.mId != locala2.a)
            break label482;
          if (locala2.d() != localModuleStrategy2.needDetail)
          {
            Object[] arrayOfObject5 = new Object[2];
            arrayOfObject5[0] = Integer.valueOf(localModuleStrategy2.mId);
            arrayOfObject5[1] = Boolean.valueOf(localModuleStrategy2.needDetail);
            com.tencent.feedback.common.e.b("rqdp{  mid:}%d rqdp{  , need detail changed:}%b ", arrayOfObject5);
            locala2.c(localModuleStrategy2.needDetail);
            i1 = 1;
          }
          if (locala2.a().equals(localModuleStrategy2.getUrl()))
            break label472;
          Object[] arrayOfObject4 = new Object[2];
          arrayOfObject4[0] = Integer.valueOf(localModuleStrategy2.mId);
          arrayOfObject4[1] = localModuleStrategy2.getUrl();
          com.tencent.feedback.common.e.b("rqdp{  mid:}%d rqdp{  , url changed:}%s", arrayOfObject4);
          locala2.a(localModuleStrategy2.getUrl());
          bool2 = true;
          i2 = 1;
        }
      }
    }
    while (true)
    {
      i1 = i2;
      bool1 = bool2;
      break label60;
      if (bool1 != locala2.c())
      {
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = Integer.valueOf(locala2.a);
        arrayOfObject3[1] = Boolean.valueOf(bool1);
        com.tencent.feedback.common.e.b("rqdp{  mid:}%d rqdp{  , enable} %b", arrayOfObject3);
        locala2.b(bool1);
        i1 = 1;
      }
      n++;
      i = i1;
      break;
      i = 0;
      label300: Iterator localIterator1 = paramArrayList.iterator();
      int k;
      int m;
      while (localIterator1.hasNext())
      {
        ModuleStrategy localModuleStrategy1 = (ModuleStrategy)localIterator1.next();
        if (paramf.e(localModuleStrategy1.mId) != null)
          continue;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(localModuleStrategy1.mId);
        com.tencent.feedback.common.e.c("rqdp{  imposiable! module base strategy not ready! mId:}%d", arrayOfObject2);
        continue;
        SparseArray localSparseArray1 = paramf.j();
        if (localSparseArray1 == null)
          break label467;
        int j = 0;
        for (k = 0; j < localSparseArray1.size(); k = m)
        {
          f.a locala1 = (f.a)localSparseArray1.valueAt(j);
          if (!locala1.c())
            break label460;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Integer.valueOf(locala1.e());
          com.tencent.feedback.common.e.b("rqdp{  mid:}%d rqdp{  , server closed}", arrayOfObject1);
          locala1.b(false);
          m = 1;
          j++;
        }
      }
      label460: label467: for (i = k; ; i = 0)
      {
        return i;
        m = k;
        break;
      }
      label472: bool2 = true;
      i2 = i1;
      continue;
      label482: bool2 = bool1;
      i2 = i1;
    }
  }

  public final void a(int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    int i = 1;
    if (paramInt != 300)
    {
      Object[] arrayOfObject8 = new Object[i];
      arrayOfObject8[0] = Integer.valueOf(paramInt);
      com.tencent.feedback.common.e.c("rqdp{  com strategy unmatch key:}%d", arrayOfObject8);
    }
    do
      return;
    while (paramArrayOfByte == null);
    f localf = c.a(this.a).b();
    if (localf == null)
    {
      com.tencent.feedback.common.e.c("rqdp{  imposible! common strategy null!}", new Object[0]);
      return;
    }
    label259: label559: label714: label720: label723: label733: 
    while (true)
    {
      CommonStrategy localCommonStrategy;
      try
      {
        localCommonStrategy = new CommonStrategy();
        localCommonStrategy.readFrom(new JceInputStream(paramArrayOfByte));
        if (localCommonStrategy == null)
          break label714;
        if (localf == null)
        {
          break label714;
          if (i == 0)
            break;
          if (!paramBoolean)
            continue;
          com.tencent.feedback.common.e.e("rqdp{  update common strategy should save}", new Object[0]);
          Context localContext = this.a;
          if (paramArrayOfByte == null)
            continue;
          i locali = new i();
          locali.a(paramInt);
          locali.a(paramArrayOfByte);
          com.tencent.feedback.anr.a.a(localContext, locali);
          com.tencent.feedback.common.e.e("rqdp{  com strategy changed notify!}", new Object[0]);
          c.a(this.a).a(localf);
          com.tencent.feedback.common.a.a(this.a, "SVR_STRATEG_META", "true");
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        com.tencent.feedback.common.e.d("rqdp{  error to common strategy!}", new Object[0]);
        return;
      }
      int j;
      SecurityStrategy localSecurityStrategy;
      if (localCommonStrategy.getUseServer() != localf.i())
      {
        Object[] arrayOfObject7 = new Object[1];
        arrayOfObject7[0] = Boolean.valueOf(localCommonStrategy.getUseServer());
        com.tencent.feedback.common.e.b("rqdp{  useSStrategy changed to} %b", arrayOfObject7);
        localf.a(localCommonStrategy.getUseServer());
        j = i;
        if (!localCommonStrategy.getUrl().equals(localf.a()))
        {
          Object[] arrayOfObject6 = new Object[1];
          arrayOfObject6[0] = localCommonStrategy.getUrl();
          com.tencent.feedback.common.e.b("rqdp{  url changed to} %s", arrayOfObject6);
          localf.a(localCommonStrategy.getUrl());
          j = i;
        }
        if (localCommonStrategy.getUploadStrategy() != localf.b())
        {
          Object[] arrayOfObject5 = new Object[1];
          arrayOfObject5[0] = Integer.valueOf(localCommonStrategy.getUploadStrategy());
          com.tencent.feedback.common.e.b("rqdp{  upStrategy changed to} %d", arrayOfObject5);
          localf.a(localCommonStrategy.getUploadStrategy());
          j = i;
        }
        if (localCommonStrategy.getQueryInterval() != localf.c())
        {
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = Integer.valueOf(localCommonStrategy.getQueryInterval());
          com.tencent.feedback.common.e.b("rqdp{  QueryPeriod changed to} %d", arrayOfObject4);
          localf.b(localCommonStrategy.getQueryInterval());
          j = i;
        }
        if (localCommonStrategy.enforceQuery != localf.k())
        {
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = Boolean.valueOf(localCommonStrategy.enforceQuery);
          com.tencent.feedback.common.e.b("rqdp{  enforceQuery changed to} %b", arrayOfObject3);
          localf.b(localCommonStrategy.enforceQuery);
          j = i;
        }
        localSecurityStrategy = localCommonStrategy.getS();
        if (localSecurityStrategy == null)
          break label720;
        if (localf == null)
          break label720;
      }
      while (true)
      {
        label487: if (a(localCommonStrategy.getModuleList(), localf))
          break label733;
        i = m;
        break;
        if (localSecurityStrategy.encryAlgorithm != localf.f())
        {
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Integer.valueOf(localSecurityStrategy.encryAlgorithm);
          com.tencent.feedback.common.e.b("rqdp{  ea changed:}%d", arrayOfObject2);
          localf.c(localSecurityStrategy.encryAlgorithm);
          k = i;
          if (localSecurityStrategy.zipAlgorithm != localf.g())
          {
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = Integer.valueOf(localSecurityStrategy.zipAlgorithm);
            com.tencent.feedback.common.e.b("rqdp{  za changed:}%d", arrayOfObject1);
            localf.d(localSecurityStrategy.zipAlgorithm);
            k = i;
          }
          if (!localSecurityStrategy.getEncryKey().equals(localf.d()))
          {
            com.tencent.feedback.common.e.b("rqdp{  enk changed}", new Object[0]);
            localf.b(localSecurityStrategy.getEncryKey());
            k = i;
          }
          if (localSecurityStrategy.getEncryPublicKey().equals(localf.e()))
            break label723;
          com.tencent.feedback.common.e.b("rqdp{  enpk changed}", new Object[0]);
          localf.c(localSecurityStrategy.getEncryPublicKey());
        }
        for (int k = i; k == 0; k = 0)
        {
          m = j;
          break label487;
          k = 0;
          break label559;
          j = 0;
          break label259;
          i = 0;
          break;
        }
        int m = i;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.b.a
 * JD-Core Version:    0.6.0
 */
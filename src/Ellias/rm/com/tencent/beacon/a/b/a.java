package com.tencent.beacon.a.b;

import android.content.Context;
import android.util.SparseArray;
import com.tencent.beacon.c.e.d;
import com.tencent.beacon.upload.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class a
  implements f
{
  private Context a = null;

  public a(Context paramContext)
  {
    this.a = paramContext;
  }

  public final void a(int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if (paramInt != 101)
    {
      Object[] arrayOfObject10 = new Object[1];
      arrayOfObject10[0] = Integer.valueOf(paramInt);
      com.tencent.beacon.d.a.c("com strategy unmatch key: %d", arrayOfObject10);
    }
    e locale;
    com.tencent.beacon.c.e.a locala;
    int i;
    while (true)
    {
      return;
      if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
        continue;
      locale = c.a(this.a).b();
      if (locale == null)
      {
        com.tencent.beacon.d.a.c("imposible! common strategy null!", new Object[0]);
        return;
      }
      try
      {
        locala = new com.tencent.beacon.c.e.a();
        locala.a(new com.tencent.beacon.e.a(paramArrayOfByte));
        if (locala == null)
          break label1048;
        if (locale != null)
          break;
        break label1048;
        if (i == 0)
          continue;
        if (paramBoolean)
        {
          com.tencent.beacon.d.a.e("update common strategy should save ", new Object[0]);
          Context localContext = this.a;
          if (paramArrayOfByte != null)
          {
            h localh = new h();
            localh.a(paramInt);
            localh.a(paramArrayOfByte);
            com.tencent.beacon.applog.a.a(localContext, localh);
          }
        }
        com.tencent.beacon.d.a.e("com strategy changed notify! ", new Object[0]);
        c.a(this.a).a(locale);
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        com.tencent.beacon.d.a.d("error to common strategy!", new Object[0]);
        return;
      }
    }
    boolean bool1 = locala.c.equals(locale.b());
    int j = 0;
    if (!bool1)
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = locala.c;
      com.tencent.beacon.d.a.b("strategy url changed to: %s", arrayOfObject1);
      j = 1;
      locale.a(locala.c);
    }
    if (locala.b != locale.c())
    {
      Object[] arrayOfObject9 = new Object[1];
      arrayOfObject9[0] = Integer.valueOf(locala.b);
      com.tencent.beacon.d.a.b("QueryPeriod changed to: %d", arrayOfObject9);
      j = 1;
      locale.a(locala.b);
    }
    ArrayList localArrayList = locala.a;
    int k;
    label322: Map localMap1;
    int n;
    int i1;
    int i2;
    label366: boolean bool3;
    label395: label467: SparseArray localSparseArray2;
    label825: int i3;
    int i4;
    if (locale == null)
    {
      k = 0;
      break label1054;
      localMap1 = locala.d;
      if (locale == null)
      {
        n = 0;
        break label1065;
      }
    }
    else
    {
      if (localArrayList != null)
      {
        SparseArray localSparseArray1 = locale.e();
        k = 0;
        if (localSparseArray1 == null)
          break label1054;
        i1 = 0;
        i2 = 0;
        if (i1 >= localSparseArray1.size())
          break label1041;
        e.a locala1 = (e.a)localSparseArray1.valueAt(i1);
        Iterator localIterator = localArrayList.iterator();
        if (!localIterator.hasNext())
          break label1085;
        d locald = (d)localIterator.next();
        if (locald.a != locala1.f())
          break label1076;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Byte.valueOf(locald.a);
        com.tencent.beacon.d.a.a("server module strategy mid: %d", arrayOfObject2);
        if (locald.b != 1)
          break label1079;
        bool3 = true;
        if (locala1.a() != bool3)
        {
          Object[] arrayOfObject7 = new Object[2];
          arrayOfObject7[0] = Byte.valueOf(locald.a);
          arrayOfObject7[1] = Boolean.valueOf(bool3);
          com.tencent.beacon.d.a.b("mid: %d , isUsable changed: %b ", arrayOfObject7);
          i2 = 1;
          locala1.a(bool3);
        }
        if (!locala1.b().equals(locald.c))
        {
          Object[] arrayOfObject6 = new Object[2];
          arrayOfObject6[0] = Byte.valueOf(locald.a);
          arrayOfObject6[1] = locald.c;
          com.tencent.beacon.d.a.b("mid: %d , url changed: %s", arrayOfObject6);
          i2 = 1;
          locala1.a(locald.c);
        }
        if (((locald.a == 1) || (locald.a == 2)) && (locala1.c() != null) && (locald.d != null) && (!locala1.c().equals(locald.d)))
        {
          Object[] arrayOfObject5 = new Object[1];
          arrayOfObject5[0] = Byte.valueOf(locald.a);
          com.tencent.beacon.d.a.b("mid: %d , detail changed...", arrayOfObject5);
          i2 = 1;
          locala1.a(locald.d);
          c.a(this.a).a(locald.a, locald.d);
        }
        if (locald.a == 1)
        {
          if ((locala1.d() == null) || (locald.e == null))
            break label825;
          if (!locala1.d().equals(locald.e))
          {
            Object[] arrayOfObject4 = new Object[1];
            arrayOfObject4[0] = Byte.valueOf(locald.a);
            com.tencent.beacon.d.a.b("mid: %d , PreventEventCode changed...", arrayOfObject4);
            i2 = 1;
            locala1.a(com.tencent.beacon.a.e.a(locald.e));
          }
        }
        while (true)
        {
          if (locald.a != 2)
            break label1076;
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = Byte.valueOf(locald.a);
          com.tencent.beacon.d.a.b("mid: %d , SpeedMonitorStrategy", arrayOfObject3);
          locala1.a(locald.f);
          break label1076;
          if ((locala1.d() != null) || (locald.e != null))
            break;
        }
      }
      localSparseArray2 = locale.e();
      k = 0;
      if (localSparseArray2 == null)
        break label1054;
      i3 = localSparseArray2.size();
      i4 = 0;
    }
    label1041: label1048: label1054: label1065: label1076: label1079: label1085: label1091: int i6;
    for (int i5 = 0; ; i5 = i6)
    {
      if (i4 < i3)
      {
        e.a locala2 = (e.a)localSparseArray2.valueAt(i4);
        if (!locala2.a())
          break label1091;
        Object[] arrayOfObject8 = new Object[1];
        arrayOfObject8[0] = Integer.valueOf(locala2.f());
        com.tencent.beacon.d.a.b("mid: %d , server not response strategy, sdk local close it!", arrayOfObject8);
        i5 = 1;
        locala2.a(false);
        break label1091;
        if ((localMap1 != null) && (locale.d() != null))
        {
          boolean bool2 = localMap1.equals(locale.d());
          n = 0;
          if (bool2)
            break label1065;
          locale.a(localMap1);
          n = 1;
          break label1065;
        }
        n = 0;
        if (localMap1 == null)
          break label1065;
        Map localMap2 = locale.d();
        n = 0;
        if (localMap2 != null)
          break label1065;
        locale.a(localMap1);
        n = 1;
      }
      while (n == 0)
      {
        i = m;
        break;
        do
        {
          m = j;
          break label322;
          k = i5;
          continue;
          k = i2;
          continue;
          i = 0;
          break;
        }
        while (k == 0);
        int m = 1;
        break label322;
      }
      i = 1;
      break;
      break label395;
      bool3 = false;
      break label467;
      i1++;
      break label366;
      i6 = i5;
      i4++;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.b.a
 * JD-Core Version:    0.6.0
 */
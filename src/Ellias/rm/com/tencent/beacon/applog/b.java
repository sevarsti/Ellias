package com.tencent.beacon.applog;

import android.content.Context;
import com.tencent.beacon.a.e;
import com.tencent.beacon.event.m;
import com.tencent.beacon.upload.g;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class b
{
  protected static Context a;
  private static b c = null;
  private static com.tencent.beacon.event.d d = null;
  private static a e = null;
  protected g b;
  private Runnable f = new Runnable()
  {
    public final void run()
    {
      try
      {
        int i = b.c();
        if (i > 0)
          try
          {
            if (b.this.b != null)
            {
              Iterator localIterator = a.a(b.a, null, -1, -1).iterator();
              while (localIterator.hasNext())
              {
                d locald = (d)localIterator.next();
                String str = e.a(locald.h());
                if ((str == null) || ("".equals(str)))
                  continue;
                locald.e(str);
                b.this.b.a(new c(b.a, locald, false));
              }
            }
          }
          catch (Throwable localThrowable2)
          {
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = localThrowable2.toString();
            com.tencent.beacon.d.a.c(" up common error }%s", arrayOfObject);
            localThrowable2.printStackTrace();
          }
        return;
      }
      catch (Throwable localThrowable1)
      {
        localThrowable1.printStackTrace();
      }
    }
  };

  protected b()
  {
    m localm = m.d();
    if (localm != null)
    {
      a = localm.c.getApplicationContext();
      this.b = localm.j();
      if (localm.a == null)
        break label70;
      d = localm.a;
    }
    while (true)
    {
      e = new a(a);
      return;
      label70: d = new com.tencent.beacon.event.d();
    }
  }

  public static b a()
  {
    monitorenter;
    try
    {
      if (c == null)
        c = new b();
      b localb = c;
      return localb;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static String a(String paramString)
  {
    try
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
      String str1 = localSimpleDateFormat.format(Long.valueOf(new Date().getTime()));
      long l = new Date().getTime();
      com.tencent.beacon.a.d locald = com.tencent.beacon.a.d.m();
      if (locald != null)
        l += locald.h();
      String str2 = localSimpleDateFormat.format(Long.valueOf(l));
      String str3 = str1 + " " + str2 + "\r\n" + paramString + "\r\n";
      return str3;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return "";
  }

  public static boolean a(String paramString1, String paramString2, boolean paramBoolean)
  {
    int i = 1;
    int j;
    if (a() == null)
    {
      com.tencent.beacon.d.a.d("isModuleAble:not init ua", new Object[0]);
      j = 0;
    }
    while (true)
    {
      if (j == 0);
      while (true)
      {
        return false;
        if ((d == null) || (!d.o()))
          break label246;
        j = i;
        break;
        int k = paramString1.length();
        if ((d != null) && (k <= d.p()))
        {
          Object[] arrayOfObject = new Object[i];
          arrayOfObject[0] = Integer.valueOf(d.p());
          com.tencent.beacon.d.a.c(" reach app Log size limited! %d ", arrayOfObject);
        }
        while ((i != 0) && (e != null))
        {
          d locald = new d();
          String str = a(paramString1);
          locald.e(str);
          locald.d(paramString2);
          locald.c(str.length());
          com.tencent.beacon.d.a.e("appLog upload   logSize:" + str.length(), new Object[0]);
          if (paramBoolean)
          {
            com.tencent.beacon.d.a.e("appLog upload   isRealTime:" + paramBoolean, new Object[0]);
            return e.a(locald);
          }
          com.tencent.beacon.d.a.e("appLog upload   isRealTime:" + paramBoolean, new Object[0]);
          return e.b(locald);
          i = 0;
        }
      }
      label246: j = 0;
    }
  }

  public static void b()
  {
    b localb = a();
    if (localb != null)
      com.tencent.beacon.a.c.a().a(localb.f);
  }

  public static int c()
  {
    return a.a(a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.applog.b
 * JD-Core Version:    0.6.0
 */
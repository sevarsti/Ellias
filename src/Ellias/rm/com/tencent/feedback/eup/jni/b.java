package com.tencent.feedback.eup.jni;

import android.content.Context;
import com.tencent.feedback.common.a;
import com.tencent.feedback.eup.CrashHandleListener;
import com.tencent.feedback.eup.d;

public final class b
  implements NativeExceptionHandler
{
  private static b b;
  private Context a;

  private b(Context paramContext)
  {
    this.a = paramContext;
  }

  public static NativeExceptionHandler a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (b == null)
        b = new b(paramContext);
      b localb = b;
      return localb;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final void handleNativeException(int paramInt1, int paramInt2, long paramLong1, long paramLong2, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    String str3;
    d locald;
    if (paramString3 == null)
    {
      str3 = null;
      Object[] arrayOfObject1 = new Object[8];
      arrayOfObject1[0] = Integer.valueOf(paramInt1);
      arrayOfObject1[1] = Integer.valueOf(paramInt2);
      arrayOfObject1[2] = Long.valueOf(paramLong1);
      arrayOfObject1[3] = Long.valueOf(paramLong2);
      arrayOfObject1[4] = paramString1;
      arrayOfObject1[5] = paramString2;
      arrayOfObject1[6] = str3;
      arrayOfObject1[7] = paramString4;
      com.tencent.feedback.common.e.e("rqdp{  na eup p:} %d , t:%d , exT:%d ,exTMS: %d, exTP:%s ,exADD:%s ,parsed exSTA:%s, TMB:%s", arrayOfObject1);
      String str4 = a.h(this.a);
      String str5 = Thread.currentThread().getName();
      locald = com.tencent.feedback.eup.b.a(this.a, str4, str5, paramString2, paramString1, paramString1, str3, 1000L * paramLong1 + paramLong2 / 1000L, null, null);
      if (locald != null)
        break label236;
      com.tencent.feedback.common.e.c("rqdp{  cr eup msg fail!}", new Object[0]);
    }
    while (true)
    {
      return;
      int i = paramString3.indexOf("java.lang.Thread.getStackTrace");
      if (i < 0)
      {
        str3 = paramString3;
        break;
      }
      int j = paramString3.indexOf("\n", i);
      if (j < 0)
      {
        str3 = paramString3;
        break;
      }
      String str1 = paramString3.substring(0, i);
      String str2 = paramString3.substring(j);
      str3 = str1 + str2;
      break;
      label236: locald.a(true);
      locald.c(true);
      locald.h(paramString4);
      com.tencent.feedback.eup.e locale = com.tencent.feedback.eup.e.k();
      CrashHandleListener localCrashHandleListener;
      if (locale == null)
      {
        com.tencent.feedback.common.e.c("rqdp{  instance == null}", new Object[0]);
        localCrashHandleListener = null;
        label277: if (localCrashHandleListener == null);
      }
      try
      {
        com.tencent.feedback.common.e.b("set exdata", new Object[0]);
        locald.b(localCrashHandleListener.getCrashExtraData());
      }
      catch (Throwable localThrowable2)
      {
        try
        {
          while (true)
          {
            com.tencent.feedback.common.e.b("set exMsg", new Object[0]);
            locald.l(localCrashHandleListener.getCrashExtraMessage());
            com.tencent.feedback.eup.b localb = com.tencent.feedback.eup.b.a(this.a);
            if (localb != null)
            {
              boolean bool = localb.a(locald);
              Object[] arrayOfObject3 = new Object[1];
              arrayOfObject3[0] = Boolean.valueOf(bool);
              com.tencent.feedback.common.e.e("rqdp{  handle na eup} %b", arrayOfObject3);
            }
            if (localCrashHandleListener == null)
              break;
            com.tencent.feedback.common.e.e("rqdp{  start your handler to native crash}", new Object[0]);
            try
            {
              localCrashHandleListener.onNativeCrash(paramInt1, paramInt2, str3);
              return;
            }
            catch (Throwable localThrowable1)
            {
              Object[] arrayOfObject2 = new Object[1];
              arrayOfObject2[0] = localThrowable1.toString();
              com.tencent.feedback.common.e.d("your crash handle happen error %s", arrayOfObject2);
              localThrowable1.printStackTrace();
              return;
            }
            localCrashHandleListener = locale.p();
            break label277;
            localThrowable2 = localThrowable2;
            Object[] arrayOfObject4 = new Object[1];
            arrayOfObject4[0] = localThrowable2.toString();
            com.tencent.feedback.common.e.d("get extra data error %s", arrayOfObject4);
            localThrowable2.printStackTrace();
          }
        }
        catch (Throwable localThrowable3)
        {
          while (true)
          {
            Object[] arrayOfObject5 = new Object[1];
            arrayOfObject5[0] = localThrowable3.toString();
            com.tencent.feedback.common.e.d("get extra msg error %s", arrayOfObject5);
            localThrowable3.printStackTrace();
          }
        }
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.eup.jni.b
 * JD-Core Version:    0.6.0
 */
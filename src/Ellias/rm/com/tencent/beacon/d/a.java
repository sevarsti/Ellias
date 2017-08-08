package com.tencent.beacon.d;

import android.util.Log;
import java.util.Locale;

public final class a
{
  public static boolean a = false;
  public static boolean b = false;

  private static void a(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    int i;
    if (a)
    {
      i = 1;
      if (i != 0)
        if (paramString2 != null)
          break label31;
    }
    for (paramString2 = "null"; ; paramString2 = String.format(Locale.US, paramString2, paramArrayOfObject))
      label31: 
      do
      {
        Log.d(paramString1, paramString2);
        return;
        i = 0;
        break;
      }
      while ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0));
  }

  public static void a(String paramString, Object[] paramArrayOfObject)
  {
    int i;
    if (a)
    {
      i = 1;
      if (i != 0)
        if (paramString != null)
          break label32;
    }
    for (paramString = "null"; ; paramString = String.format(Locale.US, paramString, paramArrayOfObject))
      label32: 
      do
      {
        Log.i("beacon", paramString);
        return;
        i = 0;
        break;
      }
      while ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0));
  }

  public static void b(String paramString, Object[] paramArrayOfObject)
  {
    int i;
    if (a)
    {
      i = 1;
      if (i != 0)
        if (paramString != null)
          break label32;
    }
    for (paramString = "null"; ; paramString = String.format(Locale.US, paramString, paramArrayOfObject))
      label32: 
      do
      {
        Log.d("beacon", paramString);
        return;
        i = 0;
        break;
      }
      while ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0));
  }

  public static void c(String paramString, Object[] paramArrayOfObject)
  {
    if (paramString == null);
    for (paramString = "null"; ; paramString = String.format(Locale.US, paramString, paramArrayOfObject))
      do
      {
        Log.w("beacon", paramString);
        return;
      }
      while ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0));
  }

  public static void d(String paramString, Object[] paramArrayOfObject)
  {
    if (paramString == null);
    for (paramString = "null"; ; paramString = String.format(Locale.US, paramString, paramArrayOfObject))
      do
      {
        Log.e("beacon", paramString);
        if (!b)
          break;
        throw new RuntimeException(String.format("ERROR CHECK:\n %s \n this error will throw only Constants.Is_AutoCheckOpen == true!\n this throw is for found error early!", new Object[] { paramString }));
      }
      while ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0));
  }

  public static void e(String paramString, Object[] paramArrayOfObject)
  {
    a("beacon_step_api", paramString, paramArrayOfObject);
  }

  public static void f(String paramString, Object[] paramArrayOfObject)
  {
    a("beacon_step_buffer", paramString, paramArrayOfObject);
  }

  public static void g(String paramString, Object[] paramArrayOfObject)
  {
    a("beacon_step_db", paramString, paramArrayOfObject);
  }

  public static void h(String paramString, Object[] paramArrayOfObject)
  {
    a("beacon_step_upload", paramString, paramArrayOfObject);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.d.a
 * JD-Core Version:    0.6.0
 */
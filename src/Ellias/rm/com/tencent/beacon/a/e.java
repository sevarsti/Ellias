package com.tencent.beacon.a;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.beacon.c.a.b;
import com.tencent.beacon.event.UserAction;
import com.tencent.beacon.event.c;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class e
{
  private static e a;

  private e(Context paramContext)
  {
    paramContext.getApplicationContext();
  }

  public static e a(Context paramContext)
  {
    monitorenter;
    try
    {
      if ((a == null) && (paramContext != null))
        a = new e(paramContext.getApplicationContext());
      e locale = a;
      return locale;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static b a(int paramInt1, d paramd, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    com.tencent.beacon.d.a.b("en2Req", new Object[0]);
    if (paramd == null)
    {
      com.tencent.beacon.d.a.d("error no com info! ", new Object[0]);
      return null;
    }
    try
    {
      b localb = new b();
      monitorenter;
      try
      {
        localb.j = paramd.a();
        localb.k = paramd.b();
        localb.a = paramd.c();
        localb.b = paramd.j();
        localb.c = paramd.d();
        localb.d = paramd.e();
        localb.e = paramd.f();
        localb.l = "";
        HashMap localHashMap;
        if (paramInt1 == 100)
        {
          localHashMap = new HashMap();
          localHashMap.put("A1", UserAction.getUserID());
          localHashMap.put("A2", paramd.i());
          c localc = c.a(paramd.l());
          localHashMap.put("A4", localc.b());
          localHashMap.put("A6", localc.a());
          localHashMap.put("A7", localc.c());
          localHashMap.put("A3", com.tencent.beacon.b.a.a(paramd.l()).a());
          localHashMap.put("A23", localc.d());
          a(paramd.l());
          localHashMap.put("A33", j(paramd.l()));
          if (!a.g(paramd.l()))
            break label473;
          localHashMap.put("A66", "F");
        }
        while (true)
        {
          localHashMap.put("A67", a.i(paramd.l()));
          localHashMap.put("A68", a.h(paramd.l()));
          localb.l = com.tencent.beacon.applog.a.a(localHashMap);
          monitorexit;
          localb.f = paramInt1;
          localb.h = (byte)paramInt3;
          localb.i = (byte)paramInt2;
          if (paramArrayOfByte == null)
            paramArrayOfByte = "".getBytes();
          localb.g = paramArrayOfByte;
          return localb;
          label473: localHashMap.put("A66", "B");
        }
      }
      finally
      {
        monitorexit;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  // ERROR //
  public static Object a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: ldc 204
    //   2: iconst_0
    //   3: anewarray 4	java/lang/Object
    //   6: invokestatic 33	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   9: aload_0
    //   10: ifnull +8 -> 18
    //   13: aload_0
    //   14: arraylength
    //   15: ifge +5 -> 20
    //   18: aconst_null
    //   19: areturn
    //   20: new 206	java/io/ByteArrayInputStream
    //   23: dup
    //   24: aload_0
    //   25: invokespecial 209	java/io/ByteArrayInputStream:<init>	([B)V
    //   28: astore_1
    //   29: new 211	java/io/ObjectInputStream
    //   32: dup
    //   33: aload_1
    //   34: invokespecial 214	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   37: astore_2
    //   38: aload_2
    //   39: invokevirtual 218	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   42: astore 9
    //   44: aload_2
    //   45: invokevirtual 221	java/io/ObjectInputStream:close	()V
    //   48: aload_1
    //   49: invokevirtual 222	java/io/ByteArrayInputStream:close	()V
    //   52: aload 9
    //   54: areturn
    //   55: astore 11
    //   57: aload 11
    //   59: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   62: aload 9
    //   64: areturn
    //   65: astore 10
    //   67: aload 10
    //   69: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   72: goto -24 -> 48
    //   75: astore_3
    //   76: aconst_null
    //   77: astore_2
    //   78: aload_3
    //   79: invokevirtual 199	java/lang/Throwable:printStackTrace	()V
    //   82: aload_3
    //   83: invokevirtual 226	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   86: iconst_0
    //   87: anewarray 4	java/lang/Object
    //   90: invokestatic 38	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   93: aload_2
    //   94: ifnull +7 -> 101
    //   97: aload_2
    //   98: invokevirtual 221	java/io/ObjectInputStream:close	()V
    //   101: aload_1
    //   102: invokevirtual 222	java/io/ByteArrayInputStream:close	()V
    //   105: aconst_null
    //   106: areturn
    //   107: astore 7
    //   109: aload 7
    //   111: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   114: aconst_null
    //   115: areturn
    //   116: astore 8
    //   118: aload 8
    //   120: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   123: goto -22 -> 101
    //   126: astore 12
    //   128: aconst_null
    //   129: astore_2
    //   130: aload 12
    //   132: astore 4
    //   134: aload_2
    //   135: ifnull +7 -> 142
    //   138: aload_2
    //   139: invokevirtual 221	java/io/ObjectInputStream:close	()V
    //   142: aload_1
    //   143: invokevirtual 222	java/io/ByteArrayInputStream:close	()V
    //   146: aload 4
    //   148: athrow
    //   149: astore 6
    //   151: aload 6
    //   153: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   156: goto -14 -> 142
    //   159: astore 5
    //   161: aload 5
    //   163: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   166: goto -20 -> 146
    //   169: astore 4
    //   171: goto -37 -> 134
    //   174: astore_3
    //   175: goto -97 -> 78
    //
    // Exception table:
    //   from	to	target	type
    //   48	52	55	java/io/IOException
    //   44	48	65	java/io/IOException
    //   29	38	75	java/lang/Throwable
    //   101	105	107	java/io/IOException
    //   97	101	116	java/io/IOException
    //   29	38	126	finally
    //   138	142	149	java/io/IOException
    //   142	146	159	java/io/IOException
    //   38	44	169	finally
    //   78	93	169	finally
    //   38	44	174	java/lang/Throwable
  }

  public static String a()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Throwable localThrowable)
    {
      com.tencent.beacon.d.a.d("getDeviceName error", new Object[0]);
      localThrowable.printStackTrace();
    }
    return "null";
  }

  // ERROR //
  public static String a(String paramString)
  {
    // Byte code:
    //   0: new 240	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 243	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   8: astore_1
    //   9: aload_1
    //   10: invokevirtual 247	java/io/FileInputStream:available	()I
    //   13: newarray byte
    //   15: astore 5
    //   17: aload_1
    //   18: aload 5
    //   20: invokevirtual 251	java/io/FileInputStream:read	([B)I
    //   23: pop
    //   24: aload 5
    //   26: ldc 253
    //   28: invokestatic 259	org/apache/http/util/EncodingUtils:getString	([BLjava/lang/String;)Ljava/lang/String;
    //   31: astore 7
    //   33: aload 7
    //   35: astore_3
    //   36: aload_1
    //   37: invokevirtual 260	java/io/FileInputStream:close	()V
    //   40: aload_3
    //   41: areturn
    //   42: astore_2
    //   43: ldc 82
    //   45: astore_3
    //   46: aload_2
    //   47: astore 4
    //   49: aload 4
    //   51: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   54: aload_3
    //   55: areturn
    //   56: astore 4
    //   58: goto -9 -> 49
    //
    // Exception table:
    //   from	to	target	type
    //   0	33	42	java/lang/Exception
    //   36	40	56	java/lang/Exception
  }

  public static HashSet<String> a(ArrayList<String> paramArrayList)
  {
    int i = paramArrayList.size();
    if ((paramArrayList != null) && (i > 0))
    {
      HashSet localHashSet = new HashSet(i);
      Iterator localIterator = paramArrayList.iterator();
      while (localIterator.hasNext())
        localHashSet.add((String)localIterator.next());
      return localHashSet;
    }
    return null;
  }

  // ERROR //
  public static byte[] a(Object paramObject)
  {
    // Byte code:
    //   0: ldc_w 292
    //   3: iconst_0
    //   4: anewarray 4	java/lang/Object
    //   7: invokestatic 33	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   10: aload_0
    //   11: ifnull +13 -> 24
    //   14: ldc_w 294
    //   17: aload_0
    //   18: invokevirtual 299	java/lang/Class:isInstance	(Ljava/lang/Object;)Z
    //   21: ifne +15 -> 36
    //   24: ldc_w 301
    //   27: iconst_0
    //   28: anewarray 4	java/lang/Object
    //   31: invokestatic 303	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   34: aconst_null
    //   35: areturn
    //   36: new 305	java/io/ByteArrayOutputStream
    //   39: dup
    //   40: invokespecial 306	java/io/ByteArrayOutputStream:<init>	()V
    //   43: astore_1
    //   44: new 308	java/io/ObjectOutputStream
    //   47: dup
    //   48: aload_1
    //   49: invokespecial 311	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   52: astore_2
    //   53: aload_2
    //   54: aload_0
    //   55: invokevirtual 315	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   58: aload_2
    //   59: invokevirtual 318	java/io/ObjectOutputStream:flush	()V
    //   62: aload_1
    //   63: invokevirtual 321	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   66: astore 9
    //   68: aload_2
    //   69: invokevirtual 322	java/io/ObjectOutputStream:close	()V
    //   72: aload_1
    //   73: invokevirtual 323	java/io/ByteArrayOutputStream:close	()V
    //   76: aload 9
    //   78: areturn
    //   79: astore 11
    //   81: aload 11
    //   83: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   86: aload 9
    //   88: areturn
    //   89: astore 10
    //   91: aload 10
    //   93: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   96: goto -24 -> 72
    //   99: astore_3
    //   100: aconst_null
    //   101: astore_2
    //   102: aload_3
    //   103: invokevirtual 199	java/lang/Throwable:printStackTrace	()V
    //   106: aload_3
    //   107: invokevirtual 226	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   110: iconst_0
    //   111: anewarray 4	java/lang/Object
    //   114: invokestatic 38	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   117: aload_2
    //   118: ifnull +7 -> 125
    //   121: aload_2
    //   122: invokevirtual 322	java/io/ObjectOutputStream:close	()V
    //   125: aload_1
    //   126: invokevirtual 323	java/io/ByteArrayOutputStream:close	()V
    //   129: aconst_null
    //   130: areturn
    //   131: astore 7
    //   133: aload 7
    //   135: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   138: aconst_null
    //   139: areturn
    //   140: astore 8
    //   142: aload 8
    //   144: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   147: goto -22 -> 125
    //   150: astore 12
    //   152: aconst_null
    //   153: astore_2
    //   154: aload 12
    //   156: astore 4
    //   158: aload_2
    //   159: ifnull +7 -> 166
    //   162: aload_2
    //   163: invokevirtual 322	java/io/ObjectOutputStream:close	()V
    //   166: aload_1
    //   167: invokevirtual 323	java/io/ByteArrayOutputStream:close	()V
    //   170: aload 4
    //   172: athrow
    //   173: astore 6
    //   175: aload 6
    //   177: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   180: goto -14 -> 166
    //   183: astore 5
    //   185: aload 5
    //   187: invokevirtual 223	java/io/IOException:printStackTrace	()V
    //   190: goto -20 -> 170
    //   193: astore 4
    //   195: goto -37 -> 158
    //   198: astore_3
    //   199: goto -97 -> 102
    //
    // Exception table:
    //   from	to	target	type
    //   72	76	79	java/io/IOException
    //   68	72	89	java/io/IOException
    //   44	53	99	java/lang/Throwable
    //   125	129	131	java/io/IOException
    //   121	125	140	java/io/IOException
    //   44	53	150	finally
    //   162	166	173	java/io/IOException
    //   166	170	183	java/io/IOException
    //   53	68	193	finally
    //   102	117	193	finally
    //   53	68	198	java/lang/Throwable
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    if ((paramArrayOfByte == null) || (paramInt == -1))
      return paramArrayOfByte;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(paramInt);
    arrayOfObject1[1] = Integer.valueOf(paramArrayOfByte.length);
    com.tencent.beacon.d.a.b("zp: %s len: %s", arrayOfObject1);
    try
    {
      byte[] arrayOfByte = com.tencent.beacon.applog.a.a(paramInt, paramArrayOfByte);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localThrowable.toString();
      com.tencent.beacon.d.a.d("err zp : %s", arrayOfObject2);
    }
    return null;
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      byte[] arrayOfByte = a(a(paramArrayOfByte, paramInt1), paramInt2, paramString);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt, String paramString)
  {
    if ((paramArrayOfByte == null) || (paramInt == -1))
      return paramArrayOfByte;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(paramArrayOfByte.length);
    arrayOfObject1[1] = Integer.valueOf(paramInt);
    com.tencent.beacon.d.a.b("enD:} %d %d", arrayOfObject1);
    try
    {
      byte[] arrayOfByte = com.tencent.beacon.applog.a.b(paramInt, paramString, paramArrayOfByte);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localThrowable.toString();
      com.tencent.beacon.d.a.d("err enD: %s", arrayOfObject2);
    }
    return null;
  }

  public static Long[] a(LinkedHashMap<Long, Long> paramLinkedHashMap, long paramLong)
  {
    if ((paramLinkedHashMap == null) || (paramLinkedHashMap.size() <= 0) || (paramLong <= 0L))
      return null;
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramLinkedHashMap.keySet().iterator();
    long l1 = 0L;
    long l4;
    if ((localIterator.hasNext()) && (l1 < paramLong))
    {
      long l2 = ((Long)localIterator.next()).longValue();
      long l3 = ((Long)paramLinkedHashMap.get(Long.valueOf(l2))).longValue();
      if (l1 + l3 > paramLong)
        break label145;
      localArrayList.add(Long.valueOf(l2));
      l4 = l1 + l3;
    }
    while (true)
    {
      l1 = l4;
      break;
      if (localArrayList.size() > 0)
        return (Long[])localArrayList.toArray(new Long[1]);
      return null;
      label145: l4 = l1;
    }
  }

  public static String b()
  {
    try
    {
      String str = Build.VERSION.RELEASE;
      return str;
    }
    catch (Throwable localThrowable)
    {
      com.tencent.beacon.d.a.d("getVersion error", new Object[0]);
      localThrowable.printStackTrace();
    }
    return "null";
  }

  public static String b(Context paramContext)
  {
    String str1 = "null";
    if (paramContext == null)
    {
      com.tencent.beacon.d.a.d("getImei but context == null!", new Object[0]);
      return str1;
    }
    try
    {
      str1 = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      if (str1 == null);
      String str2;
      for (localObject1 = "null"; ; localObject1 = str2)
      {
        try
        {
          com.tencent.beacon.d.a.a("IMEI:" + (String)localObject1, new Object[0]);
          return localObject1;
        }
        catch (Throwable localThrowable2)
        {
        }
        com.tencent.beacon.d.a.d("getImei error!", new Object[0]);
        localThrowable2.printStackTrace();
        return localObject1;
        str2 = str1.toLowerCase();
      }
    }
    catch (Throwable localThrowable1)
    {
      while (true)
      {
        Object localObject1 = str1;
        Object localObject2 = localThrowable1;
      }
    }
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt)
  {
    if ((paramArrayOfByte == null) || (paramInt == -1))
      return paramArrayOfByte;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    arrayOfObject[1] = Integer.valueOf(paramArrayOfByte.length);
    com.tencent.beacon.d.a.b("unzp: %s len: %s", arrayOfObject);
    try
    {
      byte[] arrayOfByte = com.tencent.beacon.applog.a.b(paramInt, paramArrayOfByte);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      com.tencent.beacon.d.a.d("err unzp}" + localThrowable.toString(), new Object[0]);
    }
    return null;
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    try
    {
      byte[] arrayOfByte = b(b(paramArrayOfByte, paramInt2, paramString), paramInt1);
      return arrayOfByte;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt, String paramString)
  {
    if ((paramArrayOfByte == null) || (paramInt == -1))
      return paramArrayOfByte;
    try
    {
      byte[] arrayOfByte = com.tencent.beacon.applog.a.a(paramInt, paramString, paramArrayOfByte);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localThrowable.toString();
      com.tencent.beacon.d.a.d("err unD: %s", arrayOfObject);
    }
    return null;
  }

  public static String c()
  {
    try
    {
      String str = Build.VERSION.SDK;
      return str;
    }
    catch (Throwable localThrowable)
    {
      com.tencent.beacon.d.a.d("getApiLevel error", new Object[0]);
      localThrowable.printStackTrace();
    }
    return "null";
  }

  public static String c(Context paramContext)
  {
    String str1 = "null";
    if (paramContext == null)
    {
      com.tencent.beacon.d.a.d("getImsi but context == null!", new Object[0]);
      return str1;
    }
    String str2;
    try
    {
      str1 = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
      if (str1 == null)
        return "null";
      String str3 = str1.toLowerCase();
      return str3;
    }
    catch (Throwable localThrowable)
    {
      str2 = str1;
      com.tencent.beacon.d.a.d("getImsi error!", new Object[0]);
      localThrowable.printStackTrace();
    }
    return str2;
  }

  public static String d()
  {
    try
    {
      Object localObject1 = Build.class.getField("HARDWARE").get(null);
      Object localObject2 = null;
      if (localObject1 != null)
      {
        String str = localObject1.toString();
        localObject2 = str;
      }
      return localObject2;
    }
    catch (Throwable localThrowable)
    {
      com.tencent.beacon.d.a.c("getCpuProductorName error!", new Object[0]);
    }
    return null;
  }

  // ERROR //
  public static String d(Context paramContext)
  {
    // Byte code:
    //   0: ldc 235
    //   2: astore_1
    //   3: aload_0
    //   4: ifnonnull +15 -> 19
    //   7: ldc_w 453
    //   10: iconst_0
    //   11: anewarray 4	java/lang/Object
    //   14: invokestatic 38	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   17: aload_1
    //   18: areturn
    //   19: aload_0
    //   20: invokevirtual 457	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   23: ldc_w 459
    //   26: invokestatic 464	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   29: astore_3
    //   30: aload_3
    //   31: ifnonnull +6 -> 37
    //   34: ldc 235
    //   36: areturn
    //   37: aload_3
    //   38: invokevirtual 413	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   41: astore 5
    //   43: aload 5
    //   45: areturn
    //   46: astore_2
    //   47: ldc_w 466
    //   50: iconst_0
    //   51: anewarray 4	java/lang/Object
    //   54: invokestatic 38	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   57: aload_2
    //   58: invokevirtual 199	java/lang/Throwable:printStackTrace	()V
    //   61: aload_1
    //   62: areturn
    //   63: astore 4
    //   65: aload_3
    //   66: astore_1
    //   67: aload 4
    //   69: astore_2
    //   70: goto -23 -> 47
    //
    // Exception table:
    //   from	to	target	type
    //   19	30	46	java/lang/Throwable
    //   37	43	63	java/lang/Throwable
  }

  public static String e()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l1 = localStatFs.getBlockSize();
      long l2 = localStatFs.getBlockCount();
      String str = l2 * l1 / 1024L / 1024L;
      return str;
    }
    catch (Throwable localThrowable)
    {
      com.tencent.beacon.d.a.d("getDisplayMetrics error!", new Object[0]);
      localThrowable.printStackTrace();
    }
    return null;
  }

  public static String e(Context paramContext)
  {
    String str1 = "null";
    if (paramContext == null)
    {
      com.tencent.beacon.d.a.d("getMacAddress but context == null!", new Object[0]);
      return str1;
    }
    String str2;
    try
    {
      str1 = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      if (str1 == null)
        return "null";
      String str3 = str1.toLowerCase();
      return str3;
    }
    catch (Throwable localThrowable)
    {
      str2 = str1;
      localThrowable.printStackTrace();
      com.tencent.beacon.d.a.d("getMacAddress error!", new Object[0]);
    }
    return str2;
  }

  // ERROR //
  public static String f()
  {
    // Byte code:
    //   0: new 512	java/io/FileReader
    //   3: dup
    //   4: ldc_w 514
    //   7: invokespecial 515	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: new 517	java/io/BufferedReader
    //   14: dup
    //   15: aload_0
    //   16: sipush 8192
    //   19: invokespecial 520	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 523	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   27: ldc_w 525
    //   30: iconst_2
    //   31: invokevirtual 529	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   34: iconst_1
    //   35: aaload
    //   36: invokevirtual 413	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   39: ldc_w 531
    //   42: ldc 82
    //   44: invokevirtual 535	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   47: invokevirtual 538	java/lang/String:trim	()Ljava/lang/String;
    //   50: invokestatic 542	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   53: ldc2_w 487
    //   56: ldiv
    //   57: lstore 7
    //   59: new 92	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   66: lload 7
    //   68: invokevirtual 491	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   71: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: astore 9
    //   76: aload 9
    //   78: astore 5
    //   80: aload_1
    //   81: invokevirtual 543	java/io/BufferedReader:close	()V
    //   84: aload_0
    //   85: invokevirtual 544	java/io/FileReader:close	()V
    //   88: aload 5
    //   90: areturn
    //   91: astore 10
    //   93: ldc_w 546
    //   96: iconst_0
    //   97: anewarray 4	java/lang/Object
    //   100: invokestatic 38	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   103: aload 10
    //   105: invokevirtual 199	java/lang/Throwable:printStackTrace	()V
    //   108: aload 5
    //   110: areturn
    //   111: astore_2
    //   112: aconst_null
    //   113: astore_1
    //   114: aconst_null
    //   115: astore_0
    //   116: ldc_w 546
    //   119: iconst_0
    //   120: anewarray 4	java/lang/Object
    //   123: invokestatic 38	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   126: aload_2
    //   127: invokevirtual 199	java/lang/Throwable:printStackTrace	()V
    //   130: aload_1
    //   131: ifnull +7 -> 138
    //   134: aload_1
    //   135: invokevirtual 543	java/io/BufferedReader:close	()V
    //   138: aconst_null
    //   139: astore 5
    //   141: aload_0
    //   142: ifnull -54 -> 88
    //   145: aload_0
    //   146: invokevirtual 544	java/io/FileReader:close	()V
    //   149: aconst_null
    //   150: areturn
    //   151: astore 6
    //   153: ldc_w 546
    //   156: iconst_0
    //   157: anewarray 4	java/lang/Object
    //   160: invokestatic 38	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   163: aload 6
    //   165: invokevirtual 199	java/lang/Throwable:printStackTrace	()V
    //   168: aconst_null
    //   169: areturn
    //   170: astore 12
    //   172: aconst_null
    //   173: astore_1
    //   174: aconst_null
    //   175: astore_0
    //   176: aload 12
    //   178: astore_3
    //   179: aload_1
    //   180: ifnull +7 -> 187
    //   183: aload_1
    //   184: invokevirtual 543	java/io/BufferedReader:close	()V
    //   187: aload_0
    //   188: ifnull +7 -> 195
    //   191: aload_0
    //   192: invokevirtual 544	java/io/FileReader:close	()V
    //   195: aload_3
    //   196: athrow
    //   197: astore 4
    //   199: ldc_w 546
    //   202: iconst_0
    //   203: anewarray 4	java/lang/Object
    //   206: invokestatic 38	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   209: aload 4
    //   211: invokevirtual 199	java/lang/Throwable:printStackTrace	()V
    //   214: goto -19 -> 195
    //   217: astore 11
    //   219: aload 11
    //   221: astore_3
    //   222: aconst_null
    //   223: astore_1
    //   224: goto -45 -> 179
    //   227: astore_3
    //   228: goto -49 -> 179
    //   231: astore_2
    //   232: aconst_null
    //   233: astore_1
    //   234: goto -118 -> 116
    //   237: astore_2
    //   238: goto -122 -> 116
    //
    // Exception table:
    //   from	to	target	type
    //   80	88	91	java/lang/Throwable
    //   0	11	111	java/lang/Throwable
    //   134	138	151	java/lang/Throwable
    //   145	149	151	java/lang/Throwable
    //   0	11	170	finally
    //   183	187	197	java/lang/Throwable
    //   191	195	197	java/lang/Throwable
    //   11	23	217	finally
    //   23	76	227	finally
    //   116	130	227	finally
    //   11	23	231	java/lang/Throwable
    //   23	76	237	java/lang/Throwable
  }

  public static String f(Context paramContext)
  {
    if (paramContext == null)
    {
      com.tencent.beacon.d.a.d("getMacAddress but context == null!", new Object[0]);
      return "";
    }
    try
    {
      String str2 = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getBSSID();
      str1 = str2;
      return str1;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        localThrowable.printStackTrace();
        com.tencent.beacon.d.a.d("getMacAddress error!", new Object[0]);
        String str1 = "";
      }
    }
  }

  public static DisplayMetrics g(Context paramContext)
  {
    if (paramContext == null)
    {
      com.tencent.beacon.d.a.d("getDisplayMetrics but context == null!", new Object[0]);
      return null;
    }
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      return localDisplayMetrics;
    }
    catch (Throwable localThrowable)
    {
      com.tencent.beacon.d.a.d("getDisplayMetrics error!", new Object[0]);
      localThrowable.printStackTrace();
    }
    return null;
  }

  public static String g()
  {
    try
    {
      String str = Locale.getDefault().getCountry();
      return str;
    }
    catch (Throwable localThrowable)
    {
      com.tencent.beacon.d.a.d("getCountry error!", new Object[0]);
      localThrowable.printStackTrace();
    }
    return null;
  }

  public static long h(Context paramContext)
  {
    if (paramContext == null)
    {
      com.tencent.beacon.d.a.d("getFreeMem but context == null!", new Object[0]);
      return -1L;
    }
    try
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      localActivityManager.getMemoryInfo(localMemoryInfo);
      long l = localMemoryInfo.availMem;
      return l;
    }
    catch (Throwable localThrowable)
    {
      com.tencent.beacon.d.a.d("getFreeMem error!", new Object[0]);
      localThrowable.printStackTrace();
    }
    return -1L;
  }

  public static String h()
  {
    try
    {
      String str = Locale.getDefault().getLanguage();
      return str;
    }
    catch (Throwable localThrowable)
    {
      com.tencent.beacon.d.a.d("getLanguage error!", new Object[0]);
      localThrowable.printStackTrace();
    }
    return null;
  }

  public static String i()
  {
    try
    {
      String str = Build.BRAND;
      return str;
    }
    catch (Throwable localThrowable)
    {
      com.tencent.beacon.d.a.d("getBrand error!", new Object[0]);
      localThrowable.printStackTrace();
    }
    return null;
  }

  public static String i(Context paramContext)
  {
    if (paramContext == null)
    {
      com.tencent.beacon.d.a.d("getSensor but context == null!", new Object[0]);
      return null;
    }
    com.tencent.beacon.d.a.a("getSensor start", new Object[0]);
    Object localObject1 = "X";
    StringBuffer localStringBuffer = new StringBuffer();
    if (Integer.parseInt(Build.VERSION.SDK) >= 10);
    while (true)
    {
      int k;
      int m;
      try
      {
        localClass1 = Class.forName("android.hardware.Camera");
        i = ((Integer)localClass1.getMethod("getNumberOfCameras", new Class[0]).invoke(localClass1, new Object[0])).intValue();
        if (i != 0)
          continue;
        localObject9 = "N";
        localObject10 = "N";
      }
      catch (Throwable localThrowable4)
      {
        try
        {
          localSensorManager = (SensorManager)paramContext.getSystemService("sensor");
          if (localSensorManager.getDefaultSensor(9) == null)
            continue;
          localObject3 = "Y";
        }
        catch (Throwable localThrowable4)
        {
          try
          {
            Class localClass1;
            int i;
            SensorManager localSensorManager;
            if (localSensorManager.getDefaultSensor(4) == null)
              continue;
            str1 = "Y";
            localObject2 = localObject10;
            localObject4 = localObject9;
            localStringBuffer.append((String)localObject4).append(localObject2).append((String)localObject3).append(str1);
            return localStringBuffer.toString();
            Class localClass2 = Class.forName("android.hardware.Camera$CameraInfo");
            Object localObject7 = localClass2.newInstance();
            Method[] arrayOfMethod = localClass1.getMethods();
            int j = arrayOfMethod.length;
            k = 0;
            Object localObject8 = null;
            if (k >= j)
              continue;
            Method localMethod = arrayOfMethod[k];
            if (!localMethod.getName().equals("getCameraInfo"))
              break label581;
            localObject8 = localMethod;
            Field localField1 = localClass2.getField("facing");
            Field localField2 = localClass2.getField("CAMERA_FACING_BACK");
            Field localField3 = localClass2.getField("CAMERA_FACING_FRONT");
            if (localObject8 == null)
              continue;
            localObject12 = localObject1;
            str2 = "X";
            m = 0;
            if (m >= i)
              continue;
            try
            {
              Object[] arrayOfObject = new Object[2];
              arrayOfObject[0] = Integer.valueOf(m);
              arrayOfObject[1] = localObject7;
              localObject8.invoke(localClass1, arrayOfObject);
              int n = localField1.getInt(localObject7);
              int i1 = localField2.getInt(localObject7);
              int i2 = localField3.getInt(localObject7);
              if (n != i1)
                continue;
              str2 = "Y";
              if (i != 1)
                break label575;
              localObject12 = "N";
              break label575;
              if (n != i2)
                break label575;
              localObject12 = "Y";
              if (i != 1)
                break label575;
              str2 = "N";
              break label575;
              localObject3 = "N";
              continue;
              str1 = "N";
              localObject2 = localObject10;
              localObject4 = localObject9;
              continue;
              localThrowable1 = localThrowable1;
              localObject5 = "X";
              localObject4 = "X";
              Throwable localThrowable2 = localThrowable1;
              com.tencent.beacon.d.a.d("getSensor error!", new Object[0]);
              localThrowable2.printStackTrace();
              localObject2 = localObject1;
              localObject3 = localObject5;
              str1 = "X";
            }
            catch (Throwable localThrowable3)
            {
              localObject4 = str2;
              localObject1 = localObject12;
              localObject5 = "X";
              continue;
            }
            localThrowable4 = localThrowable4;
            localObject1 = localObject10;
            localObject6 = localThrowable4;
            localObject5 = "X";
            localObject4 = localObject9;
            continue;
          }
          catch (Throwable localThrowable5)
          {
            Object localObject12;
            String str2;
            localObject4 = localObject9;
            Object localObject11 = localObject3;
            localObject1 = localObject10;
            Object localObject6 = localThrowable5;
            Object localObject5 = localObject11;
            continue;
            localObject10 = localObject12;
            localObject9 = str2;
          }
        }
        continue;
        Object localObject9 = "X";
        Object localObject10 = localObject1;
        continue;
      }
      String str1 = "X";
      Object localObject2 = localObject1;
      Object localObject3 = "X";
      Object localObject4 = "X";
      continue;
      label575: m++;
      continue;
      label581: k++;
    }
  }

  public static long j()
  {
    try
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
      long l = localSimpleDateFormat.parse(localSimpleDateFormat.format(new Date())).getTime();
      return l;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return -1L;
  }

  public static String j(Context paramContext)
  {
    String str;
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo == null)
        return "unknown";
      if (localNetworkInfo.getType() == 1)
      {
        str = "wifi";
      }
      else if (localNetworkInfo.getType() == 0)
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        if (localTelephonyManager != null)
          switch (localTelephonyManager.getNetworkType())
          {
          case 15:
            str = "HSPA+";
          default:
          case 1:
          case 2:
          case 3:
          case 8:
          case 9:
          case 10:
          case 4:
          case 5:
          case 6:
          case 7:
          case 11:
          case 12:
          case 13:
          case 14:
          }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      str = "unknown";
    }
    while (true)
    {
      return str;
      str = "unknown";
      continue;
      str = "GPRS";
      continue;
      str = "EDGE";
      continue;
      str = "UMTS";
      continue;
      str = "HSDPA";
      continue;
      str = "HSUPA";
      continue;
      str = "HSPA";
      continue;
      str = "CDMA";
      continue;
      str = "EVDO_0";
      continue;
      str = "EVDO_A";
      continue;
      str = "1xRTT";
      continue;
      str = "iDen";
      continue;
      str = "EVDO_B";
      continue;
      str = "LTE";
      continue;
      str = "eHRPD";
    }
  }

  public static int k(Context paramContext)
  {
    if (paramContext == null)
    {
      com.tencent.beacon.d.a.d("getWifiSignalLevel but context == null!", new Object[0]);
      return 0;
    }
    try
    {
      WifiInfo localWifiInfo = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
      if (localWifiInfo.getBSSID() != null)
      {
        int j = WifiManager.calculateSignalLevel(localWifiInfo.getRssi(), 5);
        i = j;
        return i;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        int i = 0;
      }
    }
  }

  public static String k()
  {
    try
    {
      String str = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.e
 * JD-Core Version:    0.6.0
 */
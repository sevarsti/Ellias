package com.tencent.android.tpush.service.b;

import android.content.Context;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.encrypt.a;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class f
{
  private static f a = new f();
  private static JSONObject b = new JSONObject();
  private static String c = a.a("com.tencent.tpush.multpkgs");

  public static f a()
  {
    return a;
  }

  // ERROR //
  private JSONObject b(Context paramContext)
  {
    // Byte code:
    //   0: ldc 38
    //   2: new 40	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 41	java/lang/StringBuilder:<init>	()V
    //   9: ldc 43
    //   11: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: aload_1
    //   15: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   18: ldc 52
    //   20: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: invokevirtual 56	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   26: invokestatic 62	com/tencent/android/tpush/logging/TLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   29: aload_1
    //   30: getstatic 31	com/tencent/android/tpush/service/b/f:c	Ljava/lang/String;
    //   33: invokestatic 67	com/tencent/android/tpush/service/c/c:c	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   36: astore_2
    //   37: new 19	org/json/JSONObject
    //   40: dup
    //   41: invokespecial 20	org/json/JSONObject:<init>	()V
    //   44: astore_3
    //   45: aload_2
    //   46: ifnull +94 -> 140
    //   49: aload_2
    //   50: invokevirtual 73	java/lang/String:length	()I
    //   53: ifeq +87 -> 140
    //   56: new 19	org/json/JSONObject
    //   59: dup
    //   60: aload_2
    //   61: invokestatic 78	com/tencent/android/tpush/encrypt/Rijndael:decrypt	(Ljava/lang/String;)Ljava/lang/String;
    //   64: invokespecial 81	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   67: astore 4
    //   69: ldc 38
    //   71: new 40	java/lang/StringBuilder
    //   74: dup
    //   75: invokespecial 41	java/lang/StringBuilder:<init>	()V
    //   78: ldc 83
    //   80: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: aload 4
    //   85: invokevirtual 84	org/json/JSONObject:length	()I
    //   88: invokevirtual 87	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   91: ldc 89
    //   93: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: aload 4
    //   98: invokevirtual 90	org/json/JSONObject:toString	()Ljava/lang/String;
    //   101: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: invokevirtual 56	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   107: invokestatic 93	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   110: aload 4
    //   112: areturn
    //   113: astore 6
    //   115: aload_3
    //   116: astore 4
    //   118: aload 6
    //   120: astore 5
    //   122: ldc 38
    //   124: aload 5
    //   126: invokevirtual 94	org/json/JSONException:toString	()Ljava/lang/String;
    //   129: invokestatic 97	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   132: aload 4
    //   134: areturn
    //   135: astore 5
    //   137: goto -15 -> 122
    //   140: aload_3
    //   141: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   56	69	113	org/json/JSONException
    //   69	110	135	org/json/JSONException
  }

  private void c(Context paramContext)
  {
    TLog.v("XGService", "@@ saveRegisterPkgs(" + paramContext + ")");
    if ((b == null) || (b.length() == 0))
      return;
    c.a(paramContext, c, Rijndael.encrypt(b.toString()));
  }

  public List a(Context paramContext, long paramLong)
  {
    TLog.v("XGService", "@@ getPkgs(" + paramContext + "," + paramLong + ")");
    ArrayList localArrayList = new ArrayList();
    if (paramContext == null)
      return localArrayList;
    try
    {
      b = b(paramContext);
      String str = b.getString(paramLong + "");
      TLog.i("XGService", ">> accessId:" + paramLong + ",pkgstr:" + str);
      Collections.addAll(localArrayList, str.split(","));
      return localArrayList;
    }
    catch (JSONException localJSONException)
    {
      TLog.e("XGService", localJSONException.toString());
      return localArrayList;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return localArrayList;
  }

  public void a(Context paramContext)
  {
    TLog.v("XGService", "@@ clearRegisterPkgs(" + paramContext + ")");
    c.a(paramContext, c, "");
  }

  public void a(Context paramContext, long paramLong, String paramString)
  {
    TLog.v("XGService", "@@ putPkg(" + paramContext + "," + paramLong + "," + paramString + ")");
    if ((paramContext == null) || (c.a(paramString)))
      return;
    while (true)
    {
      List localList;
      try
      {
        localList = a(paramContext, paramLong);
        if (localList != null)
          continue;
        localObject = new ArrayList();
        ((List)localObject).add(paramString);
        String str1 = localObject.toString().replace(" ", "").replace("\t", "");
        String str2 = str1.substring(1, -1 + str1.length());
        b.put(paramLong + "", str2);
        c(paramContext);
        return;
      }
      catch (JSONException localJSONException)
      {
        TLog.e("XGService", localJSONException.toString());
        return;
        Iterator localIterator = localList.iterator();
        if (localIterator.hasNext())
        {
          boolean bool = paramString.equals((String)localIterator.next());
          if (bool)
            return;
        }
      }
      catch (Exception localException)
      {
        TLog.e("XGService", localException.toString());
        return;
      }
      Object localObject = localList;
    }
  }

  public void a(Context paramContext, String paramString)
  {
    TLog.v("XGService", "@@ delPkg(" + paramContext + "," + paramString + ")");
    if ((paramContext == null) || (paramString == null) || (paramString.length() == 0))
      return;
    while (true)
    {
      int i;
      try
      {
        Iterator localIterator = b.keys();
        if (!localIterator.hasNext())
          break;
        String str1 = (String)localIterator.next();
        List localList = a(paramContext, Long.valueOf(str1).longValue());
        ArrayList localArrayList = new ArrayList();
        i = 0;
        if (i >= localList.size())
          continue;
        if (paramString.equals(localList.get(i)))
        {
          TLog.i("XGService", ">>> pkgName:" + paramString + " deleted");
          localArrayList.add(localList.get(i));
          break label277;
          localList.removeAll(localArrayList);
          String str2 = localList.toString().replace(" ", "").replace("\t", "");
          String str3 = str2.substring(1, -1 + str2.length());
          b.put(str1, str3);
          c(paramContext);
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        TLog.e("TPush", localJSONException.toString());
        return;
      }
      catch (Exception localException)
      {
        TLog.e("TPush", localException.toString());
        return;
      }
      label277: i++;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.b.f
 * JD-Core Version:    0.6.0
 */
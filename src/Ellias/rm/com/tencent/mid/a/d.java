package com.tencent.mid.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.tencent.mid.api.MidCallback;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.api.MidService;
import com.tencent.mid.b.a;
import com.tencent.mid.util.Util;
import com.tencent.mid.util.j;
import java.util.HashMap;
import org.json.JSONObject;

public class d
{
  private static String b = "iikVs3FGzEQ23RaD1JlHsSWSI5Z26m2hX3gO51mH3ag=";
  private static d c = null;
  private static Context d = null;
  Handler a = null;

  private d(Context paramContext)
  {
    try
    {
      HandlerThread localHandlerThread = new HandlerThread("HttpManager");
      localHandlerThread.start();
      this.a = new Handler(localHandlerThread.getLooper());
      d = paramContext.getApplicationContext();
      return;
    }
    catch (Throwable localThrowable)
    {
      Util.logWarn(localThrowable);
    }
  }

  static Context a()
  {
    return d;
  }

  public static d a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (c == null)
        c = new d(paramContext);
      d locald = c;
      return locald;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private String a(f paramf, MidCallback paramMidCallback)
  {
    int i = -1;
    int j = paramf.a();
    String str1 = paramf.b();
    String str2 = "0";
    int k;
    MidEntity localMidEntity;
    int m;
    if (j == 200)
    {
      if (!Util.isStringValid(str1))
        break label351;
      JSONObject localJSONObject = new JSONObject(str1);
      boolean bool1 = localJSONObject.isNull("mid");
      k = 0;
      if (!bool1)
      {
        str2 = localJSONObject.optString("mid");
        boolean bool2 = Util.isMidValid(str2);
        k = 0;
        if (bool2)
        {
          localMidEntity = new MidEntity();
          localMidEntity.setMid(str2);
          localMidEntity.setMac(Util.getWifiMacAddress(d));
          localMidEntity.setImei(Util.getImei(d));
          if (localJSONObject.isNull("ts"))
            break label284;
          long l = localJSONObject.optLong("ts");
          if (l > 0L)
            localMidEntity.setTimestamps(l);
          Util.logInfo("new mid midEntity:" + localMidEntity.toString());
          paramMidCallback.onSuccess(localMidEntity.toString());
          com.tencent.mid.b.g.a(d).a(localMidEntity);
          k = 1;
        }
      }
      if (localJSONObject.isNull(a.c))
        break label345;
      m = localJSONObject.getInt(a.c);
      label229: if (!localJSONObject.isNull(a.d))
        i = localJSONObject.getInt(a.d);
    }
    while (true)
    {
      com.tencent.mid.b.g.a(d).a(m, i);
      if (k == 0)
        paramMidCallback.onSuccess(com.tencent.mid.b.g.a(d).a());
      return str2;
      label284: localMidEntity.setTimestamps(System.currentTimeMillis());
      break;
      String str3 = "Server response error code:" + j + ", error:" + str1;
      Util.logInfo(str3);
      paramMidCallback.onFail(j, str3);
      return str2;
      label345: m = i;
      break label229;
      label351: m = i;
      k = 0;
    }
  }

  private String b()
  {
    return Util.decode(b);
  }

  private void b(g paramg, MidCallback paramMidCallback)
  {
    while (true)
    {
      String str2;
      int i;
      try
      {
        String str1 = Util.getHttpUrl();
        f localf1 = b.a(str1);
        if (localf1.a() == 200)
          continue;
        String str5 = "response code invalid:" + localf1.a();
        Util.logInfo(str5);
        paramMidCallback.onFail(localf1.a(), str5);
        return;
        JSONObject localJSONObject2 = new JSONObject(localf1.b());
        boolean bool = localJSONObject2.isNull("rand");
        str2 = null;
        i = 0;
        if (!bool)
        {
          i = localJSONObject2.getInt("rand");
          str2 = j.a(Util.getHMAC(b(), String.valueOf(i)));
          break label490;
          Util.logInfo("hmac == null");
          return;
          HashMap localHashMap = new HashMap();
          localHashMap.put("k", str2);
          localHashMap.put("s", String.valueOf(i));
          String str3 = str1 + b.a(localHashMap);
          f localf2 = b.a(str3);
          if (localf2.a() == 200)
            continue;
          Util.logInfo("hmac invalid.");
          paramMidCallback.onFail(localf2.a(), "hmac invalid.");
          return;
          JSONObject localJSONObject3 = new JSONObject();
          paramg.a(localJSONObject3);
          Util.jsonPut(localJSONObject3, "rip", Util.getRemoteUrlIp(Util.getHttpUrl()));
          String str4 = a(b.a(str3, "[" + localJSONObject3.toString() + "]"), paramMidCallback);
          if ((Util.isMidValid(localJSONObject3.optString("mid"))) || (Util.isMidValid(str4)))
            continue;
          throw new Exception("get Mid failed, something wrong");
        }
      }
      catch (Throwable localThrowable1)
      {
        if (!MidService.isEnableDebug())
          continue;
        Log.w("MID", "request MID from MID server failed, try to connect MTA server", localThrowable1);
        try
        {
          JSONObject localJSONObject1 = new JSONObject();
          paramg.a(localJSONObject1);
          localJSONObject1.put("ky", "A81AG9CN6AE8");
          Util.jsonPut(localJSONObject1, "rip", Util.getRemoteUrlIp("http://pingma.qq.com:80"));
          a(b.a("http://pingma.qq.com:80/mstat/report/", "[" + localJSONObject1.toString() + "]"), paramMidCallback);
          b.b();
          return;
          b.b();
          return;
        }
        catch (Throwable localThrowable2)
        {
          paramMidCallback.onFail(-10030, localThrowable1.toString());
          Log.e("MID", "request MID  failed", localThrowable1);
          continue;
        }
      }
      finally
      {
        b.b();
      }
      label490: if (str2 == null)
        continue;
      if (i != 0)
        continue;
    }
  }

  void a(g paramg, MidCallback paramMidCallback)
  {
    if ((paramg == null) || (this.a == null) || (paramMidCallback == null))
    {
      if (paramMidCallback != null)
        paramMidCallback.onFail(-10000, "packet == null || handler == null");
      return;
    }
    if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId())
    {
      b(paramg, paramMidCallback);
      return;
    }
    this.a.post(new e(this, paramg, paramMidCallback));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.a.d
 * JD-Core Version:    0.6.0
 */
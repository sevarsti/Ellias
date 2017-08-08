package com.tencent.tmassistantsdk.downloadservice;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.f.a;
import com.tencent.tmassistantsdk.f.b;
import com.tencent.tmassistantsdk.g.l;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
  public String a;
  public String b;
  public String c;
  public int d;
  public int e;
  public String f;
  boolean g;
  public int h;
  public int i;
  public long j;
  protected long k;
  public String l;
  protected String m;
  public int n;
  public int o;
  public String p;
  public long q;
  private HashMap r;
  private String s;

  public d()
  {
    this.g = false;
  }

  public d(String paramString1, int paramInt, String paramString2)
  {
    this.b = paramString1;
    this.c = c.a(paramString1);
    this.f = UUID.randomUUID().toString();
    this.n = paramInt;
    this.i = 0;
    this.o = 0;
    this.g = false;
    this.a = paramString2;
    this.p = c.b();
    this.q = 0L;
  }

  public static d a(Cursor paramCursor)
  {
    d locald = new d();
    locald.h = paramCursor.getInt(paramCursor.getColumnIndex("taskId"));
    locald.f = paramCursor.getString(paramCursor.getColumnIndex("uId"));
    locald.c = paramCursor.getString(paramCursor.getColumnIndex("finalUrl"));
    locald.b = paramCursor.getString(paramCursor.getColumnIndex("taskUrl"));
    locald.l = paramCursor.getString(paramCursor.getColumnIndex("fileName"));
    locald.a = paramCursor.getString(paramCursor.getColumnIndex("contentType"));
    locald.e = paramCursor.getInt(paramCursor.getColumnIndex("redirectCnt"));
    locald.d = paramCursor.getInt(paramCursor.getColumnIndex("retryCnt"));
    locald.k = paramCursor.getLong(paramCursor.getColumnIndex("totalBytes"));
    locald.i = paramCursor.getInt(paramCursor.getColumnIndex("status"));
    locald.j = paramCursor.getLong(paramCursor.getColumnIndex("receivedBytes"));
    locald.n = paramCursor.getInt(paramCursor.getColumnIndex("priority"));
    locald.p = paramCursor.getString(paramCursor.getColumnIndex("netType"));
    locald.o = paramCursor.getInt(paramCursor.getColumnIndex("downloadFailedErrCode"));
    locald.q = paramCursor.getLong(paramCursor.getColumnIndex("downloadFailedTime"));
    locald.s = paramCursor.getString(paramCursor.getColumnIndex("headerParams"));
    return locald;
  }

  public static void a(ContentValues paramContentValues, d paramd)
  {
    if ((paramd != null) && (paramContentValues != null))
    {
      paramContentValues.put("taskId", Integer.valueOf(paramd.h));
      paramContentValues.put("uId", paramd.f);
      paramContentValues.put("finalUrl", paramd.c);
      paramContentValues.put("taskUrl", paramd.b);
      paramContentValues.put("fileName", paramd.l);
      paramContentValues.put("contentType", paramd.a);
      paramContentValues.put("redirectCnt", Integer.valueOf(paramd.e));
      paramContentValues.put("retryCnt", Integer.valueOf(paramd.d));
      paramContentValues.put("totalBytes", Long.valueOf(paramd.k));
      paramContentValues.put("status", Integer.valueOf(paramd.i));
      paramContentValues.put("receivedBytes", Long.valueOf(paramd.j));
      paramContentValues.put("priority", Integer.valueOf(paramd.n));
      paramContentValues.put("netType", paramd.p);
      paramContentValues.put("downloadFailedErrCode", Integer.valueOf(paramd.o));
      paramContentValues.put("downloadFailedTime", Long.valueOf(paramd.q));
      paramContentValues.put("headerParams", paramd.s);
    }
  }

  public static d b(Cursor paramCursor)
  {
    d locald = new d();
    locald.h = paramCursor.getInt(paramCursor.getColumnIndex("taskId"));
    locald.f = paramCursor.getString(paramCursor.getColumnIndex("uId"));
    locald.c = paramCursor.getString(paramCursor.getColumnIndex("finalUrl"));
    locald.b = paramCursor.getString(paramCursor.getColumnIndex("taskUrl"));
    locald.l = paramCursor.getString(paramCursor.getColumnIndex("fileName"));
    locald.a = paramCursor.getString(paramCursor.getColumnIndex("contentType"));
    locald.e = paramCursor.getInt(paramCursor.getColumnIndex("redirectCnt"));
    locald.d = paramCursor.getInt(paramCursor.getColumnIndex("retryCnt"));
    locald.k = paramCursor.getLong(paramCursor.getColumnIndex("totalBytes"));
    locald.i = paramCursor.getInt(paramCursor.getColumnIndex("status"));
    locald.j = paramCursor.getLong(paramCursor.getColumnIndex("receivedBytes"));
    locald.n = paramCursor.getInt(paramCursor.getColumnIndex("priority"));
    locald.p = null;
    locald.o = 0;
    locald.q = 0L;
    locald.s = null;
    return locald;
  }

  public HashMap a()
  {
    return this.r;
  }

  public void a(int paramInt)
  {
    monitorenter;
    while (true)
    {
      try
      {
        boolean bool = e();
        if (bool)
          return;
        this.i = paramInt;
        if (this.i != 6)
        {
          if (this.i != 5)
            continue;
          this.q = System.currentTimeMillis();
          a.a().a(this);
          e.a().a(this.b, this.i, this.o, "");
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      a.a().a(this.b);
    }
  }

  public void a(long paramLong)
  {
    if (0L == paramLong)
      this.j = 0L;
    this.k = paramLong;
    a.a().a(this);
  }

  public void a(String paramString)
  {
    l.b(paramString, "--------dump DownloadInfo-----------");
    l.b(paramString, "mContentType: " + this.a);
    l.b(paramString, "mURL: " + this.b);
    l.b(paramString, "mRetryCnt: " + this.d);
    l.b(paramString, "mRedirectCnt: " + this.e);
    l.b(paramString, "mTotalBytes: " + this.k);
    l.b(paramString, "mUUID: " + this.f);
    l.b(paramString, "mStatus: " + this.i);
    l.b(paramString, "mReceivedBytes: " + this.j);
    l.b(paramString, "mFileName: " + this.l);
    l.b(paramString, "mDownloadFailedErrCode: " + this.o);
    l.b(paramString, "mNetType:" + this.p);
    l.b(paramString, "mDownloadFailedTime:" + this.q);
    l.b(paramString, "mHeaderParamString:" + this.s);
  }

  public void a(HashMap paramHashMap)
  {
    if ((paramHashMap != null) && (paramHashMap.size() > 0))
    {
      this.r = paramHashMap;
      this.s = new JSONObject(paramHashMap).toString();
    }
  }

  public long b()
  {
    return this.k;
  }

  public void b(long paramLong)
  {
    this.j = (paramLong + this.j);
    e.a().a(this.b, this.j, this.k);
  }

  boolean c()
  {
    return (this.k != 0L) && (this.j == this.k);
  }

  boolean d()
  {
    return this.d <= 5;
  }

  boolean e()
  {
    return this.i > 3;
  }

  public int f()
  {
    l.b("_DownloadInfo", "DownloadInfo::startDownloadIfReady url: " + this.b);
    HashMap localHashMap;
    if ((!TextUtils.isEmpty(this.s)) && ((this.r == null) || (this.r.size() <= 0)))
    {
      localHashMap = new HashMap();
      try
      {
        JSONObject localJSONObject = new JSONObject(this.s);
        Iterator localIterator = localJSONObject.keys();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localHashMap.put(str, localJSONObject.getString(str));
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    while (this.g)
    {
      return 5;
      if (localHashMap.size() <= 0)
        continue;
      this.r = localHashMap;
    }
    if (this.l == null)
      this.l = c.a(this.b, this.a);
    if (this.m == null)
      this.m = c.a(this.b, this.a);
    if (this.k == 0L)
      new b(this.m, this.l).a();
    if (this.i == 5)
      this.c = this.b;
    if (!TextUtils.isEmpty(this.l))
    {
      b localb = new b(this.m, this.l);
      long l1 = localb.b();
      l.b("_DownloadInfo", "FileLen: " + l1 + " filename: " + this.l);
      if (l1 > this.k)
        localb.a();
      for (this.j = 0L; c(); this.j = l1)
      {
        localb.f();
        a(4);
        return 4;
      }
    }
    if (this.i == 5)
    {
      this.e = 0;
      this.d = 0;
      this.i = 0;
      this.o = 0;
    }
    l.b("_DownloadInfo", "startDownloadIfReady...");
    a("_DownloadInfo");
    if (h.a().a(this.b) == null)
    {
      g localg = new g(this);
      a(1);
      this.h = h.a().a(localg);
    }
    return 0;
  }

  public void g()
  {
    l.b("_DownloadInfo", "DownloadInfo::pauseDownload url: " + this.b);
    h.a().a(this.h);
    a(3);
  }

  public void h()
  {
    l.b("_DownloadInfo", "DownloadInfo::stopDownload url: " + this.b);
    h.a().a(this.h);
    a(6);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.d
 * JD-Core Version:    0.6.0
 */
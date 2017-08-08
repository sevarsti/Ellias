package com.tencent.android.tpush.service.a;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.i;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  public static long a = a(i.e());
  public static int b = a("recTo", 30000);
  public static int c = a("hbIntvl", 299980);
  public static int d = a("httpHbIntvl", 600000);
  public static int e = a("stIntvl", 54000000);
  public static int f = a("cnMsgExp", 60000);
  public static int g = a("fqcSuc", 10);
  public static int h = a("fqcFal", 100);
  public static int i = a("rptIntvl", 1200);
  public static int j = a("rptMaxCnt", 5);
  public static int k = a("httpRtCnt", 3);
  public static int l = a("ackMaxCnt", 3);
  public static int m = a("ackDuration", 180000);
  public static int n = a("loadIpIntvl", 72000000);
  public static int o = a("redirectConnectTime", 30000);
  public static int p = a("redirectSoTime", 20000);
  public static int q = a("strategyExpiredTime", 1440);
  public static int r;
  public static int s;
  public static int t;
  public static String u;
  public static int v = a("rptLive", 0);
  public static int w = a("rptLiveIntvl", 3600);

  static
  {
    r = a("logLevel", 63);
    s = a("logFileSizeLimit", 262144);
    t = a("errCount", 5);
    u = a("logUploadDomain", "183.61.46.193");
  }

  public static int a(String paramString, int paramInt)
  {
    if (i.e() != null)
      paramInt = com.tencent.android.tpush.service.c.c.b(i.e(), b(paramString), paramInt);
    do
      return paramInt;
    while (com.tencent.android.tpush.logging.b.c.a() == null);
    return com.tencent.android.tpush.service.c.c.b(com.tencent.android.tpush.logging.b.c.a(), b(paramString), paramInt);
  }

  public static int a(String paramString, JSONObject paramJSONObject)
  {
    if ((paramJSONObject != null) && (!com.tencent.android.tpush.service.c.c.a(paramString)))
      try
      {
        int i1 = paramJSONObject.getInt(paramString);
        return i1;
      }
      catch (Exception localException)
      {
        TLog.e("XGService", localException.toString());
      }
    return 0;
  }

  public static long a(Context paramContext)
  {
    long l1 = 1L;
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getConfVersion(" + paramContext + ")");
      l1 = com.tencent.android.tpush.service.c.c.b(paramContext, b("confVer"), l1);
    }
    return l1;
  }

  public static String a(String paramString1, String paramString2)
  {
    String str2;
    if (i.e() != null)
    {
      str2 = com.tencent.android.tpush.service.c.c.c(i.e(), b(paramString1));
      if (!TextUtils.isEmpty(str2));
    }
    String str1;
    do
    {
      do
      {
        return paramString2;
        return str2;
      }
      while (com.tencent.android.tpush.logging.b.c.a() == null);
      str1 = com.tencent.android.tpush.service.c.c.c(com.tencent.android.tpush.logging.b.c.a(), b(paramString1));
    }
    while (TextUtils.isEmpty(str1));
    return str1;
  }

  public static void a(String paramString)
  {
    int i1 = 30000;
    int i2 = 3600;
    int i3 = 5;
    int i4 = 3;
    TLog.v("XGService", "@@ parseValue(" + paramString + ")");
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      a = a("confVer", localJSONObject);
      long l1;
      int i5;
      label102: int i6;
      label134: int i7;
      label166: int i8;
      label198: int i9;
      label227: int i10;
      label252: int i11;
      label277: int i12;
      label303: int i13;
      label327: int i14;
      label352: label373: int i15;
      label402: int i16;
      label437: label458: int i17;
      label483: int i18;
      label509: int i19;
      label533: label555: int i20;
      label579: int i21;
      if (a == 0L)
      {
        l1 = 1L;
        a = l1;
        b = 1000 * a("recTo", localJSONObject);
        if (b != 0)
          break label1087;
        i5 = i1;
        b = i5;
        c = 1000 * (60 * a("hbIntvl", localJSONObject));
        if (c != 0)
          break label1095;
        i6 = 299980;
        c = i6;
        d = 1000 * (60 * a("httpHbIntvl", localJSONObject));
        if (d != 0)
          break label1103;
        i7 = 600000;
        d = i7;
        e = 1000 * (60 * a("stIntvl", localJSONObject));
        if (e != 0)
          break label1111;
        i8 = 54000000;
        e = i8;
        f = 1000 * a("cnMsgExp", localJSONObject);
        if (f != 0)
          break label1119;
        i9 = 60000;
        f = i9;
        g = a("fqcSuc", localJSONObject);
        if (g != 0)
          break label1127;
        i10 = 10;
        g = i10;
        h = a("fqcFal", localJSONObject);
        if (h != 0)
          break label1135;
        i11 = 100;
        h = i11;
        i = a("rptIntvl", localJSONObject);
        if (i != 0)
          break label1143;
        i12 = 1200;
        i = i12;
        j = a("rptMaxCnt", localJSONObject);
        if (j != 0)
          break label1151;
        i13 = i3;
        j = i13;
        k = a("httpRtCnt", localJSONObject);
        if (k != 0)
          break label1159;
        i14 = i4;
        k = i14;
        l = a("ackMaxCnt", localJSONObject);
        if (l != 0)
          break label1167;
        l = i4;
        m = 1000 * a("ackDuration", localJSONObject);
        if (m != 0)
          break label1175;
        i15 = 180000;
        m = i15;
        n = 1000 * (60 * (60 * a("loadIpIntvl", localJSONObject)));
        if (n != 0)
          break label1183;
        i16 = 72000000;
        n = i16;
        o = a("redirectConnectTime", localJSONObject);
        if (o != 0)
          break label1191;
        o = i1;
        p = a("redirectSoTime", localJSONObject);
        if (p != 0)
          break label1198;
        i17 = 20000;
        p = i17;
        q = a("strategyExpiredTime", localJSONObject);
        if (q != 0)
          break label1206;
        i18 = 1440;
        q = i18;
        v = a("rptLive", localJSONObject);
        if (v != 0)
          break label1214;
        i19 = 0;
        v = i19;
        w = a("rptLiveIntvl", localJSONObject);
        if (w != i2)
          break label1222;
        w = i2;
        r = a("logLevel", localJSONObject);
        if (r != 0)
          break label1229;
        i20 = 63;
        r = i20;
        s = 1024 * a("logFileSizeLimit", localJSONObject);
        if (s != 0)
          break label1237;
        i21 = 262144;
        label608: s = i21;
        t = a("errCount", localJSONObject);
        if (t != 0)
          break label1245;
        label629: t = i3;
        u = b("logUploadDomain", localJSONObject);
        if (!TextUtils.isEmpty(u))
          break label1252;
      }
      label1087: label1095: label1103: label1111: label1119: label1252: for (String str = "183.61.46.193"; ; str = u)
      {
        u = str;
        TLog.i("XGService", ">> redct=" + o + " redso=" + p + " expi=" + q + " loglevel=" + r + " filesize=" + s + " rptLive=" + v);
        if (i.e() == null)
          return;
        com.tencent.android.tpush.service.c.c.a(i.e(), b("confVer"), a);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("recTo"), b);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("hbIntvl"), c);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("httpHbIntvl"), d);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("stIntvl"), e);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("cnMsgExp"), f);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("fqcSuc"), g);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("fqcFal"), h);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("rptIntvl"), i);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("rptMaxCnt"), j);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("httpRtCnt"), k);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("ackMaxCnt"), l);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("ackDuration"), m);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("loadIpIntvl"), n);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("redirectConnectTime"), o);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("redirectSoTime"), p);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("strategyExpiredTime"), q);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("rptLive"), v);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("rptLiveIntvl"), w);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("logLevel"), r);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("logFileSizeLimit"), s);
        com.tencent.android.tpush.service.c.c.a(i.e(), b("errCount"), t);
        return;
        l1 = a;
        break;
        i5 = b;
        break label102;
        i6 = c;
        break label134;
        i7 = d;
        break label166;
        i8 = e;
        break label198;
        i9 = f;
        break label227;
        label1127: i10 = g;
        break label252;
        label1135: i11 = h;
        break label277;
        label1143: i12 = i;
        break label303;
        label1151: i13 = j;
        break label327;
        i14 = k;
        break label352;
        i4 = l;
        break label373;
        i15 = m;
        break label402;
        i16 = n;
        break label437;
        i1 = o;
        break label458;
        i17 = p;
        break label483;
        i18 = q;
        break label509;
        i19 = v;
        break label533;
        i2 = w;
        break label555;
        i20 = r;
        break label579;
        i21 = s;
        break label608;
        i3 = t;
        break label629;
      }
    }
    catch (Exception localException)
    {
      label1159: label1167: label1175: label1183: label1191: label1198: label1206: label1214: label1222: label1229: label1237: label1245: TLog.e("XGService", localException.toString());
    }
  }

  public static String b(String paramString)
  {
    return "com.tencent.tpus." + paramString;
  }

  public static String b(String paramString, JSONObject paramJSONObject)
  {
    if ((paramJSONObject != null) && (!com.tencent.android.tpush.service.c.c.a(paramString)))
      try
      {
        String str = paramJSONObject.getString(paramString);
        return str;
      }
      catch (JSONException localJSONException)
      {
        TLog.e("XGService", localJSONException.toString());
      }
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.a.a
 * JD-Core Version:    0.6.0
 */
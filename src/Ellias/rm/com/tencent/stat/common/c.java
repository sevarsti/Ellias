package com.tencent.stat.common;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.tencent.stat.StatConfig;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONObject;

class c
{
  String a;
  String b = "1.6.2";
  DisplayMetrics c;
  int d = Build.VERSION.SDK_INT;
  String e = Build.MODEL;
  String f = Build.MANUFACTURER;
  String g = Locale.getDefault().getLanguage();
  String h;
  String i;
  String j;
  String k;
  int l = 0;
  String m = null;
  Context n = null;
  private String o = null;
  private String p = null;
  private String q = null;
  private String r = null;

  private c(Context paramContext)
  {
    this.n = paramContext;
    this.c = k.d(paramContext);
    this.a = k.n(paramContext);
    this.h = StatConfig.getInstallChannel(paramContext);
    this.i = k.m(paramContext);
    this.j = TimeZone.getDefault().getID();
    this.l = k.s(paramContext);
    this.k = k.t(paramContext);
    this.m = paramContext.getPackageName();
    if (this.d >= 14)
      this.o = k.A(paramContext);
    this.p = k.z(paramContext).toString();
    this.q = k.x(paramContext);
    this.r = k.e();
  }

  void a(JSONObject paramJSONObject)
  {
    paramJSONObject.put("sr", this.c.widthPixels + "*" + this.c.heightPixels);
    k.a(paramJSONObject, "av", this.a);
    k.a(paramJSONObject, "ch", this.h);
    k.a(paramJSONObject, "mf", this.f);
    k.a(paramJSONObject, "sv", this.b);
    k.a(paramJSONObject, "ov", Integer.toString(this.d));
    paramJSONObject.put("os", 1);
    k.a(paramJSONObject, "op", this.i);
    k.a(paramJSONObject, "lg", this.g);
    k.a(paramJSONObject, "md", this.e);
    k.a(paramJSONObject, "tz", this.j);
    if (this.l != 0)
      paramJSONObject.put("jb", this.l);
    k.a(paramJSONObject, "sd", this.k);
    k.a(paramJSONObject, "apn", this.m);
    if (k.h(this.n))
    {
      JSONObject localJSONObject = new JSONObject();
      k.a(localJSONObject, "bs", k.C(this.n));
      k.a(localJSONObject, "ss", k.D(this.n));
      if (localJSONObject.length() > 0)
        k.a(paramJSONObject, "wf", localJSONObject.toString());
    }
    k.a(paramJSONObject, "sen", this.o);
    k.a(paramJSONObject, "cpu", this.p);
    k.a(paramJSONObject, "ram", this.q);
    k.a(paramJSONObject, "rom", this.r);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.c
 * JD-Core Version:    0.6.0
 */
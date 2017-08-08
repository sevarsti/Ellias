package com.tencent.mid.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

class g
{
  String a;
  String b = String.valueOf(2.1F);
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

  private g(Context paramContext)
  {
    this.n = paramContext;
    this.c = i.a(paramContext);
    this.a = i.c(paramContext);
    this.i = i.b(paramContext);
    this.j = TimeZone.getDefault().getID();
    this.k = i.f(paramContext);
    this.m = paramContext.getPackageName();
    this.r = i.a();
  }

  void a(JSONObject paramJSONObject)
  {
    paramJSONObject.put("sr", this.c.widthPixels + "*" + this.c.heightPixels);
    Util.jsonPut(paramJSONObject, "av", this.a);
    Util.jsonPut(paramJSONObject, "ch", this.h);
    Util.jsonPut(paramJSONObject, "mf", this.f);
    Util.jsonPut(paramJSONObject, "sv", this.b);
    Util.jsonPut(paramJSONObject, "ov", Integer.toString(this.d));
    paramJSONObject.put("os", 1);
    Util.jsonPut(paramJSONObject, "op", this.i);
    Util.jsonPut(paramJSONObject, "lg", this.g);
    Util.jsonPut(paramJSONObject, "md", this.e);
    Util.jsonPut(paramJSONObject, "tz", this.j);
    if (this.l != 0)
      paramJSONObject.put("jb", this.l);
    Util.jsonPut(paramJSONObject, "sd", this.k);
    Util.jsonPut(paramJSONObject, "apn", this.m);
    if ((Util.isNetworkAvailable(this.n)) && (Util.isWifiNet(this.n)))
    {
      JSONObject localJSONObject = new JSONObject();
      Util.jsonPut(localJSONObject, "bs", Util.getWiFiBBSID(this.n));
      Util.jsonPut(localJSONObject, "ss", Util.getWiFiSSID(this.n));
      if (localJSONObject.length() > 0)
        Util.jsonPut(paramJSONObject, "wf", localJSONObject.toString());
    }
    JSONArray localJSONArray = Util.getWifiTopN(this.n, 10);
    if ((localJSONArray != null) && (localJSONArray.length() > 0))
      Util.jsonPut(paramJSONObject, "wflist", localJSONArray.toString());
    Util.jsonPut(paramJSONObject, "sen", this.o);
    Util.jsonPut(paramJSONObject, "cpu", this.p);
    Util.jsonPut(paramJSONObject, "ram", this.q);
    Util.jsonPut(paramJSONObject, "rom", this.r);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.util.g
 * JD-Core Version:    0.6.0
 */
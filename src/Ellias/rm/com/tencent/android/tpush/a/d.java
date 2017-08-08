package com.tencent.android.tpush.a;

import com.tencent.android.tpush.common.i;
import com.tencent.android.tpush.logging.TLog;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
  public int a = 1;
  public String b = "";
  public e c = new e();
  public String d = "";
  public String e = "";
  public String f = "";
  public int g = 1;

  private void a(String paramString)
  {
    JSONObject localJSONObject1 = new JSONObject(paramString);
    if (!localJSONObject1.isNull("action_type"))
      this.a = localJSONObject1.getInt("action_type");
    if (!localJSONObject1.isNull("activity"))
      this.b = localJSONObject1.getString("activity");
    String str;
    if (!localJSONObject1.isNull("aty_attr"))
    {
      str = localJSONObject1.optString("aty_attr");
      if (i.a(str));
    }
    try
    {
      JSONObject localJSONObject3 = new JSONObject(str);
      this.c.a = localJSONObject3.optInt("if");
      this.c.b = localJSONObject3.optInt("pf");
      if (!localJSONObject1.isNull("intent"))
        this.d = localJSONObject1.getString("intent");
      if (!localJSONObject1.isNull("browser"))
      {
        this.e = localJSONObject1.getString("browser");
        JSONObject localJSONObject2 = new JSONObject(this.e);
        if (!localJSONObject2.isNull("url"))
          this.f = localJSONObject2.getString("url");
        if (!localJSONObject2.isNull("confirm"))
          this.g = localJSONObject2.getInt("confirm");
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        TLog.w("TPush", "decode activityAttribute error", localJSONException);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.a.d
 * JD-Core Version:    0.6.0
 */
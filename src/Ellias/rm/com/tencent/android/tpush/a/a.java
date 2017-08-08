package com.tencent.android.tpush.a;

import org.json.JSONObject;

public abstract class a
{
  protected JSONObject a = null;
  protected String b = null;
  private String c = null;
  private String d = null;
  private String e = null;
  private String f = null;

  protected a(String paramString)
  {
    this.b = paramString;
  }

  public void a()
  {
    this.a = new JSONObject(this.b);
    if (!this.a.isNull("title"))
      this.c = this.a.getString("title");
    if (!this.a.isNull("content"))
      this.d = this.a.getString("content");
    if (!this.a.isNull("custom_content"))
    {
      String str = this.a.getString("custom_content");
      if ((str != null) && (!str.trim().equals("{}")))
        this.e = str;
    }
    if (!this.a.isNull("accept_time"))
      this.f = this.a.getString("accept_time");
    c();
  }

  public abstract int b();

  protected abstract void c();

  public String d()
  {
    return this.c;
  }

  public String e()
  {
    return this.d;
  }

  public String f()
  {
    return this.e;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.a.a
 * JD-Core Version:    0.6.0
 */
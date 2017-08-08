package com.tencent.android.tpush.a;

import com.tencent.android.tpush.logging.TLog;
import org.json.JSONObject;

public class c extends a
{
  private int c = 0;
  private int d = 0;
  private int e = 0;
  private int f = 1;
  private int g = 0;
  private int h = 0;
  private d i = new d();

  public c(String paramString)
  {
    super(paramString);
  }

  public int b()
  {
    return 1;
  }

  protected void c()
  {
    TLog.i("XGPushMessage", "onDecode message :" + this.a);
    this.c = this.a.optInt("builder_id");
    this.d = this.a.optInt("ring");
    this.e = this.a.optInt("vibrate");
    this.h = this.a.optInt("icon");
    this.g = this.a.optInt("n_id");
    if (!this.a.isNull("clearable"))
      this.f = this.a.optInt("clearable");
    if (!this.a.isNull("action"))
      d.a(this.i, this.a.getString("action"));
  }

  public int g()
  {
    return this.c;
  }

  public int h()
  {
    return this.d;
  }

  public int i()
  {
    return this.e;
  }

  public int j()
  {
    return this.f;
  }

  public int k()
  {
    return this.g;
  }

  public d l()
  {
    return this.i;
  }

  public int m()
  {
    return this.h;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.a.c
 * JD-Core Version:    0.6.0
 */
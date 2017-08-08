package com.tencent.android.tpush;

public class XGPushTextMessage
{
  String a = "";
  String b = "";
  String c = "";

  public String getContent()
  {
    return this.b;
  }

  public String getCustomContent()
  {
    return this.c;
  }

  public String getTitle()
  {
    return this.a;
  }

  public String toString()
  {
    return "TPushTextMessage [title=" + this.a + ", content=" + this.b + ", customContent=" + this.c + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGPushTextMessage
 * JD-Core Version:    0.6.0
 */
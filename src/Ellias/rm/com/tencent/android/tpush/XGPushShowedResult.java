package com.tencent.android.tpush;

import android.content.Intent;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.logging.TLog;

public class XGPushShowedResult
  implements XGIResult
{
  long a = 0L;
  String b = "";
  String c = "";
  String d = "";
  String e = "";
  String f = "";

  public String getActivity()
  {
    return this.e;
  }

  public String getContent()
  {
    return this.c;
  }

  public String getCustomContent()
  {
    return this.d;
  }

  public long getMsgId()
  {
    return this.a;
  }

  public String getTitle()
  {
    return this.b;
  }

  public String getUrl()
  {
    return this.f;
  }

  public void parseIntent(Intent paramIntent)
  {
    TLog.d("XGPushMessage", paramIntent.toString());
    this.a = paramIntent.getLongExtra("msgId", -1L);
    this.e = paramIntent.getStringExtra("activity");
    this.f = paramIntent.getStringExtra("url");
    this.b = Rijndael.decrypt(paramIntent.getStringExtra("title"));
    this.c = Rijndael.decrypt(paramIntent.getStringExtra("content"));
    TLog.d("XGPushMessage", this.c);
    this.d = Rijndael.decrypt(paramIntent.getStringExtra("custom_content"));
  }

  public String toString()
  {
    return "XGPushShowedResult [msgId=" + this.a + ", title=" + this.b + ", content=" + this.c + ", customContent=" + this.d + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGPushShowedResult
 * JD-Core Version:    0.6.0
 */
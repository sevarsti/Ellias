package com.tencent.component.net.http;

import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.AssertUtil;

@PluginApi(a=8)
public class AsyncHttpResult
{
  private final String a;
  private final AsyncHttpResult.Status b = new AsyncHttpResult.Status();
  private final AsyncHttpResult.Process c = new AsyncHttpResult.Process();
  private final AsyncHttpResult.Content d = new AsyncHttpResult.Content();

  protected AsyncHttpResult(String paramString)
  {
    if (!TextUtils.isEmpty(paramString));
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      this.a = paramString;
      return;
    }
  }

  final String a()
  {
    return this.a;
  }

  @PluginApi(a=8)
  public AsyncHttpResult.Content getContent()
  {
    return this.d;
  }

  @PluginApi(a=8)
  public AsyncHttpResult.Process getProcess()
  {
    return this.c;
  }

  @PluginApi(a=8)
  public AsyncHttpResult.Status getStatus()
  {
    return this.b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncHttpResult
 * JD-Core Version:    0.6.0
 */
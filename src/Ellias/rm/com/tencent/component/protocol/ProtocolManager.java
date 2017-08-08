package com.tencent.component.protocol;

import android.content.Context;
import com.tencent.component.ComponentContext;
import com.tencent.component.net.http.AsyncRequestListener;
import com.tencent.component.net.http.HttpTemplate;
import com.tencent.component.utils.log.LogUtil;

public class ProtocolManager extends HttpTemplate
{
  public static String a = "http://gamehall.3g.qq.com/gamejoy/m";
  public static final int b = 30000;
  private static final String c = "ProtocolManager";
  private static ProtocolManager d = new ProtocolManager(ComponentContext.a());
  private AsyncRequestListener e = new a(this);

  private ProtocolManager(Context paramContext)
  {
    super(paramContext);
  }

  public static ProtocolManager b()
  {
    return d;
  }

  public int a(ProtocolRequest paramProtocolRequest)
  {
    ProtocolHttpRequestWrapper localProtocolHttpRequestWrapper = new ProtocolHttpRequestWrapper(paramProtocolRequest);
    localProtocolHttpRequestWrapper.b(a);
    a(localProtocolHttpRequestWrapper, this.e);
    int i = paramProtocolRequest.f();
    LogUtil.i("ProtocolManager", "Send Request => [seqNo:" + i + "] " + "[cmd:" + paramProtocolRequest.d() + "] " + paramProtocolRequest.l() + " url:" + localProtocolHttpRequestWrapper.getUrl());
    return i;
  }

  public int a(ProtocolRequest paramProtocolRequest, String paramString)
  {
    ProtocolHttpRequestWrapper localProtocolHttpRequestWrapper = new ProtocolHttpRequestWrapper(paramProtocolRequest);
    localProtocolHttpRequestWrapper.b(paramString);
    a(localProtocolHttpRequestWrapper, this.e);
    LogUtil.i("ProtocolManager", "Send Request => [seqNo:" + paramProtocolRequest.f() + "] " + "[cmd:" + paramProtocolRequest.d() + "] " + paramProtocolRequest.l() + " url:" + localProtocolHttpRequestWrapper.getUrl());
    return paramProtocolRequest.f();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.protocol.ProtocolManager
 * JD-Core Version:    0.6.0
 */
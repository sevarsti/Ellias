package com.tencent.component.protocol;

import com.tencent.component.net.http.AsyncHttpResult;
import com.tencent.component.net.http.AsyncHttpResult.Content;
import com.tencent.component.net.http.AsyncHttpResult.NetworkFailDescription;
import com.tencent.component.net.http.AsyncHttpResult.Status;
import com.tencent.component.net.http.AsyncRequestListener;
import com.tencent.component.net.http.request.AsyncHttpRequest;
import com.tencent.component.utils.log.LogUtil;

class a
  implements AsyncRequestListener
{
  a(ProtocolManager paramProtocolManager)
  {
  }

  public void onRequestCanceled(AsyncHttpRequest paramAsyncHttpRequest)
  {
    LogUtil.i("ProtocolManager", "onRequestCanceled => [seqNo:" + paramAsyncHttpRequest.getSeqNo() + "] ");
    if ((paramAsyncHttpRequest instanceof ProtocolHttpRequestWrapper))
      ((ProtocolHttpRequestWrapper)paramAsyncHttpRequest).a.j();
  }

  public void onRequestEnqueque(AsyncHttpRequest paramAsyncHttpRequest)
  {
    if ((paramAsyncHttpRequest instanceof ProtocolHttpRequestWrapper))
      ((ProtocolHttpRequestWrapper)paramAsyncHttpRequest).a.h();
  }

  public void onRequestFailed(AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult)
  {
    LogUtil.i("ProtocolManager", "onRequestFailed => [seqNo:" + paramAsyncHttpRequest.getSeqNo() + "] ");
    ProtocolRequest localProtocolRequest;
    if ((paramAsyncHttpRequest instanceof ProtocolHttpRequestWrapper))
    {
      localProtocolRequest = ((ProtocolHttpRequestWrapper)paramAsyncHttpRequest).a;
      if (paramAsyncHttpResult != null)
        localProtocolRequest.a(paramAsyncHttpResult.getStatus().getFailReason());
    }
    else
    {
      return;
    }
    localProtocolRequest.a(new AsyncHttpResult.NetworkFailDescription(-10900));
  }

  public void onRequestStart(AsyncHttpRequest paramAsyncHttpRequest)
  {
    if ((paramAsyncHttpRequest instanceof ProtocolHttpRequestWrapper))
      ((ProtocolHttpRequestWrapper)paramAsyncHttpRequest).a.g();
  }

  public void onRequestSuccess(AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult)
  {
    LogUtil.i("ProtocolManager", "onRequestSuccess => [seqNo:" + paramAsyncHttpRequest.getSeqNo() + "] ");
    ProtocolRequest localProtocolRequest;
    if ((paramAsyncHttpRequest instanceof ProtocolHttpRequestWrapper))
    {
      localProtocolRequest = ((ProtocolHttpRequestWrapper)paramAsyncHttpRequest).a;
      byte[] arrayOfByte = (byte[])(byte[])paramAsyncHttpResult.getContent().content;
      if ((arrayOfByte != null) && (arrayOfByte.length > 0) && (paramAsyncHttpResult != null))
        localProtocolRequest.a(paramAsyncHttpResult.getContent().contentLength, arrayOfByte);
    }
    else
    {
      return;
    }
    localProtocolRequest.a(new AsyncHttpResult.NetworkFailDescription(-10900));
  }

  public void onRequestTimeout(AsyncHttpRequest paramAsyncHttpRequest)
  {
    if ((paramAsyncHttpRequest instanceof ProtocolHttpRequestWrapper))
      ((ProtocolHttpRequestWrapper)paramAsyncHttpRequest).a.i();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.protocol.a
 * JD-Core Version:    0.6.0
 */
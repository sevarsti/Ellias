package com.tencent.component.protocol;

import com.tencent.component.net.http.request.AsyncHttpPostRequest;
import org.apache.http.entity.ByteArrayEntity;

public class ProtocolHttpRequestWrapper extends AsyncHttpPostRequest
{
  public ProtocolRequest a;

  public ProtocolHttpRequestWrapper(ProtocolRequest paramProtocolRequest)
  {
    this.a = paramProtocolRequest;
    a(new ByteArrayEntity(paramProtocolRequest.b()));
    setSeqNo(paramProtocolRequest.f());
    a(paramProtocolRequest.c());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.protocol.ProtocolHttpRequestWrapper
 * JD-Core Version:    0.6.0
 */
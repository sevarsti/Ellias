package com.tencent.mm.sdk.openapi;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;

public abstract interface IWXAPIEventHandler
{
  public abstract void onReq(BaseReq paramBaseReq);

  public abstract void onResp(BaseResp paramBaseResp);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mm.sdk.openapi.IWXAPIEventHandler
 * JD-Core Version:    0.6.0
 */
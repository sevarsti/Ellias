package com.tencent.msdk.push.req;

public enum MsgId
{
  static
  {
    MsgId[] arrayOfMsgId = new MsgId[6];
    arrayOfMsgId[0] = ClickStateReq;
    arrayOfMsgId[1] = ClientRegReq;
    arrayOfMsgId[2] = PullMsgReq;
    arrayOfMsgId[3] = PullProxyReq;
    arrayOfMsgId[4] = PushStateReq;
    arrayOfMsgId[5] = UnRegisterAppReq;
    $VALUES = arrayOfMsgId;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.req.MsgId
 * JD-Core Version:    0.6.0
 */
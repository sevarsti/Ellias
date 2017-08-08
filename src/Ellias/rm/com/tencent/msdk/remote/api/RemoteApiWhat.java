package com.tencent.msdk.remote.api;

public enum RemoteApiWhat
{
  static
  {
    CleanLocation = new RemoteApiWhat("CleanLocation", 1);
    SendToQzone = new RemoteApiWhat("SendToQzone", 2);
    ShareToWx = new RemoteApiWhat("ShareToWx", 3);
    QueryQQFriends = new RemoteApiWhat("QueryQQFriends", 4);
    QueryWXFriends = new RemoteApiWhat("QueryWXFriends", 5);
    QueryWXMyInfo = new RemoteApiWhat("QueryWXMyInfo", 6);
    QueryQQMyInfo = new RemoteApiWhat("QueryQQMyInfo", 7);
    Feedback = new RemoteApiWhat("Feedback", 8);
    QueryNearbyPlayer = new RemoteApiWhat("QueryNearbyPlayer", 9);
    ShareWeChatGameCenter = new RemoteApiWhat("ShareWeChatGameCenter", 10);
    RegisterReq = new RemoteApiWhat("RegisterReq", 11);
    RemoteApiWhat[] arrayOfRemoteApiWhat = new RemoteApiWhat[12];
    arrayOfRemoteApiWhat[0] = ShareToQQ;
    arrayOfRemoteApiWhat[1] = CleanLocation;
    arrayOfRemoteApiWhat[2] = SendToQzone;
    arrayOfRemoteApiWhat[3] = ShareToWx;
    arrayOfRemoteApiWhat[4] = QueryQQFriends;
    arrayOfRemoteApiWhat[5] = QueryWXFriends;
    arrayOfRemoteApiWhat[6] = QueryWXMyInfo;
    arrayOfRemoteApiWhat[7] = QueryQQMyInfo;
    arrayOfRemoteApiWhat[8] = Feedback;
    arrayOfRemoteApiWhat[9] = QueryNearbyPlayer;
    arrayOfRemoteApiWhat[10] = ShareWeChatGameCenter;
    arrayOfRemoteApiWhat[11] = RegisterReq;
    $VALUES = arrayOfRemoteApiWhat;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.RemoteApiWhat
 * JD-Core Version:    0.6.0
 */
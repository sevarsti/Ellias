package com.tencent.component.net.socket;

class d
  implements Runnable
{
  d(SocketNetworkService paramSocketNetworkService, byte[] paramArrayOfByte)
  {
  }

  public void run()
  {
    INotifyPackageReceiver localINotifyPackageReceiver = SocketNetworkService.c(this.b);
    if (localINotifyPackageReceiver != null);
    try
    {
      localINotifyPackageReceiver.onPackageReveiver(this.a);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.d
 * JD-Core Version:    0.6.0
 */
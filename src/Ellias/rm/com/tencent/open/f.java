package com.tencent.open;

import android.location.Location;
import com.tencent.map.a.a.b;

public class f extends b
{
  private d.a a;

  public f(d.a parama)
  {
    super(1, 0, 0, 8);
    this.a = parama;
  }

  public void a(int paramInt)
  {
    com.tencent.a.a.d.c("openSDK_LOG", "location: onStatusUpdate = " + paramInt);
    super.a(paramInt);
  }

  public void a(com.tencent.map.a.a.d paramd)
  {
    com.tencent.a.a.d.c("openSDK_LOG", "location: onLocationUpdate = " + paramd);
    super.a(paramd);
    if (paramd == null);
    Location localLocation;
    do
    {
      return;
      localLocation = new Location("passive");
      localLocation.setLatitude(paramd.b);
      localLocation.setLongitude(paramd.c);
    }
    while (this.a == null);
    this.a.onLocationUpdate(localLocation);
  }

  public void a(byte[] paramArrayOfByte, int paramInt)
  {
    super.a(paramArrayOfByte, paramInt);
    com.tencent.a.a.d.c("openSDK_LOG", "location: onLocationDataUpdate = " + paramArrayOfByte);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.f
 * JD-Core Version:    0.6.0
 */
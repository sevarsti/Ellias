package com.tencent.component.plugin;

import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;

class n
  implements PluginManageHandler
{
  private IBinder a;

  n(IBinder paramIBinder)
  {
    this.a = paramIBinder;
  }

  public Intent a(String paramString, Uri paramUri)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    while (true)
    {
      try
      {
        localParcel1.writeInterfaceToken("com.tencent.component.plugin.PluginManageHandler");
        localParcel1.writeString(paramString);
        if (paramUri == null)
          continue;
        localParcel1.writeInt(1);
        paramUri.writeToParcel(localParcel1, 0);
        this.a.transact(1, localParcel1, localParcel2, 0);
        localParcel2.readException();
        if (localParcel2.readInt() != 0)
        {
          localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
          return localIntent;
          localParcel1.writeInt(0);
          continue;
        }
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
      Intent localIntent = null;
    }
  }

  public String a()
  {
    return "com.tencent.component.plugin.PluginManageHandler";
  }

  public IBinder asBinder()
  {
    return this.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.n
 * JD-Core Version:    0.6.0
 */
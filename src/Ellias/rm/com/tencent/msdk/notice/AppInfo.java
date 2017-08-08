package com.tencent.msdk.notice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class AppInfo
  implements Parcelable
{
  public static final Parcelable.Creator<AppInfo> CREATOR = new Parcelable.Creator()
  {
    public AppInfo createFromParcel(Parcel paramParcel)
    {
      return new AppInfo(paramParcel);
    }

    public AppInfo[] newArray(int paramInt)
    {
      return new AppInfo[paramInt];
    }
  };
  public String appid = "";
  public String matid = "";
  public String msdkVersion = "";
  public String openid = "";
  public String packageName = "";
  public String updateTime = "";

  public AppInfo()
  {
  }

  public AppInfo(Parcel paramParcel)
  {
    this.appid = paramParcel.readString();
    this.matid = paramParcel.readString();
    this.openid = paramParcel.readString();
    this.msdkVersion = paramParcel.readString();
    this.packageName = paramParcel.readString();
    this.updateTime = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.appid);
    paramParcel.writeString(this.matid);
    paramParcel.writeString(this.openid);
    paramParcel.writeString(this.msdkVersion);
    paramParcel.writeString(this.packageName);
    paramParcel.writeString(this.updateTime);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.AppInfo
 * JD-Core Version:    0.6.0
 */
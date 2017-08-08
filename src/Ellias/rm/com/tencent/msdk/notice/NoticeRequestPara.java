package com.tencent.msdk.notice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class NoticeRequestPara
  implements Parcelable
{
  public static final Parcelable.Creator<NoticeRequestPara> CREATOR;
  public static eMSDK_SCREENDIR SCREEN_DIR = eMSDK_SCREENDIR.eMSDK_SCREENDIR_SENSOR;
  public static int sGameVersion = 0;
  public static int sScreenDpi = 0;
  public String mApn = "";
  public String mAppId = "";
  public String mAppKey = "";
  public String mIosDeviceToken = "";
  public String mLastTime = "";
  public String mMatid = "";
  public String mMsdkVersion = "";
  public int mNoticeVersion = 0;
  public String mOpenId = "";
  public int mOs = 1;
  public String mOsVersion = "";
  public String mProtocolVer = "";
  public String mResolution = "";
  public int mScreenDir = 0;
  public int mScreenDpi = 0;
  public String mTradeMark = "";

  static
  {
    CREATOR = new Parcelable.Creator()
    {
      public NoticeRequestPara createFromParcel(Parcel paramParcel)
      {
        return new NoticeRequestPara(paramParcel);
      }

      public NoticeRequestPara[] newArray(int paramInt)
      {
        return new NoticeRequestPara[paramInt];
      }
    };
  }

  public NoticeRequestPara()
  {
  }

  public NoticeRequestPara(Parcel paramParcel)
  {
    this();
    this.mAppId = paramParcel.readString();
    this.mMatid = paramParcel.readString();
    this.mOpenId = paramParcel.readString();
    this.mOsVersion = paramParcel.readString();
    this.mTradeMark = paramParcel.readString();
    this.mResolution = paramParcel.readString();
    this.mApn = paramParcel.readString();
    this.mIosDeviceToken = paramParcel.readString();
    this.mLastTime = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mAppId);
    paramParcel.writeString(this.mMatid);
    paramParcel.writeString(this.mOpenId);
    paramParcel.writeInt(this.mOs);
    paramParcel.writeString(this.mOsVersion);
    paramParcel.writeString(this.mTradeMark);
    paramParcel.writeString(this.mResolution);
    paramParcel.writeString(this.mApn);
    paramParcel.writeString(this.mMsdkVersion);
    paramParcel.writeString(this.mProtocolVer);
    paramParcel.writeString(this.mIosDeviceToken);
    paramParcel.writeString(this.mLastTime);
    paramParcel.writeInt(this.mNoticeVersion);
    paramParcel.writeInt(this.mScreenDir);
    paramParcel.writeInt(this.mScreenDpi);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.NoticeRequestPara
 * JD-Core Version:    0.6.0
 */
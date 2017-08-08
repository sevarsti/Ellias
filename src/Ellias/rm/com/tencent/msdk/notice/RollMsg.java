package com.tencent.msdk.notice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RollMsg
  implements Parcelable
{
  public static final Parcelable.Creator<RollMsg> CREATOR = new Parcelable.Creator()
  {
    public RollMsg createFromParcel(Parcel paramParcel)
    {
      return new RollMsg(paramParcel);
    }

    public RollMsg[] newArray(int paramInt)
    {
      return new RollMsg[paramInt];
    }
  };
  public String appId = "";
  public String msgContent = "";
  public String msgId = "";
  public String msgType = "";
  public String msgurl = "";
  public String showStyle = "";

  public RollMsg()
  {
  }

  public RollMsg(Parcel paramParcel)
  {
    this.appId = paramParcel.readString();
    this.msgType = paramParcel.readString();
    this.msgId = paramParcel.readString();
    this.msgContent = paramParcel.readString();
    this.msgurl = paramParcel.readString();
    this.showStyle = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.appId);
    paramParcel.writeString(this.msgType);
    paramParcel.writeString(this.msgId);
    paramParcel.writeString(this.msgContent);
    paramParcel.writeString(this.msgurl);
    paramParcel.writeString(this.showStyle);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.RollMsg
 * JD-Core Version:    0.6.0
 */
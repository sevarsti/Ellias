package com.tencent.msdk.communicator;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MHttpResponse
  implements Parcelable
{
  public static final Parcelable.Creator<MHttpResponse> CREATOR = new Parcelable.Creator()
  {
    public MHttpResponse createFromParcel(Parcel paramParcel)
    {
      return new MHttpResponse(paramParcel);
    }

    public MHttpResponse[] newArray(int paramInt)
    {
      return new MHttpResponse[paramInt];
    }
  };
  public static final int HTTP_CPROTOCOL_EXCEPTION = 3006;
  public static final int HTTP_ILLEGAL_ARGUMENT = 3004;
  public static final int HTTP_ILLEGAL_STATE = 3002;
  public static final int HTTP_IO_EXCEPTION = 3003;
  public static final int HTTP_OTHER_ERROR = 3000;
  public static final int HTTP_PARAMS_ERROR = 1006;
  public static final int HTTP_RETURN_NULL_VALUE = 1002;
  public static final int HTTP_SOCKET_EXCEPTION = 3005;
  public static final int HTTP_SUCCESS = 200;
  public static final int HTTP_TIME_OUT = 3001;
  public static final int HTTP_UNKNOWN_HOST = 3007;
  private String body;
  private String msg;
  private int status;

  public MHttpResponse()
  {
  }

  public MHttpResponse(int paramInt, String paramString1, String paramString2)
  {
    this.status = paramInt;
    this.msg = paramString1;
    this.body = paramString2;
  }

  public MHttpResponse(Parcel paramParcel)
  {
    this.status = paramParcel.readInt();
    this.msg = paramParcel.readString();
    paramParcel.readInt();
    this.body = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getBody()
  {
    return this.body;
  }

  public String getMsg()
  {
    return this.msg;
  }

  public int getStatus()
  {
    return this.status;
  }

  public void setBody(String paramString)
  {
    this.body = paramString;
  }

  public void setMsg(String paramString)
  {
    this.msg = paramString;
  }

  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.status);
    paramParcel.writeString(this.msg);
    paramParcel.writeInt(this.body.length());
    paramParcel.writeString(this.body);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.communicator.MHttpResponse
 * JD-Core Version:    0.6.0
 */
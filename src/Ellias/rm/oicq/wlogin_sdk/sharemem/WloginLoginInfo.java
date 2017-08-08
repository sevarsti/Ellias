package oicq.wlogin_sdk.sharemem;

import B;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.List;

public class WloginLoginInfo
  implements Serializable, Parcelable
{
  public static final Parcelable.Creator<WloginLoginInfo> CREATOR;
  public static int TYPE_LOACL = 0;
  public static int TYPE_REMOTE = 0;
  private static final long serialVersionUID = 5551948389726789420L;
  public String mAccount;
  public long mAppid;
  public long mCreateTime;
  public String mFaceUrl = "";
  public int mType;
  public long mUin;

  static
  {
    CREATOR = new Parcelable.Creator()
    {
      public WloginLoginInfo createFromParcel(Parcel paramParcel)
      {
        return new WloginLoginInfo(paramParcel, null);
      }

      public WloginLoginInfo[] newArray(int paramInt)
      {
        return new WloginLoginInfo[paramInt];
      }
    };
  }

  private WloginLoginInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public WloginLoginInfo(String paramString, long paramLong1, long paramLong2, long paramLong3)
  {
    this.mAccount = paramString;
    this.mUin = paramLong1;
    this.mAppid = paramLong2;
    this.mCreateTime = paramLong3;
    this.mType = 0;
  }

  public WloginLoginInfo(String paramString, long paramLong1, long paramLong2, long paramLong3, int paramInt)
  {
    this.mAccount = paramString;
    this.mUin = paramLong1;
    this.mAppid = paramLong2;
    this.mCreateTime = paramLong3;
    this.mType = paramInt;
  }

  public WloginLoginInfo(String paramString1, long paramLong1, long paramLong2, String paramString2, long paramLong3, int paramInt)
  {
    this.mAccount = paramString1;
    this.mUin = paramLong1;
    this.mAppid = paramLong2;
    this.mFaceUrl = paramString2;
    this.mCreateTime = paramLong3;
    this.mType = paramInt;
  }

  public static WloginLoginInfo getWloginLoginInfo(WloginRemoteData paramWloginRemoteData)
  {
    if (paramWloginRemoteData == null);
    List localList1;
    List localList2;
    do
    {
      return null;
      localList1 = paramWloginRemoteData.getLongData();
      localList2 = paramWloginRemoteData.getByteData();
    }
    while ((localList1 == null) || (localList1.size() < 4) || (localList2 == null) || (localList2.size() < 2));
    return new WloginLoginInfo(new String((byte[])localList2.get(0)), ((Long)localList1.get(0)).longValue(), ((Long)localList1.get(1)).longValue(), new String((byte[])localList2.get(1)), ((Long)localList1.get(2)).longValue(), (int)((Long)localList1.get(3)).longValue());
  }

  public int describeContents()
  {
    return 0;
  }

  public WloginRemoteData getWloginRemoteData()
  {
    byte[] arrayOfByte1 = new byte[0];
    if ((this.mAccount != null) && (this.mAccount.length() > 0))
      arrayOfByte1 = (byte[])this.mAccount.getBytes().clone();
    byte[] arrayOfByte2 = new byte[0];
    if ((this.mFaceUrl != null) && (this.mFaceUrl.length() > 0))
      arrayOfByte2 = (byte[])this.mFaceUrl.getBytes().clone();
    return new WloginRemoteData(0L, this.mUin, this.mAppid, this.mCreateTime, this.mType, arrayOfByte1, arrayOfByte2);
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.mAccount = paramParcel.readString();
    this.mUin = paramParcel.readLong();
    this.mAppid = paramParcel.readLong();
    this.mCreateTime = paramParcel.readLong();
    this.mType = paramParcel.readInt();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mAccount);
    paramParcel.writeLong(this.mUin);
    paramParcel.writeLong(this.mAppid);
    paramParcel.writeLong(this.mCreateTime);
    paramParcel.writeInt(this.mType);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.sharemem.WloginLoginInfo
 * JD-Core Version:    0.6.0
 */
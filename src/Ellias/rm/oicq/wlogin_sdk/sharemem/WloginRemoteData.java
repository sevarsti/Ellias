package oicq.wlogin_sdk.sharemem;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class WloginRemoteData
  implements Parcelable
{
  public static final Parcelable.Creator<WloginRemoteData> CREATOR = new Parcelable.Creator()
  {
    public WloginRemoteData createFromParcel(Parcel paramParcel)
    {
      return new WloginRemoteData(paramParcel, null);
    }

    public WloginRemoteData[] newArray(int paramInt)
    {
      return new WloginRemoteData[paramInt];
    }
  };
  private List<byte[]> _byte_data = new ArrayList();
  private List<Long> _long_data = new ArrayList();
  private long _version = 0L;

  public WloginRemoteData()
  {
  }

  public WloginRemoteData(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    this._version = paramLong1;
    this._long_data.add(Long.valueOf(paramLong2));
    this._long_data.add(Long.valueOf(paramLong3));
    this._long_data.add(Long.valueOf(paramLong4));
    this._long_data.add(Long.valueOf(paramLong5));
    this._byte_data.add(paramArrayOfByte1);
    this._byte_data.add(paramArrayOfByte2);
  }

  public WloginRemoteData(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7, byte[] paramArrayOfByte8, byte[] paramArrayOfByte9, byte[] paramArrayOfByte10, byte[] paramArrayOfByte11, byte[] paramArrayOfByte12, byte[][] paramArrayOfByte)
  {
    this._version = paramLong1;
    this._long_data.add(Long.valueOf(paramLong2));
    this._long_data.add(Long.valueOf(paramLong3));
    this._long_data.add(Long.valueOf(paramLong4));
    this._long_data.add(Long.valueOf(paramLong5));
    this._byte_data.add(paramArrayOfByte1);
    this._byte_data.add(paramArrayOfByte2);
    this._byte_data.add(paramArrayOfByte3);
    this._byte_data.add(paramArrayOfByte4);
    this._byte_data.add(paramArrayOfByte5);
    this._byte_data.add(paramArrayOfByte6);
    this._byte_data.add(paramArrayOfByte7);
    this._byte_data.add(paramArrayOfByte8);
    this._byte_data.add(paramArrayOfByte9);
    this._byte_data.add(paramArrayOfByte10);
    this._byte_data.add(paramArrayOfByte11);
    this._byte_data.add(paramArrayOfByte12);
    if (paramArrayOfByte == null);
    while (true)
    {
      return;
      for (int i = 0; i < paramArrayOfByte.length; i++)
        this._byte_data.add(paramArrayOfByte[i]);
    }
  }

  public WloginRemoteData(long paramLong, List<Long> paramList, List<byte[]> paramList1)
  {
    this._version = paramLong;
    this._long_data = paramList;
    this._byte_data = paramList1;
  }

  public WloginRemoteData(long paramLong, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[][] paramArrayOfByte)
  {
    this._version = paramLong;
    this._byte_data.add(paramArrayOfByte1);
    this._byte_data.add(paramArrayOfByte2);
    this._byte_data.add(paramArrayOfByte3);
    this._byte_data.add(paramArrayOfByte4);
    if (paramArrayOfByte == null);
    while (true)
    {
      return;
      for (int i = 0; i < paramArrayOfByte.length; i++)
        this._byte_data.add(paramArrayOfByte[i]);
    }
  }

  private WloginRemoteData(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public int describeContents()
  {
    return 0;
  }

  public List<byte[]> getByteData()
  {
    return this._byte_data;
  }

  public List<Long> getLongData()
  {
    return this._long_data;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this._version = paramParcel.readLong();
    paramParcel.readList(this._long_data, null);
    paramParcel.readList(this._byte_data, null);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(this._version);
    paramParcel.writeList(this._long_data);
    paramParcel.writeList(this._byte_data);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.sharemem.WloginRemoteData
 * JD-Core Version:    0.6.0
 */
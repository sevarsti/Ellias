package oicq.wlogin_sdk.sharemem;

import B;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.List;

public class WloginSimpleInfo
  implements Serializable, Parcelable
{
  public static final Parcelable.Creator<WloginSimpleInfo> CREATOR = new Parcelable.Creator()
  {
    public WloginSimpleInfo createFromParcel(Parcel paramParcel)
    {
      return new WloginSimpleInfo(paramParcel, null);
    }

    public WloginSimpleInfo[] newArray(int paramInt)
    {
      return new WloginSimpleInfo[paramInt];
    }
  };
  private static final long serialVersionUID = 1L;
  public byte[] _age;
  public byte[] _face;
  public byte[] _gander;
  public byte[] _img_format;
  public byte[] _img_type;
  public byte[] _img_url;
  public byte[] _nick;
  public long _uin;

  public WloginSimpleInfo()
  {
    this._uin = 0L;
    this._face = new byte[0];
    this._age = new byte[0];
    this._gander = new byte[0];
    this._nick = new byte[0];
    this._img_type = new byte[0];
    this._img_format = new byte[0];
    this._img_url = new byte[0];
  }

  public WloginSimpleInfo(long paramLong, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7)
  {
    this._uin = paramLong;
    if (paramArrayOfByte1 != null)
    {
      this._face = ((byte[])paramArrayOfByte1.clone());
      if (paramArrayOfByte2 == null)
        break label137;
      this._age = ((byte[])paramArrayOfByte2.clone());
      label41: if (paramArrayOfByte3 == null)
        break label147;
      this._gander = ((byte[])paramArrayOfByte3.clone());
      label58: if (paramArrayOfByte4 == null)
        break label157;
      this._nick = ((byte[])paramArrayOfByte4.clone());
      label75: if (paramArrayOfByte5 == null)
        break label167;
      this._img_type = ((byte[])paramArrayOfByte5.clone());
      label92: if (paramArrayOfByte6 == null)
        break label177;
    }
    label137: label147: label157: label167: label177: for (this._img_format = ((byte[])paramArrayOfByte6.clone()); ; this._img_format = new byte[0])
    {
      if (paramArrayOfByte7 == null)
        break label187;
      this._img_url = ((byte[])paramArrayOfByte7.clone());
      return;
      this._face = new byte[0];
      break;
      this._age = new byte[0];
      break label41;
      this._gander = new byte[0];
      break label58;
      this._nick = new byte[0];
      break label75;
      this._img_type = new byte[0];
      break label92;
    }
    label187: this._img_url = new byte[0];
  }

  public WloginSimpleInfo(long paramLong, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[][] paramArrayOfByte)
  {
    this._uin = paramLong;
    if (paramArrayOfByte1 != null)
    {
      this._face = ((byte[])paramArrayOfByte1.clone());
      if (paramArrayOfByte2 == null)
        break label140;
      this._age = ((byte[])paramArrayOfByte2.clone());
      label41: if (paramArrayOfByte3 == null)
        break label150;
      this._gander = ((byte[])paramArrayOfByte3.clone());
      label58: if (paramArrayOfByte4 == null)
        break label160;
    }
    label140: label150: label160: for (this._nick = ((byte[])paramArrayOfByte4.clone()); ; this._nick = new byte[0])
    {
      if ((paramArrayOfByte == null) || (paramArrayOfByte.length != 3))
        break label170;
      this._img_type = ((byte[])paramArrayOfByte[0].clone());
      this._img_format = ((byte[])paramArrayOfByte[1].clone());
      this._img_url = ((byte[])paramArrayOfByte[2].clone());
      return;
      this._face = new byte[0];
      break;
      this._age = new byte[0];
      break label41;
      this._gander = new byte[0];
      break label58;
    }
    label170: this._img_type = new byte[0];
    this._img_format = new byte[0];
    this._img_url = new byte[0];
  }

  private WloginSimpleInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public static WloginSimpleInfo getWloginSimpleInfo(WloginRemoteData paramWloginRemoteData)
  {
    if ((paramWloginRemoteData == null) || (paramWloginRemoteData.getByteData() == null) || (paramWloginRemoteData.getByteData().size() < 7))
      return null;
    return new WloginSimpleInfo(0L, (byte[])paramWloginRemoteData.getByteData().get(0), (byte[])paramWloginRemoteData.getByteData().get(1), (byte[])paramWloginRemoteData.getByteData().get(2), (byte[])paramWloginRemoteData.getByteData().get(3), (byte[])paramWloginRemoteData.getByteData().get(4), (byte[])paramWloginRemoteData.getByteData().get(5), (byte[])paramWloginRemoteData.getByteData().get(6));
  }

  public int describeContents()
  {
    return 0;
  }

  public byte[][] getReserveData()
  {
    byte[][] arrayOfByte = new byte[3][];
    arrayOfByte[0] = ((byte[])this._img_type.clone());
    arrayOfByte[1] = ((byte[])this._img_format.clone());
    arrayOfByte[2] = ((byte[])this._img_url.clone());
    return arrayOfByte;
  }

  public WloginRemoteData getWloginRemoteData()
  {
    byte[][] arrayOfByte = getReserveData();
    return new WloginRemoteData(0L, this._face, this._age, this._gander, this._nick, arrayOfByte);
  }

  public WloginSimpleInfo get_clone()
  {
    WloginSimpleInfo localWloginSimpleInfo = new WloginSimpleInfo();
    localWloginSimpleInfo._uin = this._uin;
    if (this._face != null)
      localWloginSimpleInfo._face = ((byte[])this._face.clone());
    if (this._age != null)
      localWloginSimpleInfo._age = ((byte[])this._age.clone());
    if (this._gander != null)
      localWloginSimpleInfo._gander = ((byte[])this._gander.clone());
    if (this._nick != null)
      localWloginSimpleInfo._nick = ((byte[])this._nick.clone());
    if (this._img_type != null)
      localWloginSimpleInfo._img_type = ((byte[])this._img_type.clone());
    if (this._img_format != null)
      localWloginSimpleInfo._img_format = ((byte[])this._img_format.clone());
    if (this._img_url != null)
      localWloginSimpleInfo._img_url = ((byte[])this._img_url.clone());
    return localWloginSimpleInfo;
  }

  public void get_clone(WloginSimpleInfo paramWloginSimpleInfo)
  {
    this._uin = paramWloginSimpleInfo._uin;
    if (paramWloginSimpleInfo._face != null)
    {
      this._face = ((byte[])paramWloginSimpleInfo._face.clone());
      if (paramWloginSimpleInfo._age == null)
        break label166;
      this._age = ((byte[])paramWloginSimpleInfo._age.clone());
      label50: if (paramWloginSimpleInfo._gander == null)
        break label176;
      this._gander = ((byte[])paramWloginSimpleInfo._gander.clone());
      label71: if (paramWloginSimpleInfo._nick == null)
        break label186;
      this._nick = ((byte[])paramWloginSimpleInfo._nick.clone());
      label92: if (paramWloginSimpleInfo._img_type == null)
        break label196;
      this._img_type = ((byte[])paramWloginSimpleInfo._img_type.clone());
      label113: if (paramWloginSimpleInfo._img_format == null)
        break label206;
    }
    label166: label176: label186: label196: label206: for (this._img_format = ((byte[])paramWloginSimpleInfo._img_format.clone()); ; this._img_format = new byte[0])
    {
      if (paramWloginSimpleInfo._img_url == null)
        break label216;
      this._img_url = ((byte[])paramWloginSimpleInfo._img_url.clone());
      return;
      this._face = new byte[0];
      break;
      this._age = new byte[0];
      break label50;
      this._gander = new byte[0];
      break label71;
      this._nick = new byte[0];
      break label92;
      this._img_type = new byte[0];
      break label113;
    }
    label216: this._img_url = new byte[0];
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this._uin = paramParcel.readLong();
    this._face = paramParcel.createByteArray();
    this._age = paramParcel.createByteArray();
    this._gander = paramParcel.createByteArray();
    this._nick = paramParcel.createByteArray();
    this._img_type = paramParcel.createByteArray();
    this._img_format = paramParcel.createByteArray();
    this._img_url = paramParcel.createByteArray();
  }

  public void set_info(long paramLong, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[][] paramArrayOfByte)
  {
    this._uin = paramLong;
    if (paramArrayOfByte1 != null)
    {
      this._face = ((byte[])paramArrayOfByte1.clone());
      if (paramArrayOfByte2 == null)
        break label160;
      this._age = ((byte[])paramArrayOfByte2.clone());
      label37: if (paramArrayOfByte3 == null)
        break label170;
      this._gander = ((byte[])paramArrayOfByte3.clone());
      label54: if (paramArrayOfByte4 == null)
        break label180;
    }
    label160: label170: label180: for (this._nick = ((byte[])paramArrayOfByte4.clone()); ; this._nick = new byte[0])
    {
      if ((paramArrayOfByte != null) && (paramArrayOfByte.length == 3) && (paramArrayOfByte[0].length > 0) && (paramArrayOfByte[1].length > 0) && (paramArrayOfByte[2].length > 0))
      {
        this._img_type = ((byte[])paramArrayOfByte[0].clone());
        this._img_format = ((byte[])paramArrayOfByte[1].clone());
        this._img_url = ((byte[])paramArrayOfByte[2].clone());
      }
      return;
      this._face = new byte[0];
      break;
      this._age = new byte[0];
      break label37;
      this._gander = new byte[0];
      break label54;
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(this._uin);
    paramParcel.writeByteArray(this._face);
    paramParcel.writeByteArray(this._age);
    paramParcel.writeByteArray(this._gander);
    paramParcel.writeByteArray(this._nick);
    paramParcel.writeByteArray(this._img_type);
    paramParcel.writeByteArray(this._img_format);
    paramParcel.writeByteArray(this._img_url);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.sharemem.WloginSimpleInfo
 * JD-Core Version:    0.6.0
 */
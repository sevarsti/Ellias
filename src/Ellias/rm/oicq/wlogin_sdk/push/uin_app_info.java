package oicq.wlogin_sdk.push;

import B;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class uin_app_info
  implements Serializable, Parcelable
{
  public static final Parcelable.Creator<uin_app_info> CREATOR = new Parcelable.Creator()
  {
    public uin_app_info createFromParcel(Parcel paramParcel)
    {
      return new uin_app_info(paramParcel, null);
    }

    public uin_app_info[] newArray(int paramInt)
    {
      return new uin_app_info[paramInt];
    }
  };
  private static final long serialVersionUID = 1L;
  public long _appid;
  public int _clear;
  public String _cname;
  public String _cpath;
  public byte[] _guid;
  public int _icon;
  public int _msg_type;
  public int _notify_id;
  public String _pkg_name;
  public byte[] _st;
  public byte[] _st_key;
  public long _sub_appid;
  public long _uin;

  public uin_app_info(long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, byte[] paramArrayOfByte3, int paramInt2, String paramString1, String paramString2, String paramString3, int paramInt3)
  {
    this._uin = paramLong1;
    this._appid = paramLong2;
    this._sub_appid = paramLong3;
    if (paramArrayOfByte1 != null)
      this._st = ((byte[])paramArrayOfByte1.clone());
    if (paramArrayOfByte2 != null)
      this._st_key = ((byte[])paramArrayOfByte2.clone());
    this._clear = paramInt1;
    if (paramArrayOfByte3 != null)
      this._guid = ((byte[])paramArrayOfByte3.clone());
    this._icon = paramInt2;
    this._cpath = paramString1;
    this._cname = paramString2;
    this._pkg_name = paramString3;
    this._msg_type = paramInt3;
    this._notify_id = 0;
  }

  private uin_app_info(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public uin_app_info(uin_app_info paramuin_app_info)
  {
    this._uin = paramuin_app_info._uin;
    this._appid = paramuin_app_info._appid;
    this._sub_appid = paramuin_app_info._sub_appid;
    if (paramuin_app_info._st != null)
      this._st = ((byte[])paramuin_app_info._st.clone());
    if (paramuin_app_info._st_key != null)
      this._st_key = ((byte[])paramuin_app_info._st_key.clone());
    this._clear = paramuin_app_info._clear;
    if (paramuin_app_info._guid != null)
      this._guid = ((byte[])paramuin_app_info._guid.clone());
    this._icon = paramuin_app_info._icon;
    this._cpath = paramuin_app_info._cpath;
    this._cname = paramuin_app_info._cname;
    this._pkg_name = paramuin_app_info._pkg_name;
    this._msg_type = paramuin_app_info._msg_type;
    this._notify_id = paramuin_app_info._notify_id;
  }

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this._uin = paramParcel.readLong();
    this._appid = paramParcel.readLong();
    this._sub_appid = paramParcel.readLong();
    this._st = paramParcel.createByteArray();
    this._st_key = paramParcel.createByteArray();
    this._clear = paramParcel.readInt();
    this._guid = paramParcel.createByteArray();
    this._icon = paramParcel.readInt();
    this._cpath = paramParcel.readString();
    this._cname = paramParcel.readString();
    this._pkg_name = paramParcel.readString();
    this._msg_type = paramParcel.readInt();
    this._notify_id = paramParcel.readInt();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(this._uin);
    paramParcel.writeLong(this._appid);
    paramParcel.writeLong(this._sub_appid);
    paramParcel.writeByteArray(this._st);
    paramParcel.writeByteArray(this._st_key);
    paramParcel.writeInt(this._clear);
    paramParcel.writeByteArray(this._guid);
    paramParcel.writeInt(this._icon);
    paramParcel.writeString(this._cpath);
    paramParcel.writeString(this._cname);
    paramParcel.writeString(this._pkg_name);
    paramParcel.writeInt(this._msg_type);
    paramParcel.writeInt(this._notify_id);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.push.uin_app_info
 * JD-Core Version:    0.6.0
 */
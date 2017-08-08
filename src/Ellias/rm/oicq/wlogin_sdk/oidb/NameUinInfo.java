package oicq.wlogin_sdk.oidb;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class NameUinInfo
  implements Serializable, Parcelable
{
  public static final Parcelable.Creator<NameUinInfo> CREATOR = new Parcelable.Creator()
  {
    public NameUinInfo createFromParcel(Parcel paramParcel)
    {
      return new NameUinInfo(paramParcel, null);
    }

    public NameUinInfo[] newArray(int paramInt)
    {
      return new NameUinInfo[paramInt];
    }
  };
  private static final long serialVersionUID = 6429411463502820956L;
  public String name;
  public int tag1;
  public int tag2;
  public long uin;

  public NameUinInfo(long paramLong, int paramInt1, int paramInt2, String paramString)
  {
    this.uin = paramLong;
    this.tag1 = paramInt1;
    this.tag2 = paramInt2;
    this.name = paramString;
  }

  public NameUinInfo(long paramLong, String paramString)
  {
    this.uin = paramLong;
    this.name = paramString;
  }

  private NameUinInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.uin = paramParcel.readLong();
    this.tag1 = paramParcel.readInt();
    this.tag2 = paramParcel.readInt();
    this.name = paramParcel.readString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(this.uin);
    paramParcel.writeInt(this.tag1);
    paramParcel.writeInt(this.tag2);
    paramParcel.writeString(this.name);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.oidb.NameUinInfo
 * JD-Core Version:    0.6.0
 */
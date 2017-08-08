package oicq.wlogin_sdk.sharemem;

import B;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.List;

public class WloginSigInfo
  implements Serializable, Parcelable
{
  public static final Parcelable.Creator<WloginSigInfo> CREATOR = new Parcelable.Creator()
  {
    public WloginSigInfo createFromParcel(Parcel paramParcel)
    {
      return new WloginSigInfo(paramParcel, null);
    }

    public WloginSigInfo[] newArray(int paramInt)
    {
      return new WloginSigInfo[paramInt];
    }
  };
  private static final long serialVersionUID = 1L;
  public long _A2_expire_time = 0L;
  public byte[] _D2;
  public byte[] _D2Key;
  public long _D2_expire_time = 0L;
  public byte[] _TGT;
  public byte[] _TGTKey;
  public byte[] _access_token;
  public long _app_pri = 0L;
  public long _create_time = 0L;
  public byte[] _en_A1;
  public long _expire_time = 0L;
  public byte[] _lsKey;
  public long _lsKey_expire_time = 0L;
  public byte[] _new_userStSig;
  public byte[] _new_userSt_Key;
  public byte[] _openid;
  public byte[] _openkey;
  public long _ret_appid = 0L;
  public byte[] _sKey;
  public long _sKey_expire_time = 0L;
  public byte[] _sid;
  public long _sid_expire_time = 0L;
  public byte[] _userA5;
  public byte[] _userA8;
  public long _userA8_expire_time = 0L;
  public byte[] _userPasswdSig;
  public byte[] _userSig64;
  public byte[] _userStSig;
  public byte[] _userStWebSig;
  public long _userStWebSig_expire_time = 0L;
  public byte[] _userSt_Key;
  public long _vKey_expire_time = 0L;
  public byte[] _vkey;

  public WloginSigInfo(long paramLong1, long paramLong2, long paramLong3, long paramLong4, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7, byte[] paramArrayOfByte8, byte[] paramArrayOfByte9, byte[] paramArrayOfByte10, byte[] paramArrayOfByte11, byte[] paramArrayOfByte12, byte[][] paramArrayOfByte, long[] paramArrayOfLong)
  {
    this._app_pri = paramLong1;
    this._create_time = paramLong2;
    this._expire_time = paramLong3;
    this._A2_expire_time = paramLong4;
    label120: int i;
    if (paramArrayOfByte1 != null)
    {
      this._TGT = ((byte[])paramArrayOfByte1.clone());
      if (paramArrayOfByte2 == null)
        break label516;
      this._TGTKey = ((byte[])paramArrayOfByte2.clone());
      if (paramArrayOfByte3 == null)
        break label526;
      this._userStSig = ((byte[])paramArrayOfByte3.clone());
      label137: if (paramArrayOfByte4 == null)
        break label536;
      this._userSt_Key = ((byte[])paramArrayOfByte4.clone());
      label154: if (paramArrayOfByte5 == null)
        break label546;
      this._userStWebSig = ((byte[])paramArrayOfByte5.clone());
      label171: this._userPasswdSig = new byte[0];
      if (paramArrayOfByte6 == null)
        break label556;
      this._userA5 = ((byte[])paramArrayOfByte6.clone());
      label195: if (paramArrayOfByte7 == null)
        break label566;
      this._userA8 = ((byte[])paramArrayOfByte7.clone());
      label212: if (paramArrayOfByte8 == null)
        break label576;
      this._lsKey = ((byte[])paramArrayOfByte8.clone());
      label229: if (paramArrayOfByte9 == null)
        break label586;
      this._sKey = ((byte[])paramArrayOfByte9.clone());
      label246: if (paramArrayOfByte10 == null)
        break label596;
      this._userSig64 = ((byte[])paramArrayOfByte10.clone());
      label263: if (paramArrayOfByte11 == null)
        break label606;
      this._openid = ((byte[])paramArrayOfByte11.clone());
      label280: if (paramArrayOfByte12 == null)
        break label616;
      this._openkey = ((byte[])paramArrayOfByte12.clone());
      label297: if ((paramArrayOfByte == null) || (paramArrayOfByte.length != 8))
        break label648;
      i = 0;
      label313: if (i < paramArrayOfByte.length)
        break label626;
      this._new_userStSig = ((byte[])paramArrayOfByte[0].clone());
      this._new_userSt_Key = ((byte[])paramArrayOfByte[1].clone());
      this._vkey = ((byte[])paramArrayOfByte[2].clone());
      this._en_A1 = ((byte[])paramArrayOfByte[3].clone());
      this._access_token = ((byte[])paramArrayOfByte[4].clone());
      this._D2 = ((byte[])paramArrayOfByte[5].clone());
      this._D2Key = ((byte[])paramArrayOfByte[6].clone());
    }
    for (this._sid = ((byte[])paramArrayOfByte[7].clone()); ; this._sid = new byte[0])
    {
      if ((paramArrayOfLong != null) && (paramArrayOfLong.length == 7))
      {
        this._lsKey_expire_time = paramArrayOfLong[0];
        this._sKey_expire_time = paramArrayOfLong[1];
        this._vKey_expire_time = paramArrayOfLong[2];
        this._userA8_expire_time = paramArrayOfLong[3];
        this._userStWebSig_expire_time = paramArrayOfLong[4];
        this._D2_expire_time = paramArrayOfLong[5];
        this._sid_expire_time = paramArrayOfLong[6];
      }
      return;
      this._TGT = new byte[0];
      break;
      label516: this._TGTKey = new byte[0];
      break label120;
      label526: this._userStSig = new byte[0];
      break label137;
      label536: this._userSt_Key = new byte[0];
      break label154;
      label546: this._userStWebSig = new byte[0];
      break label171;
      label556: this._userA5 = new byte[0];
      break label195;
      label566: this._userA8 = new byte[0];
      break label212;
      label576: this._lsKey = new byte[0];
      break label229;
      label586: this._sKey = new byte[0];
      break label246;
      label596: this._userSig64 = new byte[0];
      break label263;
      label606: this._openid = new byte[0];
      break label280;
      label616: this._openkey = new byte[0];
      break label297;
      label626: if (paramArrayOfByte[i] == null)
        paramArrayOfByte[i] = new byte[0];
      i++;
      break label313;
      label648: this._new_userStSig = new byte[0];
      this._new_userSt_Key = new byte[0];
      this._vkey = new byte[0];
      this._en_A1 = new byte[0];
      this._access_token = new byte[0];
      this._D2 = new byte[0];
      this._D2Key = new byte[0];
    }
  }

  public WloginSigInfo(long paramLong1, long paramLong2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    this._app_pri = 4294967295L;
    this._create_time = paramLong1;
    this._expire_time = paramLong2;
    this._A2_expire_time = 0L;
    this._lsKey_expire_time = 0L;
    this._sKey_expire_time = 0L;
    this._vKey_expire_time = 0L;
    this._userA8_expire_time = 0L;
    this._userStWebSig_expire_time = 0L;
    this._D2_expire_time = 0L;
    this._sid_expire_time = 0L;
    this._TGT = new byte[0];
    this._TGTKey = new byte[0];
    if (paramArrayOfByte1 != null)
    {
      this._userStSig = ((byte[])paramArrayOfByte1.clone());
      if (paramArrayOfByte2 == null)
        break label299;
    }
    label299: for (this._userSt_Key = ((byte[])paramArrayOfByte2.clone()); ; this._userSt_Key = new byte[0])
    {
      this._userStWebSig = new byte[0];
      this._userPasswdSig = new byte[0];
      this._userA5 = new byte[0];
      this._userA8 = new byte[0];
      this._lsKey = new byte[0];
      this._sKey = new byte[0];
      this._userSig64 = new byte[0];
      this._openid = new byte[0];
      this._openkey = new byte[0];
      this._new_userSt_Key = new byte[0];
      this._new_userStSig = new byte[0];
      this._vkey = new byte[0];
      this._en_A1 = new byte[0];
      this._access_token = new byte[0];
      this._D2 = new byte[0];
      this._D2Key = new byte[0];
      this._sid = new byte[0];
      return;
      this._userStSig = new byte[0];
      break;
    }
  }

  private WloginSigInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public WloginSigInfo(WloginSigInfo paramWloginSigInfo)
  {
    this._app_pri = paramWloginSigInfo._app_pri;
    this._create_time = paramWloginSigInfo._create_time;
    this._expire_time = paramWloginSigInfo._expire_time;
    this._A2_expire_time = paramWloginSigInfo._A2_expire_time;
    byte[] arrayOfByte1;
    byte[] arrayOfByte2;
    label123: byte[] arrayOfByte3;
    label140: byte[] arrayOfByte4;
    label158: byte[] arrayOfByte5;
    label176: byte[] arrayOfByte6;
    label194: byte[] arrayOfByte7;
    label212: byte[] arrayOfByte8;
    label230: byte[] arrayOfByte9;
    label248: byte[] arrayOfByte10;
    label266: byte[] arrayOfByte11;
    label284: byte[] arrayOfByte12;
    label302: byte[] arrayOfByte13;
    label320: byte[] arrayOfByte14;
    label338: byte[] arrayOfByte15;
    label356: byte[] arrayOfByte16;
    label374: byte[] arrayOfByte17;
    label392: byte[] arrayOfByte18;
    label410: byte[] arrayOfByte19;
    label428: byte[] arrayOfByte20;
    if (paramWloginSigInfo._TGT == null)
    {
      arrayOfByte1 = new byte[0];
      this._TGT = arrayOfByte1;
      if (paramWloginSigInfo._TGTKey != null)
        break label541;
      arrayOfByte2 = new byte[0];
      this._TGTKey = arrayOfByte2;
      if (paramWloginSigInfo._userStSig != null)
        break label555;
      arrayOfByte3 = new byte[0];
      this._userStSig = arrayOfByte3;
      if (paramWloginSigInfo._userSt_Key != null)
        break label570;
      arrayOfByte4 = new byte[0];
      this._userSt_Key = arrayOfByte4;
      if (paramWloginSigInfo._userStWebSig != null)
        break label585;
      arrayOfByte5 = new byte[0];
      this._userStWebSig = arrayOfByte5;
      if (paramWloginSigInfo._userPasswdSig != null)
        break label600;
      arrayOfByte6 = new byte[0];
      this._userPasswdSig = arrayOfByte6;
      if (paramWloginSigInfo._userA5 != null)
        break label615;
      arrayOfByte7 = new byte[0];
      this._userA5 = arrayOfByte7;
      if (paramWloginSigInfo._userA8 != null)
        break label630;
      arrayOfByte8 = new byte[0];
      this._userA8 = arrayOfByte8;
      if (paramWloginSigInfo._lsKey != null)
        break label645;
      arrayOfByte9 = new byte[0];
      this._lsKey = arrayOfByte9;
      if (paramWloginSigInfo._sKey != null)
        break label660;
      arrayOfByte10 = new byte[0];
      this._sKey = arrayOfByte10;
      if (paramWloginSigInfo._userSig64 != null)
        break label675;
      arrayOfByte11 = new byte[0];
      this._userSig64 = arrayOfByte11;
      if (paramWloginSigInfo._openid != null)
        break label690;
      arrayOfByte12 = new byte[0];
      this._openid = arrayOfByte12;
      if (paramWloginSigInfo._openkey != null)
        break label705;
      arrayOfByte13 = new byte[0];
      this._openkey = arrayOfByte13;
      if (paramWloginSigInfo._new_userStSig != null)
        break label720;
      arrayOfByte14 = new byte[0];
      this._new_userStSig = arrayOfByte14;
      if (paramWloginSigInfo._new_userSt_Key != null)
        break label735;
      arrayOfByte15 = new byte[0];
      this._new_userSt_Key = arrayOfByte15;
      if (paramWloginSigInfo._vkey != null)
        break label750;
      arrayOfByte16 = new byte[0];
      this._vkey = arrayOfByte16;
      if (paramWloginSigInfo._en_A1 != null)
        break label765;
      arrayOfByte17 = new byte[0];
      this._en_A1 = arrayOfByte17;
      if (paramWloginSigInfo._access_token != null)
        break label780;
      arrayOfByte18 = new byte[0];
      this._access_token = arrayOfByte18;
      if (paramWloginSigInfo._D2 != null)
        break label795;
      arrayOfByte19 = new byte[0];
      this._D2 = arrayOfByte19;
      if (paramWloginSigInfo._D2Key != null)
        break label810;
      arrayOfByte20 = new byte[0];
      label446: this._D2Key = arrayOfByte20;
      if (paramWloginSigInfo._sid != null)
        break label825;
    }
    label541: label555: label690: label825: for (byte[] arrayOfByte21 = new byte[0]; ; arrayOfByte21 = (byte[])paramWloginSigInfo._sid.clone())
    {
      this._sid = arrayOfByte21;
      this._lsKey_expire_time = paramWloginSigInfo._lsKey_expire_time;
      this._sKey_expire_time = paramWloginSigInfo._sKey_expire_time;
      this._vKey_expire_time = paramWloginSigInfo._vKey_expire_time;
      this._userA8_expire_time = paramWloginSigInfo._userA8_expire_time;
      this._userStWebSig_expire_time = paramWloginSigInfo._userStWebSig_expire_time;
      this._D2_expire_time = paramWloginSigInfo._D2_expire_time;
      this._sid_expire_time = paramWloginSigInfo._sid_expire_time;
      return;
      arrayOfByte1 = (byte[])paramWloginSigInfo._TGT.clone();
      break;
      arrayOfByte2 = (byte[])paramWloginSigInfo._TGTKey.clone();
      break label123;
      arrayOfByte3 = (byte[])paramWloginSigInfo._userStSig.clone();
      break label140;
      label570: arrayOfByte4 = (byte[])paramWloginSigInfo._userSt_Key.clone();
      break label158;
      label585: arrayOfByte5 = (byte[])paramWloginSigInfo._userStWebSig.clone();
      break label176;
      label600: arrayOfByte6 = (byte[])paramWloginSigInfo._userPasswdSig.clone();
      break label194;
      label615: arrayOfByte7 = (byte[])paramWloginSigInfo._userA5.clone();
      break label212;
      label630: arrayOfByte8 = (byte[])paramWloginSigInfo._userA8.clone();
      break label230;
      arrayOfByte9 = (byte[])paramWloginSigInfo._lsKey.clone();
      break label248;
      arrayOfByte10 = (byte[])paramWloginSigInfo._sKey.clone();
      break label266;
      arrayOfByte11 = (byte[])paramWloginSigInfo._userSig64.clone();
      break label284;
      arrayOfByte12 = (byte[])paramWloginSigInfo._openid.clone();
      break label302;
      label705: arrayOfByte13 = (byte[])paramWloginSigInfo._openkey.clone();
      break label320;
      label720: arrayOfByte14 = (byte[])paramWloginSigInfo._new_userStSig.clone();
      break label338;
      label735: arrayOfByte15 = (byte[])paramWloginSigInfo._new_userSt_Key.clone();
      break label356;
      label750: arrayOfByte16 = (byte[])paramWloginSigInfo._vkey.clone();
      break label374;
      label765: arrayOfByte17 = (byte[])paramWloginSigInfo._en_A1.clone();
      break label392;
      arrayOfByte18 = (byte[])paramWloginSigInfo._access_token.clone();
      break label410;
      arrayOfByte19 = (byte[])paramWloginSigInfo._D2.clone();
      break label428;
      arrayOfByte20 = (byte[])paramWloginSigInfo._D2Key.clone();
      break label446;
    }
  }

  public static WloginSigInfo getWloginSigInfo(WloginRemoteData paramWloginRemoteData)
  {
    if (paramWloginRemoteData == null)
      return null;
    List localList1 = paramWloginRemoteData.getLongData();
    List localList2 = paramWloginRemoteData.getByteData();
    if ((localList1 == null) || (localList1.size() < 4))
      return null;
    if ((localList2 == null) || (localList2.size() < 12))
      return null;
    int i = localList2.size();
    byte[][] arrayOfByte = null;
    if (i > 12)
      arrayOfByte = new byte[-12 + localList2.size()][];
    for (int j = 12; ; j++)
    {
      int k = localList2.size();
      if (j >= k)
        return new WloginSigInfo(((Long)localList1.get(0)).longValue(), ((Long)localList1.get(1)).longValue(), ((Long)localList1.get(2)).longValue(), ((Long)localList1.get(3)).longValue(), (byte[])localList2.get(0), (byte[])localList2.get(1), (byte[])localList2.get(2), (byte[])localList2.get(3), (byte[])localList2.get(4), (byte[])localList2.get(5), (byte[])localList2.get(6), (byte[])localList2.get(7), (byte[])localList2.get(8), (byte[])localList2.get(9), (byte[])localList2.get(10), (byte[])localList2.get(11), arrayOfByte, null);
      arrayOfByte[(j - 12)] = ((byte[])localList2.get(j));
    }
  }

  public WloginSigInfo Set(long paramLong1, long paramLong2, long paramLong3, long paramLong4, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7, byte[] paramArrayOfByte8, byte[] paramArrayOfByte9, byte[] paramArrayOfByte10, byte[] paramArrayOfByte11, byte[] paramArrayOfByte12, byte[][] paramArrayOfByte, long[] paramArrayOfLong)
  {
    this._app_pri = paramLong1;
    this._create_time = paramLong2;
    this._expire_time = paramLong3;
    this._A2_expire_time = paramLong4;
    label56: label73: label90: label107: int i;
    if (paramArrayOfByte1 != null)
    {
      this._TGT = ((byte[])paramArrayOfByte1.clone());
      if (paramArrayOfByte2 == null)
        break label477;
      this._TGTKey = ((byte[])paramArrayOfByte2.clone());
      if (paramArrayOfByte3 == null)
        break label487;
      this._userStSig = ((byte[])paramArrayOfByte3.clone());
      if (paramArrayOfByte4 == null)
        break label497;
      this._userSt_Key = ((byte[])paramArrayOfByte4.clone());
      if (paramArrayOfByte5 == null)
        break label507;
      this._userStWebSig = ((byte[])paramArrayOfByte5.clone());
      this._userPasswdSig = new byte[0];
      if (paramArrayOfByte6 == null)
        break label517;
      this._userA5 = ((byte[])paramArrayOfByte6.clone());
      label131: if (paramArrayOfByte7 == null)
        break label527;
      this._userA8 = ((byte[])paramArrayOfByte7.clone());
      label148: if (paramArrayOfByte8 == null)
        break label537;
      this._lsKey = ((byte[])paramArrayOfByte8.clone());
      label165: if (paramArrayOfByte9 == null)
        break label547;
      this._sKey = ((byte[])paramArrayOfByte9.clone());
      label182: if (paramArrayOfByte10 == null)
        break label557;
      this._userSig64 = ((byte[])paramArrayOfByte10.clone());
      label199: if (paramArrayOfByte11 == null)
        break label567;
      this._openid = ((byte[])paramArrayOfByte11.clone());
      label216: if (paramArrayOfByte12 == null)
        break label577;
      this._openkey = ((byte[])paramArrayOfByte12.clone());
      label233: if ((paramArrayOfByte == null) || (paramArrayOfByte.length != 8))
        break label609;
      i = 0;
      label249: if (i < paramArrayOfByte.length)
        break label587;
      this._new_userStSig = ((byte[])paramArrayOfByte[0].clone());
      this._new_userSt_Key = ((byte[])paramArrayOfByte[1].clone());
      this._vkey = ((byte[])paramArrayOfByte[2].clone());
      if ((paramArrayOfByte[3].clone() != null) && (((byte[])paramArrayOfByte[3].clone()).length > 0))
        this._en_A1 = ((byte[])paramArrayOfByte[3].clone());
      this._access_token = ((byte[])paramArrayOfByte[4].clone());
      this._D2 = ((byte[])paramArrayOfByte[5].clone());
      this._D2Key = ((byte[])paramArrayOfByte[6].clone());
    }
    for (this._sid = ((byte[])paramArrayOfByte[7].clone()); ; this._sid = new byte[0])
    {
      if ((paramArrayOfLong != null) && (paramArrayOfLong.length == 7))
      {
        this._lsKey_expire_time = paramArrayOfLong[0];
        this._sKey_expire_time = paramArrayOfLong[1];
        this._vKey_expire_time = paramArrayOfLong[2];
        this._userA8_expire_time = paramArrayOfLong[3];
        this._userStWebSig_expire_time = paramArrayOfLong[4];
        this._D2_expire_time = paramArrayOfLong[5];
        this._sid_expire_time = paramArrayOfLong[6];
      }
      return this;
      this._TGT = new byte[0];
      break;
      label477: this._TGTKey = new byte[0];
      break label56;
      label487: this._userStSig = new byte[0];
      break label73;
      label497: this._userSt_Key = new byte[0];
      break label90;
      label507: this._userStWebSig = new byte[0];
      break label107;
      label517: this._userA5 = new byte[0];
      break label131;
      label527: this._userA8 = new byte[0];
      break label148;
      label537: this._lsKey = new byte[0];
      break label165;
      label547: this._sKey = new byte[0];
      break label182;
      label557: this._userSig64 = new byte[0];
      break label199;
      label567: this._openid = new byte[0];
      break label216;
      label577: this._openkey = new byte[0];
      break label233;
      label587: if (paramArrayOfByte[i] == null)
        paramArrayOfByte[i] = new byte[0];
      i++;
      break label249;
      label609: this._new_userStSig = new byte[0];
      this._new_userSt_Key = new byte[0];
      this._vkey = new byte[0];
      this._access_token = new byte[0];
      this._D2 = new byte[0];
      this._D2Key = new byte[0];
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public byte[][] getReserveData()
  {
    byte[][] arrayOfByte = new byte[8][];
    arrayOfByte[0] = this._new_userSt_Key;
    arrayOfByte[1] = this._new_userStSig;
    arrayOfByte[2] = this._vkey;
    arrayOfByte[3] = this._en_A1;
    arrayOfByte[4] = this._access_token;
    arrayOfByte[5] = this._D2;
    arrayOfByte[6] = this._D2Key;
    arrayOfByte[7] = this._sid;
    return arrayOfByte;
  }

  public WloginRemoteData getWloginRemoteData()
  {
    byte[][] arrayOfByte = getReserveData();
    return new WloginRemoteData(0L, this._app_pri, this._create_time, this._expire_time, this._A2_expire_time, this._TGT, this._TGTKey, this._userStSig, this._userSt_Key, this._userStWebSig, this._userA5, this._userA8, this._lsKey, this._sKey, this._userSig64, this._openid, this._openkey, arrayOfByte);
  }

  public boolean iSEarlyThan(long paramLong)
  {
    return this._create_time < paramLong;
  }

  public boolean iSExpire(long paramLong)
  {
    return paramLong > this._expire_time;
  }

  public boolean iSExpireA2(long paramLong)
  {
    return paramLong > this._A2_expire_time;
  }

  public boolean iSHighPri(long paramLong)
  {
    return this._app_pri <= paramLong;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this._TGT = paramParcel.createByteArray();
    this._TGTKey = paramParcel.createByteArray();
    this._userStSig = paramParcel.createByteArray();
    this._userSt_Key = paramParcel.createByteArray();
    this._userStWebSig = paramParcel.createByteArray();
    this._userPasswdSig = paramParcel.createByteArray();
    this._userA5 = paramParcel.createByteArray();
    this._userA8 = paramParcel.createByteArray();
    this._lsKey = paramParcel.createByteArray();
    this._sKey = paramParcel.createByteArray();
    this._userSig64 = paramParcel.createByteArray();
    this._openid = paramParcel.createByteArray();
    this._openkey = paramParcel.createByteArray();
    this._new_userSt_Key = paramParcel.createByteArray();
    this._new_userStSig = paramParcel.createByteArray();
    this._vkey = paramParcel.createByteArray();
    this._en_A1 = paramParcel.createByteArray();
    this._access_token = paramParcel.createByteArray();
    this._D2 = paramParcel.createByteArray();
    this._D2Key = paramParcel.createByteArray();
    this._sid = paramParcel.createByteArray();
    this._expire_time = paramParcel.readLong();
    this._A2_expire_time = paramParcel.readLong();
    this._create_time = paramParcel.readLong();
    this._app_pri = paramParcel.readLong();
    this._ret_appid = paramParcel.readLong();
    this._lsKey_expire_time = paramParcel.readLong();
    this._sKey_expire_time = paramParcel.readLong();
    this._vKey_expire_time = paramParcel.readLong();
    this._userA8_expire_time = paramParcel.readLong();
    this._userStWebSig_expire_time = paramParcel.readLong();
    this._D2_expire_time = paramParcel.readLong();
    this._sid_expire_time = paramParcel.readLong();
  }

  public void set_ret_appid(long paramLong)
  {
    this._ret_appid = paramLong;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeByteArray(this._TGT);
    paramParcel.writeByteArray(this._TGTKey);
    paramParcel.writeByteArray(this._userStSig);
    paramParcel.writeByteArray(this._userSt_Key);
    paramParcel.writeByteArray(this._userStWebSig);
    paramParcel.writeByteArray(this._userPasswdSig);
    paramParcel.writeByteArray(this._userA5);
    paramParcel.writeByteArray(this._userA8);
    paramParcel.writeByteArray(this._lsKey);
    paramParcel.writeByteArray(this._sKey);
    paramParcel.writeByteArray(this._userSig64);
    paramParcel.writeByteArray(this._openid);
    paramParcel.writeByteArray(this._openkey);
    paramParcel.writeByteArray(this._new_userSt_Key);
    paramParcel.writeByteArray(this._new_userStSig);
    paramParcel.writeByteArray(this._vkey);
    paramParcel.writeByteArray(this._en_A1);
    paramParcel.writeByteArray(this._access_token);
    paramParcel.writeByteArray(this._D2);
    paramParcel.writeByteArray(this._D2Key);
    paramParcel.writeByteArray(this._sid);
    paramParcel.writeLong(this._expire_time);
    paramParcel.writeLong(this._A2_expire_time);
    paramParcel.writeLong(this._create_time);
    paramParcel.writeLong(this._app_pri);
    paramParcel.writeLong(this._ret_appid);
    paramParcel.writeLong(this._lsKey_expire_time);
    paramParcel.writeLong(this._sKey_expire_time);
    paramParcel.writeLong(this._vKey_expire_time);
    paramParcel.writeLong(this._userA8_expire_time);
    paramParcel.writeLong(this._userStWebSig_expire_time);
    paramParcel.writeLong(this._D2_expire_time);
    paramParcel.writeLong(this._sid_expire_time);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.sharemem.WloginSigInfo
 * JD-Core Version:    0.6.0
 */
package oicq.wlogin_sdk.request;

import B;
import java.io.Serializable;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;

public class WUserSigInfo
  implements Serializable
{
  static final long serialVersionUID;
  public byte[] _A2;
  public byte[] _A2_Key;
  public long _A2_expire_time = 0L;
  public byte[] _D2;
  public byte[] _D2_Key;
  public long _D2_expire_time = 0L;
  public byte[] _access_token;
  public long _create_time = 0L;
  public byte[] _in_ksid;
  public byte[] _lsKey;
  public long _lsKey_expire_time = 0L;
  public byte[] _new_userStSig;
  public byte[] _new_userSt_Key;
  public byte[] _openid;
  public byte[] _openkey;
  public byte[] _reserveData;
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

  public void get_clone(WloginSigInfo paramWloginSigInfo)
  {
    if (paramWloginSigInfo._userStSig != null)
    {
      this._userStSig = ((byte[])paramWloginSigInfo._userStSig.clone());
      if (paramWloginSigInfo._userSt_Key == null)
        break label496;
      this._userSt_Key = ((byte[])paramWloginSigInfo._userSt_Key.clone());
      label42: if (paramWloginSigInfo._userStWebSig == null)
        break label506;
      this._userStWebSig = ((byte[])paramWloginSigInfo._userStWebSig.clone());
      label63: if (paramWloginSigInfo._en_A1 != null)
        break label516;
      this._userPasswdSig = new byte[0];
      label77: if (paramWloginSigInfo._userA5 == null)
        break label533;
      this._userA5 = ((byte[])paramWloginSigInfo._userA5.clone());
      label98: if (paramWloginSigInfo._userA8 == null)
        break label543;
      this._userA8 = ((byte[])paramWloginSigInfo._userA8.clone());
      label119: if (paramWloginSigInfo._lsKey == null)
        break label553;
      this._lsKey = ((byte[])paramWloginSigInfo._lsKey.clone());
      label140: if (paramWloginSigInfo._sKey == null)
        break label563;
      this._sKey = ((byte[])paramWloginSigInfo._sKey.clone());
      label161: if (paramWloginSigInfo._userSig64 == null)
        break label573;
      this._userSig64 = ((byte[])paramWloginSigInfo._userSig64.clone());
      label182: if (paramWloginSigInfo._openid == null)
        break label583;
      this._openid = ((byte[])paramWloginSigInfo._openid.clone());
      label203: if (paramWloginSigInfo._openkey == null)
        break label593;
      this._openkey = ((byte[])paramWloginSigInfo._openkey.clone());
      label224: if (paramWloginSigInfo._new_userSt_Key == null)
        break label603;
      this._new_userSt_Key = ((byte[])paramWloginSigInfo._new_userSt_Key.clone());
      label245: if (paramWloginSigInfo._new_userStSig == null)
        break label613;
      this._new_userStSig = ((byte[])paramWloginSigInfo._new_userStSig.clone());
      label266: if (paramWloginSigInfo._vkey == null)
        break label623;
      this._vkey = ((byte[])paramWloginSigInfo._vkey.clone());
      label287: if (paramWloginSigInfo._TGT == null)
        break label633;
      this._A2 = ((byte[])paramWloginSigInfo._TGT.clone());
      label308: if (paramWloginSigInfo._TGTKey == null)
        break label643;
      this._A2_Key = ((byte[])paramWloginSigInfo._TGTKey.clone());
      label329: if (paramWloginSigInfo._access_token == null)
        break label653;
      this._access_token = ((byte[])paramWloginSigInfo._access_token.clone());
      label350: if (paramWloginSigInfo._D2 == null)
        break label663;
      this._D2 = ((byte[])paramWloginSigInfo._D2.clone());
      label371: if (paramWloginSigInfo._D2Key == null)
        break label673;
      this._D2_Key = ((byte[])paramWloginSigInfo._D2Key.clone());
      label392: if (paramWloginSigInfo._sid == null)
        break label683;
    }
    label516: label533: label543: label553: label683: for (this._sid = ((byte[])paramWloginSigInfo._sid.clone()); ; this._sid = new byte[0])
    {
      this._create_time = paramWloginSigInfo._create_time;
      this._A2_expire_time = paramWloginSigInfo._A2_expire_time;
      this._lsKey_expire_time = paramWloginSigInfo._lsKey_expire_time;
      this._sKey_expire_time = paramWloginSigInfo._sKey_expire_time;
      this._vKey_expire_time = paramWloginSigInfo._vKey_expire_time;
      this._userA8_expire_time = paramWloginSigInfo._userA8_expire_time;
      this._userStWebSig_expire_time = paramWloginSigInfo._userStWebSig_expire_time;
      this._D2_expire_time = paramWloginSigInfo._D2_expire_time;
      this._sid_expire_time = paramWloginSigInfo._sid_expire_time;
      return;
      this._userStSig = new byte[0];
      break;
      label496: this._userSt_Key = new byte[0];
      break label42;
      label506: this._userStWebSig = new byte[0];
      break label63;
      this._userPasswdSig = ((byte[])paramWloginSigInfo._en_A1.clone());
      break label77;
      this._userA5 = new byte[0];
      break label98;
      this._userA8 = new byte[0];
      break label119;
      this._lsKey = new byte[0];
      break label140;
      label563: this._sKey = new byte[0];
      break label161;
      label573: this._userSig64 = new byte[0];
      break label182;
      label583: this._openid = new byte[0];
      break label203;
      label593: this._openkey = new byte[0];
      break label224;
      label603: this._new_userSt_Key = new byte[0];
      break label245;
      label613: this._new_userStSig = new byte[0];
      break label266;
      label623: this._vkey = new byte[0];
      break label287;
      label633: this._A2 = new byte[0];
      break label308;
      this._A2_Key = new byte[0];
      break label329;
      this._access_token = new byte[0];
      break label350;
      this._D2 = new byte[0];
      break label371;
      this._D2_Key = new byte[0];
      break label392;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.WUserSigInfo
 * JD-Core Version:    0.6.0
 */
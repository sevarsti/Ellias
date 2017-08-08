package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t122 extends tlv_t
{
  int _lg = 0;
  int _ln = 0;
  int _ls = 0;
  int _ly = 0;
  int _pg = 0;
  int _pn = 0;
  int _ps = 0;
  int _py = 0;

  public tlv_t122()
  {
    this._cmd = 290;
  }

  public byte[] get_g()
  {
    byte[] arrayOfByte = new byte[this._lg];
    System.arraycopy(this._buf, this._pg, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public byte[] get_n()
  {
    byte[] arrayOfByte = new byte[this._ln];
    System.arraycopy(this._buf, this._pn, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public byte[] get_x()
  {
    byte[] arrayOfByte = new byte[this._ls];
    System.arraycopy(this._buf, this._ps, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public byte[] get_y()
  {
    byte[] arrayOfByte = new byte[this._ly];
    System.arraycopy(this._buf, this._py, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public Boolean verify()
  {
    if (this._body_len < 2)
      return Boolean.valueOf(false);
    this._lg = util.buf_to_int16(this._buf, this._head_len);
    if (this._body_len < 2 + (2 + this._lg))
      return Boolean.valueOf(false);
    this._ln = util.buf_to_int16(this._buf, 2 + this._head_len + this._lg);
    if (this._body_len < 2 + (2 + this._lg) + this._ln)
      return Boolean.valueOf(false);
    this._ly = util.buf_to_int16(this._buf, 2 + (2 + this._head_len + this._lg) + this._ln);
    if (this._body_len < 2 + (2 + (2 + this._lg) + this._ln) + this._ly)
      return Boolean.valueOf(false);
    this._ls = util.buf_to_int16(this._buf, 2 + (2 + (2 + this._head_len + this._lg) + this._ln) + this._ly);
    this._pg = (2 + this._head_len);
    this._pn = (2 + (2 + this._head_len + this._lg));
    this._py = (2 + (2 + (2 + this._head_len + this._lg) + this._ln));
    this._ps = (2 + (2 + (2 + (2 + this._head_len + this._lg) + this._ln) + this._ly));
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t122
 * JD-Core Version:    0.6.0
 */
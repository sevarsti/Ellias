package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t129 extends tlv_t
{
  int _random_len = 0;

  public tlv_t129()
  {
    this._cmd = 297;
  }

  public int get_nexttime()
  {
    return util.buf_to_int32(this._buf, 4 + this._head_len);
  }

  public int get_timeout()
  {
    return util.buf_to_int32(this._buf, this._head_len);
  }

  public Boolean verify()
  {
    if (this._body_len < 8)
      return Boolean.valueOf(false);
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t129
 * JD-Core Version:    0.6.0
 */
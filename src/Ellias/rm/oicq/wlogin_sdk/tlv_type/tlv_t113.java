package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t113 extends tlv_t
{
  public tlv_t113()
  {
    this._cmd = 275;
  }

  public long get_uin()
  {
    long l = 0xFFFFFFFF & util.buf_to_int32(this._buf, this._head_len);
    util.LOGD("get uin:" + new Long(l).toString());
    return l;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t113
 * JD-Core Version:    0.6.0
 */
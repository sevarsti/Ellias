package oicq.wlogin_sdk.tlv_type;

import oicq.wlogin_sdk.tools.util;

public class tlv_t138 extends tlv_t
{
  public int _count = 0;

  public tlv_t138()
  {
    this._cmd = 312;
  }

  public int get_a2_chg_time()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this._count)
        return 0;
      if (util.buf_to_int16(this._buf, 4 + this._head_len + i * 10) == 266)
        return util.buf_to_int32(this._buf, 2 + (4 + this._head_len + i * 10));
    }
  }

  public int get_a8_chg_time()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this._count)
        return 0;
      if (util.buf_to_int16(this._buf, 4 + this._head_len + i * 10) == 258)
        return util.buf_to_int32(this._buf, 2 + (4 + this._head_len + i * 10));
    }
  }

  public int get_d2_chg_time()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this._count)
        return 0;
      if (util.buf_to_int16(this._buf, 4 + this._head_len + i * 10) == 323)
        return util.buf_to_int32(this._buf, 2 + (4 + this._head_len + i * 10));
    }
  }

  public int get_lskey_chg_time()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this._count)
        return 0;
      if (util.buf_to_int16(this._buf, 4 + this._head_len + i * 10) == 284)
        return util.buf_to_int32(this._buf, 2 + (4 + this._head_len + i * 10));
    }
  }

  public int get_sid_chg_time()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this._count)
        return 0;
      if (util.buf_to_int16(this._buf, 4 + this._head_len + i * 10) == 356)
        return util.buf_to_int32(this._buf, 2 + (4 + this._head_len + i * 10));
    }
  }

  public int get_skey_chg_time()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this._count)
        return 0;
      if (util.buf_to_int16(this._buf, 4 + this._head_len + i * 10) == 288)
        return util.buf_to_int32(this._buf, 2 + (4 + this._head_len + i * 10));
    }
  }

  public int get_stweb_chg_time()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this._count)
        return 0;
      if (util.buf_to_int16(this._buf, 4 + this._head_len + i * 10) == 259)
        return util.buf_to_int32(this._buf, 2 + (4 + this._head_len + i * 10));
    }
  }

  public int get_vkey_chg_time()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this._count)
        return 0;
      if (util.buf_to_int16(this._buf, 4 + this._head_len + i * 10) == 310)
        return util.buf_to_int32(this._buf, 2 + (4 + this._head_len + i * 10));
    }
  }

  public Boolean verify()
  {
    if (this._body_len < 4)
      return Boolean.valueOf(false);
    this._count = util.buf_to_int32(this._buf, this._head_len);
    if (this._body_len < 4 + 10 * this._count)
      return Boolean.valueOf(false);
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t138
 * JD-Core Version:    0.6.0
 */
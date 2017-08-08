package oicq.wlogin_sdk.request;

import java.io.Serializable;

public class TransReqContext
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public byte[] _body;
  public int _subcmd = 0;
  public int _type = 0;
  public long _uin = 0L;

  public byte[] get_body()
  {
    return this._body;
  }

  public int get_subcmd()
  {
    return this._subcmd;
  }

  public long get_uin()
  {
    return this._uin;
  }

  public boolean is_code2d_func_req()
  {
    return this._type == 3;
  }

  public boolean is_name_func_req()
  {
    return this._type == 2;
  }

  public boolean is_oidb_func_req()
  {
    return this._type == 4;
  }

  public boolean is_register_req()
  {
    return this._type == 1;
  }

  public void set_body(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
    {
      this._body = new byte[0];
      return;
    }
    this._body = paramArrayOfByte;
  }

  public void set_code2d_func_req()
  {
    this._type = 3;
  }

  public void set_name_func_req()
  {
    this._type = 2;
  }

  public void set_oidb_func_req()
  {
    this._type = 4;
  }

  public void set_register_req()
  {
    this._type = 1;
  }

  public void set_subcmd(int paramInt)
  {
    this._subcmd = paramInt;
  }

  public void set_uin(long paramLong)
  {
    this._uin = paramLong;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.TransReqContext
 * JD-Core Version:    0.6.0
 */
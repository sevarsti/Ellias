package oicq.wlogin_sdk.tlv_type;

public class tlv_t108 extends tlv_t
{
  int _t108_body_len = 0;

  public tlv_t108()
  {
    this._cmd = 264;
  }

  public byte[] get_tlv_108(byte[] paramArrayOfByte)
  {
    this._t108_body_len = paramArrayOfByte.length;
    byte[] arrayOfByte = new byte[this._t108_body_len];
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramArrayOfByte.length);
    fill_head(this._cmd);
    fill_body(arrayOfByte, this._t108_body_len);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t108
 * JD-Core Version:    0.6.0
 */
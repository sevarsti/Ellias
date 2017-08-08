package oicq.wlogin_sdk.tlv_type;

public class tlv_t130 extends tlv_t
{
  public tlv_t130()
  {
    this._cmd = 304;
  }

  public byte[] get_ipaddr()
  {
    byte[] arrayOfByte = new byte[4];
    System.arraycopy(this._buf, 4 + (2 + this._head_len), arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public byte[] get_time()
  {
    byte[] arrayOfByte = new byte[4];
    System.arraycopy(this._buf, 2 + this._head_len, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public void get_tlv_t130(byte[] paramArrayOfByte, int paramInt)
  {
    set_buf(paramArrayOfByte, paramInt);
  }

  public Boolean verify()
  {
    if (this._body_len < 14)
      return Boolean.valueOf(false);
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t130
 * JD-Core Version:    0.6.0
 */
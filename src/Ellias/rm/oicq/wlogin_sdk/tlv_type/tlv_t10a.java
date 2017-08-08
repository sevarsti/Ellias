package oicq.wlogin_sdk.tlv_type;

public class tlv_t10a extends tlv_t
{
  public tlv_t10a()
  {
    this._cmd = 266;
  }

  public byte[] get_tlv_10a(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[paramArrayOfByte.length];
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramArrayOfByte.length);
    fill_head(this._cmd);
    fill_body(arrayOfByte, arrayOfByte.length);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t10a
 * JD-Core Version:    0.6.0
 */
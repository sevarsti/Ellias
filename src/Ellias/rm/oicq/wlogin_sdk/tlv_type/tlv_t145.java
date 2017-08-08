package oicq.wlogin_sdk.tlv_type;

public class tlv_t145 extends tlv_t
{
  public int _t145_body_len = 0;

  public tlv_t145()
  {
    this._cmd = 325;
  }

  public byte[] get_tlv145(byte[] paramArrayOfByte)
  {
    int i = 0;
    if (paramArrayOfByte != null)
      i = 0 + paramArrayOfByte.length;
    byte[] arrayOfByte = new byte[i];
    if (arrayOfByte.length > 0)
      System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, arrayOfByte.length);
    this._t145_body_len = arrayOfByte.length;
    fill_head(this._cmd);
    fill_body(arrayOfByte, arrayOfByte.length);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t145
 * JD-Core Version:    0.6.0
 */
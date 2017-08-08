package oicq.wlogin_sdk.tlv_type;

public class tlv_t151 extends tlv_t
{
  public tlv_t151()
  {
    this._cmd = 337;
  }

  public byte[] get_tlv151(byte[] paramArrayOfByte)
  {
    int i = 0;
    if (paramArrayOfByte != null)
      i = paramArrayOfByte.length;
    byte[] arrayOfByte = new byte[i];
    if (i > 0)
      System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, i);
    fill_head(this._cmd);
    fill_body(arrayOfByte, arrayOfByte.length);
    set_length();
    return get_buf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_t151
 * JD-Core Version:    0.6.0
 */
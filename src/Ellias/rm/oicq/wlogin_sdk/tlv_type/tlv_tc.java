package oicq.wlogin_sdk.tlv_type;

public class tlv_tc extends tlv_t
{
  public tlv_tc()
  {
    this._cmd = 12;
  }

  public void get_tlv_tc(byte[] paramArrayOfByte, int paramInt)
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
 * Qualified Name:     oicq.wlogin_sdk.tlv_type.tlv_tc
 * JD-Core Version:    0.6.0
 */
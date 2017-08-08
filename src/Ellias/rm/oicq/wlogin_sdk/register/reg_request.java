package oicq.wlogin_sdk.register;

import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class reg_request
{
  public int _body_len = 0;
  public int _cmd = 0;
  public int _head_len = 11;
  public int _os_type = 5;

  public static int parse_0x5_rsp(byte[] paramArrayOfByte, reg_status paramreg_status)
  {
    if (1 > paramArrayOfByte.length)
      return -1009;
    int i = 0 + 1;
    if (3 > paramArrayOfByte.length)
      return -1009;
    int j = util.buf_to_int16(paramArrayOfByte, i);
    int k = i + 2;
    if (j != paramArrayOfByte.length)
      return -1009;
    if (7 > paramArrayOfByte.length)
      return -1009;
    int m = k + 4;
    if (8 > paramArrayOfByte.length)
      return -1009;
    int n = util.buf_to_int8(paramArrayOfByte, m);
    (m + 1);
    if (n + 8 > paramArrayOfByte.length)
      return -1009;
    int i1 = n + 8;
    if (i1 + 1 > paramArrayOfByte.length)
      return -1009;
    paramreg_status.sec_ctrl_code = util.buf_to_int8(paramArrayOfByte, i1);
    int i2 = i1 + 1;
    if (i2 + 1 > paramArrayOfByte.length)
      return -1009;
    int i3 = util.buf_to_int8(paramArrayOfByte, i2);
    int i4 = i2 + 1;
    if (i4 + i3 > paramArrayOfByte.length)
      return -1009;
    paramreg_status.token = new byte[i3];
    System.arraycopy(paramArrayOfByte, i4, paramreg_status.token, 0, i3);
    int i5 = i4 + i3;
    if (i5 + 2 > paramArrayOfByte.length)
      return -1009;
    int i6 = util.buf_to_int16(paramArrayOfByte, i5);
    int i7 = i5 + 2;
    if (i7 + i6 > paramArrayOfByte.length)
      return -1009;
    paramreg_status.promptinfo = new byte[i6];
    System.arraycopy(paramArrayOfByte, i7, paramreg_status.promptinfo, 0, i6);
    (i7 + i6);
    return 0;
  }

  public static int parse_0x6_rsp(byte[] paramArrayOfByte, reg_status paramreg_status)
  {
    if (1 > paramArrayOfByte.length)
      return -1009;
    int i = 0 + 1;
    if (3 > paramArrayOfByte.length)
      return -1009;
    int j = util.buf_to_int16(paramArrayOfByte, i);
    int k = i + 2;
    if (j != paramArrayOfByte.length)
      return -1009;
    if (7 > paramArrayOfByte.length)
      return -1009;
    int m = k + 4;
    if (8 > paramArrayOfByte.length)
      return -1009;
    int n = util.buf_to_int8(paramArrayOfByte, m);
    (m + 1);
    if (n + 8 > paramArrayOfByte.length)
      return -1009;
    int i1 = n + 8;
    if (i1 + 1 > paramArrayOfByte.length)
      return -1009;
    paramreg_status.sec_ctrl_code = util.buf_to_int8(paramArrayOfByte, i1);
    int i2 = i1 + 1;
    if (i2 + 1 > paramArrayOfByte.length)
      return -1009;
    int i3 = util.buf_to_int8(paramArrayOfByte, i2);
    int i4 = i2 + 1;
    if (i4 + i3 > paramArrayOfByte.length)
      return -1009;
    byte[] arrayOfByte1 = new byte[i3];
    System.arraycopy(paramArrayOfByte, i4, arrayOfByte1, 0, i3);
    int i5 = i4 + i3;
    if (paramreg_status.sec_ctrl_code == 0)
    {
      if ((paramreg_status.msgchk == null) || (paramreg_status.msgchk.length <= 0));
      byte[] arrayOfByte3;
      for (byte[] arrayOfByte2 = reg_status.STATIC_KEY.getBytes(); ; arrayOfByte2 = MD5.toMD5Byte(paramreg_status.msgchk))
      {
        arrayOfByte3 = cryptor.decrypt(arrayOfByte1, 0, arrayOfByte1.length, arrayOfByte2);
        if (1 <= arrayOfByte3.length)
          break;
        return -1009;
      }
      int i11 = util.buf_to_int8(arrayOfByte3, 0);
      (0 + 1);
      if (i11 + 1 > arrayOfByte3.length)
        return -1009;
      int i12 = i11 + 1;
      if (i12 + 8 > arrayOfByte3.length)
        return -1009;
      paramreg_status.uin = util.buf_to_int64(arrayOfByte3, i12);
      int i13 = i12 + 8;
      if (i13 + 2 > arrayOfByte3.length)
        return -1009;
      int i14 = util.buf_to_int16(arrayOfByte3, i13);
      int i15 = i13 + 2;
      if (i15 + i14 > arrayOfByte3.length)
        return -1009;
      paramreg_status.supersig = new byte[i14];
      System.arraycopy(arrayOfByte3, i15, paramreg_status.supersig, 0, i14);
    }
    if (i5 + 1 > paramArrayOfByte.length)
      return -1009;
    int i6 = util.buf_to_int8(paramArrayOfByte, i5);
    int i7 = i5 + 1;
    if (i7 + i6 > paramArrayOfByte.length)
      return -1009;
    paramreg_status.token = new byte[i6];
    System.arraycopy(paramArrayOfByte, i7, paramreg_status.token, 0, i6);
    int i8 = i7 + i6;
    if (i8 + 2 > paramArrayOfByte.length)
      return -1009;
    int i9 = util.buf_to_int16(paramArrayOfByte, i8);
    int i10 = i8 + 2;
    if (i10 + i9 > paramArrayOfByte.length)
      return -1009;
    paramreg_status.promptinfo = new byte[i9];
    System.arraycopy(paramArrayOfByte, i10, paramreg_status.promptinfo, 0, i9);
    (i10 + i9);
    return 0;
  }

  public static int parse_0x7_rsp(byte[] paramArrayOfByte, reg_status paramreg_status)
  {
    if (1 > paramArrayOfByte.length)
      return -1009;
    int i = 0 + 1;
    if (3 > paramArrayOfByte.length)
      return -1009;
    int j = util.buf_to_int16(paramArrayOfByte, i);
    int k = i + 2;
    if (j != paramArrayOfByte.length)
      return -1009;
    if (7 > paramArrayOfByte.length)
      return -1009;
    int m = k + 4;
    if (8 > paramArrayOfByte.length)
      return -1009;
    int n = util.buf_to_int8(paramArrayOfByte, m);
    (m + 1);
    if (n + 8 > paramArrayOfByte.length)
      return -1009;
    int i1 = n + 8;
    if (i1 + 1 > paramArrayOfByte.length)
      return -1009;
    paramreg_status.sec_ctrl_code = util.buf_to_int8(paramArrayOfByte, i1);
    int i2 = i1 + 1;
    if (i2 + 2 > paramArrayOfByte.length)
      return -1009;
    int i3 = util.buf_to_int16(paramArrayOfByte, i2);
    int i4 = i2 + 2;
    if (i4 + i3 > paramArrayOfByte.length)
      return -1009;
    paramreg_status.promptinfo = new byte[i3];
    System.arraycopy(paramArrayOfByte, i4, paramreg_status.promptinfo, 0, i3);
    (i4 + i3);
    return 0;
  }

  public static int parse_checkvalid_rsp(byte[] paramArrayOfByte, reg_status paramreg_status)
  {
    int i12;
    if (1 > paramArrayOfByte.length)
      i12 = -1009;
    byte[] arrayOfByte;
    int i11;
    do
    {
      return i12;
      int i = 0 + 1;
      if (3 > paramArrayOfByte.length)
        return -1009;
      int j = util.buf_to_int16(paramArrayOfByte, i);
      int k = i + 2;
      if (j != paramArrayOfByte.length)
        return -1009;
      if (7 > paramArrayOfByte.length)
        return -1009;
      int m = k + 4;
      if (8 > paramArrayOfByte.length)
        return -1009;
      int n = util.buf_to_int8(paramArrayOfByte, m);
      (m + 1);
      if (n + 8 > paramArrayOfByte.length)
        return -1009;
      int i1 = n + 8;
      if (i1 + 1 > paramArrayOfByte.length)
        return -1009;
      paramreg_status.sec_ctrl_code = (0xFF & util.buf_to_int8(paramArrayOfByte, i1));
      int i2 = i1 + 1;
      if (i2 + 2 > paramArrayOfByte.length)
        return -1009;
      int i3 = util.buf_to_int16(paramArrayOfByte, i2);
      int i4 = i2 + 2;
      if (i4 + i3 > paramArrayOfByte.length)
        return -1009;
      arrayOfByte = new byte[i3];
      System.arraycopy(paramArrayOfByte, i4, arrayOfByte, 0, i3);
      int i5 = i4 + i3;
      if (i5 + 1 > paramArrayOfByte.length)
        return -1009;
      int i6 = util.buf_to_int8(paramArrayOfByte, i5);
      int i7 = i5 + 1;
      if (i7 + i6 > paramArrayOfByte.length)
        return -1009;
      paramreg_status.token = new byte[i6];
      System.arraycopy(paramArrayOfByte, i7, paramreg_status.token, 0, i6);
      int i8 = i7 + i6;
      if (i8 + 2 > paramArrayOfByte.length)
        return -1009;
      int i9 = util.buf_to_int16(paramArrayOfByte, i8);
      int i10 = i8 + 2;
      if (i10 + i9 > paramArrayOfByte.length)
        return -1009;
      paramreg_status.promptinfo = new byte[i9];
      System.arraycopy(paramArrayOfByte, i10, paramreg_status.promptinfo, 0, i9);
      (i10 + i9);
      i11 = arrayOfByte.length;
      i12 = 0;
    }
    while (i11 <= 0);
    if (paramreg_status.sec_ctrl_code == 0)
    {
      if (4 > arrayOfByte.length)
        return -1009;
      paramreg_status.telnum_verify_result = util.buf_to_int32(arrayOfByte, 0);
      int i26 = 0 + 4;
      if (5 > arrayOfByte.length)
        return -1009;
      int i27 = util.buf_to_int8(arrayOfByte, i26);
      int i28 = i26 + 1;
      if (i27 + 5 > arrayOfByte.length)
        return -1009;
      paramreg_status.return_msg = new byte[i27];
      System.arraycopy(arrayOfByte, i28, paramreg_status.return_msg, 0, i27);
      (i27 + 5);
      return 0;
    }
    if (paramreg_status.sec_ctrl_code == 1)
    {
      if (2 > arrayOfByte.length)
        return -1009;
      int i21 = util.buf_to_int16(arrayOfByte, 0);
      int i22 = 0 + 2;
      if (i21 + 2 > arrayOfByte.length)
        return -1009;
      paramreg_status.picbuf = new byte[i21];
      System.arraycopy(arrayOfByte, i22, paramreg_status.picbuf, 0, i21);
      int i23 = i21 + 2;
      if (i23 + 2 > arrayOfByte.length)
        return -1009;
      int i24 = util.buf_to_int16(arrayOfByte, i23);
      int i25 = i23 + 2;
      if (i25 + i24 > arrayOfByte.length)
        return -1009;
      paramreg_status.sigbuf = new byte[i24];
      System.arraycopy(arrayOfByte, i25, paramreg_status.sigbuf, 0, i24);
      (i25 + i24);
      return 0;
    }
    if (paramreg_status.sec_ctrl_code == 2)
    {
      if (1 > arrayOfByte.length)
        return -1009;
      int i17 = util.buf_to_int8(arrayOfByte, 0);
      int i18 = 0 + 1;
      if (i17 + 1 > arrayOfByte.length)
        return -1009;
      paramreg_status.recvnum = new byte[i17];
      System.arraycopy(arrayOfByte, i18, paramreg_status.recvnum, 0, i17);
      (i17 + 1);
      paramreg_status.sendmsg = new byte[6];
      for (int i19 = 0; ; i19++)
      {
        int i20 = paramreg_status.sendmsg.length;
        i12 = 0;
        if (i19 >= i20)
          break;
        paramreg_status.sendmsg[i19] = (byte)(48 + (byte)(util.get_rand_32() % 10));
      }
    }
    if (paramreg_status.sec_ctrl_code == 3)
    {
      if (2 > arrayOfByte.length)
        return -1009;
      int i15 = util.buf_to_int16(arrayOfByte, 0);
      int i16 = 0 + 2;
      if (i15 + 2 > arrayOfByte.length)
        return -1009;
      paramreg_status.url = new byte[i15];
      System.arraycopy(arrayOfByte, i16, paramreg_status.url, 0, i15);
      (i15 + 2);
      return 0;
    }
    if (paramreg_status.sec_ctrl_code == 4)
    {
      if (2 > arrayOfByte.length)
        return -1009;
      paramreg_status.next_check_time = util.buf_to_int16(arrayOfByte, 0);
      int i14 = 0 + 2;
      if (4 > arrayOfByte.length)
        return -1009;
      paramreg_status.total_time_out = util.buf_to_int16(arrayOfByte, i14);
      (i14 + 2);
      return 0;
    }
    if (paramreg_status.sec_ctrl_code == 5)
    {
      if (2 > arrayOfByte.length)
        return -1009;
      paramreg_status.next_check_time = util.buf_to_int16(arrayOfByte, 0);
      int i13 = 0 + 2;
      if (4 > arrayOfByte.length)
        return -1009;
      paramreg_status.total_time_out = util.buf_to_int16(arrayOfByte, i13);
      (i13 + 2);
      return 0;
    }
    util.LOGW("unhandle return code int parse_checkvalid_rsp", "");
    return 0;
  }

  public int get_cmd()
  {
    return this._cmd;
  }

  public byte[] get_encrypt_token(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte1 = new byte[1 + paramArrayOfByte1.length];
    util.int8_to_buf(arrayOfByte1, 0, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, 1, paramArrayOfByte1.length);
    byte[] arrayOfByte2 = MD5.toMD5Byte(paramArrayOfByte2);
    return cryptor.encrypt(arrayOfByte1, 0, arrayOfByte1.length, arrayOfByte2);
  }

  public byte[] get_request(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[2 + this._head_len + paramArrayOfByte.length];
    util.int8_to_buf(arrayOfByte, 0, 2);
    int i = 0 + 1;
    util.int16_to_buf(arrayOfByte, i, 1 + (this._head_len + paramArrayOfByte.length));
    int j = i + 2;
    util.int16_to_buf(arrayOfByte, j, 1);
    int k = j + 2;
    util.int16_to_buf(arrayOfByte, k, this._cmd);
    int m = k + 2;
    util.int8_to_buf(arrayOfByte, m, 4);
    int n = m + 1;
    util.int32_to_buf(arrayOfByte, n, 0);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, n + 4, paramArrayOfByte.length);
    int i1 = 12 + paramArrayOfByte.length;
    util.int8_to_buf(arrayOfByte, i1, 3);
    (i1 + 1);
    return arrayOfByte;
  }

  public int get_response(byte[] paramArrayOfByte)
  {
    return 0;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.register.reg_request
 * JD-Core Version:    0.6.0
 */
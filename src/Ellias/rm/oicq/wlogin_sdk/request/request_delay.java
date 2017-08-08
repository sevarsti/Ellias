package oicq.wlogin_sdk.request;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import oicq.wlogin_sdk.tlv_type.tlv_t104;
import oicq.wlogin_sdk.tlv_type.tlv_t122;
import oicq.wlogin_sdk.tlv_type.tlv_t123;
import oicq.wlogin_sdk.tools.util;

public class request_delay extends oicq_request
{
  public request_delay(request_global paramrequest_global)
  {
    this._cmd = 2064;
    this._sub_cmd = 6;
    this._g = paramrequest_global;
  }

  public byte[] get_request_body(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5, long paramLong)
  {
    tlv_t104 localtlv_t104 = new tlv_t104();
    tlv_t123 localtlv_t123 = new tlv_t123();
    byte[] arrayOfByte1 = localtlv_t104.get_tlv_104(paramArrayOfByte1);
    byte[] arrayOfByte2 = localtlv_t123.get_tlv123(paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, paramArrayOfByte5, paramLong);
    byte[] arrayOfByte3 = new byte[arrayOfByte1.length + arrayOfByte2.length];
    int i = paramArrayOfByte1.length;
    int j = 0;
    int k = 0;
    if (i > 0)
    {
      System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, arrayOfByte1.length);
      j = 0 + arrayOfByte1.length;
      k = 0 + 1;
    }
    System.arraycopy(arrayOfByte2, 0, arrayOfByte3, j, arrayOfByte2.length);
    (j + arrayOfByte2.length);
    int m = k + 1;
    return encrypt_body(arrayOfByte3, this._sub_cmd, m);
  }

  public byte[] get_x(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4)
  {
    while (true)
    {
      int i;
      try
      {
        BigInteger localBigInteger1 = new BigInteger(1, paramArrayOfByte1);
        BigInteger localBigInteger2 = new BigInteger(1, paramArrayOfByte2);
        BigInteger localBigInteger3 = new BigInteger(1, paramArrayOfByte3);
        Object localObject = new BigInteger(1, paramArrayOfByte4);
        new BigInteger(1, paramArrayOfByte3);
        BigInteger localBigInteger4 = new BigInteger(1, new byte[] { 1 });
        i = 0;
        break label210;
        byte[] arrayOfByte1 = ((BigInteger)localObject).toByteArray();
        if (arrayOfByte1.length % 2 == 0)
          continue;
        byte[] arrayOfByte2 = new byte[-1 + arrayOfByte1.length];
        System.arraycopy(arrayOfByte1, 1, arrayOfByte2, 0, arrayOfByte2.length);
        return arrayOfByte2;
        if (localBigInteger1.modPow((BigInteger)localObject, localBigInteger2).compareTo(localBigInteger3) == 0)
          continue;
        BigInteger localBigInteger5 = ((BigInteger)localObject).add(localBigInteger4);
        localObject = localBigInteger5;
        i++;
        break label210;
        return arrayOfByte1;
      }
      catch (Exception localException)
      {
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        util.LOGW("exception", localStringWriter.toString());
        return new byte[0];
      }
      label210: if (i < 100000)
        continue;
    }
  }

  public int make_request()
  {
    int i = this._g._app_client_version;
    while (true)
    {
      long l1 = System.currentTimeMillis();
      byte[] arrayOfByte = get_x(this._g._t122.get_g(), this._g._t122.get_n(), this._g._t122.get_y(), this._g._t122.get_x());
      long l2 = System.currentTimeMillis() - l1;
      get_request(this._default_client_version, this._cmd, this._default_client_seq, this._g._uin, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, get_request_body(this._g._t104.get_data(), this._g._t122.get_g(), this._g._t122.get_n(), this._g._t122.get_y(), arrayOfByte, l2));
      int j = snd_rcv_req(String.valueOf(this._g._uin));
      if (j != 0);
      while (j != 3)
      {
        return j;
        j = get_response(this._rsp_buf, this._rsp_buf.length);
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_delay
 * JD-Core Version:    0.6.0
 */
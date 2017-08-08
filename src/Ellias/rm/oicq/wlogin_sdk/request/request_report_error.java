package oicq.wlogin_sdk.request;

import android.os.Build.VERSION;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import oicq.wlogin_sdk.report.report_t;
import oicq.wlogin_sdk.report.report_t1;
import oicq.wlogin_sdk.tlv_type.tlv_t150;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;
import org.json.JSONObject;

public class request_report_error extends oicq_report_error
{
  public final String _req_report_error_default_key = "x'Z8mSi,V(Wu~.v:";
  public final int _req_report_error_head_len = 33;
  public final int _req_report_error_head_magic = 1737040709;

  public request_report_error(request_global paramrequest_global)
  {
    this._g = paramrequest_global;
  }

  public byte[] get_request(long paramLong1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, long paramLong2)
  {
    if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length != 0))
    {
      byte[] arrayOfByte3 = cryptor.encrypt(paramArrayOfByte1, 0, paramArrayOfByte1.length, paramArrayOfByte3);
      byte[] arrayOfByte4 = new byte[10 + paramArrayOfByte2.length + arrayOfByte3.length];
      util.int32_to_buf(arrayOfByte4, 0, (int)paramLong1);
      int k = 0 + 4;
      util.int32_to_buf(arrayOfByte4, k, (int)paramLong2);
      int m = k + 4;
      util.int16_to_buf(arrayOfByte4, m, paramArrayOfByte2.length);
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte4, m + 2, paramArrayOfByte2.length);
      int n = 10 + paramArrayOfByte2.length;
      System.arraycopy(arrayOfByte3, 0, arrayOfByte4, n, arrayOfByte3.length);
      (n + arrayOfByte3.length);
      return arrayOfByte4;
    }
    byte[] arrayOfByte1 = cryptor.encrypt(paramArrayOfByte1, 0, paramArrayOfByte1.length, "x'Z8mSi,V(Wu~.v:".getBytes());
    byte[] arrayOfByte2 = new byte[10 + arrayOfByte1.length];
    util.int32_to_buf(arrayOfByte2, 0, (int)paramLong1);
    int i = 0 + 4;
    util.int32_to_buf(arrayOfByte2, i, (int)paramLong2);
    int j = i + 4;
    util.int16_to_buf(arrayOfByte2, j, 0);
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, j + 2, arrayOfByte1.length);
    (10 + arrayOfByte1.length);
    return arrayOfByte2;
  }

  public byte[] get_request_body(byte[] paramArrayOfByte, long paramLong1, long paramLong2, long paramLong3, int paramInt)
  {
    byte[] arrayOfByte = new byte[33 + paramArrayOfByte.length];
    util.int32_to_buf(arrayOfByte, 0, 1737040709);
    int i = 0 + 4;
    util.int32_to_buf(arrayOfByte, i, 0);
    int j = i + 4;
    util.int32_to_buf(arrayOfByte, j, arrayOfByte.length);
    int k = j + 4;
    util.int64_to_buf32(arrayOfByte, k, paramLong1);
    int m = k + 4;
    util.int64_to_buf32(arrayOfByte, m, paramLong2);
    int n = m + 4;
    util.int64_to_buf32(arrayOfByte, n, paramLong3 / 1000L);
    int i1 = n + 4;
    util.int8_to_buf(arrayOfByte, i1, paramInt);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 8 + (i1 + 1), paramArrayOfByte.length);
    (33 + paramArrayOfByte.length);
    return arrayOfByte;
  }

  public int make_request(long paramLong1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, long paramLong2, int paramInt)
  {
    int i;
    if (paramInt == 0)
      i = make_request_0(paramLong1, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramLong2);
    do
    {
      return i;
      i = 0;
    }
    while (paramInt != 1);
    return make_request_1(paramLong1, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramLong2);
  }

  public int make_request_0(long paramLong1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, long paramLong2)
  {
    long l = System.currentTimeMillis();
    this._g._rt1.commit(Build.VERSION.RELEASE, new String(this._g._apk_v), "", util.buf_to_string(util.get_ksid(this._g._context)), new String(this._g._apk_id), new String(this._g._device), new String(this._g._apk_sig));
    byte[] arrayOfByte1 = this._g._rt1.toJasonObj().toString().getBytes();
    if (arrayOfByte1 == null);
    int i;
    byte[] arrayOfByte3;
    for (byte[] arrayOfByte2 = new byte[0]; ; arrayOfByte2 = get_request_body(arrayOfByte3, paramLong1, paramLong2, l, 0))
    {
      report_t.delete_file(this._g._context);
      i = 0;
      if (arrayOfByte2 != null)
      {
        int j = arrayOfByte2.length;
        i = 0;
        if (j > 0)
          i = snd_rcv_req_error(encrypt_body(get_request(paramLong1, arrayOfByte2, paramArrayOfByte2, paramArrayOfByte3, paramLong2)));
      }
      util.LOGI("request_report_error(0) rsp: ret=" + i);
      if (i == 0)
        break;
      report_t.write_tofile(this._g._rt1, this._g._context);
      return i;
      arrayOfByte3 = new byte[4 + arrayOfByte1.length];
      util.int8_to_buf(arrayOfByte3, 0, 0);
      util.int8_to_buf(arrayOfByte3, 1, 0);
      util.int16_to_buf(arrayOfByte3, 2, arrayOfByte1.length);
      System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 4, arrayOfByte1.length);
    }
    this._g._rt1.clear_t2();
    return i;
  }

  public int make_request_1(long paramLong1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, long paramLong2)
  {
    long l1;
    SimpleDateFormat localSimpleDateFormat;
    Calendar localCalendar;
    int i;
    int j;
    int k;
    if ((this._g._t150 != null) && (!this._g.isUploading.booleanValue()))
    {
      this._g.isUploading = Boolean.valueOf(true);
      l1 = System.currentTimeMillis();
      localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
      localCalendar = Calendar.getInstance();
      i = this._g._t150.get_bitmap();
      j = this._g._t150.get_network();
      util.LOGI("bitmap:" + i + " network:" + j + " local network:" + util.get_network_type(this._g._context));
      k = 0;
      if (k < 32);
    }
    else
    {
      this._g._t150 = null;
      this._g.isUploading = Boolean.valueOf(false);
      return 0;
    }
    if ((i & 1 << k) == 0);
    label366: 
    while (true)
    {
      k++;
      break;
      if ((j != 0) && ((j != 1) || (util.get_network_type(this._g._context) != 2)))
        continue;
      long l2 = l1 - 86400000 * k;
      localCalendar.setTimeInMillis(l2);
      String str = localSimpleDateFormat.format(localCalendar.getTime());
      long l3 = util.getLogModifyTime(this._g._context, str);
      if (l3 == 0L)
        l3 = l2;
      byte[] arrayOfByte1 = util.readLog(this._g._context, str);
      if (arrayOfByte1 == null);
      for (byte[] arrayOfByte2 = new byte[0]; ; arrayOfByte2 = get_request_body(arrayOfByte1, paramLong1, paramLong2, l3, 1))
      {
        if ((arrayOfByte2 == null) || (arrayOfByte2.length <= 0))
          break label366;
        int m = snd_rcv_req_error(encrypt_body(get_request(paramLong1, arrayOfByte2, paramArrayOfByte2, paramArrayOfByte3, paramLong2)));
        util.LOGI("request_report_error(1) rsp: ret=" + m + "(" + str + ")");
        break;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_report_error
 * JD-Core Version:    0.6.0
 */
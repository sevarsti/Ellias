package tencent.com.cftutils;

import com.tenpay.a.c.a;
import java.io.File;
import java.io.UnsupportedEncodingException;

public class Base64EncUtil
{
  private byte[] enc_buf;
  private byte[] raw_buf;

  static
  {
    if (a.a != null)
      try
      {
        File localFile = new File(a.a);
        if ((!localFile.exists()) || (localFile.isDirectory()))
        {
          System.loadLibrary("cftutils");
          return;
        }
        System.load(a.a);
        return;
      }
      catch (Exception localException)
      {
        return;
      }
    System.loadLibrary("cftutils");
  }

  private native boolean base64_encode();

  public String base64Encode(String paramString)
  {
    if (paramString.length() > 0);
    while (true)
    {
      try
      {
        this.raw_buf = paramString.getBytes("UTF-8");
        base64_encode();
        if (this.enc_buf != null)
          try
          {
            str = new String(this.enc_buf, "ASCII");
            return str;
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException)
          {
            str = null;
            continue;
          }
      }
      catch (Exception localException)
      {
        return null;
      }
      String str = null;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     tencent.com.cftutils.Base64EncUtil
 * JD-Core Version:    0.6.0
 */
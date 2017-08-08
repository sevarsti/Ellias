package tencent.com.cftutils;

import com.tenpay.a.c.a;
import java.io.File;

public class DesDecUtil
{
  private static final String CHARSET = "UTF-8";
  private byte[] dec_buf;
  private byte[] key_buf;
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

  private native boolean decrypt_des(int paramInt, byte[] paramArrayOfByte);

  private native boolean decrypt_des_withstringkey(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

  public String decDesWithStringKey(String paramString1, String paramString2)
  {
    this.dec_buf = null;
    if ((paramString2 != null) && (paramString2.length() > 0));
    while (true)
    {
      try
      {
        this.raw_buf = paramString2.getBytes("UTF-8");
        this.key_buf = paramString1.getBytes("UTF-8");
        decrypt_des_withstringkey(this.key_buf, this.raw_buf);
        if (this.dec_buf == null)
          return null;
        try
        {
          str = new String(this.dec_buf, "UTF-8");
          return str;
        }
        catch (Exception localException2)
        {
          return null;
        }
      }
      catch (Exception localException1)
      {
        return null;
      }
      String str = null;
    }
  }

  public boolean decryptDes(int paramInt, String paramString)
  {
    int i = paramString.length();
    boolean bool = false;
    if (i > 0);
    try
    {
      this.raw_buf = paramString.getBytes("UTF-8");
      bool = decrypt_des(paramInt, this.raw_buf);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public String getDecRes()
  {
    if (this.dec_buf == null)
      return null;
    try
    {
      String str = new String(this.dec_buf, "UTF-8");
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     tencent.com.cftutils.DesDecUtil
 * JD-Core Version:    0.6.0
 */
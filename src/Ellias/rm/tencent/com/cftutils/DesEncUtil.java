package tencent.com.cftutils;

import com.tenpay.a.c.a;
import java.io.File;

public class DesEncUtil
{
  private static final String CHARSET = "UTF-8";
  private byte[] enc_buf;
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

  private native boolean encrypt_des(int paramInt, byte[] paramArrayOfByte);

  private native boolean encrypt_des_withstringkey(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

  private native boolean encrypt_des_withstringkey_onedes(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

  public boolean encryptDes(int paramInt, String paramString)
  {
    int i = paramString.length();
    boolean bool = false;
    if (i > 0);
    try
    {
      this.raw_buf = paramString.getBytes("UTF-8");
      bool = encrypt_des(paramInt, this.raw_buf);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public String encryptDesWithStringKey(String paramString1, String paramString2)
  {
    this.enc_buf = null;
    if ((paramString2 != null) && (paramString2.length() > 0) && (paramString1 != null));
    while (true)
    {
      try
      {
        this.raw_buf = paramString2.getBytes("UTF-8");
        this.key_buf = paramString1.getBytes("UTF-8");
        encrypt_des_withstringkey(this.key_buf, this.raw_buf);
        if (this.enc_buf == null)
          return null;
        try
        {
          str = new String(this.enc_buf, "UTF-8");
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

  public String encryptDesWithStringKeyOnedes(String paramString1, String paramString2)
  {
    this.enc_buf = null;
    if ((paramString2 != null) && (paramString2.length() > 0) && (paramString1 != null));
    while (true)
    {
      try
      {
        this.raw_buf = paramString2.getBytes("UTF-8");
        this.key_buf = paramString1.getBytes("UTF-8");
        encrypt_des_withstringkey_onedes(this.key_buf, this.raw_buf);
        if (this.enc_buf == null)
          return null;
        try
        {
          str = new String(this.enc_buf, "UTF-8");
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

  public String getDesEncResult()
  {
    if (this.enc_buf == null)
      return null;
    try
    {
      String str = new String(this.enc_buf, "UTF-8");
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     tencent.com.cftutils.DesEncUtil
 * JD-Core Version:    0.6.0
 */
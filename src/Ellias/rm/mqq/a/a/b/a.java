package mqq.a.a.b;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class a
  implements c
{
  private String a = null;

  public final void a(String paramString)
  {
    if (paramString != null);
    for (int i = paramString.length(); ; i++)
    {
      if (i >= 16)
      {
        this.a = paramString.substring(0, 16);
        return;
      }
      paramString = paramString + "0";
    }
  }

  public final byte[] a(byte[] paramArrayOfByte)
    throws Exception
  {
    int i = 0;
    byte[] arrayOfByte;
    if ((this.a == null) || (paramArrayOfByte == null))
    {
      arrayOfByte = null;
      return arrayOfByte;
    }
    StringBuffer localStringBuffer1 = new StringBuffer();
    int j = paramArrayOfByte.length;
    for (int k = 0; ; k++)
    {
      if (k >= j)
      {
        SecretKeySpec localSecretKeySpec = new SecretKeySpec(this.a.getBytes(), "AES");
        Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        localCipher.init(2, localSecretKeySpec, new IvParameterSpec(this.a.getBytes()));
        arrayOfByte = localCipher.doFinal(paramArrayOfByte);
        StringBuffer localStringBuffer2 = new StringBuffer();
        int m = arrayOfByte.length;
        while (i < m)
        {
          localStringBuffer2.append(arrayOfByte[i] + " ");
          i++;
        }
        break;
      }
      localStringBuffer1.append(paramArrayOfByte[k] + " ");
    }
  }

  public final byte[] b(byte[] paramArrayOfByte)
    throws Exception, NoSuchAlgorithmException
  {
    int i = 0;
    byte[] arrayOfByte;
    if ((this.a == null) || (paramArrayOfByte == null))
    {
      arrayOfByte = null;
      return arrayOfByte;
    }
    StringBuffer localStringBuffer1 = new StringBuffer();
    int j = paramArrayOfByte.length;
    for (int k = 0; ; k++)
    {
      if (k >= j)
      {
        SecretKeySpec localSecretKeySpec = new SecretKeySpec(this.a.getBytes(), "AES");
        Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        localCipher.init(1, localSecretKeySpec, new IvParameterSpec(this.a.getBytes()));
        arrayOfByte = localCipher.doFinal(paramArrayOfByte);
        StringBuffer localStringBuffer2 = new StringBuffer();
        int m = arrayOfByte.length;
        while (i < m)
        {
          localStringBuffer2.append(arrayOfByte[i] + " ");
          i++;
        }
        break;
      }
      localStringBuffer1.append(paramArrayOfByte[k] + " ");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     mqq.a.a.b.a
 * JD-Core Version:    0.6.0
 */
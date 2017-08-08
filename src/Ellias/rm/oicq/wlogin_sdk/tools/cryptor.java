package oicq.wlogin_sdk.tools;

public class cryptor
{
  public static byte[] decrypt(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
  {
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null))
      return null;
    byte[] arrayOfByte1 = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte1, paramInt1, arrayOfByte1, 0, paramInt2);
    byte[] arrayOfByte2 = new byte[paramArrayOfByte2.length];
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte2, 0, paramArrayOfByte2.length);
    return new CryptorImpl().decrypt(arrayOfByte1, arrayOfByte2);
  }

  public static byte[] encrypt(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
  {
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null))
      return null;
    byte[] arrayOfByte1 = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte1, paramInt1, arrayOfByte1, 0, paramInt2);
    byte[] arrayOfByte2 = new byte[paramArrayOfByte2.length];
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte2, 0, paramArrayOfByte2.length);
    return new CryptorImpl().encrypt(arrayOfByte1, arrayOfByte2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tools.cryptor
 * JD-Core Version:    0.6.0
 */
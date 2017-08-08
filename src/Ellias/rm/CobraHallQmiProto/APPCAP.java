package CobraHallQmiProto;

import java.io.Serializable;

public final class APPCAP
  implements Serializable
{
  public static final APPCAP APPCAP_DELTAPATCH;
  public static final int _APPCAP_DELTAPATCH = 1;
  private static APPCAP[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!APPCAP.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new APPCAP[1];
      APPCAP_DELTAPATCH = new APPCAP(0, 1, "APPCAP_DELTAPATCH");
      return;
    }
  }

  private APPCAP(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static APPCAP a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static APPCAP a(String paramString)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].toString().equals(paramString))
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public int a()
  {
    return this.__value;
  }

  public String toString()
  {
    return this.__T;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.APPCAP
 * JD-Core Version:    0.6.0
 */
package CobraHallQmiProto;

import java.io.Serializable;

public final class HallGAMEVIPTYPE
  implements Serializable
{
  public static final HallGAMEVIPTYPE HGVT_AFTLUXURYVIP;
  public static final HallGAMEVIPTYPE HGVT_AFTNORMALVIP;
  public static final HallGAMEVIPTYPE HGVT_AFTSUPERVIP;
  public static final HallGAMEVIPTYPE HGVT_LUXURYVIP;
  public static final HallGAMEVIPTYPE HGVT_NORMALVIP;
  public static final HallGAMEVIPTYPE HGVT_NOYEAEVIP;
  public static final HallGAMEVIPTYPE HGVT_SUPERVIP;
  public static final HallGAMEVIPTYPE HGVT_VIPLEVEL1;
  public static final HallGAMEVIPTYPE HGVT_VIPLEVEL2;
  public static final HallGAMEVIPTYPE HGVT_VIPLEVEL3;
  public static final HallGAMEVIPTYPE HGVT_VIPLEVEL4;
  public static final HallGAMEVIPTYPE HGVT_VIPLEVEL5;
  public static final HallGAMEVIPTYPE HGVT_VIPLEVEL6;
  public static final HallGAMEVIPTYPE HGVT_VIPLEVEL7;
  public static final HallGAMEVIPTYPE HGVT_VIPLEVEL8;
  public static final HallGAMEVIPTYPE HGVT_YEAEVIP;
  public static final HallGAMEVIPTYPE HGVT_YEAEVIPTIMEOUT;
  public static final int _HGVT_AFTLUXURYVIP = 32;
  public static final int _HGVT_AFTNORMALVIP = 16;
  public static final int _HGVT_AFTSUPERVIP = 64;
  public static final int _HGVT_LUXURYVIP = 2;
  public static final int _HGVT_NORMALVIP = 1;
  public static final int _HGVT_NOYEAEVIP = 1024;
  public static final int _HGVT_SUPERVIP = 4;
  public static final int _HGVT_VIPLEVEL1 = 65536;
  public static final int _HGVT_VIPLEVEL2 = 131072;
  public static final int _HGVT_VIPLEVEL3 = 262144;
  public static final int _HGVT_VIPLEVEL4 = 524288;
  public static final int _HGVT_VIPLEVEL5 = 1048576;
  public static final int _HGVT_VIPLEVEL6 = 2097152;
  public static final int _HGVT_VIPLEVEL7 = 4194304;
  public static final int _HGVT_VIPLEVEL8 = 8388608;
  public static final int _HGVT_YEAEVIP = 256;
  public static final int _HGVT_YEAEVIPTIMEOUT = 512;
  private static HallGAMEVIPTYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!HallGAMEVIPTYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new HallGAMEVIPTYPE[17];
      HGVT_NORMALVIP = new HallGAMEVIPTYPE(0, 1, "HGVT_NORMALVIP");
      HGVT_LUXURYVIP = new HallGAMEVIPTYPE(1, 2, "HGVT_LUXURYVIP");
      HGVT_SUPERVIP = new HallGAMEVIPTYPE(2, 4, "HGVT_SUPERVIP");
      HGVT_AFTNORMALVIP = new HallGAMEVIPTYPE(3, 16, "HGVT_AFTNORMALVIP");
      HGVT_AFTLUXURYVIP = new HallGAMEVIPTYPE(4, 32, "HGVT_AFTLUXURYVIP");
      HGVT_AFTSUPERVIP = new HallGAMEVIPTYPE(5, 64, "HGVT_AFTSUPERVIP");
      HGVT_YEAEVIP = new HallGAMEVIPTYPE(6, 256, "HGVT_YEAEVIP");
      HGVT_YEAEVIPTIMEOUT = new HallGAMEVIPTYPE(7, 512, "HGVT_YEAEVIPTIMEOUT");
      HGVT_NOYEAEVIP = new HallGAMEVIPTYPE(8, 1024, "HGVT_NOYEAEVIP");
      HGVT_VIPLEVEL1 = new HallGAMEVIPTYPE(9, 65536, "HGVT_VIPLEVEL1");
      HGVT_VIPLEVEL2 = new HallGAMEVIPTYPE(10, 131072, "HGVT_VIPLEVEL2");
      HGVT_VIPLEVEL3 = new HallGAMEVIPTYPE(11, 262144, "HGVT_VIPLEVEL3");
      HGVT_VIPLEVEL4 = new HallGAMEVIPTYPE(12, 524288, "HGVT_VIPLEVEL4");
      HGVT_VIPLEVEL5 = new HallGAMEVIPTYPE(13, 1048576, "HGVT_VIPLEVEL5");
      HGVT_VIPLEVEL6 = new HallGAMEVIPTYPE(14, 2097152, "HGVT_VIPLEVEL6");
      HGVT_VIPLEVEL7 = new HallGAMEVIPTYPE(15, 4194304, "HGVT_VIPLEVEL7");
      HGVT_VIPLEVEL8 = new HallGAMEVIPTYPE(16, 8388608, "HGVT_VIPLEVEL8");
      return;
    }
  }

  private HallGAMEVIPTYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static HallGAMEVIPTYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static HallGAMEVIPTYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.HallGAMEVIPTYPE
 * JD-Core Version:    0.6.0
 */
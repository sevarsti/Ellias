package com.tencent.component.media;

import java.util.HashMap;

public class MediaFile
{
  public static final int A = 203;
  public static final int B = 31;
  public static final int C = 32;
  public static final int D = 33;
  public static final int E = 34;
  public static final int F = 35;
  public static final int G = 36;
  public static final int H = 41;
  public static final int I = 42;
  public static final int J = 43;
  public static final int K = 44;
  public static final int L = 51;
  public static final int M = 100;
  public static final int N = 101;
  public static final int O = 102;
  public static final int P = 103;
  public static final int Q = 104;
  public static final int R = 105;
  public static final int S = 106;
  public static final int T = 107;
  private static final int U = 1;
  private static final int V = 10;
  private static final int W = 11;
  private static final int X = 13;
  private static final int Y = 21;
  private static final int Z = 30;
  public static final int a = 1;
  private static final int aa = 200;
  private static final int ab = 203;
  private static final int ac = 31;
  private static final int ad = 36;
  private static final int ae = 41;
  private static final int af = 44;
  private static final int ag = 51;
  private static final int ah = 51;
  private static final HashMap ai = new HashMap();
  private static final HashMap aj = new HashMap();
  private static final HashMap ak = new HashMap();
  private static final HashMap al = new HashMap();
  private static final HashMap am = new HashMap();
  public static final int b = 2;
  public static final int c = 3;
  public static final int d = 4;
  public static final int e = 5;
  public static final int f = 6;
  public static final int g = 7;
  public static final int h = 8;
  public static final int i = 9;
  public static final int j = 10;
  public static final int k = 11;
  public static final int l = 12;
  public static final int m = 13;
  public static final int n = 21;
  public static final int o = 22;
  public static final int p = 23;
  public static final int q = 24;
  public static final int r = 25;
  public static final int s = 26;
  public static final int t = 27;
  public static final int u = 28;
  public static final int v = 29;
  public static final int w = 30;
  public static final int x = 200;
  public static final int y = 201;
  public static final int z = 202;

  static
  {
    a("MP3", 1, "audio/mpeg", 12297);
    a("MPGA", 1, "audio/mpeg", 12297);
    a("M4A", 2, "audio/mp4", 12299);
    a("WAV", 3, "audio/x-wav", 12296);
    a("AMR", 4, "audio/amr");
    a("AWB", 5, "audio/amr-wb");
    if (a())
      a("WMA", 6, "audio/x-ms-wma", 47361);
    a("OGG", 7, "audio/ogg", 47362);
    a("OGG", 7, "application/ogg", 47362);
    a("OGA", 7, "application/ogg", 47362);
    a("AAC", 8, "audio/aac", 47363);
    a("AAC", 8, "audio/aac-adts", 47363);
    a("MKA", 9, "audio/x-matroska");
    a("MID", 11, "audio/midi");
    a("MIDI", 11, "audio/midi");
    a("XMF", 11, "audio/midi");
    a("RTTTL", 11, "audio/midi");
    a("SMF", 12, "audio/sp-midi");
    a("IMY", 13, "audio/imelody");
    a("RTX", 11, "audio/midi");
    a("OTA", 11, "audio/midi");
    a("MXMF", 11, "audio/midi");
    a("MPEG", 21, "video/mpeg", 12299);
    a("MPG", 21, "video/mpeg", 12299);
    a("MP4", 21, "video/mp4", 12299);
    a("M4V", 22, "video/mp4", 12299);
    a("3GP", 23, "video/3gpp", 47492);
    a("3GPP", 23, "video/3gpp", 47492);
    a("3G2", 24, "video/3gpp2", 47492);
    a("3GPP2", 24, "video/3gpp2", 47492);
    a("MKV", 27, "video/x-matroska");
    a("WEBM", 30, "video/webm");
    a("TS", 28, "video/mp2ts");
    a("AVI", 29, "video/avi");
    if (b())
    {
      a("WMV", 25, "video/x-ms-wmv", 47489);
      a("ASF", 26, "video/x-ms-asf");
    }
    a("JPG", 31, "image/jpeg", 14337);
    a("JPEG", 31, "image/jpeg", 14337);
    a("GIF", 32, "image/gif", 14343);
    a("PNG", 33, "image/png", 14347);
    a("BMP", 34, "image/x-ms-bmp", 14340);
    a("WBMP", 35, "image/vnd.wap.wbmp");
    a("WEBP", 36, "image/webp");
    a("M3U", 41, "audio/x-mpegurl", 47633);
    a("M3U", 41, "application/x-mpegurl", 47633);
    a("PLS", 42, "audio/x-scpls", 47636);
    a("WPL", 43, "application/vnd.ms-wpl", 47632);
    a("M3U8", 44, "application/vnd.apple.mpegurl");
    a("M3U8", 44, "audio/mpegurl");
    a("M3U8", 44, "audio/x-mpegurl");
    a("FL", 51, "application/x-android-drm-fl");
    a("TXT", 100, "text/plain", 12292);
    a("HTM", 101, "text/html", 12293);
    a("HTML", 101, "text/html", 12293);
    a("PDF", 102, "application/pdf");
    a("DOC", 104, "application/msword", 47747);
    a("XLS", 105, "application/vnd.ms-excel", 47749);
    a("PPT", 106, "application/mspowerpoint", 47750);
    a("FLAC", 10, "audio/flac", 47366);
    a("ZIP", 107, "application/zip");
    a("MPG", 200, "video/mp2p");
    a("MPEG", 200, "video/mp2p");
    a("FLV", 201, "video/x-flv");
    a("MOV", 202, "video/quicktime");
    a("QT", 202, "video/quicktime");
    a("RMVB", 203, "video/vnd.rn-realvideo");
  }

  public static int a(String paramString1, String paramString2)
  {
    if (paramString2 != null)
    {
      Integer localInteger2 = (Integer)al.get(paramString2);
      if (localInteger2 != null)
        return localInteger2.intValue();
    }
    int i1 = paramString1.lastIndexOf('.');
    if (i1 > 0)
    {
      String str = paramString1.substring(i1 + 1).toUpperCase();
      Integer localInteger1 = (Integer)ak.get(str);
      if (localInteger1 != null)
        return localInteger1.intValue();
    }
    return 12288;
  }

  public static MediaFile.MediaFileType a(String paramString)
  {
    int i1 = paramString.lastIndexOf(".");
    if (i1 < 0)
      return null;
    return (MediaFile.MediaFileType)ai.get(paramString.substring(i1 + 1).toUpperCase());
  }

  static void a(String paramString1, int paramInt, String paramString2)
  {
    ai.put(paramString1, new MediaFile.MediaFileType(paramInt, paramString2));
    aj.put(paramString2, Integer.valueOf(paramInt));
  }

  static void a(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    a(paramString1, paramInt1, paramString2);
    ak.put(paramString1, Integer.valueOf(paramInt2));
    al.put(paramString2, Integer.valueOf(paramInt2));
    am.put(Integer.valueOf(paramInt2), paramString2);
  }

  private static boolean a()
  {
    return true;
  }

  public static boolean a(int paramInt)
  {
    return ((paramInt >= 1) && (paramInt <= 10)) || ((paramInt >= 11) && (paramInt <= 13));
  }

  private static boolean b()
  {
    return true;
  }

  public static boolean b(int paramInt)
  {
    return ((paramInt >= 21) && (paramInt <= 30)) || ((paramInt >= 200) && (paramInt <= 203));
  }

  public static boolean b(String paramString)
  {
    int i1 = d(paramString);
    return (a(i1)) || (b(i1)) || (c(i1)) || (d(i1));
  }

  public static String c(String paramString)
  {
    int i1 = paramString.lastIndexOf('/');
    if (i1 >= 0)
    {
      int i3 = i1 + 1;
      if (i3 < paramString.length())
        paramString = paramString.substring(i3);
    }
    int i2 = paramString.lastIndexOf('.');
    if (i2 > 0)
      paramString = paramString.substring(0, i2);
    return paramString;
  }

  public static boolean c(int paramInt)
  {
    return (paramInt >= 31) && (paramInt <= 36);
  }

  public static int d(String paramString)
  {
    Integer localInteger = (Integer)aj.get(paramString);
    if (localInteger == null)
      return 0;
    return localInteger.intValue();
  }

  public static boolean d(int paramInt)
  {
    return (paramInt >= 41) && (paramInt <= 44);
  }

  public static String e(String paramString)
  {
    MediaFile.MediaFileType localMediaFileType = a(paramString);
    if (localMediaFileType == null)
      return null;
    return localMediaFileType.b;
  }

  public static boolean e(int paramInt)
  {
    return (paramInt >= 51) && (paramInt <= 51);
  }

  public static String f(int paramInt)
  {
    return (String)am.get(Integer.valueOf(paramInt));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.media.MediaFile
 * JD-Core Version:    0.6.0
 */
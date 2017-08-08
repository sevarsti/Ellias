package com.tencent.qqgamemi.protocol;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.QMiApplication;

public class ClientCode
{
  public static final int A = -20002;
  public static final int B = -20003;
  public static final int C = -20004;
  public static final int D = -20005;
  public static final int E = -20006;
  public static final int F = -20007;
  public static final int G = -20008;
  public static final int H = -20009;
  public static final int I = -20010;
  public static final int J = -20011;
  public static final int K = -20012;
  public static final int L = -20014;
  public static final int M = -20015;
  public static final int N = -20016;
  public static final int O = -20018;
  public static final int P = -20032;
  public static final int Q = -20033;
  public static final int R = -20040;
  public static final int S = -20041;
  public static final int T = -20042;
  public static final int U = -20043;
  public static final int V = -20044;
  public static final int W = -20048;
  public static final int X = -20064;
  public static final int Y = -20113;
  public static final int Z = -20128;
  public static final int a = 0;
  public static final int aA = -20271;
  public static final int aB = -20272;
  public static final int aC = -20356;
  public static final int aD = -20509;
  public static final int aE = -20510;
  public static final int aF = -20511;
  public static final int aG = -20512;
  private static final int aH = -10000;
  private static final int aI = -11000;
  private static final int aJ = -20000;
  public static final int aa = -20129;
  public static final int ab = -20130;
  public static final int ac = -20131;
  public static final int ad = -20132;
  public static final int ae = -20133;
  public static final int af = -20134;
  public static final int ag = -20154;
  public static final int ah = -20160;
  public static final int ai = -20161;
  public static final int aj = -20162;
  public static final int ak = -20163;
  public static final int al = -20256;
  public static final int am = -20257;
  public static final int an = -20258;
  public static final int ao = -20259;
  public static final int ap = -20260;
  public static final int aq = -20261;
  public static final int ar = -20262;
  public static final int as = -20263;
  public static final int at = -20264;
  public static final int au = -20265;
  public static final int av = -20266;
  public static final int aw = -20267;
  public static final int ax = -20268;
  public static final int ay = -20269;
  public static final int az = -20270;
  public static final int b = -1;
  public static final int c = -2;
  public static final int d = -3;
  public static final int e = -4;
  public static final int f = -5;
  public static final int g = -6;
  public static final int h = -7;
  public static final int i = -8;
  public static final int j = -9;
  public static final int k = -100;
  public static final int l = -10001;
  public static final int m = -10002;
  public static final int n = -10003;
  public static final int o = -10004;
  public static final int p = -10005;
  public static final int q = -10006;
  public static final int r = -10007;
  public static final int s = -10008;
  public static final int t = -10009;
  public static final int u = -10010;
  public static final int v = -10011;
  public static final int w = -10012;
  public static final int x = -10013;
  public static final int y = -10014;
  public static final int z = -20001;

  public static String a(int paramInt)
  {
    Context localContext = QMiApplication.a();
    if (localContext == null)
      return String.valueOf(paramInt);
    Resources localResources = localContext.getResources();
    if (localResources != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("error_");
      if (paramInt < 0)
        localStringBuilder.append("minor_");
      localStringBuilder.append(Math.abs(paramInt));
      try
      {
        int i1 = localResources.getIdentifier(localStringBuilder.toString(), "string", localContext.getPackageName());
        if (i1 != 0)
          return localResources.getString(i1);
        String str = localResources.getString(ResourceUtil.b("default_error_msg")) + "(" + paramInt + ")";
        return str;
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
      }
    }
    return "";
  }

  public static int b(int paramInt)
  {
    if (paramInt <= -11000)
      return paramInt;
    return -11000 - paramInt;
  }

  public static int c(int paramInt)
  {
    if (paramInt < 0)
      paramInt = -744 - paramInt;
    return -20000 - paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.ClientCode
 * JD-Core Version:    0.6.0
 */
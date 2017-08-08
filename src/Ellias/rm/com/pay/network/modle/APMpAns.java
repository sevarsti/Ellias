package com.pay.network.modle;

import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class APMpAns extends APBaseHttpAns
{
  private List a = new ArrayList();
  private List b = new ArrayList();
  private List c = new ArrayList();
  private String d = "";
  private String e = "";
  private String f = "";
  private String g = "";
  private List h = new ArrayList();
  private String i = "";

  public APMpAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  public String getBeginTime()
  {
    return this.f;
  }

  public String getEndTime()
  {
    return this.g;
  }

  public String getFirstsave_present_count()
  {
    return this.e;
  }

  public String getMpJson()
  {
    return this.i;
  }

  public List getMpList()
  {
    return this.a;
  }

  public List getMpPresentList()
  {
    return this.c;
  }

  public List getMpValueList()
  {
    return this.b;
  }

  public List getProductList()
  {
    return this.h;
  }

  public String getRate()
  {
    return this.d;
  }

  public void onErrorAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  // ERROR //
  public void onFinishAns(byte[] paramArrayOfByte, APBaseHttpReq paramAPBaseHttpReq)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokespecial 63	com/pay/http/APBaseHttpAns:onFinishAns	([BLcom/pay/http/APBaseHttpReq;)V
    //   6: new 65	java/lang/String
    //   9: dup
    //   10: aload_1
    //   11: invokespecial 68	java/lang/String:<init>	([B)V
    //   14: astore_3
    //   15: aload_0
    //   16: aload_3
    //   17: putfield 31	com/pay/network/modle/APMpAns:i	Ljava/lang/String;
    //   20: ldc 70
    //   22: new 72	java/lang/StringBuilder
    //   25: dup
    //   26: ldc 74
    //   28: invokespecial 77	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   31: aload_3
    //   32: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   38: invokestatic 89	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   41: new 91	org/json/JSONObject
    //   44: dup
    //   45: aload_3
    //   46: invokespecial 92	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   49: astore 4
    //   51: aload_0
    //   52: aload 4
    //   54: ldc 94
    //   56: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   59: invokevirtual 99	java/lang/String:toString	()Ljava/lang/String;
    //   62: invokestatic 105	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   65: putfield 109	com/pay/network/modle/APMpAns:resultCode	I
    //   68: aload_0
    //   69: getfield 109	com/pay/network/modle/APMpAns:resultCode	I
    //   72: ifne +205 -> 277
    //   75: aload 4
    //   77: ldc 111
    //   79: invokevirtual 115	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   82: ifeq +34 -> 116
    //   85: aload 4
    //   87: ldc 111
    //   89: invokevirtual 119	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   92: astore 7
    //   94: aload_0
    //   95: getfield 44	com/pay/network/modle/APMpAns:h	Ljava/util/List;
    //   98: invokeinterface 124 1 0
    //   103: iconst_0
    //   104: istore 8
    //   106: iload 8
    //   108: aload 7
    //   110: invokevirtual 130	org/json/JSONArray:length	()I
    //   113: if_icmplt +80 -> 193
    //   116: aload_0
    //   117: aload 4
    //   119: ldc 132
    //   121: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   124: putfield 23	com/pay/network/modle/APMpAns:d	Ljava/lang/String;
    //   127: aload 4
    //   129: ldc 134
    //   131: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   134: aload_0
    //   135: getfield 42	com/pay/network/modle/APMpAns:a	Ljava/util/List;
    //   138: invokestatic 140	com/pay/tool/APCommMethod:transformStrToList	(Ljava/lang/String;Ljava/util/List;)V
    //   141: aload_0
    //   142: aload 4
    //   144: ldc 142
    //   146: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   149: putfield 25	com/pay/network/modle/APMpAns:e	Ljava/lang/String;
    //   152: aload 4
    //   154: ldc 144
    //   156: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   159: aload_0
    //   160: getfield 38	com/pay/network/modle/APMpAns:b	Ljava/util/List;
    //   163: aload_0
    //   164: getfield 40	com/pay/network/modle/APMpAns:c	Ljava/util/List;
    //   167: invokestatic 148	com/pay/tool/APCommMethod:transformStrToMpInfoList	(Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V
    //   170: aload_0
    //   171: aload 4
    //   173: ldc 150
    //   175: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   178: putfield 27	com/pay/network/modle/APMpAns:f	Ljava/lang/String;
    //   181: aload_0
    //   182: aload 4
    //   184: ldc 152
    //   186: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   189: putfield 29	com/pay/network/modle/APMpAns:g	Ljava/lang/String;
    //   192: return
    //   193: aload 7
    //   195: iload 8
    //   197: invokevirtual 156	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   200: astore 9
    //   202: new 158	com/pay/tool/APProductItem
    //   205: dup
    //   206: invokespecial 159	com/pay/tool/APProductItem:<init>	()V
    //   209: astore 10
    //   211: aload 10
    //   213: aload 9
    //   215: ldc 161
    //   217: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   220: putfield 163	com/pay/tool/APProductItem:name	Ljava/lang/String;
    //   223: aload 10
    //   225: aload 9
    //   227: ldc 165
    //   229: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   232: putfield 168	com/pay/tool/APProductItem:productId	Ljava/lang/String;
    //   235: aload 10
    //   237: aload 9
    //   239: ldc 170
    //   241: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   244: putfield 172	com/pay/tool/APProductItem:price	Ljava/lang/String;
    //   247: aload 10
    //   249: aload 9
    //   251: ldc 174
    //   253: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   256: putfield 176	com/pay/tool/APProductItem:num	Ljava/lang/String;
    //   259: aload_0
    //   260: getfield 44	com/pay/network/modle/APMpAns:h	Ljava/util/List;
    //   263: aload 10
    //   265: invokeinterface 180 2 0
    //   270: pop
    //   271: iinc 8 1
    //   274: goto -168 -> 106
    //   277: aload_0
    //   278: aload 4
    //   280: ldc 182
    //   282: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   285: putfield 185	com/pay/network/modle/APMpAns:resultMsg	Ljava/lang/String;
    //   288: aload 4
    //   290: ldc 187
    //   292: invokevirtual 98	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   295: invokevirtual 99	java/lang/String:toString	()Ljava/lang/String;
    //   298: astore 6
    //   300: aload 6
    //   302: ldc 21
    //   304: invokevirtual 190	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   307: ifne +43 -> 350
    //   310: aload_0
    //   311: new 72	java/lang/StringBuilder
    //   314: dup
    //   315: ldc 192
    //   317: invokespecial 77	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   320: aload 6
    //   322: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   325: ldc 194
    //   327: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   333: putfield 185	com/pay/network/modle/APMpAns:resultMsg	Ljava/lang/String;
    //   336: return
    //   337: astore 5
    //   339: aload 5
    //   341: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   344: return
    //   345: astore 11
    //   347: goto -76 -> 271
    //   350: return
    //
    // Exception table:
    //   from	to	target	type
    //   41	103	337	java/lang/Exception
    //   106	116	337	java/lang/Exception
    //   116	192	337	java/lang/Exception
    //   193	211	337	java/lang/Exception
    //   277	336	337	java/lang/Exception
    //   211	271	345	java/lang/Exception
  }

  public void onReceiveAns(byte[] paramArrayOfByte, int paramInt, long paramLong, APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onStartAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onStopAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void setBeginTime(String paramString)
  {
    this.f = paramString;
  }

  public void setEndTime(String paramString)
  {
    this.g = paramString;
  }

  public void setFirstsave_present_count(String paramString)
  {
    this.e = paramString;
  }

  public void setMpList(List paramList)
  {
    this.a = paramList;
  }

  public void setMpPresentList(List paramList)
  {
    this.c = paramList;
  }

  public void setMpValueList(List paramList)
  {
    this.b = paramList;
  }

  public void setRate(String paramString)
  {
    this.d = paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APMpAns
 * JD-Core Version:    0.6.0
 */
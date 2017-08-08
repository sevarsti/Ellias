package com.pay.network.modle;

import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APDataInterface;
import com.pay.tool.APToolAES;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APSaveAns extends APBaseHttpAns
{
  private String a = "";
  private String b = "";
  private String c = "";
  private String d = "";
  private int e = -1;
  private String f = "";
  private String g = "";
  private String h = "";
  private String i = "";
  private String j = "";
  private String k = "";
  private String l = "";
  private String m = "";
  private String n = "";
  private String o = "";
  private String p = "";
  private String q = "";
  private String r = "";
  private String s = "";
  private String t = "";
  private String u = "";
  private String v = "";
  private String w = "1";
  private String x = "";
  private String y = "";
  private String z = "";

  public APSaveAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  private void a(JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject = paramJSONObject.getJSONObject("wx_info");
      this.m = localJSONObject.getString("wx_appid");
      this.n = localJSONObject.getString("wx_time");
      this.o = localJSONObject.getString("wx_noncenum");
      this.p = localJSONObject.getString("wx_sign");
      this.q = localJSONObject.getString("wx_package");
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }

  private void b(JSONObject paramJSONObject)
  {
    while (true)
    {
      try
      {
        JSONObject localJSONObject = paramJSONObject.getJSONObject("hf_info");
        this.r = localJSONObject.getString("vtype");
        this.s = localJSONObject.getString("province");
        this.t = localJSONObject.getString("accessnum");
        this.u = localJSONObject.getString("accessmsg");
        this.v = localJSONObject.getString("billno");
        if (!localJSONObject.has("up_times"))
          continue;
        this.w = localJSONObject.getString("up_times");
        if (localJSONObject.has("price"))
        {
          this.x = localJSONObject.getString("price");
          if (!localJSONObject.has("tips"))
            break label179;
          this.y = localJSONObject.getString("tips");
          if (!localJSONObject.has("fee_type"))
            break label188;
          this.z = localJSONObject.getString("fee_type");
          if (!localJSONObject.has("real_servicecode"))
            break;
          APDataInterface.singleton().setHfRealServiceCode(localJSONObject.getString("real_servicecode"));
          return;
          this.w = "1";
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      this.x = "";
      continue;
      label179: this.y = "";
      continue;
      label188: this.z = "";
    }
    APDataInterface.singleton().setHfRealServiceCode("");
  }

  private void c(JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject = paramJSONObject.getJSONObject("user_info");
      String str1 = localJSONObject.getString("uin");
      String str2 = localJSONObject.getString("uin_type");
      localJSONObject.getInt("uin_len");
      int i1 = localJSONObject.getInt("codeindex");
      if ((!str1.equals("")) && (i1 < this.AesEncodeKey.length))
      {
        APToolAES.doDecode(str1, this.AesEncodeKey[i1]);
        APDataInterface.singleton().getUserInfo().uinFromSvr = str2;
      }
      APDataInterface.singleton().getUserInfo().uinTypeFromSvr = str2;
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }

  public String getHfSerialNo()
  {
    return this.i;
  }

  public String getHf_FeeType()
  {
    return this.z;
  }

  public String getHf_Tips()
  {
    return this.y;
  }

  public String getHf_accessmsg()
  {
    return this.u;
  }

  public String getHf_accessnum()
  {
    return this.t;
  }

  public String getHf_area()
  {
    return this.s;
  }

  public String getHf_billno()
  {
    return this.v;
  }

  public String getHf_operator()
  {
    return this.r;
  }

  public String getHf_price()
  {
    return this.x;
  }

  public String getHf_upTimes()
  {
    return this.w;
  }

  public String getMbUrl()
  {
    return this.c;
  }

  public String getMcardSerialNo()
  {
    return this.h;
  }

  public String getQCardBalance()
  {
    return this.l;
  }

  public String getSaveCount()
  {
    return this.b;
  }

  public String getSmsInfo()
  {
    return this.d;
  }

  public String getSmsMbOnly()
  {
    return this.f;
  }

  public int getSmsRemain()
  {
    return this.e;
  }

  public String getTenpayUrl()
  {
    return this.g;
  }

  public String getVerifyCode()
  {
    return this.j;
  }

  public String getVerifySession()
  {
    return this.k;
  }

  public String getWXAppId()
  {
    return this.m;
  }

  public String getWXNonce()
  {
    return this.o;
  }

  public String getWXSign()
  {
    return this.p;
  }

  public String getWXTime()
  {
    return this.n;
  }

  public String getWxPackage()
  {
    return this.q;
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
    //   3: invokespecial 234	com/pay/http/APBaseHttpAns:onFinishAns	([BLcom/pay/http/APBaseHttpReq;)V
    //   6: new 173	java/lang/String
    //   9: dup
    //   10: aload_1
    //   11: invokespecial 237	java/lang/String:<init>	([B)V
    //   14: astore_3
    //   15: ldc 239
    //   17: new 241	java/lang/StringBuilder
    //   20: dup
    //   21: ldc 243
    //   23: invokespecial 245	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   26: aload_3
    //   27: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: invokestatic 257	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   36: new 99	org/json/JSONObject
    //   39: dup
    //   40: aload_3
    //   41: invokespecial 258	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   44: astore 4
    //   46: aload 4
    //   48: ldc_w 260
    //   51: invokevirtual 103	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   54: astore 6
    //   56: aload_0
    //   57: aload 4
    //   59: ldc_w 262
    //   62: invokevirtual 169	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   65: putfield 265	com/pay/network/modle/APSaveAns:resultCode	I
    //   68: aload_0
    //   69: aload 4
    //   71: ldc_w 267
    //   74: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   77: putfield 270	com/pay/network/modle/APSaveAns:resultMsg	Ljava/lang/String;
    //   80: aload_0
    //   81: aload 6
    //   83: ldc_w 272
    //   86: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   89: putfield 42	com/pay/network/modle/APSaveAns:b	Ljava/lang/String;
    //   92: aload_0
    //   93: aload 6
    //   95: ldc_w 274
    //   98: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   101: putfield 44	com/pay/network/modle/APSaveAns:c	Ljava/lang/String;
    //   104: aload_0
    //   105: aload 6
    //   107: ldc_w 276
    //   110: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   113: putfield 52	com/pay/network/modle/APSaveAns:g	Ljava/lang/String;
    //   116: aload_0
    //   117: aload 6
    //   119: ldc_w 278
    //   122: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   125: putfield 40	com/pay/network/modle/APSaveAns:a	Ljava/lang/String;
    //   128: aload_0
    //   129: getfield 40	com/pay/network/modle/APSaveAns:a	Ljava/lang/String;
    //   132: invokestatic 284	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   135: ifne +26 -> 161
    //   138: aload_0
    //   139: getfield 40	com/pay/network/modle/APSaveAns:a	Ljava/lang/String;
    //   142: ldc_w 286
    //   145: invokevirtual 177	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   148: ifne +13 -> 161
    //   151: invokestatic 153	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   154: aload_0
    //   155: getfield 40	com/pay/network/modle/APSaveAns:a	Ljava/lang/String;
    //   158: invokevirtual 289	com/pay/tool/APDataInterface:setSuccess_url	(Ljava/lang/String;)V
    //   161: aload_0
    //   162: aload 6
    //   164: ldc_w 291
    //   167: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   170: putfield 54	com/pay/network/modle/APSaveAns:h	Ljava/lang/String;
    //   173: aload_0
    //   174: aload_0
    //   175: getfield 54	com/pay/network/modle/APSaveAns:h	Ljava/lang/String;
    //   178: putfield 56	com/pay/network/modle/APSaveAns:i	Ljava/lang/String;
    //   181: aload_0
    //   182: aload 6
    //   184: ldc_w 293
    //   187: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   190: putfield 58	com/pay/network/modle/APSaveAns:j	Ljava/lang/String;
    //   193: aload_0
    //   194: aload 6
    //   196: ldc_w 295
    //   199: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   202: putfield 60	com/pay/network/modle/APSaveAns:k	Ljava/lang/String;
    //   205: aload 6
    //   207: ldc_w 297
    //   210: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   213: astore 14
    //   215: invokestatic 303	com/pay/tool/APMpDataInterface:getIntanceMpDataInterface	()Lcom/pay/tool/APMpDataInterface;
    //   218: ldc 38
    //   220: invokevirtual 306	com/pay/tool/APMpDataInterface:setGiveMp	(Ljava/lang/String;)V
    //   223: aload 14
    //   225: invokestatic 284	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   228: ifne +22 -> 250
    //   231: aload 14
    //   233: ldc_w 286
    //   236: invokevirtual 177	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   239: ifne +11 -> 250
    //   242: invokestatic 303	com/pay/tool/APMpDataInterface:getIntanceMpDataInterface	()Lcom/pay/tool/APMpDataInterface;
    //   245: aload 14
    //   247: invokevirtual 306	com/pay/tool/APMpDataInterface:setGiveMp	(Ljava/lang/String;)V
    //   250: aload 6
    //   252: ldc_w 308
    //   255: invokevirtual 139	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   258: ifeq +15 -> 273
    //   261: aload_0
    //   262: aload 6
    //   264: ldc_w 308
    //   267: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   270: putfield 46	com/pay/network/modle/APSaveAns:d	Ljava/lang/String;
    //   273: aload_0
    //   274: getfield 46	com/pay/network/modle/APSaveAns:d	Ljava/lang/String;
    //   277: ifnull +25 -> 302
    //   280: aload_0
    //   281: getfield 46	com/pay/network/modle/APSaveAns:d	Ljava/lang/String;
    //   284: ldc 38
    //   286: invokevirtual 177	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   289: ifne +13 -> 302
    //   292: invokestatic 153	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   295: aload_0
    //   296: getfield 46	com/pay/network/modle/APSaveAns:d	Ljava/lang/String;
    //   299: invokevirtual 311	com/pay/tool/APDataInterface:setSmsInfo	(Ljava/lang/String;)V
    //   302: aload 6
    //   304: ldc_w 313
    //   307: invokevirtual 139	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   310: ifeq +194 -> 504
    //   313: aload_0
    //   314: aload 6
    //   316: ldc_w 313
    //   319: invokevirtual 169	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   322: putfield 48	com/pay/network/modle/APSaveAns:e	I
    //   325: aload 6
    //   327: ldc_w 315
    //   330: invokevirtual 139	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   333: ifeq +213 -> 546
    //   336: aload_0
    //   337: aload 6
    //   339: ldc_w 315
    //   342: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   345: putfield 50	com/pay/network/modle/APSaveAns:f	Ljava/lang/String;
    //   348: aload 4
    //   350: ldc_w 317
    //   353: invokevirtual 139	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   356: ifeq +22 -> 378
    //   359: aload 4
    //   361: ldc_w 317
    //   364: invokevirtual 169	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   367: iconst_1
    //   368: if_icmpne +187 -> 555
    //   371: invokestatic 322	com/pay/tool/APAppDataInterface:singleton	()Lcom/pay/tool/APAppDataInterface;
    //   374: iconst_1
    //   375: invokevirtual 326	com/pay/tool/APAppDataInterface:setChangeKey	(Z)V
    //   378: invokestatic 153	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   381: invokevirtual 330	com/pay/tool/APDataInterface:getOrderInfo	()Lcom/pay/data/orderInfo/APOrderInfo;
    //   384: aload_0
    //   385: getfield 42	com/pay/network/modle/APSaveAns:b	Ljava/lang/String;
    //   388: putfield 335	com/pay/data/orderInfo/APOrderInfo:succSaveNum	Ljava/lang/String;
    //   391: aload_0
    //   392: aload 4
    //   394: invokespecial 337	com/pay/network/modle/APSaveAns:a	(Lorg/json/JSONObject;)V
    //   397: aload_0
    //   398: aload 4
    //   400: invokespecial 339	com/pay/network/modle/APSaveAns:b	(Lorg/json/JSONObject;)V
    //   403: aload_0
    //   404: getfield 265	com/pay/network/modle/APSaveAns:resultCode	I
    //   407: istore 11
    //   409: iload 11
    //   411: ifeq +179 -> 590
    //   414: aload_0
    //   415: aload 6
    //   417: ldc_w 341
    //   420: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   423: putfield 62	com/pay/network/modle/APSaveAns:l	Ljava/lang/String;
    //   426: aload_0
    //   427: aload 6
    //   429: invokespecial 343	com/pay/network/modle/APSaveAns:c	(Lorg/json/JSONObject;)V
    //   432: aload_0
    //   433: aload 4
    //   435: ldc_w 267
    //   438: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   441: putfield 270	com/pay/network/modle/APSaveAns:resultMsg	Ljava/lang/String;
    //   444: aload_0
    //   445: aload_0
    //   446: getfield 270	com/pay/network/modle/APSaveAns:resultMsg	Ljava/lang/String;
    //   449: putfield 346	com/pay/network/modle/APSaveAns:errorMsg	Ljava/lang/String;
    //   452: aload 4
    //   454: ldc_w 348
    //   457: invokevirtual 109	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   460: invokevirtual 349	java/lang/String:toString	()Ljava/lang/String;
    //   463: astore 13
    //   465: aload 13
    //   467: ldc 38
    //   469: invokevirtual 177	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   472: ifne +118 -> 590
    //   475: aload_0
    //   476: new 241	java/lang/StringBuilder
    //   479: dup
    //   480: ldc_w 351
    //   483: invokespecial 245	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   486: aload 13
    //   488: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: ldc_w 353
    //   494: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   497: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   500: putfield 270	com/pay/network/modle/APSaveAns:resultMsg	Ljava/lang/String;
    //   503: return
    //   504: aload_0
    //   505: iconst_m1
    //   506: putfield 48	com/pay/network/modle/APSaveAns:e	I
    //   509: goto -184 -> 325
    //   512: astore 5
    //   514: aload_0
    //   515: new 241	java/lang/StringBuilder
    //   518: dup
    //   519: ldc_w 355
    //   522: invokespecial 245	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   525: sipush 1003
    //   528: invokestatic 361	com/pay/http/APErrorCode:getErrorCode	(I)Ljava/lang/String;
    //   531: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   534: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   537: putfield 270	com/pay/network/modle/APSaveAns:resultMsg	Ljava/lang/String;
    //   540: aload 5
    //   542: invokevirtual 121	org/json/JSONException:printStackTrace	()V
    //   545: return
    //   546: aload_0
    //   547: ldc 38
    //   549: putfield 50	com/pay/network/modle/APSaveAns:f	Ljava/lang/String;
    //   552: goto -204 -> 348
    //   555: invokestatic 322	com/pay/tool/APAppDataInterface:singleton	()Lcom/pay/tool/APAppDataInterface;
    //   558: iconst_0
    //   559: invokevirtual 326	com/pay/tool/APAppDataInterface:setChangeKey	(Z)V
    //   562: goto -184 -> 378
    //   565: astore 12
    //   567: goto -141 -> 426
    //   570: astore 10
    //   572: goto -322 -> 250
    //   575: astore 9
    //   577: goto -372 -> 205
    //   580: astore 8
    //   582: goto -401 -> 181
    //   585: astore 7
    //   587: goto -426 -> 161
    //   590: return
    //
    // Exception table:
    //   from	to	target	type
    //   36	116	512	org/json/JSONException
    //   116	161	512	org/json/JSONException
    //   161	181	512	org/json/JSONException
    //   181	205	512	org/json/JSONException
    //   205	250	512	org/json/JSONException
    //   250	273	512	org/json/JSONException
    //   273	302	512	org/json/JSONException
    //   302	325	512	org/json/JSONException
    //   325	348	512	org/json/JSONException
    //   348	378	512	org/json/JSONException
    //   378	409	512	org/json/JSONException
    //   414	426	512	org/json/JSONException
    //   426	503	512	org/json/JSONException
    //   504	509	512	org/json/JSONException
    //   546	552	512	org/json/JSONException
    //   555	562	512	org/json/JSONException
    //   414	426	565	java/lang/Exception
    //   205	250	570	java/lang/Exception
    //   181	205	575	java/lang/Exception
    //   161	181	580	java/lang/Exception
    //   116	161	585	java/lang/Exception
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APSaveAns
 * JD-Core Version:    0.6.0
 */
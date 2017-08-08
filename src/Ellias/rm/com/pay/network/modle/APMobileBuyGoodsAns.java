package com.pay.network.modle;

import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APDataInterface;
import com.pay.ui.channel.APChannelInfo;
import com.pay.ui.channel.APChannelList;
import com.pay.ui.channel.APHFAmountList;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APMobileBuyGoodsAns extends APBaseHttpAns
{
  private String a = "";
  private String b = "";
  private String c = "";
  private String d = "";
  private String e = "";
  private String f = "";
  private String g = "";
  private String h = "";
  private APOrderInfo i = APDataInterface.singleton().getOrderInfo();
  private APUserInfo j = APDataInterface.singleton().getUserInfo();

  public APMobileBuyGoodsAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  private static void a(JSONObject paramJSONObject)
  {
    int k;
    int m;
    do
      try
      {
        JSONArray localJSONArray = paramJSONObject.getJSONArray("hf_amt_allow");
        k = localJSONArray.length();
        APHFAmountList.singleton().clear();
        m = 0;
        continue;
        String str = localJSONArray.get(m).toString();
        APHFAmountList.singleton().addHFAmountList(str);
        m++;
      }
      catch (JSONException localJSONException)
      {
        APHFAmountList.singleton().clear();
        localJSONException.printStackTrace();
        return;
      }
    while (m < k);
  }

  private static void b(JSONObject paramJSONObject)
  {
    try
    {
      localArrayList1 = new ArrayList();
      localJSONArray3 = paramJSONObject.getJSONArray("show_channel");
      int i2 = localJSONArray3.length();
      i3 = 0;
      if (i3 >= i2)
        APChannelList.singleton().setShowCommChannelList(localArrayList1);
    }
    catch (JSONException localJSONException1)
    {
      try
      {
        label40: localJSONArray2 = paramJSONObject.getJSONArray("qqacct_save_channel");
        localArrayList3 = new ArrayList();
        int n = localJSONArray2.length();
        i1 = 0;
        if (i1 >= n)
          APChannelList.singleton().setMoreAccoutChannelList(localArrayList3);
      }
      catch (JSONException localJSONException1)
      {
        try
        {
          ArrayList localArrayList1;
          JSONArray localJSONArray3;
          int i3;
          JSONArray localJSONArray2;
          ArrayList localArrayList3;
          int i1;
          label82: JSONArray localJSONArray1 = paramJSONObject.getJSONArray("channel");
          int k = localJSONArray1.length();
          ArrayList localArrayList2 = new ArrayList();
          int m = 0;
          while (true)
          {
            while (true)
            {
              while (true)
              {
                if (m < k)
                  break label346;
                APChannelList.singleton().setMoreCommChannelList(localArrayList2);
                return;
                APChannelInfo localAPChannelInfo3 = new APChannelInfo();
                JSONObject localJSONObject3 = (JSONObject)localJSONArray3.get(i3);
                localAPChannelInfo3.channelId = localJSONObject3.getString("name");
                localAPChannelInfo3.channelDiscount = String.valueOf(localJSONObject3.getInt("discount"));
                localAPChannelInfo3.channelInfo = localJSONObject3.getString("info");
                localAPChannelInfo3.channelState = 1;
                try
                {
                  String str6 = localJSONObject3.getString("channelname");
                  str5 = str6;
                  localAPChannelInfo3.channelName = str5;
                  localArrayList1.add(localAPChannelInfo3);
                  i3++;
                }
                catch (Exception localException4)
                {
                  while (true)
                    String str5 = "";
                }
              }
              localException1 = localException1;
              localException1.printStackTrace();
              break label40;
              JSONObject localJSONObject2 = (JSONObject)localJSONArray2.get(i1);
              APChannelInfo localAPChannelInfo2 = new APChannelInfo();
              localAPChannelInfo2.channelId = localJSONObject2.getString("name");
              localAPChannelInfo2.channelDiscount = String.valueOf(localJSONObject2.getInt("discount"));
              localAPChannelInfo2.channelState = 2;
              try
              {
                String str4 = localJSONObject2.getString("channelname");
                str3 = str4;
                localAPChannelInfo2.channelName = str3;
                localArrayList3.add(localAPChannelInfo2);
                i1++;
              }
              catch (Exception localException3)
              {
                while (true)
                  String str3 = "";
              }
            }
            localJSONException1 = localJSONException1;
            localJSONException1.printStackTrace();
            break label82;
            label346: JSONObject localJSONObject1 = (JSONObject)localJSONArray1.get(m);
            APChannelInfo localAPChannelInfo1 = new APChannelInfo();
            localAPChannelInfo1.channelId = localJSONObject1.getString("name");
            localAPChannelInfo1.channelDiscount = String.valueOf(localJSONObject1.getInt("discount"));
            localAPChannelInfo1.channelInfo = localJSONObject1.getString("info");
            localAPChannelInfo1.channelState = 2;
            try
            {
              String str2 = localJSONObject1.getString("channelname");
              str1 = str2;
              localAPChannelInfo1.channelName = str1;
              localArrayList2.add(localAPChannelInfo1);
              m++;
            }
            catch (Exception localException2)
            {
              while (true)
                String str1 = "";
            }
          }
        }
        catch (JSONException localJSONException2)
        {
          localJSONException2.printStackTrace();
        }
      }
    }
  }

  public String disService()
  {
    return this.c;
  }

  public String express()
  {
    return this.e;
  }

  public String getAppMode()
  {
    return this.f;
  }

  public String getCount()
  {
    return this.g;
  }

  public String getGoodsDiscount()
  {
    return this.b;
  }

  public String getGoodsPrice()
  {
    return this.a;
  }

  public boolean getIsAmtCanChange()
  {
    return this.f.equals("2");
  }

  public String getMaxSaveNum()
  {
    return this.h;
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
    //   3: invokespecial 193	com/pay/http/APBaseHttpAns:onFinishAns	([BLcom/pay/http/APBaseHttpReq;)V
    //   6: new 147	java/lang/String
    //   9: dup
    //   10: aload_1
    //   11: invokespecial 196	java/lang/String:<init>	([B)V
    //   14: astore_3
    //   15: ldc 198
    //   17: new 200	java/lang/StringBuilder
    //   20: dup
    //   21: ldc 202
    //   23: invokespecial 204	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   26: aload_3
    //   27: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: invokestatic 214	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   36: new 64	org/json/JSONObject
    //   39: dup
    //   40: aload_3
    //   41: invokespecial 215	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   44: astore 4
    //   46: aload_0
    //   47: aload 4
    //   49: ldc 217
    //   51: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   54: invokevirtual 218	java/lang/String:toString	()Ljava/lang/String;
    //   57: invokestatic 223	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   60: putfield 226	com/pay/network/modle/APMobileBuyGoodsAns:resultCode	I
    //   63: aload_0
    //   64: aload 4
    //   66: ldc 228
    //   68: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   71: invokevirtual 218	java/lang/String:toString	()Ljava/lang/String;
    //   74: putfield 231	com/pay/network/modle/APMobileBuyGoodsAns:resultMsg	Ljava/lang/String;
    //   77: aload 4
    //   79: ldc 156
    //   81: invokevirtual 235	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   84: astore 6
    //   86: aload_0
    //   87: getfield 226	com/pay/network/modle/APMobileBuyGoodsAns:resultCode	I
    //   90: ifne +674 -> 764
    //   93: aload_0
    //   94: getfield 51	com/pay/network/modle/APMobileBuyGoodsAns:i	Lcom/pay/data/orderInfo/APOrderInfo;
    //   97: getfield 241	com/pay/data/orderInfo/APOrderInfo:buyInfo	Lcom/pay/data/buyInfo/APBaseBuyInfo;
    //   100: aload 6
    //   102: ldc 243
    //   104: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   107: putfield 248	com/pay/data/buyInfo/APBaseBuyInfo:offerName	Ljava/lang/String;
    //   110: aload_0
    //   111: getfield 51	com/pay/network/modle/APMobileBuyGoodsAns:i	Lcom/pay/data/orderInfo/APOrderInfo;
    //   114: getfield 241	com/pay/data/orderInfo/APOrderInfo:buyInfo	Lcom/pay/data/buyInfo/APBaseBuyInfo;
    //   117: aload 6
    //   119: ldc 250
    //   121: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   124: putfield 252	com/pay/data/buyInfo/APBaseBuyInfo:name	Ljava/lang/String;
    //   127: aload_0
    //   128: getfield 51	com/pay/network/modle/APMobileBuyGoodsAns:i	Lcom/pay/data/orderInfo/APOrderInfo;
    //   131: getfield 241	com/pay/data/orderInfo/APOrderInfo:buyInfo	Lcom/pay/data/buyInfo/APBaseBuyInfo;
    //   134: checkcast 254	com/pay/data/buyInfo/APBuyGoodsInfo
    //   137: astore 19
    //   139: aload_0
    //   140: aload 6
    //   142: ldc_w 256
    //   145: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   148: putfield 35	com/pay/network/modle/APMobileBuyGoodsAns:f	Ljava/lang/String;
    //   151: aload_0
    //   152: getfield 35	com/pay/network/modle/APMobileBuyGoodsAns:f	Ljava/lang/String;
    //   155: ldc 183
    //   157: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   160: ifeq +463 -> 623
    //   163: aload_0
    //   164: getfield 51	com/pay/network/modle/APMobileBuyGoodsAns:i	Lcom/pay/data/orderInfo/APOrderInfo;
    //   167: iconst_1
    //   168: putfield 260	com/pay/data/orderInfo/APOrderInfo:isNumCanChange	Z
    //   171: aload_0
    //   172: aload 6
    //   174: ldc_w 262
    //   177: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   180: putfield 25	com/pay/network/modle/APMobileBuyGoodsAns:a	Ljava/lang/String;
    //   183: aload_0
    //   184: aload 6
    //   186: ldc_w 264
    //   189: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   192: putfield 27	com/pay/network/modle/APMobileBuyGoodsAns:b	Ljava/lang/String;
    //   195: aload 19
    //   197: aload_0
    //   198: getfield 25	com/pay/network/modle/APMobileBuyGoodsAns:a	Ljava/lang/String;
    //   201: putfield 267	com/pay/data/buyInfo/APBuyGoodsInfo:price	Ljava/lang/String;
    //   204: aload 19
    //   206: aload_0
    //   207: getfield 27	com/pay/network/modle/APMobileBuyGoodsAns:b	Ljava/lang/String;
    //   210: putfield 270	com/pay/data/buyInfo/APBuyGoodsInfo:disPrice	Ljava/lang/String;
    //   213: aload 6
    //   215: ldc_w 272
    //   218: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   221: astore 27
    //   223: aload 27
    //   225: astore 23
    //   227: aload_0
    //   228: getfield 51	com/pay/network/modle/APMobileBuyGoodsAns:i	Lcom/pay/data/orderInfo/APOrderInfo;
    //   231: aload 23
    //   233: putfield 275	com/pay/data/orderInfo/APOrderInfo:expressChannel	Ljava/lang/String;
    //   236: aload 6
    //   238: ldc_w 277
    //   241: invokevirtual 281	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   244: ifeq +28 -> 272
    //   247: aload 6
    //   249: ldc_w 277
    //   252: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   255: ldc_w 283
    //   258: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   261: ifeq +448 -> 709
    //   264: aload_0
    //   265: getfield 57	com/pay/network/modle/APMobileBuyGoodsAns:j	Lcom/pay/data/userInfo/APUserInfo;
    //   268: iconst_1
    //   269: putfield 288	com/pay/data/userInfo/APUserInfo:isCFTUser	Z
    //   272: aload_0
    //   273: aload 6
    //   275: ldc_w 290
    //   278: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   281: putfield 37	com/pay/network/modle/APMobileBuyGoodsAns:g	Ljava/lang/String;
    //   284: aload_0
    //   285: getfield 51	com/pay/network/modle/APMobileBuyGoodsAns:i	Lcom/pay/data/orderInfo/APOrderInfo;
    //   288: aload_0
    //   289: getfield 37	com/pay/network/modle/APMobileBuyGoodsAns:g	Ljava/lang/String;
    //   292: putfield 293	com/pay/data/orderInfo/APOrderInfo:saveNum	Ljava/lang/String;
    //   295: aload 19
    //   297: aload 6
    //   299: ldc_w 295
    //   302: invokevirtual 145	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   305: putfield 298	com/pay/data/buyInfo/APBuyGoodsInfo:maxNum	I
    //   308: aload 6
    //   310: ldc_w 300
    //   313: invokevirtual 281	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   316: ifeq +28 -> 344
    //   319: aload 6
    //   321: ldc_w 300
    //   324: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   327: ldc_w 283
    //   330: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   333: ifeq +398 -> 731
    //   336: aload_0
    //   337: getfield 57	com/pay/network/modle/APMobileBuyGoodsAns:j	Lcom/pay/data/userInfo/APUserInfo;
    //   340: iconst_1
    //   341: putfield 303	com/pay/data/userInfo/APUserInfo:isKJUser	Z
    //   344: aload 6
    //   346: ldc_w 305
    //   349: invokevirtual 281	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   352: ifeq +28 -> 380
    //   355: aload 6
    //   357: ldc_w 305
    //   360: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   363: ldc_w 283
    //   366: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   369: ifeq +373 -> 742
    //   372: aload_0
    //   373: getfield 57	com/pay/network/modle/APMobileBuyGoodsAns:j	Lcom/pay/data/userInfo/APUserInfo;
    //   376: iconst_1
    //   377: putfield 308	com/pay/data/userInfo/APUserInfo:isBindQQ	Z
    //   380: aload 6
    //   382: ldc_w 310
    //   385: invokevirtual 281	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   388: ifeq +365 -> 753
    //   391: aload 6
    //   393: ldc_w 310
    //   396: invokevirtual 145	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   399: istore 26
    //   401: aload_0
    //   402: getfield 57	com/pay/network/modle/APMobileBuyGoodsAns:j	Lcom/pay/data/userInfo/APUserInfo;
    //   405: iload 26
    //   407: putfield 313	com/pay/data/userInfo/APUserInfo:accoutBalance	I
    //   410: aload 6
    //   412: invokestatic 315	com/pay/network/modle/APMobileBuyGoodsAns:b	(Lorg/json/JSONObject;)V
    //   415: aload 6
    //   417: invokestatic 317	com/pay/network/modle/APMobileBuyGoodsAns:a	(Lorg/json/JSONObject;)V
    //   420: invokestatic 45	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   423: aload_0
    //   424: getfield 57	com/pay/network/modle/APMobileBuyGoodsAns:j	Lcom/pay/data/userInfo/APUserInfo;
    //   427: invokevirtual 321	com/pay/tool/APDataInterface:setUserInfo	(Lcom/pay/data/userInfo/APUserInfo;)V
    //   430: aload 6
    //   432: ldc_w 323
    //   435: invokevirtual 145	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   438: iconst_1
    //   439: if_icmpne +389 -> 828
    //   442: invokestatic 328	com/pay/tool/APAppDataInterface:singleton	()Lcom/pay/tool/APAppDataInterface;
    //   445: iconst_1
    //   446: invokevirtual 332	com/pay/tool/APAppDataInterface:setIsOwnResearch	(Z)V
    //   449: invokestatic 328	com/pay/tool/APAppDataInterface:singleton	()Lcom/pay/tool/APAppDataInterface;
    //   452: iconst_1
    //   453: invokevirtual 332	com/pay/tool/APAppDataInterface:setIsOwnResearch	(Z)V
    //   456: aload 6
    //   458: ldc_w 334
    //   461: invokevirtual 235	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   464: astore 13
    //   466: aload 13
    //   468: ldc_w 336
    //   471: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   474: astore 14
    //   476: aload 13
    //   478: ldc_w 338
    //   481: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   484: astore 15
    //   486: aload 13
    //   488: ldc_w 340
    //   491: invokevirtual 145	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   494: istore 16
    //   496: aload 13
    //   498: ldc_w 342
    //   501: invokevirtual 145	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   504: istore 17
    //   506: aload 14
    //   508: ldc 23
    //   510: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   513: ifne +44 -> 557
    //   516: iload 17
    //   518: aload_0
    //   519: getfield 346	com/pay/network/modle/APMobileBuyGoodsAns:AesEncodeKey	[Ljava/lang/String;
    //   522: arraylength
    //   523: if_icmpge +34 -> 557
    //   526: aload 14
    //   528: aload_0
    //   529: getfield 346	com/pay/network/modle/APMobileBuyGoodsAns:AesEncodeKey	[Ljava/lang/String;
    //   532: iload 17
    //   534: aaload
    //   535: invokestatic 352	com/pay/tool/APToolAES:doDecode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   538: iconst_0
    //   539: iload 16
    //   541: invokevirtual 356	java/lang/String:substring	(II)Ljava/lang/String;
    //   544: astore 18
    //   546: invokestatic 45	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   549: invokevirtual 55	com/pay/tool/APDataInterface:getUserInfo	()Lcom/pay/data/userInfo/APUserInfo;
    //   552: aload 18
    //   554: putfield 359	com/pay/data/userInfo/APUserInfo:uinFromSvr	Ljava/lang/String;
    //   557: invokestatic 45	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   560: invokevirtual 55	com/pay/tool/APDataInterface:getUserInfo	()Lcom/pay/data/userInfo/APUserInfo;
    //   563: aload 15
    //   565: putfield 362	com/pay/data/userInfo/APUserInfo:uinTypeFromSvr	Ljava/lang/String;
    //   568: aload 6
    //   570: ldc_w 364
    //   573: invokevirtual 281	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   576: istore 10
    //   578: iload 10
    //   580: ifeq +18 -> 598
    //   583: aload_0
    //   584: getfield 51	com/pay/network/modle/APMobileBuyGoodsAns:i	Lcom/pay/data/orderInfo/APOrderInfo;
    //   587: aload 6
    //   589: ldc_w 364
    //   592: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   595: putfield 367	com/pay/data/orderInfo/APOrderInfo:sessionToken	Ljava/lang/String;
    //   598: aload 6
    //   600: ldc_w 369
    //   603: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   606: ldc_w 371
    //   609: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   612: ifeq +10 -> 622
    //   615: invokestatic 45	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   618: iconst_0
    //   619: invokevirtual 374	com/pay/tool/APDataInterface:setIsSendReport	(Z)V
    //   622: return
    //   623: aload_0
    //   624: getfield 51	com/pay/network/modle/APMobileBuyGoodsAns:i	Lcom/pay/data/orderInfo/APOrderInfo;
    //   627: iconst_0
    //   628: putfield 260	com/pay/data/orderInfo/APOrderInfo:isNumCanChange	Z
    //   631: goto -460 -> 171
    //   634: astore 20
    //   636: aload_0
    //   637: getfield 51	com/pay/network/modle/APMobileBuyGoodsAns:i	Lcom/pay/data/orderInfo/APOrderInfo;
    //   640: iconst_1
    //   641: putfield 260	com/pay/data/orderInfo/APOrderInfo:isNumCanChange	Z
    //   644: goto -473 -> 171
    //   647: astore 5
    //   649: aload 5
    //   651: invokevirtual 100	org/json/JSONException:printStackTrace	()V
    //   654: aload_0
    //   655: new 200	java/lang/StringBuilder
    //   658: dup
    //   659: ldc_w 376
    //   662: invokespecial 204	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   665: sipush 1003
    //   668: invokestatic 381	com/pay/http/APErrorCode:getErrorCode	(I)Ljava/lang/String;
    //   671: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   674: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   677: putfield 231	com/pay/network/modle/APMobileBuyGoodsAns:resultMsg	Ljava/lang/String;
    //   680: ldc_w 383
    //   683: aload 5
    //   685: invokevirtual 386	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   688: invokestatic 389	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   691: return
    //   692: astore 21
    //   694: aload_0
    //   695: ldc 23
    //   697: putfield 25	com/pay/network/modle/APMobileBuyGoodsAns:a	Ljava/lang/String;
    //   700: aload_0
    //   701: ldc 23
    //   703: putfield 27	com/pay/network/modle/APMobileBuyGoodsAns:b	Ljava/lang/String;
    //   706: goto -511 -> 195
    //   709: aload_0
    //   710: getfield 57	com/pay/network/modle/APMobileBuyGoodsAns:j	Lcom/pay/data/userInfo/APUserInfo;
    //   713: iconst_0
    //   714: putfield 288	com/pay/data/userInfo/APUserInfo:isCFTUser	Z
    //   717: goto -445 -> 272
    //   720: astore 24
    //   722: aload_0
    //   723: ldc 23
    //   725: putfield 37	com/pay/network/modle/APMobileBuyGoodsAns:g	Ljava/lang/String;
    //   728: goto -444 -> 284
    //   731: aload_0
    //   732: getfield 57	com/pay/network/modle/APMobileBuyGoodsAns:j	Lcom/pay/data/userInfo/APUserInfo;
    //   735: iconst_0
    //   736: putfield 303	com/pay/data/userInfo/APUserInfo:isKJUser	Z
    //   739: goto -395 -> 344
    //   742: aload_0
    //   743: getfield 57	com/pay/network/modle/APMobileBuyGoodsAns:j	Lcom/pay/data/userInfo/APUserInfo;
    //   746: iconst_0
    //   747: putfield 308	com/pay/data/userInfo/APUserInfo:isBindQQ	Z
    //   750: goto -370 -> 380
    //   753: aload_0
    //   754: getfield 57	com/pay/network/modle/APMobileBuyGoodsAns:j	Lcom/pay/data/userInfo/APUserInfo;
    //   757: iconst_0
    //   758: putfield 313	com/pay/data/userInfo/APUserInfo:accoutBalance	I
    //   761: goto -351 -> 410
    //   764: aload 4
    //   766: ldc_w 391
    //   769: invokevirtual 136	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   772: invokevirtual 218	java/lang/String:toString	()Ljava/lang/String;
    //   775: astore 7
    //   777: aload 7
    //   779: ldc 23
    //   781: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   784: ifne -354 -> 430
    //   787: aload_0
    //   788: new 200	java/lang/StringBuilder
    //   791: dup
    //   792: aload_0
    //   793: getfield 231	com/pay/network/modle/APMobileBuyGoodsAns:resultMsg	Ljava/lang/String;
    //   796: invokestatic 394	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   799: invokespecial 204	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   802: ldc_w 396
    //   805: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   808: aload 7
    //   810: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   813: ldc_w 398
    //   816: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   819: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   822: putfield 231	com/pay/network/modle/APMobileBuyGoodsAns:resultMsg	Ljava/lang/String;
    //   825: goto -395 -> 430
    //   828: invokestatic 328	com/pay/tool/APAppDataInterface:singleton	()Lcom/pay/tool/APAppDataInterface;
    //   831: iconst_0
    //   832: invokevirtual 332	com/pay/tool/APAppDataInterface:setIsOwnResearch	(Z)V
    //   835: goto -386 -> 449
    //   838: astore 8
    //   840: aload 8
    //   842: invokevirtual 100	org/json/JSONException:printStackTrace	()V
    //   845: goto -389 -> 456
    //   848: astore 9
    //   850: aload 9
    //   852: invokevirtual 100	org/json/JSONException:printStackTrace	()V
    //   855: goto -287 -> 568
    //   858: astore 12
    //   860: aload 12
    //   862: invokevirtual 100	org/json/JSONException:printStackTrace	()V
    //   865: goto -267 -> 598
    //   868: astore 11
    //   870: aload 11
    //   872: invokevirtual 100	org/json/JSONException:printStackTrace	()V
    //   875: return
    //   876: astore 25
    //   878: goto -570 -> 308
    //   881: astore 22
    //   883: ldc 23
    //   885: astore 23
    //   887: goto -660 -> 227
    //
    // Exception table:
    //   from	to	target	type
    //   139	171	634	java/lang/Exception
    //   623	631	634	java/lang/Exception
    //   36	139	647	org/json/JSONException
    //   139	171	647	org/json/JSONException
    //   171	195	647	org/json/JSONException
    //   195	213	647	org/json/JSONException
    //   213	223	647	org/json/JSONException
    //   227	272	647	org/json/JSONException
    //   272	284	647	org/json/JSONException
    //   284	295	647	org/json/JSONException
    //   295	308	647	org/json/JSONException
    //   308	344	647	org/json/JSONException
    //   344	380	647	org/json/JSONException
    //   380	410	647	org/json/JSONException
    //   410	430	647	org/json/JSONException
    //   568	578	647	org/json/JSONException
    //   623	631	647	org/json/JSONException
    //   636	644	647	org/json/JSONException
    //   694	706	647	org/json/JSONException
    //   709	717	647	org/json/JSONException
    //   722	728	647	org/json/JSONException
    //   731	739	647	org/json/JSONException
    //   742	750	647	org/json/JSONException
    //   753	761	647	org/json/JSONException
    //   764	825	647	org/json/JSONException
    //   840	845	647	org/json/JSONException
    //   850	855	647	org/json/JSONException
    //   860	865	647	org/json/JSONException
    //   870	875	647	org/json/JSONException
    //   171	195	692	java/lang/Exception
    //   272	284	720	java/lang/Exception
    //   430	449	838	org/json/JSONException
    //   449	456	838	org/json/JSONException
    //   828	835	838	org/json/JSONException
    //   456	557	848	org/json/JSONException
    //   557	568	848	org/json/JSONException
    //   583	598	858	org/json/JSONException
    //   598	622	868	org/json/JSONException
    //   295	308	876	java/lang/Exception
    //   213	223	881	java/lang/Exception
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

  public String vipServiceCode()
  {
    return this.d;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APMobileBuyGoodsAns
 * JD-Core Version:    0.6.0
 */
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
import com.pay.ui.payCenter.APSaveValueList;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APMobileBuyPageAns extends APBaseHttpAns
{
  private APOrderInfo a = APDataInterface.singleton().getOrderInfo();
  private APUserInfo b = APDataInterface.singleton().getUserInfo();

  public APMobileBuyPageAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  private static void a(JSONObject paramJSONObject)
  {
    try
    {
      JSONArray localJSONArray = paramJSONObject.getJSONArray("recommend_list");
      int i = localJSONArray.length();
      String[] arrayOfString = new String[i];
      for (int j = 0; ; j++)
      {
        if (j >= i)
        {
          APSaveValueList.singleton().setSaveValue(arrayOfString);
          return;
        }
        arrayOfString[j] = localJSONArray.get(j).toString();
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }

  private static void b(JSONObject paramJSONObject)
  {
    int i;
    int j;
    do
      try
      {
        JSONArray localJSONArray = paramJSONObject.getJSONArray("hf_amt_allow");
        i = localJSONArray.length();
        APHFAmountList.singleton().clear();
        j = 0;
        continue;
        String str = localJSONArray.get(j).toString();
        APHFAmountList.singleton().addHFAmountList(str);
        j++;
      }
      catch (JSONException localJSONException)
      {
        APHFAmountList.singleton().clear();
        localJSONException.printStackTrace();
        return;
      }
    while (j < i);
  }

  private static void c(JSONObject paramJSONObject)
  {
    try
    {
      localArrayList1 = new ArrayList();
      localJSONArray3 = paramJSONObject.getJSONArray("show_channel");
      int n = localJSONArray3.length();
      i1 = 0;
      if (i1 >= n)
        APChannelList.singleton().setShowCommChannelList(localArrayList1);
    }
    catch (JSONException localJSONException1)
    {
      try
      {
        label40: localJSONArray2 = paramJSONObject.getJSONArray("qqacct_save_channel");
        localArrayList3 = new ArrayList();
        int k = localJSONArray2.length();
        m = 0;
        if (m >= k)
          APChannelList.singleton().setMoreAccoutChannelList(localArrayList3);
      }
      catch (JSONException localJSONException1)
      {
        try
        {
          ArrayList localArrayList1;
          JSONArray localJSONArray3;
          int i1;
          JSONArray localJSONArray2;
          ArrayList localArrayList3;
          int m;
          label82: JSONArray localJSONArray1 = paramJSONObject.getJSONArray("channel");
          int i = localJSONArray1.length();
          ArrayList localArrayList2 = new ArrayList();
          int j = 0;
          while (true)
          {
            while (true)
            {
              while (true)
              {
                if (j < i)
                  break label346;
                APChannelList.singleton().setMoreCommChannelList(localArrayList2);
                return;
                APChannelInfo localAPChannelInfo3 = new APChannelInfo();
                JSONObject localJSONObject3 = (JSONObject)localJSONArray3.get(i1);
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
                  i1++;
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
              JSONObject localJSONObject2 = (JSONObject)localJSONArray2.get(m);
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
                m++;
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
            label346: JSONObject localJSONObject1 = (JSONObject)localJSONArray1.get(j);
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
              j++;
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
    //   3: invokespecial 167	com/pay/http/APBaseHttpAns:onFinishAns	([BLcom/pay/http/APBaseHttpReq;)V
    //   6: new 49	java/lang/String
    //   9: dup
    //   10: aload_1
    //   11: invokespecial 170	java/lang/String:<init>	([B)V
    //   14: astore_3
    //   15: ldc 172
    //   17: new 174	java/lang/StringBuilder
    //   20: dup
    //   21: ldc 176
    //   23: invokespecial 178	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   26: aload_3
    //   27: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: invokestatic 189	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   36: new 37	org/json/JSONObject
    //   39: dup
    //   40: aload_3
    //   41: invokespecial 190	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   44: astore 4
    //   46: aload_0
    //   47: aload 4
    //   49: ldc 192
    //   51: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   54: invokevirtual 193	java/lang/String:toString	()Ljava/lang/String;
    //   57: invokestatic 198	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   60: putfield 201	com/pay/network/modle/APMobileBuyPageAns:resultCode	I
    //   63: aload_0
    //   64: aload 4
    //   66: ldc 203
    //   68: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   71: invokevirtual 193	java/lang/String:toString	()Ljava/lang/String;
    //   74: putfield 206	com/pay/network/modle/APMobileBuyPageAns:resultMsg	Ljava/lang/String;
    //   77: aload 4
    //   79: ldc 142
    //   81: invokevirtual 210	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   84: astore 6
    //   86: aload_0
    //   87: getfield 201	com/pay/network/modle/APMobileBuyPageAns:resultCode	I
    //   90: ifne +856 -> 946
    //   93: aload_0
    //   94: getfield 24	com/pay/network/modle/APMobileBuyPageAns:a	Lcom/pay/data/orderInfo/APOrderInfo;
    //   97: getfield 216	com/pay/data/orderInfo/APOrderInfo:buyInfo	Lcom/pay/data/buyInfo/APBaseBuyInfo;
    //   100: checkcast 218	com/pay/data/buyInfo/APBuyGameInfo
    //   103: astore 19
    //   105: aload 19
    //   107: aload 6
    //   109: ldc 220
    //   111: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   114: putfield 223	com/pay/data/buyInfo/APBuyGameInfo:offerName	Ljava/lang/String;
    //   117: aload 19
    //   119: aload 6
    //   121: ldc 225
    //   123: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   126: putfield 227	com/pay/data/buyInfo/APBuyGameInfo:name	Ljava/lang/String;
    //   129: aload 19
    //   131: aload 6
    //   133: ldc 229
    //   135: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   138: putfield 232	com/pay/data/buyInfo/APBuyGameInfo:price	Ljava/lang/String;
    //   141: aload 6
    //   143: ldc 234
    //   145: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   148: ifeq +920 -> 1068
    //   151: aload 6
    //   153: ldc 234
    //   155: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   158: astore 42
    //   160: aload 42
    //   162: invokestatic 198	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   165: istore 44
    //   167: iload 44
    //   169: istore 20
    //   171: aload 19
    //   173: iload 20
    //   175: putfield 241	com/pay/data/buyInfo/APBuyGameInfo:minNum	I
    //   178: aload 6
    //   180: ldc 243
    //   182: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   185: astore 41
    //   187: aload 41
    //   189: astore 22
    //   191: aload_0
    //   192: getfield 24	com/pay/network/modle/APMobileBuyPageAns:a	Lcom/pay/data/orderInfo/APOrderInfo;
    //   195: aload 22
    //   197: putfield 246	com/pay/data/orderInfo/APOrderInfo:expressChannel	Ljava/lang/String;
    //   200: aload 6
    //   202: ldc 248
    //   204: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   207: ifeq +26 -> 233
    //   210: aload 6
    //   212: ldc 248
    //   214: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   217: ldc 250
    //   219: invokevirtual 253	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   222: ifeq +663 -> 885
    //   225: aload_0
    //   226: getfield 30	com/pay/network/modle/APMobileBuyPageAns:b	Lcom/pay/data/userInfo/APUserInfo;
    //   229: iconst_1
    //   230: putfield 259	com/pay/data/userInfo/APUserInfo:isCFTUser	Z
    //   233: aload 6
    //   235: ldc_w 261
    //   238: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   241: ifeq +27 -> 268
    //   244: aload 6
    //   246: ldc_w 261
    //   249: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   252: ldc 250
    //   254: invokevirtual 253	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   257: ifeq +657 -> 914
    //   260: aload_0
    //   261: getfield 30	com/pay/network/modle/APMobileBuyPageAns:b	Lcom/pay/data/userInfo/APUserInfo;
    //   264: iconst_1
    //   265: putfield 264	com/pay/data/userInfo/APUserInfo:isKJUser	Z
    //   268: aload 6
    //   270: ldc_w 266
    //   273: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   276: ifeq +27 -> 303
    //   279: aload 6
    //   281: ldc_w 266
    //   284: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   287: ldc 250
    //   289: invokevirtual 253	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   292: ifeq +633 -> 925
    //   295: aload_0
    //   296: getfield 30	com/pay/network/modle/APMobileBuyPageAns:b	Lcom/pay/data/userInfo/APUserInfo;
    //   299: iconst_1
    //   300: putfield 269	com/pay/data/userInfo/APUserInfo:isBindQQ	Z
    //   303: aload_0
    //   304: getfield 30	com/pay/network/modle/APMobileBuyPageAns:b	Lcom/pay/data/userInfo/APUserInfo;
    //   307: iconst_0
    //   308: putfield 272	com/pay/data/userInfo/APUserInfo:isFirstCharge	Z
    //   311: aload 6
    //   313: ldc_w 274
    //   316: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   319: ifeq +27 -> 346
    //   322: aload 6
    //   324: ldc_w 274
    //   327: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   330: ldc 250
    //   332: invokevirtual 253	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   335: ifeq +11 -> 346
    //   338: aload_0
    //   339: getfield 30	com/pay/network/modle/APMobileBuyPageAns:b	Lcom/pay/data/userInfo/APUserInfo;
    //   342: iconst_1
    //   343: putfield 272	com/pay/data/userInfo/APUserInfo:isFirstCharge	Z
    //   346: aload 6
    //   348: ldc_w 276
    //   351: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   354: ifeq +731 -> 1085
    //   357: aload 6
    //   359: ldc_w 276
    //   362: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   365: astore 38
    //   367: aload 38
    //   369: invokestatic 198	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   372: istore 40
    //   374: iload 40
    //   376: istore 23
    //   378: aload_0
    //   379: getfield 30	com/pay/network/modle/APMobileBuyPageAns:b	Lcom/pay/data/userInfo/APUserInfo;
    //   382: iload 23
    //   384: putfield 279	com/pay/data/userInfo/APUserInfo:accoutBalance	I
    //   387: invokestatic 18	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   390: astore 24
    //   392: aload 6
    //   394: ldc_w 281
    //   397: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   400: istore 25
    //   402: iload 25
    //   404: ifeq +16 -> 420
    //   407: aload 24
    //   409: aload 6
    //   411: ldc_w 281
    //   414: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   417: invokevirtual 284	com/pay/tool/APDataInterface:setMallUrl	(Ljava/lang/String;)V
    //   420: aload 6
    //   422: ldc_w 286
    //   425: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   428: istore 26
    //   430: iload 26
    //   432: ifeq +16 -> 448
    //   435: aload 24
    //   437: aload 6
    //   439: ldc_w 286
    //   442: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   445: invokevirtual 289	com/pay/tool/APDataInterface:setResultUrl	(Ljava/lang/String;)V
    //   448: aload 6
    //   450: ldc_w 291
    //   453: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   456: ifeq +604 -> 1060
    //   459: aload 6
    //   461: ldc_w 291
    //   464: invokevirtual 210	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   467: astore 33
    //   469: aload 33
    //   471: ldc_w 293
    //   474: invokevirtual 133	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   477: istore 34
    //   479: aload 33
    //   481: ldc_w 295
    //   484: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   487: astore 35
    //   489: iload 34
    //   491: ifgt +11 -> 502
    //   494: aload 35
    //   496: invokestatic 301	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   499: ifne +561 -> 1060
    //   502: invokestatic 307	com/pay/data/mp/APMPSendInfo:getInstance	()Lcom/pay/data/mp/APMPSendInfo;
    //   505: aload 33
    //   507: invokevirtual 310	com/pay/data/mp/APMPSendInfo:analyzeSimpleMpJson	(Lorg/json/JSONObject;)V
    //   510: iconst_1
    //   511: istore 27
    //   513: aload 6
    //   515: ldc_w 312
    //   518: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   521: ifeq +57 -> 578
    //   524: aload 6
    //   526: ldc_w 312
    //   529: invokevirtual 210	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   532: astore 32
    //   534: aload 32
    //   536: ldc_w 314
    //   539: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   542: ifne +14 -> 556
    //   545: aload 32
    //   547: ldc_w 316
    //   550: invokevirtual 238	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   553: ifeq +17 -> 570
    //   556: invokestatic 307	com/pay/data/mp/APMPSendInfo:getInstance	()Lcom/pay/data/mp/APMPSendInfo;
    //   559: aload 32
    //   561: invokevirtual 317	org/json/JSONObject:toString	()Ljava/lang/String;
    //   564: invokevirtual 320	com/pay/data/mp/APMPSendInfo:analyzeJson	(Ljava/lang/String;)V
    //   567: iconst_1
    //   568: istore 27
    //   570: invokestatic 307	com/pay/data/mp/APMPSendInfo:getInstance	()Lcom/pay/data/mp/APMPSendInfo;
    //   573: aload 32
    //   575: invokevirtual 323	com/pay/data/mp/APMPSendInfo:parseMpTitle	(Lorg/json/JSONObject;)V
    //   578: ldc 172
    //   580: new 174	java/lang/StringBuilder
    //   583: dup
    //   584: ldc_w 325
    //   587: invokespecial 178	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   590: iload 27
    //   592: invokevirtual 328	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   595: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   598: invokestatic 189	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   601: iload 27
    //   603: ifne +10 -> 613
    //   606: invokestatic 307	com/pay/data/mp/APMPSendInfo:getInstance	()Lcom/pay/data/mp/APMPSendInfo;
    //   609: aconst_null
    //   610: invokevirtual 320	com/pay/data/mp/APMPSendInfo:analyzeJson	(Ljava/lang/String;)V
    //   613: aload 6
    //   615: invokestatic 330	com/pay/network/modle/APMobileBuyPageAns:a	(Lorg/json/JSONObject;)V
    //   618: aload 6
    //   620: invokestatic 332	com/pay/network/modle/APMobileBuyPageAns:c	(Lorg/json/JSONObject;)V
    //   623: aload 6
    //   625: invokestatic 334	com/pay/network/modle/APMobileBuyPageAns:b	(Lorg/json/JSONObject;)V
    //   628: aload 6
    //   630: ldc_w 291
    //   633: invokevirtual 210	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   636: astore 29
    //   638: aload 29
    //   640: ldc_w 293
    //   643: invokevirtual 133	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   646: istore 30
    //   648: invokestatic 340	com/pay/tool/APMpDataInterface:getIntanceMpDataInterface	()Lcom/pay/tool/APMpDataInterface;
    //   651: iload 30
    //   653: invokevirtual 344	com/pay/tool/APMpDataInterface:setFirstMpInfo	(I)V
    //   656: aload 29
    //   658: ldc_w 295
    //   661: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   664: astore 31
    //   666: aload 31
    //   668: ifnull +25 -> 693
    //   671: aload 31
    //   673: ldc_w 346
    //   676: invokevirtual 253	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   679: ifne +14 -> 693
    //   682: aload 31
    //   684: invokestatic 340	com/pay/tool/APMpDataInterface:getIntanceMpDataInterface	()Lcom/pay/tool/APMpDataInterface;
    //   687: invokevirtual 350	com/pay/tool/APMpDataInterface:getMpInfoMap	()Ljava/util/TreeMap;
    //   690: invokestatic 356	com/pay/tool/APCommMethod:transformStrToMap	(Ljava/lang/String;Ljava/util/TreeMap;)V
    //   693: aload 6
    //   695: ldc_w 358
    //   698: invokevirtual 133	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   701: iconst_1
    //   702: if_icmpne +298 -> 1000
    //   705: invokestatic 363	com/pay/tool/APAppDataInterface:singleton	()Lcom/pay/tool/APAppDataInterface;
    //   708: iconst_1
    //   709: invokevirtual 367	com/pay/tool/APAppDataInterface:setIsOwnResearch	(Z)V
    //   712: invokestatic 363	com/pay/tool/APAppDataInterface:singleton	()Lcom/pay/tool/APAppDataInterface;
    //   715: iconst_1
    //   716: invokevirtual 367	com/pay/tool/APAppDataInterface:setIsOwnResearch	(Z)V
    //   719: aload 6
    //   721: ldc_w 369
    //   724: invokevirtual 210	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   727: astore 13
    //   729: aload 13
    //   731: ldc_w 371
    //   734: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   737: astore 14
    //   739: aload 13
    //   741: ldc_w 373
    //   744: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   747: astore 15
    //   749: aload 13
    //   751: ldc_w 375
    //   754: invokevirtual 133	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   757: istore 16
    //   759: aload 13
    //   761: ldc_w 377
    //   764: invokevirtual 133	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   767: istore 17
    //   769: aload 14
    //   771: ldc 160
    //   773: invokevirtual 253	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   776: ifne +44 -> 820
    //   779: iload 17
    //   781: aload_0
    //   782: getfield 381	com/pay/network/modle/APMobileBuyPageAns:AesEncodeKey	[Ljava/lang/String;
    //   785: arraylength
    //   786: if_icmpge +34 -> 820
    //   789: aload 14
    //   791: aload_0
    //   792: getfield 381	com/pay/network/modle/APMobileBuyPageAns:AesEncodeKey	[Ljava/lang/String;
    //   795: iload 17
    //   797: aaload
    //   798: invokestatic 387	com/pay/tool/APToolAES:doDecode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   801: iconst_0
    //   802: iload 16
    //   804: invokevirtual 391	java/lang/String:substring	(II)Ljava/lang/String;
    //   807: astore 18
    //   809: invokestatic 18	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   812: invokevirtual 28	com/pay/tool/APDataInterface:getUserInfo	()Lcom/pay/data/userInfo/APUserInfo;
    //   815: aload 18
    //   817: putfield 394	com/pay/data/userInfo/APUserInfo:uinFromSvr	Ljava/lang/String;
    //   820: invokestatic 18	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   823: invokevirtual 28	com/pay/tool/APDataInterface:getUserInfo	()Lcom/pay/data/userInfo/APUserInfo;
    //   826: aload 15
    //   828: putfield 397	com/pay/data/userInfo/APUserInfo:uinTypeFromSvr	Ljava/lang/String;
    //   831: aload 6
    //   833: ldc_w 399
    //   836: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   839: astore 12
    //   841: aload_0
    //   842: getfield 24	com/pay/network/modle/APMobileBuyPageAns:a	Lcom/pay/data/orderInfo/APOrderInfo;
    //   845: aload 12
    //   847: putfield 402	com/pay/data/orderInfo/APOrderInfo:sessionToken	Ljava/lang/String;
    //   850: aload 6
    //   852: ldc_w 404
    //   855: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   858: ldc_w 406
    //   861: invokevirtual 253	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   864: ifeq +10 -> 874
    //   867: invokestatic 18	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   870: iconst_0
    //   871: invokevirtual 409	com/pay/tool/APDataInterface:setIsSendReport	(Z)V
    //   874: invokestatic 18	com/pay/tool/APDataInterface:singleton	()Lcom/pay/tool/APDataInterface;
    //   877: aload_0
    //   878: getfield 30	com/pay/network/modle/APMobileBuyPageAns:b	Lcom/pay/data/userInfo/APUserInfo;
    //   881: invokevirtual 413	com/pay/tool/APDataInterface:setUserInfo	(Lcom/pay/data/userInfo/APUserInfo;)V
    //   884: return
    //   885: aload_0
    //   886: getfield 30	com/pay/network/modle/APMobileBuyPageAns:b	Lcom/pay/data/userInfo/APUserInfo;
    //   889: iconst_0
    //   890: putfield 259	com/pay/data/userInfo/APUserInfo:isCFTUser	Z
    //   893: goto -660 -> 233
    //   896: astore 5
    //   898: aload 5
    //   900: invokevirtual 72	org/json/JSONException:printStackTrace	()V
    //   903: ldc 172
    //   905: aload 5
    //   907: invokevirtual 416	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   910: invokestatic 419	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   913: return
    //   914: aload_0
    //   915: getfield 30	com/pay/network/modle/APMobileBuyPageAns:b	Lcom/pay/data/userInfo/APUserInfo;
    //   918: iconst_0
    //   919: putfield 264	com/pay/data/userInfo/APUserInfo:isKJUser	Z
    //   922: goto -654 -> 268
    //   925: aload_0
    //   926: getfield 30	com/pay/network/modle/APMobileBuyPageAns:b	Lcom/pay/data/userInfo/APUserInfo;
    //   929: iconst_0
    //   930: putfield 269	com/pay/data/userInfo/APUserInfo:isBindQQ	Z
    //   933: goto -630 -> 303
    //   936: astore 28
    //   938: aload 28
    //   940: invokevirtual 161	java/lang/Exception:printStackTrace	()V
    //   943: goto -250 -> 693
    //   946: aload 4
    //   948: ldc_w 421
    //   951: invokevirtual 123	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   954: invokevirtual 193	java/lang/String:toString	()Ljava/lang/String;
    //   957: astore 7
    //   959: aload 7
    //   961: ldc 160
    //   963: invokevirtual 253	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   966: ifne -273 -> 693
    //   969: aload_0
    //   970: new 174	java/lang/StringBuilder
    //   973: dup
    //   974: ldc_w 423
    //   977: invokespecial 178	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   980: aload 7
    //   982: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   985: ldc_w 425
    //   988: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   991: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   994: putfield 206	com/pay/network/modle/APMobileBuyPageAns:resultMsg	Ljava/lang/String;
    //   997: goto -304 -> 693
    //   1000: invokestatic 363	com/pay/tool/APAppDataInterface:singleton	()Lcom/pay/tool/APAppDataInterface;
    //   1003: iconst_0
    //   1004: invokevirtual 367	com/pay/tool/APAppDataInterface:setIsOwnResearch	(Z)V
    //   1007: goto -295 -> 712
    //   1010: astore 8
    //   1012: aload 8
    //   1014: invokevirtual 72	org/json/JSONException:printStackTrace	()V
    //   1017: goto -298 -> 719
    //   1020: astore 9
    //   1022: aload 9
    //   1024: invokevirtual 72	org/json/JSONException:printStackTrace	()V
    //   1027: goto -196 -> 831
    //   1030: astore 10
    //   1032: aload 10
    //   1034: invokevirtual 72	org/json/JSONException:printStackTrace	()V
    //   1037: goto -187 -> 850
    //   1040: astore 11
    //   1042: aload 11
    //   1044: invokevirtual 72	org/json/JSONException:printStackTrace	()V
    //   1047: goto -173 -> 874
    //   1050: astore 36
    //   1052: goto -604 -> 448
    //   1055: astore 37
    //   1057: goto -637 -> 420
    //   1060: iconst_0
    //   1061: istore 27
    //   1063: goto -550 -> 513
    //   1066: astore 43
    //   1068: iconst_0
    //   1069: istore 20
    //   1071: goto -900 -> 171
    //   1074: astore 21
    //   1076: ldc 160
    //   1078: astore 22
    //   1080: goto -889 -> 191
    //   1083: astore 39
    //   1085: iconst_0
    //   1086: istore 23
    //   1088: goto -710 -> 378
    //
    // Exception table:
    //   from	to	target	type
    //   36	160	896	org/json/JSONException
    //   160	167	896	org/json/JSONException
    //   171	178	896	org/json/JSONException
    //   178	187	896	org/json/JSONException
    //   191	233	896	org/json/JSONException
    //   233	268	896	org/json/JSONException
    //   268	303	896	org/json/JSONException
    //   303	346	896	org/json/JSONException
    //   346	367	896	org/json/JSONException
    //   367	374	896	org/json/JSONException
    //   378	402	896	org/json/JSONException
    //   407	420	896	org/json/JSONException
    //   420	430	896	org/json/JSONException
    //   435	448	896	org/json/JSONException
    //   448	489	896	org/json/JSONException
    //   494	502	896	org/json/JSONException
    //   502	510	896	org/json/JSONException
    //   513	556	896	org/json/JSONException
    //   556	567	896	org/json/JSONException
    //   570	578	896	org/json/JSONException
    //   578	601	896	org/json/JSONException
    //   606	613	896	org/json/JSONException
    //   613	628	896	org/json/JSONException
    //   628	666	896	org/json/JSONException
    //   671	693	896	org/json/JSONException
    //   874	884	896	org/json/JSONException
    //   885	893	896	org/json/JSONException
    //   914	922	896	org/json/JSONException
    //   925	933	896	org/json/JSONException
    //   938	943	896	org/json/JSONException
    //   946	997	896	org/json/JSONException
    //   1012	1017	896	org/json/JSONException
    //   1022	1027	896	org/json/JSONException
    //   1032	1037	896	org/json/JSONException
    //   1042	1047	896	org/json/JSONException
    //   628	666	936	java/lang/Exception
    //   671	693	936	java/lang/Exception
    //   693	712	1010	org/json/JSONException
    //   712	719	1010	org/json/JSONException
    //   1000	1007	1010	org/json/JSONException
    //   719	820	1020	org/json/JSONException
    //   820	831	1020	org/json/JSONException
    //   831	850	1030	org/json/JSONException
    //   850	874	1040	org/json/JSONException
    //   435	448	1050	java/lang/Exception
    //   407	420	1055	java/lang/Exception
    //   160	167	1066	java/lang/Exception
    //   178	187	1074	java/lang/Exception
    //   367	374	1083	java/lang/Exception
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
 * Qualified Name:     com.pay.network.modle.APMobileBuyPageAns
 * JD-Core Version:    0.6.0
 */
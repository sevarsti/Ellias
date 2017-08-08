package com.pay.network.modle;

import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import java.util.HashMap;

public class APGetIPListAns extends APBaseHttpAns
{
  public APGetIPListAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
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
    //   3: invokespecial 18	com/pay/http/APBaseHttpAns:onFinishAns	([BLcom/pay/http/APBaseHttpReq;)V
    //   6: new 20	java/lang/String
    //   9: dup
    //   10: aload_1
    //   11: invokespecial 23	java/lang/String:<init>	([B)V
    //   14: astore_3
    //   15: ldc 25
    //   17: new 27	java/lang/StringBuilder
    //   20: dup
    //   21: ldc 29
    //   23: invokespecial 32	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   26: aload_3
    //   27: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: invokestatic 46	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   36: new 48	org/json/JSONObject
    //   39: dup
    //   40: aload_3
    //   41: invokespecial 49	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   44: astore 4
    //   46: aload_0
    //   47: aload 4
    //   49: ldc 51
    //   51: invokevirtual 55	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   54: invokevirtual 56	java/lang/String:toString	()Ljava/lang/String;
    //   57: invokestatic 62	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   60: putfield 66	com/pay/network/modle/APGetIPListAns:resultCode	I
    //   63: aload_0
    //   64: getfield 66	com/pay/network/modle/APGetIPListAns:resultCode	I
    //   67: ifne +107 -> 174
    //   70: aload 4
    //   72: ldc 68
    //   74: invokevirtual 72	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   77: ifeq +46 -> 123
    //   80: aload 4
    //   82: ldc 68
    //   84: invokevirtual 76	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   87: astore 7
    //   89: new 78	java/util/HashMap
    //   92: dup
    //   93: invokespecial 81	java/util/HashMap:<init>	()V
    //   96: astore 8
    //   98: iconst_0
    //   99: istore 9
    //   101: aload 7
    //   103: invokevirtual 87	org/json/JSONArray:length	()I
    //   106: istore 10
    //   108: iload 9
    //   110: iload 10
    //   112: if_icmplt +12 -> 124
    //   115: invokestatic 93	com/pay/http/APIPList:getInstance	()Lcom/pay/http/APIPList;
    //   118: aload 8
    //   120: invokevirtual 97	com/pay/http/APIPList:updateIPList	(Ljava/util/HashMap;)V
    //   123: return
    //   124: aload 7
    //   126: iload 9
    //   128: invokevirtual 101	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   131: astore 11
    //   133: new 103	com/pay/http/APIPState
    //   136: dup
    //   137: invokespecial 104	com/pay/http/APIPState:<init>	()V
    //   140: astore 12
    //   142: aload 11
    //   144: ldc 106
    //   146: invokevirtual 55	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   149: astore 13
    //   151: aload 12
    //   153: aload 13
    //   155: putfield 109	com/pay/http/APIPState:ip	Ljava/lang/String;
    //   158: aload 8
    //   160: aload 13
    //   162: aload 12
    //   164: invokevirtual 113	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   167: pop
    //   168: iinc 9 1
    //   171: goto -70 -> 101
    //   174: aload_0
    //   175: aload 4
    //   177: ldc 115
    //   179: invokevirtual 55	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   182: putfield 118	com/pay/network/modle/APGetIPListAns:resultMsg	Ljava/lang/String;
    //   185: aload 4
    //   187: ldc 120
    //   189: invokevirtual 55	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   192: invokevirtual 56	java/lang/String:toString	()Ljava/lang/String;
    //   195: astore 6
    //   197: aload 6
    //   199: ldc 122
    //   201: invokevirtual 126	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   204: ifne -81 -> 123
    //   207: aload_0
    //   208: new 27	java/lang/StringBuilder
    //   211: dup
    //   212: ldc 128
    //   214: invokespecial 32	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   217: aload 6
    //   219: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: ldc 130
    //   224: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   230: putfield 118	com/pay/network/modle/APGetIPListAns:resultMsg	Ljava/lang/String;
    //   233: return
    //   234: astore 5
    //   236: aload_0
    //   237: new 27	java/lang/StringBuilder
    //   240: dup
    //   241: ldc 132
    //   243: invokespecial 32	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   246: sipush 1003
    //   249: invokestatic 138	com/pay/http/APErrorCode:getErrorCode	(I)Ljava/lang/String;
    //   252: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   258: putfield 118	com/pay/network/modle/APGetIPListAns:resultMsg	Ljava/lang/String;
    //   261: aload 5
    //   263: invokevirtual 141	org/json/JSONException:printStackTrace	()V
    //   266: return
    //   267: astore 15
    //   269: return
    //
    // Exception table:
    //   from	to	target	type
    //   36	98	234	org/json/JSONException
    //   101	108	234	org/json/JSONException
    //   115	123	234	org/json/JSONException
    //   124	168	234	org/json/JSONException
    //   174	233	234	org/json/JSONException
    //   115	123	267	java/lang/Exception
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
 * Qualified Name:     com.pay.network.modle.APGetIPListAns
 * JD-Core Version:    0.6.0
 */
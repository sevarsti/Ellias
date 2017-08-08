package com.pay.network.modle;

import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataInterface;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import com.pay.ui.channel.APChannelInfo;
import com.pay.ui.channel.APChannelList;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APGetExpressPayAns extends APBaseHttpAns
{
  private String a = "";
  private String b = "";
  private APOrderInfo c = APDataInterface.singleton().getOrderInfo();
  private APUserInfo d = new APUserInfo();

  public APGetExpressPayAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  private static void a(JSONObject paramJSONObject)
  {
    int i = 0;
    try
    {
      localArrayList1 = new ArrayList();
      localJSONArray3 = paramJSONObject.getJSONArray("show_channel");
      int n = localJSONArray3.length();
      i1 = 0;
      if (i1 >= n)
      {
        APChannelList.singleton().setShowCommChannelList(localArrayList1);
        label42: if (!paramJSONObject.has("qqacct_save_channel"));
      }
    }
    catch (JSONException localJSONException2)
    {
      try
      {
        localJSONArray2 = paramJSONObject.getJSONArray("qqacct_save_channel");
        localArrayList3 = new ArrayList();
        int k = localJSONArray2.length();
        m = 0;
        if (m >= k)
        {
          APChannelList.singleton().setMoreAccoutChannelList(localArrayList3);
          label93: if (!paramJSONObject.has("channel"));
        }
      }
      catch (JSONException localJSONException2)
      {
        try
        {
          ArrayList localArrayList1;
          JSONArray localJSONArray3;
          int i1;
          JSONArray localJSONArray2;
          ArrayList localArrayList3;
          int m;
          JSONArray localJSONArray1 = paramJSONObject.getJSONArray("channel");
          int j = localJSONArray1.length();
          ArrayList localArrayList2 = new ArrayList();
          while (true)
          {
            while (true)
            {
              while (true)
              {
                if (i < j)
                  break label364;
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
                  String str7 = localJSONObject3.getString("channelname");
                  str6 = str7;
                  localAPChannelInfo3.channelName = str6;
                  localArrayList1.add(localAPChannelInfo3);
                  i1++;
                }
                catch (Exception localException4)
                {
                  while (true)
                    String str6 = "";
                }
              }
              localException1 = localException1;
              localException1.printStackTrace();
              break label42;
              JSONObject localJSONObject2 = (JSONObject)localJSONArray2.get(m);
              APChannelInfo localAPChannelInfo2 = new APChannelInfo();
              localAPChannelInfo2.channelId = localJSONObject2.getString("name");
              localAPChannelInfo2.channelDiscount = String.valueOf(localJSONObject2.getInt("discount"));
              localAPChannelInfo2.channelState = 2;
              try
              {
                String str5 = localJSONObject2.getString("channelname");
                str4 = str5;
                localAPChannelInfo2.channelName = str4;
                localArrayList3.add(localAPChannelInfo2);
                m++;
              }
              catch (Exception localException3)
              {
                while (true)
                  String str4 = "";
              }
            }
            localJSONException2 = localJSONException2;
            localJSONException2.printStackTrace();
            break label93;
            label364: JSONObject localJSONObject1 = (JSONObject)localJSONArray1.get(i);
            String str1 = localJSONObject1.getString("name");
            APChannelInfo localAPChannelInfo1;
            if (((APDataInterface.singleton().getOrderInfo().saveType != 5) && ((APDataInterface.singleton().getOrderInfo().saveType != 4) || (APMonthDataInterface.singleton().getOpenType() != APMonthDataInterface.MonthOpenType.OpenType_NoRate))) || ((!str1.equals("hfpay")) && (!str1.equals("mcard"))))
            {
              localAPChannelInfo1 = new APChannelInfo();
              localAPChannelInfo1.channelId = localJSONObject1.getString("name");
              localAPChannelInfo1.channelDiscount = String.valueOf(localJSONObject1.getInt("discount"));
              localAPChannelInfo1.channelInfo = localJSONObject1.getString("info");
              localAPChannelInfo1.channelState = 2;
            }
            try
            {
              String str3 = localJSONObject1.getString("channelname");
              str2 = str3;
              localAPChannelInfo1.channelName = str2;
              localArrayList2.add(localAPChannelInfo1);
              i++;
            }
            catch (Exception localException2)
            {
              while (true)
                String str2 = "";
            }
          }
        }
        catch (JSONException localJSONException1)
        {
          localJSONException1.printStackTrace();
        }
      }
    }
  }

  public String getDefault()
  {
    return this.b;
  }

  public String getExpress()
  {
    return this.a;
  }

  public void onErrorAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onFinishAns(byte[] paramArrayOfByte, APBaseHttpReq paramAPBaseHttpReq)
  {
    super.onFinishAns(paramArrayOfByte, paramAPBaseHttpReq);
    String str1 = new String(paramArrayOfByte);
    APLog.i("APGetExpressPayAns", "resultData=" + str1);
    JSONObject localJSONObject;
    while (true)
    {
      try
      {
        localJSONObject = new JSONObject(str1);
        this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
        if (this.resultCode != 0)
          break;
        this.a = localJSONObject.getString("express_channel");
        this.c.expressChannel = this.a;
        if (!localJSONObject.has("need_change_key"))
          continue;
        if (localJSONObject.getInt("need_change_key") != 1)
          continue;
        APAppDataInterface.singleton().setChangeKey(true);
        if (localJSONObject.has("qq_acct_balance"))
        {
          int i = localJSONObject.getInt("qq_acct_balance");
          this.d.accoutBalance = i;
          a(localJSONObject);
          return;
          APAppDataInterface.singleton().setChangeKey(false);
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      this.d.accoutBalance = 0;
    }
    this.resultMsg = localJSONObject.getString("msg");
    String str2 = localJSONObject.getString("err_code").toString();
    if (!str2.equals(""))
      this.resultMsg = ("系统繁忙,请稍后再试\n(" + str2 + ")");
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
 * Qualified Name:     com.pay.network.modle.APGetExpressPayAns
 * JD-Core Version:    0.6.0
 */
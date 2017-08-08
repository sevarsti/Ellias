package com.pay.data.mp;

import android.text.TextUtils;
import com.pay.common.tool.APTypeChange;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.tool.APDataInterface;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APMPSendInfo
{
  private static APMPSendInfo c = null;
  private ArrayList a = null;
  private APMPSendItem b = null;
  private boolean d = false;
  private boolean e = false;
  private boolean f = false;
  private String g;
  private String h = "";

  private static int a(int paramInt, ArrayList paramArrayList)
  {
    if (paramArrayList != null)
    {
      int i = -1;
      int j = -1;
      int k = 0;
      while (true)
      {
        if (k >= paramArrayList.size())
          return j;
        APMPSendItem localAPMPSendItem = (APMPSendItem)paramArrayList.get(k);
        try
        {
          int m = localAPMPSendItem.sendGames.limitNum;
          if ((paramInt >= m) && (m > i))
          {
            i = m;
            j = k;
          }
          label61: k++;
        }
        catch (Exception localException)
        {
          break label61;
        }
      }
    }
    return -1;
  }

  private static APMPSendItem a(JSONObject paramJSONObject)
  {
    APMPSendItem localAPMPSendItem = new APMPSendItem();
    try
    {
      APMPGamesItem localAPMPGamesItem = new APMPGamesItem();
      if (paramJSONObject.has("num"))
        localAPMPGamesItem.limitNum = APTypeChange.StringToInt(paramJSONObject.getString("num"));
      if (paramJSONObject.has("send_rate"))
        localAPMPGamesItem.sendRate = APTypeChange.StringToInt(paramJSONObject.getString("send_rate"));
      if (paramJSONObject.has("send_num"))
        localAPMPGamesItem.sendNum = APTypeChange.StringToInt(paramJSONObject.getString("send_num"));
      if (paramJSONObject.has("send_type"))
        localAPMPGamesItem.sendType = paramJSONObject.getString("send_type");
      if (paramJSONObject.has("send_ext"))
        localAPMPGamesItem.sendExt = paramJSONObject.getString("send_ext");
      localAPMPSendItem.sendGames = localAPMPGamesItem;
      if (paramJSONObject.has("ex_send"))
      {
        ArrayList localArrayList = new ArrayList();
        JSONArray localJSONArray = paramJSONObject.getJSONArray("ex_send");
        for (int i = 0; ; i++)
        {
          if (i >= localJSONArray.length())
          {
            localAPMPSendItem.sendGoodsList = localArrayList;
            return localAPMPSendItem;
          }
          JSONObject localJSONObject = (JSONObject)localJSONArray.get(i);
          APMPGoodsItem localAPMPGoodsItem = new APMPGoodsItem();
          if (localJSONObject.has("name"))
            localAPMPGoodsItem.name = localJSONObject.getString("name");
          if (localJSONObject.has("num"))
            localAPMPGoodsItem.num = localJSONObject.getString("num");
          localArrayList.add(localAPMPGoodsItem);
        }
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localAPMPSendItem;
  }

  private static String a(ArrayList paramArrayList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramArrayList != null);
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayList.size())
      {
        if (localStringBuffer.length() > 0)
          localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
        return localStringBuffer.toString();
      }
      localStringBuffer.append(((APMPGoodsItem)paramArrayList.get(i)).name);
      localStringBuffer.append('×');
      localStringBuffer.append(((APMPGoodsItem)paramArrayList.get(i)).num);
      localStringBuffer.append(65292);
    }
  }

  public static APMPSendInfo getInstance()
  {
    monitorenter;
    try
    {
      if (c == null)
        c = new APMPSendInfo();
      APMPSendInfo localAPMPSendInfo = c;
      return localAPMPSendInfo;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void analyzeJson(String paramString)
  {
    int i = 0;
    this.g = paramString;
    if (this.a != null)
      this.a.clear();
    if (this.b != null)
      this.b.clear();
    this.h = "";
    this.d = false;
    this.e = false;
    this.f = false;
    while (true)
    {
      JSONArray localJSONArray;
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        if (!localJSONObject.has("first_mpinfo"))
          continue;
        this.b = a(localJSONObject.getJSONObject("first_mpinfo"));
        if (!this.b.getIsHasSend())
          continue;
        this.d = true;
        if (!localJSONObject.has("utp_mpinfo"))
          break;
        localJSONArray = localJSONObject.getJSONArray("utp_mpinfo");
        if (i >= localJSONArray.length())
        {
          return;
          this.d = false;
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      APMPSendItem localAPMPSendItem = a(localJSONArray.getJSONObject(i));
      if (localAPMPSendItem.getIsHasSend())
        this.e = true;
      if (localAPMPSendItem.getIsHasSendGoods())
        this.f = true;
      this.a.add(localAPMPSendItem);
      i++;
    }
  }

  public void analyzeSimpleMpJson(JSONObject paramJSONObject)
  {
    int i = 0;
    if (this.a != null)
      this.a.clear();
    if (this.b != null)
      this.b.clear();
    this.d = false;
    this.e = false;
    try
    {
      int j = paramJSONObject.getInt("firstsave_present_count");
      if (j > 0)
      {
        APMPGamesItem localAPMPGamesItem1 = new APMPGamesItem();
        localAPMPGamesItem1.limitNum = 0;
        localAPMPGamesItem1.sendNum = j;
        localAPMPGamesItem1.sendType = "2";
        this.b = new APMPSendItem();
        this.b.sendGames = localAPMPGamesItem1;
        this.d = true;
      }
      JSONArray localJSONArray = new JSONArray(paramJSONObject.getString("present_level"));
      while (true)
      {
        if (i >= localJSONArray.length() / 2)
          return;
        APMPSendItem localAPMPSendItem = new APMPSendItem();
        APMPGamesItem localAPMPGamesItem2 = new APMPGamesItem();
        localAPMPGamesItem2.limitNum = localJSONArray.getInt(i << 1);
        localAPMPGamesItem2.sendNum = localJSONArray.getInt(1 + (i << 1));
        localAPMPGamesItem2.sendType = "2";
        localAPMPSendItem.sendGames = localAPMPGamesItem2;
        this.a.add(localAPMPSendItem);
        this.e = true;
        i++;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public APMPSendItem getFirstMpInfo()
  {
    return this.b;
  }

  public String getFirstMpInfo(String paramString)
  {
    if (!TextUtils.isEmpty(this.h))
      return this.h;
    StringBuffer localStringBuffer = new StringBuffer();
    if ((this.b.sendGames != null) && (APDataInterface.singleton().getUserInfo().isFirstCharge))
    {
      localStringBuffer.append("首次充值");
      if (this.b.sendGames.limitNum <= 0)
        break label184;
      localStringBuffer.append(this.b.sendGames.limitNum);
    }
    while (true)
    {
      localStringBuffer.append(paramString);
      localStringBuffer.append(",额外送");
      if (this.b.sendGames.getSendGamesNum() > 0)
      {
        localStringBuffer.append(this.b.sendGames.getSendGamesNum());
        localStringBuffer.append(paramString);
      }
      if ((this.b.sendGoodsList != null) && (this.b.sendGoodsList.size() > 0))
      {
        if (this.b.sendGames.getSendGamesNum() > 0)
          localStringBuffer.append('+');
        localStringBuffer.append(getFirstSendGoods());
      }
      return localStringBuffer.toString();
      label184: localStringBuffer.append("任意数量");
    }
  }

  public int getFirstMpSendGameNum(int paramInt)
  {
    if (this.b != null)
      return this.b.getRealSendGamesNum(paramInt);
    return 0;
  }

  public String getFirstSendGoods()
  {
    return a(this.b.sendGoodsList);
  }

  public String getFirstSendGoods(int paramInt)
  {
    if ((this.b != null) && (this.b.sendGames != null) && (paramInt >= this.b.sendGames.limitNum))
      return a(this.b.sendGoodsList);
    return null;
  }

  public boolean getIsHasFirstMPInfo()
  {
    int i = -1;
    APOrderInfo localAPOrderInfo = APDataInterface.singleton().getOrderInfo();
    if (localAPOrderInfo != null)
      i = localAPOrderInfo.saveType;
    return (this.d) && (i == 0);
  }

  public boolean getIsHasUptoNumMpMPInfo()
  {
    int i = -1;
    APOrderInfo localAPOrderInfo = APDataInterface.singleton().getOrderInfo();
    if (localAPOrderInfo != null)
      i = localAPOrderInfo.saveType;
    return (this.e) && (i == 0);
  }

  public String getMPJson()
  {
    return this.g;
  }

  public String getMpTitle()
  {
    return this.h;
  }

  public int getSendGameLevelNum(int paramInt)
  {
    int i = a(paramInt, this.a);
    if ((i >= 0) && (i < this.a.size()))
      return ((APMPSendItem)this.a.get(i)).sendGames.limitNum;
    return 0;
  }

  public int getSendTotalGameCoins(int paramInt)
  {
    int i = getUptoNumMpSendGameNum(paramInt);
    if (APDataInterface.singleton().getUserInfo().isFirstCharge)
      i += getFirstMpSendGameNum(paramInt);
    return i;
  }

  public String getSendTotalGoods(int paramInt)
  {
    String str1 = getFirstSendGoods(paramInt);
    String str2 = getUptoNumMpSendGoods(paramInt);
    if ((!TextUtils.isEmpty(str1)) && (APDataInterface.singleton().getUserInfo().isFirstCharge))
    {
      if (!TextUtils.isEmpty(str2))
        str1 = str1 + "，" + str2;
      return str1;
    }
    return str2;
  }

  public ArrayList getTotalSendInfo()
  {
    ArrayList localArrayList = new ArrayList();
    APOrderInfo localAPOrderInfo = APDataInterface.singleton().getOrderInfo();
    int i = localAPOrderInfo.saveType;
    int j = APTypeChange.StringToInt(localAPOrderInfo.saveNum);
    if (i != 0)
      return localArrayList;
    int k = getInstance().getSendTotalGameCoins(j);
    if (k > 0)
    {
      APMPGoodsItem localAPMPGoodsItem1 = new APMPGoodsItem();
      localAPMPGoodsItem1.name = localAPOrderInfo.buyInfo.name;
      localAPMPGoodsItem1.num = String.valueOf(k);
      localArrayList.add(localAPMPGoodsItem1);
    }
    while (true)
    {
      int n;
      try
      {
        if ((!APDataInterface.singleton().getUserInfo().isFirstCharge) || (this.b == null) || (this.b.sendGames == null) || (j < this.b.sendGames.limitNum))
          continue;
        int i3 = 0;
        if (i3 < this.b.sendGoodsList.size())
          continue;
        if (this.a != null)
        {
          int m = a(j, this.a);
          if ((m >= 0) && (m < this.a.size()))
          {
            n = 0;
            if (n < ((APMPSendItem)this.a.get(m)).sendGoodsList.size())
              continue;
            break label449;
            APMPGoodsItem localAPMPGoodsItem4 = new APMPGoodsItem();
            localAPMPGoodsItem4.name = ((APMPGoodsItem)this.b.sendGoodsList.get(i3)).name;
            localAPMPGoodsItem4.num = ((APMPGoodsItem)this.b.sendGoodsList.get(i3)).num;
            localArrayList.add(localAPMPGoodsItem4);
            i3++;
            continue;
            APMPGoodsItem localAPMPGoodsItem2 = new APMPGoodsItem();
            localAPMPGoodsItem2.name = ((APMPGoodsItem)((APMPSendItem)this.a.get(m)).sendGoodsList.get(n)).name;
            localAPMPGoodsItem2.num = ((APMPGoodsItem)((APMPSendItem)this.a.get(m)).sendGoodsList.get(n)).num;
            int i1 = 0;
            if (i1 < localArrayList.size())
              continue;
            int i2 = 0;
            if (i2 != 0)
              break label451;
            localArrayList.add(localAPMPGoodsItem2);
            break label451;
            APMPGoodsItem localAPMPGoodsItem3 = (APMPGoodsItem)localArrayList.get(i1);
            if (!localAPMPGoodsItem3.name.equals(localAPMPGoodsItem2.name))
              continue;
            localAPMPGoodsItem3.num = String.valueOf(APTypeChange.StringToInt(localAPMPGoodsItem3.num) + APTypeChange.StringToInt(localAPMPGoodsItem2.num));
            i2 = 1;
            continue;
            i1++;
            continue;
          }
        }
      }
      catch (Exception localException)
      {
      }
      label449: return localArrayList;
      label451: n++;
    }
  }

  public ArrayList getUptoNumMpMpInfo()
  {
    return this.a;
  }

  public int getUptoNumMpSendGameNum(int paramInt)
  {
    ArrayList localArrayList = this.a;
    int i = a(paramInt, localArrayList);
    if ((i >= 0) && (i < localArrayList.size()))
      return ((APMPSendItem)localArrayList.get(i)).sendGames.getRealSendGamesNum(paramInt);
    return 0;
  }

  public String getUptoNumMpSendGoods(int paramInt)
  {
    if (this.a != null)
    {
      int i = a(paramInt, this.a);
      if ((i >= 0) && (i < this.a.size()))
        return a(((APMPSendItem)this.a.get(i)).sendGoodsList);
    }
    return null;
  }

  public ArrayList getUptoNumMpSendGoodsList(int paramInt)
  {
    if (this.a != null)
    {
      int i = a(paramInt, this.a);
      if ((i >= 0) && (i < this.a.size()))
        return ((APMPSendItem)this.a.get(i)).sendGoodsList;
    }
    return null;
  }

  public APMPSendItem getUptoNumMpSendItem(int paramInt)
  {
    if (this.a != null)
    {
      int i = a(paramInt, this.a);
      if ((i >= 0) && (i < this.a.size()))
        return (APMPSendItem)this.a.get(i);
    }
    return null;
  }

  public String getUptoNumSendInfo(int paramInt, String paramString)
  {
    int i = getInstance().getUptoNumMpSendGameNum(paramInt);
    String str1 = getInstance().getUptoNumMpSendGoods(paramInt);
    String str2 = "";
    int j = getInstance().getSendGameLevelNum(paramInt);
    String str3;
    if ((j > 0) && ((!TextUtils.isEmpty(str1)) || (i > 0)))
    {
      str3 = "充值" + paramString + "满" + j + "，赠送";
      if (i <= 0)
        break label157;
      str2 = str3 + paramString + "×" + i;
      if (!TextUtils.isEmpty(str1))
        str2 = str2 + "，" + str1;
    }
    return str2;
    label157: return str3 + str1;
  }

  public boolean isHasUptoSendGoods()
  {
    return this.f;
  }

  public boolean isHashSend()
  {
    APOrderInfo localAPOrderInfo = APDataInterface.singleton().getOrderInfo();
    if (localAPOrderInfo.saveType == 0)
    {
      int i = getInstance().getSendTotalGameCoins(APTypeChange.StringToInt(localAPOrderInfo.saveNum));
      String str = getInstance().getSendTotalGoods(APTypeChange.StringToInt(localAPOrderInfo.saveNum));
      if ((i > 0) || (!TextUtils.isEmpty(str)))
        return true;
    }
    return false;
  }

  public void parseMpTitle(JSONObject paramJSONObject)
  {
    this.h = "";
    this.g = paramJSONObject.toString();
    try
    {
      if (paramJSONObject.has("mpinfo_ex"))
      {
        String str3 = paramJSONObject.getJSONObject("mpinfo_ex").getString("title");
        if (!TextUtils.isEmpty(str3))
        {
          this.h = str3;
          return;
        }
      }
      if (paramJSONObject.has("first_mpinfo_ex"))
      {
        String str2 = paramJSONObject.getJSONObject("first_mpinfo_ex").getString("title");
        if (!TextUtils.isEmpty(str2))
        {
          this.h = str2;
          return;
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    if (paramJSONObject.has("utp_mpinfo_ex"))
    {
      String str1 = paramJSONObject.getJSONObject("utp_mpinfo_ex").getString("title");
      if (!TextUtils.isEmpty(str1))
        this.h = str1;
    }
  }

  public void setFirstMpInfo(APMPSendItem paramAPMPSendItem)
  {
    this.b = paramAPMPSendItem;
  }

  public void setUptoNumMpMpInfo(ArrayList paramArrayList)
  {
    this.a = paramArrayList;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.mp.APMPSendInfo
 * JD-Core Version:    0.6.0
 */
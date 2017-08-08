package com.pay.http;

import com.pay.common.tool.APLog;
import com.pay.network.modle.APDataReportAns;
import com.pay.network.modle.APDataReportReq;
import com.pay.network.modle.APGetExpressPayAns;
import com.pay.network.modle.APGetExpressPayReq;
import com.pay.network.modle.APGetIPListAns;
import com.pay.network.modle.APGetIPListReq;
import com.pay.network.modle.APGetKeyAns;
import com.pay.network.modle.APGetKeyReq;
import com.pay.network.modle.APGetTokenAns;
import com.pay.network.modle.APGetTokenReq;
import com.pay.network.modle.APGetVerifyCodeAns;
import com.pay.network.modle.APGetVerifyCodeReq;
import com.pay.network.modle.APGoodsTestOrderAns;
import com.pay.network.modle.APGoodsTestOrderReq;
import com.pay.network.modle.APGoodsTokenAns;
import com.pay.network.modle.APGoodsTokenReq;
import com.pay.network.modle.APMobileBuyGoodsAns;
import com.pay.network.modle.APMobileBuyGoodsReq;
import com.pay.network.modle.APMobileBuyPageAns;
import com.pay.network.modle.APMobileBuyPageReq;
import com.pay.network.modle.APMobileMonthInfoAns;
import com.pay.network.modle.APMobileMonthInfoReq;
import com.pay.network.modle.APQueryMarketResultAns;
import com.pay.network.modle.APQueryMarketResultReq;
import com.pay.network.modle.APQueryMcardStatusAns;
import com.pay.network.modle.APQueryMcardStatusReq;
import com.pay.network.modle.APSaveAns;
import com.pay.network.modle.APSaveReq;
import com.pay.network.modle.APSmsCodeAns;
import com.pay.network.modle.APSmsCodeReq;
import com.pay.network.modle.APhfPayAns;
import com.pay.network.modle.APhfPayReq;
import com.pay.tool.APDataReportManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class APNetworkManager
{
  public static final String HTTP_KEY_BUY_GOODS = "buygoods";
  public static final String HTTP_KEY_BUY_MONTH = "buymonth";
  public static final String HTTP_KEY_BUY_PAGE = "buypage";
  public static final String HTTP_KEY_DATAREPORT = "datareport";
  public static final String HTTP_KEY_GETEXPRESS = "getexpress";
  public static final String HTTP_KEY_GETIPLIST = "getIPlist";
  public static final String HTTP_KEY_GETSMSCODE = "getsmscode";
  public static final String HTTP_KEY_GET_KEY = "getkey";
  public static final String HTTP_KEY_GET_TOKEN = "gettoken";
  public static final String HTTP_KEY_GET_VERIFY = "getverify";
  public static final String HTTP_KEY_GOODSORDER = "goodsorder";
  public static final String HTTP_KEY_GOODSTOKEN = "goodsToken";
  public static final String HTTP_KEY_HFSTATUS = "hfstatus";
  public static final String HTTP_KEY_MCARDSTATUS = "mcardstatus";
  public static final String HTTP_KEY_QUERYMARKET = "marketresult";
  public static final String HTTP_KEY_SAVE = "save";
  private static APNetworkManager a = null;
  private HashMap b = new HashMap();

  public static void cancelPreRequest()
  {
    ArrayList localArrayList;
    Iterator localIterator;
    int i;
    if (a.b != null)
    {
      localArrayList = new ArrayList();
      localIterator = a.b.entrySet().iterator();
      if (localIterator.hasNext())
        break label67;
      i = localArrayList.size();
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        a.b.clear();
        return;
        label67: localArrayList.add((APBaseHttpReq)((Map.Entry)localIterator.next()).getValue());
        break;
      }
      ((APBaseHttpReq)localArrayList.get(j)).stopRequest();
    }
  }

  public static void cancelRequest(String paramString)
  {
    APBaseHttpReq localAPBaseHttpReq = (APBaseHttpReq)a.b.get(paramString);
    if (localAPBaseHttpReq != null)
    {
      localAPBaseHttpReq.stopRequest();
      a.b.remove(paramString);
    }
  }

  public static APNetworkManager getInstance()
  {
    if (a == null)
      a = new APNetworkManager();
    return a;
  }

  public static void release()
  {
    a = null;
  }

  public void dataReport(IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    ArrayList localArrayList = new ArrayList();
    int i = APDataReportManager.getInstance().getLogRecord(localArrayList);
    APLog.i("APDataReportManager", "num = " + i);
    int j = 0;
    while (true)
    {
      if (j >= i)
      {
        APDataReportManager.getInstance().clearData();
        return;
      }
      String str = (String)localArrayList.get(j);
      try
      {
        Class localClass = Class.forName("com.pay.test.APDataReportTest");
        if (localClass != null)
          localClass.getDeclaredMethod("setData", new Class[] { String.class }).invoke(localClass, new Object[] { str });
        APDataReportReq localAPDataReportReq = new APDataReportReq();
        localAPDataReportReq.setHttpAns(new APDataReportAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "datareport"));
        localAPDataReportReq.startService(str);
        j++;
      }
      catch (Exception localException)
      {
        while (true)
          APLog.w("APDataReportTest error", localException.toString());
      }
    }
  }

  public void dataReport(String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APDataReportReq localAPDataReportReq = new APDataReportReq();
    localAPDataReportReq.setHttpAns(new APDataReportAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "datareport"));
    localAPDataReportReq.startService(paramString);
  }

  public void getExpress(int paramInt, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APGetExpressPayReq localAPGetExpressPayReq = new APGetExpressPayReq();
    localAPGetExpressPayReq.setHttpAns(new APGetExpressPayAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "getexpress"));
    localAPGetExpressPayReq.startService(paramInt);
  }

  public void getIpList(IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APGetIPListReq localAPGetIPListReq = new APGetIPListReq();
    localAPGetIPListReq.setHttpAns(new APGetIPListAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "getIPlist"));
    localAPGetIPListReq.startService();
  }

  public void getKey(int paramInt1, int paramInt2, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APGetKeyReq localAPGetKeyReq = new APGetKeyReq();
    localAPGetKeyReq.setHttpAns(new APGetKeyAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "getkey"));
    localAPGetKeyReq.starService(paramInt1, paramInt2);
  }

  public void getSmsCode(String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APSmsCodeReq localAPSmsCodeReq = new APSmsCodeReq(paramString);
    localAPSmsCodeReq.setHttpAns(new APSmsCodeAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "getsmscode"));
    localAPSmsCodeReq.starService();
  }

  public void getToken(IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APGetTokenReq localAPGetTokenReq = new APGetTokenReq();
    localAPGetTokenReq.setHttpAns(new APGetTokenAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "gettoken"));
    localAPGetTokenReq.startService();
  }

  public void getVerifyCode(String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APGetVerifyCodeReq localAPGetVerifyCodeReq = new APGetVerifyCodeReq();
    localAPGetVerifyCodeReq.setHttpAns(new APGetVerifyCodeAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "getverify"));
    localAPGetVerifyCodeReq.startService(paramString);
  }

  public void goodsTestOrder(String paramString1, String paramString2, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APGoodsTestOrderReq localAPGoodsTestOrderReq = new APGoodsTestOrderReq();
    localAPGoodsTestOrderReq.setHttpAns(new APGoodsTestOrderAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "goodsorder"));
    localAPGoodsTestOrderReq.startService(paramString1, paramString2);
  }

  public void goodsToken(String paramString1, String paramString2, boolean paramBoolean, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APGoodsTokenReq localAPGoodsTokenReq = new APGoodsTokenReq();
    localAPGoodsTokenReq.setHttpAns(new APGoodsTokenAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "goodsToken"));
    localAPGoodsTokenReq.startService(paramString1, paramString2, paramBoolean);
  }

  public void mobileBuyGoods(String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APMobileBuyGoodsReq localAPMobileBuyGoodsReq = new APMobileBuyGoodsReq();
    localAPMobileBuyGoodsReq.setHttpAns(new APMobileBuyGoodsAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "buygoods"));
    localAPMobileBuyGoodsReq.startService(paramString);
  }

  public void mobileBuyPage(IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APMobileBuyPageReq localAPMobileBuyPageReq = new APMobileBuyPageReq();
    localAPMobileBuyPageReq.setHttpAns(new APMobileBuyPageAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "buypage"));
    localAPMobileBuyPageReq.starService();
  }

  public void mobileMonthInfo(String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APMobileMonthInfoReq localAPMobileMonthInfoReq = new APMobileMonthInfoReq();
    localAPMobileMonthInfoReq.setHttpAns(new APMobileMonthInfoAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "buymonth"));
    localAPMobileMonthInfoReq.startService(paramString);
  }

  public void queryHFStatus(String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APhfPayReq localAPhfPayReq = new APhfPayReq();
    localAPhfPayReq.setHttpAns(new APhfPayAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "hfstatus"));
    localAPhfPayReq.startService(paramString);
  }

  public void queryMarketResult(IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APQueryMarketResultReq localAPQueryMarketResultReq = new APQueryMarketResultReq();
    localAPQueryMarketResultReq.setHttpAns(new APQueryMarketResultAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "marketresult"));
    localAPQueryMarketResultReq.startService();
  }

  public void queryMcardStatus(String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APQueryMcardStatusReq localAPQueryMcardStatusReq = new APQueryMcardStatusReq();
    localAPQueryMcardStatusReq.setHttpAns(new APQueryMcardStatusAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "mcardstatus"));
    localAPQueryMcardStatusReq.startService(paramString);
  }

  public void saveByAcct(int paramInt1, int paramInt2, String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APSaveReq localAPSaveReq = new APSaveReq(paramInt1);
    localAPSaveReq.setHttpAns(new APSaveAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "save"));
    localAPSaveReq.startServiceAcct(paramInt1, paramInt2, paramString);
  }

  public void saveByAcct(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APSaveReq localAPSaveReq = new APSaveReq(paramInt1);
    localAPSaveReq.setHttpAns(new APSaveAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "save"));
    localAPSaveReq.startServiceAcct(paramInt1, paramInt2, paramString1, paramString2, paramString3);
  }

  public void saveByGoldCoupons(int paramInt, String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APSaveReq localAPSaveReq = new APSaveReq(paramInt);
    localAPSaveReq.setHttpAns(new APSaveAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "save"));
    localAPSaveReq.startServiceGoldCoupons(paramInt, paramString);
  }

  public void saveByHF(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APSaveReq localAPSaveReq = new APSaveReq(paramInt);
    localAPSaveReq.setHttpAns(new APSaveAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "save"));
    localAPSaveReq.startServiceHF(paramInt, paramString1, paramString2, paramString3, paramString4);
  }

  public void saveByMcard(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5, String paramString6, String paramString7, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APSaveReq localAPSaveReq = new APSaveReq(paramInt);
    localAPSaveReq.setHttpAns(new APSaveAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "save"));
    localAPSaveReq.startServiceMcard(paramString1, paramString2, paramString3, paramString4, paramInt, paramString5, paramString6, paramString7);
  }

  public void saveByQQCard(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APSaveReq localAPSaveReq = new APSaveReq(paramInt);
    localAPSaveReq.setHttpAns(new APSaveAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "save"));
    localAPSaveReq.startServiceQQCard(paramString1, paramString2, paramString3, paramInt, paramString4, paramString5);
  }

  public void saveByTenpay(int paramInt1, int paramInt2, String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APSaveReq localAPSaveReq = new APSaveReq(paramInt2);
    localAPSaveReq.setHttpAns(new APSaveAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "save"));
    localAPSaveReq.startServiceTenpay(paramInt1, paramInt2, paramString);
  }

  public void saveByWeChat(int paramInt, String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APSaveReq localAPSaveReq = new APSaveReq(paramInt);
    localAPSaveReq.setHttpAns(new APSaveAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "save"));
    localAPSaveReq.startServiceWeChat(paramInt, paramString);
  }

  public void saveByYB(int paramInt, String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APSaveReq localAPSaveReq = new APSaveReq(paramInt);
    localAPSaveReq.setHttpAns(new APSaveAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, this.b, "save"));
    localAPSaveReq.startServiceYB(paramInt, paramString);
  }

  public void stopNetWorkBykey(String paramString)
  {
    APBaseHttpReq localAPBaseHttpReq = (APBaseHttpReq)this.b.get(paramString);
    if (localAPBaseHttpReq != null)
      localAPBaseHttpReq.stopRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APNetworkManager
 * JD-Core Version:    0.6.0
 */
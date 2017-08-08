package com.pay.network.modle;

import com.pay.http.APBaseHttpParam;
import com.pay.http.APHttpReqPost;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import java.util.ArrayList;
import java.util.HashMap;

public class APDataReportReq extends APHttpReqPost
{
  public APDataReportReq()
  {
    String str1 = APAppDataInterface.singleton().getOfferid();
    String str2 = String.format("/v1/r/%s/log_data", new Object[] { str1 });
    String str3 = String.format("/v1/r/%s/log_data", new Object[] { str1 });
    String str4 = String.format("/v1/800/%s/log_data", new Object[] { str1 });
    Object localObject = "";
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = APAppDataInterface.singleton().getCustomCgi();
      arrayOfObject[1] = str1;
      String str5 = String.format("/v1/%s/%s/log_data", arrayOfObject);
      localObject = str5;
      label92: setUrl((String)localObject, str2, str3, str4);
      return;
    }
    catch (Exception localException)
    {
      break label92;
    }
  }

  public void startService()
  {
    ArrayList localArrayList;
    int i;
    if (APDataInterface.singleton().getIsSendReport())
    {
      localArrayList = new ArrayList();
      i = APDataReportManager.getInstance().getLogRecord(localArrayList);
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        APDataReportManager.getInstance().clearData();
        startRequest();
        return;
      }
      String str = (String)localArrayList.get(j);
      if (str.equals(""))
        continue;
      this.httpParam.reqParam.clear();
      this.httpParam.reqParam.put(str, "");
      startRequest();
    }
  }

  public void startService(String paramString)
  {
    if (!APDataInterface.singleton().getIsSendReport());
    do
      return;
    while (paramString.equals(""));
    this.httpParam.reqParam.clear();
    this.httpParam.reqParam.put(paramString, "");
    startRequest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APDataReportReq
 * JD-Core Version:    0.6.0
 */
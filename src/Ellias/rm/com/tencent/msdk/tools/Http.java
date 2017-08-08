package com.tencent.msdk.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

public class Http
{
  public static int get(String paramString, HashMap<String, String> paramHashMap)
  {
    Logger.d("Http get");
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = paramHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localLinkedList.add(new BasicNameValuePair((String)localEntry.getKey(), (String)localEntry.getValue()));
    }
    HttpResponse localHttpResponse;
    try
    {
      String str1 = URLEncodedUtils.format(localLinkedList, "UTF-8");
      HttpGet localHttpGet = new HttpGet(paramString + "?" + str1);
      localHttpGet.setHeader("Connection", "close");
      localHttpGet.setHeader("Cache-Control", "no-cache");
      localHttpGet.getParams().setBooleanParameter("http.protocol.expect-continue", false);
      localHttpResponse = new DefaultHttpClient().execute(localHttpGet);
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localHttpResponse.getEntity().getContent()));
      while (true)
      {
        String str2 = localBufferedReader.readLine();
        if (str2 == null)
          break;
        Logger.d("WHTTP Get: " + str2);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return -1;
    }
    int i = localHttpResponse.getStatusLine().getStatusCode();
    return i;
  }

  public static int post(String paramString, HashMap<String, String> paramHashMap)
  {
    Logger.d("Http post");
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = paramHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localLinkedList.add(new BasicNameValuePair((String)localEntry.getKey(), (String)localEntry.getValue()));
    }
    HttpResponse localHttpResponse;
    try
    {
      HttpPost localHttpPost = new HttpPost(paramString);
      localHttpPost.setEntity(new UrlEncodedFormEntity(localLinkedList, "utf-8"));
      localHttpPost.setHeader("Connection", "close");
      localHttpPost.setHeader("Cache-Control", "no-cache");
      localHttpPost.getParams().setBooleanParameter("http.protocol.expect-continue", false);
      localHttpResponse = new DefaultHttpClient().execute(localHttpPost);
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localHttpResponse.getEntity().getContent()));
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          break;
        Logger.d("HTTP Post: " + str);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return -1;
    }
    int i = localHttpResponse.getStatusLine().getStatusCode();
    return i;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.Http
 * JD-Core Version:    0.6.0
 */
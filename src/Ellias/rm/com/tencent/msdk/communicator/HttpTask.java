package com.tencent.msdk.communicator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.tencent.msdk.tea.TEACoding;
import com.tencent.msdk.tools.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class HttpTask extends AsyncTask<MHttpRequest, Integer, MHttpResponse>
{
  private int TIME_OUT = 15000;
  private Handler handler;
  private TEACoding teaCode = new TEACoding("msdkmsdkmsdkmsdk".getBytes());
  private int what;

  public HttpTask(Handler paramHandler, int paramInt)
  {
    if (paramHandler == null)
      Logger.d("hanlder is null");
    this.handler = paramHandler;
    this.what = paramInt;
  }

  private MHttpResponse clientParamError(String paramString)
  {
    return new MHttpResponse(1006, paramString, null);
  }

  private MHttpResponse processHttpResponse(HttpResponse paramHttpResponse)
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    int i = (int)localHttpEntity.getContentLength();
    Logger.d("getContentLength is：" + i);
    if (i < 0)
    {
      Logger.w("response is null");
      return null;
    }
    while (true)
    {
      byte[] arrayOfByte2;
      try
      {
        if (!HttpRequestManager.isEncode.booleanValue())
          break label424;
        arrayOfByte1 = new byte[i];
        InputStream localInputStream = localHttpEntity.getContent();
        int j = 0;
        if (j >= i)
          continue;
        Logger.d("getContentLength: readContent[" + String.valueOf(j) + "]" + arrayOfByte1[j]);
        j += localInputStream.read(arrayOfByte1, j, i - j);
        Logger.d("getContentLength: get content length:" + i + ";get byte length:" + j);
        Logger.d("getContentLength: readContent[" + String.valueOf(j - 2) + "]" + arrayOfByte1[(j - 2)]);
        Logger.d("getContentLength: readContent[" + String.valueOf(j - 1) + "]" + arrayOfByte1[(j - 1)]);
        continue;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        byte[] arrayOfByte1;
        localIllegalStateException.printStackTrace();
        return serverErrorRsp(3002, "IllegalStateException " + localIllegalStateException.getMessage());
        arrayOfByte2 = this.teaCode.decode(arrayOfByte1);
        if (arrayOfByte2 == null)
        {
          localObject = "";
          Logger.w("entityAfterDecode is null");
          MHttpResponse localMHttpResponse = new MHttpResponse(paramHttpResponse.getStatusLine().getStatusCode(), "", (String)localObject);
          return localMHttpResponse;
        }
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        return serverErrorRsp(3003, "IOException " + localIOException.getMessage());
      }
      Object localObject = new String(arrayOfByte2, "UTF-8");
      Logger.d("strResult:" + (String)localObject);
      continue;
      label424: String str = EntityUtils.toString(paramHttpResponse.getEntity(), "UTF-8");
      localObject = str;
    }
  }

  private MHttpResponse serverErrorRsp(int paramInt, String paramString)
  {
    return new MHttpResponse(paramInt, paramString, null);
  }

  private MHttpResponse serverErrorRsp(String paramString)
  {
    return serverErrorRsp(3000, paramString);
  }

  protected MHttpResponse doInBackground(MHttpRequest[] paramArrayOfMHttpRequest)
  {
    if (paramArrayOfMHttpRequest.length == 0)
    {
      Logger.d("no params");
      return clientParamError("no params");
    }
    MHttpRequest localMHttpRequest = paramArrayOfMHttpRequest[0];
    if (localMHttpRequest == null)
    {
      Logger.d("HttpRequest is null");
      return clientParamError("HttpRequest is null");
    }
    try
    {
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpConnectionParams.setConnectionTimeout(localDefaultHttpClient.getParams(), this.TIME_OUT);
      int i = 1.$SwitchMap$com$tencent$msdk$communicator$MHttpRequest$HttpMethod[localMHttpRequest.getMethod().ordinal()];
      Object localObject = null;
      switch (i)
      {
      default:
      case 1:
      case 2:
      }
      while (true)
      {
        return processHttpResponse(localDefaultHttpClient.execute((HttpUriRequest)localObject));
        localObject = new HttpGet(localMHttpRequest.getUrl());
        continue;
        localObject = new HttpPost(localMHttpRequest.getUrl());
        ((HttpRequestBase)localObject).setHeader("Content-Type", "application/x-www-form-urlencoded");
        if (HttpRequestManager.isEncode.booleanValue())
        {
          ((HttpRequestBase)localObject).setHeader("Content-Encrypt", "msdktea");
          ((HttpRequestBase)localObject).setHeader("Accept-Encrypt", "msdktea");
        }
        ((HttpPost)localObject).setEntity(new ByteArrayEntity(localMHttpRequest.getBody()));
        ((HttpPost)localObject).getParams().setBooleanParameter("http.protocol.expect-continue", false);
        HttpProtocolParams.setUseExpectContinue(localDefaultHttpClient.getParams(), false);
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      Logger.w("IllegalStateException, url: " + localMHttpRequest.getUrl());
      localIllegalStateException.printStackTrace();
      return serverErrorRsp(3002, "IllegalStateException" + localIllegalStateException.getMessage());
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      Logger.w("IllegalArgumentException, url: " + localMHttpRequest.getUrl());
      localIllegalArgumentException.printStackTrace();
      return serverErrorRsp(3004, "IllegalArgumentException" + localIllegalArgumentException.getMessage());
    }
    catch (SocketException localSocketException)
    {
      Logger.w("SocketException, url: " + localMHttpRequest.getUrl());
      localSocketException.printStackTrace();
      return serverErrorRsp(3005, "SocketException" + localSocketException.getMessage());
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      Logger.w("ClientProtocolException, url: " + localMHttpRequest.getUrl());
      localClientProtocolException.printStackTrace();
      return serverErrorRsp(3006, "ClientProtocolException" + localClientProtocolException.getMessage());
    }
    catch (ConnectTimeoutException localConnectTimeoutException)
    {
      Logger.w("ConnectTimeoutException, url: " + localMHttpRequest.getUrl());
      localConnectTimeoutException.printStackTrace();
      return serverErrorRsp(3001, "ConnectTimeoutException" + localConnectTimeoutException.getMessage());
    }
    catch (UnknownHostException localUnknownHostException)
    {
      Logger.w("UnknownHostException, url: " + localMHttpRequest.getUrl());
      localUnknownHostException.printStackTrace();
      return serverErrorRsp(3007, "UnknownHostException:" + localUnknownHostException.getMessage());
    }
    catch (Exception localException)
    {
      Logger.w("UnknownException, url: " + localMHttpRequest.getUrl());
      localException.printStackTrace();
    }
    return (MHttpResponse)serverErrorRsp("UnknownException" + localException.getMessage());
  }

  protected void onPostExecute(MHttpResponse paramMHttpResponse)
  {
    super.onPostExecute(paramMHttpResponse);
    if (paramMHttpResponse == null)
    {
      Logger.d("network return null!!!");
      paramMHttpResponse = new MHttpResponse(1002, "response no params", null);
    }
    if (paramMHttpResponse.getBody() != null)
      Logger.d("result body is" + new String(paramMHttpResponse.getBody()));
    while (true)
    {
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("http_rsp", paramMHttpResponse);
      if (this.handler != null)
      {
        Message localMessage = Message.obtain(this.handler, this.what, paramMHttpResponse.getStatus(), 0);
        localMessage.setData(localBundle);
        this.handler.sendMessage(localMessage);
      }
      return;
      Logger.d("result body is null");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.communicator.HttpTask
 * JD-Core Version:    0.6.0
 */
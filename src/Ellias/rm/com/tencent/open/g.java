package com.tencent.open;

import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public class g
  implements IRequestListener
{
  private IUiListener a;

  public g(IUiListener paramIUiListener)
  {
    this.a = paramIUiListener;
  }

  private void a(Exception paramException)
  {
    if (this.a != null)
      this.a.onError(new UiError(100, paramException.getMessage(), null));
  }

  public void onComplete(JSONObject paramJSONObject)
  {
    if (this.a != null)
      this.a.onComplete(paramJSONObject);
  }

  public void onConnectTimeoutException(ConnectTimeoutException paramConnectTimeoutException)
  {
    a(paramConnectTimeoutException);
  }

  public void onHttpStatusException(HttpUtils.HttpStatusException paramHttpStatusException)
  {
    a(paramHttpStatusException);
  }

  public void onIOException(IOException paramIOException)
  {
    a(paramIOException);
  }

  public void onJSONException(JSONException paramJSONException)
  {
    a(paramJSONException);
  }

  public void onMalformedURLException(MalformedURLException paramMalformedURLException)
  {
    a(paramMalformedURLException);
  }

  public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException paramNetworkUnavailableException)
  {
    a(paramNetworkUnavailableException);
  }

  public void onSocketTimeoutException(SocketTimeoutException paramSocketTimeoutException)
  {
    a(paramSocketTimeoutException);
  }

  public void onUnknowException(Exception paramException)
  {
    a(paramException);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.g
 * JD-Core Version:    0.6.0
 */
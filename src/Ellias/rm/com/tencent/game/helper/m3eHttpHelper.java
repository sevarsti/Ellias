package com.tencent.game.helper;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class m3eHttpHelper
{
  private static String TAG = "m3eHttpHelper++++:";
  private static m3eHttpHelper instance = new m3eHttpHelper();
  private Activity mActivity;

  public static m3eHttpHelper getInstance()
  {
    return instance;
  }

  private static native void nativeGetMSGFinish(HttpData paramHttpData, int paramInt);

  private static native void nativePostMSGFinish(HttpData paramHttpData);

  public void closeHttpClient(HttpData paramHttpData)
  {
    try
    {
      if (paramHttpData.stream != null)
      {
        paramHttpData.stream.close();
        paramHttpData.stream = null;
      }
      paramHttpData.data = null;
      paramHttpData.url = null;
      paramHttpData.parent = 0;
      return;
    }
    catch (IOException localIOException)
    {
      Log.d(TAG, "Close HttpClient is failed. ", localIOException);
    }
  }

  public HttpData getRequestHttp(String paramString, byte paramByte, int paramInt)
  {
    HttpData localHttpData = new HttpData();
    localHttpData.url = paramString;
    localHttpData.parent = paramInt;
    this.mActivity.runOnUiThread(new StartTask(localHttpData, paramByte));
    return localHttpData;
  }

  public HttpData postRequsetHttp(String paramString, byte paramByte, int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    if ((paramArrayOfByte == null) || (paramInt2 < 1))
      return null;
    HttpData localHttpData = new HttpData();
    localHttpData.url = paramString;
    localHttpData.parent = paramInt1;
    localHttpData.data = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, 0, localHttpData.data, 0, paramInt2);
    this.mActivity.runOnUiThread(new StartTask(localHttpData, paramByte));
    return localHttpData;
  }

  public void setActivity(Activity paramActivity)
  {
    this.mActivity = paramActivity;
  }

  public class HttpData
  {
    public byte[] data = null;
    public int parent = 0;
    public int status = 0;
    public Closeable stream = null;
    public String url;

    public HttpData()
    {
    }
  }

  class StartTask
    implements Runnable
  {
    private m3eHttpHelper.HttpData m_Data;
    private byte m_metohd = 0;

    public StartTask(m3eHttpHelper.HttpData paramByte, byte arg3)
    {
      byte b;
      this.m_metohd = b;
      this.m_Data = paramByte;
    }

    public void run()
    {
      if (this.m_metohd == 1)
      {
        m3eHttpHelper.m3eHttpPost localm3eHttpPost = new m3eHttpHelper.m3eHttpPost(m3eHttpHelper.this);
        m3eHttpHelper.HttpData[] arrayOfHttpData1 = new m3eHttpHelper.HttpData[1];
        arrayOfHttpData1[0] = this.m_Data;
        localm3eHttpPost.execute(arrayOfHttpData1);
        return;
      }
      m3eHttpHelper.m3eHttpGet localm3eHttpGet = new m3eHttpHelper.m3eHttpGet(m3eHttpHelper.this);
      m3eHttpHelper.HttpData[] arrayOfHttpData2 = new m3eHttpHelper.HttpData[1];
      arrayOfHttpData2[0] = this.m_Data;
      localm3eHttpGet.execute(arrayOfHttpData2);
    }
  }

  class m3eHttpGet extends AsyncTask<m3eHttpHelper.HttpData, Integer, m3eHttpHelper.HttpData>
  {
    m3eHttpGet()
    {
    }

    protected m3eHttpHelper.HttpData doInBackground(m3eHttpHelper.HttpData[] paramArrayOfHttpData)
    {
      AndroidHttpClient localAndroidHttpClient = AndroidHttpClient.newInstance("AndroidHttpRequestData");
      m3eHttpHelper.HttpData localHttpData = paramArrayOfHttpData[0];
      HttpGet localHttpGet = new HttpGet(localHttpData.url);
      try
      {
        HttpResponse localHttpResponse = localAndroidHttpClient.execute(localHttpGet);
        localHttpData.status = localHttpResponse.getStatusLine().getStatusCode();
        if (localHttpData.status != 200)
        {
          Log.d(m3eHttpHelper.TAG, "from " + localHttpData.url + " loading data was failed. the error code is: " + localHttpData.status);
          m3eHttpHelper.access$100(localHttpData, -1);
          localHttpGet.abort();
          return localHttpData;
        }
        HttpEntity localHttpEntity = localHttpResponse.getEntity();
        if (localHttpEntity != null)
        {
          BufferedHttpEntity localBufferedHttpEntity = new BufferedHttpEntity(localHttpEntity);
          Long localLong = Long.valueOf(localBufferedHttpEntity.getContentLength());
          if ((localLong.longValue() < 0L) || (localLong.longValue() >= 2147483647L))
          {
            Log.w(m3eHttpHelper.TAG, "from " + localHttpData.url + " loading data was too big");
            m3eHttpHelper.access$100(localHttpData, -1);
            localHttpGet.abort();
            return null;
          }
          int i = localLong.intValue();
          localHttpData.data = new byte[i + 128];
          InputStream localInputStream = localBufferedHttpEntity.getContent();
          int j = localInputStream.read(localHttpData.data);
          if (j != i)
            Log.d(m3eHttpHelper.TAG, "read data from the Buffer is errorBuffer length: " + i + "read length :" + j);
          localHttpData.stream = localInputStream;
          m3eHttpHelper.access$100(localHttpData, j);
          return localHttpData;
        }
        return null;
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          Log.d(m3eHttpHelper.TAG, "I/O error while retrieving data from " + localHttpData.url, localIOException);
          m3eHttpHelper.access$100(localHttpData, -1);
          localHttpGet.abort();
          if (localAndroidHttpClient == null)
            continue;
          localAndroidHttpClient.close();
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        while (true)
        {
          Log.d(m3eHttpHelper.TAG, "Incorrect URL:" + localHttpData.url);
          m3eHttpHelper.access$100(localHttpData, -1);
          localHttpGet.abort();
          if (localAndroidHttpClient == null)
            continue;
          localAndroidHttpClient.close();
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          Log.d(m3eHttpHelper.TAG, "Error while retrieving data from " + localHttpData.url, localException);
          m3eHttpHelper.access$100(localHttpData, -1);
          localHttpGet.abort();
          if (localAndroidHttpClient == null)
            continue;
          localAndroidHttpClient.close();
        }
      }
      finally
      {
        if (localAndroidHttpClient != null)
          localAndroidHttpClient.close();
      }
      throw localObject;
    }
  }

  class m3eHttpPost extends AsyncTask<m3eHttpHelper.HttpData, Integer, m3eHttpHelper.HttpData>
  {
    m3eHttpPost()
    {
    }

    protected m3eHttpHelper.HttpData doInBackground(m3eHttpHelper.HttpData[] paramArrayOfHttpData)
    {
      AndroidHttpClient localAndroidHttpClient = AndroidHttpClient.newInstance("AndroidHttpSendData");
      m3eHttpHelper.HttpData localHttpData = paramArrayOfHttpData[0];
      HttpPost localHttpPost = new HttpPost(localHttpData.url);
      try
      {
        ByteArrayEntity localByteArrayEntity = new ByteArrayEntity(localHttpData.data);
        localByteArrayEntity.setContentType("application/binary_stream");
        localHttpPost.setEntity(localByteArrayEntity);
        localHttpData.status = localAndroidHttpClient.execute(localHttpPost).getStatusLine().getStatusCode();
        if (localHttpData.status != 200)
        {
          Log.d(m3eHttpHelper.TAG, "send data to : " + localHttpData.url + "  was failed. the error code is: " + localHttpData.status);
          return localHttpData;
        }
        m3eHttpHelper.access$200(localHttpData);
        return localHttpData;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        Log.d(m3eHttpHelper.TAG, "Incorrect URL:" + localHttpData.url);
        m3eHttpHelper.access$200(localHttpData);
        localHttpPost.abort();
        return null;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        while (true)
        {
          Log.d(m3eHttpHelper.TAG, "I/O error while send data to " + localHttpData.url, localUnsupportedEncodingException);
          localHttpPost.abort();
          m3eHttpHelper.access$200(localHttpData);
          if (localAndroidHttpClient == null)
            continue;
          localAndroidHttpClient.close();
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          Log.d(m3eHttpHelper.TAG, "I/O error while send data to " + localHttpData.url, localIOException);
          localHttpPost.abort();
          m3eHttpHelper.access$200(localHttpData);
          if (localAndroidHttpClient == null)
            continue;
          localAndroidHttpClient.close();
        }
      }
      finally
      {
        if (localAndroidHttpClient != null)
          localAndroidHttpClient.close();
      }
      throw localObject;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.m3eHttpHelper
 * JD-Core Version:    0.6.0
 */
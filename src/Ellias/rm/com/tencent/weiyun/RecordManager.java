package com.tencent.weiyun;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.Util;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecordManager extends BaseApi
{
  public RecordManager(Context paramContext, QQAuth paramQQAuth, QQToken paramQQToken)
  {
    super(paramContext, paramQQAuth, paramQQToken);
  }

  public RecordManager(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
  }

  public void checkRecord(String paramString, IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    BaseApi.TempRequestListener localTempRequestListener = new BaseApi.TempRequestListener(this, new IUiListener(paramIUiListener)
    {
      public void onCancel()
      {
        this.a.onCancel();
      }

      public void onComplete(Object paramObject)
      {
        JSONObject localJSONObject = (JSONObject)paramObject;
        try
        {
          if (localJSONObject.getInt("ret") == 0)
          {
            this.a.onComplete(Boolean.TRUE);
            return;
          }
          this.a.onComplete(Boolean.FALSE);
          return;
        }
        catch (JSONException localJSONException)
        {
          this.a.onError(new UiError(-4, localJSONException.getMessage(), null));
        }
      }

      public void onError(UiError paramUiError)
      {
        this.a.onError(paramUiError);
      }
    });
    localBundle.putString("key", Util.toHexString(paramString));
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/check_record", localBundle, "GET", localTempRequestListener);
  }

  public void createRecord(String paramString1, String paramString2, IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    BaseApi.TempRequestListener localTempRequestListener = new BaseApi.TempRequestListener(this, new IUiListener(paramIUiListener)
    {
      public void onCancel()
      {
        this.a.onCancel();
      }

      public void onComplete(Object paramObject)
      {
        JSONObject localJSONObject = (JSONObject)paramObject;
        try
        {
          if (localJSONObject.getInt("ret") == 0)
          {
            this.a.onComplete("");
            return;
          }
          this.a.onError(new UiError(-4, localJSONObject.toString(), null));
          return;
        }
        catch (JSONException localJSONException)
        {
          this.a.onError(new UiError(-4, localJSONException.getMessage(), null));
        }
      }

      public void onError(UiError paramUiError)
      {
        this.a.onError(paramUiError);
      }
    });
    localBundle.putString("key", Util.toHexString(paramString1));
    localBundle.putByteArray("value", Util.toHexString(paramString2).getBytes());
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/create_record", localBundle, "POST", localTempRequestListener);
  }

  public void deleteRecord(String paramString, IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    BaseApi.TempRequestListener localTempRequestListener = new BaseApi.TempRequestListener(this, new IUiListener(paramIUiListener)
    {
      public void onCancel()
      {
        this.a.onCancel();
      }

      public void onComplete(Object paramObject)
      {
        JSONObject localJSONObject = (JSONObject)paramObject;
        try
        {
          if (localJSONObject.getInt("ret") == 0)
          {
            this.a.onComplete("");
            return;
          }
          this.a.onError(new UiError(-4, localJSONObject.toString(), null));
          return;
        }
        catch (JSONException localJSONException)
        {
          this.a.onError(new UiError(-4, localJSONException.getMessage(), null));
        }
      }

      public void onError(UiError paramUiError)
      {
        this.a.onError(paramUiError);
      }
    });
    localBundle.putString("key", Util.toHexString(paramString));
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/delete_record", localBundle, "GET", localTempRequestListener);
  }

  public void getRecord(String paramString, IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    BaseApi.TempRequestListener localTempRequestListener = new BaseApi.TempRequestListener(this, new IUiListener(paramIUiListener)
    {
      public void onCancel()
      {
        this.a.onCancel();
      }

      public void onComplete(Object paramObject)
      {
        JSONObject localJSONObject = (JSONObject)paramObject;
        try
        {
          if (localJSONObject.getInt("ret") == 0)
          {
            String str = localJSONObject.getJSONObject("data").getString("value");
            this.a.onComplete(Util.hexToString(str));
            return;
          }
          this.a.onError(new UiError(-4, localJSONObject.toString(), null));
          return;
        }
        catch (JSONException localJSONException)
        {
          this.a.onError(new UiError(-4, localJSONException.getMessage(), null));
        }
      }

      public void onError(UiError paramUiError)
      {
        this.a.onError(paramUiError);
      }
    });
    localBundle.putString("key", Util.toHexString(paramString));
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/get_record", localBundle, "GET", localTempRequestListener);
  }

  public void modifyRecord(String paramString1, String paramString2, IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    BaseApi.TempRequestListener localTempRequestListener = new BaseApi.TempRequestListener(this, new IUiListener(paramIUiListener)
    {
      public void onCancel()
      {
        this.a.onCancel();
      }

      public void onComplete(Object paramObject)
      {
        JSONObject localJSONObject = (JSONObject)paramObject;
        try
        {
          if (localJSONObject.getInt("ret") == 0)
          {
            this.a.onComplete("");
            return;
          }
          this.a.onError(new UiError(-4, localJSONObject.toString(), null));
          return;
        }
        catch (JSONException localJSONException)
        {
          this.a.onError(new UiError(-4, localJSONException.getMessage(), null));
        }
      }

      public void onError(UiError paramUiError)
      {
        this.a.onError(paramUiError);
      }
    });
    localBundle.putString("key", Util.toHexString(paramString1));
    localBundle.putByteArray("value", Util.toHexString(paramString2).getBytes());
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/modify_record", localBundle, "POST", localTempRequestListener);
  }

  public void queryAllRecord(IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    BaseApi.TempRequestListener localTempRequestListener = new BaseApi.TempRequestListener(this, new IUiListener(paramIUiListener)
    {
      public void onCancel()
      {
        this.a.onCancel();
      }

      public void onComplete(Object paramObject)
      {
        JSONObject localJSONObject1 = (JSONObject)paramObject;
        try
        {
          if (localJSONObject1.getInt("ret") == 0)
          {
            ArrayList localArrayList = new ArrayList();
            JSONObject localJSONObject2 = localJSONObject1.getJSONObject("data");
            if (!localJSONObject2.isNull("keys"))
            {
              JSONArray localJSONArray = localJSONObject2.getJSONArray("keys");
              for (int i = 0; i < localJSONArray.length(); i++)
                localArrayList.add(Util.hexToString(localJSONArray.getJSONObject(i).getString("key")));
            }
            this.a.onComplete(localArrayList);
            return;
          }
          this.a.onError(new UiError(-4, localJSONObject1.toString(), null));
          return;
        }
        catch (JSONException localJSONException)
        {
          this.a.onError(new UiError(-4, localJSONException.getMessage(), null));
        }
      }

      public void onError(UiError paramUiError)
      {
        this.a.onError(paramUiError);
      }
    });
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/query_all_record", localBundle, "GET", localTempRequestListener);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.weiyun.RecordManager
 * JD-Core Version:    0.6.0
 */
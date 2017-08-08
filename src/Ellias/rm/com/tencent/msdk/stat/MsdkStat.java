package com.tencent.msdk.stat;

import com.tencent.msdk.Singleton;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.SharedPreferencesTool;
import org.json.JSONException;
import org.json.JSONObject;

public class MsdkStat
{
  private static final long LOGIN_THRESHOLD_IN_SECOND = 600L;
  private static final String PAUSE_TIME_KEY = "msdk_pause_time";
  private static final String RESUME_TIME_KEY = "msdk_resume_time";
  public static final Singleton<MsdkStat> gDefault = new Singleton()
  {
    protected MsdkStat create()
    {
      return new MsdkStat(null);
    }
  };
  private boolean mHaveReported = false;

  public void addLoginLog(JSONObject paramJSONObject, boolean paramBoolean)
  {
    if (paramJSONObject == null)
    {
      Logger.d("param for MsdkStat.addLoginLog can not be null");
      return;
    }
    Logger.d("called");
    if ((paramBoolean) || (needReportLoginInfo()));
    try
    {
      JSONObject localJSONObject = new DeviceInfo(WeGame.getInstance().getActivity()).getAllDeviceInfo();
      if (localJSONObject == null)
        localJSONObject = new JSONObject();
      long l = System.currentTimeMillis() / 1000L;
      Logger.d("pausedTime: " + (l - SharedPreferencesTool.getLong(WeGame.getInstance().getActivity(), "msdk_pause_time", l)));
      if (l - SharedPreferencesTool.getLong(WeGame.getInstance().getActivity(), "msdk_pause_time", l) > 600L)
      {
        localJSONObject.put("isFromBackground", 1);
        localJSONObject.put("backgroundTime", SharedPreferencesTool.getLong(WeGame.getInstance().getActivity(), "msdk_pause_time", 0L));
        localJSONObject.put("currentTime", l);
        localJSONObject.put("pausedTime", l - SharedPreferencesTool.getLong(WeGame.getInstance().getActivity(), "msdk_pause_time", 0L));
      }
      while (true)
      {
        paramJSONObject.put("deviceInfo", localJSONObject);
        setmHaveReported(true);
        Logger.d(paramJSONObject);
        return;
        localJSONObject.put("isFromBackground", 0);
        localJSONObject.put("backgroundTime", 0);
        localJSONObject.put("currentTime", l);
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  public void clearReportStatus()
  {
    setmHaveReported(false);
    SharedPreferencesTool.cleanKey(WeGame.getInstance().getActivity(), "msdk_pause_time");
  }

  public long getResumedTime()
  {
    return SharedPreferencesTool.getLong(WeGame.getInstance().getActivity(), "msdk_resume_time", 0L);
  }

  public boolean ismHaveReported()
  {
    return this.mHaveReported;
  }

  public boolean needReportLoginInfo()
  {
    boolean bool;
    if (SharedPreferencesTool.getLong(WeGame.getInstance().getActivity(), "msdk_pause_time", -1L) == -1L)
      if (!ismHaveReported())
        bool = true;
    while (true)
    {
      Logger.d("rtn: " + bool);
      return bool;
      bool = false;
      continue;
      if (System.currentTimeMillis() / 1000L - SharedPreferencesTool.getLong(WeGame.getInstance().getActivity(), "msdk_pause_time", -1L) > 600L)
      {
        bool = true;
        continue;
      }
      bool = false;
    }
  }

  public void onPause()
  {
    long l = System.currentTimeMillis() / 1000L;
    Logger.d("pauseed on: " + l);
    SharedPreferencesTool.putLong(WeGame.getInstance().getActivity(), "msdk_pause_time", l);
  }

  public void onResume()
  {
    long l = System.currentTimeMillis() / 1000L;
    Logger.d("resumed on: " + l);
    SharedPreferencesTool.putLong(WeGame.getInstance().getActivity(), "msdk_resume_time", l);
  }

  public void setmHaveReported(boolean paramBoolean)
  {
    this.mHaveReported = paramBoolean;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.stat.MsdkStat
 * JD-Core Version:    0.6.0
 */
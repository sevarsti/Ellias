package com.tencent.feedback.common.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import com.tencent.feedback.common.e;
import java.util.concurrent.atomic.AtomicInteger;

public class RQDService extends IntentService
{
  protected static AtomicInteger currentPendingNum = new AtomicInteger(0);

  public RQDService()
  {
    super("RQDSERVICE");
  }

  protected void handleIntent(Intent paramIntent)
  {
    if (!"com.tencent.feedback.10".equals(paramIntent.getAction()));
    RQDServiceTask localRQDServiceTask;
    do
    {
      return;
      localRQDServiceTask = a.a(paramIntent);
    }
    while (localRQDServiceTask == null);
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(localRQDServiceTask.getTaskId());
    arrayOfObject1[1] = localRQDServiceTask.getTaskName();
    e.b("handle task %d %s", arrayOfObject1);
    try
    {
      currentPendingNum.addAndGet(1);
      localRQDServiceTask.runInService(this, paramIntent);
      Object[] arrayOfObject4;
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      Object[] arrayOfObject3;
      return;
    }
    finally
    {
      currentPendingNum.addAndGet(-1);
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(currentPendingNum.get());
      e.b("current pending %d", arrayOfObject2);
    }
    throw localObject;
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    e.a = true;
    super.onCreate();
  }

  public void onDestroy()
  {
    e.b("service destory", new Object[0]);
    super.onDestroy();
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    handleIntent(paramIntent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.service.RQDService
 * JD-Core Version:    0.6.0
 */
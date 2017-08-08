package com.tencent.feedback.common.service;

import android.content.Intent;
import android.os.Parcelable;
import com.tencent.feedback.common.e;
import java.util.Map;

public class a
{
  public long a;
  public String b;
  public long c;
  public Map<String, String[]> d;

  protected static RQDServiceTask a(Intent paramIntent)
  {
    RQDServiceTask localRQDServiceTask;
    if (paramIntent == null)
      localRQDServiceTask = null;
    int i;
    while (true)
    {
      return localRQDServiceTask;
      Parcelable localParcelable = paramIntent.getParcelableExtra("com.tencent.feedback.104");
      if ((localParcelable == null) && (!RQDServiceTask.class.isInstance(localParcelable)))
        return null;
      localRQDServiceTask = (RQDServiceTask)RQDServiceTask.class.cast(localParcelable);
      i = 0;
      if (localRQDServiceTask != null)
        break;
      if (i == 0)
        return null;
    }
    switch (localRQDServiceTask.getTaskId())
    {
    default:
    case 1000:
    }
    for (boolean bool = false; ; bool = "2000".equals(localRQDServiceTask.getTaskName()))
    {
      if (!bool)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = localRQDServiceTask.getTaskName();
        arrayOfObject[1] = Integer.valueOf(localRQDServiceTask.getTaskId());
        e.d("verify task fail %s %d", arrayOfObject);
      }
      i = bool;
      break;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.service.a
 * JD-Core Version:    0.6.0
 */
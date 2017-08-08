package com.tencent.feedback.common.service;

import android.content.Intent;

public abstract interface RQDServiceTask
{
  public abstract int getTaskId();

  public abstract String getTaskName();

  public abstract void runInService(RQDService paramRQDService, Intent paramIntent);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.service.RQDServiceTask
 * JD-Core Version:    0.6.0
 */
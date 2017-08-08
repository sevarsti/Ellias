package com.tencent.msdk.api;

import com.tencent.msdk.remote.api.RelationRet;

public abstract interface WGPlatformObserver
{
  public abstract String OnCrashExtMessageNotify();

  public abstract void OnFeedbackNotify(int paramInt, String paramString);

  public abstract void OnLocationNotify(RelationRet paramRelationRet);

  public abstract void OnLoginNotify(LoginRet paramLoginRet);

  public abstract void OnRelationNotify(RelationRet paramRelationRet);

  public abstract void OnShareNotify(ShareRet paramShareRet);

  public abstract void OnWakeupNotify(WakeupRet paramWakeupRet);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.api.WGPlatformObserver
 * JD-Core Version:    0.6.0
 */
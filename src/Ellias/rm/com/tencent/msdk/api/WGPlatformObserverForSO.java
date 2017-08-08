package com.tencent.msdk.api;

import com.tencent.msdk.remote.api.RelationRet;

public class WGPlatformObserverForSO
{
  public static native String OnCrashExtMessageNotify();

  public static native void OnFeedbackNotify(int paramInt, String paramString);

  public static native void OnLocationNotify(RelationRet paramRelationRet);

  public static native void OnLoginNotify(LoginRet paramLoginRet);

  public static native void OnRelationNotify(RelationRet paramRelationRet);

  public static native void OnShareNotify(ShareRet paramShareRet);

  public static native void OnWakeupNotify(WakeupRet paramWakeupRet);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.api.WGPlatformObserverForSO
 * JD-Core Version:    0.6.0
 */
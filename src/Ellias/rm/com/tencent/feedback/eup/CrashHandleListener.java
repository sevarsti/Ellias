package com.tencent.feedback.eup;

public abstract interface CrashHandleListener
{
  public abstract byte[] getCrashExtraData();

  public abstract String getCrashExtraMessage();

  public abstract boolean onCrashFinished(Thread paramThread, Throwable paramThrowable);

  public abstract void onCrashHappen(Thread paramThread, Throwable paramThrowable);

  public abstract void onNativeCrash(int paramInt1, int paramInt2, String paramString);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.eup.CrashHandleListener
 * JD-Core Version:    0.6.0
 */
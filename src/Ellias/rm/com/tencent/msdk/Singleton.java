package com.tencent.msdk;

public abstract class Singleton<T>
{
  private T mInstance;

  protected abstract T create();

  public final T get()
  {
    monitorenter;
    try
    {
      if (this.mInstance == null)
        this.mInstance = create();
      Object localObject2 = this.mInstance;
      return localObject2;
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.Singleton
 * JD-Core Version:    0.6.0
 */
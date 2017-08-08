package com.tencent.smtt.export.external.interfaces;

public abstract interface IX5WebBackForwardList
{
  public abstract int getCurrentIndex();

  public abstract IX5WebHistoryItem getCurrentItem();

  public abstract IX5WebHistoryItem getItemAtIndex(int paramInt);

  public abstract int getSize();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.export.external.interfaces.IX5WebBackForwardList
 * JD-Core Version:    0.6.0
 */
package com.tencent.smtt.export.external.interfaces;

public abstract interface IX5WebBackForwardListClient
{
  public abstract void onIndexChanged(IX5WebHistoryItem paramIX5WebHistoryItem, int paramInt);

  public abstract void onNewHistoryItem(IX5WebHistoryItem paramIX5WebHistoryItem);

  public abstract void onRemoveHistoryItem(IX5WebHistoryItem paramIX5WebHistoryItem);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.export.external.interfaces.IX5WebBackForwardListClient
 * JD-Core Version:    0.6.0
 */
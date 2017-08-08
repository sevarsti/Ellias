package com.tencent.smtt.export.external.interfaces;

import android.net.http.SslCertificate;

public abstract interface SslError
{
  public abstract boolean addError(int paramInt);

  public abstract SslCertificate getCertificate();

  public abstract int getPrimaryError();

  public abstract boolean hasError(int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.export.external.interfaces.SslError
 * JD-Core Version:    0.6.0
 */
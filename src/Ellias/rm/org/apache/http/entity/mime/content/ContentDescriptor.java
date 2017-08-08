package org.apache.http.entity.mime.content;

public abstract interface ContentDescriptor
{
  public abstract String getCharset();

  public abstract long getContentLength();

  public abstract String getMediaType();

  public abstract String getMimeType();

  public abstract String getSubType();

  public abstract String getTransferEncoding();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     org.apache.http.entity.mime.content.ContentDescriptor
 * JD-Core Version:    0.6.0
 */
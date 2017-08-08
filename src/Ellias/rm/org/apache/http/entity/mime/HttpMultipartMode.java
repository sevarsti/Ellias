package org.apache.http.entity.mime;

public enum HttpMultipartMode
{
  static
  {
    BROWSER_COMPATIBLE = new HttpMultipartMode("BROWSER_COMPATIBLE", 1);
    HttpMultipartMode[] arrayOfHttpMultipartMode = new HttpMultipartMode[2];
    arrayOfHttpMultipartMode[0] = STRICT;
    arrayOfHttpMultipartMode[1] = BROWSER_COMPATIBLE;
    $VALUES = arrayOfHttpMultipartMode;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     org.apache.http.entity.mime.HttpMultipartMode
 * JD-Core Version:    0.6.0
 */
package org.apache.http.entity.mime.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputStreamBody extends AbstractContentBody
{
  private final String filename;
  private final InputStream in;

  public InputStreamBody(InputStream paramInputStream, String paramString)
  {
    this(paramInputStream, "application/octet-stream", paramString);
  }

  public InputStreamBody(InputStream paramInputStream, String paramString1, String paramString2)
  {
    super(paramString1);
    if (paramInputStream == null)
      throw new IllegalArgumentException("Input stream may not be null");
    this.in = paramInputStream;
    this.filename = paramString2;
  }

  public String getCharset()
  {
    return null;
  }

  public long getContentLength()
  {
    return -1L;
  }

  public String getFilename()
  {
    return this.filename;
  }

  public InputStream getInputStream()
  {
    return this.in;
  }

  public String getTransferEncoding()
  {
    return "binary";
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    try
    {
      byte[] arrayOfByte = new byte[4096];
      while (true)
      {
        int i = this.in.read(arrayOfByte);
        if (i == -1)
          break;
        paramOutputStream.write(arrayOfByte, 0, i);
      }
    }
    finally
    {
      this.in.close();
    }
    paramOutputStream.flush();
    this.in.close();
  }

  @Deprecated
  public void writeTo(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    writeTo(paramOutputStream);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     org.apache.http.entity.mime.content.InputStreamBody
 * JD-Core Version:    0.6.0
 */
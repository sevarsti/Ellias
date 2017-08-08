package com.tencent.component.net.http.upload;

public class UploadException extends RuntimeException
{
  public static final int ERROR_NONE = 0;
  public static final int ERROR_SERVER_NOT_RETRY = -4000;
  public static final int FILE_IS_EMPTY = -704;
  public static final int PREPARE_PIECE_DATA_ERROR = -703;
  public static final int SERVER_ERROR_CLIENT_RETRY = -3000;
  public static final int UI_FILE_EMPTY_PATH = -701;
  public static final int UI_FILE_NOT_EXIST_BY_EXCEPTION = -702;
  public static final int UI_FILE_NOT_EXIST_RETCODE = -700;
  private static final long serialVersionUID = 5549200219467961243L;
  private final int retCode;

  public UploadException(int paramInt, Exception paramException)
  {
    super(paramException);
    this.retCode = paramInt;
  }

  public UploadException(int paramInt, String paramString)
  {
    super(paramString);
    this.retCode = paramInt;
  }

  public int a()
  {
    return this.retCode;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.upload.UploadException
 * JD-Core Version:    0.6.0
 */
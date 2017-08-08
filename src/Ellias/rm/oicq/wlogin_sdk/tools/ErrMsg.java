package oicq.wlogin_sdk.tools;

public class ErrMsg
  implements Cloneable
{
  private String message;
  private String otherinfo;
  private String title;
  private int type;

  public ErrMsg()
  {
    ClearMsg();
  }

  public ErrMsg(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.type = paramInt;
    this.title = paramString1;
    this.message = paramString2;
    this.otherinfo = paramString3;
  }

  public void ClearMsg()
  {
    this.type = 0;
    this.title = "";
    this.message = "";
    this.otherinfo = "";
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public String getMessage()
  {
    return this.message;
  }

  public String getOtherinfo()
  {
    return this.otherinfo;
  }

  public String getTitle()
  {
    return this.title;
  }

  public int getType()
  {
    return this.type;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public void setOtherinfo(String paramString)
  {
    this.otherinfo = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tools.ErrMsg
 * JD-Core Version:    0.6.0
 */
package com.tencent.smtt.export.external.interfaces;

public abstract interface ConsoleMessage
{
  public abstract int lineNumber();

  public abstract String message();

  public abstract MessageLevel messageLevel();

  public abstract String sourceId();

  public static enum MessageLevel
  {
    static
    {
      LOG = new MessageLevel("LOG", 1);
      WARNING = new MessageLevel("WARNING", 2);
      ERROR = new MessageLevel("ERROR", 3);
      DEBUG = new MessageLevel("DEBUG", 4);
      MessageLevel[] arrayOfMessageLevel = new MessageLevel[5];
      arrayOfMessageLevel[0] = TIP;
      arrayOfMessageLevel[1] = LOG;
      arrayOfMessageLevel[2] = WARNING;
      arrayOfMessageLevel[3] = ERROR;
      arrayOfMessageLevel[4] = DEBUG;
      $VALUES = arrayOfMessageLevel;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.export.external.interfaces.ConsoleMessage
 * JD-Core Version:    0.6.0
 */
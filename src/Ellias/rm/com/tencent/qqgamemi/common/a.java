package com.tencent.qqgamemi.common;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

final class a extends DefaultHandler
{
  private static final String a = "config";
  private static final String b = "embed";
  private static final String c = "id";
  private static final String d = "showEnvironmentSelectDialog";
  private static final String e = "status";
  private boolean f = false;
  private ConfigRecord g = new ConfigRecord();

  public ConfigRecord a()
  {
    return this.g;
  }

  public void endElement(String paramString1, String paramString2, String paramString3)
  {
    if ("config".equalsIgnoreCase(paramString2))
      this.f = false;
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
  {
    boolean bool = true;
    if ("config".equalsIgnoreCase(paramString2))
      this.f = bool;
    if (!this.f);
    do
    {
      return;
      if (!"embed".equalsIgnoreCase(paramString2))
        continue;
      this.g.a = paramAttributes.getValue("id");
      return;
    }
    while (!"showEnvironmentSelectDialog".equalsIgnoreCase(paramString2));
    ConfigRecord localConfigRecord = this.g;
    if (paramAttributes.getValue("status").equals("true"));
    while (true)
    {
      localConfigRecord.b = bool;
      return;
      bool = false;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.common.a
 * JD-Core Version:    0.6.0
 */
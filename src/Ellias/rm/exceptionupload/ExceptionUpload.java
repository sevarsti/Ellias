package exceptionupload;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class ExceptionUpload extends JceStruct
  implements Cloneable
{
  static ArrayList<AppInfo> cache_appInfo;
  static ArrayList<Attachment> cache_attachmentList;
  static ContactInfo cache_contact;
  static MobileDetail cache_mobile;
  static ArrayList<PlugInfo> cache_plugins;
  static RunInfo cache_runInfo;
  public String appBaseAddr = "";
  public ArrayList<AppInfo> appInfo = null;
  public String appUUID = "";
  public String archVersion = "";
  public String attachBuildStr = "";
  public ArrayList<Attachment> attachmentList = null;
  public String callStack = "";
  public long cashTime = 0L;
  public String causeBy = "";
  public ContactInfo contact = null;
  public long crashCount = 0L;
  public String crashDetail = "";
  public String excepitonAddress = "";
  public String exceptionType = "";
  public String expuid = "";
  public String hash = "";
  public String memo = "";
  public MobileDetail mobile = null;
  public ArrayList<PlugInfo> plugins = null;
  public String processName = "";
  public RunInfo runInfo = null;
  public String threadName = "";
  public String type = "";

  static
  {
    if (!ExceptionUpload.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public ExceptionUpload()
  {
  }

  public ExceptionUpload(String paramString1, String paramString2, String paramString3, long paramLong1, ArrayList<Attachment> paramArrayList, String paramString4, String paramString5, String paramString6, MobileDetail paramMobileDetail, RunInfo paramRunInfo, ContactInfo paramContactInfo, String paramString7, ArrayList<AppInfo> paramArrayList1, long paramLong2, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, ArrayList<PlugInfo> paramArrayList2, String paramString15)
  {
    this.exceptionType = paramString1;
    this.excepitonAddress = paramString2;
    this.hash = paramString3;
    this.cashTime = paramLong1;
    this.attachmentList = paramArrayList;
    this.threadName = paramString4;
    this.callStack = paramString5;
    this.causeBy = paramString6;
    this.mobile = paramMobileDetail;
    this.runInfo = paramRunInfo;
    this.contact = paramContactInfo;
    this.crashDetail = paramString7;
    this.appInfo = paramArrayList1;
    this.crashCount = paramLong2;
    this.memo = paramString8;
    this.archVersion = paramString9;
    this.processName = paramString10;
    this.appUUID = paramString11;
    this.type = paramString12;
    this.attachBuildStr = paramString13;
    this.appBaseAddr = paramString14;
    this.plugins = paramArrayList2;
    this.expuid = paramString15;
  }

  public final String className()
  {
    return "exceptionupload.ExceptionUpload";
  }

  public final Object clone()
  {
    try
    {
      Object localObject2 = super.clone();
      localObject1 = localObject2;
      return localObject1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      boolean bool;
      do
      {
        bool = $assertionsDisabled;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public final void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.exceptionType, "exceptionType");
    localJceDisplayer.display(this.excepitonAddress, "excepitonAddress");
    localJceDisplayer.display(this.hash, "hash");
    localJceDisplayer.display(this.cashTime, "cashTime");
    localJceDisplayer.display(this.attachmentList, "attachmentList");
    localJceDisplayer.display(this.threadName, "threadName");
    localJceDisplayer.display(this.callStack, "callStack");
    localJceDisplayer.display(this.causeBy, "causeBy");
    localJceDisplayer.display(this.mobile, "mobile");
    localJceDisplayer.display(this.runInfo, "runInfo");
    localJceDisplayer.display(this.contact, "contact");
    localJceDisplayer.display(this.crashDetail, "crashDetail");
    localJceDisplayer.display(this.appInfo, "appInfo");
    localJceDisplayer.display(this.crashCount, "crashCount");
    localJceDisplayer.display(this.memo, "memo");
    localJceDisplayer.display(this.archVersion, "archVersion");
    localJceDisplayer.display(this.processName, "processName");
    localJceDisplayer.display(this.appUUID, "appUUID");
    localJceDisplayer.display(this.type, "type");
    localJceDisplayer.display(this.attachBuildStr, "attachBuildStr");
    localJceDisplayer.display(this.appBaseAddr, "appBaseAddr");
    localJceDisplayer.display(this.plugins, "plugins");
    localJceDisplayer.display(this.expuid, "expuid");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    ExceptionUpload localExceptionUpload;
    do
    {
      return false;
      localExceptionUpload = (ExceptionUpload)paramObject;
    }
    while ((!JceUtil.equals(this.exceptionType, localExceptionUpload.exceptionType)) || (!JceUtil.equals(this.excepitonAddress, localExceptionUpload.excepitonAddress)) || (!JceUtil.equals(this.hash, localExceptionUpload.hash)) || (!JceUtil.equals(this.cashTime, localExceptionUpload.cashTime)) || (!JceUtil.equals(this.attachmentList, localExceptionUpload.attachmentList)) || (!JceUtil.equals(this.threadName, localExceptionUpload.threadName)) || (!JceUtil.equals(this.callStack, localExceptionUpload.callStack)) || (!JceUtil.equals(this.causeBy, localExceptionUpload.causeBy)) || (!JceUtil.equals(this.mobile, localExceptionUpload.mobile)) || (!JceUtil.equals(this.runInfo, localExceptionUpload.runInfo)) || (!JceUtil.equals(this.contact, localExceptionUpload.contact)) || (!JceUtil.equals(this.crashDetail, localExceptionUpload.crashDetail)) || (!JceUtil.equals(this.appInfo, localExceptionUpload.appInfo)) || (!JceUtil.equals(this.crashCount, localExceptionUpload.crashCount)) || (!JceUtil.equals(this.memo, localExceptionUpload.memo)) || (!JceUtil.equals(this.archVersion, localExceptionUpload.archVersion)) || (!JceUtil.equals(this.processName, localExceptionUpload.processName)) || (!JceUtil.equals(this.appUUID, localExceptionUpload.appUUID)) || (!JceUtil.equals(this.type, localExceptionUpload.type)) || (!JceUtil.equals(this.attachBuildStr, localExceptionUpload.attachBuildStr)) || (!JceUtil.equals(this.appBaseAddr, localExceptionUpload.appBaseAddr)) || (!JceUtil.equals(this.plugins, localExceptionUpload.plugins)) || (!JceUtil.equals(this.expuid, localExceptionUpload.expuid)));
    return true;
  }

  public final String fullClassName()
  {
    return "exceptionupload.ExceptionUpload";
  }

  public final String getAppBaseAddr()
  {
    return this.appBaseAddr;
  }

  public final ArrayList<AppInfo> getAppInfo()
  {
    return this.appInfo;
  }

  public final String getAppUUID()
  {
    return this.appUUID;
  }

  public final String getArchVersion()
  {
    return this.archVersion;
  }

  public final String getAttachBuildStr()
  {
    return this.attachBuildStr;
  }

  public final ArrayList<Attachment> getAttachmentList()
  {
    return this.attachmentList;
  }

  public final String getCallStack()
  {
    return this.callStack;
  }

  public final long getCashTime()
  {
    return this.cashTime;
  }

  public final String getCauseBy()
  {
    return this.causeBy;
  }

  public final ContactInfo getContact()
  {
    return this.contact;
  }

  public final long getCrashCount()
  {
    return this.crashCount;
  }

  public final String getCrashDetail()
  {
    return this.crashDetail;
  }

  public final String getExcepitonAddress()
  {
    return this.excepitonAddress;
  }

  public final String getExceptionType()
  {
    return this.exceptionType;
  }

  public final String getExpuid()
  {
    return this.expuid;
  }

  public final String getHash()
  {
    return this.hash;
  }

  public final String getMemo()
  {
    return this.memo;
  }

  public final MobileDetail getMobile()
  {
    return this.mobile;
  }

  public final ArrayList<PlugInfo> getPlugins()
  {
    return this.plugins;
  }

  public final String getProcessName()
  {
    return this.processName;
  }

  public final RunInfo getRunInfo()
  {
    return this.runInfo;
  }

  public final String getThreadName()
  {
    return this.threadName;
  }

  public final String getType()
  {
    return this.type;
  }

  public final int hashCode()
  {
    try
    {
      throw new Exception("Need define key first!");
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    this.exceptionType = paramJceInputStream.readString(0, true);
    this.excepitonAddress = paramJceInputStream.readString(1, false);
    this.hash = paramJceInputStream.readString(2, false);
    this.cashTime = paramJceInputStream.read(this.cashTime, 3, true);
    if (cache_attachmentList == null)
    {
      cache_attachmentList = new ArrayList();
      Attachment localAttachment = new Attachment();
      cache_attachmentList.add(localAttachment);
    }
    this.attachmentList = ((ArrayList)paramJceInputStream.read(cache_attachmentList, 4, true));
    this.threadName = paramJceInputStream.readString(5, false);
    this.callStack = paramJceInputStream.readString(6, false);
    this.causeBy = paramJceInputStream.readString(7, false);
    if (cache_mobile == null)
      cache_mobile = new MobileDetail();
    this.mobile = ((MobileDetail)paramJceInputStream.read(cache_mobile, 8, false));
    if (cache_runInfo == null)
      cache_runInfo = new RunInfo();
    this.runInfo = ((RunInfo)paramJceInputStream.read(cache_runInfo, 9, false));
    if (cache_contact == null)
      cache_contact = new ContactInfo();
    this.contact = ((ContactInfo)paramJceInputStream.read(cache_contact, 10, false));
    this.crashDetail = paramJceInputStream.readString(11, false);
    if (cache_appInfo == null)
    {
      cache_appInfo = new ArrayList();
      AppInfo localAppInfo = new AppInfo();
      cache_appInfo.add(localAppInfo);
    }
    this.appInfo = ((ArrayList)paramJceInputStream.read(cache_appInfo, 12, false));
    this.crashCount = paramJceInputStream.read(this.crashCount, 13, false);
    this.memo = paramJceInputStream.readString(14, false);
    this.archVersion = paramJceInputStream.readString(15, false);
    this.processName = paramJceInputStream.readString(16, false);
    this.appUUID = paramJceInputStream.readString(17, false);
    this.type = paramJceInputStream.readString(18, false);
    this.attachBuildStr = paramJceInputStream.readString(19, false);
    this.appBaseAddr = paramJceInputStream.readString(20, false);
    if (cache_plugins == null)
    {
      cache_plugins = new ArrayList();
      PlugInfo localPlugInfo = new PlugInfo();
      cache_plugins.add(localPlugInfo);
    }
    this.plugins = ((ArrayList)paramJceInputStream.read(cache_plugins, 21, false));
    this.expuid = paramJceInputStream.readString(22, false);
  }

  public final void setAppBaseAddr(String paramString)
  {
    this.appBaseAddr = paramString;
  }

  public final void setAppInfo(ArrayList<AppInfo> paramArrayList)
  {
    this.appInfo = paramArrayList;
  }

  public final void setAppUUID(String paramString)
  {
    this.appUUID = paramString;
  }

  public final void setArchVersion(String paramString)
  {
    this.archVersion = paramString;
  }

  public final void setAttachBuildStr(String paramString)
  {
    this.attachBuildStr = paramString;
  }

  public final void setAttachmentList(ArrayList<Attachment> paramArrayList)
  {
    this.attachmentList = paramArrayList;
  }

  public final void setCallStack(String paramString)
  {
    this.callStack = paramString;
  }

  public final void setCashTime(long paramLong)
  {
    this.cashTime = paramLong;
  }

  public final void setCauseBy(String paramString)
  {
    this.causeBy = paramString;
  }

  public final void setContact(ContactInfo paramContactInfo)
  {
    this.contact = paramContactInfo;
  }

  public final void setCrashCount(long paramLong)
  {
    this.crashCount = paramLong;
  }

  public final void setCrashDetail(String paramString)
  {
    this.crashDetail = paramString;
  }

  public final void setExcepitonAddress(String paramString)
  {
    this.excepitonAddress = paramString;
  }

  public final void setExceptionType(String paramString)
  {
    this.exceptionType = paramString;
  }

  public final void setExpuid(String paramString)
  {
    this.expuid = paramString;
  }

  public final void setHash(String paramString)
  {
    this.hash = paramString;
  }

  public final void setMemo(String paramString)
  {
    this.memo = paramString;
  }

  public final void setMobile(MobileDetail paramMobileDetail)
  {
    this.mobile = paramMobileDetail;
  }

  public final void setPlugins(ArrayList<PlugInfo> paramArrayList)
  {
    this.plugins = paramArrayList;
  }

  public final void setProcessName(String paramString)
  {
    this.processName = paramString;
  }

  public final void setRunInfo(RunInfo paramRunInfo)
  {
    this.runInfo = paramRunInfo;
  }

  public final void setThreadName(String paramString)
  {
    this.threadName = paramString;
  }

  public final void setType(String paramString)
  {
    this.type = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.exceptionType, 0);
    if (this.excepitonAddress != null)
      paramJceOutputStream.write(this.excepitonAddress, 1);
    if (this.hash != null)
      paramJceOutputStream.write(this.hash, 2);
    paramJceOutputStream.write(this.cashTime, 3);
    paramJceOutputStream.write(this.attachmentList, 4);
    if (this.threadName != null)
      paramJceOutputStream.write(this.threadName, 5);
    if (this.callStack != null)
      paramJceOutputStream.write(this.callStack, 6);
    if (this.causeBy != null)
      paramJceOutputStream.write(this.causeBy, 7);
    if (this.mobile != null)
      paramJceOutputStream.write(this.mobile, 8);
    if (this.runInfo != null)
      paramJceOutputStream.write(this.runInfo, 9);
    if (this.contact != null)
      paramJceOutputStream.write(this.contact, 10);
    if (this.crashDetail != null)
      paramJceOutputStream.write(this.crashDetail, 11);
    if (this.appInfo != null)
      paramJceOutputStream.write(this.appInfo, 12);
    paramJceOutputStream.write(this.crashCount, 13);
    if (this.memo != null)
      paramJceOutputStream.write(this.memo, 14);
    if (this.archVersion != null)
      paramJceOutputStream.write(this.archVersion, 15);
    if (this.processName != null)
      paramJceOutputStream.write(this.processName, 16);
    if (this.appUUID != null)
      paramJceOutputStream.write(this.appUUID, 17);
    if (this.type != null)
      paramJceOutputStream.write(this.type, 18);
    if (this.attachBuildStr != null)
      paramJceOutputStream.write(this.attachBuildStr, 19);
    if (this.appBaseAddr != null)
      paramJceOutputStream.write(this.appBaseAddr, 20);
    if (this.plugins != null)
      paramJceOutputStream.write(this.plugins, 21);
    if (this.expuid != null)
      paramJceOutputStream.write(this.expuid, 22);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     exceptionupload.ExceptionUpload
 * JD-Core Version:    0.6.0
 */
package exceptionupload;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class AssertPackage extends JceStruct
  implements Cloneable
{
  static ArrayList<AppInfo> cache_appInfos;
  static ContactInfo cache_contactInfo;
  static ArrayList<MergedAssertInfo> cache_list;
  static MobileDetail cache_mobileInfo;
  public ArrayList<AppInfo> appInfos = null;
  public ContactInfo contactInfo = null;
  public ArrayList<MergedAssertInfo> list = null;
  public MobileDetail mobileInfo = null;

  static
  {
    if (!AssertPackage.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public AssertPackage()
  {
  }

  public AssertPackage(ArrayList<MergedAssertInfo> paramArrayList, MobileDetail paramMobileDetail, ContactInfo paramContactInfo, ArrayList<AppInfo> paramArrayList1)
  {
    this.list = paramArrayList;
    this.mobileInfo = paramMobileDetail;
    this.contactInfo = paramContactInfo;
    this.appInfos = paramArrayList1;
  }

  public final String className()
  {
    return "exceptionupload.AssertPackage";
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
    localJceDisplayer.display(this.list, "list");
    localJceDisplayer.display(this.mobileInfo, "mobileInfo");
    localJceDisplayer.display(this.contactInfo, "contactInfo");
    localJceDisplayer.display(this.appInfos, "appInfos");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    AssertPackage localAssertPackage;
    do
    {
      return false;
      localAssertPackage = (AssertPackage)paramObject;
    }
    while ((!JceUtil.equals(this.list, localAssertPackage.list)) || (!JceUtil.equals(this.mobileInfo, localAssertPackage.mobileInfo)) || (!JceUtil.equals(this.contactInfo, localAssertPackage.contactInfo)) || (!JceUtil.equals(this.appInfos, localAssertPackage.appInfos)));
    return true;
  }

  public final String fullClassName()
  {
    return "exceptionupload.AssertPackage";
  }

  public final ArrayList<AppInfo> getAppInfos()
  {
    return this.appInfos;
  }

  public final ContactInfo getContactInfo()
  {
    return this.contactInfo;
  }

  public final ArrayList<MergedAssertInfo> getList()
  {
    return this.list;
  }

  public final MobileDetail getMobileInfo()
  {
    return this.mobileInfo;
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
    if (cache_list == null)
    {
      cache_list = new ArrayList();
      MergedAssertInfo localMergedAssertInfo = new MergedAssertInfo();
      cache_list.add(localMergedAssertInfo);
    }
    this.list = ((ArrayList)paramJceInputStream.read(cache_list, 0, true));
    if (cache_mobileInfo == null)
      cache_mobileInfo = new MobileDetail();
    this.mobileInfo = ((MobileDetail)paramJceInputStream.read(cache_mobileInfo, 1, false));
    if (cache_contactInfo == null)
      cache_contactInfo = new ContactInfo();
    this.contactInfo = ((ContactInfo)paramJceInputStream.read(cache_contactInfo, 2, false));
    if (cache_appInfos == null)
    {
      cache_appInfos = new ArrayList();
      AppInfo localAppInfo = new AppInfo();
      cache_appInfos.add(localAppInfo);
    }
    this.appInfos = ((ArrayList)paramJceInputStream.read(cache_appInfos, 3, false));
  }

  public final void setAppInfos(ArrayList<AppInfo> paramArrayList)
  {
    this.appInfos = paramArrayList;
  }

  public final void setContactInfo(ContactInfo paramContactInfo)
  {
    this.contactInfo = paramContactInfo;
  }

  public final void setList(ArrayList<MergedAssertInfo> paramArrayList)
  {
    this.list = paramArrayList;
  }

  public final void setMobileInfo(MobileDetail paramMobileDetail)
  {
    this.mobileInfo = paramMobileDetail;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.list, 0);
    if (this.mobileInfo != null)
      paramJceOutputStream.write(this.mobileInfo, 1);
    if (this.contactInfo != null)
      paramJceOutputStream.write(this.contactInfo, 2);
    if (this.appInfos != null)
      paramJceOutputStream.write(this.appInfos, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     exceptionupload.AssertPackage
 * JD-Core Version:    0.6.0
 */
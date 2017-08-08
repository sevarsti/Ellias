package strategy;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class SecurityStrategyPackage extends JceStruct
  implements Cloneable
{
  public int encryAlgorithm = 0;
  public String encryKey = "";
  public String encryPublicKey = "";
  public int zipAlgorithm = 0;

  static
  {
    if (!SecurityStrategyPackage.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public SecurityStrategyPackage()
  {
    setEncryKey(this.encryKey);
    setEncryPublicKey(this.encryPublicKey);
    setEncryAlgorithm(this.encryAlgorithm);
    setZipAlgorithm(this.zipAlgorithm);
  }

  public SecurityStrategyPackage(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    setEncryKey(paramString1);
    setEncryPublicKey(paramString2);
    setEncryAlgorithm(paramInt1);
    setZipAlgorithm(paramInt2);
  }

  public final String className()
  {
    return "strategy.SecurityStrategyPackage";
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
    localJceDisplayer.display(this.encryKey, "encryKey");
    localJceDisplayer.display(this.encryPublicKey, "encryPublicKey");
    localJceDisplayer.display(this.encryAlgorithm, "encryAlgorithm");
    localJceDisplayer.display(this.zipAlgorithm, "zipAlgorithm");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    SecurityStrategyPackage localSecurityStrategyPackage;
    do
    {
      return false;
      localSecurityStrategyPackage = (SecurityStrategyPackage)paramObject;
    }
    while ((!JceUtil.equals(this.encryKey, localSecurityStrategyPackage.encryKey)) || (!JceUtil.equals(this.encryPublicKey, localSecurityStrategyPackage.encryPublicKey)) || (!JceUtil.equals(this.encryAlgorithm, localSecurityStrategyPackage.encryAlgorithm)) || (!JceUtil.equals(this.zipAlgorithm, localSecurityStrategyPackage.zipAlgorithm)));
    return true;
  }

  public final String fullClassName()
  {
    return "strategy.SecurityStrategyPackage";
  }

  public final int getEncryAlgorithm()
  {
    return this.encryAlgorithm;
  }

  public final String getEncryKey()
  {
    return this.encryKey;
  }

  public final String getEncryPublicKey()
  {
    return this.encryPublicKey;
  }

  public final int getZipAlgorithm()
  {
    return this.zipAlgorithm;
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
    this.encryKey = paramJceInputStream.readString(0, true);
    this.encryPublicKey = paramJceInputStream.readString(1, true);
    this.encryAlgorithm = paramJceInputStream.read(this.encryAlgorithm, 2, false);
    this.zipAlgorithm = paramJceInputStream.read(this.zipAlgorithm, 3, false);
  }

  public final void setEncryAlgorithm(int paramInt)
  {
    this.encryAlgorithm = paramInt;
  }

  public final void setEncryKey(String paramString)
  {
    this.encryKey = paramString;
  }

  public final void setEncryPublicKey(String paramString)
  {
    this.encryPublicKey = paramString;
  }

  public final void setZipAlgorithm(int paramInt)
  {
    this.zipAlgorithm = paramInt;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.encryKey, 0);
    paramJceOutputStream.write(this.encryPublicKey, 1);
    paramJceOutputStream.write(this.encryAlgorithm, 2);
    paramJceOutputStream.write(this.zipAlgorithm, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     strategy.SecurityStrategyPackage
 * JD-Core Version:    0.6.0
 */
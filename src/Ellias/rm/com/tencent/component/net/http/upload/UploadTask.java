package com.tencent.component.net.http.upload;

import android.os.Parcel;
import android.text.TextUtils;
import com.tencent.component.db.annotation.Column;
import com.tencent.component.db.annotation.Id;
import com.tencent.component.db.annotation.Table;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;

@Table(dynamicClass=true, name="UploadTask", version=1)
public abstract class UploadTask
{
  private static final String TAG = "UploadTask";
  public static final int TASK_STATUS_CANCLED = 6;
  public static final int TASK_STATUS_FAILED = 5;
  public static final int TASK_STATUS_PAUSE = 2;
  public static final int TASK_STATUS_QUEUE = 1;
  public static final int TASK_STATUS_SUCCESS = 4;
  public static final int TASK_STATUS_UPLOADING = 3;

  @Column
  private byte[] businessData;
  public String errorMsg;

  @Column
  public long fileSize = 0L;
  public boolean hasRetried = true;
  private int mStatus = 1;
  private UploadManager mUploadManager;

  @Column
  public String md5 = null;

  @Column
  private long sentFileSize = 0L;

  @Column
  public String sha1 = null;

  @Id(strategy=1)
  public String uploadFilePath = null;
  public IUploadTaskCallback uploadTaskCallback = null;

  public UploadTask(UploadManager paramUploadManager)
  {
    this.mUploadManager = paramUploadManager;
  }

  public void abort(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    this.errorMsg = paramString2;
    this.mUploadManager.a(false, this);
    if (this.uploadTaskCallback != null)
      this.uploadTaskCallback.a(this, paramInt, paramString2);
  }

  public void caculateFileSize()
  {
    if ((this.fileSize == 0L) && (haslegalFile()))
      this.fileSize = new File(this.uploadFilePath).length();
  }

  public void caculateMD5()
  {
    if ((TextUtils.isEmpty(this.md5)) && (haslegalFile()))
      this.md5 = UploadUtil.a(new File(this.uploadFilePath));
  }

  public void caculateSHA()
  {
    if ((TextUtils.isEmpty(this.sha1)) && (haslegalFile()))
      this.sha1 = UploadUtil.a(this.uploadFilePath);
  }

  public void cancle()
  {
    this.mUploadManager.c(this);
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    UploadTask localUploadTask;
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
        return false;
      localUploadTask = (UploadTask)paramObject;
      if (this.uploadFilePath == null)
        break;
    }
    while (this.uploadFilePath.equals(localUploadTask.uploadFilePath));
    while (true)
    {
      return false;
      if (localUploadTask.uploadFilePath == null)
        break;
    }
  }

  public byte[] getBusinessData()
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.setDataPosition(0);
      writeBizData(localParcel);
      byte[] arrayOfByte = localParcel.marshall();
      return arrayOfByte;
    }
    catch (Exception localException)
    {
      LogUtil.e("UploadTask", localException.getMessage(), localException);
      return null;
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject;
  }

  public long getSentFileSize()
  {
    return this.sentFileSize;
  }

  public int getTaskStatus()
  {
    monitorenter;
    try
    {
      int i = this.mStatus;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int hashCode()
  {
    if (this.uploadFilePath != null)
      return this.uploadFilePath.hashCode();
    return 0;
  }

  public boolean haslegalFile()
  {
    boolean bool1 = true;
    String str1 = getClass().getSimpleName();
    if (TextUtils.isEmpty(this.uploadFilePath))
    {
      String str4 = str1 + " uploadTask(), path is empty:" + this.uploadFilePath + " errMsg:";
      if (!this.hasRetried);
      while (true)
      {
        abort(-701, str4, "文件不存在", bool1);
        return false;
        bool1 = false;
      }
    }
    try
    {
      File localFile = new File(this.uploadFilePath);
      if ((!localFile.exists()) || (!localFile.isFile()) || (localFile.length() == 0L))
      {
        String str3 = str1 + " uploadTask(), not exist path:" + this.uploadFilePath + " errMsg:";
        if (!this.hasRetried)
        {
          bool2 = bool1;
          abort(-700, str3, "文件不存在", bool2);
          return false;
        }
      }
    }
    catch (Exception localException)
    {
      boolean bool2;
      LogUtil.e("UploadTask", localException.getMessage(), localException);
      String str2 = str1 + " uploadTask(), open file exception:" + this.uploadFilePath + " errMsg:";
      if (!this.hasRetried);
      while (true)
      {
        abort(-702, str2, "文件不存在", bool1);
        return false;
        bool2 = false;
        break;
        bool1 = false;
      }
    }
    return bool1;
  }

  public abstract void onProcessUploadTask();

  public void onUploadProgress(long paramLong)
  {
    if (this.uploadTaskCallback != null)
      this.uploadTaskCallback.a(this, this.fileSize, paramLong);
    this.mUploadManager.d(this);
  }

  public void onUploadSuccess(Object paramObject)
  {
    if (this.uploadTaskCallback != null)
      this.uploadTaskCallback.a(this, paramObject);
    this.mUploadManager.a(true, this);
  }

  public void pause()
  {
    this.mUploadManager.a(this);
  }

  public abstract void readBizData(Parcel paramParcel);

  public void resume()
  {
    this.mUploadManager.b(this);
  }

  public void setBusinessData(byte[] paramArrayOfByte)
  {
    Parcel localParcel;
    if (paramArrayOfByte != null)
      localParcel = Parcel.obtain();
    try
    {
      localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
      localParcel.setDataPosition(0);
      readBizData(localParcel);
      return;
    }
    catch (Exception localException)
    {
      LogUtil.e("UploadTask", localException.getMessage(), localException);
      return;
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject;
  }

  public void setSentFileSize(long paramLong)
  {
    this.sentFileSize = Math.max(paramLong, 0L);
  }

  public void setTaskStatus(int paramInt)
  {
    monitorenter;
    try
    {
      if (this.mStatus != paramInt)
      {
        this.mStatus = paramInt;
        if (this.uploadTaskCallback != null)
          this.uploadTaskCallback.a(this, paramInt);
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public abstract void writeBizData(Parcel paramParcel);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.upload.UploadTask
 * JD-Core Version:    0.6.0
 */
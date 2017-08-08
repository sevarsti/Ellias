package com.tencent.component.net.http.upload;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.component.utils.log.LogUtil;

class a extends Handler
{
  a(UploadManager paramUploadManager, Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1:
    }
    UploadTask localUploadTask;
    do
    {
      do
        return;
      while (!(paramMessage.obj instanceof UploadTask));
      localUploadTask = (UploadTask)paramMessage.obj;
    }
    while (localUploadTask == null);
    UploadLog.b("UploadManager", "receive MSG_UPLOAD_TASK file=" + localUploadTask.uploadFilePath + " sha=" + localUploadTask.sha1 + " " + localUploadTask.getClass().getSimpleName());
    if (!localUploadTask.haslegalFile())
    {
      LogUtil.e("UploadManager", "task has illegal file (in upload task thread)");
      return;
    }
    if (localUploadTask.fileSize <= 0L)
      localUploadTask.caculateFileSize();
    if (localUploadTask.fileSize <= 0L)
    {
      localUploadTask.abort(-704, "文件不存在.", "文件不存在.", true);
      return;
    }
    localUploadTask.caculateSHA();
    localUploadTask.caculateMD5();
    localUploadTask.onProcessUploadTask();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.upload.a
 * JD-Core Version:    0.6.0
 */
package oicq.wlogin_sdk.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

public class FileTracer
  implements Handler.Callback
{
  private static final int MSG_FLUSH = 1024;
  private static String bufferA;
  private static String bufferB;
  private static FileTracer instance = null;
  private Context context;
  private Handler handler;
  private volatile boolean isFlushing = false;
  private HandlerThread thread;

  static
  {
    bufferA = "";
    bufferB = "";
  }

  private FileTracer(Context paramContext)
  {
    this.context = paramContext;
    this.thread = new HandlerThread("FileTracer");
    if (this.thread != null)
      this.thread.start();
    if (this.thread.isAlive())
      this.handler = new Handler(this.thread.getLooper(), this);
    this.handler.sendEmptyMessage(1024);
  }

  private void flushBuffer()
  {
    if (Thread.currentThread() != this.thread);
    do
      return;
    while (this.isFlushing);
    this.isFlushing = true;
    writeFile();
    this.isFlushing = false;
  }

  private void prepareNextFlush()
  {
    this.handler.sendEmptyMessageDelayed(1024, 1000L);
  }

  private void writeFile()
  {
    byte[] arrayOfByte1;
    if ((bufferB != null) && (bufferB.length() > 0))
    {
      bufferA = bufferB;
      bufferB = "";
      arrayOfByte1 = util.compress(bufferA.getBytes());
      if ((arrayOfByte1 != null) && (arrayOfByte1.length != 0));
    }
    else
    {
      return;
    }
    byte[] arrayOfByte2 = new byte[4 + arrayOfByte1.length];
    util.int32_to_buf(arrayOfByte2, 0, arrayOfByte1.length);
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 4, arrayOfByte1.length);
    util.writeFile(util.getLogFileName(this.context, util.getCurrentDay()), arrayOfByte2);
  }

  public static void writeLog(Context paramContext, String paramString1, String paramString2)
  {
    if (instance == null)
      instance = new FileTracer(paramContext);
    if ((paramContext == null) || (paramString2 == null))
      return;
    synchronized (bufferB)
    {
      if (bufferB.length() > 2048)
        bufferB = "";
      bufferB = bufferB + util.getDate() + util.getThreadId() + util.getLineInfo(3) + util.getSdkVersion() + util.getUser(paramString1) + paramString2 + "\n";
      return;
    }
  }

  public void flush()
  {
    if (this.handler.hasMessages(1024))
      this.handler.removeMessages(1024);
    this.handler.sendEmptyMessage(1024);
  }

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1024:
    }
    while (true)
    {
      return true;
      flushBuffer();
      prepareNextFlush();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.tools.FileTracer
 * JD-Core Version:    0.6.0
 */
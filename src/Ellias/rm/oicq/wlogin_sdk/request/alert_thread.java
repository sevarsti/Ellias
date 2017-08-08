package oicq.wlogin_sdk.request;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextPaint;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;
import oicq.wlogin_sdk.tools.ErrMsg;

public class alert_thread extends Thread
{
  private Context context;
  private ErrMsg errMsg;
  Runnable myRunnable = new Runnable()
  {
    private void execToast(Toast paramToast, int paramInt)
    {
      new Timer().schedule(new TimerTask(paramToast, paramInt)
      {
        public void run()
        {
          alert_thread.1.this.initToast(this.val$toast, 1 + this.val$cnt);
        }
      }
      , 30L);
    }

    private void initToast(Toast paramToast, int paramInt)
    {
      if (paramInt > 5)
        return;
      paramToast.show();
      execToast(paramToast, paramInt);
    }

    public void run()
    {
      if ((alert_thread.this.context != null) && (alert_thread.this.errMsg != null))
      {
        LinearLayout localLinearLayout = new LinearLayout(alert_thread.this.context);
        localLinearLayout.setOrientation(1);
        localLinearLayout.setBackgroundColor(-7829368);
        localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        TextView localTextView1 = new TextView(alert_thread.this.context);
        localTextView1.getPaint().setFakeBoldText(true);
        localTextView1.setText(alert_thread.this.errMsg.getTitle());
        localLinearLayout.addView(localTextView1, 0, new LinearLayout.LayoutParams(-1, -2));
        TextView localTextView2 = new TextView(alert_thread.this.context);
        localTextView2.setText(alert_thread.this.errMsg.getMessage());
        localLinearLayout.addView(localTextView2, 1, new LinearLayout.LayoutParams(-1, -2));
        Toast localToast = new Toast(alert_thread.this.context);
        localToast.setGravity(17, 0, 0);
        localToast.setDuration(1);
        localToast.setView(localLinearLayout);
        initToast(localToast, 0);
      }
    }
  };

  public alert_thread(Context paramContext)
  {
    this.context = paramContext;
  }

  public alert_thread(Context paramContext, ErrMsg paramErrMsg)
  {
    this.context = paramContext;
    setErrMsg(paramErrMsg);
  }

  public ErrMsg getErrMsg()
  {
    ErrMsg localErrMsg1 = this.errMsg;
    ErrMsg localErrMsg2 = null;
    if (localErrMsg1 != null);
    try
    {
      localErrMsg2 = (ErrMsg)this.errMsg.clone();
      return localErrMsg2;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      localCloneNotSupportedException.printStackTrace();
    }
    return null;
  }

  public void run()
  {
    new Handler(Looper.getMainLooper()).post(this.myRunnable);
  }

  public void setErrMsg(ErrMsg paramErrMsg)
  {
    if (paramErrMsg != null)
      try
      {
        this.errMsg = ((ErrMsg)paramErrMsg.clone());
        return;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
        localCloneNotSupportedException.printStackTrace();
        this.errMsg = null;
        return;
      }
    this.errMsg = null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.alert_thread
 * JD-Core Version:    0.6.0
 */
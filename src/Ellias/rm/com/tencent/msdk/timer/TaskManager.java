package com.tencent.msdk.timer;

import com.tencent.msdk.Singleton;
import com.tencent.msdk.timer.task.BaseTask;
import com.tencent.msdk.tools.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class TaskManager
{
  public static Singleton<TaskManager> gDefault = new Singleton()
  {
    protected TaskManager create()
    {
      TaskManager localTaskManager = new TaskManager(null);
      TaskManager.access$102(localTaskManager, new Timer());
      return localTaskManager;
    }
  };
  private final int PERIOD = 60000;
  private ArrayList<BaseTask> taskList = new ArrayList();
  private Timer timer = null;

  public int addTask(BaseTask paramBaseTask)
  {
    synchronized (this.taskList)
    {
      if (this.taskList.contains(paramBaseTask))
        return -1;
      this.taskList.add(paramBaseTask);
      return 0;
    }
  }

  public void startTimer()
  {
    this.timer.schedule(new TaskDispatcher(null), 0L, 60000L);
  }

  private class TaskDispatcher extends TimerTask
  {
    private TaskDispatcher()
    {
    }

    public void run()
    {
      Logger.d("TaskDispatcher run");
      synchronized (TaskManager.this.taskList)
      {
        Iterator localIterator = TaskManager.this.taskList.iterator();
        while (localIterator.hasNext())
        {
          BaseTask localBaseTask = (BaseTask)localIterator.next();
          localBaseTask.increaseNotifiedTimes();
          if (localBaseTask.getNotifiedTimes() != localBaseTask.getMyInterval())
            continue;
          localBaseTask.run();
          localBaseTask.resetNotifiedTimes();
        }
      }
      monitorexit;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.timer.TaskManager
 * JD-Core Version:    0.6.0
 */
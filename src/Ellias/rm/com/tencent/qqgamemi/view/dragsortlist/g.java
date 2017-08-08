package com.tencent.qqgamemi.view.dragsortlist;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class g
{
  StringBuilder a = new StringBuilder();
  File b = new File(Environment.getExternalStorageDirectory(), "dslv_state.txt");
  private int d = 0;
  private int e = 0;
  private boolean f = false;

  public g(DragSortListView paramDragSortListView)
  {
    if (!this.b.exists());
    try
    {
      this.b.createNewFile();
      Log.d("mobeta", "file created");
      return;
    }
    catch (IOException localIOException)
    {
      Log.w("mobeta", "Could not create dslv_state.txt");
      Log.d("mobeta", localIOException.getMessage());
    }
  }

  public void a()
  {
    this.a.append("<DSLVStates>\n");
    this.e = 0;
    this.f = true;
  }

  public void b()
  {
    if (!this.f);
    do
    {
      return;
      this.a.append("<DSLVState>\n");
      int i = this.c.getChildCount();
      int j = this.c.getFirstVisiblePosition();
      this.a.append("    <Positions>");
      for (int k = 0; k < i; k++)
        this.a.append(j + k).append(",");
      this.a.append("</Positions>\n");
      this.a.append("    <Tops>");
      for (int m = 0; m < i; m++)
        this.a.append(this.c.getChildAt(m).getTop()).append(",");
      this.a.append("</Tops>\n");
      this.a.append("    <Bottoms>");
      for (int n = 0; n < i; n++)
        this.a.append(this.c.getChildAt(n).getBottom()).append(",");
      this.a.append("</Bottoms>\n");
      this.a.append("    <FirstExpPos>").append(DragSortListView.l(this.c)).append("</FirstExpPos>\n");
      this.a.append("    <FirstExpBlankHeight>").append(DragSortListView.c(this.c, DragSortListView.l(this.c)) - DragSortListView.d(this.c, DragSortListView.l(this.c))).append("</FirstExpBlankHeight>\n");
      this.a.append("    <SecondExpPos>").append(DragSortListView.m(this.c)).append("</SecondExpPos>\n");
      this.a.append("    <SecondExpBlankHeight>").append(DragSortListView.c(this.c, DragSortListView.m(this.c)) - DragSortListView.d(this.c, DragSortListView.m(this.c))).append("</SecondExpBlankHeight>\n");
      this.a.append("    <SrcPos>").append(DragSortListView.h(this.c)).append("</SrcPos>\n");
      this.a.append("    <SrcHeight>").append(DragSortListView.j(this.c) + this.c.getDividerHeight()).append("</SrcHeight>\n");
      this.a.append("    <ViewHeight>").append(this.c.getHeight()).append("</ViewHeight>\n");
      this.a.append("    <LastY>").append(DragSortListView.x(this.c)).append("</LastY>\n");
      this.a.append("    <FloatY>").append(DragSortListView.r(this.c)).append("</FloatY>\n");
      this.a.append("    <ShuffleEdges>");
      for (int i1 = 0; i1 < i; i1++)
        this.a.append(DragSortListView.a(this.c, j + i1, this.c.getChildAt(i1).getTop())).append(",");
      this.a.append("</ShuffleEdges>\n");
      this.a.append("</DSLVState>\n");
      this.d = (1 + this.d);
    }
    while (this.d <= 1000);
    c();
    this.d = 0;
  }

  public void c()
  {
    if (!this.f)
      return;
    while (true)
    {
      try
      {
        int i = this.e;
        bool = false;
        if (i == 0)
        {
          FileWriter localFileWriter = new FileWriter(this.b, bool);
          localFileWriter.write(this.a.toString());
          this.a.delete(0, this.a.length());
          localFileWriter.flush();
          localFileWriter.close();
          this.e = (1 + this.e);
          return;
        }
      }
      catch (IOException localIOException)
      {
        return;
      }
      boolean bool = true;
    }
  }

  public void d()
  {
    if (this.f)
    {
      this.a.append("</DSLVStates>\n");
      c();
      this.f = false;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.g
 * JD-Core Version:    0.6.0
 */
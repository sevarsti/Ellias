package com.tencent.qqgamemi.ui;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.component.net.download.multiplex.FileDownload;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.plugin.QMiPluginManager;
import com.tencent.qqgamemi.plugin.ui.PluginListAdapter;
import com.tencent.qqgamemi.view.dragsortlist.DragSortController;
import com.tencent.qqgamemi.view.dragsortlist.DragSortListView;

public class PluginManagerDialog extends QMiDialog
{
  private static final String a = PluginManagerDialog.class.getSimpleName();
  private Context b = null;
  private DragSortListView c = null;
  private PluginListAdapter d = null;

  public PluginManagerDialog(Context paramContext)
  {
    super(paramContext, ResourceUtil.d("Qmi_Common_Dialog"));
    a(paramContext);
  }

  public PluginManagerDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    a(paramContext);
  }

  private void a(Context paramContext)
  {
    this.b = paramContext;
    View localView = getLayoutInflater().inflate(ResourceUtil.a("qmi_plugin_manager_dialog"), null);
    a(localView);
    addContentView(localView, new ViewGroup.LayoutParams(-1, -1));
    setCanceledOnTouchOutside(true);
    getWindow().setType(2003);
  }

  private void a(View paramView)
  {
    ((ImageView)paramView.findViewById(ResourceUtil.f("title_icon"))).setImageResource(ResourceUtil.c("qmi_ic_me_dialog_title"));
    ((TextView)paramView.findViewById(ResourceUtil.f("title_text"))).setText(ResourceUtil.b("qmi_plugin_title"));
    paramView.findViewById(ResourceUtil.f("title_close")).setOnClickListener(new an(this));
    paramView.findViewById(ResourceUtil.f("title_left_layout")).setOnClickListener(new ao(this));
    TextView localTextView = (TextView)paramView.findViewById(ResourceUtil.f("plugin_head"));
    SpannableString localSpannableString = new SpannableString(localTextView.getText().toString());
    localSpannableString.setSpan(new ImageSpan(this.b, ResourceUtil.c("qmi_ic_plugin_head_drag"), 1), 2, 4, 17);
    localTextView.setText(localSpannableString);
    this.c = ((DragSortListView)paramView.findViewById(ResourceUtil.f("plugin_manager_list")));
    this.c.setFloatAlpha(0.5F);
    this.c.setTrackDragSort(false);
    this.c.setCollapsedHeight(0);
    this.c.setSlideShuffleSpeed(0.8F);
    this.c.setDragEnabled(true);
    this.c.setMaxScrollSpeed(0.5F);
    DragSortController localDragSortController = new DragSortController(this.c, ResourceUtil.f("qmi_drag_handle"), 0, 1, 0, 0);
    localDragSortController.b(false);
    localDragSortController.a(true);
    localDragSortController.g(-16777216);
    this.c.setFloatViewManager(localDragSortController);
    this.c.setOnTouchListener(localDragSortController);
    this.d = new PluginListAdapter(this.b, this.c);
    this.c.setDropListener(this.d);
    ap localap = new ap(this, this.c, this.d);
    this.c.setFloatViewManager(localap);
    this.c.setOnTouchListener(localap);
    this.c.setAdapter(this.d);
    this.d.b();
  }

  public void dismiss()
  {
    super.dismiss();
    FileDownload.b();
    QMiPluginManager.a().l();
    this.d.a();
    QMiPluginManager.a().g();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.PluginManagerDialog
 * JD-Core Version:    0.6.0
 */
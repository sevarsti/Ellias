package com.tencent.qqgamemi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.Layout.Alignment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tencent.component.event.Event;
import com.tencent.component.event.EventCenter;
import com.tencent.component.event.EventSource;
import com.tencent.component.event.Observer;
import com.tencent.component.ui.widget.drawable.TextDrawable;
import com.tencent.component.ui.widget.image.MarkImageView;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.animation.ActionListener;
import com.tencent.qqgamemi.animation.AnimationManager;
import com.tencent.qqgamemi.business.PluginUndealCountManager;
import com.tencent.qqgamemi.business.PluginUndealCountManager.UndealCount;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.GameItem;
import com.tencent.qqgamemi.plugin.PluginItem;
import com.tencent.qqgamemi.plugin.QMiPluginManager;
import com.tencent.qqgamemi.report.UserAccessStatics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QMiViewManager
  implements Observer
{
  static final String a = QMiViewManager.class.getSimpleName();
  public static long h = 0L;
  private static final int k = 2;
  private static final int l = 3;
  private static final int p = 4;
  private static final int q = 7;
  Handler b;
  Context c = null;
  QMiWindowManager d = null;
  boolean e = false;
  boolean f = false;
  public QMiSpirit g;
  Point i = null;
  boolean j = false;
  private LayoutInflater m = null;
  private boolean n = false;
  private boolean o = true;
  private LinearLayout r;
  private LinearLayout s;
  private LinearLayout t;
  private View u;
  private List v = new ArrayList(8);
  private GestureDetector w = new GestureDetector(this.c, new w(this));
  private View.OnClickListener x = new x(this);
  private View.OnClickListener y = new y(this);
  private ActionListener z = new z(this);

  public QMiViewManager(Context paramContext)
  {
    this.c = paramContext;
    this.m = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.d = new QMiWindowManager(paramContext);
    this.b = new ab(this, paramContext);
    this.g = new QMiSpirit(this, paramContext, this.d);
    m();
    EventCenter.getInstance().addUIObserver(this, "QmiUndealCount", new int[] { 1 });
  }

  public static final int a(float paramFloat, Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return (int)(paramFloat * localDisplayMetrics.density);
  }

  private Drawable a(Context paramContext)
  {
    return paramContext.getResources().getDrawable(ResourceUtil.c("qmi_plugin_icon_me"));
  }

  private View a(PluginItem paramPluginItem)
  {
    View localView = this.m.inflate(ResourceUtil.a("qmi_inflate_item"), null, false);
    TextView localTextView = (TextView)localView.findViewById(ResourceUtil.f("inflate_item_name"));
    localTextView.setText(paramPluginItem.getName());
    localTextView.setBackgroundResource(ResourceUtil.c("qmi_inflate_item_text_bg"));
    MarkImageView localMarkImageView = (MarkImageView)localView.findViewById(ResourceUtil.f("inflate_item_icon"));
    Drawable localDrawable = paramPluginItem.getIcon(this.c);
    if (localDrawable == null)
      localDrawable = this.c.getResources().getDrawable(ResourceUtil.c("qmi_default_plugin_icon"));
    if (localDrawable != null)
      localMarkImageView.setImageDrawable(localDrawable);
    localView.setTag(paramPluginItem);
    localView.setOnClickListener(this.y);
    return localView;
  }

  private void a(View paramView)
  {
    MarkImageView localMarkImageView;
    PluginUndealCountManager.UndealCount localUndealCount;
    if (paramView != null)
    {
      localMarkImageView = (MarkImageView)paramView.findViewById(ResourceUtil.f("inflate_item_icon"));
      if (localMarkImageView != null)
      {
        PluginItem localPluginItem = (PluginItem)paramView.getTag();
        if (localPluginItem != null)
        {
          localUndealCount = PluginUndealCountManager.a().a(localPluginItem.id);
          if (localUndealCount == null)
            break label90;
          if (localUndealCount.c != 1)
            break label70;
          a(localMarkImageView, localUndealCount.d);
        }
      }
    }
    return;
    label70: if (localUndealCount.c == 0)
    {
      a(localMarkImageView);
      return;
    }
    b(localMarkImageView);
    return;
    label90: b(localMarkImageView);
  }

  private void a(MarkImageView paramMarkImageView)
  {
    paramMarkImageView.setMarker(ResourceUtil.c("qmi_dot"));
    paramMarkImageView.setMarkerPosition(3);
    paramMarkImageView.setMarkerPaddingOffset(-a(15.0F, this.c), 0);
    paramMarkImageView.setMarkerVisible(true);
  }

  private void a(MarkImageView paramMarkImageView, int paramInt)
  {
    if (paramInt > 0)
    {
      TextDrawable localTextDrawable = new TextDrawable(this.c.getApplicationContext());
      int i1 = 16;
      String str;
      if (paramInt >= 100)
        str = "...";
      while (true)
      {
        int i3 = a(i1, this.c);
        int i4 = a(16.0F, this.c);
        localTextDrawable.a(str);
        localTextDrawable.a(1, 12.0F);
        localTextDrawable.a(this.c.getResources().getColorStateList(ResourceUtil.g("appfw_white")));
        localTextDrawable.c(ResourceUtil.c("qmi_num_dot"));
        localTextDrawable.a(Layout.Alignment.ALIGN_CENTER);
        paramMarkImageView.setMarkerPosition(3);
        paramMarkImageView.setMarkerSize(i3, i4);
        paramMarkImageView.setMarkerPaddingOffset(-a(13.0F, this.c), 0);
        paramMarkImageView.setMarker(localTextDrawable);
        paramMarkImageView.setMarkerVisible(true);
        return;
        str = String.valueOf(paramInt);
        if (paramInt < 10)
          continue;
        int i2 = 20;
      }
    }
    b(paramMarkImageView);
  }

  private void a(List paramList)
  {
    TLog.c("Benson", "loadAndShowInflateView");
    if (this.g.m())
    {
      Message localMessage = new Message();
      localMessage.obj = paramList;
      localMessage.what = 2;
      this.b.sendMessage(localMessage);
    }
  }

  private int b(int paramInt)
  {
    int i1 = paramInt + QMiWindowManager.e().width / 2;
    int i2 = QMiWindowManager.a().getWidth() - paramInt - QMiWindowManager.e().width / 2;
    if (i1 < i2)
      return i1;
    return i2;
  }

  private void b(MarkImageView paramMarkImageView)
  {
    paramMarkImageView.setMarker(null);
    paramMarkImageView.setMarkerVisible(false);
  }

  private int c(int paramInt)
  {
    int i1 = paramInt + QMiWindowManager.e().height / 2;
    int i2 = QMiWindowManager.a().getHeight() - paramInt - QMiWindowManager.e().height / 2;
    if (i1 < i2)
      return i1;
    return i2;
  }

  private int d(int paramInt)
  {
    if (paramInt + QMiWindowManager.e().height / 2 <= QMiWindowManager.a().getHeight() / 2)
      return 0;
    return QMiWindowManager.a().getHeight();
  }

  private void m()
  {
    View localView = View.inflate(this.c, ResourceUtil.a("qmi_inflate_layout"), null);
    if (localView != null)
    {
      this.r = ((LinearLayout)localView.findViewById(ResourceUtil.f("inflate_layout")));
      if (this.r != null)
      {
        this.s = ((LinearLayout)localView.findViewById(ResourceUtil.f("inflate_layout0")));
        this.t = ((LinearLayout)localView.findViewById(ResourceUtil.f("inflate_layout1")));
        localView.setOnTouchListener(new u(this));
        this.d.a(localView);
      }
    }
    this.u = View.inflate(this.c, ResourceUtil.a("qmi_inflate_bg"), null);
    if (this.u != null)
    {
      this.u.setOnTouchListener(new v(this));
      this.d.b(this.u);
    }
  }

  private List n()
  {
    List localList = QMiPluginManager.a().i();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      if (!((PluginItem)localIterator.next()).isHide)
        continue;
      localIterator.remove();
    }
    return localList;
  }

  private View o()
  {
    View localView = this.m.inflate(ResourceUtil.a("qmi_inflate_item"), null, false);
    TextView localTextView = (TextView)localView.findViewById(ResourceUtil.f("inflate_item_name"));
    localTextView.setText("我");
    localTextView.setBackgroundResource(ResourceUtil.c("qmi_inflate_item_text_bg"));
    ImageView localImageView = (ImageView)localView.findViewById(ResourceUtil.f("inflate_item_icon"));
    Drawable localDrawable = a(this.c);
    if (localDrawable != null)
      localImageView.setImageDrawable(localDrawable);
    localImageView.setOnClickListener(this.x);
    return localView;
  }

  private void p()
  {
    if (l())
    {
      h();
      return;
    }
    a(true);
    i();
    AnimationManager.i.c(this.g.f(), this.z);
    UserAccessStatics.getInstance(this.c).addQMiAction(202, System.currentTimeMillis(), QMiCommon.a(this.c), null);
  }

  private boolean q()
  {
    return QMiWindowManager.e().x == a(QMiWindowManager.e().x);
  }

  private View r()
  {
    ImageView localImageView = new ImageView(this.c);
    localImageView.setImageResource(ResourceUtil.c("qmi_icon_menu_shadow"));
    localImageView.setScaleType(ImageView.ScaleType.CENTER);
    localImageView.setOnTouchListener(new aa(this));
    return localImageView;
  }

  private void s()
  {
    if ((this.n) && (this.v != null) && (this.v.size() > 0))
    {
      ArrayList localArrayList = new ArrayList(this.v);
      for (int i1 = 0; i1 < localArrayList.size(); i1++)
        a((View)localArrayList.get(i1));
    }
  }

  int a(int paramInt)
  {
    if (paramInt + QMiWindowManager.e().width / 2 <= QMiWindowManager.a().getWidth() / 2)
      return 0;
    return QMiWindowManager.a().getWidth();
  }

  public void a()
  {
    this.g.a(false);
    QMiWindowManager.g();
    this.g.j();
    a(false);
  }

  public void a(boolean paramBoolean)
  {
    this.n = paramBoolean;
  }

  public boolean a(GameItem paramGameItem)
  {
    this.g.a(true);
    if (QMiWindowManager.a(paramGameItem))
    {
      this.g.k();
      this.g.i();
      this.g.g();
      this.g.n();
      return true;
    }
    return false;
  }

  boolean b()
  {
    return QMiWindowManager.e().x <= 0;
  }

  boolean c()
  {
    return QMiWindowManager.e().x >= QMiWindowManager.a().getWidth() - QMiWindowManager.e().width;
  }

  boolean d()
  {
    return QMiWindowManager.e().y <= 0;
  }

  boolean e()
  {
    return QMiWindowManager.e().y >= QMiWindowManager.a().getHeight() - QMiWindowManager.e().height;
  }

  Point f()
  {
    Point localPoint = new Point(QMiWindowManager.e().x, QMiWindowManager.e().y);
    TLog.c(a, "getBorderTargetPosition point:" + localPoint);
    int i1 = b(localPoint.x);
    int i2 = c(localPoint.y);
    TLog.c(a, "getBorderTargetPosition offsetX:" + i1 + ", offsetY:" + i2);
    if (i1 <= i2)
    {
      localPoint.x = a(localPoint.x);
      TLog.c(a, "getBorderTargetPosition x:" + localPoint.x);
      return localPoint;
    }
    localPoint.y = d(localPoint.y);
    TLog.c(a, "getBorderTargetPosition y:" + localPoint.y);
    return localPoint;
  }

  void g()
  {
    TLog.c(a, "float View is Clicked.");
    long l1 = System.currentTimeMillis();
    if (Math.abs(l1 - h) > 200L)
    {
      h = l1;
      p();
    }
  }

  public void h()
  {
    TLog.c(a, "hideInfalteView");
    QMiWindowManager.j();
    a(false);
    this.g.i();
    if (this.j)
      this.g.a(this);
  }

  public void i()
  {
    int i1 = 4;
    TLog.c(a, "showInfalteView");
    a(true);
    List localList = n();
    if (q())
    {
      a(localList);
      return;
    }
    int i2 = b(QMiWindowManager.e().x);
    if (localList.size() > i1);
    while (true)
    {
      int i3 = this.c.getResources().getDimensionPixelSize(ResourceUtil.j("qmi_menu_item_size")) * (i1 + 1);
      WindowManager localWindowManager = (WindowManager)this.c.getSystemService("window");
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
      if (localDisplayMetrics.widthPixels - i2 >= i3)
        break;
      this.g.b(this);
      return;
      i1 = localList.size();
    }
    a(localList);
  }

  void j()
  {
    a(n());
  }

  public void k()
  {
    if (l());
    do
    {
      return;
      if (e())
      {
        this.g.r = false;
        this.g.s = false;
        this.g.B = false;
        this.g.a(this, 2);
        return;
      }
      if (b())
      {
        this.g.r = true;
        this.g.s = true;
        this.g.B = false;
        this.g.b(this, 1);
        return;
      }
      if (!d())
        continue;
      this.g.r = false;
      this.g.s = true;
      this.g.B = false;
      this.g.a(this, -2);
      return;
    }
    while (!c());
    this.g.r = true;
    this.g.s = false;
    this.g.B = false;
    this.g.b(this, -1);
  }

  public boolean l()
  {
    return this.n;
  }

  public void onNotify(Event paramEvent)
  {
    if ("QmiUndealCount".equals(paramEvent.source.name));
    switch (paramEvent.what)
    {
    default:
      return;
    case 1:
    }
    s();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.QMiViewManager
 * JD-Core Version:    0.6.0
 */
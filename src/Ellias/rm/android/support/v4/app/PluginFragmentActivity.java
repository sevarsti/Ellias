package android.support.v4.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.tencent.component.app.BaseActivity;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class PluginFragmentActivity extends BaseActivity
{
  static final String a = "plugin:android:support:fragments";
  static final int b = 1;
  static final int c = 2;
  private static final String q = "PluginFragmentActivity";
  private static final int r = 11;
  final Handler d = new a(this);
  final FragmentManagerImpl e = new FragmentManagerImpl();
  final FragmentContainer f = new b(this);
  boolean g;
  boolean h;
  boolean i;
  boolean j;
  boolean k;
  boolean l;
  boolean m;
  boolean n;
  HashMap o;
  LoaderManagerImpl p;

  private static String a(View paramView)
  {
    char c1 = 'F';
    char c2 = '.';
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append(paramView.getClass().getName());
    localStringBuilder.append('{');
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(paramView)));
    localStringBuilder.append(' ');
    switch (paramView.getVisibility())
    {
    default:
      localStringBuilder.append(c2);
    case 0:
    case 4:
    case 8:
    }
    while (true)
    {
      char c3;
      label108: char c4;
      label126: char c5;
      label143: char c6;
      label161: char c7;
      label179: char c8;
      label197: char c9;
      label215: label236: char c10;
      label253: int i1;
      Resources localResources;
      if (paramView.isFocusable())
      {
        c3 = c1;
        localStringBuilder.append(c3);
        if (!paramView.isEnabled())
          break label533;
        c4 = 'E';
        localStringBuilder.append(c4);
        if (!paramView.willNotDraw())
          break label539;
        c5 = c2;
        localStringBuilder.append(c5);
        if (!paramView.isHorizontalScrollBarEnabled())
          break label546;
        c6 = 'H';
        localStringBuilder.append(c6);
        if (!paramView.isVerticalScrollBarEnabled())
          break label552;
        c7 = 'V';
        localStringBuilder.append(c7);
        if (!paramView.isClickable())
          break label558;
        c8 = 'C';
        localStringBuilder.append(c8);
        if (!paramView.isLongClickable())
          break label564;
        c9 = 'L';
        localStringBuilder.append(c9);
        localStringBuilder.append(' ');
        if (!paramView.isFocused())
          break label570;
        localStringBuilder.append(c1);
        if (!paramView.isSelected())
          break label575;
        c10 = 'S';
        localStringBuilder.append(c10);
        if (paramView.isPressed())
          c2 = 'P';
        localStringBuilder.append(c2);
        localStringBuilder.append(' ');
        localStringBuilder.append(paramView.getLeft());
        localStringBuilder.append(',');
        localStringBuilder.append(paramView.getTop());
        localStringBuilder.append('-');
        localStringBuilder.append(paramView.getRight());
        localStringBuilder.append(',');
        localStringBuilder.append(paramView.getBottom());
        i1 = paramView.getId();
        if (i1 != -1)
        {
          localStringBuilder.append(" #");
          localStringBuilder.append(Integer.toHexString(i1));
          localResources = paramView.getResources();
          if ((i1 != 0) && (localResources != null))
            switch (0xFF000000 & i1)
            {
            default:
            case 2130706432:
            case 16777216:
            }
        }
      }
      try
      {
        String str1 = localResources.getResourcePackageName(i1);
        while (true)
        {
          String str2 = localResources.getResourceTypeName(i1);
          String str3 = localResources.getResourceEntryName(i1);
          localStringBuilder.append(" ");
          localStringBuilder.append(str1);
          localStringBuilder.append(":");
          localStringBuilder.append(str2);
          localStringBuilder.append("/");
          localStringBuilder.append(str3);
          label485: localStringBuilder.append("}");
          return localStringBuilder.toString();
          localStringBuilder.append('V');
          break;
          localStringBuilder.append('I');
          break;
          localStringBuilder.append('G');
          break;
          c3 = c2;
          break label108;
          label533: c4 = c2;
          break label126;
          label539: c5 = 'D';
          break label143;
          label546: c6 = c2;
          break label161;
          label552: c7 = c2;
          break label179;
          label558: c8 = c2;
          break label197;
          label564: c9 = c2;
          break label215;
          label570: c1 = c2;
          break label236;
          label575: c10 = c2;
          break label253;
          str1 = "app";
          continue;
          str1 = "android";
        }
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        break label485;
      }
    }
  }

  private void a(String paramString, PrintWriter paramPrintWriter, View paramView)
  {
    paramPrintWriter.print(paramString);
    if (paramView == null)
      paramPrintWriter.println("null");
    while (true)
    {
      return;
      paramPrintWriter.println(a(paramView));
      if (!(paramView instanceof ViewGroup))
        continue;
      ViewGroup localViewGroup = (ViewGroup)paramView;
      int i1 = localViewGroup.getChildCount();
      if (i1 <= 0)
        continue;
      String str = paramString + "  ";
      for (int i2 = 0; i2 < i1; i2++)
        a(str, paramPrintWriter, localViewGroup.getChildAt(i2));
    }
  }

  void doReallyStop(boolean paramBoolean)
  {
    if (!this.j)
    {
      this.j = true;
      this.k = paramBoolean;
      this.d.removeMessages(1);
      onReallyStop();
    }
  }

  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    if (Build.VERSION.SDK_INT >= 11);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("Local FragmentActivity ");
    paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this)));
    paramPrintWriter.println(" State:");
    String str = paramString + "  ";
    paramPrintWriter.print(str);
    paramPrintWriter.print("mCreated=");
    paramPrintWriter.print(this.g);
    paramPrintWriter.print("mResumed=");
    paramPrintWriter.print(this.h);
    paramPrintWriter.print(" mStopped=");
    paramPrintWriter.print(this.i);
    paramPrintWriter.print(" mReallyStopped=");
    paramPrintWriter.println(this.j);
    paramPrintWriter.print(str);
    paramPrintWriter.print("mLoadersStarted=");
    paramPrintWriter.println(this.n);
    if (this.p != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("Loader Manager ");
      paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this.p)));
      paramPrintWriter.println(":");
      this.p.dump(paramString + "  ", paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
    this.e.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.println("View Hierarchy:");
    a(paramString + "  ", paramPrintWriter, getWindow().getDecorView());
  }

  public Object getLastCustomNonConfigurationInstance()
  {
    d locald = (d)getLastNonConfigurationInstance();
    if (locald != null)
      return locald.b;
    return null;
  }

  LoaderManagerImpl getLoaderManager(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.o == null)
      this.o = new HashMap();
    LoaderManagerImpl localLoaderManagerImpl = (LoaderManagerImpl)this.o.get(paramString);
    if (localLoaderManagerImpl == null)
    {
      if (paramBoolean2)
      {
        localLoaderManagerImpl = new LoaderManagerImpl(paramString, this, paramBoolean1);
        this.o.put(paramString, localLoaderManagerImpl);
      }
      return localLoaderManagerImpl;
    }
    localLoaderManagerImpl.updateActivity(this);
    return localLoaderManagerImpl;
  }

  public FragmentManager getSupportFragmentManager()
  {
    return this.e;
  }

  public LoaderManager getSupportLoaderManager()
  {
    if (this.p != null)
      return this.p;
    this.m = true;
    this.p = getLoaderManager(null, this.n, true);
    return this.p;
  }

  void invalidateSupportFragment(String paramString)
  {
    if (this.o != null)
    {
      LoaderManagerImpl localLoaderManagerImpl = (LoaderManagerImpl)this.o.get(paramString);
      if ((localLoaderManagerImpl != null) && (!localLoaderManagerImpl.mRetaining))
      {
        localLoaderManagerImpl.doDestroy();
        this.o.remove(paramString);
      }
    }
  }

  protected Fragment newFragment(String paramString, Bundle paramBundle)
  {
    return null;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.e.noteStateNotSaved();
    int i1 = paramInt1 >> 16;
    if (i1 != 0)
    {
      int i2 = i1 - 1;
      if ((this.e.mActive == null) || (i2 < 0) || (i2 >= this.e.mActive.size()))
      {
        Log.w("PluginFragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(paramInt1));
        return;
      }
      Fragment localFragment = (Fragment)this.e.mActive.get(i2);
      if (localFragment == null)
      {
        Log.w("PluginFragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(paramInt1));
        return;
      }
      localFragment.onActivityResult(0xFFFF & paramInt1, paramInt2, paramIntent);
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onAttachFragment(Fragment paramFragment)
  {
  }

  public void onBackPressed()
  {
    if (!this.e.popBackStackImmediate())
      finish();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.e.dispatchConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    this.e.attachActivity(this, this.f, null);
    if (getLayoutInflater().getFactory() == null)
      getLayoutInflater().setFactory(this);
    super.onCreate(paramBundle);
    d locald = (d)getLastNonConfigurationInstance();
    if (locald != null)
      this.o = locald.e;
    Parcelable localParcelable;
    FragmentManagerImpl localFragmentManagerImpl;
    if (paramBundle != null)
    {
      localParcelable = paramBundle.getParcelable("plugin:android:support:fragments");
      localFragmentManagerImpl = this.e;
      if (locald == null)
        break label99;
    }
    label99: for (ArrayList localArrayList = locald.d; ; localArrayList = null)
    {
      localFragmentManagerImpl.restoreAllState(localParcelable, localArrayList);
      this.e.dispatchCreate();
      return;
    }
  }

  public boolean onCreatePanelMenu(int paramInt, Menu paramMenu)
  {
    if (paramInt == 0)
    {
      boolean bool = super.onCreatePanelMenu(paramInt, paramMenu) | this.e.dispatchCreateOptionsMenu(paramMenu, getMenuInflater());
      if (Build.VERSION.SDK_INT >= 11)
        return bool;
      return true;
    }
    return super.onCreatePanelMenu(paramInt, paramMenu);
  }

  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    if (!"fragment".equals(paramString))
      return super.onCreateView(paramString, paramContext, paramAttributeSet);
    String str1 = paramAttributeSet.getAttributeValue(null, "class");
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, c.a);
    if (str1 == null)
      str1 = localTypedArray.getString(0);
    int i1 = localTypedArray.getResourceId(1, -1);
    String str2 = localTypedArray.getString(2);
    localTypedArray.recycle();
    int i2 = 0;
    if (0 != 0)
      i2 = null.getId();
    if ((i2 == -1) && (i1 == -1) && (str2 == null))
      throw new IllegalArgumentException(paramAttributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str1);
    Object localObject;
    int i3;
    if (i1 != -1)
    {
      localObject = this.e.findFragmentById(i1);
      if ((localObject == null) && (str2 != null))
        localObject = this.e.findFragmentByTag(str2);
      if ((localObject == null) && (i2 != -1))
        localObject = this.e.findFragmentById(i2);
      if (FragmentManagerImpl.DEBUG)
        Log.v("PluginFragmentActivity", "onCreateView: id=0x" + Integer.toHexString(i1) + " fname=" + str1 + " existing=" + localObject);
      if (localObject != null)
        break label408;
      Fragment localFragment = newFragment(str1, null);
      localFragment.mFromLayout = true;
      if (i1 == 0)
        break label401;
      i3 = i1;
      label290: localFragment.mFragmentId = i3;
      localFragment.mContainerId = i2;
      localFragment.mTag = str2;
      localFragment.mInLayout = true;
      localFragment.mFragmentManager = this.e;
      localFragment.onInflate(this, paramAttributeSet, localFragment.mSavedFragmentState);
      this.e.addFragment(localFragment, true);
      localObject = localFragment;
    }
    while (true)
    {
      if (((Fragment)localObject).mView != null)
        break label531;
      throw new IllegalStateException("Fragment " + str1 + " did not create a view.");
      localObject = null;
      break;
      label401: i3 = i2;
      break label290;
      label408: if (((Fragment)localObject).mInLayout)
        throw new IllegalArgumentException(paramAttributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(i1) + ", tag " + str2 + ", or parent id 0x" + Integer.toHexString(i2) + " with another fragment for " + str1);
      ((Fragment)localObject).mInLayout = true;
      if (!((Fragment)localObject).mRetaining)
        ((Fragment)localObject).onInflate(this, paramAttributeSet, ((Fragment)localObject).mSavedFragmentState);
      this.e.moveToState((Fragment)localObject);
    }
    label531: if (i1 != 0)
      ((Fragment)localObject).mView.setId(i1);
    if (((Fragment)localObject).mView.getTag() == null)
      ((Fragment)localObject).mView.setTag(str2);
    return (View)((Fragment)localObject).mView;
  }

  protected void onDestroy()
  {
    super.onDestroy();
    doReallyStop(false);
    this.e.dispatchDestroy();
    if (this.p != null)
      this.p.doDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((Build.VERSION.SDK_INT < 5) && (paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
    {
      onBackPressed();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onLowMemory()
  {
    super.onLowMemory();
    this.e.dispatchLowMemory();
  }

  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    if (super.onMenuItemSelected(paramInt, paramMenuItem))
      return true;
    switch (paramInt)
    {
    default:
      return false;
    case 0:
      return this.e.dispatchOptionsItemSelected(paramMenuItem);
    case 6:
    }
    return this.e.dispatchContextItemSelected(paramMenuItem);
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    this.e.noteStateNotSaved();
  }

  public void onPanelClosed(int paramInt, Menu paramMenu)
  {
    switch (paramInt)
    {
    default:
    case 0:
    }
    while (true)
    {
      super.onPanelClosed(paramInt, paramMenu);
      return;
      this.e.dispatchOptionsMenuClosed(paramMenu);
    }
  }

  protected void onPause()
  {
    super.onPause();
    this.h = false;
    if (this.d.hasMessages(2))
    {
      this.d.removeMessages(2);
      onResumeFragments();
    }
    this.e.dispatchPause();
  }

  protected void onPostResume()
  {
    super.onPostResume();
    this.d.removeMessages(2);
    onResumeFragments();
    this.e.execPendingActions();
  }

  public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu)
  {
    if ((paramInt == 0) && (paramMenu != null))
    {
      if (this.l)
      {
        this.l = false;
        paramMenu.clear();
        onCreatePanelMenu(paramInt, paramMenu);
      }
      boolean bool1 = super.onPreparePanel(paramInt, paramView, paramMenu) | this.e.dispatchPrepareOptionsMenu(paramMenu);
      int i1 = 0;
      if (bool1)
      {
        boolean bool2 = paramMenu.hasVisibleItems();
        i1 = 0;
        if (bool2)
          i1 = 1;
      }
      return i1;
    }
    return super.onPreparePanel(paramInt, paramView, paramMenu);
  }

  void onReallyStop()
  {
    if (this.n)
    {
      this.n = false;
      if (this.p != null)
      {
        if (this.k)
          break label41;
        this.p.doStop();
      }
    }
    while (true)
    {
      this.e.dispatchReallyStop();
      return;
      label41: this.p.doRetain();
    }
  }

  protected void onResume()
  {
    super.onResume();
    this.d.sendEmptyMessage(2);
    this.h = true;
    this.e.execPendingActions();
  }

  protected void onResumeFragments()
  {
    this.e.dispatchResume();
  }

  public Object onRetainCustomNonConfigurationInstance()
  {
    if (this.i)
      doReallyStop(true);
    ArrayList localArrayList = this.e.retainNonConfig();
    HashMap localHashMap = this.o;
    int i1 = 0;
    if (localHashMap != null)
    {
      LoaderManagerImpl[] arrayOfLoaderManagerImpl = new LoaderManagerImpl[this.o.size()];
      this.o.values().toArray(arrayOfLoaderManagerImpl);
      i1 = 0;
      if (arrayOfLoaderManagerImpl != null)
      {
        int i2 = 0;
        if (i1 < arrayOfLoaderManagerImpl.length)
        {
          LoaderManagerImpl localLoaderManagerImpl = arrayOfLoaderManagerImpl[i1];
          if (localLoaderManagerImpl.mRetaining)
            i2 = 1;
          while (true)
          {
            i1++;
            break;
            localLoaderManagerImpl.doDestroy();
            this.o.remove(localLoaderManagerImpl.mWho);
          }
        }
        i1 = i2;
      }
    }
    if ((localArrayList == null) && (i1 == 0))
      return null;
    d locald = new d();
    locald.a = null;
    locald.c = null;
    locald.d = localArrayList;
    locald.e = this.o;
    return locald;
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    Parcelable localParcelable = this.e.saveAllState();
    if (localParcelable != null)
      paramBundle.putParcelable("plugin:android:support:fragments", localParcelable);
  }

  protected void onStart()
  {
    super.onStart();
    this.i = false;
    this.j = false;
    this.d.removeMessages(1);
    if (!this.g)
    {
      this.g = true;
      this.e.dispatchActivityCreated();
    }
    this.e.noteStateNotSaved();
    this.e.execPendingActions();
    if (!this.n)
    {
      this.n = true;
      if (this.p == null)
        break label162;
      this.p.doStart();
    }
    while (true)
    {
      this.m = true;
      this.e.dispatchStart();
      if (this.o == null)
        break;
      LoaderManagerImpl[] arrayOfLoaderManagerImpl = new LoaderManagerImpl[this.o.size()];
      this.o.values().toArray(arrayOfLoaderManagerImpl);
      int i1 = 0;
      if (arrayOfLoaderManagerImpl == null)
        break;
      while (i1 < arrayOfLoaderManagerImpl.length)
      {
        LoaderManagerImpl localLoaderManagerImpl = arrayOfLoaderManagerImpl[i1];
        localLoaderManagerImpl.finishRetain();
        localLoaderManagerImpl.doReportStart();
        i1++;
      }
      label162: if (this.m)
        continue;
      this.p = getLoaderManager(null, this.n, false);
      if ((this.p == null) || (this.p.mStarted))
        continue;
      this.p.doStart();
    }
  }

  protected void onStop()
  {
    super.onStop();
    this.i = true;
    this.d.sendEmptyMessage(1);
    this.e.dispatchStop();
  }

  public void supportInvalidateOptionsMenu()
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      ActivityCompatHoneycomb.invalidateOptionsMenu(this);
      return;
    }
    this.l = true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     android.support.v4.app.PluginFragmentActivity
 * JD-Core Version:    0.6.0
 */
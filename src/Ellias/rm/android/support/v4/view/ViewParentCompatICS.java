package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

public class ViewParentCompatICS
{
  public static boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    return paramViewParent.requestSendAccessibilityEvent(paramView, paramAccessibilityEvent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     android.support.v4.view.ViewParentCompatICS
 * JD-Core Version:    0.6.0
 */
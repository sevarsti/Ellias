package com.pay.ui.payCenter;

import android.content.Context;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class APGameListGoodsPicAdapter extends BaseAdapter
{
  private Context a;
  private ArrayList b;

  public APGameListGoodsPicAdapter(Context paramContext, ArrayList paramArrayList)
  {
    this.a = paramContext;
    this.b = paramArrayList;
  }

  public int getCount()
  {
    if (this.b != null)
      return this.b.size();
    return 0;
  }

  public Object getItem(int paramInt)
  {
    return this.b.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  // ERROR //
  public android.view.View getView(int paramInt, android.view.View paramView, android.view.ViewGroup paramViewGroup)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +167 -> 168
    //   4: aload_0
    //   5: getfield 15	com/pay/ui/payCenter/APGameListGoodsPicAdapter:a	Landroid/content/Context;
    //   8: invokestatic 41	android/view/LayoutInflater:from	(Landroid/content/Context;)Landroid/view/LayoutInflater;
    //   11: aload_0
    //   12: getfield 15	com/pay/ui/payCenter/APGameListGoodsPicAdapter:a	Landroid/content/Context;
    //   15: ldc 43
    //   17: invokestatic 49	com/pay/tool/APCommMethod:getLayoutId	(Landroid/content/Context;Ljava/lang/String;)I
    //   20: aconst_null
    //   21: invokevirtual 53	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;)Landroid/view/View;
    //   24: astore 13
    //   26: aload 13
    //   28: astore 4
    //   30: aload_0
    //   31: getfield 17	com/pay/ui/payCenter/APGameListGoodsPicAdapter:b	Ljava/util/ArrayList;
    //   34: invokevirtual 24	java/util/ArrayList:size	()I
    //   37: ifle +128 -> 165
    //   40: aload_0
    //   41: getfield 17	com/pay/ui/payCenter/APGameListGoodsPicAdapter:b	Ljava/util/ArrayList;
    //   44: iload_1
    //   45: invokevirtual 29	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   48: checkcast 55	com/pay/data/mp/APMPGoodsItem
    //   51: getfield 59	com/pay/data/mp/APMPGoodsItem:num	Ljava/lang/String;
    //   54: astore 8
    //   56: aload_0
    //   57: getfield 17	com/pay/ui/payCenter/APGameListGoodsPicAdapter:b	Ljava/util/ArrayList;
    //   60: iload_1
    //   61: invokevirtual 29	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   64: checkcast 55	com/pay/data/mp/APMPGoodsItem
    //   67: getfield 62	com/pay/data/mp/APMPGoodsItem:url	Ljava/lang/String;
    //   70: astore 9
    //   72: aload 4
    //   74: aload_0
    //   75: getfield 15	com/pay/ui/payCenter/APGameListGoodsPicAdapter:a	Landroid/content/Context;
    //   78: ldc 64
    //   80: invokestatic 67	com/pay/tool/APCommMethod:getId	(Landroid/content/Context;Ljava/lang/String;)I
    //   83: invokevirtual 73	android/view/View:findViewById	(I)Landroid/view/View;
    //   86: checkcast 75	android/widget/TextView
    //   89: astore 10
    //   91: aload 4
    //   93: aload_0
    //   94: getfield 15	com/pay/ui/payCenter/APGameListGoodsPicAdapter:a	Landroid/content/Context;
    //   97: ldc 77
    //   99: invokestatic 67	com/pay/tool/APCommMethod:getId	(Landroid/content/Context;Ljava/lang/String;)I
    //   102: invokevirtual 73	android/view/View:findViewById	(I)Landroid/view/View;
    //   105: checkcast 79	android/widget/ImageView
    //   108: astore 11
    //   110: aload 10
    //   112: aload 8
    //   114: invokevirtual 83	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   117: aload_0
    //   118: getfield 15	com/pay/ui/payCenter/APGameListGoodsPicAdapter:a	Landroid/content/Context;
    //   121: invokestatic 89	com/pay/image/cache/APImageCache:getInstance	(Landroid/content/Context;)Lcom/pay/image/cache/APImageCache;
    //   124: aload 11
    //   126: aload 9
    //   128: iconst_0
    //   129: invokevirtual 93	com/pay/image/cache/APImageCache:displayImage	(Landroid/widget/ImageView;Ljava/lang/String;I)V
    //   132: aload 4
    //   134: areturn
    //   135: astore 12
    //   137: aload 12
    //   139: astore 6
    //   141: aload_2
    //   142: astore 7
    //   144: aload 6
    //   146: invokevirtual 96	java/lang/Exception:printStackTrace	()V
    //   149: aload 7
    //   151: areturn
    //   152: astore 5
    //   154: aload 5
    //   156: astore 6
    //   158: aload 4
    //   160: astore 7
    //   162: goto -18 -> 144
    //   165: aload 4
    //   167: areturn
    //   168: aload_2
    //   169: astore 4
    //   171: goto -141 -> 30
    //
    // Exception table:
    //   from	to	target	type
    //   4	26	135	java/lang/Exception
    //   30	132	152	java/lang/Exception
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.APGameListGoodsPicAdapter
 * JD-Core Version:    0.6.0
 */
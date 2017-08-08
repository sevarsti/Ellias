package com.pay.ui.saveAccount;

import android.content.Context;
import android.widget.BaseAdapter;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;

public class APAccountListValueAdapter extends BaseAdapter
{
  private Context a;
  private int[] b;
  private APOrderInfo c = null;

  public APAccountListValueAdapter(Context paramContext, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    this.a = paramContext;
    this.b = paramArrayOfInt1;
    this.c = APDataInterface.singleton().getOrderInfo();
  }

  public int getCount()
  {
    return this.b.length;
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(this.b[paramInt]);
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
    //   1: ifnonnull +245 -> 246
    //   4: aload_0
    //   5: getfield 19	com/pay/ui/saveAccount/APAccountListValueAdapter:a	Landroid/content/Context;
    //   8: invokestatic 53	android/view/LayoutInflater:from	(Landroid/content/Context;)Landroid/view/LayoutInflater;
    //   11: aload_0
    //   12: getfield 19	com/pay/ui/saveAccount/APAccountListValueAdapter:a	Landroid/content/Context;
    //   15: ldc 55
    //   17: invokestatic 61	com/pay/tool/APCommMethod:getLayoutId	(Landroid/content/Context;Ljava/lang/String;)I
    //   20: aconst_null
    //   21: invokevirtual 65	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;)Landroid/view/View;
    //   24: astore 17
    //   26: aload 17
    //   28: astore 4
    //   30: aload_0
    //   31: getfield 21	com/pay/ui/saveAccount/APAccountListValueAdapter:b	[I
    //   34: iload_1
    //   35: iaload
    //   36: invokestatic 70	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   39: astore 8
    //   41: aload 8
    //   43: aload_0
    //   44: getfield 17	com/pay/ui/saveAccount/APAccountListValueAdapter:c	Lcom/pay/data/orderInfo/APOrderInfo;
    //   47: getfield 76	com/pay/data/orderInfo/APOrderInfo:saveType	I
    //   50: invokestatic 80	com/pay/tool/APCommMethod:computerRate	(Ljava/lang/String;I)[Ljava/lang/String;
    //   53: astore 9
    //   55: aload 9
    //   57: iconst_0
    //   58: aaload
    //   59: astore 10
    //   61: aload 9
    //   63: arraylength
    //   64: iconst_1
    //   65: if_icmple +174 -> 239
    //   68: aload 9
    //   70: iconst_1
    //   71: aaload
    //   72: astore 11
    //   74: aload 4
    //   76: aload_0
    //   77: getfield 19	com/pay/ui/saveAccount/APAccountListValueAdapter:a	Landroid/content/Context;
    //   80: ldc 82
    //   82: invokestatic 85	com/pay/tool/APCommMethod:getId	(Landroid/content/Context;Ljava/lang/String;)I
    //   85: invokevirtual 91	android/view/View:findViewById	(I)Landroid/view/View;
    //   88: checkcast 93	android/widget/ImageView
    //   91: astore 12
    //   93: aload 4
    //   95: aload_0
    //   96: getfield 19	com/pay/ui/saveAccount/APAccountListValueAdapter:a	Landroid/content/Context;
    //   99: ldc 95
    //   101: invokestatic 85	com/pay/tool/APCommMethod:getId	(Landroid/content/Context;Ljava/lang/String;)I
    //   104: invokevirtual 91	android/view/View:findViewById	(I)Landroid/view/View;
    //   107: checkcast 97	android/widget/TextView
    //   110: astore 13
    //   112: aload 4
    //   114: aload_0
    //   115: getfield 19	com/pay/ui/saveAccount/APAccountListValueAdapter:a	Landroid/content/Context;
    //   118: ldc 99
    //   120: invokestatic 85	com/pay/tool/APCommMethod:getId	(Landroid/content/Context;Ljava/lang/String;)I
    //   123: invokevirtual 91	android/view/View:findViewById	(I)Landroid/view/View;
    //   126: checkcast 97	android/widget/TextView
    //   129: astore 14
    //   131: aload 4
    //   133: aload_0
    //   134: getfield 19	com/pay/ui/saveAccount/APAccountListValueAdapter:a	Landroid/content/Context;
    //   137: ldc 101
    //   139: invokestatic 85	com/pay/tool/APCommMethod:getId	(Landroid/content/Context;Ljava/lang/String;)I
    //   142: invokevirtual 91	android/view/View:findViewById	(I)Landroid/view/View;
    //   145: checkcast 97	android/widget/TextView
    //   148: astore 15
    //   150: aload 12
    //   152: aload_0
    //   153: getfield 19	com/pay/ui/saveAccount/APAccountListValueAdapter:a	Landroid/content/Context;
    //   156: ldc 103
    //   158: invokestatic 106	com/pay/tool/APCommMethod:getDrawableId	(Landroid/content/Context;Ljava/lang/String;)I
    //   161: invokevirtual 110	android/widget/ImageView:setBackgroundResource	(I)V
    //   164: aload 13
    //   166: aload 8
    //   168: invokevirtual 114	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   171: aload 14
    //   173: aload 10
    //   175: invokevirtual 114	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   178: aload 15
    //   180: iconst_0
    //   181: invokevirtual 117	android/widget/TextView:setVisibility	(I)V
    //   184: aload 15
    //   186: new 119	java/lang/StringBuilder
    //   189: dup
    //   190: ldc 121
    //   192: invokespecial 124	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   195: aload 11
    //   197: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   203: invokevirtual 114	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   206: aload 4
    //   208: areturn
    //   209: astore 16
    //   211: aload 16
    //   213: astore 6
    //   215: aload_2
    //   216: astore 7
    //   218: aload 6
    //   220: invokevirtual 135	java/lang/Exception:printStackTrace	()V
    //   223: aload 7
    //   225: areturn
    //   226: astore 5
    //   228: aload 5
    //   230: astore 6
    //   232: aload 4
    //   234: astore 7
    //   236: goto -18 -> 218
    //   239: ldc 137
    //   241: astore 11
    //   243: goto -169 -> 74
    //   246: aload_2
    //   247: astore 4
    //   249: goto -219 -> 30
    //
    // Exception table:
    //   from	to	target	type
    //   4	26	209	java/lang/Exception
    //   30	74	226	java/lang/Exception
    //   74	206	226	java/lang/Exception
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.saveAccount.APAccountListValueAdapter
 * JD-Core Version:    0.6.0
 */
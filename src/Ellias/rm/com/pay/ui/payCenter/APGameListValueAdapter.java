package com.pay.ui.payCenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBuyMonthInfo;
import com.pay.data.mp.APMPGamesItem;
import com.pay.data.mp.APMPSendInfo;
import com.pay.data.mp.APMPSendItem;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APMonthDataInterface;
import com.pay.tool.APMonthDataInterface.MonthOpenType;
import com.pay.ui.common.APUICommonMethod;
import java.util.ArrayList;
import java.util.List;

public class APGameListValueAdapter extends BaseAdapter
{
  private Context a;
  private String[] b;
  private String[] c;
  private String[] d;
  private boolean e;
  private APOrderInfo f = null;

  public APGameListValueAdapter(Context paramContext, List paramList, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3)
  {
    this.a = paramContext;
    this.b = paramArrayOfString1;
    this.c = paramArrayOfString2;
    this.d = paramArrayOfString3;
    this.f = APDataInterface.singleton().getOrderInfo();
  }

  public int getCount()
  {
    if ((this.f.saveType == 0) && (APMPSendInfo.getInstance().getIsHasUptoNumMpMPInfo()))
    {
      this.e = true;
      return APMPSendInfo.getInstance().getUptoNumMpMpInfo().size();
    }
    this.e = false;
    return this.b.length;
  }

  public Object getItem(int paramInt)
  {
    return this.b[paramInt];
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null);
    while (true)
    {
      try
      {
        View localView3 = LayoutInflater.from(this.a).inflate(APCommMethod.getLayoutId(this.a, "unipay_layout_tips_num_item"), null);
        localView1 = localView3;
        int j;
        String str4;
        int k;
        TextView localTextView5;
        ImageView localImageView2;
        TextView localTextView7;
        ImageView localImageView3;
        TextView localTextView1;
        TextView localTextView4;
        try
        {
          if (!this.e)
            continue;
          j = ((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(paramInt)).sendGames.limitNum;
          str4 = String.valueOf(j);
          k = ((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(paramInt)).sendGames.getSendGamesNum();
          localTextView5 = (TextView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_mpNum"));
          String str5 = ((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(paramInt)).sendGames.sendExt;
          TextView localTextView6 = (TextView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_mpExt"));
          if ((str5 != null) && (!str5.equals("")))
            continue;
          localTextView6.setVisibility(8);
          localImageView2 = (ImageView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_mpSendIcon"));
          if (k <= 0)
            continue;
          localTextView5.setVisibility(0);
          if (APMPSendInfo.getInstance().isHasUptoSendGoods())
            continue;
          localImageView2.setVisibility(0);
          localTextView5.setText(String.valueOf(k));
          localTextView7 = (TextView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_mpGoods"));
          localImageView3 = (ImageView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_apPaySendIcon"));
          if (((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(paramInt)).sendGoodsList == null)
            break label1148;
          m = ((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(paramInt)).sendGoodsList.size();
          if (m <= 0)
            continue;
          localImageView3.setVisibility(0);
          if (!((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(paramInt)).getIsHasGoodsPic())
            continue;
          localTextView7.setVisibility(8);
          APGameListGoodsPicAdapter localAPGameListGoodsPicAdapter = new APGameListGoodsPicAdapter(this.a, ((APMPSendItem)APMPSendInfo.getInstance().getUptoNumMpMpInfo().get(paramInt)).sendGoodsList);
          GridView localGridView = (GridView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_mpGoodsPic"));
          localGridView.setNumColumns(3);
          localGridView.setFocusable(false);
          localGridView.setVisibility(0);
          localGridView.setAdapter(localAPGameListGoodsPicAdapter);
          str1 = str4;
          int i = APDataInterface.singleton().getOrderInfo().saveType;
          if (((i != 4) && (i != 5)) || (APMonthDataInterface.singleton().getOpenType() != APMonthDataInterface.MonthOpenType.OpenType_NoRate))
            continue;
          arrayOfString = APCommMethod.moneyFormat(Float.valueOf(this.c[paramInt]).floatValue());
          String str2 = arrayOfString[0];
          if (arrayOfString.length <= 1)
            break label1141;
          str3 = arrayOfString[1];
          ImageView localImageView1 = (ImageView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_apPayIcon"));
          localTextView1 = (TextView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_apPayNum"));
          TextView localTextView2 = (TextView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_apPayMoney"));
          TextView localTextView3 = (TextView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_moneyDecima"));
          localTextView4 = (TextView)localView1.findViewById(APCommMethod.getId(this.a, "unipay_id_mpNum"));
          localImageView1.setBackgroundDrawable(APDataInterface.singleton().getAppResDrawable());
          if (this.f.saveType != 4)
            continue;
          if (APMonthDataInterface.singleton().getOpenType() != APMonthDataInterface.MonthOpenType.OpenType_NoRate)
            continue;
          localTextView4.setText(this.d[paramInt]);
          localTextView4.setTextSize(17.0F);
          localTextView4.setVisibility(0);
          localTextView1.setVisibility(8);
          localTextView2.setText(str2);
          if (TextUtils.isEmpty(str3))
            break label1138;
          localTextView3.setText("." + str3);
          return localView1;
          localTextView6.setVisibility(0);
          localTextView6.setText(str5);
          localTextView5.setTextColor(-1864636);
          continue;
        }
        catch (Exception localException1)
        {
          localObject = localException1;
          localView2 = localView1;
        }
        ((Exception)localObject).printStackTrace();
        return localView2;
        localTextView5.setText("+" + k);
        continue;
        localTextView5.setVisibility(8);
        localImageView2.setVisibility(8);
        continue;
        String str6 = APMPSendInfo.getInstance().getUptoNumMpSendGoods(j);
        localTextView7.setVisibility(0);
        localTextView7.setText(str6);
        int n = ((WindowManager)this.a.getSystemService("window")).getDefaultDisplay().getWidth() - APUICommonMethod.dip2px(this.a, 200.0F);
        localTextView7.measure(0, 0);
        int i1 = localTextView7.getMeasuredWidth();
        if (i1 % n != 0)
          continue;
        i2 = i1 / n;
        break label1160;
        localTextView7.setLines(i2);
        APLog.i("getView", "measureWidth:" + i1 + " textWidth:" + n + " lines:" + i2);
        String str1 = str4;
        continue;
        i2 = 1 + i1 / n;
        break label1160;
        localImageView3.setVisibility(8);
        localTextView7.setVisibility(8);
        str1 = str4;
        continue;
        str1 = this.b[paramInt];
        continue;
        String[] arrayOfString = APCommMethod.computerRate(str1, this.f.saveType);
        continue;
        localTextView1.setText(str1);
        localTextView4.setText(APMonthDataInterface.singleton().getUnit());
        localTextView4.setVisibility(0);
        continue;
        if (this.f.saveType != 5)
          continue;
        if (APMonthDataInterface.singleton().getOpenType() != APMonthDataInterface.MonthOpenType.OpenType_NoRate)
          continue;
        localTextView4.setText(this.d[paramInt]);
        localTextView4.setTextSize(17.0F);
        localTextView4.setVisibility(0);
        localTextView1.setVisibility(8);
        continue;
        localTextView4.setText(((APBuyMonthInfo)this.f.buyInfo).serviceName + " × " + str1);
        localTextView4.setTextSize(17.0F);
        continue;
        localTextView1.setText(str1);
        continue;
      }
      catch (Exception localException2)
      {
        Object localObject = localException2;
        View localView2 = paramView;
        continue;
      }
      label1138: return localView1;
      label1141: String str3 = "";
      continue;
      label1148: int m = 0;
      continue;
      View localView1 = paramView;
      continue;
      label1160: if (i2 <= 3)
        continue;
      int i2 = 3;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.APGameListValueAdapter
 * JD-Core Version:    0.6.0
 */
package com.pay.ui.channel;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.LinearLayout;
import com.pay.AndroidPay;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import java.util.ArrayList;
import java.util.List;

public class APChannelActivity extends APRecoChannelActivity
{
  protected int ANIMATIONTIME = 500;
  private GridView a;
  private LinearLayout b;
  private boolean c = false;
  protected List channelList = null;
  protected List moreList = null;
  protected List recoList = null;

  private void a()
  {
    int i = 0;
    if (i >= this.channelList.size())
    {
      if (this.recoList.size() <= 0)
        break label145;
      initRecoChannel();
    }
    while (true)
    {
      if (this.moreList.size() <= 0)
        break label152;
      this.b = ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_MoreChannelLayout")));
      this.b.setOnClickListener(new a(this));
      if (this.c)
        c();
      return;
      APChannelInfo localAPChannelInfo = (APChannelInfo)this.channelList.get(i);
      if (localAPChannelInfo.channelState == 1)
        this.recoList.add(localAPChannelInfo);
      if (localAPChannelInfo.channelState == 2)
        this.moreList.add(localAPChannelInfo);
      i++;
      break;
      label145: b();
    }
    label152: this.b = ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_MoreChannelLayout")));
    this.b.setVisibility(8);
    this.b = ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_MoreChannelLayout")));
    this.b.setVisibility(8);
  }

  private void b()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.moreList.size())
      {
        this.b = ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_MoreChannelLayout")));
        this.b.setVisibility(8);
        ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_ChannelBottomLayout"))).setVisibility(8);
        initRecoChannel();
        return;
      }
      this.recoList.add((APChannelInfo)this.moreList.get(i));
    }
  }

  private void c()
  {
    initMoreChannelList();
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, -1.0F, 1, 0.0F);
    localTranslateAnimation.setDuration(this.ANIMATIONTIME);
    GridView localGridView = (GridView)findViewById(APCommMethod.getId(this, "unipay_id_ChannelBottom"));
    localGridView.startAnimation(localTranslateAnimation);
    localGridView.setVisibility(0);
    localTranslateAnimation.setAnimationListener(new d(this));
    ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_MoreChannelLayout"))).setVisibility(8);
  }

  public void hideListAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, -1.0F, 1, 0.0F);
    localTranslateAnimation.setDuration(this.ANIMATIONTIME);
    GridView localGridView = (GridView)findViewById(APCommMethod.getId(this, "unipay_id_ChannelBottom"));
    localGridView.startAnimation(localTranslateAnimation);
    localGridView.setVisibility(8);
  }

  protected void initMoreChannelList()
  {
    this.a = ((GridView)findViewById(APCommMethod.getId(this, "unipay_id_ChannelBottom")));
    APChannelAdapter localAPChannelAdapter = new APChannelAdapter(this, this.moreList);
    this.a.setAdapter(localAPChannelAdapter);
    this.a.setNumColumns(1);
    this.a.setOnItemClickListener(new c(this));
  }

  protected void initRecoChannel()
  {
    GridView localGridView = (GridView)findViewById(APCommMethod.getId(this, "unipay_id_ChannelReco"));
    APChannelAdapter localAPChannelAdapter = new APChannelAdapter(this, this.recoList);
    localAPChannelAdapter.notifyDataSetChanged();
    localGridView.setAdapter(localAPChannelAdapter);
    localGridView.setNumColumns(1);
    localGridView.setOnItemClickListener(new b(this));
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
    {
      finish();
      return;
    }
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_channel"));
    this.saveType = this.orderInfo.saveType;
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null)
      this.c = localBundle.getBoolean("isPayExpress");
    this.recoList = new ArrayList();
    this.moreList = new ArrayList();
    this.channelList = new ArrayList();
    if (this.saveType == 0)
    {
      this.channelList = APChannelList.singleton().getCommChannelList();
      initGameTitle();
    }
    while (true)
    {
      titleAnimation();
      a();
      return;
      if (this.saveType == 1)
      {
        initGoodsTitle();
        this.channelList = APChannelList.singleton().getCommChannelList();
        continue;
      }
      if ((this.saveType == 3) || (this.saveType == 2))
      {
        initAccountTitle(this.saveType);
        this.channelList = APChannelList.singleton().getAcctChannelList();
        continue;
      }
      if ((this.saveType != 4) && (this.saveType != 5))
        continue;
      initMonthTitle();
      this.channelList = APChannelList.singleton().getCommChannelList();
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      APDataReportManager.getInstance().insertData("sdk.channellist.keyback", this.saveType);
      finish();
      overridePendingTransition(APCommMethod.getAnimId(this, "unipay_anim_in_from_left"), APCommMethod.getAnimId(this, "unipay_anim_out_to_right"));
      if ((!this.orderInfo.isNumCanChange) && (!this.c))
        APCommMethod.payErrorCallBack(2, "");
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    super.onResume();
    if ((!APDataInterface.singleton().getDataValid()) || (AndroidPay.singleton().applicationContext == null))
    {
      finish();
      return;
    }
    if (AndroidPay.singleton().isValidPayChannelAndMarket())
      finish();
    this.saveType = APDataInterface.singleton().getOrderInfo().saveType;
    if (this.saveType == 0)
      initGameTitle();
    while (true)
    {
      APDataReportManager.getInstance().insertData("sdk.channellist.show", this.saveType);
      return;
      if (this.saveType == 1)
      {
        initGoodsTitle();
        continue;
      }
      if ((this.saveType == 3) || (this.saveType == 2))
      {
        initAccountTitle(this.saveType);
        continue;
      }
      if ((this.saveType != 4) && (this.saveType != 5))
        continue;
      initMonthTitle();
    }
  }

  public void onStop()
  {
    super.onStop();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.APChannelActivity
 * JD-Core Version:    0.6.0
 */
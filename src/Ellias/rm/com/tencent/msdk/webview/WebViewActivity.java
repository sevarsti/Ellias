package com.tencent.msdk.webview;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.webkit.ValueCallback;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.api.eQQScene;
import com.tencent.msdk.api.eWechatScene;
import com.tencent.msdk.permission.PermissionManage;
import com.tencent.msdk.tools.CommonUtil;
import com.tencent.msdk.tools.Logger;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebSettings.PluginState;
import com.tencent.smtt.sdk.WebSettings.RenderPriority;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WebViewActivity extends Activity
  implements AdapterView.OnItemClickListener, View.OnClickListener
{
  private static final int THUMB_SIZE = 200;
  private MarginButton cancelBtn;
  private int landHeight;
  private SimpleAdapter mAdapter;
  private ImageButton mBackBtn;
  private Dialog mDownloadDlg;
  private ImageButton mForwardBtn;
  private GridView mGridView;
  private ArrayList<HashMap<String, Object>> mItemArrayList;
  private Button mMoreBtn;
  private Dialog mMoreDlg;
  private ImageButton mOpenQQBrowserBtn;
  private String mOriginalUrl;
  private ViewGroup mParentLayout;
  private ProgressBar mProgressBar;
  private ImageButton mRefreshBtn;
  private Button mReturnAppBtn;
  private String mSharedUrl;
  private ImageButton mStopBtn;
  private TextView mTitle;
  private RelativeLayout mTitleBarLayout;
  private FrameLayout mToolBarHolder;
  private LinearLayout mToolBarLayout;
  private WebView mWebView;
  private int portHeight;
  private ValueCallback<Uri> uploadFile;

  private void changeBackForwordBtnState()
  {
    if (this.mWebView.canGoForward())
    {
      this.mForwardBtn.setEnabled(true);
      this.mForwardBtn.setAlpha(255);
    }
    while (this.mWebView.canGoBack())
    {
      this.mBackBtn.setEnabled(true);
      this.mBackBtn.setAlpha(255);
      return;
      this.mForwardBtn.setEnabled(false);
      this.mForwardBtn.setAlpha(100);
    }
    this.mBackBtn.setEnabled(false);
    this.mBackBtn.setAlpha(100);
  }

  private void changeLayoutHeight(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    ViewGroup.LayoutParams localLayoutParams;
    if (paramViewGroup != null)
    {
      localLayoutParams = paramViewGroup.getLayoutParams();
      if (!paramBoolean)
        break label31;
    }
    label31: for (int i = this.portHeight; ; i = this.landHeight)
    {
      localLayoutParams.height = i;
      paramViewGroup.setLayoutParams(localLayoutParams);
      return;
    }
  }

  private void doAfterStartLoading()
  {
    if (this.mProgressBar.getVisibility() != 0)
      this.mProgressBar.setVisibility(0);
    if (this.mStopBtn.getVisibility() != 0)
      this.mStopBtn.setVisibility(0);
    if (this.mRefreshBtn.getVisibility() != 8)
      this.mRefreshBtn.setVisibility(8);
  }

  private void doAfterStop()
  {
    this.mProgressBar.setVisibility(8);
    if (this.mStopBtn.getVisibility() != 8)
      this.mStopBtn.setVisibility(8);
    if (this.mRefreshBtn.getVisibility() != 0)
      this.mRefreshBtn.setVisibility(0);
  }

  private String getCurrentUrl()
  {
    String str = this.mWebView.getUrl();
    if (str != null)
    {
      if (str.equals(this.mOriginalUrl))
      {
        Logger.d("getCurrentUrl state:true");
        str = this.mSharedUrl;
      }
      return str;
    }
    Logger.e("mWebView geturl is null!");
    return str;
  }

  private ArrayList<ShareItem> getShareItems()
  {
    ArrayList localArrayList = new ArrayList();
    boolean bool = PermissionManage.getInstance().isHavePermission("WGSendToQQ");
    if (PermissionManage.getInstance().isHavePermission("WGSendToWeixin"))
    {
      ShareItem localShareItem1 = new ShareItem(WebViewResID.drawable_share_to_wx_friend, getString(WebViewResID.str_shareToWxFriend, new Object[] { "" }), "shareToWxfriend");
      localArrayList.add(new ShareItem(WebViewResID.drawable_share_to_wx, getString(WebViewResID.str_shareToWx, new Object[] { "" }), "shareToWx"));
      localArrayList.add(localShareItem1);
    }
    if (bool)
    {
      ShareItem localShareItem2 = new ShareItem(WebViewResID.drawable_share_to_qzone, getString(WebViewResID.str_shareToQzone, new Object[] { "" }), "shareToQzone");
      localArrayList.add(new ShareItem(WebViewResID.drawable_share_to_qq, getString(WebViewResID.str_shareToQQ, new Object[] { "" }), "shareToQQ"));
      localArrayList.add(localShareItem2);
    }
    ShareItem localShareItem3 = new ShareItem(WebViewResID.drawable_open_by_qqbrowser, getString(WebViewResID.str_thrdcall_openqbx, new Object[] { "" }), "openByQQBrowser");
    ShareItem localShareItem4 = new ShareItem(WebViewResID.drawable_open_by_otherbrowser, getString(WebViewResID.str_thrdcall_openbrowser, new Object[] { "" }), "openByOtherBrowser");
    localArrayList.add(localShareItem3);
    localArrayList.add(localShareItem4);
    return localArrayList;
  }

  private void initGridView()
  {
    this.mItemArrayList = new ArrayList();
    ArrayList localArrayList = this.mItemArrayList;
    int i = WebViewResID.layout_dlg_gridview_item;
    String[] arrayOfString = { "icon", "title" };
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = WebViewResID.itemImage;
    arrayOfInt[1] = WebViewResID.itemText;
    this.mAdapter = new SimpleAdapter(this, localArrayList, i, arrayOfString, arrayOfInt);
    this.mGridView.setSelector(new ColorDrawable(0));
    this.mGridView.setAdapter(this.mAdapter);
    this.mGridView.setOnItemClickListener(this);
  }

  private void initMoreDlg()
  {
    if (this.mMoreDlg == null)
    {
      this.mMoreDlg = new Dialog(this, WebViewResID.style_SheetDialogTheme);
      this.mMoreDlg.setContentView(WebViewResID.layout_sheet_dlg);
      this.mMoreDlg.setCanceledOnTouchOutside(false);
      Window localWindow = this.mMoreDlg.getWindow();
      WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
      localLayoutParams.width = -1;
      localLayoutParams.gravity = 80;
      localLayoutParams.dimAmount = 0.5F;
      localWindow.setAttributes(localLayoutParams);
      localWindow.setWindowAnimations(WebViewResID.style_SheetDialogAnimation);
      localWindow.setBackgroundDrawableResource(WebViewResID.color_transparent);
      localWindow.addFlags(2);
      this.mGridView = ((GridView)this.mMoreDlg.findViewById(WebViewResID.dlg_gridview));
      initGridView();
      this.cancelBtn = ((MarginButton)this.mMoreDlg.findViewById(WebViewResID.dlg_btn_cancel));
      this.cancelBtn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if ((WebViewActivity.this.mMoreDlg != null) && (WebViewActivity.this.mMoreDlg.isShowing()))
            WebViewActivity.this.mMoreDlg.dismiss();
        }
      });
    }
    updateItemArrayList();
  }

  private void initWebView()
  {
    this.mWebView.setWebChromeClient(new WebChromeClient()
    {
      public void onReceivedTitle(WebView paramWebView, String paramString)
      {
        super.onReceivedTitle(paramWebView, paramString);
        WebViewActivity.this.mTitle.setText(paramString);
      }

      public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString1, String paramString2)
      {
        WebViewActivity.access$102(WebViewActivity.this, paramValueCallback);
        Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
        localIntent.addCategory("android.intent.category.OPENABLE");
        localIntent.setType("*/*");
        WebViewActivity.this.startActivityForResult(Intent.createChooser(localIntent, WebViewActivity.this.getResources().getString(WebViewResID.str_upload_file_title)), 0);
      }
    });
    this.mWebView.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramWebView, String paramString)
      {
        WebViewActivity.this.changeBackForwordBtnState();
        WebViewActivity.this.doAfterStop();
      }

      public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
      {
        WebViewActivity.this.doAfterStartLoading();
      }

      public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
      {
        Logger.d("shouldOverrideUrlLoading url:" + paramString);
        return false;
      }
    });
    this.mWebView.setDownloadListener(new DownloadListener()
    {
      public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
      {
        try
        {
          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
          WebViewActivity.this.startActivity(localIntent);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          Logger.e("default browser is uninstalled!");
        }
      }
    });
    WebSettings localWebSettings = this.mWebView.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setAllowFileAccess(true);
    localWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    localWebSettings.setSupportZoom(true);
    localWebSettings.setBuiltInZoomControls(true);
    localWebSettings.setUseWideViewPort(true);
    localWebSettings.setSupportMultipleWindows(false);
    if (Build.VERSION.SDK_INT >= 11)
      localWebSettings.setDisplayZoomControls(false);
    localWebSettings.setLoadWithOverviewMode(true);
    localWebSettings.setAppCacheEnabled(true);
    localWebSettings.setDatabaseEnabled(true);
    localWebSettings.setDomStorageEnabled(true);
    localWebSettings.setGeolocationEnabled(true);
    localWebSettings.setAppCacheMaxSize(9223372036854775807L);
    localWebSettings.setAppCachePath(getDir("appcache", 0).getPath());
    localWebSettings.setDatabasePath(getDir("databases", 0).getPath());
    localWebSettings.setGeolocationDatabasePath(getDir("geolocation", 0).getPath());
    localWebSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
    localWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    if (this.mWebView.getX5WebViewExtension() != null)
      Logger.d("CoreVersion_FromSDK::" + this.mWebView.getX5WebViewExtension().getQQBrowserVersion());
    while (true)
    {
      CookieSyncManager.createInstance(this);
      CookieSyncManager.getInstance().sync();
      return;
      Logger.d("CoreVersion");
    }
  }

  private void openByQQBrowser()
  {
    String str = getCurrentUrl();
    if (str == null)
      Logger.w("Shared Url == null!");
    int i;
    do
    {
      return;
      i = MttLoader.loadUrl(this, str, null);
      if (i != 4)
        continue;
      if ((this.mMoreDlg != null) && (this.mMoreDlg.isShowing()))
        this.mMoreDlg.dismiss();
      showDownloadQQBrowserDlg();
      return;
    }
    while (i != 5);
    Logger.w("qqbrowser version is too low...");
  }

  private String saveImageToSDCard()
  {
    File localFile1 = new File(getDir("msdk_webview", 0).getAbsolutePath());
    if (!localFile1.exists())
      localFile1.mkdirs();
    File localFile2 = new File(localFile1, "default_thumb.png");
    if (localFile2.exists())
      localFile2.delete();
    if (this.mWebView == null);
    Bitmap localBitmap2;
    do
    {
      Bitmap localBitmap1;
      do
      {
        return null;
        this.mWebView.setDrawingCacheEnabled(true);
        localBitmap1 = this.mWebView.getDrawingCache();
      }
      while (localBitmap1 == null);
      localBitmap2 = small(localBitmap1);
    }
    while (localBitmap2 == null);
    try
    {
      localFile2.createNewFile();
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
      localBitmap2.compress(Bitmap.CompressFormat.PNG, 50, localFileOutputStream);
      localFileOutputStream.flush();
      localFileOutputStream.close();
      this.mWebView.setDrawingCacheEnabled(false);
      return localFile2.toString();
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      Logger.w(localFileNotFoundException.getMessage());
      return null;
    }
    catch (IOException localIOException)
    {
      Logger.w(localIOException.getMessage());
    }
    return null;
  }

  private void sendQQShare(String paramString)
  {
    String str1 = this.mTitle.getText().toString();
    String str2 = saveImageToSDCard();
    int i;
    String str3;
    if (str2 == null)
    {
      i = 0;
      str3 = getCurrentUrl();
      if (str3 != null)
        break label50;
      Logger.w("Shared Url == null!");
    }
    label50: 
    do
    {
      return;
      i = str2.length();
      break;
      if (str3 == null);
      for (int j = 0; ; j = str3.length())
      {
        if (j >= 500)
          Logger.w("sendQQShare targetUrlLen too long!");
        if (!"shareToQQ".equals(paramString))
          break;
        Logger.d("shareToQQ...");
        WGPlatform.WGSendToQQ(eQQScene.QQScene_Session, str1, null, str3, str2, i);
        return;
      }
    }
    while (!"shareToQzone".equals(paramString));
    Logger.d("shareToQzone...");
    WGPlatform.WGSendToQQ(eQQScene.QQScene_QZone, str1, null, str3, str2, i);
  }

  private void sendWXShare(String paramString)
  {
    String str1 = this.mTitle.getText().toString();
    String str2 = getCurrentUrl();
    if (str2 == null)
      Logger.w("Shared Url == null!");
    byte[] arrayOfByte2;
    int j;
    label231: label239: 
    do
    {
      return;
      this.mWebView.setDrawingCacheEnabled(true);
      byte[] arrayOfByte1 = CommonUtil.bitmap2Bytes(this.mWebView.getDrawingCache());
      this.mWebView.setDrawingCacheEnabled(false);
      if (arrayOfByte1 == null);
      Bitmap localBitmap1;
      for (int i = 0; ; i = arrayOfByte1.length)
      {
        localBitmap1 = BitmapFactory.decodeByteArray(arrayOfByte1, 0, i);
        if (localBitmap1 != null)
          break;
        Logger.w("sendWXShare BitmapFactory.decodeByteArray is null");
        return;
      }
      float f1 = localBitmap1.getWidth();
      float f2 = localBitmap1.getHeight();
      Bitmap localBitmap2;
      if (f1 > f2)
      {
        localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, 200, (int)(200.0F * (f2 / f1)), true);
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        localBitmap2.compress(Bitmap.CompressFormat.JPEG, 90, localByteArrayOutputStream);
        arrayOfByte2 = localByteArrayOutputStream.toByteArray();
        if (arrayOfByte2 != null)
          break label231;
      }
      for (j = 0; ; j = arrayOfByte2.length)
      {
        if (!"shareToWxfriend".equals(paramString))
          break label239;
        Logger.d("shareToWxfriend...");
        WGPlatform.sendToWeixinWithUrl(eWechatScene.WechatScene_Timeline, str1, null, str2, "msdk", arrayOfByte2, j);
        return;
        localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, (int)(200.0F * (f1 / f2)), 200, true);
        break;
      }
    }
    while (!"shareToWx".equals(paramString));
    Logger.d("shareToWx...");
    WGPlatform.sendToWeixinWithUrl(eWechatScene.WechatScene_Session, str1, null, str2, "msdk", arrayOfByte2, j);
  }

  private void showDownloadQQBrowserDlg()
  {
    if (this.mDownloadDlg == null)
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle(WebViewResID.str_thrdcall_recom_mtt_title);
      localBuilder.setMessage(WebViewResID.str_thrdcall_recom_mtt_content);
      localBuilder.setPositiveButton(WebViewResID.str_thrdcall_confirm, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://mdc.html5.qq.com/d/directdown.jsp?channel_id=21380"));
          try
          {
            WebViewActivity.this.startActivity(localIntent);
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      });
      localBuilder.setNegativeButton(WebViewResID.str_thrdcall_cancel, null);
      this.mDownloadDlg = localBuilder.create();
    }
    if ((this.mDownloadDlg != null) && (!this.mDownloadDlg.isShowing()))
      this.mDownloadDlg.show();
  }

  private static Bitmap small(Bitmap paramBitmap)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    if (f1 > f2);
    for (Bitmap localBitmap = Bitmap.createScaledBitmap(paramBitmap, 200, (int)(200.0F * (f2 / f1)), true); ; localBitmap = Bitmap.createScaledBitmap(paramBitmap, (int)(200.0F * (f1 / f2)), 200, true))
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localBitmap.compress(Bitmap.CompressFormat.JPEG, 90, localByteArrayOutputStream);
      return localBitmap;
    }
  }

  private void updateItemArrayList()
  {
    this.mItemArrayList.clear();
    ArrayList localArrayList = getShareItems();
    for (int i = 0; i < localArrayList.size(); i++)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("icon", Integer.valueOf(((ShareItem)localArrayList.get(i)).iconId));
      localHashMap.put("title", ((ShareItem)localArrayList.get(i)).title);
      localHashMap.put("itemId", ((ShareItem)localArrayList.get(i)).itemId);
      this.mItemArrayList.add(localHashMap);
    }
    this.mAdapter.notifyDataSetChanged();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Logger.d("WVMActivity onActivityResult, requestCode:" + paramInt1 + ",resultCode:" + paramInt2);
    if (paramInt2 == -1)
      switch (paramInt1)
      {
      default:
      case 0:
      }
    do
    {
      do
        return;
      while (this.uploadFile == null);
      if ((paramIntent == null) || (paramInt2 != -1));
      for (Object localObject = null; ; localObject = paramIntent.getData())
      {
        this.uploadFile.onReceiveValue(localObject);
        this.uploadFile = null;
        return;
      }
    }
    while ((paramInt2 != 0) || (this.uploadFile == null));
    this.uploadFile.onReceiveValue(null);
    this.uploadFile = null;
  }

  public void onClick(View paramView)
  {
    int i = paramView.getId();
    if (i == WebViewResID.back)
      if (this.mWebView.canGoBack())
        this.mWebView.goBack();
    do
      while (true)
      {
        return;
        if (i == WebViewResID.forward)
        {
          if (!this.mWebView.canGoForward())
            continue;
          this.mWebView.goForward();
          return;
        }
        if (i == WebViewResID.refresh)
        {
          this.mWebView.reload();
          return;
        }
        if (i == WebViewResID.stop)
        {
          this.mWebView.stopLoading();
          return;
        }
        if (i == WebViewResID.return_app)
        {
          finish();
          return;
        }
        if (i != WebViewResID.more)
          break;
        initMoreDlg();
        if (this.mMoreDlg == null)
          continue;
        this.mMoreDlg.show();
        return;
      }
    while (i != WebViewResID.openByQQBrowser);
    openByQQBrowser();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    boolean bool;
    if (2 == paramConfiguration.orientation)
    {
      getWindow().setFlags(1024, 1024);
      GridView localGridView = this.mGridView;
      bool = false;
      if (localGridView != null)
        this.mGridView.setNumColumns(4);
    }
    while (true)
    {
      if (this.cancelBtn != null)
        this.cancelBtn.updateMargin(bool);
      this.portHeight = getResources().getDimensionPixelOffset(WebViewResID.dimen_titlebar_height);
      this.landHeight = getResources().getDimensionPixelOffset(WebViewResID.dimen_titlebar_land_height);
      changeLayoutHeight(this.mTitleBarLayout, bool);
      changeLayoutHeight(this.mToolBarLayout, bool);
      changeLayoutHeight(this.mToolBarHolder, bool);
      return;
      getWindow().clearFlags(1024);
      bool = true;
      if (this.mGridView == null)
        continue;
      this.mGridView.setNumColumns(3);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (WebViewResID.layout_thrdcall_window == 0)
    {
      Logger.w("WVMResID.layout_thrdcall_window == 0x00");
      finish();
    }
    do
    {
      return;
      if (2 == getResources().getConfiguration().orientation)
        getWindow().setFlags(1024, 1024);
      setContentView(WebViewResID.layout_thrdcall_window);
      this.mWebView = ((WebView)findViewById(WebViewResID.webview));
      if (this.mWebView == null)
      {
        finish();
        Logger.w("Fail to instance webview!!!");
        return;
      }
      initWebView();
      this.mTitleBarLayout = ((RelativeLayout)findViewById(WebViewResID.titleBar));
      this.mToolBarLayout = ((LinearLayout)findViewById(WebViewResID.toolbar));
      this.mToolBarHolder = ((FrameLayout)findViewById(WebViewResID.toolbar_holder));
      this.mTitle = ((TextView)findViewById(WebViewResID.title));
      this.mProgressBar = ((ProgressBar)findViewById(WebViewResID.progress));
      this.mRefreshBtn = ((ImageButton)findViewById(WebViewResID.refresh));
      this.mRefreshBtn.setOnClickListener(this);
      this.mStopBtn = ((ImageButton)findViewById(WebViewResID.stop));
      this.mStopBtn.setOnClickListener(this);
      this.mBackBtn = ((ImageButton)findViewById(WebViewResID.back));
      this.mBackBtn.setOnClickListener(this);
      this.mForwardBtn = ((ImageButton)findViewById(WebViewResID.forward));
      this.mForwardBtn.setOnClickListener(this);
      this.mReturnAppBtn = ((Button)findViewById(WebViewResID.return_app));
      this.mReturnAppBtn.setOnClickListener(this);
      this.mOpenQQBrowserBtn = ((ImageButton)findViewById(WebViewResID.openByQQBrowser));
      this.mOpenQQBrowserBtn.setOnClickListener(this);
      this.mMoreBtn = ((Button)findViewById(WebViewResID.more));
      this.mMoreBtn.setOnClickListener(this);
      this.mParentLayout = ((ViewGroup)findViewById(WebViewResID.playout));
      changeBackForwordBtnState();
      this.mOriginalUrl = getIntent().getStringExtra("url");
    }
    while (this.mOriginalUrl == null);
    this.mSharedUrl = getIntent().getStringExtra("sharedUrl");
    if (this.mSharedUrl == null)
      this.mSharedUrl = CommonUtil.getNoQueryUrl(this.mOriginalUrl);
    this.mWebView.loadUrl(this.mOriginalUrl);
  }

  protected void onDestroy()
  {
    if (this.mWebView != null)
    {
      this.mWebView.removeAllViews();
      if (this.mParentLayout != null)
        this.mParentLayout.removeView(this.mWebView);
      this.mWebView.destroy();
    }
    if ((this.mMoreDlg != null) && (this.mMoreDlg.isShowing()))
    {
      this.mMoreDlg.dismiss();
      this.mMoreDlg = null;
    }
    if ((this.mDownloadDlg != null) && (this.mDownloadDlg.isShowing()))
    {
      this.mDownloadDlg.dismiss();
      this.mDownloadDlg = null;
    }
    super.onDestroy();
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    String str1 = (String)((HashMap)paramAdapterView.getItemAtPosition(paramInt)).get("itemId");
    Intent localIntent;
    if ("openByOtherBrowser".equals(str1))
    {
      String str2 = getCurrentUrl();
      if (str2 == null)
      {
        Logger.w("Shared Url == null!");
        return;
      }
      localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
    }
    while (true)
    {
      try
      {
        startActivity(localIntent);
        if ((this.mMoreDlg == null) || (!this.mMoreDlg.isShowing()))
          break;
        this.mMoreDlg.dismiss();
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        Logger.e("default browser is uninstalled!");
        continue;
      }
      if ("openByQQBrowser".equals(str1))
      {
        openByQQBrowser();
        continue;
      }
      if (("shareToQQ".equals(str1)) || ("shareToQzone".equals(str1)))
      {
        sendQQShare(str1);
        continue;
      }
      sendWXShare(str1);
    }
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    if ((paramIntent == null) || (this.mWebView == null));
    do
    {
      return;
      this.mOriginalUrl = paramIntent.getStringExtra("url");
    }
    while (this.mOriginalUrl == null);
    this.mSharedUrl = getIntent().getStringExtra("sharedUrl");
    if (this.mSharedUrl == null)
      this.mSharedUrl = CommonUtil.getNoQueryUrl(this.mOriginalUrl);
    this.mWebView.loadUrl(this.mOriginalUrl);
  }

  public class ShareItem
  {
    public int iconId;
    public String itemId;
    public String title;

    public ShareItem(int paramString1, String paramString2, String arg4)
    {
      Object localObject;
      this.itemId = localObject;
      this.iconId = paramString1;
      this.title = paramString2;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.webview.WebViewActivity
 * JD-Core Version:    0.6.0
 */


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <meta content="IE=EmulateIE7" http-equiv="X-UA-Compatible" />
    <meta content="网页版飞信,短信,免费短信,短信群发" name="keywords" />
    <meta content="网页版飞信-中国移动Web版飞信，无需下载飞信客户端，即可与飞信好友会话，免费发送短信，享受飞信的基本功能。带给用户轻巧、便捷、快速、绿色的新体验"
        name="description" />
    <title>官方网页版飞信——在网页上与飞信好友聊天、免费发短信</title>
    <link href="css/webim.css?20130110" media="all" rel="stylesheet" type="text/css" />
    <link href="css/style.css?20130110" media="all" rel="stylesheet" type="text/css" />
    <script src="js/swfobject.js?20130110" type="text/javascript"></script>
    <!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('a,img');
        var __isIE6 = true;

        try {
            document.execCommand("BackgroundImageCache", false, true);
        } catch(err) {}
    </script>
    <![endif]-->
    <script language="JavaScript" type="text/javascript">
        var flashvars = {};
        var params = {};
        params.wmode = "transparent";
        params.bgcolor = "#cae4f3";
        params.allowscriptaccess = "sameDomain";
        var attributes = {};
        attributes.id = "onlogin";
        swfobject.embedSWF("images/logging.swf", "onlogin", "470", "268", "9.0.0", false, flashvars, params, attributes);
    </script>
</head>
<body>
    <form method="post" action="main.aspx" id="form1">
<div class="aspNetHidden">
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwUKLTUxMTcwNzgxMGRkkFyuhjAjieT8B1RTUqO0s/18MgiJt07UErMG+I4O3BM=" />
</div>

<script language="JavaScript" type="text/javascript">var __baseUrl='https://webim.feixin.10086.cn/';var __baseSSLUrl='https://webim.feixin.10086.cn/';var __mySpaceUrl='http://i.feixin.10086.cn?c={0}';var __contactSpaceUrl='http://i.feixin.10086.cn/{0}?c={1}';var __activityUrl='http://space.feixin.10086.cn/home/activityview?c={0}';
var __officialSiteUrl='http://feixin.10086.cn';
var __downloadUrl='http://feixin.10086.cn/download/pcclient';
var __callmeDomain='.feixin.10086.cn';
var __vipUrl='http://vip.feixin.10086.cn';var __feesidUrl='http://haoma.feixin.10086.cn';var __feedbackUrl='http://support.feixin.10086.cn/webim';var __redirectUri='https://webim.feixin.10086.cn/main.aspx?source=360desktop';var __isEnableDirectSMS='1';var __bannerInfo='[{Key:"BannerImg_login",Title:"飞信2013贺岁版",Img:"http://imgl.fetionpic.com/cms/20130204/6d801076-24d4-41cb-b1b7-275e5208d2c0_original.png",OffShelfTime:1,Link:"http://download.feixin.10086.cn/pc/index.html",Target:"_blank",Interval:"3000000000000000000000"},{Key:"BannerImg2",Title:"秀动传情",Img:"http://imgl.fetionpic.com/cms/20120705/d96c56db-38f5-4e73-abab-eb08d0f48870_original.jpg",OffShelfTime:1,Link:"http://huodong.feixin.10086.cn/zhuanti/preview.aspx?id=533",Target:"_blank",Interval:"3000"},{Key:"BannerImg1",Title:"贺岁版上线啦",Img:"http://imgl.fetionpic.com/cms/20130131/d814d043-7efc-4bf8-858e-1ee92cfce08f_original.jpg",OffShelfTime:1,Link:"http://download.feixin.10086.cn/pc/index.html",Target:"_blank",Interval:"3000"}]';</script>
    </form>
    <!-- 登录后主页面加载中的动画 -->
    <div id="logging" style="width: 100%; height: 100%;">
        <div style="margin: 200px auto; width: 470px; height: 268px;">
            <div id="onlogin">
            </div>
        </div>
    </div>
    <div id="Sound" style="height: 0px">
    </div>
    <!-- 登录后主页面加载中的动画 end -->
    <!-- 框架 -->
    <div class="frameset" id="maincontent">
        <!-- 头部 -->
        <div class="frame_top" id="frame_top">
            <h1 class="logo" title="网页版飞信">
                中国移动飞信 网页版飞信</h1>
            <ul class="top_nav">
                <li class="top_user_name"><span id="welcome_name"></span>&nbsp;欢迎您！</li>
                <li><a id="downloadpc" href="javascript:;" target="_blank">下载飞信客户端</a></li>
                <li><a id="officalsite" href="javascript:;" target="_blank">飞信官网</a></li>
                <li class="top_my_space"><a id="myspace" href="javascript:;" target="_blank">我的飞信同窗</a></li>
                <li><a id="feedback" href="javascript:;" target="_blank">意见反馈</a></li>
                <li><a id="quitIm" href="javascript:;">退出</a></li>
            </ul>
        </div>
        <!-- 头部 end -->
        <!--个人信息-->
        <div class="personal_information" id="personal_info">
            <span class="game_img" id="rt_img_link"></span>
            <div class="person">
                <img src="images/portraits/fetion.jpg" alt="" class="user_head_62" id="user_portrait" />
                <h1>
                    <b id="user_name"></b>(<label id="user_sid"></label>) <span id="user_status"><em>( 在线
                        )</em></span><!--鼠标事件可删除-->
                </h1>
                <p id="user_impresa" style="overflow: hidden; white-space: nowrap;">
                </p>
                <p id="user_impresa_input" style="display: none;">
                    <input type="text" maxlength="256" value="请输入心情短语..." name="" /></p>
                <ul class="service_icon">
                    <li><a id="myspace_icon" href="javascript:;" title="同窗" class="icon_tc" target="_blank">
                    </a></li>
                </ul>
                <div class="banner" id="banner_list">
                </div>
            </div>
        </div>
        <!--个人信息 end-->
        <!--主体-->
        <div class="main" id="main_window">
            <!--右边栏-->
            <div id="main_contact_list" class="friends">
                <div class="input_search" id="search_friends">
                    <span onmouseover="this.className='inputTxton'" onmouseout="this.className='inputTxt'"
                        class="inputTxt">
                        <input id="inputSearchFriends" type="text" value="查找好友..." />
                    </span><a id="btnSearchClear" class="search_value_clear" title="" href="#" style="">
                    </a>
                </div>
                <div id="group_func" class="group_func">
                    <a href="javascript:;" hidefocus='true' class="add_friends" title="好友" id="addbuddy_link">
                        好友</a>
                    <ul>
                        <li id='li_buddy' class="on"><span title="好友" class="person_btn" onclick="showPanel('buddy')">
                        </span></li>
                        <li id='li_group'><span title="群组" class="group_btn" onclick="showPanel('group')"></span>
                        </li>
                        <li id="li_gountxun"><span title="通讯录" class="phone_btn" onclick="showPanel('gountxun')">
                        </span></li>
                    </ul>
                </div>
                <!--通讯录-->
                <div class="phone_list none" id="phone_list">
                    <div class="phone_new">
                        <a href="javascript:;">新建联系人</a>
                    </div>
                    <div class="phone_leisure">
                        支持与通讯录联系人直接发起会话，沟通零门槛。赶紧添加联系人吧！ <em></em>
                    </div>
                    <ul id="phone_list_content" class="none">
                    </ul>
                </div>
                <!--通讯录 end-->
            </div>
            <!--右边栏 end-->
            <!--右边栏 推荐好友-->
            <div class="side" id="side">
                <div class="tit">
                    <h4>
                        可能认识的人</h4>
                    <span><a id="btnRefreshRecommend" href="javascript:;">换一批</a></span>
                </div>
                <div id="loadDiv" class="fx_ajax" style="position: absolute; text-align: center;
                    z-index: 90000; top: 40px; left: 0px; height: 300px; width: 232px;">
                    <div class="fxDiv">
                    </div>
                    <div class="fxColor fxTransp">
                    </div>
                </div>
                <div class="text_box none">
                    <p>
                        飞信找不到您可能认识的人，<br />
                        您可以尝试点击添加好友。</p>
                </div>
                <!--<ul class="List">
			        <li>
				        <div class="image"><img src="images/46x46.jpg" alt="慕容猫" title="慕容猫" /></div>
				        <dl>
					        <dt>慕容猫</dt>
					        <dd>53位共同好友</dd>
					        <dd><a href="#">加好友</a><a href="#">打招呼</a></dd>
				        </dl>
			        </li>
			    </ul>-->
            </div>
            <!--右边栏 end-->
            <!--主要内容-->
            <!--主要内容 end-->
        </div>
        <!--主体 end-->
        <!--任务栏-->
        <div id="taskbar" class="taskbar">
            <h6 id="cm_logo" class="cm_logo">
                中国移动通信</h6>
            <div id="msg_remind" class="msg_remind">
                <!--鼠标事件可删除-->
                <em></em><span></span>未读消息(<b>0</b>)
            </div>
            <ul id="msg_list" class="msg_list">
            </ul>
            <div id="msg_more" class="msg_more" style="display: none">
                <!--鼠标事件可删除-->
                <span></span>
            </div>
        </div>
        <!--任务栏 end-->
    </div>
    <!-- 框架 end -->
    <!-- 在线状态 -->
    <div class="pop_box pop_status" id="set_user_status" style="width: 130px; z-index: 1999;
        background: #fff; display: none">
        <div class="pop_box_inside">
            <ul>
                <li status="400"><i class="online"></i>在线</li>
                <li status="600"><i class="busy"></i>忙碌</li>
                <li status="100"><i class="away"></i>离开</li>
                <li status="0"><i class="hidden"></i>隐身</li>
            </ul>
            <div class="line">
            </div>
            <ul>
                <li status="300"><i class="away"></i>马上回来</li>
                <li status="850"><i class="busy"></i>会议中</li>
                <li status="500"><i class="busy"></i>接听电话</li>
                <li status="150"><i class="away"></i>外出就餐</li>
                <li id="userdefined_status"><i></i>设置自定义状态</li>
            </ul>
            <div class="line">
            </div>
            <p>
                <input id="input_rec_sound" checked="checked" type="checkbox" name="" />
                <label for="input_rec_sound">
                    开启声音提醒</label>
            </p>
            <div class="line">
            </div>
            <p>
                <input id="input_rec_msg" checked="checked" type="checkbox" name="" />
                <label for="input_rec_msg">
                    接收短信/彩信</label>
            </p>
        </div>
    </div>
    <!-- 在线状态 end -->
    <!-- 查找好友下拉 -->
    <div class="pop_box pop_search" style="width: 220px; display: none; background: #fff;
        z-index: 2999;" id="search_result">
        <div class="pop_box_inside">
            <ul class="friends_list_small friends_list_scroll">
            </ul>
        </div>
    </div>
    <!-- 查找好友下拉 end -->
    <!-- 个人信息面板 -->
    <div class="person_panel" style="background: #fff; display: none; z-index: 3999;"
        id="contact_info">
        <div class="person_panel_inside">
            <div class="pp_top">
                <img class="user_head_62" alt="" src="" id="contact_portrait" />
                <div class="pp_info">
                    <i id="contact_status"></i>
                    <h4 id="contact_name">
                    </h4>
                    <label id="contact_type">
                        飞信号：</label><label id="contact_id"></label><br />
                    分组：<label id="contact_list"></label>
                </div>
            </div>
            <div class="line" id="contact_impresa_line">
            </div>
            <!-- 使用span更有利于显示图片 -->
            <span id="contact_impresa"></span>
            <div class="line">
            </div>
            <div class="pp_feike">
                <a id="contact_space" title="" href="javascript:;" target="_blank">进入同窗</a>
            </div>
        </div>
    </div>
    <!-- 个人信息面板 end -->
    <!--好友操作-->
    <div class="pop_box pop_control" style="width: 96px; left: 370px; display: none;
        background: #fff" id="operate_info">
        <div class="pop_box_inside">
            <ul id="operate_items">
                <li><a id="operate_msg" title="发起会话" href="javascript:;">发起会话</a></li>
                <li><a id="operate_block" title="加入黑名单" href="javascript:;">加入黑名单</a></li>
                <li><a id="operate_add" title="添加好友" href="javascript:;">添加好友</a></li>
                <li><a id="operate_space" title="进入同窗" href="javascript:;" target="_blank">进入同窗</a></li>
            </ul>
        </div>
    </div>
    <!--通讯录操作-->
    <div id="popForTongXunLu" style="width: 106px; left: 370px;" class="pop_box pop_control none">
        <div class="pop_box_inside pop_box_inside1">
            <ul guid="">
                <li t="del" class="hover"><a class="icon_del" href="javascript:;">删除联系人</a></li>
                <li t="edit"><a class="icon_edit" href="javascript:;">编辑联系人</a></li>
            </ul>
        </div>
    </div>
    <!--遮罩层-->
    <div id="divMask" class="none" style="position: fixed; _position: absolute; z-index: 10000;
        width: 100%; height: 100%; _height: 1200px; top: 0px; left: 0px; cursor: default;
        background: #8c8c8c; opacity: 0.5; filter: alpha(opacity=50);">
    </div>
    <!--遮罩层 end-->
    <!--好友操作 end-->
    <script src="js/jquery-1.4.1.js?20130110" type="text/javascript"></script>
    <script src="js/WebUtility.js?20130110" type="text/javascript"></script>
    <script src="js/openPlatLogin.js?20130110" type="text/javascript"></script>
    <script src="js/WebIMUtility.js?20130110" type="text/javascript"></script>
    <script src="js/jPlugin.js?20130110" type="text/javascript"></script>
    <script src="js/group.js?20130110" type="text/javascript"></script>
    <script src="js/WebIM.js?20130110" type="text/javascript"></script>
</body>
</html>

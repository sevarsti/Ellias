<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta http-equiv="x-ua-compatible" content="ie=7" />
    <link href="../css/style.css?20130110" media="all" rel="stylesheet" type="text/css" />
    <link href="../css/freesms.css?20130110" media="all" rel="stylesheet" type="text/css" />
    <script src="../js/jquery-1.4.1.js?20130110" type="text/javascript"></script>
    <script src="../js/WebUtility.js?20130110" type="text/javascript"></script>
    <script src="../js/WebIMUtility.js?20130110" type="text/javascript"></script>
    <script src="../js/freeSms.js?20130124" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js?20130110"></script>
    <style type="text/css">
        body
        {
            background: #fff;
        }
    </style>
</head>
<body>
    <div class="free_sms" id="cont_1" style="display: block;">
        <div class="SMS_tis" style="display: none;">
            <em></em><a title="关闭" class="close" href="javascript:;"></a>
        </div>
        <ul class="free_sms_con" style="z-index: 1000;">
            <li class="clearfix"><strong>接收人：</strong>
                <div class="receive">
                    <div class="receive_man none">
                    </div>
                    <div class="receive_man_name" id="receive_man" style="">
                        <ul>
                            <!--
                            <li><span>陈浩<a href=""></a></span></li>
                            -->
                        </ul>
                    </div>
                    <div class="choose">
                        <a id="chooseFriendMore" class="choose_open" href="javascript:;">选择好友</a><input id="sendToMe"
                            type="checkbox" name="checkbox" /><label for="checkbox"><b>我自己</b>（给自己发送短信）</label>
                        <p class="choose_txt mt1" style="display: none">
                            最多可以选择64人，您可重新选择接收人。</p>
                        <ul class="choose_friend" style="display: none; z-index: 1;">
                        </ul>
                        <div class="choose_friend_name" style="display: none; z-index: 1;">
                            <h2>
                                <a href="javascript:;" id="chooseFriendMoreCloseBtn"></a><span class="none" style="color: #FF9900;">
                                    最多可以选择64人，您可重新选择接收人</span>
                            </h2>
                            <div class="choose_friname_con">
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="clearfix"><strong>短信内容：</strong>
                <div class="receive">
                    <textarea id="smsContent" class="sms_con" name="textarea"></textarea>
                    <div class="timing">
                        <span id="smsContentLenTips">还可输入<b id="smsContentLen">350</b>字</span>
                        <div class="timing_main">
                            <p id="ckb1" class="none">
                                <img alt="" src="../images/huiicon.jpg" />定时发送</p>
                            <div id="ckb2" class="">
                                <input id="timePannel" type="checkbox" name="checkbox2" style="margin-top: 3px;" /><label
                                    for="checkbox2">定时发送</label></div>
                            <div class="timing_con">
                                <a id="date" class="timing_time" href="javascript:;"></a>
                            </div>
                            <div class="timing_con pv">
                                <a id="hour" class="timing_hour" href="javascript:;"></a><em>时</em>
                                <div id="hour_list" class="hour_list none">
                                    <b>00</b> <b>01</b> <b>02</b> <b>03</b> <b>04</b> <b>05</b> <b>06</b> <b>07</b>
                                    <b>08</b> <b>09</b> <b>10</b> <b>11</b> <b>12</b> <b>13</b> <b>14</b> <b>15</b>
                                    <b>16</b> <b>17</b> <b>18</b> <b>19</b> <b>20</b> <b>21</b> <b>22</b> <b>23</b>
                                </div>
                            </div>
                            <div class="timing_con pv">
                                <a id="minute" class="timing_hour" href="javascript:;"></a><em>分</em>
                                <div id="minute_list" class="minute_list none">
                                    <b>00</b> <b>05</b> <b>10</b> <b>15</b> <b>20</b> <b>25</b> <b>30</b> <b>35</b>
                                    <b>40</b> <b>45</b> <b>50</b> <b>55</b>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <div class="free_sms_btn" style="z-index: 1;">
            <a class="btn_send_sms" href="javascript:;" id="submitBtn">发送短信</a>
        </div>
    </div>


</body>
</html>

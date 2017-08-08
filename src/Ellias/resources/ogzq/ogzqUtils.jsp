<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-15
  Time: 20:01:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="java.util.List" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <!--<script type='text/javascript' src='../include/json.js'> </script>-->
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
        <script type="text/javascript" src="../scripts/swfobject.js"></script>
    </head>
    <body>
    <table width="100%" class="frame" bgcolor="black" cellpadding="1" cellspacing="1">
        <tr class="head">
            <td colspan="5">球会活动</td>
        </tr>
        <tr class="row1">
            <td width="20%">
                <input type="button" value="领贝克汉姆个人战礼包" onclick="callFunction('getPersonalBeckhamGift')"/>
            </td>
            <td width="20%">
                <input type="button" value="领贝克汉姆球会战礼包" onclick="callFunction('getBeckhamClubGift')"/>
            </td>
            <td width="20%">
                <input type="button" value="领巴蒂个人战礼包" onclick="callFunction('getBatiGift')"/>
            </td>
            <td width="20%">
                <input type="button" value="领巴蒂球会战礼包" onclick="callFunction('getBatiClubGift')"/>
            </td>
            <td width="20%">
            </td>
        </tr>
        <tr class="row2">
            <td width="20%">
                <input type="button" value="领卡福个人战礼包" onclick="callFunction('getPersonalCafuGift')"/>
            </td>
            <td width="20%">
                <input type="button" value="领卡福球会战礼包" onclick="callFunction('getCafuClubGift')"/>
            </td>
            <td width="20%">
            </td>
            <td width="20%">
            </td>
            <td width="20%">
            </td>
        </tr>
        <tr class="head">
            <td colspan="5">活动线程</td>
        </tr>
        <tr class="row1">
            <td width="20%">
                <input type="button" value="领格子铺" onclick="callFunction('getGridGift')"/>
            </td>
            <td width="20%">
                <input type="button" value="启动大力丸" onclick="callFunction('beginDaliwan')"/>
            </td>
            <td width="20%">
                <input type="button" value="大力丸状态" onclick="callFunction('daliwanStatus')"/>
            </td>
            <td width="20%"></td>
            <td width="20%"></td>
        </tr>
        <tr class="head">
            <td colspan="5">免费活动</td>
        </tr>
        <tr class="row1">
            <td width="20%">
                <input type="button" value="免费疯狂砸蛋" onclick="callFunction('crazyEggFree');"/>
            </td>
            <td width="20%">
                <input type="button" value="连锁店" onclick="callFunction('liansuo')"/>
            </td>
            <td width="20%">
                <input type="button" value="球员集中营" onclick="callFunction('jizhongying')"/>
            </td>
            <td width="20%">
                <input type="button" value="刷刷乐" onclick="callFunction('shuashuale')"/>
            </td>
            <td width="20%">
                <input type="button" value="马上乐和" onclick="callFunction('mashanglehe')"/>
            </td>
        </tr>
        <tr class="row2">
            <td width="20%">
                <input type="button" value="超级转转" onclick="callFunction('superZhuanzhuan')"/>
            </td>
            <td width="20%">
                <input type="button" value="大折扣" onclick="callFunction('bigDiscount')"/>
            </td>
            <td width="20%">
                <input type="button" value="登录摇奖" onclick="callFunction('dengluyaojiang')"/>
            </td>
            <td width="20%">
                <input type="button" value="玩个球（参）" onclick="callFunction('playBall')"/>
            </td>
            <td width="20%">
                <input type="button" value="玩个球状态" onclick="callFunction('playBallStatus')"/>
            </td>
        </tr>
        <tr class="head">
            <td colspan="5">球队指令</td>
        </tr>
        <tr class="row1">
            <td width="20%">
                <input type="button" value="使用大油箱" onclick="callFunction('useYouxiang')"/>
            </td>
            <td width="20%">
                <input type="button" value="卖lv5道具" onclick="callFunction('doSellLv5')"/>
            </td>
            <td width="20%">
                <input type="button" value="开教练箱子" onclick="callFunction('openBox')"/>
            </td>
            <td width="20%">
                <input type="button" value="熔炼宝石(参)" onclick="callFunction('ronglianGem')"/>
            </td>
            <td width="20%">
                <input type="button" value="使用道具" onclick="callFunction('useItem')"/>
            </td>
        </tr>
        <tr class="row2">
            <td width="20%">
                <input type="button" value="银币商城买1级合同船" onclick="callFunction('buyCoinDaoju')"/>
            </td>
            <td width="20%">
                <input type="button" value="领取礼包" onclick="callFunction('getGift')"/>
            </td>
            <td width="20%">
                <input type="button" value="世俱小组投票" onclick="callFunction('worldclubGroupVote')"/>
            </td>
            <td width="20%">
                <input type="button" value="世俱淘汰投票" onclick="callFunction('worldclubPlayOffVote')"/>
            </td>
            <td width="20%">
                <input type="button" value="联盟冠军杯竞猜" onclick="callFunction('leagueChampionVote')"/>
            </td>
        </tr>
        <tr class="row1">
            <td width="20%">
                <input type="button" value="贝利积分升级" onclick="callFunction('upPele')"/>
            </td>
            <td width="20%">
                <input type="button" value="使用巅峰回豆" onclick="callFunction('useOgHuidou')"/>
            </td>
            <td width="20%">
                <input type="button" value="登录球会战" onclick="callFunction('doLoginTeamgame')"/>
            </td>
            <td width="20%">
            </td>
            <td width="20%">
            </td>
        </tr>
        <tr class="head">
            <td colspan="5">查看信息</td>
        </tr>
        <tr class="row1">
            <td width="20%">
                <input type="button" value="看欧冠对阵" onclick="callFunction('viewChampionCup')"/>
            </td>
            <td width="20%">
                <input type="button" value="欧冠投票（参）" onclick="callFunction('voteChampionCup')"/>
            </td>
            <td width="20%">
                <input type="button" value="读取球员数据" onclick="callFunction('loadPlayer')"/>
            </td>
            <td width="20%">
                <input type="button" value="查看球队实力（参）" onclick="callFunction('viewTeamAbility')"/>
            </td>
            <td width="20%">
                <input type="button" value="查看球队buff（参）" onclick="callFunction('viewBuff')"/>
            </td>
        </tr>
        <tr class="row2">
            <td width="20%">
                <input type="button" value="查看活动" onclick="callFunction('viewActivity')"/>
            </td>
            <td width="20%">
                <input type="button" value="新闻" onclick="callFunction('viewNews')"/>
            </td>
            <td width="20%">
                <input type="button" value="个人巅峰状态" onclick="callFunction('topChallengePersonStatus')"/>
            </td>
            <td width="20%">
                <input type="button" value="联盟争夺战排名" onclick="callFunction('viewLMGX')"/>
            </td>
            <td width="20%">
                <input type="button" value="贝利积分" onclick="callFunction('beiliStatus')"/>
            </td>
        </tr>
        <tr class="row1">
            <td width="20%">
                <input type="button" value="拜仁巅峰状态" onclick="callFunction('bayernChallengeStatus')"/>
            </td>
            <td width="20%">
            </td>
            <td width="20%">
            </td>
            <td width="20%">
            </td>
            <td width="20%">
            </td>
        </tr>
        <tr class="head">
            <td colspan="5">已废弃</td>
        </tr>
        <tr class="row1">
            <td width="20%">
                <input type="button" value="签到" onclick="callFunction('qiandao')"/>
            </td>
            <td width="20%">
                <input type="button" value="连连看" onclick="callFunction('lianliankan')"/>
            </td>
            <td width="20%">
                <input type="button" value="亚冠签到" onclick="callFunction('yaguanqiandao')"/>
            </td>
            <td width="20%">
                <input type="button" value="亚冠投票" onclick="callFunction('yaguanVote')"/>
            </td>
            <td width="20%">
                <input type="button" value="亚冠领礼包" onclick="callFunction('yaguanGet')"/>
            </td>
        </tr>
        <tr class="row2">
            <td width="20%">
                <input type="button" value="亚冠兑换" onclick="callFunction('yaguanDuihuan')"/>
            </td>
            <td width="20%">
                <input type="button" value="启动巅峰球会" onclick="callFunction('custTopChallenge')"/>
            </td>
            <td width="20%">
                <input type="button" value="吹龙鱼" onclick="callFunction('fishBlow')"/>
            </td>
            <td width="20%"></td>
            <td width="20%"></td>
        </tr>
        <tr class="head">
            <td colspan="5">其他</td>
        </tr>
        <tr class="row1">
            <td width="20%">
                <input type="button" value="镶嵌搜索状态" onclick="javascript:window.open('xiangqianTactic.jsp');"/>
            </td>
            <td width="20%">
                <input type="button" value="增加实力" onclick="javascript:window.open('uplevel.jsp');"/>
            </td>
            <td width="20%">
                <input type="button" value="查看阵型" onclick="javascript:window.open('mytactic.jsp');"/>
            </td>
            <td width="20%">
                <input type="button" value="查看国旗" onclick="javascript:window.open('flag.jsp');"/>
            </td>
            <td width="20%">
                <input type="button" value="查看球会实力" onclick="javascript:window.open('viewClubs.jsp');"/>
            </td>
        </tr>
        <tr class="row2">
            <td width="20%">
                <input type="button" value="查看俱乐部福利" onclick="javascript:window.open('clubbuff.jsp');"/>
            </td>
            <td width="20%">
                <input type="button" value="查看球会大作战" onclick="javascript:window.open('monitor.jsp?show=1');"/>
            </td>
            <td width="20%">
                <input type="button" value="查看各种积分" onclick="javascript:window.open('score.jsp');"/>
            </td>
            <td width="20%">
            </td>
            <td width="20%">
                <input type="button" value="查看全平台实力" onclick="javascript:window.open('allTop.jsp?show=1');"/>
            </td>
        </tr>
    </table>
    <br/>
    参数：<input id="param" value="" size="50" type="text"/>
    <br/>
    <span id="content"/>
    </body>
<script type="text/javascript">
    function callFunction(methodName) {
        var param = document.getElementById('param').value;
        document.getElementById('content').innerHTML = '';
        OgzqDwr.callFunction(methodName, param, after);
        document.getElementById('param').value = '';
    }

    function after(obj) {
        document.getElementById('content').innerHTML = obj;
        alert('done');
    }
</script>
</html>
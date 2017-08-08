<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--[939,151,1] published at 2011-09-16 14:35:09 from #235 by 90-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GB2312"/>
    <link href="http://games.sina.com.cn/z/qiuqiu/260.css" rel="stylesheet" type="text/css"/>
    <script src="http://i1.sinaimg.cn/gm/qiuqiu/images/jQuery.js" type="text/javascript"></script>
    <script src="http://i2.sinaimg.cn/gm/qiuqiu/images/imgPlayer091126.js" type="text/javascript"></script>
    <title>《热血球球》首页</title>

    <!-- denglu -->
    <script language="javascript" type="text/javascript"
            src="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/js/vdialog.js?dewf434"></script>

    <link href="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/css/fudongww.css?dewf434" rel="stylesheet"
          type="text/css"/>

    <link href="http://i.sso.sina.com.cn/css/cardtips.css" rel="stylesheet" type="text/css" media="all"/>
    <script language="javascript" type="text/javascript"
            src="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/js/cardtips.js?dewf434"></script>

    <script language="javascript" type="text/javascript"
            src="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/js/ssoconfig.js?dd125?dewf434"></script>
    <script language='javascript' src='http://i.sso.sina.com.cn/js/ssologin.js'></script>
    <!-- denglu -->
    <!-- 玩玩头JS -->
    <script src="http://event.games.sina.com.cn/xuyuanqiang/js/jquery.min.js"></script>
    <script type="text/javascript" src="http://i.wanwan.sina.com.cn/header/h1.js"></script>
    <!-- 玩玩头JS -->

    <!-- jietuJS -->
    <SCRIPT type=text/javascript
            src="http://i3.sinaimg.cn/gm/qiuqiu/img/jQuery-1.3.2.js"></SCRIPT>
    <LINK rel=stylesheet type=text/css href="http://i0.sinaimg.cn/gm/qiuqiu/img/thickbox.css"
          media=screen>
    <SCRIPT type=text/javascript
            src="http://i2.sinaimg.cn/gm/qiuqiu/img/thickbox-compressed.js"></SCRIPT>
    <!-- jietuJS -->
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
</head>

<body><!-- SUDA_CODE_START -->
<div style='position:absolute;top:0;left:0;width:0;height:0;z-index:1'>
    <div style='position:absolute;top:0;left:0;width:1;height:1;'>
        <iframe id='SUDA_FC' src='' width=1 height=1 SCROLLING=NO FRAMEBORDER=0></iframe>
    </div>
    <div style='position:absolute;top:0;left:0;width:0;height:0;visibility:hidden' id='SUDA_CS_DIV'></div>
</div>
<script type="text/javascript">
    //<!--
    var _SGUP_ = "SGUP";
    var _SGUP_REQ_ = "http://sgup.beacon.sina.com.cn/cgi-bin/gsgup";
    var sgup = "";
    var SSL = {Config:{},Space:function(str) {
        var a = str,o = null;
        a = a.split('.');
        o = SSL;
        for (i = 0,len = a.length; i < len; i++) {
            o[a[i]] = o[a[i]] || {};
            o = o[a[i]]
        }
        return o
    }};
    SSL.Space('Global');
    SSL.Space('Core.Dom');
    SSL.Space('Core.Event');
    SSL.Space('App');
    SSL.Global = {win:window || {},doc:document,nav:navigator,loc:location};
    SSL.Core.Dom = {get:function(id) {
        return document.getElementById(id)
    }};
    SSL.Core.Event = {on:function() {
    }};
    SSL.App = {
        _S_gConType:function() {
            var ct = "";
            try {
                SSL.Global.doc.body.addBehavior("#default#clientCaps");
                ct = SSL.Global.doc.body.connectionType
            } catch(e) {
                ct = "unkown"
            }
            return ct
        },
        _S_gKeyV:function(src, k, e, sp) {
            if (src == "") {
                return""
            }
            if (sp == "") {
                sp = "="
            }
            k = k + sp;
            var ps = src.indexOf(k);
            if (ps < 0) {
                return""
            }
            ps = ps + k.length;
            var pe = src.indexOf(e, ps);
            if (pe < ps) {
                pe = src.length
            }
            return src.substring(ps, pe)
        },
        _S_gUCk:function(ckName) {
            if ((undefined == ckName) || ("" == ckName))return"";
            return SSL.App._S_gKeyV(SSL.Global.doc.cookie, ckName, ";", "")
        },
        _S_sUCk:function(ckName, ckValue, ckDays, ckDomain) {
            if (ckValue != null) {
                if ((undefined == ckDomain) || (null == ckDomain)) {
                    ckDomain = "sina.com.cn";
                }
                if ((undefined == ckDays) || (null == ckDays) || ('' == ckDays)) {
                    SSL.Global.doc.cookie = ckName + "=" + ckValue + ";domain=" + ckDomain + ";path=/";
                } else {
                    var now = new Date();
                    var time = now.getTime();
                    time = time + 86400000 * ckDays;
                    now.setTime(time);
                    time = now.getTime();
                    SSL.Global.doc.cookie = ckName + "=" + ckValue + ";domain=" + ckDomain + ";expires=" + now.toUTCString() + ";path=/";
                }
            }
        },
        _S_gJVer:function(_S_NAV_, _S_NAN_) {
            var p,appsign,appver,jsver = 1.0,isN6 = 0;
            if ('MSIE' == _S_NAN_) {
                appsign = 'MSIE';
                p = _S_NAV_.indexOf(appsign);
                if (p >= 0) {
                    appver = parseInt(_S_NAV_.substring(p + 5));
                    if (3 <= appver) {
                        jsver = 1.1;
                        if (4 <= appver) {
                            jsver = 1.3
                        }
                    }
                }
            } else if (("Netscape" == _S_NAN_) || ("Opera" == _S_NAN_) || ("Mozilla" == _S_NAN_)) {
                jsver = 1.3;
                appsign = 'Netscape6';
                p = _S_NAV_.indexOf(appsign);
                if (p >= 0) {
                    jsver = 1.5
                }
            }
            return jsver
        },
        _S_gFVer:function(nav) {
            var ua = SSL.Global.nav.userAgent.toLowerCase();
            var flash_version = 0;
            if (SSL.Global.nav.plugins && SSL.Global.nav.plugins.length) {
                var p = SSL.Global.nav.plugins['Shockwave Flash'];
                if (typeof p == 'object') {
                    for (var i = 10; i >= 3; i--) {
                        if (p.description && p.description.indexOf(' ' + i + '.') != -1) {
                            flash_version = i;
                            break
                        }
                    }
                }
            } else if (ua.indexOf("msie") != -1 && ua.indexOf("win") != -1 && parseInt(SSL.Global.nav.appVersion) >= 4 && ua.indexOf("16bit") == -1) {
                for (var i = 10; i >= 2; i--) {
                    try {
                        var object = eval("new ActiveXObject('ShockwaveFlash.ShockwaveFlash." + i + "');");
                        if (object) {
                            flash_version = i;
                            break
                        }
                    } catch(e) {
                    }
                }
            } else if (ua.indexOf("webtv/2.5") != -1) {
                flash_version = 3
            } else if (ua.indexOf("webtv") != -1) {
                flash_version = 2
            }
            return flash_version
        },
        _S_gMeta:function(MName, pidx) {
            var pMeta = SSL.Global.doc.getElementsByName(MName);
            var idx = 0;
            if (pidx > 0) {
                idx = pidx
            }
            return(pMeta.length > idx) ? pMeta[idx].content : ""
        },
        _S_gHost:function(sUrl) {
            var r = new RegExp('^http(?:s)?\://([^/]+)', 'im');
            if (sUrl.match(r)) {
                return sUrl.match(r)[1].toString()
            } else {
                return""
            }
        },
        _S_gDomain:function(sHost) {
            var p = sHost.indexOf('.sina.');
            if (p > 0) {
                return sHost.substr(0, p)
            } else {
                return sHost
            }
        },
        _S_gTJMTMeta:function() {
            return SSL.App._S_gMeta("mediaid")
        },
        _S_gTJZTMeta:function() {
            var zt = SSL.App._S_gMeta('subjectid');
            zt.replace(",", ".");
            zt.replace(";", ",");
            return zt
        },
        _S_isFreshMeta:function() {
            var ph = SSL.Global.doc.documentElement.innerHTML.substring(0, 1024);
            var reg = new RegExp("<meta\\s*http-equiv\\s*=((\\s*refresh\\s*)|(\'refresh\')|(\"refresh\"))\s*content\s*=", "ig");
            return reg.test(ph)
        },
        _S_isIFrameSelf:function(minH, minW) {
            if (SSL.Global.win.top == SSL.Global.win) {
                return false
            } else {
                try {
                    if (SSL.Global.doc.body.clientHeight == 0) {
                        return false
                    }
                    if ((SSL.Global.doc.body.clientHeight >= minH) && (SSL.Global.doc.body.clientWidth >= minW)) {
                        return false
                    } else {
                        return true
                    }
                } catch(e) {
                    return true
                }
            }
        },
        _S_isHome:function(curl) {
            var isH = "";
            try {
                SSL.Global.doc.body.addBehavior("#default#homePage");
                isH = SSL.Global.doc.body.isHomePage(curl) ? "Y" : "N"
            } catch(e) {
                isH = "unkown"
            }
            return isH;
        }
    }
    function SUDA(config, ext1, ext2) {
        var SG = SSL.Global,SSD = SSL.Core.Dom,SSE = SSL.Core.Event,SA = SSL.App;
        var _S_JV_ = "webbug_meta_ref_mod_noiframe_async_fc_:9.10a",_S_DPID_ = "-9999-0-0-1";
        var _S_NAN_ = SG.nav.appName.indexOf('Microsoft Internet Explorer') > -1 ? 'MSIE' : SG.nav.appName;
        var _S_NAV_ = SG.nav.appVersion;
        var _S_PURL_ = SG.loc.href.toLowerCase();
        var _S_PREF_ = SG.doc.referrer.toLowerCase();
        var _SP_MPID_ = "";
        var _S_PID_ = "",
            _S_UNA_ = "SUP",
            _S_MI_ = "",
            _S_SID_ = "Apache",
            _S_GID_ = "SINAGLOBAL",
            _S_LV_ = "ULV",
            _S_UO_ = "UOR",
            _S_UPA_ = "_s_upa",
            _S_IFW = 320,
            _S_IFH = 240,
            _S_GIDT = 0,
            _S_EXT1 = "",
            _S_EXT2 = "",
            _S_SMC = 0,
            _S_SMM = 10000,
            _S_ET = 0,
            _S_ACC_ = "_s_acc";
        var _S_HTTP = _S_PURL_.indexOf('https') > -1 ? 'https://' : 'http://',_S_BCNDOMAIN = "beacon.sina.com.cn",_S_CP_RF = _S_HTTP + _S_BCNDOMAIN + "/a.gif",_S_CP_RF_D = _S_HTTP + _S_BCNDOMAIN + "/d.gif",_S_CP_RF_E = _S_HTTP + _S_BCNDOMAIN + "/e.gif",_S_CP_FC = _S_HTTP + _S_BCNDOMAIN + "/fc.html";
        var _S_T1 = 100,_S_T2 = 2000;
        var handler = {
            _S_sSID:function() {
                handler._S_p2Bcn("", _S_CP_RF_D)
            },
            _S_gsSID:function() {
                var sid = SA._S_gUCk(_S_SID_);
                if ("" == sid) {
                    handler._S_sSID()
                }
                return sid
            },
            _S_sGID:function(gid) {
                if ("" != gid) {
                    SA._S_sUCk(_S_GID_, gid, 3650)
                }
            },
            _S_gGID:function() {
                return SA._S_gUCk(_S_GID_)
            },
            _S_gsGID:function() {
                if ("" != _S_GID_) {
                    var gid = SA._S_gUCk(_S_GID_);
                    if ("" == gid) {
                        if (SA._S_gFVer(SG.nav) <= 6) {
                            return ""
                        } else {
                            handler._S_IFC2GID()
                        }
                    }
                    return gid
                } else {
                    return""
                }
            },
            _S_IFC2GID:function() {
                var _S_ifc = SSD.get("SUDA_FC");
                if (_S_ifc) {
                    _S_ifc.src = _S_CP_FC + "?a=g&n=" + _S_GID_ + "&r=" + Math.random()
                }
            },
            _S_gCid:function() {
                try {
                    var metaTxt = SA._S_gMeta("publishid");
                    if ("" != metaTxt) {
                        var pbidList = metaTxt.split(",");
                        if (pbidList.length > 0) {
                            if (pbidList.length >= 3) {
                                _S_DPID_ = "-9999-0-" + pbidList[1] + "-" + pbidList[2]
                            }
                            return pbidList[0]
                        }
                    } else {
                        return"0"
                    }
                } catch(e) {
                    return"0"
                }
            },
            _S_gAEC:function() {
                return SA._S_gUCk(_S_ACC_)
            },_S_sAEC:function(eid) {
            if ("" == eid) {
                return
            }
            var acc = handler._S_gAEC();
            if (acc.indexOf(eid + ",") < 0) {
                acc = acc + eid + ",";
            }
            SA._S_sUCk(_S_ACC_, acc, 7);
        },
            _S_p2Bcn:function(q, u) {
                var scd = SSD.get("SUDA_CS_DIV");
                if (null != scd) {
                    var now = new Date();
                    scd.innerHTML = "<img width=0 height=0 src='" + u + "?" + q + "&gUid_" + now.getTime() + "' border='0' alt='' />"
                }
            },
            _S_gSUP:function() {
                if (_S_MI_ != "") {
                    return _S_MI_
                }
                var sup = unescape(SA._S_gUCk(_S_UNA_));
                if (sup != "") {
                    var ag = SA._S_gKeyV(sup, "ag", "&", "");
                    var user = SA._S_gKeyV(sup, "user", "&", "");
                    var uid = SA._S_gKeyV(sup, "uid", "&", "");
                    var sex = SA._S_gKeyV(sup, "sex", "&", "");
                    var bday = SA._S_gKeyV(sup, "dob", "&", "");
                    _S_MI_ = ag + ":" + user + ":" + uid + ":" + sex + ":" + bday;
                    return _S_MI_
                } else {
                    return""
                }
            },
            _S_gsLVisit:function(sid) {
                var lvi = SA._S_gUCk(_S_LV_);
                var lva = lvi.split(":");
                var lvr = "";
                if (lva.length >= 6) {
                    if (sid != lva[4]) {
                        var lvn = new Date();
                        var lvd = new Date(parseInt(lva[0]));
                        lva[1] = parseInt(lva[1]) + 1;
                        if (lvn.getMonth() != lvd.getMonth()) {
                            lva[2] = 1
                        } else {
                            lva[2] = parseInt(lva[2]) + 1
                        }
                        if (((lvn.getTime() - lvd.getTime()) / 86400000) >= 7) {
                            lva[3] = 1
                        } else {
                            if (lvn.getDay() < lvd.getDay()) {
                                lva[3] = 1
                            } else {
                                lva[3] = parseInt(lva[3]) + 1
                            }
                        }
                        lvr = lva[0] + ":" + lva[1] + ":" + lva[2] + ":" + lva[3];
                        lva[5] = lva[0];
                        lva[0] = lvn.getTime();
                        SA._S_sUCk(_S_LV_, lva[0] + ":" + lva[1] + ":" + lva[2] + ":" + lva[3] + ":" + sid + ":" + lva[5], 360)
                    } else {
                        lvr = lva[5] + ":" + lva[1] + ":" + lva[2] + ":" + lva[3]
                    }
                } else {
                    var lvn = new Date();
                    lvr = ":1:1:1";
                    SA._S_sUCk(_S_LV_, lvn.getTime() + lvr + ":" + sid + ":", 360)
                }
                return lvr
            },
            _S_gUOR:function() {
                var uoc = SA._S_gUCk(_S_UO_);
                var upa = uoc.split(":");
                if (upa.length >= 2) {
                    return upa[0]
                } else {
                    return""
                }
            },
            _S_sUOR:function() {
                var uoc = SA._S_gUCk(_S_UO_),uor = "",uol = "",up_t = "",up = "";
                var re = /[&|?]c=spr(_[A-Za-z0-9]{1,}){3,}/;
                var ct = new Date();
                if (_S_PURL_.match(re)) {
                    up_t = _S_PURL_.match(re)[0]
                } else {
                    if (_S_PREF_.match(re)) {
                        up_t = _S_PREF_.match(re)[0]
                    }
                }
                if (up_t != "") {
                    up_t = up_t.substr(3) + ":" + ct.getTime()
                }
                if (uoc == "") {
                    if (SA._S_gUCk(_S_LV_) == "" && SA._S_gUCk(_S_LV_) == "") {
                        uor = SA._S_gDomain(SA._S_gHost(_S_PREF_));
                        uol = SA._S_gDomain(SA._S_gHost(_S_PURL_))
                    }
                    SA._S_sUCk(_S_UO_, uor + "," + uol + "," + up_t, 365)
                } else {
                    var ucg = 0,uoa = uoc.split(",");
                    if (uoa.length >= 1) {
                        uor = uoa[0]
                    }
                    if (uoa.length >= 2) {
                        uol = uoa[1]
                    }
                    if (uoa.length >= 3) {
                        up = uoa[2]
                    }
                    if (up_t != "") {
                        ucg = 1
                    } else {
                        var upa = up.split(":");
                        if (upa.length >= 2) {
                            var upd = new Date(parseInt(upa[1]));
                            if (upd.getTime() < (ct.getTime() - 86400000 * 30)) {
                                ucg = 1
                            }
                        }
                    }
                    if (ucg) {
                        SA._S_sUCk(_S_UO_, uor + "," + uol + "," + up_t, 365)
                    }
                }
            },
            _S_gRef:function() {
                var re = /^[^\?&#]*.swf([\?#])?/;
                if ((_S_PREF_ == "") || (_S_PREF_.match(re))) {
                    var ref = SA._S_gKeyV(_S_PURL_, "ref", "&", "");
                    if (ref != "") {
                        return ref
                    }
                }
                return _S_PREF_
            },
            _S_MEvent:function() {
                if (_S_SMC == 0) {
                    _S_SMC++;
                    var c = SA._S_gUCk(_S_UPA_);
                    if (c == "") {
                        c = 0
                    }
                    c++;
                    if (c < _S_SMM) {
                        var re = /[&|?]c=spr(_[A-Za-z0-9]{2,}){3,}/;
                        if (_S_PURL_.match(re) || _S_PREF_.match(re)) {
                            c = c + _S_SMM
                        }
                    }
                    SA._S_sUCk(_S_UPA_, c)
                }
            },
            _S_gMET:function() {
                var c = SA._S_gUCk(_S_UPA_);
                if (c == "") {
                    c = 0
                }
                return c
            },
            _S_gCInfo_v2:function() {
                var now = new Date();
                return"sz:" + screen.width + "x" + screen.height + "|dp:" + screen.colorDepth + "|ac:" + SG.nav.appCodeName + "|an:" + _S_NAN_ + "|cpu:" + SG.nav.cpuClass + "|pf:" + SG.nav.platform + "|jv:" + SA._S_gJVer(_S_NAV_, _S_NAN_) + "|ct:" + SA._S_gConType() + "|lg:" + SG.nav.systemLanguage + "|tz:" + now.getTimezoneOffset() / 60 + "|fv:" + SA._S_gFVer(SG.nav)
            },
            _S_gPInfo_v2:function(pid, ref) {
                if ((undefined == pid) || ("" == pid)) {
                    pid = handler._S_gCid() + _S_DPID_
                }
                return"pid:" + pid + "|st:" + handler._S_gMET() + "|et:" + _S_ET + "|ref:" + escape(ref) + "|hp:" + SA._S_isHome(_S_PURL_) + "|PGLS:" + SA._S_gMeta("stencil") + "|ZT:" + escape(SA._S_gTJZTMeta()) + "|MT:" + escape(SA._S_gTJMTMeta()) + "|keys:"
            },
            _S_gUInfo_v2:function(vid) {
                return"vid:" + vid + "|sid:" + handler._S_gsSID() + "|lv:" + handler._S_gsLVisit(handler._S_gsSID()) + "|un:" + handler._S_gSUP() + "|uo:" + handler._S_gUOR() + "|ae:" + handler._S_gAEC()
            },
            _S_gEXTInfo_v2:function(ext1, ext2) {
                _S_EXT1 = (undefined == ext1) ? _S_EXT1 : ext1;
                _S_EXT2 = (undefined == ext2) ? _S_EXT2 : ext2;
                return"ex1:" + _S_EXT1 + "|ex2:" + _S_EXT2
            },
            _S_pBeacon:function(pid, ext1, ext2) {
                try {
                    var gid = handler._S_gsGID();
                    if ("" == gid) {
                        if (_S_GIDT < 1) {
                            setTimeout(function() {
                                handler._S_pBeacon(pid, ext1, ext2)
                            }, _S_T2);
                            _S_GIDT++;
                            return
                        } else {
                            gid = handler._S_gsSID();
                            handler._S_sGID(gid)
                        }
                    }
                    var sgup_ck = SA._S_gUCk(_SGUP_);
                    if (sgup_ck == "") {
                        var channary = location.hostname.split(".");
                        var cur_ch = (channary.length > 3) ? channary[channary.length - 4] : "";
                        handler.__loadScript(_SGUP_REQ_, function() {
                            if (sgup != "") {
                                SA._S_sUCk(_SGUP_, sgup, 7);
                            } else {
                                SA._S_sUCk(_SGUP_, "0", 3);
                            }
                        });
                    }
                    var sVer = "V=2";
                    var sCI = handler._S_gCInfo_v2();
                    var sPI = handler._S_gPInfo_v2(pid, handler._S_gRef());
                    var sUI = handler._S_gUInfo_v2(gid);
                    var sEX = handler._S_gEXTInfo_v2(ext1, ext2);
                    var lbStr = sVer + "&CI=" + sCI + "&PI=" + sPI + "&UI=" + sUI + "&EX=" + sEX;
                    handler._S_p2Bcn(lbStr, _S_CP_RF)
                } catch(e) {
                }
            },
            __loadScript:function(url, callback, encode) {
                var script = document.createElement('script');
                script.type = 'text/javascript';
                if (encode)script.charset = encode;
                if (callback)script.onload = script.onreadystatechange = function() {
                    if (script.readyState && script.readyState != 'loaded' && script.readyState != 'complete')return;
                    script.onreadystatechange = script.onload = null;
                    callback();
                };
                script.src = url;
                document.getElementsByTagName('head')[0].appendChild(script);
            },
            _S_acTrack_i:function(eid, p) {
                if (("" == eid) || (undefined == eid)) {
                    return
                }
                handler._S_sAEC(eid);
                if (0 == p) {
                    return
                }
                var s = "AcTrack||" + handler._S_gGID() + "||" + handler._S_gsSID() + "||" + handler._S_gSUP() + "||" + eid + "||";
                handler._S_p2Bcn(s, _S_CP_RF_E)
            },
            _S_uaTrack_i:function(acode, aext) {
                var s = "UATrack||" + handler._S_gGID() + "||" + handler._S_gsSID() + "||" + handler._S_gSUP() + "||" + acode + "||" + aext + "||" + handler._S_gRef() + "||";
                handler._S_p2Bcn(s, _S_CP_RF_E)
            }
        };
        if (_S_SMC == 0) {
            if ('MSIE' == _S_NAN_) {
                SSL.Global.doc.attachEvent("onclick", handler._S_MEvent);
                SSL.Global.doc.attachEvent("onmousemove", handler._S_MEvent);
                SSL.Global.doc.attachEvent("onscroll", handler._S_MEvent)
            } else {
                SSL.Global.doc.addEventListener("click", handler._S_MEvent, false);
                SSL.Global.doc.addEventListener("mousemove", handler._S_MEvent, false);
                SSL.Global.doc.addEventListener("scroll", handler._S_MEvent, false)
            }
        }
        ;
        handler._S_sUOR();
        return{
            _S_pSt:function(pid, ext1, ext2) {
                try {
                    if ((SA._S_isFreshMeta()) || (SA._S_isIFrameSelf(_S_IFH, _S_IFW))) {
                        return
                    }
                    if (_S_ET > 0) {
                        return
                    }
                    ++_S_ET;
                    setTimeout(function() {
                        handler._S_gsSID()
                    }, _S_T1);
                    setTimeout(function() {
                        handler._S_pBeacon(pid, ext1, ext2, 0)
                    }, _S_T2)
                } catch(e) {
                }
            },
            _S_pStM:function(pid, ext1, ext2) {
                ++_S_ET;
                handler._S_pBeacon(pid, ((undefined == ext1) ? handler._S_upExt1() : ext1), ext2)
            },
            _S_acTrack:function(eid, p) {
                try {
                    if ((undefined != eid) && ("" != eid)) {
                        setTimeout(function() {
                            handler._S_acTrack_i(eid, p)
                        }, _S_T1)
                    }
                } catch(e) {
                }
            },
            _S_uaTrack:function(acode, aext) {
                try {
                    if (undefined == acode) {
                        acode = ""
                    }
                    if (undefined == aext) {
                        aext = ""
                    }
                    if (("" != acode) || ("" != aext)) {
                        setTimeout(function() {
                            handler._S_uaTrack_i(acode, aext)
                        }, _S_T1)
                    }
                } catch(e) {
                }
            }
        }
    }
    var GB_SUDA;
    if (GB_SUDA == null) {
        GB_SUDA = new SUDA({})
    }
    var _S_PID_ = "";
    function _S_pSt(pid, ext1, ext2) {
        GB_SUDA._S_pSt(pid, ext1, ext2);
    }
    function _S_pStM(pid, ext1, ext2) {
        GB_SUDA._S_pStM(pid, ext1, ext2);
    }
    function _S_acTrack(eid) {
        GB_SUDA._S_acTrack(eid, 1);
    }
    function _S_uaTrack(acode, aext) {
        GB_SUDA._S_uaTrack(acode, aext);
    }
    //-->
</script>
<script type="text/javascript">
    //<!--
    GB_SUDA._S_pSt("");
    //-->
</script>

<noScript>
    <div style='position:absolute;top:0;left:0;width:0;height:0;visibility:hidden'><img width=0 height=0
                                                                                        src='http://beacon.sina.com.cn/a.gif?noScript'
                                                                                        border='0' alt=''/></div>
</noScript>
<!-- SUDA_CODE_END -->

<!-- for iPad begin -->
<script type="text/javascript">
    (function() {
        if (!/\((iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
            return
        }
        ;
        var iPadScript = document.createElement('script');
        iPadScript.src = 'http://news.sina.com.cn/js/ui/ipad/reset.js';
        document.getElementsByTagName('head')[0].appendChild(iPadScript);
    })();
</script>
<!-- for iPad end -->

<!-- SSO_GETCOOKIE_START -->
<script type="text/javascript">var sinaSSOManager = sinaSSOManager || {};
sinaSSOManager.getSinaCookie = function() {
    function dc(u) {
        if (u == undefined) {
            return""
        }
        var decoded = decodeURIComponent(u);
        return decoded == "null" ? "" : decoded
    }
    function ps(str) {
        var arr = str.split("&");
        var arrtmp;
        var arrResult = {};
        for (var i = 0; i < arr.length; i++) {
            arrtmp = arr[i].split("=");
            arrResult[arrtmp[0]] = dc(arrtmp[1])
        }
        return arrResult
    }
    function gC(name) {
        var Res = eval("/" + name + "=([^;]+)/").exec(document.cookie);
        return Res == null ? null : Res[1]
    }
    var sup = dc(gC("SUP"));
    if (!sup) {
        sup = dc(gC("SUR"))
    }
    if (!sup) {
        return null
    }
    return ps(sup)
};</script>
<!-- SSO_GETCOOKIE_END -->

<script type="text/javascript">new function(r, s, t) {
    this.a = function(n, t, e) {
        if (window.addEventListener) {
            n.addEventListener(t, e, false);
        } else if (window.attachEvent) {
            n.attachEvent("on" + t, e);
        }
    };
    this.b = function(f) {
        var t = this;
        return function() {
            return f.apply(t, arguments);
        };
    };
    this.c = function() {
        var f = document.getElementsByTagName("form");
        for (var i = 0; i < f.length; i++) {
            var o = f[i].action;
            if (this.r.test(o)) {
                f[i].action = o.replace(this.r, this.s);
            }
        }
    };
    this.r = r;
    this.s = s;
    this.d = setInterval(this.b(this.c), t);
    this.a(window, "load", this.b(function() {
        this.c();
        clearInterval(this.d);
    }));
}
(/http:\/\/www\.google\.c(om|n)\/search/,"http://keyword.sina.com.cn/searchword.php",250);</script>


<!--顶部导航:begin-->
<!-- 加玩玩头开始 -->
<iframe id="h1frame" src="http://i.wanwan.sina.com.cn/header/h1.php" style="padding:0; margin:0;" frameborder="0"
        width="100%" height="35"></iframe>
<!-- 加玩玩头结束 -->
<!--  <div id="topl0421">

<div id="conwidth">
<div class="close"><a href="#"><img src="http://i3.sinaimg.cn/gm/qiuqiu/images/close0421.gif" border="0" /></a></div>
<ul>
<li><a href="#"><img src="http://i1.sinaimg.cn/gm/qiuqiu/images/btn0421-03.gif" border="0" /></a></li>
<li><a href="#"><img src="http://i0.sinaimg.cn/gm/qiuqiu/images/btn0421-02.gif" border="0" /></a></li>
<li><a href="#"><img src="http://i3.sinaimg.cn/gm/qiuqiu/images/btn0421-01.gif" border="0" /></a></li>
</ul>

<div class="dlogo dleft"><img src="http://i2.sinaimg.cn/gm/qiuqiu/images/s-logo0421.gif" /></div>

<div class="dword dleft">登陆名</div>
<div class="dleft">  <input type="text" name="textfield" id="textfield" /></div>
<div class="mma0421 dword dleft">密码</div>
<div class="dleft">  <input type="text" name="textfield" id="textfield" /></div>
<div class="tc0421 dleft"><a href="#"><img src="http://i1.sinaimg.cn/gm/qiuqiu/images/dl0421.gif" /></a></div>

</div>
</div>  -->
<!--顶部导航:end-->

<div class="main_01">
    <script src=http://games.sina.com.cn/131/20100621/43.js></script>
</div>

<div class="main_02">
    <div class="main_02_"></div>
</div>
<div class="main_03">
    <div class="main_03_"></div>
</div>
<div class="main_04">
<div class="main_04_">
<div class="main_04_body">

<div class="login">
    <style>
        <!--

        .server {
            width: 214px;
            margin: 0 auto;
            overflow: hidden;
        }

        .server li {
            background: url( http://i1.sinaimg.cn/gm/news/2010/0722/fwq_01.jpg );
            width: 214px;
            height: 31px;
            line-height: 31px;
            margin-top: 8px;
        }

        .server a {
            color: #ffffff;
            text-decoration: none;
        }

        .server_1 {
            width: 214px;
            margin: 0 auto;
            overflow: hidden;
        }

        .server_1 li {
            background: url( http://i2.sinaimg.cn/gm/news/2010/0722/fwq_02.jpg );
            width: 214px;
            height: 31px;
            line-height: 31px;
            margin-top: 8px;
        }

        .server_1 a {
            color: #ffffff;
            text-decoration: none;
        }

        .server_2 {
            width: 214px;
            margin: 0 auto;
            overflow: hidden;
        }

        .server_2 li {
            background: url( http://i3.sinaimg.cn/gm/news/2010/0722/fwq_03.jpg );
            width: 214px;
            height: 41px;
            line-height: 41px;
            margin-top: 8px;
            margin-bottom: 10px;
        }

        .server_2 a {
            color: #ffffff;
            text-decoration: none;
        }

        -->
    </style>
    <ul class="server_1">
        <li>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);"
                                       onclick="_gogame_Ww('webgame03603','http://s4.qiuqiu.2010.sina.com/login.aspx');return false;">冲出亚洲（火爆新服）&nbsp;&nbsp;&nbsp;&nbsp;</a>
        </li>
    </ul>
    <ul class="server_1">
        <li>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);"
                                       onclick="_gogame_Ww('webgame03602','http://s3.qiuqiu.2010.sina.com.cn/login.aspx');return false;">[称霸足坛]
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
    </ul>

    <ul class="server">

        <li>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);"
                                       onclick="_gogame_Ww('webgame036','http://s1.qiuqiu.2010.sina.com.cn/login.aspx');return false;">[新浪一服]
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
    </ul>
    <ul class="server_1">
        <li>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);"
                                       onclick="_gogame_Ww('webgame03601','http://s2.qiuqiu.2010.sina.com.cn/login.aspx');return false;">[绿茵天下]
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
    </ul>
    <div id="loginbtu_Ww"><a href="javascript:void(0);" onclick="_wan_sina_login();"></a></div>
    <!-- 登陆后 -->
    <div id="welcomeMessage_Ww" style="display:none;margin-top:10px"></div>

    <div style="margin-top:10px;line-height:20px">热血球球玩家交流群：89656147</div>

</div>
<div class="banner">
    <div class="banner_pic">
        <script type="text/javascript" language="javascript">
            var photos31 = [

                    ["http://i3.sinaimg.cn/2010/qiuqiu/idx/2011/0427/U90P939T151D1F4600DT20110427174809.jpg","《热血球球》五一节大餐—强化成功率提升50% 充值送百搭","http://2010.sina.com.cn/153/2011/0427/133.html"],["http://i1.sinaimg.cn/2010/qiuqiu/idx/2011/0331/U90P939T151D1F4601DT20110331183648.jpg","新浪《热血球球》愚人节不“愚”人 3天满100送50","http://2010.sina.com.cn/153/2011/0331/128.html"],["http://i3.sinaimg.cn/2010/qiuqiu/idx/2011/0224/U90P939T151D1F4599DT20110224135742.jpg","抽取指定卡 新浪《热血球球》好礼任你拿","http://2010.sina.com.cn/153/2011/0224/117.html"],["http://i2.sinaimg.cn/2010/qiuqiu/idx/2011/0217/U90P939T151D1F4598DT20110217102520.jpg","新浪《热血球球》欢度元宵节 充值100送50","http://2010.sina.com.cn/153/2011/0217/112.html"]

                    ];</script>

        <div id="focusViwer31"></div>
        <script type="text/javascript">
            var numStr = "";
            for (var i = 1; i < photos31.length + 1; i++) {
                numStr += ("<li><span class='numVal'>" + i + "</span></li>");
            }
            var html = '<div id="TopAD" class="slideBanner"><ul class="img"></ul><div class="panel"></div><ul class="num">' + numStr + '</ul></div>';
            $("#focusViwer31").append(html);
            imgPlayerTopAndMidIndex("TopAD", photos31);
            var interval31 = setInterval('showNumImg("TopAD")', 6000);
            $("#focusViwer31>a").remove();
            $("#TopAD").show();
            $("#TopAD").mouseenter(function() {
                clearInterval(interval31);
            });
            $("#TopAD").mouseleave(function() {
                interval31 = setInterval('showNumImg("TopAD")', 6000);
            });
        </script>
    </div>
</div>
<div class="clear"></div>

<div class="news">
    <div class="news_s">
        <ul>
            <li><span class="date">2011-09-16</span><a href="/153/2011/0916/159.html"
                                                       class="new_title">新浪《热血球球》周末好礼大赠送<img
                    src="http://i2.sinaimg.cn/ty/rexueqiuqiu/images/hot_icon.jpg" width="28" height="11"/></a></li>
            <li><span class="date">2011-09-01</span><a href="/153/2011/0901/158.html" class="new_title">新浪热血球球中秋大餐前的开胃小菜—梦工厂返点<img
                    src="http://i2.sinaimg.cn/ty/rexueqiuqiu/images/hot_icon.jpg" width="28" height="11"/></a></li>
            <li><span class="date">2011-08-29</span><a href="/153/2011/0829/157.html" class="new_title">最新改版新浪《热血球球》每日任务<img
                    src="http://i2.sinaimg.cn/ty/rexueqiuqiu/images/hot_icon.jpg" width="28" height="11"/></a></li>
            <li><span class="date">2011-08-18</span><a href="/153/2011/0818/156.html" class="new_title">新浪热血球球“顶级经理人”
                第一期（世界名帅）<img src="http://i2.sinaimg.cn/ty/rexueqiuqiu/images/hot_icon.jpg" width="28" height="11"/></a>
            </li>
            <li><span class="date">2011-08-18</span><a href="/153/2011/0818/155.html" class="new_title">新浪《热血球球》充值送百搭
                商城七天半价<img src="http://i2.sinaimg.cn/ty/rexueqiuqiu/images/hot_icon.jpg" width="28" height="11"/></a>
            </li>

        </ul>
    </div>
    <div class="news_s" style="margin-left:10px;">
        <ul>
            <li><span class="date">12-15</span><a href="/153/2010/1215/78.html" class="new_title">热血球球代币说明<img
                    src="http://i2.sinaimg.cn/ty/rexueqiuqiu/images/hot_icon.jpg" width="28" height="11"/></a></li>
            <li><span class="date">07-09</span><a href="/153/2010/0709/28.html" class="new_title">《热血球球》巧用道具事半功倍
                商城购物指南<img src="http://i2.sinaimg.cn/ty/rexueqiuqiu/images/hot_icon.jpg" width="28" height="11"/></a>
            </li>
            <li><span class="date">06-24</span><a href="/153/2010/0624/3.html" class="new_title">《热血球球》重视培养
                打造自己的个性团队<img src="http://i2.sinaimg.cn/ty/rexueqiuqiu/images/hot_icon.jpg" width="28" height="11"/></a>
            </li>
            <li><span class="date">06-23</span><a href="/153/2010/0623/2.html" class="new_title">《热血球球》如何迅速组建你的银河战队！<img
                    src="http://i2.sinaimg.cn/ty/rexueqiuqiu/images/hot_icon.jpg" width="28" height="11"/></a></li>
            <li><span class="date">06-23</span><a href="/153/2010/0623/1.html" class="new_title">《热血球球》华丽转身 球员技能心得篇<img
                    src="http://i2.sinaimg.cn/ty/rexueqiuqiu/images/hot_icon.jpg" width="28" height="11"/></a></li>

        </ul>
    </div>
</div>
<div class="game_pic"><A class=thickbox
                         href="http://i1.sinaimg.cn/gm/qiuqiu/img/1l.jpg"><IMG
        src="http://i1.sinaimg.cn/gm/qiuqiu/img/1.jpg" width=158 height=85></A> <A class=thickbox
                                                                                   href="http://i2.sinaimg.cn/gm/qiuqiu/img/2l.jpg"><IMG
        src="http://i2.sinaimg.cn/gm/qiuqiu/img/2.jpg" width=158 height=85></A> <A class=thickbox
                                                                                   href="http://i3.sinaimg.cn/gm/qiuqiu/img/3l.jpg"><IMG
        src="http://i3.sinaimg.cn/gm/qiuqiu/img/3.jpg" width=158 height=85></A> <A class=thickbox
                                                                                   href="http://i0.sinaimg.cn/gm/qiuqiu/img/4l.jpg"><IMG
        src="http://i0.sinaimg.cn/gm/qiuqiu/img/4.jpg" width=158 height=85></A> <A class=thickbox
                                                                                   href="http://i1.sinaimg.cn/gm/qiuqiu/img/5l.jpg"><IMG
        src="http://i1.sinaimg.cn/gm/qiuqiu/img/5.jpg" width=158 height=85></A></div>
<!--底部信息:begin-->
<div class="copy_right">
    <a href="http://corp.sina.com.cn/chn/">新浪简介</a> |
    <a href="http://corp.sina.com.cn/eng/">About Sina</a> |
    <a href="http://emarketing.sina.com.cn/">广告服务</a> |
    <a href="http://www.sina.com.cn/contactus.html">联系我们</a> |
    <a href="http://corp.sina.com.cn/chn/sina_job.html">招聘信息</a> |
    <a href="http://www.sina.com.cn/intro/lawfirm.shtml">网站律师</a> |
    <a href="http://english.sina.com">SINA English</a> |
    <a href="http://members.sina.com.cn/apply/">会员注册</a> |
    <a href="http://tech.2010.sina.com.cn/focus/sinahelp.shtml">产品答疑</a>

    <br/>
    <br/>
    <img src="http://i1.sinaimg.cn/ty/rexueqiuqiu/images/the9_logo.jpg" width="126" height="34"/><a
        href="http://games.sina.com.cn/guest.html">意见反馈留言版</a>　客服热线：4006900000
</div>
<!--底部信息:end-->
<div style="line-height:20px;text-align:center;">
    <p>沪新出科数[2010]191号 &nbsp;&nbsp; 文网文[2008]05 &nbsp;&nbsp; &nbsp;&nbsp; 体育休闲类游戏 适龄人群：全年龄段

    <p>抵制不良游戏, 拒绝盗版游戏,注意自我保护, 谨防受骗上当, 适度游戏益脑, 沉迷游戏伤身, 合理安排时间, 享受健康生活。

    <p>

</div>

</div>
</div>
</div>
<!-- SSO_UPDATECOOKIE_START -->
<script type="text/javascript">var sinaSSOManager = sinaSSOManager || {};
sinaSSOManager.q = function(b) {
    if (typeof b != "object") {
        return""
    }
    var a = new Array();
    for (key in b) {
        a.push(key + "=" + encodeURIComponent(b[key]))
    }
    return a.join("&")
};
sinaSSOManager.es = function(f, d, e) {
    var c = document.getElementsByTagName("head")[0];
    var a = document.getElementById(f);
    if (a) {
        c.removeChild(a)
    }
    var b = document.createElement("script");
    if (e) {
        b.charset = e
    } else {
        b.charset = "gb2312"
    }
    b.id = f;
    b.type = "text/javascript";
    d += (/\?/.test(d) ? "&" : "?") + "_=" + (new Date()).getTime();
    b.src = d;
    c.appendChild(b)
};
sinaSSOManager.doCrossDomainCallBack = function(a) {
    sinaSSOManager.crossDomainCounter++;
    document.getElementsByTagName("head")[0].removeChild(document.getElementById(a.scriptId))
};
sinaSSOManager.crossDomainCallBack = function(a) {
    if (!a || a.retcode != 0) {
        return false
    }
    var d = a.arrURL;
    var b,f;
    var e = {callback:"sinaSSOManager.doCrossDomainCallBack"};
    sinaSSOManager.crossDomainCounter = 0;
    if (d.length == 0) {
        return true
    }
    for (var c = 0; c < d.length; c++) {
        b = d[c];
        f = "ssoscript" + c;
        e.scriptId = f;
        b = b + (/\?/.test(b) ? "&" : "?") + sinaSSOManager.q(e);
        sinaSSOManager.es(f, b)
    }
};
sinaSSOManager.updateCookieCallBack = function(c) {
    var d = "ssoCrossDomainScriptId";
    var a = "http://login.sina.com.cn/sso/crossdomain.php";
    if (c.retcode == 0) {
        var e = {scriptId:d,callback:"sinaSSOManager.crossDomainCallBack",action:"login",domain:"sina.com.cn"};
        var b = a + "?" + sinaSSOManager.q(e);
        sinaSSOManager.es(d, b)
    } else {
    }
};
sinaSSOManager.updateCookie = function() {
    var g = 1800;
    var p = 7200;
    var b = "ssoLoginScript";
    var h = 3600 * 24;
    var i = "sina.com.cn";
    var m = 1800;
    var l = "http://login.sina.com.cn/sso/updatetgt.php";
    var n = null;
    var f = function(e) {
        var r = null;
        var q = null;
        switch (e) {case"sina.com.cn":q = sinaSSOManager.getSinaCookie();if (q) {
            r = q.et
        }break;case"sina.cn":q = sinaSSOManager.getSinaCookie();if (q) {
            r = q.et
        }break;case"51uc.com":q = sinaSSOManager.getSinaCookie();if (q) {
            r = q.et
        }break}
        return r
    };
    var j = function() {
        try {
            return f(i)
        } catch(e) {
            return null
        }
    };
    try {
        if (g > 5) {
            if (n != null) {
                clearTimeout(n)
            }
            n = setTimeout("sinaSSOManager.updateCookie()", g * 1000)
        }
        var d = j();
        var c = (new Date()).getTime() / 1000;
        var o = {};
        if (d == null) {
            o = {retcode:6102}
        } else {
            if (d < c) {
                o = {retcode:6203}
            } else {
                if (d - h + m > c) {
                    o = {retcode:6110}
                } else {
                    if (d - c > p) {
                        o = {retcode:6111}
                    }
                }
            }
        }
        if (o.retcode !== undefined) {
            return false
        }
        var a = l + "?callback=sinaSSOManager.updateCookieCallBack";
        sinaSSOManager.es(b, a)
    } catch(k) {
    }
    return true
};
sinaSSOManager.updateCookie();</script>
<!-- SSO_UPDATECOOKIE_END -->

<!-- Start  Wrating  -->
<script language="javascript">
    var wrUrl = "//sina.wrating.com/";
    var wrDomain = "sina.com.cn";
    var wratingDefaultAcc = "860010-0323010000";
    var wratingAccArray = {"torch.2008.sina.com.cn":"860010-0308070000","video.sina.com.cn":"860010-0309010000","cctv.sina.com.cn":"860010-0309020000","chat.sina.com.cn":"860010-0311010000","ent.sina.com.cn":"860010-0312010000","tech.sina.com.cn":"860010-0313010000","mobile.sina.com.cn":"860010-0313020000","house.sina.com.cn":"860010-0315010000","bj.house.sina.com.cn":"860010-0315020000","auto.sina.com.cn":"860010-0316010000","eladies.sina.com.cn":"860010-0317010000","bj.sina.com.cn":"860010-0317020000","woman.sina.com.cn":"860010-0317010000","women.sina.com.cn":"860010-0317010000","lady.sina.com.cn":"860010-0317010000","man.eladies.sina.com.cn":"860010-0317030000","games.sina.com.cn":"860010-0318010000","game.sina.com.cn":"860010-0318010000","edu.sina.com.cn":"860010-0307010000","baby.sina.com.cn":"860010-0320010000","kid.sina.com.cn":"860010-0320020000","astro.sina.com.cn":"860010-0321020000","news.sina.com.cn":"860010-0310010000","weather.news.sina.com.cn":"860010-0310020000","mil.news.sina.com.cn":"860010-0310030000","www.sina.com.cn":"860010-0322010000","home.sina.com.cn":"860010-0322010000","sports.sina.com.cn":"860010-0308010000","shidefc.sina.com.cn":"860010-0308020000","weiqi.sina.com.cn":"860010-0308030000","f1.sina.com.cn":"860010-0308040000","golf.sina.com.cn":"860010-0308050000","2002.sina.com.cn":"860010-0308060000","2004.sina.com.cn":"860010-0308060000","2006.sina.com.cn":"860010-0308060000","2008.sina.com.cn":"860010-0308070000","yayun2002.sina.com.cn":"860010-0308060000","yayun2006.sina.com.cn":"860010-0308060000","inter.sina.com.cn":"860010-0308080000","chelsea.sina.com.cn":"860010-0308090000","book.sina.com.cn":"860010-0319010000","cul.book.sina.com.cn":"860010-0319020000","comic.book.sina.com.cn":"860010-0319030000","finance.sina.com.cn":"860010-0314010000","money.sina.com.cn":"860010-0314020000","yue.sina.com.cn":"860010-0324010000","www.sina.com":"860010-0322010000"};
    function vjTrack() {
        var U = 1800;
        var T = false;
        var S = false;
        var R = "";
        var Q = "0";
        var P = "";
        var N;
        var L;
        var K;
        var J;
        var I;
        var H = "expires=Fri, 1 Jan 2038 00:00:00 GMT;";
        var G = 0;
        if (document.location.protocol == "file:") {
            return
        }
        T = navigator.cookieEnabled ? "1" : "0";
        S = navigator.javaEnabled() ? "1" : "0";
        var F = "0";
        var E;
        var C = -1;
        var D = document.cookie;
        if (T == "1") {
            C = D.indexOf("vjuids=");
            if (C < 0) {
                E = vjVisitorID();
                document.cookie = "vjuids=" + escape(E) + ";" + H + ";domain=" + wrDomain + ";path=/;";
                if (document.cookie.indexOf("vjuids=") < 0) {
                    T = "0"
                } else {
                    Q = "1"
                }
            } else {
                E = vjGetCookie("vjuids")
            }
        }
        L = document.referrer;
        if (!L || L == "") {
            L = ""
        }
        R = vjFlash();
        if (self.screen) {
            N = screen.width + "x" + screen.height + "x" + screen.colorDepth
        } else {
            if (self.java) {
                var M = java.awt.Toolkit.getDefaultToolkit();
                var O = M.getScreenSize();
                N = O.width + "x" + O.height + "x0"
            }
        }
        if (navigator.language) {
            K = navigator.language.toLowerCase()
        } else {
            if (navigator.browserLanguage) {
                K = navigator.browserLanguage.toLowerCase()
            } else {
                K = "-"
            }
        }
        I = "";
        var B;
        var X;
        X = new Date();
        J = X.getTimezoneOffset() / -60;
        J = X.getTimezoneOffset() / -60;
        B = "&s=" + N + "&l=" + K + "&z=" + J + "&j=" + S + "&f=" + R;
        if (T == "1") {
            C = document.cookie.indexOf("vjlast=");
            if (C < 0) {
                G = 0
            } else {
                G = parseInt(vjGetCookie("vjlast"))
            }
        }
        if ((X.getTime() / 1000) - G > U) {
            F = "1";
            document.cookie = "vjlast=" + Math.round(X.getTime() / 1000) + ";" + H + ";domain=" + wrDomain + ";path=/;"
        }
        if (L != "") {
            B = B + "&r=" + escape(L)
        }
        if (F != "0") {
            B = B + "&n=" + G
        }
        if (Q != "0") {
            B = B + "&u=" + Q
        }
        var V;
        var A = vjGetAcc();
        var W = vjGetDomain();
        V = wrUrl + "a.gif?a=" + X.getTime().toString(16) + "&t=" + escape(I) + "&i=" + escape(E) + "&b=" + escape(document.location) + "&c=" + A + B + "&ck=" + W;
        document.write('<img src="' + V + '" width="1" height="1" style="visibility:hidden;position:absolute;left:0px;top:0px;z-index:-1" />')
    }
    function vjGetAcc() {
        var B = document.location.toString().toLowerCase();
        var C = (B.split("/"))[2];
        var A = wratingAccArray[C];
        if (typeof (A) == "undefined") {
            A = wratingDefaultAcc
        }
        return A
    }
    function vjFlash() {
        var _wr_f = "-",_wr_n = navigator;
        if (_wr_n.plugins && _wr_n.plugins.length) {
            for (var ii = 0; ii < _wr_n.plugins.length; ii++) {
                if (_wr_n.plugins[ii].name.indexOf("Shockwave Flash") != -1) {
                    _wr_f = _wr_n.plugins[ii].description.split("Shockwave Flash ")[1];
                    break
                }
            }
        } else {
            if (window.ActiveXObject) {
                for (var ii = 10; ii >= 2; ii--) {
                    try {
                        var fl = eval("new ActiveXObject('ShockwaveFlash.ShockwaveFlash." + ii + "');");
                        if (fl) {
                            _wr_f = ii + ".0";
                            break
                        }
                    } catch(e) {
                    }
                }
            }
        }
        return _wr_f
    }
    function vjHash(B) {
        if (!B || B == "") {
            return 0
        }
        var D = 0;
        for (var C = B.length - 1; C >= 0; C--) {
            var A = parseInt(B.charCodeAt(C));
            D = (D << 5) + D + A
        }
        return D
    }
    function vjVisitorID() {
        var B = vjHash(document.location + document.cookie + document.referrer).toString(16);
        var A;
        A = new Date();
        return B + "." + A.getTime().toString(16) + "." + Math.random().toString(16)
    }
    function vjGetCookieVal(B) {
        var A = document.cookie.indexOf(";", B);
        if (A == -1) {
            A = document.cookie.length
        }
        return unescape(document.cookie.substring(B, A))
    }
    function vjGetCookie(C) {
        var B = C + "=";
        var F = B.length;
        var A = document.cookie.length;
        var E = 0;
        while (E < A) {
            var D = E + F;
            if (document.cookie.substring(E, D) == B) {
                return vjGetCookieVal(D)
            }
            E = document.cookie.indexOf(" ", E) + 1;
            if (E == 0) {
                break
            }
        }
        return null
    }
    function vjGetDomain() {
        var A = 0;
        try {
            if (window.self.parent != self) {
                var D = /sina.com/i;
                var C = document.location.toString().toLowerCase();
                var B = parent.location.toString().toLowerCase();
                if (D.test(C) && D.test(B)) {
                    A = 1
                }
            }
        } catch(e) {
            A = 1
        }
        return A
    }
    vjTrack();
</script>

<!-- End Wrating-->

<!-- START Nielsen//NetRatings SiteCensus V5.3 -->
<!-- COPYRIGHT 2006 Nielsen//NetRatings -->
<script type="text/javascript">
    var _rsCI = "cn-sina2006";
    var _rsCG = "0";
    var _rsDN = "//secure-cn.imrworldwide.com/";
    var _rsCC = 0;
    var _rsSE = 1;
    var _rsSM = 0.01;
    var _rsSS = 1500;
</script>
<script type="text/javascript" src="//secure-cn.imrworldwide.com/v53.js"></script>
<noscript>
    <img src="//secure-cn.imrworldwide.com/cgi-bin/m?ci=cn-sina2006&amp;cg=0" alt=""/>
</noscript>
<!-- END Nielsen//NetRatings SiteCensus V5.3 -->
</body>
</html>

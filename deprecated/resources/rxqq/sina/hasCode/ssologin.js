function SSOController() {
    var appendParamSymbol = function(a) {
        return(/\?/.test(a) ? "&" : "?")
    };
    var appendParams = function(a) {
        if (typeof a != "object")return"";
        var b = new Array();
        for (var c in a) {
            if (typeof a[c] == "function")continue;
            b.push(c + "=" + encodeURI(a[c]))
        }
        return b.join("&")
    };
    var insertParams = function(a, b) {
        for (var c in b) {
            a[c] = b[c]
        }
        return a
    };
    var encodeURI = function(a) {
        return encodeURIComponent(a)
    };
    var k = this;
    var l = null;
    var m = 1800;
    var n = 3600 * 24;
    var o = null;
    var p = null;
    var q = 3;
    var s = null;
    var t = 'http://login.sina.com.cn/sso/crossdomain.php';
    var u = 'http://login.sina.com.cn/sso/login.php';
    var v = 'http://login.sina.com.cn/sso/logout.php';
    var w = 'http://login.sina.com.cn/sso/updatetgt.php';
    var y = 'http://login.sina.com.cn/sso/prelogin.php';
    var z = "http://login.sina.com.cn/bindmail/checkmailuser.php";
    var A = null;
    var B = "";
    var C = {};
    var D = 'check_alt_login';
    var E = null;
    var F = null;
    var G = null;
    var H = null;
    var I = 1;
    var J = 2;
    var K = {};
    var generateNonce = function(a) {
        var x = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        var b = "";
        for (var i = 0; i < a; i++) {
            b += x.charAt(Math.ceil(Math.random() * 1000000) % x.length)
        }
        return b
    };
    var M = function(a) {
        s = a;
        var b = {'entry':k.getEntry(),'service':k.service,'encoding':'UTF-8','gateway':1,'returntype':'TEXT','from':k.from};
        if (k.domain == 'sina.com.cn') {
            b['callback'] = k.name + '.autoLoginCallBack2';
            b['useticket'] = 0
        } else {
            b['callback'] = k.name + '.autoLoginCallBack3';
            b['useticket'] = 1
        }
        var c = Z(u, b);
        Q(k.scriptId, c, "gb2312");
        return true
    };
    var N = function(a) {
        var b = null;
        var c = null;
        switch (a) {case"sina.com.cn":c = k.getSinaCookie();if (c)b = c['et'];break;case"51uc.com":c = k.getSinaCookie();if (c)b = c['et'];break}
        return b
    };
    var O = function(a, b, c) {
        if (a.addEventListener) {
            a.addEventListener(b, c, false)
        } else if (a.attachEvent) {
            a.attachEvent("on" + b, c)
        } else {
            a["on" + b] = c
        }
    };
    var Q = function(a, b, c) {
        var d = document.getElementsByTagName('head')[0];
        var e = document.getElementById(a);
        if (e) {
            e.parentNode.removeChild(e)
        }
        var f = document.createElement('script');
        if (c) {
            f.charset = c
        } else {
            f.charset = 'gb2312'
        }
        f.id = a;
        f.type = 'text/javascript';
        f.src = Z(b, {"client":k.getClientType(),"_":(new Date()).getTime()});
        d.appendChild(f)
    };
    var generatePwdParams = function(username, pwd, savestate) {
        var d = {"entry":k.getEntry(),"gateway":1,"from":k.from,"savestate":savestate,"useticket":k.useTicket ? 1 : 0};
        d = insertParams(d, k.loginExtraFlag);
        d = insertParams(d, k.loginExtraQuery);
        d["su"] = sinaSSOEncoder.base64.encode(encodeURI(username));
        if (k.service)d["service"] = k.service;
        if ((k.loginType & J) && k.servertime && sinaSSOEncoder && sinaSSOEncoder.hex_sha1) {
            d["servertime"] = k.servertime;
            d["nonce"] = k.nonce;
            d["pwencode"] = "wsse";
            pwd = sinaSSOEncoder.hex_sha1("" + sinaSSOEncoder.hex_sha1(sinaSSOEncoder.hex_sha1(pwd)) + k.servertime + k.nonce)
        }
        d["sp"] = pwd;
        return d
    };
    var P = function(c) {
        var d = false;
        this.start = function(a, b) {
            if (c)d = setTimeout(b, a)
        };
        this.clear = function(a) {
            if (c) {
                clearTimeout(d);
                d = false
            }
        };
        this.isset = function() {
            return d !== false
        }
    };
    var S = function(a, b, c) {
        if (k.appLoginURL[k.domain]) {
            k.useTicket = 1;
            k.service = k.appDomainService[k.domain] || k.service
        }
        var d = 0;
        if (k.domain)d = 2;
        if (!k.appLoginURL[k.domain])d = 3;
        if (k.cdult !== false) {
            d = k.cdult
        }
        if (d == 3) {
            q = k.crossDomainTime;
            delete k.appLoginURL[k.domain]
        }
        var e = generatePwdParams(a, b, c);
        e = insertParams(e, {"encoding":"UTF-8","callback":k.name + ".loginCallBack","cdult":d,"domain":k.domain,"useticket":k.appLoginURL[k.domain] ? 1 : 0,"returntype":"TEXT"});
        var f = (k.loginType & I) ? u.replace(/^http:/, "https:") : u;
        f = Z(f, e);
        Q(k.scriptId, f, "gb2312")
    };
    var U = function(a, b) {
        if (b == null)b = "javascript:false;";
        var c = k.$(a);
        if (c) {
            c.parentNode.removeChild(c)
        }
        c = document.createElement('iframe');
        c.height = 0;
        c.width = 0;
        c.style.display = 'none';
        c.name = a;
        c.id = a;
        c.src = b;
        X(document.body, c);
        window.frames[a].name = a;
        return c
    };
    var W = function(e, f) {
        if (f == null)f = 'none';
        var g = k.$(e);
        if (g) {
            g.parentNode.removeChild(g)
        }
        g = document.createElement('form');
        g.height = 0;
        g.width = 0;
        g.style.display = f;
        g.name = e;
        g.id = e;
        X(document.body, g);
        document.forms[e].name = e;
        g.addInput = function(a, b, c) {
            if (c == null)c = 'text';
            var d = this.getElementsByTagName('input')[a];
            if (d) {
                this.removeChild(d)
            }
            d = document.createElement('input');
            this.appendChild(d);
            d.id = a;
            d.name = a;
            d.type = c;
            d.value = b
        };
        return g
    };
    var T = function(a, b, c) {
        U(k.loginFrameName);
        var d = W(k.loginFormId);
        var f = generatePwdParams(a, b, c);
        f["encoding"] = "UTF-8";
        if (k.crossDomain == false) {
            f["crossdomain"] = 0
        }
        if (k.feedBackUrl) {
            f["url"] = Z(k.feedBackUrl, {"framelogin":1,"callback":"parent." + k.name + ".feedBackUrlCallBack"});
            f["returntype"] = "META"
        } else {
            f["callback"] = "parent." + k.name + ".loginCallBack";
            f["returntype"] = "IFRAME";
            f["setdomain"] = k.setDomain ? 1 : 0
        }
        for (var g in k.loginExtraQuery) {
            if (typeof k.loginExtraQuery[g] == "function")continue;
            f[g] = k.loginExtraQuery[g]
        }
        for (var h in f) {
            d.addInput(h, f[h])
        }
        var i = (k.loginType & I) ? u.replace(/^http:/, "https:") : u;
        i = Z(i, insertParams({"client":k.getClientType()}, k.loginExtraFlag));
        d.method = 'post';
        d.action = i;
        d.target = k.loginFrameName;
        var j = true;
        try {
            d.submit()
        } catch(e) {
            V(k.loginFrameName);
            j = false
        }
        d.parentNode.removeChild(d);
        return j
    };
    var V = function(a) {
        var b = k.$(a);
        if (b) {
            b.parentNode.removeChild(b)
        }
    };
    var X = function(a, b) {
        a.appendChild(b)
    };
    var Y = function(a) {
        var b = eval('/' + a + '=([^;]+)/').exec(document.cookie);
        return b == null ? null : b[1]
    };
    var Z = function(a, b) {
        return a + appendParamSymbol(a) + appendParams(b)
    };
    this.https = 1;
    this.wsse = 2;
    this.name = 'sinaSSOController';
    this.loginFormId = 'ssoLoginForm';
    this.scriptId = 'ssoLoginScript';
    this.ssoCrossDomainScriptId = 'ssoCrossDomainScriptId';
    this.loginFrameName = 'ssoLoginFrame';
    this.appLoginURL = {'51uc.com':'http://passport.51uc.com/sso/login.php','weibo.com':'http://weibo.com/sso/login.php'};
    this.appDomainService = {'51uc.com':'51uc','weibo.com':'miniblog'};
    this.loginExtraQuery = {};
    this.setDomain = false;
    this.feedBackUrl = '';
    this.service = 'sso';
    this.domain = 'sina.com.cn';
    this.from = '';
    this.pageCharset = 'GB2312';
    this.useTicket = false;
    this.isCheckLoginState = false;
    this.isUpdateCookieOnLoad = true;
    this.useIframe = true;
    this.noActiveTime = 7200;
    this.autoUpdateCookieTime = 1800;
    this.loginType = J;
    this.timeoutEnable = false;
    this.crossDomain = true;
    this.scriptLoginHttps = false;
    this.allowAutoFoundServerTime = false;
    this.allowAutoFoundServerTimeError = true;
    this.calcServerTimeInterval = 2000;
    this.servertime = null;
    this.nonce = null;
    this.loginExtraFlag = {};
    this.cdult = false;
    this.crossDomainTime = 5;
    this.getVersion = function() {
        return"ssologin.js(v1.3.16) 2011-09-15"
    };
    this.getEntry = function() {
        return k["entry"]
    };
    this.getClientType = function() {
        return k.getVersion().split(' ')[0]
    };
    this.init = function() {
        k.setLoginType(k.loginType);
        var a = window["sinaSSOConfig"];
        if (typeof a != "object") {
            a = {}
        }
        var b;
        for (b in a) {
            k[b] = a[b]
        }
        if (!k["entry"])k["entry"] = k["service"];
        if (k.isUpdateCookieOnLoad) {
            setTimeout(k.name + ".updateCookie()", 10000)
        }
        if (k.isCheckLoginState) {
            O(window, "load", function() {
                k.checkLoginState()
            })
        }
        if (k.allowAutoFoundServerTime && ssoLoginServerTime)k.setServerTime(ssoLoginServerTime);
        k.customInit()
    };
    this.customInit = function() {
    };
    this.customUpdateCookieCallBack = function(a) {
    };
    this.customLogoutCallBack = function(a) {
        k.customLoginCallBack({"result":false})
    };
    this.customLoginCallBack = function(a) {
    };
    this.login = function(b, c, d) {
        if (!F) {
            F = new P(k.timeoutEnable)
        } else {
            F.clear()
        }
        F.start(5000, function() {
            F.clear();
            k.customLoginCallBack({"result":false,"reason":unescape("%u767B%u5F55%u8D85%u65F6%uFF0C%u8BF7%u91CD%u8BD5")})
        });
        d = d == undefined ? 0 : d;
        K['savestate'] = d;
        G = function() {
            if (k.useIframe && (k.setDomain || k.feedBackUrl)) {
                if (k.setDomain) {
                    document.domain = k.domain;
                    if (!k.feedBackUrl && k.domain != "sina.com.cn")k.feedBackUrl = Z(k.appLoginURL[k.domain], {"domain":1})
                }
                B = "post";
                var a = T(b, c, d);
                if (!a) {
                    B = "get";
                    if (k.scriptLoginHttps) {
                        k.setLoginType(k.loginType | I)
                    } else {
                        k.setLoginType(k.loginType | J)
                    }
                    S(b, c, d)
                }
            } else {
                B = "get";
                S(b, c, d)
            }
            k.nonce = null
        };
        H = function() {
            if (k.loginType & J) {
                if (k.servertime) {
                    if (!k.nonce)k.nonce = generateNonce(6);
                    G();
                    return true
                }
                k.getServerTime(b, G)
            } else {
                G()
            }
        };
        H();
        return true
    };
    this.getServerTime = function(b, c) {
        if (k.servertime) {
            if (typeof c == "function")c({"retcode":0,"servertime":k.servertime});
            return true
        }
        var d = location.protocol == "https:" ? y.replace(/^http:/, "https:") : y;
        b = sinaSSOEncoder.base64.encode(encodeURI(b));
        d = Z(d, {"entry":k.entry,"callback":k.name + ".preloginCallBack","su":b});
        k.preloginCallBack = function(a) {
            if (a && a.retcode == 0) {
                k.setServerTime(a.servertime);
                k.nonce = a.nonce
            }
            if (typeof c == "function")c(a)
        };
        Q(k.scriptId, d)
    };
    this.logout = function() {
        try {
            var a = {'entry':k.getEntry(),'callback':k.name + '.ssoLogoutCallBack'};
            var b = Z(v, a);
            Q(k.scriptId, b)
        } catch(e) {
        }
        return true
    };
    this.ssoLogoutCallBack = function(a) {
        if (a.arrURL) {
            k.setCrossDomainUrlList(a)
        }
        k.crossDomainAction('logout', function() {
            k.customLogoutCallBack({'result':true})
        })
    };
    this.updateCookie = function() {
        try {
            if (k.autoUpdateCookieTime > 5) {
                if (l != null) {
                    clearTimeout(l)
                }
                l = setTimeout(k.name + ".updateCookie()", k.autoUpdateCookieTime * 1000)
            }
            var a = k.getCookieExpireTime();
            var b = (new Date()).getTime() / 1000;
            var c = {};
            if (a == null) {
                c = {"retcode":6102}
            } else if (a < b) {
                c = {"retcode":6203}
            } else if (a - n + m > b) {
                c = {"retcode":6110}
            } else if (a - b > k.noActiveTime) {
                c = {"retcode":6111}
            }
            if (c.retcode !== undefined) {
                k.customUpdateCookieCallBack(c);
                return false
            }
            var d = Z(w, {"entry":k.getEntry(),"callback":k.name + ".updateCookieCallBack"});
            Q(k.scriptId, d)
        } catch(e) {
        }
        return true
    };
    this.setCrossDomainUrlList = function(a) {
        A = a
    };
    this.checkAltLoginNameCallback = function(a) {
        var b = {'retcode':0,'detail':'','data':''};
        if (a.ret == "y") {
            b['retcode'] = 1;
            b['detail'] = '\u4e3a\u4e86\u60a8\u7684\u8d26\u53f7\u5b89\u5168\uff0c\u8bf7<a href="http://login.sina.com.cn/bindmail/signin.php?username=' + C['username'] + '">\u7ed1\u5b9a\u90ae\u7bb1</a>';
            b['data'] = C['username']
        } else if (a.ret == "n" && a.mail) {
            if (a.errcode == 'not_verify') {
                b['retcode'] = 2;
                b['detail'] = '\u60a8\u7684\u90ae\u7bb1: ' + a.mail + ' \u672a\u9a8c\u8bc1\uff0c\u70b9\u6b64<a href="http://login.sina.com.cn/bindmail/bindmail1.php?entry=sso&user=' + a.mail + '">\u91cd\u53d1\u9a8c\u8bc1\u90ae\u4ef6</a>';
                b['data'] = a.mail
            } else {
                b['retcode'] = 3;
                b['detail'] = '\u7528\u60a8\u7684\u90ae\u7bb1' + a.mail + '\u4e5f\u53ef\u4ee5\u767b\u5f55';
                b['data'] = a.mail
            }
        } else {
        }
        C['callback'](b)
    };
    this.checkAltLoginName = function(a, b) {
        if (a == "") {
            return true
        }
        var r = /^[0-9]{1,9}$/;
        if (r.exec(a)) {
            return true
        }
        C = {'username':a,'callback':b};
        var c = (k.loginType & I) ? z.replace(/^http:/, "https:") : z;
        c = Z(c, {'name':a,'type':'json','callback':'sinaSSOController.checkAltLoginNameCallback'});
        Q(D, c)
    };
    this.callFeedBackUrl = function(a) {
        try {
            var b = {'callback':k.name + ".feedBackUrlCallBack"};
            if (a['ticket']) {
                b['ticket'] = a['ticket']
            }
            if (a['retcode'] !== undefined) {
                b['retcode'] = a['retcode']
            }
            var c = Z(k.feedBackUrl, b);
            Q(k.scriptId, c)
        } catch(e) {
        }
        return true
    };
    this.loginCallBack = function(a) {
        try {
            if (k.timeoutEnable && !F.isset())return;
            F.clear();
            k.loginExtraFlag = {};
            var b = {};
            var c = a["ticket"];
            var d = a["uid"];
            if (d) {
                b['result'] = true;
                b['retcode'] = 0;
                b['userinfo'] = {"uniqueid":a["uid"]};
                if (c)b['ticket'] = c;
                if (k.feedBackUrl) {
                    if (k.crossDomain) {
                        k.crossDomainAction("login", function() {
                            k.callFeedBackUrl(b)
                        })
                    } else {
                        k.callFeedBackUrl(b)
                    }
                } else {
                    if (k.crossDomain) {
                        if (a["crossDomainUrlList"]) {
                            k.setCrossDomainUrlList({"retcode":0,"arrURL":a["crossDomainUrlList"]})
                        }
                        k.crossDomainAction("login", function() {
                            if (c && k.appLoginURL[k.domain]) {
                                k.appLogin(c, k.domain, k.name + ".customLoginCallBack")
                            } else {
                                b["userinfo"] = insertParams(b["userinfo"], k.getSinaCookie());
                                k.customLoginCallBack(b)
                            }
                        })
                    } else {
                        k.customLoginCallBack(b)
                    }
                }
            } else {
                if (H && a['retcode'] == "2092" && k.allowAutoFoundServerTimeError) {
                    k.setServerTime(0);
                    k.loginExtraFlag = insertParams(k.loginExtraFlag, {"wsseretry":"servertime_error"});
                    H();
                    H = null;
                    return false
                }
                b['result'] = false;
                b['errno'] = a['retcode'];
                b['reason'] = a['reason'];
                k.customLoginCallBack(b)
            }
        } catch(e) {
        }
        return true
    };
    this.updateCookieCallBack = function(a) {
        if (a['retcode'] == 0) {
            k.crossDomainAction("update", function() {
                k.customUpdateCookieCallBack(a)
            })
        } else {
            k.customUpdateCookieCallBack(a)
        }
    };
    this.feedBackUrlCallBack = function(a) {
        if (B == "post" && k.timeoutEnable && !F.isset())return;
        F.clear();
        k.customLoginCallBack(a);
        V(k.loginFrameName)
    };
    this.doCrossDomainCallBack = function(a) {
        k.crossDomainCounter++;
        try {
            var b = k.$(a.scriptId);
            b.parentNode.removeChild(b)
        } catch(e) {
        }
        if (k.crossDomainCounter == k.crossDomainCount) {
            clearTimeout(p);
            k.crossDomainResult()
        }
    };
    this.crossDomainCallBack = function(a) {
        var b = k.$(k.ssoCrossDomainScriptId);
        if (b) {
            b.parentNode.removeChild(b)
        }
        if (!a || a.retcode != 0) {
            return false
        }
        var c = a.arrURL;
        var d,scriptId;
        var e = {'callback':k.name + '.doCrossDomainCallBack'};
        k.crossDomainCount = c.length;
        k.crossDomainCounter = 0;
        if (c.length == 0) {
            clearTimeout(p);
            k.crossDomainResult();
            return true
        }
        for (var i = 0; i < c.length; i++) {
            d = c[i];
            scriptId = 'ssoscript' + i;
            e.scriptId = scriptId;
            d = Z(d, e);
            Q(scriptId, d)
        }
    };
    this.crossDomainResult = function() {
        A = null;
        if (typeof o == 'function') {
            o()
        }
    };
    this.crossDomainAction = function(a, b) {
        p = setTimeout(k.name + '.crossDomainResult()', q * 1000);
        if (typeof b == 'function') {
            o = b
        } else {
            o = null
        }
        if (A) {
            k.crossDomainCallBack(A);
            return false
        }
        var c = k.domain;
        if (a == "update") {
            a = "login";
            c = "sina.com.cn"
        }
        var d = {'scriptId':k.ssoCrossDomainScriptId,'callback':k.name + '.crossDomainCallBack','action':a,'domain':c};
        var e = Z(t, d);
        Q(k.ssoCrossDomainScriptId, e)
    };
    this.checkLoginState = function(d) {
        if (d) {
            k.autoLogin(d)
        } else {
            k.autoLogin(function(a) {
                var b = {};
                if (a !== null) {
                    var c = {'displayname':a['nick'],'uniqueid':a['uid'],'userid':a['user']};
                    b["result"] = true;
                    b["userinfo"] = c
                } else {
                    b["result"] = false;
                    b["reason"] = ""
                }
                k.customLoginCallBack(b)
            })
        }
    };
    this.getCookieExpireTime = function() {
        return N(k.domain)
    };
    this.getSinaCookie = function(a) {
        var b = bc(Y("SUP"));
        if (!b && !bc(Y("ALF")))return null;
        if (!b)b = bc(Y("SUR"));
        if (!b)return null;
        var c = be(b);
        if (a && c["et"] && (c["et"] * 1000 < (new Date()).getTime())) {
            return null
        }
        return c
    };
    this.get51UCCookie = function() {
        return k.getSinaCookie()
    };
    this.autoLogin = function(a) {
        if (k.domain == 'sina.com.cn') {
            if (Y('SUP') === null && Y('ALF') !== null) {
                M(a);
                return true
            }
        } else {
            if (Y('SUP') === null && (Y('SSOLoginState') !== null || Y('ALF') !== null)) {
                M(a);
                return true
            }
        }
        a(k.getSinaCookie());
        return true
    };
    this.autoLoginCallBack2 = function(a) {
        try {
            s(k.getSinaCookie())
        } catch(e) {
        }
        return true
    };
    this.appLogin = function(a, b, c) {
        var d = K['savestate'] ? parseInt((new Date()).getTime() / 1000 + K['savestate'] * 86400) : 0;
        var e = Y['ALF'] ? Y['ALF'] : 0;
        var f = {'callback':c,'ticket':a,'ssosavestate':d || e};
        var g = k.appLoginURL[b];
        var h = Z(g, f);
        Q(k.scriptId, h, "gb2312");
        return true
    };
    this.autoLoginCallBack3 = function(a) {
        if (a['retcode'] != 0) {
            k.autoLoginCallBack2(a);
            return false
        }
        k.appLogin(a["ticket"], k.domain, k.name + ".autoLoginCallBack2");
        return true
    };
    this.setLoginType = function(a) {
        var b = location.protocol == "https:" ? k.https : 0;
        if (b)k.crossDomain = false;
        k.loginType = a | b;
        return true
    };
    this.setServerTime = function(a) {
        if (!E) {
            E = new P(true)
        }
        if (a == 0) {
            E.clear();
            k.servertime = a;
            return true
        }
        if (a < 1294935546)return false;
        var b = function() {
            if (k.servertime) {
                k.servertime += k.calcServerTimeInterval / 1000;
                E.start(k.calcServerTimeInterval, b)
            }
        };
        k.servertime = a;
        E.start(k.calcServerTimeInterval, b)
    };
    var bc = function(a) {
        if (a == undefined)return"";
        var b = decodeURIComponent(a);
        return b == "null" ? "" : b
    };
    var be = function(a) {
        var b = a.split("&");
        var c;
        var d = {};
        for (var i = 0; i < b.length; i++) {
            c = b[i].split("=");
            d[c[0]] = bc(c[1])
        }
        return d
    };
    this.$ = function(a) {
        return document.getElementById(a)
    }
}
;
var sinaSSOEncoder = sinaSSOEncoder || {};
(function() {
    var n = 0;
    var o = 8;
    this.hex_sha1 = function(s) {
        return A(p(z(s), s.length * o))
    };
    var p = function(x, f) {
        x[f >> 5] |= 0x80 << (24 - f % 32);
        x[((f + 64 >> 9) << 4) + 15] = f;
        var w = Array(80);
        var a = 1732584193;
        var b = -271733879;
        var c = -1732584194;
        var d = 271733878;
        var e = -1009589776;
        for (var i = 0; i < x.length; i += 16) {
            var g = a;
            var h = b;
            var k = c;
            var l = d;
            var m = e;
            for (var j = 0; j < 80; j++) {
                if (j < 16)w[j] = x[i + j]; else w[j] = v(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);
                var t = u(u(v(a, 5), q(j, b, c, d)), u(u(e, w[j]), r(j)));
                e = d;
                d = c;
                c = v(b, 30);
                b = a;
                a = t
            }
            a = u(a, g);
            b = u(b, h);
            c = u(c, k);
            d = u(d, l);
            e = u(e, m)
        }
        return Array(a, b, c, d, e)
    };
    var q = function(t, b, c, d) {
        if (t < 20)return(b & c) | ((~b) & d);
        if (t < 40)return b ^ c ^ d;
        if (t < 60)return(b & c) | (b & d) | (c & d);
        return b ^ c ^ d
    };
    var r = function(t) {
        return(t < 20) ? 1518500249 : (t < 40) ? 1859775393 : (t < 60) ? -1894007588 : -899497514
    };
    var u = function(x, y) {
        var a = (x & 0xFFFF) + (y & 0xFFFF);
        var b = (x >> 16) + (y >> 16) + (a >> 16);
        return(b << 16) | (a & 0xFFFF)
    };
    var v = function(a, b) {
        return(a << b) | (a >>> (32 - b))
    };
    var z = function(a) {
        var b = Array();
        var c = (1 << o) - 1;
        for (var i = 0; i < a.length * o; i += o)b[i >> 5] |= (a.charCodeAt(i / o) & c) << (24 - i % 32);
        return b
    };
    var A = function(a) {
        var b = n ? "0123456789ABCDEF" : "0123456789abcdef";
        var c = "";
        for (var i = 0; i < a.length * 4; i++) {
            c += b.charAt((a[i >> 2] >> ((3 - i % 4) * 8 + 4)) & 0xF) + b.charAt((a[i >> 2] >> ((3 - i % 4) * 8)) & 0xF)
        }
        return c
    };
    this.base64 = {encode:function(a) {
        a = "" + a;
        if (a == "")return"";
        var b = '';
        var c,chr2,chr3 = '';
        var d,enc2,enc3,enc4 = '';
        var i = 0;
        do{
            c = a.charCodeAt(i++);
            chr2 = a.charCodeAt(i++);
            chr3 = a.charCodeAt(i++);
            d = c >> 2;
            enc2 = ((c & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64
            } else if (isNaN(chr3)) {
                enc4 = 64
            }
            b = b + this._keys.charAt(d) + this._keys.charAt(enc2) + this._keys.charAt(enc3) + this._keys.charAt(enc4);
            c = chr2 = chr3 = '';
            d = enc2 = enc3 = enc4 = ''
        } while (i < a.length);
        return b
    },_keys:'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/='}
}).call(sinaSSOEncoder);
sinaSSOController = new SSOController();
sinaSSOController.init();
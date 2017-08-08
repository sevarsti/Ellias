(function(c) {
    var e = "WebIM/Login.aspx";
    var f = __baseUrl + "login.aspx";
    var b = __baseUrl + "main.aspx";
    var a = "m161.com.cn";
    var d = objects(window.location.search);
    c.extend({openPlatLogin:{flag:0,hasLogin:function() {
        var g = getCookie("webim_sessionid");
        if(!!g) {
            return true;
        }
        return false;
    },isOpenPlatLogin:function() {
        if(!!d["c"]) {
            c.openPlatLogin.flag = 0;
            return true;
        } else {
            if(!!d["token"] || !!d["code"]) {
                c.openPlatLogin.flag = 1;
                return true;
            }
        }
        return false;
    },login:function(g) {
        switch(c.openPlatLogin.flag) {case 0:c.openPlatLogin.loginBySsic(g);break;case 1:c.openPlatLogin.loginFor360(g);break;default:break;}
    },loginFor360:function(k, i) {
        var j = new Object();
        j.url = e;
        var g = location.href;
        if(g.indexOf("?") != -1) {
            var h = g.substring(g.indexOf("?"), g.length);
            j.url += h;
        }
        j.type = "POST";
        if(!!i) {
            j.data = {"token":i};
        } else {
            if(!!d["token"]) {
                j.data = {"token":d["token"]};
            } else {
                if(!!d["code"]) {
                    j.data = {"code":d["code"]};
                }
            }
        }
        j.dataType = "json";
        j.success = function(l, m) {
            if(l.rc == 200) {
                c.increaseCounter(osType() + "," + getScreen() + "," + "500800002", d["source"]);
                if(!!k) {
                    k();
                }
                if(!!i) {
                    window.location.href = b;
                }
            } else {
                window.location.href = f + "?source=" + d["source"];
            }
        };
        j.error = function(l, n, m) {
            window.location.href = f + "?source=" + d["source"];
        };
        c.ajax(j);
    },loginBySsic:function(j) {
        var i = new Object();
        i.url = e;
        var g = location.href;
        if(g.indexOf("?") != -1) {
            var h = g.substring(g.indexOf("?"), g.length);
            i.url += h;
        }
        i.type = "POST";
        i.data = {"ssic":d["c"],"domain":a};
        if(!!d["domain"]) {
            i.data.domain = d["domain"];
        }
        i.dataType = "json";
        i.success = function(l, m) {
            if(l.rc == 200) {
                c.increaseCounter(osType() + "," + getScreen() + "," + "500800002", d["source"]);
                if(!!j) {
                    j();
                }
            } else {
                if(l.rc == 404) {
                    var k = __registerUrl;
                    if(__registerUrl.indexOf("?") > -1) {
                        k = __registerUrl.substring(0, __registerUrl.indexOf("?"));
                    }
                    window.location.href = k;
                } else {
                    window.location.href = f;
                }
            }
        };
        i.error = function(k, m, l) {
            window.location.href = f;
        };
        c.ajax(i);
    }},API:{app_360:{is360DeskTop:function() {
        return(typeof Bridge != "undefined" && !!Bridge.connect && !!Bridge.connect.tryLogin);
    },is360Logon:function() {
        return Bridge.support.hasLogin();
    },fetionLogin:function() {
        Bridge.connect.fetionLogin(function(g) {
            if(g) {
                var h = g.oauth.access_token;
                c.openPlatLogin.loginFor360(function() {
                }, h);
            } else {
                Bridge.oauth2.getAuthCode({"redirect_uri":__redirectUri});
            }
        });
    }}}});
})(jQuery);
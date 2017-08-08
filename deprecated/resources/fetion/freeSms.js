var freeSmsDropList = function() {
    var d = null;
    var f = [];
    var b;
    var a = $('<em class="droplistspan"></em>');
    var e = $('<a href="javascript:;" class="droplistpannel"></a>');
    var c = $('<ul class="droplist"></ul>');
    this.change = function(h) {
        b = h;
    };
    e.click(function(h) {
        stopPropagation(h);
        $(".droplist").hide();
        var i = $(this).offset();
        c.show();
        c.css({"width":this.offsetWidth});
        if(i.top + this.offsetHeight + c.height() > getViewportHeight()) {
            c.css({"top":(-1 - c.height())});
        }
        c.find("li").removeClass("droplist_select");
        c.find("li").each(function() {
            if(d.text == $(this).text()) {
                $(this).addClass("droplist_select");
            }
        });
        return false;
    });
    this.show = function() {
        a.append(e).append(c);
        return a;
    };
    this.addItem = function(i) {
        var h = $('<li ref="' + (i.value ? i.value : i.text) + '">' + i.text + "</li>");
        h.mouseover(function() {
            c.find("li").removeClass("droplist_select");
            $(this).addClass("droplist_select");
        });
        h.click(function() {
            g({value:$(this).attr("ref"),text:$(this).text()});
        });
        c.append(h);
        if(f.length == 0) {
            g(i);
        }
        f.push(i);
        if(f.length > 10) {
            c.css({height:132});
        }
    };
    function g(h) {
        e.text(h.text);
        d = h;
        if(b) {
            b();
        }
    }
    this.clearAll = function() {
        c.empty();
        f = [];
    };
    this.getValue = function() {
        return d.value;
    };
    this.setValue = function(j) {
        for(var h = 0; h < f.length; h++) {
            if(j == f[h].value) {
                g(f[h]);
                break;
            }
        }
    };
};
function formatNum(a) {
    return a < 10 ? "0" + a.toString() : a.toString();
}
var __firstSelectedUser = -1;
var __selectuserCount = 0;
var __UTCount = 0;
(function(E) {
    var a = true;
    var C = true;
    var g = ["请输入好友姓名(支持拼音首字母）或从右侧选择好友","好友列表里没有这个人，请重新输入","最多可以选择64人，您可重新选择接收人"];
    var V;
    var H;
    var K;
    var D;
    var t = window.parent.__user;
    var U = false;
    var B = /^0+.0+:0+:0+$/;
    var T = {};
    var i;
    var Q = null;
    var y = [];
    var b = 0;
    var l = 0;
    var F = 0;
    var A = window.parent.__phoneMap;
    var d = window.parent.__contactMap;
    var S = "";
    var m = "";
    E(function() {
        v();
        h();
        x();
        i = E.fn.loadingPanel({target:"cont_1"});
    });
    function h() {
        K = E("#receive_man>ul");
        H = E('<li style="border-style:none;height:24px;"><input type="text"></li>');
        V = H.find("input:first");
        V.css({"border-style":"none","border-width":0,"width":"78px","background":"#ffffff"});
        D = E(".choose_friend");
        K.append(H);
        if(!a) {
            E("#timePannel").attr("disabled", "disabled");
            E("#smsContent").attr("disabled", "disabled").css({"resize":"none"});
            E("#sendToMe").attr("disabled", "disabled");
            V.attr("disabled", "disabled");
            return;
        }
        E("#smsContent").css({"resize":"none"});
        if(!t.isCM) {
            E("#sendToMe").css({"display":"none"});
            E("#sendToMe").next().css({"display":"none"});
            E(".choose_friend_name").css({"margin-top":"21px"});
        }
        E("#receive_man").click(function() {
            V.focus();
        });
        Q = E(".SMS_tis");
        Q.find("a").bind("click", function(X) {
            Q.css({"display":"none"});
        });
        Q.find("em").bind("click", function(X) {
            E.increaseCounter(500400008);
            if(y.length <= 1) {
                window.parent.createAddBuddyTab(y[0]);
            } else {
                window.parent.createAddBuddyBatchTab(y.join("|"));
            }
        });
        n();
        V.focus(function() {
            if(this.value.length == 0) {
                z(g[0]);
                setTimeout(function() {
                    R();
                }, 10000);
                k();
            }
        });
        V.keyup(function(X) {
            if(X.keyCode != 40 && X.keyCode != 38 && X.keyCode != 39 && X.keyCode != 41 && X.keyCode != 13) {
                if(this.value.length > 0) {
                    j(this);
                } else {
                    z(g[0]);
                    setTimeout(function() {
                        R();
                    }, 10000);
                }
            }
        });
        V.keydown(function(X) {
            if(X.keyCode == 40) {
                __firstSelectedUser++;
                if(__firstSelectedUser > D.find("a").length) {
                    __firstSelectedUser = D.find("a").length;
                }
                D.find("a").removeClass("choose_select");
                D.find("a:eq(" + __firstSelectedUser + ")").addClass("choose_select");
                return false;
            }
            if(X.keyCode == 38) {
                __firstSelectedUser--;
                if(__firstSelectedUser == -1) {
                    __firstSelectedUser == 0;
                }
                D.find("a").removeClass("choose_select");
                D.find("a:eq(" + __firstSelectedUser + ")").addClass("choose_select");
                return false;
            }
            if(X.keyCode == 13) {
                D.find("li:eq(" + __firstSelectedUser + ")").click();
                return false;
            }
            if(X.keyCode == 8 && this.value.length == 0) {
                if(H.prev("li").length >= 0) {
                    p(H.prev("li").attr("uid"));
                }
            }
        });
        E("#sendToMe").click(function() {
            if(this.checked) {
                P(t.nn, t.uid, t.ca, t.mn);
            } else {
                p(t.uid);
            }
        });
        E("#chooseFriendMore").click(function() {
            var X = E(this);
            if(this.className == "choose_open") {
                O();
                G();
            } else {
                k();
            }
            return false;
        });
        D.mousemove(function() {
            if(D.find(".choose_select").length > 0) {
                D.find(".choose_select").removeClass("choose_select");
            }
        });
        E("#chooseFriendMoreCloseBtn").click(function() {
            k();
            return false;
        });
        E("#timePannel").click(function() {
            var Y = new Date();
            if(E("#hour").text() == "" && E("#minute").text() == "") {
                if(this.checked) {
                    var X = Y.getMinutes();
                    Y.setMinutes(X + 30);
                    E("#date").text(Y.getFullYear() + "-" + formatNum(Y.getMonth() + 1) + "-" + formatNum(Y.getDate()));
                    E("#hour").text(formatNum(Y.getHours()));
                    E("#minute").text(formatNum(Y.getMinutes()));
                } else {
                    E("#date").text(Y.getFullYear() + "-" + formatNum(Y.getMonth() + 1) + "-" + formatNum(Y.getDate()));
                    E("#hour").text("");
                    E("#minute").text("");
                }
            }
        });
        E(".btn_send_sms").click(w);
        E(document).click(G);
        V.click(function(X) {
            stopPropagation(X);
        });
        var W = 0;
        E("#smsContent").blur(function() {
            clearInterval(W);
        });
        E("#smsContent").focus(function() {
            clearInterval(W);
            W = setInterval(function() {
                E("#smsContentLen").text(350 - E("#smsContent").val().length);
                if(350 - E("#smsContent").val().length < 0) {
                    E("#smsContentLenTips").css({"color":"#ff0000"});
                } else {
                    E("#smsContentLenTips").css({"color":"#000000"});
                }
            }, 100);
        });
    }
    function n() {
        var ab = objects(window.location.search);
        var Y = ab["guid"];
        if(!Y) {
            return;
        }
        var Z = ab["uid"];
        var aa = ab["mn"];
        var X = A.get(Y);
        var ac = X.FN;
        if(!!Z) {
            if(Z == t.uid) {
                E("#sendToMe").get(0).checked = true;
                P(t.nn, t.uid, t.ca, t.mn);
            } else {
                var W = d.get(Z.toString());
                if((W.isCM && !B.test(W.sms)) || W.isCT || W.isCU) {
                    P(W.displayName, W.mn, "", W.mn, true);
                } else {
                    P(W.displayName, W.uid, W.ca, W.mn);
                }
            }
        } else {
            P(ac, aa, "", aa, true);
        }
    }
    function v() {
        var W = "";
        if(t.isMobile) {
            if(t.cas == "1") {
                W = "您的手机已停机，暂时不能发送短信。";
            } else {
                if(t.cas == "2") {
                    W = "您的手机已销号，暂时不能发送短信。";
                }
            }
        } else {
            W = "您尚未绑定手机号码，暂时不能使用发短信功能，请绑定手机号后再使用。";
        }
        if(W.length > 0) {
            E("#cont_1").prepend("<P class=free_error><SPAN></SPAN>" + W + "</P>");
            a = false;
            return false;
        }
        return true;
    }
    function u(W) {
        return W.displayName;
    }
    function I(W) {
        if(!W.isMobile || (W.isMobile && (W.cas == "1" || W.cas == "2")) || (W.ca && W.ca.toLocaleUpperCase() == "ROBOT") || (W.rs != 1) || (W.ct == 2) || (W.isCM && !B.test(W.sms)) || W.isBk == 1) {
            return false;
        }
        return true;
    }
    function c() {
        var Y = E("#receive_man>ul");
        var X = Y.find("input:first");
        var W = X.offset().left - Y.offset().left;
        if(W <= 9) {
            W = 0;
        }
        return W;
    }
    function z(X, Y) {
        var W = E(".choose_txt");
        D.hide();
        W.html(X);
        W.css({"left":c(),"display":"block"});
    }
    function R() {
        E(".choose_txt").hide();
    }
    function G() {
        V.val("");
        if(E(".choose_txt").text() != g[2]) {
            R();
        }
        D.hide();
        __firstSelectedUser = -1;
        E(".droplist").hide();
    }
    function s(W) {
        if(K.find("li[uid='" + W + "']").length > 0) {
            return true;
        } else {
            return false;
        }
    }
    function P(ad, aa, Y, X, W) {
        if(!t.isCM && __selectuserCount > window.parent.__leftSmsCount) {
            Q.css({"display":"block"});
            if(window.parent.__leftSmsCount == 0) {
                Q.find("em").text("您可发送的免费短信已用完。");
            } else {
                Q.find("em").text("您可发送的免费短信数量不足，有" + (__selectuserCount - window.parent.__leftSmsCount) + "条短信无法发送。");
            }
        }
        if(__selectuserCount >= 64) {
            G();
            k();
            if(!E("#sendToMe").attr("checked")) {
                E("#sendToMe").attr("disabled", "disabled");
            }
            return false;
        }
        if(!s(aa)) {
            var ae = (Y.toLowerCase() == "ct" ? "电信" : (Y.toLowerCase() == "cucc" ? "联通" : ""));
            var ac = htmlEncode(ad);
            if(!!ad) {
                if(!!X) {
                    ac += "(" + X + ")";
                }
            } else {
                ac = X;
                ad = X;
            }
            var af;
            if(!!ae) {
                af = E('<li uid="' + aa + '" ca="' + Y + '" title="' + ac + '"><span><a class="payIcon" title="付费短信" href="javascript:;"></a>' + htmlEncode(cnSubstr(ad, 14, false)) + "<em>(" + ae + ')</em><a title="取消" href="javascript:;"></a></span></li>');
            } else {
                if(W) {
                    y.push(X);
                    af = E('<li uid="' + aa + '" ca="' + Y + '" title="' + ac + '" isDirectSMS="1"><span><a class="payIcon" title="付费短信" href="javascript:;"></a>' + ad + '<a title="取消" href="javascript:;"></a></span></li>');
                } else {
                    af = E('<li uid="' + aa + '" ca="' + Y + '" title="' + ac + '"><span>' + htmlEncode(cnSubstr(ad, 14, false)) + '<a href="javascript:;"></a></span></li>');
                }
            }
            af.find("a").click(function() {
                p(E(this).parent().parent().attr("uid"));
                return false;
            });
            H.before(af);
            __selectuserCount++;
            if(Y.toLowerCase() == "ct" || Y.toLowerCase() == "cucc" || W) {
                var ab = "",Z = y.length;
                if(Z > 0) {
                    ab = "，有" + Z + "位非好友，可<em style='cursor:pointer;color:#76a9d0;'>加为好友</em>";
                }
                __UTCount++;
                Q.css({"display":"block"});
                Q.find("em").html("有" + __UTCount + "条付费短信，每人每条同本地短信资费" + ab);
            }
            if(K.height() >= 143) {
                E("#receive_man").addClass("receive_man_name_height");
                E("#receive_man").scrollTop(E("#receive_man").get(0).scrollHeight);
            }
        }
    }
    function p(Z) {
        if(s(Z)) {
            var aa = K.find("li[uid='" + Z + "']");
            if(aa.attr("ca").toLowerCase() == "cucc" || aa.attr("ca").toLowerCase() == "ct" || aa.attr("isDirectSMS") == "1") {
                __UTCount--;
                if(__UTCount <= 0) {
                    Q.css({"display":"none"});
                }
                var ab = "",W = y.length;
                if(aa.attr("isDirectSMS") == "1") {
                    var X = y.concat();
                    y.length = 0;
                    for(var Y = 0; Y < W; Y++) {
                        if(X[Y] != Z) {
                            y.push(X[Y]);
                        }
                    }
                }
                W = y.length;
                if(W > 0) {
                    ab = "，有" + W + "位非好友，可<em style='cursor:pointer;color:#76a9d0;'>加为好友</em>";
                } else {
                    E("#ckb1").addClass("none");
                    E("#ckb2").removeClass("none");
                }
                Q.find("em").html("有" + __UTCount + "条付费短信，每人每条同本地短信资费" + ab);
            }
            aa.remove();
            __selectuserCount--;
            if(__selectuserCount <= 64) {
                E(".choose_friend_name").find("h2 > span").addClass("none");
            }
            if(Z == t.uid) {
                E("#sendToMe").get(0).checked = false;
            } else {
                if(U) {
                    E("input[uid='" + Z + "']").get(0).checked = false;
                }
            }
            E("#sendToMe").removeAttr("disabled");
            if(K.height() < 143) {
                E("#receive_man").removeClass("receive_man_name_height");
            }
        }
    }
    function O() {
        U = true;
        E(".choose_friend_name").show();
        var af = window.parent.__buddyList;
        af.push({"id":-2,"n":"通讯录"});
        E("#chooseFriendMore").attr("class", "choose_away");
        E(".choose_friname_con > dl").remove();
        for(var ac = 0; ac < af.length; ac++) {
            var Y = af[ac];
            var ad = E("<dl></dl>");
            var X = E('<dt><a id="' + Y.id + '" href="javascript:;" class="all_choose">全选</a><a href="javascript:;" class="' + (ac == 0 ? "my_friend_open" : "my_friend_close") + '">' + cnSubstr(htmlEncode(Y.n), 20, false) + "</a></dt>");
            var ag = E("<dd" + (ac == 0 ? "" : ' style="display:none"') + "></dd>");
            var ae = E("<ul></ul>");
            ag.append(ae);
            var W = X.find("a:first");
            var aj = X.find("a:last");
            aj.click(function() {
                if(this.className == "my_friend_close") {
                    this.className = "my_friend_open";
                    E(this).parent().next("dd").show();
                } else {
                    this.className = "my_friend_close";
                    E(this).parent().next("dd").hide();
                }
            });
            W.click(function() {
                var ak = E(this).attr("id");
                if(E(this).text() == "全选") {
                    E(this).parent().next("dd").find("input[type='checkbox']").each(function() {
                        if(!this.checked) {
                            this.checked = true;
                            if(ak == -2) {
                                P(E(this).attr("username"), E(this).attr("uid"), E(this).attr("ca"), E(this).attr("mn"), true);
                            } else {
                                P(E(this).attr("username"), E(this).attr("uid"), E(this).attr("ca"), E(this).attr("mn"));
                            }
                            if(__selectuserCount >= 64) {
                                E(".choose_friend_name").find("h2 > span").removeClass("none");
                                setTimeout(function() {
                                    E(".choose_friend_name").find("h2 > span").addClass("none");
                                }, 10000);
                                return false;
                            }
                        }
                    });
                    E(this).text("取消");
                } else {
                    E(this).parent().next("dd").find("input[type='checkbox']").each(function() {
                        if(this.checked) {
                            this.checked = false;
                            p(E(this).attr("uid"));
                        }
                    });
                    E(this).text("全选");
                }
            });
            ad.append(X).append(ag);
            T[Y.id] = {ul:ae,count:0,dl:ad};
        }
        var ab = d.values();
        var ai = A.values();
        ab.sort(buddyComparator);
        function ah(ap, ar) {
            for(var an = 0,am = ap.length; an < am; an++) {
                var al = ap[an];
                var at = -2;
                if(ar == 0) {
                    if(!I(al)) {
                        continue;
                    }
                    if((!al.isCM) && (!t.isCM)) {
                        continue;
                    }
                    at = Number(al.bl.split(";")[0]);
                    if(at < 0) {
                        continue;
                    }
                } else {
                    if(!al.tel) {
                        continue;
                    } else {
                        if(!!al.tel.uid) {
                            if(al.tel.uid == t.uid) {
                                continue;
                            }
                            var ao = d.get(al.tel.uid.toString());
                            if(!!ao && ao.isCM && B.test(ao.sms)) {
                                continue;
                            }
                        }
                    }
                }
                var ak = T[at];
                var aw;
                if(ak.count == 0) {
                    aw = E("<li></li>");
                    ak.ul.append(aw);
                } else {
                    aw = ak.ul.find("li:last");
                    if(aw.find("span").length >= 5) {
                        aw = E("<li></li>");
                        ak.ul.append(aw);
                    }
                }
                ak.count += 1;
                var av = E("<span></span>");
                var aq;
                if(ar == 0) {
                    if(s(al.uid)) {
                        aq = E('<input type="checkbox" name="checkbox3" id="checkbox' + al.uid + '" uid="' + al.uid + '" mn="' + al.mn + '" ca="' + al.ca + '" username="' + htmlEncode(u(al)) + '" checked />');
                    } else {
                        aq = E('<input type="checkbox" name="checkbox3" id="checkbox' + al.uid + '" uid="' + al.uid + '" mn="' + al.mn + '" ca="' + al.ca + '" username="' + htmlEncode(u(al)) + '"/>');
                    }
                } else {
                    if(s(al.tel.mn)) {
                        aq = E('<input type="checkbox" name="checkbox3" id="checkbox' + al.tel.mn + '" uid="' + al.tel.mn + '" mn="' + al.tel.mn + '" ca="" username="' + htmlEncode(al.FN) + '" checked />');
                    } else {
                        aq = E('<input type="checkbox" name="checkbox3" id="checkbox' + al.tel.mn + '" uid="' + al.tel.mn + '" mn="' + al.tel.mn + '" ca="" username="' + htmlEncode(al.FN) + '"/>');
                    }
                }
                aq.click(function() {
                    var ay = E(this);
                    var ax = E(this).parent().parent().parent().find("input[type='checkbox']").length;
                    var az = 0;
                    E(this).parent().parent().parent().find("input[type='checkbox']").each(function() {
                        if(this.checked) {
                            az++;
                        }
                    });
                    if(this.checked) {
                        if(ar == 0) {
                            P(ay.attr("username"), ay.attr("uid"), ay.attr("ca"), ay.attr("mn"));
                        } else {
                            P(ay.attr("username"), ay.attr("uid"), ay.attr("ca"), ay.attr("mn"), true);
                        }
                        if(az == ax) {
                            E(this).parent().parent().parent().parent().prev("dt").find("a:first").text("取消");
                        }
                    } else {
                        p(ay.attr("uid"));
                        if(az == 0) {
                            E(this).parent().parent().parent().parent().prev("dt").find("a:first").text("全选");
                        }
                    }
                });
                var au;
                if(ar == 0) {
                    au = E('<label for="checkbox' + al.uid + '" title="' + htmlEncode(u(al)) + '"></label>');
                    au.text(cnSubstr(u(al), 6, false));
                } else {
                    if(ar == 1) {
                        au = E('<label for="checkbox' + al.tel.mn + '" title="' + htmlEncode(al.FN) + '"></label>');
                        au.text(cnSubstr(al.FN, 6, false));
                    }
                }
                av.append(aq).append(au);
                aw.append(av);
            }
        }
        ah(ab, 0);
        if(t.isCM) {
            ah(ai, 1);
        }
        for(var ac in T) {
            if(T[ac].count && T[ac].count > 0) {
                var ae = T[ac].ul,ad = T[ac].dl;
                var aa = ae.find("input[type='checkbox']").length;
                var Z = 0;
                ae.find("input[type='checkbox']").each(function() {
                    if(this.checked) {
                        Z++;
                    }
                });
                if(Z == aa) {
                    ad.find("dt > a:first").text("取消");
                }
                E(".choose_friname_con").append(ad);
            }
        }
    }
    function k() {
        E("#chooseFriendMore").attr("class", "choose_open");
        E(".choose_friend_name").hide();
        U = false;
    }
    function j(W) {
        var af = W.value.trim();
        var ak = d.values();
        var Y = ak.length;
        var aj = new Array();
        if(C) {
            for(var ai = 0; ai < Y; ai++) {
                var ae = searchHelper.MakeSpellCodeAsWordsAll(ak[ai].is);
                ak[ai].is = ae.bodyContent;
            }
            C = false;
        }
        for(var ai = 0; ai < Y; ai++) {
            var an = af.toLowerCase();
            var aa = new RegExp("[*]+", "gi");
            var ac = af.toLowerCase().replace(aa, "");
            var ab = "";
            var Z = "";
            try {
                ab = searchHelper.MakeSpellCodeAsWordsAll(ak[ai].is).bodyContent;
                Z = searchHelper.MakeSpellCodeAsWordsAll(ak[ai].pis).bodyContent;
            } catch(am) {
            }
            var ah = ak[ai];
            if(!I(ah)) {
                continue;
            }
            if(!t.isCM && (ah.ca.toLowerCase() == "ct" || ah.ca.toLowerCase() == "cucc")) {
                continue;
            }
            if((ah.nn && ah.nn.toLowerCase().indexOf(an) >= 0) || (ah.ln && ah.ln.toLowerCase().indexOf(an) >= 0) || (ah.mn && ah.mn.toLowerCase().indexOf(an) >= 0) || (ab && ab.toLowerCase().indexOf(ac) >= 0) || (Z && Z.toLowerCase().indexOf(ac) >= 0)) {
                aj.push(ah);
            }
        }
        aj.sort(buddyComparator);
        var ad = aj.length;
        E(".choose_friend > li").remove();
        if(ad > 0) {
            D.show();
            D.css({left:c()});
            R();
            __firstSelectedUser = 0;
            for(var ai = 0; ai < ad; ai++) {
                var ag = u(aj[ai]);
                var X = E('<li><a href="javascript:;" uid="' + aj[ai].uid + '" mn="' + aj[ai].mn + '" ca="' + aj[ai].ca + '">' + htmlEncode(ag) + "</a></li>");
                var al = X.find("a");
                D.append(X);
                if(ai == 0) {
                    al.addClass("choose_select");
                }
                X.click(function() {
                    var ap = E(this).find("a");
                    var ao = ap.attr("uid");
                    if(!s(ao)) {
                        P(ap.text(), ao, ap.attr("ca"), ap.attr("mn"));
                    }
                    G();
                    return false;
                });
            }
        } else {
            if(t.isCM && window.parent.__isEnableDirectSMS == 1 && numReg.test(af)) {
                D.show();
                D.css({left:c()});
                R();
                __firstSelectedUser = 0;
                var X = E('<li style="background-color:#F4FAFF;cursor:default;"><em class="payIcon"></em>' + af + "</li>");
                D.append(X);
                X.click(function() {
                    if(isMobileNoReg.test(af)) {
                        E("#ckb1").removeClass("none");
                        E("#ckb2").addClass("none");
                        if(t.mn == af) {
                            E("#sendToMe").get(0).checked = true;
                            P(t.nn, t.uid, t.ca, t.mn);
                        } else {
                            var ar = "",aq = A.values();
                            for(var ap = 0,ao = aq.length; ap < ao; ap++) {
                                phone = aq[ap];
                                if(!!phone.tel && phone.tel.mn == af) {
                                    ar = phone.FN;
                                }
                            }
                            P(ar, af, "", af, true);
                        }
                    } else {
                        M("请填写正确的手机号码");
                    }
                    G();
                    return false;
                });
            } else {
                z(g[1]);
            }
        }
    }
    var r = new freeSmsDropList();
    var J = new freeSmsDropList();
    var e = new freeSmsDropList();
    var q = new freeSmsDropList();
    var o = new freeSmsDropList();
    function x() {
        var W = new Date();
        E(document).bind("click", function(Y) {
            Y = window.event || Y;
            var X = Y.target || Y.srcElement;
            var Z = E(X).attr("id");
            if(Z != "hour" && Z != "hour_list") {
                E("#hour_list").addClass("none");
            }
            if(Z != "minute" && Z != "minute_list") {
                E("#minute_list").addClass("none");
            }
        });
        E("#date").text(W.getFullYear() + "-" + formatNum(W.getMonth() + 1) + "-" + formatNum(W.getDate()));
        E("#date").bind("click", function(X) {
            E("#hour_list").addClass("none");
            E("#minute_list").addClass("none");
            WdatePicker({dateFmt:"yyyy-MM-dd",onpicked:function(Y) {
                E("#date").text($dp.cal.getP("y") + "-" + $dp.cal.getP("M") + "-" + $dp.cal.getP("d"));
                E("#timePannel").attr("checked", "checked");
            }});
        });
        E("#hour").bind("click", function(Y) {
            var X = E("#hour_list");
            if(X.hasClass("none")) {
                X.removeClass("none");
            } else {
                X.addClass("none");
            }
        });
        E("#hour_list").bind("click", function(Y) {
            E(this).find("b").removeClass("red");
            Y = window.event || Y;
            var X = Y.target || Y.srcElement;
            if(X.nodeName.toLowerCase() == "b") {
                E(X).addClass("red");
                E("#hour").text(E(X).text());
                if(E("#minute").text() == "") {
                    E("#minute").text("00");
                }
                E("#timePannel").attr("checked", "checked");
            }
            E(this).addClass("none");
        }).bind("mouseover", function(Y) {
            Y = window.event || Y;
            var X = Y.target || Y.srcElement;
            if(X.nodeName.toLowerCase() == "b") {
                E(X).addClass("on");
            }
        }).bind("mouseout", function(Y) {
            Y = window.event || Y;
            var X = Y.target || Y.srcElement;
            if(X.nodeName.toLowerCase() == "b") {
                E(X).removeClass("on");
            }
        });
        E("#minute").bind("click", function(Y) {
            var X = E("#minute_list");
            if(X.hasClass("none")) {
                X.removeClass("none");
            } else {
                X.addClass("none");
            }
        });
        E("#minute_list").bind("click", function(Y) {
            E(this).find("b").removeClass("red");
            Y = window.event || Y;
            var X = Y.target || Y.srcElement;
            if(X.nodeName.toLowerCase() == "b") {
                E(X).addClass("red");
                E("#minute").text(E(X).text());
                E("#timePannel").attr("checked", "checked");
            }
            E(this).addClass("none");
        }).bind("mouseover", function(Y) {
            E(this).find("b").removeClass("on");
            Y = window.event || Y;
            var X = Y.target || Y.srcElement;
            if(X.nodeName.toLowerCase() == "b") {
                E(X).addClass("on");
            }
        }).bind("mouseout", function(Y) {
            Y = window.event || Y;
            var X = Y.target || Y.srcElement;
            if(X.nodeName.toLowerCase() == "b") {
                E(X).removeClass("on");
            }
        });
    }
    function N() {
        var X = E("#date").text().split("-");
        var W = new Date(X[0], X[1] - 1, X[2], E("#hour").text(), E("#minute").text());
        return W;
    }
    function M(X, Y) {
        var W = true;
        if(Y) {
            W = Y;
        }
        window.parent.$.fn.fetionHintWindow({modal:W}).show(X, "提示", "OK");
    }
    function w() {
        E.increaseCounter(500400006);
        try {
            var aa = d;
            var ab = E("#timePannel").get(0).checked;
            var af = N();
            var ak = E("#smsContent").val();
            var X = [];
            var ah = [];
            var ai = [];
            var ag = [];
            K.find("li[uid]").each(function() {
                var an = E(this).attr("uid"),am = E(this).attr("isdirectsms");
                var al = aa.get(an);
                if(an != t.uid && (!al || !I(al)) && !am) {
                    ag.push(E(this).text());
                } else {
                    if(ab) {
                        X.push(an);
                    } else {
                        if(!!al && (al.isCT || al.isCU)) {
                            ah.push(an);
                        } else {
                            if(am == 1) {
                                ai.push(an);
                            } else {
                                X.push(an);
                            }
                        }
                    }
                }
            });
            if(X.length == 0 && ah.length == 0 && ai.length == 0) {
                M("请选择好友");
                return false;
            }
            if(ak.length == 0) {
                M("请填写信息内容");
                return false;
            }
            if(ak.length > 350) {
                var ad = 0;
                var Y = 0;
                ad = setInterval(function() {
                    if(Y % 2 == 0) {
                        E("#smsContent").css({"background-color":"#FFCCCC"});
                    } else {
                        E("#smsContent").css({"background-color":"#FFFFFF"});
                    }
                    Y++;
                    if(Y >= 4) {
                        clearInterval(ad);
                    }
                }, 150);
                return false;
            }
            if(!t.isCM && __selectuserCount > window.parent.__leftSmsCount) {
                Q.css({"display":"block"});
                if(window.parent.__leftSmsCount == 0) {
                    Q.find("em").text("您可发送的免费短信已用完。");
                } else {
                    Q.find("em").text("您可发送的免费短信数量不足，有" + (__selectuserCount - window.parent.__leftSmsCount) + "条短信无法发送。");
                }
                return;
            }
            if(window.parent.codeImgFlag && !ab && ai.length > 0) {
                if(l > 0 && l % 50 == 0) {
                    window.parent.createCcpUI(0, "你向非好友发送的付费短信过于频繁，为了帐号的安全请输入验证码");
                    return;
                }
            }
            var W = false;
            function aj() {
                var am = new Object();
                am.type = "POST";
                am.dataType = "json";
                am.cache = false;
                am.data = {"UserName":t.uid,"Message":ak,"Receivers":X.join(","),"ssid":window.parent.__sessionId};
                if(ab) {
                    am.url = formatString(window.parent.setScheduleSmsUrl, window.parent.__version++, 0);
                    var al = new Date();
                    if(af - al < 600000) {
                        M("您不能设置早于当前的时间，请重新设置");
                        return false;
                    } else {
                        if(af - al > 31536000000) {
                            M("您不能设置大于当前1年的时间，请重新设置");
                            return false;
                        } else {
                            E.increaseCounter(500400005);
                            am.data["SendTime"] = af.getUTCFullYear() + "-" + (af.getUTCMonth() + 1) + "-" + af.getUTCDate() + " " + af.getUTCHours() + ":" + af.getUTCMinutes() + ":0";
                            am.data["SmsCode"] = S;
                            am.data["PicCode"] = m;
                            S = "";
                            m = "";
                        }
                    }
                } else {
                    am.url = formatString(window.parent.sendSMSUrl, window.parent.__version++);
                }
                am.success = function(ao, ap) {
                    i.hide();
                    if(typeof ao.rc == "number") {
                        switch(ao.rc) {
                            case 200:
                                if(!Z && !W) {
                                    if(ag.length > 0) {
                                        var an = ag.join("，");
                                        M("您的短信已发送！<br>" + an + " &nbsp; 暂时无法接收您的短信");
                                    } else {
                                        M("您的短信已发送！");
                                    }
                                    W = true;
                                }
                                if(!!ao.rv) {
                                    window.parent.__leftSmsCount = Math.min(ao.rv.day, ao.rv.month);
                                }
                                f();
                                break;
                            case 310:M("Session会话过期！");break;
                            case 311:M("客户端请求参数错误！");break;
                            case 400:M("信令参数错误！");break;
                            case 406:M("消息有敏感词！");break;
                            case 422:M("没有开通直接短信！");break;
                            case 486:
                                if(!!ao.rv) {
                                    window.parent.__leftSmsCount = ao.rv.day;
                                }if(!t.isCM) {
                                    Q.css({"display":"block"});
                                    if(window.parent.__leftSmsCount == 0) {
                                        Q.find("em").text("您可发送的免费短信已用完。");
                                    } else {
                                        Q.find("em").text("您可发送的免费短信数量不足，有" + (__selectuserCount - window.parent.__leftSmsCount) + "条短信无法发送。");
                                    }
                                } else {
                                    M("对不起，今日您发送短信数量已达上限，请明天再发");
                                }
                                break;
                            case 4860:M("指定时间上已经有定时短信！");break;case 4861:if(ab) {
                            M("对不起，您在该天设置的定时短信已达上限。");
                        } else {
                            if(!!ao.rv) {
                                window.parent.__leftSmsCount = ao.rv.day;
                            }
                            if(!t.isCM) {
                                Q.css({"display":"block"});
                                if(window.parent.__leftSmsCount == 0) {
                                    Q.find("em").text("您可发送的免费短信已用完。");
                                } else {
                                    Q.find("em").text("您可发送的免费短信数量不足，有" + (__selectuserCount - window.parent.__leftSmsCount) + "条短信无法发送。");
                                }
                            } else {
                                M("对不起，今日您发送短信数量已达上限，请明天再发");
                            }
                        }break;case 4862:if(ab) {
                            M("对不起，您在该月设置的定时短信已达上限。");
                        } else {
                            if(!!ao.rv) {
                                window.parent.__leftSmsCount = ao.rv.month;
                            }
                            if(!t.isCM) {
                                Q.css({"display":"block"});
                                if(window.parent.__leftSmsCount == 0) {
                                    Q.find("em").text("您可发送的免费短信已用完。");
                                } else {
                                    Q.find("em").text("您可发送的免费短信数量不足，有" + (__selectuserCount - window.parent.__leftSmsCount) + "条短信无法发送。");
                                }
                            } else {
                                M("对不起，本月您发送短信数量已达上限，请下月再发");
                            }
                        }break;case 4863:M("超过未发送限制！");break;case 4864:M("接收人数限制！");break;case 4865:M("发送时间限制！");break;case 494:M("您的飞信账号处于系统保护状态，限制了该功能的使用。<br>请手机发送短信U到12520解除限制后重新登录。");break;default:M("系统错误请重试！");break;}
                    }
                };
                am.error = function() {
                    if(!W) {
                        i.hide();
                        M("系统错误请重试！");
                        W = true;
                    }
                    f();
                };
                i.show();
                E.ajax(am);
            }
            var Z = false;
            function ae(am, al) {
                var an = new Object();
                an.type = "POST";
                an.dataType = "json";
                an.cache = false;
                an.data = {"UserName":t.uid,"msg":ak,"receivers":am.join(","),"type":al,"ssid":window.parent.__sessionId};
                an.url = formatString(window.parent.SendDirectSMSUrl, window.parent.__version++);
                an.success = function(ap, aq) {
                    i.hide();
                    if(!!ap && typeof ap.rc == "number") {
                        switch(ap.rc) {case 280:if(!Z && !W) {
                            if(ag.length > 0) {
                                var ao = ag.join("，");
                                M("您的短信已发送！<br>" + ao + " &nbsp; 暂时无法接收您的短信");
                            } else {
                                M("您的短信已发送！");
                            }
                            W = true;
                        }if(al == 1) {
                            b = ap.rv.hour;
                            l = ap.rv.day;
                            F = ap.rv.month;
                            window.parent.codeImgFlag = true;
                        }f();break;case 999:Z = true;E(".hint").remove();switch(ap.rv) {case 1:M("对不起，你发送直接短信的数量已超过上限50条/小时，请稍后再试。");break;case 2:M("对不起，你今天发送直接短信的数量已达上限，请明天继续使用。");break;case 3:M("对不起，你本月发送直接短信的数量已达上限，请下个月再继续使用。");break;}break;default:break;}
                    }
                };
                an.error = function() {
                    if(!W) {
                        i.hide();
                        M("系统错误请重试！");
                        W = true;
                    }
                    f();
                };
                i.show();
                E.ajax(an);
            }
            if(X.length > 0) {
                aj();
            }
            if(!ab && ah.length > 0) {
                ae(ah, 0);
            }
            if(!ab && ai.length > 0) {
                E.increaseCounter(500400007);
                ae(ai, 1);
            }
        } catch(ac) {
            alert(ac);
        }
        return false;
    }
    function f() {
        var X = getQueryString(window.location.search, "guid");
        if(!!X) {
            L();
            return;
        }
        y = [];
        __UTCount = 0;
        __selectuserCount = 0;
        U = false;
        Q.css({"display":"none"});
        K.find("li").each(function() {
            var Y = E(this).attr("uid");
            if(!!Y) {
                E(this).remove();
            }
        });
        k();
        E("#sendToMe").get(0).checked = false;
        E("#smsContent").val("");
        E("#ckb1").addClass("none");
        E("#ckb2").removeClass("none");
        E("#timePannel").get(0).checked = false;
        var W = new Date();
        E("#date").text(W.getFullYear() + "-" + formatNum(W.getMonth() + 1) + "-" + formatNum(W.getDate()));
        E("#hour").text("");
        E("#minute").text("");
    }
    function L() {
        var W = getQueryString(window.location.search, "tabIndex");
        if(window.parent && window.parent.__mainTabWindow) {
            window.parent.__mainTabWindow.removeTab(W);
        }
    }
})(jQuery);
String.prototype.endsWith = function(a) {
    if(typeof a == "undefined" || a == "" || this.length == 0 || a.length > this.length) {
        return false;
    }
    if(this.substring(this.length - a.length) == a) {
        return true;
    } else {
        return false;
    }
    return true;
};
String.prototype.startsWith = function(a) {
    if(typeof a == "undefined" || a == "" || this.length == 0 || a.length > this.length) {
        return false;
    }
    if(this.substr(0, a.length) == a) {
        return true;
    } else {
        return false;
    }
    return true;
};
String.prototype.trim = function(b) {
    if(typeof b == "undefined") {
        return this.replace(/^\s+|\s+$/g, "");
    }
    var a = new RegExp("^[" + b + "]+|[" + b + "]+$", "g");
    return this.replace(a, "");
};
String.prototype.trimEnd = function(b) {
    if(typeof b == "undefined") {
        return this.replace(/\s+$/g, "");
    }
    var a = new RegExp("[" + b + "]+$", "g");
    return this.replace(a, "");
};
String.prototype.toCodeArray = function() {
    var b = new Array();
    var a = this.length;
    if(a == 0) {
        return b;
    }
    for(var c = 0; c < a; c++) {
        b.push(this.charCodeAt(c));
    }
    return b;
};
String.prototype.fromCodeArray = function(b) {
    if(b == null) {
        return null;
    }
    var a = b.length;
    var d = "";
    for(var c = 0; c < a; c++) {
        d += String.fromCharCode(b[c]);
    }
    return d;
};
function formatString() {
    if(arguments.length < 2) {
        return arguments;
    }
    var a = arguments[0];
    for(var b = 0; b < arguments.length - 1; b++) {
        a = a.replace("{" + b + "}", arguments[b + 1]);
    }
    return a;
}
function cnLength(c) {
    if(c == null) {
        return null;
    }
    var a = 0;
    for(var b = 0; b < c.length; b++) {
        if(c.charCodeAt(b) > 255) {
            a += 2;
        } else {
            a++;
        }
    }
    return a;
}
function cnSubstr(e, a, c) {
    if(e == null) {
        return null;
    }
    if(cnLength(e) <= a + 2 && !c) {
        return e;
    }
    var f = 0;
    var d = "";
    if(e != null) {
        for(var b = 0; b < e.length; b++) {
            if(e.charCodeAt(b) > 255) {
                f += 2;
            } else {
                f++;
            }
            if(f <= a) {
                d += e.charAt(b);
            } else {
                if(c) {
                    return d;
                } else {
                    return d + "...";
                }
            }
        }
    }
    return d;
}
function cnLength2(d) {
    if(d == null) {
        return null;
    }
    var a = 0;
    for(var c = 0; c < d.length; c++) {
        var b = d.charAt(c);
        if(d.charCodeAt(c) > 255 || "@#$%^&+<>ADGHMNOQTUVWY".indexOf(b) >= 0) {
            a += 2;
        } else {
            a++;
        }
    }
    return a;
}
function cnSubstr2(f, a, d) {
    if(f == null) {
        return null;
    }
    if(cnLength2(f) <= a + 2 && !d) {
        return f;
    }
    var g = 0;
    var e = "";
    if(f != null) {
        for(var c = 0; c < f.length; c++) {
            var b = f.charAt(c);
            if(f.charCodeAt(c) > 255 || "@#$%^&+<>ADGHMNOQTUVWY".indexOf(b) >= 0) {
                g += 2;
            } else {
                g++;
            }
            if(g <= a) {
                e += b;
            } else {
                if(d) {
                    return e;
                } else {
                    return e + "...";
                }
            }
        }
    }
    return e;
}
var __htmlConverter = document.createElement("div");
function htmlEncode(b, c) {
    if(!c) {
        c = __htmlConverter;
    }
    if(c.textContent != null) {
        c.textContent = b;
    } else {
        c.innerText = b;
    }
    var a = c.innerHTML;
    return a;
}
function htmlDecode(b, c) {
    if(!c) {
        c = __htmlConverter;
    }
    c.innerHTML = b;
    var a = "";
    if(c.textContent != null) {
        a = c.textContent;
    } else {
        a = c.innerText;
    }
    return a;
}
function getViewportHeight() {
    var a = 0;
    if(window.innerHeight) {
        a = window.innerHeight;
    } else {
        if(document.documentElement && document.documentElement.clientHeight) {
            a = document.documentElement.clientHeight;
        } else {
            if(document.body && document.body.clientHeight) {
                a = document.body.clientHeight;
            }
        }
    }
    return a;
}
function getViewportWidth() {
    var a = 0;
    if(window.innerWidth) {
        a = window.innerWidth;
    } else {
        if(document.documentElement && document.documentElement.clientWidth) {
            a = document.documentElement.clientWidth;
        } else {
            if(document.body && document.body.clientWidth) {
                a = document.body.clientWidth;
            }
        }
    }
    return a;
}
function getViewportCenter() {
    var a = getViewportHeight();
    var d = getViewportWidth();
    var b = {"x":d / 2,"y":a / 2};
    return b;
}
var __isMsie = $.browser.msie;
function isMouseOver(b, d) {
    d = window.event || d;
    var c = d.fromElement || d.relatedTarget;
    if(__isMsie && c) {
        if(b && c != b && b.contains && !b.contains(c)) {
            return true;
        }
    } else {
        if(c && b && b.compareDocumentPosition) {
            var a = b.compareDocumentPosition(c);
            if(!(c == b || a == 20 || a == 0)) {
                return true;
            }
        }
    }
    return false;
}
function isMouseOut(b, d) {
    var d = window.event || d;
    var c = d.toElement || d.relatedTarget;
    if(__isMsie && c) {
        if(b && b.contains && !b.contains(c)) {
            return true;
        }
    } else {
        if(c && b && b.compareDocumentPosition) {
            var a = b.compareDocumentPosition(c);
            if(!(a == 20 || a == 0)) {
                return true;
            }
        }
    }
    return false;
}
function getCookie(b) {
    var a = document.cookie.match(new RegExp("(^| )" + b + "=([^;]*)(;|$)"));
    if(a != null) {
        return unescape(a[2]);
    }
    return null;
}
function setCookie(b, d, a, f, c, e) {
    document.cookie = b + "=" + d + ((a) ? "; expires=" + a : "") + ((f) ? "; path=" + f : "") + ((c) ? "; domain=" + c : "") + ((e) ? "; secure" : "");
}
var __expires = (new Date(1970, 1, 1, 8, 0, 1)).toGMTString();
function deleteCookie(a, c, b) {
    if(getCookie(a)) {
        document.cookie = a + "=" + ((c) ? "; path=" + c : "") + ((b) ? "; domain=" + b : "") + "; expires=" + __expires;
    }
}
function jHashMap() {
    var a = 0;
    var b = new Object();
    this.put = function(c, d) {
        if(!this.containsKey(c)) {
            a++;
        }
        b[c] = d;
    };
    this.get = function(c) {
        return this.containsKey(c) ? b[c] : null;
    };
    this.remove = function(c) {
        if(this.containsKey(c) && (delete b[c])) {
            a--;
        }
    };
    this.containsKey = function(c) {
        return(c in b);
    };
    this.containsValue = function(c) {
        for(var d in b) {
            if(b[d] == c) {
                return true;
            }
        }
        return false;
    };
    this.values = function() {
        var c = new Array();
        for(var d in b) {
            c.push(b[d]);
        }
        return c;
    };
    this.keys = function() {
        var c = new Array();
        for(var d in b) {
            c.push(d);
        }
        return c;
    };
    this.size = function() {
        return a;
    };
    this.clear = function() {
        a = 0;
        b = new Object();
    };
}
function getQueryString(e, b) {
    var d = "";
    var c = new RegExp("[?&]?" + b + "=([^&]+)", "i");
    var a = e.match(c);
    if(a == null || a.length < 1) {
        d = "";
    } else {
        d = a[1];
    }
    return d;
}
function objects(h) {
    var g = new Object();
    var e = /[\?\&]?([^=^\&]+)=([^\&]+)/i;
    var c = h.split("&");
    for(var b = 0; b < c.length; b++) {
        var a = c[b].match(e);
        if(a == null) {
            continue;
        }
        var f = a.length;
        if(a != null) {
            for(var d = 1; d < f; d += 2) {
                if(d + 1 < f) {
                    g[a[d]] = a[d + 1];
                } else {
                    g[a[d]] = "";
                }
            }
        }
    }
    return g;
}
function addFavourite(url, title) {
    function findKeys() {
        var isMSIE = /*@cc_on!@*/
                false;
        var ua = navigator.userAgent.toLowerCase(),isMac = (ua.indexOf("mac") != -1),isWebkit = (ua.indexOf("webkit") != -1),str = (isMac ? "Command/Cmd" : "CTRL");
        if(window.opera && (!opera.version || (opera.version() < 9))) {
            str += " + T";
        } else {
            if(ua.indexOf("konqueror") != -1) {
                str += " + B";
            } else {
                if(window.opera || window.home || isWebkit || isMSIE || isMac) {
                    str += " + D";
                } else {
                    str += " + D";
                }
            }
        }
        return str;
    }
    try {
        if(document.all) {
            window.external.addFavorite(url, title);
        } else {
            if(window.sidebar) {
                window.sidebar.addPanel(title, url, "");
            } else {
                alert("浏览器不支持自动添加收藏夹。关闭本对话框后，请您手动使用组合快捷键'" + findKeys() + "'进行添加。");
            }
        }
    } catch(e) {
        alert("浏览器不支持自动添加收藏夹。关闭本对话框后，请您手动使用组合快捷键'" + findKeys() + "'进行添加。");
    }
}
function stopPropagation(a) {
    a = window.event || a;
    if(window.event) {
        a.cancelBubble = true;
    } else {
        a.stopPropagation();
    }
}
function preventDefault(a) {
    if(a && a.preventDefault) {
        a.preventDefault();
    } else {
        window.event.returnValue = false;
        return false;
    }
}
function replaceSelection(c, e) {
    if(c != null) {
        if(c.pasteHTML) {
            c.select();
            c.pasteHTML(e);
            return true;
        } else {
            if(c.deleteContents && c.insertNode) {
                var b = document.createElement("div");
                b.innerHTML = e;
                var a = [];
                for(var d = b.childNodes.length - 1; d >= 0; d--) {
                    a.push(b.childNodes[d]);
                }
                c.deleteContents();
                for(var d = 0,f = a.length; d < f; d++) {
                    c.insertNode(a[d]);
                }
                if(a.length > 0) {
                    c.setStartBefore(a[a.length - 1]);
                    c.setEndAfter(a[0]);
                }
                c.collapse(false);
                b = null;
                return true;
            }
        }
    }
    return false;
}
var __scrollBarWidth = null;
function getScrollBarWidth() {
    if(__scrollBarWidth) {
        return __scrollBarWidth;
    }
    var a = document.createElement("div");
    a.style.cssText = "overflow:scroll;width:100px;height:100px;";
    document.body.appendChild(a);
    if(a) {
        __scrollBarWidth = {horizontal:a.offsetHeight - a.clientHeight,vertical:a.offsetWidth - a.clientWidth};
    }
    document.body.removeChild(a);
    return __scrollBarWidth;
}
(function(a) {
    a.extend(a.fn, {loadingPanel:function(c) {
        var d = a.extend({parent:a(document.body),target:a(document.body),showTime:18 * 1000}, c);
        d.parent = ((typeof d.parent == "string") ? a("#" + d.parent) : d.parent);
        d.target = ((typeof d.target == "string") ? a("#" + d.target) : d.target);
        var b = a('<div class="fx_ajax" style="position:absolute;text-align:center;z-index:90000;display:none;"><div class="fxDiv"></div><div class="fxColor fxTransp"></div></div>');
        d.parent.append(b);
        b.hide = function() {
            b.css("display", "none");
        };
        b.show = function() {
            var e;
            if(d.parent.get(0) == document.body) {
                e = d.target.offset();
            } else {
                e = d.target.position();
            }
            b.css({"top":e.top,"left":e.left,"height":d.target.height(),"width":d.target.width(),"display":""});
            setTimeout(function() {
                b.hide();
            }, d.showTime);
        };
        return b;
    }});
})(jQuery);
function setClipboard(f) {
    if(window.clipboardData) {
        return(window.clipboardData.setData("Text", f));
    } else {
        if(window.netscape) {
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            var c = Components.classes["@mozilla.org/widget/clipboard;1"].createInstance(Components.interfaces.nsIClipboard);
            if(!c) {
                return;
            }
            var b = Components.classes["@mozilla.org/widget/transferable;1"].createInstance(Components.interfaces.nsITransferable);
            if(!b) {
                return;
            }
            b.addDataFlavor("text/unicode");
            var e = new Object();
            var a = new Object();
            var e = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
            var g = new String(f);
            e.data = g;
            b.setTransferData("text/unicode", e, g.length * 2);
            var d = Components.interfaces.nsIClipboard;
            if(!c) {
                return false;
            }
            c.setData(b, null, d.kGlobalClipboard);
            return true;
        }
    }
    return false;
}
function getClipboard() {
    if(window.clipboardData) {
        return(window.clipboardData.getData("Text"));
    } else {
        if(window.netscape) {
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            var d = Components.classes["@mozilla.org/widget/clipboard;1"].createInstance(Components.interfaces.nsIClipboard);
            if(!d) {
                return;
            }
            var c = Components.classes["@mozilla.org/widget/transferable;1"].createInstance(Components.interfaces.nsITransferable);
            if(!c) {
                return;
            }
            c.addDataFlavor("text/unicode");
            d.getData(c, d.kGlobalClipboard);
            var e = new Object();
            var a = new Object();
            try {
                c.getTransferData("text/unicode", e, a);
            } catch(b) {
                return null;
            }
            if(e) {
                if(Components.interfaces.nsISupportsWString) {
                    e = e.value.QueryInterface(Components.interfaces.nsISupportsWString);
                } else {
                    if(Components.interfaces.nsISupportsString) {
                        e = e.value.QueryInterface(Components.interfaces.nsISupportsString);
                    } else {
                        e = null;
                    }
                }
            }
            if(e) {
                return(e.data.substring(0, a.value / 2));
            }
        }
    }
    return null;
}
function osType() {
    var b = (navigator.userAgent || navigator.vendor || window.opera).toLowerCase();
    isWin7 = b.indexOf("nt 6.1") > -1;
    isVista = b.indexOf("nt 6.0") > -1;
    isWin2003 = b.indexOf("nt 5.2") > -1;
    isWinXp = b.indexOf("nt 5.1") > -1;
    isWin2000 = b.indexOf("nt 5.0") > -1;
    isWinNT40 = b.indexOf("nt 4.0") > -1;
    isWindows = (b.indexOf("windows") != -1 || b.indexOf("win32") != -1);
    isMac = (b.indexOf("macintosh") != -1 || b.indexOf("mac os x") != -1);
    isAndroid = (b.indexOf("android") != -1);
    isLinux = (b.indexOf("linux") != -1);
    var c = 0;
    var a = "";
    if(isWin7) {
        c = 500300002;
    } else {
        if(isVista) {
            c = 500300003;
        } else {
            if(isWinXp) {
                c = 500300001;
            } else {
                if(isWin2003) {
                    c = 500300005;
                } else {
                    if(isWin2000) {
                        c = 500300004;
                    } else {
                        if(isWinNT40) {
                            c = 500300006;
                        } else {
                            if(isMac) {
                                c = 500300008;
                            } else {
                                if(isLinux) {
                                    c = 500300007;
                                } else {
                                    if(isAndroid) {
                                        c = 500300009;
                                    } else {
                                        if(/ip(hone|od)/i.test(b)) {
                                            c = 500300012;
                                        } else {
                                            if(/symbian/i.test(b)) {
                                                c = 500300011;
                                            } else {
                                                if(/windows (ce|phone)/i.test(b)) {
                                                    c = 500300010;
                                                } else {
                                                    c = 500300013;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return c;
}
function getScreen() {
    var b = screen.width + "," + screen.height;
    var a = 0;
    switch(b) {case"1024,768":a = 500200001;break;case"1440,900":a = 500200002;break;case"800,600":a = 500200005;break;case"1366,768":a = 500200006;break;case"1280,800":a = 500200007;break;case"1280,1024":a = 500200008;break;case"1680,1050":a = 500200009;break;case"1152,864":a = 500200010;break;case"1280,960":a = 500200011;break;case"1280,768":a = 500200012;break;case"1920,1080":a = 500200013;break;case"640,480":a = 500200014;break;case"1360,768":a = 500200015;break;default:a = 500200016;break;}
    return a;
}
var numberReg = /^\d{8,11}$/;
var numReg = /^\d+$/;
var htmlReg = /<([^<>]+)>/gi;
var numOrWordReg = /^(\w|[\u4e00-\u9fa5])+$/;
var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
var isValidSid = /^\d{1,10}$/;
var isMobileNoReg = /^(134|135|136|137|138|139|147|150|151|152|157|158|159|182|183|187|188|130|131|132|155|156|185|186|133|153|180|189)[\d]{8}$/;
var isMobileNoRegForTXL = /^(\+86)?(134|135|136|137|138|139|147|150|151|152|157|158|159|182|183|187|188|130|131|132|155|156|185|186|133|153|180|189)[\d]{8}$/;
var isChinaMobileNoReg = /^(134|135|136|137|138|139|147|150|151|152|157|158|159|182|183|187|188)[\d]{8}$/;
var isUTMoblieNoReg = /^(130|131|132|155|156|185|186|133|153|180|189)[\d]{8}$/;
var isChinaUnicomNoReg = /^(130|131|132|155|156|185|186|)[\d]{8}$/;
var isChinaTelecomNoReg = /^(133|153|180|189)[\d]{8}$/;
var __version = 0;
var __dataReportMap = new jHashMap();
(function(a) {
    a.extend({request:{URL_SetCounter:"SetCounter.aspx?Version={0}&coutertype={1}&tag={2}&val={3}&rand={4}",version:__version},increaseCounter:function(e, c, f) {
        if(!c) {
            c = "default";
        }
        if(f == undefined) {
            f = 1;
        }
        var d = formatString(a.request.URL_SetCounter, a.request.version++, e, c, f, Math.random());
        var b = new Image();
        b.src = d;
    },increaseCounters:function(b, d) {
        if(d == undefined) {
            d = 1;
        }
        var e = __dataReportMap;
        if(e.containsKey(b)) {
            var c = e.get(b) + d;
            e.put(b, c);
        } else {
            e.put(b, d);
        }
    }});
})(jQuery);
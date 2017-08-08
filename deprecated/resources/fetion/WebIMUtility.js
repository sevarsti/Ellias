var __isSmsOnline = /^0+.0+:0+:0+$/;
function jCompactUri(d) {
    this.mobileNoOrSid = 0;
    this.isRobot = false;
    this.logicalPool = -1;
    this.isMobileNo = false;
    if(!d) {
        return;
    }
    if(d.startsWith("tel:")) {
        this.mobileNoOrSid = parseMobileNo(d.substring(4));
        this.isMobileNo = true;
    } else {
        if(d.startsWith("sip:")) {
            var a = d.indexOf("@");
            var f = d.indexOf(";p=");
            if(a < 0) {
                this.mobileNoOrSid = parseInt(d.substring(4));
            } else {
                this.mobileNoOrSid = parseInt(d.substr(4, a - 4));
                if(f > 0) {
                    var b = d.substring(f + 3);
                    var e = b.indexOf(";");
                    var c;
                    if(e < 0) {
                        c = b;
                    } else {
                        c = b.substr(0, e);
                        if(b.toLowerCase().indexOf("t=robot") >= 0) {
                            this.isRobot = true;
                        }
                    }
                    this.logicalPool = parseInt(c);
                } else {
                    _logicalPool = -1;
                }
            }
        }
    }
}
function jGroupUriToId(b) {
    var a = b.replace("sip:PG", "");
    return a.substr(0, a.indexOf("@"));
}
function jMemUriToId(b) {
    var a = b.replace("sip:", "");
    return a.substr(0, a.indexOf("@"));
}
function parseMobileNo(b) {
    var a = 0;
    if(b.startsWith("-")) {
        throw new Error("Unexcepted MobileNo: " + b);
    }
    if(b.startsWith("+86")) {
        a = parseInt(b.substring(3));
    } else {
        if(b.startsWith("0086")) {
            a = parseInt(b.substring(4));
        } else {
            if(b.startsWith("86")) {
                a = parseInt(b.substring(2));
            } else {
                if(b.startsWith("852")) {
                    a = parseInt("-900" + b);
                } else {
                    if(b.startsWith("00")) {
                        a = parseInt("-9" + b);
                    } else {
                        if(b.startsWith("65")) {
                            a = parseInt("-900" + b);
                        } else {
                            a = parseInt(b);
                        }
                    }
                }
            }
        }
    }
    return a;
}
function getBuddyOrder(a) {
    if(!a) {
        return -1;
    }
    var c = 0;
    var b = parseInt(a.pb);
    if(a.rs == 1) {
        if(b == 0) {
            if((a.isCM && __isSmsOnline.test(a.sms) || (a.isCT || a.isCU))) {
                if(a.isVip) {
                    c = 7;
                } else {
                    c = (a.ct == 1) ? 9 : 8;
                }
            } else {
                if(a.isVip) {
                    c = 10;
                } else {
                    c = (a.ct == 1) ? 9 : 11;
                }
            }
        } else {
            if(b > 0) {
                c = (a.isVip ? 1 : 2);
            }
        }
    } else {
        if(a.rs == 2) {
            if(a.ct == 1) {
                c = 19;
            } else {
                c = (a.isVip) ? 17 : 18;
            }
        } else {
            if(a.rs == 0) {
                if(a.ct == 2) {
                    if(b > 0) {
                        c = a.isVip ? 12 : 13;
                    } else {
                        c = a.isVip ? 23 : 24;
                    }
                } else {
                    if(a.ct == 1) {
                        c = 16;
                    } else {
                        c = a.isVip ? 14 : 15;
                    }
                }
            } else {
                if(b > 0) {
                    c = a.isVip ? 12 : 13;
                } else {
                    c = a.isVip ? 23 : 24;
                }
            }
        }
    }
    if(a.bss == "0" && a.ct != 1) {
        c = 25;
    }
    return c;
}
function buddyComparator(e, d) {
    if(!e && !d) {
        return 0;
    }
    if(e && !d) {
        return 1;
    }
    if(!e && d) {
        return -1;
    }
    var c,b;
    if(e.orderNo) {
        c = e.orderNo;
    } else {
        c = getBuddyOrder(e);
    }
    if(d.orderNo) {
        b = d.orderNo;
    } else {
        b = getBuddyOrder(d);
    }
    if(c == b) {
        var a = (e.displayName).localeCompare(d.displayName);
        if(a == 0 && e.uri && d.uri) {
            return(e.uri).localeCompare(d.uri);
        }
        return a;
    }
    return c - b;
}
function buddyListComparator(b, d) {
    if(b.id == d.id) {
        return 0;
    }
    if(b.id == 0) {
        return -1;
    }
    if(d.id == 0) {
        return 1;
    }
    if(b.id == -1) {
        return 1;
    }
    if(d.id == -1) {
        return -1;
    }
    var a = b.n;
    var c = d.n;
    return a.localeCompare(c);
}
var ContactType = {Unknown:0,FetionBuddy:1,MobileBuddy:2,ChatFriend:4,Robot:8};
function getContactType(a) {
    var b = ContactType.Unknown;
    switch(a.ct) {case -1:b = ContactType.Unknown;break;case 0:b = ContactType.FetionBuddy;break;case 1:b = ContactType.MobileBuddy;break;case 2:b = ContactType.ChatFriend;break;default:break;}
    if(a.ca && a.ca.toLocaleUpperCase() == "ROBOT") {
        b = ContactType.Robot;
    }
    return b;
}
var ContactStatus = {Unknown:0,Online:1,SmsOnline:2,Offline:4,BlockList:8,Busy:16,Away:32,PC:64,Mobile:128,Verify:256,Refuse:512,CloseService:1024,PCOnline:64 | 1,PCBusy:64 | 16,PCAway:64 | 32,MobileOnline:128 | 1,MobileBusy:128 | 16,MobileAway:128 | 32,PCOnline_Bklist:64 | 1 | 8,PCBusy_Bklist:64 | 16 | 8,PCAway_Bklist:64 | 32 | 8,MobileOnline_Bklist:128 | 1 | 8,MobileBusy_Bklist:128 | 16 | 8,MobileAway_Bklist:128 | 32 | 8,SmsOnline_Bklist:2 | 8,Offline_Bklist:4 | 8,Verify_Bklist:256 | 8,Refuse_Bklist:512 | 8,CloseService_Bklist:1024 | 8};
function isOnlineContact(a) {
    var b = a.status;
    if((b & ContactStatus.Online) > 0 || (b & ContactStatus.Busy) > 0 || (b & ContactStatus.Away) > 0) {
        return true;
    }
    return false;
}
function getContactStatus(b) {
    if(!b) {
        return ContactStatus.Unknown;
    }
    var c = ContactStatus.Unknown;
    if(b.rs == 1 && b.ct == 0) {
        if(b.bss == "0" && !b.mn) {
            c = ContactStatus.CloseService;
            if(b.isBk == 1) {
                c |= ContactStatus.BlockList;
            }
        } else {
            var a = parseInt(b.pb);
            if(a == 0) {
                if(__isSmsOnline.test(b.sms)) {
                    if(b.isBk == 1) {
                        c = ContactStatus.SmsOnline_Bklist;
                    } else {
                        c = ContactStatus.SmsOnline;
                    }
                } else {
                    if(b.isBk == 1) {
                        c = ContactStatus.Offline_Bklist;
                    } else {
                        c = ContactStatus.Offline;
                        if(b.ca.toLowerCase() == "ct" || b.ca.toLowerCase() == "cucc") {
                            c = ContactStatus.SmsOnline;
                        }
                    }
                }
            } else {
                if(a > 0) {
                    if(a >= 400 && a < 500) {
                        c = ContactStatus.Online;
                    } else {
                        if(a < 400) {
                            c = ContactStatus.Away;
                        } else {
                            if(a >= 500) {
                                c = ContactStatus.Busy;
                            }
                        }
                    }
                    if(b.isBk == 1) {
                        c |= ContactStatus.BlockList;
                    }
                    if(b.dt == "PC" || b.dt == "WEB" || b.dt == "Robot") {
                        c |= ContactStatus.PC;
                    } else {
                        c |= ContactStatus.Mobile;
                    }
                }
            }
        }
    } else {
        if(b.rs == 1 && b.ct == 1) {
            if(b.isBk == 1) {
                c = ContactStatus.SmsOnline_Bklist;
            } else {
                if(b.cas == 2) {
                    c = ContactStatus.Offline;
                } else {
                    c = ContactStatus.SmsOnline;
                }
            }
        } else {
            if(b.rs == 0 && b.ct != 2) {
                if(b.isBk == 1) {
                    c = ContactStatus.Verify_Bklist;
                } else {
                    c = ContactStatus.Verify;
                }
            } else {
                if(b.rs == 2 && b.ct != 2) {
                    if(b.isBk == 1) {
                        c = ContactStatus.Refuse_Bklist;
                    } else {
                        c = ContactStatus.Refuse;
                    }
                } else {
                    if(b.ct == 2) {
                        var a = parseInt(b.pb);
                        if(a > 0) {
                            c = ContactStatus.Online;
                        } else {
                            c = ContactStatus.Offline;
                        }
                        if(b.isBk == 1) {
                            c |= ContactStatus.BlockList;
                        }
                        if(b.bss == "0") {
                            c |= ContactStatus.CloseService;
                        }
                    }
                }
            }
        }
    }
    return c;
}
var ClassNameSuffixes = new jHashMap();
ClassNameSuffixes.put(ContactStatus.Unknown, "offline");
ClassNameSuffixes.put(ContactStatus.Online, "pc-online");
ClassNameSuffixes.put(ContactStatus.SmsOnline, "msg-online");
ClassNameSuffixes.put(ContactStatus.Offline, "offline");
ClassNameSuffixes.put(ContactStatus.BlockList, "pc-bklist");
ClassNameSuffixes.put(ContactStatus.Busy, "pc-busy");
ClassNameSuffixes.put(ContactStatus.Away, "pc-away");
ClassNameSuffixes.put(ContactStatus.Verify, "verify");
ClassNameSuffixes.put(ContactStatus.Verify_Bklist, "verify_bklist");
ClassNameSuffixes.put(ContactStatus.Refuse, "refuse");
ClassNameSuffixes.put(ContactStatus.Refuse_Bklist, "refuse_bklist");
ClassNameSuffixes.put(ContactStatus.CloseService, "close");
ClassNameSuffixes.put(ContactStatus.CloseService_Bklist, "close_bklist");
ClassNameSuffixes.put(ContactStatus.PCOnline, "pc-online");
ClassNameSuffixes.put(ContactStatus.PCBusy, "pc-busy");
ClassNameSuffixes.put(ContactStatus.PCAway, "pc-away");
ClassNameSuffixes.put(ContactStatus.MobileOnline, "mb-online");
ClassNameSuffixes.put(ContactStatus.MobileBusy, "mb-busy");
ClassNameSuffixes.put(ContactStatus.MobileAway, "mb-away");
ClassNameSuffixes.put(ContactStatus.PCOnline_Bklist, "pc-bklist");
ClassNameSuffixes.put(ContactStatus.PCBusy_Bklist, "pc-bklist");
ClassNameSuffixes.put(ContactStatus.PCAway_Bklist, "pc-bklist");
ClassNameSuffixes.put(ContactStatus.MobileOnline_Bklist, "mb-bklist");
ClassNameSuffixes.put(ContactStatus.MobileBusy_Bklist, "mb-bklist");
ClassNameSuffixes.put(ContactStatus.MobileAway_Bklist, "mb-bklist");
ClassNameSuffixes.put(ContactStatus.SmsOnline_Bklist, "msg-bklist");
ClassNameSuffixes.put(ContactStatus.Offline_Bklist, "offline-bklist");
var StatusDescriptions = new jHashMap();
function getContactClassName(a) {
    var d = a.type,b = a.status;
    if(d == ContactType.ChatFriend) {
        if((b & ContactStatus.BlockList) == ContactStatus.BlockList) {
            return"st-pc-bklist";
        } else {
            if((b & ContactStatus.CloseService) == ContactStatus.CloseService || (b & ContactStatus.Offline) == ContactStatus.Offline) {
                return"offline";
            } else {
                return"st-pc-online";
            }
        }
    }
    if(d != ContactType.ChatFriend && (b & ContactStatus.CloseService) == ContactStatus.CloseService) {
        if((b & ContactStatus.BlockList) == ContactStatus.BlockList) {
            return"offline-bklist";
        } else {
            return"close";
        }
    }
    var e = ClassNameSuffixes.get(b);
    if(!e) {
        return"offline";
    }
    if(b == ContactStatus.Unknown || b == ContactStatus.Verify || b == ContactStatus.Verify_Bklist || b == ContactStatus.Refuse || b == ContactStatus.Refuse_Bklist || (b & ContactStatus.Offline) == ContactStatus.Offline) {
        if(d != ContactType.Robot) {
            return e;
        }
    }
    var c = "";
    switch(d) {case ContactType.FetionBuddy:if(a.isMobile) {
        c = "ff-" + e;
    } else {
        c = "ncm-" + e;
    }break;case ContactType.MobileBuddy:if(a.isMobile) {
        c = "mf-" + e;
    } else {
        c = "ncm-" + e;
    }break;case ContactType.ChatFriend:c = "st-" + e;break;case ContactType.Robot:c = "rb-" + e;break;}
    return c;
}
function getStatusDesc(a) {
    var c = a.type,b = a.status;
    if(c == ContactType.ChatFriend) {
        return"";
    }
    if((b & ContactStatus.Online) == ContactStatus.Online) {
        return"在线";
    }
    if((b & ContactStatus.SmsOnline) == ContactStatus.SmsOnline) {
        return"短信在线";
    }
    if((b & ContactStatus.Offline) == ContactStatus.Offline) {
        return"离线";
    }
    if((b & ContactStatus.Busy) == ContactStatus.Busy) {
        return"忙碌";
    }
    if((b & ContactStatus.Away) == ContactStatus.Away) {
        return"离开";
    }
    if((b & ContactStatus.Verify) == ContactStatus.Verify || (b & ContactStatus.Verify_Bklist) == ContactStatus.Verify_Bklist) {
        return"(等待对方同意添加为好友)";
    }
    if((b & ContactStatus.Refuse) == ContactStatus.Refuse || (b & ContactStatus.Refuse_Bklist) == ContactStatus.Verify_Bklist) {
        return"(对方拒绝)";
    }
    if((b & ContactStatus.CloseService) == ContactStatus.CloseService) {
        return"飞信服务已关闭";
    }
    return"";
}
var __entityList = new Array();
__entityList["<"] = "&lt;";
__entityList[">"] = "&gt;";
__entityList["&"] = "&amp;";
function EntityReplace(g) {
    var b = "";
    if(g != null) {
        var a = g.length;
        var d = 0;
        while(d < a) {
            var c = d;
            var f = __entityList[g.charAt(c)];
            while(c < a && null == f) {
                c++;
                f = __entityList[g.charAt(c)];
            }
            b += g.substr(d, c - d);
            if(f != null) {
                b += f;
                c++;
            }
            d = c;
        }
    }
    return b;
}
function EmoticonInfo(f, d, c, b, a, e) {
    this.IsLeaf = true;
    this.EmoticonToHtml = function(g) {
        var h = "";
        if(!g) {
            h = "images/emoticons/" + f + "." + e;
        } else {
            h = "images/emoticons/" + f + "." + a;
        }
        return"<img src='" + h + "' alt='' />";
    };
    this.Length = function() {
        return c;
    };
}
function Emoticons() {
    var g = new Array();
    var a = new Object();
    function f(j, n, l, k) {
        var m = l.charAt(k);
        if(k < l.length - 1) {
            if(j[m] == null) {
                j[m] = new Array();
            }
            f(j[m], n, l, k + 1);
        } else {
            j[m] = n;
        }
    }
    function b(k, j) {
        if(k in a) {
            return;
        }
        a[k] = j;
    }
    function c(l, r) {
        var j = arguments[0];
        var q = arguments[1];
        var p = arguments[2];
        var o = arguments[3];
        var k = arguments[4];
        var m = EntityReplace(q);
        var n = new EmoticonInfo(j, q, m.length, p, o, k);
        f(g, n, m, 0);
        b(j, m);
    }
    function e() {
        c("cake", "(^)", "", "png", "gif");
        c("cat", "(@)", "", "png", "gif");
        c("cat", "（@）", "", "png", "gif");
        c("cat", "（＠）", "", "png", "gif");
        c("clock", "(o)", "", "png", "gif");
        c("clock", "(O)", "", "png", "gif");
        c("clock", "(0)", "", "png", "gif");
        c("clock", "（o）", "", "png", "gif");
        c("clock", "（ｏ）", "", "png", "gif");
        c("clock", "（Ｏ）", "", "png", "gif");
        c("clock", "（O）", "", "png", "gif");
        c("clock", "(0)", "", "png", "gif");
        c("clock", "（0）", "", "png", "gif");
        c("clock", "（０）", "", "png", "gif");
        c("cloud_rain", "(st)", "", "png", "gif");
        c("cloud_rain", "(ST)", "", "png", "gif");
        c("cloud_rain", "（st）", "", "png", "gif");
        c("cloud_rain", "（ｓｔ）", "", "png", "gif");
        c("cloud_rain", "（ＳＴ）", "", "png", "gif");
        c("cloud_rain", "（ST）", "", "png", "gif");
        c("drink", ")-|", "", "png", "gif");
        c("coffee", "(c)", "", "png", "gif");
        c("coffee", "(c)", "", "png", "gif");
        c("coffee", "（c）", "", "png", "gif");
        c("coffee", "（ｃ）", "", "png", "gif");
        c("coffee", "（Ｃ）", "", "png", "gif");
        c("coffee", "（c）", "", "png", "gif");
        c("computer", "(co)", "", "png", "gif");
        c("computer", "(CO)", "", "png", "gif");
        c("computer", "（co）", "", "png", "gif");
        c("computer", "（ｃｏ）", "", "png", "gif");
        c("computer", "（ＣＯ）", "", "png", "gif");
        c("computer", "（CO）", "", "png", "gif");
        c("dog", "(&)", "", "png", "gif");
        c("dog", "（＆）", "", "png", "gif");
        c("dog", "（&）", "", "png", "gif");
        c("heart", "(L)", "", "png", "gif");
        c("heart", "(l)", "", "png", "gif");
        c("heart", "（l）", "", "png", "gif");
        c("heart", "（ｌ）", "", "png", "gif");
        c("heart", "（Ｌ）", "", "png", "gif");
        c("heart", "（L）", "", "png", "gif");
        c("heart_broken", "(U)", "", "png", "gif");
        c("heart_broken", "(u)", "", "png", "gif");
        c("heart_broken", "（u）", "", "png", "gif");
        c("heart_broken", "（ｕ）", "", "png", "gif");
        c("heart_broken", "（Ｕ）", "", "png", "gif");
        c("heart_broken", "（U）", "", "png", "gif");
        c("hug_dude", "(})", "", "png", "gif");
        c("hug_dude", "（}）", "", "png", "gif");
        c("hug_dude", "（｝）", "", "png", "gif");
        c("hug_girl", "({)", "", "png", "gif");
        c("hug_girl", "（{）", "", "png", "gif");
        c("hug_girl", "（｛）", "", "png", "gif");
        c("kiss", "(k)", "", "png", "gif");
        c("kiss", "(K)", "", "png", "gif");
        c("kiss", "（k）", "", "png", "gif");
        c("kiss", "（ｋ）", "", "png", "gif");
        c("kiss", "（Ｋ）", "", "png", "gif");
        c("kiss", "（K）", "", "png", "gif");
        c("lightbulb", "(i)", "", "png", "gif");
        c("lightbulb", "(I)", "", "png", "gif");
        c("lightbulb", "（i）", "", "png", "gif");
        c("lightbulb", "（ｉ）", "", "png", "gif");
        c("lightbulb", "（Ｉ）", "", "png", "gif");
        c("lightbulb", "（I）", "", "png", "gif");
        c("martini", "(d)", "", "png", "gif");
        c("martini", "(D)", "", "png", "gif");
        c("martini", "（d）", "", "png", "gif");
        c("martini", "（ｄ）", "", "png", "gif");
        c("martini", "（Ｄ）", "", "png", "gif");
        c("martini", "（D）", "", "png", "gif");
        c("mobile", "(mp)", "", "png", "gif");
        c("mobile", "(MP)", "", "png", "gif");
        c("mobile", "（mp）", "", "png", "gif");
        c("mobile", "（ｍｐ）", "", "png", "gif");
        c("mobile", "（ＭＰ）", "", "png", "gif");
        c("mobile", "（MP）", "", "png", "gif");
        c("moon", "(S)", "", "png", "gif");
        c("moon", "(s)", "", "png", "gif");
        c("moon", "（s）", "", "png", "gif");
        c("moon", "（ｓ）", "", "png", "gif");
        c("moon", "（Ｓ）", "", "png", "gif");
        c("moon", "（S）", "", "png", "gif");
        c("music_note", "(8)", "", "png", "gif");
        c("music_note", "-8", "", "png", "gif");
        c("music_note", "（8）", "", "png", "gif");
        c("music_note", "（８）", "", "png", "gif");
        c("present", "(g)", "", "png", "gif");
        c("present", "(G)", "", "png", "gif");
        c("present", "（g）", "", "png", "gif");
        c("present", "（ｇ）", "", "png", "gif");
        c("present", "（Ｇ）", "", "png", "gif");
        c("present", "（G）", "", "png", "gif");
        c("rainbow", "(r)", "", "png", "gif");
        c("rainbow", "(R)", "", "png", "gif");
        c("rainbow", "（r）", "", "png", "gif");
        c("rainbow", "（ｒ）", "", "png", "gif");
        c("rainbow", "（Ｒ）", "", "png", "gif");
        c("rainbow", "（R）", "", "png", "gif");
        c("rose", "(f)", "", "png", "gif");
        c("rose", "(F)", "", "png", "gif");
        c("rose", "（f）", "", "png", "gif");
        c("rose", "（ｆ）", "", "png", "gif");
        c("rose", "（Ｆ）", "", "png", "gif");
        c("rose", "（F）", "", "png", "gif");
        c("rose_wilted", "(w)", "", "png", "gif");
        c("rose_wilted", "(W)", "", "png", "gif");
        c("smile_angry", ":@", "", "png", "gif");
        c("smile_angry", ":-@", "", "png", "gif");
        c("smile_baringteeth", "8o|", "", "png", "gif");
        c("smile_baringteeth", "8O", "", "png", "gif");
        c("smile_cry", ":’(", "", "png", "gif");
        c("smile_cry", ":'(", "", "png", "gif");
        c("smile_embaressed", ':">', "尴尬", "png", "gif");
        c("smile_embaressed", ":$", "", "png", "gif");
        c("smile_embaressed", ":-$", "", "png", "gif");
        c("smile_embaressed", ':"-)', "", "png", "gif");
        c("smile_eyeroll", "8-)", "", "png", "gif");
        c("smile_omg", ":-O", "", "png", "gif");
        c("smile_omg", ":-o", "", "png", "gif");
        c("smile_omg", ":O", "", "png", "gif");
        c("smile_omg", ":o", "", "png", "gif");
        c("smile_regular", ":)", "", "png", "gif");
        c("smile_regular", ":-)", "", "png", "gif");
        c("smile_sad", ":(", "", "png", "gif");
        c("smile_sad", ":-(", "", "png", "gif");
        c("smile_sad", ":<", "", "png", "gif");
        c("smile_sad", ":-<", "", "png", "gif");
        c("smile_sarcastic", "^o)", "", "png", "gif");
        c("smile_shades", "(h)", "", "png", "gif");
        c("smile_shades", "(H)", "", "png", "gif");
        c("smile_sick", "+o(", "", "png", "gif");
        c("smile_sick", ":&", "", "png", "gif");
        c("smile_sick", "+&", "", "png", "gif");
        c("smile_teeth", ":D", "", "png", "gif");
        c("smile_teeth", ":-D", "", "png", "gif");
        c("smile_teeth", ":d", "", "png", "gif");
        c("smile_teeth", ":-d", "", "png", "gif");
        c("smile_tongue", ":p", "", "png", "gif");
        c("smile_tongue", ":-p", "", "png", "gif");
        c("smile_tongue", ":P", "", "png", "gif");
        c("smile_tongue", ":-P", "", "png", "gif");
        c("smile_whatchutalkingabout", ":|", "", "png", "gif");
        c("smile_whatchutalkingabout", ":-|", "", "png", "gif");
        c("smile_wink", ";-)", "", "png", "gif");
        c("smile_wink", ";)", "", "png", "gif");
        c("smile_yawn", "|-)", "", "png", "gif");
        c("smile_yawn", "|o", "", "png", "gif");
        c("smile_yawn", "|O", "", "png", "gif");
        c("smile_zipit", ":-#", "", "png", "gif");
        c("snail", "(sn)", "", "png", "gif");
        c("snail", "(SN)", "", "png", "gif");
        c("star", "(*)", "", "png", "gif");
        c("sun", "(#)", "", "png", "gif");
        c("thumbs_up", "(y)", "", "png", "gif");
        c("thumbs_up", "(Y)", "", "png", "gif");
        c("umbrella", "(um)", "", "png", "gif");
        c("umbrella", "(UM)", "", "png", "gif");
        c("smile_puzzle", ":-/", "", "png", "gif");
        c("smile_worried", ":-S", "", "png", "gif");
        c("smile_worried", ":-s", "", "png", "gif");
        c("smile_thinking", ":-?", "", "png", "gif");
        c("smile_quitSmile", "*-:)", "", "png", "gif");
        c("smile_bigSmile", "B)", "", "png", "gif");
        c("smile_bigSmile", "b)", "", "png", "gif");
        c("smile_angle", "o:)", "", "png", "gif");
        c("smile_angle", "O:)", "", "png", "gif");
        c("smile_angle", "0:)", "", "png", "gif");
        c("smile_fool", ":-B", "", "png", "gif");
        c("smile_fool", ":-b", "", "png", "gif");
        c("smile_archness", ">:)", "", "png", "gif");
        c("smile_idea", "*-:)", "", "png", "gif");
        c("smile_idea", "*:)", "", "png", "gif");
        c("smile_confused", ":-/", "", "png", "gif");
        c("smile_confused", "：/", "", "png", "gif");
        c("smile_worried", ":s", "", "png", "gif");
        c("smile_worried", ":S", "", "png", "gif");
        c("smile_drink", ")-|", "", "png", "gif");
        c("smile_drink", "）-|", "", "png", "gif");
        c("smile_thinking", ":?", "", "png", "gif");
        c("smile_thinking", "：？", "", "png", "gif");
        c("smile_great", "(y)", "", "png", "gif");
        c("smile_great", "(Y)", "", "png", "gif");
        c("smile_naughty", ">:)", "", "png", "gif");
        c("smile_naughty", "〉：）", "", "png", "gif");
        c("smile_fool", ":b", "", "png", "gif");
        c("smile_fool", ":B", "", "png", "gif");
        c("smile_cool", "b)", "", "png", "gif");
        c("smile_cool", "B)", "", "png", "gif");
        c("smile_cool", "(b)", "", "png", "gif");
    }
    function i(k) {
        if(k.charCodeAt != null && String.fromCharCode != null) {
            var j = k.charCodeAt(0);
            if(j >= 65281 && j < 65374) {
                k = String.fromCharCode(j - 65248);
            }
        }
        return k;
    }
    function d(j, m, l) {
        if(j == null) {
            return null;
        } else {
            if(j.IsLeaf) {
                return j;
            } else {
                if(l >= m.length || l < 0) {
                    return null;
                } else {
                    if(typeof j == "object") {
                        var k = m.charAt(l);
                        k = i(k);
                        return d(j[k], m, l + 1);
                    }
                }
            }
        }
        return null;
    }
    function h(o, l) {
        if("&" != o.charAt(l)) {
            return 1;
        }
        var j = 0;
        var n = false;
        while(l < o.length && "&" == o.charAt(l)) {
            n = false;
            for(var m in __entityList) {
                var k = __entityList[m];
                if(l + k.length <= o.length && k == o.substr(l, k.length)) {
                    j += k.length;
                    l += j;
                    n = true;
                    break;
                }
            }
            if(!n) {
                return 1;
            }
        }
        return j;
    }
    this.Replace = function(q, m) {
        var l = "";
        if(q != null) {
            var o = 0;
            var k = q.length;
            while(o < k) {
                var n = o;
                var p = d(g, q, n);
                while(n < k && null == p) {
                    n += h(q, n);
                    p = d(g, q, n);
                }
                l += q.substr(o, n - o);
                if(p != null) {
                    l += p.EmoticonToHtml(m);
                    n += p.Length();
                }
                o = n;
            }
        }
        return l;
    };
    this.GetKey = function(j) {
        if(j in a) {
            return a[j];
        } else {
            return"";
        }
    };
    e();
}
var __globalEmoticons = new Emoticons();
function EmoticonReplace(b, a) {
    return __globalEmoticons.Replace(b, a);
}
function EmotionNonhtmlReplace(b, c, a) {
    if(c >= 0) {
        b = cnSubstr(b, c);
    }
    b = htmlEncode(b);
    return b;
}
function EmotionNonhtmlReplace1(b, c, a) {
    if(c >= 0) {
        b = cnSubstr(b, c);
    }
    b = htmlEncode(b);
    return b;
}
function GetEmotionKey(a) {
    return __globalEmoticons.GetKey(a);
}
function activeRemindMsgCookie() {
    var b = getCookie("webim_remindmsgs");
    if(b) {
        var a = new Date();
        a.setMinutes(a.getMinutes() + 2);
        setCookie("webim_remindmsgs", escape(b), a.toGMTString(), "/", __callmeDomain);
    }
}
function deleteRemindMsgCookie(b) {
    var d = getCookie("webim_remindmsgs");
    var a = new Date();
    a.setMinutes(a.getMinutes() + 2);
    if(d != null && d != "") {
        d = "," + d;
        var c = new RegExp("," + b + "-(.*?)" + "-([0-9]+)" + "-([^,]*)", "ig");
        if(c.test(d)) {
            d = d.replace(c, function() {
                return"";
            });
        }
        d = d.trim(",");
        setCookie("webim_remindmsgs", escape(d), a.toGMTString(), "/", __callmeDomain);
    }
}
function setRemindMsgCookie(b, g, e, d) {
    var f = getCookie("webim_remindmsgs");
    var a = new Date();
    a.setMinutes(a.getMinutes() + 2);
    if(f == null || f == "") {
        f = b + "-" + (g != null ? g.toCodeArray().join("%") : "") + "-" + (e != null ? e : "0") + "-" + (d != null ? getGuidFormSessionid(d) : "");
        setCookie("webim_remindmsgs", escape(f), a.toGMTString(), "/", __callmeDomain);
    } else {
        f = "," + f;
        var c = new RegExp("," + b + "-(.*?)" + "-([0-9]+)" + "-([^,]*)", "ig");
        if(c.test(f)) {
            f = f.replace(c, function(i, h, k, j) {
                return"," + b + "-" + (g != null ? g.toCodeArray().join("%") : h) + "-" + (e != null ? e : k) + "-" + (d != null ? getGuidFormSessionid(d) : "");
            });
        } else {
            f += "," + b + "-" + (g != null ? g.toCodeArray().join("%") : "") + "-" + (e != null ? e : "0") + "-" + (d != null ? getGuidFormSessionid(d) : "");
        }
        f = f.trim(",");
        setCookie("webim_remindmsgs", escape(f), a.toGMTString(), "/", __callmeDomain);
    }
}
function getGuidFormSessionid(b) {
    if(!b) {
        return"";
    } else {
        var a = b.substr(b.indexOf("-") + 1).replace(/-/g, "");
        return a;
    }
}
function getUserPermission(b, c) {
    b = ";" + b + ";";
    var d = new RegExp(";" + c + "=([0-9]*);", "i");
    var a = b.match(d);
    if(a != null) {
        return a[1];
    }
    return null;
}
function ContactDefault() {
    this.uid = 0;
    this.uri = "";
    this.bl = "";
    this.ln = "";
    this.rs = -1;
    this.ct = -1;
    this.isBk = -1;
    this.p = "";
    this.sid = 0;
    this.mn = "";
    this.ca = "";
    this.cas = "";
    this.bss = "";
    this.slv = "";
    this.s = "";
    this.nn = "";
    this.i = "";
    this.crc = "";
    this.sms = "";
    this.pb = "";
    this.pd = "";
    this.dt = "";
    this.dc = "";
    this.lastIsOc = false;
    this.currIsOc = false;
    this.is = "";
    this.pis = "";
}
function UserDefault() {
    this.c = "";
    this.v = "";
    this.uid = 0;
    this.sid = 0;
    this.mn = "";
    this.em = "";
    this.uri = "";
    this.nn = "";
    this.gd = -1;
    this.i = "";
    this.crc = "";
    this.bd = "";
    this.bdv = -1;
    this.ca = "";
    this.cas = "";
    this.car = "";
    this.ur = "";
    this.prof = "";
    this.bt = "";
    this.oc = "";
    this.pe = "";
    this.we = "";
    this.ebs = -1;
    this.sv = "";
    this.lv = "";
    this.lvs = "";
    this.gp = "";
    this.sms = "";
    this.creds = [];
    this.sers = "";
}
function PhoneDefault() {
    this.FN = "";
    this.GUID = "";
    this.HMobile = "";
    this.HMobile = "";
    this.OMobile = "";
    this.OPhone = "";
    this.Source = "";
}
function extendUser() {
    __user.displayName = __user.nn || __user.sid.toString();
    if(!__user.ca || __user.ca == "NIL") {
        __user.isMobile = false;
    } else {
        __user.isMobile = true;
        if(__user.ca == "CMCC") {
            __user.isCM = true;
        } else {
            if(__user.ca == "CT") {
                __user.isCT = true;
            } else {
                if(__user.ca == "CUCC") {
                    __user.isCU = true;
                }
            }
        }
    }
    if(!__user.sers) {
        __user.isVip = false;
    } else {
        var a = "," + __user.sers + ",";
        __user.isVip = (a.indexOf(",50,") >= 0 || a.indexOf(",51,") >= 0 || a.indexOf(",52,") >= 0);
    }
}
function extendContact(a) {
    if(!a.pb) {
        a.pb = "0";
    }
    if(!a.sms) {
        a.sms == "1.1:1:1";
    }
    if(!a.bss) {
        a.bss = "1";
    }
    if(a.bl) {
        a.bl = a.bl.trim("; ");
    }
    if(!a.bl) {
        a.bl = (a.ct == 2) ? "-1" : "0";
    }
    if(2 == a.cas) {
        a.mn = "";
    }
    a.compactUri = a.uri ? (new jCompactUri(a.uri)) : undefined;
    if(2 != a.ct && 1 == a.rs && a.compactUri) {
        a.ct = a.compactUri.isMobileNo ? 1 : 0;
    }
    if(a.ct == 1) {
        a.displayName = a.ln || a.mn;
    } else {
        a.displayName = "";
        a.displayName = a.ln || a.nn || (a.compactUri && a.compactUri.mobileNoOrSid.toString());
    }
    if(!a.s) {
        a.isVip = false;
    } else {
        var b = "," + a.s + ",";
        a.isVip = (b.indexOf(",50,") >= 0 || b.indexOf(",51,") >= 0 || b.indexOf(",52,") >= 0);
    }
    a.isMobile = !!(a.ca && a.ca != "NIL");
    a.isCM = !!(a.ca == "CMCC");
    a.isCT = !!(a.ca == "CT");
    a.isCU = !!(a.ca == "CUCC");
    a.type = getContactType(a);
    a.status = getContactStatus(a);
    a.statusClass = getContactClassName(a);
    a.orderNo = getBuddyOrder(a);
    a.lastIsOc = a.currIsOc;
    a.currIsOc = isOnlineContact(a);
}
function eachBuddyList(a, f, d) {
    if(!a.bl) {
        return;
    }
    var e = a.bl.split(";");
    var b = e.length;
    for(var c = 0; c < b; c++) {
        if("0" == e[c] && b > 1 && !d) {
            continue;
        }
        if(typeof f == "function") {
            if(false == f(e[c])) {
                break;
            }
        }
    }
}
function getGroupItemPortraitUrl(b, a) {
    a = a || "4";
    if(b == null) {
        if(a == 1) {
            return portrait.fetion_16;
        } else {
            if(a == 2) {
                return portrait.fetion_32;
            } else {
                return portrait.defaultIcon;
            }
        }
    } else {
        if(b.crc && b.crc !== "0") {
            return formatString(getGroupPortraitUrl, b.gUri, a, b.crc, __user.uid);
        } else {
            if(a == 1) {
                return portrait.fetion_16;
            } else {
                if(a == 2) {
                    return portrait.fetion_32;
                } else {
                    return portrait.defaultIcon;
                }
            }
        }
    }
}
function getUserPortraitUrl(a, b) {
    b = b || "4";
    if((a.crc && a.crc != "0")) {
        return formatString(getPortraitUrl, a.uid, b, a.crc, __user.uid);
    } else {
        if(b == 1 || b == 5) {
            return portrait.fetion_16;
        } else {
            if(b == 2) {
                return portrait.fetion_32;
            } else {
                return portrait.defaultIcon;
            }
        }
    }
}
function getDefaultPortraitUrl(a) {
    if(a.ct == 1) {
        return portrait.mobile;
    } else {
        return portrait.defaultIcon;
    }
}
var __userPortraitMap = new jHashMap();
var __userPathMap = new jHashMap();
var __portraitCacheDiv = null;
function cacheUerPortrait(c, a, e) {
    if(__portraitCacheDiv == null) {
        __portraitCacheDiv = $("<div style='display:none'></div>");
        $(document.body).append(__portraitCacheDiv);
    }
    var b;
    if(!__userPortraitMap.containsKey(c.uid)) {
        b = $("<img src='" + a + "' />");
        __userPortraitMap.put(c.uid, b);
        __portraitCacheDiv.append(b);
        b.bind("load", function() {
            if(typeof e == "function") {
                e(b, c);
            }
        });
    } else {
        b = __userPortraitMap.get(c.uid);
        var d = b.attr("src");
        if(d && !d.endsWith(a)) {
            b.unbind("load");
            b.remove();
            b.attr("src", a);
            __portraitCacheDiv.append(b);
            b.bind("load", function() {
                if(typeof e == "function") {
                    e(b, c);
                }
            });
        } else {
            if(typeof e == "function") {
                e(b, c);
            }
        }
    }
}
function getContactPresDesc(a) {
    var c = a.type,b = a.status;
    if(c == ContactType.MobileBuddy) {
        return"";
    }
    if((b & ContactStatus.Verify) == ContactStatus.Verify || (b & ContactStatus.Verify_Bklist) == ContactStatus.Verify_Bklist) {
        return"(等待对方同意添加为好友)";
    }
    if((b & ContactStatus.Refuse) == ContactStatus.Refuse || (b & ContactStatus.Refuse_Bklist) == ContactStatus.Refuse_Bklist) {
        return"(对方拒绝)";
    }
    if((b & ContactStatus.CloseService) == ContactStatus.CloseService) {
        return"(飞信服务已关闭)";
    }
    return"";
}
var searchHelper = {SAFE_DEEP_DEFAULT:4,SEPARATOR_BETWEEN_WORDS:"",WILDCARD_ALL:"*",_cache:new jHashMap(),MakeSpellCodeAsWordsAll:function(i) {
    var d = new String(i);
    if(searchHelper._cache.containsKey(d)) {
        return searchHelper._cache.get(i.toString());
    }
    var a = null;
    var h = null;
    try {
        var c = i.split(searchHelper.WILDCARD_ALL);
        i = "";
        for(var b = 0; b < c.length; ++b) {
            a = searchHelper.MakeSpellCodeAsWords(c[b]);
            i += a.bodyList.join(",") + searchHelper.WILDCARD_ALL;
        }
        if(a) {
            h = a.safeDeep;
        }
        if(!h) {
            h = searchHelper.SAFE_DEEP_DEFAULT;
        }
        var f = {"bodyContent":i,"safeDeep":h};
        if(!searchHelper._cache.containsKey(d)) {
            searchHelper._cache.put(d, f);
        }
        return f;
    } catch(g) {
        return{"bodyContent":i,"safeDeep":h};
    }
},MakeSpellCodeAsWords:function(a, g) {
    var f = new Array();
    if(!g) {
        g = searchHelper.SAFE_DEEP_DEFAULT;
    }
    if(!a) {
        return{"bodyList":f,"safeDeep":g};
    }
    var h = a.split(new RegExp(/,/gi));
    var b = new Array();
    for(var d = 0,e = h.length; d < e; ++d) {
        var c = h[d].split(" ");
        b.push(c);
    }
    for(var d = 0,e = b.length; d < e && d < g; ++d) {
        if(b[d].length > 1) {
            f = searchHelper._CopyNTimes(f, b[d].length);
        }
        if(b[d].length > 0) {
            f = searchHelper._AppendToEachItem(f, b[d]);
        }
    }
    return{"bodyList":f,"safeDeep":g};
},_CopyNTimes:function(d, e) {
    if(d.length == 0 || e == 0) {
        return d;
    }
    var a = d.length;
    var c = e;
    while((--c) > 0) {
        for(var b = 0; b < a; ++b) {
            d.push(d[b]);
        }
    }
    return d;
},_AppendToEachItem:function(e, b) {
    if(e.length == 0) {
        e = e.concat(b);
    } else {
        var g = e.length;
        var h = b.length;
        var f = e.length / b.length;
        var a = 0;
        for(var d = 0; d < h; ++d) {
            for(var c = 0; c < f; ++c) {
                e[a * f + c] += b[d];
            }
            ++a;
        }
    }
    return e;
}};
function GroupDefault() {
    this.gUri = "";
    this.i = 0;
    this.nn = "";
    this.crc = "";
    this.id = 0;
    this.n = "";
    this.gnn = "";
    this.smsRecv = 0;
    this.msgRecv = 0;
    this.apvRs = 0;
}
function extendGroupItem(b) {
    var a = jGroupUriToId(b.gUri);
    b.id = parseInt(a);
    b.orderNo = getGroupOrder(b);
}
function GroupMemDefault() {
    this.ct = "";
    this.minn = "";
    this.mnn = "";
    this.ms = 0;
    this.i = 0;
    this.displayName = "";
    this.lastIsOc = false;
    this.currIsOc = false;
    this.crc = "";
    this.uid = 0;
}
function extendMemItem(c) {
    var b = jMemUriToId(c.MemberUri);
    c.id = parseInt(b);
    if(!c.t6) {
        c.isVip = false;
    } else {
        var a = "," + c.t6 + ",";
        c.isVip = (a.indexOf(",50,") >= 0 || a.indexOf(",51,") >= 0 || a.indexOf(",52,") >= 0);
    }
    c.priName = "";
    if(c.i == 1) {
        c.priName = "founder";
    } else {
        if(c.i == 2) {
            c.priName = "adminor";
        } else {
            if(c.i == 3) {
                c.priName = "every";
            }
        }
    }
    c.displayName = c.mnn || c.minn || c.id.toString();
    c.orderNo = getGroupBuddyOrder(c);
    c.lastIsOc = c.currIsOc;
    if(c.ms == 400) {
        c.statusClass = "ff-pc-online";
        c.currIsOc = true;
    } else {
        if(c.ms == 0) {
            c.statusClass = "offline";
            c.currIsOc = false;
        }
    }
}
function groupBuddyComparator(e, d) {
    if(!e && !d) {
        return 0;
    }
    if(e && !d) {
        return 1;
    }
    if(!e && d) {
        return -1;
    }
    var c,b;
    if(e.orderNo) {
        c = e.orderNo;
    } else {
        c = getGroupBuddyOrder(e);
    }
    if(d.orderNo) {
        b = d.orderNo;
    } else {
        b = getGroupBuddyOrder(d);
    }
    if(c == b) {
        var a = (e.displayName).localeCompare(d.displayName);
        if(a == 0 && e.MemberUri && d.MemberUri) {
            return(e.MemberUri).localeCompare(d.MemberUri);
        }
        return a;
    }
    return c - b;
}
function getGroupBuddyOrder(a) {
    if(!a) {
        return -1;
    }
    var c = 0;
    var b = parseInt(a.ms);
    if(b == 0) {
        if(a.priName == "founder") {
            c = 5;
        } else {
            if(a.priName == "adminor") {
                c = 6;
            } else {
                c = a.isVip ? 7 : 8;
            }
        }
    } else {
        if(b == 400) {
            if(a.priName == "founder") {
                c = 1;
            } else {
                if(a.priName == "adminor") {
                    c = 2;
                } else {
                    c = a.isVip ? 3 : 4;
                }
            }
        }
    }
    return c;
}
function groupComparator(b, e) {
    if(!b && !e) {
        return 0;
    }
    if(b && !e) {
        return 1;
    }
    if(!b && e) {
        return -1;
    }
    var d,c;
    if(b.orderNo) {
        d = b.orderNo;
    } else {
        d = getGroupOrder(b);
    }
    if(e.orderNo) {
        c = e.orderNo;
    } else {
        c = getGroupOrder(e);
    }
    if(d == c) {
        var a = 0;
        if(b.n && e.n) {
            a = (b.n).localeCompare(e.n);
        } else {
            if(b.n && !e.n) {
                a = -1;
            } else {
                if(!b.n && e.n) {
                    a = 1;
                }
            }
        }
        if(a == 0 && b.gUri && e.gUri) {
            return(b.gUri).localeCompare(e.gUri);
        }
        return a;
    }
    return d - c;
}
function getGroupOrder(a) {
    if(!a) {
        return -1;
    }
    var b = 0;
    b = parseInt(a.i);
    return b;
}
function sort_by(b, a) {
    a = (a) ? -1 : 1;
    return function(e, d) {
        if(e.GDataType == 9 && d.GDataType == 9) {
            e = e.GData[b];
            d = d.GData[b];
        }
        var c = 0;
        if(e && d) {
            c = (e.toString()).localeCompare(d.toString());
        } else {
            if(e && !d) {
                c = -1;
            } else {
                if(!e && d) {
                    c = 1;
                }
            }
        }
        if(c > 0) {
            return a * 1;
        } else {
            if(c < 0) {
                return a * -1;
            }
        }
        return 0;
    };
}
function buddyPor(b, a, d) {
    var c = "";
    if(a.indexOf("bklist") > 0 || a == "offline") {
        if(d == 2) {
            c = portrait.offline_32;
        } else {
            c = portrait.offline_16;
        }
    } else {
        switch(a) {case"refuse":c = portrait.refuse;break;case"close":c = portrait.close;break;case"refuse":c = portrait.refuse;break;case"verify":c = portrait.verify;break;case"rb-verify":c = portrait.verify;break;case"rb-pc-online":if(d == 2) {
            c = portrait.robot_online_32;
        } else {
            c = portrait.robot_online_16;
        }break;case"rb-offline":if(d == 2) {
            c = portrait.robot_offline_32;
        } else {
            c = portrait.robot_offline_16;
        }break;default:if(b) {
            if(d == 1) {
                c = getUserPortraitUrl(b, "5");
            } else {
                c = getUserPortraitUrl(b, "2");
            }
        }break;}
    }
    if(c == "") {
        c = portrait.defaultIcon;
    }
    return c;
}
(function(a) {
    a.extend({cacheImages:function() {
        var b = a("<div style='display:none'></div>");
        b.append('<img src="images/emoticons/smile_regular.png" />');
        b.append('<img src="images/emoticons/smile_teeth.png" />');
        b.append('<img src="images/emoticons/smile_wink.png" />');
        b.append('<img src="images/emoticons/smile_omg.png" />');
        b.append('<img src="images/emoticons/smile_tongue.png" />');
        b.append('<img src="images/emoticons/smile_shades.png" />');
        b.append('<img src="images/emoticons/smile_angry.png" />');
        b.append('<img src="images/emoticons/smile_sad.png" />');
        b.append('<img src="images/emoticons/smile_cry.png" />');
        b.append('<img src="images/emoticons/smile_embaressed.png" />');
        b.append('<img src="images/emoticons/smile_sarcastic.png" />');
        b.append('<img src="images/emoticons/smile_sick.png" />');
        b.append('<img src="images/emoticons/smile_baringteeth.png" />');
        b.append('<img src="images/emoticons/smile_yawn.png" />');
        b.append('<img src="images/emoticons/smile_zipit.png" />');
        b.append('<img src="images/emoticons/smile_eyeroll.png" />');
        b.append('<img src="images/emoticons/moon.png" />');
        b.append('<img src="images/emoticons/cloud_rain.png" />');
        b.append('<img src="images/emoticons/clock.png" />');
        b.append('<img src="images/emoticons/heart.png" />');
        b.append('<img src="images/emoticons/heart_broken.png" />');
        b.append('<img src="images/emoticons/cat.png" />');
        b.append('<img src="images/emoticons/dog.png" />');
        b.append('<img src="images/emoticons/snail.png" />');
        b.append('<img src="images/emoticons/star.png" />');
        b.append('<img src="images/emoticons/sun.png" />');
        b.append('<img src="images/emoticons/rainbow.png" />');
        b.append('<img src="images/emoticons/hug_girl.png" />');
        b.append('<img src="images/emoticons/hug_dude.png" />');
        b.append('<img src="images/emoticons/kiss.png" />');
        b.append('<img src="images/emoticons/rose.png" />');
        b.append('<img src="images/emoticons/rose_wilted.png" />');
        b.append('<img src="images/emoticons/present.png" />');
        b.append('<img src="images/emoticons/cake.png" />');
        b.append('<img src="images/emoticons/music_note.png" />');
        b.append('<img src="images/emoticons/lightbulb.png" />');
        b.append('<img src="images/emoticons/smile_idea.png" />');
        b.append('<img src="images/emoticons/coffee.png" />');
        b.append('<img src="images/emoticons/umbrella.png" />');
        b.append('<img src="images/emoticons/mobile.png" />');
        b.append('<img src="images/emoticons/computer.png" />');
        b.append('<img src="images/emoticons/smile_whatchutalkingabout.png" />');
        b.append('<img src="images/emoticons/smile_confused.png" />');
        b.append('<img src="images/emoticons/smile_worried.png" />');
        b.append('<img src="images/emoticons/smile_drink.png" />');
        b.append('<img src="images/emoticons/martini.png" />');
        b.append('<img src="images/emoticons/smile_angle.png" />');
        b.append('<img src="images/emoticons/smile_thinking.png" />');
        b.append('<img src="images/emoticons/smile_great.png" />');
        b.append('<img src="images/emoticons/smile_naughty.png" />');
        b.append('<img src="images/emoticons/smile_fool.png" />');
        b.append('<img src="images/emoticons/smile_cool.png" />');
        a(document.body).append(b);
    }});
})(jQuery);
var __zIndex = 100;
var __hrefReg = /(http:\/\/|https:\/\/|ftp:\/\/|www\.){1}[\w-]+(\.[\w-]+)*(:[\d]+)?(\/[\w-\.\/?&=]*)?/ig;
function jChatWindowPopMessage(a) {
    var c = $.extend({target:"",canClose:true,className:"pop_blacklist",onClose:function() {
    }}, a);
    c.target = ((typeof c.target == "string") ? $("#" + c.target) : c.target);
    var b = $("<div />").addClass(c.className);
    c.target.prepend(b);
    if(c.canClose) {
        var d = $("<i class='close'>关闭</i>");
        b.append(d);
        d.bind("click", function(f) {
            b.css("display", "none");
            c.onClose();
            stopPropagation(f);
            preventDefault(f);
        });
    }
    b.setInnerHtml = function(e) {
        b.prepend(e);
        var f = $(b).find("div");
        var g = f.find("div");
        f.removeClass("chat_append_sec").addClass("chat_append");
        g.removeClass("chat_append_tip1");
    };
    b.buddyStatus = c.buddyStatus;
    return b;
}
function jChatWindowPopExpression(b) {
    var a = $("<div />").addClass("pop_expression facial_new1");
    var d = $('<div class="facial_icon_new1"><img class="icon_facial_new1" alt="" src="images/face_btn.png" /></div>');
    var f = $("<div class=expression_tit><ul><li class=center>飞信表情 </li></ul></div>");
    var e = $("<div />").addClass("layer_face");
    var c = $("<ul />");
    a.fillImgs = function() {
        c.append('<li><a href="#"><img title="微笑 :)" pattern=":)" src="images/emoticons/smile_regular.png" /></a>');
        c.append('<li><a href="#"><img title="大笑 :d" pattern=":d" src="images/emoticons/smile_teeth.png" /></a>');
        c.append('<li><a href="#"><img title="眨眼 ;)" pattern=";)" src="images/emoticons/smile_wink.png" /></a>');
        c.append('<li><a href="#"><img title="惊讶 :-o" pattern=":-o" src="images/emoticons/smile_omg.png" /></a>');
        c.append('<li><a href="#"><img title="吐舌笑脸 :p" pattern=":p" src="images/emoticons/smile_tongue.png" /></a>');
        c.append('<li><a href="#"><img title="热烈的笑脸 (h)" pattern="(h)" src="images/emoticons/smile_shades.png" /></a>');
        c.append('<li><a href="#"><img title="生气 :@" pattern=":@" src="images/emoticons/smile_angry.png" /></a>');
        c.append('<li><a href="#"><img title="悲伤 :(" pattern=":(" src="images/emoticons/smile_sad.png" /></a>');
        c.append('<li><a href="#"><img title="哭泣 :’(" pattern=":’(" src="images/emoticons/smile_cry.png" /></a>');
        c.append('<li><a href="#"><img title="尴尬 :&quot;&gt;" pattern=":&quot;&gt;" src="images/emoticons/smile_embaressed.png" /></a>');
        c.append('<li><a href="#"><img title="讽刺 ^o)" pattern="^o)" src="images/emoticons/smile_sarcastic.png" /></a>');
        c.append('<li><a href="#"><img title="生病 :&amp;" pattern=":&amp;" src="images/emoticons/smile_sick.png" /></a>');
        c.append('<li><a href="#"><img title="咬牙切齿 8o|" pattern="8o|" src="images/emoticons/smile_baringteeth.png" /></a>');
        c.append('<li><a href="#"><img title="困 |o" pattern="|o" src="images/emoticons/smile_yawn.png" /></a>');
        c.append('<li><a href="#"><img title="保密 :-#" pattern=":-#" src="images/emoticons/smile_zipit.png" /></a>');
        c.append('<li><a href="#"><img title="转动的眼睛 8-)" pattern="8-)" src="images/emoticons/smile_eyeroll.png" /></a>');
        c.append('<li><a href="#"><img title="沉睡的弯月 (s)" pattern="(s)" src="images/emoticons/moon.png" /></a>');
        c.append('<li><a href="#"><img title="下雨 (st)" pattern="(st)" src="images/emoticons/cloud_rain.png" /></a>');
        c.append('<li><a href="#"><img title="时钟 (o)" pattern="(o)" src="images/emoticons/clock.png" /></a>');
        c.append('<li><a href="#"><img title="红心 (l)" pattern="(l)" src="images/emoticons/heart.png" /></a>');
        c.append('<li><a href="#"><img title="破碎的心 (u)" pattern="(u)" src="images/emoticons/heart_broken.png" /></a>');
        c.append('<li><a href="#"><img title="猫脸 (@)" pattern="(@)" src="images/emoticons/cat.png" /></a>');
        c.append('<li><a href="#"><img title="狗脸 (&amp;)" pattern="(&amp;)" src="images/emoticons/dog.png" /></a>');
        c.append('<li><a href="#"><img title="蜗牛 (sn)" pattern="(sn)" src="images/emoticons/snail.png" /></a>');
        c.append('<li><a href="#"><img title="星星 (*)" pattern="(*)" src="images/emoticons/star.png" /></a>');
        c.append('<li><a href="#"><img title="太阳 (#)" pattern="(#)" src="images/emoticons/sun.png" /></a>');
        c.append('<li><a href="#"><img title="彩虹 (r)" pattern="(r)" src="images/emoticons/rainbow.png" /></a>');
        c.append('<li><a href="#"><img title="左侧拥抱 ({)" pattern="({)" src="images/emoticons/hug_girl.png" /></a>');
        c.append('<li><a href="#"><img title="右侧拥抱 (})" pattern="(})" src="images/emoticons/hug_dude.png" /></a>');
        c.append('<li><a href="#"><img title="红唇 (k)" pattern="(k)" src="images/emoticons/kiss.png" /></a>');
        c.append('<li><a href="#"><img title="红玫瑰 (f)" pattern="(f)" src="images/emoticons/rose.png" /></a>');
        c.append('<li><a href="#"><img title="凋谢的玫瑰 (w)" pattern="(w)" src="images/emoticons/rose_wilted.png" /></a>');
        c.append('<li><a href="#"><img title="礼品盒 (g)" pattern="(g)" src="images/emoticons/present.png" /></a>');
        c.append('<li><a href="#"><img title="生日蛋糕 (^)" pattern="(^)" src="images/emoticons/cake.png" /></a>');
        c.append('<li><a href="#"><img title="音乐 (8)" pattern="(8)" src="images/emoticons/music_note.png" /></a>');
        c.append('<li><a href="#"><img title="灯泡 (i)" pattern="(i)" src="images/emoticons/lightbulb.png" /></a>');
        c.append('<li><a href="#"><img title="想法 *-:)" pattern="*-:)" src="images/emoticons/smile_idea.png" /></a>');
        c.append('<li><a href="#"><img title="咖啡 (c)" pattern="(c)" src="images/emoticons/coffee.png" /></a>');
        c.append('<li><a href="#"><img title="雨伞 (um)" pattern="(um)" src="images/emoticons/umbrella.png" /></a>');
        c.append('<li><a href="#"><img title="手机 (mp)" pattern="(mp)" src="images/emoticons/mobile.png" /></a>');
        c.append('<li><a href="#"><img title="计算机 (co)" pattern="(co)" src="images/emoticons/computer.png" /></a>');
        c.append('<li><a href="#"><img title="失望 :|" pattern=":|" src="images/emoticons/smile_whatchutalkingabout.png" /></a>');
        c.append('<li><a href="#"><img title="困惑 :-/" pattern=":-/" src="images/emoticons/smile_confused.png" /></a>');
        c.append('<li><a href="#"><img title="担心 :s" pattern=":s" src="images/emoticons/smile_worried.png" /></a>');
        c.append('<li><a href="#"><img title="饮料 )-|" pattern=")-|" src="images/emoticons/smile_drink.png" /></a>');
        c.append('<li><a href="#"><img title="高脚杯 (d)" pattern="(d)" src="images/emoticons/martini.png" /></a>');
        c.append('<li><a href="#"><img title="天使 o:)" pattern="o:)" src="images/emoticons/smile_angle.png" /></a>');
        c.append('<li><a href="#"><img title="沉思 :?" pattern=":?" src="images/emoticons/smile_thinking.png" /></a>');
        c.append('<li><a href="#"><img title="太棒了 (y)" pattern="(y)" src="images/emoticons/smile_great.png" /></a>');
        c.append('<li><a href="#"><img title="淘气 &gt;:)" pattern="&gt;:)" src="images/emoticons/smile_naughty.png" /></a>');
        c.append('<li><a href="#"><img title="笨蛋、呆子 :b" pattern=":b" src="images/emoticons/smile_fool.png" /></a>');
        c.append('<li><a href="#"><img title="戴着太阳镜 b)" pattern="b)" src="images/emoticons/smile_cool.png" /></a>');
    };
    a.bindItemClick = function(g) {
        c.find("li > a").bind("click", function(h) {
            a.css("display", "none");
            g(this, h);
        });
    };
    a.append(d).append(f).append(e.append(c));
    return a;
}
var IMSound = {isAjaxReady:false,ajaxInit:function() {
    try {
        var a = '<object codebase="//download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0"' + ' width="0" height="0" id="fetion_ajaxproxy" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000">' + '<param name="allowScriptAccess" value="always" />' + '<param name="movie" value="swf/ajaxhelper.swf" />' + '<param name="quality" value="high" />' + '<param name="bgcolor" value="#ffffff" />' + ' <embed src="swf/ajaxhelper.swf" quality="high" width="0" height="0" bgcolor="#ffffff"' + ' type="application/x-shockwave-flash" pluginspage="//www.macromedia.com/go/getflashplayer"' + ' name="fetion_ajaxproxy" swLiveConnect="true" allowScriptAccess="always">' + "</embed>" + "</object>";
        $("#Sound").append(a);
    } catch(b) {
    }
},ajaxReady:function() {
    this.isAjaxReady = true;
},onAjaxInitError:function() {
},getAjaxProxy:function() {
    if(navigator.appName.indexOf("Microsoft") != -1) {
        if(window["fetion_ajaxproxy"].length > 0) {
            return window["fetion_ajaxproxy"][0];
        } else {
            return window["fetion_ajaxproxy"];
        }
    } else {
        return document["fetion_ajaxproxy"];
    }
},playSound:function(a) {
    var b = this.getAjaxProxy();
    if(b && b.playSound) {
        b.playSound(a);
    }
}};
$(function() {
    $.popWinMsg = function(K) {
        var B;
        var P = {gName:"",group:{},fName:"",joinItems:{},iR:"",msgN:"msg1",isLink:false,openWin:function() {
        },handleEntity:{},consent:function() {
        },refuse:function() {
        },zindex:9000,timeout:10000,isClose:true};
        var x = function(an) {
            var al = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            for(var ak = 0,am = ""; ak < an; ak++) {
                am += al.charAt(Math.floor(Math.random() * 62));
            }
            return am;
        };
        var h = $.extend(P, K);
        h.isClose = true;
        var C;
        var F = getGroupItemPortraitUrl(h.group, "4");
        h.gName = EmotionNonhtmlReplace(h.gName, 40, false);
        h.fName = EmotionNonhtmlReplace(h.fName, 20, false);
        var T;
        switch(h.msgN) {case"msg1":var w = "您已被从群<strong>" + h.gName + "</strong>中删除";T = w;break;case"msg2":var u = "您被指定为群<strong>" + h.gName + "</strong>的管理员";T = u;break;case"msg3":var t = "您被指定为群<strong>" + h.gName + "</strong>的超级管理员";T = t;break;case"msg4":var q = "您的好友<strong>" + h.fName + "</strong>邀请您加入群<strong>" + h.gName + "</strong>";T = q;break;case"msg5":var ab = jGroupUriToId(h.group.gUri);var o = __groupMap.get(ab);var p = "<strong>" + h.fName + "</strong>加入您的群<strong>" + o.n + "</strong>";T = p;break;case"msg6":var n = "您所在的群<strong>" + h.gName + "</strong>已经被删除";T = n;break;case"msg7":var l = "您被取消群<strong>" + h.gName + "</strong>的管理员身份";T = l;break;case"msg8":var k = "欢迎您加入群<strong>" + h.gName + "</strong>";T = k;break;case"msg10":var ah = "<strong>" + h.fName + "</strong>邀请" + h.joinItems.length + "位好友加入群<strong>" + h.gName + "</strong>";T = ah;break;case"msg11":var af = "您是飞信会员，最多可以加入22个群";T = af;break;case"msg12":var ag = "http://vip.feixin.10086.cn/open/junior/code/" + __user.mn;var ad = "普通用户最多可以加入20个群，<a href=" + ag + ' target="_blank" ><strong>开通会员立即获得加入更多群的特权</strong></a>';T = ad;break;case"msg13":var ab = jGroupUriToId(h.group.GroupUri);var o = __groupMap.get(ab);var ac = "<strong>" + cnSubstr(h.fName, 20, true) + "</strong>申请加入群<strong>" + cnSubstr(o.n, 36, true) + "</strong>";T = ac;break;case"msg14":var aa = "申请已经被审批";T = aa;break;default:break;}
        var Q = $('<div class="pop_group_cont fixed-bottom fixed-right" id="' + x(10) + '" ></div>').css("z-index", h.zindex);
        var b = $('<div class="pop_group_cont_bg"></div>');
        var aj = $(' <div class="pop_group_cont_tit"><a href="javascript:;" class="pop_group_close"></a><h3>群提醒</h3> </div>');
        if(h.msgN == "msg10" || h.msgN == "msg13") {
            aj = $(' <div class="pop_group_cont_tit"><a href="javascript:;" class="pop_group_close"></a><h3>加群申请</h3> </div>');
        } else {
            if(h.msgN == "msg4") {
                aj = $(' <div class="pop_group_cont_tit"><a href="javascript:;" class="pop_group_close"></a><h3>加群邀请</h3> </div>');
            }
        }
        var c = $('<div class="pop_group_cont_box"></div>');
        var N = $('<div class="pop_group_tips"></div>');
        var ae = $('<div class="pop_group_tips_pic"><span class="pop_group_tips_pic_bg"></span></div>');
        var A = $("<img />").attr("src", F);
        var V = $('<div class="pop_group_tips_text"></div>');
        if(h.msgN == "msg10") {
            Q.removeClass("pop_group_cont").addClass("pop_group_invite_box");
            var m = $('<div class="pop_group_invite_sub"></div>');
            var ai = $('<div class="pop_group_invite_info"></div>');
            ae = $('<div class="pop_group_invite_info_pic"></div>');
            ae.append(A);
            V.removeClass().addClass("pop_group_invite_info_text");
            V.html(T);
            var H = ai.append(ae).append(V);
            m.append(aj).append(H);
            var z = $('<div class="pop_group_invite_cont"></div>');
            var d = $('<div class="pop_group_invite_list_dec"></div>');
            var O = $('<div class="pop_group_invite_list"></div>');
            var S = $('<div class="pop_group_invite_list_dec"></div>');
            var R = $('<div class="pop_group_invite_list_c"></div>');
            for(var Z = 0; Z < h.joinItems.length; Z++) {
                var Y = $("<span></span>").html(h.joinItems[Z].nn);
                R.append(Y);
            }
            var M = O.append(d).append(R).append(S);
            var E = $('<div class="pop_group_invite_explain">邀请说明：' + h.iR + "</div>");
            var a = $('<div class="pop_group_invite_link"></div>');
            var J = $('<a href="javascript:;" class="consent">批准</a>').bind("click", function(i) {
                $.closeMessageBox(B, true);
                h.consent(h.handleEntity, 1);
                stopPropagation(i);
                preventDefault(i);
            });
            var L = $('<a href="javascript:;" class="refuse">拒绝</a>').bind("click", function(i) {
                $.closeMessageBox(B, true);
                h.consent(h.handleEntity, 2);
                stopPropagation(i);
                preventDefault(i);
            });
            var U = a.append(L).append(J);
            z.append(M).append(E).append(U);
            Q.append(m).append(z);
        } else {
            if(h.msgN == "msg13") {
                var ab = jGroupUriToId(h.group.GroupUri);
                var g = jMemUriToId(h.group.ApplyGroupUserEntity[0].Uri);
                var G = ab + "_" + g;
                Q.attr("id", G);
                Q.removeClass("pop_group_cont").addClass("pop_group_invite_box");
                var m = $('<div class="pop_group_invite_sub bg">' + '<div class="pop_group_cont_tit">' + '<a href="javascript:;" class="pop_group_close"></a>' + "<h3>加群申请</h3></div>" + '<div class="pop_group_invite_info">' + '<div class="pop_group_invite_info_pic matop"></div>' + '<div class="pop_group_invite_info_text lh">' + T + "<p>申请说明：" + cnSubstr(h.iR, 60, false) + "</p>" + "</div></div></div>");
                m.find(".pop_group_invite_info_pic").append(A);
                var J = $('<div class="pop_group_invite_cont">' + '<div class="pop_group_invite_link2"><a href="javascript:;" class="consent">拒绝</a><a href="javascript:;" class="confirm">批准</a></div>' + "</div>");
                Q.append(m).append(J);
                J.find(".pop_group_invite_link2 > a:eq(1)").bind("click", function(i) {
                    $.closeMessageBox(B, true);
                    h.consent(h.handleEntity, 1);
                    stopPropagation(i);
                    preventDefault(i);
                }).addClass("hover");
                J.find(".pop_group_invite_link2 > a:eq(0)").bind("click", function(i) {
                    $.closeMessageBox(B, true);
                    h.consent(h.handleEntity, 2);
                    stopPropagation(i);
                    preventDefault(i);
                }).bind("mouseover", function(i) {
                    J.find(".pop_group_invite_link2 > a:eq(1)").removeClass("hover");
                });
                if($("#" + G).attr("id")) {
                    $("#" + G).find(".pop_group_invite_info_text").html("").html(T + "<p>申请说明：" + h.iR + "</p>");
                } else {
                    Q.appendTo(document.body);
                    C = setTimeout(function() {
                        $.closeMessageBox(B, h);
                    }, h.timeout);
                }
            } else {
                if(h.msgN == "msg14") {
                    c.addClass("pabo");
                    c.append($('<div class="pop_group_cont_box_icon"></div><p class="pop_group_cont_box_t">' + T + '。</p><a class="confirm" href="javascript:;">确定</a>'));
                    c.find("a").bind("click", function(i) {
                        Q.remove();
                        stopPropagation(i);
                        preventDefault(i);
                    });
                } else {
                    if(h.msgN == "msg11" || h.msgN == "msg12") {
                        V.addClass("w170");
                        var y = "<strong>您所加入群的数量已达上限!</strong>";
                        var I = $("<p></p>").html(T);
                        V.append(y).append(I);
                    } else {
                        V.html(T);
                    }
                }
            }
        }
        if(h.msgN != "msg10" && h.msgN != "msg13") {
            var f = ae.append(A);
            var v = "";
            if(h.msgN == "msg11" || h.msgN == "msg12") {
                var s = $('<div class="pop_group_tips_icon"></div>');
                v = N.append(s).append(V);
            } else {
                if(h.msgN != "msg14") {
                    v = N.append(f).append(V);
                }
            }
            var D = c.append(v);
            if(K.isLink) {
                var a = $('<div class="pop_group_link"></div>');
                var J = $('<a href="javascript:;" class="consent">同意</a>').bind("click", function(i) {
                    $.closeMessageBox(B, true);
                    h.consent(h.handleEntity);
                    stopPropagation(i);
                    preventDefault(i);
                });
                var L = $('<a href="javascript:;" class="refuse">拒绝</a>').bind("click", function(i) {
                    $.closeMessageBox(B, true);
                    stopPropagation(i);
                    preventDefault(i);
                });
                var U = a.append(J).append(L);
                D.append(U);
            }
            if(h.msgN == "msg8") {
                Q.addClass("pop_group_spe");
                c.removeClass().addClass("pop_group_cont_box_spe");
                var e = $('<div class="pop_group_tips_join"></div>');
                var j = $('<a href="javascript:;"  hidefocus="true">加入会话</a>').click(function(i) {
                    h.openWin(this, h.group.id);
                    stopPropagation(i);
                    preventDefault(i);
                });
                var W = e.append(j);
                D.append(W);
            }
            var r = b.append(aj).append(D);
            Q.append(r);
        }
        var X = Q.html();
        if(h.msgN == "msg10" || h.msgN == "msg4") {
            if(!__groupMsgMap.containsKey(X)) {
                __groupMsgMap.put(X);
                Q.appendTo(document.body);
            } else {
                return;
            }
        } else {
            if(h.msgN != "msg13") {
                Q.appendTo(document.body);
            }
        }
        startSound("sys");
        $(".pop_group_close").click(function(i) {
            $.closeMessageBox(B, true);
            stopPropagation(i);
            preventDefault(i);
        });
        Q.slideDown("slow");
        if(h.isClose && h.msgN != "msg13") {
            C = setTimeout(function() {
                $.closeMessageBox(B, h);
            }, h.timeout);
        }
        B = {d:Q,i:C};
        return B;
    };
    $.closeMessageBox = function(c, b) {
        if(c.d.attr("class").indexOf("fixed-bottom fixed-right") > 0) {
            c.d.slideUp("slow");
        }
        if(b == true) {
            c.d.remove();
            clearTimeout(c.i);
            onCloseGroupSysChatWindow(c.d);
            var a = $(c.d).html();
            if(__groupMsgMap.containsKey(a)) {
                __groupMsgMap.remove(a);
            }
        } else {
            if(b && (b.msgN == "msg10" || b.msgN == "msg4" || b.msgN == "msg13")) {
                gsw = $.fn.jGroupSysMsg({user:__user,group:b.group,popWin:c.d,onActive:onActiveGroupSysChatWindow,onClosed:onCloseGroupSysChatWindow,onFocus:onFocusGroupSysChatWindow});
                gsw.active();
                gsw.removeClass("fixed-bottom fixed-right");
                if(b.msgN != "msg4") {
                    gsw.removeClass("pop_group_invite_box").addClass("pop_group_invite_box2");
                }
                gsw.css("display", "");
                if(b.msgN == "msg13") {
                    if(!__chatWindowMap.get("group_sys_" + $(c.d).attr("id"))) {
                        __chatWindowMap.put("group_sys_" + $(c.d).attr("id"), gsw);
                    }
                } else {
                    __chatWindowMap.put("group_sys_" + $(c.d).attr("id"), gsw);
                }
            } else {
                c.d.remove();
                clearTimeout(c.i);
                var a = $(c.d).html();
                if(__groupMsgMap.containsKey(a)) {
                    __groupMsgMap.remove(a);
                }
            }
        }
    };
});
"use strict";
function Mst(buffer, ext) {
    var type = (arguments.length > 2 ? arguments[2] : "");
    var Buffer;
    var Text;
    var Meta;
    var Data;
    var Index = 0;
    var isValid = false;
    var reset = function () {
        Buffer = {};
        Buffer["Mst"] = [];
        Buffer["Bms"] = [];
        Buffer["Bme"] = [];
        Buffer["Pms"] = [];
        Buffer["Vosvos000"] = undefined;
        Buffer["Vosvos001"] = undefined;
        Buffer["Vosvos006"] = undefined;
        Buffer["Vosvos022"] = undefined;
        Buffer["Mid"] = undefined;
        Buffer["Lrc"] = undefined;
        Buffer["Hexvosvos000"] = undefined;
        Buffer["Hexvosvos001"] = undefined;
        Buffer["Hexvosvos006"] = undefined;
        Buffer["Hexvosvos022"] = undefined;
        Buffer["Heximd"] = [];
        Buffer["Tjataiko"] = undefined;
        Buffer["Tjajube"] = undefined;
        Buffer["Osuosu"] = [];
        Buffer["Osutaiko"] = [];
        Buffer["Osuctb"] = [];
        Buffer["Osumania"] = [];
        Buffer["Xmlyddr"] = [];
        Buffer["Xmlydsd"] = [];
        Buffer["Xmlmde"] = [];
        Buffer["Vox"] = [];
        Buffer["Ksh"] = [];
        Buffer["Imd"] = [];
        Buffer["Mde"] = [];
        Buffer["Mckey"] = [];
        Buffer["Mcstep"] = [];
        Buffer["Mcdj"] = [];
        Buffer["Mccatch"] = [];
        Buffer["Mcpad"] = [];
        Buffer["Mctaiko"] = [];
        Buffer["Aff"] = [];
        Text = {};
        Text["Mst"] = [];
        Text["Bms"] = [];
        Text["Bme"] = [];
        Text["Pms"] = [];
        Text["Vosvos000"] = undefined;
        Text["Vosvos001"] = undefined;
        Text["Vosvos006"] = undefined;
        Text["Vosvos022"] = undefined;
        Text["Mid"] = undefined;
        Text["Lrc"] = undefined;
        Text["Hexvosvos000"] = undefined;
        Text["Hexvosvos001"] = undefined;
        Text["Hexvosvos006"] = undefined;
        Text["Hexvosvos022"] = undefined;
        Text["Heximd"] = [];
        Text["Tjataiko"] = undefined;
        Text["Tjajube"] = undefined;
        Text["Osuosu"] = [];
        Text["Osutaiko"] = [];
        Text["Osuctb"] = [];
        Text["Osumania"] = [];
        Text["Xmlyddr"] = [];
        Text["Xmlydsd"] = [];
        Text["Xmlmde"] = [];
        Text["Vox"] = [];
        Text["Ksh"] = [];
        Text["Imd"] = [];
        Text["Mde"] = [];
        Text["Mckey"] = [];
        Text["Mcstep"] = [];
        Text["Mcdj"] = [];
        Text["Mccatch"] = [];
        Text["Mcpad"] = [];
        Text["Mctaiko"] = [];
        Text["Aff"] = [];
        Text["Png"] = undefined;
        Text["Html"] = undefined;
        Meta = {};
        Data = [];
        Index = 0;
        isValid = false;
    };
    var readMeta = function (f) {
        var d = (arguments.length > 1 ? arguments[1] : undefined);
        var index = (arguments.length > 2 ? arguments[2] : 0);
        for(var i = 0; i < f.length; i++) {
            if(typeof Meta[f[i]] !== "undefined") {
                switch(f[i]) {
                    case"Course":
                    case"Level":
                    case"Player":
                    case"Style":
                    case"Balloon":
                    case"ScoreInit":
                    case"ScoreDiff":
                    case"ScoreMode":
                        if(typeof Meta[f[i]][index] !== "undefined") {
                            return Meta[f[i]][index];
                        }
                        break;
                    default:
                        if(typeof Meta[f[i]] !== "undefined") {
                            return Meta[f[i]];
                        }
                        break;
                }
            }
        }
        return d;
    };
    var getBeatData = function (a) {
        var time = getActionDataTime(a);
        var bpm = getActionDataBPM(a);
        var offsets = getActionDataOffsets(a, bpm);
        var r = [];
        var t = 0;
        var n = 0;
        while(true) {
            var b = bpm;
            if((n < offsets.length) && (t + 60000 / b * 2 >= offsets[n][0])) {
                b = 60000 / (60000 / b + offsets[n][1]);
                n += 1;
            }
            r.push([t, b]);
            if(t > time + 60000 / bpm) {
                break;
            }
            t += 60000 / b;
        }
        return r;
    };
    var fromBeatDataBeat = function (b, n) {
        if(n <= b.length - 1) {
            var s = Math.floor(n);
            if(s == n) {
                return b[s][0];
            } else {
                return b[s][0] + (n - s) * (b[s + 1][0] - b[s][0]);
            }
        }
        return-1;
    };
    var toBeatDataBeat = function (b, t) {
        for(var i = 0; i < b.length - 1; i++) {
            if((t >= b[i][0]) && (t < b[i + 1][0])) {
                return(t - b[i][0]) / (b[i + 1][0] - b[i][0]) + i;
            }
        }
        return-1;
    };
    var copyBeatData = function (b) {
        var r = [];
        for(var i = 0; i < b.length; i++) {
            r.push([b[i][0], b[i][1]]);
        }
        return r;
    };
    var unrepeatBeatData = function (b) {
        var r = copyBeatData(b);
        for(var i = 0; i < r.length; i++) {
            for(var j = 0; j < r.length; j++) {
                if((j != i) && IsNumberClose(r[j][0], r[i][0])) {
                    r.splice(j, 1);
                    j -= 1;
                }
            }
        }
        return r;
    }
    var offsetBeatData = function (b, o) {
        var r = copyBeatData(b);
        if(o != 0) {
            for(var i = 0; i < r.length; i++) {
                r[i][0] -= o;
                if(r[i][0] < 0) {
                    r.push([r[r.length - 1][0] + 60000 / r[r.length - 1][1], r[r.length - 1][1]])
                    r.splice(i, 1);
                    i -= 1;
                }
            }
        }
        while(r[0][0] < 0) {
            r.push([r[r.length - 1][0] + 60000 / r[r.length - 1][1], r[r.length - 1][1]])
            r.shift();
        }
        if(r[0][0] > 0) {
            while(r[0][0] > 60000 / r[0][1]) {
                var t = r[0][0] - 60000 / r[0][1];
                if(IsNumberClose(t, 0)) {
                    t = 0;
                }
                r.unshift([t, r[0][1]]);
            }
            if(r[0][0] > 0) {
                r.unshift([0, 60000 / r[0][0]]);
            }
        }
        return r;
    };
    var enlargeBeatData = function (b, a) {
        var r = copyBeatData(b);
        var l = (function () {
            var v = 0;
            for(var i = 0; i < a.length; i++) {
                switch(a[i][2]) {
                    case 0x00:
                    case 0x01:
                    case 0x61:
                    case 0x21:
                    case 0xA1:
                        if(v < a[i][0]) {
                            v = a[i][0];
                        }
                        break;
                    case 0x02:
                    case 0x62:
                    case 0x22:
                    case 0xA2:
                        if(v < a[i][0] + a[i][3]) {
                            v = a[i][0] + a[i][3];
                        }
                        break;
                }
            }
            return v;
        })();
        while((r[r.length - 1][0] < l) || (r.length % 4 != 1)) {
            r.push([r[r.length - 1][0] + 60000 / r[r.length - 1][1], r[r.length - 1][1]]);
        }
        return r;
    };
    var getActionDataTime = function (a) {
        var r = 0;
        for(var i = 0; i < a.length; i++) {
            var t = a[i][0];
            switch(a[i][2]) {
                case 0x02:
                case 0x62:
                case 0x22:
                case 0xA2:
                    t += a[i][3];
                    break;
            }
            if(r < t) {
                r = t;
            }
        }
        return r;
    };
    var getActionDataBPM = function (a) {
        var t = [];
        var d = [];
        var b = [];
        for(var i = 0; i < a.length; i++) {
            var v = a[i][0];
            v = parseInt(v);
            if(isNaN(v)) {
                continue;
            }
            t.push(v);
        }
        t = t.unrepeat().sort(SortNumber);
        for(var i = 0; i < t.length - 1; i++) {
            var v = t[i + 1] - t[i];
            if(v != 0) {
                d.push(v);
            }
        }
        d = d.sort(SortNumber);
        for(var i = 0; i < d.length; i++) {
            var v = 60000 / d[i];
            while(v < 50) {
                v *= 2;
            }
            while(v > 200) {
                v /= 2;
            }
            b.push(Math.round(v));
        }
        return b.majority();
    };
    var getActionDataOffsets = function (a) {
        var bpm = (arguments.length > 1 ? arguments[1] : getActionDataBPM(a));
        var d = [];
        var m = 60000 / 4 / bpm;
        for(var i = 0; i < a.length; i++) {
            d.push(a[i][0] % m);
        }
        var r = [];
        var t = [];
        var p = 0;
        var u = 0;
        var s = 0;
        var e = 0;
        while(true) {
            var b = (function () {
                for(var i = 1; i < 6; i++) {
                    if(!IsNumberClose(d[p + 0], d[p + i])) {
                        return false;
                    }
                }
                return true;
            })();
            if(b) {
                t.push(d[p]);
                if(u > 0) {
                    u -= 1;
                }
            } else {
                u += 1;
            }
            if(u == 0) {
                e = p;
            }
            if(u > 2) {
                if((t.length > 0) && (e - s > 2)) {
                    var v = Math.round(t.average());
                    if(v != 0) {
                        r.push([a[s][0], v]);
                    }
                }
                t = [];
                u = 0;
                s = p + 1;
                e = s;
            }
            p += 1;
            if(p >= d.length - 5) {
                if((r.length > 0) && (r[r.length - 1][0] != a[s][0])) {
                    var v = Math.round(t.average());
                    if(v != 0) {
                        r.push([a[s][0], v]);
                    }
                }
                break;
            }
        }
        for(var i = 1; i < r.length; i++) {
            if(r[i - 1][1] == r[i][1]) {
                r.splice(i, 1);
                i -= 1;
            }
        }
        var o = 0;
        for(var i = 0; i < r.length; i++) {
            if(i == 0) {
                o = r[i][1];
            } else {
                r[i][1] -= o;
                while(r[i][1] < 0) {
                    r[i][1] += 60000 / bpm;
                }
                r[i][1] = Math.round(r[i][1]);
            }
        }
        return r;
    };
    var getActionDataKey = function (a) {
        var r = 0;
        for(var i = 0; i < a.length; i++) {
            var t = 0;
            switch(a[i][2]) {
                case 0x00:
                case 0x02:
                case 0x62:
                case 0x22:
                case 0xA2:
                    t = a[i][1] + 1;
                    break;
                case 0x01:
                case 0x61:
                case 0x21:
                case 0xA1:
                    t = a[i][1] + a[i][3] + 1;
                    break;
            }
            if(r < t) {
                r = t;
            }
        }
        return r;
    };
    var copyActionData = function (a) {
        var r = [];
        for(var i = 0; i < a.length; i++) {
            r.push([a[i][0], a[i][1], a[i][2], a[i][3]]);
        }
        return r;
    };
    var unrepeatActionData = function (a) {
        var r = copyActionData(a);
        for(var i = 0; i < r.length; i++) {
            switch(r[i][2]) {
                case 0x00:
                case 0x01:
                case 0x02:
                    for(var j = 0; j < r.length; j++) {
                        if((j != i) && IsNumberClose(r[j][0], r[i][0]) && (r[j][1] == r[i][1]) && (r[j][2] == r[i][2]) && (r[j][3] == r[i][3])) {
                            r.splice(j, 1);
                            j -= 1;
                        }
                    }
                    break;
            }
        }
        return r;
    }
    var sortActionData = function (a) {
        var r = copyActionData(a);
        var h = [];
        var d = [];
        for(var i = 0; i < r.length; i++) {
            switch(r[i][2]) {
                case 0x02:
                case 0xA2:
                    for(var j = i + 1; j < r.length; j++) {
                        switch(r[j][2]) {
                            case 0x02:
                            case 0x62:
                                if(IsNumberClose(r[j][0], r[i][0] + r[i][3]) && (r[j][1] == r[i][1])) {
                                    switch(r[i][2]) {
                                        case 0x02:
                                            switch(r[j][2]) {
                                                case 0x02:
                                                    r[i][2] = 0x02;
                                                    break;
                                                case 0x62:
                                                    r[i][2] = 0x62;
                                                    break;
                                            }
                                            break;
                                        case 0xA2:
                                            switch(r[j][2]) {
                                                case 0x02:
                                                    r[i][2] = 0xA2;
                                                    break;
                                                case 0x62:
                                                    r[i][2] = 0x22;
                                                    break;
                                            }
                                            break;
                                    }
                                    r[i][3] += r[j][3];
                                    r.splice(j, 1);
                                    j -= 1;
                                }
                                break;
                        }
                    }
                    for(var j = i + 1; j < r.length; j++) {
                        switch(r[j][2]) {
                            case 0x01:
                            case 0x61:
                                if(IsNumberClose(r[j][0], r[i][0] + r[i][3]) && (r[j][1] == r[i][1])) {
                                    switch(r[i][2]) {
                                        case 0x02:
                                            switch(r[j][2]) {
                                                case 0x01:
                                                    r[i][2] = 0x62;
                                                    r[j][2] = 0xA1;
                                                    break;
                                                case 0x61:
                                                    r[i][2] = 0x62;
                                                    r[j][2] = 0x21;
                                                    break;
                                            }
                                            break;
                                        case 0xA2:
                                            switch(r[j][2]) {
                                                case 0x01:
                                                    r[i][2] = 0x22;
                                                    r[j][2] = 0xA1;
                                                    break;
                                                case 0x61:
                                                    r[i][2] = 0x22;
                                                    r[j][2] = 0x21;
                                                    break;
                                            }
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
            }
        }
        for(var i = 0; i < r.length; i++) {
            switch(r[i][2]) {
                case 0x61:
                case 0x01:
                case 0xA1:
                    for(var j = i + 1; j < r.length; j++) {
                        switch(r[j][2]) {
                            case 0x02:
                            case 0x62:
                                if(IsNumberClose(r[j][0], r[i][0]) && (r[j][1] == r[i][1] + r[i][3])) {
                                    switch(r[i][2]) {
                                        case 0x01:
                                            switch(r[j][2]) {
                                                case 0x02:
                                                    r[i][2] = 0x61;
                                                    r[j][2] = 0xA2;
                                                    break;
                                                case 0x62:
                                                    r[i][2] = 0x61;
                                                    r[j][2] = 0x22;
                                                    break;
                                            }
                                            break;
                                        case 0xA1:
                                            switch(r[j][2]) {
                                                case 0x02:
                                                    r[i][2] = 0x21;
                                                    r[j][2] = 0xA2;
                                                    break;
                                                case 0x62:
                                                    r[i][2] = 0x21;
                                                    r[j][2] = 0x22;
                                                    break;
                                            }
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
            }
        }
        var extract = function () {
            for(var i = 0; i < r.length; i++) {
                if((r[i][2] == 0x61) || (r[i][2] == 0x62)) {
                    var t = [];
                    t.push(r[i]);
                    var e = false;
                    while(true) {
                        if((t[t.length - 1][2] == 0x62) || (t[t.length - 1][2] == 0x22)) {
                            var n = (function () {
                                var v = -1;
                                if((i + 1 < r.length) && ((r[i + 1][2] == 0x21) || (r[i + 1][2] == 0xA1)) && (IsNumberClose(r[i + 1][0], t[t.length - 1][0] + t[t.length - 1][3]) && (r[i + 1][1] == t[t.length - 1][1]))) {
                                    v = i + 1;
                                } else if((i + 1 < r.length) && ((r[i + 1][2] == 0x21) || (r[i + 1][2] == 0xA1)) && (IsNumberClose(r[i + 1][0], t[t.length - 1][0] + t[t.length - 1][3]))) {
                                    v = i + 1;
                                } else {
                                    for(var j = 0; j < r.length; j++) {
                                        if(((r[j][2] == 0x21) || (r[j][2] == 0xA1)) && (IsNumberClose(r[j][0], t[t.length - 1][0] + t[t.length - 1][3]) && (r[j][1] == t[t.length - 1][1]))) {
                                            v = j;
                                            break;
                                        }
                                    }
                                }
                                if(v == -1) {
                                    for(var j = 0; j < r.length; j++) {
                                        if(((r[j][2] == 0x21) || (r[j][2] == 0xA1)) && (IsNumberClose(r[j][0], t[t.length - 1][0] + t[t.length - 1][3]))) {
                                            v = j;
                                            break;
                                        }
                                    }
                                }
                                return v;
                            })();
                            if(n != -1) {
                                t.push(r[n]);
                                r.splice(n, 1);
                                if(n < i) {
                                    i -= 1;
                                }
                            } else {
                                e = true;
                            }
                            if((t[t.length - 1][2] == 0xA1) || (t[t.length - 1][2] == 0xA2) || e) {
                                break;
                            }
                        }
                        if((t[t.length - 1][2] == 0x61) || (t[t.length - 1][2] == 0x21)) {
                            var n = (function () {
                                var v = -1;
                                if((i + 1 < r.length) && ((r[i + 1][2] == 0x22) || (r[i + 1][2] == 0xA2)) && (IsNumberClose(r[i + 1][0], t[t.length - 1][0])) && (r[i + 1][1] == t[t.length - 1][1] + t[t.length - 1][3])) {
                                    v = i + 1;
                                } else {
                                    for(var j = 0; j < r.length; j++) {
                                        if(((r[j][2] == 0x22) || (r[j][2] == 0xA2)) && (IsNumberClose(r[j][0], t[t.length - 1][0])) && (r[j][1] == t[t.length - 1][1] + t[t.length - 1][3])) {
                                            v = j;
                                            break;
                                        }
                                    }
                                }
                                return v;
                            })();
                            if(n != -1) {
                                t.push(r[n]);
                                r.splice(n, 1);
                                if(n < i) {
                                    i -= 1;
                                }
                            } else {
                                e = true;
                            }
                            if((t[t.length - 1][2] == 0xA1) || (t[t.length - 1][2] == 0xA2) || e) {
                                break;
                            }
                        }
                    }
                    if(t.length != 1) {
                        h.push(r[i]);
                        r.splice(i, 1);
                        i -= 1;
                        t[t.length - 1][2] = (t[t.length - 1][2] == 0x21 ? 0xA1 : t[t.length - 1][2]);
                        t[t.length - 1][2] = (t[t.length - 1][2] == 0x22 ? 0xA2 : t[t.length - 1][2]);
                        d.push(t);
                    }
                }
            }
        };
        extract();
        for(var i = 0; i < r.length; i++) {
            switch(r[i][2]) {
                case 0x61:
                case 0x22:
                case 0xA1:
                    break;
                case 0x21:
                    r[i][2] = 0x61;
                    break;
                case 0x62:
                case 0xA2:
                    r[i][2] = 0x22;
                    break;
            }
        }
        extract();
        r.write(h);
        r = unrepeatActionData(r).sort(SortNumbers);
        for(var i = 0; i < r.length; i++) {
            if((r[i][2] == 0x61) || (r[i][2] == 0x62)) {
                for(var j = 0; j < d.length; j++) {
                    if((d[j][0][0] == r[i][0]) && (d[j][0][1] == r[i][1]) && (d[j][0][2] == r[i][2]) && (d[j][0][3] == r[i][3])) {
                        r.splice(i, 1);
                        r.insert(d[j], i);
                        d.splice(j, 1);
                        break;
                    }
                }
            }
        }
        for(var i = 0; i < r.length; i++) {
            switch(r[i][2]) {
                case 0x61:
                    var lostEnd = !((i != r.length - 1) && ((r[i + 1][2] == 0x22) || (r[i + 1][2] == 0xA2)));
                    if(lostEnd) {
                        r.splice(i, 1);
                        i -= 1;
                    }
                    break;
                case 0x62:
                    var lostEnd = !((i != r.length - 1) && ((r[i + 1][2] == 0x21) || (r[i + 1][2] == 0xA1)));
                    if(lostEnd) {
                        r[i][2] = 0x02;
                    }
                    break;
                case 0x21:
                    var lostStart = !((i != 0) && ((r[i - 1][2] == 0x62) || (r[i - 1][2] == 0x22)));
                    var lostEnd = !((i != r.length - 1) && ((r[i + 1][2] == 0x22) || (r[i + 1][2] == 0xA2)));
                    if((lostStart) && (!lostEnd)) {
                        r[i][2] = 0x61;
                    } else if((!lostStart) && (lostEnd)) {
                        r.splice(i, 1);
                        i -= 1;
                    } else if((lostStart) && (lostEnd)) {
                        r.splice(i, 1);
                        i -= 1;
                    }
                    break;
                case 0x22:
                    var lostStart = !((i != 0) && ((r[i - 1][2] == 0x61) || (r[i - 1][2] == 0x21)));
                    var lostEnd = !((i != r.length - 1) && ((r[i + 1][2] == 0x21) || (r[i + 1][2] == 0xA1)));
                    if((lostStart) && (!lostEnd)) {
                        r[i][2] = 0x62;
                    } else if((!lostStart) && (lostEnd)) {
                        r[i][2] = 0xA2;
                    } else if((lostStart) && (lostEnd)) {
                        r[i][2] = 0x02;
                    }
                    break;
                case 0xA1:
                    var lostStart = !((i != 0) && ((r[i - 1][2] == 0x62) || (r[i - 1][2] == 0x22)));
                    if(lostStart) {
                        r[i][2] = 0x01;
                    }
                    break;
                case 0xA2:
                    var lostStart = !((i != 0) && ((r[i - 1][2] == 0x61) || (r[i - 1][2] == 0x21)));
                    if(lostStart) {
                        r[i][2] = 0x02;
                    }
                    break;
            }
        }
        return r;
    };
    var toActionDataTransTrack = function (a, track) {
        var track2 = (arguments.length > 2 ? arguments[2] : track);
        var r = copyActionData(a);
        var key = getActionDataKey(a);
        if(key != track) {
            for(var i = 0; i < r.length; i++) {
                switch(r[i][2]) {
                    case 0x00:
                    case 0x02:
                        r[i][1] = Math.floor((r[i][1] + 0.5) / (key / track));
                        break;
                }
            }
        }
        if(key != track2) {
            for(var i = 0; i < r.length; i++) {
                switch(r[i][2]) {
                    case 0x01:
                    case 0x61:
                    case 0x62:
                    case 0x21:
                    case 0x22:
                    case 0xA1:
                    case 0xA2:
                        var t = r[i][1];
                        r[i][1] = Math.floor((r[i][1] + 0.5) / (key / track2));
                        switch(r[i][2]) {
                            case 0x01:
                            case 0x61:
                            case 0x21:
                            case 0xA1:
                                r[i][3] = Math.floor((t + r[i][3] + 0.5) / (key / track2)) - r[i][1];
                                if((r[i][2] == 0x01) && (r[i][3] == 0)) {
                                    r[i][2] = 0x00;
                                }
                                break;
                        }
                        break;
                }
            }
        }
        if((key != track) || (key != track2)) {
            r = unrepeatActionData(r);
        }
        return r;
    };
    var toActionDataRandomTrack = function (a) {
        var r = [];
        var key = getActionDataKey(a);
        var newTracks = (function () {
            var v = [];
            var tracks = (function () {
                var v = [];
                for(var i = 0; i < key; i++) {
                    v.push(i);
                }
                return v;
            })();
            for(var i = 0; i < key; i++) {
                var n = Math.floor(Math.random() * tracks.length);
                v.push(tracks[n]);
                tracks.splice(n, 1);
            }
            return v;
        })();
        for(var i = 0; i < a.length; i++) {
            switch(a[i][2]) {
                case 0x00:
                case 0x01:
                case 0x02:
                case 0x62:
                case 0x22:
                case 0xA2:
                    r.push([a[i][0], newTracks[a[i][1]], a[i][2], a[i][3]]);
                    break;
                case 0x61:
                case 0x21:
                case 0xA1:
                    r.push([a[i][0], newTracks[a[i][1]], a[i][2], newTracks[a[i][1] + a[i][3]] - newTracks[a[i][1]]]);
                    break;
            }
        }
        return r;
    };
    var toActionDataRandomNote = function (a) {
        var r = [];
        var key = getActionDataKey(a);
        var list = (function () {
            var v = [];
            var indexOf = function (t) {
                for(var i = 0; i < v.length; i++) {
                    if(IsNumberClose(v[i][0], t)) {
                        return i;
                    }
                }
                return-1;
            };
            for(var i = 0; i < a.length; i++) {
                var l = indexOf(a[i][0]);
                if(l != -1) {
                    v[l][1].push([a[i][2], a[i][3]]);
                } else {
                    v.push([a[i][0], [
                        [a[i][2], a[i][3]]
                    ]]);
                }
            }
            return v;
        })();
        var newTrack = function (t) {
            var n = (arguments.length > 1 ? arguments[1] : -1);
            var occupys = (function () {
                var v = [];
                var setValue = function (n, a, b) {
                    if(n == -1) {
                        for(var k = a; k < b + 1; k++) {
                            v[k] = true;
                        }
                    } else {
                        if((a == b) && (n == b)) {
                            v[n] = true;
                        } else if(n >= b) {
                            for(var k = 0; k < b + 1; k++) {
                                v[k] = true;
                            }
                        } else if(n <= a) {
                            for(var k = a; k < key; k++) {
                                v[k] = true;
                            }
                        } else {
                            for(var k = 0; k < key; k++) {
                                v[k] = true;
                            }
                        }
                    }
                };
                for(var j = 0; j < r.length; j++) {
                    switch(r[j][2]) {
                        case 0x00:
                            if(IsNumberClose(r[j][0], t)) {
                                setValue(n, r[j][1], r[j][1]);
                            }
                            break;
                        case 0x01:
                        case 0x61:
                        case 0x21:
                        case 0xA1:
                            if(IsNumberClose(r[j][0], t)) {
                                setValue(n, Math.min(r[j][1], r[j][1] + r[j][3]), Math.max(r[j][1], r[j][1] + r[j][3]));
                            }
                            break;
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            if(IsNumberClose(r[j][0], t) || ((r[j][0] < t) && (r[j][0] + r[j][3] > t)) || IsNumberClose(r[j][0] + r[j][3], t)) {
                                setValue(n, r[j][1], r[j][1]);
                            }
                            break;
                    }
                }
                return v;
            })();
            var tracks = (function () {
                var v = [];
                for(var j = 0; j < key; j++) {
                    if(typeof occupys[j] === "undefined") {
                        v.push(j);
                    }
                }
                return v;
            })();
            if(tracks.length == 0) {
                return-1;
            }
            if(n != -1) {
                var tracksT = [];
                if((n > 0) && (tracks.indexOf(n - 1) != -1)) {
                    tracksT.push(n - 1);
                }
                if((n < key - 1) && (tracks.indexOf(n + 1) != -1)) {
                    tracksT.push(n + 1);
                }
                if(tracksT.length > 0) {
                    return tracksT[Math.floor(Math.random() * tracksT.length)];
                }
            }
            return tracks[Math.floor(Math.random() * tracks.length)];
        };
        var c = [];
        for(var i = 0; i < list.length; i++) {
            for(var j = 0; j < list[i][1].length; j++) {
                switch(list[i][1][j][0]) {
                    case 0x00:
                    case 0x02:
                    case 0x62:
                        var t = newTrack(list[i][0]);
                        if(t != -1) {
                            r.push([list[i][0], t, list[i][1][j][0], list[i][1][j][1]]);
                        }
                        break;
                    case 0x61:
                        var t1 = newTrack(list[i][0]);
                        if(t1 != -1) {
                            var t2 = newTrack(list[i][0], t1);
                            if(t1 != t2) {
                                r.push([list[i][0], t1, list[i][1][j][0], t2 - t1]);
                            }
                        }
                        break;
                    case 0x21:
                    case 0xA1:
                        var n = (function () {
                            for(var l = 0; l < r.length; l++) {
                                switch(r[l][2]) {
                                    case 0x62:
                                    case 0x22:
                                        if((typeof c[l] === "undefined") && IsNumberClose(r[l][0] + r[l][3], list[i][0])) {
                                            c[l] = true;
                                            return l;
                                        }
                                        break;
                                }
                            }
                            return-1;
                        })();
                        if(n != -1) {
                            var t1 = r[n][1];
                            var t2 = newTrack(list[i][0], t1);
                            if(t1 != t2) {
                                r.push([list[i][0], t1, list[i][1][j][0], t2 - t1]);
                            } else {
                                switch(r[n][2]) {
                                    case 0x62:
                                        r[n][2] = 0x02;
                                        break;
                                    case 0x22:
                                        r[n][2] = 0xA2;
                                        break;
                                }
                            }
                        } else {
                            var t1 = newTrack(list[i][0]);
                            if(t1 != -1) {
                                var t2 = newTrack(list[i][0], t1);
                                if(t1 != t2) {
                                    switch(list[i][1][j][0]) {
                                        case 0x21:
                                            r.push([list[i][0], t1, 0x61, t2 - t1]);
                                            break;
                                        case 0xA1:
                                            r.push([list[i][0], t1, 0x01, t2 - t1]);
                                            break;
                                    }
                                } else {
                                    r.push([list[i][0], t1, 0x00, t2 - t1]);
                                }
                            }
                        }
                        break;
                    case 0x22:
                    case 0xA2:
                        var n = (function () {
                            for(var l = 0; l < r.length; l++) {
                                switch(r[l][2]) {
                                    case 0x61:
                                    case 0x21:
                                        if((typeof c[l] === "undefined") && IsNumberClose(r[l][0], list[i][0])) {
                                            c[l] = true;
                                            return l;
                                        }
                                        break;
                                }
                            }
                            return-1;
                        })();
                        if(n != -1) {
                            r.push([list[i][0], r[n][1] + r[n][3], list[i][1][j][0], list[i][1][j][1]]);
                        } else {
                            var t = newTrack(list[i][0]);
                            if(t != -1) {
                                switch(list[i][1][j][0]) {
                                    case 0x22:
                                        r.push([list[i][0], t, 0x62, list[i][1][j][1]]);
                                        break;
                                    case 0xA2:
                                        r.push([list[i][0], t, 0x02, list[i][1][j][1]]);
                                        break;
                                }
                            }
                        }
                        break;
                    case 0x01:
                        var t1 = newTrack(list[i][0]);
                        if(t1 != -1) {
                            var t2 = newTrack(list[i][0], t1);
                            if(t1 != t2) {
                                r.push([list[i][0], t1, list[i][1][j][0], t2 - t1]);
                            } else {
                                r.push([list[i][0], t1, 0x00, t2 - t1]);
                            }
                        }
                        break;
                }
            }
        }
        return sortActionData(r);
    };
    var toActionDataMirrorTrack = function (a) {
        var r = copyActionData(a);
        var key = getActionDataKey(r);
        for(var i = 0; i < r.length; i++) {
            r[i][1] = key - 1 - r[i][1];
            switch(r[i][2]) {
                case 0x01:
                case 0x61:
                case 0x21:
                case 0xA1:
                    r[i][3] = 0 - r[i][3];
                    break;
            }
        }
        return r;
    };
    var toActionDataLinkSingle = function (a) {
        var r = copyActionData(a);
        var u = 60000 / Data[Index]["BPM"] / 4;
        var m = [1 / 3, 1 / 2, 2 / 3, 1, 4 / 3, 3 / 2, 5 / 3, 2];
        for(var i = 0; i < m.length; i++) {
            for(var j = 0; j < r.length; j++) {
                if((r[j][2] == 0x00) || ((r[j][2] == 0x01) && (r[j][3] == 0))) {
                    var t = [];
                    var n = j;
                    for(var k = n + 1; k < r.length; k++) {
                        if((r[k][2] == 0x00) || ((r[k][2] == 0x01) && (r[k][3] == 0))) {
                            if(IsNumberClose(r[k][0] - r[n][0], u * m[i])) {
                                var isNext = (function () {
                                    if(Math.abs(r[k][1] - r[n][1]) != 1) {
                                        return true;
                                    } else {
                                        var e = (function () {
                                            for(var l = n + 1; l < r.length; l++) {
                                                if(!IsNumberClose(r[l][0], r[k][0]) && (r[l][0] > r[k][0])) {
                                                    return l;
                                                }
                                            }
                                            return r.length - 1;
                                        })();
                                        for(var l = n + 1; l < e; l++) {
                                            if(IsNumberClose(r[l][0], r[k][0]) && (r[l][1] == r[n][1])) {
                                                return true;
                                            }
                                        }
                                    }
                                    return false;
                                })();
                                if(isNext) {
                                    continue;
                                } else {
                                    t.push([r[n][0], r[n][1]]);
                                    r.splice(n, 1);
                                    k -= 1;
                                    n = k;
                                    if(n == r.length - 1) {
                                        t.push([r[n][0], r[n][1]]);
                                        r.splice(n, 1);
                                    }
                                }
                            } else {
                                if(r[k][0] - r[n][0] > u * m[i]) {
                                    if(t.length != 0) {
                                        t.push([r[n][0], r[n][1]]);
                                        r.splice(n, 1);
                                        k -= 1;
                                    }
                                    break;
                                }
                            }
                        } else if(k == r.length - 1) {
                            if(t.length != 0) {
                                t.push([r[n][0], r[n][1]]);
                                r.splice(n, 1);
                            }
                        }
                    }
                    if(t.length != 0) {
                        var d = [];
                        for(var k = 1; k < t.length; k++) {
                            d.push([t[k - 1][0], t[k - 1][1], 0x22, t[k][0] - t[k - 1][0]]);
                            d.push([t[k][0], t[k - 1][1], 0x21, t[k][1] - t[k - 1][1]]);
                        }
                        d[0][2] = 0x62;
                        d[d.length - 1][2] = 0xA1;
                        r.insert(d, j);
                        j += d.length - 1;
                    }
                }
            }
        }
        return r;
    };
    var toActionDataLinkLong = function (a) {
        var r = copyActionData(a);
        for(var i = 0; i < r.length; i++) {
            if((r[i][2] == 0x02) || (r[i][2] == 0x62)) {
                for(var j = 0; j < r.length; j++) {
                    if((r[j][2] == 0x02) || (r[j][2] == 0xA2)) {
                        if((IsNumberClose(r[i][0], r[j][0] + r[j][3])) && (Math.abs(r[i][1] - r[j][1]) == 1)) {
                            switch(r[j][2]) {
                                case 0x02:
                                    r[j][2] = 0x62;
                                    break;
                                case 0xA2:
                                    r[j][2] = 0x22;
                                    break;
                            }
                            switch(r[i][2]) {
                                case 0x02:
                                    r[i][2] = 0xA2;
                                    break;
                                case 0x62:
                                    r[i][2] = 0x22;
                                    break;
                            }
                            var t = [];
                            t.push([r[j][0] + r[j][3], r[j][1], 0x21, r[i][1] - r[j][1]]);
                            var b = false;
                            while(true) {
                                for(var k = i; k < r.length; k++) {
                                    if((r[k][2] == 0x21) || (r[k][2] == 0xA1)) {
                                        if(t[t.length - 1][2] == 0x22) {
                                            if((IsNumberClose(t[t.length - 1][0] + t[t.length - 1][3], r[k][0])) && (t[t.length - 1][1] == r[k][1])) {
                                                t.push(r[k]);
                                                r.splice(k, 1);
                                                i -= 1;
                                                break;
                                            }
                                        }
                                    }
                                    if((r[k][2] == 0x22) || (r[k][2] == 0xA2)) {
                                        if(t[t.length - 1][2] == 0x21) {
                                            if((IsNumberClose(t[t.length - 1][0], r[k][0])) && (t[t.length - 1][1] + t[t.length - 1][3] == r[k][1])) {
                                                t.push(r[k]);
                                                r.splice(k, 1);
                                                i -= 1;
                                                break;
                                            }
                                        }
                                    }
                                    if(k == r.length - 1) {
                                        b = true;
                                    }
                                }
                                if((t[t.length - 1][2] == 0xA1) || (t[t.length - 1][2] == 0xA2) || b) {
                                    break;
                                }
                            }
                            r.insert(t, j + 1);
                            j += r.length;
                            break;
                        }
                    }
                }
            }
        }
        return r;
    };
    var toActionDataLinkSilde = function (a) {
        var r = copyActionData(a);
        for(var i = 0; i < r.length; i++) {
            if((r[i][2] == 0x00) || ((r[i][2] == 0x01) && (r[i][3] == 0))) {
                for(var j = 0; j < r.length; j++) {
                    if((r[j][2] == 0x02) || (r[j][2] == 0x62)) {
                        if((IsNumberClose(r[i][0], r[j][0])) && (Math.abs(r[i][1] - r[j][1]) == 1)) {
                            r[i][2] = 0x61;
                            r[i][3] = r[j][1] - r[i][1];
                            switch(r[j][2]) {
                                case 0x02:
                                    r[j][2] = 0xA2;
                                    break;
                                case 0x62:
                                    r[j][2] = 0x22;
                                    break;
                            }
                            break;
                        }
                    }
                }
            }
        }
        for(var i = 0; i < r.length; i++) {
            if((r[i][2] == 0x00) || ((r[i][2] == 0x01) && (r[i][3] == 0))) {
                for(var j = 0; j < r.length; j++) {
                    if((r[j][2] == 0x02) || (r[j][2] == 0xA2)) {
                        if((IsNumberClose(r[i][0], r[j][0] + r[j][3])) && (Math.abs(r[i][1] - r[j][1]) == 1)) {
                            r[i][2] = 0xA1;
                            r[i][3] = r[i][1] - r[j][1];
                            r[i][1] = r[j][1];
                            switch(r[j][2]) {
                                case 0x02:
                                    r[j][2] = 0x62;
                                    break;
                                case 0xA2:
                                    r[j][2] = 0x22;
                                    break;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return sortActionData(r);
    };
    var toActionDataTransOblique = function (a) {
        var r = copyActionData(a);
        for(var i = 0; i < r.length; i++) {
            if((r[i][2] == 0x62) || (r[i][2] == 0x22)) {
                if((i != r.length - 1) && ((r[i + 1][2] == 0x21) || (r[i + 1][2] == 0xA1)) && (IsNumberClose(r[i][0] + r[i][3], r[i + 1][0])) && (r[i][1] == r[i + 1][1])) {
                    r[i + 1][1] = r[i + 1][1] + r[i + 1][3];
                    r[i + 1][3] = 0;
                }
            }
        }
        return r;
    };
    var toActionDataNoOblique = function (a) {
        var r = copyActionData(a);
        for(var i = 0; i < r.length; i++) {
            if((r[i][2] == 0x62) || (r[i][2] == 0x22)) {
                if((i != r.length - 1) && ((r[i + 1][2] == 0x21) || (r[i + 1][2] == 0xA1)) && (IsNumberClose(r[i][0] + r[i][3], r[i + 1][0]))) {
                    r[i + 1][3] = r[i + 1][1] - r[i][1];
                    r[i + 1][1] = r[i][1];
                }
            }
        }
        return r;
    };
    var toActionDataNoSlide = function (a) {
        var r = copyActionData(a);
        for(var i = 0; i < r.length; i++) {
            switch(r[i][2]) {
                case 0x01:
                case 0xA1:
                    r[i] = [r[i][0], r[i][1] + r[i][3], 0x00, 0];
                    break;
                case 0x61:
                case 0x21:
                    r.splice(i, 1);
                    i -= 1;
                    break;
                case 0x62:
                case 0x22:
                case 0xA2:
                    r[i] = [r[i][0], r[i][1], 0x02, r[i][3]];
                    break;
            }
        }
        return r;
    };
    var toActionDataNoLong = function (a) {
        var r = copyActionData(a);
        for(var i = 0; i < r.length; i++) {
            switch(r[i][2]) {
                case 0x02:
                case 0x61:
                case 0x62:
                case 0x21:
                case 0x22:
                case 0xA2:
                    r.splice(i, 1);
                    i -= 1;
                    break;
                case 0xA1:
                    r[i] = [r[i][0], r[i][1], 0x01, r[i][3]];
                    break;
            }
        }
        return r;
    };
    var toActionDataNoSingle = function (a) {
        var r = copyActionData(a);
        for(var i = 0; i < r.length; i++) {
            switch(r[i][2]) {
                case 0x00:
                    r.splice(i, 1);
                    i -= 1;
                    break;
            }
        }
        return r;
    };
    var toActionDataNoCross = function (a) {
        var r = copyActionData(a);
        var getPoint = function (a, b, c, d) {
            var isLine = (arguments.length > 4 ? arguments[4] : false);
            var abc = (a[0] - c[0]) * (b[1] - c[1]) - (a[1] - c[1]) * (b[0] - c[0]);
            var abd = (a[0] - d[0]) * (b[1] - d[1]) - (a[1] - d[1]) * (b[0] - d[0]);
            if(isLine) {
                return(abc * abd < 0);
            }
            var cda = (c[0] - a[0]) * (d[1] - a[1]) - (c[1] - a[1]) * (d[0] - a[0]);
            var cdb = (c[0] - b[0]) * (d[1] - b[1]) - (c[1] - b[1]) * (d[0] - b[0]);
            if((abc * abd < 0) && (cda * cdb < 0)) {
                var t = cda / (abd - abc);
                return[a[0] + (b[0] - a[0]) * t, a[1] + (b[1] - a[1]) * t];
            }
            return false;
        };
        var isCross = function (i, j) {
            switch(r[i][2]) {
                case 0x01:
                case 0x61:
                case 0x21:
                case 0xA1:
                    switch(r[j][2]) {
                        case 0x00:
                            if(IsNumberClose(r[j][0], r[i][0]) && (r[j][1] > Math.min(r[i][1], r[i][1] + r[i][3])) && (r[j][1] < Math.max(r[i][1], r[i][1] + r[i][3]))) {
                                return true;
                            }
                            break;
                        case 0x01:
                        case 0x61:
                        case 0x21:
                        case 0xA1:
                            if((IsNumberClose(r[j][0], r[i][0])) && (!((Math.min(r[j][1], r[j][1] + r[j][3]) > Math.max(r[i][1], r[i][1] + r[i][3])) || (Math.max(r[j][1], r[j][1] + r[j][3]) < Math.min(r[i][1], r[i][1] + r[i][3]))))) {
                                return true;
                            }
                            break;
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            if((r[j][0] + r[j][3] > r[i][0]) && (r[j][0] < r[i][0])) {
                                var o = 0;
                                if((j + 1 < r.length) && ((r[j][2] == 0x62) || (r[j][2] == 0x22)) && ((r[j + 1][2] == 0x21) || (r[j + 1][2] == 0xA1))) {
                                    o = r[j + 1][1] - r[j][1];
                                }
                                var p = getPoint([r[i][1], r[i][0]], [r[i][1] + r[i][3], r[i][0]], [r[j][1], r[j][0]], [r[j][1] + o, r[j][0] + r[j][3]]);
                                var x = r[j][1] + o / r[j][3] * (r[i][0] - r[j][0]);
                                if(p && (x != r[i][1]) && (x != r[i][1] + r[i][3])) {
                                    return true;
                                } else {
                                    var p1 = [];
                                    var p2 = [];
                                    if((x == r[i][1]) && (x == r[i][1] + r[i][3])) {
                                        if((r[i][2] == 0x21) && (i > 0) && ((r[i - 1][2] == 0x62) || (r[i - 1][2] == 0x22)) && (i + 1 < r.length) && ((r[i + 1][2] == 0x22) || (r[i + 1][2] == 0xA2))) {
                                            p1 = [r[i - 1][1], r[i - 1][0]];
                                            if(r[i + 1][2] == 0xA2) {
                                                p2 = [r[i + 1][1], r[i + 1][0] + r[i + 1][3]];
                                            } else if((r[i + 1][2] == 0x22) && (i + 2 < r.length) && ((r[i + 2][2] == 0x21) || (r[i + 2][2] == 0xA1))) {
                                                p2 = [r[i + 2][1], r[i + 2][0]];
                                            }
                                        }
                                    } else if(x == r[i][1]) {
                                        if(((r[i][2] == 0x21) || (r[i][2] == 0xA1)) && (i > 0) && ((r[i - 1][2] == 0x62) || (r[i - 1][2] == 0x22))) {
                                            p1 = [r[i - 1][1], r[i - 1][0]];
                                            p2 = [r[i][1] + r[i][3], r[i][0]];
                                        }
                                    } else if(x == r[i][1] + r[i][3]) {
                                        if(((r[i][2] == 0x61) || (r[i][2] == 0x21)) && (i + 1 < r.length) && ((r[i + 1][2] == 0x22) || (r[i + 1][2] == 0xA2))) {
                                            p1 = [r[i][1], r[i][0]];
                                            if(r[i + 1][2] == 0xA2) {
                                                p2 = [r[i + 1][1], r[i + 1][0] + r[i + 1][3]];
                                            } else if((r[i + 1][2] == 0x22) && (i + 2 < r.length) && ((r[i + 2][2] == 0x21) || (r[i + 2][2] == 0xA1))) {
                                                p2 = [r[i + 2][1], r[i + 2][0]];
                                            }
                                        }
                                    }
                                    return getPoint([r[j][1], r[j][0]], [r[j][1] + o, r[j][0] + r[j][3]], p1, p2, true);
                                }
                            }
                            break;
                    }
                    break;
                case 0x02:
                case 0x62:
                case 0x22:
                case 0xA2:
                    var o1 = 0;
                    if((i + 1 < r.length) && ((r[i][2] == 0x62) || (r[i][2] == 0x22)) && ((r[i + 1][2] == 0x21) || (r[i + 1][2] == 0xA1))) {
                        o1 = r[i + 1][1] - r[i][1];
                    }
                    switch(r[j][2]) {
                        case 0x01:
                        case 0x61:
                        case 0x21:
                        case 0xA1:
                            if((r[j][0] < r[i][0] + r[i][3]) && (r[j][0] > r[i][0])) {
                                var p = getPoint([r[i][1], r[i][0]], [r[i][1] + o1, r[i][0] + r[i][3]], [r[j][1], r[j][0]], [r[j][1] + r[j][3], r[j][0]]);
                                var x = r[i][1] + o1 / r[i][3] * (r[j][0] - r[i][0]);
                                if(p && (x != r[j][1]) && (x != r[j][1] + r[j][3])) {
                                    return true;
                                } else {
                                    var p1 = [];
                                    var p2 = [];
                                    if((x == r[j][1]) && (x == r[j][1] + r[j][3])) {
                                        if((r[j][2] == 0x21) && (j > 0) && ((r[j - 1][2] == 0x62) || (r[j - 1][2] == 0x22)) && (j + 1 < r.length) && ((r[j + 1][2] == 0x22) || (r[j + 1][2] == 0xA2))) {
                                            p1 = [r[j - 1][1], r[j - 1][0]];
                                            if(r[j + 1][2] == 0xA2) {
                                                p2 = [r[j + 1][1], r[j + 1][0] + r[j + 1][3]];
                                            } else if((r[j + 1][2] == 0x22) && (j + 2 < r.length) && ((r[j + 2][2] == 0x21) || (r[j + 2][2] == 0xA1))) {
                                                p2 = [r[j + 2][1], r[j + 2][0]];
                                            }
                                        }
                                    } else if(x == r[j][1]) {
                                        if(((r[j][2] == 0x21) || (r[j][2] == 0xA1)) && (j > 0) && ((r[j - 1][2] == 0x62) || (r[j - 1][2] == 0x22))) {
                                            p1 = [r[j - 1][1], r[j - 1][0]];
                                            p2 = [r[j][1] + r[j][3], r[j][0]];
                                        }
                                    } else if(x == r[j][1] + r[j][3]) {
                                        if(((r[j][2] == 0x61) || (r[j][2] == 0x21)) && (j + 1 < r.length) && ((r[j + 1][2] == 0x22) || (r[j + 1][2] == 0xA2))) {
                                            p1 = [r[j][1], r[j][0]];
                                            if(r[j + 1][2] == 0xA2) {
                                                p2 = [r[j + 1][1], r[j + 1][0] + r[j + 1][3]];
                                            } else if((r[j + 1][2] == 0x22) && (j + 2 < r.length) && ((r[j + 2][2] == 0x21) || (r[j + 2][2] == 0xA1))) {
                                                p2 = [r[j + 2][1], r[j + 2][0]];
                                            }
                                        }
                                    }
                                    return getPoint([r[i][1], r[i][0]], [r[i][1] + o1, r[i][0] + r[i][3]], p1, p2, true);
                                }
                            }
                            break;
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            if((r[j][0] < r[i][0] + r[i][3]) && (r[j][0] + r[j][3] > r[i][0])) {
                                var o2 = 0;
                                if((j + 1 < r.length) && ((r[j][2] == 0x62) || (r[j][2] == 0x22)) && ((r[j + 1][2] == 0x21) || (r[j + 1][2] == 0xA1))) {
                                    o2 = r[j + 1][1] - r[j][1];
                                }
                                var p = getPoint([r[j][1], r[j][0]], [r[j][1] + o2, r[j][0] + r[j][3]], [r[i][1], r[i][0]], [r[i][1] + o1, r[i][0] + r[i][3]]);
                                if((p) && (((r[i][2] == 0x22) && (r[j][2] == 0x22)) || (!(((p[0] == r[i][1]) && IsNumberClose(p[1], r[i][0])) || ((p[0] == r[i][1] + o1) && IsNumberClose(p[1], r[i][0] + r[i][3])))))) {
                                    return true;
                                }
                            }
                            break;
                    }
                    break;
            }
            return false;
        };
        var doSplice = function (j) {
            switch(r[j][2]) {
                case 0x62:
                case 0x22:
                    if((j != r.length - 1)) {
                        switch(r[j + 1][2]) {
                            case 0x21:
                            case 0xA1:
                                if((j != r.length - 2)) {
                                    switch(r[j + 2][2]) {
                                        case 0x22:
                                            r[j + 2][2] = 0x62;
                                            break;
                                        case 0xA2:
                                            r[j + 2][2] = 0x02;
                                            break;
                                    }
                                }
                                r.splice(j + 1, 1);
                                break;
                        }
                    }
                    break;
            }
            switch(r[j][2]) {
                case 0x22:
                case 0xA2:
                    if((j != 0)) {
                        switch(r[j - 1][2]) {
                            case 0x61:
                                r.splice(j - 1, 1);
                                break;
                            case 0x21:
                                r[j - 1][2] = 0xA1;
                                break;
                        }
                    }
                    break;
            }
            r.splice(j, 1);
        };
        for(var i = 0; i < r.length; i++) {
            for(var j = 0; j < r.length; j++) {
                if((j != i) && isCross(i, j)) {
                    if(r[i][2] == 0x02) {
                        doSplice(i);
                        i -= 1;
                        break;
                    } else {
                        doSplice(j);
                        j -= 1;
                    }
                }
            }
        }
        return sortActionData(r);
    };
    var toActionDataNoOverlap = function (a) {
        var r = copyActionData(a);
        var isOverlap = function (i, j) {
            switch(r[i][2]) {
                case 0x00:
                    switch(r[j][2]) {
                        case 0x00:
                            if(IsNumberClose(r[j][0], r[i][0]) && (r[j][1] == r[i][1])) {
                                return true;
                            }
                            break;
                        case 0x01:
                        case 0x61:
                        case 0x21:
                        case 0xA1:
                            if(IsNumberClose(r[j][0], r[i][0]) && ((r[j][1] == r[i][1]) || (r[j][1] + r[j][3] == r[i][1]))) {
                                return true;
                            }
                            break;
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            var o = 0;
                            if((j + 1 < r.length) && ((r[j][2] == 0x62) || (r[j][2] == 0x22)) && ((r[j + 1][2] == 0x21) || (r[j + 1][2] == 0xA1))) {
                                o = r[j + 1][1] - r[j][1];
                            }
                            if((IsNumberClose(r[i][0], r[j][0]) || ((r[i][0] >= r[j][0]) && (r[i][0] <= r[j][0] + r[j][3])) || IsNumberClose(r[i][0], r[j][0] + r[j][3])) && (r[i][1] == r[j][1])) {
                                if(r[i][1] - r[j][1] == (r[i][0] - r[j][0]) / r[j][3] * o) {
                                    return true;
                                }
                            }
                            break;
                    }
                    break;
                case 0x01:
                case 0x61:
                case 0x21:
                case 0xA1:
                    switch(r[j][2]) {
                        case 0x00:
                            if(IsNumberClose(r[j][0], r[i][0]) && ((r[j][1] == r[i][1]) || (r[j][1] == r[i][1] + r[i][3]))) {
                                return true;
                            }
                            break;
                        case 0x01:
                        case 0x61:
                        case 0x21:
                        case 0xA1:
                            if(IsNumberClose(r[j][0], r[i][0]) && ((r[j][1] == r[i][1]) || (r[j][1] == r[i][1] + r[i][3]) || (r[j][1] + r[j][3] == r[i][1]) || (r[j][1] + r[j][3] == r[i][1] + r[i][3]))) {
                                return true;
                            }
                            break;
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            if(((r[i][2] == 0x61) || (r[i][2] == 0x21)) && ((r[j][2] == 0x22) || (r[j][2] == 0xA2))) {
                                if(IsNumberClose(r[i][0], r[j][0]) && (j - i == 1)) {
                                    return false;
                                }
                            } else if(((r[j][2] == 0x62) || (r[j][2] == 0x22)) && ((r[i][2] == 0x21) || (r[i][2] == 0xA1))) {
                                if((IsNumberClose(r[i][0], r[j][0] + r[j][3])) && (i - j == 1)) {
                                    return false;
                                }
                            } else if((IsNumberClose(r[i][0], r[j][0]) || ((r[i][0] > r[j][0]) && (r[i][0] < r[j][0] + r[j][3])) || IsNumberClose(r[i][0], r[j][0] + r[j][3]))) {
                                var o = 0;
                                if((j + 1 < r.length) && ((r[j][2] == 0x62) || (r[j][2] == 0x22)) && ((r[j + 1][2] == 0x21) || (r[j + 1][2] == 0xA1))) {
                                    o = r[j + 1][1] - r[j][1];
                                }
                                if((r[i][1] - r[j][1] == (r[i][0] - r[j][0]) / r[j][3] * o) || (r[i][1] + r[i][3] - r[j][1] == (r[i][0] - r[j][0]) / r[j][3] * o)) {
                                    return true;
                                }
                            }
                            break;
                    }
                    break;
                case 0x02:
                case 0x62:
                case 0x22:
                case 0xA2:
                    switch(r[j][2]) {
                        case 0x00:
                            var o = 0;
                            if((i + 1 < r.length) && ((r[i][2] == 0x62) || (r[i][2] == 0x22)) && ((r[i + 1][2] == 0x21) || (r[i + 1][2] == 0xA1))) {
                                o = r[i + 1][1] - r[i][1];
                            }
                            if((IsNumberClose(r[j][0], r[i][0]) || ((r[j][0] >= r[i][0]) && (r[j][0] <= r[i][0] + r[i][3])) || IsNumberClose(r[j][0], r[i][0] + r[i][3])) && (r[j][1] == r[i][1])) {
                                if(r[j][1] - r[i][1] == (r[j][0] - r[i][0]) / r[i][3] * o) {
                                    return true;
                                }
                            }
                            break;
                        case 0x01:
                        case 0x61:
                        case 0x21:
                        case 0xA1:
                            if(((r[j][2] == 0x61) || (r[j][2] == 0x21)) && ((r[i][2] == 0x22) || (r[i][2] == 0xA2))) {
                                if(IsNumberClose(r[j][0], r[i][0]) && (i - j == 1)) {
                                    return false;
                                }
                            } else if(((r[i][2] == 0x62) || (r[i][2] == 0x22)) && ((r[j][2] == 0x21) || (r[j][2] == 0xA1))) {
                                if((IsNumberClose(r[j][0], r[i][0] + r[i][3])) && (j - i == 1)) {
                                    return false;
                                }
                            } else if((IsNumberClose(r[j][0], r[i][0]) || ((r[j][0] > r[i][0]) && (r[j][0] < r[i][0] + r[i][3])) || IsNumberClose(r[j][0], r[i][0] + r[i][3]))) {
                                var o = 0;
                                if((i + 1 < r.length) && ((r[i][2] == 0x62) || (r[i][2] == 0x22)) && ((r[i + 1][2] == 0x21) || (r[i + 1][2] == 0xA1))) {
                                    o = r[i + 1][1] - r[i][1];
                                }
                                if((r[j][1] - r[i][1] == (r[j][0] - r[i][0]) / r[i][3] * o) || (r[j][1] + r[j][3] - r[i][1] == (r[j][0] - r[i][0]) / r[i][3] * o)) {
                                    return true;
                                }
                            }
                            break;
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            var o1 = 0;
                            if((i + 1 < r.length) && ((r[i][2] == 0x62) || (r[i][2] == 0x22)) && ((r[i + 1][2] == 0x21) || (r[i + 1][2] == 0xA1))) {
                                o1 = r[i + 1][1] - r[i][1];
                            }
                            var o2 = 0;
                            if((j + 1 < r.length) && ((r[j][2] == 0x62) || (r[j][2] == 0x22)) && ((r[j + 1][2] == 0x21) || (r[j + 1][2] == 0xA1))) {
                                o2 = r[j + 1][1] - r[j][1];
                            }
                            if((o1 / r[i][3] == o2 / r[j][3]) && (!((r[i][0] + r[i][3] < r[j][0]) && !IsNumberClose(r[i][0] + r[i][3], r[j][0]) || (r[i][0] > r[j][0] + r[j][3]) && !IsNumberClose(r[i][0], r[j][0] + r[j][3])))) {
                                if((o1 == 0) && (o2 == 0) && (r[i][1] == r[j][1])) {
                                    return true;
                                } else if(r[j][1] - r[i][1] == (r[j][0] - r[i][0]) / r[i][3] * o1) {
                                    return true;
                                }
                            }
                            break;
                    }
                    break;
            }
            return false;
        };
        var doSplice = function (j) {
            switch(r[j][2]) {
                case 0x61:
                case 0x21:
                    if(j + 1 < r.length) {
                        switch(r[j + 1][2]) {
                            case 0x22:
                                r[j + 1][2] = 0x62;
                                break;
                            case 0xA2:
                                r[j + 1][2] = 0x02;
                                break;
                        }
                    }
                    break;
                case 0x62:
                case 0x22:
                    if(j + 1 < r.length) {
                        switch(r[j + 1][2]) {
                            case 0x21:
                                r[j + 1][2] = 0x61;
                                break;
                            case 0xA1:
                                r[j + 1][2] = 0x01;
                                break;
                        }
                    }
                    break;
            }
            switch(r[j][2]) {
                case 0x21:
                case 0xA1:
                    if(j != 0) {
                        switch(r[j - 1][2]) {
                            case 0x62:
                                r[j - 1][2] = 0x02;
                                break;
                            case 0x22:
                                r[j - 1][2] = 0xA2;
                                break;
                        }
                    }
                    break;
                case 0x22:
                case 0xA2:
                    if((j != 0)) {
                        switch(r[j - 1][2]) {
                            case 0x61:
                                r[j - 1][2] = 0x01;
                                break;
                            case 0x21:
                                r[j - 1][2] = 0xA1;
                                break;
                        }
                    }
                    break;
            }
            r.splice(j, 1);
        };
        for(var i = 0; i < r.length; i++) {
            for(var j = i + 1; j < r.length; j++) {
                if(isOverlap(i, j)) {
                    if((r[i][2] == 0x00) || (r[i][2] == 0x01)) {
                        if(r[j][2] == 0x00) {
                            doSplice(j);
                            j -= 1;
                        } else {
                            doSplice(j);
                            i -= 1;
                            break;
                        }
                    } else {
                        switch(r[i][2]) {
                            case 0x61:
                            case 0x21:
                            case 0xA1:
                                switch(r[j][2]) {
                                    case 0x00:
                                    case 0x01:
                                        doSplice(j);
                                        j -= 1;
                                        break;
                                }
                                break;
                            case 0x02:
                            case 0x62:
                            case 0x22:
                            case 0xA2:
                                switch(r[j][2]) {
                                    case 0x00:
                                    case 0x01:
                                        doSplice(j);
                                        j -= 1;
                                        break;
                                    case 0x02:
                                    case 0x62:
                                    case 0x22:
                                    case 0xA2:
                                        if(IsNumberClose(r[i][0] + r[i][3], r[j][0])) {
                                            var needLink = (function () {
                                                for(var k = 0; k < r.length; k++) {
                                                    switch(r[k][2]) {
                                                        case 0x21:
                                                        case 0xA1:
                                                            if(IsNumberClose(r[k][0], r[i][0] + r[i][3]) && (Math.abs(k, i))) {
                                                                return false;
                                                            }
                                                            break;
                                                    }
                                                }
                                                return true;
                                            })();
                                            if(needLink) {
                                                r[i][3] += r[j][3];
                                                doSplice(j);
                                                j -= 1;
                                            }
                                        } else {
                                            doSplice(j);
                                            j -= 1;
                                        }
                                        break;
                                }
                                break;
                        }
                    }
                }
            }
        }
        return sortActionData(r);
    };
    var toActionDataOneTrack = function (a, bpm) {
        var r = [];
        var d = (function () {
            var r = [];
            r = toActionDataNoOverlap(toActionDataNoSlide(a));
            for(var i = 0; i < r.length; i++) {
                if(r[i][2] == 0x02) {
                    if(!IsNumberClose(r[i][3], 60000 / bpm) && (r[i][3] < 60000 / bpm)) {
                        r[i][2] = 0x00;
                        r[i][3] = 0;
                    }
                }
            }
            for(var i = r.length - 1; i > 0 - 1; i--) {
                if(r[i][2] == 0x02) {
                    var isOccupy = (function () {
                        for(var j = 0; j < r.length; j++) {
                            if((j != i) && (r[j][2] == 0x00) && (r[j][0] > r[i][0]) && (r[j][0] < r[i][0] + r[i][3]) && !IsNumberClose(r[j][0], r[i][0]) && !IsNumberClose(r[j][0], r[i][0] + r[i][3])) {
                                return true;
                            }
                        }
                        return false;
                    })();
                    if(isOccupy) {
                        r[i][2] = 0x00;
                        r[i][3] = 0;
                    }
                }
            }
            for(var i = 0; i < r.length; i++) {
                if(r[i][2] == 0x02) {
                    for(var j = 0; j < r.length; j++) {
                        if((j != i) && (r[j][2] == 0x00) && (IsNumberClose(r[j][0], r[i][0]) || IsNumberClose(r[j][0], r[i][0] + r[i][3]))) {
                            r.splice(j, 1);
                            if(i > j) {
                                i -= 1;
                            }
                            j -= 1;
                        }
                    }
                }
            }
            for(var i = 0; i < r.length; i++) {
                if(r[i][2] == 0x02) {
                    for(var j = 0; j < r.length; j++) {
                        if((j != i) && (r[j][2] == 0x02) && (((r[j][0] > r[i][0]) && (r[j][0] < r[i][0] + r[i][3])) || IsNumberClose(r[j][0], r[i][0]) || IsNumberClose(r[j][0], r[i][0] + r[i][3]))) {
                            r[i][3] = Math.max(r[i][0] + r[i][3], r[j][0] + r[j][3]) - r[i][0];
                            r.splice(j, 1);
                            if(i > j) {
                                i -= 1;
                            }
                            j -= 1;
                        }
                    }
                }
            }
            return toActionDataTransTrack(r, 4);
        })();
        for(var i = 0; i < d.length; i++) {
            var t = (function () {
                var v = ["0000", "0000", "0000", "0000"];
                v[d[i][1]] = (d[i][1] == 0x00 ? (d[i][3] == "0" ? "1" : d[i][3]) : "1").toString(2).fill(4);
                for(var j = i + 1; j < d.length; j++) {
                    if(IsNumberClose(d[j][0], d[i][0])) {
                        v[d[j][1]] = (d[j][3] == "0" ? "1" : d[j][3]).toString(2).fill(4);
                    } else {
                        i = j - 1;
                        break;
                    }
                    if(j == d.length - 1) {
                        i = j;
                    }
                }
                return parseInt(v.join(""), 2);
            })();
            switch(d[i][2]) {
                case 0x00:
                    r.push([d[i][0], 0, 0x00, t]);
                    break;
                case 0x02:
                    r.push([d[i][0], 0, 0x02, t]);
                    r.push([d[i][0] + d[i][3], 0, 0x02, t]);
                    break;
            }
        }
        return r;
    };
    var toActionDataSquareLong = function (a, bpm) {
        var squareX = (arguments.length > 2 ? arguments[2] : 4);
        var squareY = (arguments.length > 3 ? arguments[3] : 4);
        var r = [];
        if((squareX > 8) || (squareX < 2) || (squareY < 2)) {
            return r;
        }
        var d = toActionDataNoOverlap(toActionDataTransTrack(a, squareX));
        var u0 = 60000 / bpm;
        var u1 = 60000 / bpm / squareY * (squareY - 1);
        var u2 = 60000 / bpm / squareY;
        var addLongX = function (t1, t2, tr1, tr2) {
            r.push([t1, tr1, tr2 + 10, t2]);
        };
        var addLongY = function (t1, t2, tr) {
            while((t2 > u1) || (IsNumberClose(t2, u1))) {
                r.push([t1, tr, 0x02, u1]);
                t1 += u0;
                t2 -= u0;
            }
            if((t2 > u2) || (IsNumberClose(t2, u2))) {
                r.push([t1, tr, 0x02, t2]);
            } else if(t2 > 0) {
                r.push([t1, tr, 0x00, 0]);
            }
        };
        for(var i = 0; i < d.length; i++) {
            switch(d[i][2]) {
                case 0x00:
                    r.push([d[i][0], d[i][1], 0x00, 0]);
                    break;
                case 0x01:
                    r.push([d[i][0], d[i][1] + d[i][3], 0x00, 0]);
                    break;
                case 0x02:
                    addLongY(d[i][0], d[i][3], d[i][1]);
                    break;
                case 0x61:
                case 0x62:
                    var t = [];
                    if(d[i][2] == 0x61) {
                        i += 1;
                    }
                    while(true) {
                        if(d[i][2] == 0xA2) {
                            if(t.length != 0) {
                                addLongX(t[0], t[3], t[1], t[2]);
                            }
                            addLongY(d[i][0], d[i][3], d[i][1]);
                            break;
                        } else if((d[i][2] == 0x62) || (d[i][2] == 0x22)) {
                            if((i != d.length - 1) && ((d[i + 1][2] == 0x21) || (d[i + 1][2] == 0xA1)) && (IsNumberClose(d[i][3], u2))) {
                                if(t.length == 0) {
                                    t = [d[i][0], d[i][1], d[i + 1][3], d[i][3]];
                                } else {
                                    if((t[2] > 0) ^ (d[i + 1][3] > 0)) {
                                        addLongX(t[0], t[3], t[1], t[2]);
                                        t = [d[i][0], d[i][1], d[i + 1][3], d[i][3]];
                                    } else {
                                        t = [t[0], t[1], t[2] + d[i + 1][3], t[3] + d[i][3]];
                                    }
                                }
                            } else {
                                addLongY(d[i][0], d[i][3], d[i][1]);
                            }
                            if(d[i + 1][2] == 0xA1) {
                                i += 1;
                                if(t.length != 0) {
                                    addLongX(t[0], t[3], t[1], t[2]);
                                }
                                break;
                            } else {
                                i += 2;
                            }
                        }
                    }
                    break;
            }
        }
        return sortActionData(r);
    };
    var toActionDataShortSlide = function (a, bpm) {
        var r = copyActionData(a);
        var t = [];
        for(var i = 0; i < r.length; i++) {
            if(r[i][2] == 0x61) {
                if(i + 1 < r.length) {
                    r[i + 1][2] = (r[i + 1][2] == 0x22 ? 0x62 : r[i + 1][2]);
                    r[i + 1][2] = (r[i + 1][2] == 0xA2 ? 0x02 : r[i + 1][2]);
                }
                r.splice(i, 1);
                i -= 1;
            }
        }
        for(var i = 0; i < r.length; i++) {
            switch(r[i][2]) {
                case 0x62:
                    var u = 0;
                    var d = 0;
                    var e = false;
                    while(true) {
                        if((r[i][2] == 0x62) || (r[i][2] == 0x22) || (r[i][2] == 0xA2)) {
                            if((i + 1 < r.length) && ((r[i + 1][2] == 0x21) || (r[i + 1][2] == 0xA1))) {
                                if(r[i + 1][2] == 0xA1) {
                                    e = true;
                                }
                                if((IsNumberClose(r[i][3], u)) && (r[i + 1][3] == d)) {
                                    t[t.length - 1][3] += u;
                                    t[t.length - 1][5] += d;
                                } else {
                                    u = r[i][3];
                                    d = r[i + 1][3];
                                    t.push([r[i][0], r[i][1], 0x62, r[i][3], r[i + 1][1], r[i + 1][3]]);
                                }
                                r.splice(i, 2);
                            } else {
                                if(r[i][2] == 0xA2) {
                                    e = true;
                                }
                                t.push([r[i][0], r[i][1], 0x62, r[i][3], 0, 0]);
                                r.splice(i, 1);
                            }
                        }
                        if(e) {
                            break;
                        }
                    }
                    i -= 1;
                    break;
            }
        }
        for(var i = 0; i < t.length; i++) {
            if(!IsNumberClose(t[i][3], 60000 / bpm / 2) && (t[i][3] < 60000 / bpm / 2)) {
                r.insert([
                             [t[i][0], t[i][1], 0x00, 0]
                         ], i);
            } else {
                if(t[i][5] == 0) {
                    r.insert([
                                 [t[i][0], t[i][1], 0x02, t[i][3]]
                             ], i);
                } else {
                    r.insert([
                                 [t[i][0], t[i][1], 0x62, t[i][3]],
                                 [t[i][0] + t[i][3], t[i][4], 0xA1, t[i][5]]
                             ], i);
                }
            }
        }
        return sortActionData(r);
    };
    var toActionDataNoSametime = function (a) {
        var r = copyActionData(a);
        var s = (function () {
            var v = [];
            var l = [];
            for(var i = 0; i < r.length; i++) {
                switch(r[i][2]) {
                    case 0x00:
                    case 0x01:
                        var n = (function () {
                            for(var j = 0; j < v.length; j++) {
                                if(v[j][0] == r[i][0]) {
                                    return j;
                                }
                            }
                            return-1;
                        })();
                        if(n != -1) {
                            switch(r[i][2]) {
                                case 0x00:
                                    v[n][1].push(r[i][1]);
                                    break;
                                case 0x01:
                                    v[n][1].push(r[i][1] + r[i][3]);
                                    break;
                            }
                        } else {
                            switch(r[i][2]) {
                                case 0x00:
                                    v.push([r[i][0], [r[i][1]]]);
                                    break;
                                case 0x01:
                                    v.push([r[i][0], [r[i][1] + r[i][3]]]);
                                    break;
                            }
                        }
                        break;
                    case 0x02:
                    case 0x62:
                    case 0x22:
                    case 0xA2:
                        l.push([r[i][0], r[i][0] + r[i][3]]);
                        break;
                }
            }
            for(var i = 0; i < v.length; i++) {
                for(var j = 0; j < l.length; j++) {
                    if(IsNumberClose(v[i][0], l[j][0]) || ((v[i][0] >= l[j][0]) && (v[i][0] <= l[j][1])) || IsNumberClose(v[i][0], l[j][1])) {
                        v[i][1] = [];
                        break;
                    }
                }
            }
            return v;
        })();
        var l = [
            [-1, -1]
        ];
        for(var i = 0; i < r.length; i++) {
            switch(r[i][2]) {
                case 0x00:
                case 0x01:
                    for(var j = 0; j < s.length; j++) {
                        if(s[j][0] == r[i][0]) {
                            if(s[j][1].length != 0) {
                                r[i][1] = s[j][1].average();
                                r[i][2] = 0x00;
                                r[i][3] = (function () {
                                    var v = 0;
                                    for(var k = 0; k < s[j][1].length; k++) {
                                        v += Math.pow(2, s[j][1][k]);
                                    }
                                    return v;
                                })();
                                s[j][1] = [];
                            } else {
                                r.splice(i, 1);
                                i -= 1;
                            }
                            break;
                        }
                    }
                    break;
                case 0x02:
                case 0x61:
                case 0x62:
                    var c = 1;
                    var t1 = r[i][0];
                    var t2 = t1;
                    if(r[i][2] == 0x02) {
                        t2 += r[i][3];
                    } else {
                        while(i + c < r.length) {
                            if(((r[i + c - 1][2] == 0x62) || (r[i + c - 1][2] == 0x22)) && ((r[i + c][2] == 0x21) || (r[i + c][2] == 0xA1))) {
                                c += 1;
                                if(r[i + c - 1][2] == 0xA1) {
                                    break;
                                }
                            } else if(((r[i + c - 1][2] == 0x61) || (r[i + c - 1][2] == 0x21)) && ((r[i + c][2] == 0x22) || (r[i + c][2] == 0xA2))) {
                                t2 += r[i + c][3];
                                c += 1;
                                if(r[i + c - 1][2] == 0xA2) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    var n = (function () {
                        for(var j = 0; j < l.length; j++) {
                            if((t1 < l[j][1]) && (t2 > l[j][0])) {
                                return j;
                            }
                        }
                        return-1;
                    })();
                    if(n != -1) {
                        r.splice(i, c);
                        i -= 1;
                    } else {
                        l.push([t1, t2]);
                        i += c - 1;
                    }
                    break;
            }
        }
        return r;
    };
    var fromData = function (meta, data) {
        var checks = GetMstCheck();
        for(var index = 0; index < data.length; index++) {
            data[index]["Key"] = getActionDataKey(data[index]["ActionData"]);
            var excludeBug = (function () {
                for(var i = 0; i < checks["TrackCheck"].length; i++) {
                    if(!checks["TrackCheck"][i]) {
                        return true;
                    }
                }
                return(FileExtension != "imd") || (!IsReading && (checks["TransKey"] != data[index]["Key"])) || checks["RandomTrack"] || checks["RandomNote"] || checks["MirrorTrack"] || (checks["ChangeSpeed"] != 1) || checks["LinkSingle"] || checks["LinkLong"] || checks["LinkSlide"] || checks["TransOblique"] || checks["NoOblique"] || checks["NoSlide"] || checks["NoLong"] || checks["NoSingle"] || checks["NoOverlap"] || checks["NoCross"] || checks["ExcludeBug"];
            })();
            var optimizaData = (function () {
                return checks["OptimizaData"];
            })();
            var checkAction = (function () {
                if(excludeBug) {
                    for(var i = 0; i < data[index]["ActionData"].length; i++) {
                        data[index]["ActionData"][i][0] = Math.floor(data[index]["ActionData"][i][0]);
                        data[index]["ActionData"][i][3] = Math.floor(data[index]["ActionData"][i][3]);
                        if((data[index]["ActionData"][i][2] == 0x00) && (data[index]["ActionData"][i][3] < 0)) {
                            data[index]["ActionData"][i][3] = 0;
                        }
                        if((i != data[index]["ActionData"].length - 1) && ((data[index]["ActionData"][i][2] == 0x62) || (data[index]["ActionData"][i][2] == 0x22)) && ((data[index]["ActionData"][i + 1][2] == 0x21) || (data[index]["ActionData"][i + 1][2] == 0xA1))) {
                            if(IsNumberClose(data[index]["ActionData"][i][0] + data[index]["ActionData"][i][3], data[index]["ActionData"][i + 1][0], 96)) {
                                data[index]["ActionData"][i][3] = data[index]["ActionData"][i + 1][0] - data[index]["ActionData"][i][0];
                            }
                        }
                        if(data[index]["ActionData"][i][0] < 300) {
                            data[index]["ActionData"].splice(i, 1);
                            i -= 1;
                        }
                    }
                }
            })();
            var checkBeat = (function () {
                if(excludeBug) {
                    if((typeof data[index]["Length"] === "undefined") || (data[index]["Length"] == 0)) {
                        data[index]["Length"] = getActionDataTime(data[index]["ActionData"]);
                    }
                    if((typeof data[index]["BeatData"] === "undefined") || (data[index]["BeatData"].length == 0)) {
                        data[index]["BeatData"] = getBeatData(data[index]["ActionData"]);
                    }
                    data[index]["BeatData"] = unrepeatBeatData(data[index]["BeatData"]).sort(SortNumbers);
                    data[index]["BeatData"] = enlargeBeatData(offsetBeatData(data[index]["BeatData"], 0), data[index]["ActionData"]);
                    for(var i = 0; i < data[index]["BeatData"].length - 1; i++) {
                        if(data[index]["BeatData"][i][1] == 0) {
                            if(i > 0) {
                                data[index]["BeatData"][i][1] = data[index]["BeatData"][i - 1][1];
                            } else if(i + 1 < data[index]["BeatData"].length) {
                                data[index]["BeatData"][i][1] = data[index]["BeatData"][i + 1][1];
                            } else {
                                data[index]["BeatData"][i][1] = getActionDataBPM(data[index]["ActionData"]);
                            }
                        }
                    }
                    for(var i = 0; i < data[index]["BeatData"].length - 1; i++) {
                        if(i + 1 < data[index]["BeatData"].length) {
                            if(!IsNumberClose(data[index]["BeatData"][i + 1][0], data[index]["BeatData"][i][0])) {
                                var b = Math.round(60000 / (data[index]["BeatData"][i + 1][0] - data[index]["BeatData"][i][0]));
                                if(!IsNumberClose(data[index]["BeatData"][i][1], b)) {
                                    data[index]["BeatData"][i][1] = b;
                                }
                            }
                        }
                        if(i == data[index]["BeatData"].length - 2) {
                            data[index]["BeatData"][i + 1][1] = data[index]["BeatData"][i][1];
                            data[index]["BeatData"][i + 1][0] = Math.round(data[index]["BeatData"][i + 1][0]);
                            data[index]["BeatData"][i + 1][1] = Math.round(data[index]["BeatData"][i + 1][1] * 1000) / 1000;
                        }
                        data[index]["BeatData"][i][0] = Math.round(data[index]["BeatData"][i][0]);
                        data[index]["BeatData"][i][1] = Math.round(data[index]["BeatData"][i][1] * 1000) / 1000;
                    }
                }
            })();
            var transTrack = (function () {
                if(IsReading) {
                    if(DefaultKey == -1) {
                        SetTransKey(data[index]["Key"]);
                    }
                    checks["TransKey"] = data[index]["Key"];
                    IsReading = false;
                }
                data[index]["ActionData"] = toActionDataTransTrack(data[index]["ActionData"], checks["TransKey"]);
                if(data[index]["Key"] != checks["TransKey"]) {
                    data[index]["Key"] = checks["TransKey"];
                }
            })();
            if(checks["RandomTrack"]) {
                data[index]["ActionData"] = toActionDataRandomTrack(data[index]["ActionData"]);
            }
            if(checks["RandomNote"]) {
                data[index]["ActionData"] = toActionDataRandomNote(data[index]["ActionData"]);
            }
            if(checks["MirrorTrack"]) {
                data[index]["ActionData"] = toActionDataMirrorTrack(data[index]["ActionData"]);
            }
            var changeSpeed = (function () {
                data[index]["Length"] = Math.round(data[index]["Length"] / checks["ChangeSpeed"]);
                for(var i = 0; i < data[index]["BeatData"].length; i++) {
                    data[index]["BeatData"][i][0] = Math.round(data[index]["BeatData"][i][0] / checks["ChangeSpeed"]);
                    data[index]["BeatData"][i][1] = Math.round(data[index]["BeatData"][i][1] * checks["ChangeSpeed"]);
                }
                for(var i = 0; i < data[index]["ActionData"].length; i++) {
                    data[index]["ActionData"][i][0] = Math.round(data[index]["ActionData"][i][0] / checks["ChangeSpeed"]);
                    switch(data[index]["ActionData"][i][2]) {
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            data[index]["ActionData"][i][3] = Math.round(data[index]["ActionData"][i][3] / checks["ChangeSpeed"]);
                            break;
                    }
                }
            })();
            data[index]["Start"] = data[index]["BeatData"][0][0];
            data[index]["End"] = data[index]["BeatData"][data[index]["BeatData"].length - 1][0];
            data[index]["BPM"] = (function () {
                var bpms = [];
                for(var i = 0; i < data[index]["BeatData"].length; i++) {
                    bpms.push(data[index]["BeatData"][i][1]);
                }
                return bpms.majority();
            })();
            var removeTrack = (function () {
                if(checks["TrackCheck"].length != data[index]["Key"]) {
                    SetTrackCheck(data[index]["Key"], checks["TrackCheck"]);
                    checks["TrackCheck"] = GetMstCheck()["TrackCheck"];
                }
                for(var i = 0; i < data[index]["ActionData"].length; i++) {
                    if(!checks["TrackCheck"][data[index]["ActionData"][i][1]]) {
                        data[index]["ActionData"].splice(i, 1);
                        i -= 1;
                    } else {
                        switch(data[index]["ActionData"][i][2]) {
                            case 0x01:
                            case 0x61:
                            case 0x21:
                            case 0xA1:
                                if(!checks["TrackCheck"][data[index]["ActionData"][i][1] + data[index]["ActionData"][i][3]]) {
                                    data[index]["ActionData"].splice(i, 1);
                                    i -= 1;
                                }
                                break;
                        }
                    }
                }
                for(var i = 0; i < data[index]["ActionData"].length; i++) {
                    if(data[index]["ActionData"][i][3] != 0) {
                        switch(data[index]["ActionData"][i][2]) {
                            case 0x01:
                            case 0x61:
                            case 0x21:
                            case 0xA1:
                                var t = data[index]["ActionData"][i][1] + data[index]["ActionData"][i][3];
                                if(data[index]["ActionData"][i][3] < 0) {
                                    for(var j = t; j < data[index]["ActionData"][i][1]; j++) {
                                        if(!checks["TrackCheck"][j]) {
                                            data[index]["ActionData"][i][3] += 1;
                                        }
                                    }
                                } else {
                                    for(var j = data[index]["ActionData"][i][1] + 1; j < t + 1; j++) {
                                        if(!checks["TrackCheck"][j]) {
                                            data[index]["ActionData"][i][3] -= 1;
                                        }
                                    }
                                }
                                if(data[index]["ActionData"][i][3] == 0) {
                                    data[index]["ActionData"].splice(i, 1);
                                    i -= 1;
                                    continue;
                                }
                                break;
                        }
                    }
                    for(var j = 0; j < data[index]["ActionData"][i][1]; j++) {
                        if(!checks["TrackCheck"][j]) {
                            data[index]["ActionData"][i][1] -= 1;
                        }
                    }
                }
                data[index]["Key"] = getActionDataKey(data[index]["ActionData"]);
            })();
            var checkTime = (function () {
                var t = 0;
                for(var i = 0; i < data[index]["ActionData"].length; i++) {
                    switch(data[index]["ActionData"][i][2]) {
                        case 0x00:
                        case 0x01:
                        case 0x61:
                        case 0x21:
                        case 0xA1:
                            if(t < data[index]["ActionData"][i][0]) {
                                t = data[index]["ActionData"][i][0];
                            }
                            break;
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            if(t < data[index]["ActionData"][i][0] + data[index]["ActionData"][i][3]) {
                                t = data[index]["ActionData"][i][0] + data[index]["ActionData"][i][3];
                            }
                            break;
                    }
                }
                var b0 = (data[index]["BeatData"].length > 1 ? data[index]["BeatData"][data[index]["BeatData"].length - 1][0] - data[index]["BeatData"][data[index]["BeatData"].length - 2][0] : data[index]["BeatData"][data[index]["BeatData"].length - 1][0]);
                var b1 = data[index]["BeatData"][data[index]["BeatData"].length - 1][1];
                while(data[index]["BeatData"][data[index]["BeatData"].length - 1][0] < t) {
                    data[index]["BeatData"].push([data[index]["BeatData"][data[index]["BeatData"].length - 1][0] + b0, b1]);
                }
                while((data[index]["BeatData"].length - 1) % 4 != 0) {
                    data[index]["BeatData"].push([data[index]["BeatData"][data[index]["BeatData"].length - 1][0] + b0, b1]);
                }
                if((data[index]["BeatData"].length > 4) && (t >= data[index]["BeatData"][data[index]["BeatData"].length - 5][0])) {
                    for(var i = 0; i < 4; i++) {
                        data[index]["BeatData"].push([data[index]["BeatData"][data[index]["BeatData"].length - 1][0] + b0, b1]);
                    }
                }
                data[index]["Time"] = data[index]["Length"] - data[index]["Start"];
                if(data[index]["Time"] < data[index]["BeatData"][data[index]["BeatData"].length - 1][0]) {
                    data[index]["Time"] = Math.round(data[index]["BeatData"][data[index]["BeatData"].length - 1][0] + 60000 / data[index]["BPM"]);
                }
            })();
            var getBeatLines = (function () {
                data[index]["BeatLines"] = [];
                data[index]["BeatLinesW"] = [];
                data[index]["BeatLinesH"] = [];
                data[index]["BeatLinesQ"] = [];
                for(var i = 0; i < data[index]["BeatData"].length; i++) {
                    if(i < data[index]["BeatData"].length - 1) {
                        var n = (data[index]["BeatData"][i + 1][0] - data[index]["BeatData"][i][0]) / 4;
                        data[index]["BeatLinesW"].push(Math.round(data[index]["BeatData"][i][0]));
                        data[index]["BeatLines"].push(Math.round(data[index]["BeatData"][i][0]));
                        data[index]["BeatLinesQ"].push(Math.round(data[index]["BeatData"][i][0] + n));
                        data[index]["BeatLines"].push(Math.round(data[index]["BeatData"][i][0] + n));
                        data[index]["BeatLinesH"].push(Math.round(data[index]["BeatData"][i][0] + n * 2));
                        data[index]["BeatLines"].push(Math.round(data[index]["BeatData"][i][0] + n * 2));
                        data[index]["BeatLinesQ"].push(Math.round(data[index]["BeatData"][i][0] + n * 3));
                        data[index]["BeatLines"].push(Math.round(data[index]["BeatData"][i][0] + n * 3));
                    } else {
                        data[index]["BeatLinesW"].push(Math.round(data[index]["BeatData"][i][0]));
                        data[index]["BeatLines"].push(Math.round(data[index]["BeatData"][i][0]));
                    }
                }
            })();
            var fixTimestamps = (function () {
                if(optimizaData) {
                    var v = 60000 / data[index]["BPM"] / 48;
                    for(var i = 0; i < data[index]["ActionData"].length; i++) {
                        data[index]["ActionData"][i][0] = CloseNumbers(data[index]["ActionData"][i][0], data[index]["BeatLines"], v);
                    }
                    for(var i = 0; i < data[index]["ActionData"].length; i++) {
                        switch(data[index]["ActionData"][i][2]) {
                            case 0x62:
                            case 0x22:
                                if((i + 1 < data[index]["ActionData"].length) && ((data[index]["ActionData"][i + 1][2] == 0x21) || (data[index]["ActionData"][i + 1][2] == 0xA1))) {
                                    if(IsNumberClose(data[index]["ActionData"][i][0] + data[index]["ActionData"][i][3], data[index]["ActionData"][i + 1][0], 96)) {
                                        data[index]["ActionData"][i][3] = data[index]["ActionData"][i + 1][0] - data[index]["ActionData"][i][0];
                                    }
                                }
                                break;
                            case 0x02:
                            case 0xA2:
                                data[index]["ActionData"][i][3] = CloseNumbers(data[index]["ActionData"][i][0] + data[index]["ActionData"][i][3], data[index]["BeatLines"], v) - data[index]["ActionData"][i][0];
                                break;
                        }
                    }
                }
            })();
            if(checks["LinkSingle"]) {
                data[index]["ActionData"] = toActionDataLinkSingle(data[index]["ActionData"]);
            }
            if(checks["LinkLong"]) {
                data[index]["ActionData"] = toActionDataLinkLong(data[index]["ActionData"]);
            }
            if(checks["LinkSilde"]) {
                data[index]["ActionData"] = toActionDataLinkSilde(data[index]["ActionData"]);
            }
            if(checks["TransOblique"]) {
                data[index]["ActionData"] = toActionDataTransOblique(data[index]["ActionData"]);
            }
            if(checks["NoOblique"]) {
                data[index]["ActionData"] = toActionDataNoOblique(data[index]["ActionData"]);
            }
            if(checks["NoSlide"]) {
                data[index]["ActionData"] = toActionDataNoSlide(data[index]["ActionData"]);
            }
            if(checks["NoLong"]) {
                data[index]["ActionData"] = toActionDataNoLong(data[index]["ActionData"]);
            }
            if(checks["NoSingle"]) {
                data[index]["ActionData"] = toActionDataNoSingle(data[index]["ActionData"]);
            }
            if(checks["NoCross"]) {
                data[index]["ActionData"] = toActionDataNoCross(data[index]["ActionData"]);
            }
            if(checks["NoOverlap"]) {
                data[index]["ActionData"] = toActionDataNoOverlap(data[index]["ActionData"]);
            }
            var setData = (function () {
                if(excludeBug || optimizaData) {
                    data[index]["ActionData"] = sortActionData(data[index]["ActionData"]);
                    data[index]["Length"] = data[index]["Time"];
                    data[index]["End"] = data[index]["BeatData"][data[index]["BeatData"].length - 1][0];
                }
            })();
            var setDetermine = (function () {
                var isCloseNumber = optimizaData;
                var determineData = [];
                var indexOf = function (t) {
                    for(var i = 0; i < determineData.length; i++) {
                        if(determineData[i][0] == t) {
                            return i;
                        }
                    }
                    return-1;
                };
                var addData = function (t, x, n) {
                    var l = indexOf(t);
                    if(l != -1) {
                        determineData[l][n].push(x);
                    } else {
                        determineData.push([t, [], []]);
                        determineData[determineData.length - 1][n].push(x);
                    }
                };
                for(var i = 0; i < data[index]["ActionData"].length; i++) {
                    var t = (isCloseNumber ? CloseNumbers(data[index]["ActionData"][i][0], data[index]["BeatLines"]) : data[index]["ActionData"][i][0]);
                    switch(data[index]["ActionData"][i][2]) {
                        case 0x00:
                            addData(t, data[index]["ActionData"][i][1], 1);
                            break;
                        case 0x01:
                            addData(t, data[index]["ActionData"][i][1] + data[index]["ActionData"][i][3], 2);
                            break;
                        case 0x21:
                            break;
                        case 0xA1:
                            addData(t, data[index]["ActionData"][i][1] + data[index]["ActionData"][i][3], 2);
                            break;
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            var d = [];
                            var dt = data[index]["ActionData"][i][0];
                            var db = toBeatDataBeat(data[index]["BeatData"], data[index]["ActionData"][i][0]);
                            d.push([(isCloseNumber ? CloseNumbers(Math.round(dt), data[index]["BeatLines"]) : Math.round(dt)), data[index]["ActionData"][i][1], ((data[index]["ActionData"][i][2] == 0x22) || (data[index]["ActionData"][i][2] == 0xA2) ? 2 : 1)]);
                            while(true) {
                                db += 0.25;
                                dt = fromBeatDataBeat(data[index]["BeatData"], db);
                                if((dt < data[index]["ActionData"][i][0] + data[index]["ActionData"][i][3]) || (IsNumberClose(dt, data[index]["ActionData"][i][0] + data[index]["ActionData"][i][3]))) {
                                    d.push([(isCloseNumber ? CloseNumbers(Math.round(dt), data[index]["BeatLines"]) : Math.round(dt)), data[index]["ActionData"][i][1], 1]);
                                } else {
                                    break;
                                }
                            }
                            for(var j = 0; j < d.length; j++) {
                                if((i != data[index]["ActionData"].length - 1) && ((data[index]["ActionData"][i][2] == 0x62) || (data[index]["ActionData"][i][2] == 0x22)) && ((data[index]["ActionData"][i + 1][2] == 0x21) || (data[index]["ActionData"][i + 1][2] == 0xA1)) && (data[index]["ActionData"][i + 1][1] != data[index]["ActionData"][i][1])) {
                                    if(data[index]["ActionData"][i][3] != 0) {
                                        d[j][1] += (d[j][0] - data[index]["ActionData"][i][0]) / (data[index]["ActionData"][i][3]) * (data[index]["ActionData"][i + 1][1] - data[index]["ActionData"][i][1]);
                                    }
                                }
                                addData(d[j][0], d[j][1], d[j][2]);
                            }
                            break;
                    }
                }
                data[index]["DetermineLines"] = (function () {
                    var r = [];
                    for(var i = 0; i < determineData.length; i++) {
                        r.push(determineData[i][0]);
                    }
                    return r.unrepeat().sort(SortNumber);
                })();
                data[index]["ComboPoints"] = (function () {
                    var r = [];
                    for(var i = 0; i < data[index]["DetermineLines"].length; i++) {
                        var l = indexOf(data[index]["DetermineLines"][i]);
                        if(l != -1) {
                            determineData[l][1] = determineData[l][1].sort(SortNumber);
                            for(var j = 0; j < determineData[l][1].length; j++) {
                                r.push([determineData[l][0], determineData[l][1][j]]);
                            }
                            determineData[l][2] = determineData[l][2].sort(SortNumber);
                            for(var j = 0; j < determineData[l][2].length; j++) {
                                r.push([determineData[l][0], determineData[l][2][j]]);
                            }
                        }
                    }
                    return r;
                })();
            })();
            data[index]["Combo"] = data[index]["ComboPoints"].length;
            data[index]["Rank"] = (function () {
                var r = 0;
                for(var i = 0; i < data[index]["ActionData"].length; i++) {
                    switch(data[index]["ActionData"][i][2]) {
                        case 0x00:
                            r += 1;
                            break;
                        case 0x01:
                            r += Math.abs(data[index]["ActionData"][i][3]) + 2;
                            break;
                        case 0x61:
                        case 0xA1:
                            r += Math.abs(data[index]["ActionData"][i][3]) + 1;
                            break;
                        case 0x21:
                            r += Math.abs(data[index]["ActionData"][i][3]);
                            break;
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            r += Math.floor(data[index]["ActionData"][i][3] / 120) + 1;
                            break;
                    }
                }
                return Math.sqrt(r / 16);
            })();
            data[index]["Difficulty"] = (function () {
                var r = 0;
                var d = [];
                var getLong = function (t) {
                    return Math.floor(t / 120) / 4;
                };
                var getSlide = function (t) {
                    return Math.abs(t) / data[index]["Key"];
                };
                var indexOf = function (t) {
                    for(var i = 0; i < d.length; i++) {
                        if(d[i][0] == t) {
                            return i;
                        }
                    }
                    return-1;
                };
                var addData = function (t, v) {
                    var l = indexOf(t);
                    if(l != -1) {
                        d[l][1] += v;
                    } else {
                        d.push([t, v]);
                    }
                };
                for(var i = 0; i < data[index]["ActionData"].length; i++) {
                    switch(data[index]["ActionData"][i][2]) {
                        case 0x00:
                            addData(data[index]["ActionData"][i][0], 1);
                            break;
                        case 0x01:
                            addData(data[index]["ActionData"][i][0], 2 + getSlide(data[index]["ActionData"][i][3]));
                            break;
                        case 0x61:
                        case 0xA1:
                            addData(data[index]["ActionData"][i][0], 1 + getSlide(data[index]["ActionData"][i][3]));
                            break;
                        case 0x21:
                            addData(data[index]["ActionData"][i][0], 0 + getSlide(data[index]["ActionData"][i][3]));
                            break;
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            addData(data[index]["ActionData"][i][0], 1 + getLong(data[index]["ActionData"][i][3]));
                            break;
                    }
                }
                d = d.sort(SortNumbers);
                d.unshift([0, 0]);
                for(var i = 1; i < d.length; i++) {
                    r += d[i][1] / (d[i][0] - d[i - 1][0]) * 120;
                }
                return Math.sqrt(r / 16);
            })();
            SetFileInfo(data[index]["Key"]);
        }
        Meta = meta;
        Data = data;
        SetIndexList(readMeta(["IndexList"], []));
        isValid = true;
    };
    var fromMstBuffer = function (buffer) {
    };
    var fromBmsBuffer = function (buffer) {
        var extension = (arguments.length > 1 ? arguments[1] : "");
        var hTime = 0;
        var hBPM = 130;
        var hLNTYPE = 1;
        var hLNOBJ = "";
        var hWAVs = [];
        var hBPMs = [];
        var meta = {};
        var a = buffer.getText().replace(/\r/g, "\n").replace(/\n\n/g, "\n").split("\n");
        var d = [];
        var existChanels = (function () {
            var r = [];
            for(var i = 0; i < 9; i++) {
                r[i + 11] = false;
                r[i + 21] = false;
                r[i + 51] = false;
                r[i + 61] = false;
            }
            return r;
        })();
        for(var i = 0; i < a.length; i++) {
            a[i] += " ";
            if((a[i].length >= 5) && (a[i][0] == "#")) {
                var ls = a[i].indexOf(" ", 1);
                if(ls != -1) {
                    var n = a[i].substring(1, ls).toUpperCase().trim();
                    var v = a[i].substring(ls + 1).trim();
                    switch(n) {
                        case"TIME":
                            v = parseFloat(v);
                            if(!isNaN(v) && (v >= 0)) {
                                hTime = v;
                            }
                            break;
                        case"BPM":
                            v = parseFloat(v);
                            if(!isNaN(v) && (v >= 0)) {
                                hBPM = v;
                            }
                            break;
                        case"LNTYPE":
                            v = parseInt(v);
                            if(!isNaN(v) && (v != 0)) {
                                hLNTYPE = v;
                            }
                            break;
                        case"LNOBJ":
                            if(v.length == 2) {
                                hLNOBJ = v;
                            }
                            break;
                    }
                    var h = ["Player", "Genre", "Title", "SubTitle", "Artist", "Creator", "Maker", "DateTime", "Total", "Player", "Rank", "Difficulty", "MidFile", "StageFile", "ExtChr", "VolWav", "VideoF/S"];
                    for(var j = 0; j < h.length; j++) {
                        if(n == h[j].toUpperCase()) {
                            meta[h[j]] = v;
                            break;
                        }
                    }
                    if(n.length == 5) {
                        var m = n.substring(0, 3);
                        var c = n.substring(3, 5);
                        switch(m) {
                            case"BPM":
                                c = BaseToDec(c, 36);
                                if(!isNaN(c)) {
                                    v = parseFloat(v);
                                    if(!isNaN(v) && (v >= 0)) {
                                        hBPMs[c] = v;
                                    }
                                }
                                break;
                            case"WAV":
                                c = BaseToDec(c, 36);
                                if(!isNaN(c)) {
                                    if(v != "") {
                                        hWAVs[c] = v;
                                    }
                                }
                                break;
                        }
                    }
                }
                var lc = a[i].indexOf(":", 1);
                if(lc != -1) {
                    var n = a[i].substring(1, lc).toUpperCase().trim();
                    var v = a[i].substring(lc + 1).trim();
                    if(n.length == 5) {
                        var m = n.substring(0, 3);
                        var c = n.substring(3, 5);
                        m = parseInt(m);
                        if(!isNaN(m)) {
                            if(typeof d[m] === "undefined") {
                                d[m] = [];
                            }
                            c = parseInt(c);
                            if(!isNaN(c) && (c != 0)) {
                                d[m][c] = v;
                                existChanels[c] = true;
                            }
                        }
                    }
                }
            }
        }
        var isChanelExist = function (m) {
            for(var i = 0; i < m.length; i++) {
                if((existChanels[m[i]]) || (existChanels[m[i] + 40])) {
                    return true;
                }
            }
            return false;
        };
        switch(extension) {
            case"bms":
                if(isChanelExist([16, 26])) {
                    FileType = "bms2";
                }
                if(isChanelExist([17, 27])) {
                    FileType = "bm98";
                }
                if(isChanelExist([19, 29])) {
                    if(FileType != "bms2") {
                        FileType = "bms";
                    }
                }
                break;
            case"bme":
                FileType = "bme5";
                if(isChanelExist([11, 12, 21, 22])) {
                    FileType = "bme3";
                    if(isChanelExist([18, 19, 28, 29])) {
                        FileType = "bme14";
                    }
                }
                break;
            case"pms":
                FileType = "pms5";
                if(isChanelExist([11, 12])) {
                    FileType = "pms3";
                    if(isChanelExist([22, 23, 24, 25])) {
                        FileType = "pms";
                    }
                    if(isChanelExist([16, 17, 18, 19, 26, 27, 28, 29])) {
                        FileType = "pmse";
                    }
                }
                break;
            default:
                FileType = "bms2";
                break;
        }
        FileType = (FileType == "" ? "bms2" : FileType);
        for(var i = 0; i < a.length; i++) {
            if(a[a.length - 1 - i] != "") {
                if(i > 1) {
                    d[d.length - 1 + i - 1] = [];
                }
                break;
            }
        }
        var offset = 0;
        var timeSigns = [];
        var innerBPMs = [];
        var innerTimestamps = [];
        var WAVTimestamps = [];
        var BPMTimestamps = [];
        var snData = [];
        var lnData = [];
        var beatData = [];
        var actionData = [];
        var isAvd = (hLNTYPE == 1);
        var avdSnScript = ["A8", "A3", "A4", "A5", "A6", "A7", "A9", "AA", "AB", "AC", "E3", "E4", "E5", "E6", "E7", "E9", "EA", "EB", "EC"];
        var avdLnScript = ["A8", "B8", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "D3", "D4", "D5", "D6", "D7", "D9", "DA", "DB", "DC", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "F3", "F4", "F5", "F6", "F7", "F9", "FA", "FB", "FC"];
        var fillScript = function (t) {
            if(t.length % 2 != 0) {
                t += "0";
            }
            return t;
        };
        var chanelToTrack = function (c) {
            var r = -1;
            if(c > 40) {
                c -= 40;
            }
            switch(FileType) {
                case"bm98":
                    switch(c) {
                        case 11:
                            r = 0;
                            break;
                        case 12:
                            r = 1;
                            break;
                        case 13:
                            r = 2;
                            break;
                        case 14:
                            r = 3;
                            break;
                        case 15:
                            r = 4;
                            break;
                        case 16:
                            r = 5;
                            break;
                        case 17:
                            r = 6;
                            break;
                        case 21:
                            r = 7;
                            break;
                        case 22:
                            r = 8;
                            break;
                        case 23:
                            r = 9;
                            break;
                        case 24:
                            r = 10;
                            break;
                        case 25:
                            r = 11;
                            break;
                        case 26:
                            r = 12;
                            break;
                        case 27:
                            r = 13;
                            break;
                    }
                    break;
                case"bms":
                    switch(c) {
                        case 11:
                            r = 0;
                            break;
                        case 12:
                            r = 1;
                            break;
                        case 13:
                            r = 2;
                            break;
                        case 14:
                            r = 3;
                            break;
                        case 15:
                            r = 4;
                            break;
                        case 18:
                            r = 5;
                            break;
                        case 19:
                            r = 6;
                            break;
                        case 21:
                            r = 7;
                            break;
                        case 22:
                            r = 8;
                            break;
                        case 23:
                            r = 9;
                            break;
                        case 24:
                            r = 10;
                            break;
                        case 25:
                            r = 11;
                            break;
                        case 28:
                            r = 12;
                            break;
                        case 29:
                            r = 13;
                            break;
                    }
                    break;
                case"bms2":
                    switch(c) {
                        case 16:
                            r = 0;
                            break;
                        case 11:
                            r = 1;
                            break;
                        case 12:
                            r = 2;
                            break;
                        case 13:
                            r = 3;
                            break;
                        case 14:
                            r = 4;
                            break;
                        case 15:
                            r = 5;
                            break;
                        case 18:
                            r = 6;
                            break;
                        case 26:
                            r = 7;
                            break;
                        case 21:
                            r = 8;
                            break;
                        case 22:
                            r = 9;
                            break;
                        case 23:
                            r = 10;
                            break;
                        case 24:
                            r = 11;
                            break;
                        case 25:
                            r = 12;
                            break;
                        case 28:
                            r = 13;
                            break;
                    }
                    break;
                case"bme3":
                    switch(c) {
                        case 11:
                            r = 0;
                            break;
                        case 12:
                            r = 1;
                            break;
                        case 13:
                            r = 2;
                            break;
                    }
                    break;
                case"bme5":
                    switch(c) {
                        case 13:
                            r = 0;
                            break;
                        case 14:
                            r = 1;
                            break;
                        case 15:
                            r = 2;
                            break;
                        case 18:
                            r = 3;
                            break;
                        case 19:
                            r = 4;
                            break;
                    }
                    break;
                case"bme14":
                    switch(c) {
                        case 11:
                            r = 0;
                            break;
                        case 12:
                            r = 1;
                            break;
                        case 13:
                            r = 2;
                            break;
                        case 14:
                            r = 3;
                            break;
                        case 15:
                            r = 4;
                            break;
                        case 18:
                            r = 5;
                            break;
                        case 19:
                            r = 6;
                            break;
                        case 21:
                            r = 7;
                            break;
                        case 22:
                            r = 8;
                            break;
                        case 23:
                            r = 9;
                            break;
                        case 24:
                            r = 10;
                            break;
                        case 25:
                            r = 11;
                            break;
                        case 28:
                            r = 12;
                            break;
                        case 29:
                            r = 13;
                            break;
                    }
                    break;
                case"pms3":
                    switch(c) {
                        case 11:
                            r = 0;
                            break;
                        case 12:
                            r = 1;
                            break;
                        case 13:
                            r = 2;
                            break;
                    }
                    break;
                case"pms5":
                    switch(c) {
                        case 13:
                            r = 0;
                            break;
                        case 14:
                            r = 1;
                            break;
                        case 15:
                            r = 2;
                            break;
                        case 22:
                            r = 3;
                            break;
                        case 23:
                            r = 4;
                            break;
                    }
                    break;
                case"pms":
                    switch(c) {
                        case 11:
                            r = 0;
                            break;
                        case 12:
                            r = 1;
                            break;
                        case 13:
                            r = 2;
                            break;
                        case 14:
                            r = 3;
                            break;
                        case 15:
                            r = 4;
                            break;
                        case 22:
                            r = 5;
                            break;
                        case 23:
                            r = 6;
                            break;
                        case 24:
                            r = 7;
                            break;
                        case 25:
                            r = 8;
                            break;
                    }
                    break;
                case"pmse":
                    switch(c) {
                        case 11:
                            r = 0;
                            break;
                        case 12:
                            r = 1;
                            break;
                        case 13:
                            r = 2;
                            break;
                        case 14:
                            r = 3;
                            break;
                        case 15:
                            r = 4;
                            break;
                        case 18:
                            r = 5;
                            break;
                        case 19:
                            r = 6;
                            break;
                        case 16:
                            r = 7;
                            break;
                        case 17:
                            r = 8;
                            break;
                        case 21:
                            r = 9;
                            break;
                        case 22:
                            r = 10;
                            break;
                        case 23:
                            r = 11;
                            break;
                        case 24:
                            r = 12;
                            break;
                        case 25:
                            r = 13;
                            break;
                        case 28:
                            r = 14;
                            break;
                        case 29:
                            r = 15;
                            break;
                        case 26:
                            r = 16;
                            break;
                        case 27:
                            r = 17;
                            break;
                    }
                    break;
            }
            return r;
        };
        var measureActionTimestamps = function (t, p, l) {
            var r = -1;
            var n = -1;
            for(var i = 0; i < t.length + 1; i++) {
                if(i / t.length >= p / l) {
                    n = i;
                    break;
                }
            }
            if(n == 0) {
                r = 0;
            } else if(n == 1) {
                r = t[n - 1] - (n / t.length - p / l) * t[n - 1];
            } else if(n > 1) {
                r = t[n - 1] - (n / t.length - p / l) * (t[n - 1] - t[n - 2]) * t.length;
            }
            return r;
        };
        var measureBPMTimestamps = function (t, s) {
            var r = [];
            var n = Math.ceil(s / 0.25);
            for(var i = 0; i < n; i++) {
                r.push(measureActionTimestamps(t, i, n));
            }
            return r;
        };
        var tm = 0;
        for(var m = 0; m < d.length; m++) {
            timeSigns[m] = 1;
            innerBPMs[m] = [hBPM];
            innerTimestamps[m] = [60000 * 4 / hBPM];
            if(typeof d[m] !== "undefined") {
                var innerBPMs03 = [0];
                var innerBPMs08 = [0];
                var innerBPMsM = [];
                if(typeof d[m][3] !== "undefined") {
                    var s = fillScript(d[m][3]);
                    for(var i = 0; i < s.length; i++) {
                        var v = BaseToDec(s.substring(i, i + 2), 16);
                        if(isNaN(v)) {
                            v = 0;
                        }
                        innerBPMs03[i / 2] = v;
                        i += 1;
                    }
                }
                if(typeof d[m][8] !== "undefined") {
                    var s = fillScript(d[m][8]);
                    for(var i = 0; i < s.length; i++) {
                        var v = 0;
                        var h = BaseToDec(s.substring(i, i + 2), 36);
                        if(typeof hBPMs[h] !== "undefined") {
                            v = hBPMs[h];
                        }
                        innerBPMs08[i / 2] = v;
                        i += 1;
                    }
                }
                for(var i = 0; i < innerBPMs03.length * innerBPMs08.length; i++) {
                    innerBPMsM[i] = 0;
                    if(i % innerBPMs08.length == 0) {
                        innerBPMsM[i] = innerBPMs03[Math.floor(i / innerBPMs08.length)];
                    }
                    if((i % innerBPMs03.length == 0) && (innerBPMs08[Math.floor(i / innerBPMs03.length)] != 0)) {
                        innerBPMsM[i] = innerBPMs08[Math.floor(i / innerBPMs03.length)];
                    }
                }
                for(var i = 0; i < innerBPMsM.length; i++) {
                    if(innerBPMsM[i] != 0) {
                        hBPM = innerBPMsM[i];
                    }
                    innerBPMsM[i] = hBPM;
                }
                innerBPMs[m] = innerBPMsM;
                if(typeof d[m][2] !== "undefined") {
                    timeSigns[m] = d[m][2];
                }
                if(innerTimestamps[m].length == 1) {
                    innerTimestamps[m] = (function () {
                        var r = [];
                        var t = 0;
                        var u = 60000 * 4 * timeSigns[m] / innerBPMs[m].length;
                        for(var i = 0; i < innerBPMs[m].length; i++) {
                            r[i] = t + u / innerBPMs[m][i];
                            t = r[i];
                        }
                        return r;
                    })();
                }
                for(var c = 0; c < d[m].length; c++) {
                    if(typeof d[m][c] !== "undefined") {
                        var t = chanelToTrack(c);
                        if(t != -1) {
                            var s = fillScript(d[m][c]);
                            for(var i = 0; i < s.length; i++) {
                                var u = s.substring(i, i + 2);
                                if(u != "00") {
                                    if(c < 30) {
                                        if(isAvd) {
                                            isAvd = (avdSnScript.indexOf(u) != -1);
                                        }
                                        if(typeof snData[t] === "undefined") {
                                            snData[t] = [];
                                        }
                                        snData[t].push([Math.round(tm + measureActionTimestamps(innerTimestamps[m], i / 2, s.length / 2)), u]);
                                    } else if((c > 50) && (hLNTYPE == 1)) {
                                        if(isAvd) {
                                            isAvd = (avdLnScript.indexOf(u) != -1);
                                        }
                                        if(typeof lnData[t] === "undefined") {
                                            lnData[t] = [];
                                        }
                                        lnData[t].push([Math.round(tm + measureActionTimestamps(innerTimestamps[m], i / 2, s.length / 2)), u]);
                                    }
                                }
                                i += 1;
                            }
                        }
                    }
                }
                if(typeof d[m][1] !== "undefined") {
                    var s = fillScript(d[m][1]);
                    for(var i = 0; i < s.length; i++) {
                        var h = BaseToDec(s.substring(i, i + 2), 36);
                        if(typeof hWAVs[h] !== "undefined") {
                            WAVTimestamps.push(Math.round(tm + measureActionTimestamps(innerTimestamps[m], i / 2, s.length / 2)));
                        }
                        i += 1;
                    }
                }
            }
            var s = measureBPMTimestamps(innerTimestamps[m], timeSigns[m]);
            for(var i = 0; i < s.length; i++) {
                BPMTimestamps.push(tm + s[i]);
            }
            tm += innerTimestamps[m][innerTimestamps[m].length - 1];
        }
        if(WAVTimestamps.length != 0) {
            offset = WAVTimestamps[0];
            meta["Wav"] = hWAVs[0];
        }
        if(BPMTimestamps.length == 0) {
            BPMTimestamps.push(0);
        }
        if(BPMTimestamps.length < 2) {
            BPMTimestamps.push(BPMTimestamps[BPMTimestamps.length - 1] + 60000 / hBPM);
        }
        for(var i = 0; i < BPMTimestamps.length - 1; i++) {
            beatData.push([BPMTimestamps[i], 60000 / (BPMTimestamps[i + 1] - BPMTimestamps[i])]);
        }
        for(var i = 0; i < snData.length; i++) {
            if(typeof snData[i] !== "undefined") {
                for(var j = 0; j < snData[i].length; j++) {
                    if((hLNTYPE == 2) && (j != snData[i].length - 1) && (snData[i][j + 1][1] == hLNOBJ)) {
                        actionData.push([snData[i][j][0], i, 2, snData[i][j + 1][0] - snData[i][j][0]]);
                        j += 1;
                    } else {
                        if(isAvd) {
                            switch(snData[i][j][1]) {
                                case"A8":
                                    actionData.push([snData[i][j][0], i, 0x00, 0]);
                                    break;
                                case"A3":
                                case"A4":
                                case"A5":
                                case"A6":
                                case"A7":
                                case"A9":
                                case"AA":
                                case"AB":
                                case"AC":
                                    var t = BaseToDec(snData[i][j][1], 36) - BaseToDec("A8", 36);
                                    actionData.push([snData[i][j][0], i - t, 0x01, t]);
                                    break;
                                case"E3":
                                case"E4":
                                case"E5":
                                case"E6":
                                case"E7":
                                case"E9":
                                case"EA":
                                case"EB":
                                case"EC":
                                    var t = BaseToDec(snData[i][j][1], 36) - BaseToDec("E8", 36);
                                    actionData.push([snData[i][j][0], i - t, 0xA1, t]);
                                    break;
                            }
                        } else {
                            actionData.push([snData[i][j][0], i, 0x00, 0]);
                        }
                    }
                }
            }
        }
        if(hLNTYPE == 1) {
            for(var i = 0; i < lnData.length; i++) {
                if(typeof lnData[i] !== "undefined") {
                    for(var j = 0; j < lnData[i].length; j++) {
                        if(j != lnData[i].length - 1) {
                            if(isAvd) {
                                switch(lnData[i][j][1]) {
                                    case"A8":
                                        actionData.push([lnData[i][j][0], i, 0x02, lnData[i][j + 1][0] - lnData[i][j][0]]);
                                        break;
                                    case"B8":
                                        actionData.push([lnData[i][j][0], i, 0x62, lnData[i][j + 1][0] - lnData[i][j][0]]);
                                        break;
                                    case"C3":
                                    case"C4":
                                    case"C5":
                                    case"C6":
                                    case"C7":
                                    case"C8":
                                    case"C9":
                                    case"CA":
                                    case"CB":
                                    case"CC":
                                        var t = BaseToDec(lnData[i][j][1], 36) - BaseToDec("C8", 36);
                                        if(t != 0) {
                                            actionData.push([lnData[i][j][0], i - t, 0x61, t]);
                                        }
                                        actionData.push([lnData[i][j][0], i, 0x22, lnData[i][j + 1][0] - lnData[i][j][0]]);
                                        break;
                                    case"D3":
                                    case"D4":
                                    case"D5":
                                    case"D6":
                                    case"D7":
                                    case"D9":
                                    case"DA":
                                    case"DB":
                                    case"DC":
                                        var t = BaseToDec(lnData[i][j][1], 36) - BaseToDec("D8", 36);
                                        if(t != 0) {
                                            actionData.push([lnData[i][j][0], i - t, 0x21, t]);
                                        }
                                        actionData.push([lnData[i][j][0], i, 0x22, lnData[i][j + 1][0] - lnData[i][j][0]]);
                                        break;
                                    case"E3":
                                    case"E4":
                                    case"E5":
                                    case"E6":
                                    case"E7":
                                    case"E8":
                                    case"E9":
                                    case"EA":
                                    case"EB":
                                    case"EC":
                                        var t = BaseToDec(lnData[i][j][1], 36) - BaseToDec("E8", 36);
                                        if(t != 0) {
                                            actionData.push([lnData[i][j][0], i - t, 0x61, t]);
                                        }
                                        actionData.push([lnData[i][j][0], i, 0xA2, lnData[i][j + 1][0] - lnData[i][j][0]]);
                                        break;
                                    case"F3":
                                    case"F4":
                                    case"F5":
                                    case"F6":
                                    case"F7":
                                    case"F9":
                                    case"FA":
                                    case"FB":
                                    case"FC":
                                        var t = BaseToDec(lnData[i][j][1], 36) - BaseToDec("F8", 36);
                                        if(t != 0) {
                                            actionData.push([lnData[i][j][0], i - t, 0x21, t]);
                                        }
                                        actionData.push([lnData[i][j][0], i, 0xA2, lnData[i][j + 1][0] - lnData[i][j][0]]);
                                        break;
                                }
                            } else {
                                actionData.push([lnData[i][j][0], i, 0x02, lnData[i][j + 1][0] - lnData[i][j][0]]);
                            }
                            j += 1;
                        }
                    }
                }
            }
        }
        for(var i = 0; i < actionData.length; i++) {
            actionData[i][0] -= offset;
            if(actionData[i][0] < 300) {
                actionData.splice(i, 1);
                i -= 1;
            }
        }
        beatData = enlargeBeatData(offsetBeatData(beatData, offset), actionData);
        fromData(meta, [
            {"ActionData": actionData.sort(SortNumbers), "BeatData": beatData, "Length": hTime}
        ]);
    };
    var fromBmeBuffer = function (buffer) {
        fromBmsBuffer(buffer, "bme");
    };
    var fromPmsBuffer = function (buffer) {
        fromBmsBuffer(buffer, "pms");
    };
    var fromVosBuffer = function (buffer) {
        var readInt = function (l) {
            var e = (arguments.length > 1 ? arguments[1] : LittleEndian);
            var r = buffer.getInt(l, p, e);
            p += l;
            return r;
        };
        var readUint = function (l) {
            var e = (arguments.length > 1 ? arguments[1] : LittleEndian);
            var r = buffer.getUint(l, p, e);
            p += l;
            return r;
        };
        var readText = function (l) {
            var r = buffer.getText(p, l);
            p += l;
            return r;
        };
        var readDelta = function () {
            var r = 0;
            for(var i = 0; i < 4; i++) {
                var v = readUint(1);
                if(v < 0x80) {
                    r *= 128;
                    r += v;
                    break;
                } else {
                    v -= 0x80;
                    r *= 128;
                    r += v;
                }
            }
            return r;
        };
        var meta = {};
        meta["BufferVos"] = buffer;
        var vos = {};
        var actionData = [];
        var beatData = [];
        var p = 0;
        var timeSet = [];
        var sequSet = [];
        var beatMax = 0;
        var readMidCore = function () {
            p += 10;
            var n = readInt(2, false);
            var frame = 1;
            var frametick = 120;
            var tick = (function () {
                var p1 = readUint(1);
                var p2 = readUint(1);
                if(p1 < 0x80) {
                    frame = 1;
                    frametick = p1 * 256 + p2;
                } else {
                    frame = p1 - 0x80;
                    frametick = p2;
                }
                return frame * frametick;
            })();
            var index = [];
            var comment = [];
            var author = [];
            var title = [];
            var instrument = [];
            var lyrics = [];
            var tag = [];
            var prompt = [];
            var chanel = [];
            var offset = [];
            var timespan = 500;
            var sequencer = 192;
            var bom = 4;
            var bon = 4;
            var toc = 24;
            var com = 8;
            timeSet = [
                [0, timespan]
            ];
            sequSet = [
                [0, sequencer]
            ];
            for(var i = 0; i < n; i++) {
                p += 4;
                var e = readInt(4, false) + p;
                var t = 0;
                while(p < e) {
                    var d = readDelta();
                    t += d / tick * 4;
                    var c = readUint(1);
                    if(c == 0xFF) {
                        c = readUint(1);
                        switch(c) {
                            case 0x00:
                                index[i] = readInt(readUint(1), false);
                                break;
                            case 0x01:
                                comment[i] = readText(readUint(1));
                                break;
                            case 0x02:
                                author[i] = readText(readUint(1));
                                break;
                            case 0x03:
                                title[i] = readText(readUint(1));
                                break;
                            case 0x04:
                                instrument[i] = readText(readUint(1));
                                break;
                            case 0x05:
                                lyrics[i] = readText(readUint(1));
                                break;
                            case 0x06:
                                tag[i] = readText(readUint(1));
                                break;
                            case 0x07:
                                prompt[i] = readText(readUint(1));
                                break;
                            case 0x20:
                                chanel[i] = readInt(readUint(1));
                                break;
                            case 0x21:
                                p = readUint(1) + p;
                                break;
                            case 0x2F:
                                p = readUint(1) + p;
                                break;
                            case 0x51:
                                var v = readInt(readUint(1), false) / 1000 / 4;
                                if(v != timespan) {
                                    timespan = v;
                                    if(t != timeSet[timeSet.length - 1][0]) {
                                        timeSet.push([t, timespan]);
                                    } else {
                                        timeSet[timeSet.length - 1][1] = timespan;
                                    }
                                }
                                break;
                            case 0x54:
                                var h = readUint(1);
                                var m = readUint(1);
                                var s = readUint(1);
                                var f = readUint(1);
                                offset[i] = (((h * 60) + m) * 60 + s) * 1000 + f * 1000 / frame;
                                break;
                            case 0x58:
                                bom = readUint(1);
                                bon = Math.pow(2, readUint(1));
                                toc = readUint(1);
                                com = readUint(1);
                                var v = toc * com * bon / bom;
                                if(v != sequencer) {
                                    sequencer = v;
                                    if(t != sequSet[sequSet.length - 1][0]) {
                                        sequSet.push([t, sequencer]);
                                    } else {
                                        sequSet[sequSet.length - 1][1] = sequencer;
                                    }
                                }
                                break;
                            case 0x59:
                                p = readUint(1) + p;
                                break;
                            case 0x7F:
                                p = readUint(1) + p;
                                break;
                        }
                    }
                }
                if(t > beatMax) {
                    beatMax == t;
                }
            }
            beatMax = Math.ceil(beatMax / 4) * 4;
        }
        switch(buffer[4]) {
            case 0x40:
                var seg = [];
                var readHeader = (function () {
                    for(var i = 0; i < 3; i++) {
                        p += 4;
                        for(var i = 0; i < 3; i++) {
                            seg.push(readInt(4, true));
                            p += 16;
                        }
                    }
                })();
                var readInf = (function () {
                    p = seg[0];
                    var n = 4;
                    if((buffer[p] == 0x56) && (buffer[p + 1] == 0x4F) && (buffer[p + 2] == 0x53) && (buffer[p + 3] == 0x31)) {
                        p += 4;
                        p += 2;
                        p += 64;
                        n = 5;
                    }
                    vos.title = readText(readUint(1));
                    meta["Title"] = vos.title;
                    vos.artist = readText(readUint(1));
                    meta["Artist"] = vos.artist;
                    vos.comment = readText(readUint(1));
                    meta["Comment"] = vos.comment;
                    if(n == 5) {
                        p = readUint(1) + p;
                    }
                    vos.author = readText(readUint(1));
                    meta["Author"] = vos.author;
                    vos.genre = readUint(1);
                    meta["Genre"] = ["Pop", "New Age", "Techno", "Rock", "SoundTrack", "Game&Anime", "Jazz", "CenturyEnd", "Classical", "Other"][vos.genre];
                    p += 1;
                    vos.time = readInt(4, true);
                    meta["Time"] = vos.time;
                    vos.level = readUint(1);
                    meta["Level"] = (vos.level + 1).toString();
                    p += 1023;
                    vos.instrument = [];
                    vos.data = [];
                    for(var i = 0; i < 16; i++) {
                        vos.instrument[i] = readInt(4, true);
                        vos.data[i] = [];
                        var n = readInt(4, true);
                        p += 14;
                        for(var j = 0; j < n; j++) {
                            var timestamp = readInt(4, true);
                            var timespan = readInt(4, true);
                            var index = readUint(1) - 0x90;
                            var pitch = readUint(1);
                            var volume = readUint(1);
                            var track = readUint(1);
                            var type = readUint(1);
                            var islong = 0;
                            var isuser = 0;
                            if(type >= 0x80) {
                                type -= 0x80;
                                islong = 1;
                            }
                            if(track >= 0x80) {
                                track -= 0x80;
                                isuser = 1;
                            }
                            track = parseInt(track.toString(16).fill(2, "0", true)[0]);
                            vos.data[i][j] = [];
                            vos.data[i][j][0] = timestamp;
                            vos.data[i][j][1] = track;
                            vos.data[i][j][2] = islong;
                            vos.data[i][j][3] = timespan;
                            vos.data[i][j][4] = pitch;
                            vos.data[i][j][5] = volume;
                            vos.data[i][j][6] = isuser;
                            vos.data[i][j][7] = type;
                        }
                    }
                })();
                var readMid = (function () {
                    p = seg[1];
                    meta["BufferMid"] = new Uint8Array(buffer.buffer, seg[1], seg[2] - seg[1]);
                    readMidCore();
                })();
                break;
            case 0x0C:
                p += 4;
                var readTrk = (function () {
                    p = readInt(4, true) + p;
                    var m = readInt(4, true) + p;
                    p += 3;
                    var v = parseInt(readText(3));
                    vos.title = readText(readInt(2, true));
                    meta["Title"] = vos.title;
                    vos.artist = readText(readInt(2, true));
                    meta["Artist"] = vos.artist;
                    vos.comment = readText(readInt(2, true));
                    meta["Comment"] = vos.comment;
                    vos.author = readText(readInt(2, true));
                    meta["Author"] = vos.author;
                    vos.genre = readText(readInt(2, true));
                    meta["Genre"] = vos.genre;
                    p += 11;
                    p += 4;
                    p += 4;
                    p += 1024;
                    vos.instrument = [];
                    vos.data = [];
                    var l = readInt(4, true);
                    p += 4;
                    for(var i = 0; i < l; i++) {
                        vos.instrument[readUint(1, true)] = readInt(4, true);
                    }
                    p += 1;
                    vos.level = readUint(1);
                    meta["Level"] = (vos.level + 1).toString();
                    vos.mode = readText(readInt(2, true));
                    p += 4;
                    for(var i = 0; i < l; i++) {
                        vos.data[i] = [];
                        var n = readInt(4, true);
                        for(var j = 0; j < n; j++) {
                            p += 1;
                            var timestamp = readInt(4, true);
                            var pitch = readUint(1, true);
                            var index = readUint(1, true) - 1;
                            var volume = readUint(1, true);
                            var isuser = readUint(1, true);
                            p += 1;
                            var islong = readUint(1, true);
                            var timespan = readInt(4, true);
                            p += 1;
                            vos.data[i][j] = [];
                            vos.data[i][j][0] = timestamp;
                            vos.data[i][j][1] = -1;
                            vos.data[i][j][2] = islong;
                            vos.data[i][j][3] = timespan;
                            vos.data[i][j][4] = pitch;
                            vos.data[i][j][5] = volume;
                            vos.data[i][j][6] = isuser;
                            vos.data[i][j][7] = 0;
                        }
                    }
                    p += 4;
                    var c = readInt(4, true);
                    for(var n = 0; n < c; n++) {
                        vos.data[readUint(1, true)][readInt(4, true)][1] = readUint(1, true);
                    }
                    p = m;
                })();
                var readMid = (function () {
                    p = readInt(4, true) + p;
                    var m = readInt(4, true) + p;
                    meta["BufferMid"] = new Uint8Array(buffer.buffer, p, m - p);
                    readMidCore();
                })();
                break;
        }
        for(var i = 0; i < vos.data.length; i++) {
            if(typeof vos.data[i] === "undefined") {
                continue;
            }
            for(var j = 0; j < vos.data[i].length; j++) {
                if(vos.data[i][j][6]) {
                    if(vos.data[i][j][1] < 0) {
                        continue;
                    }
                    var action = (vos.data[i][j][2] == 0 ? 0x00 : 0x02);
                    var timespan = vos.data[i][j][3];
                    if(action == 0x00) {
                        timespan = 0;
                    }
                    actionData.push([vos.data[i][j][0], vos.data[i][j][1], action, timespan]);
                }
            }
        }
        actionData = actionData.sort(SortNumbers);
        for(var i = 0; i < actionData.length; i++) {
            for(var j = i + 1; j < actionData.length; j++) {
                if(actionData[j][0] <= actionData[i][0] + actionData[i][3]) {
                    if(actionData[i][1] == actionData[j][1]) {
                        actionData.splice(j, 1);
                        j -= 1;
                    }
                } else {
                    break;
                }
            }
        }
        var sequMax = (function () {
            var r = 0;
            for(var i = 0; i < actionData.length; i++) {
                switch(actionData[i][2]) {
                    case 0x00:
                        if(r < actionData[i][0]) {
                            r = actionData[i][0];
                        }
                        break;
                    case 0x02:
                        if(r < actionData[i][0] + actionData[i][3]) {
                            r = actionData[i][0] + actionData[i][3];
                        }
                        break;
                }
            }
            return r;
        })();
        var sequBeat = (function () {
            var r = [0];
            var t = 0;
            var n = 0;
            while(t < sequMax) {
                while((sequSet.length > n + 1) && (r.length - 1 >= sequSet[n + 1][0])) {
                    n += 1;
                }
                var p = r.length - 1;
                while((sequSet.length > n + 1) && (sequSet[n + 1][0] - r.length < 0)) {
                    t += sequSet[n][1] * (sequSet[n + 1][0] - p);
                    p = sequSet[n + 1][0];
                    n += 1;
                }
                t += sequSet[n][1] * (r.length - p);
                r.push(Math.round(t));
            }
            return r;
        })();
        var timeBeat = (function () {
            var r = [0];
            var t = 0;
            var n = 0;
            for(var i = 0; i < sequBeat.length; i++) {
                while((timeSet.length > n + 1) && (i >= timeSet[n + 1][0])) {
                    n += 1;
                }
                var p = i;
                while((timeSet.length > n + 1) && (timeSet[n + 1][0] - i < 1)) {
                    t += timeSet[n][1] * (timeSet[n + 1][0] - p);
                    p = timeSet[n + 1][0];
                    n += 1;
                }
                t += timeSet[n][1] * (i + 1 - p);
                r.push(t);
            }
            return r;
        })();
        var timeMax = (function () {
            while(timeBeat.length < beatMax + 1) {
                timeBeat.push(timeSet[timeSet.length - 1][1]);
            }
            return timeBeat[timeBeat.length - 1];
        })();
        var sequToTime = function (s) {
            for(var i = 0; i < sequBeat.length; i++) {
                if(s < sequBeat[i]) {
                    return timeBeat[i - 1] + (s - sequBeat[i - 1]) / (sequBeat[i] - sequBeat[i - 1]) * (timeBeat[i] - timeBeat[i - 1]);
                }
            }
        };
        for(var i = 0; i < actionData.length; i++) {
            actionData[i][3] = Math.round(sequToTime(actionData[i][0] + actionData[i][3]) - sequToTime(actionData[i][0]));
            actionData[i][0] = Math.round(sequToTime(actionData[i][0]));
        }
        for(var i = 0; i < timeBeat.length - 1; i++) {
            beatData.push([timeBeat[i], 60000 / (timeBeat[i + 1] - timeBeat[i])]);
        }
        beatData = enlargeBeatData(offsetBeatData(beatData, 0), actionData);
        meta["BufferVos"] = vos;
        fromData(meta, [
            {"ActionData": actionData, "BeatData": beatData, "Length": timeMax}
        ]);
    };
    var fromHexBuffer = function (buffer) {
        switch(type) {
            case"vos":
                fromVosBuffer(new Uint8Array().fromHex(buffer.getText().replace(/(\r\n|\s+)/g, "")));
                break;
            case"imd":
                fromImdBuffer([new Uint8Array().fromHex(buffer.getText().replace(/(\r\n|\s+)/g, ""))]);
                break;
        }
    };
    var fromTjaBuffer = function (buffer) {
        var hTime = 0;
        var hOffset = 0;
        var hBPM = 120;
        var hBeat = 1;
        var hDelay = 0;
        var meta = {};
        var game = "Taiko";
        var time = 0;
        var offset = hOffset;
        var bpm = hBPM;
        var beat = hBeat;
        var delay = hDelay;
        var course = 3;
        var level = 0;
        var style = 1;
        var balloon = [];
        var scoreinit = 0;
        var scorediff = 0;
        var scoremode = 1;
        var a = (function () {
            var r = buffer.getText().replace(/\r/g, "\n").replace(/\n\n/g, "\n").split("\n");
            for(var i = 0; i < r.length; i++) {
                if(r[i].substr(0, 12).toUpperCase() == "#BRANCHSTART") {
                    var j = (function () {
                        for(var k = i + 1; k < r.length; k++) {
                            if((r[i].substr(0, 10).toUpperCase() == "#BRANCHEND") || (r[i].substr(0, 4).toUpperCase() == "#END")) {
                                return k;
                            }
                        }
                    })();
                    var d = [];
                    var c = 0;
                    for(var k = i + 1; k < j; k++) {
                        switch(r[i].substr(0, 2).toUpperCase()) {
                            case"#N":
                                c = 1;
                                break;
                            case"#E":
                                c = 2;
                                break;
                            case"#M":
                                c = 3;
                                break;
                        }
                        if(typeof d[c] === "undefined") {
                            d[c] = [];
                        }
                        d[c].push[r[k]];
                    }
                    r.splice(i, j - i);
                    for(var k = 0; k < d.length; k++) {
                        if(d[d.length - k - 1].length != 0) {
                            r.insert(d[d.length - k - 1], i);
                            break;
                        }
                    }
                }
            }
            return r;
        })();
        var index = -1;
        var beatData = [];
        var actionData = [];
        var scripts = [];
        var beats = [];
        var bpms = [];
        var delays = [];
        var dLeft = true;
        var kLeft = true;
        var l5Start = -1;
        var l6Start = -1;
        var l7Start = -1;
        var l9Start = -1;
        for(var i = 0; i < a.length; i++) {
            var ln = a[i].indexOf("//");
            if(ln != -1) {
                a[i] = a[i].substring(0, ln);
            }
            a[i] += " ";
            var lc = a[i].indexOf(":");
            if(lc != -1) {
                var n = a[i].substring(0, lc).toUpperCase().trim();
                var v = a[i].substring(lc + 1).trim();
                switch(n) {
                    case"GAME":
                        game = v;
                        meta["Game"] = v;
                        break;
                    case"TIME":
                        v = parseFloat(v);
                        if(!isNaN(v) && (v >= 0)) {
                            hTime = Math.round(v * 1000);
                        }
                        break;
                    case"OFFSET":
                        v = parseFloat(v);
                        if(!isNaN(v)) {
                            hOffset = Math.round(v * 1000);
                            offset = hOffset;
                        }
                        break;
                    case"BPM":
                        v = parseFloat(v);
                        if(!isNaN(v) && (v >= 0)) {
                            bpm = v;
                        }
                        break;
                    case"COURSE":
                        switch(v.toUpperCase().trim()) {
                            case"0":
                            case"EASY":
                                course = 0;
                                break;
                            case"1":
                            case"NORMAL":
                                course = 1;
                                break;
                            case"2":
                            case"HARD":
                                course = 2;
                                break;
                            case"3":
                            case"ONI":
                                course = 3;
                                break;
                            case"4":
                            case"EDIT":
                                course = 4;
                                break;
                            case"5":
                            case"TOWER":
                                course = 5;
                                break;
                        }
                        break;
                    case"LEVEL":
                        level = v;
                        break;
                    case"STYLE":
                        switch(v.toUpperCase().trim()) {
                            case"1":
                            case"SINGLE":
                                style = 1;
                                break;
                            case"2":
                            case"DOUBLE":
                            case"COUPLE":
                                style = 2;
                                break;
                        }
                        break;
                    case"BALLOON":
                        v = v.split(",");
                        for(var j = 0; j < v.length; j++) {
                            if(!isNaN(v[j]) && (v[j] >= 0)) {
                                balloon.push(v[j]);
                            }
                        }
                        break;
                    case"SCOREINIT":
                        v = parseFloat(v);
                        if(!isNaN(v) && (v >= 0)) {
                            scoreinit = v;
                        }
                        break;
                    case"SCOREDIFF":
                        v = parseFloat(v);
                        if(!isNaN(v) && (v >= 0)) {
                            scorediff = v;
                        }
                        break;
                    case"SCOREMODE":
                        v = parseFloat(v);
                        if(!isNaN(v) && (v >= 0)) {
                            scoremode = v;
                        }
                        break;
                }
                var h = ["Title", "SubTitle", "Creator", "Maker", "DateTime", "Wave", "SongVol", "SeVol", "DemoStart", "Life", "Side"];
                for(var j = 0; j < h.length; j++) {
                    if(n == h[j].toUpperCase()) {
                        meta[h[j]] = v;
                        break;
                    }
                }
            } else {
                if(a[i][0] == "#") {
                    var ls = a[i].indexOf(" ", 1);
                    if(ls != -1) {
                        var n = a[i].substring(1, ls).toUpperCase().trim();
                        var v = a[i].substring(ls + 1).trim();
                        switch(n) {
                            case"START":
                                index += 1;
                                if(index == 0) {
                                    meta["Course"] = [];
                                    meta["Level"] = [];
                                    meta["Player"] = [];
                                    meta["Style"] = [];
                                    meta["Balloon"] = [];
                                    meta["ScoreInit"] = [];
                                    meta["ScoreDiff"] = [];
                                    meta["ScoreMode"] = [];
                                }
                                meta["Course"][index] = course;
                                meta["Level"][index] = level;
                                meta["Player"][index] = (function () {
                                    switch(v) {
                                        case"P1":
                                            return 1;
                                            break;
                                        case"P2":
                                            return 2;
                                            break;
                                        default:
                                            return undefined;
                                            break;
                                    }
                                })();
                                meta["Style"][index] = style;
                                meta["Balloon"][index] = balloon;
                                meta["ScoreInit"][index] = scoreinit;
                                meta["ScoreDiff"][index] = scorediff;
                                meta["ScoreMode"][index] = scoremode;
                                beatData[index] = (function () {
                                    var r = [];
                                    if(offset < 0) {
                                        var b = Math.ceil(-offset / 60000 / beat * bpm);
                                        var tt = -offset / b;
                                        var bt = 60000 / tt;
                                        r.push([time, bt]);
                                        for(var j = 0; j < b; j++) {
                                            time += tt;
                                            r.push([time, bt]);
                                        }
                                    } else if(offset > 0) {
                                        var b = Math.ceil(offset / 60000 / beat * bpm);
                                        var tt = b * 60000 * beat / bpm - offset;
                                        var bt = 60000 / tt;
                                        r.push([time, bt]);
                                        time += tt;
                                        r.push([time, bt]);
                                    } else {
                                        r.push([time, 1 / beat * bpm]);
                                    }
                                    return r;
                                })();
                                actionData[index] = [];
                                break;
                            case"END":
                                time = 0;
                                offset = hOffset;
                                bpm = hBPM;
                                beat = hBeat;
                                delay = hDelay;
                                l5Start = -1;
                                l6Start = -1;
                                l7Start = -1;
                                l9Start = -1;
                                break;
                            case"BPMCHANGE":
                                if(!isNaN(v) && (v > 0)) {
                                    bpm = v;
                                }
                                break;
                            case"MEASURE":
                                var ld = v.indexOf("/");
                                var d1 = v.substring(0, ld).trim();
                                var d2 = v.substring(ld + 1).trim();
                                v = d1 / d2;
                                if(!isNaN(v) && (v > 0)) {
                                    beat = v;
                                }
                                break;
                            case"DELAY":
                                if(!isNaN(v)) {
                                    delay += Math.floor(v * 1000);
                                }
                                break;
                            case"SECTION":
                            case"BRANCHSTART":
                            case"LEVELHOLD":
                            case"BRANCHEND":
                            case"GOGOSTART":
                            case"GOGOEND":
                            case"SCROLL":
                            case"BMSCROLL":
                            case"HBSCROLL":
                            case"BARLINEON":
                            case"BARLINEOFF":
                                break;
                        }
                    }
                } else if(index != -1) {
                    a[i] = a[i].toUpperCase().trim();
                    if(a[i] == ",") {
                        a[i] = "0,";
                    }
                    for(var j = 0; j < a[i].length; j++) {
                        switch(a[i][j]) {
                            case",":
                                if(game.toUpperCase() == "JUBE") {
                                    while(scripts.length % 4 != 0) {
                                        scripts.push("0");
                                        beats.push(beat);
                                        bpms.push(bpm);
                                        delays.push(delay);
                                    }
                                    var s = [];
                                    var b1 = [];
                                    var b2 = [];
                                    var d = [];
                                    for(var k = 0; k < scripts.length; k += 4) {
                                        s.push(scripts[k] + scripts[k + 1] + scripts[k + 2] + scripts[k + 3]);
                                        b1.push(beats[k]);
                                        b2.push(bpms[k]);
                                        d.push(delays[k]);
                                    }
                                    scripts = s;
                                    beats = b1;
                                    bpms = b2;
                                    delays = d;
                                }
                                var p = (function () {
                                    var r = 0;
                                    for(var k = 0; k < scripts.length; k++) {
                                        r += beats[k] / bpms[k];
                                    }
                                    return r;
                                })();
                                var t = (function () {
                                    var r = [];
                                    var d = (function () {
                                        var r = 0;
                                        for(var k = 0; k < scripts.length; k++) {
                                            r += 60000 * beats[k] / bpms[k] / scripts.length * 4;
                                        }
                                        return r;
                                    })();
                                    for(var k = 0; k < scripts.length; k++) {
                                        r[k] = beats[k] / bpms[k] / p * d + delays[k];
                                    }
                                    return r;
                                })();
                                for(var k = 0; k < scripts.length; k++) {
                                    if(game.toUpperCase() == "JUBE") {
                                        var b = ["", "", "", ""];
                                        for(var l = 0; l < scripts[k].length; l++) {
                                            var s = parseInt(scripts[k][l], 16).toString(2).fill(4);
                                            for(var m = 0; m < s.length; m++) {
                                                b[m] += s[m];
                                            }
                                        }
                                        for(var l = 0; l < b.length; l++) {
                                            b[l] = parseInt(b[l], 2);
                                            if(b[l] != 0) {
                                                actionData[index].push([time, l, 0x00, b[l]]);
                                            }
                                        }
                                    } else {
                                        var endLong = (function () {
                                            switch(scripts[k]) {
                                                case"1":
                                                case"2":
                                                case"3":
                                                case"4":
                                                case"5":
                                                case"6":
                                                case"7":
                                                case"8":
                                                case"9":
                                                    if(l5Start != -1) {
                                                        actionData[index].push([l5Start, 1, 0x02, time - l5Start]);
                                                        l5Start = -1;
                                                    }
                                                    if(l6Start != -1) {
                                                        actionData[index].push([l6Start, 2, 0x02, time - l6Start]);
                                                        l6Start = -1;
                                                    }
                                                    if(l7Start != -1) {
                                                        actionData[index].push([l7Start, 0, 0x02, time - l7Start]);
                                                        l7Start = -1;
                                                    }
                                                    if(l9Start != -1) {
                                                        actionData[index].push([l9Start, 3, 0x02, time - l9Start]);
                                                        l9Start = -1;
                                                    }
                                                    break;
                                            }
                                        })();
                                        switch(scripts[k]) {
                                            case"1":
                                                actionData[index].push([time, (dLeft ? 1 : 2), 0x00, 0]);
                                                dLeft = !dLeft;
                                                break;
                                            case"2":
                                                actionData[index].push([time, (kLeft ? 0 : 3), 0x00, 0]);
                                                kLeft = !kLeft;
                                                break;
                                            case"3":
                                                actionData[index].push([time, 1, 0x00, 0]);
                                                actionData[index].push([time, 2, 0x00, 0]);
                                                break;
                                            case"4":
                                                actionData[index].push([time, 0, 0x00, 0]);
                                                actionData[index].push([time, 3, 0x00, 0]);
                                                break;
                                            case"5":
                                                if(l5Start == -1) {
                                                    l5Start = time;
                                                }
                                                break;
                                            case"6":
                                                if(l6Start == -1) {
                                                    l6Start = time;
                                                }
                                                break;
                                            case"7":
                                                if(l7Start == -1) {
                                                    l7Start = time;
                                                }
                                                break;
                                            case"8":
                                                break;
                                            case"9":
                                                if(l9Start == -1) {
                                                    l9Start = time;
                                                }
                                                break;
                                        }
                                    }
                                    time += t[k];
                                }
                                var b = (function () {
                                    var r = (function () {
                                        var v = [];
                                        var l = Math.ceil(beat * 4);
                                        for(var k = 0; k < l; k++) {
                                            v[k] = 0;
                                        }
                                        return v;
                                    })();
                                    var c = 0;
                                    var d1 = 0;
                                    var d2 = 0;
                                    for(var k = 0; k < t.length; k++) {
                                        if((k + 1) / t.length <= (c + 1) / r.length) {
                                            r[c] += t[k] * ((k + 1) / t.length - c / r.length - d1) * t.length;
                                            d1 += 1 / t.length;
                                            d2 = 0;
                                            if((k + 1) / t.length == (c + 1) / r.length) {
                                                c += 1;
                                                d1 = 0;
                                            }
                                        } else {
                                            r[c] += t[k] * ((c + 1) / r.length - k / t.length - d2) * t.length;
                                            c += 1;
                                            d1 = 0;
                                            d2 += 1 / r.length;
                                            k -= 1;
                                        }
                                    }
                                    return r;
                                })();
                                for(var k = 0; k < b.length; k++) {
                                    beatData[index].push([beatData[index][beatData[index].length - 1][0] + b[k], 60000 * beat / b[k]]);
                                }
                                scripts = [];
                                beats = [];
                                bpms = [];
                                delays = [];
                                break;
                            default:
                                scripts.push(a[i][j]);
                                beats.push(beat);
                                bpms.push(bpm);
                                delays.push(delay);
                                break;
                        }
                    }
                }
            }
        }
        var data = [];
        var list = [];
        for(var i = 0; i < index + 1; i++) {
            data[i] = {"ActionData": actionData[i].sort(SortNumbers), "BeatData": beatData[i], "Length": hTime};
            list[i] = "C " + meta["Course"][i] + ", L " + meta["Level"][i] + (typeof meta["Player"][i] === "undefined" ? "" : ", P " + meta["Player"][i]);
        }
        meta["IndexList"] = list;
        fromData(meta, data);
    };
    var fromOsuBuffer = function (buffer) {
        var osu = buffer.getText().replace(/\r/g, "\n").replace(/\n\n/g, "\n") + "\n" + "[";
        var readValue = function (f1, f2) {
            var d = (arguments.length > 2 ? arguments[2] : "");
            var b = (arguments.length > 3 ? arguments[3] : false);
            var f3 = ":";
            var e = osu.extract("[" + f1 + "]" + "\n", "\n" + "[");
            if(typeof e === "undefined") {
                return d;
            }
            var a = e.split("\n");
            for(var i = 0; i < a.length; i++) {
                var s = a[i].trim();
                if(s.indexOf(f2) == 0) {
                    s = s.substring(f2.length).trim();
                    if(s.indexOf(f3) == 0) {
                        s = s.substring(f3.length).trim();
                        if(b) {
                            var v = parseFloat(s);
                            if(!isNaN(v)) {
                                return v;
                            }
                        } else {
                            return s;
                        }
                    }
                }
            }
            return d;
        };
        var readArray = function (f) {
            var r = [];
            var s = osu.extract("[" + f + "]" + "\n", "\n" + "[");
            if(typeof s !== "undefined") {
                r = s.split("\n");
            }
            return r;
        };
        var readMeta = function (f1, f2) {
            var d = (arguments.length > 2 ? arguments[2] : "");
            var v = readValue(f1, f2, d);
            if(v != "") {
                meta[f2] = v;
            }
        };
        var meta = {};
        readMeta("General", "AudioFilename");
        readMeta("General", "AudioLeadIn");
        readMeta("General", "PreviewTime");
        readMeta("General", "Countdown");
        readMeta("General", "SampleSet");
        readMeta("General", "StackLeniency");
        readMeta("General", "LetterboxInBreaks");
        readMeta("General", "SpecialStyle");
        readMeta("General", "StoryFireInFront");
        readMeta("General", "EpilepsyWarning");
        readMeta("General", "CountdownOffset");
        readMeta("General", "WidescreenStoryboard");
        readMeta("Editor", "Bookmarks");
        readMeta("Editor", "DistanceSpacing");
        readMeta("Editor", "BeatDivisor");
        readMeta("Editor", "GridSize");
        readMeta("Editor", "TimelineZoom");
        readMeta("Metadata", "Title");
        readMeta("Metadata", "TitleUnicode");
        readMeta("Metadata", "Artist");
        readMeta("Metadata", "ArtistUnicode");
        readMeta("Metadata", "Creator");
        readMeta("Metadata", "Maker");
        readMeta("Metadata", "DateTime");
        readMeta("Metadata", "Version");
        readMeta("Metadata", "Tags");
        readMeta("Metadata", "BeatmapID");
        readMeta("Metadata", "BeatmapSetID");
        readMeta("Difficulty", "HPDrainRate");
        readMeta("Difficulty", "CircleSize");
        readMeta("Difficulty", "OverallDifficulty");
        readMeta("Difficulty", "ApproachRate");
        readMeta("Difficulty", "SliderMultiplier");
        readMeta("Difficulty", "SliderTickRate");
        var events = readArray("Events");
        var l1 = events.indexOf("//Background and Video events");
        var l2 = events.indexOf("//Break Periods");
        for(var i = l2 - 1; i > l1; i--) {
            var a = events[i].split(",");
            if((a.length > 2) && (a[0] != "Video")) {
                var v = a[2];
                if(v[0] == '"') {
                    v = v.substring(1);
                }
                if(v[v.length - 1] == '"') {
                    v = v.substring(0, v.length - 1);
                }
                meta["Background"] = v;
                break;
            }
        }
        var mode = readValue("General", "Mode", 0, true);
        var circleSize = readValue("Difficulty", "CircleSize", 4, true);
        var sliderMultiplier = readValue("Difficulty", "SliderMultiplier", 1.4, true);
        var timingPoints = readArray("TimingPoints");
        var colours = readArray("Colours");
        meta["Colours"] = (function () {
            var r = [];
            for(var i = 0; i < colours.length; i++) {
                var l = colours[i].split(":");
                if(l.length == 2) {
                    l[0] = l[0].trim();
                    l[1] = l[1].trim();
                    var v = l[1].split(",");
                    for(var j = 0; j < 3; j++) {
                        if(v.length > j) {
                            v[j] = parseInt(v[j].trim()) || 0;
                        } else {
                            v[j] = 0;
                        }
                    }
                    v = v[0] + "," + v[1] + "," + v[2];
                    if((l[0].length > 5) && (l[0].substring(0, 5) == "Combo")) {
                        var n = parseInt(l[0].substring(5, l[0].length));
                        if(isNaN(n)) {
                            continue;
                        }
                        r[n] = v;
                    }
                }
            }
            for(var i = 0; i < r.length; i++) {
                if(typeof r[i] === "undefined") {
                    r.splice(i, 1);
                    i -= 1;
                }
            }
            return r;
        })();
        var hitObjects = readArray("HitObjects");
        var toBeat = function (t) {
            while(t + 60000 / beatData[beatData.length - 1][1] > beatData[beatData.length - 1][0]) {
                beatData.push([beatData[beatData.length - 1][0] + 60000 / beatData[beatData.length - 1][1], beatData[beatData.length - 1][1]]);
            }
            return toBeatDataBeat(beatData, t);
        };
        var fromBeat = function (b) {
            while(b > beatData.length - 1) {
                beatData.push([beatData[beatData.length - 1][0] + 60000 / beatData[beatData.length - 1][1], beatData[beatData.length - 1][1]]);
            }
            return fromBeatDataBeat(beatData, b);
        };
        var beatData = (function () {
            var r = [];
            var a = (function () {
                var v = [];
                for(var i = 0; i < timingPoints.length; i++) {
                    if(timingPoints[i] != "") {
                        var d = timingPoints[i].split(",");
                        var l = (function () {
                            for(var j = 0; j < v.length; j++) {
                                if(v[j][0] == d[0]) {
                                    return j;
                                }
                            }
                            return-1;
                        })();
                        if(l != -1) {
                            v[l] = d;
                        } else {
                            v.push(d);
                        }
                    }
                }
                return v.sort(SortNumbers);
            })();
            var o = 0;
            var b = 120;
            var x = 1;
            var m = 4;
            var d = 500;
            for(var i = 0; i < a.length; i++) {
                var inherited = 0;
                var offset = NaN;
                var bpm = 120;
                var meter = 4;
                if(a[i].length > 6) {
                    inherited = parseInt(a[i][6]) || 0;
                }
                if(a[i].length > 1) {
                    a[i][0] = parseFloat(a[i][0]);
                    if(isNaN(a[i][0])) {
                        continue;
                    }
                    offset = a[i][0];
                    if(i != 0) {
                        while(o + d < offset) {
                            r.push([o + d, b * x / 4 * m]);
                            o += d;
                        }
                    }
                    a[i][1] = parseFloat(a[i][1]) || (inherited == 1 ? 500 : -100);
                    if(inherited == 1) {
                        if(a[i][1] <= 0) {
                            a[i][1] = 60000 / b;
                        }
                        bpm = 60000 / a[i][1];
                        b = bpm;
                    } else {
                        if(a[i][1] >= 0) {
                            a[i][1] = -100;
                        }
                        x = -100 / a[i][1];
                        bpm = b * x;
                    }
                }
                if(a[i].length > 2) {
                    meter = parseFloat(a[i][2]) || 4;
                    m = meter;
                }
                bpm = bpm / 4 * meter;
                d = 60000 / bpm;
                o = offset;
                r.push([offset, bpm]);
            }
            return r;
        })();
        var actionData = (function () {
            var r = [];
            var a = (function () {
                var v = [];
                for(var i = 0; i < hitObjects.length; i++) {
                    if(hitObjects[i] != "") {
                        v.push(hitObjects[i].split(","));
                    }
                }
                return v;
            })();
            var k = GetSelectValue("selectTransKey");
            for(var i = 0; i < a.length; i++) {
                var getTrack = function (t) {
                    var v = -1;
                    if(a[i].length > 0) {
                        a[i][0] = parseFloat(a[i][0]) || 0;
                        a[i][0] = (a[i][0] < 0 ? 0 : a[i][0]);
                        a[i][0] = (a[i][0] > 511 ? 511 : a[i][0]);
                        v = a[i][0] / 512 * t;
                        v = (v < 0 ? 0 : Math.floor(v));
                    }
                    return v;
                };
                var getSlideData = function () {
                    var v = ["", []];
                    if(a[i].length > 5) {
                        var d = a[i][5].split("|");
                        if(d.length > 0) {
                            switch(d[0]) {
                                case"L":
                                case"C":
                                case"P":
                                case"B":
                                    v[0] = d[0];
                                    break;
                                default:
                                    v[0] = "B";
                                    break;
                            }
                            v[1].push([parseFloat(a[i][0]) || 0, parseFloat(a[i][1]) || 0]);
                            for(var j = 1; j < d.length; j++) {
                                var p = d[j].split(":");
                                if(p.length == 2) {
                                    p[0] = parseFloat(p[0]) || 0;
                                    p[1] = parseFloat(p[1]) || 0;
                                } else {
                                    p[0] = 0;
                                    p[1] = 0;
                                }
                                p[0] = (p[0] < 0 ? 0 : p[0]);
                                p[0] = (p[0] > 511 ? 511 : p[0]);
                                p[1] = (p[1] < 0 ? 0 : p[1]);
                                p[1] = (p[1] > 511 ? 511 : p[1]);
                                v[1].push([p[0], p[1]]);
                            }
                        }
                    }
                    return v;
                };
                var getPoint = function (d, t, r) {
                    var v = [];
                    for(var j = 0; j < d.length; j++) {
                        for(var k = 0; k < d.length; k++) {
                            if((k != j) && (Math.floor(d[k][0]) == Math.floor(d[j][0])) && (Math.floor(d[k][1]) == Math.floor(d[j][1]))) {
                                d.splice(k, 1);
                                j -= 1;
                                break;
                            }
                        }
                    }
                    t = (t < 0 ? 0 : t);
                    t = (t > r ? r : t);
                    var reverse = (Math.floor(t) % 2 != 0);
                    t = t - Math.floor(t);
                    t = (reverse ? 1 - t : t);
                    var GetL = function (d) {
                        var x = (d[1][0] - d[0][0]) * t + d[0][0];
                        var y = (d[1][1] - d[0][1]) * t + d[0][1];
                        return[x, y];
                    };
                    var GetC = function (d) {
                        var s = [];
                        var l = 0;
                        for(var j = 1; j < d.length; j++) {
                            s.push(Math.sqrt(Math.pow(d[j][0] - d[j - 1][0], 2) + Math.pow(d[j][1] - d[j - 1][1], 2)));
                            l += s[s.length - 1];
                        }
                        l *= t;
                        for(var j = 0; j < s.length; j++) {
                            if((l > s[j]) && (!IsNumberClose(l, s[j]))) {
                                l -= s[j];
                            } else {
                                var x = d[j][0] + (d[j + 1][0] - d[j][0]) * l / s[j];
                                var y = d[j][1] + (d[j + 1][1] - d[j][1]) * l / s[j];
                                return[x, y];
                            }
                        }
                    };
                    var GetP = function (d) {
                        return GetC(d);
                    };
                    var GetB = function (d) {
                        return GetC(d);
                    };
                    switch(d[0]) {
                        case"L":
                            switch(d[1].length) {
                                case 0:
                                    v = [0, 0];
                                    break;
                                case 1:
                                    v = d[1][0];
                                    break;
                                default:
                                    v = GetL([d[1][0], d[1][1]]);
                                    break;
                            }
                            break;
                        case"C":
                            switch(d[1].length) {
                                case 0:
                                    v = [0, 0];
                                    break;
                                case 1:
                                    v = d[1][0];
                                    break;
                                default:
                                    v = GetC(d[1]);
                                    break;
                            }
                            break;
                        case"P":
                            switch(d[1].length) {
                                case 0:
                                    v = [0, 0];
                                    break;
                                case 1:
                                    v = d[1][0];
                                    break;
                                case 2:
                                    v = GetL([d[1][0], d[1][1]]);
                                    break;
                                default:
                                    v = GetP([d[1][0], d[1][1], d[1][2]]);
                                    break;
                            }
                            break;
                        case"B":
                            switch(d[1].length) {
                                case 0:
                                    v = [0, 0];
                                    break;
                                case 1:
                                    v = d[1][0];
                                    break;
                                case 2:
                                    v = GetL([d[1][0], d[1][1]]);
                                    break;
                                default:
                                    v = GetB(d[1]);
                                    break;
                            }
                            break;
                    }
                    return v;
                };
                var getRepeat = function () {
                    if(a[i].length > 6) {
                        return parseInt(a[i][6]) || 1;
                    }
                };
                var getTimespan = function (type, repeat) {
                    var v = 0;
                    switch(type) {
                        case"Slider":
                            if(a[i].length > 7) {
                                v = parseFloat(a[i][7]) || 0;
                                v = fromBeat(toBeat(timestamp) + a[i][7] / sliderMultiplier / 100) - timestamp;
                            }
                            break;
                        case"Spinner":
                            if(a[i].length > 5) {
                                v = (parseFloat(a[i][5]) || 0) - timestamp;
                            }
                            break;
                        case"Hold":
                            if(a[i].length > 5) {
                                var t = a[i][5].split(":");
                                if(t.length > 0) {
                                    v = (parseFloat(t[0]) || 0) - timestamp;
                                }
                            }
                            break;
                    }
                    return v * repeat;
                };
                var bpm = (function () {
                    var b = [];
                    for(var i = 0; i < beatData.length; i++) {
                        b.push(beatData[i][1]);
                    }
                    return b.majority();
                })();
                var type = (function () {
                    if(a[i].length > 3) {
                        a[i][3] = parseInt(a[i][3]) || 0;
                        a[i][3] = a[i][3].toString(2).fill(8);
                        if(a[i][3] >> 0 & 1) {
                            return"Circle";
                        } else if(a[i][3] >> 1 & 1) {
                            return"Slider";
                        } else if(a[i][3] >> 3 & 1) {
                            return"Spinner";
                        } else if(a[i][3] >> 7 & 1) {
                            return"Hold";
                        }
                    }
                    return"";
                })();
                var timestamp = (function () {
                    if(a[i].length > 2) {
                        a[i][2] = parseFloat(a[i][2]) || -1;
                        return a[i][2];
                    }
                    return-1;
                })();
                if(timestamp < 0) {
                    continue;
                }
                var track = -1;
                var action = -1;
                var timespan = 0;
                switch(mode) {
                    case 0:
                    case 2:
                        track = getTrack(k);
                        switch(type) {
                            case"Circle":
                                action = 0x00;
                                r.push([timestamp, track, action, timespan]);
                                break;
                            case"Slider":
                                var repeat = getRepeat();
                                timespan = getTimespan(type, repeat);
                                if(timespan <= 0) {
                                    action = 0x00;
                                    timespan = 0;
                                    r.push([timestamp, track, action, timespan]);
                                } else {
                                    var d = [];
                                    var data = getSlideData();
                                    var u = 60000 / bpm / 4;
                                    var t = 0;
                                    var tk = Math.floor(getPoint(data, t / timespan * repeat, repeat)[0] / 512 * k);
                                    var b = true;
                                    while(b) {
                                        d.push([timestamp + t, tk, 0x22, u]);
                                        t += u;
                                        if((t > timespan) || (IsNumberClose(t, timespan))) {
                                            t = timespan;
                                            b = false;
                                        }
                                        tk = Math.floor(getPoint(data, t / timespan * repeat, repeat)[0] / 512 * k);
                                        if(tk != d[d.length - 1][1]) {
                                            d.push([timestamp + t, d[d.length - 1][1], 0x21, tk - d[d.length - 1][1]]);
                                        }
                                    }
                                    for(var j = 0; j < d.length - 1; j++) {
                                        if((j + 1 < d.length) && (d[j][2] == 0x22) && (d[j + 1][2] == 0x22) && (d[j][1] == d[j + 1][1])) {
                                            d[j][3] += d[j + 1][3];
                                            d.splice(j + 1, 1);
                                            j -= 1;
                                        }
                                    }
                                    if(d.length == 1) {
                                        d[0][2] = 0x02;
                                    } else {
                                        d[0][2] = 0x62;
                                        d[d.length - 1][2] = (d[d.length - 1][2] == 0x22 ? 0xA2 : d[d.length - 1][2]);
                                        d[d.length - 1][2] = (d[d.length - 1][2] == 0x21 ? 0xA1 : d[d.length - 1][2]);
                                    }
                                    r.write(d);
                                }
                                break;
                            case"Spinner":
                                action = 0x02;
                                timespan = getTimespan(type, 1);
                                if(timespan <= 0) {
                                    action = 0x00;
                                    timespan = 0;
                                }
                                r.push([timestamp, track, action, timespan]);
                                break;
                        }
                        break;
                    case 1:
                        track = getTrack(4);
                        var addData = function () {
                            switch(action) {
                                case 0x00:
                                    var tag = (function () {
                                        if(a[i].length > 4) {
                                            a[i][4] = parseFloat(a[i][4]) || 0;
                                            a[i][4] = a[i][4].toString(2).fill(8);
                                            return a[i][4].substring(a[i][4].length - 4, a[i][4].length - 1);
                                        }
                                        return"000";
                                    })();
                                    switch(tag) {
                                        case"000":
                                            r.push([timestamp, track, action, timespan]);
                                            break;
                                        case"001":
                                        case"100":
                                        case"101":
                                            r.push([timestamp, track, action, timespan]);
                                            break;
                                        case"010":
                                            r.push([timestamp, 1, action, timespan]);
                                            r.push([timestamp, 2, action, timespan]);
                                            break;
                                        case"011":
                                        case"110":
                                        case"111":
                                            r.push([timestamp, 0, action, timespan]);
                                            r.push([timestamp, 3, action, timespan]);
                                            break;
                                    }
                                    break;
                                case 0x02:
                                    r.push([timestamp, track, action, timespan]);
                                    break;
                            }
                        };
                        switch(type) {
                            case"Circle":
                                action = 0x00;
                                addData();
                                break;
                            case"Slider":
                            case"Spinner":
                                action = 0x02;
                                var repeat = getRepeat() || 1;
                                timespan = getTimespan(type, repeat);
                                if(timespan <= 0) {
                                    action = 0x00;
                                    timespan = 0;
                                }
                                addData();
                                break;
                        }
                        break;
                    case 3:
                        track = getTrack(circleSize);
                        switch(type) {
                            case"Circle":
                                action = 0x00;
                                break;
                            case"Hold":
                                action = 0x02;
                                timespan = getTimespan(type, 1);
                                if(timespan <= 0) {
                                    action = 0x00;
                                    timespan = 0;
                                }
                                break;
                        }
                        r.push([timestamp, track, action, timespan]);
                        break;
                }
            }
            for(var i = 0; i < r.length; i++) {
                if(r[i][2] == 0xA1) {
                    for(var j = 0; j < r.length; j++) {
                        if(IsNumberClose(r[j][0], r[i][1]) && (r[j][1] == r[i][1] + r[i][3])) {
                            switch(r[j][2]) {
                                case 0x00:
                                    r.splice(j, 1);
                                    break;
                                case 0x02:
                                    r[i][1] = 0x21;
                                    r[j][2] = 0xA2;
                                    break;
                                case 0x62:
                                    r[i][1] = 0x21;
                                    r[j][2] = 0x22;
                                    break;
                            }
                        }
                    }
                }
            }
            return sortActionData(r);
        })();
        beatData = enlargeBeatData(offsetBeatData(beatData, 0), actionData);
        fromData(meta, [
            {"ActionData": sortActionData(actionData), "BeatData": beatData}
        ]);
    };
    var fromXmlBuffer = function (buffer, type) {
        switch(type) {
            case"yddr":
                var a = buffer.getText().replace(/\r/g, "\n").replace(/\n\n/g, "\n").extract("<beats>\n", "\n</beats>").split("\n");
                var actionData = [];
                for(var i = 0; i < a.length; i++) {
                    var time = a[i].extract("<beat time=" + '"', '"' + " type=" + '"');
                    var type = a[i].extract('"' + " type=" + '"', '"' + "/>").split("$");
                    time = parseInt(time);
                    if(isNaN(time)) {
                        continue;
                    }
                    for(var j = 0; j < type.length; j++) {
                        var t = type[j].split("_");
                        switch(t.length) {
                            case 1:
                                t = parseInt(t);
                                if(isNaN(t)) {
                                    continue;
                                }
                                actionData.push([time, t - 1, 0x00, 0]);
                                break;
                            case 2:
                                var t1 = parseInt(t[0]);
                                if(isNaN(t1)) {
                                    continue;
                                }
                                var t2 = parseInt(t[1]);
                                if(isNaN(t2)) {
                                    continue;
                                }
                                actionData.push([time, t1 - 1, 0x02, t2]);
                                break;
                        }
                    }
                }
                fromData({}, [
                    {"ActionData": actionData.sort(SortNumbers)}
                ]);
                break;
            case"ydsd":
                fromXmlBuffer(CryptDes(false, new Uint8Array().fromBase64(buffer.getText()), new Uint8Array().fromText(new Uint8Array().fromText((3263047).toString(16)).getBase64())), "yddr");
                break;
            case"mde":
                var key = GetSelectValue("selectTransKey");
                var meta = {};
                meta["BufferXmlmde"] = [];
                meta["BufferXmlmde"][Index] = buffer;
                if(typeof Buffer["Xmlmde"][Index] === "undefined") {
                    Buffer["Xmlmde"][Index] = buffer;
                }
                if(typeof Text["Xmlmde"][Index] === "undefined") {
                    Text["Xmlmde"][Index] = toXmlText(Index, "mde");
                }
                var p = 0;
                var readXml = function (x, f, b) {
                    var b = (arguments.length > 2 ? arguments[2] : true);
                    if(typeof x === "undefined") {
                        return;
                    }
                    if(b) {
                        p = 0;
                    }
                    var f1 = "<" + f;
                    var f2 = "</" + f + ">";
                    var l1 = x.indexOf(f1, p);
                    if(l1 == -1) {
                        return;
                    }
                    var l2 = x.indexOf(f2, l1);
                    if(l2 == -1) {
                        return;
                    }
                    p = l2 + f2.length;
                    return x.substring(l1 + f1.length, l2);
                };
                var readString = function (x, f) {
                    var f1 = "<" + f + ">";
                    var f2 = "</" + f + ">";
                    return x.extract(f1, f2);
                };
                var readFloat = function (x, f) {
                    var r = readString(x, f);
                    if(typeof r !== "undefined") {
                        r = parseFloat(r);
                    }
                    return r;
                };
                var transPos = function (t, p) {
                    for(var i = 0; i < camData.length; i++) {
                        if((camData[i][0] >= t) || (i == camData.length - 1)) {
                            var j = (i == 0 ? 0 : i - 1);
                            var r = (camData[j][0] == camData[i][0] ? 0 : (t >= camData[i][0] ? 1 : (t - camData[j][0]) / (camData[i][0] - camData[j][0])));
                            var x = r * (camData[i][1][0] - camData[j][1][0]) + camData[j][1][0];
                            var y = r * (camData[i][1][1] - camData[j][1][1]) + camData[j][1][1];
                            var s = r * (camData[i][2] - camData[j][2]) + camData[j][2];
                            var w = canvasSize[0] * s / 1280;
                            var h = canvasSize[1] * s / 1280;
                            var rx = (p[0] - x + w / 2) / w;
                            var ry = (p[1] - y + h / 2) / h;
                            if((rx < 0) || (rx >= 1) || (ry < 0) || (ry >= 1)) {
                                var rr = 0;
                                while(((rx < 0) && (-rx / rr >= 1)) || ((ry < 0) && (-ry / rr >= 1)) || ((rx >= 1) && (rx / rr >= 1)) || ((ry >= 1) && (ry / rr >= 1))) {
                                    rr += 1;
                                }
                                rx = (rx / rr + 1) / 2;
                                ry = (ry / rr + 1) / 2;
                            }
                            return[rx, ry];
                        }
                    }
                };
                var xml = readXml(Text["Xmlmde"][Index], "CTimeStream");
                var length = readFloat(xml, "mLength") * 1000;
                var songName = readString(xml, "mSongName");
                var delay = readFloat(xml, "mStartDelay") * 1000;
                var bpm = readFloat(xml, "mBPM");
                var notes = readXml(xml, "mNotes");
                var canvasSize = (function () {
                    var s = readXml(xml, "mCanvasSize");
                    var x = readFloat(s, "x");
                    var y = readFloat(s, "y");
                    return[x, y];
                })();
                var camList = readXml(xml, "mCamList");
                var defaultCamSize = readFloat(xml, "mDefaultCamSize");
                notes = (function () {
                    var r = [];
                    p = 0;
                    while(true) {
                        var x = readXml(notes, "CNoteBase", false);
                        if(typeof x === "undefined") {
                            break;
                        }
                        r.push(x);
                    }
                    return r;
                })();
                camList = (function () {
                    var r = [];
                    p = 0;
                    while(true) {
                        var x = readXml(camList, "CNoteCamData", false);
                        if(typeof x === "undefined") {
                            break;
                        }
                        r.push(x);
                    }
                    return r;
                })();
                var noteData = (function () {
                    var r = [];
                    p = 0;
                    for(var i = 0; i < notes.length; i++) {
                        var type = notes[i].extract("xsi:type=" + '"', '"' + ">");
                        var index = readFloat(notes[i], "mIdx");
                        var tick = readFloat(notes[i], "mTick");
                        var pos = (function () {
                            var s = readXml(notes[i], "mPos");
                            var x = readFloat(s, "x");
                            var y = readFloat(s, "y");
                            return[x, y];
                        })();
                        var isCircleTail = readString(notes[i], "mIsCircleTail");
                        var isCircleClockwise = readString(notes[i], "mIsCircleClockwise");
                        var endTick = readFloat(notes[i], "mEndTick");
                        var points = (function () {
                            var r = [];
                            var j = readXml(notes[i], "mPoints");
                            if(typeof j === "undefined") {
                                return r;
                            }
                            var v = (function () {
                                var r = [];
                                p = 0;
                                while(true) {
                                    var s = readXml(j, "Vector2", false);
                                    if(typeof s === "undefined") {
                                        break;
                                    }
                                    var x = readFloat(s, "x");
                                    var y = readFloat(s, "y");
                                    r.push([x, y]);
                                }
                                return r;
                            })();
                            var n = (function () {
                                var r = 0;
                                var t = tick - delay;
                                var ts = 60000 / bpm / 4;
                                while((t < endTick - delay) || IsNumberClose(t, endTick - delay)) {
                                    r += 1;
                                    t += ts;
                                }
                                r = (r == 1 ? 2 : r);
                                return r;
                            })();
                            if((v.length == 2) && isCircleTail) {
                                var d = Math.sqrt(Math.pow(v[1][0] - v[0][0], 2) + Math.pow(v[1][1] - v[0][1], 2));
                                var b = Math.atan2(v[1][0] - v[0][0], v[1][1] - v[0][1]) / Math.PI * 180;
                                for(var j = 0; j < n; j++) {
                                    var c = Math.sin(j / (n - 1) * Math.PI) * d;
                                    var a = b - (90 - 180 * j / (n - 1));
                                    var x = c * Math.sin(a / 180 * Math.PI);
                                    var y = c * Math.cos(a / 180 * Math.PI);
                                    r.push([Math.round(v[0][0] + x), Math.round(v[0][1] + y)]);
                                }
                                r = (isCircleClockwise ? r : r.reverse());
                            } else {
                                for(var j = 0; j < n; j++) {
                                    for(var k = 0; k < v.length - 1; k++) {
                                        if((j / n >= k / v.length) && (j / n <= (k + 1) / v.length)) {
                                            var d = (j / n - k / v.length) * v.length;
                                            var x = v[k][0] + (v[k + 1][0] - v[k][0]) * d;
                                            var y = v[k][1] + (v[k + 1][1] - v[k][1]) * d;
                                            r.push([Math.round(x), Math.round(y)]);
                                            break;
                                        }
                                    }
                                }
                            }
                            return r;
                        })();
                        var slideDirection = readString(notes[i], "mSlideDirection");
                        r[index] = [type, tick - delay, pos, points, endTick - delay, slideDirection];
                    }
                    return r;
                })();
                var camData = (function () {
                    var r = [];
                    p = 0;
                    for(var i = 0; i < camList.length; i++) {
                        var index = readFloat(camList[i], "mIdx");
                        var tick = readFloat(camList[i], "mTick");
                        var pos = (function () {
                            var s = readXml(camList[i], "mPos");
                            var x = readFloat(s, "x");
                            var y = readFloat(s, "y");
                            return[x, y];
                        })();
                        var size = readFloat(camList[i], "mSize");
                        r[index] = [tick - delay, pos, size];
                    }
                    if((r.length > 0) && (r[0][0] != delay) || (r.length == 0)) {
                        r.unshift([delay, [canvasSize[0] / 2, canvasSize[1] / 2], defaultCamSize]);
                    }
                    return r;
                })();
                for(var i = 0; i < noteData.length; i++) {
                    if(typeof noteData[i] === "undefined") {
                        continue;
                    }
                    if(noteData[i][3].length != 0) {
                        for(var j = 0; j < noteData[i][3].length; j++) {
                            noteData[i][3][j] = transPos(noteData[i][1], noteData[i][3][j]);
                        }
                    }
                }
                var beatData = (function () {
                    var r = [];
                    var t = 0;
                    var u = 60000 / bpm;
                    while(t < length) {
                        r.push([t, bpm]);
                        t += u;
                    }
                    return r;
                })();
                var actionData = (function () {
                    var r = [];
                    for(var i = 0; i < noteData.length; i++) {
                        if(typeof noteData[i] === "undefined") {
                            continue;
                        }
                        noteData[i][2] = transPos(noteData[i][1], noteData[i][2]);
                        switch(noteData[i][0]) {
                            case"CNoteSingle":
                                r.push([noteData[i][1], Math.floor(noteData[i][2][0] * key), 0x00, 0]);
                                break;
                            case"CNoteSlide":
                                var track = Math.floor(noteData[i][2][0] * key);
                                var timespan = 0;
                                switch(noteData[i][5]) {
                                    case"Left":
                                    case"UpLeft":
                                    case"DownLeft":
                                        timespan = -1;
                                        break;
                                    case"Right":
                                    case"UpRight":
                                    case"DownRight":
                                        timespan = 1;
                                        break;
                                    case"Up":
                                    case"Down":
                                        timespan = (noteData[i][2][0] < 0.5 ? -1 : 1);
                                        break;
                                }
                                if(track + timespan < 0) {
                                    track = 0 - timespan;
                                }
                                if(track + timespan > key - 1) {
                                    track = key - 1 - timespan;
                                }
                                r.push([noteData[i][1], track, 0x01, timespan]);
                                break;
                            case"CNoteLong":
                                r.push([noteData[i][1], Math.floor(noteData[i][2][0] * key), 0x02, noteData[i][4] - noteData[i][1]]);
                                break;
                            case"CNoteLongSlide":
                                var s = [];
                                var u = (noteData[i][4] - noteData[i][1]) / (noteData[i][3].length - 1);
                                var b = false;
                                if(noteData[i][3].length == 0) {
                                    s.push([noteData[i][1], Math.floor(noteData[i][2][0] * key), 0x02, noteData[i][4] - noteData[i][1]]);
                                } else {
                                    var t1 = Math.floor(noteData[i][2][0] * key);
                                    var t2 = Math.floor(noteData[i][3][0][0] * key);
                                    if(t1 == t2) {
                                        s.push([noteData[i][1], t1, 0x62, 0]);
                                    } else {
                                        s.push([noteData[i][1], t1, 0x61, t2 - t1]);
                                        s.push([noteData[i][1], t2, 0x22, 0]);
                                    }
                                    for(var j = 1; j < noteData[i][3].length; j++) {
                                        switch(s[s.length - 1][2]) {
                                            case 0x61:
                                            case 0x21:
                                                s.push([s[s.length - 1][0], s[s.length - 1][1] + s[s.length - 1][3], 0x22, u]);
                                                break;
                                            case 0x62:
                                            case 0x22:
                                                s[s.length - 1][3] += u;
                                                break;
                                        }
                                        var timespan = Math.floor(noteData[i][3][j][0] * key) - Math.floor(noteData[i][3][j - 1][0] * key);
                                        if(timespan != 0) {
                                            s.push([noteData[i][1] + j * u, s[s.length - 1][1], 0x21, timespan]);
                                            b = true;
                                        }
                                    }
                                }
                                if(s.length == 1) {
                                    s[s.length - 1][2] -= 0x60;
                                } else {
                                    if(!b) {
                                        var timespan = (function () {
                                            var r = 0;
                                            var x = noteData[i][3][noteData[i][3].length - 1][0] - noteData[i][3][noteData[i][3].length - 2][0];
                                            var y = noteData[i][3][noteData[i][3].length - 1][1] - noteData[i][3][noteData[i][3].length - 2][1];
                                            if(x > 0) {
                                                r = 1;
                                            } else if(x < 0) {
                                                r = -1;
                                            } else {
                                                if(y >= 0) {
                                                    r = 1;
                                                } else if(y < 0) {
                                                    r = -1;
                                                }
                                            }
                                            return r;
                                        })();
                                        s.push([noteData[i][4] - noteData[i][1], s[s.length - 1][1] + timespan, 0xA1, timespan]);
                                    }
                                    switch(s[s.length - 1][2]) {
                                        case 0x61:
                                        case 0x21:
                                            s[s.length - 1][2] = 0xA1;
                                            break;
                                        case 0x62:
                                        case 0x22:
                                            s[s.length - 1][2] = 0xA2;
                                            break;
                                    }
                                    var offset = (function () {
                                        var r = (function () {
                                            for(var j = 0; j < s.length; j++) {
                                                var t = 0;
                                                switch(s[j][2]) {
                                                    case 0x61:
                                                    case 0x21:
                                                    case 0xA1:
                                                        t = s[j][1] + s[j][3];
                                                        break;
                                                    case 0x62:
                                                    case 0x22:
                                                    case 0xA2:
                                                        t = s[j][1];
                                                        break;
                                                }
                                                if(t < 0) {
                                                    return 0 - t;
                                                }
                                                if(t > key - 1) {
                                                    return key - 1 - t;
                                                }
                                            }
                                            return 0;
                                        })();
                                        if(r != 0) {
                                            for(var j = 0; j < s.length; j++) {
                                                var t = 0;
                                                switch(s[j][2]) {
                                                    case 0x61:
                                                    case 0x21:
                                                    case 0xA1:
                                                        t = s[j][1] + s[j][3];
                                                        break;
                                                    case 0x62:
                                                    case 0x22:
                                                    case 0xA2:
                                                        t = s[j][1];
                                                        break;
                                                }
                                                if(t + r < 0) {
                                                    return 0;
                                                }
                                                if(t + r > key - 1) {
                                                    return 0;
                                                }
                                            }
                                        }
                                        return r;
                                    })();
                                    if(offset != 0) {
                                        for(var j = 0; j < s.length; j++) {
                                            s[j][1] += offset;
                                        }
                                    }
                                }
                                r.write(s);
                                break;
                        }
                    }
                    for(var i = 0; i < r.length; i++) {
                        r[i][0] = Math.round(r[i][0]);
                        r[i][3] = Math.round(r[i][3]);
                    }
                    return r;
                })();
                beatData = enlargeBeatData(offsetBeatData(beatData, delay), actionData);
                meta["AudioFilename"] = songName;
                fromData(meta, [
                    {"ActionData": actionData.sort(SortNumbers), "BeatData": beatData, "Length": length}
                ]);
                break;
        }
    };
    var fromVoxBuffer = function (buffer) {
        var format = 9;
        var beatInfo = [];
        var bpmInfo = [];
        var tiltInfo = [];
        var lyricInfo = [];
        var endInfo = [];
        var btEffect = [];
        var fxEffect = [];
        var volEffect = [];
        var tabParamAssign = [];
        var trackInfo = [];
        var Spcontroler = [];
        var a = buffer.getText().replace(/\r/g, "\n").replace(/\n\n/g, "\n").split("\n");
        var addInfo = function (f, d0, d1, d2, v) {
            var l = (function () {
                for(var i = 0; i < f.length; i++) {
                    if((f[i][0] == d0) && (f[i][1] == d1) && (f[i][2] == d2)) {
                        return i;
                    }
                }
                return-1;
            })();
            if(l != -1) {
                f[l][1] = v;
            } else {
                f.push([d0, d1, d2, v]);
            }
            return f;
        };
        var getEffect = function (i, e) {
            var r = [];
            for(var j = i + 1; j < e; j++) {
                var d = a[j].split(",\t");
                var b = true;
                var v = [];
                for(var k = 0; k < d.length; k++) {
                    d[k] = (k == 0 ? parseInt(d[k]) : parseFloat(d[k]));
                    if(!isNaN(d[k])) {
                        v.push(d[k]);
                    } else {
                        b = false;
                        break;
                    }
                }
                if(b) {
                    r.push(v);
                }
            }
            return r;
        };
        var getBtInfo = function (i, e) {
            var r = [];
            for(var j = i + 1; j < e; j++) {
                var d = a[j].split("\t");
                if(d.length > 2) {
                    var b = d[0].split(",");
                    b[0] = parseInt(b[0]);
                    b[1] = parseInt(b[1]);
                    b[2] = parseInt(b[2]);
                    d[1] = parseInt(d[1]);
                    d[2] = parseInt(d[2]);
                    if((!isNaN(b[0])) && (!isNaN(b[1])) && (!isNaN(b[2])) && (!isNaN(d[2]))) {
                        r.push([b[0] - 1, b[1] - 1, b[2], d[1], d[2]]);
                    }
                }
            }
            return r;
        };
        var getFxInfo = function (i, e) {
            return getBtInfo(i, e);
        };
        var getVolInfo = function (i, e) {
            var r = [];
            for(var j = i + 1; j < e; j++) {
                var d = a[j].split("\t");
                if(d.length > 4) {
                    var b = d[0].split(",");
                    b[0] = parseInt(b[0]);
                    b[1] = parseInt(b[1]);
                    b[2] = parseInt(b[2]);
                    d[1] = parseInt(d[1]);
                    d[2] = parseInt(d[2]);
                    d[3] = parseInt(d[3]);
                    d[4] = parseInt(d[4]);
                    if((!isNaN(b[0])) && (!isNaN(b[1])) && (!isNaN(b[2])) && (!isNaN(d[2])) && (!isNaN(d[3])) && (!isNaN(d[4]))) {
                        r.push([b[0] - 1, b[1] - 1, b[2], d[1], d[2], d[3], d[4], d[5]]);
                    }
                }
            }
            return r;
        };
        for(var i = 0; i < a.length; i++) {
            var ln = a[i].indexOf("//");
            if(ln != -1) {
                a[i] = a[i].substring(0, ln);
            }
            if(a[i] == "") {
                continue;
            }
            if(a[i][0] == "#") {
                var e = (function () {
                    for(var j = i + 1; j < a.length; j++) {
                        if(a[j] == "#END") {
                            return j;
                        }
                    }
                    return-1;
                })();
                if(e > i) {
                    switch(a[i].substring(1)) {
                        case"FORMAT VERSION":
                            format = parseInt(a[e - 1]) || format;
                            break;
                        case"BEAT INFO":
                            for(var j = i + 1; j < e; j++) {
                                var d = a[j].split("\t");
                                if(d.length > 2) {
                                    var b = d[0].split(",");
                                    b[0] = parseInt(b[0]);
                                    b[1] = parseInt(b[1]);
                                    b[2] = parseInt(b[2]);
                                    d[1] = parseInt(d[1]);
                                    d[2] = parseInt(d[2]);
                                    if((!isNaN(b[0])) && (!isNaN(b[1])) && (!isNaN(b[2])) && (!isNaN(d[1])) && (!isNaN(d[2])) && (d[2] != 0)) {
                                        beatInfo = addInfo(beatInfo, b[0] - 1, b[1] - 1, b[2], d[1] / d[2]);
                                    }
                                }
                            }
                            break;
                        case"BPM INFO":
                            for(var j = i + 1; j < e; j++) {
                                var d = a[j].split("\t");
                                if(d.length > 2) {
                                    var b = d[0].split(",");
                                    b[0] = parseInt(b[0]);
                                    b[1] = parseInt(b[1]);
                                    b[2] = parseInt(b[2]);
                                    d[1] = parseFloat(d[1]);
                                    d[2] = parseInt(d[2]);
                                    if((!isNaN(b[0])) && (!isNaN(b[1])) && (!isNaN(b[2])) && (!isNaN(d[1])) && (d[1] != 0) && (!isNaN(d[2])) && (d[2] != 0)) {
                                        bpmInfo = addInfo(bpmInfo, b[0] - 1, b[1] - 1, b[2], d[1] * d[2] / 4);
                                    }
                                }
                            }
                            break;
                        case"TILT MODE INFO":
                            for(var j = i + 1; j < e; j++) {
                                var d = a[j].split("\t");
                                if(d.length > 1) {
                                    var b = d[0].split(",");
                                    b[0] = parseInt(b[0]);
                                    b[1] = parseInt(b[1]);
                                    b[2] = parseInt(b[2]);
                                    d[1] = parseInt(d[1]);
                                    if((!isNaN(b[0])) && (!isNaN(b[1])) && (!isNaN(b[2])) && (!isNaN(d[1]))) {
                                        tiltInfo = addInfo(tiltInfo, b[0] - 1, b[1] - 1, b[2], d[1]);
                                    }
                                }
                            }
                            break;
                        case"LYRIC INFO":
                            for(var j = i + 1; j < e; j++) {
                                var d = a[j].split("\t");
                                if(d.length > 1) {
                                    var b = d[0].split(",");
                                    b[0] = parseInt(b[0]);
                                    b[1] = parseInt(b[1]);
                                    b[2] = parseInt(b[2]);
                                    if((!isNaN(b[0])) && (!isNaN(b[1])) && (!isNaN(b[2]))) {
                                        lyricInfo = addInfo(lyricInfo, b[0] - 1, b[1] - 1, b[2], d[1]);
                                    }
                                }
                            }
                            break;
                        case"END POSITION":
                            for(var j = i + 1; j < e; j++) {
                                var d = a[j].split("\t");
                                if(d.length > 0) {
                                    var b = d[0].split(",");
                                    b[0] = parseInt(b[0]);
                                    b[1] = parseInt(b[1]);
                                    b[2] = parseInt(b[2]);
                                    if((!isNaN(b[0])) && (!isNaN(b[1])) && (!isNaN(b[2]))) {
                                        endInfo.push([b[0] - 1, b[1] - 1, b[2]]);
                                    }
                                }
                            }
                            break;
                        case"TAB EFFECT INFO":
                            btEffect = getEffect(i, e);
                            break;
                        case"FXBUTTON EFFECT INFO":
                            fxEffect = getEffect(i, e);
                            break;
                        case"TAB PARAM ASSIGN INFO":
                            tabParamAssign = getEffect(i, e);
                            break;
                        case"REVERB EFFECT PARAM":
                            volEffect = getEffect(i, e);
                            break;
                        case"TRACK1":
                            trackInfo[6] = getVolInfo(i, e);
                            break;
                        case"TRACK2":
                            trackInfo[4] = getFxInfo(i, e);
                            break;
                        case"TRACK3":
                            trackInfo[0] = getBtInfo(i, e);
                            break;
                        case"TRACK4":
                            trackInfo[1] = getBtInfo(i, e);
                            break;
                        case"TRACK5":
                            trackInfo[2] = getBtInfo(i, e);
                            break;
                        case"TRACK6":
                            trackInfo[3] = getBtInfo(i, e);
                            break;
                        case"TRACK7":
                            trackInfo[5] = getFxInfo(i, e);
                            break;
                        case"TRACK8":
                            trackInfo[7] = getVolInfo(i, e);
                            break;
                        case"TRACK AUTO TAB":
                            break;
                        case"SPCONTROLER":
                            for(var j = i + 1; j < e; j++) {
                                var d = a[j].split("\t");
                                if(d.length > 7) {
                                    var b = d[0].split(",");
                                    b[0] = parseInt(b[0]);
                                    b[1] = parseInt(b[1]);
                                    b[2] = parseInt(b[2]);
                                    d[2] = parseInt(d[2]);
                                    d[3] = parseInt(d[3]);
                                    d[4] = parseFloat(d[4]);
                                    d[5] = parseFloat(d[5]);
                                    d[6] = parseFloat(d[6]);
                                    d[7] = parseFloat(d[7]);
                                    if((!isNaN(b[0])) && (!isNaN(b[1])) && (!isNaN(b[2])) && (!isNaN(d[2])) && (!isNaN(d[3])) && (!isNaN(d[4])) && (!isNaN(d[5])) && (!isNaN(d[6])) && (!isNaN(d[7]))) {
                                        Spcontroler.push([b[0] - 1, b[1] - 1, b[2], d[1], d[2], d[3], d[4], d[5], d[6], d[7]]);
                                    }
                                }
                            }
                            break;
                    }
                    i = e;
                }
            }
        }
        var times = (function () {
            var r = [];
            var time = 0;
            var beat = 1;
            var bpm = 125;
            var n1 = 0;
            var n2 = 0;
            if(beatInfo.length == 0) {
                beatInfo.push([0, 0, 0, 1]);
            }
            beatInfo = beatInfo.sort(SortNumbers);
            if(bpmInfo.length == 0) {
                bpmInfo.push([0, 0, 0, 125]);
            }
            bpmInfo = bpmInfo.sort(SortNumbers);
            var end = (function () {
                var v = 0;
                for(var i = 0; i < endInfo.length; i++) {
                    if(endInfo[i][0] > v) {
                        v = endInfo[i][0];
                        if((endInfo[i][1] != 0) || (endInfo[i][2] != 0)) {
                            v += 1;
                        }
                    }
                }
                return v;
            })();
            for(var i = 0; i < end; i++) {
                var l = 4 * beat;
                for(var j = 0; j < l; j++) {
                    for(var k = 0; k < 48; k++) {
                        if((n1 < beatInfo.length) && (beatInfo[n1][0] == i) && (beatInfo[n1][1] == j) && (beatInfo[n1][2] == k)) {
                            beat = beatInfo[n1][3];
                            l = 4 * beat;
                            n1 += 1;
                        }
                        if((n2 < bpmInfo.length) && (bpmInfo[n2][0] == i) && (bpmInfo[n2][1] == j) && (bpmInfo[n2][2] == k)) {
                            bpm = bpmInfo[n2][3];
                            n2 += 1;
                        }
                        if(j >= l) {
                            break;
                        }
                        r.push([i, j, k, time]);
                        time += 60000 / bpm * 4 * beat / l / 48;
                    }
                }
            }
            r.push([end, 0, 0, time]);
            return r;
        })();
        var getIndex = function (d0, d1, d2) {
            for(var i = 0; i < times.length; i++) {
                if((times[i][0] == d0) && (times[i][1] == d1) && (times[i][2] == d2)) {
                    return i;
                }
            }
            return-1;
        };
        var actionData = (function () {
            var r = [];
            var rv = [];
            var key = GetSelectValue("selectTransKey");
            var ln = [-1, -1];
            var vx = [-1, -1];
            var s = false;
            for(var i = 0; i < trackInfo.length; i++) {
                for(var j = 0; j < trackInfo[i].length; j++) {
                    var i0 = getIndex(trackInfo[i][j][0], trackInfo[i][j][1], trackInfo[i][j][2]);
                    var timestamps = Math.round(times[i0][3]);
                    if(i < 6) {
                        var i1 = i0 + trackInfo[i][j][3];
                        var timespan = Math.round(times[i1][3] - timestamps);
                        var action = (timespan != 0 ? 0x02 : 0x00);
                        if(i < 4) {
                            r.push([timestamps, i, action, timespan]);
                        } else {
                            r.push([timestamps, (i - 4) * 2, action, timespan]);
                            r.push([timestamps, (i - 4) * 2 + 1, action, timespan]);
                        }
                    } else {
                        var track = Math.floor(trackInfo[i][j][3] / 128 * key);
                        switch(trackInfo[i][j][4]) {
                            case 0:
                            case 2:
                                if((ln[i - 6] != -1) && (vx[i - 6] != -1)) {
                                    var getAction = function (n, s, e) {
                                        var v = 0x20 + n;
                                        if(s && !e) {
                                            v = 0x60 + n;
                                        } else if(!s && e) {
                                            v = 0xA0 + n;
                                        } else if(s && e) {
                                            v = 0x00 + n;
                                        }
                                        return v;
                                    };
                                    if(track == vx[i - 6]) {
                                        rv.push([ln[i - 6], vx[i - 6], getAction(2, s, trackInfo[i][j][4] == 2), timestamps - ln[i - 6]]);
                                    } else if(IsNumberClose(timestamps, ln[i - 6])) {
                                        var action = getAction(1, s, trackInfo[i][j][4] == 2);
                                        if(IsNumberClose(rv[rv.length - 1][0], ln[i - 6]) && (rv[rv.length - 1][1] == vx[i - 6]) && ((rv[rv.length - 1][2] == 0x21) || (rv[rv.length - 1][2] == 0xA1))) {
                                            rv.pop();
                                        }
                                        rv.push([ln[i - 6], vx[i - 6], action, track - vx[i - 6]]);
                                    } else {
                                        rv.push([ln[i - 6], vx[i - 6], getAction(2, s, false), timestamps - ln[i - 6]]);
                                        rv.push([timestamps, track, getAction(1, false, trackInfo[i][j][4] == 2), 0]);
                                    }
                                    ln[i - 6] = (trackInfo[i][j][4] == 0 ? timestamps : ln[i - 6]);
                                    vx[i - 6] = (trackInfo[i][j][4] == 0 ? track : vx[i - 6]);
                                    ln[i - 6] = (trackInfo[i][j][4] == 2 ? -1 : ln[i - 6]);
                                    vx[i - 6] = (trackInfo[i][j][4] == 2 ? -1 : vx[i - 6]);
                                    s = false;
                                }
                                break;
                            case 1:
                                ln[i - 6] = timestamps;
                                vx[i - 6] = track;
                                s = true;
                                break;
                        }
                    }
                }
            }
            r = toActionDataTransTrack(r, key);
            r.write(rv);
            return r;
        })();
        var beatData = (function () {
            var r = [];
            var t = [];
            for(var i = 0; i < times.length; i++) {
                if(typeof t[times[i][0]] === "undefined") {
                    t[times[i][0]] = [];
                }
                if(typeof t[times[i][0]][times[i][1]] === "undefined") {
                    t[times[i][0]][times[i][1]] = 0;
                }
                if(times[i][2] == 0) {
                    t[times[i][0]][times[i][1]] = times[i][3];
                }
            }
            for(var i = 0; i < t.length; i++) {
                for(var j = 0; j < t[i].length; j++) {
                    r.push([t[i][j]]);
                }
            }
            if(r.length > 1) {
                for(var i = 1; i < r.length; i++) {
                    r[i - 1][1] = 60000 / (r[i][0] - r[i - 1][0]);
                }
                r[r.length - 1][1] = r[r.length - 2][1];
            }
            return r;
        })();
        beatData = enlargeBeatData(offsetBeatData(beatData, 0), actionData);
        fromData({}, [
            {"ActionData": sortActionData(actionData), "BeatData": beatData}
        ]);
    };
    var fromKshBuffer = function (buffer) {
        var offset = 0;
        var bpm = 125;
        var beat = 1;
        var tilt = "normal";
        var meta = {};
        var a = buffer.getText().replace(/\r/g, "\n").replace(/\n\n/g, "\n").split("\n");
        var d = [];
        var n = -1;
        for(var i = 0; i < a.length; i++) {
            a[i] = a[i].trim();
            var m = a[i].split("=");
            if(m.length > 1) {
                m[0] = m[0].trim();
                m[1] = m[1].trim();
                if(n != -1) {
                    switch(m[0]) {
                        case"t":
                            bpm = m[1] || bpm;
                            break;
                        case"beat":
                            var v = m[1].split("/");
                            if(v.length == 2) {
                                var d1 = parseFloat(v[0]);
                                var d2 = parseFloat(v[1]);
                                if((!isNaN(d1)) && (!isNaN(d2)) && (d2 != 0)) {
                                    beat = d1 / d2;
                                }
                            }
                            break;
                        case"tilt":
                            tilt = m[1];
                            break;
                    }
                } else {
                    switch(m[0]) {
                        case"title":
                            meta["Title"] = m[1];
                            break;
                        case"artist":
                            meta["Artist"] = m[1];
                            break;
                        case"effect":
                            meta["Effect"] = m[1];
                            break;
                        case"jacket":
                            meta["Jacket"] = m[1];
                            break;
                        case"illustrator":
                            meta["Illustrator"] = m[1];
                            break;
                        case"difficulty":
                            meta["DifficultyKsh"] = m[1];
                            break;
                        case"level":
                            meta["Level"] = parseFloat(m[1]) || 1;
                            break;
                        case"t":
                            bpm = parseFloat(m[1]) || bpm;
                            meta["BPM"] = bpm;
                            break;
                        case"m":
                            meta["AudioFilenameKsh"] = m[1];
                            var l = m[1].split(";")
                            if(l.length > 0) {
                                meta["AudioFilename"] = l[0];
                            }
                            break;
                        case"mvol":
                            meta["SongVol"] = parseFloat(m[1]) || 100;
                            break;
                        case"o":
                            offset = -(parseFloat(m[1]) || 0);
                            meta["Offset"] = offset;
                            break;
                        case"bg":
                            meta["Background"] = m[1];
                            break;
                        case"layer":
                            meta["Layer"] = m[1];
                            break;
                        case"po":
                            meta["PreviewTime"] = parseFloat(m[1]) || 0;
                            break;
                        case"plength":
                            meta["PreviewLength"] = parseFloat(m[1]) || 0;
                            break;
                        case"pfiltergain":
                            break;
                        case"filtertype":
                            break;
                        case"chokkakuvol":
                            meta["SeVol"] = parseFloat(m[1]) || 0;
                            break;
                        case"icon":
                            meta["Icon"] = m[1];
                            break;
                        case"ver":
                            meta["Version"] = m[1];
                        case"maker":
                            meta["Maker"] = m[1];
                        case"datetime":
                            meta["DateTime"] = m[1];
                            break;
                    }
                }
            } else {
                if(a[i] == "--") {
                    n += 1;
                    if(typeof d[n] === "undefined") {
                        d[n] = [];
                    }
                } else {
                    var m = a[i].split("|");
                    var bt = m[0] || "0000";
                    var fx = m[1] || "00";
                    var vol = m[2] || "--";
                    d[n].push([bt[0] || "0", bt[1] || "0", bt[2] || "0", bt[3] || "0", fx[0] || "0", fx[1] || "0", vol[0] || "-", vol[1] || "-", bpm, beat, tilt]);
                }
            }
        }
        var beatData = [];
        var actionData = [];
        var actionDataV = [];
        var toVolX = function (t) {
            var m = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmno";
            if((typeof t !== "undefined") && (t.length > 0)) {
                return m.indexOf(t[0]) / 50 * 127;
            }
            return-1;
        };
        var toFxE = function (t) {
            var m = "11STIFUHQADXVB11111";
            if((typeof t !== "undefined") && (t.length > 0)) {
                if(t[0] == "2") {
                    return 255;
                } else {
                    return m.indexOf(t[0]);
                }
            }
            return-1;
        };
        var key = GetSelectValue("selectTransKey");
        var time = 0;
        var ln = [-1, -1, -1, -1, -1, -1, -1, -1];
        var lb = [-1, -1, -1, -1, -1, -1, -1, -1];
        var vx = [-1, -1];
        for(var i = 0; i < d.length; i++) {
            for(var j = 0; j < d[i].length; j++) {
                d[i][j][11] = 60000 * 4 / d[i][j][8] * d[i][j][9];
            }
            for(var j = 0; j < d[i].length; j++) {
                d[i][j][11] /= d[i].length;
                for(var k = 0; k < 4; k++) {
                    switch(d[i][j][k]) {
                        case"0":
                            if(ln[k] != -1) {
                                actionData.push([ln[k], k, 0x02, time - ln[k]]);
                                ln[k] = -1;
                            }
                            break;
                        case"1":
                            actionData.push([time, k, 0x00, 0]);
                            break;
                        case"2":
                            if(ln[k] == -1) {
                                ln[k] = time;
                            }
                            break;
                    }
                }
                for(var k = 4; k < 6; k++) {
                    switch(d[i][j][k]) {
                        case"0":
                            if(ln[k] != -1) {
                                actionData.push([ln[k], (k - 4) * 2, 0x02, time - ln[k]]);
                                actionData.push([ln[k], (k - 4) * 2 + 1, 0x02, time - ln[k]]);
                                ln[k] = -1;
                            }
                            break;
                        case"2":
                            actionData.push([time, (k - 4) * 2, 0x00, 0]);
                            actionData.push([time, (k - 4) * 2 + 1, 0x00, 0]);
                            break;
                        default:
                            if(ln[k] == -1) {
                                ln[k] = time;
                            }
                            break;
                    }
                }
                for(var k = 6; k < 8; k++) {
                    switch(d[i][j][k]) {
                        case"-":
                            if(ln[k] != -1) {
                                ln[k] = -1;
                                lb[k] = -1;
                                vx[k - 6] = -1;
                            }
                            break;
                        case":":
                            break;
                        default:
                            var x = toVolX(d[i][j][k]);
                            if(x >= 0) {
                                if((ln[k] != -1) && (lb[k] != -1) && (vx[k - 6] >= 0)) {
                                    var t0 = Math.floor(vx[k - 6] / 128 * key);
                                    var t1 = Math.floor(x / 128 * key);
                                    if(i + j / d[i].length - lb[k] < 0.25 / 4) {
                                        actionDataV.push([ln[k], t0, 0x01, t1 - t0]);
                                    } else {
                                        if(t0 != t1) {
                                            actionDataV.push([ln[k], t0, 0x62, time - ln[k]]);
                                            actionDataV.push([time, t1, 0xA1, 0]);
                                        } else {
                                            actionDataV.push([ln[k], t0, 0x02, time - ln[k]]);
                                        }
                                        ln[k] = time;
                                        lb[k] = i + j / d[i].length;
                                    }
                                    vx[k - 6] = x;
                                } else {
                                    ln[k] = time;
                                    lb[k] = i + j / d[i].length;
                                    vx[k - 6] = x;
                                }
                            }
                            break;
                    }
                }
                time += d[i][j][11];
            }
            var s = (beatData.length == 0 ? 0 : beatData[beatData.length - 1][0]);
            var l = (time - s) / 4;
            for(var j = 0; j < 4; j++) {
                beatData.push([s + l * (j + 1), 60000 / l]);
            }
        }
        for(var i = 0; i < actionDataV.length; i++) {
            switch(actionDataV[i][2]) {
                case 0x02:
                    for(var j = 0; j < actionDataV.length; j++) {
                        if((actionDataV[j][2] == 0x02) || (actionDataV[j][2] == 0x62)) {
                            if(IsNumberClose(actionDataV[j][0], actionDataV[i][0] + actionDataV[i][3]) && (actionDataV[j][1] == actionDataV[i][1])) {
                                actionDataV[j][3] = actionDataV[j][0] + actionDataV[j][3] - actionDataV[i][0];
                                actionDataV[j][0] = actionDataV[i][0];
                                actionDataV.splice(i, 1);
                                i -= 1;
                                break;
                            }
                        } else if(actionDataV[j][2] == 0x01) {
                            if(IsNumberClose(actionDataV[j][0], actionDataV[i][0] + actionDataV[i][3]) && (actionDataV[j][1] == actionDataV[i][1])) {
                                actionDataV[i][2] = 0x62;
                                actionDataV[j][2] = 0xA1;
                                break;
                            }
                        }
                    }
                    break;
                case 0x01:
                case 0xA1:
                    for(var j = 0; j < actionDataV.length; j++) {
                        if((actionDataV[j][2] == 0x02) || (actionDataV[j][2] == 0x62)) {
                            if(IsNumberClose(actionDataV[j][0], actionDataV[i][0]) && (actionDataV[j][1] == actionDataV[i][1] + actionDataV[i][3])) {
                                actionDataV[i][2] = (actionDataV[i][2] == 0x01 ? 0x61 : actionDataV[i][2]);
                                actionDataV[i][2] = (actionDataV[i][2] == 0xA1 ? 0x21 : actionDataV[i][2]);
                                actionDataV[j][2] = (actionDataV[j][2] == 0x02 ? 0xA2 : actionDataV[j][2]);
                                actionDataV[j][2] = (actionDataV[j][2] == 0x62 ? 0x22 : actionDataV[j][2]);
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        actionData = toActionDataTransTrack(actionData, key);
        actionData.write(actionDataV);
        for(var i = 0; i < actionData.length; i++) {
            actionData[i][0] -= offset;
        }
        beatData = enlargeBeatData(offsetBeatData(beatData, offset), actionData);
        fromData(meta, [
            {"ActionData": sortActionData(actionData), "BeatData": beatData}
        ]);
    };
    var fromImdBuffer = function (buffer) {
        var data = [];
        for(var index = 0; index < buffer.length; index++) {
            if(buffer[index].length == 0) {
                return;
            }
            data[index] = [];
            var p = 0;
            data[index]["Length"] = buffer[index].getInt32(p, true);
            var readBeat = (function () {
                p += 4;
                var beat = buffer[index].getInt32(p, true);
                p += 4;
                data[index]["BeatData"] = [];
                for(var i = 0; i < beat; i++) {
                    data[index]["BeatData"][i] = [];
                    data[index]["BeatData"][i][0] = buffer[index].getInt32(p, true);
                    p += 4;
                    data[index]["BeatData"][i][1] = buffer[index].getFloat64(p, true);
                    p += 8;
                }
                if(data[index]["BeatData"].length == 0) {
                    return;
                }
            })();
            var readAction = (function () {
                p += 2;
                var action = buffer[index].getInt32(p, true);
                p += 4;
                data[index]["ActionData"] = [];
                for(var i = 0; i < action; i++) {
                    data[index]["ActionData"][i] = [];
                    data[index]["ActionData"][i][2] = buffer[index].getInt16(p, true);
                    p += 2;
                    data[index]["ActionData"][i][0] = buffer[index].getInt32(p, true);
                    p += 4;
                    data[index]["ActionData"][i][1] = buffer[index].getInt8(p, true);
                    p += 1;
                    data[index]["ActionData"][i][3] = buffer[index].getInt32(p, true);
                    p += 4;
                }
            })();
        }
        fromData({}, data);
    };
    var fromMdeBuffer = function (buffer) {
        Buffer["Mde"][Index] = buffer;
        Buffer["Xmlmde"][Index] = toXmlBuffer(Index, "mde");
        fromXmlBuffer(Buffer["Xmlmde"][Index], "mde")
    };
    var fromMcBuffer = function (buffer) {
        var mc = new Function("return " + buffer.getText())();
        var getBeat = function (t) {
            if((typeof t !== "undefined") && (t.length == 3)) {
                var b = t[0];
                var c = t[1];
                var d = t[2];
                if((typeof b === "number") && (typeof c === "number") && (typeof d === "number")) {
                    return b + c / d;
                }
            }
        };
        var meta = (function () {
            var r = {};
            if(typeof mc["meta"] !== "undefined") {
                r["Cover"] = mc["meta"]["cover"];
                r["Background"] = mc["meta"]["background"];
                r["Version"] = mc["meta"]["version"];
                r["Creator"] = mc["meta"]["creator"];
                r["Maker"] = mc["meta"]["maker"];
                r["DateTime"] = mc["meta"]["time"];
                r["Id"] = mc["meta"]["id"];
                r["Mode"] = mc["meta"]["mode"];
                if(typeof mc["meta"]["song"] !== "undefined") {
                    r["SongTitle"] = mc["meta"]["song"]["title"];
                    r["SongArtist"] = mc["meta"]["song"]["artist"];
                    r["SongId"] = mc["meta"]["song"]["id"];
                    r["SongSource"] = mc["meta"]["song"]["source"];
                    r["Time"] = mc["meta"]["song"]["length"];
                    if(typeof mc["meta"]["song"]["org"] !== "undefined") {
                        r["SongOrgTitle"] = mc["meta"]["song"]["org"]["title"];
                        r["SongOrgArtist"] = mc["meta"]["song"]["org"]["artist"];
                        r["SongOrgSource"] = mc["meta"]["song"]["org"]["source"];
                    }
                }
                if(typeof mc["meta"]["mode_ext"] !== "undefined") {
                    r["Mode_extColumn"] = mc["meta"]["mode_ext"]["column"];
                    r["Mode_extSpeed"] = mc["meta"]["mode_ext"]["speed"];
                    r["Mode_extInterval"] = mc["meta"]["mode_ext"]["interval"];
                }
            }
            return r;
        })();
        var time = (function () {
            var r = [];
            if(typeof mc["time"] !== "undefined") {
                for(var i = 0; i < mc["time"].length; i++) {
                    var beat = getBeat(mc["time"][i]["beat"]);
                    var bpm = mc["time"][i]["bpm"];
                    var signature = mc["time"][i]["signature"];
                    if((typeof beat === "number") && (typeof bpm === "number")) {
                        r.push({"beat": beat, "bpm": bpm});
                    }
                }
            }
            if(r.length == 0) {
                r.push({"beat": 0, "bpm": 116});
            }
            r = r.sort(SortNumbers);
            for(var i = 1; i < r.length; i++) {
                if(r[i]["beat"] == r[i - 1]["beat"]) {
                    r.splice(i - 1, 1);
                    i -= 1;
                }
            }
            if(r[0]["beat"] != 0) {
                r.unshift({"beat": 0, "bpm": r[0]["bpm"]});
            }
            return r;
        })();
        var note = (function () {
            var r = [];
            if(typeof mc["note"] !== "undefined") {
                var m = ["beat", "endbeat", "offset", "column", "x", "index", "endindex", "type", "style", "sound", "vol", "isBgm"];
                for(var i = 0; i < mc["note"].length; i++) {
                    var beat = getBeat(mc["note"][i]["beat"]);
                    var endbeat = getBeat(mc["note"][i]["endbeat"]);
                    if(typeof beat === "number") {
                        var v = {"beat": beat, "endbeat": endbeat};
                        for(var j = 2; j < m.length; j++) {
                            v[m[j]] = mc["note"][i][m[j]];
                        }
                        r.push(v);
                    }
                }
            }
            return r.sort(SortNumbers);
        })();
        var extra = (function () {
            var r = {};
            if(typeof mc["extra"] !== "undefined") {
                if(typeof mc["extra"]["test"] !== "undefined") {
                    r["ExtraTestDivide"] = mc["extra"]["test"]["divide"];
                    r["ExtraTestSpeed"] = mc["extra"]["test"]["speed"];
                    r["ExtraTestSave"] = mc["extra"]["test"]["save"];
                    r["ExtraTestLock"] = mc["extra"]["test"]["lock"];
                }
            }
            return r;
        })();
        var beatData = (function () {
            var r = [];
            var e = (function () {
                var r = 0;
                for(var i = 0; i < note.length; i++) {
                    if(r < note[i]["beat"]) {
                        r = note[i]["beat"];
                    }
                    if(typeof note[i]["endbeat"] !== "undefined") {
                        if(r < note[i]["endbeat"]) {
                            r = note[i]["endbeat"];
                        }
                    }
                }
                return r;
            })();
            var b = 0;
            var t = 0;
            var n = 0;
            while(b < e) {
                var bt = b;
                while(true) {
                    if((n + 1 < time.length) && (b + 1 > time[n + 1]["beat"])) {
                        t += (time[n + 1]["beat"] - bt) * 60000 / time[n]["bpm"];
                        bt = time[n + 1]["beat"];
                        n += 1;
                    } else {
                        t += (b + 1 - bt) * 60000 / time[n]["bpm"];
                        break;
                    }
                }
                if(r.length == 0) {
                    r.push([0, 60000 / t]);
                }
                r.push([t, 60000 / (t - r[r.length - 1][0])]);
                b += 1;
            }
            if(r.length == 0) {
                r.push([0, 116]);
            }
            return r;
        })();
        var offset = (function () {
            var r = undefined;
            for(var i = 0; i < note.length; i++) {
                if((typeof r === "undefined") && (typeof note[i]["sound"] !== "undefined") && (note[i]["sound"] != "")) {
                    meta["AudioFilename"] = GetFileName(note[i]["sound"]);
                    r = note[i]["offset"];
                    if(typeof r === "undefined") {
                        r = 0;
                    }
                    r += fromBeatDataBeat(beatData, note[i]["beat"]);
                    break;
                }
            }
            return r;
        })();
        var actionData = (function () {
            var r = [];
            var key = GetSelectValue("selectTransKey");
            var tCatch = Math.ceil(key / 2);
            var nCatch = 0;
            var dLeft = true;
            var kLeft = true;
            var bLeft = true;
            for(var i = 0; i < note.length; i++) {
                var timestamps = fromBeatDataBeat(beatData, note[i]["beat"]);
                var timespans = fromBeatDataBeat(beatData, note[i]["endbeat"]) - timestamps;
                timestamps -= offset;
                switch(meta["Mode"]) {
                    case 0:
                        var track = note[i]["column"];
                        if(typeof track !== "undefined") {
                            if(track < meta["Mode_extColumn"]) {
                                if(timespans > 0) {
                                    r.push([timestamps, track, 0x02, timespans]);
                                } else {
                                    r.push([timestamps, track, 0x00, 0]);
                                }
                            }
                        }
                        break;
                    case 3:
                        var x = note[i]["x"];
                        if(typeof x !== "undefined") {
                            x = (x < 0 ? 0 : x);
                            x = (x > 511 ? 511 : x);
                            r.push([timestamps, Math.floor((x / 512) * key), 0x00, 0]);
                        } else {
                            var type = note[i]["type"];
                            if(typeof type !== "undefined") {
                                switch(type) {
                                    case 3:
                                        var track = (function () {
                                            if(((key % 2 != 0) && (tCatch == key - 1)) || ((key % 2 == 0) && (tCatch == 0))) {
                                                tCatch = Math.ceil(key / 2);
                                                nCatch = 0;
                                            }
                                            tCatch += (nCatch % 2 != 0 ? -nCatch : nCatch);
                                            nCatch += 1;
                                            return tCatch;
                                        })();
                                        if(timespans > 0) {
                                            r.push([timestamps, track, 0x02, timespans]);
                                        } else {
                                            r.push([timestamps, track, 0x00, 0]);
                                        }
                                        break;
                                }
                            }
                        }
                        break;
                    case 4:
                        var index = note[i]["index"];
                        if(typeof index !== "undefined") {
                            var endindex = note[i]["endindex"];
                            if((typeof endindex !== "undefined") && (timespans > 0)) {
                                if(timespans > 0) {
                                    var s = index % 4;
                                    var e = endindex % 4;
                                    if(s == e) {
                                        r.push([timestamps, s, 0x02, timespans]);
                                    } else if(s < e) {
                                        var t = timestamps;
                                        var u = timespans / (e - s);
                                        for(var j = s; j < e + 1; j++) {
                                            r.push([t, j, (j == s ? 0x62 : 0x22), u]);
                                            t += u;
                                            r.push([t, j, (j == e ? 0xA1 : 0x21), 1]);
                                        }
                                    } else {
                                        var t = timestamps;
                                        var u = timespans / (s - e);
                                        for(var j = s; j > e - 1; j--) {
                                            r.push([t, j, (j == s ? 0x62 : 0x22), u]);
                                            t += u;
                                            r.push([t, j, (j == e ? 0xA1 : 0x21), -1]);
                                        }
                                    }
                                } else {
                                    r.push([timestamps, s, 0x00, 0]);
                                }
                            } else {
                                var n = (function () {
                                    for(var j = 0; j < r.length; j++) {
                                        if(IsNumberClose(r[j][0], timestamps) && (r[j][1] == index % 4)) {
                                            return j;
                                            break;
                                        }
                                    }
                                    return-1;
                                })();
                                if(n != -1) {
                                    r[n][3] += Math.pow(2, Math.floor(index / 4));
                                } else {
                                    r.push([timestamps, index % 4, 0x00, Math.pow(2, Math.floor(index / 4))]);
                                }
                            }
                        }
                        break;
                    case 5:
                        var style = note[i]["style"];
                        if(typeof style !== "undefined") {
                            switch(style) {
                                case 0:
                                    r.push([timestamps, (dLeft ? 1 : 2), 0x00, 0]);
                                    dLeft = !dLeft;
                                    break;
                                case 1:
                                    r.push([timestamps, (kLeft ? 0 : 3), 0x00, 0]);
                                    kLeft = !kLeft;
                                    break;
                                case 2:
                                    r.push([timestamps, 1, 0x00, 0]);
                                    r.push([timestamps, 2, 0x00, 0]);
                                    break;
                                case 3:
                                    r.push([timestamps, 0, 0x00, 0]);
                                    r.push([timestamps, 3, 0x00, 0]);
                                    break;
                                case 4:
                                    if(timespans > 0) {
                                        r.push([timestamps, 1, 0x02, timespans]);
                                    } else {
                                        r.push([timestamps, 1, 0x00, 0]);
                                    }
                                    break;
                                case 5:
                                    if(timespans > 0) {
                                        r.push([timestamps, 2, 0x02, timespans]);
                                    } else {
                                        r.push([timestamps, 2, 0x00, 0]);
                                    }
                                    break;
                                case 6:
                                    if(timespans > 0) {
                                        r.push([timestamps, (bLeft ? 0 : 3), 0x02, timespans]);
                                    } else {
                                        r.push([timestamps, (bLeft ? 0 : 3), 0x00, 0]);
                                    }
                                    bLeft = !bLeft;
                                    break;
                            }
                        }
                        break;
                }
            }
            return r;
        })();
        beatData = enlargeBeatData(offsetBeatData(beatData, offset), actionData);
        fromData(meta, [
            {"ActionData": sortActionData(actionData), "BeatData": beatData}
        ]);
    };
    var fromAffBuffer = function (buffer) {
        var aff = buffer.getText().replace(/\r/g, "\n").replace(/\n\n/g, "\n").split("\n");
        var key = GetSelectValue("selectTransKey");
        var offset = 0;
        var timing = [];
        var tab = [];
        var hold = [];
        var arc = [];
        var n = 0;
        while(true) {
            var d = aff[n].split(":");
            if(d.length == 2) {
                switch(d[0]) {
                    case"AudioOffset":
                        offset = parseFloat(d[1]) || 0;
                        break;
                }
            } else {
                if(aff[n].trim() == "-") {
                    n += 1;
                    break;
                }
            }
            n += 1;
        }
        for(var i = n; i < aff.length; i++) {
            var l1 = aff[i].indexOf("(");
            if(l1 != -1) {
                var l2 = aff[i].indexOf(")", l1);
                if(l2 != -1) {
                    var l3 = aff[i].indexOf(";", l2);
                    if(l3 != -1) {
                        var d = aff[i].substring(l1 + "(".length, l2).split(",");
                        switch(aff[i].substring(0, l1).trim()) {
                            case"timing":
                                if(d.length > 2) {
                                    var time = parseFloat(d[0]);
                                    if(isNaN(time)) {
                                        continue;
                                    }
                                    var bpm = parseFloat(d[1]) || 0;
                                    if(isNaN(bpm) || (bpm == 0)) {
                                        continue;
                                    }
                                    var beat = parseFloat(d[2]);
                                    if(isNaN(beat)) {
                                        continue;
                                    }
                                    var l = (function () {
                                        for(var j = 0; j < timing.length; j++) {
                                            if(timing[j][0] == time) {
                                                return j;
                                            }
                                        }
                                        return-1;
                                    })();
                                    if(l != -1) {
                                        timing[l][1] = bpm;
                                    } else {
                                        timing.push([time, bpm]);
                                    }
                                }
                                break;
                            case"":
                                if(d.length > 1) {
                                    var time = parseFloat(d[0]);
                                    if(isNaN(time)) {
                                        continue;
                                    }
                                    var track = parseFloat(d[1]);
                                    if(isNaN(track)) {
                                        continue;
                                    }
                                    track = (track < 1 ? 1 : track);
                                    track = (track > 4 ? 4 : track);
                                    tab.push([time, track]);
                                }
                                break;
                            case"hold":
                                if(d.length > 2) {
                                    var time1 = parseFloat(d[0]);
                                    if(isNaN(time1)) {
                                        continue;
                                    }
                                    var time2 = parseFloat(d[1]);
                                    if(isNaN(time2)) {
                                        continue;
                                    }
                                    var track = parseFloat(d[2]);
                                    if(isNaN(track)) {
                                        continue;
                                    }
                                    if(time2 >= time1) {
                                        track = (track < 1 ? 1 : track);
                                        track = (track > 4 ? 4 : track);
                                        hold.push([time1, time2, track]);
                                    }
                                }
                                break;
                            case"arc":
                                if(d.length > 9) {
                                    var time1 = parseFloat(d[0]);
                                    if(isNaN(time1)) {
                                        continue;
                                    }
                                    var time2 = parseFloat(d[1]);
                                    if(isNaN(time2)) {
                                        continue;
                                    }
                                    var x1 = parseFloat(d[2]);
                                    if(isNaN(x1)) {
                                        continue;
                                    }
                                    var x2 = parseFloat(d[3]);
                                    if(isNaN(x2)) {
                                        continue;
                                    }
                                    var type = d[4].trim();
                                    var y1 = parseFloat(d[5]);
                                    if(isNaN(y1)) {
                                        continue;
                                    }
                                    var y2 = parseFloat(d[6]);
                                    if(isNaN(y2)) {
                                        continue;
                                    }
                                    var color = parseInt(d[7]);
                                    if(isNaN(color) || ((color != 0) && (color != 1))) {
                                        continue;
                                    }
                                    var adv = d[8].trim();
                                    var istab = d[9].trim();
                                    if((istab != "true") && ((istab != "false"))) {
                                        continue;
                                    }
                                    istab = (istab == "true" ? true : istab);
                                    istab = (istab == "false" ? false : istab);
                                    var points = (function () {
                                        var v = [];
                                        var l4 = aff[i].indexOf("[", l2);
                                        if(l4 != -1) {
                                            var l5 = aff[i].indexOf("]", l4);
                                            if(l5 != -1) {
                                                var l = aff[i].substring(l4 + "[".length, l5).split(",");
                                                for(var j = 0; j < l.length; j++) {
                                                    var la = l[j].indexOf("arctap(");
                                                    if(la != -1) {
                                                        var lb = l[j].indexOf(")", la);
                                                        if(lb != -1) {
                                                            var time = parseFloat(l[j].substring(la + "arctap(".length, lb));
                                                            if(isNaN(time)) {
                                                                continue;
                                                            }
                                                            v.push(time);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        return v;
                                    })();
                                }
                                if(time2 >= time1) {
                                    x1 = (x1 < -0.5 ? -0.5 : x1);
                                    x1 = (x1 > 1.5 ? 1.5 : x1);
                                    x2 = (x2 < -0.5 ? -0.5 : x2);
                                    x2 = (x2 > 1.5 ? 1.5 : x2);
                                    y1 = (y1 < 0 ? 0 : y1);
                                    y1 = (y1 > 1 ? 1 : y1);
                                    y2 = (y2 < 0 ? 0 : y2);
                                    y2 = (y2 > 1 ? 1 : y2);
                                    arc.push([time1, time2, x1, x2, type, y1, y2, color, adv, istab, points]);
                                }
                                break;
                        }
                    }
                }
            }
        }
        var actionData = (function () {
            var r = [];
            var getTab = function () {
                var v = [];
                for(var i = 0; i < tab.length; i++) {
                    var t = Math.floor((tab[i][1] - 0.5) / 4 * key);
                    v.push([tab[i][0], t, 0x00, 0]);
                }
                return v;
            };
            var getHold = function () {
                var v = [];
                for(var i = 0; i < hold.length; i++) {
                    var t = Math.floor((hold[i][2] - 0.5) / 4 * key);
                    v.push([hold[i][0], t, 0x02, hold[i][1] - hold[i][0]]);
                }
                return v;
            };
            var getArc = function (c) {
                var v = [];
                for(var i = 0; i < arc.length; i++) {
                    var t1 = Math.floor((((arc[i][2] - 0.5) * 0.99 + 1) / 2 * 3 + 0.5) / 4 * key);
                    var t2 = Math.floor((((arc[i][3] - 0.5) * 0.99 + 1) / 2 * 3 + 0.5) / 4 * key);
                    if((!arc[i][9]) && (arc[i][7] == c) && (arc[i][0] != arc[i][1])) {
                        if(t1 == t2) {
                            v.push([arc[i][0], t1, 0x02, arc[i][1] - arc[i][0]]);
                        } else {
                            v.push([arc[i][0], t1, 0x62, arc[i][1] - arc[i][0]]);
                            v.push([arc[i][1], t2, 0xA1, 0]);
                        }
                    }
                    for(var j = 0; j < arc[i][10].length; j++) {
                        var t = Math.floor((arc[i][10][j] - arc[i][0]) / (arc[i][1] - arc[i][0]) * (t2 - t1) + t1);
                        var ts = (((t >= 0) && (t < (key - 1) / 4)) || ((t > (key - 1) / 2) && (t <= (key - 1) / 4 * 3)) ? -1 : 1);
                        v.push([arc[i][10][j], t - ts, 0x01, ts]);
                    }
                }
                for(var i = 0; i < v.length; i++) {
                    switch(v[i][2]) {
                        case 0x02:
                            for(var j = 0; j < v.length; j++) {
                                if(v[j][2] == 0x62) {
                                    if(IsNumberClose(v[j][0], v[i][0] + v[i][3]) && (v[j][1] == v[i][1])) {
                                        v[j][3] = v[j][0] + v[j][3] - v[i][0];
                                        v[j][0] = v[i][0];
                                        v.splice(i, 1);
                                        i -= 1;
                                        break;
                                    }
                                }
                            }
                            break;
                        case 0xA1:
                            for(var j = 0; j < v.length; j++) {
                                if((v[j][2] == 0x02) || (v[j][2] == 0x62)) {
                                    if(IsNumberClose(v[j][0], v[i][0]) && (v[j][1] == v[i][1] + v[i][3])) {
                                        v[i][2] = 0x21;
                                        v[j][2] = (v[j][2] == 0x02 ? 0xA2 : v[j][2]);
                                        v[j][2] = (v[j][2] == 0x62 ? 0x22 : v[j][2]);
                                        break;
                                    }
                                }
                            }
                            break;
                    }
                }
                return v;
            };
            r.write(getTab());
            r.write(getHold());
            r.write(getArc(0));
            r.write(getArc(1));
            return r;
        })();
        var beatData = (function () {
            var r = [];
            if(timing.length == 0) {
                timing.push([0, 125]);
            }
            timing = timing.sort(SortNumbers);
            for(var i = 0; i < timing.length; i++) {
                while((r.length > 0) && (!IsNumberClose(r[r.length - 1][0] + 60000 / r[r.length - 1][1], timing[i][0]))) {
                    if(r[r.length - 1][0] + 60000 / r[r.length - 1][1] < timing[i][0]) {
                        r.push([r[r.length - 1][0] + 60000 / r[r.length - 1][1], r[r.length - 1][1]]);
                    } else {
                        var d = timing[i][0] - r[r.length - 1][0] + 60000 / r[r.length - 1][1];
                        r.push([r[r.length - 1][0] + d, 60000 / d]);
                        break;
                    }
                }
                r.push(timing[i]);
            }
            r = enlargeBeatData(offsetBeatData(r, offset), actionData);
            return r;
        })();
        fromData({}, [
            {"ActionData": sortActionData(actionData), "BeatData": beatData}
        ]);
    };
    var toMstBuffer = function (index) {
        if(typeof Text["Mst"][index] === "undefined") {
            Text["Mst"][index] = toMstText(index);
        }
        return new Uint8Array().fromText(Text["Mst"][index]);
    };
    var toBmsBuffer = function (index) {
        if(typeof Text["Bms"][index] === "undefined") {
            Text["Bms"][index] = toBmsText(index);
        }
        return new Uint8Array().fromText(Text["Bms"][index]);
    };
    var toBmeBuffer = function (index) {
        if(typeof Text["Bme"][index] === "undefined") {
            Text["Bme"][index] = toBmeText(index);
        }
        return new Uint8Array().fromText(Text["Bme"][index]);
    };
    var toPmsBuffer = function (index) {
        if(typeof Text["Pms"][index] === "undefined") {
            Text["Pms"][index] = toPmsText(index);
        }
        return new Uint8Array().fromText(Text["Pms"][index]);
    };
    var toVosBuffer = function (type) {
        var vos = readMeta(["BufferVos"]);
        return vos;
    };
    var toMidBuffer = function () {
        var mid = readMeta(["BufferMid"]);
        return mid;
    };
    var toLrcBuffer = function () {
        if(typeof Text["Lrc"] === "undefined") {
            Text["Lrc"] = toLrcText();
        }
        if(typeof Text["Lrc"] === "undefined") {
            return;
        }
        return new Uint8Array().fromText(Text["Lrc"]);
    };
    var toHexBuffer = function (index, type) {
        switch(type) {
            case"vos000":
                if(typeof Text["Hexvosvos000"] === "undefined") {
                    Text["Hexvosvos000"] = toHexText(index, type);
                }
                return new Uint8Array().fromText(Text["Hexvosvos000"]);
                break;
            case"vos001":
                if(typeof Text["Hexvosvos001"] === "undefined") {
                    Text["Hexvosvos001"] = toHexText(index, type);
                }
                return new Uint8Array().fromText(Text["Hexvosvos001"]);
                break;
            case"vos006":
                if(typeof Text["Hexvosvos006"] === "undefined") {
                    Text["Hexvosvos006"] = toHexText(index, type);
                }
                return new Uint8Array().fromText(Text["Hexvosvos006"]);
                break;
            case"vos022":
                if(typeof Text["Hexvosvos022"] === "undefined") {
                    Text["Hexvosvos022"] = toHexText(index, type);
                }
                return new Uint8Array().fromText(Text["Hexvosvos022"]);
                break;
            case"imd":
                if(typeof Text["Heximd"][index] === "undefined") {
                    Text["Heximd"][index] = toHexText(index, "imd");
                }
                return new Uint8Array().fromText(Text["Heximd"][index]);
                break;
        }
    };
    var toTjaBuffer = function (type) {
        switch(type) {
            case"taiko":
                if(typeof Text["Tjataiko"] === "undefined") {
                    Text["Tjataiko"] = toTjaText(type);
                }
                return new Uint8Array().fromText(Text["Tjataiko"], "ANSI");
                break;
            case"jube":
                if(typeof Text["Tjajube"] === "undefined") {
                    Text["Tjajube"] = toTjaText(type);
                }
                return new Uint8Array().fromText(Text["Tjajube"], "ANSI");
                break;
        }
    };
    var toOsuBuffer = function (index, type) {
        switch(type) {
            case"osu":
                if(typeof Text["Osuosu"][index] === "undefined") {
                    Text["Osuosu"][index] = toOsuText(index, "osu");
                }
                return new Uint8Array().fromText(Text["Osuosu"][index]);
                break;
            case"taiko":
                if(typeof Text["Osutaiko"][index] === "undefined") {
                    Text["Osutaiko"][index] = toOsuText(index, "taiko");
                }
                return new Uint8Array().fromText(Text["Osutaiko"][index]);
                break;
            case"ctb":
                if(typeof Text["Osuctb"][index] === "undefined") {
                    Text["Osuctb"][index] = toOsuText(index, "ctb");
                }
                return new Uint8Array().fromText(Text["Osuctb"][index]);
                break;
            case"mania":
                if(typeof Text["Osumania"][index] === "undefined") {
                    Text["Osumania"][index] = toOsuText(index, "mania");
                }
                return new Uint8Array().fromText(Text["Osumania"][index]);
                break;
        }
    };
    var toXmlBuffer = function (index, type) {
        switch(type) {
            case"yddr":
                if(typeof Text["Xmlyddr"][index] === "undefined") {
                    Text["Xmlyddr"][index] = toXmlText(index, "yddr");
                }
                return new Uint8Array().fromText(Text["Xmlyddr"][index]);
                break;
            case"ydsd":
                if(typeof Text["Xmlydsd"][index] === "undefined") {
                    Text["Xmlydsd"][index] = toXmlText(index, "ydsd");
                }
                return new Uint8Array().fromText(Text["Xmlydsd"][index]);
                break;
            case"mde":
                if(typeof Text["Mde"][index] === "undefined") {
                    Text["Mde"][index] = toMdeText(index);
                }
                return new Uint8Array().fromBase64(Text["Mde"][index]).trimRight();
                break;
        }
    };
    var toVoxBuffer = function (index) {
        if(typeof Text["Vox"][index] === "undefined") {
            Text["Vox"][index] = toVoxText(index);
        }
        return new Uint8Array().fromText(Text["Vox"][index]);
    };
    var toKshBuffer = function (index) {
        if(typeof Text["Ksh"][index] === "undefined") {
            Text["Ksh"][index] = toKshText(index);
        }
        return new Uint8Array().fromText(Text["Ksh"][index]);
    };
    var toImdBuffer = function (index) {
        var imd = new Uint8Array(14 + Data[index]["BeatData"].length * 12 + Data[index]["ActionData"].length * 11);
        var p = 0;
        imd.setInt32(Data[index]["Length"], p, true);
        p += 4;
        imd.setInt32(Data[index]["BeatData"].length, p, true);
        p += 4;
        for(var i = 0; i < Data[index]["BeatData"].length; i++) {
            imd.setInt32(Data[index]["BeatData"][i][0], p, true);
            p += 4;
            imd.setFloat64(Data[index]["BeatData"][i][1], p, true);
            p += 8;
        }
        imd.setUint8(3, p);
        p += 1;
        imd.setUint8(3, p);
        p += 1;
        imd.setInt32(Data[index]["ActionData"].length, p, true);
        p += 4;
        for(var i = 0; i < Data[index]["ActionData"].length; i++) {
            imd.setInt16(Data[index]["ActionData"][i][2], p, true);
            p += 2;
            imd.setInt32(Data[index]["ActionData"][i][0], p, true);
            p += 4;
            imd.setUint8(Data[index]["ActionData"][i][1], p);
            p += 1;
            imd.setInt32(Data[index]["ActionData"][i][3], p, true);
            p += 4;
        }
        return imd;
    };
    var toMdeBuffer = function (index) {
        if(typeof Text["Mde"][index] === "undefined") {
            Text["Mde"][index] = toMdeText(index);
        }
        if(typeof Text["Mde"][index] !== "undefined") {
            return new Uint8Array().fromText(Text["Mde"][index]);
        }
    };
    var toMcBuffer = function (index, type) {
        switch(type) {
            case"key":
                if(typeof Text["Mckey"][index] === "undefined") {
                    Text["Mckey"][index] = toMcText(index, "key");
                }
                return new Uint8Array().fromText(Text["Mckey"][index]);
                break;
            case"step":
                if(typeof Text["Mcstep"][index] === "undefined") {
                    Text["Mcstep"][index] = toMcText(index, "step");
                }
                return new Uint8Array().fromText(Text["Mcstep"][index]);
                break;
            case"dj":
                if(typeof Text["Mcdj"][index] === "undefined") {
                    Text["Mcdj"][index] = toMcText(index, "dj");
                }
                return new Uint8Array().fromText(Text["Mcdj"][index]);
                break;
            case"catch":
                if(typeof Text["Mccatch"][index] === "undefined") {
                    Text["Mccatch"][index] = toMcText(index, "catch");
                }
                return new Uint8Array().fromText(Text["Mccatch"][index]);
                break;
            case"pad":
                if(typeof Text["Mcpad"][index] === "undefined") {
                    Text["Mcpad"][index] = toMcText(index, "pad");
                }
                return new Uint8Array().fromText(Text["Mcpad"][index]);
                break;
            case"taiko":
                if(typeof Text["Mctaiko"][index] === "undefined") {
                    Text["Mctaiko"][index] = toMcText(index, "taiko");
                }
                return new Uint8Array().fromText(Text["Mctaiko"][index]);
                break;
        }
    };
    var toAffBuffer = function (index) {
        if(typeof Text["Aff"][index] === "undefined") {
            Text["Aff"][index] = toAffText(index);
        }
        return new Uint8Array().fromText(Text["Aff"][index]);
    };
    var toMstText = function (index) {
        var Mst = [];
        return Mst.join("\r\n");
    };
    var toBmsText = function (index) {
        var extension = (arguments.length > 1 ? arguments[1] : "bms");
        var Offset = (Data[index]["Start"] < 0 ? -Data[index]["Start"] : 0);
        var actionData = toActionDataNoOverlap(Data[index]["ActionData"]);
        var actionDataB = (function () {
            var r = [];
            var s = [];
            for(var i = 0; i < actionData.length; i++) {
                switch(actionData[i][2]) {
                    case 0x00:
                        r.push([Offset + actionData[i][0], actionData[i][1], actionData[i][2], actionData[i][3], actionData[i][2], actionData[i][3]]);
                        break;
                    case 0x01:
                    case 0xA1:
                        r.push([Offset + actionData[i][0], actionData[i][1] + actionData[i][3], 0x00, 0, actionData[i][2], actionData[i][3]]);
                        break;
                    case 0x61:
                    case 0x21:
                        s.push([Offset + actionData[i][0], actionData[i][1], actionData[i][2], actionData[i][3]]);
                        break;
                    case 0x02:
                    case 0x62:
                        r.push([Offset + actionData[i][0], actionData[i][1], 0x02, 0, actionData[i][2], 0]);
                        r.push([Offset + actionData[i][0] + actionData[i][3], actionData[i][1], 0x02, 0, actionData[i][2], 0]);
                        break;
                    case 0x22:
                    case 0xA2:
                        var t = 0;
                        for(var j = 0; j < s.length; j++) {
                            if(s[j][0] == Offset + actionData[i][0]) {
                                t = s[j][3];
                                if(s[j][2] == 0x21) {
                                    t += 36;
                                }
                                break;
                            }
                        }
                        r.push([Offset + actionData[i][0], actionData[i][1], 0x02, 0, actionData[i][2], t]);
                        r.push([Offset + actionData[i][0] + actionData[i][3], actionData[i][1], 0x02, 0, actionData[i][2], t]);
                        break;
                }
            }
            for(var i = 0; i < r.length; i++) {
                var n = Math.floor(toBeatDataBeat(Data[index]["BeatData"], r[i][0]));
                var t = Data[index]["BeatData"][n + 1][0] - Data[index]["BeatData"][n][0];
                for(var j = 0; j < 48; j++) {
                    var c = CloseNumber(r[i][0], Data[index]["BeatData"][n][0] + t / 48 * j);
                    if(r[i][0] != c) {
                        r[i][0] = c;
                        break;
                        ;
                    }
                }
            }
            return r.sort(SortNumbers);
        })();
        var trackToChanel = function (t, b) {
            var r;
            switch(extension) {
                case"bms":
                    switch(t) {
                        case 0:
                            r = 16;
                            break;
                        case 1:
                            r = 11;
                            break;
                        case 2:
                            r = 12;
                            break;
                        case 3:
                            r = 13;
                            break;
                        case 4:
                            r = 14;
                            break;
                        case 5:
                            r = 15;
                            break;
                        case 6:
                            r = 18;
                            break;
                        case 7:
                            r = 26;
                            break;
                        case 8:
                            r = 21;
                            break;
                        case 9:
                            r = 22;
                            break;
                        case 10:
                            r = 23;
                            break;
                        case 11:
                            r = 24;
                            break;
                        case 12:
                            r = 25;
                            break;
                        case 13:
                            r = 28;
                            break;
                    }
                    break;
                case"bme":
                    if(Data[index]["Key"] <= 3) {
                        switch(t) {
                            case 0:
                                r = 11;
                                break;
                            case 1:
                                r = 12;
                                break;
                            case 2:
                                r = 13;
                                break;
                        }
                    } else if(Data[index]["Key"] <= 5) {
                        switch(t) {
                            case 0:
                                r = 13;
                                break;
                            case 1:
                                r = 14;
                                break;
                            case 2:
                                r = 15;
                                break;
                            case 3:
                                r = 18;
                                break;
                            case 4:
                                r = 19;
                                break;
                        }
                    } else {
                        switch(t) {
                            case 0:
                                r = 11;
                                break;
                            case 1:
                                r = 12;
                                break;
                            case 2:
                                r = 13;
                                break;
                            case 3:
                                r = 14;
                                break;
                            case 4:
                                r = 15;
                                break;
                            case 5:
                                r = 18;
                                break;
                            case 6:
                                r = 19;
                                break;
                            case 7:
                                r = 21;
                                break;
                            case 8:
                                r = 22;
                                break;
                            case 9:
                                r = 23;
                                break;
                            case 10:
                                r = 24;
                                break;
                            case 11:
                                r = 25;
                                break;
                            case 12:
                                r = 28;
                                break;
                            case 13:
                                r = 29;
                                break;
                        }
                    }
                    break;
                case"pms":
                    if(Data[index]["Key"] <= 3) {
                        switch(t) {
                            case 0:
                                r = 11;
                                break;
                            case 1:
                                r = 12;
                                break;
                            case 2:
                                r = 13;
                                break;
                        }
                    } else if(Data[index]["Key"] <= 5) {
                        switch(t) {
                            case 0:
                                r = 13;
                                break;
                            case 1:
                                r = 14;
                                break;
                            case 2:
                                r = 15;
                                break;
                            case 3:
                                r = 22;
                                break;
                            case 4:
                                r = 23;
                                break;
                        }
                    } else if(Data[index]["Key"] <= 9) {
                        switch(t) {
                            case 0:
                                r = 11;
                                break;
                            case 1:
                                r = 12;
                                break;
                            case 2:
                                r = 13;
                                break;
                            case 3:
                                r = 14;
                                break;
                            case 4:
                                r = 15;
                                break;
                            case 5:
                                r = 22;
                                break;
                            case 6:
                                r = 23;
                                break;
                            case 7:
                                r = 24;
                                break;
                            case 8:
                                r = 25;
                                break;
                        }
                    } else {
                        switch(t) {
                            case 0:
                                r = 11;
                                break;
                            case 1:
                                r = 12;
                                break;
                            case 2:
                                r = 13;
                                break;
                            case 3:
                                r = 14;
                                break;
                            case 4:
                                r = 15;
                                break;
                            case 5:
                                r = 18;
                                break;
                            case 6:
                                r = 19;
                                break;
                            case 7:
                                r = 16;
                                break;
                            case 8:
                                r = 17;
                                break;
                            case 9:
                                r = 21;
                                break;
                            case 10:
                                r = 22;
                                break;
                            case 11:
                                r = 23;
                                break;
                            case 12:
                                r = 24;
                                break;
                            case 13:
                                r = 25;
                                break;
                            case 14:
                                r = 28;
                                break;
                            case 15:
                                r = 29;
                                break;
                            case 16:
                                r = 26;
                                break;
                            case 17:
                                r = 27;
                                break;
                        }
                    }
                    break;
            }
            if(b) {
                r += 40;
            }
            return r;
        };
        var addScript = function (t, t1, t2, b1, b2) {
            var c = (arguments.length > 5 ? arguments[5] : "A8");
            var d = 192;
            if(typeof t === "undefined") {
                t = new String().duplicate(b1 * d * c.length, "0");
            }
            t = t.splice(b2 * d * c.length + Math.round(t2 / t1 * d) * c.length, c);
            return t;
        };
        var reductScript = function (t) {
            var b = (arguments.length > 1 ? arguments[1] : true);
            var r = t.splitEvery(2);
            if(b) {
                for(var i = r.length; i > 1; i--) {
                    if(r.length % i != 0) {
                        continue;
                    }
                    var isEnlarge = (function () {
                        for(var j = 0; j < r.length; j++) {
                            if((j % i != 0) && (r[j] != "00")) {
                                return false;
                            }
                        }
                        return true;
                    })();
                    if(isEnlarge) {
                        var v = [];
                        for(var j = 0; j < r.length; j++) {
                            if(j % i == 0) {
                                v.push(r[j]);
                            }
                        }
                        r = v;
                        i = r.length - 1;
                    }
                }
            }
            r = r.join("");
            if(r == "00") {
                r = "";
            }
            return r;
        };
        var BOM = Math.ceil((Data[index]["BeatData"].length - 1) / 4 / 1000) * 4;
        var tBPM = Data[index]["BPM"];
        var hBPMs = [];
        var hdBPMs = [];
        var md = [];
        var j = 0;
        var w = false;
        for(var i = 0; i < Data[index]["BeatData"].length - 1; i++) {
            var m = Math.floor(i / BOM);
            var b = ((m + 1) * BOM <= Data[index]["BeatData"].length - 1 ? BOM : Data[index]["BeatData"].length - 1 - m * BOM);
            var n = i % b;
            if(typeof md[m] === "undefined") {
                md[m] = [];
                if((b != 4) && (i != Data[index]["BeatData"].length - 2)) {
                    md[m][2] = (b / 4).toString();
                }
            }
            if(!w && (Offset >= Data[index]["BeatData"][i][0]) && (Offset < Data[index]["BeatData"][i + 1][0])) {
                md[m][1] = addScript(md[m][1], Data[index]["BeatData"][i + 1][0] - Data[index]["BeatData"][i][0], Offset - Data[index]["BeatData"][i][0], b, n, "FF");
                w = true;
            }
            if(Data[index]["BeatData"][i][1] != tBPM) {
                if((Data[index]["BeatData"][i][1] == Math.round(Data[index]["BeatData"][i][1])) && (Data[index]["BeatData"][i][1] < 256)) {
                    md[m][3] = addScript(md[m][3], Data[index]["BeatData"][i + 1][0] - Data[index]["BeatData"][i][0], 0, b, n, DecToBase(Data[index]["BeatData"][i][1]));
                } else {
                    var l = hBPMs.indexOf(Data[index]["BeatData"][i][1]);
                    if(l == -1) {
                        hBPMs.push(Data[index]["BeatData"][i][1]);
                        l = hBPMs.length - 1;
                    }
                    md[m][8] = addScript(md[m][8], Data[index]["BeatData"][i + 1][0] - Data[index]["BeatData"][i][0], 0, b, n, DecToBase(l + 1, 36));
                }
                tBPM = Data[index]["BeatData"][i][1];
            }
            while((j < actionDataB.length) && (actionDataB[j][0] < Data[index]["BeatData"][i + 1][0])) {
                var c = trackToChanel(actionDataB[j][1], actionDataB[j][2] == 0x02);
                var t = "00";
                switch(actionDataB[j][4]) {
                    case 0x00:
                    case 0x01:
                    case 0x02:
                        t = "A8";
                        break;
                    case 0x62:
                        t = "B8";
                        break;
                    case 0x22:
                        t = "C8";
                        break;
                    case 0xA1:
                    case 0xA2:
                        t = "E8";
                        break;
                }
                if(actionDataB[j][4] != 0x00) {
                    t = DecToBase(BaseToDec(t, 36) + actionDataB[j][5], 36);
                }
                md[m][c] = addScript(md[m][c], Data[index]["BeatData"][i + 1][0] - Data[index]["BeatData"][i][0], actionDataB[j][0] - Data[index]["BeatData"][i][0], b, n, t);
                j += 1;
            }
        }
        md[md.length - 1][1] = addScript(md[md.length - 1][1], Math.round(60000 / Data[index]["BPM"]), 0, 4, 0, "01");
        for(var i = 0; i < md.length; i++) {
            if(typeof md[i] === "undefined") {
                md[i] = "";
            } else {
                var sd = [];
                for(var j = 0; j < md[i].length; j++) {
                    if(typeof md[i][j] !== "undefined") {
                        sd.push("#" + i.toString().fill(3, "0", true) + j.toString().fill(2, "0", true) + ":" + reductScript(md[i][j], j != 2));
                    }
                }
                md[i] = sd.join("\r\n");
                if(md[i] != "") {
                    md[i] += "\r\n";
                }
            }
        }
        for(var i = 0; i < hBPMs.length; i++) {
            hdBPMs.push("#BPM" + DecToBase(i + 1, 36).fill(2, "0", true) + " " + hBPMs[i].toString());
        }
        var writeBms = function (n, v) {
            if((typeof v !== "undefined") && (v != "")) {
                bms.push("#" + n + " " + v);
            }
        };
        var bms = [];
        bms.push("");
        bms.push("*---------------------- HEADER FIELD");
        bms.push("");
        writeBms("PLAYER", readMeta(["Player"], "1", index));
        writeBms("GENRE", readMeta(["Genre"], ""));
        writeBms("TITLE", readMeta(["Title"], FileName));
        writeBms("SUBTITLE", readMeta(["SubTitle"], ""));
        writeBms("ARTIST", readMeta(["Artist"], ""));
        writeBms("CREATOR", readMeta(["Creator"], "rmstZ.html[" + FileExtension + (FileType == FileExtension ? "" : "_" + FileType) + "]"));
        writeBms("MAKER", "rmstZ.html[" + FileExtension + (FileType == FileExtension ? "" : "_" + FileType) + "]");
        writeBms("DATETIME", new Date().format());
        writeBms("TIME", readMeta(["Time"], Data[index]["Time"]));
        writeBms("BPM", readMeta(["BPM"], Data[index]["BPM"]));
        writeBms("TOTAL", readMeta(["ToTal"], ""));
        writeBms("PLAYLEVEL", readMeta(["PlayLevel", "Level"], ""));
        writeBms("RANK", readMeta(["Rank", "Level"], ""));
        writeBms("DIFFICULTY", readMeta(["Difficulty"], ""));
        writeBms("MIDFILE", readMeta(["MidFile"], ""));
        writeBms("STAGEFILE", readMeta(["StageFile", "Background"], ""));
        writeBms("EXTCHR", readMeta(["ExtChr"], ""));
        writeBms("VOLWAV", readMeta(["VolWav"], ""));
        writeBms("VIDEOF/S", readMeta(["VideoF/S"], ""));
        bms.push("");
        bms.push("#WAVFF " + GetFileName(readMeta(["Wav", "Wave", "AudioFilename"], FileName)) + ".mp3");
        bms.push("");
        bms.push(hdBPMs.join("\r\n"));
        bms.push("");
        bms.push("");
        bms.push("");
        bms.push("#LNTYPE 1");
        bms.push("");
        bms.push("");
        bms.push("");
        bms.push("*---------------------- MAIN DATA FIELD");
        bms.push("");
        bms.push("");
        bms.push(md.join("\r\n"));
        return bms.join("\r\n");
    };
    var toBmeText = function (index) {
        return toBmsText(index, "bme");
    };
    var toPmsText = function (index) {
        return toBmsText(index, "pms");
    };
    var toVosText = function (type) {
        _buffer = readMeta(["BufferVos"]);
        if(typeof _buffer === "undefined") {
            return;
        }
        var readInt = function (l) {
            var e = (arguments.length > 1 ? arguments[1] : LittleEndian);
            var r = _buffer.getInt(l, p, e);
            writeHex(l);
            return r;
        };
        var readUint = function (l) {
            var e = (arguments.length > 1 ? arguments[1] : LittleEndian);
            var r = buffer.getUint(l, p, e);
            p += l;
            return r;
        };
        var writeHex = function (l) {
            hex.push(_buffer.getHex(p, l, " "));
            p += l;
        };
        var hex = [];
        var p = 0;
        switch(type) {
            case"vos000":
            case"vos001":
                var seg = [];
                var readHeader = (function () {
                    writeHex(4);
                    for(var i = 0; i < 3; i++) {
                        seg.push(readInt(4, true));
                        writeHex(16);
                    }
                })();
                var readInf = (function () {
                    p = seg[0];
                    var n = 4;
                    if((_buffer[p] == 0x56) && (_buffer[p + 1] == 0x4F) && (_buffer[p + 2] == 0x53) && (_buffer[p + 3] == 0x31)) {
                        writeHex(4);
                        writeHex(2);
                        writeHex(64);
                        n = 5;
                    }
                    for(var i = 0; i < n; i++) {
                        writeHex(readUint(1));
                    }
                    writeHex(1);
                    writeHex(1);
                    writeHex(4);
                    writeHex(1);
                    for(var i = 0; i < 64; i++) {
                        writeHex(i == 63 ? 15 : 16);
                    }
                    for(var i = 0; i < 17; i++) {
                        writeHex(4);
                        var n = readInt(4, true);
                        writeHex(14);
                        for(var j = 0; j < n; j++) {
                            writeHex(13);
                        }
                    }
                })();
                var readMid = (function () {
                    p = seg[1];
                    writeHex(4);
                    writeHex(4);
                    var type = readInt(2, false);
                    var track = readInt(2, false);
                    var tick = readInt(2, false);
                    for(var i = 0; i < track; i++) {
                        writeHex(4);
                        var l = readInt(4, false);
                        writeHex(l);
                    }
                })();
                var ReadEof = (function () {
                })();
                break;
            case"vos006":
            case"vos022":
                var readHeader = (function () {
                    writeHex(4);
                })();
                var readTrk = (function () {
                    writeHex(readInt(4, true));
                    var t = readInt(4, true);
                    var v = ((_buffer[p + 4] == 0x30) && (_buffer[p + 5] == 0x36) ? 6 : 22);
                    writeHex(6);
                    for(var i = 0; i < 5; i++) {
                        writeHex(readInt(2, true));
                    }
                    writeHex(1);
                    writeHex(10);
                    var l1 = readInt(4, true);
                    var l2 = readInt(4, true);
                    for(var i = 0; i < 64; i++) {
                        writeHex(16);
                    }
                    var l = readInt(4, true);
                    writeHex(4);
                    for(var i = 0; i < l; i++) {
                        writeHex(5);
                    }
                    writeHex(1);
                    writeHex(1);
                    writeHex(readInt(2, true));
                    writeHex(4);
                    for(var i = 0; i < l; i++) {
                        var n = readInt(4, true);
                        for(var j = 0; j < n; j++) {
                            writeHex(16);
                        }
                    }
                    if(v == 22) {
                        writeHex(4);
                    }
                    var n = readInt(4, true);
                    for(var i = 0; i < n; i++) {
                        writeHex(6);
                    }
                    if((v == 22 ? readInt(4, true) : 0) == 0) {
                        var nl = readInt(4, true);
                        for(var i = 0; i < nl; i++) {
                            writeHex(4);
                            writeHex(readInt(2, true));
                        }
                    }
                })();
                var readMid = (function () {
                    writeHex(readInt(4, true));
                    var l = readInt(4, true);
                    writeHex(4);
                    writeHex(4);
                    var type = readInt(2, false);
                    var track = readInt(2, false);
                    var tick = readInt(2, false);
                    for(var i = 0; i < track; i++) {
                        writeHex(4);
                        var l = readInt(4, false);
                        writeHex(l);
                    }
                })();
                break;
        }
        return hex.join("\r\n");
    };
    var toMidText = function () {
        if(typeof Buffer["Mid"] === "undefined") {
            Buffer["Mid"] = toMidBuffer();
        }
        if(typeof Buffer["Mid"] === "undefined") {
            return;
        }
        var readInt = function (l) {
            var e = (arguments.length > 1 ? arguments[1] : LittleEndian);
            var r = Buffer["Mid"].getInt(l, p, e);
            writeHex(l);
            return r;
        };
        var writeHex = function (l) {
            hex.push(Buffer["Mid"].getHex(p, l, " "));
            p += l;
        };
        var hex = [];
        var p = 0;
        var readMid = (function () {
            writeHex(4);
            writeHex(4);
            var type = readInt(2, false);
            var track = readInt(2, false);
            var tick = readInt(2, false);
            for(var i = 0; i < track; i++) {
                writeHex(4);
                var l = readInt(4, false);
                writeHex(l);
            }
        })();
        return hex.join("\r\n");
    };
    var toLrcText = function () {
        var lrc = readMeta(["BufferLrc"], new Uint8Array(0));
        if(lrc.length == 0) {
            return;
        }
        var readInt = function (l) {
            var e = (arguments.length > 1 ? arguments[1] : LittleEndian);
            var r = lrc.getInt(l, p, e);
            p += l;
            return r;
        };
        var readText = function (l) {
            var r = lrc.getText(p, l);
            p += l;
            return r;
        };
        var lrc = [];
        var p = 0;
        var readLrc = (function () {
            var n = readInt(4, true);
            for(var i = 0; i < n; i++) {
                var ms = readInt(4, true);
                var c = readText(readInt(2, true));
                lrc.push("[" + MillisecondToTime(ms) + "]" + c);
            }
        })();
        return lrc.join("");
    };
    var toHexText = function (index, type) {
        switch(type) {
            case"vos000":
                if(typeof Text["Vosvos000"] === "undefined") {
                    Text["Vosvos000"] = toVosText(type);
                }
                return Text["Vosvos000"];
                break;
            case"vos001":
                if(typeof Text["Vosvos001"] === "undefined") {
                    Text["Vosvos001"] = toVosText(type);
                }
                return Text["Vosvos001"];
                break;
            case"vos006":
                if(typeof Text["Vosvos006"] === "undefined") {
                    Text["Vosvos006"] = toVosText(type);
                }
                return Text["Vosvos006"];
                break;
            case"vos022":
                if(typeof Text["Vosvos022"] === "undefined") {
                    Text["Vosvos022"] = toVosText(type);
                }
                return Text["Vosvos022"];
                break;
            case"imd":
                if(typeof Text["Imd"][index] === "undefined") {
                    Text["Imd"][index] = toImdText(index);
                }
                return Text["Imd"][index];
                break;
        }
    };
    var toTjaText = function (type) {
        var writeTja = function (n, v) {
            var nov = (arguments.length > 2 ? arguments[2] : "");
            if((typeof v !== "undefined") && (v != nov)) {
                tja.push(n + ":" + v);
            }
        };
        var addData = function (t, t1, t2, c) {
            var d = 192 / 4 / 4;
            if(typeof t === "undefined") {
                t = new String().duplicate(d * c.length, "0");
            }
            t = t.splice(Math.round(t2 / t1 * d) * c.length, c);
            return t;
        };
        var reductData = function (t) {
            var u = (arguments.length > 1 ? arguments[1] : 1);
            var c = new String().duplicate(u, "0");
            var r = t.splitEvery(u);
            for(var i = r.length; i > 1; i--) {
                if(r.length % i != 0) {
                    continue;
                }
                var isEnlarge = (function () {
                    for(var j = 0; j < r.length; j++) {
                        if((j % i != 0) && (r[j] != c)) {
                            return false;
                        }
                    }
                    return true;
                })();
                if(isEnlarge) {
                    var v = [];
                    for(var j = 0; j < r.length; j++) {
                        if(j % i == 0) {
                            v.push(r[j]);
                        }
                    }
                    r = v;
                    i = r.length - 1;
                }
            }
            r = r.join("");
            if(r == c) {
                r = "";
            }
            return r;
        };
        var enLargeData = function (t, l) {
            var u = (arguments.length > 2 ? arguments[2] : 1);
            if(t == "") {
                t = "0";
            }
            var r = t.splitEvery(u);
            var n = l / t.length;
            for(var i = 0; i < r.length; i++) {
                r[i] = r[i] + new String().duplicate((n - 1) * u, "0");
            }
            return r.join("");
        };
        var tja = [];
        if(type != "taiko") {
            writeTja("GAME", type);
        }
        writeTja("TITLE", readMeta(["Title"], FileName));
        writeTja("SUBTITLE", readMeta(["SubTitle"], ""));
        writeTja("CREATOR", readMeta(["Creator"], "rmstZ.html[" + FileExtension + (FileType == FileExtension ? "" : "_" + FileType) + "]"));
        writeTja("MAKER", "rmstZ.html[" + FileExtension + (FileType == FileExtension ? "" : "_" + FileType) + "]");
        writeTja("DATETIME", new Date().format());
        writeTja("WAVE", GetFileName(readMeta(["Wave", "Wav", "AudioFilename"], FileName)) + ".mp3");
        writeTja("TIME", readMeta(["Time"], Data[0]["Time"]) / 1000);
        writeTja("OFFSET", readMeta(["Offset"], -Data[0]["Start"] / 1000), 0);
        writeTja("BPM", readMeta(["BPM"], Data[0]["BPM"]));
        writeTja("SONGVOL", readMeta(["SongVol"], ""));
        writeTja("SEVOL", readMeta(["SeVol"], ""));
        writeTja("DEMOSTART", readMeta(["DemoStart", "PreviewTime"], ""));
        writeTja("LIFE", readMeta(["Life"], ""));
        writeTja("SIDE", readMeta(["Side"], ""));
        var unit = (function () {
            switch(type) {
                case"taiko":
                    return 1;
                    break;
                case"jube":
                    return 4;
                    break;
            }
            return 1;
        })();
        for(var index = 0; index < Data.length; index++) {
            var beatData = (function () {
                var r = copyBeatData(Data[index]["BeatData"]);
                while((r.length - 1) % 4 != 0) {
                    r.push([Math.round(r[r.length - 1][0] + 60000 / r[r.length - 1][1]), r[r.length - 1][1]]);
                }
                return r;
            })();
            var actionData = (function () {
                switch(type) {
                    case"taiko":
                        return toActionDataOneTrack(Data[index]["ActionData"], Data[index]["BPM"]);
                        break;
                    case"jube":
                        return toActionDataOneTrack(toActionDataNoLong(Data[index]["ActionData"]), Data[index]["BPM"]);
                        break;
                }
            })();
            var balloon = [];
            var tkData = (function () {
                var r = [];
                var sDong = true;
                var bDong = true;
                var jbEx = (function () {
                    var v = [];
                    for(var i = 0; i < actionData.length; i++) {
                        if(actionData[i][2] == 0x00) {
                            v.push(actionData[i][3].toString(16).fill(4).toUpperCase());
                        }
                    }
                    return(v.join("").replace(/0/g, "").replace(/1/g, "").length == 0);
                })();
                for(var i = 0; i < actionData.length; i++) {
                    var b = toBeatDataBeat(beatData, actionData[i][0]);
                    var d = (function () {
                        var v = "";
                        switch(type) {
                            case"taiko":
                                v = "0";
                                var t = (function () {
                                    var v = ["0", "0", "0", "0"];
                                    var a = actionData[i][3].toString(2).fill(16).splitEvery(4);
                                    for(var j = 0; j < a.length; j++) {
                                        for(var k = 0; k < a[j].length; k++) {
                                            if(a[j][k] != "0") {
                                                v[j] = "1";
                                                break;
                                            }
                                        }
                                    }
                                    return v.join("");
                                })();
                                switch(actionData[i][2]) {
                                    case 0x00:
                                        switch(t) {
                                            case"0010":
                                            case"0100":
                                                v = "1";
                                                break;
                                            case"0001":
                                            case"1000":
                                                v = "2";
                                                break;
                                            case"0110":
                                            case"0111":
                                            case"1110":
                                                v = "3";
                                                break;
                                            case"1001":
                                            case"1011":
                                            case"1101":
                                                v = "4";
                                                break;
                                            case"0011":
                                            case"1100":
                                            case"0101":
                                            case"1010":
                                                v = (sDong ? "1" : "2");
                                                sDong = !sDong;
                                                break;
                                            case"1111":
                                                v = (bDong ? "3" : "4");
                                                bDong = !bDong;
                                                break;
                                        }
                                        break;
                                    case 0x02:
                                        var track = (function () {
                                            var v = 0;
                                            var n = 0;
                                            for(var j = 0; j < t.length; j++) {
                                                if(t[j] != "0") {
                                                    v += j;
                                                    n += 1;
                                                }
                                            }
                                            return Math.floor(v / n);
                                        })();
                                        switch(track) {
                                            case 0:
                                                v = "7";
                                                break;
                                            case 1:
                                                v = "5";
                                                break;
                                            case 2:
                                                v = "6";
                                                break;
                                            case 3:
                                                v = "9";
                                                break;
                                        }
                                        if((r.length > 0) && (v == r[r.length - 1][1])) {
                                            v = "8";
                                        }
                                        break;
                                }
                                break;
                            case"jube":
                                v = "0000";
                                if(jbEx) {
                                    var n = Math.floor((b - Math.floor(b)) * 4);
                                    if(Math.floor(b) % 2 == 0) {
                                        n = 4 - n - 1;
                                    }
                                    var h = parseInt(actionData[i][3].toString(16), 2).toString(16).toUpperCase();
                                    h = h[h.length - 1];
                                    v = v.splice(n, h);
                                } else {
                                    var t = ["", "", "", ""];
                                    var h = actionData[i][3].toString(2).fill(16);
                                    for(var j = 0; j < h.length; j++) {
                                        t[j % 4] += h[j];
                                    }
                                    for(var j = 0; j < t.length; j++) {
                                        t[j] = parseInt(t[j], 2).toString(16).toUpperCase();
                                    }
                                    v = t.join("");
                                }
                                break;
                        }
                        return v;
                    })();
                    r.push([b, d]);
                    if(d == "8") {
                        switch(r[r.length - 2][1]) {
                            case"7":
                            case"9":
                                balloon.push(Math.floor((r[r.length - 1][0] - r[r.length - 2][0]) * 4) + 1);
                                break;
                        }
                    }
                }
                return r;
            })();
            var md = (function () {
                var r = [];
                var bpm = Data[index]["BPM"];
                var n = 0;
                for(var i = 0; i < beatData.length - 1; i++) {
                    var m = Math.floor(i / 4 * unit);
                    if(typeof r[m] === "undefined") {
                        r[m] = [];
                    }
                    if(beatData[i][1] != bpm) {
                        r[m].push("#BPMCHANGE " + beatData[i][1]);
                        bpm = beatData[i][1];
                    }
                    var d = (function () {
                        var r = addData(r, 1, 0, new String().duplicate(unit, "0"));
                        while((n < tkData.length) && (tkData[n][0] >= i) && (tkData[n][0] < i + 1)) {
                            r = addData(r, 1, tkData[n][0] - i, tkData[n][1]);
                            n += 1;
                        }
                        return r;
                    })();
                    r[m].push(d);
                }
                for(var i = 0; i < r.length; i++) {
                    var e = (function () {
                        for(var j = 0; j < r[i].length; j++) {
                            if(r[i][j].substring(0, 1) == "#") {
                                return true;
                            }
                        }
                        return false;
                    })();
                    if(e) {
                        var t = [];
                        var l = (function () {
                            var v = 0;
                            for(var j = 0; j < r[i].length; j++) {
                                if(r[i][j].substring(0, 1) != "#") {
                                    r[i][j] = reductData(r[i][j], unit);
                                    if(r[i][j].length > v) {
                                        v = r[i][j].length;
                                    }
                                }
                            }
                            if(v == 0) {
                                v = 1;
                            }
                            return v;
                        })();
                        for(var j = 0; j < r[i].length; j++) {
                            if(r[i][j].substring(0, 1) != "#") {
                                if(r[i][j].length != l) {
                                    r[i][j] = enLargeData(r[i][j], l, unit);
                                }
                                if((t.length != 0) && (t[t.length - 1].substring(0, 1) != "#")) {
                                    t[t.length - 1] += r[i][j];
                                } else {
                                    t.push(r[i][j]);
                                }
                            } else {
                                t.push(r[i][j]);
                            }
                        }
                        r[i] = t.join("\r\n");
                    } else {
                        r[i] = reductData(r[i].join(""), unit);
                    }
                }
                return r;
            })();
            tja.push("");
            writeTja("COURSE", readMeta(["Course"], 3, index));
            writeTja("LEVEL", readMeta(["Level", "PlayLevel"], 0, index), 0);
            writeTja("STYLE", readMeta(["Style"], "", index), 1);
            if(type == "taiko") {
                writeTja("BALLOON", balloon.join(","));
            }
            writeTja("SCOREINIT", readMeta(["ScoreInit"], "", index), 0);
            writeTja("SCOREDIFF", readMeta(["ScoreDiff"], "", index), 0);
            writeTja("SCOREMODE", readMeta(["ScoreMode"], "", index), 1);
            tja.push("#START");
            if(type == "jube") {
                tja.push("#MEASURE 1/4");
            }
            tja.push(md.join(",\r\n") + ",");
            tja.push("#END");
        }
        return tja.join("\r\n");
    };
    var toOsuText = function (index, type) {
        var mode = (function () {
            var v = -1;
            switch(type) {
                case"osu":
                    v = 0;
                    break;
                case"taiko":
                    v = 1;
                    break;
                case"ctb":
                    v = 2;
                    break;
                case"mania":
                    v = 3;
                    break;
            }
            return v;
        })();
        var version = (function () {
            var v = "[" + type + "]";
            switch(type) {
                case"mania":
                    v += Data[index]["Key"] + "K";
                    break;
            }
            return v;
        })();
        var colours = (function () {
            return readMeta(["Colours"], ["255,0,0", "0,255,0", "0,0,255", "255,255,0", "0,255,255", "255,0,255"]);
        })();
        var osu = [];
        osu.push("osu file format v14");
        osu.push("");
        osu.push("[General]");
        osu.push("AudioFilename: " + GetFileName(readMeta(["AudioFilename", "WAV", "WAVE"], FileName)) + ".mp3");
        osu.push("AudioLeadIn: " + readMeta(["AudioLeadIn"], "0"));
        osu.push("PreviewTime: " + readMeta(["PreviewTime", "DemoStart"], "-1"));
        osu.push("Countdown: " + readMeta(["Countdown"], "0"));
        osu.push("SampleSet: " + readMeta(["SampleSet"], "Soft"));
        osu.push("StackLeniency: " + readMeta(["StackLeniency"], "0.2"));
        osu.push("Mode: " + mode);
        osu.push("LetterboxInBreaks: " + readMeta(["LetterboxInBreaks"], "0"));
        osu.push("SpecialStyle: " + readMeta(["SpecialStyle"], "0"));
        osu.push("StoryFireInFront: " + readMeta(["StoryFireInFront"], "0"));
        osu.push("EpilepsyWarning: " + readMeta(["EpilepsyWarning"], "0"));
        osu.push("CountdownOffset: " + readMeta(["CountdownOffset"], "0"));
        osu.push("WidescreenStoryboard: " + readMeta(["WidescreenStoryboard"], "0"));
        osu.push("");
        osu.push("[Editor]");
        osu.push("Bookmarks: " + readMeta(["Bookmarks"], "0"));
        osu.push("DistanceSpacing: " + readMeta(["DistanceSpacing"], "1"));
        osu.push("BeatDivisor: " + readMeta(["BeatDivisor"], "4"));
        osu.push("GridSize: " + readMeta(["GridSize"], "4"));
        osu.push("TimelineZoom: " + readMeta(["TimelineZoom"], "1"));
        osu.push("");
        osu.push("[Metadata]");
        osu.push("Title:" + readMeta(["Title", "TitleUnicode"], FileName));
        osu.push("TitleUnicode:" + readMeta(["TitleUnicode", "Title"], FileName));
        osu.push("Artist:" + readMeta(["Artist", "ArtistUnicode"], ""));
        osu.push("ArtistUnicode:" + readMeta(["ArtistUnicode", "Artist"], ""));
        osu.push("Creator:" + readMeta(["Creator"], "rmstZ.html[" + FileExtension + (FileType == FileExtension ? "" : "_" + FileType) + "]"));
        osu.push("Maker:" + "rmstZ.html[" + FileExtension + (FileType == FileExtension ? "" : "_" + FileType) + "]");
        osu.push("DateTime:" + new Date().format());
        osu.push("Version:" + readMeta(["Version"], version));
        osu.push("Source:" + readMeta(["Source", "SongSource"], ""));
        osu.push("Tags:" + readMeta(["Tags"], ""));
        osu.push("BeatmapID:" + readMeta(["BeatmapID"], "-1"));
        osu.push("BeatmapSetID:" + readMeta(["BeatmapSetID"], "-1"));
        osu.push("");
        osu.push("[Difficulty]");
        osu.push("HPDrainRate:" + readMeta(["HPDrainRate"], "0"));
        osu.push("CircleSize:" + Data[index]["Key"]);
        osu.push("OverallDifficulty:" + readMeta(["OverallDifficulty"], "3"));
        osu.push("ApproachRate:" + readMeta(["ApproachRate"], "3"));
        osu.push("SliderMultiplier:" + readMeta(["SliderMultiplier"], "1"));
        osu.push("SliderTickRate:" + readMeta(["SliderTickRate"], "1"));
        osu.push("");
        osu.push("[Events]");
        osu.push("//Background and Video events");
        osu.push("0,0," + '"' + readMeta(["Background", "StageFile"], FileName + ".png") + '"' + ",0,0");
        osu.push("//Break Periods");
        osu.push("//Storyboard Layer 0 (Background)");
        osu.push("//Storyboard Layer 1 (Fail)");
        osu.push("//Storyboard Layer 2 (Pass)");
        osu.push("//Storyboard Layer 3 (Foreground)");
        osu.push("//Storyboard Sound Samples");
        osu.push("//Background Colour Transformations");
        osu.push("");
        osu.push("[TimingPoints]");
        for(var i = 0; i < Data[index]["BeatData"].length; i++) {
            var MsPB = Math.round(60000 / Data[index]["BeatData"][i][1] * 1000000000000) / 1000000000000;
            var Inherited = 1;
            if((i > 0) && (Data[index]["BeatData"][i][1] == Data[index]["BeatData"][i - 1][1])) {
                MsPB = -100;
                Inherited = 0;
                continue;
            }
            osu.push(Data[index]["BeatData"][i][0] + "," + MsPB + ",4,2,0,60," + Inherited + ",0");
        }
        osu.push("");
        osu.push("[Colours]");
        for(var i = 0; i < colours.length; i++) {
            osu.push("Combo" + (i + 1) + " : " + colours[i])
        }
        osu.push("");
        osu.push("[HitObjects]");
        var ouputFull = false;
        var beatReturn = 2;
        var getYup = function (b) {
            return Math.floor(b / beatReturn) % 2 == 0;
        };
        var getY = function (t) {
            var b = toBeatDataBeat(Data[index]["BeatData"], t);
            var i = Math.floor(b);
            var r = b - i;
            r = (r + i % beatReturn) / beatReturn;
            r = (getYup(i) ? r : 1 - r);
            return Math.floor(r * 384);
        };
        switch(type) {
            case"osu":
                var actionData = toActionDataShortSlide(toActionDataNoSametime(Data[index]["ActionData"]), Data[index]["BPM"]);
                var getX = function (t) {
                    return Math.floor((t + 0.5) / Data[index]["Key"] * 512);
                };
                for(var i = 0; i < actionData.length; i++) {
                    var x = getX(Math.floor(actionData[i][1]));
                    var y = getY(actionData[i][0]);
                    switch(actionData[i][2]) {
                        case 0x00:
                            osu.push(x + "," + y + "," + Math.floor(actionData[i][0]) + ",1,0" + (ouputFull ? ",0:0:0:0:" : ""));
                            break;
                        case 0x02:
                            osu.push(x + "," + y + "," + Math.floor(actionData[i][0]) + ",8,0," + Math.floor(actionData[i][0] + actionData[i][3]));
                            break;
                        case 0x62:
                            if((i + 1 < actionData.length) && (actionData[i + 1][2] == 0xA1)) {
                                var x2 = getX(actionData[i + 1][1] + actionData[i + 1][3]);
                                var y2 = getY(actionData[i + 1][0]);
                                osu.push(x + "," + y + "," + Math.floor(actionData[i][0]) + ",2,0,L|" + x2 + ":" + y2 + ",1," + Math.floor((toBeatDataBeat(Data[index]["BeatData"], actionData[i][0] + actionData[i][3]) - toBeatDataBeat(Data[index]["BeatData"], actionData[i][0])) * 1 * 100) + (ouputFull ? ",0|0,0:0|0:0,0:0:0:0:" : ""));
                            }
                            break;
                    }
                }
                break;
            case"taiko":
                var actionData = toActionDataOneTrack(Data[index]["ActionData"], Data[index]["BPM"]);
                var sDong = true;
                var bDong = true;
                var vLong = 0;
                for(var i = 0; i < actionData.length; i++) {
                    var t = (function () {
                        var v = ["0", "0", "0", "0"];
                        var a = actionData[i][3].toString(2).fill(16).splitEvery(4);
                        for(var j = 0; j < a.length; j++) {
                            for(var k = 0; k < a[j].length; k++) {
                                if(a[j][k] != "0") {
                                    v[j] = "1";
                                    break;
                                }
                            }
                        }
                        return v.join("");
                    })();
                    var track = (function () {
                        var v = 0;
                        var n = 0;
                        for(var j = 0; j < t.length; j++) {
                            if(t[j] != "0") {
                                v += j;
                                n += 1;
                            }
                        }
                        return Math.floor(v / n);
                    })();
                    var x = Math.floor((track + 0.5) / 4 * 512);
                    switch(actionData[i][2]) {
                        case 0x00:
                            var h = (function () {
                                var v = -1;
                                switch(t) {
                                    case"0010":
                                    case"0100":
                                        v = 0;
                                        break;
                                    case"0001":
                                    case"1000":
                                        v = 2;
                                        break;
                                    case"0110":
                                    case"0111":
                                    case"1110":
                                        v = 4;
                                        break;
                                    case"1001":
                                    case"1011":
                                    case"1101":
                                        v = 6;
                                        break;
                                    case"0011":
                                    case"1100":
                                    case"0101":
                                    case"1010":
                                        v = (sDong ? 1 : 2);
                                        sDong = !sDong;
                                        break;
                                    case"1111":
                                        v = (bDong ? 4 : 6);
                                        bDong = !bDong;
                                        break;
                                }
                                return v;
                            })();
                            osu.push(x + ",0," + Math.floor(actionData[i][0]) + ",1," + h + ",0:0:0:0:");
                            break;
                        case 0x02:
                            if(vLong != 0) {
                                switch(track) {
                                    case 0:
                                    case 3:
                                        osu.push(x + ",0," + Math.floor(vLong) + ",8,0," + Math.floor(actionData[i][0]));
                                        break;
                                    case 1:
                                    case 2:
                                        osu.push(x + ",0," + Math.floor(vLong) + ",2,0,0,1," + Math.floor((toBeatDataBeat(Data[index]["BeatData"], actionData[i][0]) - toBeatDataBeat(Data[index]["BeatData"], vLong)) * 1 * 100));
                                        break;
                                }
                                vLong = 0;
                            } else {
                                vLong = Math.floor(actionData[i][0]);
                            }
                            break;
                    }
                }
                break;
            case"ctb":
                var actionData = toActionDataShortSlide(toActionDataNoSametime(Data[index]["ActionData"]), Data[index]["BPM"]);
                var getX = function (t) {
                    var r = -1;
                    var m = "offset";
                    var u = 1;
                    var p = 1;
                    var b = toBeatDataBeat(Data[index]["BeatData"], actionData[i][0]);
                    switch(m) {
                        case"fix":
                            r = Math.floor((0.5 + t) / Data[index]["Key"] * 512);
                            break;
                        case"offset":
                            r = Math.floor((0.5 - p / 2 + (t + (b % 4 > 2 ? 1 - (b % 2) / 2 : (b % 2) / 2)) / Data[index]["Key"] * p) * 512);
                            break;
                        case"return":
                            r = Math.floor((b % u + (t + 0.5) / Data[index]["Key"]) / (u + 1) * 512);
                            if(Math.floor(b / u) % 2 != 0) {
                                r = 511 - r;
                            }
                            break;
                    }
                    return r;
                };
                for(var i = 0; i < actionData.length; i++) {
                    var x = getX(Math.floor(actionData[i][1]));
                    var y = getY(actionData[i][0]);
                    switch(actionData[i][2]) {
                        case 0x00:
                            var u = 1;
                            var p = 1;
                            var b = toBeatDataBeat(Data[index]["BeatData"], actionData[i][0]);
                            osu.push(x + "," + y + "," + Math.floor(actionData[i][0]) + ",1,0" + (ouputFull ? ",0:0:0:0:" : ""));
                            break;
                        case 0x02:
                            osu.push(x + "," + y + "," + Math.floor(actionData[i][0]) + ",8,0," + Math.floor(actionData[i][0] + actionData[i][3]));
                            break;
                        case 0x62:
                            if((i + 1 < actionData.length) && (actionData[i + 1][2] == 0xA1)) {
                                var x2 = getX(actionData[i + 1][1] + actionData[i + 1][3]);
                                var y2 = getY(actionData[i + 1][0]);
                                osu.push(x + "," + y + "," + Math.floor(actionData[i][0]) + ",2,0,L|" + x2 + ":" + y2 + ",1," + Math.floor((toBeatDataBeat(Data[index]["BeatData"], actionData[i][0] + actionData[i][3]) - toBeatDataBeat(Data[index]["BeatData"], actionData[i][0])) * 1 * 100) + (ouputFull ? ",0|0,0:0|0:0,0:0:0:0:" : ""));
                            } else {
                                osu.push(x + ",0," + Math.floor(actionData[i][0]) + ",8,0," + Math.floor(actionData[i][0] + actionData[i][3]));
                            }
                            break;
                    }
                }
                break;
            case"mania":
                var actionData = toActionDataNoOverlap(toActionDataNoSlide(Data[index]["ActionData"]));
                var getX = function (t) {
                    return Math.floor((t + 0.5) / Data[index]["Key"] * 512);
                };
                for(var i = 0; i < actionData.length; i++) {
                    var x = getX(Math.floor(actionData[i][1]));
                    var y = getY(actionData[i][0]);
                    switch(actionData[i][2]) {
                        case 0x00:
                            osu.push(x + "," + y + "," + Math.floor(actionData[i][0]) + ",1,0" + (ouputFull ? ",0:0:0:0:" : ""));
                            break;
                        case 0x02:
                            osu.push(x + "," + y + "," + Math.floor(actionData[i][0]) + ",128,0," + Math.floor(actionData[i][0] + actionData[i][3]) + (ouputFull ? ":0:0:0:0:" : ""));
                            break;
                    }
                }
                break;
        }
        osu.push("");
        return osu.join("\r\n");
    };
    var toXmlText = function (index, type) {
        switch(type) {
            case"yddr":
                var times = [];
                var types = [];
                var ns = toActionDataNoOverlap(toActionDataNoSlide(Data[index]["ActionData"]));
                for(var i = 0; i < ns.length; i++) {
                    var l = times.indexOf(ns[i][0]);
                    if(l == -1) {
                        times.push(ns[i][0]);
                        types.push(ns[i][1] + 1);
                        if(ns[i][2] == 0x02) {
                            types[types.length - 1] += "_" + ns[i][3];
                        }
                    } else {
                        types[l] += "$" + (ns[i][1] + 1);
                        if(ns[i][2] == 0x02) {
                            types[l] += "_" + ns[i][3];
                        }
                    }
                }
                var r = [];
                for(var i = 0; i < times.length; i++) {
                    r.push(" <beat time=" + '"' + times[i] + '"' + " type=" + '"' + types[i] + '"' + "/>");
                }
                return"<beats>\r\n" + r.join("\r\n") + "\r\n</beats>";
                break;
            case"ydsd":
                if(typeof Buffer["Xmlyddr"][index] === "undefined") {
                    Buffer["Xmlyddr"][index] = toXmlBuffer(index, "yddr");
                }
                var r = Buffer["Xmlyddr"][index].length % 8;
                return CryptDes(true, Buffer["Xmlyddr"][index].fill(Buffer["Xmlyddr"][index].length + r, r, false), new Uint8Array().fromText(new Uint8Array().fromText((3263047).toString(16)).getBase64())).getBase64();
                break;
            case"mde":
                if(typeof readMeta(["BufferXmlmde"]) !== "undefined") {
                    Buffer["Xmlmde"][index] = readMeta(["BufferXmlmde"])[index];
                }
                if(typeof Buffer["Xmlmde"][index] !== "undefined") {
                    return Buffer["Xmlmde"][index].getText();
                }
                var songName = GetFileName(readMeta(["Wav", "Wave", "AudioFilename"], FileName));
                var scaleSize = Math.min(Math.sqrt(Data[index]["Key"]) * 160, 420) / 320;
                var delay = 25;
                var showLength = 48;
                var showTick = Math.round(60000 / Data[index]["BPM"] * 2);
                var canvasSize = [3840, 2560];
                var distance = 400;
                var tailLength = Math.round(60000 / Data[index]["BPM"] * 2);
                var defaultCamSize = 320 * scaleSize;
                var camClickTick = 1 / 8;
                var camClickSize = 1 / 16 * scaleSize;
                var camSlideDistance = 1 / 8 * scaleSize;
                var camMoveDistance = 1 / 4 * scaleSize;
                var camSlideMax = Math.SQRT2 * 2 * camSlideDistance;
                var camMoveMax = Math.SQRT2 * 2 * camMoveDistance;
                var camSizeReturn = camSlideMax * 2 / (camSlideMax * 2 + 1);
                var camMoveReturn = camMoveMax * 2 / (camMoveMax * 2 + 1);
                var beatMove = 4;
                var beatReturn = 2;
                var scaleY = 0.65;
                var offsetY = -0.065;
                var typeLong = "return";
                var getYup = function (b) {
                    return Math.floor(b / beatReturn) % 2 == 0;
                };
                var getX = function (t) {
                    return(t + 0.5) / Data[index]["Key"];
                };
                var getY = function (t) {
                    var b = toBeatDataBeat(Data[index]["BeatData"], t);
                    var i = Math.floor(b);
                    var r = b - i;
                    r = (r + i % beatReturn) / beatReturn;
                    r = (getYup(i) ? r : 1 - r);
                    return r;
                };
                var getMiddle = function (p) {
                    if(p.length == 0) {
                        return;
                    }
                    var xa = 0;
                    var ya = 0;
                    for(var i = 0; i < p.length; i++) {
                        xa += p[i][0];
                        ya += p[i][1];
                    }
                    return[xa / p.length, ya / p.length];
                };
                var getPythagorean = function (x, y) {
                    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
                };
                var getAngle = function (x, y) {
                    var r = 0;
                    if(x == 0) {
                        r = 0;
                        if(y < 0) {
                            r = -180;
                        }
                    } else if(y == 0) {
                        r = -90;
                        if(x < 0) {
                            r = 90;
                        }
                    } else {
                        r = Math.round((Math.atan2(-x, y) / Math.PI * 180) * 1000000) / 1000000;
                    }
                    return r;
                };
                var getPos = function (t, p) {
                    var l = false;
                    for(var i = 0; i < camData.length; i++) {
                        if((camData[i]["mTick"] - delay >= t) || (i == camData.length - 1)) {
                            var j = (i == 0 ? 0 : i - 1);
                            var r = (camData[j]["mTick"] == camData[i]["mTick"] ? 0 : (t >= camData[i]["mTick"] - delay ? 1 : (t - camData[j]["mTick"] + delay) / (camData[i]["mTick"] - camData[j]["mTick"])));
                            var x = r * (camData[i]["mPos"][0] - camData[j]["mPos"][0]) + camData[j]["mPos"][0];
                            var y = r * (camData[i]["mPos"][1] - camData[j]["mPos"][1]) + camData[j]["mPos"][1];
                            var s = r * (camData[i]["mSize"] - camData[j]["mSize"]) + camData[j]["mSize"];
                            var w = canvasSize[0] * s / 1280;
                            var h = canvasSize[1] * s / 1280;
                            if(l) {
                                if((p[0] < 0) || (p[0] > 1) || (p[1] < 0) || (p[1] > 1)) {
                                    var rr = 0;
                                    while(((p[0] < 0) && (-p[0] / rr >= 1)) || ((p[1] < 0) && (-p[1] / rr >= 1)) || ((p[0] >= 1) && (p[0] / rr >= 1)) || ((p[1] >= 1) && (p[1] / rr >= 1))) {
                                        rr += 1;
                                    }
                                    p[0] = (p[0] / rr + 1) / 2;
                                    p[1] = (p[1] / rr + 1) / 2;
                                }
                            }
                            p[1] = 0.5 - (0.5 - p[1]) * scaleY + offsetY;
                            var px = Math.round(p[0] * w + x - w / 2);
                            var py = Math.round(p[1] * h + y - h / 2);
                            return[px, py];
                        }
                    }
                };
                var actionData = toActionDataNoOverlap(Data[index]["ActionData"]);
                var singleData = (function () {
                    var r = [];
                    var c = false;
                    var s = 0;
                    var l = 0;
                    for(var i = 0; i < actionData.length; i++) {
                        switch(actionData[i][2]) {
                            case 0x00:
                            case 0x01:
                            case 0x02:
                                r.push([actionData[i][0], actionData[i][1], actionData[i][2], actionData[i][3], 0, 0]);
                                break;
                            case 0x61:
                                s = actionData[i][3];
                                break;
                            case 0x62:
                            case 0x22:
                                l = actionData[i][3];
                                break;
                            case 0x21:
                                if(l < 60000 / Data[index]["BPM"] / 2) {
                                    r.push([actionData[i][0] - l, actionData[i][1], 0x00, 0, 0, 0]);
                                } else if((l >= 60000 / Data[index]["BPM"] / 2) && (l < 60000 / Data[index]["BPM"] * 2)) {
                                    r.push([actionData[i][0] - l, actionData[i][1], 0x02, l, s, actionData[i][3]]);
                                } else {
                                    r.push([actionData[i][0] - l, actionData[i][1], 0x02, l, 0, 0]);
                                }
                                c = true;
                                s = 0;
                                break;
                            case 0xA1:
                                if(c && (l < 60000 / Data[index]["BPM"] / 2)) {
                                    r.push([actionData[i][0] - l, actionData[i][1], 0x00, 0, 0, 0]);
                                    r.push([actionData[i][0], actionData[i][1] + actionData[i][3], 0x00, 0, 0, 0]);
                                } else if(c && (l >= 60000 / Data[index]["BPM"] / 2) && (l < 60000 / Data[index]["BPM"] * 2)) {
                                    r.push([actionData[i][0] - l, actionData[i][1], 0x02, l, s, actionData[i][3]]);
                                } else {
                                    r.push([actionData[i][0] - l, actionData[i][1], 0x02, l, 0, actionData[i][3]]);
                                }
                                c = false;
                                s = 0;
                                break;
                            case 0xA2:
                                if(c && (actionData[i][3] < 60000 / Data[index]["BPM"] / 2)) {
                                    r.push([actionData[i][0], actionData[i][1], 0x00, 0, 0, 0]);
                                } else {
                                    r.push([actionData[i][0], actionData[i][1], 0x02, actionData[i][3], s, 0]);
                                }
                                c = false;
                                s = 0;
                                break;
                        }
                    }
                    return sortActionData(r);
                })();
                var clickData = (function () {
                    var r = [];
                    for(var i = 0; i < singleData.length; i++) {
                        var b = toBeatDataBeat(Data[index]["BeatData"], singleData[i][0]);
                        var b2 = (function () {
                            switch(singleData[i][2]) {
                                case 0x01:
                                    return singleData[i][3];
                                    break;
                                case 0x02:
                                case 0x62:
                                    return toBeatDataBeat(Data[index]["BeatData"], singleData[i][0] + singleData[i][3]);
                                    break;
                            }
                            return b;
                        })();
                        var x = getX(Math.floor(singleData[i][1] - singleData[i][4]));
                        var y = getY(singleData[i][0]);
                        var s = (function () {
                            if(singleData[i][2] == 0x01) {
                                var d = ["Up", "UpLeft", "Left", "DownLeft", "Down", "DownRight", "Right", "UpRight"];
                                var ml = [2, 1, 1, 1, 0, 0, 1, 2, 4, 4, 3, 2, 2, 3, 3, 3];
                                var mr = [7, 7, 7, 6, 6, 7, 0, 0, 6, 5, 4, 4, 5, 5, 5, 6];
                                var q = Math.floor(x * 4) + Math.floor((getYup(b) ? y : 1 - y) * 4) * 4;
                                if(singleData[i][3] < 0) {
                                    return d[ml[q]];
                                } else {
                                    return d[mr[q]];
                                }
                            }
                            return"";
                        })();
                        r.push([b, [x, y], s, b2]);
                    }
                    return r;
                })();
                var camRota = (function () {
                    var r = [];
                    var b = Math.floor(clickData[0][0]);
                    var e = Math.ceil(clickData[clickData.length - 1][0]);
                    var n = 0;
                    for(var i = b; i < e + 1; i++) {
                        var pl = [];
                        var pr = [];
                        var b = (i + beatMove > e ? e : i + beatMove);
                        while((n < clickData.length) && (clickData[n][0] >= i) && (clickData[n][0] < (i + b) / 2)) {
                            pl.push(clickData[n][1]);
                            n += 1;
                        }
                        while((n < clickData.length) && (clickData[n][0] >= (i + b) / 2) && (clickData[n][0] < b)) {
                            pr.push(clickData[n][1]);
                            n += 1;
                        }
                        pl = getMiddle(pl) || [0.5, 0.5];
                        pr = getMiddle(pr) || [0.5, 0.5];
                        if((r.length != 0) && (pl[0] == 0.5) && (pl[1] == 0.5) && (pr[0] == 0.5) && (pr[1] == 0.5)) {
                            r.push(r[r.length - 1]);
                        } else {
                            r.push(getAngle(pr[0] - pl[0], pr[1] - pl[1]));
                        }
                        i += beatMove - 1;
                    }
                    r = (function () {
                        var a = [];
                        for(var i = 0; i < r.length; i++) {
                            for(var j = 0; j < beatMove; j++) {
                                a.push(r[i]);
                            }
                        }
                        return a;
                    })();
                    return r;
                })();
                var camClick = (function () {
                    var r = [];
                    for(var i = 0; i < clickData.length; i++) {
                        var b = clickData[i][0];
                        var n = [i];
                        while((i + 1 < clickData.length) && (b == clickData[i + 1][0])) {
                            n.push(i + 1);
                            i += 1;
                        }
                        r.push([
                                   [b - camClickTick, b, b + camClickTick],
                                   n
                               ]);
                    }
                    for(var i = 0; i < r.length; i++) {
                        if((i + 1 < r.length) && (r[i][0][2] > r[i + 1][0][0])) {
                            var d = (r[i + 1][0][1] - r[i][0][1]) / 2;
                            r[i][0][2] = r[i][0][1] + d;
                            r[i + 1][0][0] = r[i + 1][0][1] - d;
                        }
                    }
                    for(var i = 0; i < r.length; i++) {
                        var p = [];
                        var pc = [];
                        var ps = [];
                        var pa = [];
                        var pca = [];
                        var c = (r[i][1].length == 1 ? 1 : 2) * 320 / defaultCamSize;
                        var d = [];
                        var s = [0, 0, 0, 0];
                        for(var j = 0; j < r[i][1].length; j++) {
                            switch(clickData[r[i][1][j]][2]) {
                                case"":
                                    p.push(clickData[r[i][1][j]][1]);
                                    pc.push(clickData[r[i][1][j]][1]);
                                    break;
                                default:
                                    p.push(clickData[r[i][1][j]][1]);
                                    ps.push(clickData[r[i][1][j]][1]);
                                    break;
                            }
                            for(var k = 0; k < clickData.length; k++) {
                                if((Math.floor(clickData[r[i][1][j]][0]) > Math.floor(clickData[k][0])) && (clickData[r[i][1][j]][0] >= clickData[k][0]) && (clickData[r[i][1][j]][0] <= clickData[k][3])) {
                                    p.push(clickData[r[i][1][j]][1]);
                                    pc.push(clickData[r[i][1][j]][1]);
                                    break;
                                }
                            }
                        }
                        pa = getMiddle(p);
                        pca = getMiddle(pc) || pa;
                        for(var j = 0; j < r[i][1].length; j++) {
                            switch(clickData[r[i][1][j]][2]) {
                                case"Up":
                                    d.push([0, camSlideDistance]);
                                    s[0] = (clickData[r[i][1][j]][1][1] >= pca[1] ? s[0] + clickData[r[i][1][j]][3] : s[0] - clickData[r[i][1][j]][3]);
                                    break;
                                case"UpLeft":
                                    d.push([-camSlideDistance / Math.SQRT2, camSlideDistance / Math.SQRT2]);
                                    s[1] = (clickData[r[i][1][j]][1][0] - clickData[r[i][1][j]][1][1] <= pca[0] - pca[1] ? s[0] + clickData[r[i][1][j]][3] : s[0] - clickData[r[i][1][j]][3]);
                                    break;
                                case"Left":
                                    d.push([-camSlideDistance, 0]);
                                    s[2] = (clickData[r[i][1][j]][1][0] <= pca[0] ? s[0] + clickData[r[i][1][j]][3] : s[0] - clickData[r[i][1][j]][3]);
                                    break;
                                case"DownLeft":
                                    d.push([-camSlideDistance / Math.SQRT2, -camSlideDistance / Math.SQRT2]);
                                    s[3] = (clickData[r[i][1][j]][1][0] + clickData[r[i][1][j]][1][1] <= pca[0] + pca[1] ? s[0] + clickData[r[i][1][j]][3] : s[0] - clickData[r[i][1][j]][3]);
                                    break;
                                case"Down":
                                    d.push([0, -camSlideDistance]);
                                    s[0] = (clickData[r[i][1][j]][1][1] <= pca[1] ? s[0] + clickData[r[i][1][j]][3] : s[0] - clickData[r[i][1][j]][3]);
                                    break;
                                case"DownRight":
                                    d.push([camSlideDistance / Math.SQRT2, -camSlideDistance / Math.SQRT2]);
                                    s[1] = (clickData[r[i][1][j]][1][0] - clickData[r[i][1][j]][1][1] >= pca[0] - pca[1] ? s[0] + clickData[r[i][1][j]][3] : s[0] - clickData[r[i][1][j]][3]);
                                    break;
                                case"Right":
                                    d.push([camSlideDistance, 0]);
                                    s[2] = (clickData[r[i][1][j]][1][0] >= pca[0] ? s[0] + clickData[r[i][1][j]][3] : s[0] - clickData[r[i][1][j]][3]);
                                    break;
                                case"UpRight":
                                    d.push([camSlideDistance / Math.SQRT2, camSlideDistance / Math.SQRT2]);
                                    s[3] = (clickData[r[i][1][j]][1][0] + clickData[r[i][1][j]][1][1] >= pca[0] + pca[1] ? s[0] + clickData[r[i][1][j]][3] : s[0] - clickData[r[i][1][j]][3]);
                                    break;
                            }
                        }
                        d = getMiddle(d) || [0, 0];
                        d = [(d[0] > camSlideMax ? camSlideMax : d[0]), (d[1] > camSlideMax ? camSlideMax : d[1])];
                        s = (function () {
                            if((pc.length == 0) && (ps.length == 1)) {
                                return 0;
                            }
                            var a = getPythagorean(s[0], s[0] * (1 + Math.SQRT2));
                            var b = getPythagorean(s[1], s[1] * (1 + Math.SQRT2)) / Math.SQRT2;
                            var c = getPythagorean(s[2], s[2] * (1 + Math.SQRT2)) / Math.SQRT2;
                            var d = getPythagorean(s[3], s[3] * (1 + Math.SQRT2));
                            var r = (s[0] > 0 ? a : -a) + (s[1] > 0 ? b : -b) + (s[2] > 0 ? c : -c) + (s[3] > 0 ? d : -d);
                            r = (r > camMoveMax ? camMoveMax : r);
                            r = (r < -camMoveMax ? -camMoveMax : r);
                            return r;
                        })();
                        var rl = (r[i][0][1] - r[i][0][0]) / camClickTick;
                        var rr = (r[i][0][2] - r[i][0][1]) / camClickTick;
                        c = c * rl;
                        d = [d[0] * rr, d[1] * rr];
                        s = s * camSlideDistance * rr + 1;
                        r[i] = [r[i][0], c, d, s];
                    }
                    return r;
                })();
                var camData = (function () {
                    var d = (function () {
                        var toSin = function (a) {
                            return Math.sin(a / 180 * Math.PI);
                        };
                        var toCos = function (a) {
                            return Math.cos(a / 180 * Math.PI);
                        };
                        var fixSize = function (s) {
                            return Math.round((s - (s - defaultCamSize) * camSizeReturn) * 1000) / 1000;
                        };
                        var fixX = function (x) {
                            return Math.round((x - (x - canvasSize[0] / 2) * camMoveReturn) * 1000) / 1000;
                        };
                        var fixY = function (y) {
                            return Math.round((y - (y - canvasSize[1] / 2) * camMoveReturn) * 1000) / 1000;
                        };
                        var refreshData = function () {
                            s += (fixSize(s) - s) * d;
                            x += -mx * s * toSin(camRota[n]) * d;
                            x += (fixX(x) - x) * d;
                            var lx = canvasSize[0] / 2 * s / 1280;
                            x = (x < lx ? lx : x);
                            x = (x > canvasSize[0] - lx ? canvasSize[0] - lx : x);
                            y += my * s * toCos(camRota[n]) * d;
                            y += (fixY(y) - y) * d;
                            var ly = canvasSize[1] / 2 * s / 1280;
                            y = (y < ly ? ly : y);
                            y = (y > canvasSize[0] - ly ? canvasSize[0] - ly : y);
                        };
                        var r = [];
                        var mx = canvasSize[0] / 1280 * camMoveDistance;
                        var my = canvasSize[1] / 1280 * camMoveDistance;
                        var b = Math.floor(clickData[0][0]);
                        var e = Math.ceil(clickData[clickData.length - 1][0]);
                        var n = 0;
                        var x = canvasSize[0] / 2;
                        var y = canvasSize[1] / 2;
                        var s = defaultCamSize;
                        var d = 0;
                        r.push([0, [x, y], 1280]);
                        for(var i = 0; i < camClick.length; i++) {
                            if(i == 0) {
                                if(camClick[i][0][0] >= b) {
                                    r.push([Data[index]["BeatData"][b][0], [x, y], s]);
                                    if(camClick[i][0][0] != b) {
                                        d = camClick[i][0][0] - b;
                                        refreshData();
                                        r.push([fromBeatDataBeat(Data[index]["BeatData"], camClick[i][0][0]), [x, y], s]);
                                    }
                                } else {
                                    r.push([fromBeatDataBeat(Data[index]["BeatData"], camClick[i][0][0]), [x, y], s]);
                                }
                            } else {
                                if(camClick[i][0][0] > camClick[i - 1][0][2]) {
                                    if(camClick[i][0][0] <= b + n + 1) {
                                        d = camClick[i][0][0] - camClick[i - 1][0][2];
                                        refreshData();
                                        r.push([fromBeatDataBeat(Data[index]["BeatData"], camClick[i][0][0]), [x, y], s]);
                                    } else {
                                        d = (b + n + 1) - camClick[i - 1][0][2];
                                        refreshData();
                                        r.push([Data[index]["BeatData"][b + n + 1][0], [x, y], s]);
                                        n += 1;
                                        while(camClick[i][0][0] > b + n + 1) {
                                            d = 1;
                                            refreshData();
                                            r.push([Data[index]["BeatData"][b + n + 1][0], [x, y], s]);
                                            n += 1;
                                        }
                                        d = camClick[i][0][0] - (b + n);
                                        refreshData();
                                        r.push([fromBeatDataBeat(Data[index]["BeatData"], camClick[i][0][0]), [x, y], s]);
                                    }
                                }
                            }
                            if(camClick[i][0][1] <= b + n + 1) {
                                d = camClick[i][0][1] - camClick[i][0][0];
                                refreshData();
                            } else {
                                d = (b + n + 1) - camClick[i][0][0];
                                refreshData();
                                n += 1;
                                d = camClick[i][0][1] - (b + n);
                                refreshData();
                            }
                            r.push([fromBeatDataBeat(Data[index]["BeatData"], camClick[i][0][1]), [x, y], s * (1 - camClickSize * camClick[i][1])]);
                            if(camClick[i][0][2] <= b + n + 1) {
                                d = camClick[i][0][2] - camClick[i][0][0];
                                refreshData();
                            } else {
                                d = (b + n + 1) - camClick[i][0][0];
                                refreshData();
                                n += 1;
                                d = camClick[i][0][2] - (b + n);
                                refreshData();
                            }
                            s *= camClick[i][3];
                            s += (fixSize(s) - s) * d;
                            x += canvasSize[0] * s / 1280 * camClick[i][2][0];
                            x += (fixX(x) - x) * d;
                            y += canvasSize[1] * s / 1280 * camClick[i][2][1];
                            y += (fixY(y) - y) * d;
                            r.push([fromBeatDataBeat(Data[index]["BeatData"], camClick[i][0][2]), [x, y], s]);
                            if((i == camClick.length - 1) && (camClick[i][0][2] < e)) {
                                d = e - camClick[i][0][2];
                                refreshData();
                                r.push([Data[index]["BeatData"][e][0], [x, y], s]);
                            }
                        }
                        r.push([Data[index]["BeatData"][Data[index]["BeatData"].length - 1][0], [canvasSize[0] / 2, canvasSize[1] / 2], 1280]);
                        for(var i = 1; i < r.length; i++) {
                            if(r[i][0] == r[i - 1][0]) {
                                r.splice(i - 1, 1);
                                i -= 1;
                            }
                        }
                        return r;
                    })();
                    var r = [];
                    for(var i = 0; i < d.length; i++) {
                        r[i] = {};
                        r[i]["mIdx"] = i;
                        r[i]["mTick"] = d[i][0] + delay;
                        r[i]["mGrid"] = Math.round(r[i]["mTick"] / 60000 * Data[index]["BPM"]) * 24;
                        r[i]["mPos"] = d[i][1];
                        r[i]["mSize"] = d[i][2];
                        r[i]["Pos"] = r[i]["mPos"];
                        r[i]["Size"] = r[i]["mSize"];
                    }
                    return r;
                })();
                var noteData = (function () {
                    var r = [];
                    for(var i = 0; i < singleData.length; i++) {
                        var x = clickData[i][1][0];
                        var y = clickData[i][1][1];
                        var u = getYup(clickData[i][0]);
                        r[i] = {};
                        r[i]["mIdx"] = i;
                        r[i]["mTick"] = singleData[i][0] + delay;
                        r[i]["mGrid"] = Math.round(r[i]["mTick"] / 60000 * Data[index]["BPM"]) * 24;
                        r[i]["mShowNoteGridLength"] = showLength;
                        r[i]["mShowNoteTick"] = r[i]["mTick"] - showTick;
                        r[i]["mPos"] = getPos(singleData[i][0], [x, y]);
                        r[i]["mStartDistance"] = distance;
                        r[i]["mStartDirection"] = (function () {
                            var d = ["Up", "UpLeft", "Left", "DownLeft", "Down", "DownRight", "Right", "UpRight"];
                            var m = [7, 0, 1, 6, 0, 2, 5, 4, 3];
                            var q = Math.floor(x * 3) + (u ? Math.floor(y * 3) : 2 - Math.floor((1 - y) * 3)) * 3;
                            return d[m[q]];
                        })();
                        r[i]["mStartPos"] = (function () {
                            switch(r[i]["mStartDirection"]) {
                                case"UpRight":
                                    return[r[i]["mPos"][0] + distance, r[i]["mPos"][1] + distance];
                                    break;
                                case"Right":
                                    return[r[i]["mPos"][0] + distance, r[i]["mPos"][1]];
                                    break;
                                case"DownRight":
                                    return[r[i]["mPos"][0] + distance, r[i]["mPos"][1] - distance];
                                    break;
                                case"Down":
                                    return[r[i]["mPos"][0], r[i]["mPos"][1] - distance];
                                    break;
                                case"DownLeft":
                                    return[r[i]["mPos"][0] - distance, r[i]["mPos"][1] - distance];
                                    break;
                                case"Left":
                                    return[r[i]["mPos"][0] - distance, r[i]["mPos"][1]];
                                    break;
                                case"UpLeft":
                                    return[r[i]["mPos"][0] - distance, r[i]["mPos"][1] + distance];
                                    break;
                                case"Up":
                                    return[r[i]["mPos"][0], r[i]["mPos"][1] + distance];
                                    break;
                            }
                        })();
                        r[i]["mDrawArrow"] = "false";
                        r[i]["mArrowRota"] = 0;
                        r[i]["mDrawLink"] = "false";
                        r[i]["mLinkPos"] = [0, 0];
                        r[i]["mLinkNoteIdx"] = -1;
                        r[i]["mNoteInCurve"] = "true";
                        r[i]["mSamePosPreNoteIdx"] = -1;
                        switch(singleData[i][2]) {
                            case 0x00:
                                r[i]["type"] = "CNoteSingle";
                                break;
                            case 0x01:
                                r[i]["type"] = "CNoteSlide";
                                r[i]["mSlideDirection"] = clickData[i][2];
                                break;
                            case 0x02:
                                r[i]["mEndTick"] = singleData[i][0] + singleData[i][3] + delay;
                                r[i]["mEndGrid"] = Math.round(r[i]["mEndTick"] / 60000 * Data[index]["BPM"]) * 24;
                                r[i]["mIsCircleTail"] = "false";
                                r[i]["mIsCircleClockwise"] = (!u ^ (x < 0.5) ? "true" : "false");
                                r[i]["mTailLength"] = tailLength;
                                r[i]["mJudgeCnt"] = Math.round(singleData[i][3] / 10000 * Data[index]["BPM"] - 0.25) + 1;
                                if(singleData[i][5] == 0) {
                                    r[i]["type"] = "CNoteLong";
                                    r[i]["mIsHeadFollowType"] = "false";
                                    r[i]["mPoints"] = (function () {
                                        var p = (function () {
                                            var r = [];
                                            var l = [];
                                            var t = singleData[i][0];
                                            while((t < singleData[i][0] + singleData[i][3]) || IsNumberClose(t, singleData[i][0] + singleData[i][3])) {
                                                l.push([x, y]);
                                                r.push(getPos(t, [x, y]));
                                                t += 60000 / Data[index]["BPM"] / 4;
                                            }
                                            if(r.length == 1) {
                                                l.push([x, y]);
                                                r.push(getPos(singleData[i][0] + singleData[i][3], [x, y]));
                                            }
                                            return r;
                                        })();
                                        var f = (function () {
                                            for(var j = 1; j < p.length; j++) {
                                                if(IsNumberClose(p[j][0], p[j - 1][0], 1) && IsNumberClose(p[j][1], p[j - 1][1], 1)) {
                                                    return false;
                                                }
                                            }
                                            return true;
                                        })();
                                        if(!f) {
                                            p = (function () {
                                                var r = [];
                                                var c = !(x >= 0.5) ^ (y >= 0.5);
                                                var s = toBeatDataBeat(Data[index]["BeatData"], singleData[i][0]);
                                                var e = toBeatDataBeat(Data[index]["BeatData"], singleData[i][0] + singleData[i][3]);
                                                var u = 0.2 / (e - s < 4 ? 4 : Math.ceil(e - s));
                                                var n = 360 / 16;
                                                var d = (180 - 360 / 16) / 2;
                                                var b = 0;
                                                var l = u;
                                                var a = (function () {
                                                    var r = 0;
                                                    if(x >= 0.5) {
                                                        r = 0;
                                                        if(y < 0.5) {
                                                            r = -90;
                                                        }
                                                    } else if(x < 0.5) {
                                                        r = 90;
                                                        if(y < 0.5) {
                                                            r = -180;
                                                        }
                                                    }
                                                    r = (c ? r : r - 90);
                                                    return r;
                                                })();
                                                r.push([x, y]);
                                                while(true) {
                                                    var h = l * 2 * Math.sin(n / 360 * Math.PI);
                                                    b += 1 / 4;
                                                    if(b > e - s) {
                                                        break;
                                                    }
                                                    r.push([r[r.length - 1][0] + h * Math.cos((d + a - 90) / 180 * Math.PI), r[r.length - 1][1] + h * Math.sin((d + a - 90) / 180 * Math.PI)]);
                                                    l += u / 16;
                                                    a = (c ? a + n : a - n);
                                                }
                                                for(var j = 0; j < r.length; j++) {
                                                    r[j] = getPos(fromBeatDataBeat(Data[index]["BeatData"], s + j / 4), r[j]);
                                                }
                                                return r;
                                            })();
                                            var o = (function () {
                                                for(var j = 1; j < p.length; j++) {
                                                    if(IsNumberClose(p[j][0], p[j - 1][0], 1) && IsNumberClose(p[j][1], p[j - 1][1], 1)) {
                                                        return false;
                                                    }
                                                }
                                                return true;
                                            })();
                                            if(!o) {
                                                p = (function () {
                                                    var r = [];
                                                    for(var j = 0; j < Data[index]["BeatData"].length - 1; j++) {
                                                        if((singleData[i][0] >= Data[index]["BeatData"][j][0]) && (singleData[i][0] < Data[index]["BeatData"][j + 1][0])) {
                                                            r.push(getPos(singleData[i][0], [x, y]));
                                                            var t = singleData[i][0] + singleData[i][3];
                                                            var m = [0, 0.25, 0.25, 0, -0.25, -0.25];
                                                            var n = (u ? 0 : m.length / 2);
                                                            while(true) {
                                                                var b = Data[index]["BeatData"][j - j % beatReturn][0];
                                                                var bc = (j - j % beatReturn + beatReturn < Data[index]["BeatData"].length ? Data[index]["BeatData"][j - j % beatReturn + beatReturn][0] : (beatReturn - j % beatReturn) * Data[index]["BeatData"][Data[index]["BeatData"].length - 1][0] + b);
                                                                var xc = x + m[n] / Data[index]["Key"];
                                                                if(t > bc) {
                                                                    r.push(getPos(bc, [xc, getY(bc)]));
                                                                    n = (n < m.length - 1 ? n + 1 : 0);
                                                                    j += beatReturn;
                                                                } else {
                                                                    r.push(getPos(t, [x + m[(n == 0 ? m.length - 1 : n - 1)] / Data[index]["Key"] * (bc - t) / (bc - b), getY(t)]));
                                                                    break;
                                                                }
                                                            }
                                                            return r;
                                                        }
                                                    }
                                                })();
                                            }
                                        }
                                        r[i]["mIsHeadFollowType"] = (f ? "true" : "false");
                                        return p;
                                    })();
                                } else {
                                    r[i]["type"] = "CNoteLongSlide";
                                    r[i]["mPoints"] = (function () {
                                        var p = (function () {
                                            var r = [];
                                            var n = 0;
                                            var t = singleData[i][0];
                                            var td = 60000 / Data[index]["BPM"] / 4;
                                            while((t < singleData[i][0] + singleData[i][3]) || IsNumberClose(t, singleData[i][0] + singleData[i][3])) {
                                                n += 1;
                                                t += td;
                                            }
                                            n = Math.max(n, 2);
                                            var xs = getX(singleData[i][1] - singleData[i][4]);
                                            var xl = getX(singleData[i][1]);
                                            var xe = getX(singleData[i][1] + singleData[i][5]);
                                            var yd = (getY(singleData[i][0] + singleData[i][3]) - y) / (n - 1);
                                            var nh = Math.floor((n - 1) / 2);
                                            var nd = Math.max(nh, 1);
                                            for(var k = 0; k < n; k++) {
                                                if(k <= nh) {
                                                    r.push(getPos(singleData[i][0] + td * k, [xs + (xl - xs) / nd * k, y + yd * k]));
                                                } else {
                                                    r.push(getPos(singleData[i][0] + td * k, [xl + (xe - xl) / nd * (k - nh - ((n % 2 == 1) || (n == 2) ? 0 : 1)), y + yd * k]));
                                                }
                                            }
                                            return r;
                                        })();
                                        return p;
                                    })();
                                }
                                if(r[i]["mPoints"].length == 2) {
                                    if(Math.abs(r[i]["mPoints"][1][0] - r[i]["mPoints"][0][0]) < 15) {
                                        r[i]["mPoints"][1][0] = r[i]["mPoints"][0][0] + (r[i]["mPoints"][1][0] > r[i]["mPoints"][0][0] ? 15 : -15);
                                    }
                                    if(Math.abs(r[i]["mPoints"][1][1] - r[i]["mPoints"][0][1]) < 15) {
                                        r[i]["mPoints"][1][1] = r[i]["mPoints"][0][1] + (r[i]["mPoints"][1][1] > r[i]["mPoints"][0][1] ? 15 : -15);
                                    }
                                }
                                break;
                        }
                    }
                    for(var i = 0; i < r.length; i++) {
                        for(var j = i + 1; j < r.length; j++) {
                            if(r[i]["mTick"] != r[j]["mTick"]) {
                                break;
                            }
                            if((r[i]["type"] == r[j]["type"]) && !((r[i]["type"] == "CNoteLong") && ((r[i]["mIsHeadFollowType"] == "true") || (r[j]["mIsHeadFollowType"] == "true")))) {
                                r[i]["mDrawLink"] = "true";
                                r[i]["mLinkPos"] = r[j]["mPos"];
                                r[i]["mLinkNoteIdx"] = r[j]["mIdx"];
                                r[j]["mDrawLink"] = "false";
                                r[j]["mLinkPos"] = r[i]["mPos"];
                                r[j]["mLinkNoteIdx"] = r[i]["mIdx"];
                                i += 1;
                                break;
                            }
                        }
                    }
                    for(var i = 0; i < r.length; i++) {
                        var j = i + 1;
                        if(r[i]["mDrawLink"] == "true") {
                            j += 1;
                        }
                        if(j > r.length - 1) {
                            break;
                        }
                        r[i]["mDrawArrow"] = "true";
                        r[i]["mArrowRota"] = getAngle(r[j]["mPos"][0] - r[i]["mPos"][0], r[j]["mPos"][1] - r[i]["mPos"][1]);
                    }
                    return r;
                })();
                var countTotal = (function () {
                    var r = 0;
                    for(var i = 0; i < noteData.length; i++) {
                        switch(noteData[i]["type"]) {
                            case"CNoteSingle":
                            case"CNoteSlide":
                                r += 1;
                                break;
                            case"CNoteLong":
                            case"CNoteLongSlide":
                                r += noteData[i]["mJudgeCnt"];
                                break;
                        }
                    }
                    return r;
                })();
                var xml = (function () {
                    var r = [];
                    var writeString = function (l, c) {
                        if(typeof c === "undefined") {
                            return;
                        }
                        r.push(new String().duplicate(2 * l, "&nbsp;") + c);
                    };
                    var writeData = function (l, f, c) {
                        if(typeof c === "undefined") {
                            return;
                        }
                        r.push(new String().duplicate(2 * l, "&nbsp;") + "&lt;" + f + "&gt;" + c + "&lt;/" + f + "&gt;");
                    };
                    var writePos = function (l, f, p) {
                        if(typeof p === "undefined") {
                            return;
                        }
                        writeString(l, "&lt;" + f + "&gt;");
                        writeData(l + 1, "x", p[0]);
                        writeData(l + 1, "y", p[1]);
                        writeString(l, "&lt;/" + f + "&gt;");
                    };
                    var writePoints = function (l, f, p) {
                        if(typeof p === "undefined") {
                            return;
                        }
                        writeString(l, "&lt;" + f + "&gt;");
                        for(var i = 0; i < p.length; i++) {
                            writePos(l + 1, "Vector2", p[i]);
                        }
                        writeString(l, "&lt;/" + f + "&gt;");
                    };
                    var writeNotes = function (l) {
                        writeString(l, "&lt;mNotes&gt;");
                        for(var i = 0; i < noteData.length; i++) {
                            writeString(l + 1, "&lt;CNoteBase xsi:type=&quot;" + noteData[i]["type"] + "&quot;&gt;");
                            writeData(l + 2, "mIdx", noteData[i]["mIdx"]);
                            writeData(l + 2, "mGrid", noteData[i]["mGrid"]);
                            writeData(l + 2, "mTick", noteData[i]["mTick"]);
                            writeData(l + 2, "mShowNoteGridLength", noteData[i]["mShowNoteGridLength"]);
                            writeData(l + 2, "mShowNoteTick", noteData[i]["mShowNoteTick"]);
                            writePos(l + 2, "mPos", noteData[i]["mPos"]);
                            writePos(l + 2, "mStartPos", noteData[i]["mStartPos"]);
                            writeData(l + 2, "mStartDistance", noteData[i]["mStartDistance"]);
                            writeData(l + 2, "mStartDirection", noteData[i]["mStartDirection"]);
                            writeData(l + 2, "mDrawArrow", noteData[i]["mDrawArrow"]);
                            writeData(l + 2, "mArrowRota", noteData[i]["mArrowRota"]);
                            writeData(l + 2, "mDrawLink", noteData[i]["mDrawLink"]);
                            writePos(l + 2, "mLinkPos", noteData[i]["mLinkPos"]);
                            writeData(l + 2, "mLinkNoteIdx", noteData[i]["mLinkNoteIdx"]);
                            writeData(l + 2, "mNoteInCurve", noteData[i]["mNoteInCurve"]);
                            writeData(l + 2, "mSamePosPreNoteIdx", noteData[i]["mSamePosPreNoteIdx"]);
                            writeData(l + 2, "mSlideDirection", noteData[i]["mSlideDirection"]);
                            writeData(l + 2, "mIsCircleTail", noteData[i]["mIsCircleTail"]);
                            writeData(l + 2, "mIsCircleClockwise", noteData[i]["mIsCircleClockwise"]);
                            writePoints(l + 2, "mPoints", noteData[i]["mPoints"]);
                            writeData(l + 2, "mTailLength", noteData[i]["mTailLength"]);
                            writeData(l + 2, "mEndGrid", noteData[i]["mEndGrid"]);
                            writeData(l + 2, "mEndTick", noteData[i]["mEndTick"]);
                            writeData(l + 2, "mIsHeadFollowType", noteData[i]["mIsHeadFollowType"]);
                            writeData(l + 2, "mJudgeCnt", noteData[i]["mJudgeCnt"]);
                            writeString(l + 1, "&lt;/CNoteBase&gt;");
                        }
                        writeString(l, "&lt;/mNotes&gt;");
                    };
                    var writeCamList = function (l) {
                        writeString(l, "&lt;mCamList&gt;");
                        for(var i = 0; i < camData.length; i++) {
                            writeString(l + 1, "&lt;CNoteCamData&gt;");
                            writeData(l + 2, "mIdx", camData[i]["mIdx"]);
                            writeData(l + 2, "mGrid", camData[i]["mGrid"]);
                            writeData(l + 2, "mTick", Math.round(camData[i]["mTick"]));
                            writePos(l + 2, "mPos", [Math.round(camData[i]["mPos"][0]), Math.round(camData[i]["mPos"][1])]);
                            writeData(l + 2, "mSize", Math.round(camData[i]["mSize"]));
                            writePos(l + 2, "Pos", [Math.round(camData[i]["Pos"][0]), Math.round(camData[i]["Pos"][1])]);
                            writeData(l + 2, "Size", Math.round(camData[i]["Size"]));
                            writeString(l + 1, "&lt;/CNoteCamData&gt;");
                        }
                        writeString(l, "&lt;/mCamList&gt;");
                    };
                    writeString(0, "&lt;?xml version=&quot;1.0&quot; encoding=&quot;utf-8&quot;?&gt;");
                    writeString(0, "&lt;CTimeStream xmlns:xsi=&quot;http://www.w3.org/2001/XMLSchema-instance&quot; xmlns:xsd=&quot;http://www.w3.org/2001/XMLSchema&quot; xsi:type=&quot;CNoteStream&quot;&gt;");
                    writeData(1, "mLength", (Data[index]["Time"] + delay) / 1000);
                    writeString(1, "&lt;mChildStreams /&gt;");
                    writeData(1, "mSongName", songName);
                    writeData(1, "mStartDelay", delay / 1000);
                    writeData(1, "mBGImagePath", "Assets/Resources/Song/" + songName + "/" + songName + "_ipad.jpg");
                    writeData(1, "mJudgeCnt", countTotal);
                    writeData(1, "mBPM", Data[index]["BPM"]);
                    writeString(1, "&lt;mBPMList /&gt;");
                    writeNotes(1);
                    writePos(1, "mCanvasSize", canvasSize);
                    writeCamList(1);
                    writeData(1, "mDefaultCamSize", defaultCamSize);
                    writeData(1, "mFixCamSize", false);
                    writeData(1, "mPosGridSize", 40);
                    writeData(1, "mKeepOnGrid", true);
                    writeData(1, "mShowGrid", false);
                    writeData(1, "mShowBG", true);
                    writeData(1, "mVisualGridType", "G6");
                    writeData(1, "DefaultCamSize", defaultCamSize);
                    writeString(0, "&lt;/CTimeStream&gt;");
                    return r;
                })();
                return xml.join("\r\n").fromHtml();
                break;
        }
    };
    var toVoxText = function (index) {
        var vox = [];
        return vox.join("\r\n");
    };
    var toKshText = function (index) {
        var ksh = [];
        return ksh.join("\r\n");
    };
    var toImdText = function (index) {
        if(typeof Buffer["Imd"][Index] === "undefined") {
            Buffer["Imd"][Index] = toImdBuffer(Index);
        }
        var readInt = function (l) {
            var e = (arguments.length > 1 ? arguments[1] : LittleEndian);
            var r = Buffer["Imd"][index].getInt(l, p, e);
            writeHex(l);
            return r;
        };
        var writeHex = function (l) {
            hex.push(Buffer["Imd"][index].getHex(p, l, " "));
            p += l;
        };
        var hex = [];
        var p = 0;
        writeHex(4);
        var b = readInt(4, true);
        for(var i = 0; i < b; i++) {
            writeHex(12);
        }
        writeHex(2);
        var a = readInt(4, true);
        for(var i = 0; i < a; i++) {
            writeHex(11);
        }
        return hex.join("\r\n");
    };
    var toMdeText = function (index) {
        if(typeof Buffer["Mde"][index] !== "undefined") {
            return Buffer["Mde"][index].getText();
        }
        if(typeof Text["Xmlmde"][index] === "undefined") {
            Text["Xmlmde"][index] = toXmlText(index, "mde");
        }
        Buffer["Xmlmde"][index] = new Uint8Array().fromText(Text["Xmlmde"][index]);
        if(typeof Buffer["Xmlmde"][index] !== "undefined") {
            var l = Buffer["Xmlmde"][index].length;
            var n = 0;
            while(true) {
                n += 65536 * 4;
                if(l <= n) {
                    l = n;
                    break;
                }
            }
            var d = new Uint8Array(l);
            d.set(Buffer["Xmlmde"][index], 0);
            return d.getBase64();
        }
    };
    var toMcText = function (index, type) {
        var writeMc = function (l, t) {
            mc.push(new String().duplicate(4 * l, " ") + t);
        };
        var toBeatString = function (t) {
            var b = toBeatDataBeat(Data[index]["BeatData"], t);
            var c = Math.round((b - Math.floor(b)) * 48);
            var d = 48;
            b = Math.floor(b);
            if(c == 0) {
                d = 1;
            } else if(c == d) {
                b += 1
                c = 0;
                d = 1;
            } else {
                while((c % 2 == 0) && (d % 2 == 0)) {
                    c /= 2;
                    d /= 2;
                }
                while((c % 3 == 0) && (d % 3 == 0)) {
                    c /= 3;
                    d /= 3;
                }
            }
            return"[" + b + "," + c + "," + d + "]";
        };
        var actionData = (function () {
            var r = [];
            var key = Data[index]["Key"];
            var track = (function () {
                var r = key;
                switch(type) {
                    case"key":
                        r = (r < 4 ? 4 : r);
                        r = (r > 10 ? 10 : r);
                        break;
                    case"step":
                        r = (r < 4 ? 4 : r);
                        r = (r > 10 ? 10 : r);
                        r = (r == 9 ? 10 : r);
                        break;
                    case"dj":
                        r = (r < 7 ? 6 : 8);
                        break;
                }
                return r;
            })();
            switch(type) {
                case"key":
                case"step":
                case"dj":
                    r = toActionDataNoOverlap(toActionDataTransTrack(toActionDataNoSlide(Data[index]["ActionData"]), track));
                    break;
                case"catch":
                case"taiko":
                    r = toActionDataOneTrack(Data[index]["ActionData"], Data[index]["BPM"]);
                    break;
                case"pad":
                    r = toActionDataSquareLong(Data[index]["ActionData"], Data[index]["BPM"], 4, 4);
                    break;
            }
            return r;
        })();
        var key = getActionDataKey(actionData);
        var version = (function () {
            var v = "[" + type + "]";
            switch(type) {
                case"key":
                case"step":
                case"dj":
                    v += key + "K";
                    break;
            }
            return v;
        })();
        var time = (function () {
            var r = [];
            for(var i = 0; i < Data[index]["BeatData"].length; i++) {
                if(r.length == 0) {
                    r.push({"beat": toBeatString(0), "bpm": Data[index]["BeatData"][i][1]});
                }
                if(Data[index]["BeatData"][i][1] != r[r.length - 1]["bpm"]) {
                    r.push({"beat": toBeatString(Data[index]["BeatData"][i][0]), "bpm": Data[index]["BeatData"][i][1]});
                }
            }
            return r;
        })();
        var note = (function () {
            var r = [];
            var sDong = true;
            var bDong = true;
            for(var i = 0; i < actionData.length; i++) {
                var d = {};
                d["beat"] = toBeatString(actionData[i][0]);
                switch(type) {
                    case"key":
                    case"step":
                    case"dj":
                        switch(actionData[i][2]) {
                            case 0x00:
                                d["column"] = actionData[i][1];
                                break;
                            case 0x02:
                                d["endbeat"] = toBeatString(actionData[i][0] + actionData[i][3]);
                                d["column"] = actionData[i][1];
                                break;
                        }
                        break;
                    case"catch":
                        switch(actionData[i][2]) {
                            case 0x00:
                                var m = "fix";
                                var u = 1;
                                var p = 1;
                                var s = (function () {
                                    var v = ["0", "0", "0", "0"];
                                    var a = actionData[i][3].toString(2).fill(16).splitEvery(4);
                                    for(var j = 0; j < a.length; j++) {
                                        for(var k = 0; k < a[j].length; k++) {
                                            if(a[j][k] != "0") {
                                                v[j] = "1";
                                                break;
                                            }
                                        }
                                    }
                                    v = v.join("");
                                    var t = [];
                                    for(var j = 0; j < v.length; j++) {
                                        if(v[j] != "0") {
                                            t.push(j);
                                        }
                                    }
                                    return t.average();
                                })();
                                var b = toBeatDataBeat(Data[index]["BeatData"], actionData[i][0]);
                                switch(m) {
                                    case"fix":
                                        d["x"] = Math.floor((0.5 + s) / Data[index]["Key"] * 512);
                                        break;
                                    case"offset":
                                        d["x"] = Math.floor((0.5 - p / 2 + (t + (b % 4 > 2 ? 1 - (b % 2) / 2 : (b % 2) / 2)) / Data[index]["Key"] * p) * 512);
                                        break;
                                    case"return":
                                        d["x"] = Math.floor((b % u + (s + 0.5) / Data[index]["Key"]) / (u + 1) * 512);
                                        if(Math.floor(b / u) % 2 != 0) {
                                            d["x"] = 511 - d["x"];
                                        }
                                        break;
                                }
                                break;
                            case 0x02:
                                if((r.length > 0) && (typeof r[r.length - 1]["endbeat"] !== "undefined") && (r[r.length - 1]["endbeat"] == 0)) {
                                    r[r.length - 1]["endbeat"] = toBeatString(actionData[i][0]);
                                } else {
                                    d["endbeat"] = 0;
                                    d["type"] = 3;
                                }
                                break;
                        }
                        if((typeof d["x"] === "undefined") && (typeof d["type"] === "undefined")) {
                            continue;
                        }
                        break;
                    case"pad":
                        var getIndex = function (t, tr) {
                            var b = toBeatDataBeat(Data[index]["BeatData"], t);
                            var n = Math.floor((b - Math.floor(b)) * 4);
                            if(Math.floor(b) % 2 == 0) {
                                n = 4 - n - 1;
                            }
                            return n * 4 + tr;
                        };
                        d["index"] = getIndex(actionData[i][0], actionData[i][1]);
                        switch(actionData[i][2]) {
                            case 0x00:
                                break;
                            case 0x02:
                                d["endbeat"] = toBeatString(actionData[i][0] + actionData[i][3]);
                                d["endindex"] = getIndex(actionData[i][0] + actionData[i][3], actionData[i][1]);
                                break;
                            default:
                                d["endbeat"] = toBeatString(actionData[i][0] + actionData[i][3]);
                                d["endindex"] = d["index"] + actionData[i][2] - 10;
                                break;
                        }
                        break;
                    case"taiko":
                        d["style"] = (function () {
                            var v = -1;
                            var t = (function () {
                                var v = ["0", "0", "0", "0"];
                                var a = actionData[i][3].toString(2).fill(16).splitEvery(4);
                                for(var j = 0; j < a.length; j++) {
                                    for(var k = 0; k < a[j].length; k++) {
                                        if(a[j][k] != "0") {
                                            v[j] = "1";
                                            break;
                                        }
                                    }
                                }
                                return v.join("");
                            })();
                            switch(actionData[i][2]) {
                                case 0x00:
                                    switch(t) {
                                        case"0010":
                                        case"0100":
                                            v = 0;
                                            break;
                                        case"0001":
                                        case"1000":
                                            v = 1;
                                            break;
                                        case"0110":
                                        case"0111":
                                        case"1110":
                                            v = 2;
                                            break;
                                        case"1001":
                                        case"1011":
                                        case"1101":
                                            v = 3;
                                            break;
                                        case"0011":
                                        case"1100":
                                        case"0101":
                                        case"1010":
                                            v = (sDong ? 0 : 1);
                                            sDong = !sDong;
                                            break;
                                        case"1111":
                                            v = (bDong ? 2 : 3);
                                            bDong = !bDong;
                                            break;
                                    }
                                    break;
                                case 0x02:
                                    if((r.length > 0) && (typeof r[r.length - 1]["endbeat"] !== "undefined") && (r[r.length - 1]["endbeat"] == 0)) {
                                        r[r.length - 1]["endbeat"] = toBeatString(actionData[i][0]);
                                    } else {
                                        d["endbeat"] = 0;
                                        var track = (function () {
                                            var v = 0;
                                            var n = 0;
                                            for(var j = 0; j < t.length; j++) {
                                                if(t[j] != "0") {
                                                    v += j;
                                                    n += 1;
                                                }
                                            }
                                            return Math.floor(v / n);
                                        })();
                                        switch(track) {
                                            case 0:
                                                v = 6;
                                                break;
                                            case 1:
                                                v = 4;
                                                break;
                                            case 2:
                                                v = 5;
                                                break;
                                            case 3:
                                                v = 6;
                                                break;
                                        }
                                        if(v == 6) {
                                            d["hits"] = Math.floor((toBeatDataBeat(Data[index]["BeatData"], actionData[i][0]) - toBeatDataBeat(Data[index]["BeatData"], actionData[i - 1][0])) * 4) + 1;
                                        }
                                    }
                                    break;
                            }
                            return v;
                        })();
                        if(d["style"] == -1) {
                            continue;
                        }
                        break;
                }
                r.push(d);
            }
            return r;
        })();
        var mc = [];
        writeMc(0, "{");
        writeMc(1, '"meta": {');
        writeMc(2, '"cover": "' + readMeta(["Cover"], FileName + ".png") + '",');
        writeMc(2, '"background": "' + readMeta(["Background"], FileName + ".png") + '",');
        writeMc(2, '"version": "' + readMeta(["Version"], version) + '",');
        writeMc(2, '"creator": "' + readMeta(["Creator"], "rmstZ.html[" + FileExtension + (FileType == FileExtension ? "" : "_" + FileType) + "]") + '",');
        writeMc(2, '"maker": "' + "rmstZ.html[" + FileExtension + (FileType == FileExtension ? "" : "_" + FileType) + "]" + '",');
        writeMc(2, '"datetime": "' + new Date().format() + '",');
        writeMc(2, '"time": ' + Math.floor(new Date().getTime() / 1000) + ",");
        writeMc(2, '"id": ' + readMeta(["Id"], 0) + ",");
        switch(type) {
            case"key":
            case"step":
            case"dj":
                writeMc(2, '"mode": ' + "0,");
                break;
            case"catch":
                writeMc(2, '"mode": ' + "3,");
                break;
            case"pad":
                writeMc(2, '"mode": ' + "4,");
                break;
            case"taiko":
                writeMc(2, '"mode": ' + "5,");
                break;
        }
        writeMc(2, '"song": {');
        writeMc(3, '"title": "' + readMeta(["SongTitle"], FileName) + '",');
        writeMc(3, '"artist": "' + readMeta(["SongArtist"], "") + '",');
        writeMc(3, '"id": "' + readMeta(["SongId"], "") + '",');
        writeMc(3, '"source": "' + readMeta(["SongSource"], "") + '",');
        writeMc(3, '"length": "' + readMeta(["Time"], "") + '",');
        writeMc(3, '"org": {');
        writeMc(4, '"title": "' + readMeta(["SongOrgTitle", "Title"], "") + '",');
        writeMc(4, '"artist": "' + readMeta(["SongOrgArtist", "Artist"], "") + '",');
        writeMc(4, '"source": "' + readMeta(["SongOrgSource", "Source"], '"'));
        writeMc(3, "}");
        writeMc(2, "},");
        writeMc(2, '"mode_ext": {');
        switch(type) {
            case"key":
            case"step":
            case"dj":
                writeMc(3, '"column": ' + key);
                break;
            case"catch":
                writeMc(3, '"speed": ' + readMeta(["Mode_extSpeed"], Math.floor(25 / Data[index]["Key"])));
                break;
            case"pad":
                writeMc(3, '"interval": ' + readMeta(["Mode_extInterval"], 0.001));
                break;
            case"taiko":
                writeMc(3, '"column": 4');
                break;
        }
        writeMc(2, "}");
        writeMc(1, "},");
        writeMc(1, '"time": [');
        for(var i = 0; i < time.length; i++) {
            writeMc(2, "{");
            writeMc(3, '"beat": ' + time[i]["beat"] + ",");
            writeMc(3, '"bpm": ' + time[i]["bpm"]);
            writeMc(2, (i == time.length - 1 ? "}" : "},"));
        }
        writeMc(1, "],");
        writeMc(1, '"note": [');
        writeMc(2, "{");
        writeMc(3, '"beat": [' + [0, 0, 1].join(",") + "],");
        writeMc(3, '"sound": "' + GetFileName(readMeta(["AudioFilename", "Wave", "Wav"], FileName)) + ".mp3" + '",');
        writeMc(3, '"type": 1');
        writeMc(2, (note.length == 0 ? "}" : "},"));
        var m = ["beat", "endbeat", "column", "x", "index", "endindex", "type", "style"];
        for(var i = 0; i < note.length; i++) {
            writeMc(2, "{");
            var t = [];
            for(var n in note[i]) {
                if(typeof note[i][n] !== "undefined") {
                    t.push('"' + n + '": ' + note[i][n]);
                }
            }
            writeMc(3, t.join(",\r\n" + new String().duplicate(4 * 3, " ")));
            writeMc(2, (i == note.length - 1 ? "}" : "},"));
        }
        writeMc(1, "],");
        writeMc(1, '"extra": {');
        writeMc(2, '"test": {');
        writeMc(3, '"divide": ' + readMeta(["ExtraTestDivide"], 48) + ',');
        writeMc(3, '"speed": ' + readMeta(["ExtraTestSpeed"], 100) + ',');
        writeMc(3, '"save": ' + readMeta(["ExtraTestSave"], 0) + ',');
        writeMc(3, '"lock": ' + readMeta(["ExtraTestLock"], 0));
        writeMc(2, "}");
        writeMc(1, "}");
        writeMc(0, "}");
        return mc.join("\r\n");
    };
    var toAffText = function (index) {
        var toY = function (t, c) {
            var r = 0.5;
            var u = 2;
            var b = toBeatDataBeat(Data[index]["BeatData"], t) % (u * 4);
            if((b >= 0) && (b < u)) {
                r = 0.5 + 0.5 * (b - 0) / u;
            } else if((b >= u) && (b < u * 3)) {
                r = 1 - 1 * (b - u) / (u * 2);
            } else {
                r = 0 + 0.5 * (b - u * 3) / u;
            }
            if(c == 1) {
                r = 1 - r;
            }
            return r;
        };
        var aff = [];
        var a = [];
        var actionData = toActionDataNoOverlap(toActionDataTransOblique(toActionDataTransTrack(Data[index]["ActionData"], 4, 9)));
        var bpm = -1;
        for(var i = 0; i < Data[index]["BeatData"].length; i++) {
            if(Math.floor(Data[index]["BeatData"][i][1]) != Math.floor(bpm)) {
                bpm = Data[index]["BeatData"][i][1];
                a.push([Math.floor(Data[index]["BeatData"][i][0]), 0, Data[index]["BeatData"][i][1]]);
            }
        }
        for(var i = 0; i < actionData.length; i++) {
            actionData[i][0] = Math.floor(actionData[i][0]);
            actionData[i][3] = Math.floor(actionData[i][3]);
            switch(actionData[i][2]) {
                case 0x00:
                    a.push([actionData[i][0], 1, actionData[i][1] + 1]);
                    break;
                case 0x01:
                    var t = (actionData[i][1] + actionData[i][3]) / 4 - 0.5;
                    var c = (actionData[i][1] / 4 < 1 ? 0 : 1);
                    var y = toY(actionData[i][0], c);
                    a.push([actionData[i][0], 3, actionData[i][0] + 1, t, t, "s", y, y, c, "none", true, [actionData[i][0]]]);
                    break;
                case 0x02:
                    a.push([actionData[i][0], 2, actionData[i][1] + 1, actionData[i][0] + actionData[i][3]]);
                    break;
                case 0x61:
                case 0x62:
                    var c = 0;
                    while(i + 1 < actionData.length) {
                        if((actionData[i][2] == 0x61) || (actionData[i][2] == 0x62)) {
                            c = (actionData[i][1] / 4 < 1 ? 0 : 1);
                        }
                        if(((actionData[i][2] == 0x62) || (actionData[i][2] == 0x22)) && ((actionData[i + 1][2] == 0x21) || (actionData[i + 1][2] == 0xA1)) || (actionData[i][2] == 0xA2)) {
                            var t1 = actionData[i][1] / 4 - 0.5;
                            var t2 = t1;
                            if(((actionData[i][2] == 0x62) || (actionData[i][2] == 0x22)) && ((actionData[i + 1][2] == 0x21) || (actionData[i + 1][2] == 0xA1))) {
                                t2 = actionData[i + 1][1] / 4 - 0.5;
                            }
                            a.push([actionData[i][0], 3, actionData[i][0] + actionData[i][3], t1, t2, "s", toY(actionData[i][0], c), toY(actionData[i][0] + actionData[i][3], c), c, "none", false, []]);
                            if(actionData[i][2] == 0xA2) {
                                break;
                            }
                            i += 1;
                        } else if(((actionData[i][2] == 0x61) || (actionData[i][2] == 0x21)) && ((actionData[i + 1][2] == 0x22) || (actionData[i + 1][2] == 0xA2)) || (actionData[i][2] == 0xA1)) {
                            if(actionData[i][2] == 0xA1) {
                                break;
                            }
                            i += 1;
                        } else {
                            break;
                        }
                    }
                    break;
            }
        }
        a = a.sort(SortNumbers);
        for(var i = 0; i < a.length; i++) {
            if((a[i].length > 10) && (!a[i][10])) {
                for(var j = 0; j < a.length; j++) {
                    if((a[j].length > 10) && (!a[j][10])) {
                        if((a[j][8] == a[i][8]) && IsNumberClose(a[j][0], a[i][2]) && (a[j][3] == a[i][4]) && (a[j][6] == a[i][7])) {
                            if((IsNumberClose(a[j][2] - a[j][0], a[i][2] - a[i][0])) && (a[j][3] - a[j][4] == a[i][3] - a[i][4])) {
                                a[i][2] = a[j][2];
                                a[i][4] = a[j][4];
                                a[i][7] = a[j][7];
                                a.splice(j, 1);
                                j -= 1;
                            } else if(((a[j][3] < a[j][4]) && (a[i][3] > a[i][4])) || ((a[j][3] > a[j][4]) && (a[i][3] < a[i][4]))) {
                                a[i][5] = (a[i][5] == "s" ? "si" : a[i][5]);
                                a[i][5] = (a[i][5] == "so" ? "b" : a[i][5]);
                                a[j][5] = (a[j][5] == "s" ? "so" : a[j][5]);
                                a[j][5] = (a[j][5] == "si" ? "b" : a[j][5]);
                            }
                        }
                    }
                }
            }
        }
        aff.push("AudioOffset:0");
        aff.push("-");
        for(var i = 0; i < a.length; i++) {
            switch(a[i][1]) {
                case 0:
                    aff.push("timing(" + a[i][0] + "," + (Math.round(a[i][2] * 100) / 100).toFixed(2) + ",4.00);");
                    break;
                case 1:
                    aff.push("(" + a[i][0] + "," + a[i][2] + ");");
                    break;
                case 2:
                    aff.push("hold(" + a[i][0] + "," + a[i][3] + "," + a[i][2] + ");");
                    break;
                case 3:
                    var p = [];
                    if(a[i][10]) {
                        for(var j = 0; j < a[i][11].length; j++) {
                            p.push("arctap(" + a[i][11][j] + ")");
                        }
                    }
                    aff.push("arc(" + a[i][0] + "," + a[i][2] + "," + (Math.round(a[i][3] * 100) / 100).toFixed(2) + "," + (Math.round(a[i][4] * 100) / 100).toFixed(2) + "," + a[i][5] + "," + (Math.round(a[i][6] * 100) / 100).toFixed(2) + "," + (Math.round(a[i][7] * 100) / 100).toFixed(2) + "," + a[i][8] + "," + a[i][9] + "," + a[i][10] + ")" + (p.length != 0 ? "[" + p.join(",") + "]" : "") + ";");
                    break;
            }
        }
        return aff.join("\r\n");
    };
    var toPngText = function () {
        var options = (arguments.length > 0 ? arguments[0] : GetMstOption(false));
        var maxTime = (function () {
            var r = 0;
            for(var index = 0; index < Data.length; index++) {
                if(Data[index]["BeatData"][Data[index]["BeatData"].length - 1][0] > r) {
                    r = Data[index]["BeatData"][Data[index]["BeatData"].length - 1][0];
                }
            }
            return r;
        })();
        var cvs = [];
        var ctx = [];
        for(var index = 0; index < Data.length; index++) {
            var scaleX = parseFloat(options["selectMstScaleX"]);
            var scaleY = parseFloat(options["selectMstScaleY"]);
            var paddingTop = 128 * scaleX;
            var paddingBottom = 64 * scaleX;
            var paddingLeft = 64 * scaleX;
            var paddingRight = 64 * scaleX;
            var coreWidth = (Data[index]["Key"] == 0 ? 1 : Math.ceil(Data[index]["Key"] / 10)) * 128 * scaleX;
            var speedRate = Math.ceil(maxTime / (32767 - paddingTop - paddingBottom));
            var splitHeight = paddingTop + maxTime / speedRate + paddingBottom;
            if(scaleY != 0) {
                speedRate = 2400 / coreWidth / scaleX / scaleY;
            } else {
                scaleY = 2400 / coreWidth / scaleX / speedRate;
            }
            var coreHeight = maxTime / speedRate;
            var wholeWidth = paddingLeft + coreWidth + paddingRight;
            var wholeHeight = paddingTop + coreHeight + paddingBottom;
            var splitCount = Math.ceil(wholeHeight / splitHeight);
            var noteWidth = Math.round(Data[index]["Key"] < 7 ? coreWidth / Data[index]["Key"] * (105 - 5 * Data[index]["Key"]) / 100 : coreWidth / Data[index]["Key"] * 0.85);
            var noteHeight = Math.round(Data[index]["Key"] < 7 ? coreWidth / 16 : noteWidth / 2);
            var touchNoteStyle = "#0000FF";
            var holdNoteStyle = "#00FF00";
            var catchNoteStyle = "#0080FF";
            var clickNoteStyle = "#00FF80";
            var strokeWidth = noteHeight / 2;
            var arrowStyle = "#00FF00";
            var turnPointRadius = noteHeight / 3;
            var turnClickPointStyle = ["#CCFFE4", "#66FFB2", "#00FF80"];
            var turnHoldPointStyle = ["#00FF00", "#00FF00", "#00FF00"];
            var backgroundStyle = "#FFFFFF";
            var frameStokeWidth = 1 * scaleX;
            var frameStokeStyle = "#000000";
            var trackLineWidth = 0.24 * scaleX;
            var trackLineStyle = "#00FFFF";
            var beatLineWidth = 0.24 * scaleX;
            var beatLineStyle = "#000000";
            var startLineWidth = 0.96 * scaleX;
            var startLineStyle = "#0000FF";
            var determineLineWidth = 0.24 * scaleX;
            var determineLineStyle = "#FF0000";
            var determinePointRadius = noteHeight / 2;
            var determinePointStyle = ["#FFCCCC", "#FF6666", "#FF0000"];
            var determineTextStyle = "#FF00FF";
            var textStyle = "#000000";
            var textFont = "12px Arial";
            var boldTextFont = "14px 微软雅黑 bold";
            cvs[index] = [];
            ctx[index] = [];
            for(var i = 0; i < splitCount; i++) {
                cvs[index][i] = document.createElement("canvas");
                cvs[index][i].style.display = "none";
                cvs[index][i].width = wholeWidth;
                cvs[index][i].height = (i != splitCount - 1 ? splitHeight : (wholeHeight == splitHeight ? wholeHeight : wholeHeight % splitHeight));
                ctx[index][i] = cvs[index][i].getContext("2d");
            }
            var drawLine = function (x1, y1, x2, y2, w, s, t) {
                var i1;
                var i2;
                if(y1 > y2) {
                    i1 = Math.floor(y2 / splitHeight);
                    i2 = Math.floor(y1 / splitHeight);
                } else if(y1 < y2) {
                    i1 = Math.floor(y1 / splitHeight);
                    i2 = Math.floor(y2 / splitHeight);
                } else {
                    i1 = Math.floor((y1 - w / 2) / splitHeight);
                    i2 = Math.floor((y2 + w / 2) / splitHeight);
                }
                if(i1 < 0) {
                    i1 = 0;
                }
                if(i2 > splitCount - 1) {
                    i2 = splitCount - 1;
                }
                for(var i = i1; i < i2 + 1; i++) {
                    ctx[index][i].beginPath();
                    switch(t) {
                        case 1:
                            ctx[index][i].moveTo(Math.round(x1), Math.round(y1 - splitHeight * i));
                            ctx[index][i].lineTo(Math.round(x2), Math.round(y2 - splitHeight * i));
                            break;
                        case 2:
                        case 4:
                            var n = Math.ceil(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) / 40 * t);
                            var x = Math.min(x1, x2);
                            var xs = Math.abs(x1 - x2) / (n * 2 + 1);
                            var y = Math.min(y1, y2);
                            var ys = Math.abs(y1 - y2) / (n * 2 + 1);
                            for(var j = 0; j < n + 1; j++) {
                                ctx[index][i].moveTo(Math.round(x), Math.round(y - splitHeight * i));
                                x += xs;
                                y += ys;
                                ctx[index][i].lineTo(Math.round(x), Math.round(y - splitHeight * i));
                                x += xs;
                                y += ys;
                            }
                            break;
                    }
                    ctx[index][i].lineWidth = w;
                    ctx[index][i].strokeStyle = s;
                    ctx[index][i].stroke();
                }
            };
            var drawPoint = function (x, y, r, s) {
                var i1 = Math.floor((y - r) / splitHeight);
                var i2 = Math.floor((y + r) / splitHeight);
                if(i1 < 0) {
                    i1 = 0;
                }
                if(i2 > splitCount - 1) {
                    i2 = splitCount - 1;
                }
                for(var i = i1; i < i2 + 1; i++) {
                    ctx[index][i].beginPath();
                    ctx[index][i].arc(Math.round(x), Math.round(y - splitHeight * i), r, 0, Math.PI * 2);
                    var cs = ctx[index][i].createRadialGradient(Math.round(x), Math.round(y - splitHeight * i), r / 8, Math.round(x), Math.round(y - splitHeight * i), r);
                    for(var j = 0; j < s.length; j++) {
                        cs.addColorStop(j / s.length, s[j]);
                    }
                    ctx[index][i].fillStyle = cs;
                    ctx[index][i].fill();
                }
            };
            var drawText = function (x, y, t, s, f, a) {
                var n = GetFontSize(f) * scaleX;
                var i1 = Math.floor((y - n) / splitHeight);
                var i2 = Math.floor((y + n) / splitHeight);
                if(i1 < 0) {
                    i1 = 0;
                }
                if(i2 > splitCount - 1) {
                    i2 = splitCount - 1;
                }
                for(var i = i1; i < i2 + 1; i++) {
                    ctx[index][i].fillStyle = s;
                    ctx[index][i].font = SetFontSize(f, n);
                    ctx[index][i].textAlign = a;
                    ctx[index][i].fillText(t, Math.round(x), Math.round(y - splitHeight * i));
                }
            };
            var drawNote = function (a, x, y) {
                var s = (arguments.length > 3 ? arguments[3] : false);
                var l = (arguments.length > 4 ? arguments[4] : false);
                var i1 = Math.floor((y - noteHeight / 2) / splitHeight);
                var i2 = Math.floor((y + noteHeight / 2) / splitHeight);
                if(i1 < 0) {
                    i1 = 0;
                }
                if(i2 > splitCount - 1) {
                    i2 = splitCount - 1;
                }
                for(var i = i1; i < i2 + 1; i++) {
                    switch(a) {
                        case 0x00:
                            ctx[index][i].fillStyle = (l ? catchNoteStyle : touchNoteStyle);
                            break;
                        default:
                            ctx[index][i].fillStyle = (s ? clickNoteStyle : holdNoteStyle);
                            break;
                    }
                    ctx[index][i].fillRect(Math.round(x - noteWidth / 2), Math.round(y - splitHeight * i - noteHeight / 2), noteWidth, noteHeight);
                }
            };
            var drawStroke = function (x1, y1, x2, y2) {
                var s = (arguments.length > 4 ? arguments[4] : false);
                drawLine(Math.round(x1), Math.ceil(y1), Math.round(x2), Math.ceil(y2), strokeWidth, (s ? clickNoteStyle : holdNoteStyle), 1);
            };
            var drawArrow = function (t, x, y) {
                var i1 = Math.floor((y - strokeWidth) / splitHeight);
                var i2 = Math.floor((y + strokeWidth) / splitHeight);
                i1 = (i1 < 0 ? 0 : i1);
                i2 = (i2 > splitCount - 1 ? splitCount - 1 : i2);
                for(var i = i1; i < i2 + 1; i++) {
                    ctx[index][i].beginPath();
                    if(t < 0) {
                        ctx[index][i].moveTo(Math.round(x + strokeWidth / 2), Math.round(y - splitHeight * i + strokeWidth));
                        ctx[index][i].lineTo(Math.round(x + strokeWidth / 2), Math.round(y - splitHeight * i - strokeWidth));
                        ctx[index][i].lineTo(Math.round(x + strokeWidth / 2 - strokeWidth * 2), Math.round(y - splitHeight * i));
                    } else {
                        ctx[index][i].moveTo(Math.round(x - strokeWidth / 2), Math.round(y - splitHeight * i + strokeWidth));
                        ctx[index][i].lineTo(Math.round(x - strokeWidth / 2), Math.round(y - splitHeight * i - strokeWidth));
                        ctx[index][i].lineTo(Math.round(x - strokeWidth / 2 + strokeWidth * 2), Math.round(y - splitHeight * i));
                    }
                    ctx[index][i].closePath();
                    ctx[index][i].lineWidth = 1;
                    ctx[index][i].fillStyle = arrowStyle;
                    ctx[index][i].fill();
                }
            };
            var drawTurn = function (x, y) {
                var s = (arguments.length > 2 ? arguments[2] : false);
                drawPoint(x, y, turnPointRadius, (s ? turnClickPointStyle : turnHoldPointStyle));
            };
            var drawKey = function (action, timestamp, track, timespan) {
                var track2 = (arguments.length > 4 ? arguments[4] : track);
                var mkSingle = (arguments.length > 5 ? arguments[5] : false);
                var mkLong = (arguments.length > 6 ? arguments[6] : false);
                switch(action) {
                    case 0x00:
                        drawNote(action, coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, mkSingle, mkLong);
                        break;
                    case 0x01:
                        drawNote(action, coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, mkSingle, mkLong);
                        if(timespan < 0) {
                            drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) + strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5) - noteWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        } else if(timespan > 0) {
                            drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5) + noteWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) - strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        }
                        drawArrow(timespan, coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        break;
                    case 0x02:
                        drawNote(action, coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, mkSingle, mkLong);
                        drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - timespan / speedRate - paddingBottom);
                        drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5) - strokeWidth + paddingLeft, wholeHeight - timestamp / speedRate - timespan / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5) + strokeWidth + paddingLeft, wholeHeight - timestamp / speedRate - timespan / speedRate - paddingBottom, mkSingle);
                        break;
                    case 0x61:
                        drawNote(action, coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, mkSingle, mkLong);
                        if(timespan < 0) {
                            drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) - strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5) - noteWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        } else if(timespan > 0) {
                            drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5) + noteWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) + strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        }
                        drawTurn(coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        break;
                    case 0x62:
                        drawNote(action, coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, mkSingle, mkLong);
                        drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track2 + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - timespan / speedRate - paddingBottom);
                        break;
                    case 0x21:
                        if(timespan < 0) {
                            drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) - strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5) + strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        } else if(timespan > 0) {
                            drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5) - strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) + strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        }
                        drawTurn(coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        drawTurn(coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        break;
                    case 0x22:
                        drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track2 + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - timespan / speedRate - paddingBottom);
                        break;
                    case 0xA1:
                        if(timespan < 0) {
                            drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) + strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5) + strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        } else if(timespan > 0) {
                            drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5) - strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) - strokeWidth / 2 + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        }
                        drawTurn(coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        drawArrow(timespan, coreWidth / Data[index]["Key"] * (track + 0.5 + timespan) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom);
                        break;
                    case 0xA2:
                        drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5) + paddingLeft, wholeHeight - timestamp / speedRate - timespan / speedRate - paddingBottom);
                        drawStroke(coreWidth / Data[index]["Key"] * (track + 0.5) - strokeWidth + paddingLeft, wholeHeight - timestamp / speedRate - timespan / speedRate - paddingBottom, coreWidth / Data[index]["Key"] * (track + 0.5) + strokeWidth + paddingLeft, wholeHeight - timestamp / speedRate - timespan / speedRate - paddingBottom);
                        break;
                }
            };
            var ComboToScore = function (n) {
                var r = 0;
                if(n >= 100) {
                    r += (n - 100) * 600;
                    n = 100;
                }
                if(n >= 50) {
                    r += (n - 50) * 466;
                    n = 50;
                }
                if(n >= 20) {
                    r += (n - 20) * 332;
                    n = 20;
                }
                r += n * 200;
                return r;
            };
            if(options["inputDrawBackground"]) {
                for(var i = 0; i < splitCount; i++) {
                    ctx[index][i].fillStyle = backgroundStyle;
                    ctx[index][i].fillRect(0, 0, cvs[index][i].width, cvs[index][i].height);
                }
            }
            for(var i = 0; i < splitCount; i++) {
                ctx[index][i].globalAlpha = 0.618;
            }
            if(options["inputDrawTrack"]) {
                for(var i = 0; i < Data[index]["Key"] + 1; i++) {
                    drawLine(coreWidth / Data[index]["Key"] * i + paddingLeft, wholeHeight - paddingBottom, coreWidth / Data[index]["Key"] * i + paddingLeft, paddingTop, trackLineWidth, trackLineStyle, 1);
                    drawLine(coreWidth / Data[index]["Key"] * i + paddingLeft, wholeHeight - paddingBottom, coreWidth / Data[index]["Key"] * i + paddingLeft, paddingTop, trackLineWidth, trackLineStyle, 1);
                }
            }
            if(options["inputDrawFrame"]) {
                drawLine(0, 0, 0, wholeHeight, frameStokeWidth, frameStokeStyle, 1);
                drawLine(wholeWidth, 0, wholeWidth, wholeHeight, frameStokeWidth, frameStokeStyle, 1);
                drawLine(1, 0, wholeWidth - 1, 0, frameStokeWidth, frameStokeStyle, 1);
                drawLine(1, (wholeHeight % 2 != 0 ? wholeHeight - 1 : wholeHeight), wholeWidth - 1, (wholeHeight % 2 != 0 ? wholeHeight - 1 : wholeHeight), frameStokeWidth, frameStokeStyle, 1);
            }
            if(options["inputDrawBeat"]) {
                for(var i = 0; i < Data[index]["BeatLinesW"].length; i++) {
                    if((Data[index]["BeatLinesW"][i] != Data[index]["Start"]) && (Data[index]["BeatLinesW"][i] != Data[index]["End"])) {
                        drawLine(1, wholeHeight - (Data[index]["BeatLinesW"][i] - Data[index]["Start"]) / speedRate - beatLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (Data[index]["BeatLinesW"][i] - Data[index]["Start"]) / speedRate - beatLineWidth / 2 - paddingBottom, (i % 4 == 0 ? beatLineWidth * 2 : beatLineWidth), beatLineStyle, 1);
                        drawText(1, wholeHeight - 1 - (Data[index]["BeatLinesW"][i] - Data[index]["Start"]) / speedRate - beatLineWidth / 2 - paddingBottom, Math.round(Data[index]["BeatLinesW"][i]).toString(), beatLineStyle, textFont, "left");
                    }
                }
                for(var i = 0; i < Data[index]["BeatLinesH"].length; i++) {
                    drawLine(1, wholeHeight - (Data[index]["BeatLinesH"][i] - Data[index]["Start"]) / speedRate - beatLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (Data[index]["BeatLinesH"][i] - Data[index]["Start"]) / speedRate - beatLineWidth / 2 - paddingBottom, beatLineWidth, beatLineStyle, 2);
                    drawText(1, wholeHeight - 1 - (Data[index]["BeatLinesH"][i] - Data[index]["Start"]) / speedRate - beatLineWidth / 2 - paddingBottom, Math.round(Data[index]["BeatLinesH"][i]).toString(), beatLineStyle, textFont, "left");
                }
                for(var i = 0; i < Data[index]["BeatLinesQ"].length; i++) {
                    drawLine(1, wholeHeight - (Data[index]["BeatLinesQ"][i] - Data[index]["Start"]) / speedRate - beatLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (Data[index]["BeatLinesQ"][i] - Data[index]["Start"]) / speedRate - beatLineWidth / 2 - paddingBottom, beatLineWidth, beatLineStyle, 4);
                    drawText(1, wholeHeight - 1 - (Data[index]["BeatLinesQ"][i] - Data[index]["Start"]) / speedRate - beatLineWidth / 2 - paddingBottom, Math.round(Data[index]["BeatLinesQ"][i]).toString(), beatLineStyle, textFont, "left");
                }
                drawLine(1, wholeHeight - (Data[index]["Start"] - Data[index]["Start"]) / speedRate - startLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (Data[index]["Start"] - Data[index]["Start"]) / speedRate - startLineWidth / 2 - paddingBottom, beatLineWidth * 2, startLineStyle, 1);
                drawText(1, wholeHeight - 1 - (Data[index]["Start"] - Data[index]["Start"]) / speedRate - startLineWidth / 2 - paddingBottom, Data[index]["Start"].toString(), startLineStyle, textFont, "left");
                drawLine(1, wholeHeight - (Data[index]["End"] - Data[index]["Start"]) / speedRate - startLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (Data[index]["End"] - Data[index]["Start"]) / speedRate - startLineWidth / 2 - paddingBottom, beatLineWidth * 2, startLineStyle, 1);
                drawText(1, wholeHeight - 1 - (Data[index]["End"] - Data[index]["Start"]) / speedRate - startLineWidth / 2 - paddingBottom, Data[index]["End"].toString(), startLineStyle, textFont, "left");
            }
            for(var i = 0; i < Data[index]["ActionData"].length; i++) {
                var t = Data[index]["ActionData"][i][1];
                if(i != Data[index]["ActionData"].length - 1) {
                    switch(Data[index]["ActionData"][i][2]) {
                        case 0x62:
                        case 0x22:
                            switch(Data[index]["ActionData"][i + 1][2]) {
                                case 0x21:
                                case 0xA1:
                                    t = Data[index]["ActionData"][i + 1][1];
                                    break;
                            }
                            break;
                    }
                }
                drawKey(Data[index]["ActionData"][i][2], Data[index]["ActionData"][i][0] - Data[index]["Start"], Data[index]["ActionData"][i][1], Data[index]["ActionData"][i][3], t);
            }
            if(options["inputDrawDetermine"]) {
                for(var i = 0; i < Data[index]["DetermineLines"].length; i++) {
                    drawLine(1, wholeHeight - (Data[index]["DetermineLines"][i] - Data[index]["Start"]) / speedRate - determineLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (Data[index]["DetermineLines"][i] - Data[index]["Start"]) / speedRate - determineLineWidth / 2 - paddingBottom, determineLineWidth, determineLineStyle, 1);
                    drawText(wholeWidth - 1, wholeHeight - 1 - (Data[index]["DetermineLines"][i] - Data[index]["Start"]) / speedRate - determineLineWidth / 2 - paddingBottom, Math.round(Data[index]["DetermineLines"][i]).toString(), determineLineStyle, textFont, "right");
                }
            }
            if(options["inputDrawCombo"]) {
                for(var i = 0; i < Data[index]["ComboPoints"].length; i++) {
                    drawPoint(coreWidth / Data[index]["Key"] * (Data[index]["ComboPoints"][i][1] + 0.5) + paddingLeft, wholeHeight - (Data[index]["ComboPoints"][i][0] - Data[index]["Start"]) / speedRate - paddingBottom, determinePointRadius, determinePointStyle);
                    drawText(coreWidth / Data[index]["Key"] * (Data[index]["ComboPoints"][i][1] + 0.5) + paddingLeft, wholeHeight - (Data[index]["ComboPoints"][i][0] - Data[index]["Start"]) / speedRate - paddingBottom - noteHeight / 2, (i + 1).toString(), determineTextStyle, textFont, "center");
                }
            }
            drawText(wholeWidth / 2, 18 * scaleX, FileNameOriginal + (typeof Meta["IndexList"] === "undefined" ? "" : " (" + Meta["IndexList"][index] + ")"), textStyle, boldTextFont, "center");
            drawText(0 + paddingLeft / 1.3, 38 * scaleX, "时间：" + MillisecondToTime(Data[index]["Length"]).toString(), textStyle, textFont, "left");
            drawText(wholeWidth - coreWidth / 2 - paddingRight / 1.3, 38 * scaleX, "拍速：" + Data[index]["BPM"].toString(), textStyle, textFont, "left");
            drawText(0 + paddingLeft / 1.3, 55 * scaleX, "节拍：" + Data[index]["BeatData"].length.toString(), textStyle, textFont, "left");
            drawText(wholeWidth - coreWidth / 2 - paddingRight / 1.3, 55 * scaleX, "动作：" + Data[index]["ActionData"].length.toString(), textStyle, textFont, "left");
            drawText(0 + paddingLeft / 1.3, 72 * scaleX, "音符：" + Data[index]["Combo"].toString(), textStyle, textFont, "left");
            drawText(wholeWidth - coreWidth / 2 - paddingRight / 1.3, 72 * scaleX, "满分：" + ComboToScore(Data[index]["Combo"]).toString(), textStyle, textFont, "left");
            drawText(0 + paddingLeft / 1.3, 89 * scaleX, "键位：" + Data[index]["Key"].toString(), textStyle, textFont, "left");
            drawText(wholeWidth - coreWidth / 2 - paddingRight / 1.3, 89 * scaleX, "速率：" + scaleY.toFixed(3).toString(), textStyle, textFont, "left");
            drawText(0 + paddingLeft / 1.3, 106 * scaleX, "等级：" + Data[index]["Rank"].toFixed(3).toString(), textStyle, textFont, "left");
            drawText(wholeWidth - coreWidth / 2 - paddingRight / 1.3, 106 * scaleX, "难度：" + Data[index]["Difficulty"].toFixed(3).toString(), textStyle, textFont, "left");
            drawText(wholeWidth / 2, wholeHeight - 48 * scaleX, "Draw by rmstZ.html", textStyle, textFont, "center");
            drawText(wholeWidth / 2, wholeHeight - 30 * scaleX, "at " + new Date().format(), textStyle, textFont, "center");
            drawText(wholeWidth / 2, wholeHeight - 10 * scaleX, "Copyright ? 心のsky Group", textStyle, boldTextFont, "center");
        }
        cvs = (function () {
            var maxWidth = (function () {
                var r = 0;
                for(var index = 0; index < cvs.length; index++) {
                    r += cvs[index][0].width;
                }
                return r;
            })();
            var canvas = [];
            var context = [];
            for(var i = 0; i < cvs[0].length; i++) {
                canvas[i] = document.createElement("canvas");
                canvas[i].style.display = "none";
                canvas[i].width = maxWidth;
                canvas[i].height = cvs[0][i].height;
                context[i] = canvas[i].getContext("2d");
            }
            var w = 0;
            for(var index = 0; index < cvs.length; index++) {
                for(var i = 0; i < cvs[index].length; i++) {
                    context[i].drawImage(cvs[index][i], w, 0);
                }
                w += cvs[index][0].width;
            }
            return canvas;
        })();
        var png = [];
        for(var i = 0; i < splitCount; i++) {
            png[i] = cvs[i].toDataURL("image/png");
        }
        return png.join("|");
    };
    var toHtmlText = function () {
        var png = toPngText(GetMstOption(true)).split("|");
        var html = [];
        html.push("<html><head><meta name=" + '"' + "viewport" + '"' + "content=" + '"' + "width=device-width,initial-scale=1,user-scalable=yes" + '"' + "/><title>" + FileName + FileSuffixImd + "." + FileExtension + "</title><style type=" + '"' + "text/css" + '"' + ">div{text-align:center;}</style></head><body>");
        for(var i = 0; i < png.length; i++) {
            html.push("<div><img src=" + '"' + png[i] + '"' + "/></div>");
        }
        html.push("</body></html>");
        return html.join("");
    };
    this.FromBuffer = function (buffer, ext) {
        var type = (arguments.length > 2 ? arguments[2] : "");
        if(buffer.length == 0) {
            return;
        }
        reset();
        switch(ext) {
            case"mst":
                fromMstBuffer(buffer);
                break;
            case"bms":
                fromBmsBuffer(buffer);
                break;
            case"bme":
                fromBmeBuffer(buffer);
                break;
            case"pms":
                fromPmsBuffer(buffer);
                break;
            case"vos":
                fromVosBuffer(buffer);
                break;
            case"hex":
                fromHexBuffer(buffer, type);
                break;
            case"tja":
                fromTjaBuffer(buffer);
                break;
            case"osu":
                fromOsuBuffer(buffer);
                break;
            case"xml":
                fromXmlBuffer(buffer, type);
                break;
            case"vox":
                fromVoxBuffer(buffer);
                break;
            case"ksh":
                fromKshBuffer(buffer);
                break;
            case"imd":
                fromImdBuffer([buffer]);
                break;
            case"mde":
                fromMdeBuffer(buffer);
                break;
            case"mc":
                fromMcBuffer(buffer);
                break;
            case"aff":
                fromAffBuffer(buffer);
                break;
        }
    };
    this.ToBuffer = function (ext) {
        var type = (arguments.length > 1 ? arguments[1] : "");
        switch(ext) {
            case"mst":
                if(typeof Buffer["Mst"][Index] === "undefined") {
                    Buffer["Mst"][Index] = toMstBuffer(Index);
                }
                return Buffer["Mst"][Index];
                break;
            case"bms":
                if(typeof Buffer["Bms"][Index] === "undefined") {
                    Buffer["Bms"][Index] = toBmsBuffer(Index);
                }
                return Buffer["Bms"][Index];
                break;
            case"bme":
                if(typeof Buffer["Bme"][Index] === "undefined") {
                    Buffer["Bme"][Index] = toBmeBuffer(Index);
                }
                return Buffer["Bme"][Index];
                break;
            case"pms":
                if(typeof Buffer["Pms"][Index] === "undefined") {
                    Buffer["Pms"][Index] = toPmsBuffer(Index);
                }
                return Buffer["Pms"][Index];
                break;
            case"vos":
                switch(type) {
                    case"vos000":
                        if(typeof Buffer["Vosvos000"] === "undefined") {
                            Buffer["Vosvos000"] = toVosBuffer(type);
                        }
                        return Buffer["Vosvos000"];
                        break;
                    case"vos001":
                        if(typeof Buffer["Vosvos001"] === "undefined") {
                            Buffer["Vosvos001"] = toVosBuffer(type);
                        }
                        return Buffer["Vosvos001"];
                        break;
                    case"vos006":
                        if(typeof Buffer["Vosvos006"] === "undefined") {
                            Buffer["Vosvos006"] = toVosBuffer(type);
                        }
                        return Buffer["Vosvos006"];
                        break;
                    case"vos022":
                        if(typeof Buffer["Vosvos022"] === "undefined") {
                            Buffer["Vosvos022"] = toVosBuffer(type);
                        }
                        return Buffer["Vosvos022"];
                        break;
                }
                break;
            case"mid":
                if(typeof Buffer["Mid"] === "undefined") {
                    Buffer["Mid"] = toMidBuffer();
                }
                return Buffer["Mid"];
                break;
            case"lrc":
                if(typeof Buffer["Lrc"] === "undefined") {
                    Buffer["Lrc"] = toLrcBuffer();
                }
                return toLrcBuffer();
                break;
            case"hex":
                switch(type) {
                    case"vos000":
                        if(typeof Buffer["Hexvosvos000"] === "undefined") {
                            Buffer["Hexvosvos000"] = toHexBuffer(Index, type);
                        }
                        return Buffer["Hexvos000"];
                        break;
                    case"vos001":
                        if(typeof Buffer["Hexvosvos001"] === "undefined") {
                            Buffer["Hexvosvos001"] = toHexBuffer(Index, type);
                        }
                        return Buffer["Hexvos001"];
                        break;
                    case"vos006":
                        if(typeof Buffer["Hexvosvos006"] === "undefined") {
                            Buffer["Hexvosvos006"] = toHexBuffer(Index, type);
                        }
                        return Buffer["Hexvos006"];
                        break;
                    case"vos022":
                        if(typeof Buffer["Hexvosvos022"] === "undefined") {
                            Buffer["Hexvosvos022"] = toHexBuffer(Index, type);
                        }
                        return Buffer["Hexvos022"];
                        break;
                    case"imd":
                        if(typeof Buffer["Heximd"][Index] === "undefined") {
                            Buffer["Heximd"][Index] = toHexBuffer(Index, "imd");
                        }
                        return Buffer["Heximd"][Index];
                        break;
                }
                break;
            case"tja":
                switch(type) {
                    case"taiko":
                        if(typeof Buffer["Tjataiko"] === "undefined") {
                            Buffer["Tjataiko"] = toTjaBuffer("taiko");
                        }
                        return Buffer["Tjataiko"];
                        break;
                    case"jube":
                        if(typeof Buffer["Tjajube"] === "undefined") {
                            Buffer["Tjajube"] = toTjaBuffer("jube");
                        }
                        return Buffer["Tjajube"];
                        break;
                }
                break;
            case"osu":
                switch(type) {
                    case"osu":
                        if(typeof Buffer["Osuosu"][Index] === "undefined") {
                            Buffer["Osuosu"][Index] = toOsuBuffer(Index, "osu");
                        }
                        return Buffer["Osuosu"][Index];
                        break;
                    case"taiko":
                        if(typeof Buffer["Osutaiko"][Index] === "undefined") {
                            Buffer["Osutaiko"][Index] = toOsuBuffer(Index, "taiko");
                        }
                        return Buffer["Osutaiko"][Index];
                        break;
                    case"ctb":
                        if(typeof Buffer["Osuctb"][Index] === "undefined") {
                            Buffer["Osuctb"][Index] = toOsuBuffer(Index, "ctb");
                        }
                        return Buffer["Osuctb"][Index];
                        break;
                    case"mania":
                        if(typeof Buffer["Osumania"][Index] === "undefined") {
                            Buffer["Osumania"][Index] = toOsuBuffer(Index, "mania");
                        }
                        return Buffer["Osumania"][Index];
                        break;
                }
                break;
            case"xml":
                switch(type) {
                    case"yddr":
                        if(typeof Buffer["Xmlyddr"][Index] === "undefined") {
                            Buffer["Xmlyddr"][Index] = toXmlBuffer(Index, "yddr");
                        }
                        return Buffer["Xmlyddr"][Index];
                        break;
                    case"ydsd":
                        if(typeof Buffer["Xmlydsd"][Index] === "undefined") {
                            Buffer["Xmlydsd"][Index] = toXmlBuffer(Index, "ydsd");
                        }
                        return Buffer["Xmlydsd"][Index];
                        break;
                    case"mde":
                        if(typeof Buffer["Xmlmde"][Index] === "undefined") {
                            Buffer["Xmlmde"][Index] = readMeta(["BufferXmlmde"]);
                        }
                        if(typeof Buffer["Xmlmde"][Index] === "undefined") {
                            Buffer["Xmlmde"][Index] = toXmlBuffer(Index, "mde");
                        }
                        return Buffer["Xmlmde"][Index];
                        break;
                }
                break;
            case"vox":
                if(typeof Buffer["Vox"][Index] === "undefined") {
                    Buffer["Vox"][Index] = toVoxBuffer(Index);
                }
                return Buffer["Vox"][Index];
                break;
            case"ksh":
                if(typeof Buffer["Ksh"][Index] === "undefined") {
                    Buffer["Ksh"][Index] = toKshBuffer(Index);
                }
                return Buffer["Ksh"][Index];
                break;
            case"imd":
                if(typeof Buffer["Imd"][Index] === "undefined") {
                    Buffer["Imd"][Index] = toImdBuffer(Index);
                }
                return Buffer["Imd"][Index];
                break;
            case"mde":
                if(typeof Buffer["Mde"][Index] === "undefined") {
                    Buffer["Mde"][Index] = toMdeBuffer(Index);
                }
                return Buffer["Mde"][Index];
                break;
            case"mc":
                switch(type) {
                    case"key":
                        if(typeof Buffer["Mckey"][Index] === "undefined") {
                            Buffer["Mckey"][Index] = toMcBuffer(Index, "key");
                        }
                        return Buffer["Mckey"][Index];
                        break;
                    case"step":
                        if(typeof Buffer["Mcstep"][Index] === "undefined") {
                            Buffer["Mcstep"][Index] = toMcBuffer(Index, "step");
                        }
                        return Buffer["Mcstep"][Index];
                        break;
                    case"dj":
                        if(typeof Buffer["Mcdj"][Index] === "undefined") {
                            Buffer["Mcdj"][Index] = toMcBuffer(Index, "dj");
                        }
                        return Buffer["Mcdj"][Index];
                        break;
                    case"catch":
                        if(typeof Buffer["Mccatch"][Index] === "undefined") {
                            Buffer["Mccatch"][Index] = toMcBuffer(Index, "catch");
                        }
                        return Buffer["Mccatch"][Index];
                        break;
                    case"pad":
                        if(typeof Buffer["Mcpad"][Index] === "undefined") {
                            Buffer["Mcpad"][Index] = toMcBuffer(Index, "pad");
                        }
                        return Buffer["Mcpad"][Index];
                        break;
                    case"taiko":
                        if(typeof Buffer["Mctaiko"][Index] === "undefined") {
                            Buffer["Mctaiko"][Index] = toMcBuffer(Index, "taiko");
                        }
                        return Buffer["Mctaiko"][Index];
                        break;
                }
                break;
            case"aff":
                if(typeof Buffer["Aff"][Index] === "undefined") {
                    Buffer["Aff"][Index] = toAffBuffer(Index);
                }
                return Buffer["Aff"][Index];
                break;
        }
    };
    this.ToText = function (ext) {
        var type = (arguments.length > 1 ? arguments[1] : "");
        switch(ext) {
            case"bms":
                if(typeof Text["Bms"][Index] === "undefined") {
                    Text["Bms"][Index] = toBmsText(Index);
                }
                return Text["Bms"][Index];
                break;
            case"bme":
                if(typeof Text["Bme"][Index] === "undefined") {
                    Text["Bme"][Index] = toBmeText(Index);
                }
                return Text["Bme"][Index];
                break;
            case"pms":
                if(typeof Text["Pms"][Index] === "undefined") {
                    Text["Pms"][Index] = toPmsText(Index);
                }
                return Text["Pms"][Index];
                break;
            case"vos":
                switch(type) {
                    case"vos000":
                        if(typeof Text["Vosvos000"] === "undefined") {
                            Text["Vosvos000"] = toVosText(type);
                        }
                        return Text["Vosvos000"];
                        break;
                    case"vos001":
                        if(typeof Text["Vosvos001"] === "undefined") {
                            Text["Vosvos001"] = toVosText(type);
                        }
                        return Text["Vosvos001"];
                        break;
                    case"vos006":
                        if(typeof Text["Vosvos006"] === "undefined") {
                            Text["Vosvos006"] = toVosText(type);
                        }
                        return Text["Vosvos006"];
                        break;
                    case"vos022":
                        if(typeof Text["Vosvos022"] === "undefined") {
                            Text["Vosvos022"] = toVosText(type);
                        }
                        return Text["Vosvos022"];
                        break;
                }
                break;
            case"mid":
                if(typeof Text["Mid"] === "undefined") {
                    Text["Mid"] = toMidText();
                }
                return Text["Mid"];
                break;
            case"lrc":
                if(typeof Text["Lrc"] === "undefined") {
                    Text["Lrc"] = toLrcText();
                }
                return Text["Lrc"];
                break;
            case"hex":
                switch(type) {
                    case"vos000":
                        if(typeof Text["Hexvosvos000"] === "undefined") {
                            Text["Hexvosvos000"] = toHexText(Index, type);
                        }
                        return Text["Hexvosvos000"];
                        break;
                    case"vos001":
                        if(typeof Text["Hexvosvos001"] === "undefined") {
                            Text["Hexvosvos001"] = toHexText(Index, type);
                        }
                        return Text["Hexvosvos001"];
                        break;
                    case"vos006":
                        if(typeof Text["Hexvosvos006"] === "undefined") {
                            Text["Hexvosvos006"] = toHexText(Index, type);
                        }
                        return Text["Hexvosvos006"];
                        break;
                    case"vos022":
                        if(typeof Text["Hexvosvos022"] === "undefined") {
                            Text["Hexvosvos022"] = toHexText(Index, type);
                        }
                        return Text["Hexvosvos022"];
                        break;
                    case"imd":
                        if(typeof Text["Heximd"][Index] === "undefined") {
                            Text["Heximd"][Index] = toHexText(Index, "imd");
                        }
                        return Text["Heximd"][Index];
                        break;
                }
                break;
            case"tja":
                switch(type) {
                    case"taiko":
                        if(typeof Text["Tjataiko"] === "undefined") {
                            Text["Tjataiko"] = toTjaText("taiko");
                        }
                        return Text["Tjataiko"];
                        break;
                    case"jube":
                        if(typeof Text["Tjajube"] === "undefined") {
                            Text["Tjajube"] = toTjaText("jube");
                        }
                        return Text["Tjajube"];
                        break;
                }
                break;
            case"osu":
                switch(type) {
                    case"osu":
                        if(typeof Text["Osuosu"][Index] === "undefined") {
                            Text["Osuosu"][Index] = toOsuText(Index, "osu");
                        }
                        return Text["Osuosu"][Index];
                        break;
                    case"taiko":
                        if(typeof Text["Osutaiko"][Index] === "undefined") {
                            Text["Osutaiko"][Index] = toOsuText(Index, "taiko");
                        }
                        return Text["Osutaiko"][Index];
                        break;
                    case"ctb":
                        if(typeof Text["Osuctb"][Index] === "undefined") {
                            Text["Osuctb"][Index] = toOsuText(Index, "ctb");
                        }
                        return Text["Osuctb"][Index];
                        break;
                    case"mania":
                        if(typeof Text["Osumania"][Index] === "undefined") {
                            Text["Osumania"][Index] = toOsuText(Index, "mania");
                        }
                        return Text["Osumania"][Index];
                        break;
                }
                break;
            case"xml":
                switch(type) {
                    case"yddr":
                        if(typeof Text["Xmlyddr"][Index] === "undefined") {
                            Text["Xmlyddr"][Index] = toXmlText(Index, "yddr");
                        }
                        return Text["Xmlyddr"][Index];
                        break;
                    case"ydsd":
                        if(typeof Text["Xmlydsd"][Index] === "undefined") {
                            Text["Xmlydsd"][Index] = toXmlText(Index, "ydsd");
                        }
                        return Text["Xmlydsd"][Index];
                        break;
                    case"mde":
                        if(typeof Text["Xmlmde"][Index] === "undefined") {
                            Text["Xmlmde"][Index] = toXmlText(Index, "mde");
                        }
                        return Text["Xmlmde"][Index];
                        break;
                }
                break;
            case"vox":
                if(typeof Text["Vox"][Index] === "undefined") {
                    Text["Vox"][Index] = toVoxText(Index);
                }
                return Text["Vox"][Index];
                break;
            case"ksh":
                if(typeof Text["Ksh"][Index] === "undefined") {
                    Text["Ksh"][Index] = toKshText(Index);
                }
                return Text["Ksh"][Index];
                break;
            case"imd":
                if(typeof Text["Imd"][Index] === "undefined") {
                    Text["Imd"][Index] = toImdText(Index);
                }
                return Text["Imd"][Index];
                break;
            case"mde":
                if(typeof Text["Mde"][Index] === "undefined") {
                    Text["Mde"][Index] = toMdeText(Index);
                }
                return Text["Mde"][Index];
                break;
            case"mc":
                switch(type) {
                    case"key":
                        if(typeof Text["Mckey"][Index] === "undefined") {
                            Text["Mckey"][Index] = toMcText(Index, "key");
                        }
                        return Text["Mckey"][Index];
                        break;
                    case"step":
                        if(typeof Text["Mcstep"][Index] === "undefined") {
                            Text["Mcstep"][Index] = toMcText(Index, "step");
                        }
                        return Text["Mcstep"][Index];
                        break;
                    case"dj":
                        if(typeof Text["Mcdj"][Index] === "undefined") {
                            Text["Mcdj"][Index] = toMcText(Index, "dj");
                        }
                        return Text["Mcdj"][Index];
                        break;
                    case"catch":
                        if(typeof Text["Mccatch"][Index] === "undefined") {
                            Text["Mccatch"][Index] = toMcText(Index, "catch");
                        }
                        return Text["Mccatch"][Index];
                        break;
                    case"pad":
                        if(typeof Text["Mcpad"][Index] === "undefined") {
                            Text["Mcpad"][Index] = toMcText(Index, "pad");
                        }
                        return Text["Mcpad"][Index];
                        break;
                    case"taiko":
                        if(typeof Text["Mctaiko"][Index] === "undefined") {
                            Text["Mctaiko"][Index] = toMcText(Index, "taiko");
                        }
                        return Text["Mctaiko"][Index];
                        break;
                }
                break;
            case"aff":
                if(typeof Text["Aff"][Index] === "undefined") {
                    Text["Aff"][Index] = toAffText(Index);
                }
                return Text["Aff"][Index];
                break;
            case"png":
                if(typeof Text["Png"] === "undefined") {
                    Text["Png"] = toPngText();
                }
                return Text["Png"];
                break;
            case"html":
                if(typeof Text["Html"] === "undefined") {
                    Text["Html"] = toHtmlText();
                }
                return Text["Html"];
                break;
        }
    };
    this.IsValid = function () {
        return isValid;
    };
    this.IsExist = function (ext) {
        switch(ext) {
            case"vos":
                if(typeof Buffer["Vosvos000"] === "undefined") {
                    Buffer["Vosvos000"] = toVosBuffer();
                }
                if(typeof Buffer["Vosvos000"] !== "undefined") {
                    return true;
                }
                ;
                if(typeof Buffer["Vosvos001"] === "undefined") {
                    Buffer["Vosvos001"] = toVosBuffer();
                }
                if(typeof Buffer["Vosvos001"] !== "undefined") {
                    return true;
                }
                ;
                if(typeof Buffer["Vosvos006"] === "undefined") {
                    Buffer["Vosvos006"] = toVosBuffer();
                }
                if(typeof Buffer["Vosvos006"] !== "undefined") {
                    return true;
                }
                ;
                if(typeof Buffer["Vosvos022"] === "undefined") {
                    Buffer["Vosvos022"] = toVosBuffer();
                }
                if(typeof Buffer["Vosvos022"] !== "undefined") {
                    return true;
                }
                ;
                return false;
                break;
            case"mid":
                if(typeof Buffer["Mid"] === "undefined") {
                    Buffer["Mid"] = toMidBuffer();
                }
                return typeof Buffer["Mid"] !== "undefined";
            case"lrc":
                if(typeof Buffer["Lrc"] === "undefined") {
                    Buffer["Lrc"] = toLrcBuffer();
                }
                return typeof Buffer["Lrc"] !== "undefined";
                break;
        }
    };
    this.Choose = function (name) {
        if(typeof Meta["IndexList"] !== "undefined") {
            for(var i = 0; i < Meta["IndexList"].length; i++) {
                if(Meta["IndexList"][i] == name) {
                    Index = i;
                    break;
                }
            }
        }
    };
    this.ReDraw = function () {
        Text["Png"] = undefined;
        Text["Html"] = undefined;
    };
    this.FromBuffer(buffer, ext, type);
}
function Bin(buffer, ext) {
    var Buffer = {};
    var Text = {};
    var isValid = false;
    var reset = function () {
        Buffer["Bin"] = undefined;
        Buffer["Hex"] = undefined;
        Buffer["Xml"] = undefined;
        Buffer["List"] = undefined;
        Buffer["Bat"] = undefined;
        Text["Bin"] = undefined;
        Text["Hex"] = undefined;
        Text["Xml"] = undefined;
        Text["Html"] = undefined;
        Text["List"] = undefined;
        Text["Bat"] = undefined;
        isValid = false;
    };
    var getBinName = function (u) {
        var r = [];
        var BinUnit = [
            ["errno_main_client", 564],
            ["mrock.active_client", 35],
            ["mrock.character_client", 644],
            ["mrock.characterproperty_client", 327],
            ["mrock.floornode_client", 16],
            ["mrock.floorreward_client", 186],
            ["mrock.innerpublicnotify_client", 928],
            ["mrock.innerrace_client", 616],
            ["mrock.luckyturntablereward_client", 18],
            ["mrock.luckyturntabletypeshow_client", 137],
            ["mrock.marketact_client", 152],
            ["mrock.mission_client", 414],
            ["mrock.scorebuy_client", 22],
            ["mrock.scoreexchange_client", 21],
            ["mrock.surviveact_client", 263],
            ["mrock.thingbuy_client", 31],
            ["mrock.timelimitcharacter_client", 284],
            ["mrock_buynew_client", 295],
            ["mrock_dailyactive_client", 105],
            ["mrock_guild_song_client", 28],
            ["mrock_guildactivity_client", 332],
            ["mrock_Map_client", 57],
            ["mrock_match_client", 4156],
            ["mrock_match_division_client", 260],
            ["mrock_medal_client", 92],
            ["mrock_noble_client", 3993],
            ["mrock_noble_gift_client", 304],
            ["mrock_papasong_client", 361],
            ["mrock_questconfig_client", 640],
            ["errno_main_client", 564],
            ["mrock_recharge_ios_client", 406],
            ["mrock_recharge_android_client", 406],
            ["mrock_room_client", 24],
            ["mrock_scoreexchange_client", 21],
            ["mrock_share_client", 268],
            ["mrock_song_client", 830],
            ["mrock_song_client_android", 830],
            ["mrock_songlevel_client", 830],
            ["mrock_SongPkg_client", 808],
            ["mrock_starmall_exchange_client", 93],
            ["mrock_SysParam_client", 8],
            ["mrock_txt_client", 260]
        ];
        for(var i = 0; i < BinUnit.length; i++) {
            if(BinUnit[i][1] == u) {
                r.push(BinUnit[i][0]);
            }
        }
        return r;
    };
    var getBinStructure = function () {
        var r = [];
        switch(FileName) {
            case"errno_main_client":
                r.push([0, "m_szName", 48]);
                r.push([0, "m_uiValue", 4]);
                r.push([0, "m_szDescriptionZH", 256]);
                r.push([0, "m_szDescriptionEN", 256]);
                r = ["ErrnoConfig_Client_Tab", "ErrnoConfig_Client", r];
                break;
            case"mrock.active_client":
                r.push([0, "m_ushActiveID", 2]);
                r.push([0, "m_ucActiveType", 1]);
                r.push([0, "m_iNumber", 4]);
                r.push([0, "m_ucRewardNumber", 1]);
                r.push([1, "m_astReward,ActiveReward", 3]);
                r = ["ActiveConfig_Client_Tab", "ActiveConfig_Client", r];
                break;
            case"mrock.character_client":
                r.push([0, "m_usID", 2]);
                r.push([0, "m_usIconID", 2]);
                r.push([0, "m_szDesc", 256]);
                r.push([0, "m_szNoteDesc", 256]);
                r.push([0, "m_szNoteStyle1", 32]);
                r.push([0, "m_szNoteStyle2", 32]);
                r.push([0, "m_szNoteStyle3", 32]);
                r.push([0, "m_szGameSpeaking", 32]);
                r = ["CharacterConfig_Client_Tab", "CharacterConfig_Client", r];
                break;
            case"mrock.characterproperty_client":
                r.push([0, "m_usID", 2]);
                r.push([0, "m_ucPropertyID", 1]);
                r.push([0, "m_ucType", 1]);
                r.push([0, "m_usIconID", 2]);
                r.push([0, "m_ucEffectType", 1]);
                r.push([0, "m_szName", 32]);
                r.push([0, "m_szEffect", 32]);
                r.push([0, "m_szDesc", 256]);
                r = ["CharacterPropertyConfig_Client_Tab", "CharacterPropertyConfig_Client", r];
                break;
            case"mrock.floornode_client":
                r.push([0, "m_iNodeID", 4]);
                r.push([0, "m_iNodeType", 4]);
                r.push([0, "m_iNodeValue", 4]);
                r.push([0, "m_iPlayStyle", 4]);
                r = ["FloorNodeConfig_Client_Tab", "FloorNodeConfig_Client", r];
                break;
            case"mrock.floorreward_client":
                r.push([0, "m_iRewardID", 4]);
                r.push([0, "m_ucRewardType", 1]);
                r.push([0, "m_iRewardValue", 4]);
                r = ["FloorRewardConfig_Client_Tab", "FloorRewardConfig_Client", r];
                break;
            case"mrock.innerpublicnotify_client":
                r.push([0, "m_iID", 4]);
                r.push([0, "m_iLevel", 4]);
                r.push([0, "m_iLinkType", 4]);
                r.push([0, "m_iContentType", 4]);
                r.push([0, "m_szTitle", 300]);
                r.push([0, "m_szContent", 300]);
                r.push([0, "m_szUrl", 300]);
                r.push([0, "m_iForce", 4]);
                r.push([0, "m_iPhonetype", 4]);
                r.push([0, "m_iLoginType", 4]);
                r = ["InnerPublicNotifyConfig_Client_Tab", "InnerPublicNotifyConfig_Client", r];
                break;
            case"mrock.innerrace_client":
                r.push([0, "m_iMatchID", 4]);
                r.push([0, "m_iLevel", 4]);
                r.push([0, "m_iContentType", 4]);
                r.push([0, "m_szTitle", 300]);
                r.push([0, "m_szContent", 300]);
                r.push([0, "m_iPhonetype", 4]);
                r = ["InnerRaceConfig_Client_Tab", "InnerRaceConfig_Client", r];
                break;
            case"mrock.luckyturntablereward_client":
                break;
            case"mrock.luckyturntabletypeshow_client":
                break;
            case"mrock.marketact_client":
                r.push([0, "m_ushMarketActID", 2]);
                r.push([0, "m_szDescription", 128]);
                r.push([0, "m_ucRewardNumber", 1]);
                r.push([1, "m_astReward,MarketActReward", 3]);
                r = ["MarketActConfig_Client_Tab", "MarketActConfig_Client", r];
                break;
            case"mrock.mission_client":
                r.push([0, "m_iMissionID", 4]);
                r.push([0, "m_szTitle", 128]);
                r.push([0, "m_iIconID", 4]);
                r.push([0, "m_szDiffDesc", 64]);
                r.push([0, "m_szDiffColor", 64]);
                r.push([0, "m_szBeginTime", 64]);
                r.push([0, "m_szEndTime", 64]);
                r.push([0, "m_iStartFloor", 4]);
                r.push([0, "m_iEndFloor", 4]);
                r.push([0, "m_ucResetable", 4]);
                r.push([0, "m_iResetCost", 4]);
                r.push([0, "m_ucRewardType", 1]);
                r.push([0, "m_ucIsNew", 1]);
                r.push([0, "m_iSeqID", 4]);
                r = ["MissionConfig_Client_Tab", "MissionConfig_Client", r];
                break;
            case"mrock.scorebuy_client":
                r.push([0, "m_iBuyID", 4]);
                r.push([0, "m_ucItemType", 1]);
                r.push([0, "m_iItemID", 4]);
                r.push([0, "m_iItemValue", 4]);
                r.push([0, "m_iScorePresent", 4]);
                r.push([0, "m_ucBuyType", 1]);
                r.push([0, "m_iCostNum", 4]);
                r = ["ScoreBuyConfig_Client_Tab", "ScoreBuyConfig_Client", r];
                break;
            case"mrock.scoreexchange_client":
            case"mrock_scoreexchange_client":
                r.push([0, "m_iExchangeID", 4]);
                r.push([0, "m_ucItemType", 1]);
                r.push([0, "m_iItemID", 4]);
                r.push([0, "m_iItemValue", 4]);
                r.push([0, "m_iScoreCost", 4]);
                r.push([0, "m_iVersion", 4]);
                r = ["ScoreExchangeConfig_Client_Tab", "ScoreExchangeConfig_Client", r];
                break;
            case"mrock.surviveact_client":
                r.push([0, "m_iID", 4]);
                r.push([0, "m_ushSongID", 2]);
                r.push([0, "m_ucNoteType", 1]);
                r.push([0, "m_szOpenTimeDesc", 256]);
                r = ["SurviveActConfig_Client_Tab", "SurviveActConfig_Client", r];
                break;
            case"mrock.thingbuy_client":
                break;
            case"mrock.timelimitcharacter_client":
                r.push([0, "m_ushID", 2]);
                r.push([0, "m_ushCharacterID", 2]);
                r.push([0, "m_ushInitLevel", 2]);
                r.push([0, "m_szName", 22]);
                r.push([0, "m_szDesc", 256]);
                r = ["TimeLimitCharacterConfig_Client_Tab", "TimeLimitCharacterConfig_Client", r];
                break;
            case"mrock_buynew_client":
                r.push([0, "m_ushID", 2]);
                r.push([0, "m_ucType", 1]);
                r.push([0, "m_iThingID", 4]);
                r.push([0, "m_szItemName", 128]);
                r.push([0, "m_iThingNumber", 4]);
                r.push([0, "m_iIOSMoney", 4]);
                r.push([0, "m_iAndroidMoney", 4]);
                r.push([0, "m_iDiamonds", 4]);
                r.push([0, "m_szDescriptionName", 128]);
                r.push([0, "m_iIconID", 4]);
                r.push([0, "m_iIsopen", 4]);
                r.push([0, "m_iSequence", 4]);
                r.push([0, "m_iIsone", 4]);
                r = ["BuyConfig_Client_Tab", "BuyConfig_Client", r];
                break;
            case"mrock_dailyactive_client":
                break;
            case"mrock_guild_song_client":
                break;
            case"mrock_guildactivity_client":
                break;
            case"mrock_Map_client":
                break;
            case"mrock_match_client":
                r.push([0, "m_iMatchIndex", 4]);
                r.push([0, "m_iRegion", 4]);
                r.push([0, "m_iType", 4]);
                r.push([0, "m_szBeginTime", 64]);
                r.push([0, "m_szEndTime", 64]);
                r.push([0, "m_iElectNumber", 4]);
                r.push([0, "m_iGroupNumber", 4]);
                r.push([0, "m_szApplySuccess", 64]);
                r.push([0, "m_szTitle", 64]);
                r.push([0, "m_szTitle1", 64]);
                r.push([0, "m_szContent1", 200]);
                r.push([0, "m_szTitle2", 64]);
                r.push([0, "m_szContent2", 200]);
                r.push([0, "m_szTitle3", 64]);
                r.push([0, "m_szContent3", 1024]);
                r.push([0, "m_szContentPage201", 200]);
                r.push([0, "m_szContentPage202", 200]);
                r.push([0, "m_szContentPage203", 200]);
                r.push([0, "m_szContentPage204", 200]);
                r.push([0, "m_szContentPage205", 200]);
                r.push([0, "m_szTimeDesc", 200]);
                r.push([0, "m_szPostTitle", 64]);
                r.push([0, "m_szPostDesc1", 200]);
                r.push([0, "m_szPostDesc2", 200]);
                r.push([0, "m_szPostDesc3", 200]);
                r.push([0, "m_szPostDesc4", 200]);
                r.push([0, "m_szStageName", 200]);
                r = ["MatchConfig_Client_Tab", "MatchConfig_Client", r];
                break;
            case"mrock_match_division_client":
                r.push([0, "m_iMatchID", 4]);
                r.push([0, "m_szFilterSort1", 64]);
                r.push([0, "m_szFilterSort2", 64]);
                r.push([0, "m_szFilter1", 64]);
                r.push([0, "m_szFilter2", 64]);
                r = ["MatchDivisionConfig_Client_Tab", "MatchDivisionConfig_Client", r];
                break;
            case"mrock_medal_client":
                break;
            case"mrock_noble_client":
                break;
            case"mrock_noble_gift_client":
                break;
            case"mrock_papasong_client":
                r.push([0, "m_ushSongID", 2]);
                r.push([0, "m_iVersion", 4]);
                r.push([0, "m_szSongName", 64]);
                r.push([0, "m_cDifficulty", 1]);
                r.push([0, "m_cLevel", 1]);
                r.push([0, "m_szPath", 64]);
                r.push([0, "m_szArtist", 64]);
                r.push([0, "m_szSongTime", 64]);
                r.push([0, "m_iGameTime", 4]);
                r.push([0, "m_szRegion", 20]);
                r.push([0, "m_szStyle", 20]);
                r.push([0, "m_szBPM", 20]);
                r.push([0, "m_szNoteNumber", 20]);
                r.push([0, "m_iOrderIndex", 4]);
                r.push([0, "m_ucIsOpen", 1]);
                r.push([0, "m_ucIsFree", 1]);
                r.push([0, "m_ucIsHide", 1]);
                r.push([0, "m_ucIsReward", 1]);
                r.push([0, "m_ucIsLevelReward", 1]);
                r.push([0, "m_iSongType", 4]);
                r = ["PapaSongConfig_Client_Tab", "PapaSongConfig_Client", r];
                break;
            case"mrock_questconfig_client":
                r.push([0, "m_iQuestId", 4]);
                r.push([0, "m_szQuestName", 300]);
                r.push([0, "m_szDescription", 300]);
                r.push([0, "m_iMethod", 4]);
                r.push([0, "m_iNodeType", 4]);
                r.push([0, "m_iNodeValue", 4]);
                r.push([0, "m_iNodeLimit", 4]);
                r.push([0, "m_iSongNodeID", 4]);
                r.push([0, "m_iSongNodeNoteType", 4]);
                r.push([0, "m_iRewardType", 4]);
                r.push([0, "m_iRewardID", 4]);
                r.push([0, "m_iRewardValue", 4]);
                r = ["QuestConfig_Client_Tab", "QuestConfig_Client", r];
                break;
            case"mrock_recharge_ios_client":
            case"mrock_recharge_android_client":
                break;
            case"mrock_room_client":
                r.push([0, "m_ushRoomID", 2]);
                r.push([0, "m_ucSongType", 1]);
                r.push([0, "m_ucTicketType", 1]);
                r.push([0, "m_iTicketNum", 4]);
                r.push([0, "m_ucRewardType", 1]);
                r.push([0, "m_iRewardNum", 4]);
                r.push([0, "m_iRewardIntergral", 4]);
                r.push([0, "m_ucIsOpen", 1]);
                r.push([0, "m_ucIsNewOpen", 1]);
                r.push([0, "m_ucIsProp", 1]);
                r.push([0, "m_iMinClientVersion", 4]);
                r = ["RoomConfig_Client_Tab", "RoomConfig_Client", r];
                break;
            case"mrock_share_client":
                break;
            case"mrock_song_client":
            case"mrock_song_client_android":
            case"mrock_songlevel_client":
                r.push([0, "m_ushSongID", 2]);
                r.push([0, "m_iVersion", 4]);
                r.push([0, "m_szSongName", 64]);
                r.push([0, "m_szPath", 64]);
                r.push([0, "m_szArtist", 64]);
                r.push([0, "m_szComposer", 64]);
                r.push([0, "m_szSongTime", 64]);
                r.push([0, "m_iGameTime", 4]);
                r.push([0, "m_iRegion", 4]);
                r.push([0, "m_iStyle", 4]);
                r.push([0, "m_ucIsNew", 2]);
                r.push([0, "m_ucIsHot", 2]);
                r.push([0, "m_ucIsRecommend", 2]);
                r.push([0, "m_szBPM", 64]);
                r.push([0, "m_ucIsOpen", 2]);
                r.push([0, "m_ucCanBuy", 1]);
                r.push([0, "m_iOrderIndex", 4]);
                r.push([0, "m_bIsFree", 1]);
                r.push([0, "m_bSongPkg", 1]);
                r.push([0, "m_szFreeBeginTime", 64]);
                r.push([0, "m_szFreeEndTime", 64]);
                r.push([0, "m_ush4KeyEasy", 2]);
                r.push([0, "m_ush4KeyNormal", 2]);
                r.push([0, "m_ush4KeyHard", 2]);
                r.push([0, "m_ush5KeyEasy", 2]);
                r.push([0, "m_ush5KeyNormal", 2]);
                r.push([0, "m_ush5KeyHard", 2]);
                r.push([0, "m_ush6KeyEasy", 2]);
                r.push([0, "m_ush6KeyNormal", 2]);
                r.push([0, "m_ush6KeyHard", 2]);
                r.push([0, "m_iPrice", 4]);
                r.push([0, "m_szNoteNumber", 128]);
                r.push([0, "m_szProductID", 128]);
                r.push([0, "m_iVipFlag", 4]);
                r.push([0, "m_bIsHide", 1]);
                r.push([0, "m_bIsReward", 1]);
                r.push([0, "m_bIsLevelReward", 1]);
                r = ["SongConfig_Client_Tab", "SongConfig_Client", r];
                break;
            case"mrock_SongPkg_client":
                r.push([0, "m_ushIndex", 2]);
                r.push([0, "m_szProductID", 128]);
                r.push([0, "m_szDesc", 512]);
                r.push([0, "m_szPath", 128]);
                r.push([0, "m_iAndroidPrice", 4]);
                r.push([0, "m_iPrice", 4]);
                r.push([0, "m_iEnable", 4]);
                r.push([0, "m_bIsNew", 1]);
                r.push([0, "m_bIsHot", 1]);
                r.push([0, "m_iNum", 4]);
                r.push([1, "m_astSongs,PkgSong", 10]);
                r = ["SongPkgConfig_Client_Tab", "SongPkgConfig_Client", r];
                break;
            case"mrock_starmall_exchange_client":
                break;
            case"mrock_SysParam_client":
                r.push([0, "m_iParamID", 4]);
                r.push([0, "m_iParamValue", 4]);
                r = ["SysParamConfig_Client_Tab", "SysParamConfig_Client", r];
                break;
            case"mrock_txt_client":
                r.push([0, "m_uiID", 4]);
                r.push([0, "m_szDescriptionZH", 256]);
                r = ["TxtConfig_Client_Tab", "TxtConfig_Client", r];
                break;
        }
        return r;
    };
    var generateListBat = function () {
        if((typeof Buffer["Bin"] !== "undefined") || (typeof Buffer["Xml"] !== "undefined")) {
            switch(FileName) {
                case"mrock_song_client":
                case"mrock_song_client_android":
                case"mrock_papasong_client":
                    Text["List"] = toListText();
                    Text["Bat"] = toBatText();
                    break;
            }
        }
    };
    var fromBinBuffer = function (buffer) {
        Buffer["Bin"] = buffer;
        generateListBat();
        isValid = true;
    };
    var fromHexBuffer = function (buffer) {
        Buffer["Hex"] = buffer;
        Buffer["Bin"] = new Uint8Array().fromHex(buffer.getText().replace(/(\r\n|\s+)/g, ""), true);
        generateListBat();
        isValid = true;
    };
    var fromXmlBuffer = function (buffer) {
        Buffer["Xml"] = buffer;
        generateListBat();
        isValid = true;
    };
    var toBinBuffer = function () {
        if(typeof Text["Xml"] === "undefined") {
            Text["Xml"] = toXmlText();
        }
        var x = Text["Xml"].replace(/\r\n/g, "");
        var readData = function (f1, f2) {
            var l1 = x.indexOf(f1, px);
            if(l1 == -1) {
                px = x.length;
                return"";
            } else {
                l1 += f1.length;
            }
            var l2 = x.indexOf(f2, l1);
            if(l2 == -1) {
                px = x.length;
                return"";
            }
            px = l2 + f2.length;
            return x.substring(l1, l2);
        };
        var writeInt8 = function (c) {
            if(c == "") {
                c = "0x0 "
            }
            if((c.indexOf("0x") == 0) && (c.lastIndexOf(" ") == (c.length - 1))) {
                bin.setInt8(parseInt(c, 16), pb);
                pb += 1;
            }
        };
        var writeInt16 = function (c) {
            if(c == "") {
                c = "0 "
            }
            if(c.lastIndexOf(" ") == (c.length - 1)) {
                bin.setInt16(parseInt(c, 10), pb, true);
                pb += 2;
            }
        };
        var writeInt32 = function (c) {
            if(c == "") {
                c = "0 "
            }
            if(c.lastIndexOf(" ") == (c.length - 1)) {
                bin.setInt32(parseInt(c, 10), pb, true);
                pb += 4;
            }
        };
        var writeDateTime = function (c) {
            if(c == "") {
                c = " 0-00-00 00:00:00"
            }
            var a = c.split("-");
            var y = a[0];
            writeInt32(y.toString() + " ");
            var M = a[1];
            writeInt16(M.toString() + " ");
            a = a[2].split(" ");
            var d = a[0];
            writeInt16(d.toString() + " ");
            a = a[1].split(":");
            var H = a[0];
            writeInt16(H.toString() + " ");
            var m = a[1];
            writeInt16(m.toString() + " ");
            var s = a[2];
            writeInt16(s.toString() + " ");
        };
        var writeText = function (c, n) {
            bin.setText(c, pb);
            var t = new Uint8Array().fromText(c);
            pb += t.length;
            bin.set(new Uint8Array().duplicate(n - t.length, 0), pb)
            pb += n - t.length;
        };
        var writeData = function (c, n) {
            switch(n) {
                case 1:
                    writeInt8(c);
                    break;
                case 2:
                    writeInt16(c);
                    break;
                case 4:
                    writeInt32(c);
                    break;
                case 14:
                    writeDateTime(c);
                    break;
                case 20:
                case 22:
                case 32:
                case 48:
                case 64:
                case 128:
                case 200:
                case 256:
                case 300:
                case 512:
                case 1024:
                    writeText(c, n);
                    break;
            }
        };
        var writeDataE = function (f, t, n) {
            var l = [];
            for(var i = 0; i < n; i++) {
                if(f == "TResHeadAll") {
                    l.push(x);
                } else {
                    l.push(readData("<" + f + " type=" + '"' + t + '"' + ">", "</" + f + ">"));
                }
            }
            for(var i = 0; i < l.length; i++) {
                x = l[i];
                px = 0;
                switch(f) {
                    case"TResHeadAll":
                        writeData(readData("<Version>", "</Version>"), 4);
                        writeData(readData("<Unit>", "</Unit>"), 4);
                        writeData(readData("<Count>", "</Count>"), 4);
                        writeData(readData("<MetalibHash>", "</MetalibHash>"), 32);
                        writeData(readData("<ResVersion>", "</ResVersion>"), 2);
                        writeData(readData("<CreateTime>", "</CreateTime>"), 14);
                        writeData(readData("<ResEncording>", "</ResEncording>"), 32);
                        writeData(readData("<ContentHash>", "</ContentHash>"), 32);
                        writeData("", 4);
                        writeData(readData("<DataOffset>", "</DataOffset>"), 4);
                        break;
                    case"m_astReward":
                        switch(t) {
                            case"ActiveReward":
                                if(x != "") {
                                    writeData(readData("<m_ucRewardType>", "</m_ucRewardType>"), 1);
                                    writeData(readData("<m_iRewardID>", "</m_iRewardID>"), 4);
                                    writeData(readData("<m_iRewardValue>", "</m_iRewardValue>"), 4);
                                } else {
                                    writeData("", 1);
                                    writeData("", 4);
                                    writeData("", 4);
                                }
                                break;
                            case"MarketActReward":
                                if(x != "") {
                                    writeData(readData("<m_ucRewardType>", "</m_ucRewardType>"), 1);
                                    writeData(readData("<m_ushRewardID>", "</m_ushRewardID>"), 2);
                                    writeData(readData("<m_iRewardValue>", "</m_iRewardValue>"), 4);
                                } else {
                                    writeData("", 1);
                                    writeData("", 2);
                                    writeData("", 4);
                                }
                                break;
                        }
                        break;
                    case"m_astSongs":
                        if(x != "") {
                            writeData(readData("<ID>", "</ID>"), 2);
                        } else {
                            writeData("", 2);
                        }
                        break;
                }
            }
        };
        var writeDataT = function (s) {
            if(s.length == 0) {
                return;
            }
            x = readData("<" + s[0] + ">", "</" + s[0] + ">");
            px = 0;
            writeText("MSES", 4);
            writeDataE("TResHeadAll", "", 1);
            var l = [];
            while(px < x.length) {
                l.push(readData("<" + s[1] + " version=" + '"' + "1" + '"' + ">", "</" + s[1] + ">"))
            }
            for(var i = 0; i < l.length; i++) {
                x = l[i];
                px = 0;
                for(var j = 0; j < s[2].length; j++) {
                    switch(s[2][j][0]) {
                        case 0:
                            writeData(readData("<" + s[2][j][1] + ">", "</" + s[2][j][1] + ">"), s[2][j][2]);
                            break;
                        case 1:
                            var a = s[2][j][1].split(",");
                            writeDataE(a[0], a[1], s[2][j][2]);
                            break;
                    }
                }
            }
            bin.setInt32(l.length, 12, true);
            bin.setText(bin.getMD5(136, pb - 136).toLowerCase(), 96);
        };
        var bin = new Uint8Array(x.length * 3);
        var px = 0;
        var pb = 0;
        writeDataT(getBinStructure());
        return bin.slice(0, pb);
    };
    var toHexBuffer = function () {
        if(typeof Text["Hex"] === "undefined") {
            Text["Hex"] = toHexText();
        }
        return new Uint8Array().fromText(Text["Hex"]);
    };
    var toXmlBuffer = function () {
        if(typeof Text["Xml"] === "undefined") {
            Text["Xml"] = toXmlText();
        }
        return new Uint8Array().fromText(Text["Xml"]);
    };
    var toListBuffer = function () {
        if(typeof Text["List"] === "undefined") {
            Text["List"] = toListText();
        }
        return new Uint8Array().fromText(Text["List"]);
    };
    var toBatBuffer = function () {
        if(typeof Text["Bat"] === "undefined") {
            Text["Bat"] = toBatText();
        }
        return new Uint8Array().fromText(Text["Bat"], "ANSI");
    };
    var toBinText = function () {
        if(typeof Buffer["Bin"] === "undefined") {
            Buffer["Bin"] = toBinBuffer();
        }
        var writeHex = function (l) {
            hex.push(Buffer["Bin"].getHex(p, l, " "));
            p += l;
        };
        var writeHexE = function (f, t, n) {
            for(var i = 0; i < n; i++) {
                switch(f) {
                    case"TResHeadAll":
                        writeHex(4);
                        writeHex(4);
                        writeHex(4);
                        writeHex(32);
                        writeHex(2);
                        writeHex(14);
                        writeHex(32);
                        writeHex(32);
                        writeHex(4);
                        writeHex(4);
                        break;
                    case"m_astReward":
                        switch(t) {
                            case"ActiveReward":
                                writeHex(1);
                                writeHex(4);
                                writeHex(4);
                                break;
                            case"MarketActReward":
                                writeHex(1);
                                writeHex(2);
                                writeHex(4);
                                break;
                        }
                        break;
                    case"m_astSongs":
                        writeHex(2);
                        break;
                }
            }
        };
        var writeHexT = function (s) {
            if(s.length == 0) {
                return;
            }
            writeHex(4);
            writeHexE("TResHeadAll", "", 1);
            while(p < Buffer["Bin"].length) {
                for(var i = 0; i < s[2].length; i++) {
                    switch(s[2][i][0]) {
                        case 0:
                            writeHex(s[2][i][2]);
                            break;
                        case 1:
                            var a = s[2][i][1].split(",");
                            writeHexE(a[0], a[1], s[2][i][2]);
                            break;
                    }
                }
            }
        };
        var hex = [];
        var p = 0;
        writeHexT(getBinStructure());
        return hex.join("\r\n");
    };
    var toHexText = function () {
        if(typeof Text["Bin"] === "undefined") {
            Text["Bin"] = toBinText();
        }
        return Text["Bin"];
    };
    var toXmlText = function () {
        if(typeof Buffer["Xml"] !== "undefined") {
            return Buffer["Xml"].getText();
        }
        if(typeof Buffer["Bin"] !== "undefined") {
            var b = Buffer["Bin"];
            var readByte = function () {
                var r = b.getHex(p, 1).toLowerCase().trimLeft("0");
                if(r == "") {
                    r = "0";
                }
                p += 1;
                return"0x" + r + " ";
            };
            var readInt16 = function () {
                var r = b.getInt16(p, true);
                p += 2;
                return r.toString() + " ";
            };
            var readInt32 = function () {
                var r = b.getInt32(p, true);
                p += 4;
                return r.toString() + " ";
            };
            var readDateTime = function () {
                var y = b.getInt32(p, true);
                p += 4;
                var M = b.getInt16(p, true);
                p += 2;
                var d = b.getInt16(p, true);
                p += 2;
                var H = b.getInt16(p, true);
                p += 2;
                var m = b.getInt16(p, true);
                p += 2;
                var s = b.getInt16(p, true);
                p += 2;
                return y.toString().fill(4, " ", true).replace(/ /g, "&nbsp;") + "-" + M.toString().fill(2, "0", true) + "-" + d.toString().fill(2, "0", true) + " " + H.toString().fill(2, "0", true) + ":" + m.toString().fill(2, "0", true) + ":" + s.toString().fill(2, "0", true);
            };
            var readText = function (n) {
                var l = n;
                for(var i = 0; i < n; i++) {
                    if(b[p + n - i - 1] == 0) {
                        l -= 1;
                    } else {
                        break;
                    }
                }
                var r = b.getText(p, l);
                p += n;
                return r;
            };
            var readData = function (n) {
                switch(n) {
                    case 1:
                        return readByte();
                        break;
                    case 2:
                        return readInt16();
                        break;
                    case 4:
                        return readInt32();
                        break;
                    case 14:
                        return readDateTime();
                        break;
                    case 20:
                    case 22:
                    case 32:
                    case 48:
                    case 64:
                    case 128:
                    case 200:
                    case 256:
                    case 300:
                    case 512:
                    case 1024:
                        return readText(n);
                        break;
                    default:
                        p += n;
                        return"";
                }
            };
            var writeData = function (l, f, c) {
                xml.push(new String().duplicate(4 * l, "&nbsp;") + "&lt;" + f + "&gt;" + c + "&lt;/" + f + "&gt;");
            };
            var writeDataA = function (l, f, n) {
                writeData(l, f, readData(n));
            };
            var writeDataE = function (l, f, t, n) {
                for(var i = 0; i < n; i++) {
                    switch(f) {
                        case"TResHeadAll":
                            xml.push(new String().duplicate(4 * l, "&nbsp;") + "&lt;" + f + "&nbsp;version=&quot;5&quot;&gt;");
                            xml.push(new String().duplicate(4 * (l + 1), "&nbsp;") + "&lt;resHead type=&quot;TResHead&quot;&gt;");
                            xml.push(new String().duplicate(4 * (l + 2), "&nbsp;") + "&lt;Magic&gt;1397052237 &lt;/Magic&gt;");
                            writeDataA(l + 2, "Version", 4);
                            writeDataA(l + 2, "Unit", 4);
                            writeDataA(l + 2, "Count", 4);
                            writeDataA(l + 2, "MetalibHash", 32);
                            writeDataA(l + 2, "ResVersion", 2);
                            writeDataA(l + 2, "CreateTime", 14);
                            writeDataA(l + 2, "ResEncording", 32);
                            writeDataA(l + 2, "ContentHash", 32);
                            p += 4;
                            xml.push(new String().duplicate(4 * (l + 1), "&nbsp;") + "&lt;/resHead&gt;");
                            xml.push(new String().duplicate(4 * (l + 1), "&nbsp;") + "&lt;resHeadExt&nbsp;type=&quot;TResHeadExt&quot;&gt;");
                            writeDataA(l + 2, "DataOffset", 4);
                            xml.push(new String().duplicate(4 * (l + 1), "&nbsp;") + "&lt;/resHeadExt&gt;");
                            xml.push(new String().duplicate(4 * l, "&nbsp;") + "&lt;/" + f + "&gt;");
                            break;
                        case"m_astReward":
                            switch(t) {
                                case"ActiveReward":
                                    var c1 = readData(1);
                                    var c2 = readData(4);
                                    var c3 = readData(4);
                                    if(!((c1 == "0x0 ") && (c2 == "0 ") && (c3 == "0 "))) {
                                        xml.push(new String().duplicate(4 * l, "&nbsp;") + "&lt;" + f + " type=&quot;" + t + "&quot;&gt;");
                                        writeData(l + 1, "m_ucRewardType", c1);
                                        writeData(l + 1, "m_iRewardID", c2);
                                        writeData(l + 1, "m_iRewardValue", c3);
                                        xml.push(new String().duplicate(4 * l, "&nbsp;") + "&lt;/" + f + "&gt;");
                                    }
                                    break;
                                case"MarketActReward":
                                    var c1 = readData(1);
                                    var c2 = readData(2);
                                    var c3 = readData(4);
                                    if(!((c1 == "0x0 ") && (c2 == "0 ") && (c3 == "0 "))) {
                                        xml.push(new String().duplicate(4 * l, "&nbsp;") + "&lt;" + f + " type=&quot;" + t + "&quot;&gt;");
                                        writeData(l + 1, "m_ucRewardType", c1);
                                        writeData(l + 1, "m_ushRewardID", c2);
                                        writeData(l + 1, "m_iRewardValue", c3);
                                        xml.push(new String().duplicate(4 * l, "&nbsp;") + "&lt;/" + f + "&gt;");
                                    }
                                    break;
                            }
                            break;
                        case"m_astSongs":
                            var c = readData(2);
                            if(!(c == "0 ")) {
                                xml.push(new String().duplicate(4 * l, "&nbsp;") + "&lt;" + f + " type=&quot;" + t + "&quot;&gt;");
                                writeData(l + 1, "ID", c);
                                xml.push(new String().duplicate(4 * l, "&nbsp;") + "&lt;/" + f + "&gt;");
                            }
                            break;
                    }
                }
            };
            var writeDataT = function (s) {
                if(s.length == 0) {
                    return;
                }
                p = 4;
                xml.push("&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot; standalone=&quot;yes&quot; ?&gt;");
                xml.push("&lt;" + s[0] + "&gt;");
                writeDataE(0, "TResHeadAll", "", 1);
                while(p < b.length) {
                    xml.push("&lt;" + s[1] + " version=&quot;1&quot;&gt;");
                    for(var i = 0; i < s[2].length; i++) {
                        switch(s[2][i][0]) {
                            case 0:
                                writeDataA(1, s[2][i][1], s[2][i][2]);
                                break;
                            case 1:
                                var a = s[2][i][1].split(",");
                                writeDataE(1, a[0], a[1], s[2][i][2]);
                                break;
                        }
                    }
                    xml.push("&lt;/" + s[1] + "&gt;");
                }
                xml.push("&lt;/" + s[0] + "&gt;");
            };
            var xml = [];
            var p = 0;
            writeDataT(getBinStructure());
            return xml.join("\r\n").fromHtml();
        }
    };
    var toHtmlText = function () {
        if(typeof Text["Xml"] === "undefined") {
            Text["Xml"] = toXmlText();
        }
        var b = Text["Xml"].replace(/\r\n/g, "");
        var readData = function (f1, f2) {
            var r;
            var s1 = b.indexOf(f1, p);
            if(s1 == -1) {
                p = b.length;
                return"";
            } else {
                s1 += f1.length;
            }
            var s2 = b.indexOf(f2, s1);
            if(s2 == -1) {
                p = b.length;
                return"";
            }
            p = s2 + f2.length;
            return b.substring(s1, s2);
        };
        var writeDataT = function (s) {
            if(s.length == 0) {
                return;
            }
            html.push("<html><meta charset =" + '"UTF-8"' + "/><head><title>" + FileName + FileSuffixImd + "</title></head><body><table border=" + '"1"' + "><caption>" + FileName + FileSuffixImd + "</caption>");
            var e = false;
            for(var i = 0; i < s[2].length; i++) {
                if(s[2][i][0] == 1) {
                    e = true;
                    break;
                }
            }
            html.push("<tr>");
            for(var i = 0; i < s[2].length; i++) {
                switch(s[2][i][0]) {
                    case 0:
                        html.push((e ? "<th rowspan=" + '"2"' + ">" : "<th>") + s[2][i][1] + "</th>");
                        break;
                    case 1:
                        var a = s[2][i][1].split(",");
                        switch(a[0]) {
                            case"m_astReward":
                                switch(a[1]) {
                                    case"ActiveReward":
                                        html.push(new String().duplicate(s[2][i][2], (e ? "<th colspan=" + '"3"' + ">" : "<th>") + a[0] + "</th>"));
                                        break;
                                    case"MarketActReward":
                                        html.push(new String().duplicate(s[2][i][2], (e ? "<th colspan=" + '"3"' + ">" : "<th>") + a[0] + "</th>"));
                                        break;
                                }
                                break;
                            case"m_astSongs":
                                html.push(new String().duplicate(s[2][i][2], (e ? "<th colspan=" + '"1"' + ">" : "<th>") + a[0] + "</th>"));
                                break;
                        }
                        break;
                }
            }
            html.push("</tr>");
            html.push("<tr>");
            for(var i = 0; i < s[2].length; i++) {
                switch(s[2][i][0]) {
                    case 1:
                        var a = s[2][i][1].split(",");
                        switch(a[0]) {
                            case"m_astReward":
                                switch(a[1]) {
                                    case"ActiveReward":
                                        html.push(new String().duplicate(s[2][i][2], "<th>m_ucRewardType</th><th>m_iRewardID</th><th>m_iRewardValue</th>"));
                                        break;
                                    case"MarketActReward":
                                        html.push(new String().duplicate(s[2][i][2], "<th>m_ucRewardType</th><th>m_ushRewardID</th><th>m_iRewardValue</th>"));
                                        break;
                                }
                                break;
                            case"m_astSongs":
                                html.push(new String().duplicate(s[2][i][2], "<th>ID</th>"));
                                break;
                        }
                        break;
                }
            }
            html.push("</tr>");
            b = readData("<" + s[0] + ">", "</" + s[0] + ">");
            p = 0;
            var l = [];
            while(p < b.length) {
                l.push(readData("<" + s[1] + " version=" + '"' + "1" + '"' + ">", "</" + s[1] + ">"))
            }
            for(var i = 0; i < l.length; i++) {
                b = l[i];
                p = 0;
                html.push("<tr>");
                for(var j = 0; j < s[2].length; j++) {
                    switch(s[2][j][0]) {
                        case 0:
                            html.push("<td>" + readData("<" + s[2][j][1] + ">", "</" + s[2][j][1] + ">") + "</td>");
                            break;
                        case 1:
                            var e = [];
                            for(var k = 0; k < s[2][j][2]; k++) {
                                e.push(readData("<" + a[0] + " type=" + '"' + a[1] + '"' + ">", "</" + a[0] + ">"));
                            }
                            for(var k = 0; k < s[2][j][2]; k++) {
                                b = e[k];
                                p = 0;
                                var a = s[2][j][1].split(",");
                                switch(a[0]) {
                                    case"m_astReward":
                                        switch(a[1]) {
                                            case"ActiveReward":
                                                html.push("<td>" + readData("<m_ucRewardType>", "</m_ucRewardType>") + "</td>");
                                                html.push("<td>" + readData("<m_iRewardID>", "</m_iRewardID>") + "</td>");
                                                html.push("<td>" + readData("<m_iRewardValue>", "</m_iRewardValue>") + "</td>");
                                                break;
                                            case"MarketActReward":
                                                html.push("<td>" + readData("<m_ucRewardType>", "</m_ucRewardType>") + "</td>");
                                                html.push("<td>" + readData("<m_ushRewardID>", "</m_ushRewardID>") + "</td>");
                                                html.push("<td>" + readData("<m_iRewardValue>", "</m_iRewardValue>") + "</td>");
                                                break;
                                        }
                                        break;
                                    case"m_astSongs":
                                        html.push("<td>" + readData("<ID>", "</ID>") + "</td>");
                                        break;
                                }
                            }
                            break;
                    }
                }
                html.push("</tr>");
            }
            html.push("</table></body></html>");
        };
        var html = [];
        var p = 0;
        writeDataT(getBinStructure());
        return html.join("");
    };
    var toListText = function () {
        var getXml = function () {
            if(typeof Text["Xml"] === "undefined") {
                Text["Xml"] = toXmlText();
            }
            return Text["Xml"];
        };
        var readXml = function (t, t1, t2) {
            var f1 = "<" + t;
            var f2 = "</" + t + ">";
            var l1 = x.indexOf(f1, p);
            if(l1 == -1) {
                p = x.length;
                return"";
            } else {
                l1 += f1.length;
            }
            var l2 = x.indexOf(f2, l1);
            if(l2 == -1) {
                p = x.length;
                return"";
            }
            p = l2 + f2.length;
            var d = x.substring(l1, l2);
            var r = [];
            r.push(d.extract("<" + t1 + ">", "</" + t1 + ">"));
            for(var i = 0; i < t2.length; i++) {
                r.push(d.extract("<" + t2[i] + ">", "</" + t2[i] + ">"));
            }
            return r;
        };
        var r = [];
        var p = 0;
        switch(FileName) {
            case"mrock_song_client":
            case"mrock_song_client_android":
                var T = ["m_ush4KeyEasy", "m_ush4KeyNormal", "m_ush4KeyHard", "m_ush5KeyEasy", "m_ush5KeyNormal", "m_ush5KeyHard", "m_ush6KeyEasy", "m_ush6KeyNormal", "m_ush6KeyHard"];
                var S = ["_4k_ez", "_4k_nm", "_4k_hd", "_5k_ez", "_5k_nm", "_5k_hd", "_6k_ez", "_6k_nm", "_6k_hd"];
                var E = ".imd";
                var x = getXml();
                while(true) {
                    var a = readXml("SongConfig_Client", "m_szPath", T);
                    if((typeof a[0] === "undefined") || (a[0] == "")) {
                        break;
                    }
                    r.push(a[0] + ".mp3");
                    for(var i = 0; i < T.length; i++) {
                        var v = parseInt(a[i + 1]);
                        if((!isNaN(v)) && (v > 0) && (v <= 10)) {
                            r.push(a[0] + S[i] + E);
                        }
                    }
                }
                break;
            case"mrock_papasong_client":
                var T = ["m_cDifficulty"];
                var S = ["_Papa_Easy", "_Papa_Normal", "_Papa_Hard"];
                var E = ".mde";
                var x = getXml();
                while(true) {
                    var a = readXml("PapaSongConfig_Client", "m_szPath", T);
                    if((typeof a[0] === "undefined") || (a[0] == "")) {
                        break;
                    }
                    r.push(a[0] + ".mp3");
                    for(var i = 0; i < T.length; i++) {
                        var v = parseInt(a[i + 1]);
                        if((!isNaN(v)) && (v > 0) && (v - 1 < S.length)) {
                            r.push(a[0] + S[v - 1] + E);
                        }
                    }
                }
                break;
        }
        return r.join("\r\n");
    };
    var toBatText = function () {
        var r = [];
        r.push("@echo off & setlocal enabledelayedexpansion");
        r.push("@echo;");
        r.push("@echo 　　　　 ☆☆☆☆☆　「节奏大师」资源批量下载 by rmstZ.html　☆☆☆☆☆");
        r.push("@echo;");
        r.push("@echo 　——————————————————————————————————————");
        r.push("@echo;");
        r.push("@echo 　　【步骤说明】");
        r.push("@echo 　　①链接分析，通过分析★res_imd.list、★res_mde.list生成★res_new.txt；");
        r.push("@echo 　　②批量下载，将★res_new.txt全部链接复制到IDM等下载工具并下载到当前文件夹；");
        r.push("@echo 　　③文件移动，批量下载到当前文件夹后执行文件移动，移动结束后步骤完成。");
        r.push("@echo;");
        r.push("@echo 　——————————————————————————————————————");
        r.push("@echo;");
        r.push("set /p t=　　【？】请选择资源类型（1、谱面；2、音乐）：");
        r.push("@echo;");
        r.push("if %t%==1 @echo 　　【！】已选择资源类型：谱面，开始执行……");
        r.push("if %t%==2 @echo 　　【！】已选择资源类型：音乐，开始执行……");
        r.push("@echo;");
        r.push("@echo 　——————————————————————————————————————");
        r.push("@echo;");
        r.push("@echo 　　【①链接分析】");
        r.push("@echo;");
        r.push("@echo 　　┏正在分析（根据设备性能可能需要较长时间，请耐心等待）");
        r.push("@echo 　　┃　　★res_imd.list");
        r.push("@echo 　　┃　　★res_mde.list");
        r.push("if exist ★res_new.txt del /f ★res_new.txt");
        r.push("set c=0 & set e=0 & set n=0");
        r.push("if %t%==1 ((for /f " + '"' + "delims=. tokens=1,2" + '"' + " %%i in (★res_imd.list ★res_mde.list) do (if not " + '"' + "%%j" + '"' + "==" + '"' + "mp3" + '"' + " (if not defined %%i.%%j (set %%i.%%j=A & set /a c+=1 & for /f " + '"' + "delims=_ tokens=1,*" + '"' + " %%k in (" + '"' + "%%i.%%j" + '"' + ") do (if exist res\\song\\%%k\\%%k_%%l (set /a e+=1) else (set /a n+=1 & set " + '"' + "f!n!=%%k_%%l" + '"' + " & set " + '"' + "d!n!=res\\song\\%%k\\" + '"' + " & echo http://game.ds.qq.com/Com_SongRes/song/%%k/%%k_%%l))))))>★res_new.txt)");
        r.push("if %t%==2 ((for /f " + '"' + "delims=. tokens=1,2" + '"' + " %%i in (★res_imd.list ★res_mde.list) do (if " + '"' + "%%j" + '"' + "==" + '"' + "mp3" + '"' + " (if not defined %%i.%%j (set %%i.%%j=A & set /a c+=1 & if exist res\\song\\%%i\\%%i.%%j (set /a e+=1) else (set /a n+=1 & set " + '"' + "f!n!=%%i.%%j" + '"' + " & set " + '"' + "d!n!=res\\song\\%%i\\" + '"' + " & echo http://game.ds.qq.com/Com_SongRes/song/%%i/%%i.%%j)))))>★res_new.txt)");
        r.push("@echo 　　┣分析结果");
        r.push("@echo 　　┃　　资源总数：%c%");
        r.push("@echo 　　┃　　已存在：%e%");
        r.push("@echo 　　┃　　需下载：%n%");
        r.push("@echo 　　┗分析结束，★res_new.txt生成完毕，进入下一步骤……");
        r.push("@echo;");
        r.push("@echo 　——————————————————————————————————————");
        r.push("@echo;");
        r.push("@echo 　　【②批量下载】");
        r.push("@echo;");
        r.push("@echo 　☆┏用户操作");
        r.push("@echo 　☆┣将★res_new.txt内全部链接复制到IDM等下载工具并下载到当前文件夹");
        r.push("@echo 　☆┃");
        r.push("@echo 　☆┗步骤完成后按任意键执行文件校对……");
        r.push("pause>nul");
        r.push("@echo;");
        r.push("@echo 　　┏正在校对（可能需要几秒的时间，请耐心等待）");
        r.push("if exist ★res_new.txt del /f ★res_new.txt");
        r.push("set r=0");
        r.push("if %t%==1 (");
        r.push("@echo 　　┃　　.txt -^> .imd");
        r.push("for /f " + '"' + "delims=. tokens=1,2" + '"' + " %%i in (★res_imd.list) do (if not " + '"' + "%%j" + '"' + "==" + '"' + "mp3" + '"' + " (if not exist %%i.imd (if exist %%i.txt (ren %%i.txt %%i.imd & set /a r+=1))))");
        r.push("@echo 　　┃　　.txt -^> .mde");
        r.push("for /f " + '"' + "delims=. tokens=1,2" + '"' + " %%i in (★res_mde.list) do (if not " + '"' + "%%j" + '"' + "==" + '"' + "mp3" + '"' + " (if not exist %%i.mde (if exist %%i.txt (ren %%i.txt %%i.mde & set /a r+=1))))");
        r.push(")");
        r.push("@echo 　　┣校对结果");
        r.push("if %t%==1 (");
        r.push("@echo 　　┃　　需更正：%r%");
        r.push("@echo 　　┃　　已更正：%r%");
        r.push("@echo 　　┗校对结束，自动进入下一步骤……");
        r.push(")");
        r.push("if %t%==2 (");
        r.push("@echo 　　┗无需校对，自动进入下一步骤……");
        r.push(")");
        r.push("@echo;");
        r.push("@echo 　——————————————————————————————————————");
        r.push("@echo;");
        r.push("@echo 　　【③文件移动】");
        r.push("@echo;");
        r.push("@echo 　☆┏用户操作");
        r.push("@echo 　☆┣准备就绪，即将把文件移动到各自的文件夹");
        r.push("@echo 　☆┃");
        r.push("@echo 　☆┗按任意键开始执行文件移动……");
        r.push("pause>nul");
        r.push("@echo;");
        r.push("@echo 　　┏正在移动（根据设备性能可能需要较长时间，请耐心等待）");
        r.push("if %t%==1 (");
        r.push("@echo 　　┃　　*.imd");
        r.push("@echo 　　┃　　*.mde");
        r.push(")");
        r.push("if %t%==2 (");
        r.push("@echo 　　┃　　*.mp3");
        r.push(")");
        r.push("set u=0 & set m=0");
        r.push("for /l %%i in (1,1,%n%) do (if not exist !f%%i! (set /a u+=1) else ((if not exist !d%%i! md !d%%i!) & move /y !f%%i! !d%%i! & set /a m+=1))");
        r.push("@echo 　　┣移动结果");
        r.push("@echo 　　┃　　执行总数：%n%");
        r.push("@echo 　　┃　　不存在：%u%");
        r.push("@echo 　　┃　　已移动：%m%");
        r.push("@echo 　　┗移动结束，文件位于res/song/文件夹内……");
        r.push("@echo;");
        r.push("@echo 　☆┏用户操作");
        r.push("@echo 　☆┣全部步骤完成，谢谢使用！");
        r.push("@echo 　☆┃");
        r.push("@echo 　☆┗按任意键退出本程序。");
        r.push("@echo;");
        r.push("@echo 　——————————————————————————————————————");
        r.push("@echo;");
        r.push("@echo 　　　　 ☆☆☆☆☆　「节奏大师」资源批量下载 by rmstZ.html　☆☆☆☆☆");
        r.push("@echo;");
        r.push("pause>nul");
        return r.join("\r\n");
    };
    this.FromBuffer = function (buffer, ext) {
        if(buffer.length == 0) {
            return;
        }
        reset();
        switch(ext) {
            case"bin":
                fromBinBuffer(buffer);
                break;
            case"hex":
                fromHexBuffer(buffer);
                break;
            case"xml":
                fromXmlBuffer(buffer);
                break;
        }
    };
    this.ToBuffer = function (ext) {
        switch(ext) {
            case"bin":
                if(typeof Buffer["Bin"] === "undefined") {
                    Buffer["Bin"] = toBinBuffer();
                }
                return Buffer["Bin"];
                break;
            case"hex":
                if(typeof Buffer["Hex"] === "undefined") {
                    Buffer["Hex"] = toHexBuffer();
                }
                return Buffer["Hex"];
                break;
            case"xml":
                if(typeof Buffer["Xml"] === "undefined") {
                    Buffer["Xml"] = toXmlBuffer();
                }
                return Buffer["Xml"];
                break;
            case"list":
                if(typeof Buffer["List"] === "undefined") {
                    Buffer["List"] = toListBuffer();
                }
                return Buffer["List"];
                break;
            case"bat":
                if(typeof Buffer["Bat"] === "undefined") {
                    Buffer["Bat"] = toBatBuffer();
                }
                return Buffer["Bat"];
                break;
        }
    };
    this.ToText = function (ext) {
        switch(ext) {
            case"bin":
                if(typeof Text["Bin"] === "undefined") {
                    Text["Bin"] = toBinText();
                }
                return Text["Bin"];
                break;
            case"hex":
                if(typeof Text["Hex"] === "undefined") {
                    Text["Hex"] = toHexText();
                }
                return Text["Hex"];
                break;
            case"xml":
                if(typeof Text["Xml"] === "undefined") {
                    Text["Xml"] = toXmlText();
                }
                return Text["Xml"];
                break;
            case"html":
                if(typeof Text["Html"] === "undefined") {
                    Text["Html"] = toHtmlText();
                }
                return Text["Html"];
                break;
            case"list":
                if(typeof Text["List"] === "undefined") {
                    Text["List"] = toListText();
                }
                return Text["List"];
                break;
            case"bat":
                if(typeof Text["Bat"] === "undefined") {
                    Text["Bat"] = toBatText();
                }
                return Text["Bat"];
                break;
        }
    };
    this.IsValid = function () {
        return isValid;
    };
    this.IsExist = function (ext) {
        switch(ext) {
            case"list":
                return(typeof Buffer["List"] !== "undefined") || (typeof Text["List"] !== "undefined");
                break;
            case"bat":
                return(typeof Buffer["Bat"] !== "undefined") || (typeof Text["Bat"] !== "undefined");
                break;
        }
    };
    this.FromBuffer(buffer, ext);
}
function Img(buffer, ext) {
    var Buffer = {};
    var Text = {};
    var isValid = false;
    var reset = function () {
        Buffer["Img"] = undefined;
        Text["Png"] = [];
        isValid = false;
    };
    var fromImgBuffer = function (buffer) {
        Buffer["Img"] = buffer;
        isValid = true;
    };
    var toPngText = function () {
        var a = GetSelectValue("selectModeImg").split("X");
        var w = a[0];
        var h = a[1];
        var img = document.createElement("img");
        img.style.display = "none";
        img.src = GetImgSrc(Buffer["Img"]);
        var cvs = document.createElement("canvas");
        cvs.style.display = "none";
        cvs.width = w;
        cvs.height = h;
        var ctx = cvs.getContext("2d");
        if(img.width / img.height >= w / h) {
            var r = h / img.height;
            ctx.drawImage(img, (img.width - w / r) / 2, 0, w / r, h / r, 0, 0, w, h);
        } else {
            var r = w / img.width;
            ctx.drawImage(img, 0, (img.height - h / r) / 2, w / r, h / r, 0, 0, w, h);
        }
        return cvs.toDataURL("image/png");
    };
    this.FromBuffer = function (buffer, ext) {
        if(buffer.length == 0) {
            return;
        }
        reset();
        switch(ext) {
            case"bmp":
            case"jpg":
            case"gif":
            case"png":
                fromImgBuffer(buffer);
                break;
        }
    };
    this.ToText = function (ext, i) {
        switch(ext) {
            case"png":
                if(typeof Text["Png"][i] === "undefined") {
                    Text["Png"][i] = toPngText();
                }
                return Text["Png"][i];
                break;
        }
    };
    this.IsValid = function () {
        return isValid;
    };
    this.FromBuffer(buffer, ext);
}
function Msc(b) {
    var Buffer = {};
    var Text = {};
    var isValid = false;
    var reset = function () {
        Buffer["Mp3"] = undefined;
        Buffer["Imd"] = undefined;
        Text["Imd"] = undefined;
        isValid = false;
    };
    var fromMp3Buffer = function (buffer) {
        reset();
        if(buffer.length == 0) {
            return;
        }
        Buffer["Mp3"] = buffer;
        window.AudioContext = window.AudioContext || window.webkitAudioContext || window.mozAudioContext || window.msAudioContext;
        var ctx = new AudioContext();
        var bf = ctx.createBuffer(b.buffer, false);
        return bf.getChannelData(0);
        var visualize = function (ctx, bf) {
            var bs = ctx.createBufferSource();
            var as = ctx.createAnalyser();
            bs.connect(as);
            as.connect(ctx.destination);
            bs.buffer = bf;
            if(!bs.start) {
                bs.start = bs.noteOn
                bs.stop = bs.noteOff
            }
            bs.start(0);
            this.status = 1;
            this.source = bs;
            var fd = new Uint8Array(as.frequencyBinCount);
            as.getByteFrequencyData(fd);
            var td = new Uint8Array(as.fftSize);
            as.getByteTimeDomainData(td);
            return;
        };
        ctx.decodeAudioData(b.buffer, function (bf) {
            visualize(ctx, bf);
        }, function (e) {
        });
        isValid = true;
    };
    var toMp3Buffer = function () {
        return Buffer["Mp3"];
    };
    var toImdBuffer = function () {
    };
    var toImdText = function () {
    };
    this.FromBuffer = function (buffer, ext) {
        if(buffer.length == 0) {
            return;
        }
        switch(ext) {
            case"mp3":
                fromMp3Buffer(buffer);
                break;
        }
    };
    this.ToBuffer = function (ext) {
        switch(ext) {
            case"mp3":
                return toMp3Buffer();
                break;
            case"xml":
                if(typeof Buffer["Imd"] === "undefined") {
                    Buffer["Imd"] = toImdBuffer();
                }
                return Buffer["Imd"];
                break;
        }
    };
    this.ToText = function (ext) {
        switch(ext) {
            case"imd":
                if(typeof Text["Imd"] === "undefined") {
                    Text["Imd"] = toImdText();
                }
                return Text["Imd"];
                break;
        }
    };
    this.IsValid = function () {
        return isValid;
    };
    this.FromBuffer(buffer, ext);
}
function Pak(buffer, ext) {
    var Data = [];
    var Index = 0;
    var isValid = false;
    var reset = function () {
        Data = [];
        Index = 0;
        isValid = false;
    };
    var fromZipBuffer = function (buffer) {
        var readHeader = function () {
            while(p + 3 < buffer.length) {
                if((buffer[p] == 0x50) && (buffer[p + 1] == 0x4b)) {
                    var v = readInt(4);
                    switch(v) {
                        case 0x04034b50:
                        case 0x08074b50:
                        case 0x08064b50:
                        case 0x02014b50:
                        case 0x05054b50:
                        case 0x06064b50:
                        case 0x07064b50:
                        case 0x06054b50:
                        case 0x30304b50:
                            return v;
                            break;
                    }
                    p -= 2;
                } else {
                    p += 1;
                }
            }
            return 0;
        };
        var readInt = function (n) {
            var r = buffer.getInt(n, p, true);
            p += n;
            return r;
        };
        var readDateTime = function () {
            var r = readInt(4);
            var date = (r & 0xFFFF0000) >> 16;
            var time = r & 0x0000FFFF;
            return new Date(1980 + ((date & 0xFE00) >> 9), ((date & 0x01E0) >> 5) - 1, date & 0x001F, (time & 0xF800) >> 11, (time & 0x07E0) >> 5, (time & 0x001F) * 2, 0).format();
        };
        var readText = function (n) {
            var l = n;
            for(var i = 0; i < n; i++) {
                if(buffer[p + n - i - 1] == 0) {
                    l -= 1;
                } else {
                    break;
                }
            }
            var r = buffer.getText(p, l);
            p += n;
            return r;
        };
        var p = 0;
        while(true) {
            var header = readHeader();
            switch(header) {
                case 0x04034b50:
                    var dVersion = readInt(2);
                    var flag = readInt(2);
                    var method = readInt(2);
                    if(method != 8) {
                        return;
                    }
                    var datetime = readDateTime();
                    var crc32 = readInt(4);
                    var cSize = readInt(4);
                    var uSize = readInt(4);
                    var nLength = readInt(2);
                    var eLength = readInt(2);
                    var name = readText(nLength);
                    var extra = readText(eLength);
                    if(name[name.length - 1] != "/") {
                        crc32 = (function () {
                            var a = new Uint8Array(4);
                            a.setInt32(crc32, 0, false);
                            return a.getHex();
                        })();
                        Data.push({"path": name, "buffer": buffer.slice(p, p + cSize), "datetime": datetime, "crc32": crc32, "size": uSize, "checked": false});
                    }
                    p += cSize;
                    if(flag >> 4 & 1) {
                        var crc32_d = readInt(4);
                        var cSize_d = readInt(4);
                        var uSize_d = readInt(4);
                    }
                    break;
                case 0x08074b50:
                    break;
                case 0x08064b50:
                    var eLength = readInt(4);
                    var extra = readText(eLength);
                    break;
                case 0x02014b50:
                    var mVersion = readInt(2);
                    var dVersion = readInt(2);
                    var flag = readInt(2);
                    var method = readInt(2);
                    var datetime = readDateTime();
                    var crc32 = readInt(4);
                    var cSize = readInt(4);
                    var uSize = readInt(4);
                    var nLength = readInt(2);
                    var eLength = readInt(2);
                    var cLength = readInt(2);
                    var sNumber = readInt(2);
                    var iAttributes = readInt(2);
                    var eAttributes = readInt(4);
                    var offset = readInt(4);
                    var name = readText(nLength);
                    var extra = readText(eLength);
                    var comment = readText(cLength);
                    break;
                case 0x05054b50:
                    var sLength = readInt(2);
                    var signature = readText(sLength);
                    break;
                case 0x06064b50:
                    var eSize = readInt(8);
                    var mVersion = readInt(2);
                    var dVersion = readInt(2);
                    var cNumber = readInt(4);
                    var sNumber = readInt(4);
                    var cCount = readInt(8);
                    var tCount = readInt(8);
                    var size = readInt(8);
                    var offset = readInt(8);
                    var e = p + eSize - 44;
                    while(p < e) {
                        var id = readInt(2);
                        var length = readInt(4);
                        var value = readText(length);
                    }
                    break;
                case 0x07064b50:
                    var sNumber = readInt(4);
                    var offset = readInt(8);
                    var tCount = readInt(4);
                    break;
                case 0x06054b50:
                    var cNumber = readInt(2);
                    var sNumber = readInt(2);
                    var cCount = readInt(2);
                    var tCount = readInt(2);
                    var size = readInt(4);
                    var offset = readInt(4);
                    var cLength = readInt(2);
                    var comment = readText(cLength);
                    break;
                case 0x30304b50:
                    break;
            }
            if(header == 0) {
                break;
            }
        }
        isValid = true;
    };
    var from2dxBuffer = function (buffer) {
        var readInt32 = function () {
            var r = buffer.getInt32(p, true);
            p += 4;
            return r;
        };
        var pointer = [];
        var p = 0;
        p += 72;
        pointer[0] = readInt32();
        while(p < pointer[0]) {
            var v = readInt32();
            if(v < buffer.length) {
                pointer.push(v);
            }
        }
        var l = pointer.length.toString().length;
        for(var i = 0; i < pointer.length; i++) {
            Data.push({"path": i.toString().fill(l, "0", true) + ".wav", "buffer": buffer.slice(pointer[i] + 24, (i != pointer.length - 1 ? pointer[i + 1] : buffer.length)), "checked": false});
        }
        isValid = true;
    };
    var checkBuffer = function (ext) {
        if(!Data[Index]["checked"]) {
            var tb = new Date();
            switch(ext) {
                case"zip":
                case"apk":
                case"ipa":
                case"osz":
                case"mcz":
                    Data[Index]["buffer"] = CompressZip(false, Data[Index]["buffer"], Data[Index]["size"]);
                    var ta = new Date();
                    if(Data[Index]["crc32"] == Data[Index]["buffer"].getCRC32()) {
                        Data[Index]["checked"] = true;
                        Data[Index]["time"] = ta - tb;
                    }
                    break;
                case"2dx":
                    var ta = new Date();
                    Data[Index]["checked"] = true;
                    Data[Index]["time"] = ta - tb;
                    break;
            }
        }
    };
    this.ToBuffer = function (ext) {
        checkBuffer(ext);
        return Data[Index]["buffer"];
    };
    this.ToText = function (ext, type) {
        checkBuffer(ext);
        var d = [];
        d.push("path: " + GetFileDirectory(Data[Index]["path"]));
        d.push("name: " + GetFileName(Data[Index]["path"]));
        d.push("type: " + type);
        d.push("byte: " + Data[Index]["buffer"].length + " (" + Data[Index]["buffer"].length.toByteLength(2) + ")");
        if(typeof Data[Index]["datetime"] !== "undefined") {
            d.push("date: " + Data[Index]["datetime"]);
        }
        d.push("time: " + (typeof Data[Index]["time"] !== "undefined" ? Data[Index]["time"] + "ms" : "Error"));
        return d.join("\r\n");
    };
    this.FromBuffer = function (buffer, ext) {
        if(buffer.length == 0) {
            return;
        }
        reset();
        switch(ext) {
            case"zip":
            case"apk":
            case"ipa":
            case"osz":
            case"mcz":
                fromZipBuffer(buffer);
                break;
            case"2dx":
                from2dxBuffer(buffer);
                break;
        }
        if(isValid) {
            var list = [];
            for(var i = 0; i < Data.length; i++) {
                list[i] = Data[i]["path"];
            }
            SetPakList(list);
        }
    };
    this.IsValid = function () {
        return isValid;
    };
    this.Choose = function (name) {
        for(var i = 0; i < Data.length; i++) {
            if(Data[i]["path"] == name) {
                Index = i;
                break;
            }
        }
    };
    this.FromBuffer(buffer, ext);
}

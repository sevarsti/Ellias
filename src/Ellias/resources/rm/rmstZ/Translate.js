"use strict";
function Mst(info) {
    var buffer = info.buffer;
    var ext = info.extension;
    var type = info.type;
    var isValid;
    var Raw;
    var Semi;
    var Buffer;
    var Text;
    var Checks;
    var reset = function () {
        var m = (arguments.length > 0 ? arguments[0] : "all");
        switch(m) {
            case "preview":
                Text["Pngbms"] = {};
                Text["Pngosu"] = {};
                Text["Pngimd"] = {};
                Text["Pngmc"] = {};
                Text["Pngaff"] = {};
                Text["Htmlbms"] = {};
                Text["Htmlosu"] = {};
                Text["Htmlimd"] = {};
                Text["Htmlmc"] = {};
                Text["Htmlaff"] = {};
                break;
            case "all":
                isValid = false;
                Semi = getSemi();
                Buffer = {};
                Buffer["Bms"] = [];
                Buffer["Bme"] = [];
                Buffer["Bml"] = [];
                Buffer["Pms"] = [];
                Buffer["Vosvos000"] = [];
                Buffer["Vosvos001"] = [];
                Buffer["Vosvos006"] = [];
                Buffer["Vosvos022"] = [];
                Buffer["Mid"] = [];
                Buffer["Lrc"] = [];
                Buffer["Hexvosvos000"] = [];
                Buffer["Hexvosvos001"] = [];
                Buffer["Hexvosvos006"] = [];
                Buffer["Hexvosvos022"] = [];
                Buffer["Heximd"] = [];
                Buffer["Tjataiko"] = {};
                Buffer["Tjajube"] = {};
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
                Buffer["Mst"] = {};
                Text = {};
                Text["Bms"] = [];
                Text["Bme"] = [];
                Text["Bml"] = [];
                Text["Pms"] = [];
                Text["Vosvos000"] = {};
                Text["Vosvos001"] = {};
                Text["Vosvos006"] = {};
                Text["Vosvos022"] = {};
                Text["Mid"] = {};
                Text["Lrc"] = {};
                Text["Hexvosvos000"] = {};
                Text["Hexvosvos001"] = {};
                Text["Hexvosvos006"] = {};
                Text["Hexvosvos022"] = {};
                Text["Heximd"] = [];
                Text["Tjataiko"] = {};
                Text["Tjajube"] = {};
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
                Text["Mst"] = {};
                Text["Pngbms"] = {};
                Text["Pngosu"] = {};
                Text["Pngimd"] = {};
                Text["Pngmc"] = {};
                Text["Pngaff"] = {};
                Text["Htmlbms"] = {};
                Text["Htmlosu"] = {};
                Text["Htmlimd"] = {};
                Text["Htmlmc"] = {};
                Text["Htmlaff"] = {};
                break;
        }
    };
    var readMeta = function (f) {
        var d = (arguments.length > 1 ? arguments[1] : undefined);
        var index = (arguments.length > 2 ? arguments[2] : 0);
        for(var i = 0; i < f.length; i++) {
            if(!isUndefined(Semi["meta"][f[i]])) {
                switch(f[i]) {
                    case "Course":
                    case "Level":
                    case "Player":
                    case "Style":
                    case "Balloon":
                    case "ScoreInit":
                    case "ScoreDiff":
                    case "ScoreMode":
                        if(!isUndefined(Meta[f[i]][index])) {
                            return Meta[f[i]][index];
                        }
                        break;
                    default:
                        if(!isUndefined(Meta[f[i]])) {
                            return Meta[f[i]];
                        }
                        break;
                }
            }
        }
        return d;
    };

    var toActionDataOneTrack = function (a, bpm) {
        var r = [];
        var d = (function () {
            var r = [];
            r = toActionDataNoIntersect(toActionDataNoSlide(a));
            for(var i = 0; i < r.length; i++) {
                if(r[i][2] == 0x02) {
                    if(!r[i][3].isClose(60000 / bpm) && (r[i][3] < 60000 / bpm)) {
                        r[i][2] = 0x00;
                        r[i][3] = 0;
                    }
                }
            }
            for(var i = r.length - 1; i > 0 - 1; i--) {
                if(r[i][2] == 0x02) {
                    var isOccupy = (function () {
                        for(var j = 0; j < r.length; j++) {
                            if((j != i) && (r[j][2] == 0x00) && (r[j][0] > r[i][0]) && (r[j][0] < r[i][0] + r[i][3]) && !r[j][0].isClose(r[i][0]) && !r[j][0].isClose(r[i][0] + r[i][3])) {
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
                        if((j != i) && (r[j][2] == 0x00) && (r[j][0].isClose(r[i][0]) || r[j][0].isClose(r[i][0] + r[i][3]))) {
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
                        if((j != i) && (r[j][2] == 0x02) && (((r[j][0] > r[i][0]) && (r[j][0] < r[i][0] + r[i][3])) || r[j][0].isClose(r[i][0]) || r[j][0].isClose(r[i][0] + r[i][3]))) {
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
                    if(d[j][0].isClose(d[i][0])) {
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
        var d = toActionDataNoIntersect(toActionDataTransTrack(a, squareX));
        var u0 = 60000 / bpm;
        var u1 = 60000 / bpm / squareY * (squareY - 1);
        var u2 = 60000 / bpm / squareY;
        var addLongX = function (t1, t2, tr1, tr2) {
            r.push([t1, tr1, tr2 + 10, t2]);
        };
        var addLongY = function (t1, t2, tr) {
            while((t2 > u1) || (t2.isClose(u1))) {
                r.push([t1, tr, 0x02, u1]);
                t1 += u0;
                t2 -= u0;
            }
            if((t2 > u2) || (t2.isClose(u2))) {
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
                            if(t.length > 0) {
                                addLongX(t[0], t[3], t[1], t[2]);
                            }
                            addLongY(d[i][0], d[i][3], d[i][1]);
                            break;
                        } else if((d[i][2] == 0x62) || (d[i][2] == 0x22)) {
                            if((i != d.length - 1) && ((d[i + 1][2] == 0x21) || (d[i + 1][2] == 0xA1)) && (d[i][3].isClose(u2))) {
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
                                if(t.length > 0) {
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
                                if(r[i][3].isClose(u) && (r[i + 1][3] == d)) {
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
            if(!t[i][3].isClose(60000 / bpm / 2) && (t[i][3] < 60000 / bpm / 2)) {
                r.inserts([
                              [t[i][0], t[i][1], 0x00, 0]
                          ], i);
            } else {
                if(t[i][5] == 0) {
                    r.inserts([
                                  [t[i][0], t[i][1], 0x02, t[i][3]]
                              ], i);
                } else {
                    r.inserts([
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
                            return -1;
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
                    if(v[i][0].isClose(l[j][0]) || ((v[i][0] >= l[j][0]) && (v[i][0] <= l[j][1])) || v[i][0].isClose(l[j][1])) {
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
                            if(s[j][1].length > 0) {
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
                        return -1;
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

    var copyObject = function (object) {
        if(isUndefined(object) || (object === null)) {
            return object;
        }
        if(object instanceof Float32Array) {
            var r = new Float32Array(object.length);
            for(var i = 0; i < object.length; i++) {
                r[i] = object[i];
            }
            return r;
        }
        if(Array.isArray(object)) {
            var r = [];
            for(var i = 0; i < object.length; i++) {
                r[i] = copyObject(object[i]);
            }
            return r;
        }
        if(object instanceof Object) {
            var r = {};
            for(var t in object) {
                if(object.hasOwnProperty(t)) {
                    r[t] = copyObject(object[t]);
                }
            }
            return r;
        }
        return object;
    };
    var mergeObject = function (object, value, isCover) {
        for(var name in value) {
            if(isCover) {
                object[name] = copyObject(value[name]);
            } else {
                object[name] = object[name] || copyObject(value[name]);
            }
        }
        return object;
    };
    var newData = function (mode) {
        var r = {};
        switch(mode) {
            case "file":
                r["name"] = "";
                r["base64"] = "";
                break;
            case "when":
                r["b"] = 0;
                r["n"] = 0;
                r["t"] = 0;
                break;
            case "where":
                r["x"] = -1;
                r["y"] = -1;
                r["z"] = -1;
                break;
            case "link":
                r["b"] = -1;
                r["c"] = -1;
                r["a"] = -1;
                break;
            case "line":
                r["vector"] = [];
                r["clockwise"] = "";
                r["follow"] = "";
                r["combo"] = 0;
                r["curve"] = "";
                break;
            case "matrix":
                r["a"] = 0;
                r["b"] = 0;
                r["c"] = 0;
                r["d"] = 0;
                r["e"] = 0;
                r["f"] = 0;
                r["g"] = 0;
                r["h"] = 0;
                r["i"] = 0;
                r["j"] = 0;
                r["k"] = 0;
                r["l"] = 0;
                r["m"] = 0;
                r["m"] = 0;
                r["o"] = 0;
                r["p"] = 0;
                break;
            case "note":
                r["when"] = newData("when");
                r["where"] = newData("where");
                r["link"] = newData("link");
                r["line"] = newData("line");
                r["group"] = "";
                r["auto"] = "";
                r["hitEffect"] = [];
                break;
            case "beat":
                r["when"] = newData("when");
                r["bpm"] = 125;
                r["bpn"] = 4;
                r["flow"] = 125;
                break;
            case "effect":
                r["type"] = "";
                r["when"] = newData("when");
                r["where"] = newData("where");
                r["name"] = "";
                r["start"] = 0;
                r["duration"] = 0;
                r["volume"] = 1;
                r["speed"] = 1;
                r["repeat"] = "";
                break;
            case "transform":
                r["when"] = newData("when");
                r["matrix"] = newData("matrix");
                break;
        }
        return r;
    };
    var beatFromNote = function (data, note) {
        var d = (arguments.length > 2 ? arguments[2] : Checks["CorrectTime"]);
        note = CheckNumber(note);
        if(isUndefined(note)) {
            return;
        }
        var isNegative = (note < 0);
        note = Math.abs(note);
        var v = 0;
        for(var i = 0; i < data["beat"].length; i++) {
            var u = data["beat"][i]["bpn"];
            if((i < data["beat"].length - 1) && (note >= data["beat"][i + 1]["when"]["n"])) {
                v += (data["beat"][i + 1]["when"]["n"] - data["beat"][i]["when"]["n"]) * u;
            } else {
                v += (note - data["beat"][i]["when"]["n"]) * u;
                break;
            }
        }
        v = v.attach(d);
        return (isNegative ? -v : v);
    };
    var noteFromBeat = function (data, beat) {
        var d = (arguments.length > 2 ? arguments[2] : Checks["CorrectTime"] * 4);
        beat = CheckNumber(beat);
        if(isUndefined(beat)) {
            return;
        }
        var isNegative = (beat < 0);
        beat = Math.abs(beat);
        var v = 0;
        for(var i = 0; i < data["beat"].length; i++) {
            var u = data["beat"][i]["bpn"];
            if((i < data["beat"].length - 1) && (beat >= data["beat"][i + 1]["when"]["b"])) {
                v += (data["beat"][i + 1]["when"]["b"] - data["beat"][i]["when"]["b"]) / u;
            } else {
                v += (beat - data["beat"][i]["when"]["b"]) / u;
                break;
            }
        }
        v = v.attach(d);
        return (isNegative ? -v : v);
    };
    var beatFromTime = function (data, time) {
        var d = (arguments.length > 2 ? arguments[2] : Checks["CorrectTime"]);
        time = CheckNumber(time);
        if(isUndefined(time)) {
            return;
        }
        var isNegative = (time < 0);
        time = Math.abs(time);
        var v = 0;
        for(var i = 0; i < data["beat"].length; i++) {
            var u = 60000 / data["beat"][i]["bpm"] / data["beat"][i]["bpn"] * 4;
            if((i < data["beat"].length - 1) && (time >= data["beat"][i + 1]["when"]["t"])) {
                v += (data["beat"][i + 1]["when"]["t"] - data["beat"][i]["when"]["t"]) / u;
            } else {
                v += (time - data["beat"][i]["when"]["t"]) / u;
                break;
            }
        }
        v = v.attach(d);
        return (isNegative ? -v : v);
    };
    var timeFromBeat = function (data, beat) {
        beat = CheckNumber(beat);
        if(isUndefined(beat)) {
            return;
        }
        var isNegative = (beat < 0);
        beat = Math.abs(beat);
        var v = 0;
        for(var i = 0; i < data["beat"].length; i++) {
            var u = 60000 / data["beat"][i]["bpm"] / data["beat"][i]["bpn"] * 4;
            if((i < data["beat"].length - 1) && (beat >= data["beat"][i + 1]["when"]["b"])) {
                v += (data["beat"][i + 1]["when"]["b"] - data["beat"][i]["when"]["b"]) * u;
            } else {
                v += (beat - data["beat"][i]["when"]["b"]) * u;
                break;
            }
        }
        v = v.toAbbreviated(6);
        return (isNegative ? -v : v);
    };
    var noteFromTime = function (data, time) {
        return noteFromBeat(data, beatFromTime(data, time));
    };
    var timeFromNote = function (data, note) {
        return timeFromBeat(data, beatFromNote(data, note));
    };
    var axisFromNote = function (data, note) {
        note = note % 1;
        return (note < 0.5 ? note : (1 - note) * 0.999) * 2;
    };
    var indexFromCurrent = function (note, current) {
        if(current > -1) {
            for(var i = 0; i < note.length; i++) {
                if(note[i]["link"]["c"] == current) {
                    return i;
                }
            }
        }
        return -1;
    };
    var getFileIndex = function (name) {
        for(var i = 0; i < Semi["file"].length; i++) {
            if(Semi["file"][i]["name"] == name) {
                return i;
            }
        }
        return -1;
    };
    var getEffectOffset = function (index, type) {
        var r = 0;
        for(var i = 0; i < Semi["data"][index]["effect"].length; i++) {
            if((Semi["data"][index]["effect"][i]["type"] == type) && (Semi["data"][index]["effect"][i]["when"]["t"] != 0)) {
                r = Semi["data"][index]["effect"][i]["when"]["t"];
                break;
            }
        }
        for(var i = 0; i < Semi["data"][index]["effect"].length; i++) {
            if((Semi["data"][index]["effect"][i]["type"] == type) && (r > Semi["data"][index]["effect"][i]["when"]["t"])) {
                r = Semi["data"][index]["effect"][i]["when"]["t"];
            }
        }
        return r;
    };
    var classifyNote = function (note) {
        var r = {"tab": [], "hold": [], "slide": [], "drag": [], "all": []};
        var d = copyObject(note);
        for(var i = 0; i < d.length; i++) {
            if(indexFromCurrent(note, d[i]["link"]["b"]) == -1) {
                var t = [];
                var n = i;
                while(true) {
                    if(d[n]["link"]["c"] == -1) {
                        break;
                    }
                    t.push(copyObject(d[n]));
                    d[n]["link"]["c"] = -1;
                    var a = indexFromCurrent(note, d[n]["link"]["a"]);
                    if(a != -1) {
                        n = a;
                    } else {
                        var l = (function () {
                            for(var j = 0; j < d.length; j++) {
                                if((d[j]["link"]["c"] != -1) && (indexFromCurrent(note, d[j]["link"]["b"]) == -1) && (d[j]["when"]["b"] == d[n]["when"]["b"]) && (d[j]["where"]["x"] == d[n]["where"]["x"]) && (d[j]["where"]["y"] == d[n]["where"]["y"]) && (d[j]["where"]["z"] == d[n]["where"]["z"]) && (d[j]["group"] == d[n]["group"]) && (((d[j]["auto"] == "true") && (d[n]["auto"] == "true")) || ((d[j]["auto"] != "true") && (d[n]["auto"] != "true"))) && (indexFromCurrent(note, d[j]["link"]["a"]) != -1)) {
                                    return j;
                                }
                            }
                            return -1;
                        })();
                        if(l != -1) {
                            d[l]["link"]["c"] = -1;
                            var na = indexFromCurrent(note, d[l]["link"]["a"]);
                            if(na != -1) {
                                n = na;
                                d[n]["link"]["b"] = t[t.length - 1]["link"]["c"];
                                t[t.length - 1]["link"]["a"] = n;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if(t.length > 0) {
                    switch(t.length) {
                        case 1:
                            r["tab"].push(t);
                            break;
                        case 2:
                            if(t[0]["when"]["b"] == t[1]["when"]["b"]) {
                                r["slide"].push(t);
                            } else {
                                if((t[0]["where"]["x"] == t[1]["where"]["x"]) && (t[0]["where"]["y"] == t[1]["where"]["y"]) && (t[0]["where"]["z"] == t[1]["where"]["z"])) {
                                    r["hold"].push(t);
                                } else {
                                    r["drag"].push(t);
                                }
                            }
                            break;
                        default:
                            r["drag"].push(t);
                            break;
                    }
                    r["all"].push(t);
                }
            }
        }
        var s = function (a, b) {
            if(!isUndefined(a[0]["when"]) && !isUndefined(b[0]["when"])) {
                if(!isUndefined(a[0]["when"]["b"]) && !isUndefined(b[0]["when"]["b"]) && (a[0]["when"]["b"] != b[0]["when"]["b"])) {
                    return a[0]["when"]["b"] - b[0]["when"]["b"];
                } else if(!isUndefined(a[0]["when"]["n"]) && !isUndefined(b[0]["when"]["n"]) && (a[0]["when"]["n"] != b[0]["when"]["n"])) {
                    return a[0]["when"]["n"] - b[0]["when"]["n"];
                } else if(!isUndefined(a[0]["when"]["t"]) && !isUndefined(b[0]["when"]["t"]) && (a[0]["when"]["t"] != b[0]["when"]["t"])) {
                    return a[0]["when"]["t"] - b[0]["when"]["t"];
                }
            }
            if(!isUndefined(a[0]["where"]) && !isUndefined(b[0]["where"])) {
                if(!isUndefined(a[0]["where"]["xt"]) && !isUndefined(b[0]["where"]["xt"]) && (a[0]["where"]["xt"] != b[0]["where"]["xt"])) {
                    return a[0]["where"]["xt"] - b[0]["where"]["xt"];
                }
                if(!isUndefined(a[0]["where"]["yt"]) && !isUndefined(b[0]["where"]["yt"]) && (a[0]["where"]["yt"] != b[0]["where"]["yt"])) {
                    return a[0]["where"]["yt"] - b[0]["where"]["yt"];
                }
                if(!isUndefined(a[0]["where"]["zt"]) && !isUndefined(b[0]["where"]["zt"]) && (a[0]["where"]["zt"] != b[0]["where"]["zt"])) {
                    return a[0]["where"]["zt"] - b[0]["where"]["zt"];
                } else if(!isUndefined(a[0]["where"]["x"]) && !isUndefined(b[0]["where"]["x"]) && (a[0]["where"]["x"] != b[0]["where"]["x"])) {
                    return a[0]["where"]["x"] - b[0]["where"]["x"];
                } else if(!isUndefined(a[0]["where"]["y"]) && !isUndefined(b[0]["where"]["y"]) && (a[0]["where"]["y"] != b[0]["where"]["y"])) {
                    return a[0]["where"]["y"] - b[0]["where"]["y"];
                } else if(!isUndefined(a[0]["where"]["z"]) && !isUndefined(b[0]["where"]["z"]) && (a[0]["where"]["z"] != b[0]["where"]["z"])) {
                    return a[0]["where"]["z"] - b[0]["where"]["z"];
                }
            }
            return a.length - b.length;
        };
        r["tab"] = r["tab"].sort(s);
        r["hold"] = r["hold"].sort(s);
        r["slide"] = r["slide"].sort(s);
        r["drag"] = r["drag"].sort(s);
        r["all"] = r["all"].sort(s);
        var p = 0;
        var reindex = function (a) {
            for(var i = 0; i < a.length; i++) {
                for(var j = 0; j < a[i].length; j++) {
                    a[i][j]["link"]["c"] = p + j;
                    a[i][j]["link"]["b"] = (j != 0 ? a[i][j]["link"]["c"] - 1 : -1);
                    a[i][j]["link"]["a"] = (j != a[i].length - 1 ? a[i][j]["link"]["c"] + 1 : -1);
                }
                p += a[i].length;
            }
            return a;
        };
        p = 0;
        r["all"] = reindex(r["all"]);
        return r;
    };
    var sortBeat = function (beat) {
        var r = copyObject(beat);
        var s = function (a, b) {
            if(!isUndefined(a["when"]["b"]) && !isUndefined(b["when"]["b"])) {
                return a["when"]["b"] - b["when"]["b"];
            } else if(!isUndefined(a["when"]["n"]) && !isUndefined(b["when"]["n"])) {
                return a["when"]["n"] - b["when"]["n"];
            } else if(!isUndefined(a["when"]["t"]) && !isUndefined(b["when"]["t"])) {
                return a["when"]["t"] - b["when"]["t"];
            }
        };
        r = r.sort(s);
        return r;
    };
    var sortNote = function (note) {
        var r = [];
        var a = classifyNote(note)["all"];
        for(var i = 0; i < a.length; i++) {
            r.writes(a[i]);
        }
        return r;
    };
    var preData = function (mode) {
        switch(mode) {
            case "pcm":
                for(var index = 0; index < Semi["data"].length; index++) {
                    for(var i = 0; i < Semi["data"][index]["effect"].length; i++) {
                        if(Semi["data"][index]["effect"][i]["type"] == "audio") {
                            var l = getFileIndex(Semi["data"][index]["effect"][i]["name"]);
                            if(l != -1) {
                                Semi["file"][l]["pcm"] = Semi["file"][l]["pcm"] || {};
                                Semi["file"][l]["pcm"]["sampleRate"] = Semi["file"][l]["pcm"]["sampleRate"] || 48000;
                                Semi["file"][l]["pcm"]["channelData"] = Semi["file"][l]["pcm"]["channelData"] || [];
                                if(Semi["file"][l]["pcm"]["channelData"].length == 0) {
                                    var audioBuffer = new Uint8Array().fromBase64(Semi["file"][l]["base64"]);
                                    if(audioBuffer.length != 0) {
                                        new AudioContext().decodeAudioData(audioBuffer.buffer, function (data) {
                                            var channelData = [];
                                            for(var j = 0; j < data.numberOfChannels; j++) {
                                                channelData[j] = data.getChannelData(j);
                                            }
                                            Semi["file"][l]["pcm"]["sampleRate"] = data.sampleRate;
                                            Semi["file"][l]["pcm"]["channelData"] = channelData;
                                            Raw["file"][l]["pcm"] = copyObject(Semi["file"][l]["pcm"]);
                                        });
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case "note":
                for(var index = 0; index < Semi["data"].length; index++) {
                    var length = (function () {
                        var v = 0;
                        for(var i = 0; i < Semi["data"][index]["effect"].length; i++) {
                            if(Semi["data"][index]["effect"][i]["type"] == "audio") {
                                var l = getFileIndex(Semi["data"][index]["effect"][i]["name"]);
                                if((l != -1) && (Semi["file"][l]["pcm"]["sampleRate"] != 0) && (Semi["file"][l]["pcm"]["channelData"].length != 0)) {
                                    for(var j = 0; j < Semi["file"][l]["pcm"]["channelData"].length; j++) {
                                        var t = Semi["file"][l]["pcm"]["channelData"][j].length / Semi["file"][l]["pcm"]["sampleRate"];
                                        if(v < t) {
                                            v = t;
                                        }
                                    }
                                }
                            }
                        }
                        return v;
                    })();
                    Raw["data"][index]["info"] = {"length": {"when": {"t": Math.round(length * 1000)}}};
                }
                setRaw(Raw);
                break;
        }
    };
    var toData = function (data, mode) {
        var r = copyObject(data);
        var getLinkTracks = function (t, k) {
            var v = [];
            var d = Math.floor(k * 0.5);
            for(var i = 1; i < d + 1; i++) {
                if(t - i >= 0) {
                    v.push(t - i);
                }
                if(t + i <= k - 1) {
                    v.push(t + i);
                }
            }
            return v;
        };
        var canLink = function (a, b, u1, u2) {
            var d = (arguments.length > 4 ? arguments[4] : "undefined");
            for(var i = 0; i < r["note"].length; i++) {
                if(r["note"][i]["link"]["c"] == -1) {
                    continue;
                }
                var p = (function () {
                    for(var j = 0; j < u1.length; j++) {
                        if(i == u1[j]) {
                            return true;
                        }
                        if((r["note"][i]["link"]["a"] != -1) && (r["note"][i]["link"]["a"] == u1[j])) {
                            return true;
                        }
                    }
                    for(var j = 0; j < u2.length; j++) {
                        if(i == u2[j]) {
                            return true;
                        }
                        if((r["note"][i]["link"]["a"] != -1) && (r["note"][i]["link"]["a"] == u2[j])) {
                            return true;
                        }
                    }
                    return false;
                })();
                if(p) {
                    continue;
                }
                var c = (function () {
                    if(r["note"][i]["link"]["a"] != -1) {
                        return Math.isLineCross(a, b, [r["note"][i]["where"]["xt"], r["note"][i]["when"]["b"]], [r["note"][r["note"][i]["link"]["a"]]["where"]["xt"], r["note"][r["note"][i]["link"]["a"]]["when"]["b"]]);
                    } else {
                        return Math.isLineCross(a, b, [r["note"][i]["where"]["xt"], r["note"][i]["when"]["b"]], [r["note"][i]["where"]["xt"], r["note"][i]["when"]["b"]]);
                    }
                })();
                switch(d) {
                    case "true":
                        if(c == true) {
                            return false;
                        }
                        break;
                    case "false":
                        if(c == false) {
                            return false;
                        }
                        break;
                    case "undefined":
                        if(!isUndefined(c)) {
                            return false;
                        }
                        break;
                }
            }
            return true;
        };
        switch(mode) {
            case "XAxisRemove":
                for(var i = 0; i < r["note"].length; i++) {
                    r["note"][i]["where"]["x"] = -1;
                }
                break;
            case "YAxisRemove":
                for(var i = 0; i < r["note"].length; i++) {
                    r["note"][i]["where"]["y"] = -1;
                }
                break;
            case "ZAxisRemove":
                for(var i = 0; i < r["note"].length; i++) {
                    r["note"][i]["where"]["z"] = -1;
                }
                break;
            case "XAxisExtend":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["where"]["x"] == -1) {
                        r["note"][i]["where"]["x"] = axisFromNote(r, r["note"][i]["when"]["n"] + r["note"][i]["when"]["n"] % 4 / 4);
                    }
                }
                break;
            case "YAxisExtend":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["where"]["y"] == -1) {
                        r["note"][i]["where"]["y"] = axisFromNote(r, r["note"][i]["when"]["n"]);
                    }
                }
                break;
            case "ZAxisExtend":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["where"]["z"] == -1) {
                        r["note"][i]["where"]["z"] = axisFromNote(r, r["note"][i]["when"]["n"] - r["note"][i]["when"]["n"] % 4 / 4);
                    }
                }
                break;
            case "InvalidKeyRemove":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    if(r["note"][i]["auto"] == "true") {
                        r["note"][i]["link"]["c"] = -1;
                    }
                }
                r["note"] = sortNote(r["note"]);
                break;
            case "FlowRemove":
                for(var i = 0; i < r["beat"].length; i++) {
                    r["beat"][i]["flow"] = r["beat"][i]["bpm"] / r["beat"][i]["bpn"] * 4;
                }
                break;
            case "TransformRemove":
                r["transform"] = [];
                break;
            case "InvalidKeyExtend":
                break;
            case "FlowExtend":
                break;
            case "TransformExtend":
                break;
            case "TransKey":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["where"]["x"] != -1) {
                        r["note"][i]["where"]["xt"] = Math.floor(r["note"][i]["where"]["x"] * Checks["TransKey"]);
                        r["note"][i]["where"]["xp"] = r["note"][i]["where"]["x"] * Checks["TransKey"] - r["note"][i]["where"]["xt"];
                    }
                }
                r["info"]["key"] = Checks["TransKey"];
                break;
            case "MirrorTrack":
                for(var i = 0; i < r["note"].length; i++) {
                    if(!isUndefined(r["note"][i]["where"]["xt"])) {
                        r["note"][i]["where"]["xt"] = r["info"]["key"] - 1 - r["note"][i]["where"]["xt"];
                    }
                    if(!isUndefined(r["note"][i]["where"]["tp"])) {
                        r["note"][i]["where"]["tp"] = 1 - r["note"][i]["where"]["tp"];
                    }
                    if(r["note"][i]["where"]["x"] != -1) {
                        r["note"][i]["where"]["x"] = 1 - r["note"][i]["where"]["x"];
                    }
                }
                break;
            case "RandomTrack":
                var tracks = (function () {
                    var v = [];
                    for(var i = 0; i < r["info"]["key"]; i++) {
                        v.push(i);
                    }
                    return v;
                })();
                var newTracks = (function () {
                    var v = [];
                    for(var i = 0; i < r["info"]["key"]; i++) {
                        var n = Math.floor(Math.random() * tracks.length);
                        v.push(tracks[n]);
                        tracks.splice(n, 1);
                    }
                    return v;
                })();
                for(var i = 0; i < r["note"].length; i++) {
                    if(!isUndefined(r["note"][i]["where"]["xt"])) {
                        r["note"][i]["where"]["xt"] = newTracks(r["note"][i]["where"]["xt"]);
                    }
                    if((r["note"][i]["where"]["x"] != -1) && !isUndefined(r["note"][i]["where"]["xt"]) && !isUndefined(r["note"][i]["where"]["tp"])) {
                        r["note"][i]["where"]["x"] = (r["note"][i]["where"]["xt"] + r["note"][i]["where"]["tp"]) / r["info"]["key"];
                    }
                }
                break;
            case "RandomNote":
                var tracks = (function () {
                    var v = [];
                    for(var i = 0; i < r["info"]["key"]; i++) {
                        v.push(i);
                    }
                    return v;
                })();
                var newTrack = function () {
                    return tracks[Math.floor(Math.random() * tracks.length)];
                };
                for(var i = 0; i < r["note"].length; i++) {
                    if(!isUndefined(r["note"][i]["where"]["xt"])) {
                        r["note"][i]["where"]["xt"] = newTrack();
                    }
                    if(!isUndefined(r["note"][i]["where"]["tp"])) {
                        r["note"][i]["where"]["tp"] = Math.random();
                    }
                    if((r["note"][i]["where"]["x"] != -1) && !isUndefined(r["note"][i]["where"]["xt"]) && !isUndefined(r["note"][i]["where"]["tp"])) {
                        r["note"][i]["where"]["x"] = (r["note"][i]["where"]["xt"] + r["note"][i]["where"]["tp"]) / r["info"]["key"];
                    }
                }
                break;
            case "ChangeSpeed":
                for(var i = 0; i < r["beat"].length; i++) {
                    r["beat"][i]["when"]["t"] = r["beat"][i]["when"]["t"] / Checks["ChangeSpeed"];
                    r["beat"][i]["bpm"] = r["beat"][i]["bpm"] * Checks["ChangeSpeed"];
                }
                for(var i = 0; i < r["note"].length; i++) {
                    r["note"][i]["when"]["t"] = r["note"][i]["when"]["t"] / Checks["ChangeSpeed"];
                }
                break;
            case "TrackCheck":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    if(!isUndefined(r["note"][i]["where"]["xt"]) && !Checks["TrackCheck"][r["note"][i]["where"]["xt"]]) {
                        var b = r["note"][i]["link"]["b"];
                        if(b != -1) {
                            r["note"][b]["link"]["a"] = -1;
                        }
                        var a = r["note"][i]["link"]["a"];
                        if(a != -1) {
                            r["note"][a]["link"]["b"] = -1;
                        }
                        r["note"][i]["link"]["c"] = -1;
                    }
                }
                var tracks = (function () {
                    var v = [];
                    for(var i = 0; i < r["info"]["key"]; i++) {
                        if(Checks["TrackCheck"][i]) {
                            v.push(i);
                        }
                    }
                    return v;
                })();
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    if(!isUndefined(r["note"][i]["where"]["xt"]) && !isUndefined(r["note"][i]["where"]["xp"])) {
                        r["note"][i]["where"]["x"] = (tracks.indexOf(r["note"][i]["where"]["xt"]) + r["note"][i]["where"]["xp"]) / tracks.length;
                    }
                }
                r["info"]["key"] = tracks.length;
                r["note"] = sortNote(r["note"]);
                break;
            case "LinkSingle":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    if((r["note"][i]["link"]["b"] == -1) && (r["note"][i]["link"]["a"] == -1)) {
                        var n = i;
                        var u = [];
                        while(true) {
                            u.push(n);
                            var l = (function () {
                                var w = getLinkTracks(r["note"][n]["where"]["xt"], r["info"]["key"]);
                                var a = (function () {
                                    var v = [];
                                    var t = [];
                                    for(var j = 0; j < r["note"].length; j++) {
                                        if((r["note"][j]["link"]["b"] == -1) && (r["note"][j]["link"]["a"] == -1)) {
                                            if((w.indexOf(r["note"][j]["where"]["xt"]) != -1) && (r["note"][j]["when"]["b"] - r["note"][n]["when"]["b"] > 0) && (r["note"][j]["when"]["b"] - r["note"][n]["when"]["b"] < 2)) {
                                                t.push([r["note"][j]["when"]["b"], r["note"][j]["where"]["xt"], j]);
                                            }
                                        }
                                    }
                                    var s = function (a, b) {
                                        if(a[0] != b[0]) {
                                            return a[0] - b[0];
                                        } else {
                                            return w.indexOf(a[1]) - w.indexOf(b[1]);
                                        }
                                    }
                                    t = t.sort(s);
                                    for(var j = 0; j < t.length; j++) {
                                        v.push(t[j][2]);
                                    }
                                    return v;
                                })();
                                for(var j = 0; j < a.length; j++) {
                                    if(canLink([r["note"][n]["where"]["xt"], r["note"][n]["when"]["b"]], [r["note"][n]["where"]["xt"], r["note"][a[j]]["when"]["b"]], u, [a[j]]) && canLink([r["note"][n]["where"]["xt"], r["note"][a[j]]["when"]["b"]], [r["note"][a[j]]["where"]["xt"], r["note"][a[j]]["when"]["b"]], u, [a[j]])) {
                                        return a[j];
                                    }
                                }
                                return -1;
                            })();
                            if(l != -1) {
                                r["note"].push(mergeObject(newData("note"), {"when": r["note"][l]["when"], "where": r["note"][n]["where"], "link": {"b": r["note"][n]["link"]["c"], "c": r["note"][r["note"].length - 1]["link"]["c"] + 1, "a": r["note"][l]["link"]["c"]}}, true));
                                r["note"][l]["link"]["b"] = r["note"][r["note"].length - 1]["link"]["c"];
                                r["note"][n]["link"]["a"] = r["note"][r["note"].length - 1]["link"]["c"];
                                n = l;
                                u.push(r["note"][r["note"].length - 1]["link"]["c"]);
                            } else {
                                break;
                            }
                        }
                    }
                }
                r["note"] = sortNote(r["note"]);
                break;
            case "LinkLong":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    if((r["note"][i]["link"]["a"] == -1) && (r["note"][i]["link"]["b"] != -1) && (r["note"][r["note"][i]["link"]["b"]]["when"]["b"] != r["note"][i]["when"]["b"])) {
                        var l = (function () {
                            var w = getLinkTracks(r["note"][i]["where"]["xt"], r["info"]["key"]);
                            var a = (function () {
                                var v = [];
                                var t = [];
                                for(var j = 0; j < r["note"].length; j++) {
                                    if((r["note"][j]["link"]["b"] == -1) && (r["note"][j]["link"]["a"] != -1) && (r["note"][r["note"][j]["link"]["a"]]["when"]["b"] != r["note"][j]["when"]["b"])) {
                                        if((w.indexOf(r["note"][j]["where"]["xt"]) != -1) && (r["note"][j]["when"]["b"] == r["note"][i]["when"]["b"])) {
                                            t.push([r["note"][j]["where"]["xt"], j]);
                                        }
                                    }
                                }
                                var s = function (a, b) {
                                    return w.indexOf(a[0]) - w.indexOf(b[0]);
                                }
                                t = t.sort(s);
                                for(var j = 0; j < t.length; j++) {
                                    v.push(t[j][1]);
                                }
                                return v;
                            })();
                            for(var j = 0; j < a.length; j++) {
                                if(canLink([r["note"][i]["where"]["xt"], r["note"][i]["when"]["b"]], [r["note"][a[j]]["where"]["xt"], r["note"][a[j]]["when"]["b"]], [], [
                                    [r["note"][i]["link"]["b"]],
                                    i,
                                    a[j],
                                    [r["note"][a[j]]["link"]["a"]]
                                ])) {
                                    return a[j];
                                }
                            }
                            return -1;
                        })();
                        if(l != -1) {
                            r["note"][i]["link"]["a"] = r["note"][l]["link"]["c"];
                            r["note"][l]["link"]["b"] = r["note"][i]["link"]["c"];
                        }
                    }
                }
                r["note"] = sortNote(r["note"]);
                break;
            case "LinkSlide":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    if((r["note"][i]["link"]["b"] == -1) && (r["note"][i]["link"]["a"] == -1)) {
                        var l = (function () {
                            var w = getLinkTracks(r["note"][i]["where"]["xt"], r["info"]["key"]);
                            var b = (function () {
                                var v = [];
                                var t = [];
                                for(var j = 0; j < r["note"].length; j++) {
                                    if((r["note"][j]["link"]["a"] == -1) && (r["note"][j]["link"]["b"] != -1) && (r["note"][r["note"][j]["link"]["b"]]["when"]["b"] != r["note"][j]["when"]["b"])) {
                                        if((w.indexOf(r["note"][j]["where"]["xt"]) != -1) && (r["note"][j]["when"]["b"] == r["note"][i]["when"]["b"])) {
                                            t.push([r["note"][j]["where"]["xt"], j]);
                                        }
                                    }
                                }
                                var s = function (a, b) {
                                    return w.indexOf(a[0]) - w.indexOf(b[0]);
                                }
                                t = t.sort(s);
                                for(var j = 0; j < t.length; j++) {
                                    v.push(t[j][1]);
                                }
                                return v;
                            })();
                            for(var j = 0; j < b.length; j++) {
                                if(canLink([r["note"][i]["where"]["xt"], r["note"][i]["when"]["b"]], [r["note"][b[j]]["where"]["xt"], r["note"][b[j]]["when"]["b"]], [], [r["note"][b[j]]["link"]["b"], b[j], i])) {
                                    return b[j];
                                }
                            }
                            var a = (function () {
                                var v = [];
                                var t = [];
                                for(var j = 0; j < r["note"].length; j++) {
                                    if((r["note"][j]["link"]["b"] == -1) && (r["note"][j]["link"]["a"] != -1) && (r["note"][r["note"][j]["link"]["a"]]["when"]["b"] != r["note"][j]["when"]["b"])) {
                                        if((w.indexOf(r["note"][j]["where"]["xt"]) != -1) && (r["note"][j]["when"]["b"] == r["note"][i]["when"]["b"])) {
                                            t.push([r["note"][j]["where"]["xt"], j]);
                                        }
                                    }
                                }
                                var s = function (a, b) {
                                    return w.indexOf(a[0]) - w.indexOf(b[0]);
                                }
                                t = t.sort(s);
                                for(var j = 0; j < t.length; j++) {
                                    v.push(t[j][1]);
                                }
                                return v;
                            })();
                            for(var j = 0; j < a.length; j++) {
                                if(canLink([r["note"][i]["where"]["xt"], r["note"][i]["when"]["b"]], [r["note"][a[j]]["where"]["xt"], r["note"][a[j]]["when"]["b"]], [], [i, a[j], [r["note"][a[j]]["link"]["a"]]])) {
                                    return a[j];
                                }
                            }
                            return -1;
                        })();
                        if(l != -1) {
                            if(r["note"][l]["link"]["b"] == -1) {
                                r["note"][i]["link"]["a"] = r["note"][l]["link"]["c"];
                                r["note"][l]["link"]["b"] = r["note"][i]["link"]["c"];
                            } else if(r["note"][l]["link"]["a"] == -1) {
                                r["note"][i]["link"]["b"] = r["note"][l]["link"]["c"];
                                r["note"][l]["link"]["a"] = r["note"][i]["link"]["c"];
                            }
                        }
                    }
                }
                r["note"] = sortNote(r["note"]);
                break;
            case "TransOblique":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    var b = r["note"][i]["link"]["b"];
                    var a = r["note"][i]["link"]["a"];
                    if((b != -1) && (a != -1)) {
                        if((r["note"][b]["where"]["x"] == r["note"][i]["where"]["x"]) && (r["note"][b]["when"]["b"] != r["note"][i]["when"]["b"])) {
                            if((r["note"][i]["where"]["x"] != r["note"][a]["where"]["x"]) && (r["note"][i]["when"]["b"] == r["note"][a]["when"]["b"])) {
                                r["note"][b]["link"]["a"] = r["note"][a]["link"]["c"];
                                r["note"][a]["link"]["b"] = r["note"][b]["link"]["c"];
                                r["note"][i]["link"]["c"] = -1;
                            }
                        }
                    }
                }
                r["note"] = sortNote(r["note"]);
                break;
            case "NoOblique":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    var b = r["note"][i]["link"]["b"];
                    if(b != -1) {
                        if(r["note"][b]["where"]["xt"] != r["note"][i]["where"]["xt"]) {
                            if(r["note"][b]["when"]["b"] != r["note"][i]["when"]["b"]) {
                                r["note"].push(mergeObject(newData("note"), {"when": r["note"][i]["when"], "where": r["note"][b]["where"], "link": {"b": r["note"][b]["link"]["c"], "c": r["note"][r["note"].length - 1]["link"]["c"] + 1, "a": r["note"][i]["link"]["c"]}}, true));
                                r["note"][b]["link"]["a"] = r["note"][r["note"].length - 1]["link"]["c"];
                                r["note"][i]["link"]["b"] = r["note"][r["note"].length - 1]["link"]["c"];
                            }
                        }
                    }
                }
                r["note"] = sortNote(r["note"]);
                break;
            case "NoSlide":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    var b = r["note"][i]["link"]["b"];
                    if(b != -1) {
                        if(r["note"][b]["where"]["xt"] != r["note"][i]["where"]["xt"]) {
                            if(r["note"][b]["when"]["b"] != r["note"][i]["when"]["b"]) {
                                r["note"].push(mergeObject(newData("note"), {"when": r["note"][i]["when"], "where": r["note"][b]["where"], "link": {"b": r["note"][b]["link"]["c"], "c": r["note"][r["note"].length - 1]["link"]["c"] + 1, "a": -1}}, true));
                                r["note"][b]["link"]["a"] = r["note"][r["note"].length - 1]["link"]["c"];
                            } else {
                                r["note"][b]["link"]["a"] = -1;
                            }
                            r["note"][i]["link"]["b"] = -1;
                        }
                    }
                }
                r["note"] = sortNote(r["note"]);
                break;
            case "NoLong":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    var b = r["note"][i]["link"]["b"];
                    if((b != -1) && (r["note"][b]["when"]["b"] != r["note"][i]["when"]["b"])) {
                        if(r["note"][b]["where"]["xt"] == r["note"][i]["where"]["xt"]) {
                            if(r["note"][i]["link"]["a"] != -1) {
                                r["note"][i]["link"]["b"] = -1;
                            } else {
                                r["note"][i]["link"]["c"] = -1;
                            }
                        } else {
                            r["note"].push(mergeObject(newData("note"), {"when": r["note"][i]["when"], "where": r["note"][b]["where"], "link": {"b": -1, "c": r["note"][r["note"].length - 1]["link"]["c"] + 1, "a": r["note"][i]["link"]["c"]}}, true));
                            r["note"][i]["link"]["b"] = r["note"][r["note"].length - 1]["link"]["c"];
                        }
                        r["note"][b]["link"]["a"] = -1;
                    }
                }
                r["note"] = sortNote(r["note"]);
                break;
            case "NoSingle":
                for(var i = 0; i < r["note"].length; i++) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    if((r["note"][i]["link"]["b"] == -1) && (r["note"][i]["link"]["a"] == -1)) {
                        r["note"][i]["link"]["c"] = -1;
                    }
                }
                r["note"] = sortNote(r["note"]);
                break;
            case "NoIntersect":
                for(var i = r["note"].length - 1; i > 0 - 1; i--) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    var b = r["note"][i]["link"]["b"];
                    var a = r["note"][i]["link"]["a"];
                    if(canLink([r["note"][i]["where"]["xt"], r["note"][i]["when"]["b"]], [r["note"][i]["where"]["xt"], r["note"][i]["when"]["b"]], [], [i], "false") == false) {
                        if(b != -1) {
                            r["note"][b]["link"]["a"] = -1;
                            r["note"][i]["link"]["b"] = -1;
                            if(r["note"][b]["link"]["b"] == -1) {
                                r["note"][b]["link"]["c"] = -1;
                            }
                        }
                        if(a != -1) {
                            r["note"][i]["link"]["a"] = -1;
                            r["note"][a]["link"]["b"] = -1;
                            if(r["note"][a]["link"]["a"] == -1) {
                                r["note"][a]["link"]["c"] = -1;
                            }
                        }
                        r["note"][i]["link"]["c"] = -1;
                    } else if((b != -1) && (canLink([r["note"][b]["where"]["xt"], r["note"][b]["when"]["b"]], [r["note"][b]["where"]["xt"], r["note"][b]["when"]["b"]], [], [b], "false") == false)) {
                        var b2 = r["note"][b]["link"]["b"];
                        if(b2 != -1) {
                            r["note"][b2]["link"]["a"] = -1;
                            r["note"][b]["link"]["b"] = -1;
                            if(r["note"][b2]["link"]["b"] == -1) {
                                r["note"][b2]["link"]["c"] = -1;
                            }
                        }
                        r["note"][b]["link"]["a"] = -1;
                        r["note"][i]["link"]["b"] = -1;
                        if(r["note"][i]["link"]["a"] == -1) {
                            r["note"][i]["link"]["c"] = -1;
                        }
                        r["note"][b]["link"]["c"] = -1;
                    } else if((b != -1) && (canLink([r["note"][b]["where"]["xt"], r["note"][b]["when"]["b"]], [r["note"][i]["where"]["xt"], r["note"][i]["when"]["b"]], [], [b, i], "false") == false)) {
                        var b2 = r["note"][b]["link"]["b"];
                        if(b2 != -1) {
                            r["note"][b2]["link"]["a"] = -1;
                            r["note"][b]["link"]["b"] = -1;
                            if(r["note"][b2]["link"]["b"] == -1) {
                                r["note"][b2]["link"]["c"] = -1;
                            }
                        }
                        if(a != -1) {
                            r["note"][i]["link"]["a"] = -1;
                            r["note"][a]["link"]["b"] = -1;
                            if(r["note"][a]["link"]["a"] == -1) {
                                r["note"][a]["link"]["c"] = -1;
                            }
                        }
                        r["note"][b]["link"]["a"] = -1;
                        r["note"][i]["link"]["b"] = -1;
                        r["note"][b]["link"]["c"] = -1;
                        r["note"][i]["link"]["c"] = -1;
                    }
                }
                r["note"] = sortNote(r["note"]);
                break;
            case "NoCross":
                for(var i = r["note"].length - 1; i > 0 - 1; i--) {
                    if(r["note"][i]["link"]["c"] == -1) {
                        continue;
                    }
                    var b = r["note"][i]["link"]["b"];
                    if(b != -1) {
                        if(canLink([r["note"][b]["where"]["xt"], r["note"][b]["when"]["b"]], [r["note"][i]["where"]["xt"], r["note"][i]["when"]["b"]], [], [b, i], "true") == false) {
                            r["note"][b]["link"]["a"] = -1;
                            r["note"][i]["link"]["b"] = -1;
                            if(r["note"][i]["link"]["a"] == -1) {
                                r["note"][i]["link"]["c"] = -1;
                            }
                            if(r["note"][b]["link"]["b"] == -1) {
                                r["note"][b]["link"]["c"] = -1;
                            }
                        }
                    }
                }
                r["note"] = sortNote(r["note"]);
                break;
            case "EquilongDrag":
                r = toData(r, "TransOblique");
                var c = classifyNote(r["note"]);
                var d = (function () {
                    var v = [];
                    for(var i = 0; i < c["drag"].length; i++) {
                        var t = [];
                        var ut = 0;
                        var ul = 0;
                        for(var j = 1; j < c["drag"][i].length; j++) {
                            var ct = c["drag"][i][j]["when"]["t"] - c["drag"][i][j - 1]["when"]["t"];
                            var cl = Math.getPointsLength([
                                                              [c["drag"][i][j]["where"]["x"], c["drag"][i][j]["where"]["y"], c["drag"][i][j]["where"]["z"]],
                                                              [c["drag"][i][j - 1]["where"]["x"], c["drag"][i][j - 1]["where"]["y"], c["drag"][i][j - 1]["where"]["z"]]
                                                          ]);
                            if(ct != 0) {
                                if(ct.isClose(ut) && cl.isNear(ul)) {
                                    t.push(copyObject(c["drag"][i][j]));
                                } else {
                                    ut = ct;
                                    ul = cl;
                                    if(t.length > 0) {
                                        v.push(t);
                                    }
                                    t = [copyObject(c["drag"][i][j - 1]), copyObject(c["drag"][i][j])];
                                }
                            }
                            if(j == c["drag"][i].length - 1) {
                                if(t.length > 0) {
                                    v.push(t);
                                }
                            }
                        }
                    }
                    for(var i = 0; i < v.length; i++) {
                        v[i][0]["link"]["b"] = -1;
                        v[i][v[i].length - 1]["link"]["a"] = -1;
                    }
                    return v;
                })();
                var n = (function () {
                    var v = [];
                    var t = [].concat(c["tab"], c["hold"], c["slide"], d);
                    for(var i = 0; i < t.length; i++) {
                        v.writes(t[i]);
                    }
                    return v;
                })();
                r["note"] = sortNote(n);
                break;
            case "SingleAction":

                break;
        }
        return r;
    };
    var toDraw = function (index, type) {
        var r = {};
        var key = Semi["data"][index]["info"]["key"];
        r["noteData"] = (function () {
            switch(type) {
                case "bms":
                case "osu":
                case "imd":
                case "mc":
                    Buffer["Imd"][index] = Buffer["Imd"][index] || toImdBuffer(index);
                    var buffer = Buffer["Imd"][index];
                    var readInt = function (l) {
                        if(buffer.length - p < l) {
                            p = buffer.length;
                            return 0;
                        } else {
                            var r = buffer.getInt(l, p, true);
                            p += l;
                            return r;
                        }
                    };
                    var readFloat = function (l) {
                        if(buffer.length - p < l) {
                            p = buffer.length;
                            return 0;
                        } else {
                            var r = buffer.getFloat(l, p, true);
                            p += l;
                            return r;
                        }
                    };
                    var p = 0;
                    var length = readInt(4);
                    var beatData = (function () {
                        var v = [];
                        var count = readInt(4);
                        for(var i = 0; i < count; i++) {
                            var time = readInt(4);
                            var bpm = readFloat(8);
                            v.push([time, bpm]);
                        }
                        return v;
                    })();
                    p += 2;
                    var actionData = (function () {
                        var v = [];
                        var count = readInt(4);
                        for(var i = 0; i < count; i++) {
                            var action = readInt(2);
                            var time = readInt(4);
                            var track = readInt(1);
                            var param = readInt(4);
                            v.push([action, time, track, param]);
                        }
                        if(type != "imd") {
                            for(var i = 0; i < v.length; i++) {
                                switch(v[i][0]) {
                                    case 0x01:
                                        if(v[i][3] > 0) {
                                            v.insert([0x00, v[i][1], v[i][2] + v[i][3], 0], i + 1);
                                            v[i] = [0x00, v[i][1], v[i][2], 0];
                                            i += 1;
                                        } else if(v[i][3] < 0) {
                                            v.insert([0x00, v[i][1], v[i][2] + v[i][3], 0], i);
                                            i += 1;
                                            v[i] = [0x00, v[i][1], v[i][2], 0];
                                        } else if(v[i][3] == 0) {
                                            v[i] = [0x00, v[i][1], v[i][2] + v[i][3], 0];
                                        }
                                        break;
                                    case 0xA1:
                                        if(v[i][3] != 0) {
                                            v[i] = [0x00, v[i][1], v[i][2] + v[i][3], 0];
                                        } else {
                                            v.splice(i, 1);
                                            i -= 1;
                                        }
                                        break;
                                    case 0x61:
                                        if(v[i][3] != 0) {
                                            v[i] = [0x00, v[i][1], v[i][2], 0];
                                        } else {
                                            v.splice(i, 1);
                                            i -= 1;
                                        }
                                        break;
                                    case 0x21:
                                        v.splice(i, 1);
                                        i -= 1;
                                        break;
                                    case 0x62:
                                    case 0x22:
                                    case 0xA2:
                                        v[i] = [0x02, v[i][1], v[i][2], v[i][3]];
                                        break;
                                }
                            }
                            for(var i = 0; i < v.length; i++) {
                                var isIntersect = (function () {
                                    for(var j = 0; j < v.length; j++) {
                                        if(j != i) {
                                            switch(v[i][0]) {
                                                case 0x00:
                                                    switch(v[j][0]) {
                                                        case 0x00:
                                                            if((v[j][1] == v[i][1]) && (v[j][2] == v[i][2])) {
                                                                return true;
                                                            }
                                                            break;
                                                        case 0x02:
                                                            if((v[j][1] <= v[i][1]) && (v[j][1] + v[j][3] >= v[i][1]) && (v[j][2] == v[i][2])) {
                                                                return true;
                                                            }
                                                            break;
                                                    }
                                                    break;
                                                case 0x02:
                                                    switch(v[j][0]) {
                                                        case 0x02:
                                                            if((v[j][1] <= v[i][1]) && (v[j][1] + v[j][3] >= v[i][1]) && (v[j][2] == v[i][2])) {
                                                                return true;
                                                            }
                                                            break;
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                    return false;
                                })();
                                if(isIntersect) {
                                    v.splice(i, 1);
                                    i -= 1;
                                }
                            }
                        }
                        return v;
                    })();
                    var noteData = (function () {
                        var v = [];
                        for(var i = 0; i < actionData.length; i++) {
                            var track2 = actionData[i][2];
                            switch(actionData[i][0]) {
                                case 0x01:
                                case 0x61:
                                case 0x21:
                                case 0xA1:
                                    track2 = actionData[i][2] + actionData[i][3];
                                    break;
                                case 0x62:
                                case 0x22:
                                    if(i != actionData.length - 1) {
                                        switch(actionData[i + 1][0]) {
                                            case 0x21:
                                            case 0xA1:
                                                track2 = actionData[i + 1][2];
                                                break;
                                        }
                                        break;
                                    }
                            }
                            var t = actionData[i][1];
                            var b = beatFromTime(Semi["data"][index], t);
                            var n = noteFromBeat(Semi["data"][index], b);
                            var t2 = actionData[i][1] + actionData[i][3];
                            var b2 = beatFromTime(Semi["data"][index], t2);
                            var n2 = noteFromBeat(Semi["data"][index], b2);
                            var x = (actionData[i][2] + 0.5) / key;
                            var x2 = (track2 + 0.5) / key;
                            v.push({"action": actionData[i][0], "when": {"b": b, "n": n, "t": t}, "when2": {"b": b2, "n": n2, "t": t2}, "where": {"x": x}, "where2": {"x": x2}});
                        }
                        return v;
                    })();
                    return noteData;
                    break;
                case "aff":
                    Text["Aff"][index] = Text["Aff"][index] || toAffText(index);
                    return Semi["data"][index]["draw"][type]["noteData"];
                    break;
            }
        })();
        r["unflowAera"] = (function () {
            var v = [];
            for(var i = 0; i < Semi["data"][index]["beat"].length; i++) {
                if(Semi["data"][index]["beat"][i]["flow"] <= 0) {
                    v.push({"when": Semi["data"][index]["beat"][i]["when"], "when2": (i < Semi["data"][index]["beat"].length - 1 ? Semi["data"][index]["beat"][i + 1]["when"] : Semi["data"][index]["info"]["max"]["when"]), "flow": Semi["data"][index]["beat"][i]["flow"]});
                }
            }
            return v;
        })();
        r["beatLines"] = (function () {
            var v = {};
            v["w"] = [];
            v["h"] = [];
            v["q"] = [];
            for(var i = 0; i < Semi["data"][index]["info"]["max"]["when"]["b"]; i++) {
                v["w"].push({"when": {"b": i, "n": noteFromBeat(Semi["data"][index], i), "t": timeFromBeat(Semi["data"][index], i)}});
                v["q"].push({"when": {"b": i + 0.25, "n": noteFromBeat(Semi["data"][index], i + 0.25), "t": timeFromBeat(Semi["data"][index], i + 0.25)}});
                v["h"].push({"when": {"b": i + 0.5, "n": noteFromBeat(Semi["data"][index], i + 0.5), "t": timeFromBeat(Semi["data"][index], i + 0.5)}});
                v["q"].push({"when": {"b": i + 0.75, "n": noteFromBeat(Semi["data"][index], i + 0.75), "t": timeFromBeat(Semi["data"][index], i + 0.75)}});
            }
            v["w"].push({"when": Semi["data"][index]["info"]["max"]["when"]});
            return v;
        })();
        var determineData = [];
        var indexOf = function (t) {
            for(var i = 0; i < determineData.length; i++) {
                if(determineData[i][0] == t) {
                    return i;
                }
            }
            return -1;
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
        for(var i = 0; i < r["noteData"].length; i++) {
            if(r["noteData"][i]["auto"] == "true") {
                continue;
            }
            switch(r["noteData"][i]["action"]) {
                case 0x00:
                    addData(r["noteData"][i]["when"]["t"], r["noteData"][i]["where"]["x"], 1);
                    break;
                case 0x01:
                    addData(r["noteData"][i]["when"]["t"], r["noteData"][i]["where2"]["x"], 2);
                    break;
                case 0x61:
                    break;
                case 0x21:
                    if(type == "aff") {
                        addData(r["noteData"][i]["when"]["t"], r["noteData"][i]["where"]["x"], 1);
                    }
                    break;
                case 0xA1:
                    addData(r["noteData"][i]["when"]["t"], r["noteData"][i]["where2"]["x"], 2);
                    break;
                case 0x02:
                case 0x62:
                case 0x22:
                case 0xA2:
                    var d = [];
                    var t = [];
                    var dt = r["noteData"][i]["when"]["t"];
                    var db = beatFromTime(Semi["data"][index], dt, 0);
                    var affNew = (function () {
                        if(type == "aff") {
                            if(r["noteData"][i]["action"] == 0x22) {
                                for(var j = 0; j < r["noteData"].length; j++) {
                                    if((j != i) && (r["noteData"][j]["action"] == 0x22) && (r["noteData"][j]["group"] == r["noteData"][i]["group"]) && (r["noteData"][j]["auto"] != "true") && (((r["noteData"][j]["when"]["t"] < r["noteData"][i]["when"]["t"]) && (r["noteData"][j]["when2"]["t"] >= r["noteData"][i]["when"]["t"])) || ((r["noteData"][i]["when"]["t"] - r["noteData"][j]["when2"]["t"] >= 0) && (r["noteData"][i]["when"]["t"] - r["noteData"][j]["when2"]["t"] < 10) && (Math.abs(r["noteData"][j]["where2"]["x"] - r["noteData"][i]["where"]["x"]) < 0.1) && (r["noteData"][j]["where2"]["y"] == r["noteData"][i]["where"]["y"])))) {
                                        return false;
                                    }
                                }
                            }
                        }
                        return true;
                    })();
                    switch(type) {
                        case "aff":
                            if(!affNew) {
                                d.push([dt + 1, r["noteData"][i]["where"]["x"], (r["noteData"][i]["action"] == 0x22 ? 2 : 1)]);
                            }
                            break;
                        default:
                            d.push([dt, r["noteData"][i]["where"]["x"], ((r["noteData"][i]["action"] == 0x22) || (r["noteData"][i]["action"] == 0xA2) ? 2 : 1)]);
                            break;
                    }
                    while(true) {
                        switch(type) {
                            case "bms":
                                db += 1 / 4;
                                dt = timeFromBeat(Semi["data"][index], db);
                                break;
                            case "osu":
                                dt += 100;
                                break;
                            case "imd":
                                var tn = (function () {
                                    var v = 0;
                                    for(var j = 0; j < Semi["data"][index]["beat"].length; j++) {
                                        if((dt >= Semi["data"][index]["beat"][j]["when"]["t"]) && (((j < Semi["data"][index]["beat"].length - 1) && (dt < Semi["data"][index]["beat"][j + 1]["when"]["t"])) || (j >= Semi["data"][index]["beat"].length - 1))) {
                                            var bn = 0;
                                            while(bn < 1) {
                                                var u = Math.floor(60000 / Semi["data"][index]["beat"][j]["bpm"] / Semi["data"][index]["beat"][j]["bpn"]);
                                                if(((j < Semi["data"][index]["beat"].length - 1) && (dt + v + u * (1 - bn) < Semi["data"][index]["beat"][j + 1]["when"]["t"])) || (j >= Semi["data"][index]["beat"].length - 1)) {
                                                    v += Math.ceil(u * (1 - bn));
                                                    bn = 1;
                                                } else {
                                                    v = Semi["data"][index]["beat"][j + 1]["when"]["t"] - dt;
                                                    bn += (Semi["data"][index]["beat"][j + 1]["when"]["t"] - dt - v) / u;
                                                    j += 1;
                                                }
                                            }
                                        }
                                    }
                                    return v;
                                })();
                                dt += tn;
                                break;
                            case "mc":
                                db += 222 / 768;
                                dt = timeFromBeat(Semi["data"][index], db);
                                break;
                            case "aff":
                                db += 1 / 2;
                                dt = timeFromBeat(Semi["data"][index], db);
                                break;
                        }
                        if(dt <= r["noteData"][i]["when2"]["t"]) {
                            t.push(dt);
                        } else {
                            break;
                        }
                    }
                    switch(type) {
                        case "aff":
                            for(var j = 0; j < t.length - 1; j++) {
                                var t0 = (j > 0 ? t[j - 1] : r["noteData"][i]["when"]["t"]);
                                d.push([t0 + (t[j] - t0) / 2, r["noteData"][i]["where"]["x"], 1]);
                            }
                            if(d.length == 0) {
                                d.push([r["noteData"][i]["when"]["t"] + 1, r["noteData"][i]["where"]["x"], 1]);
                            }
                            break;
                        default:
                            for(var j = 0; j < t.length; j++) {
                                d.push([t[j], r["noteData"][i]["where"]["x"], 1]);
                            }
                            break;
                    }
                    for(var j = 0; j < d.length; j++) {
                        switch(r["noteData"][i]["action"]) {
                            case 0x62:
                            case 0x22:
                                switch(type) {
                                    case "imd":
                                        if((i != r["noteData"].length - 1) && ((r["noteData"][i]["action"] == 0x62) || (r["noteData"][i]["action"] == 0x22)) && ((r["noteData"][i + 1]["action"] == 0x21) || (r["noteData"][i + 1]["action"] == 0xA1)) && (r["noteData"][i + 1]["where"]["x"] != r["noteData"][i]["where"]["x"]) && (r["noteData"][i]["when2"]["t"] - r["noteData"][i]["when"]["t"] != 0)) {
                                            if(r["noteData"][i]["when2"]["t"] != r["noteData"][i]["when"]["t"]) {
                                                d[j][1] += (d[j][0] - r["noteData"][i]["when"]["t"]) / (r["noteData"][i]["when2"]["t"] - r["noteData"][i]["when"]["t"]) * (r["noteData"][i + 1]["where"]["x"] - r["noteData"][i]["where"]["x"]);
                                            }
                                        }
                                        break;
                                    case "aff":
                                        if(r["noteData"][i]["when2"]["t"] != r["noteData"][i]["when"]["t"]) {
                                            d[j][1] += (d[j][0] - r["noteData"][i]["when"]["t"]) / (r["noteData"][i]["when2"]["t"] - r["noteData"][i]["when"]["t"]) * (r["noteData"][i]["where2"]["x"] - r["noteData"][i]["where"]["x"]);
                                        }
                                        break;
                                }
                                break;
                        }
                        addData(Math.round(d[j][0]), d[j][1], d[j][2]);
                    }
                    break;
            }
        }
        r["determineLines"] = (function () {
            var v = [];
            for(var i = 0; i < determineData.length; i++) {
                v.push(determineData[i][0]);
            }
            v = v.unrepeat().sort(SortNumber);
            for(var i = 0; i < v.length; i++) {
                var t = v[i];
                var b = beatFromTime(Semi["data"][index], t);
                var n = noteFromBeat(Semi["data"][index], b);
                v[i] = {"when": {"b": b, "n": n, "t": t}};
            }
            return v;
        })();
        r["comboPoints"] = (function () {
            var v = [];
            for(var i = 0; i < r["determineLines"].length; i++) {
                var l = indexOf(r["determineLines"][i]["when"]["t"]);
                if(l != -1) {
                    determineData[l][1] = determineData[l][1].sort(SortNumber);
                    var t = determineData[l][0];
                    var b = beatFromTime(Semi["data"][index], t);
                    var n = noteFromBeat(Semi["data"][index], b);
                    for(var j = 0; j < determineData[l][1].length; j++) {
                        v.push({"when": {"b": b, "n": n, "t": t}, "where": {"x": determineData[l][1][j]}});
                    }
                    determineData[l][2] = determineData[l][2].sort(SortNumber);
                    for(var j = 0; j < determineData[l][2].length; j++) {
                        v.push({"when": {"b": b, "n": n, "t": t}, "where": {"x": determineData[l][2][j]}});
                    }
                }
            }
            return v;
        })();
        r["noteStyle"] = function (x, key, action, group) {
            var track = Math.floor(x * key - 0.5);
            var m = [];
            switch(type) {
                case "bms":
                    var cg = "#8C8C8C";
                    var cb = "#0000BA";
                    m = [
                        [cb],
                        [cg, cg],
                        [cg, cb, cg],
                        [cg, cb, cb, cg],
                        [cg, cb, cg, cb, cg],
                        [cg, cb, cg, cg, cb, cg],
                        [cg, cb, cg, cb, cg, cb, cg],
                        [cg, cb, cg, cb, cb, cg, cb, cg],
                        [cg, cb, cg, cb, cg, cb, cg, cb, cg]
                    ];
                    break;
                case "osu":
                    var cy = "#EBCD00";
                    var cw = "#D2BAD2";
                    var cp = "#C86096";
                    m = [
                        [cy],
                        [cw, cw],
                        [cw, cy, cw],
                        [cw, cp, cp, cw],
                        [cw, cp, cy, cp, cw],
                        [cw, cp, cw, cw, cp, cw],
                        [cw, cp, cw, cy, cw, cp, cw],
                        [cw, cp, cw, cp, cp, cw, cp, cw],
                        [cw, cp, cw, cp, cy, cp, cw, cp, cw]
                    ];
                    break;
                case "imd":
                    switch(action) {
                        case 0x00:
                            return "#0000FF";
                            break;
                        default:
                            return "#00FF00";
                            break;
                    }
                    break;
                case "mc":
                    var cy = "#FFDC92";
                    var cw = "#CACACA";
                    var cp = "#FF9292";
                    m = [
                        [cy],
                        [cw, cw],
                        [cw, cy, cw],
                        [cw, cp, cp, cw],
                        [cw, cp, cw, cp, cw],
                        [cw, cp, cw, cw, cp, cw],
                        [cw, cp, cw, cy, cw, cp, cw],
                        [cw, cp, cp, cw, cw, cp, cp, cw],
                        [cw, cp, cw, cp, cy, cp, cw, cp, cw],
                        [cw, cp, cw, cp, cw, cp, cw, cp, cw, cp]
                    ];
                    break;
                case "aff":
                    switch(action) {
                        case 0x00:
                            return "#7FBFBF";
                            break;
                        case 0x02:
                            return "#7FBFBF";
                            break;
                        case 0x21:
                            switch(group) {
                                case "":
                                    return "#007FFF";
                                    break;
                                case "arcaea_shadow":
                                    return "#DFDFDF";
                                    break;
                            }
                            break;
                        case 0x22:
                            switch(group) {
                                case "arcaea_0":
                                    return "#00DFDF";
                                    break;
                                case "arcaea_1":
                                    return "#DF00DF";
                                    break;
                                case "arcaea_dark":
                                    return "#7F7F7F";
                                    break;
                                case "arcaea_shadow":
                                    return "#DFDFDF";
                                    break;
                            }
                            break;
                    }
                    break;
            }
            var k = [];
            var n = Math.ceil(key / m.length);
            var u = Math.floor(key / n);
            for(var i = 0; i < n; i++) {
                k[i] = u;
            }
            var o = key - u * n;
            var c = 0;
            while(o > 0) {
                k[c] += 1;
                c += 1;
                o -= 1;
            }
            for(var i = 0; i < n; i++) {
                if(track > k[i]) {
                    track -= k[i];
                } else {
                    return m[k[i] - 1][track];
                }
            }
        };
        r["fullScore"] = (function () {
            var v = 0;
            switch(type) {
                case "bms":
                    v = 200000;
                    break;
                case "osu":
                    v = 1000000;
                    break;
                case "imd":
                    var c = r["comboPoints"].length;
                    if(c >= 100) {
                        v += (c - 100) * 600;
                        c = 100;
                    }
                    if(c >= 50) {
                        v += (c - 50) * 466;
                        c = 50;
                    }
                    if(c >= 20) {
                        v += (c - 20) * 332;
                        c = 20;
                    }
                    v += c * 200;
                    break;
                case "mc":
                    v = r["noteData"].length * 1400;
                    break;
                case "aff":
                    v = 10000000 + r["comboPoints"].length;
                    break;
            }
            return v;
        })();
        r["density"] = (function () {
            var v = 0;
            var d = [];
            var getLong = function (t) {
                return Math.floor(t / 120) / 4;
            };
            var getSlide = function (t) {
                return Math.abs(t);
            };
            var indexOf = function (t) {
                for(var i = 0; i < d.length; i++) {
                    if(d[i][0] == t) {
                        return i;
                    }
                }
                return -1;
            };
            var addData = function (t, v) {
                var l = indexOf(t);
                if(l != -1) {
                    d[l][1] += v;
                } else {
                    d.push([t, v]);
                }
            };
            for(var i = 0; i < r["noteData"].length; i++) {
                if(!isUndefined(r["noteData"][i]["auto"]) && r["noteData"][i]["auto"] == "true") {
                    continue;
                }
                switch(r["noteData"][i]["action"]) {
                    case 0x00:
                        addData(r["noteData"][i]["when"]["t"], 1);
                        break;
                    case 0x01:
                        addData(r["noteData"][i]["when"]["t"], 2 + getSlide(r["noteData"][i]["where2"]["x"] - r["noteData"][i]["where"]["x"]));
                        break;
                    case 0x61:
                    case 0x21:
                    case 0xA1:
                        addData(r["noteData"][i]["when"]["t"], 1 + getSlide(r["noteData"][i]["where2"]["x"] - r["noteData"][i]["where"]["x"]));
                        break;
                    case 0x02:
                    case 0x62:
                    case 0x22:
                    case 0xA2:
                        addData(r["noteData"][i]["when"]["t"], 1 + getLong(r["noteData"][i]["when2"]["t"] - r["noteData"][i]["when"]["t"]));
                        break;
                }
            }
            d = d.sort(SortNumbers);
            for(var i = 1; i < d.length; i++) {
                v += d[i][1] / (d[i][0] - d[i - 1][0]) * 120;
            }
            return Math.sqrt(v / 16);
        })();
        r["quantity"] = (function () {
            var v = 0;
            for(var i = 0; i < r["noteData"].length; i++) {
                if(!isUndefined(r["noteData"][i]["auto"]) && r["noteData"][i]["auto"] == "true") {
                    continue;
                }
                switch(r["noteData"][i]["action"]) {
                    case 0x00:
                        v += 1;
                        break;
                    case 0x01:
                        v += 2 + Math.abs(r["noteData"][i]["where2"]["x"] - r["noteData"][i]["where"]["x"]);
                        break;
                    case 0x61:
                    case 0xA1:
                        v += 1 + Math.abs(r["noteData"][i]["where2"]["x"] - r["noteData"][i]["where"]["x"]);
                        break;
                    case 0x21:
                        if(type == "aff") {
                            v += 1;
                        } else {
                            v += 0 + Math.abs(r["noteData"][i]["where2"]["x"] - r["noteData"][i]["where"]["x"]);
                        }
                        break;
                    case 0x02:
                    case 0x62:
                    case 0x22:
                    case 0xA2:
                        v += 1 + Math.floor((r["noteData"][i]["when2"]["t"] - r["noteData"][i]["when"]["t"]) / 120);
                        break;
                }
            }
            return Math.sqrt(v / 16);
        })();
        return r;
    };
    var getSemi = function () {
        var r = copyObject(Raw || {});
        Checks = GetMstCheck();
        r["meta"] = r["meta"] || {};
        r["file"] = r["file"] || [];
        for(var i = 0; i < r["file"].length; i++) {
            r["file"][i]["name"] = r["file"][i]["name"] || file["name"];
            r["file"][i]["base64"] = r["file"][i]["base64"] || file["base64"];
        }
        r["data"] = r["data"] || [];
        var file = newData("file");
        var note = newData("note");
        var beat = newData("beat");
        var effect = newData("effect");
        var transform = newData("transform");
        for(var index = 0; index < r["data"].length; index++) {
            var fillValue = (function () {
                r["data"][index]["note"] = r["data"][index]["note"] || [];
                r["data"][index]["beat"] = r["data"][index]["beat"] || [];
                r["data"][index]["effect"] = r["data"][index]["effect"] || [
                    {"type": "audio", "name": info.nameCore + ".mp3"}
                ];
                for(var i = 0; i < r["data"][index]["effect"].length; i++) {
                    r["data"][index]["effect"][i]["type"] = r["data"][index]["effect"][i]["type"] || effect["type"];
                    r["data"][index]["effect"][i]["when"] = r["data"][index]["effect"][i]["when"] || {"b": 0, "n": 0, "t": 0};
                    r["data"][index]["effect"][i]["where"] = r["data"][index]["effect"][i]["where"] || {"x": -1, "y": -1, "z": -1};
                    r["data"][index]["effect"][i]["name"] = r["data"][index]["effect"][i]["name"] || effect["name"];
                    r["data"][index]["effect"][i]["start"] = r["data"][index]["effect"][i]["start"] || effect["start"];
                    r["data"][index]["effect"][i]["duration"] = r["data"][index]["effect"][i]["duration"] || effect["duration"];
                    r["data"][index]["effect"][i]["volume"] = r["data"][index]["effect"][i]["volume"] || effect["volume"];
                    r["data"][index]["effect"][i]["speed"] = r["data"][index]["effect"][i]["speed"] || effect["speed"];
                    r["data"][index]["effect"][i]["repeat"] = r["data"][index]["effect"][i]["repeat"] || effect["repeat"];
                }
                r["data"][index]["transform"] = r["data"][index]["transform"] || [];
                r["data"][index]["info"] = r["data"][index]["info"] || {};
                r["data"][index]["info"]["key"] = CheckNumber(r["data"][index]["info"]["key"], Checks["TransKey"]);
                r["data"][index]["info"]["name"] = r["data"][index]["info"]["name"] || "";
                r["data"][index]["draw"] = r["data"][index]["draw"] || {};
                if(r["data"][index]["beat"].length == 0) {
                    r["data"][index]["beat"] = (function () {
                        var v = [];
                        var t = [];
                        for(var i = 0; i < r["data"][index]["note"].length; i++) {
                            r["data"][index]["note"][i]["when"]["t"] = CheckNumber(r["data"][index]["note"][i]["when"]["t"]);
                            if(!isUndefined(r["data"][index]["note"][i]["when"]["t"])) {
                                t.push(r["data"][index]["note"][i]["when"]["t"]);
                            }
                        }
                        if(t.length == 0) {
                            t.push(480);
                        }
                        t = t.unrepeat().sort(SortNumber);
                        for(var i = 0; i < t.length; i++) {
                            t[i] = [t[i], 0];
                        }
                        for(var i = 0; i < t.length - 1; i++) {
                            var b = 60000 / (t[i + 1][0] - t[i][0]);
                            while(b < 100) {
                                b *= 2;
                            }
                            while(b >= 200) {
                                b /= 2;
                            }
                            t[i][1] = Math.round(b);
                        }
                        var bpm = 0;
                        for(var i = 0; i < t.length - 4; i++) {
                            if((t[i][1] != bpm) && (t[i + 1][1] == t[i][1]) && (t[i + 2][1] == t[i][1]) && (t[i + 3][1] == t[i][1])) {
                                bpm = t[i][1];
                                v.push({"when": {"t": t[i][0]}, "bpm": bpm});
                            }
                        }
                        if(v.length == 0) {
                            v.push(beat);
                        }
                        if(v[0]["when"]["t"] > 0) {
                            v[0]["when"]["t"] = 0;
                        }
                        return v;
                    })();
                }
                for(var i = 0; i < r["data"][index]["beat"].length; i++) {
                    var bpm = (i != 0 ? r["data"][index]["beat"][i - 1]["bpm"] : beat["bpm"]);
                    var bpn = (i != 0 ? r["data"][index]["beat"][i - 1]["bpn"] : beat["bpn"]);
                    r["data"][index]["beat"][i]["bpm"] = Math.abs(CheckNumber(r["data"][index]["beat"][i]["bpm"], bpm));
                    r["data"][index]["beat"][i]["bpn"] = Math.abs(CheckNumber(r["data"][index]["beat"][i]["bpn"], bpn));
                    r["data"][index]["beat"][i]["flow"] = CheckNumber(r["data"][index]["beat"][i]["flow"], r["data"][index]["beat"][i]["bpm"] / r["data"][index]["beat"][i]["bpn"] * 4);
                    r["data"][index]["beat"][i]["when"] = r["data"][index]["beat"][i]["when"] || {"b": 0, "n": 0, "t": 0};
                }
                r["data"][index]["beat"] = sortBeat(r["data"][index]["beat"]);
                for(var i = 0; i < r["data"][index]["beat"].length; i++) {
                    if((r["data"][index]["beat"][i]["bpm"] == 0) || (r["data"][index]["beat"][i]["bpn"] == 0)) {
                        r["data"][index]["beat"][i]["bpm"] = (r["data"][index]["beat"][i]["bpm"] == 0 ? (i != 0 ? r["data"][index]["beat"][i - 1]["bpm"] : bpm) : r["data"][index]["beat"][i]["bpm"]);
                        r["data"][index]["beat"][i]["bpn"] = (r["data"][index]["beat"][i]["bpn"] == 0 ? (i != 0 ? r["data"][index]["beat"][i - 1]["bpn"] : bpn) : r["data"][index]["beat"][i]["bpn"]);
                        r["data"][index]["beat"][i]["flow"] = r["data"][index]["beat"][i]["bpm"] / r["data"][index]["beat"][i]["bpn"] * 4;
                    }
                }
                for(var i = 0; i < r["data"][index]["beat"].length; i++) {
                    r["data"][index]["beat"][i]["when"] = r["data"][index]["beat"][i]["when"] || {};
                    r["data"][index]["beat"][i]["when"]["b"] = (!isUndefined(r["data"][index]["beat"][i]["when"]["b"]) ? r["data"][index]["beat"][i]["when"]["b"].attach(Checks["CorrectTime"]) : beatFromNote(r["data"][index], r["data"][index]["beat"][i]["when"]["n"]) || beatFromTime(r["data"][index], r["data"][index]["beat"][i]["when"]["t"]) || beat["when"]["b"]);
                    r["data"][index]["beat"][i]["when"]["n"] = noteFromBeat(r["data"][index], r["data"][index]["beat"][i]["when"]["b"]);
                    r["data"][index]["beat"][i]["when"]["t"] = timeFromBeat(r["data"][index], r["data"][index]["beat"][i]["when"]["b"]);
                }
                for(var i = 0; i < r["data"][index]["beat"].length; i++) {
                    if(r["data"][index]["beat"][i]["when"]["b"] < 0) {
                        r["data"][index]["beat"][i]["when"]["b"] == 0;
                        r["data"][index]["beat"][i]["when"]["n"] == 0;
                        r["data"][index]["beat"][i]["when"]["t"] == 0;
                    }
                }
                for(var i = 1; i < r["data"][index]["beat"].length; i++) {
                    if(r["data"][index]["beat"][i]["when"]["b"] == r["data"][index]["beat"][i - 1]["when"]["b"]) {
                        r["data"][index]["beat"].splice(i - 1, 1);
                        i -= 1;
                    }
                }
                for(var i = 1; i < r["data"][index]["beat"].length; i++) {
                    if((r["data"][index]["beat"][i]["bpm"] == r["data"][index]["beat"][i - 1]["bpm"]) && (r["data"][index]["beat"][i]["bpn"] == r["data"][index]["beat"][i - 1]["bpn"]) && (r["data"][index]["beat"][i]["flow"] == r["data"][index]["beat"][i - 1]["flow"])) {
                        r["data"][index]["beat"].splice(i, 1);
                        i -= 1;
                    }
                }
                for(var i = 0; i < r["data"][index]["note"].length; i++) {
                    r["data"][index]["note"][i]["when"] = r["data"][index]["note"][i]["when"] || {};
                    r["data"][index]["note"][i]["when"]["b"] = (!isUndefined(r["data"][index]["note"][i]["when"]["b"]) ? r["data"][index]["note"][i]["when"]["b"].attach(Checks["CorrectTime"]) : beatFromNote(r["data"][index], r["data"][index]["note"][i]["when"]["n"]) || beatFromTime(r["data"][index], r["data"][index]["note"][i]["when"]["t"]) || note["when"]["b"]);
                    r["data"][index]["note"][i]["when"]["n"] = noteFromBeat(r["data"][index], r["data"][index]["note"][i]["when"]["b"]);
                    r["data"][index]["note"][i]["when"]["t"] = timeFromBeat(r["data"][index], r["data"][index]["note"][i]["when"]["b"]);
                }
                for(var i = 0; i < r["data"][index]["note"].length; i++) {
                    r["data"][index]["note"][i]["where"] = r["data"][index]["note"][i]["where"] || {};
                    r["data"][index]["note"][i]["where"]["x"] = (!isUndefined(r["data"][index]["note"][i]["where"]["x"]) && (r["data"][index]["note"][i]["where"]["x"] >= 0) && (r["data"][index]["note"][i]["where"]["x"] < 1) ? r["data"][index]["note"][i]["where"]["x"] : note["where"]["x"]);
                    r["data"][index]["note"][i]["where"]["y"] = (!isUndefined(r["data"][index]["note"][i]["where"]["y"]) && (r["data"][index]["note"][i]["where"]["y"] >= 0) && (r["data"][index]["note"][i]["where"]["y"] < 1) ? r["data"][index]["note"][i]["where"]["y"] : note["where"]["y"]);
                    r["data"][index]["note"][i]["where"]["z"] = (!isUndefined(r["data"][index]["note"][i]["where"]["z"]) && (r["data"][index]["note"][i]["where"]["z"] >= 0) && (r["data"][index]["note"][i]["where"]["z"] < 1) ? r["data"][index]["note"][i]["where"]["z"] : note["where"]["z"]);
                    r["data"][index]["note"][i]["link"] = r["data"][index]["note"][i]["link"] || {};
                    r["data"][index]["note"][i]["link"]["b"] = (!isUndefined(r["data"][index]["note"][i]["link"]["b"]) ? r["data"][index]["note"][i]["link"]["b"] : note["link"]["b"]);
                    r["data"][index]["note"][i]["link"]["c"] = (!isUndefined(r["data"][index]["note"][i]["link"]["c"]) ? r["data"][index]["note"][i]["link"]["c"] : i);
                    r["data"][index]["note"][i]["link"]["a"] = (!isUndefined(r["data"][index]["note"][i]["link"]["a"]) ? r["data"][index]["note"][i]["link"]["a"] : note["link"]["a"]);
                    r["data"][index]["note"][i]["line"] = r["data"][index]["note"][i]["line"] || {};
                    r["data"][index]["note"][i]["line"]["vector"] = r["data"][index]["note"][i]["line"]["vector"] || note["line"]["vector"];
                    for(var j = 0; j < r["data"][index]["note"][i]["line"]["vector"].length; j++) {
                        r["data"][index]["note"][i]["line"]["vector"][j] = r["data"][index]["note"][i]["line"]["vector"][j] || {};
                        r["data"][index]["note"][i]["line"]["vector"][j]["where"] = r["data"][index]["note"][i]["line"]["vector"][j]["where"] || {};
                        r["data"][index]["note"][i]["line"]["vector"][j]["where"]["x"] = r["data"][index]["note"][i]["line"]["vector"][j]["where"]["x"] || note["where"]["x"];
                        r["data"][index]["note"][i]["line"]["vector"][j]["where"]["y"] = r["data"][index]["note"][i]["line"]["vector"][j]["where"]["y"] || note["where"]["y"];
                        r["data"][index]["note"][i]["line"]["vector"][j]["where"]["z"] = r["data"][index]["note"][i]["line"]["vector"][j]["where"]["z"] || note["where"]["z"];
                    }
                    r["data"][index]["note"][i]["line"]["clockwise"] = r["data"][index]["note"][i]["line"]["clockwise"] || note["line"]["clockwise"];
                    r["data"][index]["note"][i]["line"]["follow"] = r["data"][index]["note"][i]["line"]["follow"] || note["line"]["follow"];
                    r["data"][index]["note"][i]["line"]["combo"] = r["data"][index]["note"][i]["combo"] || note["line"]["combo"];
                    r["data"][index]["note"][i]["line"]["curve"] = r["data"][index]["note"][i]["line"]["curve"] || note["line"]["curve"];
                    r["data"][index]["note"][i]["group"] = r["data"][index]["note"][i]["group"] || note["group"];
                    r["data"][index]["note"][i]["auto"] = r["data"][index]["note"][i]["auto"] || note["auto"];
                    r["data"][index]["note"][i]["hitSound"] = r["data"][index]["note"][i]["hitSound"] || note["hitSound"];
                    r["data"][index]["note"][i]["hitEffect"] = r["data"][index]["note"][i]["hitEffect"] || note["hitEffect"];
                }
                for(var i = 0; i < r["data"][index]["note"].length; i++) {
                    r["data"][index]["note"][i]["link"]["b"] = (function () {
                        var b = indexFromCurrent(r["data"][index]["note"], r["data"][index]["note"][i]["link"]["b"]);
                        if(b != -1) {
                            var a = indexFromCurrent(r["data"][index]["note"], r["data"][index]["note"][b]["link"]["a"]);
                            if(a != -1) {
                                return r["data"][index]["note"][i]["link"]["b"];
                            } else {
                                return -1;
                            }
                        } else {
                            return -1;
                        }
                    })();
                    r["data"][index]["note"][i]["link"]["a"] = (function () {
                        var b = indexFromCurrent(r["data"][index]["note"], r["data"][index]["note"][i]["link"]["a"]);
                        if(b != -1) {
                            var a = indexFromCurrent(r["data"][index]["note"], r["data"][index]["note"][b]["link"]["b"]);
                            if(a != -1) {
                                return r["data"][index]["note"][i]["link"]["a"];
                            } else {
                                return -1;
                            }
                        } else {
                            return -1;
                        }
                    })();
                }
                for(var i = 0; i < r["data"][index]["note"].length; i++) {
                    if(r["data"][index]["note"][i]["link"]["b"] == -1) {
                        var n = i;
                        while(true) {
                            var a = r["data"][index]["note"][n]["link"]["a"];
                            if(a != -1) {
                                n = a;
                                r["data"][index]["note"][n]["group"] = r["data"][index]["note"][i]["group"];
                                r["data"][index]["note"][n]["auto"] = r["data"][index]["note"][i]["auto"];
                            } else {
                                break;
                            }
                        }
                    }
                }
                r["data"][index]["note"] = sortNote(r["data"][index]["note"]);
            })();
            var ExtValue = (function () {
                if(Checks["XAxisRemove"]) {
                    r["data"][index] = toData(r["data"][index], "XAxisRemove");
                }
                if(Checks["YAxisRemove"]) {
                    r["data"][index] = toData(r["data"][index], "YAxisRemove");
                }
                if(Checks["ZAxisRemove"]) {
                    r["data"][index] = toData(r["data"][index], "ZAxisRemove");
                }
                if(Checks["XAxisExtend"]) {
                    r["data"][index] = toData(r["data"][index], "XAxisExtend");
                }
                if(Checks["YAxisExtend"]) {
                    r["data"][index] = toData(r["data"][index], "YAxisExtend");
                }
                if(Checks["ZAxisExtend"]) {
                    r["data"][index] = toData(r["data"][index], "ZAxisExtend");
                }
                if(Checks["InvalidKeyRemove"]) {
                    r["data"][index] = toData(r["data"][index], "InvalidKeyRemove");
                }
                if(Checks["FlowRemove"]) {
                    r["data"][index] = toData(r["data"][index], "FlowRemove");
                }
                if(Checks["TransformRemove"]) {
                    r["data"][index] = toData(r["data"][index], "TransformRemove");
                }
                if(Checks["InvalidKeyExtend"]) {
                    r["data"][index] = toData(r["data"][index], "InvalidKeyExtend");
                }
                if(Checks["FlowExtend"]) {
                    r["data"][index] = toData(r["data"][index], "FlowExtend");
                }
                if(Checks["TransformExtend"]) {
                    r["data"][index] = toData(r["data"][index], "TransformExtend");
                }
                r["data"][index] = toData(r["data"][index], "TransKey");
                if(Checks["MirrorTrack"]) {
                    r["data"][index] = toData(r["data"][index], "MirrorTrack");
                }
                if(Checks["RandomTrack"]) {
                    r["data"][index] = toData(r["data"][index], "RandomTrack");
                }
                if(Checks["RandomNote"]) {
                    r["data"][index] = toData(r["data"][index], "RandomNote");
                }
                if(Checks["ChangeSpeed"] != 1) {
                    r["data"][index] = toData(r["data"][index], "ChangeSpeed", Checks);
                }
                r["data"][index]["info"]["min"] = (function () {
                    var note = 0;
                    for(var i = 0; i < r["data"][index]["note"].length; i++) {
                        if(note > r["data"][index]["note"][i]["when"]["n"]) {
                            note = r["data"][index]["note"][i]["when"]["n"];
                        }
                    }
                    note = Math.floor(note);
                    var beat = beatFromNote(r["data"][index], note);
                    var time = timeFromBeat(r["data"][index], beat);
                    return {"when": {"b": beat, "n": note, "t": time}};
                })();
                r["data"][index]["info"]["max"] = (function () {
                    var note = 0;
                    for(var i = 0; i < r["data"][index]["note"].length; i++) {
                        if(note < r["data"][index]["note"][i]["when"]["n"]) {
                            note = r["data"][index]["note"][i]["when"]["n"];
                        }
                    }
                    r["data"][index]["info"]["length"] = r["data"][index]["info"]["length"] || {};
                    r["data"][index]["info"]["length"]["when"] = r["data"][index]["info"]["length"]["when"] || {};
                    var length = noteFromTime(r["data"][index], r["data"][index]["info"]["length"]["when"]["t"] || 0);
                    if(note < length) {
                        note = length;
                    }
                    note = Math.ceil(note + 0.5);
                    var beat = beatFromNote(r["data"][index], note);
                    var time = timeFromBeat(r["data"][index], beat);
                    return {"when": {"b": beat, "n": note, "t": time}};
                })();
                r["data"][index]["info"]["length"] = (function () {
                    var t = r["data"][index]["info"]["length"]["when"]["t"] || r["data"][index]["info"]["max"]["when"]["t"];
                    var b = beatFromTime(r["data"][index], t);
                    var n = noteFromBeat(r["data"][index], b);
                    t = timeFromBeat(r["data"][index], b);
                    return {"when": {"b": b, "n": n, "t": t}};
                })();
                r["data"][index]["info"]["bpm"] = (function () {
                    var bpm = 125;
                    var v = [];
                    var add = function (bpm, d) {
                        for(var i = 0; i < v.length; i++) {
                            if(v[i][0] == bpm) {
                                v[i][1] += d;
                                return;
                            }
                        }
                        v.push([bpm, d]);
                    };
                    for(var i = 0; i < r["data"][index]["beat"].length; i++) {
                        add(r["data"][index]["beat"][i]["bpm"], (i + 1 < r["data"][index]["beat"].length ? r["data"][index]["beat"][i + 1]["when"]["b"] - r["data"][index]["beat"][i]["when"]["b"] : r["data"][index]["info"]["max"]["when"]["b"] - r["data"][index]["beat"][i]["when"]["b"]));
                    }
                    var d = 0;
                    for(var i = 0; i < v.length; i++) {
                        if(d < v[i][1]) {
                            d = v[i][1];
                            bpm = v[i][0];
                        }
                    }
                    return bpm;
                })();
                r["data"][index] = toData(r["data"][index], "TrackCheck");
                if(Checks["LinkSingle"]) {
                    r["data"][index] = toData(r["data"][index], "LinkSingle");
                }
                if(Checks["LinkLong"]) {
                    r["data"][index] = toData(r["data"][index], "LinkLong");
                }
                if(Checks["LinkSlide"]) {
                    r["data"][index] = toData(r["data"][index], "LinkSlide");
                }
                if(Checks["TransOblique"]) {
                    r["data"][index] = toData(r["data"][index], "TransOblique");
                }
                if(Checks["NoOblique"]) {
                    r["data"][index] = toData(r["data"][index], "NoOblique");
                }
                if(Checks["NoSlide"]) {
                    r["data"][index] = toData(r["data"][index], "NoSlide");
                }
                if(Checks["NoLong"]) {
                    r["data"][index] = toData(r["data"][index], "NoLong");
                }
                if(Checks["NoSingle"]) {
                    r["data"][index] = toData(r["data"][index], "NoSingle");
                }
                if(Checks["NoIntersect"]) {
                    r["data"][index] = toData(r["data"][index], "NoIntersect");
                }
                if(Checks["NoCross"]) {
                    r["data"][index] = toData(r["data"][index], "NoCross");
                }
            })();
            var formatValue = (function () {
                for(var i = 0; i < r["data"][index]["beat"].length; i++) {
                    r["data"][index]["beat"][i]["when"]["b"] = r["data"][index]["beat"][i]["when"]["b"].toAbbreviated(6);
                    r["data"][index]["beat"][i]["when"]["n"] = r["data"][index]["beat"][i]["when"]["n"].toAbbreviated(6);
                    r["data"][index]["beat"][i]["when"]["t"] = r["data"][index]["beat"][i]["when"]["t"].toAbbreviated(0);
                    r["data"][index]["beat"][i]["bpm"] = r["data"][index]["beat"][i]["bpm"].toAbbreviated(6);
                    r["data"][index]["beat"][i]["bpn"] = r["data"][index]["beat"][i]["bpn"].toAbbreviated(6);
                    r["data"][index]["beat"][i]["flow"] = r["data"][index]["beat"][i]["flow"].toAbbreviated(6);
                }
                ;
                for(var i = 0; i < r["data"][index]["note"].length; i++) {
                    r["data"][index]["note"][i]["when"]["b"] = r["data"][index]["note"][i]["when"]["b"].toAbbreviated(6);
                    r["data"][index]["note"][i]["when"]["n"] = r["data"][index]["note"][i]["when"]["n"].toAbbreviated(6);
                    r["data"][index]["note"][i]["when"]["t"] = r["data"][index]["note"][i]["when"]["t"].toAbbreviated(0);
                    r["data"][index]["note"][i]["where"]["x"] = r["data"][index]["note"][i]["where"]["x"].toAbbreviated(6);
                    r["data"][index]["note"][i]["where"]["y"] = r["data"][index]["note"][i]["where"]["y"].toAbbreviated(6);
                    r["data"][index]["note"][i]["where"]["z"] = r["data"][index]["note"][i]["where"]["z"].toAbbreviated(6);
                    for(var j = 0; j < r["data"][index]["note"][i]["line"]["vector"].length; j++) {
                        r["data"][index]["note"][i]["line"]["vector"][j]["where"]["x"] = r["data"][index]["note"][i]["line"]["vector"][j]["where"]["x"].toAbbreviated(6);
                        r["data"][index]["note"][i]["line"]["vector"][j]["where"]["y"] = r["data"][index]["note"][i]["line"]["vector"][j]["where"]["y"].toAbbreviated(6);
                        r["data"][index]["note"][i]["line"]["vector"][j]["where"]["z"] = r["data"][index]["note"][i]["line"]["vector"][j]["where"]["z"].toAbbreviated(6);
                    }
                }
                ;
                r["data"][index]["info"]["length"]["when"]["b"] = r["data"][index]["info"]["length"]["when"]["b"].toAbbreviated(6);
                r["data"][index]["info"]["length"]["when"]["n"] = r["data"][index]["info"]["length"]["when"]["n"].toAbbreviated(6);
                r["data"][index]["info"]["length"]["when"]["t"] = r["data"][index]["info"]["length"]["when"]["t"].toAbbreviated(0);
                r["data"][index]["info"]["max"]["when"]["b"] = r["data"][index]["info"]["max"]["when"]["b"].toAbbreviated(6);
                r["data"][index]["info"]["max"]["when"]["n"] = r["data"][index]["info"]["max"]["when"]["n"].toAbbreviated(6);
                r["data"][index]["info"]["max"]["when"]["t"] = r["data"][index]["info"]["max"]["when"]["t"].toAbbreviated(0);
                r["data"][index]["info"]["min"]["when"]["b"] = r["data"][index]["info"]["min"]["when"]["b"].toAbbreviated(6);
                r["data"][index]["info"]["min"]["when"]["n"] = r["data"][index]["info"]["min"]["when"]["n"].toAbbreviated(6);
                r["data"][index]["info"]["min"]["when"]["t"] = r["data"][index]["info"]["min"]["when"]["t"].toAbbreviated(0);
            })();
            if(r["data"].length == 1) {
                info.setKey(r["data"][index]["info"]["key"]);
            }
        }
        isValid = true;
        return r;
    };
    var setRaw = function (mst) {
        Raw = mst;
        Semi = getSemi();
    };

    var fromBmsBuffer = function (buffer) {
        var ext = (arguments.length > 1 ? arguments[1] : "");
        var hTime = 0;
        var hBPM = 130;
        var hLNTYPE = 1;
        var hLNOBJ = "";
        var hWAVs = [];
        var hBMPs = [];
        var hBGAs = [];
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
                        case "LENGTH":
                            v = parseFloat(v);
                            if(!isNaN(v) && (v >= 0)) {
                                hTime = v;
                            }
                            break;
                        case "BPM":
                            v = parseFloat(v);
                            if(!isNaN(v) && (v >= 0)) {
                                hBPM = v;
                            }
                            break;
                        case "LNTYPE":
                            v = parseInt(v);
                            if(!isNaN(v) && (v != 0)) {
                                hLNTYPE = v;
                            }
                            break;
                        case "LNOBJ":
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
                            case "BPM":
                                c = c.toBase(36);
                                if(!isNaN(c)) {
                                    v = parseFloat(v);
                                    if(!isNaN(v) && (v >= 0)) {
                                        hBPMs[c] = v;
                                    }
                                }
                                break;
                            case "WAV":
                                c = c.toBase(36);
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
                            if(isUndefined(d[m])) {
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
        switch(ext) {
            case "bms":
                if(isChanelExist([16, 26])) {
                    info.type = "bms2";
                }
                if(isChanelExist([17, 27])) {
                    info.type = "bm98";
                }
                if(isChanelExist([19, 29])) {
                    if(info.type != "bms2") {
                        info.type = "bms";
                    }
                }
                break;
            case "bme":
                info.type = "bme5";
                if(isChanelExist([11, 12, 21, 22])) {
                    info.type = "bme3";
                    if(isChanelExist([18, 19, 28, 29])) {
                        info.type = "bme14";
                    }
                }
                break;
            case "pms":
                info.type = "pms5";
                if(isChanelExist([11, 12])) {
                    info.type = "pms3";
                    if(isChanelExist([22, 23, 24, 25])) {
                        info.type = "pms";
                    }
                    if(isChanelExist([16, 17, 18, 19, 26, 27, 28, 29])) {
                        info.type = "pmse";
                    }
                }
                break;
            default:
                info.type = "bms2";
                break;
        }
        info.type = (info.type == "" ? "bms2" : info.type);
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
            switch(info.type) {
                case "bm98":
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
                case "bms":
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
                case "bms2":
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
                case "bme3":
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
                case "bme5":
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
                case "bme14":
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
                case "pms3":
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
                case "pms5":
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
                case "pms":
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
                case "pmse":
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
            if(!isUndefined(d[m])) {
                var innerBPMs03 = [0];
                var innerBPMs08 = [0];
                var innerBPMsM = [];
                if(!isUndefined(d[m][3])) {
                    var s = fillScript(d[m][3]);
                    for(var i = 0; i < s.length; i++) {
                        var v = new Number().fromBase(s.substring(i, i + 2), 16);
                        if(isNaN(v)) {
                            v = 0;
                        }
                        innerBPMs03[i / 2] = v;
                        i += 1;
                    }
                }
                if(!isUndefined(d[m][8])) {
                    var s = fillScript(d[m][8]);
                    for(var i = 0; i < s.length; i++) {
                        var v = 0;
                        var h = new Number().fromBase(s.substring(i, i + 2), 36);
                        if(!isUndefined(hBPMs[h])) {
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
                if(!isUndefined(d[m][2])) {
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
                    if(!isUndefined(d[m][c])) {
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
                                        if(isUndefined(snData[t])) {
                                            snData[t] = [];
                                        }
                                        snData[t].push([Math.round(tm + measureActionTimestamps(innerTimestamps[m], i / 2, s.length / 2)), u]);
                                    } else if((c > 50) && (hLNTYPE == 1)) {
                                        if(isAvd) {
                                            isAvd = (avdLnScript.indexOf(u) != -1);
                                        }
                                        if(isUndefined(lnData[t])) {
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
                if(!isUndefined(d[m][1])) {
                    var s = fillScript(d[m][1]);
                    for(var i = 0; i < s.length; i++) {
                        var h = new Number().fromBase(s.substring(i, i + 2), 36);
                        if(!isUndefined(hWAVs[h])) {
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
        if(WAVTimestamps.length > 0) {
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
            if(!isUndefined(snData[i])) {
                for(var j = 0; j < snData[i].length; j++) {
                    if((hLNTYPE == 2) && (j != snData[i].length - 1) && (snData[i][j + 1][1] == hLNOBJ)) {
                        actionData.push([snData[i][j][0], i, 2, snData[i][j + 1][0] - snData[i][j][0]]);
                        j += 1;
                    } else {
                        if(isAvd) {
                            switch(snData[i][j][1]) {
                                case "A8":
                                    actionData.push([snData[i][j][0], i, 0x00, 0]);
                                    break;
                                case "A3":
                                case "A4":
                                case "A5":
                                case "A6":
                                case "A7":
                                case "A9":
                                case "AA":
                                case "AB":
                                case "AC":
                                    var t = new Number().fromBase(snData[i][j][1], 36) - new Number().fromBase("A8", 36);
                                    actionData.push([snData[i][j][0], i - t, 0x01, t]);
                                    break;
                                case "E3":
                                case "E4":
                                case "E5":
                                case "E6":
                                case "E7":
                                case "E9":
                                case "EA":
                                case "EB":
                                case "EC":
                                    var t = new Number().fromBase(snData[i][j][1], 36) - new Number().fromBase("E8", 36);
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
                if(!isUndefined(lnData[i])) {
                    for(var j = 0; j < lnData[i].length; j++) {
                        if(j != lnData[i].length - 1) {
                            if(isAvd) {
                                switch(lnData[i][j][1]) {
                                    case "A8":
                                        actionData.push([lnData[i][j][0], i, 0x02, lnData[i][j + 1][0] - lnData[i][j][0]]);
                                        break;
                                    case "B8":
                                        actionData.push([lnData[i][j][0], i, 0x62, lnData[i][j + 1][0] - lnData[i][j][0]]);
                                        break;
                                    case "C3":
                                    case "C4":
                                    case "C5":
                                    case "C6":
                                    case "C7":
                                    case "C8":
                                    case "C9":
                                    case "CA":
                                    case "CB":
                                    case "CC":
                                        var t = new Number().fromBase(lnData[i][j][1], 36) - new Number().fromBase("C8", 36);
                                        if(t != 0) {
                                            actionData.push([lnData[i][j][0], i - t, 0x61, t]);
                                        }
                                        actionData.push([lnData[i][j][0], i, 0x22, lnData[i][j + 1][0] - lnData[i][j][0]]);
                                        break;
                                    case "D3":
                                    case "D4":
                                    case "D5":
                                    case "D6":
                                    case "D7":
                                    case "D9":
                                    case "DA":
                                    case "DB":
                                    case "DC":
                                        var t = new Number().fromBase(lnData[i][j][1], 36) - new Number().fromBase("D8", 36);
                                        if(t != 0) {
                                            actionData.push([lnData[i][j][0], i - t, 0x21, t]);
                                        }
                                        actionData.push([lnData[i][j][0], i, 0x22, lnData[i][j + 1][0] - lnData[i][j][0]]);
                                        break;
                                    case "E3":
                                    case "E4":
                                    case "E5":
                                    case "E6":
                                    case "E7":
                                    case "E8":
                                    case "E9":
                                    case "EA":
                                    case "EB":
                                    case "EC":
                                        var t = new Number().fromBase(lnData[i][j][1], 36) - new Number().fromBase("E8", 36);
                                        if(t != 0) {
                                            actionData.push([lnData[i][j][0], i - t, 0x61, t]);
                                        }
                                        actionData.push([lnData[i][j][0], i, 0xA2, lnData[i][j + 1][0] - lnData[i][j][0]]);
                                        break;
                                    case "F3":
                                    case "F4":
                                    case "F5":
                                    case "F6":
                                    case "F7":
                                    case "F9":
                                    case "FA":
                                    case "FB":
                                    case "FC":
                                        var t = new Number().fromBase(lnData[i][j][1], 36) - new Number().fromBase("F8", 36);
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

        setRaw({"data": [
            {"beat": beat, "note": note, "info": {"length": {"when": {"t": hTime}}}}
        ]});
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
            if(isUndefined(vos.data[i])) {
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
            case "vos":
                fromVosBuffer(new Uint8Array().fromHex(buffer.getText()));
                break;
            case "imd":
                fromImdBuffer([new Uint8Array().fromHex(buffer.getText())]);
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
                            case "#N":
                                c = 1;
                                break;
                            case "#E":
                                c = 2;
                                break;
                            case "#M":
                                c = 3;
                                break;
                        }
                        if(isUndefined(d[c])) {
                            d[c] = [];
                        }
                        d[c].push[r[k]];
                    }
                    r.splice(i, j - i);
                    for(var k = 0; k < d.length; k++) {
                        if(d[d.length - k - 1].length > 0) {
                            r.inserts(d[d.length - k - 1], i);
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
                    case "GAME":
                        game = v;
                        meta["Game"] = v;
                        break;
                    case "TIME":
                        v = parseFloat(v);
                        if(!isNaN(v) && (v >= 0)) {
                            hTime = Math.round(v * 1000);
                        }
                        break;
                    case "OFFSET":
                        v = parseFloat(v);
                        if(!isNaN(v)) {
                            hOffset = Math.round(v * 1000);
                            offset = hOffset;
                        }
                        break;
                    case "BPM":
                        v = parseFloat(v);
                        if(!isNaN(v) && (v >= 0)) {
                            bpm = v;
                        }
                        break;
                    case "COURSE":
                        switch(v.toUpperCase().trim()) {
                            case "0":
                            case "EASY":
                                course = 0;
                                break;
                            case "1":
                            case "NORMAL":
                                course = 1;
                                break;
                            case "2":
                            case "HARD":
                                course = 2;
                                break;
                            case "3":
                            case "ONI":
                                course = 3;
                                break;
                            case "4":
                            case "EDIT":
                                course = 4;
                                break;
                            case "5":
                            case "TOWER":
                                course = 5;
                                break;
                        }
                        break;
                    case "LEVEL":
                        level = v;
                        break;
                    case "STYLE":
                        switch(v.toUpperCase().trim()) {
                            case "1":
                            case "SINGLE":
                                style = 1;
                                break;
                            case "2":
                            case "DOUBLE":
                            case "COUPLE":
                                style = 2;
                                break;
                        }
                        break;
                    case "BALLOON":
                        v = v.split(",");
                        for(var j = 0; j < v.length; j++) {
                            if(!isNaN(v[j]) && (v[j] >= 0)) {
                                balloon.push(v[j]);
                            }
                        }
                        break;
                    case "SCOREINIT":
                        v = parseFloat(v);
                        if(!isNaN(v) && (v >= 0)) {
                            scoreinit = v;
                        }
                        break;
                    case "SCOREDIFF":
                        v = parseFloat(v);
                        if(!isNaN(v) && (v >= 0)) {
                            scorediff = v;
                        }
                        break;
                    case "SCOREMODE":
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
                            case "START":
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
                                        case "P1":
                                            return 1;
                                            break;
                                        case "P2":
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
                            case "END":
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
                            case "BPMCHANGE":
                                if(!isNaN(v) && (v > 0)) {
                                    bpm = v;
                                }
                                break;
                            case "MEASURE":
                                var ld = v.indexOf("/");
                                var d1 = v.substring(0, ld).trim();
                                var d2 = v.substring(ld + 1).trim();
                                v = d1 / d2;
                                if(!isNaN(v) && (v > 0)) {
                                    beat = v;
                                }
                                break;
                            case "DELAY":
                                if(!isNaN(v)) {
                                    delay += Math.floor(v * 1000);
                                }
                                break;
                            case "SECTION":
                            case "BRANCHSTART":
                            case "LEVELHOLD":
                            case "BRANCHEND":
                            case "GOGOSTART":
                            case "GOGOEND":
                            case "SCROLL":
                            case "BMSCROLL":
                            case "HBSCROLL":
                            case "BARLINEON":
                            case "BARLINEOFF":
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
                            case ",":
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
                                                case "1":
                                                case "2":
                                                case "3":
                                                case "4":
                                                case "5":
                                                case "6":
                                                case "7":
                                                case "8":
                                                case "9":
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
                                            case "1":
                                                actionData[index].push([time, (dLeft ? 1 : 2), 0x00, 0]);
                                                dLeft = !dLeft;
                                                break;
                                            case "2":
                                                actionData[index].push([time, (kLeft ? 0 : 3), 0x00, 0]);
                                                kLeft = !kLeft;
                                                break;
                                            case "3":
                                                actionData[index].push([time, 1, 0x00, 0]);
                                                actionData[index].push([time, 2, 0x00, 0]);
                                                break;
                                            case "4":
                                                actionData[index].push([time, 0, 0x00, 0]);
                                                actionData[index].push([time, 3, 0x00, 0]);
                                                break;
                                            case "5":
                                                if(l5Start == -1) {
                                                    l5Start = time;
                                                }
                                                break;
                                            case "6":
                                                if(l6Start == -1) {
                                                    l6Start = time;
                                                }
                                                break;
                                            case "7":
                                                if(l7Start == -1) {
                                                    l7Start = time;
                                                }
                                                break;
                                            case "8":
                                                break;
                                            case "9":
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
        var indexes = [];
        for(var i = 0; i < index + 1; i++) {
            data[i] = {"ActionData": actionData[i].sort(SortNumbers), "BeatData": beatData[i], "Length": hTime};
            list[i] = "C " + meta["Course"][i] + ", L " + meta["Level"][i] + (isUndefined(meta["Player"][i]) ? "" : ", P " + meta["Player"][i]);
            indexes[i] = i;
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
            if(isUndefined(e)) {
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
            if(!isUndefined(s)) {
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
                if(isUndefined(r[i])) {
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
                            return -1;
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
                                case "L":
                                case "C":
                                case "P":
                                case "B":
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
                        return [x, y];
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
                            if((l > s[j]) && (!l.isClose(s[j]))) {
                                l -= s[j];
                            } else {
                                var x = d[j][0] + (d[j + 1][0] - d[j][0]) * l / s[j];
                                var y = d[j][1] + (d[j + 1][1] - d[j][1]) * l / s[j];
                                return [x, y];
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
                        case "L":
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
                        case "C":
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
                        case "P":
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
                        case "B":
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
                        case "Slider":
                            if(a[i].length > 7) {
                                v = parseFloat(a[i][7]) || 0;
                                v = fromBeat(toBeat(timestamp) + a[i][7] / sliderMultiplier / 100) - timestamp;
                            }
                            break;
                        case "Spinner":
                            if(a[i].length > 5) {
                                v = (parseFloat(a[i][5]) || 0) - timestamp;
                            }
                            break;
                        case "Hold":
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
                            return "Circle";
                        } else if(a[i][3] >> 1 & 1) {
                            return "Slider";
                        } else if(a[i][3] >> 3 & 1) {
                            return "Spinner";
                        } else if(a[i][3] >> 7 & 1) {
                            return "Hold";
                        }
                    }
                    return "";
                })();
                var timestamp = (function () {
                    if(a[i].length > 2) {
                        a[i][2] = parseFloat(a[i][2]) || -1;
                        return a[i][2];
                    }
                    return -1;
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
                            case "Circle":
                                action = 0x00;
                                r.push([timestamp, track, action, timespan]);
                                break;
                            case "Slider":
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
                                        if((t > timespan) || (t.isClose(timespan))) {
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
                                    r.writes(d);
                                }
                                break;
                            case "Spinner":
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
                                        return "000";
                                    })();
                                    switch(tag) {
                                        case "000":
                                            r.push([timestamp, track, action, timespan]);
                                            break;
                                        case "001":
                                        case "100":
                                        case "101":
                                            r.push([timestamp, track, action, timespan]);
                                            break;
                                        case "010":
                                            r.push([timestamp, 1, action, timespan]);
                                            r.push([timestamp, 2, action, timespan]);
                                            break;
                                        case "011":
                                        case "110":
                                        case "111":
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
                            case "Circle":
                                action = 0x00;
                                addData();
                                break;
                            case "Slider":
                            case "Spinner":
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
                            case "Circle":
                                action = 0x00;
                                break;
                            case "Hold":
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
                        if(r[j][0].isClose(r[i][1]) && (r[j][1] == r[i][1] + r[i][3])) {
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
            case "yddr":
                var a = buffer.getText().replace(/\r/g, "\n").replace(/\n\n/g, "\n").extract("<beats>\n", "\n</beats>").split("\n");
                var d = [];
                var note = [];
                var key = 0;
                var trackToX = function (track) {
                    return (track * 2 + 1) / (key * 2);
                };
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
                                var track = parseFloat(t[0]);
                                if(isNaN(track)) {
                                    continue;
                                }
                                if(key < track) {
                                    key = track;
                                }
                                d.push([time, 0x00, track, time]);
                                break;
                            case 2:
                                var track = parseFloat(t[0]);
                                if(isNaN(track)) {
                                    continue;
                                }
                                if(key < track) {
                                    key = track;
                                }
                                var time2 = parseFloat(t[1]);
                                if(isNaN(time2)) {
                                    continue;
                                }
                                time2 += time;
                                d.push([time, 0x02, track, time2]);
                                break;
                        }
                    }
                }
                for(var i = 0; i < d.length; i++) {
                    switch(d[i][1]) {
                        case 0x00:
                            note.push({"when": {"t": d[i][0]}, "where": {"x": trackToX(d[i][2] - 1)}, "link": {"b": -1, "c": note.length, "a": -1}});
                            break;
                        case 0x02:
                            note.push({"when": {"t": d[i][0]}, "where": {"x": trackToX(d[i][2] - 1)}, "link": {"b": -1, "c": note.length, "a": note.length + 1}});
                            note.push({"when": {"t": d[i][3]}, "where": {"x": trackToX(d[i][2] - 1)}, "link": {"b": note.length - 1, "c": note.length, "a": -1}});
                            break;
                    }
                }
                setRaw({"data": [
                    {"note": note, "info": {"key": key}}
                ]});
                break;
            case "ydsd":
                fromXmlBuffer(new Uint8Array().fromBase64(buffer.getText()).cryptDES(false, new Uint8Array().fromText(new Uint8Array().fromText((3263047).toString(16)).getBase64())), "yddr");
                break;
            case "mde":
                var xml = buffer.getText();
                var p = 0;
                var readXml = function (x, f, b) {
                    var b = (arguments.length > 2 ? arguments[2] : true);
                    if(isUndefined(x)) {
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
                        l2 = x.indexOf("/>", l1);
                    }
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
                var readInt = function (x, f) {
                    var r = readString(x, f);
                    if(!isUndefined(r)) {
                        r = parseInt(r);
                    }
                    if(isNaN(r)) {
                        return;
                    }
                    return r;
                };
                var readFloat = function (x, f) {
                    var r = readString(x, f);
                    if(!isUndefined(r)) {
                        r = parseFloat(r);
                    }
                    if(isNaN(r)) {
                        return;
                    }
                    return r;
                };
                var length = readFloat(xml, "mLength") * 1000;
                var songName = readString(xml, "mSongName");
                var delay = readFloat(xml, "mStartDelay") * 1000;
                var bpm = readFloat(xml, "mBPM");
                var bpmList = readXml(xml, "mBPMList");
                var notes = readXml(xml, "mNotes");
                var canvasSize = (function () {
                    var s = readXml(xml, "mCanvasSize");
                    var x = readFloat(s, "x");
                    var y = readFloat(s, "y");
                    return [x, y];
                })();
                var camList = readXml(xml, "mCamList");
                var defaultCamSize = readFloat(xml, "mDefaultCamSize");
                bpmList = (function () {
                    var v = [];
                    p = 0;
                    while(true) {
                        var x = readXml(bpmList, "CNoteBPMData", false);
                        if(isUndefined(x)) {
                            break;
                        }
                        v.push(x);
                    }
                    return v;
                })();
                notes = (function () {
                    var v = [];
                    p = 0;
                    while(true) {
                        var x = readXml(notes, "CNoteBase", false);
                        if(isUndefined(x)) {
                            break;
                        }
                        v.push(x);
                    }
                    return v;
                })();
                camList = (function () {
                    var v = [];
                    p = 0;
                    while(true) {
                        var x = readXml(camList, "CNoteCamData", false);
                        if(isUndefined(x)) {
                            break;
                        }
                        v.push(x);
                    }
                    return v;
                })();
                var bpmData = (function () {
                    var v = [];
                    for(var i = 0; i < bpmList.length; i++) {
                        var index = readInt(bpmList[i], "mIdx");
                        var tick = readFloat(bpmList[i], "mTick");
                        var bpm = readFloat(bpmList[i], "mBPM");
                        if(bpm > 0) {
                            v.push([index, tick - delay, bpm]);
                        }
                    }
                    v = v.sort(SortNumbers);
                    for(var i = 0; i < v.length; i++) {
                        var d = [];
                        for(var j = 1; j < v[i].length; j++) {
                            d.push(v[i][j]);
                        }
                        v[i] = d;
                    }
                    return v;
                })();
                var noteData = (function () {
                    var v = [];
                    for(var i = 0; i < notes.length; i++) {
                        var type = notes[i].extract("xsi:type=" + '"', '"' + ">");
                        var index = readInt(notes[i], "mIdx");
                        var tick = readFloat(notes[i], "mTick");
                        var pos = (function () {
                            var s = readXml(notes[i], "mPos");
                            var x = readFloat(s, "x");
                            var y = readFloat(s, "y");
                            return [x, y];
                        })();
                        var slideDirection = readString(notes[i], "mSlideDirection");
                        var isCircleTail = readString(notes[i], "mIsCircleTail") || "";
                        var isCircleClockwise = readString(notes[i], "mIsCircleClockwise") || "";
                        var points = (function () {
                            var v = [];
                            var j = readXml(notes[i], "mPoints");
                            if(isUndefined(j)) {
                                return v;
                            }
                            p = 0;
                            while(true) {
                                var s = readXml(j, "Vector2", false);
                                if(isUndefined(s)) {
                                    break;
                                }
                                var x = readFloat(s, "x");
                                var y = readFloat(s, "y");
                                v.push([x, y]);
                            }
                            if((v.length == 0) || ((v[0][0] != pos[0]) || (v[0][1] != pos[1]))) {
                                v.unshift(pos);
                            }
                            return v;
                        })();
                        var endTick = readFloat(notes[i], "mEndTick");
                        var isHeadFollowType = readString(notes[i], "mIsHeadFollowType") || "";
                        var judgeCnt = readInt(notes[i], "mJudgeCnt") || -1;
                        v.push([index, type, tick - delay, pos, points, endTick - delay, slideDirection, (isCircleTail == "true" ? isCircleClockwise : ""), (type == "CNoteLong" ? isHeadFollowType : ""), judgeCnt]);
                    }
                    v = v.sort(SortNumbers);
                    for(var i = 0; i < v.length; i++) {
                        var d = [];
                        for(var j = 1; j < v[i].length; j++) {
                            d.push(v[i][j]);
                        }
                        v[i] = d;
                    }
                    return v;
                })();
                var camData = (function () {
                    var v = [];
                    for(var i = 0; i < camList.length; i++) {
                        var index = readInt(camList[i], "mIdx");
                        var tick = readFloat(camList[i], "mTick");
                        var pos = (function () {
                            var s = readXml(camList[i], "mPos");
                            var x = readFloat(s, "x");
                            var y = readFloat(s, "y");
                            return [x, y];
                        })();
                        var size = readFloat(camList[i], "mSize");
                        if(size > 0) {
                            v.push([index, tick - delay, pos, size]);
                        }
                    }
                    if((v.length > 0) && (v[0][1] != 0) || (v.length == 0)) {
                        v.unshift([-1, 0, [canvasSize[0] / 2, canvasSize[1] / 2], defaultCamSize]);
                    }
                    v = v.sort(SortNumbers);
                    for(var i = 0; i < v.length; i++) {
                        var d = [];
                        for(var j = 1; j < v[i].length; j++) {
                            d.push(v[i][j]);
                        }
                        v[i] = d;
                    }
                    return v;
                })();
                var getCam = function (t) {
                    var cL = [];
                    var cR = [];
                    if(t < camData[0][0]) {
                        cL = [0, [canvasSize[0] / 2, canvasSize[1] / 2], defaultCamSize];
                        cR = [camData[0][0], [camData[0][1][0], camData[0][1][1]], camData[0][2]];
                    } else if(t >= camData[camData.length - 1][0]) {
                        cL = [camData[camData.length - 1][0], [camData[camData.length - 1][1][0], camData[camData.length - 1][1][1]], camData[camData.length - 1][2]];
                        cR = [camData[camData.length - 1][0], [camData[camData.length - 1][1][0], camData[camData.length - 1][1][1]], camData[camData.length - 1][2]];
                    } else {
                        for(var i = 0; i < camData.length - 1; i++) {
                            if((camData[i][0] <= t) && (camData[i + 1][0] > t)) {
                                cL = [camData[i][0], [camData[i][1][0], camData[i][1][1]], camData[i][2]];
                                cR = [camData[i + 1][0], [camData[i + 1][1][0], camData[i + 1][1][1]], camData[i + 1][2]];
                                break;
                            }
                        }
                    }
                    var r = (cR[0] == cL[0] ? 0 : (t - cL[0]) / (cR[0] - cL[0]));
                    var x = cL[1][0] + (cR[1][0] - cL[1][0]) * r;
                    var y = cL[1][1] + (cR[1][1] - cL[1][1]) * r;
                    var s = cL[2] + (cR[2] - cL[2]) * r;
                    return [t, [x, y], s];
                }
                var transPos = function (t, p) {
                    var limit = (arguments.length > 2 ? arguments[2] : false);
                    var cam = getCam(t);
                    var x = cam[1][0];
                    var y = cam[1][1];
                    var s = cam[2];
                    var rx = (p[0] - (x - s * 1.5)) / (s * 1.5 * 2);
                    var ry = (p[1] - (y - s)) / (s * 2);
                    if(limit) {
                        if((rx < 0) || (rx >= 1) || (ry < 0) || (ry >= 1)) {
                            var rr = 0;
                            while(((rx < 0) && (-rx / rr >= 1)) || ((ry < 0) && (-ry / rr >= 1)) || ((rx >= 1) && (rx / rr >= 1)) || ((ry >= 1) && (ry / rr >= 1))) {
                                rr += 1;
                            }
                            rx = (rx / rr + 1 ) / 2;
                            ry = (ry / rr + 1 ) / 2;
                        }
                    }
                    var scaleY = 2 / 3;
                    var offsetY = -2 / 3 / 10;
                    ry = 0.5 - (0.5 - ry + offsetY) / scaleY;
                    return [rx, ry];
                };
                var beat = (function () {
                    var v = [];
                    for(var i = 0; i < bpmData.length; i++) {
                        v.push({"when": {"t": bpmData[i][0]}, "bpm": bpmData[i][1]});
                    }
                    if(v.length == 0) {
                        v.push({"when": {"t": 0}, "bpm": bpm});
                    }
                    return v;
                })();
                var note = (function () {
                    var v = [];
                    for(var i = 0; i < noteData.length; i++) {
                        noteData[i][2] = transPos(noteData[i][1], noteData[i][2], true);
                        var vector = (function () {
                            var p = [];
                            if(noteData[i][3].length > 0) {
                                var t = [];
                                var s = 0;
                                for(var j = 0; j < noteData[i][3].length; j++) {
                                    var d = (j == 0 ? 0 : Math.sqrt(Math.pow(noteData[i][3][j][0] - noteData[i][3][j - 1][0], 2) + Math.pow(noteData[i][3][j][1] - noteData[i][3][j - 1][1], 2)));
                                    s += d;
                                    t.push(s);
                                }
                                for(var j = 0; j < noteData[i][3].length; j++) {
                                    var time = noteData[i][1] + t[j] / s * (noteData[i][4] - noteData[i][1])
                                    noteData[i][3][j] = transPos(time, noteData[i][3][j], false);
                                    p.push({"when": {"t": time}, "where": {"x": noteData[i][3][j][0], "y": noteData[i][3][j][1]}});
                                }
                            }
                            return p;
                        })();
                        switch(noteData[i][0]) {
                            case "CNoteSingle":
                                v.push({"when": {"t": noteData[i][1]}, "where": {"x": noteData[i][2][0], "y": noteData[i][2][1]}, "link": {"b": -1, "c": v.length, "a": -1}});
                                break;
                            case "CNoteSlide":
                                var x = noteData[i][2][0];
                                var y = noteData[i][2][1];
                                var x2 = noteData[i][2][0];
                                var y2 = noteData[i][2][1];
                                var d = 0.25;
                                var ds = d * Math.SQRT1_2;
                                switch(noteData[i][5]) {
                                    case "Left":
                                        x2 -= d / 2;
                                        x += d / 2;
                                        break;
                                    case "Right":
                                        x2 += d / 2;
                                        x -= d / 2;
                                        break;
                                    case "Up":
                                        y2 += d / 2;
                                        y -= d / 2;
                                        break;
                                    case "Down":
                                        y2 -= d / 2;
                                        y += d / 2;
                                        break;
                                    case "UpLeft":
                                        x2 -= ds / 2;
                                        x += ds / 2;
                                        y2 += ds / 2;
                                        y -= ds / 2;
                                        break;
                                    case "DownLeft":
                                        x2 -= ds / 2;
                                        x += ds / 2;
                                        y2 -= ds / 2;
                                        y += ds / 2;
                                        break;
                                    case "UpRight":
                                        x2 += ds / 2;
                                        x -= ds / 2;
                                        y2 += ds / 2;
                                        y -= ds / 2;
                                        break;
                                    case "DownRight":
                                        x2 += ds / 2;
                                        x -= ds / 2;
                                        y2 -= ds / 2;
                                        y += ds / 2;
                                        break;
                                }
                                x = x.limit(0, 0.999);
                                y = y.limit(0, 0.999);
                                x2 = x2.limit(0, 0.999);
                                y2 = y2.limit(0, 0.999);
                                v.push({"when": {"t": noteData[i][1]}, "where": {"x": x, "y": y}, "link": {"b": -1, "c": v.length, "a": v.length + 1}});
                                v.push({"when": {"t": noteData[i][1]}, "where": {"x": x2, "y": y2}, "link": {"b": v.length - 1, "c": v.length, "a": -1}});
                                break;
                            case "CNoteLong":
                            case "CNoteLongSlide":
                                v.push({"when": {"t": noteData[i][1]}, "where": {"x": noteData[i][2][0], "y": noteData[i][2][1]}, "link": {"b": -1, "c": v.length, "a": v.length + 1}, "line": {"vector": vector, "clockwise": noteData[i][6], "follow": noteData[i][7]}, "combo": noteData[i][8]});
                                v.push({"when": {"t": noteData[i][4]}, "where": ((noteData[i][0] == "CNoteLongSlide") && vector.length ? {"x": vector[vector.length - 1]["where"]["x"], "y": vector[vector.length - 1]["where"]["y"]} : {"x": noteData[i][2][0], "y": noteData[i][2][1]}), "link": {"b": v.length - 1, "c": v.length, "a": -1}});
                                break;
                        }
                    }
                    return v;
                })();
                var transform = (function () {
                    var v = [];
                    var c = [];
                    camData.unshift([0, [canvasSize[0] / 2, canvasSize[1] / 2], defaultCamSize]);
                    for(var i = 1; i < camData.length; i++) {
                        var x = (camData[i - 1][1][0] - camData[i][1][0]).toAbbreviated(6);
                        var y = (camData[i - 1][1][1] - camData[i][1][1]).toAbbreviated(6);
                        var s = (camData[i - 1][2] / camData[i][2]).toAbbreviated(6);
                        c.push([camData[i][0], [x, y], s]);
                    }
                    for(var i = 1; i < c.length - 1; i++) {
                        if(((c[i][1][0] == c[i - 1][1][0]) && (c[i][1][1] == c[i - 1][1][1]) && (c[i][2] == c[i - 1][2])) && ((c[i][1][0] == c[i + 1][1][0]) && (c[i][1][1] == c[i + 1][1][1]) && (c[i][2] == c[i + 1][2]))) {
                            c.splice(i, 1);
                            i -= 1;
                        }
                    }
                    for(var i = 0; i < c.length; i++) {
                        v.push({"when": {"t": c[i][0]}, "matrix": new Matrix().setTranslate3d(c[i][1][0], c[i][1][1], 0).setScale3d(c[i][2], c[i][2], 1).getMatrix3d()});
                    }
                    return v;
                })();
                setRaw({"data": [
                    {"beat": beat, "note": note, "transform": transform, "info": {"length": {"when": {"t": length}}}}
                ]});
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
                return -1;
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
                    return -1;
                })();
                if(e > i) {
                    switch(a[i].substring(1)) {
                        case "FORMAT VERSION":
                            format = parseInt(a[e - 1]) || format;
                            break;
                        case "BEAT INFO":
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
                        case "BPM INFO":
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
                        case "TILT MODE INFO":
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
                        case "LYRIC INFO":
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
                        case "END POSITION":
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
                        case "TAB EFFECT INFO":
                            btEffect = getEffect(i, e);
                            break;
                        case "FXBUTTON EFFECT INFO":
                            fxEffect = getEffect(i, e);
                            break;
                        case "TAB PARAM ASSIGN INFO":
                            tabParamAssign = getEffect(i, e);
                            break;
                        case "REVERB EFFECT PARAM":
                            volEffect = getEffect(i, e);
                            break;
                        case "TRACK1":
                            trackInfo[6] = getVolInfo(i, e);
                            break;
                        case "TRACK2":
                            trackInfo[4] = getFxInfo(i, e);
                            break;
                        case "TRACK3":
                            trackInfo[0] = getBtInfo(i, e);
                            break;
                        case "TRACK4":
                            trackInfo[1] = getBtInfo(i, e);
                            break;
                        case "TRACK5":
                            trackInfo[2] = getBtInfo(i, e);
                            break;
                        case "TRACK6":
                            trackInfo[3] = getBtInfo(i, e);
                            break;
                        case "TRACK7":
                            trackInfo[5] = getFxInfo(i, e);
                            break;
                        case "TRACK8":
                            trackInfo[7] = getVolInfo(i, e);
                            break;
                        case "TRACK AUTO TAB":
                            break;
                        case "SPCONTROLER":
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
            return -1;
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
                                    } else if(timestamps.isClose(ln[i - 6])) {
                                        var action = getAction(1, s, trackInfo[i][j][4] == 2);
                                        if(rv[rv.length - 1][0].isClose(ln[i - 6]) && (rv[rv.length - 1][1] == vx[i - 6]) && ((rv[rv.length - 1][2] == 0x21) || (rv[rv.length - 1][2] == 0xA1))) {
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
            r.writes(rv);
            return r;
        })();
        var beatData = (function () {
            var r = [];
            var t = [];
            for(var i = 0; i < times.length; i++) {
                if(isUndefined(t[times[i][0]])) {
                    t[times[i][0]] = [];
                }
                if(isUndefined(t[times[i][0]][times[i][1]])) {
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
                        case "t":
                            bpm = m[1] || bpm;
                            break;
                        case "beat":
                            var v = m[1].split("/");
                            if(v.length == 2) {
                                var d1 = parseFloat(v[0]);
                                var d2 = parseFloat(v[1]);
                                if((!isNaN(d1)) && (!isNaN(d2)) && (d2 != 0)) {
                                    beat = d1 / d2;
                                }
                            }
                            break;
                        case "tilt":
                            tilt = m[1];
                            break;
                    }
                } else {
                    switch(m[0]) {
                        case "title":
                            meta["Title"] = m[1];
                            break;
                        case "artist":
                            meta["Artist"] = m[1];
                            break;
                        case "effect":
                            meta["Effect"] = m[1];
                            break;
                        case "jacket":
                            meta["Jacket"] = m[1];
                            break;
                        case "illustrator":
                            meta["Illustrator"] = m[1];
                            break;
                        case "difficulty":
                            meta["DifficultyKsh"] = m[1];
                            break;
                        case "level":
                            meta["Level"] = parseFloat(m[1]) || 1;
                            break;
                        case "t":
                            bpm = parseFloat(m[1]) || bpm;
                            meta["BPM"] = bpm;
                            break;
                        case "m":
                            meta["AudioFilenameKsh"] = m[1];
                            var l = m[1].split(";")
                            if(l.length > 0) {
                                meta["AudioFilename"] = l[0];
                            }
                            break;
                        case "mvol":
                            meta["SongVol"] = parseFloat(m[1]) || 100;
                            break;
                        case "o":
                            offset = -(parseFloat(m[1]) || 0);
                            meta["Offset"] = offset;
                            break;
                        case "bg":
                            meta["Background"] = m[1];
                            break;
                        case "layer":
                            meta["Layer"] = m[1];
                            break;
                        case "po":
                            meta["PreviewTime"] = parseFloat(m[1]) || 0;
                            break;
                        case "plength":
                            meta["PreviewLength"] = parseFloat(m[1]) || 0;
                            break;
                        case "pfiltergain":
                            break;
                        case "filtertype":
                            break;
                        case "chokkakuvol":
                            meta["SeVol"] = parseFloat(m[1]) || 0;
                            break;
                        case "icon":
                            meta["Icon"] = m[1];
                            break;
                        case "ver":
                            meta["Version"] = m[1];
                        case "maker":
                            meta["Maker"] = m[1];
                        case "datetime":
                            meta["DateTime"] = m[1];
                            break;
                    }
                }
            } else {
                if(a[i] == "--") {
                    n += 1;
                    if(isUndefined(d[n])) {
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
            if(!isUndefined(t) && !isUndefined(t.length) && (t.length > 0)) {
                return m.indexOf(t[0]) / 50 * 127;
            }
            return -1;
        };
        var toFxE = function (t) {
            var m = "11STIFUHQADXVB11111";
            if(!isUndefined(t) && !isUndefined(t.length) && (t.length > 0)) {
                if(t[0] == "2") {
                    return 255;
                } else {
                    return m.indexOf(t[0]);
                }
            }
            return -1;
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
                        case "0":
                            if(ln[k] != -1) {
                                actionData.push([ln[k], k, 0x02, time - ln[k]]);
                                ln[k] = -1;
                            }
                            break;
                        case "1":
                            actionData.push([time, k, 0x00, 0]);
                            break;
                        case "2":
                            if(ln[k] == -1) {
                                ln[k] = time;
                            }
                            break;
                    }
                }
                for(var k = 4; k < 6; k++) {
                    switch(d[i][j][k]) {
                        case "0":
                            if(ln[k] != -1) {
                                actionData.push([ln[k], (k - 4) * 2, 0x02, time - ln[k]]);
                                actionData.push([ln[k], (k - 4) * 2 + 1, 0x02, time - ln[k]]);
                                ln[k] = -1;
                            }
                            break;
                        case "2":
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
                        case "-":
                            if(ln[k] != -1) {
                                ln[k] = -1;
                                lb[k] = -1;
                                vx[k - 6] = -1;
                            }
                            break;
                        case ":":
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
                            if(actionDataV[j][0].isClose(actionDataV[i][0] + actionDataV[i][3]) && (actionDataV[j][1] == actionDataV[i][1])) {
                                actionDataV[j][3] = actionDataV[j][0] + actionDataV[j][3] - actionDataV[i][0];
                                actionDataV[j][0] = actionDataV[i][0];
                                actionDataV.splice(i, 1);
                                i -= 1;
                                break;
                            }
                        } else if(actionDataV[j][2] == 0x01) {
                            if(actionDataV[j][0].isClose(actionDataV[i][0] + actionDataV[i][3]) && (actionDataV[j][1] == actionDataV[i][1])) {
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
                            if(actionDataV[j][0].isClose(actionDataV[i][0]) && (actionDataV[j][1] == actionDataV[i][1] + actionDataV[i][3])) {
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
        actionData.writes(actionDataV);
        for(var i = 0; i < actionData.length; i++) {
            actionData[i][0] -= offset;
        }
        beatData = enlargeBeatData(offsetBeatData(beatData, offset), actionData);
        fromData(meta, [
            {"ActionData": sortActionData(actionData), "BeatData": beatData}
        ]);
    };
    var fromImdBuffer = function (buffer) {
        var readInt = function (l) {
            if(buffer.length - p < l) {
                p = buffer.length;
                return 0;
            } else {
                var r = buffer.getInt(l, p, true);
                p += l;
                return r;
            }
        };
        var readFloat = function (l) {
            if(buffer.length - p < l) {
                p = buffer.length;
                return 0;
            } else {
                var r = buffer.getFloat(l, p, true);
                p += l;
                return r;
            }
        };
        var p = 0;
        var length = readInt(4);
        var beat = (function () {
            var r = [];
            var d = [];
            var addData = function (time, bpm) {
                var l = (function () {
                    for(var i = 0; i < d.length; i++) {
                        if(d[i][0] == time) {
                            return i;
                        }
                    }
                    return -1;
                })();
                if(l != -1) {
                    d[l][1] = bpm;
                } else {
                    d.push([time, bpm]);
                }
            };
            var addBeat = function (time, bpm) {
                if(time < 0) {
                    time += 60000 / bpm;
                }
                if((r.length == 0) && (time > 0)) {
                    var bpm = 60000 / time;
                    while(bpm < 100) {
                        bpm *= 2;
                    }
                    while(bpm >= 200) {
                        bpm /= 2;
                    }
                    r.push({"when": {"t": 0}, "bpm": bpm});
                }
                var l = (function () {
                    for(var i = 0; i < r.length; i++) {
                        if(r[i]["when"]["t"] == time) {
                            return i;
                        }
                    }
                    return -1;
                })();
                if(l != -1) {
                    r[l]["bpm"] = bpm;
                } else {
                    if((r.length == 0) || ((r.length > 0) && (bpm != r[r.length - 1]["bpm"]))) {
                        r.push({"when": {"t": time}, "bpm": bpm});
                    }
                }
            };
            var count = readInt(4);
            for(var i = 0; i < count; i++) {
                var time = readInt(4);
                var bpm = readFloat(8);
                addData(time, bpm);
            }
            d = d.sort(SortNumbers);
            for(var i = 0; i < d.length; i++) {
                if((d[i][0] < 0) && (i > d.length - 1) && (d[i + 1][0] < 0)) {
                    continue;
                }
                addBeat(d[i][0], d[i][1]);
            }
            return r;
        })();
        var flag = readInt(2);
        var key = 0;
        var note = (function () {
            var r = [];
            var d = [];
            var trackToX = function (track) {
                return (track * 2 + 1) / (key * 2);
            };
            var pointsToNotes = function (p, s) {
                var v = [];
                for(var i = 0; i < p.length; i++) {
                    v.push({"when": {"t": p[i][0]}, "where": {"x": trackToX(p[i][1])}, "link": {"b": (i > 0 ? s + i - 1 : -1), "c": s + i, "a": (i + 1 < p.length ? s + i + 1 : -1)}});
                }
                return v;
            };
            var count = readInt(4);
            for(var i = 0; i < count; i++) {
                var action = readInt(2);
                var time = readInt(4);
                var track = readInt(1);
                var param = readInt(4);
                if((time >= 0) && (time <= length)) {
                    switch(action) {
                        case 0x00:
                            if(key < track) {
                                key = track;
                            }
                            break;
                        case 0x01:
                        case 0x61:
                        case 0x21:
                        case 0xA1:
                            if(key < track + param) {
                                key = track + param;
                            }
                            break;
                        case 0x02:
                        case 0x62:
                        case 0x22:
                        case 0xA2:
                            if(time + param > length) {
                                param = length - time;
                            }
                            if(key < track) {
                                key = track;
                            }
                            break;
                    }
                    d.push([time, track, action, param]);
                }
            }
            key += 1;
            for(var i = 0; i < d.length; i++) {
                var p = [];
                switch(d[i][2]) {
                    case 0x00:
                        p.push([d[i][0], d[i][1]]);
                        break;
                    case 0x01:
                        p.push([d[i][0], d[i][1]]);
                        if(d[i][3] != 0) {
                            p.push([d[i][0], d[i][1] + d[i][3]]);
                        }
                        break;
                    case 0x02:
                        p.push([d[i][0], d[i][1]]);
                        if(d[i][3] != 0) {
                            p.push([d[i][0] + d[i][3], d[i][1]]);
                        }
                        break;
                    case 0x61:
                    case 0x62:
                    case 0x21:
                    case 0x22:
                    case 0xA1:
                    case 0xA2:
                        var e = false;
                        while(true) {
                            switch(d[i][2]) {
                                case 0x62:
                                case 0x22:
                                    p.push([d[i][0], d[i][1]]);
                                    if((i + 1 < d.length) && ((d[i + 1][2] == 0x21) || (d[i + 1][2] == 0xA1))) {
                                        i += 1;
                                    }
                                    break;
                                case 0xA2:
                                    p.push([d[i][0], d[i][1]]);
                                    if(d[i][3] != 0) {
                                        p.push([d[i][0] + d[i][3], d[i][1]]);
                                    }
                                    e = true;
                                    break;
                                case 0x61:
                                case 0x21:
                                    p.push([d[i][0], d[i][1]]);
                                    if((i + 1 < d.length) && ((d[i + 1][2] == 0x22) || (d[i + 1][2] == 0xA2))) {
                                        i += 1;
                                    }
                                    break;
                                case 0xA1:
                                    p.push([d[i][0], d[i][1]]);
                                    if(d[i][3] != 0) {
                                        p.push([d[i][0], d[i][1] + d[i][3]]);
                                    }
                                    e = true;
                                    break;
                            }
                            if(e) {
                                break;
                            }
                        }
                        break;
                }
                r.writes(pointsToNotes(p, r.length));
            }
            return r;
        })();
        setRaw({"data": [
            {"beat": beat, "note": note, "info": {"key": key, "length": {"when": {"t": length}}}}
        ]});
    };
    var fromMdeBuffer = function (buffer) {
        fromXmlBuffer(new Uint8Array().fromBase64(buffer.getText()), "mde")
    };
    var fromMcBuffer = function (buffer) {
        var mc = new Function("return " + buffer.getText())();
        var getBeat = function (t) {
            if(!isUndefined(t) && !isUndefined(t.length) && (t.length == 3)) {
                var b = t[0];
                var c = t[1];
                var d = t[2];
                if(isNmuber(b) && isNmuber(c) && isNmuber(d)) {
                    return b + c / d;
                }
            }
        };
        var meta = (function () {
            var r = {};
            if(!isUndefined(mc["meta"])) {
                r["Cover"] = mc["meta"]["cover"];
                r["Background"] = mc["meta"]["background"];
                r["Version"] = mc["meta"]["version"];
                r["Creator"] = mc["meta"]["creator"];
                r["Maker"] = mc["meta"]["maker"];
                r["DateTime"] = mc["meta"]["time"];
                r["Id"] = mc["meta"]["id"];
                r["Mode"] = mc["meta"]["mode"];
                if(!isUndefined(mc["meta"]["song"])) {
                    r["SongTitle"] = mc["meta"]["song"]["title"];
                    r["SongArtist"] = mc["meta"]["song"]["artist"];
                    r["SongId"] = mc["meta"]["song"]["id"];
                    r["SongSource"] = mc["meta"]["song"]["source"];
                    r["Time"] = mc["meta"]["song"]["length"];
                    if(!isUndefined(mc["meta"]["song"]["org"])) {
                        r["SongOrgTitle"] = mc["meta"]["song"]["org"]["title"];
                        r["SongOrgArtist"] = mc["meta"]["song"]["org"]["artist"];
                        r["SongOrgSource"] = mc["meta"]["song"]["org"]["source"];
                    }
                }
                if(!isUndefined(mc["meta"]["mode_ext"])) {
                    r["Mode_extColumn"] = mc["meta"]["mode_ext"]["column"];
                    r["Mode_extSpeed"] = mc["meta"]["mode_ext"]["speed"];
                    r["Mode_extInterval"] = mc["meta"]["mode_ext"]["interval"];
                }
            }
            return r;
        })();
        var time = (function () {
            var r = [];
            if(!isUndefined(mc["time"])) {
                for(var i = 0; i < mc["time"].length; i++) {
                    var beat = getBeat(mc["time"][i]["beat"]);
                    var bpm = mc["time"][i]["bpm"];
                    var signature = mc["time"][i]["signature"];
                    if(isNumber(beat) && isNumber(bpm)) {
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
            if(!isUndefined(mc["note"])) {
                var m = ["beat", "endbeat", "offset", "column", "x", "index", "endindex", "type", "style", "sound", "vol", "isBgm"];
                for(var i = 0; i < mc["note"].length; i++) {
                    var beat = getBeat(mc["note"][i]["beat"]);
                    var endbeat = getBeat(mc["note"][i]["endbeat"]);
                    if(isNumber(beat)) {
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
            if(!isUndefined(mc["extra"])) {
                if(!isUndefined(mc["extra"]["test"])) {
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
                    if(!isUndefined(note[i]["endbeat"])) {
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
                if(isUndefined(r) && !isUndefined(note[i]["sound"]) && (note[i]["sound"] != "")) {
                    meta["AudioFilename"] = new FileInfo(note[i]["sound"]).name;
                    r = note[i]["offset"];
                    if(isUndefined(r)) {
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
                var timespan = fromBeatDataBeat(beatData, note[i]["endbeat"]) - timestamps;
                timestamps -= offset;
                switch(meta["Mode"]) {
                    case 0:
                        var track = note[i]["column"];
                        if(!isUndefined(track)) {
                            if(track < meta["Mode_extColumn"]) {
                                if(timespan > 0) {
                                    r.push([timestamps, track, 0x02, timespan]);
                                } else {
                                    r.push([timestamps, track, 0x00, 0]);
                                }
                            }
                        }
                        break;
                    case 3:
                        var x = note[i]["x"];
                        if(!isUndefined(x)) {
                            x = (x < 0 ? 0 : x);
                            x = (x > 511 ? 511 : x);
                            r.push([timestamps, Math.floor((x / 512) * key), 0x00, 0]);
                        } else {
                            var type = note[i]["type"];
                            if(!isUndefined(type)) {
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
                                        if(timespan > 0) {
                                            r.push([timestamps, track, 0x02, timespan]);
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
                        if(!isUndefined(index)) {
                            var endindex = note[i]["endindex"];
                            if(!isUndefined(endindex) && (timespan > 0)) {
                                if(timespan > 0) {
                                    var s = index % 4;
                                    var e = endindex % 4;
                                    if(s == e) {
                                        r.push([timestamps, s, 0x02, timespan]);
                                    } else if(s < e) {
                                        var t = timestamps;
                                        var u = timespan / (e - s);
                                        for(var j = s; j < e + 1; j++) {
                                            r.push([t, j, (j == s ? 0x62 : 0x22), u]);
                                            t += u;
                                            r.push([t, j, (j == e ? 0xA1 : 0x21), 1]);
                                        }
                                    } else {
                                        var t = timestamps;
                                        var u = timespan / (s - e);
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
                                        if(r[j][0].isClose(timestamps) && (r[j][1] == index % 4)) {
                                            return j;
                                            break;
                                        }
                                    }
                                    return -1;
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
                        if(!isUndefined(style)) {
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
                                    if(timespan > 0) {
                                        r.push([timestamps, 1, 0x02, timespan]);
                                    } else {
                                        r.push([timestamps, 1, 0x00, 0]);
                                    }
                                    break;
                                case 5:
                                    if(timespan > 0) {
                                        r.push([timestamps, 2, 0x02, timespan]);
                                    } else {
                                        r.push([timestamps, 2, 0x00, 0]);
                                    }
                                    break;
                                case 6:
                                    if(timespan > 0) {
                                        r.push([timestamps, (bLeft ? 0 : 3), 0x02, timespan]);
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
        var offset = 0;
        var arc = [];
        var beat = [];
        var note = [];
        var trackToX = function (track) {
            return (track * 2 + 1) / (4 * 2);
        };
        var toX = function (x) {
            return ((x - 0.5) * 0.998 + 1) / 2;
        };
        var toY = function (y) {
            return ((y - 0.5) * 2 * 0.998 + 1) / 2;
        };
        var n = 0;
        while(true) {
            var d = aff[n].split(":");
            if(d.length == 2) {
                switch(d[0]) {
                    case "AudioOffset":
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
        aff.splice(0, n);
        var s = function (a, b) {
            var getTime = function (d) {
                var l1 = d.indexOf("(");
                if(l1 != -1) {
                    var l2 = d.indexOf(",", l1);
                    if(l2 != -1) {
                        var v = parseFloat(d.substring(l1 + "(".length, l2));
                        if(!isNaN(v)) {
                            return v;
                        }
                    }
                }
                return -1;
            };
            var getType = function (d) {
                var l = d.indexOf("(");
                if(l != -1) {
                    var t = d.substring(0, l).toLowerCase().trim();
                    switch(t) {
                        case "timing":
                            return 0;
                            break;
                        case "":
                            return 1;
                            break;
                        case "hold":
                            return 2;
                            break;
                        case "arc":
                            return 3;
                            break;
                        default:
                            return -1;
                            break;
                    }
                }
                return -1;
            };
            var ta = getTime(a);
            var tb = getTime(b);
            if(ta != tb) {
                return ta - tb;
            } else {
                return getType(a) - getType(b);
            }
        };
        aff.sort(s);
        for(var i = 0; i < aff.length; i++) {
            var l1 = aff[i].indexOf("(");
            if(l1 != -1) {
                var l2 = aff[i].indexOf(")", l1);
                if(l2 != -1) {
                    var l3 = aff[i].indexOf(";", l2);
                    if(l3 != -1) {
                        var d = aff[i].substring(l1 + "(".length, l2).split(",");
                        var m = aff[i].substring(0, l1).toLowerCase().trim();
                        switch(m) {
                            case "timing":
                                if(d.length > 2) {
                                    var time = parseFloat(d[0]);
                                    if(isNaN(time)) {
                                        continue;
                                    }
                                    var bpm = parseFloat(d[1]);
                                    if(isNaN(bpm)) {
                                        continue;
                                    }
                                    var bpn = parseFloat(d[2]);
                                    if((beat.length == 0) || ((beat.length > 0) && (bpm != beat[beat.length - 1]["bpm"]))) {
                                        beat.push({"when": {"t": time}, "bpm": bpm, "flow": bpm});
                                    }
                                }
                                break;
                            case "":
                                if(d.length > 1) {
                                    var time = parseFloat(d[0]);
                                    if(isNaN(time)) {
                                        continue;
                                    }
                                    var track = parseFloat(d[1]);
                                    if(isNaN(track)) {
                                        continue;
                                    }
                                    track -= 1;
                                    track = (track < 0 ? 0 : track);
                                    track = (track > 3 ? 3 : track);
                                    note.push({"when": {"t": time}, "where": {"x": trackToX(track)}, "link": {"b": -1, "c": note.length, "a": -1}});
                                }
                                break;
                            case "hold":
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
                                    track -= 1;
                                    track = (track < 0 ? 0 : track);
                                    track = (track > 3 ? 3 : track);
                                    if(time2 >= time1) {
                                        note.push({"when": {"t": time1}, "where": {"x": trackToX(track)}, "link": {"b": -1, "c": note.length, "a": note.length + 1}});
                                        note.push({"when": {"t": time2}, "where": {"x": trackToX(track)}, "link": {"b": note.length - 1, "c": note.length, "a": -1}});
                                    }
                                }
                                break;
                            case "arc":
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
                                    var group = parseInt(d[7]);
                                    if(isNaN(group) || ((group != 0) && (group != 1))) {
                                        continue;
                                    }
                                    var adv = d[8].trim();
                                    var isTab = d[9].trim();
                                    if((isTab != "true") && ((isTab != "false"))) {
                                        continue;
                                    }
                                    var times = (function () {
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
                                    x1 = toX(x1.limit(-0.5, 1.5));
                                    x2 = toX(x2.limit(-0.5, 1.5));
                                    y1 = toY(y1.limit(0, 1));
                                    y2 = toY(y2.limit(0, 1));
                                    if(((isTab != "true") && !((time2 == time1) && (x2 == x1) && (y2 == y1))) || (isTab == "true")) {
                                        note.push({"when": {"t": time1}, "where": {"x": x1, "y": y1, "z": -1}, "link": {"b": -1, "c": note.length, "a": note.length + 1}, "line": {"curve": "arcaea_" + type}, "group": "arcaea_" + group, "auto": isTab});
                                        note.push({"when": {"t": time2}, "where": {"x": x2, "y": y2, "z": -1}, "link": {"b": note.length - 1, "c": note.length, "a": -1}, "line": {"curve": "arcaea_" + type}, "group": "arcaea_" + group, "auto": isTab});
                                    }
                                    if(times.length > 0) {
                                        for(var j = 0; j < times.length; j++) {
                                            if((times[j] >= time1) && (times[j] <= time2)) {
                                                var x = x1 + ((times[j] == time1) || (time2 == time1) ? 0 : (times[j] - time1) / (time2 - time1) * (x2 - x1));
                                                var y = y1 + ((times[j] == time1) || (time2 == time1) ? 0 : (times[j] - time1) / (time2 - time1) * (y2 - y1));
                                                var isToSlide = true;
                                                if(isToSlide) {
                                                    var x0 = (function () {
                                                        if(((x >= 0) && (x < 0.25)) || ((x >= 0.5) && (x < 0.75))) {
                                                            return x + 0.25;
                                                        } else if(((x >= 0.25) && (x < 0.5)) || ((x >= 0.75) && (x < 1))) {
                                                            return x - 0.25;
                                                        }
                                                    })();
                                                    note.push({"when": {"t": times[j]}, "where": {"x": x0, "y": y, "z": -1}, "link": {"b": -1, "c": note.length, "a": note.length + 1}});
                                                    note.push({"when": {"t": times[j]}, "where": {"x": x, "y": y, "z": -1}, "link": {"b": note.length - 1, "c": note.length, "a": -1}});
                                                } else {
                                                    note.push({"when": {"t": times[j]}, "where": {"x": x, "y": y, "z": -1}, "link": {"b": -1, "c": note.length, "a": -1}});
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }
            }
        }
        for(var i = 1; i < beat.length - 1; i++) {
            if((beat[i]["bpm"] <= 0) || ((beat[i + 1]["when"]["t"] - beat[i]["when"]["t"]) / (60000 / beat[i - 1]["bpm"]) < 4)) {
                beat[i]["bpm"] = beat[i - 1]["bpm"];
            }
        }
        setRaw({"data": [
            {"beat": beat, "note": note, "effect": [
                {"type": "audio", "when": {"t": offset}, "name": info.nameCore + ".mp3"}
            ]}
        ]});
    };
    var fromMstBuffer = function (buffer) {
        setRaw(new Function("return " + buffer.getText())());
    };
    var fromAudioBuffer = function (buffer, ext) {
        setRaw({"file": [
            {"name": info.nameCore + "." + ext, "base64": buffer.getBase64()}
        ], "data": [
            {"effect": [
                {"type": "audio", "name": info.nameCore + "." + ext}
            ]}
        ]});
    };
    var toBmsBuffer = function (index) {
        Text["Bms"][index] = Text["Bms"][index] || toBmsText(index);
        return new Uint8Array().fromText(Text["Bms"][index]);
    };
    var toBmeBuffer = function (index) {
        Text["Bme"][index] = Text["Bme"][index] || toBmeText(index);
        return new Uint8Array().fromText(Text["Bme"][index]);
    };
    var toBmlBuffer = function (index) {
        Text["Bml"][index] = Text["Bml"][index] || toBmlText(index);
        return new Uint8Array().fromText(Text["Bml"][index]);
    };
    var toPmsBuffer = function (index) {
        Text["Pms"][index] = Text["Pms"][index] || toPmsText(index);
        return new Uint8Array().fromText(Text["Pms"][index]);
    };
    var toVosBuffer = function (index, type) {

    };
    var toMidBuffer = function (index) {

    };
    var toLrcBuffer = function (index) {
        Text["Lrc"] = Text["Lrc"] || toLrcText();
        if(!isUndefined(Text["Lrc"])) {
            return new Uint8Array().fromText(Text["Lrc"]);
        }
    };
    var toHexBuffer = function (index, type) {
        switch(type) {
            case "vos000":
                Text["Hexvosvos000"] = Text["Hexvosvos000"] || toHexText(index, type);
                return new Uint8Array().fromText(Text["Hexvosvos000"]);
                break;
            case "vos001":
                Text["Hexvosvos001"] = Text["Hexvosvos001"] || toHexText(index, type);
                return new Uint8Array().fromText(Text["Hexvosvos001"]);
                break;
            case "vos006":
                Text["Hexvosvos006"] = Text["Hexvosvos006"] || toHexText(index, type);
                return new Uint8Array().fromText(Text["Hexvosvos006"]);
                break;
            case "vos022":
                Text["Hexvosvos022"] = Text["Hexvosvos022"] || toHexText(index, type);
                return new Uint8Array().fromText(Text["Hexvosvos022"]);
                break;
            case "imd":
                Text["Heximd"][index] = Text["Heximd"][index] || toHexText(index, "imd");
                return new Uint8Array().fromText(Text["Heximd"][index]);
                break;
        }
    };
    var toTjaBuffer = function (indexes, type) {
        var list = indexes.join(",");
        switch(type) {
            case "taiko":
            case "jube":
                Text["Tja" + type][list] = Text["Tja" + type][list] || toTjaText(indexes, type);
                return new Uint8Array().fromText(Text["Tja" + type][list], "ANSI");
                break;
        }
    };
    var toOsuBuffer = function (index, type) {
        switch(type) {
            case "osu":
            case "taiko":
            case "ctb":
            case "mania":
                Text["Osu" + type][index] = Text["Osu" + type][index] || toOsuText(index, type);
                return new Uint8Array().fromText(Text["Osu" + type][index]);
                break;
        }
    };
    var toXmlBuffer = function (index, type) {
        switch(type) {
            case "yddr":
            case "ydsd":
            case "mde":
                Text["Xml" + type][index] = Text["Xml" + type][index] || toXmlText(index, type);
                return new Uint8Array().fromText(Text["Xml" + type][index]);
                break;
        }
    };
    var toVoxBuffer = function (index) {
        Text["Vox"][index] = Text["Vox"][index] || toVoxText(index);
        return new Uint8Array().fromText(Text["Vox"][index]);
    };
    var toKshBuffer = function (index) {
        Text["Ksh"][index] = Text["Ksh"][index] || toKshText(index);
        return new Uint8Array().fromText(Text["Ksh"][index]);
    };
    var toImdBuffer = function (index) {
        Text["Imd"][index] = Text["Imd"][index] || toImdText(index);
        return new Uint8Array().fromHex(Text["Imd"][index]);
    };
    var toMdeBuffer = function (index) {
        Text["Mde"][index] = Text["Mde"][index] || toMdeText(index);
        return new Uint8Array().fromText(Text["Mde"][index]);
    };
    var toMcBuffer = function (index, type) {
        switch(type) {
            case "key":
            case "step":
            case "dj":
            case "catch":
            case "pad":
            case "taiko":
                Text["Mc" + type][index] = Text["Mc" + type][index] || toMcText(index, type);
                return new Uint8Array().fromText(Text["Mc" + type][index]);
                break;
        }
    };
    var toAffBuffer = function (index) {
        Text["Aff"][index] = Text["Aff"][index] || toAffText(index);
        return new Uint8Array().fromText(Text["Aff"][index]);
    };
    var toMstBuffer = function (indexes) {
        var list = indexes.join(",");
        Text["Mst"][list] = Text["Mst"][list] || toMstText(indexes);
        return new Uint8Array().fromText(Text["Mst"][list]);
    };
    var toBmsText = function (index) {
        var extension = (arguments.length > 1 ? arguments[1] : "bms");
        var Offset = (Data[index]["Start"] < 0 ? -Data[index]["Start"] : 0);
        var actionData = toActionDataNoIntersect(Data[index]["ActionData"]);
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
                    var c = r[i][0].close(Data[index]["BeatData"][n][0] + t / 48 * j);
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
                case "bms":
                case "bml":
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
                case "bme":
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
                case "pms":
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
            if(isUndefined(t)) {
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
        var BPN = Math.ceil((Data[index]["BeatData"].length - 1) / 4 / 1000) * 4;
        var tBPM = Data[index]["BPM"];
        var hBPMs = [];
        var hdBPMs = [];
        var md = [];
        var j = 0;
        var w = false;
        for(var i = 0; i < Data[index]["BeatData"].length - 1; i++) {
            var m = Math.floor(i / BPN);
            var b = ((m + 1) * BPN <= Data[index]["BeatData"].length - 1 ? BPN : Data[index]["BeatData"].length - 1 - m * BPN);
            var n = i % b;
            if(isUndefined(md[m])) {
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
                    md[m][3] = addScript(md[m][3], Data[index]["BeatData"][i + 1][0] - Data[index]["BeatData"][i][0], 0, b, n, Data[index]["BeatData"][i][1]).toBase(16);
                } else {
                    var l = hBPMs.indexOf(Data[index]["BeatData"][i][1]);
                    if(l == -1) {
                        hBPMs.push(Data[index]["BeatData"][i][1]);
                        l = hBPMs.length - 1;
                    }
                    md[m][8] = addScript(md[m][8], Data[index]["BeatData"][i + 1][0] - Data[index]["BeatData"][i][0], 0, b, n, (l + 1).toBase(36));
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
                    t = (new Number().fromBase(t, 36) + actionDataB[j][5]).toBase(36);
                }
                md[m][c] = addScript(md[m][c], Data[index]["BeatData"][i + 1][0] - Data[index]["BeatData"][i][0], actionDataB[j][0] - Data[index]["BeatData"][i][0], b, n, t);
                j += 1;
            }
        }
        md[md.length - 1][1] = addScript(md[md.length - 1][1], Math.round(60000 / Data[index]["BPM"]), 0, 4, 0, "01");
        for(var i = 0; i < md.length; i++) {
            if(isUndefined(md[i])) {
                md[i] = "";
            } else {
                var sd = [];
                for(var j = 0; j < md[i].length; j++) {
                    if(!isUndefined(md[i][j])) {
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
            hdBPMs.push("#BPM" + (i + 1).toBase(36).fill(2, "0", true) + " " + hBPMs[i].toString());
        }
        var writeBms = function (n, v) {
            if(!isUndefined(v) && (v != "")) {
                bms.push("#" + n + " " + v);
            }
        };
        var bms = [];
        bms.push("");
        bms.push("*---------------------- HEADER FIELD");
        bms.push("");
        writeBms("PLAYER", readMeta(["Player"], "1", index));
        writeBms("GENRE", readMeta(["Genre"], ""));
        writeBms("TITLE", readMeta(["Title"], info.nameCore));
        writeBms("SUBTITLE", readMeta(["SubTitle"], ""));
        writeBms("ARTIST", readMeta(["Artist"], ""));
        writeBms("CREATOR", readMeta(["Creator"], "rmstZ.html[" + info.nameCore + (info.type == info.nameCore ? "" : "_" + info.type) + "]"));
        writeBms("MAKER", "rmstZ.html[" + info.nameCore + (info.type == info.nameCore ? "" : "_" + info.type) + "]");
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
        bms.push("#WAVFF " + new FileInfo(readMeta(["Wav", "Wave", "AudioFilename"], info.nameCore)).name + ".mp3");
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
    var toBmlText = function (index) {
        return toBmsText(index, "bml");
    };
    var toPmsText = function (index) {
        return toBmsText(index, "pms");
    };
    var toVosText = function (type) {
        _buffer = readMeta(["BufferVos"]);
        if(isUndefined(_buffer)) {
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
            case "vos000":
            case "vos001":
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
            case "vos006":
            case "vos022":
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
        Buffer["Mid"] = Buffer["Mid"] || toMidBuffer();
        if(isUndefined(Buffer["Mid"])) {
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
        var lrc = new Uint8Array(0);
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
            case "vos000":
                Text["Vosvos000"] = Text["Vosvos000"] || toVosText(type);
                return Text["Vosvos000"];
                break;
            case "vos001":
                Text["Vosvos001"] = Text["Vosvos001"] || toVosText(type);
                return Text["Vosvos001"];
                break;
            case "vos006":
                Text["Vosvos006"] = Text["Vosvos006"] || toVosText(type);
                return Text["Vosvos006"];
                break;
            case "vos022":
                Text["Vosvos022"] = Text["Vosvos022"] || toVosText(type);
                return Text["Vosvos022"];
                break;
            case "imd":
                Text["Imd"][index] = Text["Imd"][index] || toImdText(index);
                return Text["Imd"][index];
                break;
        }
    };
    var toTjaText = function (indexes, type) {
        var writeTja = function (n, v) {
            var nov = (arguments.length > 2 ? arguments[2] : "");
            if(!isUndefined(v) && (v != nov)) {
                tja.push(n + ":" + v);
            }
        };
        var addData = function (t, t1, t2, c) {
            var d = 192 / 4 / 4;
            if(isUndefined(t)) {
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
        writeTja("TITLE", readMeta(["Title"], info.nameCore));
        writeTja("SUBTITLE", readMeta(["SubTitle"], ""));
        writeTja("CREATOR", readMeta(["Creator"], "rmstZ.html[" + info.nameCore + (info.type == info.nameCore ? "" : "_" + info.type) + "]"));
        writeTja("MAKER", "rmstZ.html[" + info.nameCore + (info.type == info.nameCore ? "" : "_" + info.type) + "]");
        writeTja("DATETIME", new Date().format());
        writeTja("WAVE", new FileInfo(readMeta(["Wave", "Wav", "AudioFilename"], info.nameCore)).name + ".mp3");
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
                case "taiko":
                    return 1;
                    break;
                case "jube":
                    return 4;
                    break;
            }
            return 1;
        })();
        for(var index = 0; index < Data.length; index++) {
            if(indexes.indexOf(index) != -1) {
                var beatData = (function () {
                    var r = copyBeatData(Data[index]["BeatData"]);
                    while((r.length - 1) % 4 != 0) {
                        r.push([Math.round(r[r.length - 1][0] + 60000 / r[r.length - 1][1]), r[r.length - 1][1]]);
                    }
                    return r;
                })();
                var actionData = (function () {
                    switch(type) {
                        case "taiko":
                            return toActionDataOneTrack(Data[index]["ActionData"], Data[index]["BPM"]);
                            break;
                        case "jube":
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
                        return (v.join("").replace(/0/g, "").replace(/1/g, "").length == 0);
                    })();
                    for(var i = 0; i < actionData.length; i++) {
                        var b = toBeatDataBeat(beatData, actionData[i][0]);
                        var d = (function () {
                            var v = "";
                            switch(type) {
                                case "taiko":
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
                                                case "0010":
                                                case "0100":
                                                    v = "1";
                                                    break;
                                                case "0001":
                                                case "1000":
                                                    v = "2";
                                                    break;
                                                case "0110":
                                                case "0111":
                                                case "1110":
                                                    v = "3";
                                                    break;
                                                case "1001":
                                                case "1011":
                                                case "1101":
                                                    v = "4";
                                                    break;
                                                case "0011":
                                                case "1100":
                                                case "0101":
                                                case "1010":
                                                    v = (sDong ? "1" : "2");
                                                    sDong = !sDong;
                                                    break;
                                                case "1111":
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
                                case "jube":
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
                                case "7":
                                case "9":
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
                        if(isUndefined(r[m])) {
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
                                    if((t.length > 0) && (t[t.length - 1].substring(0, 1) != "#")) {
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
        }
        return tja.join("\r\n");
    };
    var toOsuText = function (index, type) {
        var mode = (function () {
            var v = -1;
            switch(type) {
                case "osu":
                    v = 0;
                    break;
                case "taiko":
                    v = 1;
                    break;
                case "ctb":
                    v = 2;
                    break;
                case "mania":
                    v = 3;
                    break;
            }
            return v;
        })();
        var version = (function () {
            var v = "[" + type + "]";
            switch(type) {
                case "mania":
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
        osu.push("AudioFilename: " + new FileInfo(readMeta(["AudioFilename", "WAV", "WAVE"], info.nameCore)).name + ".mp3");
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
        osu.push("Title:" + readMeta(["Title", "TitleUnicode"], info.nameCore));
        osu.push("TitleUnicode:" + readMeta(["TitleUnicode", "Title"], info.nameCore));
        osu.push("Artist:" + readMeta(["Artist", "ArtistUnicode"], ""));
        osu.push("ArtistUnicode:" + readMeta(["ArtistUnicode", "Artist"], ""));
        osu.push("Creator:" + readMeta(["Creator"], "rmstZ.html[" + info.nameCore + (info.type == info.nameCore ? "" : "_" + info.type) + "]"));
        osu.push("Maker:" + "rmstZ.html[" + info.nameCore + (info.type == info.nameCore ? "" : "_" + info.type) + "]");
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
        osu.push("0,0," + '"' + readMeta(["Background", "StageFile"], info.nameCore + ".png") + '"' + ",0,0");
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
            var MsPB = (60000 / Data[index]["BeatData"][i][1]).toAbbreviated(12);
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
            case "osu":
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
            case "taiko":
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
                                    case "0010":
                                    case "0100":
                                        v = 0;
                                        break;
                                    case "0001":
                                    case "1000":
                                        v = 2;
                                        break;
                                    case "0110":
                                    case "0111":
                                    case "1110":
                                        v = 4;
                                        break;
                                    case "1001":
                                    case "1011":
                                    case "1101":
                                        v = 6;
                                        break;
                                    case "0011":
                                    case "1100":
                                    case "0101":
                                    case "1010":
                                        v = (sDong ? 1 : 2);
                                        sDong = !sDong;
                                        break;
                                    case "1111":
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
            case "ctb":
                var actionData = toActionDataShortSlide(toActionDataNoSametime(Data[index]["ActionData"]), Data[index]["BPM"]);
                var getX = function (t) {
                    var r = -1;
                    var m = "offset";
                    var u = 1;
                    var p = 1;
                    var b = toBeatDataBeat(Data[index]["BeatData"], actionData[i][0]);
                    switch(m) {
                        case "fix":
                            r = Math.floor((0.5 + t) / Data[index]["Key"] * 512);
                            break;
                        case "offset":
                            r = Math.floor((0.5 - p / 2 + (t + (b % 4 > 2 ? 1 - (b % 2) / 2 : (b % 2) / 2)) / Data[index]["Key"] * p) * 512);
                            break;
                        case "return":
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
            case "mania":
                var actionData = toActionDataNoIntersect(toActionDataNoSlide(Data[index]["ActionData"]));
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
            case "yddr":
                var times = [];
                var types = [];
                var notes = toData(Semi["data"][index], "NoSlide")["note"];
                var key = Semi["data"][index]["info"]["key"];
                var lr = 0;
                var xToTrack = function (x) {
                    x = Math.max(x, 0);
                    var v = Math.floor(x * key);
                    if(key % 2 == 0) {
                        if((x == 0.5) && (lr < 0)) {
                            v -= 1;
                        }
                        lr = (x < 0.5 ? -1 : (x > 0.5 ? 1 : lr));
                    }
                    return v;
                };
                for(var i = 0; i < notes.length; i++) {
                    if(notes[i]["link"]["b"] == -1) {
                        var l = times.indexOf(Math.round(notes[i]["when"]["t"]));
                        if(l == -1) {
                            times.push(Math.round(notes[i]["when"]["t"]));
                            types.push(xToTrack(notes[i]["where"]["x"]) + 1);
                            var a = notes[i]["link"]["a"];
                            if(a != -1) {
                                types[types.length - 1] += "_" + Math.round(notes[a]["when"]["t"] - notes[i]["when"]["t"]);
                            }
                        } else {
                            types[l] += "$" + (xToTrack(notes[i]["where"]["x"]) + 1);
                            var a = notes[i]["link"]["a"];
                            if(a != -1) {
                                types[l] += "_" + Math.round(notes[a]["when"]["t"] - notes[i]["when"]["t"]);
                            }
                        }
                    }
                }
                var r = [];
                for(var i = 0; i < times.length; i++) {
                    r.push("  <beat time=" + '"' + times[i] + '"' + " type=" + '"' + types[i] + '"' + "/>");
                }
                return "<beats>\r\n" + r.join("\r\n") + "\r\n</beats>";
                break;
            case "ydsd":
                Buffer["Xmlyddr"][index] = Buffer["Xmlyddr"][index] || toXmlBuffer(index, "yddr");
                var r = Buffer["Xmlyddr"][index].length % 8;
                return Buffer["Xmlyddr"][index].fill(Buffer["Xmlyddr"][index].length + r, r, false).cryptDES(true, new Uint8Array().fromText(new Uint8Array().fromText((3263047).toString(16)).getBase64())).getBase64();
                break;
            case "mde":
                var songName = info.nameCore;
                var delay = 0;
                var defaultCamSize = 320;
                var canvasSize = [3840, 2560];
                var gridFromBeat = function (b) {
                    return Math.round(b * 4) * 6;
                };
                var getCam = function (t) {
                    var cL = [];
                    var cR = [];
                    if(camList.length == 0) {
                        cL = [0, [canvasSize[0] / 2, canvasSize[1] / 2], defaultCamSize];
                        cR = [Semi["data"][index]["info"]["max"]["when"]["t"], [canvasSize[0] / 2, canvasSize[1] / 2], defaultCamSize];
                    } else if(t < camList[0]["mTick"]) {
                        cL = [0, [canvasSize[0] / 2, canvasSize[1] / 2], defaultCamSize];
                        cR = [camList[0]["mTick"], [camList[0]["mPos"][0], camList[0]["mPos"][1]], camList[0]["mSize"]];
                    } else if(t >= camList[camList.length - 1]["mTick"]) {
                        cL = [camList[camList.length - 1]["mTick"], [camList[camList.length - 1]["mPos"][0], camList[camList.length - 1]["mPos"][1]], camList[camList.length - 1]["mSize"]];
                        cR = [camList[camList.length - 1]["mTick"], [camList[camList.length - 1]["mPos"][0], camList[camList.length - 1]["mPos"][1]], camList[camList.length - 1]["mSize"]];
                    } else {
                        for(var i = 0; i < camList.length - 1; i++) {
                            if((camList[i]["mTick"] <= t) && (camList[i + 1]["mTick"] > t)) {
                                cL = [camList[i]["mTick"], [camList[i]["mPos"][0], camList[i]["mPos"][1]], camList[i]["mSize"]];
                                cR = [camList[i + 1]["mTick"], [camList[i + 1]["mPos"][0], camList[i + 1]["mPos"][1]], camList[i + 1]["mSize"]];
                                break;
                            }
                        }
                    }
                    var r = (cR[0] == cL[0] ? 0 : (t - cL[0]) / (cR[0] - cL[0]));
                    var x = cL[1][0] + (cR[1][0] - cL[1][0]) * r;
                    var y = cL[1][1] + (cR[1][1] - cL[1][1]) * r;
                    var s = cL[2] + (cR[2] - cL[2]) * r;
                    return [t, [x, y], s];
                }
                var getPos = function (t, p) {
                    var cam = getCam(t);
                    var x = cam[1][0];
                    var y = cam[1][1];
                    var s = cam[2];
                    var limit = false;
                    if(limit) {
                        if((p[0] < 0) || (p[0] > 1) || (p[1] < 0) || (p[1] > 1)) {
                            var rr = 0;
                            while(((p[0] < 0) && (-p[0] / rr >= 1)) || ((p[1] < 0) && (-p[1] / rr >= 1)) || ((p[0] >= 1) && (p[0] / rr >= 1)) || ((p[1] >= 1) && (p[1] / rr >= 1))) {
                                rr += 1;
                            }
                            p[0] = (p[0] / rr + 1 ) / 2;
                            p[1] = (p[1] / rr + 1 ) / 2;
                        }
                    }
                    var scaleY = 2 / 3;
                    var offsetY = -2 / 3 / 10;
                    p[1] = 0.5 - (0.5 - p[1]) * scaleY + offsetY;
                    var px = (x - s * 1.5) + p[0] * (s * 1.5 * 2);
                    var py = (y - s) + p[1] * (s * 2);
                    return [px.toAbbreviated(0), py.toAbbreviated(0)];
                };
                var getRota = function (x, y) {
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
                        r = (Math.atan2(-x, y) / Math.PI * 180).toAbbreviated(6);
                    }
                    return r;
                };
                var note = toData(Semi["data"][index], "EquilongDrag")["note"];
                var noteData = (function () {
                    var v = [];
                    for(var i = 0; i < note.length; i++) {
                        note[i]["where"]["x"] = (note[i]["where"]["x"] == -1 ? 0.5 : note[i]["where"]["x"]);
                        note[i]["where"]["y"] = (note[i]["where"]["y"] == -1 ? 0.5 : note[i]["where"]["y"]);
                        note[i]["where"]["z"] = (note[i]["where"]["x"] == -1 ? 0.5 : note[i]["where"]["z"]);
                    }
                    for(var i = 0; i < note.length; i++) {
                        if(note[i]["link"]["b"] == -1) {
                            var d = {};
                            d["when"] = {"b": note[i]["when"]["b"], "n": note[i]["when"]["n"], "t": note[i]["when"]["t"]};
                            d["where"] = {"x": note[i]["where"]["x"], "y": note[i]["where"]["y"], "z": note[i]["where"]["z"]};
                            if(note[i]["link"]["a"] == -1) {
                                d["type"] = "CNoteSingle";
                            } else {
                                var a = note[i]["link"]["a"];
                                if(note[a]["link"]["a"] == -1) {
                                    if(note[a]["when"]["b"] == note[i]["when"]["b"]) {
                                        d["type"] = "CNoteSlide";
                                        d["where"] = {"x": (note[i]["where"]["x"] + note[a]["where"]["x"]) / 2, "y": (note[i]["where"]["y"] + note[a]["where"]["y"]) / 2, "z": (note[i]["where"]["z"] + note[a]["where"]["z"]) / 2};
                                        d["direction"] = (function (x, y) {
                                            var d = ["Up", "UpLeft", "Left", "DownLeft", "Down", "DownRight", "Right", "UpRight"];
                                            var ml = [2, 1, 1, 1, 0, 0, 1, 2, 4, 4, 3, 2, 2, 3, 3, 3];
                                            var mr = [7, 7, 7, 6, 6, 7, 0, 0, 6, 5, 4, 4, 5, 5, 5, 6];
                                            var q = Math.floor(x * 4) + Math.floor(y * 4) * 4;
                                            if(note[a]["where"]["x"] - note[i]["where"]["x"] < 0) {
                                                return d[ml[q]];
                                            } else {
                                                return d[mr[q]];
                                            }
                                        })(d["where"]["x"], d["where"]["y"]);
                                    } else {
                                        if((note[i]["mIsHeadFollowType"]) || (note[a]["where"]["x"] == note[i]["where"]["x"])) {
                                            d["type"] = "CNoteLong";
                                        } else {
                                            d["type"] = "CNoteLongSlide";
                                        }
                                        d["when2"] = {"b": note[a]["when"]["b"], "n": note[a]["when"]["n"], "t": note[a]["when"]["t"]};
                                        d["where2"] = {"x": note[a]["where"]["x"], "y": note[a]["where"]["y"], "z": note[a]["where"]["z"]};
                                    }
                                } else {
                                    d["type"] = "CNoteLongSlide";
                                    var e = note[a]["link"]["a"];
                                    while(note[e]["link"]["a"] != -1) {
                                        e = note[e]["link"]["a"];
                                    }
                                    d["when2"] = {"b": note[e]["when"]["b"], "n": note[e]["when"]["n"], "t": note[e]["when"]["t"]};
                                    d["where2"] = {"x": note[e]["where"]["x"], "y": note[e]["where"]["y"], "z": note[e]["where"]["z"]};
                                }
                            }
                            switch(d["type"]) {
                                case "CNoteLong":
                                case "CNoteLongSlide":
                                    if(note[i]["line"]["vector"].length) {
                                        var vector = [];
                                        for(var j = 0; j < note[i]["line"]["vector"].length; j++) {
                                            vector.push([note[i]["line"]["vector"][j]["when"]["t"], [note[i]["line"]["vector"][j]["where"]["x"], note[i]["line"]["vector"][j]["where"]["y"]]]);
                                        }
                                        d["line"] = {"vector": vector, "clockwise": note[i]["line"]["clockwise"], "combo": note[i]["combo"], "follow": note[i]["line"]["follow"]};
                                        break;
                                    }
                            }
                            v.push(d);
                        }
                    }
                    return v;
                })();
                var camData = (function () {
                    var cam = [];
                    if(Semi["data"][index]["transform"].length) {
                        var p = [canvasSize[0] / 2, canvasSize[1] / 2];
                        var s = defaultCamSize;
                        for(var i = 0; i < Semi["data"][index]["transform"].length; i++) {
                            var m = new Matrix().setMatrix3d(Semi["data"][index]["transform"][i]["matrix"]);
                            var translate = m.getTranslate();
                            var scale = m.getScale();
                            p[0] = (p[0] - translate[0]).toAbbreviated(3);
                            p[1] = (p[1] - translate[1]).toAbbreviated(3);
                            s = (s / scale[0]).toAbbreviated(3);
                            cam.push([Semi["data"][index]["transform"][i]["when"]["t"], [p[0], p[1]], s]);
                        }
                    } else if(Checks["TransformExtend"]) {
                        var trans = [];
                        var isMoveCam = true;
                        var isTabCam = true;
                        var isSlideCam = true;
                        var isInoutCam = true;
                        var noteMove = 1;
                        var moveRate = 1 / 12;
                        var actionBeat = 1 / 8;
                        var tabRate = 1 / 32;
                        var slideRate = 1 / 16;
                        var indexOf = function (t) {
                            for(var i = 0; i < trans.length - 1; i++) {
                                if((trans[i][0] <= t) && (trans[i + 1][0] > t)) {
                                    return i;
                                }
                            }
                            return -1;
                        };
                        var breakTrans = function (i, t) {
                            var r = (t - trans[i][0]) / (trans[i + 1][0] - trans[i][0]);
                            var d = [t, [trans[i + 1][1][0] * r, trans[i + 1][1][1] * r], 1 + (trans[i + 1][2] - 1) * r];
                            trans[i + 1] = [trans[i + 1][0], [trans[i + 1][1][0] - d[1][0], trans[i + 1][1][1] - d[1][1]], trans[i + 1][2] / d[2]];
                            trans.insert(d, i + 1);
                        };
                        trans.push([0, [0, 0], 1]);
                        if(isMoveCam) {
                            trans.push([noteData[0]["when"]["t"], [0, 0], 1]);
                            var s = noteData[0]["when"]["n"];
                            var e = noteData[noteData.length - 1]["when"]["n"];
                            var i = 0;
                            var n = s;
                            while(n < e) {
                                var x = 0;
                                var y = 0;
                                var nn = (n + noteMove < e ? n + noteMove : e);
                                while((i < noteData.length) && (noteData[i]["when"]["n"] >= n) && (noteData[i]["when"]["n"] < nn)) {
                                    x += noteData[i]["where"]["x"] - 0.5;
                                    y += noteData[i]["where"]["y"] - 0.5;
                                    i += 1;
                                }
                                trans.push([timeFromNote(Semi["data"][index], n + 1), [x * moveRate, y * moveRate], 1]);
                                n = nn;
                            }
                        }
                        trans.push([Semi["data"][index]["info"]["length"]["when"]["t"], [0, 0], 1]);
                        if(isTabCam) {
                            var tab = (function () {
                                var v = [];
                                var s = 1;
                                var add = function () {
                                    s *= (1 - tabRate);
                                };
                                for(var i = 0; i < noteData.length; i++) {
                                    var b = noteData[i]["when"]["b"];
                                    s = 1;
                                    add();
                                    while((i + 1 < noteData.length) && (b == noteData[i + 1]["when"]["b"])) {
                                        add();
                                        i += 1;
                                    }
                                    v.push([
                                               [timeFromBeat(Semi["data"][index], Math.max(b - actionBeat, 0)), timeFromBeat(Semi["data"][index], b), timeFromBeat(Semi["data"][index], b + actionBeat)],
                                               s
                                           ]);
                                }
                                for(var i = 0; i < v.length; i++) {
                                    if((i + 1 < v.length) && (v[i][0][2] > v[i + 1][0][0])) {
                                        var d = (v[i + 1][0][1] - v[i][0][1]) / 2;
                                        v[i][0][2] = v[i][0][1] + d;
                                        v[i + 1][0][0] = v[i + 1][0][1] - d;
                                    }
                                }
                                return v;
                            })();
                            for(var i = 0; i < tab.length; i++) {
                                var s = indexOf(tab[i][0][0]);
                                breakTrans(s, tab[i][0][0]);
                                var m = indexOf(tab[i][0][1]);
                                breakTrans(m, tab[i][0][1]);
                                var e = indexOf(tab[i][0][2]);
                                breakTrans(e, tab[i][0][2]);
                                var tabIn = [trans[m + 1][0], [0, 0], 1];
                                for(var j = s + 2; j < m + 2; j++) {
                                    tabIn[1][0] += trans[j][1][0];
                                    tabIn[1][1] += trans[j][1][1];
                                    tabIn[2] *= trans[j][2];
                                }
                                tabIn[2] *= tab[i][1];
                                var tabOut = [trans[e + 1][0], [0, 0], 1];
                                for(var j = m + 2; j < e + 2; j++) {
                                    tabOut[1][0] += trans[j][1][0];
                                    tabOut[1][1] += trans[j][1][1];
                                    tabOut[2] *= trans[j][2];
                                }
                                tabOut[2] /= tab[i][1];
                                trans.splice(s + 2, e - s);
                                trans.inserts([tabIn, tabOut], s + 2);
                            }
                        }
                        if(isSlideCam) {
                            var slide = (function () {
                                var v = [];
                                var x = 0;
                                var y = 0;
                                var add = function (direction) {
                                    switch(direction) {
                                        case "Up":
                                            y += slideRate;
                                            break;
                                        case "UpLeft":
                                            x -= slideRate * Math.SQRT1_2;
                                            y += slideRate * Math.SQRT1_2;
                                            break;
                                        case "Left":
                                            x -= slideRate;
                                            break;
                                        case "DownLeft":
                                            x -= slideRate * Math.SQRT1_2;
                                            y -= slideRate * Math.SQRT1_2;
                                            break;
                                        case "Down":
                                            y -= slideRate;
                                            break;
                                        case "DownRight":
                                            x += slideRate * Math.SQRT1_2;
                                            y -= slideRate * Math.SQRT1_2;
                                            break;
                                        case "Right":
                                            x += slideRate;
                                            break;
                                        case "UpRight":
                                            x += slideRate * Math.SQRT1_2;
                                            y += slideRate * Math.SQRT1_2;
                                            break;
                                    }
                                };
                                for(var i = 0; i < noteData.length; i++) {
                                    if(noteData[i]["type"] == "CNoteSlide") {
                                        var b = noteData[i]["when"]["b"];
                                        x = 0;
                                        y = 0;
                                        add(noteData[i]["direction"]);
                                        while((i + 1 < noteData.length) && (b == noteData[i + 1]["when"]["b"])) {
                                            add(noteData[i + 1]["direction"]);
                                            i += 1;
                                        }
                                        v.push([
                                                   [timeFromBeat(Semi["data"][index], b), timeFromBeat(Semi["data"][index], b + actionBeat)],
                                                   [x, y]
                                               ]);
                                    }
                                }
                                for(var i = 0; i < v.length; i++) {
                                    if((i + 1 < v.length) && (v[i][0][1] > v[i + 1][0][0])) {
                                        var d = (v[i + 1][0][0] - v[i][0][1]) / 2;
                                        v[i][0][1] = v[i][0][0] + d;
                                        v[i + 1][0][0] = v[i + 1][0][1] - d;
                                    }
                                }
                                return v;
                            })();
                            for(var i = 0; i < slide.length; i++) {
                                var s = indexOf(slide[i][0][0]);
                                breakTrans(s, slide[i][0][0]);
                                var e = indexOf(slide[i][0][1]);
                                breakTrans(e, slide[i][0][1]);
                                var slideTo = [trans[e + 1][0], [0, 0], 1];
                                for(var j = s + 2; j < e + 2; j++) {
                                    slideTo[1][0] += trans[j][1][0];
                                    slideTo[1][1] += trans[j][1][1];
                                    slideTo[2] *= trans[j][2];
                                }
                                slideTo[1][0] += slide[i][1][0];
                                slideTo[1][1] += slide[i][1][1];
                                trans.splice(s + 2, e - s);
                                trans.inserts([slideTo], s + 2);
                            }
                        }
                        var fix = (function () {
                            var clear = (function () {
                                for(var i = 0; i < trans.length; i++) {
                                    trans[i][0] = trans[i][0].toAbbreviated(0);
                                }
                                for(var i = 1; i < trans.length; i++) {
                                    if(trans[i][0] == trans[i - 1][0]) {
                                        trans[i] = [trans[i][0], [trans[i - 1][1][0] + trans[i][1][0], trans[i - 1][1][1] + trans[i][1][1]], trans[i - 1][2] * trans[i][2]];
                                        trans.splice(i - 1, 1);
                                        i -= 1;
                                    }
                                }
                            })();
                            var merge = (function () {
                                var x = 0;
                                var y = 0;
                                var s = 1;
                                for(var i = 0; i < trans.length; i++) {
                                    x += trans[i][1][0];
                                    y += trans[i][1][1];
                                    s *= trans[i][2];
                                    cam.push([trans[i][0], [x, y], s]);
                                }
                            })();
                            var xMax = 0;
                            var yMax = 0;
                            var sMax = 1;
                            var xMin = 0;
                            var yMin = 0;
                            var sMin = 1;
                            var xyMax = 0;
                            for(var i = 0; i < cam.length; i++) {
                                xMax = Math.max(xMax, cam[i][1][0]);
                                xMin = Math.min(xMin, cam[i][1][0]);
                                yMax = Math.max(yMax, cam[i][1][1]);
                                yMin = Math.min(yMin, cam[i][1][1]);
                                sMax = Math.max(sMax, cam[i][2]);
                                sMin = Math.min(sMin, cam[i][2]);
                            }
                            var xyR = Math.max(xMax - xMin, yMax - yMin);
                            for(var i = 0; i < cam.length; i++) {
                                cam[i][1][0] = (cam[i][1][0] - xMin) / (xyR + sMax) + (sMax / 2 / (xyR + sMax));
                                cam[i][1][1] = (cam[i][1][1] - yMin) / (xyR + sMax) + (sMax / 2 / (xyR + sMax));
                            }
                            canvasSize[1] = Math.ceil(xyR + sMax) * defaultCamSize * 2;
                            canvasSize[0] = canvasSize[1] * 1.5;
                        })();
                        if(isInoutCam) {
                            cam[0] = [0, [0.5, 0.5], canvasSize[1] / 2 / defaultCamSize];
                            cam[cam.length - 1] = [Semi["data"][index]["info"]["length"]["when"]["t"], [0.5, 0.5], canvasSize[1] / 2 / defaultCamSize];
                        }
                        var compute = (function () {
                            for(var i = 0; i < cam.length; i++) {
                                cam[i][1][0] *= canvasSize[0];
                                cam[i][1][1] *= canvasSize[1];
                                cam[i][2] *= defaultCamSize;
                            }
                        })();
                    }
                    return cam;
                })();
                var camList = (function () {
                    var v = [];
                    for(var i = 0; i < camData.length; i++) {
                        v[i] = {};
                        v[i]["mIdx"] = i;
                        v[i]["mTick"] = camData[i][0].toAbbreviated(0) + delay;
                        v[i]["mGrid"] = gridFromBeat(beatFromTime(Semi["data"][index], camData[i][0]));
                        v[i]["mPos"] = [camData[i][1][0].toAbbreviated(0), camData[i][1][1].toAbbreviated(0)];
                        v[i]["mSize"] = camData[i][2].toAbbreviated(6);
                        v[i]["Pos"] = [camData[i][1][0].toAbbreviated(0), camData[i][1][1].toAbbreviated(0)];
                        v[i]["Size"] = camData[i][2].toAbbreviated(6);
                    }
                    return v;
                })();
                var notes = (function () {
                    var v = [];
                    for(var i = 0; i < noteData.length; i++) {
                        var x = noteData[i]["where"]["x"];
                        var y = noteData[i]["where"]["y"];
                        v[i] = {};
                        v[i]["mIdx"] = i;
                        v[i]["mTick"] = noteData[i]["when"]["t"] + delay;
                        v[i]["mGrid"] = gridFromBeat(noteData[i]["when"]["b"]);
                        v[i]["mShowNoteTick"] = timeFromBeat(Semi["data"][index], noteData[i]["when"]["b"] - 2).toAbbreviated(0) + delay;
                        v[i]["mShowNoteGridLength"] = v[i]["mGrid"] - gridFromBeat(noteData[i]["when"]["b"] - 2);
                        v[i]["mPos"] = getPos(v[i]["mTick"], [x, y]);
                        v[i]["mStartDistance"] = (getCam(v[i]["mTick"])[2] * 1.25).toAbbreviated(0);
                        v[i]["mStartDirection"] = (function () {
                            var d = ["Up", "UpLeft", "Left", "DownLeft", "Down", "DownRight", "Right", "UpRight"];
                            var m = [7, 0, 1, 6, 0, 2, 5, 4, 3];
                            var q = Math.floor(x * 3) + Math.floor(y * 3) * 3;
                            return d[m[q]];
                        })();
                        v[i]["mStartPos"] = (function () {
                            switch(v[i]["mStartDirection"]) {
                                case "UpRight":
                                    return [v[i]["mPos"][0] + v[i]["mStartDistance"], v[i]["mPos"][1] + v[i]["mStartDistance"]];
                                    break;
                                case "Right":
                                    return [v[i]["mPos"][0] + v[i]["mStartDistance"], v[i]["mPos"][1]];
                                    break;
                                case "DownRight":
                                    return [v[i]["mPos"][0] + v[i]["mStartDistance"], v[i]["mPos"][1] - v[i]["mStartDistance"]];
                                    break;
                                case "Down":
                                    return [v[i]["mPos"][0], v[i]["mPos"][1] - v[i]["mStartDistance"]];
                                    break;
                                case "DownLeft":
                                    return [v[i]["mPos"][0] - v[i]["mStartDistance"], v[i]["mPos"][1] - v[i]["mStartDistance"]];
                                    break;
                                case "Left":
                                    return [v[i]["mPos"][0] - v[i]["mStartDistance"], v[i]["mPos"][1]];
                                    break;
                                case "UpLeft":
                                    return [v[i]["mPos"][0] - v[i]["mStartDistance"], v[i]["mPos"][1] + v[i]["mStartDistance"]];
                                    break;
                                case "Up":
                                    return [v[i]["mPos"][0], v[i]["mPos"][1] + v[i]["mStartDistance"]];
                                    break;
                            }
                        })();
                        v[i]["mDrawArrow"] = "false";
                        v[i]["mArrowRota"] = 0;
                        v[i]["mDrawLink"] = "false";
                        v[i]["mLinkPos"] = [0, 0];
                        v[i]["mLinkNoteIdx"] = -1;
                        v[i]["mNoteInCurve"] = "true";
                        v[i]["mSamePosPreNoteIdx"] = -1;
                        v[i]["type"] = noteData[i]["type"];
                        switch(v[i]["type"]) {
                            case "CNoteSingle":
                                break;
                            case "CNoteSlide":
                                v[i]["mSlideDirection"] = noteData[i]["direction"];
                                break;
                            case "CNoteLong":
                            case "CNoteLongSlide":
                                v[i]["mEndTick"] = noteData[i]["when2"]["t"] + delay;
                                v[i]["mEndGrid"] = gridFromBeat(noteData[i]["when2"]["b"]);
                                v[i]["mTailLength"] = ((v[i]["mTick"] - v[i]["mShowNoteTick"]) * 0.75).toAbbreviated(0);
                                if(noteData[i]["line"]) {
                                    v[i]["mPoints"] = (function () {
                                        var p = [];
                                        for(var j = 0; j < noteData[i]["line"]["vector"].length; j++) {
                                            p.push(getPos(noteData[i]["line"]["vector"][j][0], noteData[i]["line"]["vector"][j][1]));
                                        }
                                        return p;
                                    })();
                                    v[i]["mIsCircleClockwise"] = noteData[i]["line"]["clockwise"];
                                    v[i]["mIsCircleTail"] = (v[i]["mIsCircleClockwise"] ? "true" : "false");
                                    v[i]["mIsHeadFollowType"] = noteData[i]["line"]["follow"];
                                    v[i]["mJudgeCnt"] = noteData[i]["line"]["combo"];
                                    if(v[i]["type"] == "CNoteLong") {
                                        v[i]["mIsHeadFollowType"] = (function () {
                                            for(var j = 1; j < noteData[i]["line"]["vector"].length; j++) {
                                                if((noteData[i]["line"]["vector"][j][1][0] != noteData[i]["line"]["vector"][j - 1][1][0]) || (noteData[i]["line"]["vector"][j][1][1] != noteData[i]["line"]["vector"][j - 1][1][1])) {
                                                    return "false";
                                                }
                                            }
                                            return "true";
                                        })();
                                    }
                                } else {
                                    v[i]["mPoints"] = (function () {
                                        var p = [];
                                        var isSimple = false;
                                        if(isSimple) {
                                            p.push(getPos(v[i]["mTick"], [x, y]));
                                            p.push(getPos(v[i]["mEndTick"], [noteData[i]["where2"]["x"], noteData[i]["where2"]["y"]]));
                                        } else {
                                            var b = [];
                                            var s = Math.ceil(noteData[i]["when"]["b"]);
                                            var e = Math.floor(noteData[i]["when2"]["b"]);
                                            if(s - noteData[i]["when"]["b"] > 0.01) {
                                                b.push(noteData[i]["when"]["b"]);
                                            }
                                            for(var j = s; j < e + 1; j++) {
                                                b.push(j);
                                            }
                                            if(noteData[i]["when2"]["b"] - e > 0.01) {
                                                b.push(noteData[i]["when2"]["b"]);
                                            }
                                            for(var j = 0; j < b.length; j++) {
                                                p.push(getPos(timeFromBeat(Semi["data"][index], b[j]), [x + (b[j] - b[0]) / (b[b.length - 1] - b[0]) * (noteData[i]["where2"]["x"] - x), axisFromNote(Semi["data"][index], noteFromBeat(Semi["data"][index], b[j]))]));
                                            }
                                        }
                                        return p;
                                    })();
                                    v[i]["mIsCircleTail"] = (function () {
                                        if(v[i]["mPoints"] && (v[i]["mPoints"].length == 2)) {
                                            var m = [(v[i]["mPoints"][0][0] + v[i]["mPoints"][1][0]) / 2, (v[i]["mPoints"][0][1] + v[i]["mPoints"][1][1]) / 2];
                                            var r = Math.sqrt(Math.pow(v[i]["mPoints"][1][0] - v[i]["mPoints"][0][0], 2) + Math.pow(v[i]["mPoints"][1][1] - v[i]["mPoints"][0][1], 2)) / 2;
                                            var c = getCam(v[i]["mEndTick"] - v[i]["mTick"]);
                                            if((m[0] + r <= c[1][0] + c[2]) && (m[1] + r <= c[1][1] + c[2]) && (m[0] - r <= c[1][0] - c[2]) && (m[1] - r <= c[1][1] - c[2])) {
                                                return "true";
                                            }
                                        }
                                        return "false";
                                    })();
                                }
                                v[i]["mIsCircleClockwise"] = v[i]["mIsCircleClockwise"] || (!(noteData[i]["when"]["b"] % 1 < 0.5) ^ (x < 0.5) ? "true" : "false");
                                v[i]["mIsHeadFollowType"] = v[i]["mIsHeadFollowType"] || "false";
                                v[i]["mJudgeCnt"] = v[i]["mJudgeCnt"] || Math.max(Math.floor((noteData[i]["when2"]["b"] - noteData[i]["when"]["b"]) * 4) + 1, 2);
                                if(v[i]["mPoints"]) {
                                    for(var j = 1; j < v[i]["mPoints"].length; j++) {
                                        if((Math.abs(v[i]["mPoints"][j][0] - v[i]["mPoints"][j - 1][0]) < 15) && (Math.abs(v[i]["mPoints"][j][1] - v[i]["mPoints"][j - 1][1]) < 15)) {
                                            v[i]["mPoints"][j][0] = v[i]["mPoints"][j - 1][0] + (v[i]["mPoints"][j][0] > v[i]["mPoints"][j - 1][0] ? 15 : -15);
                                            v[i]["mPoints"][j][1] = v[i]["mPoints"][j - 1][1] + (v[i]["mPoints"][j][1] > v[i]["mPoints"][j - 1][1] ? 15 : -15);
                                        }
                                    }
                                }
                                break;
                        }
                    }
                    for(var i = 0; i < v.length; i++) {
                        for(var j = i + 1; j < v.length; j++) {
                            if(v[i]["mTick"] != v[j]["mTick"]) {
                                break;
                            }
                            if((v[i]["type"] == v[j]["type"]) && !((v[i]["type"] == "CNoteLong") && ((v[i]["mIsHeadFollowType"] == "true") || (v[j]["mIsHeadFollowType"] == "true")))) {
                                v[i]["mDrawLink"] = "true";
                                v[i]["mLinkPos"] = v[j]["mPos"];
                                v[i]["mLinkNoteIdx"] = v[j]["mIdx"];
                                v[j]["mDrawLink"] = "false";
                                v[j]["mLinkPos"] = v[i]["mPos"];
                                v[j]["mLinkNoteIdx"] = v[i]["mIdx"];
                                i += 1;
                                break;
                            }
                        }
                    }
                    for(var i = 0; i < v.length; i++) {
                        var j = i + 1;
                        if(v[i]["mDrawLink"] == "true") {
                            j += 1;
                        }
                        if(j > v.length - 1) {
                            break;
                        }
                        v[i]["mDrawArrow"] = "true";
                        v[i]["mArrowRota"] = getRota(v[j]["mPos"][0] - v[i]["mPos"][0], v[j]["mPos"][1] - v[i]["mPos"][1]);
                    }
                    for(var i = 1; i < v.length; i++) {
                        for(var j = i - 1; j > -1; j--) {
                            if((v[j]["mPos"][0] == v[i]["mPos"][0]) && (v[j]["mPos"][1] == v[i]["mPos"][1])) {
                                v[i]["mSamePosPreNoteIdx"] = v[j]["mIdx"];
                                break;
                            }
                        }
                    }
                    return v;
                })();
                var bpmList = (function () {
                    var v = [];
                    for(var i = 0; i < Semi["data"][index]["beat"].length; i++) {
                        v[i] = {};
                        v[i]["mIdx"] = i;
                        v[i]["mTick"] = Semi["data"][index]["beat"][i]["when"]["t"].toAbbreviated(0) + delay;
                        v[i]["mGrid"] = gridFromBeat(Semi["data"][index]["beat"][i]["when"]["b"]);
                        v[i]["mBPM"] = Semi["data"][index]["beat"][i]["bpm"];
                        v[i]["BPM"] = Semi["data"][index]["beat"][i]["bpm"];
                    }
                    return v;
                })();
                var judgeCnt = (function () {
                    var r = 0;
                    for(var i = 0; i < notes.length; i++) {
                        switch(notes[i]["type"]) {
                            case "CNoteSingle":
                            case "CNoteSlide":
                                r += 1;
                                break;
                            case "CNoteLong":
                            case "CNoteLongSlide":
                                r += notes[i]["mJudgeCnt"];
                                break;
                        }
                    }
                    return r;
                })();
                var xml = (function () {
                    var r = [];
                    var writeString = function (l, c) {
                        if(isUndefined(c)) {
                            return;
                        }
                        r.push(new String().duplicate(2 * l, "&nbsp;") + c);
                    };
                    var writeData = function (l, f, c) {
                        if(isUndefined(c)) {
                            return;
                        }
                        r.push(new String().duplicate(2 * l, "&nbsp;") + "&lt;" + f + "&gt;" + c + "&lt;/" + f + "&gt;");
                    };
                    var writePos = function (l, f, p) {
                        if(isUndefined(p)) {
                            return;
                        }
                        writeString(l, "&lt;" + f + "&gt;");
                        writeData(l + 1, "x", p[0]);
                        writeData(l + 1, "y", p[1]);
                        writeString(l, "&lt;/" + f + "&gt;");
                    };
                    var writePoints = function (l, f, p) {
                        if(isUndefined(p)) {
                            return;
                        }
                        writeString(l, "&lt;" + f + "&gt;");
                        for(var i = 0; i < p.length; i++) {
                            writePos(l + 1, "Vector2", p[i]);
                        }
                        writeString(l, "&lt;/" + f + "&gt;");
                    };
                    var writeBPMList = function (l) {
                        if(bpmList.length > 1) {
                            writeString(l, "&lt;mBPMList&gt;");
                            for(var i = 0; i < bpmList.length; i++) {
                                writeString(l + 1, "&lt;CNoteBPMData&gt;");
                                writeData(l + 2, "mIdx", bpmList[i]["mIdx"]);
                                writeData(l + 2, "mGrid", bpmList[i]["mGrid"]);
                                writeData(l + 2, "mTick", bpmList[i]["mTick"]);
                                writeData(l + 2, "mBPM", bpmList[i]["mBPM"]);
                                writeData(l + 2, "BPM", bpmList[i]["BPM"]);
                                writeString(l + 1, "&lt;/CNoteBPMData&gt;");
                            }
                            writeString(l, "&lt;/mBPMList&gt;");
                        } else {
                            writeString(l, "&lt;mBPMList /&gt;");
                        }
                    };
                    var writeNotes = function (l) {
                        if(notes.length) {
                            writeString(l, "&lt;mNotes&gt;");
                            for(var i = 0; i < notes.length; i++) {
                                writeString(l + 1, "&lt;CNoteBase xsi:type=&quot;" + notes[i]["type"] + "&quot;&gt;");
                                writeData(l + 2, "mIdx", notes[i]["mIdx"]);
                                writeData(l + 2, "mGrid", notes[i]["mGrid"]);
                                writeData(l + 2, "mTick", notes[i]["mTick"]);
                                writeData(l + 2, "mShowNoteGridLength", notes[i]["mShowNoteGridLength"]);
                                writeData(l + 2, "mShowNoteTick", notes[i]["mShowNoteTick"]);
                                writePos(l + 2, "mPos", notes[i]["mPos"]);
                                writePos(l + 2, "mStartPos", notes[i]["mStartPos"]);
                                writeData(l + 2, "mStartDistance", notes[i]["mStartDistance"]);
                                writeData(l + 2, "mStartDirection", notes[i]["mStartDirection"]);
                                writeData(l + 2, "mDrawArrow", notes[i]["mDrawArrow"]);
                                writeData(l + 2, "mArrowRota", notes[i]["mArrowRota"]);
                                writeData(l + 2, "mDrawLink", notes[i]["mDrawLink"]);
                                writePos(l + 2, "mLinkPos", notes[i]["mLinkPos"]);
                                writeData(l + 2, "mLinkNoteIdx", notes[i]["mLinkNoteIdx"]);
                                writeData(l + 2, "mNoteInCurve", notes[i]["mNoteInCurve"]);
                                writeData(l + 2, "mSamePosPreNoteIdx", notes[i]["mSamePosPreNoteIdx"]);
                                writeData(l + 2, "mSlideDirection", notes[i]["mSlideDirection"]);
                                writeData(l + 2, "mIsCircleTail", notes[i]["mIsCircleTail"]);
                                writeData(l + 2, "mIsCircleClockwise", notes[i]["mIsCircleClockwise"]);
                                writePoints(l + 2, "mPoints", notes[i]["mPoints"]);
                                writeData(l + 2, "mTailLength", notes[i]["mTailLength"]);
                                writeData(l + 2, "mEndGrid", notes[i]["mEndGrid"]);
                                writeData(l + 2, "mEndTick", notes[i]["mEndTick"]);
                                writeData(l + 2, "mIsHeadFollowType", notes[i]["mIsHeadFollowType"]);
                                writeData(l + 2, "mJudgeCnt", notes[i]["mJudgeCnt"]);
                                writeString(l + 1, "&lt;/CNoteBase&gt;");
                            }
                            writeString(l, "&lt;/mNotes&gt;");
                        } else {
                            writeString(l, "&lt;mNotes /&gt;");
                        }
                    };
                    var writeCamList = function (l) {
                        if(camList.length) {
                            writeString(l, "&lt;mCamList&gt;");
                            for(var i = 0; i < camList.length; i++) {
                                writeString(l + 1, "&lt;CNoteCamData&gt;");
                                writeData(l + 2, "mIdx", camList[i]["mIdx"]);
                                writeData(l + 2, "mGrid", camList[i]["mGrid"]);
                                writeData(l + 2, "mTick", camList[i]["mTick"]);
                                writePos(l + 2, "mPos", [camList[i]["mPos"][0], camList[i]["mPos"][1]]);
                                writeData(l + 2, "mSize", camList[i]["mSize"]);
                                writePos(l + 2, "Pos", [camList[i]["Pos"][0], camList[i]["Pos"][1]]);
                                writeData(l + 2, "Size", camList[i]["Size"]);
                                writeString(l + 1, "&lt;/CNoteCamData&gt;");
                            }
                            writeString(l, "&lt;/mCamList&gt;");
                        } else {
                            writeString(l, "&lt;mCamList /&gt;");
                        }
                    };
                    writeString(0, "&lt;?xml version=&quot;1.0&quot; encoding=&quot;utf-8&quot;?&gt;");
                    writeString(0, "&lt;CTimeStream xmlns:xsi=&quot;http://www.w3.org/2001/XMLSchema-instance&quot; xmlns:xsd=&quot;http://www.w3.org/2001/XMLSchema&quot; xsi:type=&quot;CNoteStream&quot;&gt;");
                    writeData(1, "mLength", (Semi["data"][index]["info"]["length"]["when"]["t"] + delay) / 1000);
                    writeString(1, "&lt;mChildStreams /&gt;");
                    writeData(1, "mSongName", songName);
                    writeData(1, "mStartDelay", delay / 1000);
                    writeData(1, "mBGImagePath", "Assets/Resources/Song/" + songName + "/" + songName + "_ipad.jpg");
                    writeData(1, "mJudgeCnt", judgeCnt);
                    writeData(1, "mBPM", Semi["data"][index]["info"]["bpm"]);
                    writeBPMList(1);
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
        var actionData = (function () {
            var r = [];
            var key = Semi["data"][index]["info"]["key"];
            var lr = 0;
            var xToTrack = function (x) {
                var v = Math.floor(x * key);
                if(key % 2 == 0) {
                    if((x == 0.5) && (lr < 0)) {
                        v -= 1;
                    }
                    lr = (x < 0.5 ? -1 : (x > 0.5 ? 1 : lr));
                }
                return v;
            };
            var fromPoints = function (p) {
                var v = [];
                var t = -1;
                for(var i = 0; i < p.length; i++) {
                    var action = 0x00;
                    var time = p[i][0];
                    var track = xToTrack(p[i][1]);
                    var param = 0;
                    if(t != -1) {
                        v.push([0x21, time, t, 0]);
                        t = -1;
                    }
                    if(i + 1 < p.length) {
                        var time2 = p[i + 1][0];
                        var track2 = xToTrack(p[i + 1][1]);
                        if(time2 == time) {
                            action = 0x21;
                            param = track2 - track;
                        } else {
                            action = 0x22;
                            param = time2 - time;
                            if(track2 != track) {
                                t = track2;
                            }
                        }
                    }
                    if((v.length > 0) && (action == v[v.length - 1][0])) {
                        switch(action) {
                            case 0x21:
                                v[v.length - 1][3] = track - v[v.length - 1][2];
                                break;
                            case 0x22:
                                if(t != -1) {
                                    v.push([0x21, time, track, 0]);
                                    v.push([action, time, track, param]);
                                } else {
                                    v[v.length - 1][3] += param;
                                }
                                break;
                        }
                    } else if((p.length == 1) || ((p.length > 1) && (i != p.length - 1))) {
                        v.push([action, time, track, param]);
                    }
                }
                if(t != -1) {
                    v.push([0x21, v[v.length - 1][1], t, 0]);
                    t = -1;
                }
                if(v.length == 1) {
                    v[0][0] = (v[0][0] == 0x21 ? 0x01 : v[0][0]);
                    v[0][0] = (v[0][0] == 0x22 ? 0x02 : v[0][0]);
                } else {
                    v[0][0] = (v[0][0] == 0x21 ? 0x61 : v[0][0]);
                    v[0][0] = (v[0][0] == 0x22 ? 0x62 : v[0][0]);
                    v[v.length - 1][0] = (v[v.length - 1][0] == 0x21 ? 0xA1 : v[v.length - 1][0]);
                    v[v.length - 1][0] = (v[v.length - 1][0] == 0x22 ? 0xA2 : v[v.length - 1][0]);
                }
                for(var i = 0; i < v.length; i++) {
                    v[i] = [v[i][0], v[i][1], Math.round(v[i][2]), Math.round(v[i][3])]
                }
                return v;
            };
            for(var i = 0; i < Semi["data"][index]["note"].length; i++) {
                if(Semi["data"][index]["note"][i]["link"]["b"] == -1) {
                    var p = [];
                    var n = i;
                    if(Semi["data"][index]["note"][i]["auto"] != "true") {
                        p.push([Semi["data"][index]["note"][n]["when"]["t"], Semi["data"][index]["note"][n]["where"]["x"]]);
                    }
                    while(true) {
                        var a = Semi["data"][index]["note"][n]["link"]["a"];
                        if(a != -1) {
                            if(Semi["data"][index]["note"][i]["auto"] != "true") {
                                p.push([Semi["data"][index]["note"][a]["when"]["t"], Semi["data"][index]["note"][a]["where"]["x"]]);
                            }
                            n = a;
                        } else {
                            break;
                        }
                    }
                    if(p.length > 0) {
                        r.writes(fromPoints(p));
                    }
                }
            }
            return r;
        })();
        var beatData = (function () {
            var r = [];
            var isFill = false;
            for(var i = 0; i < Semi["data"][index]["beat"].length; i++) {
                var time = Semi["data"][index]["beat"][i]["when"]["t"];
                var bpm = Semi["data"][index]["beat"][i]["bpm"] / Semi["data"][index]["beat"][i]["bpn"] * 4;
                if((r.length == 0) || ((r.length > 0) && (bpm != r[r.length - 1][1]))) {
                    r.push([time, bpm]);
                }
            }
            if(r[r.length - 1][0] < Semi["data"][index]["info"]["max"]["when"]["t"]) {
                r.push([Semi["data"][index]["info"]["max"]["when"]["t"], r[r.length - 1][1]]);
            }
            if(isFill) {
                var v = [];
                for(var i = 1; i < r.length; i++) {
                    v.push(r[i - 1]);
                    var s = Math.floor(beatFromTime(Semi["data"][index], r[i - 1][0])) + 1;
                    var e = Math.ceil(beatFromTime(Semi["data"][index], r[i][0]));
                    for(var j = s; j < e; j++) {
                        v.push([timeFromBeat(Semi["data"][index], j), v[v.length - 1][1]]);
                    }
                }
                v.push(r[r.length - 1]);
                r = v;
            }
            return r;
        })();
        var buffer = (function () {
            var r = new Uint8Array(14 + beatData.length * 12 + actionData.length * 11);
            var p = 0;
            r.setInt32(Semi["data"][index]["info"]["length"]["when"]["t"], p, true);
            p += 4;
            r.setInt32(beatData.length, p, true);
            p += 4;
            for(var i = 0; i < beatData.length; i++) {
                r.setInt32(beatData[i][0], p, true);
                p += 4;
                r.setFloat64(beatData[i][1], p, true);
                p += 8;
            }
            r.setUint8(3, p);
            p += 1;
            r.setUint8(3, p);
            p += 1;
            r.setInt32(actionData.length, p, true);
            p += 4;
            for(var i = 0; i < actionData.length; i++) {
                r.setInt16(actionData[i][0], p, true);
                p += 2;
                r.setInt32(actionData[i][1], p, true);
                p += 4;
                r.setUint8(actionData[i][2], p);
                p += 1;
                r.setInt32(actionData[i][3], p, true);
                p += 4;
            }
            return r;
        })();
        var imd = (function () {
            var r = [];
            var writeHex = function (l) {
                r.push(buffer.getHex(p, l, " "));
                p += l;
            };
            var p = 0;
            writeHex(4);
            writeHex(4);
            for(var i = 0; i < beatData.length; i++) {
                writeHex(12);
            }
            writeHex(2);
            writeHex(4);
            for(var i = 0; i < actionData.length; i++) {
                writeHex(11);
            }
            return r.join("\r\n");
        })();
        return imd;
    };
    var toMdeText = function (index) {
        Text["Xmlmde"][index] = Text["Xmlmde"][index] || toXmlText(index, "mde");
        Buffer["Xmlmde"][index] = new Uint8Array().fromText(Text["Xmlmde"][index]);
        if(!isUndefined(Buffer["Xmlmde"][index])) {
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
            return "[" + b + "," + c + "," + d + "]";
        };
        var actionData = (function () {
            var r = [];
            var key = Data[index]["Key"];
            var track = (function () {
                var r = key;
                switch(type) {
                    case "key":
                        r = (r < 4 ? 4 : r);
                        r = (r > 10 ? 10 : r);
                        break;
                    case "step":
                        r = (r < 4 ? 4 : r);
                        r = (r > 10 ? 10 : r);
                        r = (r == 9 ? 10 : r);
                        break;
                    case "dj":
                        r = (r < 7 ? 6 : 8);
                        break;
                }
                return r;
            })();
            switch(type) {
                case "key":
                case "step":
                case "dj":
                    r = toActionDataNoIntersect(toActionDataTransTrack(toActionDataNoSlide(Data[index]["ActionData"]), track));
                    break;
                case "catch":
                case "taiko":
                    r = toActionDataOneTrack(Data[index]["ActionData"], Data[index]["BPM"]);
                    break;
                case "pad":
                    r = toActionDataSquareLong(Data[index]["ActionData"], Data[index]["BPM"], 4, 4);
                    break;
            }
            return r;
        })();
        var key = getActionDataKey(actionData);
        var version = (function () {
            var v = "[" + type + "]";
            switch(type) {
                case "key":
                case "step":
                case "dj":
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
                    case "key":
                    case "step":
                    case "dj":
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
                    case "catch":
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
                                    case "fix":
                                        d["x"] = Math.floor((0.5 + s) / Data[index]["Key"] * 512);
                                        break;
                                    case "offset":
                                        d["x"] = Math.floor((0.5 - p / 2 + (t + (b % 4 > 2 ? 1 - (b % 2) / 2 : (b % 2) / 2)) / Data[index]["Key"] * p) * 512);
                                        break;
                                    case "return":
                                        d["x"] = Math.floor((b % u + (s + 0.5) / Data[index]["Key"]) / (u + 1) * 512);
                                        if(Math.floor(b / u) % 2 != 0) {
                                            d["x"] = 511 - d["x"];
                                        }
                                        break;
                                }
                                break;
                            case 0x02:
                                if((r.length > 0) && !isUndefined(r[r.length - 1]["endbeat"]) && (r[r.length - 1]["endbeat"] == 0)) {
                                    r[r.length - 1]["endbeat"] = toBeatString(actionData[i][0]);
                                } else {
                                    d["endbeat"] = 0;
                                    d["type"] = 3;
                                }
                                break;
                        }
                        if(isUndefined(d["x"]) && isUndefined(d["type"])) {
                            continue;
                        }
                        break;
                    case "pad":
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
                    case "taiko":
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
                                        case "0010":
                                        case "0100":
                                            v = 0;
                                            break;
                                        case "0001":
                                        case "1000":
                                            v = 1;
                                            break;
                                        case "0110":
                                        case "0111":
                                        case "1110":
                                            v = 2;
                                            break;
                                        case "1001":
                                        case "1011":
                                        case "1101":
                                            v = 3;
                                            break;
                                        case "0011":
                                        case "1100":
                                        case "0101":
                                        case "1010":
                                            v = (sDong ? 0 : 1);
                                            sDong = !sDong;
                                            break;
                                        case "1111":
                                            v = (bDong ? 2 : 3);
                                            bDong = !bDong;
                                            break;
                                    }
                                    break;
                                case 0x02:
                                    if((r.length > 0) && !isUndefined(r[r.length - 1]["endbeat"]) && (r[r.length - 1]["endbeat"] == 0)) {
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
        writeMc(2, '"cover": "' + readMeta(["Cover"], info.nameCore + ".png") + '",');
        writeMc(2, '"background": "' + readMeta(["Background"], info.nameCore + ".png") + '",');
        writeMc(2, '"version": "' + readMeta(["Version"], version) + '",');
        writeMc(2, '"creator": "' + readMeta(["Creator"], "rmstZ.html[" + info.nameCore + (info.type == info.nameCore ? "" : "_" + info.type) + "]") + '",');
        writeMc(2, '"maker": "' + "rmstZ.html[" + info.nameCore + (info.type == info.nameCore ? "" : "_" + info.type) + "]" + '",');
        writeMc(2, '"datetime": "' + new Date().format() + '",');
        writeMc(2, '"time": ' + Math.floor(new Date().getTime() / 1000) + ",");
        writeMc(2, '"id": ' + readMeta(["Id"], 0) + ",");
        switch(type) {
            case "key":
            case "step":
            case "dj":
                writeMc(2, '"mode": ' + "0,");
                break;
            case "catch":
                writeMc(2, '"mode": ' + "3,");
                break;
            case "pad":
                writeMc(2, '"mode": ' + "4,");
                break;
            case "taiko":
                writeMc(2, '"mode": ' + "5,");
                break;
        }
        writeMc(2, '"song": {');
        writeMc(3, '"title": "' + readMeta(["SongTitle"], info.nameCore) + '",');
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
            case "key":
            case "step":
            case "dj":
                writeMc(3, '"column": ' + key);
                break;
            case "catch":
                writeMc(3, '"speed": ' + readMeta(["Mode_extSpeed"], Math.floor(25 / Data[index]["Key"])));
                break;
            case "pad":
                writeMc(3, '"interval": ' + readMeta(["Mode_extInterval"], 0.001));
                break;
            case "taiko":
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
        writeMc(3, '"sound": "' + new FileInfo(readMeta(["AudioFilename", "Wave", "Wav"], info.nameCore)).name + ".mp3" + '",');
        writeMc(3, '"type": 1');
        writeMc(2, (note.length == 0 ? "}" : "},"));
        var m = ["beat", "endbeat", "column", "x", "index", "endindex", "type", "style"];
        for(var i = 0; i < note.length; i++) {
            writeMc(2, "{");
            var t = [];
            for(var n in note[i]) {
                if(!isUndefined(note[i][n])) {
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
        var aff = [];
        var d = [];
        var timing = [];
        var tab = [];
        var hold = [];
        var arc = [];
        var arctab = [];
        var isTrans4Key = false;
        var beat = copyObject(Semi["data"][index]["beat"]);
        var note = (isTrans4Key ? toData(Semi["data"][index], "TransKey", {"TransKey": 4})["note"] : copyObject(Semi["data"][index]["note"]));
        var key = Semi["data"][index]["info"]["key"];
        for(var i = 0; i < beat.length; i++) {
            if(beat[i]["flow"] != beat[i]["bpm"] / beat[i]["bpn"] * 4) {
                timing.push([beat[i]["when"]["t"], 0, beat[i]["flow"]]);
            } else {
                timing.push([beat[i]["when"]["t"], 0, beat[i]["bpm"]]);
            }
        }
        for(var i = 0; i < note.length; i++) {
            if(note[i]["link"]["b"] == -1) {
                var v = [];
                var n = i;
                while(true) {
                    var a = note[n]["link"]["a"];
                    if(a != -1) {
                        if((note[a]["when"]["t"] != note[n]["when"]["t"]) || (note[n]["where"]["x"] != note[a]["where"]["x"]) || (note[n]["where"]["y"] != note[a]["where"]["y"])) {
                            v.push([note[n]["when"]["t"], 3, note[n]["where"]["xt"] + 1, note[a]["when"]["t"], note[n]["where"]["x"], note[a]["where"]["x"], note[n]["line"]["curve"], note[n]["where"]["y"], note[a]["where"]["y"], note[n]["group"], "none", note[n]["auto"], []]);
                        }
                        n = a;
                    } else {
                        if(note[n]["link"]["b"] == -1) {
                            if(note[n]["auto"] != "true") {
                                if(note[n]["where"]["y"] == -1) {
                                    tab.push([note[n]["when"]["t"], 1, note[n]["where"]["xt"] + 1]);
                                } else {
                                    arctab.push([note[n]["when"]["t"], note[n]["where"]["xt"] + 1, note[n]["where"]["x"], note[n]["where"]["y"]]);
                                }
                            }
                        } else if((v.length > 0) && (note[n]["where"]["t"] == v[v.length - 1][0]) && (note[n]["where"]["x"] != v[v.length - 1][4]) && (note[n]["where"]["y"] == v[v.length - 1][7])) {
                            v.push([note[n]["when"]["t"], 3, note[n]["where"]["xt"] + 1, note[n]["when"]["t"] + 1, note[n]["where"]["x"], note[n]["where"]["x"], note[n]["line"]["curve"], note[n]["where"]["y"], note[n]["where"]["y"], note[n]["group"], "none", note[n]["auto"], []]);
                        }
                        break;
                    }
                }
                if(v.length != 0) {
                    arc.push(v);
                }
            }
        }
        var isHold = function (n) {
            if((arc[n].length != 1) || (arc[n][0][0] == arc[n][0][3]) || (arc[n][0][4] != arc[n][0][5]) || (arc[n][0][7] != -1) || (arc[n][0][8] != -1) || (arc[n][0][7] != arc[n][0][8]) || (arc[n][0][6] != "") || (arc[n][0][9] != "") || (arc[n][0][11] == "true") || (arc[n][0][12].length != 0)) {
                return false;
            }
            return true;
        };
        for(var i = 0; i < arc.length; i++) {
            if(isHold(i)) {
                hold.push([arc[i][0][0], 2, arc[i][0][2], arc[i][0][3]]);
                arc.splice(i, 1);
                i -= 1;
            }
        }
        for(var i = 0; i < arc.length; i++) {
            var color = (function () {
                var v = "";
                switch(arc[i][0][9]) {
                    case "arcaea_0":
                        v = "0";
                        break;
                    case "arcaea_1":
                        v = "1";
                        break;
                    default:
                        v = (arc[i][0][4] < 0.5 ? "0" : "1");
                        break;
                }
                return v;
            })();
            var isTab = (arc[i][0][11] == "true" ? "true" : "false");
            for(var j = 0; j < arc[i].length; j++) {
                arc[i][j][2] = -1;
                arc[i][j][4] = arc[i][j][4];
                arc[i][j][5] = arc[i][j][5];
                arc[i][j][6] = (function () {
                    var v = [];
                    switch(arc[i][0][6]) {
                        case "arcaea_s":
                            v = "s";
                            break;
                        case "arcaea_so":
                            v = "so";
                            break;
                        case "arcaea_si":
                            v = "si";
                            break;
                        case "arcaea_b":
                            v = "b";
                            break;
                        case "arcaea_soso":
                            v = "soso";
                            break;
                        case "arcaea_sosi":
                            v = "sosi";
                            break;
                        case "arcaea_siso":
                            v = "siso";
                            break;
                        case "arcaea_sisi":
                            v = "sisi";
                            break;
                        default:
                            v = "s";
                            break;
                    }
                    return v;
                })();
                arc[i][j][7] = (arc[i][j][7] == -1 ? 0 : arc[i][j][7]);
                arc[i][j][8] = (arc[i][j][8] == -1 ? 0 : arc[i][j][8]);
                arc[i][j][9] = color;
                arc[i][j][11] = isTab;
            }
        }
        for(var i = 0; i < arc.length; i++) {
            if((arc[i].length == 1) && (arc[i][0][0] == arc[i][0][3])) {
                arctab.push([arc[i][0][3], arc[i][0][2], arc[i][0][5], arc[i][0][8]]);
                arc.splice(i, 1);
                i -= 1;
            }
        }
        var existTime = {"0": [], "1": []};
        var isExist = function (t) {
            if(t[11] == "true") {
                return false;
            }
            for(var i = 0; i < existTime[t[9]].length; i++) {
                if((t[0] != existTime[t[9]][i][1]) && (t[3] != existTime[t[9]][i][0])) {
                    if(((t[0] >= existTime[t[9]][i][0]) && (t[0] < existTime[t[9]][i][1])) || ((t[3] > existTime[t[9]][i][0]) && (t[3] <= existTime[t[9]][i][1])) || ((existTime[t[9]][i][0] >= t[0]) && (existTime[t[9]][i][1] < t[0])) || ((existTime[t[9]][i][0] > t[3]) && (existTime[t[9]][i][1] <= t[3]))) {
                        return true;
                    }
                }
            }
            (function () {
                for(var i = 0; i < existTime[t[9]].length; i++) {
                    if((t[0] >= existTime[t[9]][i][0]) && (t[0] <= existTime[t[9]][i][1])) {
                        if(existTime[t[9]][i][1] < t[3]) {
                            existTime[t[9]][i][1] = t[3];
                        }
                        return;
                    }
                }
                for(var i = 0; i < existTime[t[9]].length; i++) {
                    if((t[3] >= existTime[t[9]][i][0]) && (t[3] <= existTime[t[9]][i][1])) {
                        if(existTime[t[9]][i][0] < t[0]) {
                            existTime[t[9]][i][0] = t[0];
                        }
                        return;
                    }
                }
                existTime[t[9]].push([t[0], t[3]]);
            })();
            return false;
        };
        for(var i = 0; i < arc.length; i++) {
            for(var j = 0; j < arc[i].length; j++) {
                if(isExist(arc[i][j])) {
                    arc[i].splice(j, 1);
                    j -= 1;
                }
            }
        }
        var unflowTimes = (function () {
            var v = [];
            for(var i = 0; i < timing.length; i++) {
                if((timing[i][1] == 0) && (timing[i][2] == 0)) {
                    if(i < timing.length - 1) {
                        v.push([timing[i][0], timing[i + 1][0]]);
                    } else {
                        v.push([timing[i][0], Semi["data"][index]["info"]["max"]["when"]["t"]]);
                    }
                }
            }
            return v;
        })();
        var isInUnflow = function (t) {
            var t2 = (arguments.length > 1 ? arguments[1] : t);
            for(var i = 0; i < unflowTimes.length; i++) {
                if(t == unflowTimes[i][0]) {
                    return "on";
                }
                if(t2 == t) {
                    if(((t > unflowTimes[i][0]) && (t <= unflowTimes[i][1]))) {
                        return "in";
                    }
                } else {
                    if(((t > unflowTimes[i][0]) && (t <= unflowTimes[i][1])) || ((t2 >= unflowTimes[i][0]) && (t2 <= unflowTimes[i][1])) || ((unflowTimes[i][0] > t) && (unflowTimes[i][1] <= t)) || ((unflowTimes[i][0] >= t2) && (unflowTimes[i][1] <= t2))) {
                        return "in";
                    }
                }
            }
            return "away";
        };
        for(var i = 0; i < tab.length; i++) {
            if(isInUnflow(tab[i][0]) == "in") {
                tab.splice(i, 1);
                i -= 1;
            }
        }
        for(var i = 0; i < hold.length; i++) {
            if(isInUnflow(hold[i][0], hold[i][3]) != "away") {
                hold.splice(i, 1);
                i -= 1;
            }
        }
        for(var i = 0; i < arc.length; i++) {
            for(var j = 0; j < arc[i].length; j++) {
                var s = isInUnflow(arc[i][j][0], arc[i][j][3]);
                if(s != "away") {
                    if((arc[i][j].length > 11) && (arc[i][j][11] == "true")) {
                        arc[i][j][13] = s;
                    } else {
                        arc[i].splice(j, 1);
                        j -= 1;
                    }
                } else {
                    arc[i][j][13] = s;
                }
            }
        }
        for(var i = 0; i < arctab.length; i++) {
            if(isInUnflow(arctab[i][0]) == "in") {
                arctab.splice(i, 1);
                i -= 1;
            }
        }
        var addSkyTab = function (t) {
            for(var i = 0; i < arc.length; i++) {
                for(var j = 0; j < arc[i].length; j++) {
                    if((arc[i][j][11] == "true") && (((arc[i][j][13] == "on") && (arc[i][j][0] == t[0])) || (arc[i][j][13] == "away")) && (arc[i][j][0] <= t[0]) && (arc[i][j][3] >= t[0]) && (((arc[i][j][0] == arc[i][j][3]) && (arc[i][j][4] == t[2]) && (arc[i][j][7] == t[3])) || ((arc[i][j][0] != arc[i][j][3]) && ((arc[i][j][4] + (t[0] - arc[i][j][0]) / (arc[i][j][3] - arc[i][j][0]) * (arc[i][j][5] - arc[i][j][4])).toAbbreviated(6) == t[2]) && ((arc[i][j][7] + (t[0] - arc[i][j][0]) / (arc[i][j][3] - arc[i][j][0]) * (arc[i][j][8] - arc[i][j][7])).toAbbreviated(6) == t[3])))) {
                        arc[i][j][12].push(t[0]);
                        return true;
                    }
                }
            }
            return false;
        };
        for(var i = 0; i < arctab.length; i++) {
            if(addSkyTab(arctab[i])) {
                arctab.splice(i, 1);
                i -= 1;
            }
        }
        var linkSkyTab = function (t) {
            var isLinkSkyTab = Checks["InvalidKeyExtend"];
            if(!isLinkSkyTab) {
                return false;
            }
            for(var i = arc.length - 1; i > -1; i--) {
                for(var j = arc[i].length - 1; j > -1; j--) {
                    if((arc[i][j][3] < t[0]) && (Math.abs(arc[i][j][5] - t[2]) <= 0.25) && (isInUnflow(arc[i][j][3], t[0]) == "away")) {
                        arc.push([
                                     [arc[i][j][3], 3, t[1], t[0], arc[i][j][5], t[2], "s", arc[i][j][8], t[3], (arc[i][j][4] < 0.5 ? "0" : "1"), "none", "true", [t[0]]]
                                 ]);
                        return true;
                    }
                }
            }
            return false;
        };
        for(var i = 0; i < arctab.length; i++) {
            if(!linkSkyTab(arctab[i])) {
                arc.push([
                             [arctab[i][0], 3, arctab[i][1], arctab[i][0], arctab[i][2], arctab[i][2], "s", arctab[i][3], arctab[i][3], (arctab[i][2] < 0.5 ? "0" : "1"), "none", "true", [arctab[i][0]]]
                         ]);
            }
            arctab.splice(i, 1);
            i -= 1;
        }
        d.writes(timing);
        d.writes(tab);
        d.writes(hold);
        for(var i = 0; i < arc.length; i++) {
            d.writes(arc[i]);
        }
        d = d.sort(SortNumbers);
        var drawExtend = (ext != "aff");
        if(drawExtend) {
            for(var i = 0; i < d.length; i++) {
                if((d[i].length > 13) && (d[i][13] == "away")) {
                    for(var j = 0; j < d.length; j++) {
                        if((j != i) && (d[j].length > 13) && (d[j][13] == "away") && (d[j][0] != d[j][3]) && (((d[j][11] == "true") && (d[i][11] == "true")) || ((d[j][11] != "true") && (d[i][11] != "true"))) && (((d[j][11] != "true") && (d[j][9] == d[i][9])) || (d[j][11] == "true")) && (d[j][0] == d[i][3]) && (d[j][4] == d[i][5]) && (d[j][7] == d[i][8])) {

                            if((((d[i][5] == d[i][4])) && ((d[j][5] == d[j][4]))) || ((d[i][3] != d[i][0]) && (d[i][5] != d[i][4]) && (d[j][3] != d[j][0]) && (d[j][5] != d[j][4]) && (Math.atan((d[j][3] - d[j][0]) / (d[j][5] - d[j][4])) / Math.PI * 180).isClose(Math.atan((d[i][3] - d[i][0]) / (d[i][5] - d[i][4])) / Math.PI * 180) && (Math.atan((d[j][3] - d[j][0]) / (d[j][8] - d[j][7])) / Math.PI * 180).isClose(Math.atan((d[i][3] - d[i][0]) / (d[i][8] - d[i][7])) / Math.PI * 180))) {
                                d[i][6] = (d[i][6] == "b" ? "so" : d[i][6]);
                                d[i][6] = (d[i][6] == "si" ? "s" : d[i][6]);
                                d[j][6] = (d[j][6] == "b" ? "si" : d[j][6]);
                                d[j][6] = (d[j][6] == "so" ? "s" : d[j][6]);
                            } else {
                                if(d[i][4] != d[i][5]) {
                                    d[i][6] = (d[i][6] == "s" ? "si" : d[i][6]);
                                    d[i][6] = (d[i][6] == "sisi" ? "si" : d[i][6]);
                                    d[i][6] = (d[i][6] == "siso" ? "si" : d[i][6]);
                                    d[i][6] = (d[i][6] == "so" ? "b" : d[i][6]);
                                    d[i][6] = (d[i][6] == "sosi" ? "b" : d[i][6]);
                                    d[i][6] = (d[i][6] == "soso" ? "b" : d[i][6]);
                                } else {
                                    d[i][6] = "s";
                                }
                                if(d[j][4] != d[j][5]) {
                                    d[j][6] = (d[j][6] == "s" ? "so" : d[j][6]);
                                    d[i][6] = (d[i][6] == "sosi" ? "so" : d[i][6]);
                                    d[i][6] = (d[i][6] == "soso" ? "so" : d[i][6]);
                                    d[j][6] = (d[j][6] == "si" ? "b" : d[j][6]);
                                    d[j][6] = (d[j][6] == "siso" ? "b" : d[j][6]);
                                    d[j][6] = (d[j][6] == "sisi" ? "b" : d[j][6]);
                                } else {
                                    d[j][6] = "s";
                                }
                            }
                        }
                    }
                }
            }
            for(var i = 0; i < d.length; i++) {
                if((d[i].length > 13) && (d[i][13] == "away")) {
                    for(var j = 0; j < d.length; j++) {
                        if((j != i) && (d[j].length > 13) && (d[j][13] == "away") && (d[j][0] != d[j][3]) && (((d[j][11] == "true") && (d[i][11] == "true")) || ((d[j][11] != "true") && (d[i][11] != "true"))) && (((d[j][11] != "true") && (d[j][9] == d[i][9])) || (d[j][11] == "true")) && (d[j][0] == d[i][3]) && (d[j][4] == d[i][5]) && (d[j][7] == d[i][8])) {
                            if(d[i][7] != d[i][8]) {
                                d[i][6] = (d[i][6] == "s" ? "sosi" : d[i][6]);
                                d[i][6] = (d[i][6] == "si" ? "sisi" : d[i][6]);
                            }
                            if(d[j][7] != d[j][8]) {
                                d[j][6] = (d[j][6] == "s" ? "siso" : d[j][6]);
                                d[j][6] = (d[j][6] == "so" ? "soso" : d[j][6]);
                            }
                        }
                    }
                }
            }
        }
        for(var i = 0; i < d.length; i++) {
            if(d[i].length > 3) {
                if((d[i].length > 11) && (d[i][11] == "true") && (d[i][12].length > 0)) {
                    d[i][3] = (d[i][0] == d[i][3] ? d[i][3] + 1 : d[i][3]);
                }
            }
        }
        var toX = function (x) {
            return (x * 2 - 1) / 0.998 + 0.5;
        };
        var toY = function (y) {
            return (y * 2 - 1) / 0.998 / 2 + 0.5;
        };
        aff.push("AudioOffset:" + getEffectOffset(index, "audio"));
        aff.push("-");
        var noteData = [];
        for(var i = 0; i < d.length; i++) {
            switch(d[i][1]) {
                case 0:
                    aff.push("timing(" + d[i][0] + "," + d[i][2].toFormated(2) + ",4.00);");
                    break;
                case 1:
                    aff.push("(" + d[i][0] + "," + d[i][2] + ");");
                    var t = d[i][0];
                    var b = beatFromTime(Semi["data"][index], t);
                    var n = noteFromBeat(Semi["data"][index], b);
                    var x = (d[i][2] - 0.5) / key;
                    noteData.push({"action": 0x00, "when": {"b": b, "n": n, "t": t}, "when2": {"b": b, "n": n, "t": t}, "where": {"x": x}, "where2": {"x": x}});
                    break;
                case 2:
                    aff.push("hold(" + d[i][0] + "," + d[i][3] + "," + d[i][2] + ");");
                    var t = d[i][0];
                    var b = beatFromTime(Semi["data"][index], t);
                    var n = noteFromBeat(Semi["data"][index], b);
                    var t2 = d[i][3];
                    var b2 = beatFromTime(Semi["data"][index], t2);
                    var n2 = noteFromBeat(Semi["data"][index], b2);
                    var x = (d[i][2] - 0.5) / key;
                    noteData.push({"action": 0x02, "when": {"b": b, "n": n, "t": t}, "when2": {"b": b2, "n": n2, "t": t2}, "where": {"x": x}, "where2": {"x": x}});
                    break;
                case 3:
                    var p = [];
                    if(d[i][11] == "true") {
                        for(var j = 0; j < d[i][12].length; j++) {
                            p.push("arctap(" + d[i][12][j] + ")");
                        }
                    }
                    aff.push("arc(" + d[i][0] + "," + d[i][3] + "," + toX(d[i][4]).toFormated(2) + "," + toX(d[i][5]).toFormated(2) + "," + d[i][6] + "," + toY(d[i][7]).toFormated(2) + "," + toY(d[i][8]).toFormated(2) + "," + d[i][9] + "," + d[i][10] + "," + d[i][11] + ")" + (p.length > 0 ? "[" + p.join(",") + "]" : "") + ";");
                    var t = d[i][0];
                    var b = beatFromTime(Semi["data"][index], t);
                    var n = noteFromBeat(Semi["data"][index], b);
                    var t2 = d[i][3];
                    var b2 = beatFromTime(Semi["data"][index], t2);
                    var n2 = noteFromBeat(Semi["data"][index], b2);
                    var x = d[i][4];
                    var y = d[i][7];
                    var x2 = d[i][5];
                    var y2 = d[i][8];
                    noteData.push({"action": 0x22, "when": {"b": b, "n": n, "t": t}, "when2": {"b": b2, "n": n2, "t": t2}, "where": {"x": x, "y": y}, "where2": {"x": x2, "y": y2}, "style": {"curve": "arcaea_" + d[i][6]}, "group": "arcaea_" + d[i][9], "auto": d[i][11]});
                    if(d[i][11] == "true") {
                        for(var j = 0; j < d[i][12].length; j++) {
                            var t = d[i][12][j];
                            var b = beatFromTime(Semi["data"][index], t);
                            var n = noteFromBeat(Semi["data"][index], b);
                            var x = d[i][4] + ((d[i][12][j] == d[i][0]) || (d[i][3] == d[i][0]) ? 0 : (d[i][12][j] - d[i][0]) / (d[i][3] - d[i][0]) * (d[i][5] - d[i][4]));
                            var y = d[i][7] + ((d[i][12][j] == d[i][0]) || (d[i][3] == d[i][0]) ? 0 : (d[i][12][j] - d[i][0]) / (d[i][3] - d[i][0]) * (d[i][8] - d[i][7]));
                            noteData.push({"action": 0x21, "when": {"b": b, "n": n, "t": t}, "when2": {"b": b, "n": n, "t": t}, "where": {"x": x, "y": y}, "where2": {"x": x, "y": y}});
                        }
                    }
                    break;
            }
        }
        Semi["data"][index]["draw"]["aff"] = Semi["data"][index]["draw"]["aff"] || {};
        Semi["data"][index]["draw"]["aff"]["noteData"] = noteData;
        return aff.join("\r\n");
    };
    var toMstText = function (indexes) {
        var mst = copyObject(Semi);
        var isClear = true;
        if(isClear) {
            var file = newData("file");
            var note = newData("note");
            var beat = newData("beat");
            var effect = newData("effect");
            var transform = newData("transform");
            var clearData = function (data, mode) {
                var current = (arguments.length > 2 ? arguments[2] : note["link"]["c"]);
                var r = copyObject(data);
                var clearDefault = function (o, d) {
                    return (isUndefined(o) || (o == d) ? undefined : o);
                };
                var isEmpty = function (o) {
                    if(!isUndefined(o)) {
                        if(isObject(o)) {
                            var n = Object.getOwnPropertyNames(o);
                            for(var i = 0; i < n.length; i++) {
                                switch(n[i]) {
                                    case "length":
                                        if(o[n[i]] != 0) {
                                            for(var j = 0; j < o[n[i]]; j++) {
                                                if(!isUndefined(o[j])) {
                                                    return false;
                                                }
                                            }
                                        }
                                        break;
                                    default:
                                        if(!isUndefined(o[n[i]])) {
                                            return false;
                                        }
                                        break;
                                }
                            }
                        } else {
                            return false;
                        }
                    }
                    return true;
                };
                var clearEmpty = function (o) {
                    return (isEmpty(o) ? undefined : o);
                };
                switch(mode) {
                    case "when":
                        r = r || {};
                        r["b"] = clearDefault(r["b"], beat[mode]["b"]);
                        r["n"] = clearDefault(r["n"], beat[mode]["n"]);
                        r["t"] = clearDefault(r["t"], beat[mode]["t"]);
                        break;
                    case "where":
                        r = r || {};
                        r["x"] = clearDefault(r["x"], note[mode]["x"]);
                        r["y"] = clearDefault(r["y"], note[mode]["y"]);
                        r["z"] = clearDefault(r["z"], note[mode]["z"]);
                        r["xt"] = undefined;
                        r["xp"] = undefined;
                        break;
                    case "link":
                        r = r || {};
                        r["b"] = clearDefault(r["b"], note[mode]["b"]);
                        r["c"] = clearDefault(r["c"], current);
                        r["a"] = clearDefault(r["a"], note[mode]["a"]);
                        break;
                    case "line":
                        r = r || {};
                        r["vector"] = clearData(r["vector"], "vector");
                        r["clockwise"] = clearDefault(r["clockwise"], note[mode]["clockwise"]);
                        r["follow"] = clearDefault(r["follow"], note[mode]["follow"]);
                        r["combo"] = clearDefault(r["curve"], note[mode]["combo"]);
                        r["curve"] = clearDefault(r["curve"], note[mode]["curve"]);
                        break;
                    case "vector":
                        r = r || [];
                        for(var i = 0; i < r.length; i++) {
                            r[i] = r[i] || {};
                            r[i]["where"] = clearData(r[i]["where"], "where");
                        }
                        break;
                    case "group":
                    case "auto":
                        r = clearDefault(r, note[mode]);
                        break;
                    case "bpn":
                    case "flow":
                        r = clearDefault(r, beat[mode]);
                        break;
                    case "matrix":
                        r = r || {};
                        r["a"] = clearDefault(r["a"], transform[mode]["a"]);
                        r["b"] = clearDefault(r["b"], transform[mode]["b"]);
                        r["c"] = clearDefault(r["c"], transform[mode]["c"]);
                        r["d"] = clearDefault(r["d"], transform[mode]["d"]);
                        r["e"] = clearDefault(r["e"], transform[mode]["e"]);
                        r["f"] = clearDefault(r["f"], transform[mode]["f"]);
                        r["g"] = clearDefault(r["g"], transform[mode]["g"]);
                        r["h"] = clearDefault(r["h"], transform[mode]["h"]);
                        r["i"] = clearDefault(r["i"], transform[mode]["i"]);
                        r["j"] = clearDefault(r["j"], transform[mode]["j"]);
                        r["k"] = clearDefault(r["k"], transform[mode]["k"]);
                        r["l"] = clearDefault(r["l"], transform[mode]["l"]);
                        r["m"] = clearDefault(r["m"], transform[mode]["m"]);
                        r["n"] = clearDefault(r["n"], transform[mode]["n"]);
                        r["o"] = clearDefault(r["o"], transform[mode]["o"]);
                        r["p"] = clearDefault(r["p"], transform[mode]["p"]);
                        break;
                    case "beat":
                        r = r || [];
                        for(var i = 0; i < r.length; i++) {
                            r[i] = r[i] || {};
                            r[i]["when"] = clearData(r[i]["when"], "when");
                            r[i]["flow"] = clearDefault(r[i]["flow"], r[i]["bpm"] / r[i]["bpn"] * 4);
                            r[i]["bpm"] = clearData(r[i]["bpm"], "bpm");
                            r[i]["bpn"] = clearData(r[i]["bpn"], "bpn");
                            r[i] = clearEmpty(r[i]);
                        }
                        break;
                    case "note":
                        r = r || [];
                        for(var i = 0; i < r.length; i++) {
                            r[i] = r[i] || {};
                            r[i]["when"] = clearData(r[i]["when"], "when");
                            r[i]["where"] = clearData(r[i]["where"], "where");
                            r[i]["link"] = clearData(r[i]["link"], "link", i);
                            r[i]["line"] = clearData(r[i]["line"], "line");
                            r[i]["group"] = clearData(r[i]["group"], "group");
                            r[i]["auto"] = clearData(r[i]["auto"], "auto");
                            r[i]["hitEffect"] = clearData(r[i]["hitEffect"], "effect");
                            r[i] = clearEmpty(r[i]);
                        }
                        break;
                    case "effect":
                        r = r || [];
                        for(var i = 0; i < r.length; i++) {
                            r[i] = r[i] || {};
                            r[i]["type"] = clearDefault(r[i]["type"], effect["type"]);
                            r[i]["when"] = clearData(r[i]["when"], "when");
                            r[i]["where"] = clearData(r[i]["where"], "where");
                            r[i]["name"] = clearDefault(r[i]["name"], effect["name"]);
                            r[i]["start"] = clearDefault(r[i]["start"], effect["start"]);
                            r[i]["duration"] = clearDefault(r[i]["duration"], effect["duration"]);
                            r[i]["volume"] = clearDefault(r[i]["volume"], effect["volume"]);
                            r[i]["speed"] = clearDefault(r[i]["speed"], effect["speed"]);
                            r[i]["repeat"] = clearDefault(r[i]["repeat"], effect["repeat"]);
                            r[i] = clearEmpty(r[i]);
                        }
                        break;
                    case "transform":
                        r = r || [];
                        for(var i = 0; i < r.length; i++) {
                            r[i] = r[i] || {};
                            r[i]["when"] = clearData(r[i]["when"], "when");
                            r[i]["matrix"] = clearData(r[i]["matrix"], "matrix");
                            r[i] = clearEmpty(r[i]);
                        }
                        break;
                    case "info":
                        r = r || {};
                        r["name"] = clearDefault(r["name"], "");
                        r["length"]["when"] = clearData(r["length"]["when"], "when");
                        r["length"] = clearEmpty(r["length"]);
                        r["min"]["when"] = clearData(r["min"]["when"], "when");
                        r["min"] = clearEmpty(r["min"]);
                        r["max"]["when"] = clearData(r["max"]["when"], "when");
                        r["max"] = clearEmpty(r["max"]);
                        break;
                    case "meta":
                        r = r || {};
                        break;
                    case "file":
                        r = r || [];
                        for(var i = 0; i < r.length; i++) {
                            r[i] = r[i] || {};
                            r[i]["name"] = clearDefault(r[i]["name"], file["name"]);
                            r[i]["base64"] = clearDefault(r[i]["base64"], file["base64"]);
                            r[i]["pcm"] = undefined;
                            r[i] = clearEmpty(r[i]);
                        }
                        break;
                    case "data":
                        r = r || {};
                        r["note"] = clearData(r["note"], "note");
                        r["beat"] = clearData(r["beat"], "beat");
                        r["effect"] = clearData(r["effect"], "effect");
                        r["transform"] = clearData(r["transform"], "transform");
                        r["info"] = clearData(r["info"], "info");
                        r["draw"] = undefined;
                        r = clearEmpty(r);
                        break;
                }
                r = clearEmpty(r);
                return r;
            };
            mst["meta"] = clearData(mst["meta"], "meta");
            mst["file"] = clearData(mst["file"], "file");
            mst["data"] = (function () {
                var v = [];
                for(var index = 0; index < mst["data"].length; index++) {
                    if(indexes.indexOf(index) != -1) {
                        mst["data"][index] = clearData(mst["data"][index], "data");
                        v.push(mst["data"][index]);
                    }
                }
                return v;
            })();
        }
        return JSON.stringify(mst, null, 2).replace(/\n/g, "\r\n");
    };
    var toPngText = function (indexes, type) {
        var options = (arguments.length > 2 ? arguments[2] : GetMstOption(false));
        var maxTime = (function () {
            var v = 0;
            for(var index = 0; index < Semi["data"].length; index++) {
                if((indexes.indexOf(index) != -1) && (Semi["data"][index]["info"]["max"]["when"]["t"] > v)) {
                    v = Semi["data"][index]["info"]["max"]["when"]["t"];
                }
            }
            return v;
        })();
        var isSameAudio = (function () {
            var list = [];
            for(var index = 0; index < Semi["data"].length; index++) {
                if(indexes.indexOf(index) != -1) {
                    for(var i = 0; i < Semi["data"][index]["effect"].length; i++) {
                        if(Semi["data"][index]["effect"][i]["type"] == "audio") {
                            list.push(Semi["data"][index]["effect"][i]["name"]);
                            break;
                        }
                    }
                }
            }
            for(var i = 1; i < list.length; i++) {
                if(list[i] != list[0]) {
                    return false;
                    break;
                }
            }
            return true;
        })();
        var cvs = [];
        var ctx = [];
        for(var index = 0; index < Semi["data"].length; index++) {
            Semi["data"][index]["draw"][type] = Semi["data"][index]["draw"][type] || toDraw(index, type);
            if(isUndefined(Semi["data"][index]["draw"][type]["beatLines"])) {
                Semi["data"][index]["draw"][type] = toDraw(index, type);
            }
            var draw = Semi["data"][index]["draw"][type];
            if(indexes.indexOf(index) != -1) {
                cvs[index] = {"sL": [], "k": [], "sR": []};
                ctx[index] = {"sL": [], "k": [], "sR": []};
                var key = Semi["data"][index]["info"]["key"];
                var scaleX = parseFloat(options["MstScaleX"]);
                var scaleY = parseFloat(options["MstScaleY"]);
                var frameX = 128;
                var frameY = 64;
                var paddingTop = 128 * scaleX;
                var paddingBottom = 64 * scaleX;
                var paddingLeft = 0 + (options["DrawTime"] ? 64 * scaleX : 0) + (options["DrawBeat"] ? 64 * scaleX : 0) + (options["DrawNote"] ? 64 * scaleX : 0);
                var paddingRight = 0 + (options["DrawTime"] ? 64 * scaleX : 0) + (options["DrawBeat"] ? 64 * scaleX : 0) + (options["DrawNote"] ? 64 * scaleX : 0);
                var coreWidth = (key == 0 ? 1 : Math.ceil(key / 10)) * frameX * scaleX;
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
                var noteWidth = Math.round(key < 7 ? coreWidth / key * (105 - 5 * key) / 100 : coreWidth / key * 0.85);
                var noteHeight = Math.round(key < 7 ? coreWidth / 16 : noteWidth / 2);
                var drawAlpha = 0.618;
                var touchNoteStyle = "#0000FF";
                var holdNoteStyle = "#00FF00";
                var strokeWidth = noteHeight / 2;
                var arrowStyle = "#00FF00";
                var turnPointRadius = noteHeight / 3;
                var turnPointStyle = ["#00FF00", "#00FF00", "#00FF00"];
                var backgroundStyle = "#FFFFFF";
                var frameStokeWidth = 1 * scaleX;
                var frameStokeStyle = "#000000";
                var trackLineWidth = 0.24 * scaleX;
                var trackLineStyle = "#00FFFF";
                var beatLineWidth = 0.24 * scaleX;
                var beatLineStyle = "#000000";
                var startLineWidth = 0.96 * scaleX;
                var startLineStyle = "#0000FF";
                var stopAeraStyle = "#BFBFBF";
                var lengthLineWidth = 0.96 * scaleX;
                var lengthLineStyle = "#BFBFBF";
                var determineLineWidth = 0.24 * scaleX;
                var determineLineStyle = "#FF00FF";
                var determinePointRadius = noteHeight / 2;
                var determinePointStyle = ["#FFCCCC", "#FF6666", "#FF0000"];
                var determineTextStyle = "#FF00FF";
                var textStyle = "#000000";
                var textFont = "12px Arial";
                var boldTextFont = "14px 微软雅黑 bold";
                for(var i = 0; i < splitCount; i++) {
                    cvs[index]["k"][i] = document.createElement("canvas");
                    cvs[index]["k"][i].style.display = "none";
                    cvs[index]["k"][i].width = wholeWidth;
                    cvs[index]["k"][i].height = (i != splitCount - 1 ? splitHeight : (wholeHeight == splitHeight ? wholeHeight : wholeHeight % splitHeight));
                    ctx[index]["k"][i] = cvs[index]["k"][i].getContext("2d");
                    ctx[index]["k"][i].globalAlpha = drawAlpha;
                }
                var drawRect = function (x1, y1, x2, y2, s) {
                    var i1 = 0;
                    var i2 = 0;
                    if(y1 > y2) {
                        i1 = Math.floor(y2 / splitHeight);
                        i2 = Math.floor(y1 / splitHeight);
                    } else if(y1 < y2) {
                        i1 = Math.floor(y1 / splitHeight);
                        i2 = Math.floor(y2 / splitHeight);
                    } else {
                        i1 = Math.floor(y1 / splitHeight);
                        i2 = Math.floor(y2 / splitHeight);
                    }
                    i1 = (i1 < 0 ? 0 : i1);
                    i2 = (i2 > splitCount - 1 ? splitCount - 1 : i2);
                    for(var i = i1; i < i2 + 1; i++) {
                        ctx[index]["k"][i].fillStyle = s;
                        ctx[index]["k"][i].fillRect(x1, y1, x2 - x1, y2 - y1);
                    }
                };
                var drawLine = function (x1, y1, x2, y2, w, s, t) {
                    var i1 = 0;
                    var i2 = 0;
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
                    i1 = (i1 < 0 ? 0 : i1);
                    i2 = (i2 > splitCount - 1 ? splitCount - 1 : i2);
                    for(var i = i1; i < i2 + 1; i++) {
                        ctx[index]["k"][i].beginPath();
                        switch(t) {
                            case "dot_2":
                            case "dot_4":
                                var d = parseInt(t.replace("dot_", ""));
                                var n = Math.ceil(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) / 40 * d);
                                var x = Math.min(x1, x2);
                                var xs = Math.abs(x1 - x2) / (n * 2 + 1);
                                var y = Math.min(y1, y2);
                                var ys = Math.abs(y1 - y2) / (n * 2 + 1);
                                for(var j = 0; j < n + 1; j++) {
                                    ctx[index]["k"][i].moveTo(Math.round(x), Math.round(y - splitHeight * i));
                                    x += xs;
                                    y += ys;
                                    ctx[index]["k"][i].lineTo(Math.round(x), Math.round(y - splitHeight * i));
                                    x += xs;
                                    y += ys;
                                }
                                break;
                            case "":
                            case "arcaea_s":
                                ctx[index]["k"][i].moveTo(Math.round(x1), Math.round(y1 - splitHeight * i));
                                ctx[index]["k"][i].lineTo(Math.round(x2), Math.round(y2 - splitHeight * i));
                                break;
                            case "arcaea_si":
                            case "arcaea_siso":
                            case "arcaea_sisi":
                                ctx[index]["k"][i].moveTo(Math.round(x1), Math.round(y1 - splitHeight * i));
                                ctx[index]["k"][i].quadraticCurveTo(Math.round(x2), Math.round(y1 + (y2 - y1) / 2 - splitHeight * i), Math.round(x2), Math.round(y2 - splitHeight * i));
                                break;
                            case "arcaea_so":
                            case "arcaea_soso":
                            case "arcaea_sosi":
                                ctx[index]["k"][i].moveTo(Math.round(x1), Math.round(y1 - splitHeight * i));
                                ctx[index]["k"][i].quadraticCurveTo(Math.round(x1), Math.round(y1 + (y2 - y1) / 2 - splitHeight * i), Math.round(x2), Math.round(y2 - splitHeight * i));
                                break;
                            case "arcaea_b":
                                ctx[index]["k"][i].moveTo(Math.round(x1), Math.round(y1 - splitHeight * i));
                                ctx[index]["k"][i].bezierCurveTo(Math.round(x1), Math.round(y1 + (y2 - y1) / 3 - splitHeight * i), Math.round(x2), Math.round(y1 + (y2 - y1) / 3 * 2 - splitHeight * i), Math.round(x2), Math.round(y2 - splitHeight * i));
                                break;
                        }
                        ctx[index]["k"][i].lineWidth = w;
                        ctx[index]["k"][i].strokeStyle = s;
                        ctx[index]["k"][i].stroke();
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
                        ctx[index]["k"][i].beginPath();
                        ctx[index]["k"][i].arc(Math.round(x), Math.round(y - splitHeight * i), r, 0, Math.PI * 2);
                        var cs = ctx[index]["k"][i].createRadialGradient(Math.round(x), Math.round(y - splitHeight * i), r / 8, Math.round(x), Math.round(y - splitHeight * i), r);
                        for(var j = 0; j < s.length; j++) {
                            cs.addColorStop(j / s.length, s[j]);
                        }
                        ctx[index]["k"][i].fillStyle = cs;
                        ctx[index]["k"][i].fill();
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
                        ctx[index]["k"][i].fillStyle = s;
                        ctx[index]["k"][i].font = SetFontSize(f, n);
                        ctx[index]["k"][i].textAlign = a;
                        ctx[index]["k"][i].fillText(t, Math.round(x), Math.round(y - splitHeight * i));
                    }
                };
                var drawTime = function (x, y, d, s, f, a) {
                    var n = 0 + (options["DrawTime"] ? 1 : 0) + (options["DrawBeat"] ? 1 : 0) + (options["DrawNote"] ? 1 : 0);
                    switch(a) {
                        case "left":
                            if(options["DrawNote"]) {
                                drawText(x, y, d["n"].toFormated(4).toString(), s, f, a);
                            }
                            if(options["DrawBeat"]) {
                                drawText(x + (options["DrawNote"] ? paddingLeft / n : 0), y, d["b"].toFormated(2).toString(), s, f, a);
                            }
                            if(options["DrawTime"]) {
                                drawText(x + (options["DrawNote"] ? paddingLeft / n : 0) + (options["DrawBeat"] ? paddingLeft / n : 0), y, d["t"].toFormated(0).toString(), s, f, a);
                            }
                            break;
                        case "right":
                            if(options["DrawNote"]) {
                                drawText(x, y, d["n"].toFormated(4).toString(), s, f, a);
                            }
                            if(options["DrawBeat"]) {
                                drawText(x - (options["DrawNote"] ? paddingLeft / n : 0), y, d["b"].toFormated(2).toString(), s, f, a);
                            }
                            if(options["DrawTime"]) {
                                drawText(x - (options["DrawNote"] ? paddingLeft / n : 0) - (options["DrawBeat"] ? paddingLeft / n : 0), y, d["t"].toFormated(0).toString(), s, f, a);
                            }
                            break;
                    }
                }
                var drawNote = function (a, x, y) {
                    var y2 = (arguments.length > 3 ? arguments[3] : y);
                    var s = (arguments.length > 4 ? arguments[4] : "");
                    var i1 = Math.floor((y2 - noteHeight / 2) / splitHeight);
                    var i2 = Math.floor((y + noteHeight / 2) / splitHeight);
                    if(i1 < 0) {
                        i1 = 0;
                    }
                    if(i2 > splitCount - 1) {
                        i2 = splitCount - 1;
                    }
                    if(s == "") {
                        switch(a) {
                            case 0x00:
                                s = touchNoteStyle;
                                break;
                            default:
                                s = holdNoteStyle;
                                break;
                        }
                    }
                    for(var i = i1; i < i2 + 1; i++) {
                        ctx[index]["k"][i].fillStyle = s;
                        ctx[index]["k"][i].fillRect(Math.round(x - noteWidth / 2), Math.round(y2 - splitHeight * i - noteHeight / 2), noteWidth, noteHeight + y - y2);
                    }
                };
                var drawStroke = function (x1, y1, x2, y2) {
                    var w = (arguments.length > 4 ? arguments[4] : strokeWidth);
                    var s = (arguments.length > 5 ? arguments[5] : holdNoteStyle);
                    var t = (arguments.length > 6 ? arguments[6] : "");
                    drawLine(x1, y1, x2, y2, w, s, t);
                };
                var drawArrow = function (t, x, y) {
                    var i1 = Math.floor((y - strokeWidth) / splitHeight);
                    var i2 = Math.floor((y + strokeWidth) / splitHeight);
                    i1 = (i1 < 0 ? 0 : i1);
                    i2 = (i2 > splitCount - 1 ? splitCount - 1 : i2);
                    for(var i = i1; i < i2 + 1; i++) {
                        ctx[index]["k"][i].beginPath();
                        if(t < 0) {
                            ctx[index]["k"][i].moveTo(Math.round(x + strokeWidth / 2), Math.round(y - splitHeight * i + strokeWidth));
                            ctx[index]["k"][i].lineTo(Math.round(x + strokeWidth / 2), Math.round(y - splitHeight * i - strokeWidth));
                            ctx[index]["k"][i].lineTo(Math.round(x + strokeWidth / 2 - strokeWidth * 2), Math.round(y - splitHeight * i));
                        } else {
                            ctx[index]["k"][i].moveTo(Math.round(x - strokeWidth / 2), Math.round(y - splitHeight * i + strokeWidth));
                            ctx[index]["k"][i].lineTo(Math.round(x - strokeWidth / 2), Math.round(y - splitHeight * i - strokeWidth));
                            ctx[index]["k"][i].lineTo(Math.round(x - strokeWidth / 2 + strokeWidth * 2), Math.round(y - splitHeight * i));
                        }
                        ctx[index]["k"][i].closePath();
                        ctx[index]["k"][i].lineWidth = 1;
                        ctx[index]["k"][i].fillStyle = arrowStyle;
                        ctx[index]["k"][i].fill();
                    }
                };
                var drawTurn = function (x, y) {
                    drawPoint(x, y, turnPointRadius, turnPointStyle);
                };
                var drawKey = function (noteData) {
                    var step = (arguments.length > 1 ? arguments[1] : 0);
                    var action = noteData["action"];
                    var time = noteData["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"];
                    var xPos = noteData["where"]["x"];
                    var xPos2 = noteData["where2"]["x"];
                    var yPos = noteData["where"]["y"];
                    var yPos2 = noteData["where2"]["y"];
                    var x = coreWidth * xPos + paddingLeft;
                    var x2 = coreWidth * xPos2 + paddingLeft;
                    var y = wholeHeight - time / speedRate - paddingBottom;
                    var xd = xPos2 - xPos;
                    var td = noteData["when2"]["t"] - noteData["when"]["t"];
                    switch(step) {
                        case 0:
                            switch(action) {
                                case 0x00:
                                    drawNote(action, x, y, y, draw["noteStyle"](xPos, key, action, ""));
                                    break;
                                case 0x01:
                                    drawNote(action, x, y);
                                    if(xd < 0) {
                                        drawStroke(coreWidth * xPos2 + paddingLeft + strokeWidth / 2, y, x - noteWidth / 2, y);
                                    } else if(xd > 0) {
                                        drawStroke(x + noteWidth / 2, y, coreWidth * xPos2 + paddingLeft - strokeWidth / 2, y);
                                    }
                                    drawArrow(xd * key, coreWidth * xPos2 + paddingLeft, y);
                                    break;
                                case 0x02:
                                    switch(type) {
                                        case "bms":
                                        case "osu":
                                        case "mc":
                                        case "aff":
                                            drawNote(action, x, y, y - td / speedRate, draw["noteStyle"](xPos, key, action, ""));
                                            break;
                                        case "imd":
                                            drawNote(action, x, y);
                                            drawStroke(x, y, x, y - td / speedRate);
                                            drawStroke(x - strokeWidth, y - td / speedRate, x + strokeWidth, y - td / speedRate);
                                            break;
                                    }
                                    break;
                                case 0x61:
                                    drawNote(action, x, y);
                                    if(xd < 0) {
                                        drawStroke(coreWidth * xPos2 + paddingLeft - strokeWidth / 2, y, x - noteWidth / 2, y);
                                    } else if(xd > 0) {
                                        drawStroke(x + noteWidth / 2, y, coreWidth * xPos2 + paddingLeft + strokeWidth / 2, y);
                                    }
                                    drawTurn(coreWidth * xPos2 + paddingLeft, y);
                                    break;
                                case 0x62:
                                    drawNote(action, x, y);
                                    drawStroke(x, y, x2, y - td / speedRate);
                                    break;
                                case 0x21:
                                    switch(type) {
                                        case "imd":
                                            if(xd < 0) {
                                                drawStroke(coreWidth * xPos2 + paddingLeft - strokeWidth / 2, y, x + strokeWidth / 2, y);
                                            } else if(xd > 0) {
                                                drawStroke(x - strokeWidth / 2, y, coreWidth * xPos2 + paddingLeft + strokeWidth / 2, y);
                                            }
                                            drawTurn(x, y);
                                            drawTurn(coreWidth * xPos2 + paddingLeft, y);
                                            break;
                                        case "aff":
                                            drawNote(action, x, y, y, draw["noteStyle"](xPos, key, action, "arcaea_shadow"));
                                            break;
                                    }
                                    break;
                                case 0x22:
                                    switch(type) {
                                        case "imd":
                                            drawStroke(x, y, x2, y - td / speedRate);
                                            break;
                                        case "aff":
                                            if(noteData["auto"] != "true") {
                                                drawStroke(x, wholeHeight - (time + 1) / speedRate - paddingBottom, x2, y - td / speedRate, noteWidth / 2, draw["noteStyle"](xPos, key, action, "arcaea_shadow"), noteData["line"]["curve"]);
                                            } else {
                                                drawStroke(x, wholeHeight - (time + 1) / speedRate - paddingBottom, x2, y - td / speedRate, 2, draw["noteStyle"](xPos, key, action, "arcaea_shadow"), noteData["line"]["curve"]);
                                            }
                                            break;
                                    }
                                    break;
                                case 0xA1:
                                    if(xd < 0) {
                                        drawStroke(coreWidth * xPos2 + paddingLeft + strokeWidth / 2, y, x + strokeWidth / 2, y);
                                    } else if(xd > 0) {
                                        drawStroke(x - strokeWidth / 2, y, coreWidth * xPos2 + paddingLeft - strokeWidth / 2, y);
                                    }
                                    drawTurn(x, y);
                                    drawArrow(xd * key, coreWidth * xPos2 + paddingLeft, y);
                                    break;
                                case 0xA2:
                                    drawStroke(x, y, x, y - td / speedRate);
                                    drawStroke(x - strokeWidth, y - td / speedRate, x + strokeWidth, y - td / speedRate);
                                    break;
                            }
                            break;
                        case 1:
                            switch(action) {
                                case 0x21:
                                    switch(type) {
                                        case "aff":
                                            drawNote(action, x, y - yPos * frameY, y - yPos * frameY, draw["noteStyle"](xPos, key, action, ""));
                                            break;
                                    }
                                    break;
                                case 0x22:
                                    switch(type) {
                                        case "aff":
                                            if(noteData["auto"] != "true") {
                                                drawStroke(x, wholeHeight - (time + 1) / speedRate - paddingBottom - yPos * frameY, x2, y - td / speedRate - yPos2 * frameY, noteWidth / 2, draw["noteStyle"](xPos, key, action, noteData["group"]), noteData["line"]["curve"]);
                                            } else {
                                                drawStroke(x, wholeHeight - (time + 1) / speedRate - paddingBottom - yPos * frameY, x2, y - td / speedRate - yPos2 * frameY, 2, draw["noteStyle"](xPos, key, action, "arcaea_dark"), noteData["line"]["curve"]);
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                };
                if(options["DrawBackground"]) {
                    for(var i = 0; i < splitCount; i++) {
                        drawRect(0, 0, cvs[index]["k"][i].width, cvs[index]["k"][i].height, backgroundStyle);
                    }
                }
                for(var i = 0; i < draw["unflowAera"].length; i++) {
                    var style = (function () {
                        var u = Math.floor(Semi["data"][index]["info"]["bpm"] / 2);
                        var v = parseInt(stopAeraStyle.substring(1), 16);
                        var r = v % 256;
                        v -= r * 256 * 256;
                        var g = v % 256;
                        v -= g * 256;
                        var b = v % 256;
                        v -= b;
                        var f = Math.abs(draw["unflowAera"][i]["flow"]);
                        if(f > u / 2) {
                            f -= u / 2;
                            r = 255;
                        } else {
                            r += Math.floor((255 - r) * (f / (u / 2)));
                            f = 0;
                        }
                        if(f > u / 2) {
                            b = 255;
                        } else {
                            f = 0;
                            b += Math.floor((255 - b) * (f / (u / 2)));
                        }
                        v = "#" + new Uint8Array([r, g, b]).getHex();
                        return v;
                    })();
                    drawRect(1 + paddingLeft, wholeHeight - (draw["unflowAera"][i]["when2"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - beatLineWidth / 2 - paddingBottom, wholeWidth - 1 - paddingLeft, wholeHeight - (draw["unflowAera"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - beatLineWidth / 2 - paddingBottom, style);
                }
                if(options["DrawTrack"]) {
                    for(var i = 0; i < key + 1; i++) {
                        drawLine(coreWidth / key * i + paddingLeft, wholeHeight - paddingBottom, coreWidth / key * i + paddingLeft, paddingTop, trackLineWidth, trackLineStyle, "");
                        drawLine(coreWidth / key * i + paddingLeft, wholeHeight - paddingBottom, coreWidth / key * i + paddingLeft, paddingTop, trackLineWidth, trackLineStyle, "");
                    }
                }
                if(options["DrawFrame"]) {
                    drawLine(0, 0, 0, wholeHeight, frameStokeWidth, frameStokeStyle, "");
                    drawLine(wholeWidth, 0, wholeWidth, wholeHeight, frameStokeWidth, frameStokeStyle, "");
                    drawLine(1, 0, wholeWidth - 1, 0, frameStokeWidth, frameStokeStyle, "");
                    drawLine(1, (wholeHeight % 2 != 0 ? wholeHeight - 1 : wholeHeight), wholeWidth - 1, (wholeHeight % 2 != 0 ? wholeHeight - 1 : wholeHeight), frameStokeWidth, frameStokeStyle, "");
                }
                if(options["DrawBeatline"]) {
                    for(var i = 0; i < draw["beatLines"]["w"].length; i++) {
                        if((draw["beatLines"]["w"][i]["when"]["t"] != Semi["data"][index]["info"]["min"]["when"]["t"]) && (draw["beatLines"]["w"][i]["when"]["t"] != Semi["data"][index]["info"]["max"]["when"]["t"]) && (draw["beatLines"]["w"][i]["when"]["t"] != Semi["data"][index]["info"]["length"]["when"]["t"])) {
                            drawLine(1, wholeHeight - (draw["beatLines"]["w"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - beatLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (draw["beatLines"]["w"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - beatLineWidth / 2 - paddingBottom, (i % 4 == 0 ? beatLineWidth * 2 : beatLineWidth), beatLineStyle, "");
                            drawTime(1, wholeHeight - 1 - (draw["beatLines"]["w"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - beatLineWidth / 2 - paddingBottom, draw["beatLines"]["w"][i]["when"], beatLineStyle, textFont, "left");
                        }
                    }
                    for(var i = 0; i < draw["beatLines"]["h"].length; i++) {
                        if((draw["beatLines"]["h"][i]["when"]["t"] != Semi["data"][index]["info"]["min"]["when"]["t"]) && (draw["beatLines"]["h"][i]["when"]["t"] != Semi["data"][index]["info"]["max"]["when"]["t"]) && (draw["beatLines"]["h"][i]["when"]["t"] != Semi["data"][index]["info"]["length"]["when"]["t"])) {
                            drawLine(1, wholeHeight - (draw["beatLines"]["h"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - beatLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (draw["beatLines"]["h"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - beatLineWidth / 2 - paddingBottom, beatLineWidth, beatLineStyle, "dot_2");
                            drawTime(1, wholeHeight - 1 - (draw["beatLines"]["h"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - beatLineWidth / 2 - paddingBottom, draw["beatLines"]["h"][i]["when"], beatLineStyle, textFont, "left");
                        }
                    }
                    for(var i = 0; i < draw["beatLines"]["q"].length; i++) {
                        if((draw["beatLines"]["q"][i]["when"]["t"] != Semi["data"][index]["info"]["min"]["when"]["t"]) && (draw["beatLines"]["q"][i]["when"]["t"] != Semi["data"][index]["info"]["max"]["when"]["t"]) && (draw["beatLines"]["q"][i]["when"]["t"] != Semi["data"][index]["info"]["length"]["when"]["t"])) {
                            drawLine(1, wholeHeight - (draw["beatLines"]["q"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - beatLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (draw["beatLines"]["q"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - beatLineWidth / 2 - paddingBottom, beatLineWidth, beatLineStyle, "dot_4");
                            drawTime(1, wholeHeight - 1 - (draw["beatLines"]["q"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - beatLineWidth / 2 - paddingBottom, draw["beatLines"]["q"][i]["when"], beatLineStyle, textFont, "left");
                        }
                    }
                    drawLine(1, wholeHeight - (Semi["data"][index]["info"]["min"]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - startLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (Semi["data"][index]["info"]["min"]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - startLineWidth / 2 - paddingBottom, beatLineWidth * 2, startLineStyle, "");
                    drawTime(1, wholeHeight - 1 - (Semi["data"][index]["info"]["min"]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - startLineWidth / 2 - paddingBottom, Semi["data"][index]["info"]["min"]["when"], startLineStyle, textFont, "left");
                    drawLine(1, wholeHeight - (Semi["data"][index]["info"]["max"]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - startLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (Semi["data"][index]["info"]["max"]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - startLineWidth / 2 - paddingBottom, beatLineWidth * 2, startLineStyle, "");
                    drawTime(1, wholeHeight - 1 - (Semi["data"][index]["info"]["max"]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - startLineWidth / 2 - paddingBottom, Semi["data"][index]["info"]["max"]["when"], startLineStyle, textFont, "left");
                    if(Semi["data"][index]["info"]["length"]["when"]["t"] != Semi["data"][index]["info"]["max"]["when"]["t"]) {
                        drawLine(1, wholeHeight - (Semi["data"][index]["info"]["length"]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - lengthLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (Semi["data"][index]["info"]["length"]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - lengthLineWidth / 2 - paddingBottom, beatLineWidth * 4, lengthLineStyle, "");
                        drawTime(1, wholeHeight - 1 - (Semi["data"][index]["info"]["length"]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - lengthLineWidth / 2 - paddingBottom, Semi["data"][index]["info"]["length"]["when"], lengthLineStyle, textFont, "left");
                    }
                }
                for(var i = 0; i < draw["noteData"].length; i++) {
                    drawKey(draw["noteData"][i], 0);
                }
                for(var i = 0; i < draw["noteData"].length; i++) {
                    drawKey(draw["noteData"][i], 1);
                }
                if(options["DrawDetermine"]) {
                    for(var i = 0; i < draw["determineLines"].length; i++) {
                        drawLine(1, wholeHeight - (draw["determineLines"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - determineLineWidth / 2 - paddingBottom, wholeWidth - 1, wholeHeight - (draw["determineLines"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - determineLineWidth / 2 - paddingBottom, determineLineWidth, determineLineStyle, "");
                        drawTime(wholeWidth - 1, wholeHeight - 1 - (draw["determineLines"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - determineLineWidth / 2 - paddingBottom, draw["determineLines"][i]["when"], determineLineStyle, textFont, "right");
                    }
                }
                if(options["DrawCombo"]) {
                    for(var i = 0; i < draw["comboPoints"].length; i++) {
                        drawPoint(coreWidth * draw["comboPoints"][i]["where"]["x"] + paddingLeft, wholeHeight - (draw["comboPoints"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - paddingBottom, determinePointRadius, determinePointStyle);
                        drawText(coreWidth * draw["comboPoints"][i]["where"]["x"] + paddingLeft, wholeHeight - (draw["comboPoints"][i]["when"]["t"] - Semi["data"][index]["info"]["min"]["when"]["t"]) / speedRate - paddingBottom - noteHeight / 2, (i + 1).toString(), determineTextStyle, textFont, "center");
                    }
                }
                var drawInfo = (function () {
                    var wL = wholeWidth / 2 - coreWidth * 0.618;
                    var wR = wholeWidth / 2 + coreWidth * 0.124;
                    drawText(wholeWidth / 2, 18 * scaleX, info.filename + (Semi["data"].length > 1 ? " (" + (Semi["data"][index]["info"]["name"] || index) + ")" : ""), textStyle, boldTextFont, "center");
                    drawText(wL, 38 * scaleX, "时间：" + MillisecondToTime(Semi["data"][index]["info"]["length"]["when"]["t"]).toString(), textStyle, textFont, "left");
                    drawText(wR, 38 * scaleX, "动作：" + draw["noteData"].length.toString(), textStyle, textFont, "left");
                    drawText(wL, 55 * scaleX, "拍速：" + Semi["data"][index]["info"]["bpm"].toString(), textStyle, textFont, "left");
                    drawText(wR, 55 * scaleX, "连击：" + draw["comboPoints"].length.toString(), textStyle, textFont, "left");
                    drawText(wL, 72 * scaleX, "节拍：" + Semi["data"][index]["info"]["max"]["when"]["b"].toString(), textStyle, textFont, "left");
                    drawText(wR, 72 * scaleX, "满分：" + draw["fullScore"].toString(), textStyle, textFont, "left");
                    drawText(wL, 89 * scaleX, "速率：" + scaleY.toFormated(3).toString(), textStyle, textFont, "left");
                    drawText(wR, 89 * scaleX, "键位：" + key.toString(), textStyle, textFont, "left");
                    drawText(wL, 106 * scaleX, "密度：" + draw["density"].toFormated(3).toString(), textStyle, textFont, "left");
                    drawText(wR, 106 * scaleX, "量级：" + draw["quantity"].toFormated(3).toString(), textStyle, textFont, "left");
                    drawText(wholeWidth / 2, wholeHeight - 48 * scaleX, "Draw by rmstZ.html", textStyle, textFont, "center");
                    drawText(wholeWidth / 2, wholeHeight - 30 * scaleX, "on " + new Date().format(), textStyle, textFont, "center");
                    drawText(wholeWidth / 2, wholeHeight - 10 * scaleX, "Copyright © 心のsky Group", textStyle, boldTextFont, "center");
                })();
                var maxChannel = (function () {
                    var v = 0;
                    for(var i = 0; i < Semi["data"][index]["effect"].length; i++) {
                        var l = getFileIndex(Semi["data"][index]["effect"][i]["name"]);
                        if(l != -1) {
                            if(v < Semi["file"][l]["pcm"]["channelData"].length) {
                                v = Semi["file"][l]["pcm"]["channelData"].length;
                            }
                        }
                    }
                    return v;
                })();
                var drawChannel = Math.ceil(maxChannel / 2) * 2;
                var stepRate = 12;
                var channelWidth = frameX;
                var waveLineWidth = 0.24 * scaleX;
                var isDrawLeft = (isSameAudio && (index == indexes[0])) || !isSameAudio;
                var isDrawRight = (isSameAudio && (index == indexes[indexes.length - 1])) || !isSameAudio;
                var aeroStyle = (function () {
                    var v = [];
                    for(var s in Aero) {
                        v.push(Aero[s]);
                    }
                    return v;
                })();
                for(var i = 0; i < splitCount; i++) {
                    cvs[index]["sL"][i] = document.createElement("canvas");
                    cvs[index]["sL"][i].style.display = "none";
                    cvs[index]["sL"][i].width = (isDrawLeft ? drawChannel / 2 * channelWidth : 0);
                    cvs[index]["sL"][i].height = cvs[index]["k"][i].height;
                    ctx[index]["sL"][i] = cvs[index]["sL"][i].getContext("2d");
                    ctx[index]["sL"][i].globalAlpha = drawAlpha;
                    cvs[index]["sR"][i] = document.createElement("canvas");
                    cvs[index]["sR"][i].style.display = "none";
                    cvs[index]["sR"][i].width = (isDrawRight ? drawChannel / 2 * channelWidth : 0);
                    cvs[index]["sR"][i].height = cvs[index]["k"][i].height;
                    ctx[index]["sR"][i] = cvs[index]["sR"][i].getContext("2d");
                    ctx[index]["sR"][i].globalAlpha = drawAlpha;
                }
                var drawLink = function (m, x1, y1, x2, y2, w, s) {
                    var i1 = 0;
                    var i2 = 0;
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
                    i1 = (i1 < 0 ? 0 : i1);
                    i2 = (i2 > splitCount - 1 ? splitCount - 1 : i2);
                    for(var i = i1; i < i2 + 1; i++) {
                        ctx[index][m][i].beginPath();
                        ctx[index][m][i].moveTo(Math.round(x1), Math.round(y1 - splitHeight * i));
                        ctx[index][m][i].lineTo(Math.round(x2), Math.round(y2 - splitHeight * i));
                        ctx[index][m][i].lineWidth = w;
                        ctx[index][m][i].strokeStyle = s;
                        ctx[index][m][i].stroke();
                    }
                };
                for(var i = 0; i < Semi["data"][index]["effect"].length; i++) {
                    var l = getFileIndex(Semi["data"][index]["effect"][i]["name"]);
                    if(l != -1) {
                        for(var j = 0; j < Semi["file"][l]["pcm"]["channelData"].length; j++) {
                            for(var k = stepRate; k < Semi["file"][l]["pcm"]["channelData"][j].length; k += stepRate) {
                                if(j < drawChannel / 2) {
                                    if(isDrawLeft) {
                                        drawLink("sL", ((1 - Semi["file"][l]["pcm"]["channelData"][j][k - stepRate]) / 2 + j) * channelWidth, wholeHeight - ((k - stepRate) / Semi["file"][l]["pcm"]["sampleRate"] * 1000) / speedRate - paddingBottom, ((1 - Semi["file"][l]["pcm"]["channelData"][j][k]) / 2 + j) * channelWidth, wholeHeight - (k / Semi["file"][l]["pcm"]["sampleRate"] * 1000) / speedRate - paddingBottom, waveLineWidth, aeroStyle[j % 16]);
                                    }
                                } else {
                                    if(isDrawRight) {
                                        drawLink("sR", 1 + ((1 - Semi["file"][l]["pcm"]["channelData"][j][k - stepRate]) / 2 + j - drawChannel / 2) * channelWidth, wholeHeight - ((k - stepRate) / Semi["file"][l]["pcm"]["sampleRate"] * 1000) / speedRate - paddingBottom, 1 + ((1 - Semi["file"][l]["pcm"]["channelData"][j][k]) / 2 + j - drawChannel / 2) * channelWidth, wholeHeight - (k / Semi["file"][l]["pcm"]["sampleRate"] * 1000) / speedRate - paddingBottom, waveLineWidth, aeroStyle[j % 16]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        var cvs = (function () {
            var maxWidth = (function () {
                var v = 0;
                for(var index = 0; index < cvs.length; index++) {
                    if(indexes.indexOf(index) != -1) {
                        v += cvs[index]["sL"][0].width;
                        v += cvs[index]["k"][0].width;
                        v += cvs[index]["sR"][0].width;
                    }
                }
                return v;
            })();
            var canvas = [];
            var context = [];
            var l = -1;
            for(var index = 0; index < cvs.length; index++) {
                if(indexes.indexOf(index) != -1) {
                    l = index;
                    break;
                }
            }
            for(var i = 0; i < cvs[l]["k"].length; i++) {
                canvas[i] = document.createElement("canvas");
                canvas[i].style.display = "none";
                canvas[i].width = maxWidth;
                canvas[i].height = cvs[l]["k"][i].height;
                context[i] = canvas[i].getContext("2d");
            }
            var w = 0;
            for(var index = 0; index < cvs.length; index++) {
                if(indexes.indexOf(index) != -1) {
                    for(var i = 0; i < cvs[index]["sL"].length; i++) {
                        context[i].drawImage(cvs[index]["sL"][i], w, 0);
                    }
                    w += cvs[index]["sL"][0].width;
                    for(var i = 0; i < cvs[index]["k"].length; i++) {
                        context[i].drawImage(cvs[index]["k"][i], w, 0);
                    }
                    w += cvs[index]["k"][0].width;
                    for(var i = 0; i < cvs[index]["sR"].length; i++) {
                        context[i].drawImage(cvs[index]["sR"][i], w, 0);
                    }
                    w += cvs[index]["sR"][0].width;
                }
            }
            return canvas;
        })();
        var png = [];
        for(var i = 0; i < splitCount; i++) {
            png[i] = cvs[i].toDataURL("image/png");
        }
        return png.join("|");
    };
    var toHtmlText = function (indexes, type) {
        var png = toPngText(indexes, type, GetMstOption(true)).split("|");
        var html = [];
        html.push("<html><head><meta name=" + '"' + "viewport" + '"' + "content=" + '"' + "width=device-width,initial-scale=1,user-scalable=yes" + '"' + "/><title>" + info.nameCore + (ext == "imd" ? info.suffixImd : "") + (ext == "mde" ? info.suffixMde : "") + "." + info.extension + "</title><style type=" + '"' + "text/css" + '"' + ">div{text-align:center;}</style></head><body>");
        for(var i = 0; i < png.length; i++) {
            html.push("<div><img src=" + '"' + png[i] + '"' + "/></div>");
        }
        html.push("</body></html>");
        return html.join("");
    };
    this.PreData = preData;
    this.ToBuffer = function (indexes, ext) {
        var type = (arguments.length > 2 ? arguments[2] : "");
        if(indexes.length == 0) {
            return new Uint8Array();
        }
        var index = indexes[0];
        var list = indexes.join(",");
        switch(ext) {
            case "bms":
                Buffer["Bms"][index] = Buffer["Bms"][index] || toBmsBuffer(index);
                return Buffer["Bms"][index];
                break;
            case "bme":
                Buffer["Bme"][index] = Buffer["Bme"][index] || toBmeBuffer(index);
                return Buffer["Bme"][index];
                break;
            case "bml":
                Buffer["Bml"][index] = Buffer["Bml"][index] || toBmlBuffer(index);
                return Buffer["Bml"][index];
                break;
            case "pms":
                Buffer["Pms"][index] = Buffer["Pms"][index] || toPmsBuffer(index);
                return Buffer["Pms"][index];
                break;
            case "vos":
                switch(type) {
                    case "vos000":
                        Buffer["Vosvos000"][index] = Buffer["Vosvos000"][index] || toVosBuffer(index, type);
                        return Buffer["Vosvos000"][index];
                        break;
                    case "vos001":
                        Buffer["Vosvos001"][index] = Buffer["Vosvos001"][index] || toVosBuffer(index, type);
                        return Buffer["Vosvos001"][index];
                        break;
                    case "vos006":
                        Buffer["Vosvos006"][index] = Buffer["Vosvos006"][index] || toVosBuffer(index, type);
                        return Buffer["Vosvos006"][index];
                        break;
                    case "vos022":
                        Buffer["Vosvos022"][index] = Buffer["Vosvos022"][index] || toVosBuffer(index, type);
                        return Buffer["Vosvos022"][index];
                        break;
                }
                break;
            case "mid":
                Buffer["Mid"][index] = Buffer["Mid"][index] || toMidBuffer();
                return Buffer["Mid"][index];
                break;
            case "lrc":
                Buffer["Lrc"][index] = Buffer["Lrc"][index] || toLrcBuffer();
                return Buffer["Lrc"][index];
                break;
            case "hex":
                switch(type) {
                    case "vos000":
                        Buffer["Hexvosvos000"][index] = Buffer["Hexvosvos000"][index] || toHexBuffer(index, type);
                        return Buffer["Hexvos000"][index];
                        break;
                    case "vos001":
                        Buffer["Hexvos001"][index] = Buffer["Hexvos001"][index] || toHexBuffer(index, type);
                        return Buffer["Hexvos001"][index];
                        break;
                    case "vos006":
                        Buffer["Hexvos006"][index] = Buffer["Hexvos006"][index] || toHexBuffer(index, type);
                        return Buffer["Hexvos006"][index];
                        break;
                    case "vos022":
                        Buffer["Hexvos022"][index] = Buffer["Hexvos022"][index] || toHexBuffer(index, type);
                        return Buffer["Hexvos022"][index];
                        break;
                    case "imd":
                        Buffer["Heximd"][index] = Buffer["Heximd"][index] || toHexBuffer(index, "imd");
                        return Buffer["Heximd"][index];
                        break;
                }
                break;
            case "tja":
                switch(type) {
                    case "taiko":
                    case "jube":
                        Buffer["Tja" + type][list] = Buffer["Tja" + type][list] || toTjaBuffer(indexes, type);
                        return Buffer["Tja" + type][list];
                        break;
                }
                break;
            case "osu":
                switch(type) {
                    case "osu":
                    case "taiko":
                    case "ctb":
                    case "mania":
                        Buffer["Osu" + type][index] = Buffer["Osu" + type][index] || toOsuBuffer(index, type);
                        return Buffer["Osu" + type][index];
                        break;
                }
                break;
            case "xml":
                switch(type) {
                    case "yddr":
                    case "ydsd":
                    case "mde":
                        Buffer["Xml" + type][index] = Buffer["Xml" + type][index] || toXmlBuffer(index, type);
                        return Buffer["Xml" + type][index];
                        break;
                }
                break;
            case "vox":
                Buffer["Vox"][index] = Buffer["Vox"][index] || toVoxBuffer(index);
                return Buffer["Vox"][index];
                break;
            case "ksh":
                Buffer["Ksh"][index] = Buffer["Ksh"][index] || toKshBuffer(index);
                return Buffer["Ksh"][index];
                break;
            case "imd":
                Buffer["Imd"][index] = Buffer["Imd"][index] || toImdBuffer(index);
                return Buffer["Imd"][index];
                break;
            case "mde":
                Buffer["Mde"][index] = Buffer["Mde"][index] || toMdeBuffer(index);
                return Buffer["Mde"][index];
                break;
            case "mc":
                switch(type) {
                    case "key":
                    case "step":
                    case "dj":
                    case "catch":
                    case "pad":
                    case "taiko":
                        Buffer["Mc" + type][index] = Buffer["Mc" + type][index] || toMcBuffer(index, type);
                        return Buffer["Mc" + type][index];
                        break;
                }
                break;
            case "aff":
                Buffer["Aff"][index] = Buffer["Aff"][index] || toAffBuffer(index);
                return Buffer["Aff"][index];
                break;
            case "mst":
                Buffer["Mst"][list] = Buffer["Mst"][list] || toMstBuffer(indexes);
                return Buffer["Mst"][list];
                break;
        }
    };
    this.ToText = function (indexes, ext) {
        var type = (arguments.length > 2 ? arguments[2] : "");
        if(indexes.length == 0) {
            return new Uint8Array();
        }
        var index = indexes[0];
        var list = indexes.join(",");
        switch(ext) {
            case "bms":
                Text["Bms"][index] = Text["Bms"][index] || toBmsText(index);
                return Text["Bms"][index];
                break
            case "bme":
                Text["Bme"][index] = Text["Bme"][index] || toBmeText(index);
                return Text["Bme"][index];
                break;
            case "bml":
                Text["Bml"][index] = Text["Bml"][index] || toBmlText(index);
                return Text["Bml"][index];
                break;
            case "pms":
                Text["Pms"][index] = Text["Pms"][index] || toPmsText(index);
                return Text["Pms"][index];
                break;
            case "vos":
                switch(type) {
                    case "vos000":
                        Text["Vosvos000"] = Text["Vosvos000"] || toVosText(type);
                        return Text["Vosvos000"];
                        break;
                    case "vos001":
                        Text["Vosvos001"] = Text["Vosvos001"] || toVosText(type);
                        return Text["Vosvos001"];
                        break;
                    case "vos006":
                        Text["Vosvos006"] = Text["Vosvos006"] || toVosText(type);
                        return Text["Vosvos006"];
                        break;
                    case "vos022":
                        Text["Vosvos022"] = Text["Vosvos022"] || toVosText(type);
                        return Text["Vosvos022"];
                        break;
                }
                break;
            case "mid":
                Text["Mid"] = Text["Mid"] || toMidText();
                return Text["Mid"];
                break;
            case "lrc":
                Text["Lrc"] = Text["Lrc"] || toLrcText();
                return Text["Lrc"];
                break;
            case "hex":
                switch(type) {
                    case "vos000":
                        Text["Hexvosvos000"] = Text["Hexvosvos000"] || toHexText(index, type);
                        return Text["Hexvosvos000"];
                        break;
                    case "vos001":
                        Text["Hexvosvos001"] = Text["Hexvosvos001"] || toHexText(index, type);
                        return Text["Hexvosvos001"];
                        break;
                    case "vos006":
                        Text["Hexvosvos006"] = Text["Hexvosvos006"] || toHexText(index, type);
                        return Text["Hexvosvos006"];
                        break;
                    case "vos022":
                        Text["Hexvosvos022"] = Text["Hexvosvos022"] || toHexText(index, type);
                        return Text["Hexvosvos022"];
                        break;
                    case "imd":
                        Text["Heximd"][index] = Text["Heximd"][index] || toHexText(index, "imd");
                        return Text["Heximd"][index];
                        break;
                }
                break;
            case "tja":
                switch(type) {
                    case "taiko":
                    case "jube":
                        Text["Tja" + type][list] = Text["Tja" + type][list] || toTjaText(indexes, type);
                        return Text["Tja" + type][list];
                        break;
                }
                break;
            case "osu":
                switch(type) {
                    case "osu":
                    case "taiko":
                    case "ctb":
                    case "mania":
                        Text["Osu" + type][index] = Text["Osu" + type][index] || toOsuText(index, type);
                        return Text["Osu" + type][index];
                        break;
                }
                break;
            case "xml":
                switch(type) {
                    case "yddr":
                    case "ydsd":
                    case "mde":
                        Text["Xml" + type][index] = Text["Xml" + type][index] || toXmlText(index, type);
                        return Text["Xml" + type][index];
                        break;
                }
                break;
            case "vox":
                Text["Vox"][index] = Text["Vox"][index] || toVoxText(index);
                return Text["Vox"][index];
                break;
            case "ksh":
                Text["Ksh"][index] = Text["Ksh"][index] || toKshText(index);
                return Text["Ksh"][index];
                break;
            case "imd":
                Text["Imd"][index] = Text["Imd"][index] || toImdText(index);
                return Text["Imd"][index];
                break;
            case "mde":
                Text["Mde"][index] = Text["Mde"][index] || toMdeText(index);
                return Text["Mde"][index];
                break;
            case "mc":
                switch(type) {
                    case "key":
                    case "step":
                    case "dj":
                    case "catch":
                    case "pad":
                    case "taiko":
                        Text["Mc" + type][index] = Text["Mc" + type][index] || toMcText(index, type);
                        return Text["Mc" + type][index];
                        break;
                }
                break;
            case "aff":
                Text["Aff"][index] = Text["Aff"][index] || toAffText(index);
                return Text["Aff"][index];
                break;
            case "mst":
                Text["Mst"][list] = Text["Mst"][list] || toMstText(indexes);
                return Text["Mst"][list];
                break;
            case "png":
                switch(type) {
                    case "bms":
                    case "osu":
                    case "imd":
                    case "mc":
                    case "aff":
                        Text["Png" + type][list] = Text["Png" + type][list] || toPngText(indexes, type);
                        return Text["Png" + type][list];
                        break;
                }
                break;
            case "html":
                switch(type) {
                    case "bms":
                    case "osu":
                    case "imd":
                    case "mc":
                    case "aff":
                        Text["Html" + type][list] = Text["Html" + type][list] || toHtmlText(indexes, type);
                        return Text["Html" + type][list];
                        break;
                }
                break;
        }
    };
    this.IsValid = function () {
        return isValid;
    };
    this.IsExist = function (ext) {
        return false;
        switch(ext) {
            case "vos":
                Buffer["Vosvos000"] = Buffer["Vosvos000"] || toVosBuffer();
                if(!isUndefined(Buffer["Vosvos000"])) {
                    return true;
                }
                Buffer["Vosvos001"] = Buffer["Vosvos001"] || toVosBuffer();
                if(!isUndefined(Buffer["Vosvos001"])) {
                    return true;
                }
                Buffer["Vosvos006"] = Buffer["Vosvos006"] || toVosBuffer();
                if(!isUndefined(Buffer["Vosvos006"])) {
                    return true;
                }
                Buffer["Vosvos022"] = Buffer["Vosvos022"] || toVosBuffer();
                if(!isUndefined(Buffer["Vosvos022"])) {
                    return true;
                }
                return false;
                break;
            case "mid":
                Buffer["Mid"] = Buffer["Mid"] || toMidBuffer();
                return !isUndefined(Buffer["Mid"]);
            case "lrc":
                Buffer["Lrc"] = Buffer["Lrc"] || toLrcBuffer();
                return !isUndefined(Buffer["Lrc"]);
                break;
        }
    };
    this.GetValue = function (name) {
        switch(name) {
            case "list":
                var v = [];
                for(var index = 0; index < Semi["data"].length; index++) {
                    v.push(Semi["data"][index]["info"]["name"] || index);
                }
                return v;
        }
    }
    this.Reset = function (m) {
        reset(m);
    };
    (function () {
        if(buffer.length == 0) {
            return;
        }
        reset();
        switch(ext) {
            case "bms":
                fromBmsBuffer(buffer);
                break;
            case "bme":
                fromBmeBuffer(buffer);
                break;
            case "bml":
                fromBmlBuffer(buffer);
                break;
            case "pms":
                fromPmsBuffer(buffer);
                break;
            case "vos":
                fromVosBuffer(buffer);
                break;
            case "hex":
                fromHexBuffer(buffer, type);
                break;
            case "tja":
                fromTjaBuffer(buffer);
                break;
            case "osu":
                fromOsuBuffer(buffer);
                break;
            case "xml":
                fromXmlBuffer(buffer, type);
                break;
            case "vox":
                fromVoxBuffer(buffer);
                break;
            case "ksh":
                fromKshBuffer(buffer);
                break;
            case "imd":
                fromImdBuffer(buffer);
                break;
            case "mde":
                fromMdeBuffer(buffer);
                break;
            case "mc":
                fromMcBuffer(buffer);
                break;
            case "aff":
                fromAffBuffer(buffer);
                break;
            case "mst":
                fromMstBuffer(buffer);
                break;
            case "wav":
            case "ogg":
            case "mp3":
            case "aac":
            case "m4a":
            case "m4r":
            case "ape":
                fromAudioBuffer(buffer, ext);
        }
    })();
}
function Bin(info) {
    var buffer = info.buffer;
    var ext = info.extension;
    var isValid;
    var Buffer;
    var Text;
    var reset = function () {
        isValid = false;
        Buffer = {};
        Buffer["Bin"] = undefined;
        Buffer["Hex"] = undefined;
        Buffer["Xml"] = undefined;
        Buffer["List"] = undefined;
        Buffer["Bat"] = undefined;
        Text = {};
        Text["Bin"] = undefined;
        Text["Hex"] = undefined;
        Text["Xml"] = undefined;
        Text["Html"] = undefined;
        Text["List"] = undefined;
        Text["Bat"] = undefined;
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
        switch(info.nameCore) {
            case "errno_main_client":
                r.push([0, "m_szName", 48]);
                r.push([0, "m_uiValue", 4]);
                r.push([0, "m_szDescriptionZH", 256]);
                r.push([0, "m_szDescriptionEN", 256]);
                r = ["ErrnoConfig_Client_Tab", "ErrnoConfig_Client", r];
                break;
            case "mrock.active_client":
                r.push([0, "m_ushActiveID", 2]);
                r.push([0, "m_ucActiveType", 1]);
                r.push([0, "m_iNumber", 4]);
                r.push([0, "m_ucRewardNumber", 1]);
                r.push([1, "m_astReward,ActiveReward", 3]);
                r = ["ActiveConfig_Client_Tab", "ActiveConfig_Client", r];
                break;
            case "mrock.character_client":
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
            case "mrock.characterproperty_client":
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
            case "mrock.floornode_client":
                r.push([0, "m_iNodeID", 4]);
                r.push([0, "m_iNodeType", 4]);
                r.push([0, "m_iNodeValue", 4]);
                r.push([0, "m_iPlayStyle", 4]);
                r = ["FloorNodeConfig_Client_Tab", "FloorNodeConfig_Client", r];
                break;
            case "mrock.floorreward_client":
                r.push([0, "m_iRewardID", 4]);
                r.push([0, "m_ucRewardType", 1]);
                r.push([0, "m_iRewardValue", 4]);
                r = ["FloorRewardConfig_Client_Tab", "FloorRewardConfig_Client", r];
                break;
            case "mrock.innerpublicnotify_client":
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
            case "mrock.innerrace_client":
                r.push([0, "m_iMatchID", 4]);
                r.push([0, "m_iLevel", 4]);
                r.push([0, "m_iContentType", 4]);
                r.push([0, "m_szTitle", 300]);
                r.push([0, "m_szContent", 300]);
                r.push([0, "m_iPhonetype", 4]);
                r = ["InnerRaceConfig_Client_Tab", "InnerRaceConfig_Client", r];
                break;
            case "mrock.luckyturntablereward_client":
                break;
            case "mrock.luckyturntabletypeshow_client":
                break;
            case "mrock.marketact_client":
                r.push([0, "m_ushMarketActID", 2]);
                r.push([0, "m_szDescription", 128]);
                r.push([0, "m_ucRewardNumber", 1]);
                r.push([1, "m_astReward,MarketActReward", 3]);
                r = ["MarketActConfig_Client_Tab", "MarketActConfig_Client", r];
                break;
            case "mrock.mission_client":
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
            case "mrock.scorebuy_client":
                r.push([0, "m_iBuyID", 4]);
                r.push([0, "m_ucItemType", 1]);
                r.push([0, "m_iItemID", 4]);
                r.push([0, "m_iItemValue", 4]);
                r.push([0, "m_iScorePresent", 4]);
                r.push([0, "m_ucBuyType", 1]);
                r.push([0, "m_iCostNum", 4]);
                r = ["ScoreBuyConfig_Client_Tab", "ScoreBuyConfig_Client", r];
                break;
            case "mrock.scoreexchange_client":
            case "mrock_scoreexchange_client":
                r.push([0, "m_iExchangeID", 4]);
                r.push([0, "m_ucItemType", 1]);
                r.push([0, "m_iItemID", 4]);
                r.push([0, "m_iItemValue", 4]);
                r.push([0, "m_iScoreCost", 4]);
                r.push([0, "m_iVersion", 4]);
                r = ["ScoreExchangeConfig_Client_Tab", "ScoreExchangeConfig_Client", r];
                break;
            case "mrock.surviveact_client":
                r.push([0, "m_iID", 4]);
                r.push([0, "m_ushSongID", 2]);
                r.push([0, "m_ucNoteType", 1]);
                r.push([0, "m_szOpenTimeDesc", 256]);
                r = ["SurviveActConfig_Client_Tab", "SurviveActConfig_Client", r];
                break;
            case "mrock.thingbuy_client":
                break;
            case "mrock.timelimitcharacter_client":
                r.push([0, "m_ushID", 2]);
                r.push([0, "m_ushCharacterID", 2]);
                r.push([0, "m_ushInitLevel", 2]);
                r.push([0, "m_szName", 22]);
                r.push([0, "m_szDesc", 256]);
                r = ["TimeLimitCharacterConfig_Client_Tab", "TimeLimitCharacterConfig_Client", r];
                break;
            case "mrock_buynew_client":
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
            case "mrock_dailyactive_client":
                break;
            case "mrock_guild_song_client":
                break;
            case "mrock_guildactivity_client":
                break;
            case "mrock_Map_client":
                break;
            case "mrock_match_client":
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
            case "mrock_match_division_client":
                r.push([0, "m_iMatchID", 4]);
                r.push([0, "m_szFilterSort1", 64]);
                r.push([0, "m_szFilterSort2", 64]);
                r.push([0, "m_szFilter1", 64]);
                r.push([0, "m_szFilter2", 64]);
                r = ["MatchDivisionConfig_Client_Tab", "MatchDivisionConfig_Client", r];
                break;
            case "mrock_medal_client":
                break;
            case "mrock_noble_client":
                break;
            case "mrock_noble_gift_client":
                break;
            case "mrock_papasong_client":
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
            case "mrock_questconfig_client":
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
            case "mrock_recharge_ios_client":
            case "mrock_recharge_android_client":
                break;
            case "mrock_room_client":
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
            case "mrock_share_client":
                break;
            case "mrock_song_client":
            case "mrock_song_client_android":
            case "mrock_songlevel_client":
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
            case "mrock_SongPkg_client":
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
            case "mrock_starmall_exchange_client":
                break;
            case "mrock_SysParam_client":
                r.push([0, "m_iParamID", 4]);
                r.push([0, "m_iParamValue", 4]);
                r = ["SysParamConfig_Client_Tab", "SysParamConfig_Client", r];
                break;
            case "mrock_txt_client":
                r.push([0, "m_uiID", 4]);
                r.push([0, "m_szDescriptionZH", 256]);
                r = ["TxtConfig_Client_Tab", "TxtConfig_Client", r];
                break;
        }
        return r;
    };
    var generateListBat = function () {
        if(!isUndefined(Buffer["Bin"]) || !isUndefined(Buffer["Xml"])) {
            switch(info.nameCore) {
                case "mrock_song_client":
                case "mrock_song_client_android":
                case "mrock_papasong_client":
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
        Text["Xml"] = Text["Xml"] || toXmlText();
        var x = Text["Xml"].replace(/\r\n/g, "");
        var readData = function (f1, f2) {
            var l1 = x.indexOf(f1, px);
            if(l1 == -1) {
                px = x.length;
                return "";
            } else {
                l1 += f1.length;
            }
            var l2 = x.indexOf(f2, l1);
            if(l2 == -1) {
                px = x.length;
                return "";
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
                c = "   0-00-00 00:00:00"
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
                    case "TResHeadAll":
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
                    case "m_astReward":
                        switch(t) {
                            case "ActiveReward":
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
                            case "MarketActReward":
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
                    case "m_astSongs":
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
        Text["Hex"] = Text["Hex"] || toHexText();
        return new Uint8Array().fromText(Text["Hex"]);
    };
    var toXmlBuffer = function () {
        Text["Xml"] = Text["Xml"] || toXmlText();
        return new Uint8Array().fromText(Text["Xml"]);
    };
    var toListBuffer = function () {
        Text["List"] = Text["List"] || toListText();
        return new Uint8Array().fromText(Text["List"]);
    };
    var toBatBuffer = function () {
        Text["Bat"] = Text["Bat"] || toBatText();
        return new Uint8Array().fromText(Text["Bat"], "ANSI");
    };
    var toBinText = function () {
        Buffer["Bin"] = Buffer["Bin"] || toBinBuffer();
        var writeHex = function (l) {
            hex.push(Buffer["Bin"].getHex(p, l, " "));
            p += l;
        };
        var writeHexE = function (f, t, n) {
            for(var i = 0; i < n; i++) {
                switch(f) {
                    case "TResHeadAll":
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
                    case "m_astReward":
                        switch(t) {
                            case "ActiveReward":
                                writeHex(1);
                                writeHex(4);
                                writeHex(4);
                                break;
                            case "MarketActReward":
                                writeHex(1);
                                writeHex(2);
                                writeHex(4);
                                break;
                        }
                        break;
                    case "m_astSongs":
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
        Text["Bin"] = Text["Bin"] || toBinText();
        return Text["Bin"];
    };
    var toXmlText = function () {
        if(!isUndefined(Buffer["Xml"])) {
            return Buffer["Xml"].getText();
        }
        if(!isUndefined(Buffer["Bin"])) {
            var b = Buffer["Bin"];
            var readByte = function () {
                var r = b.getHex(p, 1).toLowerCase().trimLeft("0");
                if(r == "") {
                    r = "0";
                }
                p += 1;
                return "0x" + r + " ";
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
                        return "";
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
                        case "TResHeadAll":
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
                        case "m_astReward":
                            switch(t) {
                                case "ActiveReward":
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
                                case "MarketActReward":
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
                        case "m_astSongs":
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
        Text["Xml"] = Text["Xml"] || toXmlText();
        var b = Text["Xml"].replace(/\r\n/g, "");
        var readData = function (f1, f2) {
            var r;
            var s1 = b.indexOf(f1, p);
            if(s1 == -1) {
                p = b.length;
                return "";
            } else {
                s1 += f1.length;
            }
            var s2 = b.indexOf(f2, s1);
            if(s2 == -1) {
                p = b.length;
                return "";
            }
            p = s2 + f2.length;
            return b.substring(s1, s2);
        };
        var writeDataT = function (s) {
            if(s.length == 0) {
                return;
            }
            html.push("<html><meta charset =" + '"UTF-8"' + "/><head><title>" + info.nameCore + "</title></head><body><table border=" + '"1"' + "><caption>" + info.nameCore + "</caption>");
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
                            case "m_astReward":
                                switch(a[1]) {
                                    case "ActiveReward":
                                        html.push(new String().duplicate(s[2][i][2], (e ? "<th colspan=" + '"3"' + ">" : "<th>") + a[0] + "</th>"));
                                        break;
                                    case "MarketActReward":
                                        html.push(new String().duplicate(s[2][i][2], (e ? "<th colspan=" + '"3"' + ">" : "<th>") + a[0] + "</th>"));
                                        break;
                                }
                                break;
                            case "m_astSongs":
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
                            case "m_astReward":
                                switch(a[1]) {
                                    case "ActiveReward":
                                        html.push(new String().duplicate(s[2][i][2], "<th>m_ucRewardType</th><th>m_iRewardID</th><th>m_iRewardValue</th>"));
                                        break;
                                    case "MarketActReward":
                                        html.push(new String().duplicate(s[2][i][2], "<th>m_ucRewardType</th><th>m_ushRewardID</th><th>m_iRewardValue</th>"));
                                        break;
                                }
                                break;
                            case "m_astSongs":
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
                                    case "m_astReward":
                                        switch(a[1]) {
                                            case "ActiveReward":
                                                html.push("<td>" + readData("<m_ucRewardType>", "</m_ucRewardType>") + "</td>");
                                                html.push("<td>" + readData("<m_iRewardID>", "</m_iRewardID>") + "</td>");
                                                html.push("<td>" + readData("<m_iRewardValue>", "</m_iRewardValue>") + "</td>");
                                                break;
                                            case "MarketActReward":
                                                html.push("<td>" + readData("<m_ucRewardType>", "</m_ucRewardType>") + "</td>");
                                                html.push("<td>" + readData("<m_ushRewardID>", "</m_ushRewardID>") + "</td>");
                                                html.push("<td>" + readData("<m_iRewardValue>", "</m_iRewardValue>") + "</td>");
                                                break;
                                        }
                                        break;
                                    case "m_astSongs":
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
            Text["Xml"] = Text["Xml"] || toXmlText();
            return Text["Xml"];
        };
        var readXml = function (t, t1, t2) {
            var f1 = "<" + t;
            var f2 = "</" + t + ">";
            var l1 = x.indexOf(f1, p);
            if(l1 == -1) {
                p = x.length;
                return "";
            } else {
                l1 += f1.length;
            }
            var l2 = x.indexOf(f2, l1);
            if(l2 == -1) {
                p = x.length;
                return "";
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
        switch(info.nameCore) {
            case "mrock_song_client":
            case "mrock_song_client_android":
                var T = ["m_ush4KeyEasy", "m_ush4KeyNormal", "m_ush4KeyHard", "m_ush5KeyEasy", "m_ush5KeyNormal", "m_ush5KeyHard", "m_ush6KeyEasy", "m_ush6KeyNormal", "m_ush6KeyHard"];
                var S = ["_4k_ez", "_4k_nm", "_4k_hd", "_5k_ez", "_5k_nm", "_5k_hd", "_6k_ez", "_6k_nm", "_6k_hd"];
                var E = ".imd";
                var x = getXml();
                while(true) {
                    var a = readXml("SongConfig_Client", "m_szPath", T);
                    a = a || [];
                    if(a.length == 0) {
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
            case "mrock_papasong_client":
                var T = ["m_cDifficulty"];
                var S = ["_Papa_Easy", "_Papa_Normal", "_Papa_Hard"];
                var E = ".mde";
                var x = getXml();
                while(true) {
                    var a = readXml("PapaSongConfig_Client", "m_szPath", T);
                    a = a || [];
                    if(a.length == 0) {
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
        r.push("if %t%==1 if exist ★res_imd.list ((for /f " + '"' + "delims=. tokens=1,2" + '"' + " %%i in (★res_imd.list) do (if not " + '"' + "%%j" + '"' + "==" + '"' + "mp3" + '"' + " (if not defined %%i.%%j (set %%i.%%j=A & set /a c+=1 & for /f " + '"' + "delims=_ tokens=1,*" + '"' + " %%k in (" + '"' + "%%i.%%j" + '"' + ") do (if exist res\\song\\%%k\\%%k_%%l (set /a e+=1) else (set /a n+=1 & set " + '"' + "f!n!=%%k_%%l" + '"' + " & set " + '"' + "d!n!=res\\song\\%%k\\" + '"' + " & echo http://game.ds.qq.com/Com_SongRes/song/%%k/%%k_%%l))))))>★res_new.txt)");
        r.push("if %t%==1 if exist ★res_mde.list ((for /f " + '"' + "delims=. tokens=1,2" + '"' + " %%i in (★res_mde.list) do (if not " + '"' + "%%j" + '"' + "==" + '"' + "mp3" + '"' + " (if not defined %%i.%%j (set %%i.%%j=A & set /a c+=1 & for /f " + '"' + "delims=_ tokens=1,*" + '"' + " %%k in (" + '"' + "%%i.%%j" + '"' + ") do (if exist res\\song\\%%k\\%%k_%%l (set /a e+=1) else (set /a n+=1 & set " + '"' + "f!n!=%%k_%%l" + '"' + " & set " + '"' + "d!n!=res\\song\\%%k\\" + '"' + " & echo http://game.ds.qq.com/Com_SongRes/song/%%k/%%k_%%l))))))>★res_new.txt)");
        r.push("if %t%==2 if exist ★res_imd.list ((for /f " + '"' + "delims=. tokens=1,2" + '"' + " %%i in (★res_imd.list) do (if " + '"' + "%%j" + '"' + "==" + '"' + "mp3" + '"' + " (if not defined %%i.%%j (set %%i.%%j=A & set /a c+=1 & if exist res\\song\\%%i\\%%i.%%j (set /a e+=1) else (set /a n+=1 & set " + '"' + "f!n!=%%i.%%j" + '"' + " & set " + '"' + "d!n!=res\\song\\%%i\\" + '"' + " & echo http://game.ds.qq.com/Com_SongRes/song/%%i/%%i.%%j)))))>★res_new.txt)");
        r.push("if %t%==2 if exist ★res_mde.list ((for /f " + '"' + "delims=. tokens=1,2" + '"' + " %%i in (★res_mde.list) do (if " + '"' + "%%j" + '"' + "==" + '"' + "mp3" + '"' + " (if not defined %%i.%%j (set %%i.%%j=A & set /a c+=1 & if exist res\\song\\%%i\\%%i.%%j (set /a e+=1) else (set /a n+=1 & set " + '"' + "f!n!=%%i.%%j" + '"' + " & set " + '"' + "d!n!=res\\song\\%%i\\" + '"' + " & echo http://game.ds.qq.com/Com_SongRes/song/%%i/%%i.%%j)))))>★res_new.txt)");
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
        r.push("if exist ★res_imd.list for /f " + '"' + "delims=. tokens=1,2" + '"' + " %%i in (★res_imd.list) do (if not " + '"' + "%%j" + '"' + "==" + '"' + "mp3" + '"' + " (if not exist %%i.imd (if exist %%i.txt (ren %%i.txt %%i.imd & set /a r+=1))))");
        r.push("@echo 　　┃　　.txt -^> .mde");
        r.push("if exist ★res_mde.list for /f " + '"' + "delims=. tokens=1,2" + '"' + " %%i in (★res_mde.list) do (if not " + '"' + "%%j" + '"' + "==" + '"' + "mp3" + '"' + " (if not exist %%i.mde (if exist %%i.txt (ren %%i.txt %%i.mde & set /a r+=1))))");
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
    this.ToBuffer = function (ext) {
        switch(ext) {
            case "bin":
                Buffer["Bin"] = Buffer["Bin"] || toBinBuffer();
                return Buffer["Bin"];
                break;
            case "hex":
                Buffer["Hex"] = Buffer["Hex"] || toHexBuffer();
                return Buffer["Hex"];
                break;
            case "xml":
                Buffer["Xml"] = Buffer["Xml"] || toXmlBuffer();
                return Buffer["Xml"];
                break;
            case "list":
                Buffer["List"] = Buffer["List"] || toListBuffer();
                return Buffer["List"];
                break;
            case "bat":
                Buffer["Bat"] = Buffer["Bat"] || toBatBuffer();
                return Buffer["Bat"];
                break;
        }
    };
    this.ToText = function (ext) {
        switch(ext) {
            case "bin":
                Text["Bin"] = Text["Bin"] || toBinText();
                return Text["Bin"];
                break;
            case "hex":
                Text["Hex"] = Text["Hex"] || toHexText();
                return Text["Hex"];
                break;
            case "xml":
                Text["Xml"] = Text["Xml"] || toXmlText();
                return Text["Xml"];
                break;
            case "html":
                Text["Html"] = Text["Html"] || toHtmlText();
                return Text["Html"];
                break;
            case "list":
                Text["List"] = Text["List"] || toListText();
                return Text["List"];
                break;
            case "bat":
                Text["Bat"] = Text["Bat"] || toBatText();
                return Text["Bat"];
                break;
        }
    };
    this.IsValid = function () {
        return isValid;
    };
    this.IsExist = function (ext) {
        switch(ext) {
            case "list":
                return (!isUndefined(Buffer["List"]) || !isUndefined(Text["List"]));
                break;
            case "bat":
                return (!isUndefined(Buffer["Bat"]) || !isUndefined(Text["Bat"]));
                break;
        }
    };
    (function () {
        if(buffer.length == 0) {
            return;
        }
        reset();
        switch(ext) {
            case "bin":
                fromBinBuffer(buffer);
                break;
            case "hex":
                fromHexBuffer(buffer);
                break;
            case "xml":
                fromXmlBuffer(buffer);
                break;
        }
    })();
}
function Img(info) {
    var buffer = info.buffer;
    var ext = info.extension;
    var isValid;
    var Buffer;
    var Text;
    var reset = function () {
        isValid = false;
        Buffer = {};
        Buffer["Img"] = undefined;
        Text = {};
        Text["Img"] = {};
    };
    var fromImgBuffer = function (buffer) {
        Buffer["Img"] = buffer;
        isValid = true;
    };
    var toImgText = function (mode, type) {
        var a = mode.split("X");
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
        return cvs.toDataURL("image/" + type);
    };
    this.ToText = function (mode, type) {
        Text["Img"][mode] = Text["Img"][mode] || toImgText(mode, type);
        return Text["Img"][mode];
    };
    this.IsValid = function () {
        return isValid;
    };
    (function () {
        if(buffer.length == 0) {
            return;
        }
        reset();
        switch(ext) {
            case "bmp":
            case "jpg":
            case "gif":
            case "png":
                fromImgBuffer(buffer);
                break;
        }
    })();
}
function Pkg(info) {
    var buffer = info.buffer;
    var ext = info.extension;
    var isValid;
    var Data;
    var Index;
    var p;
    var reset = function () {
        isValid = false;
        Data = [];
        Index = 0;
        p = 0;
    };
    var readInt = function (n) {
        var r = buffer.getInt(n, p, true);
        p += n;
        return r;
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
        var readDateTime = function () {
            var r = readInt(4);
            var date = (r & 0xFFFF0000) >> 16;
            var time = r & 0x0000FFFF;
            return new Date(1980 + ((date & 0xFE00) >> 9), ((date & 0x01E0) >> 5) - 1, date & 0x001F, (time & 0xF800) >> 11, (time & 0x07E0) >> 5, (time & 0x001F) * 2, 0).format();
        };
        while(true) {
            var header = readHeader();
            switch(header) {
                case 0x04034b50:
                    var dVersion = readInt(2);
                    var flag = readInt(2);
                    var method = readInt(2);
                    switch(method) {
                        case 1:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 12:
                        case 14:
                        case 97:
                        case 98:
                            break;
                        default:
                            method = 8;
                            break;
                    }
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
        var pointer = [];
        p += 72;
        pointer[0] = readInt(4);
        while(p < pointer[0]) {
            var v = readInt(4);
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
    var fromOjmBuffer = function (buffer) {
        var signature = readInt(4);
        var version = readInt(4);
        var encryption = readInt(4);
        var samples = readInt(4);
        var start = readInt(4);
        var offset = readInt(4);
        var padding = readInt(4);
        p = start;
        while(true) {
            var name = readText(32);
            var size = readInt(4);
            var type = readInt(2);
            p += 6;
            var id = readInt(2);
            p += 6;
            var mask = [0x00, 0x00, 0x00, 0x00];
            switch(encryption) {
                case 16:
                    mask = [0x6E, 0x61, 0x6D, 0x69];
                    break;
                case 32:
                    mask = [0x30, 0x34, 0x31, 0x32];
                    break;
            }
            Data.push({"path": name + ".ogg", "buffer": buffer.slice(p, p + size), "ojmmask": mask, "checked": false});
            p += size;
            if(p >= buffer.length) {
                break;
            }
        }
        isValid = true;
    };
    var checkBuffer = function (ext) {
        if(!Data[Index]["checked"]) {
            var tb = new Date();
            switch(ext) {
                case "zip":
                case "apk":
                case "ipa":
                case "osz":
                case "mcz":
                    Data[Index]["buffer"] = Data[Index]["buffer"].compressInflate(Data[Index]["size"]);
                    var ta = new Date();
                    if(Data[Index]["crc32"] == Data[Index]["buffer"].getCRC32()) {
                        Data[Index]["checked"] = true;
                        Data[Index]["time"] = ta - tb;
                    }
                    break;
                case "2dx":
                    var ta = new Date();
                    Data[Index]["checked"] = true;
                    Data[Index]["time"] = ta - tb;
                    break;
                case "ojm":
                    var n = 0;
                    for(var i = 0; i < Data[Index]["buffer"].length; i++) {
                        Data[Index]["buffer"][i] ^= Data[Index]["ojmmask"][n];
                        n += 1;
                        n = (n > Data[Index]["ojmmask"].length - 1 ? 0 : n);
                    }
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
        var f = new FileInfo(Data[Index]["path"]);
        d.push("path: " + f.directory);
        d.push("name: " + f.name);
        d.push("type: " + type);
        d.push("byte: " + Data[Index]["buffer"].length + " (" + Data[Index]["buffer"].length.toByteLength(2) + ")");
        if(!isUndefined(Data[Index]["datetime"])) {
            d.push("date: " + Data[Index]["datetime"]);
        }
        d.push("time: " + (!isUndefined(Data[Index]["time"]) ? Data[Index]["time"] + "ms" : "Error"));
        return d.join("\r\n");
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
    (function () {
        if(buffer.length == 0) {
            return;
        }
        reset();
        switch(ext) {
            case "zip":
            case "apk":
            case "ipa":
            case "osz":
            case "mcz":
                fromZipBuffer(buffer);
                break;
            case "2dx":
                from2dxBuffer(buffer);
                break;
            case "ojm":
                fromOjmBuffer(buffer);
                break;
        }
        if(isValid) {
            var list = [];
            for(var i = 0; i < Data.length; i++) {
                list[i] = Data[i]["path"];
            }
            SetPkgList(list);
        }
    })();
}
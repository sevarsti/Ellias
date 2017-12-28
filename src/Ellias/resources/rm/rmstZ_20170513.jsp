<!DOCTYPE html><html><head><meta http-equiv="Content-Type"content="text/html;charset=utf-8"/>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes"/>
<style type="text/css">
    #divRoot {
        text-align: center
    }

    #divTitle {
        clear: both;
        margin: 16px
    }

    #divOperate {
        margin: 3px
    }

    #divOperateMst {
        display: none
    }

    #divOperateBin {
        display: none
    }

    #divOperateImg {
        display: none
    }

    #divOperatePak {
        display: none
    }

    #divSave {
        margin: 3px;
        display: none
    }

    #divResult {
        margin: 16px
    }

    #divCopyright {
        clear: both;
        margin: 16px
    }
</style>
<script type='text/javascript' src='rmstZ1.js'> </script>
<script type='text/javascript' src='rmstZ2.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/RMDwr.js'></script>
</head>

<body onload="Initialize()">
<div id="divRoot">
    <div id="divTitle">rmstZ</div>
    <div id="divFileReader"><input id="inputFile" type="file"
                                   accept=".mst,.bms,.bme,.pms,.vos,.hex,.tja,.osu,.xml,.vox,.ksh,.imd,.mde,.mc,.aff,.bin,.bmp,.jpg,.gif,.png,.mp3,.zip,.apk,.ipa,.osz,.mcz,.2dx"
                                   multiple="true" onchange="ReadFile(event)" ondragover="DragBlob(event)"
                                   ondrop="DragFile(event)"/></div>
    <div id="divOperate">
        <div id="divOperateMst">
            <div id="divSelectMst"><select id="selectModeMst" onchange="SelectChanged()">
                <option value="mst">mst</option>
                <option value="bms">bms</option>
                <option value="bme">bme</option>
                <option value="pms">pms</option>
                <option value="vos">vos</option>
                <option value="mid">mid</option>
                <option value="lrc">lrc</option>
                <option value="hex">hex</option>
                <option value="tja">tja</option>
                <option value="osu">osu</option>
                <option value="xml">xml</option>
                <option value="vox">vox</option>
                <option value="ksh">ksh</option>
                <option value="imd">imd</option>
                <option value="mde">mde</option>
                <option value="mc">mc</option>
                <option value="aff">aff</option>
                <option value="png" selected="selected">png</option>
                <option value="html">html</option>
            </select></div>
            <div id="divSelectMstvos"><select id="selectModeMstvos" onchange="SelectChanged()">
                <option value="vos000" selected="selected">vos000</option>
                <option value="vos001">vos001</option>
                <option value="vos006">vos006</option>
                <option value="vos022">vos022</option>
            </select></div>
            <div id="divSelectMsthex"><select id="selectModeMsthex" onchange="SelectChanged()">
                <option value="vos000">vos000</option>
                <option value="vos001">vos001</option>
                <option value="vos006">vos006</option>
                <option value="vos022">vos022</option>
                <option value="imd" selected="selected">imd</option>
            </select></div>
            <div id="divSelectMsttja"><select id="selectModeMsttja" onchange="SelectChanged()">
                <option value="taiko" selected="selected">taiko</option>
                <option value="jube">jube</option>
            </select></div>
            <div id="divSelectMstosu"><select id="selectModeMstosu" onchange="SelectChanged()">
                <option value="osu" selected="selected">osu</option>
                <option value="taiko">taiko</option>
                <option value="ctb">ctb</option>
                <option value="mania">mania</option>
            </select></div>
            <div id="divSelectMstxml"><select id="selectModeMstxml" onchange="SelectChanged()">
                <option value="yddr" selected="selected">yddr</option>
                <option value="ydsd">ydsd</option>
                <option value="mde">mde</option>
            </select></div>
            <div id="divSelectMstmc"><select id="selectModeMstmc" onchange="SelectChanged()">
                <option value="key" selected="selected">key</option>
                <option value="step">step</option>
                <option value="dj">dj</option>
                <option value="catch">catch</option>
                <option value="pad">pad</option>
                <option value="taiko">taiko</option>
            </select></div>
            <div id="divSelectMstIndex"><select id="selectMstIndex" onchange="SelectChanged()"></select></div>
            <div id="divOptionMstTrans"><select id="selectTransKey" onchange="CheckChanged()">
                <option value="1">1K</option>
                <option value="2">2K</option>
                <option value="3">3K</option>
                <option value="4">4K</option>
                <option value="5">5K</option>
                <option value="6" selected="selected">6K</option>
                <option value="7">7K</option>
                <option value="8">8K</option>
                <option value="9">9K</option>
                <option value="10">10K</option>
                <option value="11">11K</option>
                <option value="12">12K</option>
                <option value="13">13K</option>
                <option value="14">14K</option>
                <option value="15">15K</option>
                <option value="16">16K</option>
                <option value="17">17K</option>
                <option value="18">18K</option>
            </select><input type="checkbox" id="inputRandomTrack" onchange="CheckChanged()">乱轨</input><input
                    type="checkbox" id="inputRandomNote" onchange="CheckChanged()">乱音</input><input type="checkbox"
                                                                                                    id="inputMirrorTrack"
                                                                                                    onchange="CheckChanged()">镜面</input>
                <select id="selectChangeSpeed" onchange="CheckChanged()">
                    <option value="0.333">1/3x</option>
                    <option value="0.5">1/2x</option>
                    <option value="1" selected="selected">1x</option>
                    <option value="2">2x</option>
                    <option value="3">3x</option>
                </select></div>
            <div id="divOptionMstKey"></div>
            <div id="divOptionMstEx"><input type="checkbox" id="inputLinkSingle" onchange="CheckChanged()">连单键</input>
                <input type="checkbox" id="inputLinkLong" onchange="CheckChanged()">连长键</input><input type="checkbox"
                                                                                                      id="inputLinkSlide"
                                                                                                      onchange="CheckChanged()">连滑键</input>
                <input type="checkbox" id="inputTransOblique" onchange="CheckChanged()">转斜键</input></div>
            <div id="divOptionMstAdv"><input type="checkbox" id="inputNoOblique" onchange="CheckChanged()">无斜键</input>
                <input type="checkbox" id="inputNoSlide" onchange="CheckChanged()">无滑键</input><input type="checkbox"
                                                                                                     id="inputNoLong"
                                                                                                     onchange="CheckChanged()">无长键</input>
                <input type="checkbox" id="inputNoSingle" onchange="CheckChanged()">无单键</input></div>
            <div id="divOptionMstFix"><input type="checkbox" id="inputNoCross" onchange="CheckChanged()">无叉键</input>
                <input type="checkbox" id="inputNoOverlap" onchange="CheckChanged()">无叠键</input><input type="checkbox"
                                                                                                       id="inputExcludeBug"
                                                                                                       checked="checked"
                                                                                                       onchange="CheckChanged()">自修复</input>
                <input type="checkbox" id="inputOptimizaData" checked="checked" onchange="CheckChanged()">自优化</input>
            </div>
            <div id="divOptionMstDraw1"><input type="checkbox" id="inputDrawBackground" checked="checked"
                                               onchange="OptionChanged()">背景色</input><input type="checkbox"
                                                                                            id="inputDrawTrack"
                                                                                            checked="checked"
                                                                                            onchange="OptionChanged()">轨道线</input>
                <input type="checkbox" id="inputDrawFrame" checked="checked" onchange="OptionChanged()">边框线</input>
            </div>
            <div id="divOptionMstDraw2"><input type="checkbox" id="inputDrawBeat" checked="checked"
                                               onchange="OptionChanged()">节拍线</input><input type="checkbox"
                                                                                            id="inputDrawCombo"
                                                                                            checked="checked"
                                                                                            onchange="OptionChanged()">连击数</input>
                <input type="checkbox" id="inputDrawDetermine" checked="checked" onchange="OptionChanged()">判定线</input>
            </div>
            <div id="divOptionMstScale"><select id="selectMstScaleX" onchange="OptionChanged()">
                <option value="1" selected="selected">128px</option>
                <option value="2.5">320px</option>
                <option value="5">640px</option>
                <option value="7.5">960px</option>
            </select><select id="selectMstScaleY" onchange="OptionChanged()">
                <option value="1">X1</option>
                <option value="2">X2</option>
                <option value="3">X3</option>
                <option value="4" selected="selected">X4</option>
            </select></div>
        </div>
        <div id="divOperateBin"><select id="selectModeBin" onchange="SelectChanged()">
            <option value="bin">bin</option>
            <option value="hex">hex</option>
            <option value="xml" selected="selected">xml</option>
            <option value="html">html</option>
            <option value="list">list</option>
            <option value="bat">bat</option>
        </select></div>
        <div id="divOperateImg"><select id="selectModeImg" onchange="SelectChanged()">
            <option value="480X320" selected="selected">.png</option>
            <option value="140X60">_title_ipad.png</option>
            <option value="140X90">_title_140_90.png</option>
            <option value="210X98">_cover_hd.png</option>
            <option value="1024X768">_ipad.png</option>
        </select></div>
        <div id="divOperatePak"><select id="selectPakList" onchange="SelectChanged()"></select></div>
        <div id="divSave"><a id="aSave" ondragstart="DragInput(event)">保存</a></div>
    </div>
    <div id="divResult"></div>
    <div id="divCopyright">Copyright©心のsky Group</div>
</div>

<script type="text/javascript">"use strict";
var FileNameOriginal = "";
var FileName = "";
var FileSuffixImd = "";
var FileSuffixMde = "";
var FileExtension = "";
var FileType;
var FileBuffer;
var FileData;
var IsReading = false;
var DefaultKey = -1;
var SelectCount;
function HideOperate() {
    document.getElementById("divOperateMst").style.display = "none";
    document.getElementById("divOperateBin").style.display = "none";
    document.getElementById("divOperateImg").style.display = "none";
    document.getElementById("divOperatePak").style.display = "none";
    document.getElementById("divSave").style.display = "none";
    document.getElementById("divResult").innerHTML = "";
}
function HideSelect(ext) {
    var id;
    var d;
    var t;
    switch(ext) {
        case"imd":
            id = ["selectModeMst"];
            d = "png";
            t = ["vos", "mid", "lrc"];
            break;
        case"bin":
            id = "selectModeBin";
            d = "xml";
            t = ["list", "bat"];
            break;
    }
    var n = -1;
    var c = 0;
    var s = document.getElementById(id);
    for(var i = 0; i < s.options.length; i++) {
        if((s.options[i].value == d) && (s.options[i].style.display != "none")) {
            n = i;
        }
        for(var j = 0; j < t.length; j++) {
            if(s.options[i].value == t[j]) {
                s.options[i].style.display = (FileData.IsExist(t[j]) ? "block" : "none");
            }
        }
        if(s.options[i].style.display != "none") {
            c += 1;
        }
    }
    if(s.options[s.selectedIndex].style.display == "none") {
        if(n != -1) {
            s.selectedIndex = n;
        } else if(c > 0) {
            s.selectedIndex = 0;
        } else {
            s.selectedIndex = -1;
        }
    }
    var HideHexVos = (function () {
        var s1 = document.getElementById("selectModeMst");
        var s2 = document.getElementById("selectModeMsthex");
        for(var i = 0; i < s1.options.length; i++) {
            if(s1.options[i].value == "vos") {
                for(var j = 0; j < s2.options.length; j++) {
                    switch(s2.options[j].value) {
                        case"vos000":
                        case"vos001":
                        case"vos006":
                        case"vos022":
                            s2.options[j].style.display = s1.options[i].style.display;
                            if((s2.selectedIndex == j) && (s2.options[j].style.display == "none")) {
                                s2.selectedIndex = 0;
                            }
                            break;
                    }
                }
            }
        }
    })();
}
function HideOption() {
    var t = GetSelectValue("selectModeMst");
    document.getElementById("divSelectMst").style.display = "block";
    document.getElementById("divSelectMsthex").style.display = (t == "hex" ? "block" : "none");
    document.getElementById("divSelectMstvos").style.display = (t == "vos" ? "block" : "none");
    document.getElementById("divSelectMstxml").style.display = (t == "xml" ? "block" : "none");
    document.getElementById("divSelectMsttja").style.display = (t == "tja" ? "block" : "none");
    document.getElementById("divSelectMstosu").style.display = (t == "osu" ? "block" : "none");
    document.getElementById("divSelectMstmc").style.display = (t == "mc" ? "block" : "none");
    document.getElementById("divSelectMstIndex").style.display = ((document.getElementById("selectMstIndex").childNodes.length > 1) && !(((t == "tja") || (t == "png") || (t == "html"))) ? "block" : "none");
    document.getElementById("divOptionMstEx").style.display = "block";
    document.getElementById("divOptionMstAdv").style.display = "block";
    document.getElementById("divOptionMstFix").style.display = "block";
    document.getElementById("divOptionMstTrans").style.display = "block";
    document.getElementById("divOptionMstKey").style.display = "block";
    document.getElementById("divOptionMstDraw1").style.display = ((t == "png") || (t == "html") ? "block" : "none");
    document.getElementById("divOptionMstDraw2").style.display = ((t == "png") || (t == "html") ? "block" : "none");
    document.getElementById("divOptionMstScale").style.display = (t == "html" ? "block" : "none");
}
function SetIndexList(l) {
    document.getElementById("selectMstIndex").onchange = null;
    document.getElementById("selectMstIndex").innerHTML = "";
    var o = [];
    for(var i = 0; i < l.length; i++) {
        o[i] = document.createElement("option");
        o[i].value = l[i];
        o[i].innerHTML = l[i];
        document.getElementById("selectMstIndex").appendChild(o[i]);
    }
    document.getElementById("selectMstIndex").onchange = SelectChanged;
}
function SetTransKey(k) {
    document.getElementById("selectTransKey").onchange = null;
    SetSelectValue("selectTransKey", k);
    document.getElementById("selectTransKey").onchange = CheckChanged;
}
function SetTrackCheck(n) {
    var b = (arguments.length > 1 ? arguments[1] : []);
    document.getElementById("divOptionMstKey").innerHTML = "";
    var l = n.toString().length;
    var c = [];
    var p = [];
    for(var i = 0; i < n; i++) {
        c[i] = document.createElement("input");
        c[i].type = "checkbox";
        if((typeof b[i] === "undefined") || (b[i])) {
            c[i].checked = "checked";
        }
        c[i].onchange = CheckChanged;
    }
    if(n <= 9) {
        var d = document.createElement("div");
        for(var i = 0; i < n; i++) {
            d.appendChild(c[i]);
            d.appendChild(document.createTextNode(i.toString().fill(l, "0", true)));
        }
        document.getElementById("divOptionMstKey").appendChild(d);
    } else if(n <= 14) {
        var d1 = document.createElement("div");
        for(var i = 0; i < 9; i++) {
            d1.appendChild(c[i]);
            d1.appendChild(document.createTextNode(i.toString().fill(l, "0", true)));
        }
        document.getElementById("divOptionMstKey").appendChild(d1);
        var d2 = document.createElement("div");
        for(var i = 9; i < n; i++) {
            d2.appendChild(c[i]);
            d2.appendChild(document.createTextNode(i.toString().fill(l, "0", true)));
        }
        document.getElementById("divOptionMstKey").appendChild(d2);
    } else {
        var d = [];
        var t = Math.ceil(n / 9);
        for(var i = 0; i < t; i++) {
            d[i] = document.createElement("div");
            var m = 9 * (i + 1);
            if(m > n) {
                m = n;
            }
            for(var j = 9 * i; j < m; j++) {
                d[i].appendChild(c[j]);
                d[i].appendChild(document.createTextNode(j.toString().fill(l, "0", true)));
            }
            document.getElementById("divOptionMstKey").appendChild(d[i]);
        }
    }
}
function SetPakList(l) {
    document.getElementById("selectPakList").onchange = null;
    document.getElementById("selectPakList").innerHTML = "";
    var o = [];
    for(var i = 0; i < l.length; i++) {
        o[i] = document.createElement("option");
        o[i].value = l[i];
        o[i].innerHTML = l[i];
        document.getElementById("selectPakList").appendChild(o[i]);
    }
    document.getElementById("selectPakList").onchange = SelectChanged;
}
function SetSave(d, n, e, t) {
    var a = document.getElementById("aSave");
    URL.revokeObjectURL(a.href);
    a.href = URL.createObjectURL(new Blob([d], {"type": t}));
    a.download = n + "." + e;
    a.title = a.download + "\r\n" + d.length.toByteLength(2);
    document.getElementById("divSave").style.display = "block";
}
;
function SetResult(m, s, d, n, e, t) {
    var w = (arguments.length > 6 ? arguments[6] : false);
    switch(m) {
        case"text":
            if(d.length == 0) {
                return;
            }
            SetSave(d, n, e, t);
            if(((FileExtension == "bin") || (FileType == "bin")) && (d.length == 0)) {
                document.getElementById("divOperateBin").style.display = "none";
                alert("目前不支持 " + n + " 。");
                return;
            }
            var p = document.createElement("p");
            p.style.textAlign = "left";
            p.style.fontSize = "14px";
            if(w) {
                p.style.wordWrap = "break-word";
            }
            p.innerHTML = s.toHtml().replace(/\r\n/g, "<br/>");
            document.getElementById("divResult").appendChild(p);
            break;
        case"html":
            if(s.length == 0) {
                return;
            }
            d = new Uint8Array().fromText(s);
            SetSave(d, n, e, t);
            if((FileExtension == "bin") && (s.length == 0)) {
                document.getElementById("divOperateBin").style.display = "none";
                alert("目前不支持 " + n + " 。");
                return;
            }
            var iframe = document.createElement("iframe");
            iframe.onload = function () {
                var c = this.contentDocument;
                if(this != null && c != null) {
                    this.style.border = "none";
                    this.width = "100%";
                    this.height = c.body.scrollHeight + 17;
                    this.style.display = "block";
                }
            };
            iframe.style.display = "none";
            iframe.src = document.getElementById("aSave").href;
            document.getElementById("divResult").appendChild(iframe);
            break;
        case"image":
            if(s.length == 0) {
                return;
            }
            d = new Uint8Array().fromBase64(s.split(",")[1]);
            SetSave(d, n, e, t);
            var img = document.createElement("img");
            img.src = s;
            document.getElementById("divResult").appendChild(img);
            break;
        case"audio":
            if(d.length == 0) {
                return;
            }
            SetSave(d, n, e, t);
            var audio = document.createElement("audio");
            audio.src = s;
            audio.controls = "controls";
            document.getElementById("divResult").appendChild(audio);
            break;
    }
}
;
function ReadBuffer() {
    IsReading = true;
    switch(FileExtension) {
        case"mst":
        case"bms":
        case"bme":
        case"pms":
        case"vos":
        case"tja":
        case"osu":
        case"vox":
        case"ksh":
        case"imd":
        case"mde":
        case"mc":
        case"aff":
            FileData = new Mst(FileBuffer, FileExtension);
            break;
        case"hex":
            switch(FileType) {
                case"vos":
                case"imd":
                    FileData = new Mst(FileBuffer, FileExtension, FileType);
                    break;
                case"bin":
                    FileData = new Bin(FileBuffer, FileExtension);
                    break;
            }
            break;
        case"xml":
            switch(FileType) {
                case"yddr":
                case"ydsd":
                case"mde":
                    FileData = new Mst(FileBuffer, FileExtension, FileType);
                    break;
                case"bin":
                    FileData = new Bin(FileBuffer, FileExtension);
                    break;
            }
            break;
        case"bin":
            FileData = new Bin(FileBuffer, FileExtension);
            break;
        case"bmp":
        case"jpg":
        case"gif":
        case"png":
            FileData = new Img(FileBuffer, FileExtension);
            break;
        case"mp3":
            FileData = new Msc(FileBuffer, FileExtension);
            break;
        case"zip":
        case"apk":
        case"ipa":
        case"osz":
        case"mcz":
        case"2dx":
            FileData = new Pak(FileBuffer, FileExtension);
            break;
    }
}
function ReadData() {
    HideOperate();
    var s = (arguments.length > 0 ? arguments[0] : "");
    switch(FileExtension) {
        case"mst":
        case"bms":
        case"bme":
        case"pms":
        case"vos":
        case"tja":
        case"osu":
        case"vox":
        case"ksh":
        case"imd":
        case"mde":
        case"mc":
        case"aff":
            ReadMst(s, FileExtension);
            break;
        case"hex":
            switch(FileType) {
                case"vos":
                case"imd":
                    ReadMst(s, "hex", FileType);
                    break;
                case"bin":
                    ReadBin();
                    break;
            }
            break;
        case"xml":
            switch(FileType) {
                case"yddr":
                case"ydsd":
                case"mde":
                    ReadMst(s, "xml", FileType);
                    break;
                case"bin":
                    ReadBin();
                    break;
            }
            break;
        case"bin":
            ReadBin();
            break;
        case"bmp":
        case"jpg":
        case"gif":
        case"png":
            ReadImg();
            break;
        case"mp3":
            ReadMp3();
            break;
        case"zip":
        case"apk":
        case"ipa":
        case"osz":
        case"mcz":
        case"2dx":
            ReadPak(s, FileExtension);
            break;
    }
}
function ReadMst(s, e) {
    var t = (arguments.length > 2 ? arguments[2] : "");
    if(!FileData.IsValid()) {
        return;
    }
    switch(s) {
        case"select":
            FileData.Choose(GetSelectValue("selectMstIndex"));
            break;
        case"option":
            FileData.ReDraw();
            break;
        case"check":
            FileData.FromBuffer(FileBuffer, e, t);
            break;
    }
    document.getElementById("divOperateMst").style.display = "block";
    HideSelect("imd");
    HideOption();
    var m = GetSelectValue("selectModeMst");
    switch(m) {
        case"mst":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName, m, "text/plain");
            break;
        case"bms":
        case"bme":
        case"pms":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName, m, "text/plain");
            break;
        case"vos":
            var t = GetSelectValue("selectModeMstvos");
            SetResult("text", FileData.ToText(m, t), FileData.ToBuffer(m, t), FileName, m, "application/octet-binary");
            break;
        case"mid":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName, m, "application/octet-binary");
            break;
        case"lrc":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName, m, "text/plain");
            break;
        case"hex":
            var t = GetSelectValue("selectModeMsthex");
            SetResult("text", FileData.ToText(m, t), FileData.ToBuffer(m, t), FileName + (t == "imd" ? FileSuffixImd : "") + "." + FileType, m, "text/plain");
            break;
        case"tja":
            var t = GetSelectValue("selectModeMsttja");
            SetResult("text", FileData.ToText(m, t), FileData.ToBuffer(m, t), FileName + "[" + t + "]", m, "text/plain");
            break;
        case"osu":
            var t = GetSelectValue("selectModeMstosu");
            SetResult("text", FileData.ToText(m, t), FileData.ToBuffer(m, t), FileName + "[" + t + "]", m, "text/plain");
            break;
        case"xml":
            var t = GetSelectValue("selectModeMstxml");
            switch(t) {
                case"yddr":
                case"ydsd":
                    SetResult("text", FileData.ToText(m, t), FileData.ToBuffer(m, t), FileName, m, "text/xml", t == "ydsd");
                    break;
                case"mde":
                    SetResult("text", FileData.ToText(m, t), FileData.ToBuffer(m, t), FileName + FileSuffixMde, m, "text/html");
                    break;
            }
            break;
        case"vox":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName, m, "text/plain");
            break;
        case"ksh":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName, m, "text/plain");
            break;
        case"imd":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName + FileSuffixImd, m, "application/octet-binary");
            break;
        case"mde":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName + FileSuffixMde, m, "text/html", true);
            break;
        case"mc":
            var t = GetSelectValue("selectModeMstmc");
            SetResult("text", FileData.ToText(m, t), FileData.ToBuffer(m, t), FileName + "[" + t + "]", m, "text/plain");
            break;
        case"aff":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName, m, "text/plain");
            break;
        case"png":
            if(!SupportAPI("Canvas")) {
                alert("当前浏览器内核不支持Canvas API。");
                return;
            }
            SetResult("image", FileData.ToText("png"), new Uint8Array(), FileName, "png", "image/png");
            break;
        case"html":
            SetResult("html", FileData.ToText("html"), new Uint8Array(), FileName, "html", "text/html");
            break;
    }
}
function ReadBin() {
    if(!FileData.IsValid()) {
        return;
    }
    document.getElementById("divOperateBin").style.display = "block";
    HideSelect("bin");
    var m = GetSelectValue("selectModeBin");
    switch(m) {
        case"bin":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName, m, "application/octet-binary");
            break;
        case"hex":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName + "." + FileType, m, "text/plain");
            break;
        case"xml":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), FileName, m, "text/html");
            break;
        case"html":
            SetResult("html", FileData.ToText(m), new Uint8Array(), FileName, m, "text/html");
            break;
        case"list":
            var n = "";
            switch(FileName) {
                case"mrock_song_client_android":
                    n = "★res_imd";
                    break;
                case"mrock_papasong_client":
                    n = "★res_mde";
                    break;
            }
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), n, m, "text/html");
            break;
        case"bat":
            SetResult("text", FileData.ToText(m), FileData.ToBuffer(m), "★res_all", m, "text/html");
            break;
    }
}
function ReadImg() {
    if(!FileData.IsValid()) {
        return;
    }
    if(!SupportAPI("Canvas")) {
        alert("当前浏览器内核不支持Canvas API。");
        return;
    }
    document.getElementById("divOperateImg").style.display = "block";
    if(SelectCount == 0) {
        var v = GetImgSize(FileBuffer);
        var s = document.getElementById("selectModeImg");
        for(var i = 0; i < s.children.length; i++) {
            if(s.children[i].value == v) {
                s.selectedIndex = i;
                break;
            }
        }
    }
    var s = document.getElementById("selectModeImg");
    var t = s.options[s.selectedIndex].text;
    SetResult("image", FileData.ToText("png", s.selectedIndex), new Uint8Array(), FileName + GetFileName(t), "png", "image/png");
}
function ReadMp3() {
    return;
    if(!FileData.IsValid()) {
        return;
    }
    if(!SupportAPI("Canvas")) {
        alert("当前浏览器内核不支持Canvas API。");
        return;
    }
    if(!SupportAPI("Audio")) {
        alert("当前浏览器内核不支持Audio API。");
        return;
    }
    SetResult("audio", "", FileData.ToText("imd"), FileName + FileSuffixImd, "imd", "application/octet-binary");
}
function ReadPak(s, e) {
    if(!FileData.IsValid()) {
        return;
    }
    switch(s) {
        case"select":
            FileData.Choose(GetSelectValue("selectPakList"));
            break;
    }
    document.getElementById("divOperatePak").style.display = "block";
    var f = GetSelectValue("selectPakList");
    var m = GetFileExtension(f);
    f = GetFileName(f);
    SetResult("text", FileData.ToText(e, m), FileData.ToBuffer(e), f, m, "application/octet-binary");
}
function ReadInput(n, b) {
    FileNameOriginal = n;
    FileBuffer = b;
    SetFileInfo();
    SelectCount = 0;
    ReadBuffer();
    ReadData();
}
function forJava(filename, base64code) {
    ReadInput(filename, new Uint8Array().fromBase64(base64code));
//    alert(FileData.ToText("png"));
//    document.getElementById('result').innerHTML =  FileData.ToText("png");
}
function ReadBlob(n, b) {
    if(!SupportAPI("File")) {
        alert("当前浏览器内核不支持File API。");
        return;
    }
    var r = new FileReader();
    r.onload = function () {
        ReadInput(n, new Uint8Array(this.result));
    };
    r.readAsArrayBuffer(b);
}
function ReadFile(e) {
    ReadBlob(e.target.files[0].name, e.target.files[0]);
}
function DragFile(e) {
    var n = e.dataTransfer.getData("name");
    if(n != "") {
        var r = new XMLHttpRequest();
        r.open("GET", e.dataTransfer.getData("blob"));
        r.responseType = "blob";
        r.onload = function () {
            document.getElementById("inputFile").value = "";
            ReadBlob(n, r.response);
        };
        r.send();
    }
}
function DragBlob(e) {
    e.preventDefault();
}
function DragInput(e) {
    e.dataTransfer.setData("name", e.target.download);
    e.dataTransfer.setData("blob", e.target.href);
}
function SelectChanged() {
    SelectCount += 1;
    ReadData("select");
}
function OptionChanged() {
    ReadData("option");
}
function CheckChanged() {
    ReadData("check");
}
function Initialize() {
    var LoadLinkExtend = (function () {
        var loadLink = function (id, url) {
            var a = document.createElement("a");
            a.href = url;
            a.target = "_blank";
            a.innerHTML = document.getElementById(id).innerHTML;
            a.style.textDecoration = "none";
            a.style.color = "black";
            document.getElementById(id).innerHTML = "";
            document.getElementById(id).appendChild(a);
        };
        loadLink("divTitle", "http://shang.qq.com/wpa/qunwpa?idkey=73a1de166e06a6c8a9a3856ef18bec30bfe9036ca3b7cee2fa537edd5ebf2d20");
        loadLink("divCopyright", "http://wpa.qq.com/msgrd?v=3&uin=308973930&site=rmstZ_" + Version + ".html&menu=yes");
    })();
    var LoadUrlParameter = (function () {
        var s = location.search.substring(1).split("&");
        for(var i = 0; i < s.length; i++) {
            var a = s[i].split("=");
            if(a.length == 2) {
                switch(a[0].toLowerCase()) {
                    case"help":
                        var m = (function () {
                            var r = "";
                            var helphelp = "\r\n<help>\r\n　[bgcolor]　Help of bgcolor\r\n　[show]　Help of show\r\n　[check]　Help of check\r\n　[default]　Help of default\r\n　[input]　Help of input\r\n　[all]　Help above all\r\n";
                            var helpbgcolor = "\r\n<bgcolor>\r\n　[#rrggbbaa]　RGBA Hex\r\n";
                            var helpshow = "\r\n<show>\r\n　[version]　Version\r\n　[bgcolor]　BackgroundColor\r\n";
                            var helpcheck = "\r\n<check>\r\n　[le]　LittleEndian\r\n　[api]　SupportAPI\r\n　[agent]　UserAgent\r\n";
                            var helpdefault = "\r\n<default>\r\n　Trans Mode Select\r\n　{modemst}　[mst] or [bms] or [bme] or [pms] or [vos] or [hex] or [tja] or [osu] or [xml] or [vos] or [ksh] or [imd] or [mde] or [mc] or [aff] or [png] or [html]\r\n　{modemsthex}　[imd]\r\n　{modemsttja}　[taiko] or [jube]\r\n　{modemstosu}　[osu] or [taiko] or [ctb] or [mania]\r\n　{modemstxml}　[yddr] or [ydsd] or [mde]\r\n　{modemstmc}　[key] or [step] or [dj] or [catch] or [pad] or [taiko]\r\n　{modebin}　[bin] or [hex] or [xml] or [html] or [list] or [bat]\r\n　{modeimg}　[480X320] or [140X60] or [140X90] or [210X98] or [1024X768]\r\n\r\n　Data Change Option\r\n　{randomtrack}　[true] or [false]\r\n　{randomnote}　[true] or [false]\r\n　{mirrortrack}　[true] or [false]\r\n　{linksingle}　[true] or [false]\r\n　{linklong}　[true] or [false]\r\n　{linkslide}　[true] or [false]\r\n　{transoblique}　[true] or [false]\r\n　{nooblique}　[true] or [false]\r\n　{noslide}　[true] or [false]\r\n　{nolong}　[true] or [false]\r\n　{nosingle}　[true] or [false]\r\n　{nocross}　[true] or [false]\r\n　{nooverlap}　[true] or [false]\r\n　{excludebug}　[true] or [false]\r\n　{optimizadata}　[true] or [false]\r\n　{transkey}　[1] to [18]\r\n　{changespeed}　[0.33] or [0.5] or [1] to [3]\r\n\r\n　To Preview Png Parameter\r\n　{drawbackground}　[true] or [false]\r\n　{drawtrack}　[true] or [false]\r\n　{drawframe}　[true] or [false]\r\n　{drawbeat}　[true] or [false]\r\n　{drawcombo}　[true] or [false]\r\n　{drawdetermine}　[true] or [false]\r\n\r\n　To Preview Html Parameter\r\n　{scalex}　[1] or [2.5] or [5] or [7.5]\r\n　{scaley}　[1] to [4]\r\n";
                            var helpinput = "\r\n<input>\r\n　[filename.ext|filebufferbase64string]　Input File Info\r\n";
                            var v = a[1].split(",");
                            for(var j = 0; j < v.length; j++) {
                                switch(v[j].toLowerCase()) {
                                    case"help":
                                        r += helphelp;
                                        break;
                                    case"bgcolor":
                                        r += helpbgcolor;
                                        break;
                                    case"show":
                                        r += helpshow;
                                        break;
                                    case"check":
                                        r += helpcheck;
                                        break;
                                    case"default":
                                        r += helpdefault;
                                        break;
                                    case"input":
                                        r += helpinput;
                                        break;
                                    case"all":
                                        return helphelp + helpbgcolor + helpshow + helpcheck + helpdefault + helpinput;
                                        break;
                                }
                            }
                            return r;
                        })();
                        document.write(("【" + a[0] + "】\r\n\r\nexpurl/rmstZ.html?key1=value1&key2=value2,value3|value4&key3=name1:value5,name2:value6|value7\r\n\r\n<key>　{name}　[value]　explain\r\n" + m).toHtml().replace(/\r\n/g, "</br>"))
                        break;
                    case"bgcolor":
                        var v = a[1].split(",");
                        var c = v[v.length - 1].substring(0, 8).fill(8, "0", false);
                        if(c.isHex()) {
                            var a = parseInt(c.substr(0, 2), 16) / 256;
                            var r = parseInt(c.substr(2, 2), 16);
                            var g = parseInt(c.substr(4, 2), 16);
                            var b = parseInt(c.substr(6, 2), 16);
                            document.body.style.backgroundColor = "rgba(" + r + ", " + g + ", " + b + ", " + a + ")";
                        }
                        break;
                    case"show":
                        var m = (function () {
                            var r = "";
                            var showversion = "\r\nVersion：" + Version + "\r\n";
                            var showbgcolor = "\r\nBgColor：" + (document.body.style.backgroundColor == "" ? "rgb(255, 255, 255)" : document.body.style.backgroundColor) + "\r\n";
                            var v = a[1].split(",");
                            for(var j = 0; j < v.length; j++) {
                                switch(v[j].toLowerCase()) {
                                    case"version":
                                        r += showversion;
                                        break;
                                    case"bgcolor":
                                        r += showbgcolor;
                                        break;
                                    case"all":
                                        return showversion + showbgcolor;
                                        break;
                                }
                            }
                            return r;
                        })();
                        alert("【" + a[0] + "】\r\n" + m);
                        break;
                    case"check":
                        var m = (function () {
                            var r = "";
                            var checkle = "\r\nLittleEndian：" + LittleEndian + "\r\n";
                            var checkapi = (function () {
                                var n = ["File", "TypedArrays", "Canvas", "Audio", "MIDI", "UserMedia", "Gamepads"];
                                var m = [];
                                for(var k = 0; k < n.length; k++) {
                                    m.push("\r\nSupport" + n[k] + "API：" + SupportAPI(n[k]));
                                }
                                return m.join("") + "\r\n";
                            })();
                            var checkagent = (function () {
                                var n = ["Chrome", "Mozilla", "MSIE", "Safari", "AppleWebKit", "UCBrowser"];
                                var m = [];
                                for(var k = 0; k < n.length; k++) {
                                    m.push("\r\nInclude" + n[k] + "Agent：" + IncludeAgent(n[k]));
                                }
                                return m.join("") + "\r\n";
                            })();
                            var v = a[1].split(",");
                            for(var j = 0; j < v.length; j++) {
                                switch(v[j].toLowerCase()) {
                                    case"le":
                                        r += checkle;
                                        break;
                                    case"api":
                                        r += checkapi;
                                        break;
                                    case"agent":
                                        r += checkagent;
                                        break;
                                    case"all":
                                        return checkle + checkapi + checkagent;
                                        break;
                                }
                            }
                            return r;
                        })();
                        alert("【" + a[0] + "】\r\n" + m);
                        break;
                    case"default":
                        var v = a[1].split(",");
                        for(var j = 0; j < v.length; j++) {
                            var c = v[j].split(":");
                            if(c.length == 2) {
                                switch(c[0].toLowerCase()) {
                                    case"modemst":
                                    case"modemsthex":
                                    case"modemstxml":
                                    case"modemsttja":
                                    case"modemstosu":
                                    case"modemstmc":
                                    case"modebin":
                                    case"modeimg":
                                    case"transkey":
                                    case"changespeed":
                                    case"scalex":
                                    case"scaley":
                                        var m = {"modemst": "selectModeMst", "modemsthex": "selectModeMsthex", "modemstxml": "selectModeMstxml", "modemsttja": "selectModeMsttja", "modemstosu": "selectModeMstosu", "modemstmc": "selectModeMstmc", "modebin": "selectModeBin", "modeimg": "selectModeImg", "transkey": "selectTransKey", "changespeed": "selectChangeSpeed", "scalex": "selectMstScaleX", "scaley": "selectMstScaleY"};
                                        if(typeof m[c[0]] !== "undefined") {
                                            var l = c[1].split("|");
                                            for(var k = 0; k < l.length; k++) {
                                                if(SetSelectValue(m[c[0]], l[k])) {
                                                    if(c[0] == "transkey") {
                                                        DefaultKey = l[k];
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    case"randomtrack":
                                    case"randomnote":
                                    case"mirrortrack":
                                    case"linksingle":
                                    case"linklong":
                                    case"linkslide":
                                    case"transoblique":
                                    case"nooblique":
                                    case"noslide":
                                    case"nolong":
                                    case"nosingle":
                                    case"nocross":
                                    case"nooverlap":
                                    case"excludebug":
                                    case"optimizadata":
                                    case"drawbackground":
                                    case"drawtrack":
                                    case"drawframe":
                                    case"drawbeat":
                                    case"drawcombo":
                                    case"drawdetermine":
                                        var m = {"randomtrack": "inputRandomTrack", "randomnote": "inputRandomNote", "mirrortrack": "inputMirrorTrack", "linksingle": "inputLinkSingle", "linklong": "inputLinkLong", "linkslide": "inputLinkSlide", "transoblique": "inputTransOblique", "nooblique": "inputNoOblique", "noslide": "inputNoSlide", "nolong": "inputNoLong", "nosingle": "inputNoSingle", "nocross": "inputNoCross", "nooverlap": "inputNoOverlap", "excludebug": "inputExcludeBug", "optimizadata": "inputOptimizaData", "drawbackground": "inputDrawBackground", "drawtrack": "inputDrawTrack", "drawframe": "inputDrawFrame", "drawbeat": "inputDrawBeat", "drawcombo": "inputDrawCombo", "drawdetermine": "inputDrawDetermine"};
                                        if(typeof m[c[0]] !== "undefined") {
                                            var l = c[1].split("|");
                                            for(var k = 0; k < l.length; k++) {
                                                if(l[k] == "true") {
                                                    document.getElementById(m[c[0]]).checked = "checked";
                                                    break;
                                                } else if(l[k] == "false") {
                                                    document.getElementById(m[c[0]]).checked = "";
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                        break;
                    case"input":
                        var v = a[1].split(",");
                        for(var j = 0; j < v.length; j++) {
                            var f = v[j].split("|");
                            if((f.length == 2) && (j == 0)) {
                                ReadInput(f[0], new Uint8Array().fromBase64(f[1]));
                            }
                        }
                        break;
                }
            }
        }
    })();
    loadData();
}
function loadData() {
    RMDwr.fromServer(null, after);
}
function after(data) {
    if(data != null && data.length > 0) {
        var name = data[0];
        var filedata = data[1];
        forJava(name, filedata);
        RMDwr.fromServer(FileData.ToText("png"), after);
    } else {
        setTimeout(loadData, 2000);
    }
}
</script>
<input type="test" onclick="forJava('daybyday_4k_ez.imd', document.getElementById('base64value').value)"/>
<textarea id="base64value">

</textarea>
<textarea id="result">

</textarea>
</body>
</html>
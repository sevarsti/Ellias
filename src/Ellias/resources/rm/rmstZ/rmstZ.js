"use strict";
var Info = {};
var Data = {};
var ChangeApply = {};
var Indexes = [0];
var TrackChecks = [];
var SelectCount = 0;
function HideOperate() {
	document.getElementById("divOperateMst").style.display = "none";
	document.getElementById("divOperateBin").style.display = "none";
	document.getElementById("divOperateImg").style.display = "none";
	document.getElementById("divOperatePak").style.display = "none";
	document.getElementById("divSave").style.display = "none";
	document.getElementById("divResult").innerHTML = "";
}
function HideSelect(m) {
	var id;
	var d;
	var t;
	switch (m) {
	case "mst":
		id = ["selectMstMode"];
		d = "png";
		t = ["vos", "mid", "lrc"];
		break;
	case "bin":
		id = "selectBinMode";
		d = "xml";
		t = ["list", "bat"];
		break;
	}
	var n = -1;
	var c = 0;
	var s = document.getElementById(id);
	for (var i = 0; i < s.options.length; i++) {
		if ((s.options[i].value == d) && (s.options[i].style.display != "none")) {
			n = i;
		}
		for (var j = 0; j < t.length; j++) {
			if (s.options[i].value == t[j]) {
				s.options[i].style.display = (Data.IsExist(t[j]) ? "block": "none");
			}
		}
		if (s.options[i].style.display != "none") {
			c += 1;
		}
	}
	if (s.options[s.selectedIndex].style.display == "none") {
		if (n != -1) {
			s.selectedIndex = n;
		}
		 else if (c > 0) {
			s.selectedIndex = 0;
		}
		 else {
			s.selectedIndex = -1;
		}
	}
	var HideHexVos = (function() {
		var s1 = document.getElementById("selectMstMode");
		var s2 = document.getElementById("selectMsthexMode");
		for (var i = 0; i < s1.options.length; i++) {
			if (s1.options[i].value == "vos") {
				for (var j = 0; j < s2.options.length; j++) {
					switch (s2.options[j].value) {
					case "vos000":
					case "vos001":
					case "vos006":
					case "vos022":
						s2.options[j].style.display = s1.options[i].style.display;
						if ((s2.selectedIndex == j) && (s2.options[j].style.display == "none")) {
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
	var t = GetSelectValue("selectMstMode");
	document.getElementById("divSelectMst").style.display = "block";
	document.getElementById("divSelectMsthex").style.display = (t == "hex" ? "block": "none");
	document.getElementById("divSelectMstvos").style.display = (t == "vos" ? "block": "none");
	document.getElementById("divSelectMstxml").style.display = (t == "xml" ? "block": "none");
	document.getElementById("divSelectMsttja").style.display = (t == "tja" ? "block": "none");
	document.getElementById("divSelectMstosu").style.display = (t == "osu" ? "block": "none");
	document.getElementById("divSelectMstmc").style.display = (t == "mc" ? "block": "none");
	document.getElementById("divSelectMstdraw").style.display = ((t == "png") || (t == "html") ? "block": "none");
	document.getElementById("divOptionMst").style.display = "block";
	document.getElementById("detailsOptionMstDraw").style.display = ((t == "png") || (t == "html") ? "block": "none");
	document.getElementById("divOptionDrawScale").style.display = (t == "html" ? "block": "none");
}
function SetIndexList(l) {
  if (l.length > 1) {
    document.getElementById("divSelectMstIndex").innerHTML = "";
    var o = [];
    var indexes = [];
    for (var i = 0; i < l.length; i++) {
      o[i] = document.createElement("input");
      o[i].type = "checkbox";
      o[i].checked = "checked";
      o[i].value = l[i];
      o[i].onchange = IndexChanged;
      var d = document.createElement("div");
      d.appendChild(o[i]);
      d.appendChild(document.createTextNode(l[i]));
      document.getElementById("divSelectMstIndex").appendChild(d);
      indexes.push(i);
    }
    Indexes = indexes;
    document.getElementById("divSelectMstIndex").style.display = (l.length != 0 ? "block" : "none");
  }
}
function SetTrackCheck(n) {
	document.getElementById("divOptionMstTrack").onchange = null;
	document.getElementById("divOptionMstTrack").innerHTML = "";
	var b = TrackChecks;
	b = b || [];
	var l = n.toString().length;
	var c = [];
	var p = [];
	for (var i = 0; i < n; i++) {
		c[i] = document.createElement("input");
		c[i].type = "checkbox";
		if (isUndefined(b[i]) || b[i]) {
			c[i].checked = "checked";
		}
		c[i].title = "TrackCheck";
	}
	if (n <= 9) {
		var d = document.createElement("div");
		for (var i = 0; i < n; i++) {
			d.appendChild(c[i]);
			d.appendChild(document.createTextNode(i.toString().fill(l, "0", true)));
		}
		document.getElementById("divOptionMstTrack").appendChild(d);
	}
	 else if (n <= 14) {
		var d1 = document.createElement("div");
		for (var i = 0; i < 9; i++) {
			d1.appendChild(c[i]);
			d1.appendChild(document.createTextNode(i.toString().fill(l, "0", true)));
		}
		document.getElementById("divOptionMstTrack").appendChild(d1);
		var d2 = document.createElement("div");
		for (var i = 9; i < n; i++) {
			d2.appendChild(c[i]);
			d2.appendChild(document.createTextNode(i.toString().fill(l, "0", true)));
		}
		document.getElementById("divOptionMstTrack").appendChild(d2);
	}
	 else {
		var d = [];
		var t = Math.ceil(n / 9);
		for (var i = 0; i < t; i++) {
			d[i] = document.createElement("div");
			var m = 9 * (i + 1);
			if (m > n) {
				m = n;
			}
			for (var j = 9 * i; j < m; j++) {
				d[i].appendChild(c[j]);
				d[i].appendChild(document.createTextNode(j.toString().fill(l, "0", true)));
			}
			document.getElementById("divOptionMstTrack").appendChild(d[i]);
		}
	}
	document.getElementById("divOptionMstTrack").onchange = OptionChanged;
}
function SetPkgList(l) {
	document.getElementById("selectPkgList").onchange = null;
  document.getElementById("selectPkgList").innerHTML = "";
  var o = [];
  for (var i = 0; i < l.length; i++) {
    o[i] = document.createElement("option");
    o[i].value = l[i];
    o[i].innerHTML = l[i];
    document.getElementById("selectPkgList").appendChild(o[i]);
  }
	document.getElementById("selectPkgList").onchange = ModeChanged;
}
function SetSave(d, n, e, t) {
	var a = document.getElementById("aSave");
	URL.revokeObjectURL(a.href);
	a.href = URL.createObjectURL(new Blob([d], {"type": t}));
	a.download = n + "." + e;
	a.title = a.download + "\r\n" + d.length.toByteLength(2);
	document.getElementById("divSave").style.display = "block";
};
function SetResult(m, s, d, n, e, t) {
	var w = (arguments.length > 6 ? arguments[6] : false);
  switch(m) {
  case "text":
    if (d.length == 0) {
      return;
    }
    SetSave(d, n, e, t);
    if (((Info.extension == "bin") || (Info.type == "bin")) && (d.length == 0)) {
      document.getElementById("divOperateBin").style.display = "none";
      alert("目前不支持 " + n + " 。");
      return;
    }
    var p = document.createElement("p");
    p.style.textAlign = "left";
    p.style.fontSize = "14px";
    if (w) {
      p.style.wordWrap = "break-word";
    }
    p.innerHTML = s.toHtml().replace(/\r\n/g, "<br/>");
    document.getElementById("divResult").appendChild(p);
    break;
  case "html":
    if (s.length == 0) {
      return;
    }
    d = new Uint8Array().fromText(s);
    SetSave(d, n, e, t);
    if ((Info.extension == "bin") && (s.length == 0)) {
      document.getElementById("divOperateBin").style.display = "none";
      alert("目前不支持 " + n + " 。");
      return;
    }
    var iframe = document.createElement("iframe");
    iframe.onload = function() {
      var c = this.contentDocument;
      if (this != null && c != null) {
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
  case "image":
    if (s.length == 0) {
      return;
    }
    d = new Uint8Array().fromBase64(s.split(",")[1]);
    SetSave(d, n, e, t);
    var img = document.createElement("img");
    img.src = s;
    document.getElementById("divResult").appendChild(img);
    break;
  }
};
function ReadMst() {
	if (!Data.IsValid()) {
		return;
	}
//	document.getElementById("divOperateMst").style.display = "block";
//	HideSelect("mst");
//	HideOption();
//  if (document.getElementById("inputAutoApply").checked) {
  if (true) {
    if (ChangeApply["index"]) {
      var names = GetMstList();
      var list = Data.GetValue("list");
      var indexes = [];
      for (var i = 0; i < names.length; i++) {
        var l = list.indexOf(names[i]);
        if (l != -1) {
          indexes.push(l);
        }
      }
      if (indexes.length > 0) {
        Indexes = indexes;
      }
    }
    if (ChangeApply["all"]) {
      Data.Reset("all");
    }
    else if (ChangeApply["preview"]) {
      Data.Reset("preview");
    }
    var m = 'png';//GetSelectValue("selectMstMode");
    switch (m) {
    case "bms":
    case "bme":
    case "bml":
    case "pms":
      SetResult("text", Data.ToText(Indexes, m), Data.ToBuffer(Indexes, m), Info.nameCore, m, "text/plain");
      break;
    case "vos":
      var t = GetSelectValue("selectMstvosMode");
      SetResult("text", Data.ToText(Indexes, m, t), Data.ToBuffer(Indexes, m, t), Info.nameCore, m, "application/octet-binary");
      break;
    case "mid":
      SetResult("text", Data.ToText(Indexes, m), Data.ToBuffer(Indexes, m), Info.nameCore, m, "application/octet-binary");
      break;
    case "lrc":
      SetResult("text", Data.ToText(Indexes, m), Data.ToBuffer(Indexes, m), Info.nameCore, m, "text/plain");
      break;
    case "hex":
      var t = GetSelectValue("selectMsthexMode");
      SetResult("text", Data.ToText(Indexes, m, t), Data.ToBuffer(Indexes, m, t), Info.nameCore + (t == "imd" ? Info.suffixImd : "") + "." + Info.type, m, "text/plain");
      break;
    case "tja":
      var t = GetSelectValue("selectMsttjaMode");
      SetResult("text", Data.ToText(Indexes, m, t), Data.ToBuffer(Indexes, m, t), Info.nameCore + "[" + t + "]", m, "text/plain");
      break;
    case "osu":
      var t = GetSelectValue("selectMstosuMode");
      SetResult("text", Data.ToText(Indexes, m, t), Data.ToBuffer(Indexes, m, t), Info.nameCore + "[" + t + "]", m, "text/plain");
      break;
    case "xml":
      var t = GetSelectValue("selectMstxmlMode");
      switch(t) {
      case "yddr":
      case "ydsd":
        SetResult("text", Data.ToText(Indexes, m, t), Data.ToBuffer(Indexes, m, t), Info.nameCore, m, "text/xml", t == "ydsd");
        break;
      case "mde":
        SetResult("text", Data.ToText(Indexes, m, t), Data.ToBuffer(Indexes, m, t), Info.nameCore + Info.suffixMde, m, "text/html");
        break;
      }
      break;
    case "vox":
      SetResult("text", Data.ToText(Indexes, m), Data.ToBuffer(Indexes, m), Info.nameCore, m, "text/plain");
      break;
    case "ksh":
      SetResult("text", Data.ToText(Indexes, m), Data.ToBuffer(Indexes, m), Info.nameCore, m, "text/plain");
      break;
    case "imd":
      SetResult("text", Data.ToText(Indexes, m), Data.ToBuffer(Indexes, m), Info.nameCore + Info.suffixImd, m, "application/octet-binary");
      break;
    case "mde":
      SetResult("text", Data.ToText(Indexes, m), Data.ToBuffer(Indexes, m), Info.nameCore + Info.suffixMde, m, "text/html", true);
      break;
    case "mc":
      var t = GetSelectValue("selectMstmcMode");
      SetResult("text", Data.ToText(Indexes, m, t), Data.ToBuffer(Indexes, m, t), Info.nameCore + "[" + t + "]", m, "text/plain");
      break;
    case "aff":
      SetResult("text", Data.ToText(Indexes, m), Data.ToBuffer(Indexes, m), Info.nameCore, m, "text/plain");
      break;
    case "mst":
      SetResult("text", Data.ToText(Indexes, m), Data.ToBuffer(Indexes, m), Info.nameCore, m, "text/plain");
      break;
    case "png":
    case "html":
      if (!SupportAPI("Canvas")) {
        alert("当前浏览器内核不支持Canvas API。");
        return;
      }
      (function() {
        Data.PreData("pcm");
        var t = 'imd';//GetSelectValue("selectMstdrawMode");
        switch(m) {
        case "png":
          SetResult("image", Data.ToText(Indexes, m, t), new Uint8Array(), Info.nameCore + (Info.type == "imd" ? Info.suffixImd : "") + (Info.type == "mde" ? Info.suffixMde : ""), "png", "image/png");
          break;
        case "html":
          SetResult("html", Data.ToText(Indexes, m, t), new Uint8Array(), Info.nameCore + (Info.type == "imd" ? Info.suffixImd : "") + (Info.type == "mde" ? Info.suffixMde : ""), "html", "text/html");
          break;
        }
      })();
      break;
    }
  }
}
function ReadBin() {
	if (!Data.IsValid()) {
		return;
	}
	document.getElementById("divOperateBin").style.display = "block";
	HideSelect("bin");
  var m = GetSelectValue("selectBinMode");
  switch (m) {
  case "bin":
    SetResult("text", Data.ToText(m), Data.ToBuffer(m), Info.nameCore, m, "application/octet-binary");
    break;
  case "hex":
    SetResult("text", Data.ToText(m), Data.ToBuffer(m), Info.nameCore + "." + Info.type, m, "text/plain");
    break;
  case "xml":
    SetResult("text", Data.ToText(m), Data.ToBuffer(m), Info.nameCore, m, "text/html");
    break;
  case "html":
    SetResult("html", Data.ToText(m), new Uint8Array(), Info.nameCore, m, "text/html");
    break;
  case "list":
    var n = "";
    switch (Info.nameCore) {
    case "mrock_song_client_android":
      n = "★res_imd";
      break;
    case "mrock_papasong_client":
      n = "★res_mde";
      break;
    }
    SetResult("text", Data.ToText(m), Data.ToBuffer(m), n, m, "text/html");
    break;
  case "bat":
    SetResult("text", Data.ToText(m), Data.ToBuffer(m), "★res_all", m, "text/html");
    break;
  }
}
function ReadImg() {
	if (!Data.IsValid()) {
		return;
	}
	if (!SupportAPI("Canvas")) {
		alert("当前浏览器内核不支持Canvas API。");
		return;
	}
	document.getElementById("divOperateImg").style.display = "block";
  if (SelectCount == 0) {
    var v = GetImgSize(Info.buffer);
    var s = document.getElementById("selectImgMode");
    for (var i = 0; i < s.children.length; i++) {
      if (s.children[i].value == v) {
        s.selectedIndex = i;
        break;
      }
    }
  }
  var m = GetSelectValue("selectImgMode");
  var t = (function() {
    switch(m) {
    case "480X320":
    case "140X60":
    case "140X90":
    case "210X98":
    case "1024X768":
      return "png";
      break;
    case "500X500":
    case "256X256":
      return "jpeg";
      break;
    }
  })();
  var s = document.getElementById("selectImgMode");
  var n = s.options[s.selectedIndex].text;
  SetResult("image", Data.ToText(m, t), new Uint8Array(), Info.nameCore + new FileInfo(n).name, (t == "jpeg" ? "jpg" : t), "image/" + t);
}
function ReadPak(e) {
	if (!Data.IsValid()) {
		return;
	}
  document.getElementById("divOperatePak").style.display = "block";
  var fn = GetSelectValue("selectPkgList");
  Data.Choose(fn);
  var f = new FileInfo(fn);
  var n = f.name;
  var m = f.extension;
  SetResult("text", Data.ToText(e, m), Data.ToBuffer(e), n, m, "application/octet-binary");
}
function ReadBuffer() {
	switch (Info.extension) {
	case "bms":
	case "bme":
	case "bml":
	case "pms":
	case "vos":
	case "tja":
	case "osu":
	case "vox":
	case "ksh":
	case "imd":
	case "mde":
	case "mc":
	case "aff":
	case "mst":
	case "wav":
	case "ogg":
	case "mp3":
	case "aac":
	case "m4a":
	case "m4r":
		Data = new Mst(Info);
    SetIndexList(Data.GetValue("list"));
    switch (Info.extension) {
    case "wav":
    case "ogg":
    case "mp3":
    case "aac":
    case "m4a":
    case "m4r":
      Data.PreData("pcm");
      Data.PreData("note");
      break;
    }
		break;
	case "hex":
		switch (Info.type) {
		case "vos":
		case "imd":
			Data = new Mst(Info);
      SetIndexList(Data.GetValue("list"));
			break;
		case "bin":
			Data = new Bin(Info);
			break;
		}
		break;
	case "xml":
		switch (Info.type) {
		case "yddr":
		case "ydsd":
		case "mde":
			Data = new Mst(Info);
      SetIndexList(Data.GetValue("list"));
			break;
		case "bin":
			Data = new Bin(Info);
			break;
		}
		break;
	case "bin":
		Data = new Bin(Info);
		break;
	case "bmp":
	case "jpg":
	case "gif":
	case "png":
		Data = new Img(Info);
		break;
	case "zip":
	case "apk":
	case "ipa":
	case "osz":
	case "mcz":
	case "2dx":
	case "ojm":
		Data = new Pkg(Info);
		break;
	}
}
function ReadData() {
//	HideOperate();
	switch (Info.extension) {
	case "bms":
	case "bme":
	case "bml":
	case "pms":
	case "vos":
	case "tja":
	case "osu":
	case "vox":
	case "ksh":
	case "imd":
	case "mde":
	case "mc":
	case "aff":
	case "mst":
	case "wav":
	case "ogg":
	case "mp3":
	case "aac":
	case "m4a":
	case "m4r":
		ReadMst();
		break;
	case "hex":
		switch (Info.type) {
		case "vos":
		case "imd":
			ReadMst();
			break;
		case "bin":
			ReadBin();
			break;
		}
		break;
	case "xml":
		switch (Info.type) {
		case "yddr":
		case "ydsd":
		case "mde":
			ReadMst();
			break;
		case "bin":
			ReadBin();
			break;
		}
		break;
	case "bin":
		ReadBin();
		break;
	case "bmp":
	case "jpg":
	case "gif":
	case "png":
		ReadImg();
		break;
	case "zip":
	case "apk":
	case "ipa":
	case "osz":
	case "mcz":
	case "2dx":
	case "ojm":
		ReadPak(Info.extension);
		break;
	}
}
function ReadInput(n, b) {
  Info = new FileInfo(n, b);
//  SetTrackCheck(GetSelectValue("selectTransKey"));
  SelectCount = 0;
  (function() {
    ReadBuffer();
    ReadData();
  })();
}
function forJava(filename, base64code) {
    ReadBlob(filename, new Uint8Array().fromBase64(base64code));
//    alert(FileData.ToText("png"));
    return Data;
}
function ReadBlob(n, b) {
	if (!SupportAPI("File")) {
		alert("当前浏览器内核不支持File API。");
		return;
	}
//	var r = new FileReader();
//	r.onload = function() {
    ReadInput(n, b);
//	};
//	r.readAsArrayBuffer(b);
}
function ReadFile(e) {
  if (e.target.files.length > 0) {
    ReadBlob(e.target.files[0].name, e.target.files[0]);
  }
}
function DragFile(e) {
	var n = e.dataTransfer.getData("name");
  if (n != "") {
    var r = new XMLHttpRequest();
    r.open("GET", e.dataTransfer.getData("blob"));
    r.responseType = "blob";
    r.onload = function() {
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
function ApplyChanged(e) {
  ReadData();
  if (document.getElementById("inputAutoApply").checked) {
    ChangeApply = {};
  }
}
function ModeChanged(e) {
	ApplyChanged(e);
}
function IndexChanged(e) {
	ChangeApply["index"] = true;
	ApplyChanged(e);
}
function OptionChanged(e) {
  switch (e.currentTarget.id) {
  case "selectTransKey":
    SetTrackCheck(GetSelectValue("selectTransKey"));
    break;
  case "divOptionMstTrack":
    TrackChecks = GetMstTracks();
    break;
  }
	ChangeApply["all"] = true;
	ApplyChanged(e);
}
function DrawChanged(e) {
	ChangeApply["preview"] = true;
	ApplyChanged(e);
}
function SelectChanged(e) {
	SelectCount += 1;
  ReadData();
}
function Initialize() {
  var LoadConfigParameter = (function() {
    var loadLink = function(id, url) {
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
    var loadAccept = function(id, m) {
      var v = [];
      for (var i = 0; i < m.length; i++) {
        v.push("★" + m[i].join(","));
      }
      v = v.join("\r\n");
      document.getElementById(id).title = v;
      document.getElementById(id).accept = v.replace(/★/g, "").replace(/\r\n/g, ",");
    };
    loadAccept("inputFile", [[".bms", ".bme", ".bml", ".pms", ".vos", ".hex", ".tja", ".osu", ".xml", ".vox", ".ksh", ".imd", ".mde", ".mc", ".aff", ".mst"], [".wav", ".ogg", ".mp3", ".aac", ".m4a", ".m4r"], [".bin"], [".bmp", ".jpg", ".gif", ".png"], [".zip", ".apk", ".ipa", ".osz", ".mcz", ".2dx", ".ojm"]]);
	})();
  var LoadUrlParameter = (function() {
    var s = location.search.substring(1).split("&");
    for (var i = 0; i < s.length; i++) {
      var a = s[i].split("=");
      if (a.length == 2) {
        switch (a[0].toLowerCase()) {
        case "help":
          var m = (function() {
            var r = "";
            var helphelp = "\r\n<help>\r\n　[bgcolor]　Help of bgcolor\r\n　[show]　Help of show\r\n　[check]　Help of check\r\n　[default]　Help of default\r\n　[input]　Help of input\r\n　[all]　Help above all\r\n";
            var helpbgcolor = "\r\n<bgcolor>\r\n　[#rrggbbaa]　RGBA Hex\r\n";
            var helpshow = "\r\n<show>\r\n　[version]　Version\r\n　[bgcolor]　BackgroundColor\r\n";
            var helpcheck = "\r\n<check>\r\n　[le]　LittleEndian\r\n　[api]　SupportAPI\r\n　[agent]　UserAgent\r\n";
            var helpdefault = "\r\n<default>\r\n　Trans Option\r\n　{autoapply}　[true] or [false]\r\n　{correcttime}　[0] or [1] or [3] or [4] or [12] or [48] or [192]\r\n\r\n　Trans Mode Select\r\n　{modemst}　[bms] or [bme] or [bml] or [pms] or [vos] or [hex] or [tja] or [osu] or [xml] or [vos] or [ksh] or [imd] or [mde] or [mc] or [aff] or [mst] or [png] or [html]\r\n　{modemsthex}　[imd]\r\n　{modemsttja}　[taiko] or [jube]\r\n　{modemstosu}　[osu] or [taiko] or [ctb] or [mania]\r\n　{modemstxml}　[yddr] or [ydsd] or [mde]\r\n　{modemstmc}　[key] or [step] or [dj] or [catch] or [pad] or [taiko]\r\n　{modemstdraw}　[bms] or [osu] or [imd] or [mc] or [aff]\r\n　{modebin}　[bin] or [hex] or [xml] or [html] or [list] or [bat]\r\n　{modeimg}　[480X320] or [140X60] or [140X90] or [210X98] or [1024X768]\r\n\r\n　Change Option\r\n　{xaxisremove}　[true] or [false]\r\n　{yaxisremove}　[true] or [false]\r\n　{zaxisremove}　[true] or [false]\r\n　{xaxisextend}　[true] or [false]\r\n　{yaxisextend}　[true] or [false]\r\n　{zaxisextend}　[true] or [false]\r\n　{invalidkeyremove}　[true] or [false]\r\n　{flowremove}　[true] or [false]\r\n　{transformremove}　[true] or [false]\r\n　{invalidkeyextend}　[true] or [false]\r\n　{flowextend}　[true] or [false]\r\n　{transformextend}　[true] or [false]\r\n　{transkey}　[1] to [18]\r\n　{mirrortrack}　[true] or [false]\r\n　{randomtrack}　[true] or [false]\r\n　{randomnote}　[true] or [false]\r\n　{changespeed}　[0.33] or [0.5] or [1] to [3]\r\n　{linksingle}　[true] or [false]\r\n　{linklong}　[true] or [false]\r\n　{linkslide}　[true] or [false]\r\n　{transoblique}　[true] or [false]\r\n　{nooblique}　[true] or [false]\r\n　{noslide}　[true] or [false]\r\n　{nolong}　[true] or [false]\r\n　{nosingle}　[true] or [false]\r\n　{nointersect}　[true] or [false]\r\n　{nocross}　[true] or [false]\r\n\r\n　To Preview Parameter\r\n　{drawbackground}　[true] or [false]\r\n　{drawtrack}　[true] or [false]\r\n　{drawframe}　[true] or [false]\r\n　{drawbeatline}　[true] or [false]\r\n　{drawcombo}　[true] or [false]\r\n　{drawdetermine}　[true] or [false]\r\n　{drawtime}　[true] or [false]\r\n　{drawbeat}　[true] or [false]\r\n　{drawnote}　[true] or [false]\r\n\r\n　To Preview Html Parameter\r\n　{scalex}　[1] or [2.5] or [5] or [7.5]\r\n　{scaley}　[1] to [4]\r\n";
            var helpinput = "\r\n<input>\r\n　[filename.ext|filebufferbase64string]　Input File Info\r\n";
            var v = a[1].split(",");
            for (var j = 0; j < v.length; j++) {
              switch (v[j].toLowerCase()) {
              case "help":
                r += helphelp;
                break;
              case "bgcolor":
                r += helpbgcolor;
                break;
              case "show":
                r += helpshow;
                break;
              case "check":
                r += helpcheck;
                break;
              case "default":
                r += helpdefault;
                break;
              case "input":
                r += helpinput;
                break;
              case "all":
                return helphelp + helpbgcolor + helpshow + helpcheck + helpdefault + helpinput;
                break;
              }
            }
            return r;
          })();
          document.write(("【" + a[0] + "】\r\n\r\nexpurl/rmstZ.html?key1=value1&key2=value2,value3|value4&key3=name1:value5,value6;name2:value7|value8\r\n\r\n<key>　{name}　[value]　explain\r\n" + m).toHtml().replace(/\r\n/g, "</br>"))
          break;
        case "bgcolor":
          var v = a[1].split(",");
          var c = v[v.length - 1].substring(0, 8).fill(8, "0", false);
          if (c.isHex()) {
            var a = parseInt(c.substr(0, 2), 16) / 256;
            var r = parseInt(c.substr(2, 2), 16);
            var g = parseInt(c.substr(4, 2), 16);
            var b = parseInt(c.substr(6, 2), 16);
            document.body.style.backgroundColor = "rgba(" + r + ", " + g + ", " + b + ", " + a + ")";
          }
          break;
        case "show":
          var m = (function() {
            var r = "";
            var showversion = "\r\nVersion：" + Version + "\r\n";
            var showbgcolor = "\r\nBgColor：" + (document.body.style.backgroundColor == "" ? "rgb(255, 255, 255)" : document.body.style.backgroundColor) + "\r\n";
            var v = a[1].split(",");
            for (var j = 0; j < v.length; j++) {
              switch (v[j].toLowerCase()) {
              case "version":
                r += showversion;
                break;
              case "bgcolor":
                r += showbgcolor;
                break;
              case "all":
                return showversion + showbgcolor;
                break;
              }
            }
            return r;
          })();
          alert("【" + a[0] + "】\r\n" + m);
          break;
        case "check":
          var m = (function() {
            var r = "";
            var checkle = "\r\nLittleEndian：" + LittleEndian + "\r\n";
            var checkapi = (function() {
              var n = ["File", "TypedArrays", "Canvas", "Audio", "MIDI", "UserMedia", "Gamepads"];
              var m = [];
              for (var k = 0; k < n.length; k++) {
                m.push("\r\nSupport" + n[k] + "API：" + SupportAPI(n[k]));
              }
              return m.join("") + "\r\n";
            })();
            var checkagent = (function() {
              var n = ["Chrome", "Mozilla", "MSIE", "Safari", "AppleWebKit", "UCBrowser"];
              var m = [];
              for (var k = 0; k < n.length; k++) {
                m.push("\r\nInclude" + n[k] + "Agent：" + IncludeAgent(n[k]));
              }
              return m.join("") + "\r\n";
            })();
            var v = a[1].split(",");
            for (var j = 0; j < v.length; j++) {
              switch (v[j].toLowerCase()) {
              case "le":
                r += checkle;
                break;
              case "api":
                r += checkapi;
                break;
              case "agent":
                r += checkagent;
                break;
              case "all":
                return checkle + checkapi + checkagent;
                break;
              }
            }
            return r;
          })();
          alert("【" + a[0] + "】\r\n" + m);
          break;
        case "default":
          var v = a[1].split(";");
          for (var j = 0; j < v.length; j++) {
            var c = v[j].split(":");
            if (c.length == 2) {
              switch(c[0].toLowerCase()) {
              case "modemst":
              case "correcttime":
              case "modemsthex":
              case "modemstxml":
              case "modemsttja":
              case "modemstosu":
              case "modemstmc":
              case "modemstdraw":
              case "transkey":
              case "changespeed":
              case "scalex":
              case "scaley":
              case "modebin":
              case "modeimg":
                var m = {"modemst": "selectMstMode",
                         "correcttime": "selectCorrectTime",
                         "modemsthex": "selectMsthexMode",
                         "modemstxml": "selectMstxmlMode",
                         "modemsttja": "selectMsttjaMode",
                         "modemstosu": "selectMstosuMode",
                         "modemstmc": "selectMstmcMode",
                         "modemstdraw": "selectMstdrawMode",
                         "transkey": "selectTransKey",
                         "changespeed": "selectChangeSpeed",
                         "scalex": "selectMstScaleX",
                         "scaley": "selectMstScaleY",
                         "modebin": "selectBinMode",
                         "modeimg": "selectImgMode"};
                if (typeof m[c[0]] !== "undefined") {
                  var l = c[1].split("|");
                  for (var k = 0; k < l.length; k++) {
                    SetSelectValue(m[c[0]], l[k]);
                  }
                }
                break;
              case "autoapply":
              case "xaxisremove":
              case "yaxisremove":
              case "zaxisremove":
              case "xaxisextend":
              case "yaxisextend":
              case "zaxisextend":
              case "invalidkeyremove":
              case "flowremove":
              case "transformremove":
              case "invalidkeyextend":
              case "flowextend":
              case "transformextend":
              case "mirrortrack":
              case "randomtrack":
              case "randomnote":
              case "linksingle":
              case "linklong":
              case "linkslide":
              case "transoblique":
              case "nooblique":
              case "noslide":
              case "nolong":
              case "nosingle":
              case "nointersect":
              case "nocross":
              case "drawbackground":
              case "drawtrack":
              case "drawframe":
              case "drawbeatline":
              case "drawcombo":
              case "drawdetermine":
              case "drawtime":
              case "drawbeat":
              case "drawbote":
                var m = {"autoapply": "inputAutoApply",
                         "xaxisremove": "inputXAxisRemove",
                         "yaxisremove": "inputYAxisRemove",
                         "zaxisremove": "inputZAxisRemove",
                         "xaxisextend": "inputXAxisExtend",
                         "yaxisextend": "inputYAxisExtend",
                         "zaxisextend": "inputZAxisExtend",
                         "invalidkeyremove": "inputInvalidKeyRemove",
                         "flowremove": "inputFlowRemove",
                         "transformremove": "inputTransformRemove",
                         "invalidkeyextend": "inputInvalidKeyExtend",
                         "flowextend": "inputFlowExtend",
                         "transformextend": "inputTransformExtend",
                         "mirrortrack": "inputMirrorTrack",
                         "randomtrack": "inputRandomTrack",
                         "randomnote": "inputRandomNote",
                         "linksingle": "inputLinkSingle",
                         "linklong": "inputLinkLong",
                         "linkslide": "inputLinkSlide",
                         "transoblique": "inputTransOblique",
                         "nooblique": "inputNoOblique",
                         "noslide": "inputNoSlide",
                         "nolong": "inputNoLong",
                         "nosingle": "inputNoSingle",
                         "nointersect": "inputNoIntersect",
                         "nocross": "inputNoCross",
                         "drawbackground": "inputDrawBackground",
                         "drawtrack": "inputDrawTrack",
                         "drawframe": "inputDrawFrame",
                         "drawbeatline": "inputDrawBeatline",
                         "drawcombo": "inputDrawCombo",
                         "drawdetermine": "inputDrawDetermine",
                         "drawtime": "inputDrawTime",
                         "drawbeat": "inputDrawBeat",
                         "drawnote": "inputDrawNote"};
                if (typeof m[c[0]] !== "undefined") {
                  var l = c[1].split("|");
                  for (var k = 0; k < l.length; k++) {
                    if (l[k] == "true") {
                      document.getElementById(m[c[0]]).checked = "checked";
                      break;
                    }
                    else if (l[k] == "false") {
                      document.getElementById(m[c[0]]).checked = "";
                      break;
                    }
                  }
                }
                break;
              case "transcheck":
                var l = c[1].split(",");
                var tc = [];
                for (var k = 0; k < l.length; k++) {
                  if (l[k] == "false") {
                    tc.push(false);
                  }
                  else {
                    tc.push(true);
                  }
                }
                SetTrackCheck(GetSelectValue("selectTransKey"));
                break;
              }
            }
          }
          break;
        case "input":
          var v = a[1].split(",");
          for (var j = 0; j < v.length; j++) {
            var f = v[j].split("|");
            if ((f.length == 2) && (j == 0)) {
              ReadInput(f[0], new Uint8Array().fromBase64(f[1]));
            }
          }
          break;
        }
      }
    }
	})();
}
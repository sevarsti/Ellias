var _lastRschDocAdapterTarget;
var RschDocAdapter = Class.create({
    initialize : function(target, stkCtrl){
        this.target = target;
        this.stkCtrl = stkCtrl; //stock code input
    },

    selectDoc : function(src){
        //alert(this.stkCtrl + "\t" + $(this.stkCtrl));        
        var stkCode = src.stkCode;
        if(!src.stkCode){
            stkCode = $(this.stkCtrl).value;
        }
        var u = '/rschdoc/docSearch.do?method=docSearch(amp)target=' + this.target + '(amp)queryForm.prototype.brokerId=1';
        if(stkCode!=null && stkCode!=''){
            u += '(amp)queryForm.prototype.stkCode=' + stkCode;
        }
        ShowWindow(u,'选择报告',0,0,750,450,'no','yes');
    },

    getRschDoc : function(docId){
        PflDwrController.getRschDoc(docId, this.remoteCallBack);
        _lastRschDocAdapterTarget = this.target;
    },

    remoteCallBack : function(res){
        if(res!=null){
            var s = '<a href=\"javascript:RschDocDisplay(' + res.id + ')\">' + res.title + '</a>';
            var b = document.createElement("input");
            b.type = "button";
            b.title = "删除相关报告";
            b.onclick = function(){
                    new RschDocAdapter(this.target).removeCurrentDoc();
                }
            b.value = "-";
            b.className = "btnDelete";
            b.target = _lastRschDocAdapterTarget;

            //s += "<button class=\"btnDelete\" title=\"删除相关报告\"
            //onclick=\"(new RschDocAdapter(this.target)).removeCurrentDoc();\">-</button>";
            var p = document.getElementById(_lastRschDocAdapterTarget); //span
            p.innerHTML=s;
            p.appendChild(b);

            var ipt = document.getElementById('input_' + _lastRschDocAdapterTarget);    //input
            ipt.value=res.id;

            var iptype = document.getElementById('inputType_' + _lastRschDocAdapterTarget);    //add by Elephant 20110103
            iptype.value=res.brokerId;
        }else{
            alert("无法找到报告。");
        }
    },

    removeCurrentDoc : function(){
        document.getElementById(this.target).innerHTML='';          //set span inner empty
        document.getElementById('input_' + this.target).value='';   //set input value empty
        document.getElementById('inputType_' + this.target).value='';   //add by Elephant 20110103
    }
});


function windowReturn(t, docId, docTitle){
    //alert(t + '\t' + docId + '\t' + docTitle);
    var o = new RschDocAdapter(t);
    o.getRschDoc(docId);
}


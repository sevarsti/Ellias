var _lastRschDocAdapterTarget;
var _title;
var RschDocAdapter = Class.create({
     initialize : function(target){
        this.target = target;
    },

    selectDoc1 : function(src) {
        var stkCode = src.stkCode;
        var u = '/rschdocapp/rschdoc/selectDoc.do?method=realPflDocSearch(amp)target=' + this.target + '(amp)brokerId=1';
        if (stkCode != null && stkCode != '') {
            u += '(amp)stkCode=' + stkCode;
        }
        ShowWindow(u, '选择报告', 0, 0, 750, 450, 'no', 'yes');
    },

    getRschDoc: function(docId) {
        PflDwrController.getRschDoc(docId, this.remoteCallBack);
        _lastRschDocAdapterTarget = this.target;
    },
    remoteCallBack: function(res) {
        if (res != null) {  
            var s = '<a href=\"javascript:RschDocDisplay(' + res.id + ')\">' + _title + '</a>';
//            var s = '<a href=\"javascript:RschDocDisplay(' + res.id + ')\">' + res.title + '</a>';
            var p = document.getElementById(_lastRschDocAdapterTarget);
            p.innerHTML = s;
            var ipt = document.getElementById('input_' + _lastRschDocAdapterTarget);
            ipt.value = res.id;
        } else {
            alert("无法找到报告。");
        }
    }
});

function windowReturn1(t, docId, docTitle) {
    var o = new RschDocAdapter(t);
    _title = docTitle;
    o.getRschDoc(docId);
}
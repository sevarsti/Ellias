/*
 * Ext JS Library 2.1
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 *
 * http://extjs.com/license
 */

Ext.onReady(function(){

    var ds = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({
            url: '/portfolio/stkrsch/spirit.do?method=jsonBond'
        }),
        reader: new Ext.data.JsonReader({
            root: 'bonds',
            totalProperty: 'totalCount',
            id: 'stkCode,mktCode,stkType'
        }, [
            {name: 'stkCode', mapping: 'stkCode'},
            {name: 'stkName', mapping: 'stkName'},
                {name: 'mktCode', mapping: 'mktCode',type:'int'},
                {name: 'closePrice', mapping: 'closePrice'},
                {name: 'stkType', mapping: 'stkType'},
                {name: 'displayName', mapping: 'displayName'}
        ])
    });

    var ds2 = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({
            url: '/portfolio/stkrsch/spirit.do?method=jsonBond'
        }),
        reader: new Ext.data.JsonReader({
            root: 'bonds',
            totalProperty: 'totalCount',
            id: 'stkCode'
        }, [
            {name: 'stkCode', mapping: 'stkCode'},
            {name: 'stkName', mapping: 'stkName'},
                {name: 'mktCode', mapping: 'mktCode',type:'int'},
                {name: 'closePrice', mapping: 'closePrice'},
                {name: 'stkType', mapping: 'stkType'},
                {name: 'displayName', mapping: 'displayName'}
        ])
    });

    // Custom rendering Template
    var resultTpl = new Ext.XTemplate(
        '<tpl for="."><div class="search-item">',
            '<span style="width:70">{stkCode}</span><span style="width:90">{stkName}</span>',
        '</div></tpl>'
    );


    function txtOnFocus(evt,t,o){
        var _t = Ext.Element.get(t.id);
        var n = _t.next();
        if(n && n.hasClass('spirit-append')){
            n.dom.innerText = '';
        }

            //t.disabled = true;

            /**
               var de = new Ext.util.DelayedTask(function(){
                   this.getEl().focus();
                   this.getEl().dom.click();
                   }, search, [{targetId:t.id}]);
               de.delay(1000);
           }
           **/
    }

    function addSearch(t){
        var search = new Ext.form.ComboBox({
                store: ds,
                displayField:'stkCode',
                    valueField:'stkCode',
                //typeAhead: true,
                loadingText: 'Searching...',
                //emptyText:'Select Work...',
                //selectOnFocus:true,
                minChars:1,
                width: 80,
                pageSize: 20,
                listWidth:300,
                hideTrigger:true,
                onSelect: function(r){
                    var _t = Ext.Element.get(t.id);
                    t.value = r.data.stkCode;
                    var p = document.getElementsByClassName('xn-hidden-bondMktcode');
                    if(p)
                    {
                        p[0].value = r.data.mktCode;
                        matchMktCode = true;
                    }
                    p = document.getElementsByClassName('xn-hidden-bondStkType');
                    if(p)
                    {
                        p[0].value = r.data.stkType;
                    }
                    if (t.value)
                    {
                        _t.applyStyles("background-color:yellow");
                        ds2.load({params:{match:t.value,limit:20,start:0},
                            callback:remoteCallback, scope:_t});
                    }
                    else
                    {
                        _t.applyStyles("background-color:white");
                    }
                    this.collapse();
                },
                //triggerAction: 'all',
                tpl: resultTpl,
                lazyInit: true,
                lazyRender: true,
                focusClass:'',
                itemSelector: 'div.search-item'
            });

           search.applyToMarkup(t);
           t.search = search;
    }

    function txtOnClick(evt,t,o){
//        if(t.stkType) {
//            ds.baseParams.stkType = t.stkType;
//        }
//        if(t.seqKey) {
//            ds.baseParams.seqKey = t.seqKey;
//        }
        var p = document.getElementsByClassName('xn-hidden-bondMktcode');
        if(p)
        {
            p[0].value = 0;
        }
        p = document.getElementsByClassName('xn-hidden-bondStkType');
        if(p)
        {
            p[0].value = 0;
        }
        ds.load({params:{start:0,limit:20}});
    }

    function txtOnBlur(evt, t, o){
        //alert(Object.toJSON(evt));
        //alert(t.id);
        //alert(Object.toJSON(o));
        var _t = Ext.Element.get(t.id);
        if(t.value){
            _t.applyStyles("background-color:yellow");
            var p = document.getElementsByClassName('xn-hidden-bondMktcode');
            if(p) {
                ds2.baseParams.mktCode = p[0].value;
            } else {
                ds2.baseParams.mktCode = 0;
            }
            ds2.load({params:{match:t.value,limit:20,start:0},
                callback:remoteCallback, scope:_t});
        }else{
            _t.applyStyles("background-color:white");
        }
    };


    function remoteCallback(r, options, success){
		if(success){
			var t = options.scope;
			if(r.length==1){
				var d = r[0].data;
				var s = d.stkName;
                var closePrice = d.closePrice;

                if(t && t.dom && document.getElementById(t.id).value != ''){
                    t.applyStyles("border:1px solid silver;background-color:lightgreen");

                    var p = t.parent('div.xn-bond-spirit-container');
                    if(p){
                        var h = p.child('.spirit-append');
                        if(h){
                            h.dom.innerText=s;
                        }
                        if (!isNaN(closePrice)) {
                            var c = p.child('.spirit-append_bondClosePrice');
                            if (c) {
                                c.dom.innerText = closePrice;
                            }
                            var hc = p.child('input.xn-hidden-bondClosePrice');
                            if (hc) {
                                hc.dom.value = closePrice;
                            }
                        }
                    }
                    localOnStockReady(t,d);
                }
			}else{
                t.applyStyles("border:1px solid silver;background-color:red");
			}
		}else{
			alert('request failed');
		}
	};

    function localOnStockReady(srcEle, data){
        var p = srcEle.parent('div.xn-bond-spirit-container');
        if(p){
            var h = p.child('input.xn-hidden-bondMktcode');
            if(h){
                h.dom.value=data.mktCode;
            }
            h = p.child('input.xn-hidden-bondStkType');
            if(h) {
                h.dom.value=data.stkType;
            }
        }
    };

    var ss = Ext.select('.xn-bond-spirit');
	for(var i=0;i<ss.getCount();i++){
		//alert(ss.item(i).dom.id);
        var el = ss.item(i);

        el.addListener("focus",txtOnFocus);
        el.addListener("click",txtOnClick);
        el.addListener("blur",txtOnBlur);

        addSearch(el.dom);
    };

    ds2.on('beforeload', function()
    {
        var mktcode = 0, stktype = 0;
        var p = document.getElementsByClassName('xn-hidden-bondMktcode');
        if(p)
        {
            mktcode = p[0].value;
        }
        p = document.getElementsByClassName('xn-hidden-bondStkType');
        if(p)
        {
            stktype = p[0].value;
        }
        Ext.apply(this.baseParams,
        {
            'stkCode':el.value,
            'mktCode':mktcode,
            'stkType':stktype
        });
    });

});
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
            url: '/portfolio/stkrsch/spirit.do?method=jsonFund'
        }),
        reader: new Ext.data.JsonReader({
            root: 'funds',
            totalProperty: 'totalCount',
            id: 'code'
        }, [
            {name: 'code', mapping: 'code'},
            {name: 'name', mapping: 'name'},
                {name: 'pinyin', mapping: 'pinyin'},
                {name: 'type', mapping: 'type',type:'int'},
                {name: 'mktCode', mapping: 'mktCode',type:'int'}
        ])
    });

    var ds2 = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({
            url: '/portfolio/stkrsch/spirit.do?method=jsonFund'
        }),
        reader: new Ext.data.JsonReader({
            root: 'funds',
            totalProperty: 'totalCount',
            id: 'code'
        }, [
            {name: 'code', mapping: 'code'},
            {name: 'name', mapping: 'name'},
                {name: 'pinyin', mapping: 'pinyin'},
                {name: 'type', mapping: 'type',type:'int'},
                {name: 'mktCode', mapping: 'mktCode',type:'int'}
        ])
    });

    // Custom rendering Template
    var resultTpl = new Ext.XTemplate(
        '<tpl for="."><div class="search-item">',
            '<span style="width:80">{code}</span><span style="width:100">{name}</span>',
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
                displayField:'code',
                    valueField:'code',
                //typeAhead: true,
                loadingText: 'Searching...',
                //emptyText:'Select Work...',
                //selectOnFocus:true,
                minChars:1,
                width: 80,
                pageSize: 20,
                listWidth:300,
                hideTrigger:true,
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
        if(t.portfolioType) {
            ds.baseParams.portfolioType = t.portfolioType;
        }
//        ds.baseParams={};
        ds.load({params:{start:0,limit:20}});
    }

    function txtOnBlur(evt, t, o){
        if(t.portfolioType) {
            ds2.baseParams.portfolioType = t.portfolioType;
        }
        //alert(Object.toJSON(evt));
        //alert(t.id);
        //alert(Object.toJSON(o));
        var _t = Ext.Element.get(t.id);

        if(t.value){
            _t.applyStyles("background-color:yellow");
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
				var s = d.name;
				if(t && t.dom){
					t.applyStyles("border:1px solid silver;background-color:lightgreen");

                    var p = t.parent('div.xn-fund-spirit-container');
                    if(p){
                        var h = p.child('.spirit-append');
                        if(h){
                            h.dom.innerText=s;
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
        var p = srcEle.parent('div.xn-fund-spirit-container');
        if(p){
            var h = p.child('input.xn-hidden-mktcode');
            if(h){
                h.dom.value=data.mktCode;
            }
        }
    };

    var ss = Ext.select('.xn-fund-spirit');
	for(var i=0;i<ss.getCount();i++){
		//alert(ss.item(i).dom.id);
        var el = ss.item(i);

        el.addListener("focus",txtOnFocus);
        el.addListener("click",txtOnClick);
        el.addListener("blur",txtOnBlur);

        addSearch(el.dom);
    };

});
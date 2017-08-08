<script type="text/javascript">
function $GE(id){ return document.getElementById(id); }
var sinaGameDialogwwlogin = {
	_body : '' ,
	_top : 'center',
	_left : 'center',
	_offset : 0 ,
	_mask : true ,
	_lastScrollTop : 0,
	_show : false,
	_lastInMode : 'html',

	show : function(title,html,width){
		this.init();
		//$GE('sina_game_dialog_ww_login_top_title').innerHTML = title;
		//$GE('sina_game_dialog_ww_login_top_title').style.width = width - 30 + 'px';

		if(typeof(html) == 'string')
			$GE('sina_game_dialog_ww_login_text').innerHTML = html;
		else if(typeof(html) == 'object'){
			sinaGameDialogwwlogin._lastInMode = html ;
			try{
				$GE('sina_game_dialog_ww_login_text').innerHTML = '';
				$GE('sina_game_dialog_ww_login_text').appendChild(html);
			}catch(e){}
		}
		if(typeof(width) == 'number') width += 'px';
		$GE('sina_game_dialog_ww_login').style.width = width ;
		$GE('sina_game_dialog_ww_login_button').style.display = 'none';
		this.showAgain();
	},

	iframe : function(title,url,width,height){
		return sinaGameDialogwwlogin.show(title
			,'<iframe id="sina_game_dialog_ww_login_html_iframe" name="sina_game_dialog_ww_login_html_iframe" frameborder="0" src="' + url + '" width="' + (width-16) + '" height="' + height + '"></iframe>'
			,width);
	},

	showOnce : function(title,html){
		$GE('sina_game_dialog_ww_login_top_title').innerHTML = title;
		$GE('sina_game_dialog_ww_login_top_title').style.width = width - 30 + 'px';
		$GE('sina_game_dialog_ww_login_text').innerHTML = html;
	},

	close : function(){
		$GE('sina_game_dialog_ww_login_box').style.display = 'none';
		$GE('sina_game_dialog_ww_login').style.display = 'none';

		if(document.detachEvent){
			document.detachEvent('onresize',sinaGameDialogwwlogin.reseizeFun);
		}else{
			document.removeEventListener('resize',sinaGameDialogwwlogin.reseizeFun,false);
		}
		this._top ='center' ;
		this._left = 'center';
		this._offset = 0 ;
		this._mask = true ;
		this._lastScrollTop = 0;
		this._show = false ;

		if($GE('sina_game_dialog_ww_login_html_iframe')) $GE('sina_game_dialog_ww_login_html_iframe').src='';
		if($GE('sina_game_dialog_ww_login_text_in')) $GE('sina_game_dialog_ww_login_text_in').innerHTML = '';

	},

	//带两个按钮的面板
	confirm : function(title,html,width,btn1,btn2,btnfun1,btnfun2,ico){
		this.init();
		$GE('sina_game_dialog_ww_login_top_title').innerHTML = title;
		$GE('sina_game_dialog_ww_login_top_title').style.width = width - 30 + 'px';

		if(ico == 0) ico = 'vdialog_ico_alert.gif';
		else if(ico == 1) ico = 'vdialog_ico_con.gif';
		else ico = 'vdialog_ico_con.gif';

		ico = '../images/' + ico;

		$GE('sina_game_dialog_ww_login_text').innerHTML = '<div id="sina_game_dialog_ww_login_ico"><img src="'+ ico +'" id="sina_game_dialog_ww_login_ico_img" /></div><div id="sina_game_dialog_ww_login_text_in"></div>';
		$GE('sina_game_dialog_ww_login_text_in').innerHTML += html ;

		if(typeof(width) == 'number'){
			if(!document.all)
				$GE('sina_game_dialog_ww_login_text_in').style.width = width - 88 + 'px';
			width += 'px';
		}
		$GE('sina_game_dialog_ww_login').style.width = width ;
		$GE('sina_game_dialog_ww_login_button').style.display = 'block';
		$GE('sina_game_dialog_ww_login_button2').style.display = 'inline';
		$GE('sina_game_dialog_ww_login_button1').value = btn1;
		$GE('sina_game_dialog_ww_login_button2').value = btn2;
		$GE('sina_game_dialog_ww_login_button1').onclick = btnfun1 ;
		$GE('sina_game_dialog_ww_login_button2').onclick = btnfun2 ;

		var inputs  = $GE('sina_game_dialog_ww_login').getElementsByTagName('input');
		var addEvent = function(obj,eventType,func){if(obj.attachEvent){obj.attachEvent("on" + eventType,func);}else{obj.addEventListener(eventType,func,false)}};

		var onkey = function(event){
			var e = event || window.event;
			var key = window.event ? e.keyCode:e.which;
			if(key==13)	btnfun1();
			return false ;
		}
		for(var i=0;i<inputs.length;i++){
			addEvent(inputs[i],'keyup',onkey);
		}
		this.showAgain();
	},

	alert : function(title,html,width,btn1,btnfun1,ico){
		this.init();

		if(ico == 0) ico = 'vdialog_ico_alert.gif';
		else if(ico == 1) ico = 'vdialog_ico_con.gif';
		else ico = 'vdialog_ico_alert.gif';

		ico = 'http://event.games.sina.com.cn/common/image/' + ico;

		$GE('sina_game_dialog_ww_login_top_title').innerHTML = title;
		$GE('sina_game_dialog_ww_login_top_title').style.width = width - 30 + 'px';
		$GE('sina_game_dialog_ww_login_text').innerHTML = '<div id="sina_game_dialog_ww_login_ico"><img src="' + ico + '" id="sina_game_dialog_ww_login_ico_img" /></div><div id="sina_game_dialog_ww_login_text_in"></div>';
		$GE('sina_game_dialog_ww_login_text_in').innerHTML += html ;
		if(typeof(width) == 'number'){
			if(!document.all)
				$GE('sina_game_dialog_ww_login_text_in').style.width = width - 88 + 'px';
			width += 'px';
		}
		$GE('sina_game_dialog_ww_login').style.width = width ;
		$GE('sina_game_dialog_ww_login_button').style.display = 'block';
		$GE('sina_game_dialog_ww_login_button1').value = btn1;
		$GE('sina_game_dialog_ww_login_button2').style.display = 'none';
		$GE('sina_game_dialog_ww_login_button1').onclick = btnfun1 ;

		this.showAgain();
	},

	position : 'absolute',
	init : function(){
		$GE('sina_game_dialog_ww_login').onkeypress = null;
		this._show = true ;
		if(sinaGameDialogwwlogin._lastInMode != 'html') $GE('sina_game_dialog_ww_login_box_recycle').appendChild(sinaGameDialogwwlogin._lastInMode);
		if(this._init) return ;

		this._body = document.compatMode == 'BackCompat' ? document.body : document.documentElement ;

		if(document.compatMode == 'BackCompat' && document.all){
			$GE('sina_game_dialog_ww_login_top').style.height = 20 + 'px';
		}else{
			if (!/MSIE [1-6].0/ig.test(navigator.appVersion)){
				this.position = 'fixed';
			}

			if (/MSIE [7-9].0/ig.test(navigator.appVersion)){
				this.position = 'fixed';
			}
		}

		$GE('sina_game_dialog_ww_login').style.position = this.position ;
		$GE('sina_game_dialog_ww_login_box').style.position = this.position ;

		if(this.position == 'absolute'){
			//add Event
			if(window.attachEvent){
				window.attachEvent('onscroll',sinaGameDialogwwlogin.scoll);
			}else{
				window.addEventListener('scroll',sinaGameDialogwwlogin.scoll,false);
			}
		}else{
			$GE('sina_game_dialog_ww_login_box').style.top = 0 ;
			$GE('sina_game_dialog_ww_login_box').style.bottom = 0 ;
		}


		this._init = true ;
	},

	reseizeFun : function(){
		$GE('sina_game_dialog_ww_login_box').style.height = Math.max(sinaGameDialogwwlogin._body.clientHeight,sinaGameDialogwwlogin._body.scrollHeight) + 'px';
		$GE('sina_game_dialog_ww_login_box').style.width = Math.max(sinaGameDialogwwlogin._body.clientWidth,sinaGameDialogwwlogin._body.scrollWidth) + 'px';
		sinaGameDialogwwlogin.adjust();

	},
	showAgain :function(){
		$GE('sina_game_dialog_ww_login_box').style.height = Math.max(sinaGameDialogwwlogin._body.clientHeight,sinaGameDialogwwlogin._body.scrollHeight) + 'px';
		$GE('sina_game_dialog_ww_login_box').style.width = Math.max(sinaGameDialogwwlogin._body.clientWidth,sinaGameDialogwwlogin._body.scrollWidth) + 'px';

		$GE('sina_game_dialog_ww_login_box_in').style.height = Math.max(sinaGameDialogwwlogin._body.clientHeight,sinaGameDialogwwlogin._body.scrollHeight) + 'px';
		$GE('sina_game_dialog_ww_login_box_in').style.width = Math.max(sinaGameDialogwwlogin._body.clientWidth,sinaGameDialogwwlogin._body.scrollWidth) + 'px';

		//$GE('sina_game_dialog_ww_login').onkeypress = null;

		setTimeout("$GE('sina_game_dialog_ww_login').style.display = 'block'; sinaGameDialogwwlogin.adjust();",25);

		if(this._mask){
			$GE('sina_game_dialog_ww_login_box').style.display = 'block';
			if(window.attachEvent){
				window.attachEvent('onresize',sinaGameDialogwwlogin.reseizeFun);
			}else{
				window.addEventListener('resize',sinaGameDialogwwlogin.reseizeFun,false);
			}
		}
	},

	getTop : function(){
		var top ;
		if(this._top == 'center'){
			mtop =  (this._body.clientHeight - $GE('sina_game_dialog_ww_login').clientHeight)/2 ;
		}else if(this._top == 'top'){
			mtop = this._offset ;
		}else if(this._top == 'bottom'){
			mtop = this._body.clientHeight - $GE('sina_game_dialog_ww_login').clientHeight - this._offset ;
		}else{
			mtop = this._top ;
		}
		mtop = parseInt(mtop);
		if(this.position == 'absolute'){
			mtop += this._body.scrollTop ;
			this._lastScrollTop = this._body.scrollTop ;
		}
		if(mtop < 0 ) mtop = 0;
		return mtop ;
	},

	getLeft : function(){
		var mleft = 0;
		if(this._left == 'center'){
			mleft = (this._body.clientWidth - $GE('sina_game_dialog_ww_login').clientWidth)/2 ;
		}else if(this._left == 'left'){
			mleft = this._offset ;
		}else if(this._left == 'right'){
			mleft = this._body.clientWidth - $GE('sina_game_dialog_ww_login').clientWidth - this._offset ;
		}else{
			mleft = this._left ;
		}
		mleft = parseInt(mleft);
		if(this.position == 'absolute')	mleft += this._body.scrollLeft ;
		if(mleft < 0 ) mleft = 0 ;
		return mleft ;
	},

	adjust : function(){
		//$GE('sina_game_dialog_ww_login_iframe').height =  Math.max(sinaGameDialogwwlogin._body.clientHeight,sinaGameDialogwwlogin._body.scrollHeight);
		var mtop = this.getTop();
		var mleft = this.getLeft();

		$GE('sina_game_dialog_ww_login').style.top = mtop + 'px' ;
		$GE('sina_game_dialog_ww_login').style.left = mleft + 'px' ;
	},

	adjustTopSlowly : function(){

		var firstTop =  parseInt($GE('sina_game_dialog_ww_login').style.top);
		var mtop = firstTop - sinaGameDialogwwlogin._lastScrollTop + sinaGameDialogwwlogin._body.scrollTop  ;

		var a___ = 3 ; //加速度
		var s___ = Math.abs(firstTop - mtop) ;
		var counter = 0 ;
		var up___ = firstTop > mtop ;

		var v = Math.sqrt(2*a___*s___) ;
		var t = Math.sqrt(2*s___/a___) ;

		var goHome = function(){
			counter ++ ;
			var thisS = v*counter - 1/2 * a___ * counter * counter ;
			if(thisS >= s___ || thisS < 0 || counter > t){
				$GE('sina_game_dialog_ww_login').style.top = mtop + 'px'  ;
				counter = 0 ;
				clearInterval(si___);
				return ;
			}
			if(up___){
				$GE('sina_game_dialog_ww_login').style.top = firstTop - thisS + 'px'  ;
			}else{
				$GE('sina_game_dialog_ww_login').style.top = firstTop + thisS + 'px'  ;
			}
		}
		var si___ = setInterval(goHome,20) ;

		//重新记录原来的 scrollTop
		sinaGameDialogwwlogin._lastScrollTop = sinaGameDialogwwlogin._body.scrollTop ;

	},

	/* drag */
    drag : function(event){
		var event=event||window.event;
		if (typeof event.which == "undefined") {
			event.which = event.button;
		}
	    if (event.which != 1 ) {
	        return true;
    	}

		var darging = function(event){
			event=event||window.event;
			if (typeof event.which == "undefined") {
				event.which = event.button;
			}
			if(event.which == 0 ) {
				endDarg();
				return false;
			}
			var _clientX = event.clientY;
			var _clientY = event.clientX;
			if (element.lastMouseX == _clientY && element.lastMouseY == _clientX) {
				return false;
			}
			var _lastX = parseInt(element.style.top);
			var _lastY = parseInt(element.style.left);
			var newX, newY;
			newX = _lastY + _clientY - element.lastMouseX;
			newY = _lastX + _clientX - element.lastMouseY;


			if(newX < minX) newX = minX;
			if(newX > maxX ) newX = maxX ;

			if(newY < minY) newY = minY;
			if(newY > maxY ) newY = maxY ;

			element.style.left = newX + "px";
			element.style.top = newY + "px";
			element.lastMouseX = _clientY;
			element.lastMouseY = _clientX;
		}

		var endDarg = function(){
			if(document.detachEvent){
				document.detachEvent('onmousemove',darging);
				document.detachEvent('onmouseup',endDarg);
			}else{
				document.removeEventListener('mousemove',darging,false);
				document.removeEventListener('mouseup',endDarg,false);
			}
		}

		var element = document.getElementById('sina_game_dialog_ww_login');
	    element.lastMouseX = event.clientX;
	    element.lastMouseY = event.clientY;
		var maxX = sinaGameDialogwwlogin._body.clientWidth - element.clientWidth ;
		var maxY = sinaGameDialogwwlogin._body.clientHeight - element.clientHeight ;

		var minX = 0;
		var minY = 0;

		if(sinaGameDialogwwlogin.position != 'fixed'){
			minX += sinaGameDialogwwlogin._body.scrollLeft ;
			minY += sinaGameDialogwwlogin._body.scrollTop;
			maxX += sinaGameDialogwwlogin._body.scrollLeft ;
			maxY += sinaGameDialogwwlogin._body.scrollTop;

			if(minX < 0) minX = 0 ;
			if(minY < 0) minY = 0 ;
		}


		if(document.attachEvent){
			document.attachEvent('onmousemove',darging);
			document.attachEvent('onmouseup',endDarg);
		}else{
			document.addEventListener('mousemove',darging,false);
			document.addEventListener('mouseup',endDarg,false);
		}
	    return false;
    },
	scolling : '',
	scoll : function(){
		if(!sinaGameDialogwwlogin._show) return ;
		clearTimeout(sinaGameDialogwwlogin.scolling);
		sinaGameDialogwwlogin.scolling = setTimeout(sinaGameDialogwwlogin.adjustTopSlowly,700);
	}

};

//var vdialogHtml = '<div id="sina_game_dialog_ww_login_box_recycle" style="display:none;"></div><div id="sina_game_dialog_ww_login_box" style="display:none;"><iframe id="sina_game_dialog_ww_login_iframe" frameborder="0"></iframe><div id="sina_game_dialog_ww_login_box_in"></div></div><div id="sina_game_dialog_ww_login" style="overflow:hidden;display:none;" onselectstart="return event.srcElement.tagName==\'INPUT\' || event.srcElement.tagName==\'TEXTAREA\'" style="-moz-user-select:-moz-none;"><div id="sina_game_dialog_ww_login_top" onmousedown="sinaGameDialogwwlogin.drag(event);"><div id="sina_game_dialog_ww_login_top_title"></div><div id="sina_game_dialog_ww_login_top_close"><a href="javascript:;" onclick="sinaGameDialogwwlogin.close();return false;"></a></div><div class="clear"></div></div><div id="sina_game_dialog_ww_login_text"></div><div id="sina_game_dialog_ww_login_button"><input type="button" class="sina_game_dialog_ww_login_button" id="sina_game_dialog_ww_login_button1" /><input type="button" class="sina_game_dialog_ww_login_button" id="sina_game_dialog_ww_login_button2" /></div></div>';
var vdialogHtml = '<div id="sina_game_dialog_ww_login_box_recycle" style="display:none;"></div><div id="sina_game_dialog_ww_login_box" style="display:none;"><iframe id="sina_game_dialog_ww_login_iframe" frameborder="0"></iframe><div id="sina_game_dialog_ww_login_box_in"></div></div><div id="sina_game_dialog_ww_login" style="overflow:hidden;display:none;" onselectstart="return event.srcElement.tagName==\'INPUT\' || event.srcElement.tagName==\'TEXTAREA\'" style="-moz-user-select:-moz-none;"><div id="sina_game_dialog_ww_login_text"></div><div id="sina_game_dialog_ww_login_button"><input type="button" class="sina_game_dialog_ww_login_button" id="sina_game_dialog_ww_login_button1" /><input type="button" class="sina_game_dialog_ww_login_button" id="sina_game_dialog_ww_login_button2" /></div></div>';
document.write('<link href="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/css/vdialog.css" rel="stylesheet" type="text/css" />');
document.write(vdialogHtml);
</script>

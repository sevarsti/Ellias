if (sinitek == null) var sinitek = {};
if (sinitek.keyboard2 == null) sinitek.keyboard2 = {};
if (sinitek.keyboard2.keyboard == null)
{
    sinitek.keyboard2.keyboard = function(){
	    this.columns = new Array();
	    this.width = "";
	    this.hasMore = false;
	    this.hideSelects = new Array();
	    // 翻页
	    this.data = new Array();
	    this.currentPage = 1;
	    this.pageSize = 20;
	    // 控件
	    this.inputControl = null;
	    this.panel = null;
	    this.panelDiv = null;
	    // 事件
	    this.onMore = null;
	    this.onSelect = null;
	    this.onFilter = null;
    }
}

/**
 * 设置面板
 */
sinitek.keyboard2.keyboard.prototype.setPanel = function( panel ){
	this.panel = panel;

	// 创建表格对象
	this.panelDiv = document.createElement("<div nowrap style='display:none;border:#8EAAE9 1px solid;height:200px;overflow-y:auto'>");
	var _oTable = document.createElement("<table width='100%' cellspacing='0' cellpadding='2' border='0' bgcolor='white' style='font-family:Arial, Helvetica, sans-serif;font-size:10pt;'>");
	this.panelDiv.appendChild( _oTable );
	document.body.appendChild( this.panelDiv );
	this.panel.setTable( _oTable );

	var _me = this;
	this.panel.onClick = function(){
		_me.inputControl.ctrl.focus();
	}

	this.panel.onDblClick = function(){
		_me.setValue();
	}
}

/**
 * 设置数据
 */
sinitek.keyboard2.keyboard.prototype.setData = function( data ){
	// 清空数据
	this.data = new Array();
	for( var i = this.panel.table.rows.length - 1; i >= 0; i-- )
	{
		this.panel.table.deleteRow();
	}
    this.appendData( data );
}

/**
 * 附加数据
 */
sinitek.keyboard2.keyboard.prototype.appendData = function( data ){
    var _data = new Array();
	if ( data.length > this.pageSize ){
        this.panelDiv.style.height = "200px";
        this.hasMore = true;
        if ( this.currentPage == 1 ){
	        var _row = this.panel.table.insertRow();
	        var _cell = _row.insertCell();
	        _cell.align = "left";
	        _cell.innerText = "更多...";
	        _cell.colSpan = this.panel.columns.length;
        }
        for( var i = 0; i < data.length-1; i++ )
		{
			_data[_data.length] = data[i];
		}
    }
    else{
        this.panelDiv.style.height = data.length * 22 + "px";
        this.hasMore = false;
        for( var i = 0; i < data.length; i++ )
		{
			_data[_data.length] = data[i];
		}
		if ( this.currentPage > 1 ){
			this.panel.table.deleteRow( this.panel.table.rows.length-1 );
		}
    }
	for( var i = 0; i < _data.length; i++ ){
		this.data[this.data.length] = _data[i];
	}

    // 创建表格
    for( var i = 0; i < _data.length; i++ )
	{
		var _row = this.hasMore ? this.panel.table.insertRow(this.panel.table.rows.length-1) : this.panel.table.insertRow();
		for( var j = 0; j < this.panel.columns.length; j++ )
		{
			var _cell = _row.insertCell();
			var _column = this.panel.columns[j];
			_cell.align = _column.align;
			_cell.width = _column.width;
            _cell.noWrap = true;
            if ( _column.property != "" )
			{
				eval( "var _value = _data[i]." + _column.property + ";" );
				_cell.innerText = _value;
			}
			else
			{
				eval( "var _value = _data[i][" + _column.propertyIndex + "];" );
				_cell.innerText = _value;
			}
		}
	}
}


/**
 * 显示小键盘
 */
sinitek.keyboard2.keyboard.prototype.show = function(){
    if ( this.panel.table.rows.length == 0 ) return;

    var _ctrl = this.inputControl.ctrl;
	var offsets = Position.positionedOffset( _ctrl );
    var top     = offsets[1] + _ctrl.clientHeight + 5;
    var left    = offsets[0];

    this.panelDiv.style.position = 'absolute';
    this.panelDiv.style.top    = top + 'px';
    this.panelDiv.style.left   = left + 'px';
    this.panelDiv.style.display = "";
    this.panelDiv.style.width = _ctrl.clientWidth + 'px';
    // 判断是否有select控件，如果有则需要隐藏
    this.hideSelects = new Array();
    var selects = document.getElementsByTagName( "select" );
	for( var i = 0; i < selects.length; i++ )
	{
		var _select = selects[i];
		if ( _select.style.display != "none" )
		{
			var _pos = Position.positionedOffset( _select );
			var _top = _pos[1];
			var _left = _pos[0];
			var _hidden = Position.within( this.panelDiv, _left, _top ) ||
		              Position.within( this.panelDiv, _left, _top + _select.clientHeight ) ||
		              Position.within( this.panelDiv, _left + _select.clientWidth , _top ) ||
		              Position.within(this.panelDiv, _left + _select.clientWidth, _top + _select.clientHeight );
			if ( _hidden )
			{
				this.hideSelects[this.hideSelects.length] = _select;
				_select.style.display = "none";
			}
		}
	}

	//alert("start");
    var _me = this;
    document.attachEvent("onclick", function()
    {
    	var _srcElement = event.srcElement;
    	var _isok = (_srcElement == _me.inputControl.lastctrl);
    	var _element = _srcElement;
    	while( _element != null )
    	{
    		_isok |= ( _me.panel.table == _element );
    		_element = _element.parentElement;
    	}
    	if ( !_isok )
    	{
        	_me.hide();
        }
        this.lastPanelDiv = this.panelDiv;
    });
    //alert("end");
}

/**
 * 隐藏小键盘
 */
sinitek.keyboard2.keyboard.prototype.hide = function(){
	this.panelDiv.style.display = "none";
	for( var i = 0; i < this.hideSelects.length; i++ )
	{
		this.hideSelects[i].style.display = "";
	}
	this.hideSelects = new Array();
}

/**
 * 设置输入控件
 */
sinitek.keyboard2.keyboard.prototype.setInputControl = function( ctrl ){
	this.inputControl = ctrl;

	var _me = this;
	this.inputControl.onMoveDown = function(){_me.moveDown();}
    this.inputControl.onMoveUp = function(){_me.moveUp();}
    this.inputControl.onCancel = function(){ _me.hide(); };
    this.inputControl.onFilter = function(){ if ( _me.onFilter != null ){_me.currentPage = 1;_me.onFilter(this.ctrl,_me)} };
    this.inputControl.onSelect = function(){ _me.setValue(); };
}

/**
 * 光标下移
 */
sinitek.keyboard2.keyboard.prototype.moveDown = function()
{
	this.panel.moveDown();
    // 控制滚轴的位置
    if( this.panel.table.rows.length > 0 )
    {
        var _top = this.panel.currentIndex * this.panel.table.rows[0].clientHeight;
        if ( _top < 0 )
        {
            _top = 0;
        }
        if ( _top > this.panelDiv.clientHeight )
        {
            this.panelDiv.scrollTop = _top -
                                      this.panelDiv.clientHeight +
                                      this.panel.table.rows[0].clientHeight;
        }
    }
}


/**
 * 光标上移
 */
sinitek.keyboard2.keyboard.prototype.moveUp = function()
{
	this.panel.moveUp();
    // 控制滚轴的位置
    if( this.panel.table.rows.length > 0 )
    {
        var _top = this.panel.currentIndex * this.panel.table.rows[0].clientHeight;
        if ( _top < 0 )
        {
            _top = 0;
        }
        this.panelDiv.scrollTop = _top;
    }
}


/**
 * 确定
 */
sinitek.keyboard2.keyboard.prototype.setValue = function()
{
	var _onSelect = true;
	var _index = this.panel.currentIndex;
	if ( this.hasMore )
	{
		if (_index == this.panel.table.rows.length-1)
		{
			if ( this.onMore != null )
			{
				this.currentPage++;
				this.onMore( this.inputControl.ctrl, this );
			}
			_onSelect = false;
		}
	}
	if ( _onSelect )
	{
		if ( _index >= 0 && _index < this.data.length )
		{
			if ( this.onSelect != null )
			{
				this.onSelect( this.data[_index], this.inputControl.ctrl );
			}
		}
		this.hide();
	}
	this.panel.reset();
}


sinitek.keyboard2.keyboard.prototype.refresh = function()
{
	// todo:清空数据
	// todo:添加列
	// todo:填充数据
}

if ( sinitek.keyboard2.column == null )
{
    sinitek.keyboard2.column = function(){
    	this.property = "";
    	this.propertyIndex = 0;
    	this.align = "";
    	this.width = "";
    }
}


if ( sinitek.keyboard2.panel == null )
{
	sinitek.keyboard2.panel = function(){
		this.currentIndex = -1;
		this.table = null;
		this.columns = new Array();
		// 事件
		this.onClick = null;
		this.onDblClick = null;
	}
}

sinitek.keyboard2.panel.prototype.addColumn = function( propName, propIndex, align, width ){
	var _column = new sinitek.keyboard2.column();
    _column.property = propName;
    _column.propertyIndex = propIndex;
    _column.align = align;
    _column.width = width;
    this.columns[this.columns.length] = _column;
}

sinitek.keyboard2.panel.prototype.setTable = function( table ){
	this.table = table
	var _me = this;
	this.table.onclick = function(){
		_me.click();
	}
	this.table.ondblclick = function(){
		if ( _me.onDblClick != null ) _me.onDblClick();
	}
}

sinitek.keyboard2.panel.prototype.reset = function(){
	this.currentIndex = -1;
	// todo:清空表格
}

/**
 * 处理鼠标选择事件
 */
sinitek.keyboard2.panel.prototype.dblclick = function(){

}

/**
 * 选择记录事件
 */
sinitek.keyboard2.panel.prototype.click = function(){
	var _row = event.srcElement.parentElement;
    if ( this.currentIndex >= 0 && this.currentIndex < this.table.rows.length )
    {
        this.table.rows[this.currentIndex].style.backgroundColor = "white";
    }
    _row.style.backgroundColor = "lightblue";
    this.currentIndex = _row.rowIndex;
    if ( this.onClick != null ) this.onClick();
}


/**
 * 处理按键操作。抛出按键操作
 */
sinitek.keyboard2.panel.prototype.moveUp = function(){
	var _lastIndex = this.currentIndex;
	this.currentIndex--;
	if ( this.currentIndex < 0 )
    {
        this.currentIndex = 0;
    }
    if ( _lastIndex >= 0 && _lastIndex < this.table.rows.length )
    {
        this.table.rows[_lastIndex].style.backgroundColor = "white";
    }
    this.table.rows[this.currentIndex].style.backgroundColor = "lightblue";
}

sinitek.keyboard2.panel.prototype.moveDown = function(){
	var _lastIndex = this.currentIndex;
	this.currentIndex++;
    if ( this.currentIndex >= this.table.rows.length )
    {
        this.currentIndex = this.table.rows.length-1;
    }
    if ( _lastIndex >= 0 && _lastIndex < this.table.rows.length )
    {
        this.table.rows[_lastIndex].style.backgroundColor = "white";
    }
    this.table.rows[this.currentIndex].style.backgroundColor = "lightblue";
}

if ( sinitek.keyboard2.inputbox == null )
{
	sinitek.keyboard2.inputbox = function(){
		this.last = ""; // 上次输入内容
		this.current = ""; // 本次输入内容
		// 控件
		this.ctrl = null;
		this.lastctrl = null;
		// 事件
		this.onFilter = null;
		this.onMoveDown = null;
		this.onMoveUp = null;
		this.onCancel = null;
		this.onSelect = null;
		//
		this.timeHandler = null;
	}
}

sinitek.keyboard2.inputbox.prototype.setCtrl = function( ctrl ){
	this.lastctrl = this.ctrl;
	this.ctrl = ctrl;
	var _me = this;
	this.ctrl.onkeydown = function(){_me.keyDown();}
    this.ctrl.onkeyup = function(){_me.keyUp();}
}

sinitek.keyboard2.inputbox.prototype.keyUp = function(){
	var _me = this;
    if ( this.timeHandler != null ){
        window.clearInterval(this.timeHandler);
    }
    this.timeHandler = window.setInterval(function(){
                _me.demon();
            }, 1000);
    if ( event.keyCode == 27 )
    {
    	// ESC
    	// 隐藏小键盘
    	/*if ( this.onCancel != null ){
    		this.onCancel();
    	}*/
    }
    else if ( event.keyCode == 13 )
    {
    	// 确定
    	if ( this.onSelect != null ) this.onSelect();
    }
}

sinitek.keyboard2.inputbox.prototype.keyDown = function(){
    if ( event.keyCode == 38 )
    {
    	//上移
    	if ( this.onMoveUp != null ) this.onMoveUp();
    }
    else if ( event.keyCode == 40 )
    {
    	//下移
    	if ( this.onMoveDown != null ) this.onMoveDown();
    }
    this.last = this.ctrl.value;
	if ( this.timeHandler != null ){
        window.clearInterval( this.timeHandler );
    }
}

sinitek.keyboard2.inputbox.prototype.demon = function(){
    window.clearInterval( this.timeHandler );

    this.current = this.ctrl.value;
	var _isok = (this.current != this.last) ||
		        ( this.current == "" )
	if ( _isok )
	{
		if ( this.onFilter != null ){this.onFilter()};
	}
}
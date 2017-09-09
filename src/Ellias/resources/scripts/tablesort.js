/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 12-4-13
 * Time: 上午11:07
 * To change this template use File | Settings | File Templates.
 */
/**
 * @param obj row对象
 * @param sortby 根据第几列排序
 * @param number 是否为数字
 * @param type 是否有最底说明行
 */
function resort(obj, sortby, number,type)
{
    var table = obj;
    while(table.tagName != 'TBODY') {
        table = table.parentNode;
    }
//    var table = obj.parentNode.parentNode.parentNode; //table对象
//    if(table.tagName == 'TBODY')
//    {
//        table = table.parentNode;
//    }
    if(table.sort == null) {
        table.sort = 0;
    }
    if(table.order == null) {
        table.order = 2;
    }
    if(sortby == table.sort)
    {
        table.order = 3 - table.order;
    }
    else
    {
        table.sort = sortby;
        if(number) {
            table.order = 1;
        } else {
            table.order = 2;
        }
    }
    var length = table.rows.length;
    if(type==1){
      length=length-type;
    }
    var beginrow = 1;
    for(var j = 0; j < length; j++)
    {
        if(table.rows[j].className != 'head')
        {
            beginrow = j;
            break;
        }
    }
    for(var i = beginrow; i < length; i++)
    {
        var max = i;
        for(var j = i + 1; j < length; j++)
        {
            if(table.rows[j].cells.length < table.rows[i].cells.length)
            {
                continue;
            }
            var valueMax, valueCurrent;
            if(number)
            {
                if(table.order == 1){
                   if(getValue(table.rows[max].cells[sortby])==''||getValue(table.rows[max].cells[sortby])==null){
//                   if(table.rows[max].cells[sortby].value==''||table.rows[max].cells[sortby].value==null){
                       valueMax=-999999999999;
                   }else{
                       valueMax = parseFloat(getValue(table.rows[max].cells[sortby]));
                   }
                   if(getValue(table.rows[j].cells[sortby])==''||getValue(table.rows[j].cells[sortby])==null){
                       valueCurrent=-999999999999;
                   }else{
                       valueCurrent = parseFloat(getValue(table.rows[j].cells[sortby]));
                   }
                }else if(table.order == 2){
                    if(getValue(table.rows[max].cells[sortby])==''||getValue(table.rows[max].cells[sortby])==null){
                        valueMax=999999999999;
                    }else{
                        valueMax = parseFloat(getValue(table.rows[max].cells[sortby]));
                    }
                    if(getValue(table.rows[j].cells[sortby])==''||getValue(table.rows[j].cells[sortby])==null){
                        valueCurrent=999999999999;
                    }else{
                        valueCurrent = parseFloat(getValue(table.rows[j].cells[sortby]));
                    }
                }
            }
            else
            {
                valueMax = getValue(table.rows[max].cells[sortby]);
                valueCurrent = getValue(table.rows[j].cells[sortby]);
            }
            if(table.order == 1)
            {
                if(number)
                {
                    if(valueMax < valueCurrent)
                    {
                        max = j;
                    }
                    else
                    {
                        max = max;
                    }
                }
                else
                {
                    if(valueMax.localeCompare(valueCurrent) < 0)
                    {
                        max = j;
                    }
                }
            }
            else if(table.order == 2)
            {
                if(number)
                {
                    if(valueMax < valueCurrent)
                    {
                        max = max;
                    }
                    else
                    {
                        max = j;
                    }
                }
                else
                {
                    if(valueMax.localeCompare(valueCurrent) < 0)
                    {
                        max = max;
                    }
                    else
                    {
                        max = j;
                    }
                }
            }
        }
//        table.moveRow(max, i);
        moveRow(table, max, i);
        var begin = i;
        for(var j = max + 1; j < length; j++)
        {
            if(table.rows[j].cells.length < table.rows[begin].cells.length)
            {
                i++;
                moveRow(table, j, i);
//                table.moveRow(j, i);
            }
            else
            {
                break;
            }
        }
    }
    var classidx = 2;
    for(var j = beginrow; j < length; j++) {
        if(table.rows[j].cells[sortby].innerHTML != table.rows[j-1].cells[sortby].innerHTML)
        {
            classidx = 3 - classidx;
        }
        table.rows[j].className = 'row' + classidx;
    }
}

function getValue(obj) {
    if(obj.attributes['value'] != null) {
        return obj.attributes['value'].value;
    } else {
        return obj.innerHTML;
    }
}

function getTRByIndex(sourceELIndex){
     var trArray = getTRArray();
     var result = trArray[sourceELIndex];
     return result;
}

 //入参说明：
 //sourceELIndex ：需要移动的tr在tbody中的第几行（>=1）
 //targetELIndex ：需要移动到tbody中的第几行（>=1，<=行数）
 function moveRow(table, sourceELIndex,targetELIndex)
 {
     var add = true;
     if(sourceELIndex > targetELIndex) {
         add = false;
     }
     var atts = table.rows[sourceELIndex].attributes;
     var html = table.rows[sourceELIndex].innerHTML;
     var newrow = table.insertRow(targetELIndex + (add ? 1 : 0));
     for(var i = 0; i < atts.length; i++) {
         newrow.setAttribute(atts[i].name, atts[i].value);
//         newrow.attributes[atts[i].name] = atts[i].value;
     }
//     newrow.attributes = atts;
     newrow.innerHTML = html;
     table.deleteRow(sourceELIndex + (add ? 0 : 1));
 }
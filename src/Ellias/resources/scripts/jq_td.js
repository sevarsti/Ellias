$(document).ready(function(){
    //------------------ 页面加载的时候初始化当前时间------------------------------
    var now=new Date();
    var startDate=$("#startDate");
    var endDate=$("#endDate");

    var defaultstartDate=now.getYear()+"-"+((now.getMonth())>9?(now.getMonth()):"0"+(now.getMonth()))+"-"+(now.getDate()>9?now.getDate():"0"+now.getDate());
         startDate.attr("value",defaultstartDate);
    var defaultEndDate=now.getYear()+"-"+((now.getMonth()+1)>9?(now.getMonth()+1):"0"+(now.getMonth()+1))+"-"+(now.getDate()>9?now.getDate():"0"+now.getDate());
         endDate.attr("value",defaultEndDate);
    //------------------------------------------------------------------------------

    //------------------  计算贡献率按钮点击事件 开始 ------------------------------
    $("#calCombin").click(function(){
        $("#result").html("");
        $("#downLoadPath").attr("value","");
        $("#downLoadExcel").attr("style","display:none");
        $("#downLoadExcel").attr("href","");
        //验证日期的合法性
        if(checkDate(startDate,endDate)==false){
          return
        }
        $("#loading").attr("style","display:block;text-align:center;margin-top:150px;");
        //通过验证后开始获取传递参数
          //1.获取要计算的组合ID
            var pflIds=getPflIds();
            if(pflIds==null || pflIds.length==0){
                alert("请选择要计算的组合名称！");
                return;
            }
          //2.获取开始日期
            var sdValue=startDate.attr("value");
          //3。获取结束日期
            var edValue=endDate.attr("value");

        //开始异步调用
        $.ajax({
            type:"GET",
            url:"pflAnalysis.do?method=calCombin&pflIds="+pflIds+"&startDate="+sdValue+"&endDate="+edValue+"&curEmpId="+$("#curEmpId").attr("value"),
            dataType:"json",
            success:callback, //成功时调用的回调函数
            error: function() {
                     $("#loading").attr("style","display:none;");
                    var resultObj=$("#result");
                    resultObj.html("<font style='color:red;text-align:center'>对不起,查询不到相关数据,请确认查询条件！</font>");
                 }
            });
    });
    //计算贡献率按钮事件的回调函数
    function callback(data){
    $("#loading").attr("style","display:none;text-align:center;margin-top:150px;");
    $("#downLoadExcel").attr("style","display:inline");
    var downLoadPath=$("#downLoadPath");
    //3.将服务器端返回的数据动态的显示再页面上
    var resultObj=$("#result");
    var htmlText="";

    for(var i=0;i<data.length;i++){
        var htmlText1="";
        htmlText1="<tr>"
                    +"<td width='18%' align='center' class='td1'><b>组合名称[ID]:</b></td>"
                    +"<td width='32%' align='center' class='td1'>"+data[i].name+"["+data[i].id+"]</td>"
                    +"<td width='18%' align='center' class='td1'><b>时间区间:</b></td>"
                    +"<td width='32%' align='center' class='td1'>"+data[i].startDate+"-"+data[i].endDate+"</td>"
                +"</tr>"
                +"<tr>"
				    +"<td colspan='2' align='center' class='td1'><b>股票名称/代码</b></td>"
				    +"<td colspan='2' align='center' class='td1'><b>贡献率</b></td>"
			    +"</tr>"
        downLoadPath.attr("value",downLoadPath.attr("value")+data[i].xlsName);
        $("#downLoadExcel").attr("href","../portfolio2/Analysis/downLoadExcel.jsp?fileName="+$("#downLoadPath").attr("value"));
        var htmlText2="";
        for(var j=0;j<data[i].itemList.length;j++){
            htmlText2=htmlText2+"<tr>"
                    +"<td colspan='2' align='center' class='td1'>"+data[i].itemList[j].itemName+"("+data[i].itemList[j].itemCode+")"+"</td>"
                    +"<td colspan='2' align='center' class='td1'>"+data[i].itemList[j].contriRate+"</td>"
                +"</tr>"

        }
        var htmlText3="";
        htmlText3="<tr>"
                    +"<td colspan='4' class='td1' align='center'><b>贡献率图:</b></td>"
                +"<tr>"
                +"<tr>"
                    +"<td colspan='4' class='td1'><img src='../images/TDChart/"+data[i].fileName+"'/></td>"
                +"</tr>"
        htmlText=htmlText+"<table width='100%' cellspacing='0' class='tbl' id='expTable'>"+htmlText1+htmlText2+htmlText3+"</table>";

    }
     resultObj.html("<div id='container'>"+htmlText+"</div>");
}
    //------------------  计算贡献率按钮点击事件 结束 ------------------------------

    //------------------ 查询基础数据按钮点击事件  开始-----------------------------
    $("#searchBaseData").click(function(){
        $("#result").html("");
        $("#downLoadPath").attr("value","");
        $("#downLoadExcel").attr("style","display:none");
        $("#downLoadExcel").attr("href","");
        //验证日期的合法性
        if(checkDate(startDate,endDate)==false){
          return
        }
        $("#loading").attr("style","display:block;text-align:center;margin-top:150px;");
        //通过验证后开始获取传递参数
          //1.获取要计算的组合ID
          var pflIds=getPflIds();
          if(pflIds==null || pflIds.length==0){
            alert("请选择要计算的组合名称！");
            return;
          }
          //2.获取开始日期
          var sdValue=startDate.attr("value");
          //3。获取结束日期
          var edValue=endDate.attr("value");

        //开始异步调用
        $.ajax({
            type:"GET",
            url:"pflAnalysis.do?method=searchBaseData&pflIds="+pflIds+"&startDate="+sdValue+"&endDate="+edValue+"&curEmpId="+$("#curEmpId").attr("value"),
            dataType:"json",
            success:callBackBaseData, //成功时调用的回调函数
            error: function() {
                     $("#loading").attr("style","display:none;");
                    var resultObj=$("#result");
                    resultObj.html("<font style='color:red;text-align:center'>对不起,查询不到任何数据,无法获取该组合的基础数据！</font>");
                 }
            });
    });
    //查询组合基本数据的回调函数
    function callBackBaseData(data){
        $("#loading").attr("style","display:none;text-align:center;margin-top:150px;");
        var downLoadPath=$("#downLoadPath");
        //3.将服务器端返回的数据动态的显示再页面上
        var resultObj=$("#result");
        //----------- 开始输出顶部栏 --------------
        for(var i=0;i<data.length;i++){
            var htmlText1="<tr style='background-color:#ccc'>"
                            +"<td class='td1' align='center'><b>日期</b></td>";
            //取出第一项的名称
            var firstItemName=data[i].itemList[0].itemName;
            //取得时间数组
            var dateArr=new Array();
            var htmlContent="";
            for(var j=0;j<data[i].itemList.length-1;j++){
                if(data[i].itemList[j].itemName != data[i].itemList[j+1].itemName){
                    htmlText1=htmlText1+"<td class='td1' align='center'><b>"+data[i].itemList[j].itemCode+"</b></td>";
                }
                if(data[i].itemList[j].itemName==firstItemName){
                      dateArr[j]=data[i].itemList[j].pubDate;
                }
            }
            htmlText1=htmlText1+"<td class='td1' align='center'><b>"+data[i].itemList[j].itemCode+"</b></td>";
            var colsNo=0;
            for(var k=0;k<dateArr.length;k++){
                if(k%2!=0){
                    htmlContent=htmlContent+"<tr style='background-color:#ccc'>";
                }
                for(var h=0;h<data[i].itemList.length;h++){
                    if(data[i].itemList[h].pubDate==dateArr[k]){
                        if(h==k){
                            htmlContent=htmlContent+"<td class='td1' align='center'><b>"+data[i].itemList[h].pubDate+"</b></td>"+"<td class='td1' align='center'>"+data[i].itemList[h].totalBalance+"</td>";
                        }else{
                            htmlContent=htmlContent+"<td class='td1' align='center'>"+data[i].itemList[h].totalBalance+"</td>";
                        }
                    }
                }
                htmlContent=htmlContent+"</tr>";
                colsNo++;
            }
            downLoadPath.attr("value",downLoadPath.attr("value")+data[i].xlsName);
            $("#downLoadExcel").attr("style","display:inline");
            $("#downLoadExcel").attr("href","../portfolio2/Analysis/downLoadExcel.jsp?fileName="+$("#downLoadPath").attr("value"));
            htmlText1=htmlText1+"</tr>";
            htmlText1= "<tr><td colspan='"+ (colsNo+3) +"' class='td1' align='center'><b>组合[ID]:&nbsp;&nbsp;</b>"+data[0].name+"["+data[0].id+"]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>阶段区间:&nbsp;&nbsp;</b>"+data[0].startDate+"-"+data[0].endDate+"</td>"+"</tr>"+htmlText1;
            //-------- 顶部输出结束 -------------------
            //-------- 输出内容部分 -------------------
            htmlText1=htmlText1+htmlContent;
            //-------- 内容部分打印结束 ---------------
        }
        resultObj.html("<div id='container'><table width='100%' cellspacing='0' class='tbl' id='expTable'>"+htmlText1+"</table></div>");
    }
    //------------------ 查询基础数据按钮点击事件  结束-----------------------------

    //------------------ 组合成分区间表现按钮点击事件 开始 -------------------------
    $("#itemPerformance").click(function(){
        $("#result").html("");
        $("#downLoadPath").attr("value","");
        $("#downLoadExcel").attr("style","display:none").attr("href","");
        //验证日期的合法性
        if(checkDate(startDate,endDate)==false){
          return
        }
        $("#loading").attr("style","display:block;text-align:center;margin-top:150px;");
        //通过验证后开始获取传递参数
          //1.获取要计算的组合ID
          var pflIds=getPflIds();
          if(pflIds==null || pflIds.length==0){
            alert("请选择要计算的组合名称！");
            return;
          }
          //2.获取开始日期
          var sdValue=startDate.attr("value");
          //3。获取结束日期
          var edValue=endDate.attr("value");

        //开始异步调用
        $.ajax({
            type:"GET",
            url:"pflAnalysis.do?method=showItemPerformance&pflIds="+pflIds+"&startDate="+sdValue+"&endDate="+edValue+"&curEmpId="+$("#curEmpId").attr("value"),
            dataType:"json",
            success:callBackItemPerformance, //成功时调用的回调函数
            error: function() {
                     $("#loading").attr("style","display:none;");
                    var resultObj=$("#result");
                    resultObj.html("<font style='color:red;text-align:center'>对不起,查询不到任何数据,无法分析该组合的成分区间表现！</font>");
                 }
        });
    });
    //组合成分区间表现的回调函数
    function callBackItemPerformance(data){
        $("#loading").attr("style","display:none;text-align:center;margin-top:150px;");
        var downLoadPath=$("#downLoadPath");
        $("#downLoadExcel").attr("style","display:inline");
        //3.将服务器端返回的数据动态的显示再页面上
        var resultObj=$("#result");
        var htmlText="";
        //----------- 开始输出顶部栏 --------------
        for(var i=0;i<data.length;i++){
              var htmlText1="";
              htmlText1="<table width='100%' cellspacing='0' class='tbl' id='expTable'><tr>"
                    +"<td width='18%' align='center' class='td1'><b>组合名称[ID]:</b></td>"
                    +"<td width='32%' align='center' class='td1'>"+data[i].name+"["+data[i].id+"]</td>"
                    +"<td width='18%' align='center' class='td1'><b>时间区间:</b></td>"
                    +"<td width='32%' align='center' class='td1'>"+data[i].startDate+"-"+data[i].endDate+"</td>"
                +"</tr><table>"
                +"<table width='100%' cellspacing='0' class='tbl' id='expTable'><tr>"
                    +"<td width='18%' align='center' class='td1'><b>组合成分</b></td>"
                    +"<td width='21%' align='center' class='td1'><b>平均成本</b></td>"
                    +"<td width='18%' align='center' class='td1'><b>期末持股数</b></td>"
                    +"<td width='21%' align='center' class='td1'><b>建仓以来收益率</b></td>"
                    +"<td width='22%' align='center' class='td1'><b>区间涨跌幅</b></td>"
                +"</tr>"
              var htmlText2="";
              for(var j=0;j<data[i].itemList.length;j++){
                  if(data[i].itemList[j].amount !=0){
                      htmlText2=htmlText2+"<tr>"
                                   +"<td width='18%' align='center' class='td1'>"+data[i].itemList[j].itemName+"</td>"
                                   +"<td width='21%' align='center' class='td1'>"+data[i].itemList[j].holdPrice.toFixed(2)+"</td>"
                                   +"<td width='18%' align='center' class='td1'>"+data[i].itemList[j].amount.toFixed(2)+"</td>"
                                   +"<td width='21%' align='center' class='td1'>"+(data[i].itemList[j].totalProfit*100).toFixed(2)+"%</td>"
                                   +"<td width='22%' align='center' class='td1'>"+(data[i].itemList[j].profit*100).toFixed(2)+"%</td>"
                                +"</tr>"
                  }
              }
              var htmlText3=""
                    htmlText3="<tr>"
                                +"<td colspan='5' class='td1' align='center'><b>组合区间收益率分析图</b></td>"
                            +"<tr>"
                            +"<tr>"
                                +"<td colspan='5' class='td1'><img src='../images/TDChart/"+data[i].fileName+"'/></td>"
                            +"</tr>"
                 htmlText=htmlText+htmlText1+htmlText2+htmlText3+"</table>";
            downLoadPath.attr("value",downLoadPath.attr("value")+data[i].xlsName);
        }
        $("#downLoadExcel").attr("href","../portfolio2/Analysis/downLoadExcel.jsp?fileName="+$("#downLoadPath").attr("value"));
        resultObj.html("<div id='container'>"+htmlText+"</div>");
    }
    //------------------ 组合成分区间表现按钮点击事件 开始 -------------------------

    //------------------ 组合区间减仓分析按钮点击事件 开始 ------------------------
    $("#sellStatus").click(function(){
        $("#result").html("");
        $("#downLoadPath").attr("value","");
        $("#downLoadExcel").attr("style","display:none").attr("href","");
        //验证日期的合法性
        if(checkDate(startDate,endDate)==false){
          return
        }
        $("#loading").attr("style","display:block;text-align:center;margin-top:150px;");
        //通过验证后开始获取传递参数
          //1.获取要计算的组合ID
          var pflIds=getPflIds();
          if(pflIds==null || pflIds.length==0){
            alert("请选择要计算的组合名称！");
            return;
          }
          //2.获取开始日期
          var sdValue=startDate.attr("value");
          //3。获取结束日期
          var edValue=endDate.attr("value");

        //开始异步调用
        $.ajax({
            type:"GET",
            url:"pflAnalysis.do?method=showSellStatus&pflIds="+pflIds+"&startDate="+sdValue+"&endDate="+edValue+"&curEmpId="+$("#curEmpId").attr("value"),
            dataType:"json",
            success:callBackSellStatus, //成功时调用的回调函数
            error: function() {
                     $("#loading").attr("style","display:none;");
                    var resultObj=$("#result");
                    resultObj.html("<font style='color:red;text-align:center'>对不起,查询不到任何数据,无法进行该组合的成分区间减仓分析！</font>");
                 }
        });
    });
    //组合区间减仓分析的回调函数
    function  callBackSellStatus(data){
        $("#loading").attr("style","display:none;text-align:center;margin-top:150px;");
        var downLoadPath=$("#downLoadPath");
        //3.将服务器端返回的数据动态的显示再页面上
        var resultObj=$("#result");
        var htmlText="";

        for(var i=0;i<data.length;i++){
            for(var j=0;j<data[i].itemList.length;j++){
                var htmlText1="";
                htmlText1="<tr>"
                        +"<td width='18%' align='center' class='td1'><b>股票简称</b></td>"
                        +"<td width='82%' colspan='"+ data[i].itemList[j].bargainList.length +"' align='center' class='td1'>"+data[i].itemList[j].itemName+"</td>"
                        +"</tr>";

                var htmlText2="";
                var htmlText3="";
                var htmlText4="";
                var htmlText5="";
                var htmlText6="";
                var htmlText7="";
                var htmlText8="";
                htmlText2="<tr>"
                        +"<td width='18%' align='center' class='td1'><b>减仓时间</b></td>";
                htmlText3="<tr>"
                        +"<td width='18%' align='center' class='td1'><b>研究员</b></td>";
                htmlText4="<tr>"
                        +"<td width='18%' align='center' class='td1'><b>平均成本</b></td>";
                htmlText5="<tr>"
                        +"<td width='18%' align='center' class='td1'><b>减仓数量(手)</b></td>";
                htmlText6="<tr>"
                        +"<td width='18%' align='center' class='td1'><b>减仓价格</b></td>";
                htmlText7="<tr>"
                        +"<td width='18%' align='center' class='td1'><b>减仓收益率</b></td>";

                htmlText8="<tr>"
                        +"<td align='center' class='td1' colspan='"+ (data[i].itemList[j].bargainList.length+1) +"'><b>成分减仓分析图</b></td></tr>"
                        +"<tr>"
                        +"<td align='center' class='td1' colspan='"+ (data[i].itemList[j].bargainList.length+1) +"'><img src='../images/TDChart/"+data[i].itemList[j].picFilePath+"'/></td>";
                var wid=80/data[i].itemList[j].bargainList.length;
                for(k=0;k<data[i].itemList[j].bargainList.length;k++){
                    if(data[i].itemList[j].bargainList[k].tradeType==11 || data[i].itemList[j].bargainList[k].tradeType==12){
                        htmlText2=htmlText2+"<td align='center' class='td1' width='"+wid+"%'>"+data[i].itemList[j].bargainList[k].tradeDate+"</td>";
                        htmlText3=htmlText3+"<td align='center' class='td1' width='"+wid+"%'>"+data[i].pflOwnerName+"</td>";
                        htmlText5=htmlText5+"<td align='center' class='td1' width='"+wid+"%'>"+data[i].itemList[j].bargainList[k].bargainAmount.toFixed(2)+"</td>";
                        htmlText6=htmlText6+"<td align='center' class='td1' width='"+wid+"%'>"+data[i].itemList[j].bargainList[k].bargainPrice.toFixed(2)+"</td>";
                    }
                }
                for(k=0;k<data[i].itemList[j].bargainList.length;k++){
                    if(data[i].itemList[j].bargainList[k].tradeType==11|| data[i].itemList[j].bargainList[k].tradeType==12){
                        for(h=0;data[i].itemList[j].itemList.length;h++){
                            if(data[i].itemList[j].itemList[h].publishDate==data[i].itemList[j].bargainList[k].tradeDate){
                                htmlText4=htmlText4+"<td align='center' class='td1' width='"+wid+"%'>"+data[i].itemList[j].itemList[h].holdPrice.toFixed(2)+"</td>";
                                var temp=((data[i].itemList[j].bargainList[k].bargainPrice/data[i].itemList[j].itemList[h].holdPrice)-1)*100;
                                htmlText7=htmlText7+"<td align='center' class='td1' width='"+wid+"%'>"+temp.toFixed(2)+"%</td>";
                                break;
                            }
                         }
                     }
                }
                htmlText1=htmlText1+"</tr>";
                htmlText2=htmlText2+"</tr>";
                htmlText3=htmlText3+"</tr>";
                htmlText4=htmlText4+"</tr>";
                htmlText5=htmlText5+"</tr>";
                htmlText6=htmlText6+"</tr>";
                htmlText7=htmlText7+"</tr>";
                htmlText8=htmlText8+"</tr>";
                htmlText=htmlText+"<table width='80%' cellspacing='0' class='tbl' id='expTable'>"+htmlText1+htmlText2+htmlText3+htmlText4+htmlText5+htmlText6+htmlText7+htmlText8+"</table></br>";
            }
            if(data[i].xlsName !=''){
                $("#downLoadExcel").attr("style","display:inline");
                downLoadPath.attr("value",downLoadPath.attr("value")+data[i].xlsName);
                $("#downLoadExcel").attr("href","../portfolio2/Analysis/downLoadExcel.jsp?fileName="+$("#downLoadPath").attr("value"));
            }
        }

        resultObj.html("<div id='container'>"+htmlText+"</div>");
    }
    //------------------ 组合区间减仓分析按钮点击事件 结束 ------------------------

    //验证开始日期和结束日期的合法性
    function  checkDate(sDate,eDate){
        var sDateValue=sDate.attr("value");
        var eDateValue=eDate.attr("value");
        //验证日期格式为:1997-01-01的正则表达式
        var reg=/^[1-2]\d{3}-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[0-1])$/;

        if(sDateValue == "" || eDateValue == ""){
            alert("开始日期或结束日期不能为空");
            return false;
        }
        if(!sDateValue.match(reg)){
            alert("开始日期格式错误！\n正确格式为:2001-01-01");
            return false;
        }
        if(!eDateValue.match(reg)){
            alert("结束日期格式错误！\n正确格式为:2001-01-01");
            return false;
        }
        if(sDateValue > eDateValue){
            alert("开始日期不能大于结束日期");
            return false;
        }
        return true;
    }

    //获取要计算的组合ID
    function getPflIds(){
        //获取到页面组合名称下拉框
        var ddlPflIdNode=$("#pflIds");
        var pflIds="";
        if(ddlPflIdNode.val()=='0'){
            $("select[@name=portfolio] option").each(function(){
                if($(this).val() != 0){
                   pflIds+=$(this).val()+"-";
                }
            });
        }else{
            pflIds=ddlPflIdNode.val()+"-";
        }
        return pflIds;
    }
});
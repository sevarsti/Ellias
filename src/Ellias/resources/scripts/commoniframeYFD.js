
//公用iframe应用 add 向金华 易方达
function IframeWidow_Open(sSrc,iWidth,iHeight,iTop,iLeft,sScrolling){
            var oIframe=document.all("IframeWidow");
            oIframe.onreadystatechange=onchangestatus;
            oIframe.src=sSrc;
             oIframe.style.width=iWidth;
             oIframe.style.height=iHeight;
             oIframe.style.backgroundColor="red";
             oIframe.style.zIndex=1;
             var iframeTop;
             var iframeLeft;
             if ((iTop=="") || (iTop==null))
             {
                 iframeTop=((document.body.clientHeight/2) - (parseInt(oIframe.style.height)/20))-0 ;
                 iframeTop=(iframeTop>0)?(iframeTop):(0);
             }else{
                 iframeTop=iTop;
             }
             if ((iLeft=="") || (iLeft==null))
             {
                 iframeLeft=((document.body.clientWidth/2) - (parseInt(oIframe.style.width)/2))-0;
                 iframeLeft=(iframeLeft>0)?(iframeTop):(0);
             }else{
                 iframeLeft=iLeft;
             }

            oIframe.style.top=iframeTop;
            //oIframe.style.left=iframeLeft;
            //oIframe.style.left=document.body.scrollLeft + 400+"px";
            oIframe.style.left  = document.body.clientWidth/50-22+"px";

             switch (sScrolling)
             {
                   case "yes" :
                     oIframe.style.scrolling="yes";
                     break;
                   case "no" :
                       oIframe.style.scrolling="no";
                       break;
                default :
                    oIframe.style.scrolling="auto";
                    break;
            }

            //window.status="H"+(document.body.clientHeight/2)+'-/-'+(parseInt(oIframe.style.height)/2)+'='+((document.body.clientHeight/2) - (parseInt(oIframe.style.height)/2));
            //window.status+=".....";
            //window.status+="W"+document.body.clientWidth+'-/-'+parseInt(oIframe.style.width)+'='+((document.body.clientWidth/2) - (parseInt(oIframe.style.width)/2));
            //window.status+=".....";
            //window.status+="afterH"+iframeTop+'-/-'+oIframe.style.top;
            //window.status+=".....";
            //window.status+="afterW"+iframeLeft+'-/-'+oIframe.style.left;

    }

function onchangestatus()
{
      var othis=event.srcElement;
      if (othis.readyState=="complete"){
        othis.style.display="";
        //othis.focus();
      }
}

function hiddenwin(){
    var parentframe=parent.document.all("IframeWidow");
	parentframe.style.display = 'none';

	document.getElementById("topDivID").style.display = 'none';

}



window.onload=function (){
    //setChild();
    document.getElementById("Elephant_iframe").document.body.onload=function addGgColor(){
             iframe.document.body.style.backgroundColor="#ff0000";
    }
}
window.onscroll=function (){
    resizeDiva();
}


function resizeDiva(){
    document.getElementById("Elephant_iframe").style.position="absolute";
    //document.getElementById("menu_zdd").style.top=(parseInt(document.documentElement.clientHeight,10)/2)+parseInt(document.documentElement.scrollTop,10)+30+"px";
    //document.getElementById("menu_zdd").style.left=((parseInt(document.documentElement.clientWidth,10)/2))+"px";
    document.getElementById("Elephant_iframe").style.top=document.body.scrollTop + 50+"px";
    document.getElementById("Elephant_iframe").style.left=document.body.scrollLeft + 280+"px";
    //document.getElementById("Elephant_iframe").style.cssText = "left: 0px; top: 0px; width: 100%; height: 100%; background-color: #F5F5F5;";
}
function block(){
    document.getElementById("IframeWidow").style.display="block";
}

function setChild(){
      var div = document.getElementById("Elephant_iframe");
    //document.getElementById('Elephant_iframe').style.filter='alpha(opacity=10)';
     //setChild(div);
    //底色
   var cDiv = document.createElement;
   var backDiv = cDiv("div");
   backDiv.style.cssText = "left: 0px; top: 0px; width: 100%; height: 100%; background-color: #F5F5F5;";
   div.appendChild(backDiv);
}
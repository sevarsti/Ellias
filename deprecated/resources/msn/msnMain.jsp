<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2009-12-20
  Time: 1:47:53
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<script type='text/javascript' src='/dwr/engine.js'> </script>
<script type='text/javascript' src='/dwr/util.js'> </script>
<script type='text/javascript' src='/dwr/interface/MsnUtils.js'></script>
<html>
  <head>
      <title>Simple jsp page</title>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
    <script type="text/javascript">
        var order = ${msnForm.contactListOrder};
      function updateStatus()
      {
          try
          {
              MsnUtils.updateStatus('${msnForm.username}', afterUpdate);
          }
          catch(e)
          {

          }
      }

      function afterUpdate(contents)
      {
          if(contents != null) {
              //1: message
              var curContent = document.getElementById('content').innerHTML;
              if(contents[0] != null)
              {
                  for(var i = 0; i < contents[0].length; i++)
                  {
                      curContent += "<br/>" + contents[0][i][0] + "对你说: <br/>" + contents[0][i][1];
                  }
                  document.getElementById('content').innerHTML = curContent;
              }

              //2: status change
              curContent = document.getElementById('statusChange').innerHTML;
              if(contents[1] != null)
              {
                  for(var i = 0; i < contents[1].length; i++)
                  {
                      for(var j = 0; j < document.getElementsByName('contactListOption').length; j++)
                      {
                          if(document.getElementsByName('contactListOption')[j].value == contents[1][i][0])
                          {
                              var newName = '(' + contents[1][i][1] + ')';
                              if(contents[1][i][2].length > 20)
                              {
                                  newName += contents[1][i][2].substring(0,20) + '...';
                              }
                              else
                              {
                                  newName += contents[1][i][2];
                              }
                              newName += '(';
                              if(contents[1][i][3].length > 10)
                              {
                                  newName += contents[1][i][3].substring(0,10) + '...)';
                              }
                              else
                              newName += contents[1][i][3] + ')';
                              document.getElementsByName('contactListOption')[j].innerHTML =
                                newName;
                              break;
                          }
                      }
                  }
                  for(var i = 0; i < contents[1].length; i++)
                  {
                      curContent += "<br/>" + contents[1][i][4] + ' ' + contents[1][i][0] + "状态变为" + contents[1][i][1];
                  }
                  document.getElementById('statusChange').innerHTML = curContent;
              }

              //3: contact list
              if(contents[2] != null)
              {
                  for(var i = 0; i < contents[2].length; i++)
                  {
                      var found = false;
                      for(var j = 0; j < document.getElementById('contactList').options.length; j++)
                      {
                          if(contents[2][i][0] == document.getElementById('contactList').options[j].value)
                          {
                              found = true;
                              break;
                          }
                      }
                      if(!found)
                      {
                          document.getElementById('contactList').options[document.getElementById('contactList').options.length] = new Option();
                          document.getElementById('contactList').options[document.getElementById('contactList').options.length - 1].value = contents[2][i][0];
                          var newContName = '(' + contents[2][i][1] + ')';
                          if(contents[2][i][2].length > 20)
                          {
                              newContName += contents[2][i][2].substring(0, 20) + '...';
                          }
                          else
                          {
                              newContName += contents[2][i][2];
                          }
                          newContName += "(";
                          if(contents[2][i][3].length > 10)
                          {
                              newContName += contents[2][i][3].substring(0, 10) + '...';
                          }
                          else
                          {
                              newContName += contents[2][i][3];
                          }
                          newContName += ')';
                          document.getElementById('contactList').options[document.getElementById('contactList').options.length - 1].innerHTML = newContName;
                          document.getElementById('contactList').options[document.getElementById('contactList').options.length - 1].setAttribute('status', contents[2][i][1]);
                          document.getElementById('contactList').options[document.getElementById('contactList').options.length - 1].setAttribute('displayName', contents[2][i][2]);
                          document.getElementById('contactList').options[document.getElementById('contactList').options.length - 1].title =
                                '(' + contents[2][i][1] + ')' + contents[2][i][2] + '(' + contents[2][i][3] + ')';
//              var textlength = Number(list[i].parentNode.getAttribute("TextLength"));
                      }
                  }
              }
          }
      }

      function contextMenu()
      {
          popMenu(itemMenu,100,"111");
      }

        function popMenu(menuDiv,width,rowControlString)
        {
            //创建弹出菜单
            var pop=window.createPopup();
            //设置弹出菜单的内容
            pop.document.body.innerHTML = menuDiv.innerHTML;

            var rowObjs=pop.document.body.all[0].rows;
            //获得弹出菜单的行数
            var rowCount=rowObjs.length;
            //循环设置每行的属性
            for(var i=0;i<rowObjs.length;i++)
            {
                //如果设置该行不显示，则行数减一
                var hide = rowControlString.charAt(i)!='1';
                if(hide)
                {
                    rowCount--;
                }
                //设置是否显示该行
                rowObjs[i].style.display=(hide)?"none":"";
                //设置鼠标滑入该行时的效果
                rowObjs[i].cells[0].onmouseover=function(){
                    this.style.background="#818181";
                    this.style.color="white";
                }
                //设置鼠标滑出该行时的效果
                rowObjs[i].cells[0].onmouseout=function(){
                    this.style.background="#cccccc";
                    this.style.color="black";
                }
            }
            //屏蔽菜单的菜单
            pop.document.oncontextmenu=function(){
                return false;
            }
            //选择右键菜单的一项后，菜单隐藏
            pop.document.onclick=function(){
                pop.hide();
            }
            //显示菜单
            pop.show(event.clientX + 1,event.clientY + 1,width,rowCount*25,document.body);
            return true;
        }

      //根据状态对联系人排序
      function sortByStatus()
      {
          var newList = new Array(document.getElementsByName('contactListOption').length);
          for(var i = 0; i < document.getElementsByName('contactListOption').length; i++)
          {
//              var textlength = Number(list[i].parentNode.getAttribute("TextLength"));
              
          }
      }

      function addCurrentTalk()
      {
          document.getElementById('currentTalk').options[document.getElementById('currentTalk').options.length] = new Option();
          document.getElementById('currentTalk').options[document.getElementById('currentTalk').options.length - 1].value =
                  document.getElementById('contactList').value;
          for(var i = 0; i < document.getElementsByName('contactListOption').length; i++)
          {
              if(document.getElementsByName('contactListOption')[i].value == document.getElementById('contactList').value)
              {
                  document.getElementById('currentTalk').options[document.getElementById('currentTalk').options.length - 1].innerHTML =
                          document.getElementsByName('contactListOption')[i].getAttribute('displayName');
                  break;
              }
          }
          document.getElementById('currentTalk').options[document.getElementById('currentTalk').options.length - 1].text =
                  document.getElementById('contactList').getAttribute('displayName');
      }

      function sendMessage()
      {
          MsnUtils.sendMessage('${msnForm.username}', document.getElementById('currentTalk').value, document.getElementById('messageContent').value);
      }
  </script>
  </head>
  <%--<body onload="setInterval( updateStatus,1000)" oncontextmenu="contextMenu(); return false;">--%>
  <body  oncontextmenu="return false;">
  <input type="button" onclick="updateStatus();"/>
  <table width="100%" bgcolor="blue" cellpadding="0" cellspacing="1" style="height:400px;">
      <tr bgcolor="white" style="height:400px;">
          <td width="30%" rowspan="2" style="height:400px;">
              <select id="contactList" oncontextmenu="alert('aa')" size="30" ondblclick="addCurrentTalk();">
              <%--<select id="cont  actList" oncontextmenu="popMenu(contactListPopup,100,'111')" size="30">--%>
                  <c:forEach items="${msnForm.main.messenger.contactList.contacts}" var="contact">
                      <option status="${contact.status}" displayName="${contact.displayName}" value="${contact.email}"
                              id="contactListOption" name="contactListOption"
                              oncontextmenu="alert('end')"
                              <%--oncontextmenu="popMenu(contactListPopup,100,'111')"--%>
                              title="(${contact.status})${contact.displayName}(${contact.personalMessage})">
                          (${contact.status})${functions:cutString(contact.displayName, 20)}(${functions:cutString(contact.personalMessage, 10)})
                      </option>
                  </c:forEach>
              </select>
          </td>
          <td width="50%">
              <div id="content" style="height:350px;overflow-y:scroll;">
                  content
              </div>
          </td>
          <td width="20%">
              <div id="currentContactList">
                  
              </div>
          </td>
      </tr>
      <tr bgcolor="white">
          <td width="70%" colspan="2">
              <div style="overflow-y:scroll;height:50px;" id="statusChange">
                  x
              </div>
          </td>
      </tr>
      <tr>
          <td colspan="3">
              <select id="currentTalk">

              </select>
              <textarea rows="4" cols="100" id="messageContent"></textarea>
              <input type="button" value="发送" onclick="sendMessage();"/>
          </td>
      </tr>
  </table>

  <div id="contactListPopup" style="display:none">
      <table border="1" width="100%" height="100%" bgcolor="#CCCCCC" style="border:thin" cellspacing="0">
          <tr>
              <td style="border:outset 1;" align="left" onclick="parent.sortByStatus()">
                  按照状态排序
              </td>
          </tr>
      </table>
  </div>
  <div id="itemMenu" style="display:none">
              <table border="1" width="100%" height="100%" bgcolor="#cccccc" style="border:thin" cellspacing="0">
                  <tr>
                      <td style="cursor:default;border:outset 1;" align="center" onclick="parent.create()">
                          creat row
                      </td>
                  </tr>
                  <tr>
                      <td style="cursor:default;border:outset 1;" align="center" onclick="parent.update();">
                          modify row
                      </td>
                  </tr>
                  <tr>
                      <td style="cursor:default;border:outset 1;" align="center" onclick="parent.del();">
                          delete row
                      </td>
                  </tr>
              </table>
          </div>
  </body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2009-1-10
  Time: 1:57:29
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<%--<%@ page pageEncoding="gb2312" %>--%>
<html>
    <head><title>Simple jsp page</title></head>
    <body>
        <html:form action="/system/addTable.do">
            <input type="hidden" name="method" value="addTable"/>
            表名：<html:text property="tableName"/>
            <script type="text/javascript">
                function addColumn()
                {
                    var newRow = document.getElementById('table').insertRow(document.getElementById('table').rows.length - 1);
                    var newTd = newRow.insertCell();
                    newTd.className = 'fieldvalue';
                    newTd.innerHTML = "<input name='colName' type='text' class='inputbox'/>";
                    newTd = newRow.insertCell();
                    newTd.className = 'fieldvalue';
                    newTd.innerHTML = "<td class='fieldvalue'><select name='colType'><option value='int'>int</option><option value='varchar'>varchar</option><option value='date'>date</option></select></td>";
                    newTd = newRow.insertCell();
                    newTd.className = 'fieldvalue';
                    newTd.innerHTML = "<input type='text' name='colLength' class='inputbox'/>"
                    newTd = newRow.insertCell();
                    newTd.className = 'fieldvalue';
                    newTd.innerHTML = "<input type='button' onclick='deleteColumn(this.parentNode.parentNode.rowIndex)' class='otterbtn' value='删除'/>";
                }
                function deleteColumn(id)
                {
                    document.getElementById('table').deleteRow(id);
                }
            </script>
            <table id="table" cellpadding="1" cellspacing="1" border="1">
                <!--class="navigator" -->
                <tr>
                    <td >列名</td>
                    <!--class="fieldname"-->
                    <td >类型</td>
                    <td >长度</td>
                    <td >操作</td>
                </tr>
                <!--<tr>-->
                    <!--<td class="fieldvalue">-->
                        <!--<input type="text" class="inputbox"/>-->
                    <!--</td>-->
                    <!--<td class="fieldvalue">-->
                        <!--<select>-->
                            <!--<option value="int">int</option>-->
                            <!--<option value="varchar">varchar</option>-->
                            <!--<option value="date">date</option>-->
                        <!--</select>-->
                    <!--</td>-->
                    <!--<td class="fieldvalue">-->
                        <!--<input type="text" class="inputbox" disabled/>-->
                    <!--</td>-->
                    <!--<td class="fieldvalue">-->
                    <!--</td>-->
                <!--</tr>-->
            </table>
            <input type="button" class="otterbtn" value="增加" onclick="addColumn();"/>
            <br/>
            <input type="submit" value="确定" class="otterbtn"/>
        </html:form>
    </body>
</html>
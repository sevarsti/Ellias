<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 11-12-15
  Time: 下午3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
<HEAD>
    <title>每日持仓查询</title>
    <LINK href="<c:url value="/include/main.css"/>" type="text/css" rel="stylesheet">

    <!--<script language="javascript" src="/scripts/prototype.js"></script>-->
    <!--<script language="javascript" src="/scripts/jquery-1.3.2.min.js"></script>-->
    <!--<script language="javascript" src="/scripts/jquery-ui-1.7.custom.min.js"></script>-->
    <script language="javascript" src="/scripts/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<c:url value="/scripts/extjs/adapter/ext/ext-base.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/scripts/extjs/ext-all.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/scripts/extjs/resources/css/core.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/scripts/extjs/resources/css/tabs.css"/>"/>

</HEAD>
<body>
<div class="body">
    <%--<%@ include file="header.jsp" %>--%>
    <div class="page_title">
       组合报表查询
    </div>
    <html:form action="/gh/query">
        <input type="hidden" name="method" value="query"/>
        <script type="text/javascript">
    Ext.onReady(function() {
                // basic tabs 1, built from existing content
                var tabs = new Ext.TabPanel({
                    renderTo: 'tabs1',
                    activeTab: 0,
                    frame:true,
                    defaults:{autoHeight: true},
                    items:[
                    {contentEl:'portfolioAnalyze', title: '个股归因分析'},
                    {contentEl:'bargain', title: '组合交易'},
                    {contentEl:'reference', title: '基准权重'}
                            ]
                });

            });
            function showObj(id)
            {
                var obj = document.getElementById(id);
                if(obj.style.display == '')
                {
                    obj.style.display = 'none';
                }
                else
                {
                    obj.style.display = '';
                }
            }

            function queryItems(){
                document.forms[0].method.value='query';
                document.forms[0].submit() ;
            }

            function expData() {
                document.forms[0].method.value='export';
                document.forms[0].submit() ;
            }
        </script>
        <p></p>
        <table>
            <tr>
                <td>
                    开始时间：
                    <html:text property="startdate" size="12" onclick="WdatePicker();" styleClass="inputbox"/>
                    结束时间：
                    <html:text property="enddate" size="12" onclick="WdatePicker();" styleClass="inputbox"/>
                    &nbsp;
                    <input type="button" value="查询" class="otterbtn" onclick="queryItems();" id='doquery'/>
                    <%--<input type="button" value="导出" class="otterbtn" onclick="expData();" id='doexport'/>--%>
                </td>
            </tr>
        </table>
        <div id="tabs1">
            <div id="portfolioAnalyze" class="x-hide-display defaultMargin">
                <input type="button" value="展开/收起组合收益率"  size="85" class="otterbtn" onclick="showObj('pflProfit');"/>
                <br/>

                <div id="pflProfit">
                    <table border=0 bordercolor="#EEEEEE" cellpadding="1" cellspacing="1">
                        <tr class="portfolioRowHeader1">
                            <th>日期</th>
                            <th>组合日收益率</th>
                            <th>基准日收益率</th>
                            <th>组合累计收益率</th>
                            <th>基准累计收益率</th>
                            <th>累计超额</th>
                        </tr>
                        <c:forEach items="${pubdates}" var="d" varStatus="i">
                            <tr class="row${i.index % 2 + 1}">
                                <td>
                                    <fmt:formatDate value="${d}" pattern="yyyy-MM-dd"/>
                                    <%--<fmt:formatNumber value="${p['PUBDATE']}" pattern="00000000"/>--%>
                                </td>
                                <td align="right" class="numbergrid">
                                    <functions:dispColorfulProfitRatio value="${pflProfits[d]['PFLPROFITRATIO']}" pattern="#,##0.0000%"/>
                                </td>
                                <td align="right" class="numbergrid">
                                    <functions:dispColorfulProfitRatio value="${pflProfits[d]['REFPROFITRATIO']}" pattern="#,##0.0000%"/>
                                </td>
                                <td align="right" class="numbergrid">
                                    <functions:dispColorfulProfitRatio value="${pflProfits[d]['PFLTOTALPROFITRATIO']}" pattern="#,##0.0000%"/>
                                </td>
                                <td align="right" class="numbergrid">
                                    <functions:dispColorfulProfitRatio value="${pflProfits[d]['REFTOTALPROFITRATIO']}" pattern="#,##0.0000%"/>
                                </td>
                                <td align="right" class="numbergrid">
                                    <functions:dispColorfulProfitRatio value="${pflProfits[d]['PFLTOTALEXCESS']}" pattern="#,##0.0000%"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <br/>
                <input type="button" value="展开/收起组合权重"  size="15"  class="otterbtn" onclick="showObj('itemWeight');"/>
                <br/>

                <div id="itemWeight" style="display:none;">
                    <table border=0 bordercolor="#EEEEEE" cellpadding="1" cellspacing="1">
                        <tr class="portfolioRowHeader1">
                            <th>日期</th>
                            <c:forEach items="${pflStocks}" var="stock">
                                <th>${stock[1]}(${stock[0]})</th>
                            </c:forEach>
                        </tr>
                        <c:forEach items="${pubdates}" var="d" varStatus="i">
                            <tr class="row${i.index % 2 + 1}">
                                <td nowrap>
                                    <fmt:formatDate value="${d}" pattern="yyyy-MM-dd"/>
                                </td>
                                <c:forEach items="${pflStocks}" var="s" varStatus="j">
                                    <td align="right" class="numbergrid">
                                        <c:choose>
                                            <c:when test="${!empty itemWeights[d][s[0]]}">
                                                <fmt:formatNumber value="${itemWeights[d][s[0]]['WEIGHTINPFL']}" pattern="#,##0.0000%"/>
                                            </c:when>
                                            <c:otherwise>
                                                -
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <br/>
                <input type="button" value="展开/收起个股收益" size="15" class="otterbtn" onclick="showObj('itemProfit');"/>
                <br/>

                <div id="itemProfit" style="display:none;">
                    <table border=0 bordercolor="#EEEEEE" cellpadding="1" cellspacing="1">
                        <tr class="portfolioRowHeader1">
                            <th>日期</th>
                            <c:forEach items="${pflStocks}" var="stock">
                                <th>${stock[1]}(${stock[0]})</th>
                            </c:forEach>
                        </tr>
                        <c:forEach items="${pubdates}" var="d" varStatus="i">
                            <tr class="row${i.index % 2 + 1}">
                                <td nowrap>
                                    <fmt:formatDate value="${d}" pattern="yyyy-MM-dd"/>
                                </td>
                                <c:forEach items="${pflStocks}" var="s" varStatus="j">
                                    <td align="right" class="numbergrid">
                                        <c:choose>
                                            <c:when test="${!empty itemWeights[d][s[0]]}">
                                                <functions:dispColorfulProfitRatio value="${itemWeights[d][s[0]]['PROFITRATIOINPFL']}" pattern="#,##0.0000%"/>
                                            </c:when>
                                            <c:otherwise>
                                                -
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <br/>
                <input type="button" value="展开/收起个股超额贡献" size="15" class="otterbtn" onclick="showObj('contriProfit');"/>
                <br/>
                <div id="contriProfit" style="display:none;">
                    <table border=0 bordercolor="#EEEEEE" cellpadding="1" cellspacing="1">
                        <tr class="portfolioRowHeader1">
                            <th nowrap>日期</th>
                            <th nowrap>累计贡献</th>
                            <th nowrap>单日贡献</th>
                            <c:forEach items="${pflStocks}" var="stock">
                                <th>${stock[1]}(${stock[0]})</th>
                            </c:forEach>
                        </tr>
                        <c:forEach items="${pubdates}" var="d" varStatus="i">
                            <tr class="row1">
                                <td nowrap class="numbergrid">
                                    <fmt:formatDate value="${d}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td nowrap align="right">
                                    <functions:dispColorfulProfitRatio value="${pflProfits[d]['TOTALCONTRIBUTERATIO']}" pattern="#,##0.0000%"/>
                                </td>
                                <td nowrap align="right">
                                    <functions:dispColorfulProfitRatio value="${pflProfits[d]['CONTRIBUTERATIO']}" pattern="#,##0.0000%"/>
                                </td>
                                <c:forEach items="${pflStocks}" var="s">
                                    <td align="right" class="numbergrid">
                                        <c:choose>
                                            <c:when test="${!empty itemWeights[d][s[0]]}">
                                                <functions:dispColorfulProfitRatio value="${itemWeights[d][s[0]]['CONTRIBUTIONINPFL']}" pattern="#,##0.0000%"/>
                                            </c:when>
                                            <c:otherwise>
                                                -
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        <%--<tr class="row1">--%>
                            <%--<td>合计</td>--%>
                            <%--<td></td>--%>
                            <%--<td></td>--%>
                                <%--<td nowrap align="right" class="numbergrid">--%>
                                    <%--<functions:dispColorfulProfitRatio value="1.2" pattern="#,##0.00%"/>--%>
                                <%--</td>--%>
                        <%--</tr>--%>
                    </table>
                </div>
                <br/>
            </div>
            <p></p>
            <div id="bargain" class="x-hide-display defaultMargin">
                <table border=0 bordercolor="#EEEEEE" cellpadding="1" cellspacing="1">
                    <tr class="portfolioRowHeader1">
                        <th>发生日期</th>
                        <th>代码</th>
                        <th>简称</th>
                        <th>买入数量</th>
                        <th>买入金额</th>
                        <th>卖出数量</th>
                        <th>卖出金额</th>
                        <th>送股数量</th>
                        <th>分红金额</th>
                    </tr>
                    <c:set var="rowindex" value="1"/>
                    <c:forEach items="${bargains}" var="b" varStatus="i">
                        <c:if test="${i.index != 0 && (bargains[i.index]['PUBDATE'] != bargains[i.index - 1]['PUBDATE'])}">
                            <c:set var="rowindex" value="${3 - rowindex}"/>
                        </c:if>
                        <tr class="row${rowindex}">
                            <td>
                                <fmt:formatDate value="${b['PUBDATE']}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                ${b['STKCODE']}
                            </td>
                            <td>
                                ${b['STKNAME']}
                            </td>
                            <td align="right">
                                ${b['BUYAMOUNT']}
                            </td>
                            <td align="right">
                                ${b['BUYMONEY']}
                            </td>
                            <td align="right">
                                ${b['SELLAMOUNT']}
                            </td>
                            <td align="right">
                                ${b['SELLMONEY']}
                            </td>
                            <td align="right">
                                ${b['DIVIDEND']}
                            </td>
                            <td align="right">
                                ${b['CASH']}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <p></p>
            <div id="reference" class="x-hide-display defaultMargin">
                  <table border=0 bordercolor="#EEEEEE" cellpadding="1" cellspacing="1">
                        <tr class="portfolioRowHeader1">
                            <th>日期</th>
                            <th>代码</th>
                            <th>简称</th>
                            <th>权重</th>
                            <th>基准内贡献</th>
                        </tr>
                        <c:set var="rowClass" value="1"/>
                        <c:forEach items="${refStocks}" var="s" varStatus="i">
                            <c:if test="${i.index != 0 && (refStocks[i.index]['PUBDATE'] != refStocks[i.index - 1]['PUBDATE'])}">
                                <c:set var="rowindex" value="${3 - rowindex}"/>
                            </c:if>
                            <tr class="row${rowindex}">
                                <td>
                                    <fmt:formatDate value="${s['PUBDATE']}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td>
                                    ${s['STKCODE']}
                                </td>
                                <td>
                                    ${s['STKNAME']}
                                </td>
                                <td align="right">
                                    <fmt:formatNumber value="${s['WEIGHTINREF']}" pattern="#,##0.00%"/>
                                </td>
                                <td align="right">
                                    <functions:dispColorfulProfitRatio value="${s['CONTRIBUTIONINREF']}" pattern="#,##0.0000%"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
            </div>
        </div>
    </html:form>
</body>
</HTML>
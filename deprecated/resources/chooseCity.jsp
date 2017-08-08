<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2010-4-27
  Time: 21:54:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<LINK type="text/css" rel="stylesheet" href="/include/main.css">
<html>
    <head>
        <title>Simple jsp page</title>
    </head>
    <script type="text/javascript">
        var provinces = new Array("上海","北京","天津");
        var cities = new Array();
        cities[0] = new Array("长宁", "杨浦");
        cities[1] = new Array("1", "2");

        function initList()
        {
            for(var i = 0; i < provinces.length; i++)
            {
                document.getElementById('province')[i] = new Option();
                document.getElementById('province')[i].text = provinces[i];
                document.getElementById('province')[i].value = provinces[i];
            }
        }

        function changeProvince(p)
        {
            for(var i = document.getElementById('city').length - 1; i >= 0; i--)
            {
                document.getElementById('city')[i] = null;
            }
            var provinceIndex = -1;
            for(var i = 0; i < provinces.length; i++)
            {
                if(provinces[i] == p)
                {
                    provinceIndex = i;
                    break;
                }
            }
            if(provinceIndex != -1)
            {
                for(var i = 0; i < cities[provinceIndex].length; i++)
                {
                    document.getElementById('city')[i] = new Option();
                    document.getElementById('city')[i].text = cities[provinceIndex][i];
                    document.getElementById('city')[i].value = cities[provinceIndex][i];
                }
            }
        }
    </script>
    <body onload="initList();">
        <select id="province" onchange="changeProvince(this.value)"></select>
        <br/>
        <select id="city"></select>
        <input type="button" onclick="alert(cities[document.getElementById('a').value][document.getElementById('b').value])"/>
    </body>
</html>
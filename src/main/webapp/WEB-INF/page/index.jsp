<%--
  Created by IntelliJ IDEA.
  User: panqingjian
  Date: 2017/3/24
  Time: 下午5:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>爬取后台</title>
</head>
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/laydate.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        query(1);
    });

    function load(url, data) {
        $("#queryContent").load(url, data);
    }

    function query(page) {
        var url = "/queryContent.htm";
        load(url, getQueryObj(page));
    }

    function getQueryObj(page) {
        var obj = new Object();
        var sendTimeOrder = $("#sendTimeOrder").attr("checked");
        var moneyOrder = $("#moneyOrder").attr("checked");
        var address = $("#address").val();
        obj.address = address;
        var moneyStart = $("#moneyStart").val();
        obj.moneyStart = moneyStart;
        var moneyEnd = $("#moneyEnd").val();
        obj.moneyEnd = moneyEnd;
        var sendTimeStart = $("#sendTimeStart").val();
        obj.sendTimeStart = sendTimeStart;
        var sendTimeEnd = $("#sendTimeEnd").val();
        obj.sendTimeEnd = sendTimeEnd;
        if (sendTimeOrder == 'checked') {
            obj.sendTimeOrder = true;
        }
        if (moneyOrder == 'checked') {
            obj.moneyOrder = true;
        }
        obj.page = page;
        return obj;
    }

</script>
<body>
地址:<input id="address" name="address" type="text"><br/>
金额:<input id="moneyStart" name="moneyStart" type="number" min="1"> -- <input id="moneyEnd" name="moneyEnd" type="number"
                                                                             min="1"><br/>
发布时间:<input id="sendTimeStart" name="sendTimeStart" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"> --
<input id="sendTimeEnd" name="sendTimeEnd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
<br/>
<input type="checkbox" id="sendTimeOrder" name="sendTimeOrder" checked/>最近发布时间排序
<input type="checkbox" id="moneyOrder" name="moneyOrder"/>最低金额排序
<input type="button" value="查 询" onclick="query(1);">
<div id="queryContent">

</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: panqingjian
  Date: 2017/3/24
  Time: 下午5:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    table {
        border: 1px solid
    }

    table td {
        border: 1px solid
    }
    /* css注释：只对table td标签设置红色边框样式 */
</style>
<table>
    <tr align="center" style="font-size: larger">
        <td>标题</td>
        <td>链接</td>
        <td>房屋大小</td>
        <td>地址</td>
        <td>个人信息</td>
        <td>金额(元/月)</td>
        <td>发布时间</td>
        <%--<td>爬取时间</td>--%>
    </tr>
    <c:forEach var="obj" items="${list}">
        <tr align="center">
            <td>${obj.titleName}</td>
            <td><a href="${obj.href}" target="_blank">查看</a></td>
            <td>${obj.roomSize}</td>
            <td>${obj.address}</td>
            <td>${obj.geren}</td>
            <td>${obj.money}</td>
            <td>${obj.sendTime}</td>
                <%--<td>${obj.updateTime}</td>--%>
        </tr>
    </c:forEach>
</table>
<hr/>
<c:forEach var="i" begin="1" end="${pages}" step="1">
<a href="javascript:void(0)" onclick="query(${i})">第${i}页||</a>
</c:forEach>
<br/>当前第${page}页,一共${pages}页,${count}条

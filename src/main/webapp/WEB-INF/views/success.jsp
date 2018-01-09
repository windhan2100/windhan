<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>成功页面</title>
  </head>
  
  <body>
    <h2>操作成功！！！</h2>
    <br /><br />
    <c:if test="${not empty user }">
    	<h4>查找一个用户成功！</h4>
    	用户名：${user.username }<br />
    	密&nbsp;码：${user.password }
    </c:if>
    
    <br /><hr />
    map的key为一个对象时取值：${map[user.username]}
            例子的值：${map[user.username]}
  </body>
</html>

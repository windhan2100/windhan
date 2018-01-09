<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>主页</title>
  </head>
  
  <body>
    <h5>跳转后的结果</h5><br /><hr />
    1.表单提交：<br />
   	 用户名：${username }<br />
   	性别：${sex } <br />
    demo:${sessionScope.demo } <br />
    demoReuqest:${demoRequest } <br />
    user对象里取名字：${user.username }<br /><br /><hr />
   
    <c:if test="${flag == 'ok' }">
    	<div style="color:red;">删除用户id为${id }成功</div>
    	map的例子：${map.mapDemo}
    </c:if>
    <br /><br /><hr />
    
     <c:if test="${flag == 'ok1' }">
    	<div style="color:blue;">修改用户id为${id }成功</div>
    </c:if>
    <br /><br /><hr />
   <c:if test="${!empty user}">
    	用户名：${user.username }<br />
    	密码： ${user.password } <br />
    	ID：  ${user.id }
   </c:if>
   
	<br /><br />
	<c:if test="${!empty userList}">
		<c:forEach items="${userList }" var="user">
			<div style="background:pink;">
				用户名：${user.username }<br />
	    		密码： ${user.password } <br />
	    		ID：  ${user.id } <br />
			</div>
		</c:forEach>    
	</c:if>
  </body>
</html>

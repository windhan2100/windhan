<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查询所有的用户</title>
   <script type="text/javascript" src="../../static/script/jquery.js"></script>
   <script type="text/javascript">
    	$(document).ready(function(){
    			 //判断一下输入的页数是否为阿拉伯数字
  			$("#num").keyup(function(){
  				//需要检索的页数
  				var num = $("#num").val();
  				 for(var i=0; i<num.trim().length; i++) {
  					if(num.charCodeAt(i)<48 || num.charCodeAt(i)>57) {
  						$("#num").val("");
  					} 
  				}
  			});
    		  		    
    		$("#go").click(function(){
    			//需要检索的页数
    			var num = $("#num").val();
    			var basePath = $("#basePath").val();
    			
				//检索结果的总页数
				var total = $("#total").val();
				//注意一下：IE不支持trim()
    			//if(num.trim().length == 0) {
    			if(num.length == 0) {
    				alert("请输入后再查询！");
    			} else {
					if(parseInt(num) > parseInt(total)) {
						alert("请输入正确的页数！！");
						$("#num").val("");
						$("#num").focus();
					} else {
		    			window.location.href= basePath + "/user/showall/"+num;
					}
    			}
    		});
    	
    	});
    </script>
  </head>
  
  <body>
  <a href="${basePath}">去首页</a>
  <br /><hr />
   <c:choose>
   	<c:when test="${listSize > 0 }">
   		<table cellpadding="15" cellspacing="1" bgcolor="#000000">
 			<thead>
 				<tr bgcolor="#ffffff" >
	   				<td>Id</td>
	   				<td>用户名</td>
	   				<td>密码</td>
   				</tr>
   			</thead>
   			<tbody>
 			<c:forEach var="item" items="${page.pageList}">
   				<tr bgcolor="#ffffff">
   					<td>${item.id}</td>
   					<td>${item.username}</td>
   					<td>${item.password}</td>
   				</tr>
 			</c:forEach>
   			</tbody>
		</table>
		   		<br/>
   		<c:choose>
   			<c:when test="${page.first}">
   				首页
   			</c:when>
   			<c:otherwise>
   				<a href="${basePath }/user/showall/1">首页</a>
   			</c:otherwise>
   		</c:choose>
   		<c:choose>
   			<c:when test="${page.prev}">
   				<a href="${basePath }/user/showall/${page.currPage-1}">上一页</a>
   			</c:when>
   			<c:otherwise>
   				上一页
   			</c:otherwise>
   		</c:choose>
   		<c:choose>
   			<c:when test="${page.next}">
   				<a href="${basePath }/user/showall/${page.currPage+1}">下一页</a>
   			</c:when>
   			<c:otherwise>
   				下一页
   			</c:otherwise>
   		</c:choose>
   		<c:choose>
   			<c:when test="${page.last}">
   				尾页
   			</c:when>
   			<c:otherwise>
   				<a href="${basePath }/user/showall/${page.totalPages}">尾页</a>
   			</c:otherwise>
   		</c:choose>
		   		<br />
		   		
   		<h5 style="margin-left:100px;margin-right:100px;display:inline"> 
   			共${page.totalCount}条记录&nbsp;&nbsp;&nbsp;共 ${page.totalPages } 页 
   			&nbsp;&nbsp;&nbsp;${page.pageSize}条/页  &nbsp;&nbsp;&nbsp;
   			<span style="color:red;">当前是第${page.currPage }页</span></h5>
   		
   		转到<input type="text" id="num"/>页→ <input id="go" type="button" value="Go">
   	</c:when>
   	<c:otherwise>
   		<div style="color:blue;font-size:24px">很抱歉，没有相应的结果！</div>
   	</c:otherwise>
   </c:choose>
   <input  type="hidden" id="basePath" value="${basePath}"/>
   <input type="hidden" value="${page.totalPages}" id="total"/>
  </body>
</html>

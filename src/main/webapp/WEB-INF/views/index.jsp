<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页-测试</title>
<script type="text/javascript" src="static/script/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		/**
			删除用户
		*/
		$("#del").click(function(){
			var basePath = $("#basePath").val();
			alert(basePath +  "/user/delajax/25");
			$.get(basePath + "/user/delajax/25",function(data){
				if(data == 'ok') {
					alert("删除成功！");
				} else {
					alert("删除失败！！");
				}
			});
			
		});
		
		/**
		查找 用户
		*/
		/*	$("#show").click(function(){
				var basePath = $("#basePath").val();
				alert(basePath);
				$.get(basePath + "/user/showajax/25",function(data){
					alert(data);
					if(data == null) {
						alert("null");//此种情况，我们是不能这样判断后台传来的是否为null;
					}
					
					if(data == '[object XMLDocument]') {
						alert("后台传来的数据为null");
					}
				});
			});
		*/
		
			/**
			 * 查找用户
			 */
			$("#show").click(function(){
				var basePath = $("#basePath").val();
				//alert(basePath);
				$.get(basePath + "/user/showajax/25",function(data){
					//var jsonx = {"tom":"xx","jerry":"xxxx","marry":{"tom":"xxxxxx","jerry":"xxxx"}};
					//alert(jsonx.marry.tom);
					//alert(jsonx);
					
					//alert(data);
					console.log(data.id);
					if(data == 'null') {
						alert("后台传来的为null");//我们加一个‘json’就可以判断后台传来的是否为null,当我们的后台传来的是json格式是，前台最好加一个‘json’格式
					}
				},'json');
			});
		//alert(1);
		
		/**
			文件上传
		**/
		$("#upload_button").click(function(){
			var basePath = $("#basePath").val();
			var formData = new FormData($("#upload2")[0]);
			console.log(basePath);
			console.log(formData);
			$.ajax({
				url:basePath + "/user/upload2",
				type:"POST",
				data:formData,
				async:false,
				cache:false,
				contentType: false,  
		        processData: false, 
		        success:function(returnData){
		        	alert(returnData.msg);
		        },
		        error:function(returnData){
		        	alert(returnData.msg);
		        }
			});
			
		});
	});
	
	function uploadFile() {
		var basePath = $("#basePath").val();
		var fileObj = document.getElementById("file");//JS获取文件
		var formData = new FormData($("#upload3")[0]);
		var url = basePath + "/user/upload2";
		
		// XMLHttpRequest 对象
        var xhr = new XMLHttpRequest();
        xhr.open("post", url, true);
        xhr.onload = function () {
           // alert("上传完成!");
        };
        
        xhr.upload.addEventListener("progress", progressFunction, false);
        xhr.send(formData);
	}
    function progressFunction(evt) {
        var progressBar = document.getElementById("progressBar");
        var percentageDiv = document.getElementById("percentage");
        if (evt.lengthComputable) {
            progressBar.max = evt.total;
            progressBar.value = evt.loaded;
            percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100) + "%";
            if(evt.loaded==evt.total){
                alert("上传完成100%");
            }
        }
    } 
</script>
</head>
<body>
<h1>欢迎来到springmvc</h1>
	绝对路径BasePath:${basePath }<br/>
<input type="hidden" value="${basePath}" id="basePath"/>
<hr/>

<%-- 1.表单提交(增) --%>
    <h4>1.表单提交(增)</h4>
    <!-- 表单默认：method="get" -->
    <form action="${basePath }/user/login" method="post">
    	Username:<input type="text" name="username"/><br /><br />
    	&nbsp;&nbsp;&nbsp;&nbsp;
    	Sex:<input type="text" name="sex"/><br /><br />
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="submit" value="login"/>
    </form>
    <hr />	
    
    2.删<br />
    <a href="${basePath }/user/del/25">删除id为25的用户（后台代码有map的例子）</a> <br /><br />
    <a href="javascript:void(0)" id="del">删除id为25的用户___ajax</a>
    <br /><br /><hr />

    3.改<br />
    <a href="${basePath }/user/update/25">修改id为25的用户</a>
    <!-- ajax的修改的就不写了。和删除类型！！ -->
    
    <br /><br /><br />
    4.查<br />
    <a href="${basePath }/user/show/25">查id为25的用户</a><br />
    <a href="javascript:void(0)" id="show">ajax查找id为25的用户</a><br />
    
    <hr /><br />
    5.<a href="${basePath }/user/showall/1">查找所有（+加分页）</a>
    
    <hr /><br />
    6.当Map集合的key为一个对象时，我们应该如何去取值：<br />
    <a href="${basePath}/user/getmap">当Map集合的key为一个对象时，我们应该如何去取值：</a>
    
    <hr /><br />
    7.文件的上传<br />
    <%-- 采用file.Transto 来保存上传的文件 --%>
    <form action="user/upload" method="post" enctype="multipart/form-data">
    	File: <input type="file" name="myfile"/><br />
    	name: <input type="text" name="name"/><br />
    	<input type="submit" value="go"/><br />
    </form>
    <br/><hr />
    
    8.文件的上传2:Ajax<br />
    <%-- 采用file.Transto 来保存上传的文件 --%>
    <form id="upload2" action="user/upload2" method="post" enctype="multipart/form-data">
    	File: <input type="file" name="file"/><br />
    	name: <input type="text" name="decription"/><br />
    	<input type="button" id="upload_button" value="上传"/><br />
    </form>
    <br/><hr/>
    
    9.文件上传3:带有进度条
    <progress id="progressBar" value="0" max="100"></progress>
    <span id="percentage"></span><br/><br/><br/><br/>
    <form id="upload3">
    	File: <input type="file" name="file"/><br />
    	<input type="button" onclick="uploadFile()" value="上传"/><br />
    </form>
</body>
</html>
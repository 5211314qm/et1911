<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>首页</title>
	</head>
	<body>
		<div style="width: 400px;margin: 200px auto">
			<fieldset>
			 	<legend>测试数组传参</legend>
				<!-- /complex/array -->
				<form action="${pageContext.request.contextPath}/complex/array" method="post">
					爱好: 
					<input type="checkbox" name="hobby" value="踢球">踢球
					<input type="checkbox" name="hobby" value="听音乐">听音乐
					<input type="checkbox" name="hobby" value="看书">看书
					<br>
					<input style="margin-top: 10px;" type="submit" value="测试数组传参" />
				</form>
			</fieldset>
			
			<hr>
			
			<fieldset>
				<legend>测试List传参</legend>
				<!-- /complex/list -->
				<form action="${pageContext.request.contextPath}/complex/list" method="post">
					爱好: 
					<input type="checkbox" name="hobbyList" value="踢球">踢球
					<input type="checkbox" name="hobbyList" value="听音乐">听音乐
					<input type="checkbox" name="hobbyList" value="看书">看书
					<br>
					<input style="margin-top: 10px;" type="submit" value="测试List传参" />
				</form>
			</fieldset>
			
			<hr>
			<button type="button" id="testMap">测试Map传参</button>
			
			<hr>
			<button type="button" onclick="jsonToMap()">测试json转Map传参</button>
			
			<hr>
			<button type="button" onclick="jsonToList()">测试json转List传参</button>
			
			<hr>
			<button type="button" onclick="jsonToBean()">测试json转Bean传参</button>
			
		</div>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
		<script type="text/javascript">
			const path = "${pageContext.request.contextPath}";
			
			$(function(){
				
				//测试Map传参
				$('#testMap').click(() => {
					$.ajax({
						url: path + '/complex/map',
						type: "post",	//http method
						dataType: 'text',	//文本
						data: 'stuMap["id"]=1&stuMap["name"]=zhangsna',
						success: function(data){
							alert(data);
						}
					});
				});
				
			})
			
			function jsonToMap() {
				// js对象
				let obj = {id:1,name:"et1911"}
				$.ajax({
					url: path+'/json/jsonToMap',
					type: 'post',
					data: JSON.stringify(obj), //将js对象转成json字符串
					dataType: 'json',
					contentType: 'application/json;charset=UTF-8',
					success: function (data) {
						alert(data.msg)
					}
				});
			}
			
			function jsonToList() {
				let array = [{id:2,name:"et1911",age:25}];
				let user = {id:1,name:"etoak",age:20}
				array.push(user);
				$.ajax({
					url: path+'/json/jsonToList',
					type: 'post',
					data: JSON.stringify(array),
					dataType: 'json',
					contentType: 'application/json;charset=UTF-8',
					success: function(data){
						alert(data.code + " - " + data.msg);
					}
					
				});
			}
			
			function jsonToBean(){
				
				let obj = {
						id: 1,
						name: "张三",
						age: 23,
						hobbyList: ['看书','踢球'],
						stuMap: {id:2,score:200}
				};
				
				$.ajax({
					url: path + '/json/jsonToBean',
					type: 'post',
					data: JSON.stringify(obj),
					dataType: 'json',
					contentType: 'application/json;charset=UTF-8',
					success: function(data){
						alert(data.code + " : " +data.msg);
					}
				});
			}
		</script>
	</body>
</html>
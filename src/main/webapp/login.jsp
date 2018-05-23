<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<style type="text/css">
span {
	display: block;
}

.error {
	height: 20px;
	color: red;
	display: block;
}

.error1 {
	display: none;
}

.error2 {
	display: none;
}
</style>
</head>
<body style="background-color: #cdcdcd">
	<div class="container"
		style="width: 1000px; background-color: #ffffff; padding: 0px; height: 600px;">
		<br> <br>
		<table>
			<tr>
				<td style="width: 80px;"></td>
				<td style="position: relative; font-size: xx-large;">用户登录</td>
			</tr>
		</table>
		<hr
			style="border: none; border-top: 5px groove skyblue; width: 850px; margin-top: 10px; margin-bottom: 10px" />
		<br>
		<div class="row" style="margin-top: 100px;">
			<form>
				<div class="col-md-6"></div>
				<div class="col-md-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">登录</h3>
						</div>
						<div class="panel-body">
							<div>
								<input type="text" class="form-control" id="name" name="name"
									placeholder="用户名">
							</div>
							<div class="error">
								<h5 class="error1">用户名不能为空!</h5>
							</div>
							<div>
								<input type="password" class="form-control" id="psw" name="psw"
									placeholder="密码">
							</div>
							<div class="error">
								<h5 class="error2">密码不能为空!</h5>
							</div>
							<div>
								<input id="login" type="button"
									class="btn btn-primary btn-lg btn-block" value="登录">
							</div>
							<div>
								<h5 style="color: red;" id="error0"></h5>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		//回车事件绑定
		document.onkeydown = function(event) {
			var e = event || window.event
					|| arguments.callee.caller.arguments[0];
			if (e && e.keyCode == 13) {
				$('#login').click();
			}
		};

		//登录操作
		$('#login').click(
				function() {
					var name = $('#name').val();
					var psw = $('#psw').val();
					if (name == "") {
						$(".error1").css("display", "block");
						return false;
					} else {
						$(".error1").css("display", "none");
					}
					if (psw == "") {
						$(".error2").css("display", "block");
						return false;
					} else {
						$(".error2").css("display", "none");
					}
					var data = {
						psw : psw,
						name : name,
					};
					//console.log(data);
					$.ajax({
						url : "${pageContext.request.contextPath}/deliverysession.do",
						data : data,
						type : "post",
						dataType : "json",
						success : function(result) {
							console.log(result);
							if (result.status != 100) {
								$("#error0").text(result.message);
								return;
							} else {
								$("#error0").text(result.message);
								setTimeout(function() {
											//登录返回
											window.location.href = result.back_url || "${pageContext.request.contextPath}/";
										}, 500)
							}
						},
					});
					$("#error0").css("display", "block");
				});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<!-- ȭ�� ����ȭ -->
	<meta name="viewport" content="width-device-width", initial-scale="1">
	<!-- ��Ʈ ������ ��Ʈ��Ʈ���� �����ϴ� ��ũ -->
	<link rel="stylesheet" href="css/bootstrap.css"><!-- href="css/bootstrap.min.css"�� �����ϴ�!!-->
	<link rel="stylesheet" href="css/custom.css">
	
	<title>�α����ϱ�</title>
	<!-- ��Ʈ��Ʈ�� ���� ���� -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script><!-- jquery ���� -->
	<script src="js/bootstrap.js"></script> <!-- ��Ʈ��Ʈ������ �������ִ� js���� ���� -->
</head>
<body>
	<nav class="navbar navbar-default"> <!-- �׺���̼� -->
		<div class="navbar-header"> 	<!-- �׺���̼� ��� �κ� -->
			<!-- �׺���̼� ��� �ڽ� ���� -->
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<!-- �� ���� ��ư�� ȭ���� �������� ������ ��Ÿ���� -->
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<!-- ��� �ٿ� ������ ��Ÿ���� Ŭ���ϸ� main �������� �̵��Ѵ� -->
			<a class="navbar-brand" href="main.do">JSP �Խ��� �� ����Ʈ</a>
		</div>
		<!-- �Խ��� ���� �̸� ���� ��Ÿ���� �޴� ���� -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.do">����</a></li>
				<li><a href="board.do">�Խ���</a></li>
			</ul>
			<!-- ��� ������ ��Ÿ���� ����ٿ� ���� -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">�����ϱ�<span class="caret"></span></a>
					<!-- ����ٿ� ������ ���� -->	
					<ul class="dropdown-menu">
						<li class="active"><a href="login.do">�α���</a></li><!-- active ���� ������ �Ǿ��ٴ� �ǹ��̴�. 1���� Ȩ�������� 1���� �� �� �ִ�. -->
						<li><a href="join.do">ȸ������</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top:20px;">
				<form method="post" action="login.do">
					<h3 style="text-align:center;">�α��� ȭ��</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="���̵�" name="userID" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="��й�ȣ" name="userPassword" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="�α���">
				</form>
			</div>
		</div>
	</div>
</body> 
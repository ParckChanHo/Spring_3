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
	
	<title>���� ȭ��</title>
	<!-- ��Ʈ��Ʈ�� ���� ���� -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script><!-- jquery ���� -->
	<script src="js/bootstrap.js"></script> <!-- ��Ʈ��Ʈ������ �������ִ� js���� ���� -->
</head>
<body>
	<!-- ���� �Ϸ� -->
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String)session.getAttribute("userID");
		}
	%>
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
			<a class="navbar-brand" href="main.do">Spring �Խ��� �� ����Ʈ</a>
		</div>
		<!-- �Խ��� ���� �̸� ���� ��Ÿ���� �޴� ���� -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="main.do">����</a></li><!-- ���� �������������� ������������� ���� �˷��ֱ�!!! -->
				<li><a href="board.do">�Խ���</a></li>
			</ul>
			<!-- �α����� �Ǿ����� ���� ��쿡�� �α��� �� ȸ�������� �� �� �ֵ��� �Ѵ�!! -->
			<%
				if(userID == null){
					
			%>
			<!-- ��� ������ ��Ÿ���� ����ٿ� ���� -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">�����ϱ�<span class="caret"></span></a>
					<!-- ����ٿ� ������ ���� -->	
					<ul class="dropdown-menu">
						<li><a href="login.do">�α���</a></li><!-- active ���� ������ �Ǿ��ٴ� �ǹ��̴�. 1���� Ȩ�������� 1���� �� �� �ִ�. -->
						<li><a href="join.do">ȸ������</a></li>
					</ul>
				</li>
			</ul>
			<%
				} else{	
			%>
			<!-- ��� ������ ��Ÿ���� ����ٿ� ���� -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">ȸ������<span class="caret"></span></a>
					<!-- ����ٿ� ������ ���� -->	
					<ul class="dropdown-menu">
						<li><a href="logout.do">�α׾ƿ�</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h1>�� ����Ʈ �Ұ�</h1>
				<p>�� �� ����Ʈ�� ��Ʈ��Ʈ������ ���� Spring �� ����Ʈ �Դϴ�.</p>
				<p><a class="btn btn-primary" href="https://github.com/ParckChanHo/Spring_3" role="button">����� ����Ʈ</a></p>
			</div>
		</div>
	</div>

	<div class="container">
		<div id="carousel-example-generic" class="carousel slide"
			data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0"
					class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			</ol>
			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="1.png" alt="���� ��¿� ������ ������ϴ�.">
					<div class="carousel-caption">
						<h3>1 ��° �����Դϴ�.</h3>
					</div>
				</div>
				<div class="item">
					<img src="2.png" alt="���� ��¿� ������ ������ϴ�.">
					<div class="carousel-caption">
						<h3>2 ��° �����Դϴ�.</h3>
					</div>
				</div>
				<div class="item">
					<img src="3.png" alt="���� ��¿� ������ ������ϴ�.">
					<div class="carousel-caption">
						<h3>3 ��° �����Դϴ�.</h3>
					</div>
				</div>
			</div>
			<!-- Controls -->
			<a class="left carousel-control" href="#carousel-example-generic"
				role="button" data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>

</body> 
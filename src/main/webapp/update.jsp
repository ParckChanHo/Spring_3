<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.dto.BoardDTO" %>     
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<!-- ȭ�� ����ȭ -->
	<meta name="viewport" content="width-device-width", initial-scale="1">
	<!-- ��Ʈ ������ ��Ʈ��Ʈ���� �����ϴ� ��ũ -->
	<link rel="stylesheet" href="css/bootstrap.css"><!-- href="css/bootstrap.min.css"�� �����ϴ�!!-->
	<link rel="stylesheet" href="css/custom.css">
	
	<title>�Խ���</title>
	<style type="text/css">
		a { 
			color:#000000;
			text-decoration: none;
		}
		a:hover,a:visited,a:active{
			color:#000000;
			text-decoration: none;
		}
	</style>
 	<!-- a>>> a �±׸� ��ü �����Ѵ�. a:hover�� ������� Ŀ��(���콺 ������)�� ��� ���� �ö� ������ ���õȴ�.-->
	<!-- ��Ʈ��Ʈ�� ���� ���� -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script><!-- jquery ���� -->
	<script src="js/bootstrap.js"></script> <!-- ��Ʈ��Ʈ������ �������ִ� js���� ���� -->
</head>
<body>
	<%
		BoardDTO bbs = (BoardDTO)request.getAttribute("board");
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
			<a class="navbar-brand" href="main.jsp">Spring �Խ��� �� ����Ʈ</a>
		</div>
	</nav>
	<div class="container">
		<div class="container">
		<div class="row">
			<form method="post" action="update.do">
				<table class="table table-striped" style="text-align: center; border:1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="backgroud-color: #eeeeee; text-align:center;">�Խ��� �� ���� ���</th>	
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="�� ����" name="bbsTitle" maxlength="20" value=<%=bbs.getBbsTitle() %>></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="�� ����" name="bbsContent" maxlength="2048" style="height:350px;"><%=bbs.getBbsContent() %></textarea></td>
							<!-- ������ �ʹ� ũ�ų� �۾����� �ʰ� �ϱ� ���ؼ� ���̸� 350px�� ���������� -->
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="bbsID" value=<%=bbs.getBbsID() %>>
				<input type="submit" class="btn btn-primary pull-right" value="�ۼ���"/>
			</form>
		</div>	
		</div>
	</div>
</body> 
</html>
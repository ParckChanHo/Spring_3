<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.dto.BoardDTO" %> 
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 
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
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String)session.getAttribute("userID");
		}
		
		int pageNumber = 1;
		if(request.getAttribute("pageNumber")!= null){
			pageNumber = (int)request.getAttribute("pageNumber");
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
				<li><a href="main.do">����</a></li><!-- ���� �������������� ������������� ���� �˷��ֱ�!!! -->
				<li class="active"><a href="board.do">�Խ���</a></li>
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
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="backgroud-color: #eeeeee; text-align:center;">��ȣ</th>
						<th style="backgroud-color: #eeeeee; text-align:center;">����</th>
						<th style="backgroud-color: #eeeeee; text-align:center;">�ۼ���</th>
						<th style="backgroud-color: #eeeeee; text-align:center;">�ۼ���</th>
					</tr>
				</thead>
				<tbody>
					<%
						ArrayList<BoardDTO> list = (ArrayList<BoardDTO>) request.getAttribute("list");
						for(int i=0; i < list.size(); i++){
					%>
					<tr>
						<td><%= list.get(i).getBbsID() %></td>
						<td><a href="view.do?bbsID=<%=list.get(i).getBbsID() %>"><%=list.get(i).getBbsTitle() %></a></td>
						<td><%=list.get(i).getUserID() %></td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value="${list.get(i).getBbsDate()}"/></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<%
				if(pageNumber != 1){
			%>
			<a href="board.do?pageNumber=<%= pageNumber-1 %>" class="btn btn-success btn-arrow-left">����</a>
			<%
				} if((boolean)request.getAttribute("nextPage")){
			%>
			<a href="board.do?pageNumber=<%= pageNumber+1 %>" class="btn btn-success btn-arrow-right">����</a>
			<%		
				}
			%>
			<a href="write.do" class="btn btn-primary pull-right">�۾���</a>
		</div>
	</div>
</body> 
</html>
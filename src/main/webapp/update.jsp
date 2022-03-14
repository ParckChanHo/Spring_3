<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.dto.BoardDTO" %>     
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<!-- 화면 최적화 -->
	<meta name="viewport" content="width-device-width", initial-scale="1">
	<!-- 루트 폴더에 부트스트랩을 참조하는 링크 -->
	<link rel="stylesheet" href="css/bootstrap.css"><!-- href="css/bootstrap.min.css"도 동일하다!!-->
	<link rel="stylesheet" href="css/custom.css">
	
	<title>게시판</title>
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
 	<!-- a>>> a 태그를 전체 선택한다. a:hover는 사용자의 커서(마우스 포인터)가 요소 위에 올라가 있으면 선택된다.-->
	<!-- 부트스트랩 참조 영역 -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script><!-- jquery 삽입 -->
	<script src="js/bootstrap.js"></script> <!-- 부트스트랩에서 제공해주는 js파일 삽입 -->
</head>
<body>
	<%
		BoardDTO bbs = (BoardDTO)request.getAttribute("board");
	%>
	<nav class="navbar navbar-default"> <!-- 네비게이션 -->
		<div class="navbar-header"> 	<!-- 네비게이션 상단 부분 -->
			<!-- 네비게이션 상단 박스 영역 -->
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<!-- 이 삼줄 버튼은 화면이 좁아지면 우측에 나타난다 -->
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<!-- 상단 바에 제목이 나타나고 클릭하면 main 페이지로 이동한다 -->
			<a class="navbar-brand" href="main.jsp">Spring 게시판 웹 사이트</a>
		</div>
	</nav>
	<div class="container">
		<div class="container">
		<div class="row">
			<form method="post" action="update.do">
				<table class="table table-striped" style="text-align: center; border:1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="backgroud-color: #eeeeee; text-align:center;">게시판 글 수정 양식</th>	
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="20" value=<%=bbs.getBbsTitle() %>></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height:350px;"><%=bbs.getBbsContent() %></textarea></td>
							<!-- 내용이 너무 크거나 작아지지 않게 하기 위해서 높이를 350px로 고정시켜줌 -->
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="bbsID" value=<%=bbs.getBbsID() %>>
				<input type="submit" class="btn btn-primary pull-right" value="글수정"/>
			</form>
		</div>	
		</div>
	</div>
</body> 
</html>
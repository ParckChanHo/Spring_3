<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.dto.BoardDTO" %> 
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<!-- 화면 최적화 -->
	<meta name="viewport" content="width-device-width", initial-scale="1">
	<!-- 루트 폴더에 부트스트랩을 참조하는 링크 -->
	<link rel="stylesheet" href="css/bootstrap.css"><!-- href="css/bootstrap.min.css"도 동일하다!!-->
	<link rel="stylesheet" href="css/custom.css">
	
	<title>글 상세보기</title>
	<style type="text/css">
		a, a:hover { 
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
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String)session.getAttribute("userID");
		}
		
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
			<a class="navbar-brand" href="main.do">Spring 게시판 웹 사이트</a>
		</div>
		<!-- 게시판 제목 이름 옆에 나타나는 메뉴 영역 -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.do">메인</a></li><!-- 현재 접속한페이지가 메인페이지라는 것을 알려주기!!! -->
				<li class="active"><a href="board.do">게시판</a></li>
			</ul>
			<!-- 로그인이 되어있지 않은 경우에만 로그인 및 회원가입을 할 수 있도록 한다!! -->
			<%
				if(userID == null){
					
			%>
			<!-- 헤더 우측에 나타나는 드랍다운 영역 -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<!-- 드랍다운 아이템 영역 -->	
					<ul class="dropdown-menu">
						<li><a href="login.do">로그인</a></li><!-- active 현재 선택이 되었다는 의미이다. 1개의 홈페이지에 1개만 들어갈 수 있다. -->
						<li><a href="join.do">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<%
				} else{	
			%>
			<!-- 헤더 우측에 나타나는 드랍다운 영역 -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<!-- 드랍다운 아이템 영역 -->	
					<ul class="dropdown-menu">
						<li><a href="logout.do">로그아웃</a></li>
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
			<table class="table table-striped" style="text-align: center; border:1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="backgroud-color: #eeeeee; text-align:center;">게시판 글보기</th>	
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width:20%;">글 제목</td>
						<td colspan="2"><%=bbs.getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>")%></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%=bbs.getUserID() %></td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2"><fmt:formatDate pattern = "yyyy-MM-dd" value="${list.get(i).getBbsDate()}"/></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="min-height:200px; text-align:left;"><%=bbs.getBbsContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>")%></td>
					</tr>
				</tbody>
			</table>

			<a href="board.do" class="btn btn-primary">목록 </a>
			<!-- userID = (String)session.getAttribute("userID");  로그인을 하였으면 아이디 값으로 세션이 새롭게 만들어졌음
			따라서 만들어진 세션 값을 userID가 받는 것이다!!!-->
			<%
				if(userID != null && userID.equals(bbs.getUserID())){
			%>
				<a href="update.do?bbsID=<%=bbs.getBbsID() %>" class="btn btn-primary">수정</a>
				<a href="delete.do?bbsID=<%=bbs.getBbsID() %>" class="btn btn-primary" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
			<%
				}
			%>

			<input type="button" class="btn btn-primary pull-right" value="글쓰기" onclick="location.href='write.jsp?userID=<%=userID%>'"/>
		</div>	
	</div>
</body> 
</html>